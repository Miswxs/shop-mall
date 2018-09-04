package com.lj.db.mapper;


import com.lj.db.model.TOrderInfo;
import com.lj.db.model.TOrderInfoPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司:良建商城
 * @功能: Dao
 * @作者:wangxs    
 * @日期:2018-07-09 22:17:07  
 * @版本:1.0
 * @修改:
 */
 
public interface TOrderInfoMapper {

	/**
	 *  新增
	 **/
	int insert(TOrderInfo TAppCaptcha);

	TOrderInfo findOrderInfoById(@Param("id")Long id);

	TOrderInfo findOrderInfoByOrderNo(@Param("orderNo")String orderNo);

	List<TOrderInfo> findOrderInfoByStatus(@Param("order")TOrderInfoPo order,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

	Integer countOrderInfoByStatus(@Param("order")TOrderInfoPo order);

	List<TOrderInfo> findOrderByUserId(@Param("userId") Long userId);

	List<TOrderInfo> findOrderByUserIdAndKeywords(@Param("userId") Long userId,@Param("keywords") String keywords);

	int deletedOrderByIds(@Param("ids") List<String> ids,@Param("userId") Long userId);

	int updateOrderStatus(@Param("id") Long id,@Param("status") String status);

	Integer countSoldGoods(@Param("order")TOrderInfoPo order);

	List<TOrderInfoPo> listSoldOrderInfo(@Param("order")TOrderInfoPo order,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

	List<TOrderInfoPo> exportSoldGoods(@Param("order")TOrderInfoPo order,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

	List<TOrderInfoPo> getOrderDetailsByOrderId(@Param("orderId") Long orderId);

	int updateOrderByWXPay(@Param("order") TOrderInfo orderInfo);

	int saveWxPrepayId(@Param("orderId") Long orderId,@Param("prepayid") String prepayid);
}


