package com.lj.main.controller.pay;


import com.lj.common.enums.ErrorCode;
import com.lj.common.enums.OrderStatus;
import com.lj.common.exception.LJShopException;
import com.lj.common.wxpay.WXPayConfig;
import com.lj.common.wxpay.WXPayConstants;
import com.lj.common.wxpay.WXPayUtil;
import com.lj.db.model.TOrderInfo;
import com.lj.main.util.SessionUtil;
import com.lj.service.order.OrderService;
import com.lj.service.pay.WXPayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wxPay")
public class WXPayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WXPayController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 微信统一下单接口
     * @param orderId
     */
    @RequestMapping("/wxUnifiedorder")
    public Map<String, String> wxUnifiedorder(@RequestParam Long orderId){
        LOGGER.info("微信统一下单接口入参[orderId={}]",orderId);
        TOrderInfo orderInfo=orderService.getOrderInfoByOrderId(orderId);
        LOGGER.info("微信统一下单接口-订单信息:[{}]",orderInfo);
        if (orderInfo == null || "1".equals(orderInfo.getDeleted())){
            throw new LJShopException(ErrorCode.ORDER_NOT_EXIST,"订单信息不存在");
        }
        if (!orderInfo.getStatus().equals(OrderStatus.PENDING_PAY.getValue())){
            throw new LJShopException(ErrorCode.ORDER_STATUS_ERROR,"订单状态不是待付款状态");
        }
        /*if (!total_fee.equals(orderInfo.getTotalPrice().toString())){
            throw new LJShopException(ErrorCode.ERR_API_CUSTOM,"订单金额不正确");
        }*/
        WXPayConfig config = new WXPayConfig();
        String prepayid = orderInfo.getPrepayId();
        try {
            if (!StringUtils.isEmpty(prepayid)){ //已有预支付id
                Map<String, String> _payparam = new HashMap<String, String>();
                _payparam.put("appid",config.getAppID());
                _payparam.put("mch_id",config.getMchID());
                _payparam.put("prepay_id",prepayid);
                _payparam = orderService.buildWxPayParam(_payparam);
                LOGGER.info("微信调起支付接口-请求参数[{}]",_payparam);
                return _payparam;
            }
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "良建商城-购买商品");
            data.put("out_trade_no", orderInfo.getSaleOrderNo());
            data.put("fee_type", "CNY");
            data.put("total_fee",String.valueOf((int)(orderInfo.getTotalPrice().doubleValue()*100)));
            data.put("spbill_create_ip", SessionUtil.getIp());
            data.put("notify_url", WXPayConstants.NOTIFY_URL);
            data.put("trade_type", "APP");

            WXPayService wxpay = new WXPayService(config);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            LOGGER.info("微信统一下单接口-返回信息[{}]",resp);
            prepayid = resp.get("prepay_id");
            if (!StringUtils.isEmpty(prepayid)) {
                orderService.saveWxPrepayId(orderInfo.getId(),prepayid);
                Map<String, String> _payparam = orderService.buildWxPayParam(resp);
                LOGGER.info("微信调起支付接口-请求参数[{}]",_payparam);
                return _payparam;
            }
        } catch (Exception e) {
            LOGGER.error("微信统一下单接口调用失败,{}",e);
        }
        return null;
    }

    @RequestMapping("/notify")
    public void wxPayNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        LOGGER.info("进入微信支付回调函数/wxPay/notify");
        Map<String,String> rep_param = new HashMap<String, String>();
        String rep_param_xml="";
        try {
            InputStream in = request.getInputStream();
            String _param = InputStream2String(in);
            LOGGER.info("微信支付回调函数请求参数xml[{}]", _param);
            WXPayConfig config = new WXPayConfig();
            WXPayService wxpay = new WXPayService(config);
            Map<String, String> responseXml = wxpay.processResponseXml(_param);
            LOGGER.info("微信支付回调函数请求参数map[{}]", responseXml);
            if (!WXPayConstants.SUCCESS.equals(responseXml.get("result_code"))) {
                rep_param.put("return_code",WXPayConstants.FAIL);
                rep_param.put("return_msg","微信支付回调函数result_code不为SUCCESS");
                rep_param_xml = WXPayUtil.mapToXml(rep_param);
                LOGGER.error("微信支付回调函数返参[{}]",rep_param_xml);
                response.getWriter().write(rep_param_xml);
                return;
            }
            String tradeid = responseXml.get("transaction_id");
            if (StringUtils.isEmpty(tradeid)) {
                rep_param.put("return_code",WXPayConstants.FAIL);
                rep_param.put("return_msg","微信支付回调函数微信支付单号为空");
                rep_param_xml = WXPayUtil.mapToXml(rep_param);
                LOGGER.error("微信支付回调函数返参[{}]",rep_param_xml);
                response.getWriter().write(rep_param_xml);
                return;
            }
            /** 自定义逻辑开始；校验订单状态、金额 begin */
            String orderno = responseXml.get("out_trade_no");
            String total_fee = responseXml.get("total_fee");
            TOrderInfo orderInfo = orderService.findOrderInfoByOrderNo(orderno);
            if (orderInfo == null) {
                rep_param.put("return_code",WXPayConstants.FAIL);
                rep_param.put("return_msg","微信支付回调函数-系统订单不存在");
                rep_param_xml = WXPayUtil.mapToXml(rep_param);
                LOGGER.error("微信支付回调函数返参[{}]",rep_param_xml);
                response.getWriter().write(rep_param_xml);
                return;
            }
            String statusInDB = orderInfo.getStatus();
            if (!statusInDB.equals(OrderStatus.PENDING_PAY.getValue())) {
                rep_param.put("return_code",WXPayConstants.SUCCESS);
                rep_param.put("return_msg","OK");
                rep_param_xml = WXPayUtil.mapToXml(rep_param);
                LOGGER.info("微信支付回调函数返参[{}]",rep_param_xml);
                response.getWriter().write(rep_param_xml);
                return;
            }
            String order_amount = String.valueOf((int) (orderInfo.getTotalPrice().doubleValue() * 100));
            if (!total_fee.equals(order_amount)) {
                rep_param.put("return_code",WXPayConstants.FAIL);
                rep_param.put("return_msg","微信支付回调函数-微信支付金额与系统内订单的支付金额不一致");
                rep_param_xml = WXPayUtil.mapToXml(rep_param);
                LOGGER.error("微信支付回调函数返参[{}]",rep_param_xml);
                response.getWriter().write(rep_param_xml);
                return;
            }
            orderInfo.setTradeId(tradeid);
            orderInfo.setUpdatedBy("wxpay");
            orderInfo.setStatus(OrderStatus.PENDING_DELIVERY.getValue());
            orderService.orderPayedCallBack(orderInfo);

            rep_param.put("return_code",WXPayConstants.SUCCESS);
            rep_param.put("return_msg","OK");
            rep_param_xml = WXPayUtil.mapToXml(rep_param);
            LOGGER.info("微信支付回调函数返参[{}]",rep_param_xml);
            response.getWriter().write(rep_param_xml);
            return;
            /** 自定义逻辑开始；校验订单状态、金额 end */
        }catch (Exception e){
            LOGGER.error("微信支付回调函数出错");
            LOGGER.error(e.getMessage(),e);
            rep_param.put("return_code",WXPayConstants.FAIL);
            rep_param.put("return_msg",e.getMessage());
            rep_param_xml = WXPayUtil.mapToXml(rep_param);
            LOGGER.error("微信支付回调函数返参[{}]",rep_param_xml);
            response.getWriter().write(rep_param_xml);
            return;
        }
    }

    private String InputStream2String(InputStream in) {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(in, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("WXPayController.InputStream2String輸入流转换失败,[{}]",e);
        }
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.info("WXPayController.InputStream2String輸入流转换失败,[{}]",e);
        }
        return sb.toString();
    }

}
