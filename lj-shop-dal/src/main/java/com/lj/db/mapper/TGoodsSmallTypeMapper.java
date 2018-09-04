package com.lj.db.mapper;

import com.github.pagehelper.Page;
import com.lj.db.model.TGoodsSmallType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TGoodsSmallTypeMapper {
    /**
	 *  新增良建商城
	 **/
	public int insert(TGoodsSmallType lj);
        /**
	 *  修改良建商城
	 **/
	public int update(TGoodsSmallType lj);
	/**
	 *  批量新增良建商城
	 **/
	public void insertList(List<TGoodsSmallType> list);
	/**
	 *  批量更新良建商城
	 **/
	public void updateList(List<TGoodsSmallType> list);
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
	public List<TGoodsSmallType> getHaveLjList(List<TGoodsSmallType> list);
	/**
	 *  分页列表良建商城列表
	 **/
	public Page<TGoodsSmallType> getLjList(Map map);

	/**
	 * 根据ID获取良建商城
	 */
	public TGoodsSmallType getLjById(Integer id);

	/**
	 *  良建商城ID列表
	 **/
	public List<String> getIdList();
	
	/**
	 * 分页查询数据
	 * @param map
	 * @return
	 */
	public List<TGoodsSmallType> pageList(Map<String, Object> map);
	
	/**
	 * 查询出总记录数
	 * @param bigTypeName
	 * @return
	 */
	public int getLjCount(@Param("smallTypeName") String smallTypeName);

	List<TGoodsSmallType> findGoodsTypeByMidIds(@Param("midIds") List<Integer> midIds);
}
