package com.lj.service.order;

import com.google.common.collect.Lists;
import com.lj.common.enums.ErrorCode;
import com.lj.common.enums.OrderStatus;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.PageResp;
import com.lj.common.util.BeanUtil;
import com.lj.common.util.DateHelper;
import com.lj.common.util.RandomNum;
import com.lj.common.util.WorkbookUtil;
import com.lj.common.wxpay.WXPayConstants;
import com.lj.common.wxpay.WXPayUtil;
import com.lj.db.mapper.*;
import com.lj.db.model.TOrderGoods;
import com.lj.db.model.TOrderInfo;
import com.lj.db.model.TOrderInfoPo;
import com.lj.domain.dto.GoodsDto;
import com.lj.domain.dto.OrderGoodsDto;
import com.lj.domain.dto.OrderInfoDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单相关
 */
@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private TOrderInfoMapper orderInfoMapper;
    @Autowired
    private TOrderGoodsMapper orderGoodsMapper;
    @Autowired
    private TShopCartMapper shopCartMapper;
    @Autowired
    private TGoodsMapper goodsMapper;
    @Autowired
    private TUserMapper userMapper;

    public PageResp<OrderInfoDto> listOrderInfoByStatus(OrderInfoDto order,Integer offset,Integer pageSize){
        TOrderInfoPo orderInfoPo = BeanUtil.copyProperties(order,TOrderInfoPo.class);
        Integer total = orderInfoMapper.countOrderInfoByStatus(orderInfoPo);
        List<OrderInfoDto> list = Lists.newArrayListWithCapacity(pageSize);
        // 分页已结束（此次分页请求没有数据返回）
        if(offset >= total){
            return new PageResp<OrderInfoDto>(list,total);
        }
        if (total>0){
            // 订单信息
            List<TOrderInfo> orderInfoList = orderInfoMapper.findOrderInfoByStatus(orderInfoPo,offset,pageSize);
            List<String> orderNos = orderInfoList.stream().map(o->o.getSaleOrderNo()).collect(Collectors.toList());
            // 商品信息
            List<TOrderGoods> orderGoodsList =orderGoodsMapper.findByOrderNos(orderNos,null);
            orderGoodsList.stream().collect(Collectors.groupingBy(g -> g.getOrderNo())).forEach((k, v) -> {
                TOrderInfo orderTmp = orderInfoList.stream().filter(o->o.getSaleOrderNo().equals(k)).findAny().orElse(null);
                OrderInfoDto orderInfoDto = BeanUtil.copyProperties(orderTmp,OrderInfoDto.class);
                orderInfoDto.setGoodsList(BeanUtil.copyBeans(v,OrderGoodsDto.class));
                list.add(orderInfoDto);
            });
            //list.sort((o1, o2) -> o2.getId().intValue() - o1.getId().intValue());
        }
        return new PageResp<OrderInfoDto>(list,total);
    }

    public PageResp<OrderInfoDto> listSoldItems(TOrderInfoPo req,Integer offset,Integer pageSize){
        Integer total = orderInfoMapper.countSoldGoods(req);
        List<OrderInfoDto> list = Lists.newArrayListWithCapacity(pageSize);
        if(offset >= total){
            return new PageResp<OrderInfoDto>(list,total);
        }
        if (total>0){
            // 订单信息
            List<TOrderInfoPo> orderInfoList = orderInfoMapper.listSoldOrderInfo(req,offset,pageSize);
            List<String> orderNos = orderInfoList.stream().map(o->o.getSaleOrderNo()).collect(Collectors.toList());
            // 商品信息
            List<TOrderGoods> orderGoodsList =orderGoodsMapper.findByOrderNos(orderNos,req.getCreatedBy());
            orderGoodsList.stream().collect(Collectors.groupingBy(g -> g.getOrderNo())).forEach((k, v) -> {
                TOrderInfoPo orderTmp = orderInfoList.stream().filter(o->o.getSaleOrderNo().equals(k)).findAny().orElse(null);
                OrderInfoDto orderInfoDto = BeanUtil.copyProperties(orderTmp,OrderInfoDto.class);
                orderInfoDto.setGoodsList(BeanUtil.copyBeans(v,OrderGoodsDto.class));
                list.add(orderInfoDto);
            });
        }
        return new PageResp<OrderInfoDto>(list,total);
    }

    /**
     * 计算该用户各状态的订单数量
     * @param id
     * @return
     */
    public Map<String,Integer> getOrdersCountStatus(Long id,String keywords){
        List<TOrderInfo> orders = null;
        if (StringUtils.isBlank(keywords)){
            orders = orderInfoMapper.findOrderByUserId(id);
        }else {
            orders = orderInfoMapper.findOrderByUserIdAndKeywords(id,keywords);
        }
        Map<String,Integer> map = new HashMap<String,Integer>();
        if (!CollectionUtils.isEmpty(orders)){
            map = orders.stream().collect(Collectors.groupingBy(o ->o.getStatus(),Collectors.summingInt(o->1)));
        }
        map.put("all",orders.size());
        return map;
    }

    /**
     * 删除订单
     * @param ids 订单表的主键id
     * @param userId
     */
    public void deleteOrders(List<String> ids,Long userId){
        orderInfoMapper.deletedOrderByIds(ids,userId);
    }

    /**
     * 生成订单，返回订单表的主键ID
     * @param userId
     * @param orderGoodsDtos
     * @return
     */
    @Transactional
    public TOrderInfo createOrder(Long userId,OrderInfoDto orderInfoDto,List<OrderGoodsDto> orderGoodsDtos){
        // 创建订单
        String orderNo = generateOrderNo(userId);
        TOrderInfo orderInfo = BeanUtil.copyProperties(orderInfoDto,TOrderInfo.class);
        orderInfo.setSaleOrderNo(orderNo);
        orderInfo.setCustomerId(userId);
        orderInfo.setStatus(OrderStatus.PENDING_PAY.getValue());
        Integer totalGoodsNum =orderGoodsDtos.stream().mapToInt(o->o.getGoodsNum()).sum();
        BigDecimal totalPrice = new BigDecimal(orderGoodsDtos.stream().mapToDouble(o->o.getHistGoodsPrice().doubleValue()*o.getGoodsNum()).sum());
        orderInfo.setTotalGoodsNum(totalGoodsNum);
        orderInfo.setTotalPrice(totalPrice);
        orderInfo.setCreatedBy(userId.toString());
        orderInfo.setUpdatedBy(userId.toString());
        orderInfoMapper.insert(orderInfo);
        orderGoodsMapper.batchInsert(BeanUtil.copyBeans(orderGoodsDtos,TOrderGoods.class),orderNo);
        // 删除购物车的东西
        List<Long> gIds = orderGoodsDtos.stream().map(o->o.getGoodsNo()).collect(Collectors.toList());
        shopCartMapper.deleteGoodsByIds(userId,gIds,"1");
        return orderInfo;
    }

    /**
     * 生成订单编号：时间（到毫秒）+用户id+3位随机数
     * @param userId
     * @return
     */
    public String generateOrderNo(Long userId){
        return DateHelper.getCurrentTime("yyyyMMddHHmmssSSS")+String.valueOf(userId)+ RandomNum.createRandomString(3);
    }

    /**
     * 商品详情
     * @param id
     * @return
     */
    public GoodsDto getGoodsDetail(Long id){
        return BeanUtil.copyProperties(goodsMapper.findGoodsById(id), GoodsDto.class);
    }

    /**
     * 根据商品分类获取商品列表
     * @param goodsType
     * @param offset
     * @param pageSize
     * @return
     */
    public PageResp<GoodsDto> getGoodsListByGoodsType(String catId,String catType,String searchTxt,Integer offset,Integer pageSize){
        Integer total = goodsMapper.countGoodsListByGoodsType(catId,catType,searchTxt);
        List<GoodsDto> list = Lists.newArrayListWithCapacity(pageSize);
        // 分页已结束（此次分页请求没有数据返回）
        if(offset >= total){
            return new PageResp<GoodsDto>(list,total);
        }
        if (total > 0){
            list = BeanUtil.copyBeans(goodsMapper.getGoodsListByGoodsType(catId,catType,searchTxt,offset,pageSize),GoodsDto.class);
        }
        return new PageResp<GoodsDto>(list,total);
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    public int updateOrderStatus(Long id,String status){
        TOrderInfo orderInfo = orderInfoMapper.findOrderInfoById(id);
        if (orderInfo == null || orderInfo.getDeleted().equals("1")){
            throw new LJShopException(ErrorCode.ORDER_NOT_EXIST,"订单不存在");
        }
        String statusInDB = orderInfo.getStatus();
        String nextStatus = OrderStatus.getEnum(statusInDB).getNextValue();
        if ("done".equals(status) || !nextStatus.equals(status)){
            LOGGER.error("订单[{}]状态流转不正确，当前流转为[{}->{}]，正确应为[{}->{}]",id,statusInDB,status,statusInDB,nextStatus);
            throw new LJShopException(ErrorCode.ORDER_STATUS_ERROR,"订单状态流转不正确");
        }
        return orderInfoMapper.updateOrderStatus(id,status);
    }

    public Workbook exportSoldGoods(TOrderInfoPo orderInfoPo) throws Exception{
        long time = System.currentTimeMillis();
        List<TOrderInfoPo> list = orderInfoMapper.exportSoldGoods(orderInfoPo,0, WorkbookUtil.IMPORT_MAX_ROWS);
        String[] headers = new String[]{
                "订单编号",
                "订单总金额",
                "订单创建时间",
                "订单状态",
                "买家昵称",
                "商品编号",
                "商品名称",
                "商品单价",
                "商品数量"};
        String[] columns = new String[]{
                "saleOrderNo",
                "totalPrice",
                "createdAt",
                "status",
                "customerName",
                "goodsNo",
                "histGoodsName",
                "histGoodsPrice",
                "goodsNum"};
        List<Map<String, Object>> dataset = Lists.newArrayListWithExpectedSize(list.size());
        int seri = 0;
        Map<String, Object> convertBean = null;
        for (TOrderInfoPo dto : list) {
            dto.setStatus(OrderStatus.getEnum(dto.getStatus()).getDesc());
            convertBean = BeanUtil.convertBean(dto);
            convertBean.put("seri", ++seri);
            dataset.add(convertBean);
        }
        Workbook workbook = WorkbookUtil.getExcelXlsx(headers,columns,dataset);
        LOGGER.info("完成{}个订单数据导出，总共耗时:{}", list.size(), (System.currentTimeMillis() - time));
        return workbook;
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    public OrderInfoDto getOrderDetailsByOrderId(Long orderId){
        List<TOrderInfoPo> orderInfoPos = orderInfoMapper.getOrderDetailsByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderInfoPos)){
            throw new LJShopException(ErrorCode.ORDER_NOT_EXIST,"订单详情不存在");
        }
        List<OrderInfoDto> orderInfoDtoList = Lists.newArrayListWithCapacity(1);
        orderInfoPos.stream().collect(Collectors.groupingBy(g -> g.getSaleOrderNo())).forEach((k, v) -> {
            OrderInfoDto orderInfoDto = BeanUtil.copyProperties(v.get(0),OrderInfoDto.class);
            orderInfoDto.setGoodsList(BeanUtil.copyBeans(v,OrderGoodsDto.class));
            orderInfoDtoList.add(orderInfoDto);
        });
        return orderInfoDtoList.get(0);
    }

    /**
     * 订单支付成功后的回调方法
     * 减库存、更新订单状态、更新会员积分
     */
    @Transactional
    public void orderPayedCallBack(TOrderInfo orderInfo){
        orderInfoMapper.updateOrderByWXPay(orderInfo);
        goodsMapper.decreaseGoodsStock(orderInfo.getSaleOrderNo());
        userMapper.updateMemberPoint(orderInfo.getCustomerId(), String.valueOf(orderInfo.getTotalPrice().intValue()));
    }

    /**
     * 通过主键查找订单信息
     * @param orderId
     * @return
     */
    public TOrderInfo getOrderInfoByOrderId(Long orderId){
        return orderInfoMapper.findOrderInfoById(orderId);
    }

    /**
     * 通过订单号查询订单信息
     * @param orderNo
     * @return
     */
    public TOrderInfo findOrderInfoByOrderNo(String orderNo){
        return orderInfoMapper.findOrderInfoByOrderNo(orderNo);
    }

    public Map<String,String> buildWxPayParam(Map<String,String> unifiedorder) throws Exception{
        Map<String,String> _param = new HashMap<String, String>();
        _param.put("appid",unifiedorder.get("appid"));
        _param.put("partnerid",unifiedorder.get("mch_id"));
        _param.put("prepayid",unifiedorder.get("prepay_id"));
        _param.put("package","Sign=WXPay");
        _param.put("noncestr", WXPayUtil.generateNonceStr());
        _param.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        _param.put("sign",WXPayUtil.generateSignature(_param, WXPayConstants.KEY, WXPayConstants.SignType.HMACSHA256));
        return _param;
    }

    /**
     * 保存预支付id
     * @param orderId
     * @param prepayid
     */
    @Transactional
    public void saveWxPrepayId(Long orderId,String prepayid){
        orderInfoMapper.saveWxPrepayId(orderId,prepayid);
    }
}
