package com.lj.db.mapper;


import com.lj.db.model.TOrderGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司:
 * @功能:良建商城 Dao
 * @作者:leiquan    
 * @日期:2018-07-24 14:26:39  
 * @版本:1.0
 * @修改:
 */
 
public interface TOrderGoodsMapper {

        /**
	 *  新增良建商城
	 **/
	int insert(TOrderGoods tOrderGoods);

	List<TOrderGoods> findByOrderNos(@Param("orderNos") List<String> orderNos,@Param("sellerId") String sellerId);

	int batchInsert(@Param("orderGoods") List<TOrderGoods> orderGoods,@Param("orderNo") String orderNo);

}


