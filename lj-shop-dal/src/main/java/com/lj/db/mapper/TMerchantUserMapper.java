package com.lj.db.mapper;

import com.lj.db.model.TMerchantUser;
import org.apache.ibatis.annotations.Param;

/**
 * @公司:
 * @功能:良建商城 Dao
 * @作者:leiquan    
 * @日期:2018-08-16 09:53:18  
 * @版本:1.0
 * @修改:
 */
 
public interface TMerchantUserMapper {

	/**
	 *  新增良建商城
	 **/
	int insert(TMerchantUser lj);

	TMerchantUser findMerchantUserByAccount(@Param("userAccount") String userAccount);

	int updatePwd(@Param("userId") Long userId,@Param("pwd") String pwd);
}


