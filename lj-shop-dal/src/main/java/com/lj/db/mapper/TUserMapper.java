package com.lj.db.mapper;

import com.lj.db.model.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司:良建商城
 * @功能: Dao
 * @作者:wangxs    
 * @日期:2018-07-07 22:41:38  
 * @版本:1.0
 * @修改:
 */
public interface TUserMapper {

	/**
	 *  新增
	 **/
	int insert(TUser user);

	TUser findByCellphone(@Param("cellphone") String cellphone);

	TUser findByUserId(@Param("id") Long id);

	int editUserInfo(@Param("user") TUser user);

	int modifyPwd(@Param("cellphone") String cellphone,@Param("pwd") String pwd);

	List<TUser> pageUserInfo(@Param("searchTxt") String searchTxt,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

	Integer countUserInfo(@Param("searchTxt") String searchTxt);

	int deleteUserById(@Param("userId") Long userId);

	int updateMemberPoint(@Param("userId") Long userId,@Param("points") String points);
}


