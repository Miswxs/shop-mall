package com.lj.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.lj.db.model.TGoodsMidType;

public interface TGoodsMidTypeMapper {
   
	  /**
		 *  新增良建商城
		 **/
		public int insert(TGoodsMidType lj);
	        /**
		 *  修改良建商城
		 **/
		public int update(TGoodsMidType lj);
		/**
		 *  批量新增良建商城
		 **/
		public void insertList(List<TGoodsMidType> list);
		/**
		 *  批量更新良建商城
		 **/
		public void updateList(List<TGoodsMidType> list);
		/**
		 *  是否存在良建商城
		 **/
		public int isHave(Integer id);

		/**
		 *  删除良建商城
		 **/
		public int delete(Integer id);

		/**
		 * 标识删除
		 */
		public int updateToDelete(Integer id);

		/**
		 *  已经存在良建商城列表
		 **/
		public List<TGoodsMidType> getHaveLjList(List<TGoodsMidType> list);
		/**
		 *  分页列表良建商城列表
		 **/
		public Page<TGoodsMidType> getLjList(Map map);

		/**
		 * 根据ID获取良建商城
		 */
		public TGoodsMidType getLjById(Integer id);

		/**
		 *  良建商城ID列表
		 **/
		public List<String> getIdList();
		
		/**
		 * 分页查询数据
		 * @param map
		 * @return
		 */
		public List<TGoodsMidType> pageList(Map<String, Object> map);
		
		/**
		 * 查询出总记录数
		 * @param bigTypeName
		 * @return
		 */
		public int getLjCount(@Param("midTypeName") String midTypeName);

		List<TGoodsMidType> findGoodsTypeByBigId(@Param("bigId") Integer bigId);
}
