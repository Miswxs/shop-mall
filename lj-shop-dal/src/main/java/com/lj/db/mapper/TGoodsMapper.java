package com.lj.db.mapper;


import com.lj.db.model.TGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @公司:良建商城
 * @功能: Dao
 * @作者:wangxs    
 * @日期:2018-07-09 22:33:25  
 * @版本:1.0
 * @修改:
 */
 
public interface TGoodsMapper {

	/**
	 *  新增
	 **/
	int insert(TGoods tGoods);
	
	List<TGoods> listGoodsByUserId(@Param("userId") Long userId,@Param("type") String type);
	
	Integer countGoodsByUserId(@Param("userId") Long userId,@Param("type") String type);

	TGoods findGoodsById(@Param("id") long id);

	List<TGoods> getGoodsListByGoodsType(@Param("catId") String catId,@Param("catType") String catType,@Param("searchTxt") String searchTxt,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

	Integer countGoodsListByGoodsType(@Param("catId") String catId,@Param("catType") String catType,@Param("searchTxt") String searchTxt);

	List<TGoods> selectGoodsForRecent();
	
	/**
	 * 统计
	 * @param map
	 * @return
	 */
	int getGoodsCount(Map<String, Object> map);
	
	List<TGoods> pageList(Map<String, Object> map);

	/**
	 * 标识删除
	 */
	int updateToDelete(Integer id);
	
	/**
	 * 修改商品信息
	 **/
	int update(TGoods lj);

	List<TGoods> selectGoodsForHot();

	int decreaseGoodsStock(@Param("orderNo") String orderNo);

}


