package com.lj.main.controller.order;

import com.lj.common.annotation.Login;
import com.lj.common.enums.ErrorCode;
import com.lj.common.enums.OrderStatus;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.PageResp;
import com.lj.common.resp.RespMessage;
import com.lj.common.util.BeanUtil;
import com.lj.common.util.PageUtil;
import com.lj.common.util.ValidateUtil;
import com.lj.common.wxpay.WXPayConfig;
import com.lj.common.wxpay.WXPayConstants;
import com.lj.db.model.TOrderInfo;
import com.lj.db.model.TOrderInfoPo;
import com.lj.domain.dto.BaseUser;
import com.lj.domain.dto.GoodsDto;
import com.lj.domain.dto.OrderGoodsDto;
import com.lj.domain.dto.OrderInfoDto;
import com.lj.main.req.OrderReq;
import com.lj.main.req.OrderSearchReq;
import com.lj.main.util.SessionUtil;
import com.lj.main.util.ThreadLocalUtil;
import com.lj.service.order.OrderService;
import com.lj.service.pay.WXPayService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单相关
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 根据订单状态查询订单信息
     */
    @RequestMapping("/list")
    @Login
    public PageResp<OrderInfoDto> listOrderInfoByStatus(@RequestBody OrderReq req){
        LOGGER.info("/list订单查询入参[{}]",req);

        BaseUser baseUser= ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        PageUtil pageUtil = new PageUtil(req.getCurrentPage(),req.getPageSize());
        OrderInfoDto orderInfoDto= new OrderInfoDto();
        orderInfoDto.setCustomerId(baseUser.getId());
        orderInfoDto.setStatus(req.getOrderStatus());
        orderInfoDto.setSearchTxt(req.getSearchTxt());
        PageResp<OrderInfoDto> orderInfoDtoPageResp = orderService.listOrderInfoByStatus(orderInfoDto,pageUtil.getSkipCount(),pageUtil.getPageSize());

        return orderInfoDtoPageResp;
    }

    /**
     * admin-web 查询已卖出的宝贝
     * @param req
     * @return
     */
    @RequestMapping("/listSoldItems")
    @Login
    public RespMessage listSoldItems(@ModelAttribute OrderSearchReq req){
        LOGGER.info("/orders/listSoldItems入参[{}]",req);
        PageUtil pageUtil =new PageUtil(req.getPageCount(),req.getPageSize());
        BaseUser baseUser = (BaseUser) SessionUtil.getHttpSession().getAttribute("baseUser");
        if (baseUser == null){
            new LJShopException(ErrorCode.UNLOGIN_ERROR,"登录已失效，请重新登录");
        }
        TOrderInfoPo orderReq = BeanUtil.copyProperties(req,TOrderInfoPo.class);
        orderReq.setCreatedBy(baseUser.getId().toString());
        if ("admin".equals(baseUser.getUserName())){
            orderReq.setCreatedBy(null);
        }
        PageResp<OrderInfoDto> orderInfoDtoPageResp = orderService.listSoldItems(orderReq,pageUtil.getSkipCount(),pageUtil.getPageSize());

        pageUtil.setRowCount((int)orderInfoDtoPageResp.getTotal());
        pageUtil.setList(orderInfoDtoPageResp.getContent());
        pageUtil.setPageSize(pageUtil.getPageSize());
        pageUtil.setPageCount(pageUtil.getPageCount());
        pageUtil.setPageIndex(pageUtil.getPageCount()+1);
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("modle", pageUtil);
        return RespMessage.successResp(retMap);
    }


    @RequestMapping("/getOrdersCountStatus")
    @Login
    public Map<String,Integer> getOrdersCountStatus(@RequestBody String keywords){
        LOGGER.info("/orders/getOrdersCountStatus 入参[keywords={}]",keywords);
        BaseUser baseUser= ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        return orderService.getOrdersCountStatus(baseUser.getId(),keywords);
    }
    @RequestMapping("/deleteOrders")
    @Login
    public void deleteOrders(@RequestBody List<String> ids){
        BaseUser baseUser= ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        if (CollectionUtils.isEmpty(ids)){
            throw new LJShopException(ErrorCode.ARGS_MISS_ERROR,"请勾选待删除订单!");
        }
        orderService.deleteOrders(ids,baseUser.getId());
    }
    @RequestMapping("/deleteSingleOrder")
    @Login
    public void deleteSingleOrder(@RequestParam String id){
        BaseUser baseUser= ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        if (StringUtils.isEmpty(id)){
            throw new LJShopException(ErrorCode.ARGS_MISS_ERROR,"请勾选待删除订单!");
        }
        orderService.deleteOrders(Arrays.asList(id),baseUser.getId());
    }

    /**
     * 更新订单状态
     * @param id
     */
    @RequestMapping("/updateOrderStatus")
    @Login
    public void updateOrderStatus(@RequestParam Long id,String status){
        BaseUser baseUser= ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        if (id == null){
            throw new LJShopException(ErrorCode.ARGS_MISS_ERROR,"请先选择订单!");
        }
        orderService.updateOrderStatus(id, OrderStatus.getEnum(status).getValue());
    }

    /**
     * 创建订单，返回订单主键id
     * @param orderReq
     */
    @RequestMapping("/createOrder")
    @Login
    public Map<String, String> createOrder(@RequestBody @Valid OrderReq orderReq){
        BaseUser baseUser= ThreadLocalUtil.currentAccount();
        if (baseUser==null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        ValidateUtil.isNotBlank(orderReq.getHistAddressCellphone(),"收件人手机号不能为空",ErrorCode.ARGS_MISS_ERROR);
        ValidateUtil.isNotBlank(orderReq.getHistAddressDetail(),"收件人地址不能为空",ErrorCode.ARGS_MISS_ERROR);
        ValidateUtil.isNotBlank(orderReq.getHistAddressName(),"收件人姓名不能为空",ErrorCode.ARGS_MISS_ERROR);
        TOrderInfo orderInfo= orderService.createOrder(baseUser.getId(),BeanUtil.copyProperties(orderReq,OrderInfoDto.class),BeanUtil.copyBeans(orderReq.getOrderGoodsReqs(),OrderGoodsDto.class));

        WXPayConfig config = new WXPayConfig();
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "良建商城-购买商品");
        data.put("out_trade_no", orderInfo.getSaleOrderNo());
        data.put("fee_type", "CNY");
        data.put("total_fee", String.valueOf((int)(orderInfo.getTotalPrice().doubleValue()*100)));
        data.put("spbill_create_ip", SessionUtil.getIp());
        data.put("notify_url", WXPayConstants.NOTIFY_URL);
        data.put("trade_type", "APP");
        Map<String, String> _payparam = new HashMap<String, String>();
        _payparam.put("orderId", orderInfo.getId().toString());
        try {
            WXPayService wxpay = new WXPayService(config);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            LOGGER.info("微信统一下单接口-返回信息[{}]",resp);
            String prepayid = resp.get("prepay_id");
            if (!StringUtils.isEmpty(prepayid)) {
                orderService.saveWxPrepayId(orderInfo.getId(),prepayid);
                _payparam.putAll(orderService.buildWxPayParam(resp));
                LOGGER.info("微信调起支付接口-请求参数[{}]", _payparam);
                return _payparam;
            }
        } catch (Exception e) {
            LOGGER.error("微信统一下单接口调用失败,{}",e);
        }
        return _payparam;
    }

    /**
     * 商品详情
     * @param id
     * @return
     */
    @RequestMapping("/getGoodsDetail")
    public GoodsDto getGoodsDetail(@RequestParam Long id){
        return orderService.getGoodsDetail(id);
    }

    /**
     * 根据商品分类跳转列表
     * @param searchTxt
     * @param catId
     * @param catType
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getGoodsListByGoodsType")
    public PageResp<GoodsDto> getGoodsListByGoodsType(@RequestParam(value="searchTxt",required=false) String searchTxt,String catId,String catType,Integer currentPage,Integer pageSize){
        LOGGER.info("orders/getGoodsListByGoodsType入参：[searchTxt={},catId={},catType={},currentPage={},pageSize={}]",searchTxt,catId,catType,currentPage,pageSize);
        ValidateUtil.isNotBlank(catId,"请先选择商品分类",ErrorCode.ARGS_MISS_ERROR);
        PageUtil pageUtil = new PageUtil(currentPage,pageSize);
        return orderService.getGoodsListByGoodsType(catId,catType,searchTxt,pageUtil.getSkipCount(),pageUtil.getPageSize());
    }

    @RequestMapping("/getGoodsListByKeywords")
    public PageResp<GoodsDto> getGoodsListByKeywords(@RequestParam(value="searchTxt",required=false) String searchTxt,Integer currentPage,Integer pageSize){
        PageUtil pageUtil = new PageUtil(currentPage,pageSize);
        return orderService.getGoodsListByGoodsType(null,null,searchTxt,pageUtil.getSkipCount(),pageUtil.getPageSize());
    }

    @RequestMapping("/exportSoldGoods")
    @Login
    public void exportSoldGoods(@ModelAttribute OrderSearchReq req, HttpServletResponse response) throws Exception{
        BaseUser baseUser = (BaseUser) SessionUtil.getHttpSession().getAttribute("baseUser");
        if (baseUser == null){
            new LJShopException(ErrorCode.UNLOGIN_ERROR,"登录已失效，请重新登录");
        }
        TOrderInfoPo orderReq = BeanUtil.copyProperties(req,TOrderInfoPo.class);
        orderReq.setCreatedBy(baseUser.getId().toString());
        if ("admin".equals(baseUser.getUserName())){
            orderReq.setCreatedBy(null);
        }
        SXSSFWorkbook workbook = null;
        try {
            workbook = (SXSSFWorkbook)orderService.exportSoldGoods(orderReq);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String exportFileName = "订单数据" + format.format(new Date())+ ".xlsx";
            // 设置文件MIME类型
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(exportFileName, "UTF-8"));
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.getWriter().write(e.getMessage());
        }
    }

    /**
     * 订单详情
     * @param orderId
     */
    @RequestMapping("/getOrderDetailsByOrderId")
    @Login
    public OrderInfoDto getOrderDetailsByOrderId(@RequestParam Long orderId){
        return orderService.getOrderDetailsByOrderId(orderId);
    }

}
