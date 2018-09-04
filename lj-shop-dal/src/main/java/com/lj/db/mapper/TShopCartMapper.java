package com.lj.db.mapper;

import com.lj.db.model.TGoods;
import com.lj.db.model.TShopCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司:
 * @功能:良建商城 Dao
 * @作者:leiquan    
 * @日期:2018-07-27 17:56:34  
 * @版本:1.0
 * @修改:
 */
 
public interface TShopCartMapper {

	/**
	 *  新增良建商城
	 **/
	int insert(TShopCart lj);

	int update(TShopCart lj);

	int decreaseCart(TShopCart lj);

	List<TGoods> findShopCartByUserId(@Param("userId") Long userId,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

	Integer countShopCartByUserId(@Param("userId") Long userId);

	Integer deleteGoodsByIds(@Param("userId") Long userId ,@Param("gIds") List<Long> gIds,@Param("type") String type);

	List<TGoods> getShopCartByIds(@Param("userId") Long userId ,@Param("cartIds") List<Long> cartIds);

	Integer countGoodsNumByUserId(@Param("userId")Long userId);
}


