package com.lj.db.mapper;


import com.lj.db.model.TReceiveAddr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @公司:良建商城
 * @功能: Dao
 * @作者:wangxs    
 * @日期:2018-07-10 22:39:43  
 * @版本:1.0
 * @修改:
 */
 
public interface TReceiveAddrMapper {

	/**
	 *  新增
	 **/
	int insert(TReceiveAddr tReceiveAddr);

	int update(@Param("tReceiveAddr") TReceiveAddr tReceiveAddr);

	List<TReceiveAddr> findAddrByUserId(@Param("userId") Long userId);

	TReceiveAddr findAddressById(@Param("id") Long id);

	int setDefaultAddr(@Param("id") Long id);

	int setNotDefaultAddr();

	int deleteAddr(@Param("id") Long id);
}


