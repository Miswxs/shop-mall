package com.lj.db.mapper;


import com.lj.db.model.TMessageConfig;
import org.apache.ibatis.annotations.Param;

/**
 * @公司:良建商城
 * @功能: Dao
 * @作者:wangxs    
 * @日期:2018-07-08 14:00:02  
 * @版本:1.0
 * @修改:
 */
 
public interface TMessageConfigMapper {

	/**
	 *  新增
	 **/
	public int insert(TMessageConfig TAppCaptcha);

	public TMessageConfig getMessageConfigByType(@Param("type") String type);

}


