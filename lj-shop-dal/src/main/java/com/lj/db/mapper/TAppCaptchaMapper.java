package com.lj.db.mapper;


import com.lj.db.model.TAppCaptcha;
import org.apache.ibatis.annotations.Param;

/**
 * @公司:良建商城
 * @功能: Dao
 * @作者:wangxs    
 * @日期:2018-07-07 23:46:28  
 * @版本:1.0
 * @修改:
 */
 
public interface TAppCaptchaMapper {

	/**
	 *  新增
	 **/
	public int insert(TAppCaptcha TAppCaptcha);

	/**
	 * 通过手机号获取最新的验证码
	 * @return cellphone
	 */
	public TAppCaptcha getLastCaptchaByCellphone(@Param("cellphone") String cellphone);

}


