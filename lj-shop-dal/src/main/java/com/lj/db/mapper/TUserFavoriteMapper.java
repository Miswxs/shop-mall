package com.lj.db.mapper;

import com.lj.db.model.TUserFavorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司:良建商城
 * @功能: Dao
 * @作者:wangxs    
 * @日期:2018-07-15 13:55:26  
 * @版本:1.0
 * @修改:
 */
 
public interface TUserFavoriteMapper {

        /**
	 *  新增
	 **/
	int insert(TUserFavorite userFavorite);

	int updateUserFavoriteDeleted(@Param("userId") Long userId,@Param("goodsId") String goodsId,@Param("deleted") String deleted,@Param("type") String type);

	int batchDeleteFavorite(@Param("userId") Long userId, @Param("gIds") List<Long> gIds, @Param("type") String type);

}


