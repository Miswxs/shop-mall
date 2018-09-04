package com.lj.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.lj.common.util.PageUtil;
import com.lj.db.model.TGoodsBigType;

/**
 * @公司:王晓深专属
 * @功能:良建商城 Dao
 * @作者:leiquan
 * @日期:2018-07-09 22:34:16
 * @版本:1.0
 * @修改:
 */

public interface TGoodsBigTypeMapper {

	/**
	 * 新增良建商城
	 **/
	public int insert(TGoodsBigType lj);

	/**
	 * 修改良建商城
	 **/
	public int update(TGoodsBigType lj);

	/**
	 * 批量新增良建商城
	 **/
	public void insertList(List<TGoodsBigType> list);

	/**
	 * 批量更新良建商城
	 **/
	public void updateList(List<TGoodsBigType> list);

	/**
	 * 是否存在良建商城
	 **/
	public int isHave(Integer id);

	/**
	 * 删除良建商城
	 **/
	public int delete(Integer id);

	/**
	 * 标识删除
	 */
	public int updateToDelete(Integer id);

	/**
	 * 已经存在良建商城列表
	 **/
	public List<TGoodsBigType> getHaveLjList(List<TGoodsBigType> list);

	/**
	 * 分页列表良建商城列表
	 **/
	public Page<TGoodsBigType> getLjList(Map map);

	/**
	 * 根据ID获取良建商城
	 */
	public TGoodsBigType getLjById(Integer id);

	/**
	 * 良建商城ID列表
	 **/
	public List<String> getIdList();
	
	/**
	 * 分页查询数据
	 * @param map
	 * @return
	 */
	public List<TGoodsBigType> pageList(Map<String, Object> map);
	
	/**
	 * 查询出总记录数
	 * @param bigTypeName
	 * @return
	 */
	public int getLjCount(@Param("bigTypeName") String bigTypeName);

	List<TGoodsBigType> getAllGoodsBigType();

	List<TGoodsBigType> selectGoodsForScroll();

}
