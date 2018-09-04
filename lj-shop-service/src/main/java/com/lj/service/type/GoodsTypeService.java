package com.lj.service.type;

import com.google.common.collect.Lists;
import com.lj.common.enums.ErrorCode;
import com.lj.common.resp.RespMessage;
import com.lj.common.util.BeanUtil;
import com.lj.common.util.PageUtil;
import com.lj.db.mapper.TGoodsBigTypeMapper;
import com.lj.db.mapper.TGoodsMidTypeMapper;
import com.lj.db.mapper.TGoodsSmallTypeMapper;
import com.lj.db.model.TGoodsBigType;
import com.lj.db.model.TGoodsMidType;
import com.lj.db.model.TGoodsSmallType;
import com.lj.domain.dto.CategoryDto;
import com.lj.domain.dto.GoodsMidTypeDto;
import com.lj.domain.dto.GoodsSmallTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodsTypeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsTypeService.class);

	/**
	 * 大类Dao接口
	 */
	@Autowired
	private TGoodsBigTypeMapper goodsBigTypeMapper;

	/**
	 * 中类Dao接口
	 */
	@Autowired
	private TGoodsMidTypeMapper goodsMidTypeMapper;

	/**
	 * 小类Dao 接口
	 */
	@Autowired
	private TGoodsSmallTypeMapper goodsSmallTypeMapper;

	/**
	 * 获取大类数据
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage getBigType(Integer id) {

		if (id <= 0) {
			LOGGER.info("GoodsTypeService.getBigType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		TGoodsBigType result = goodsBigTypeMapper.getLjById(id);
		if (result !=null) {
			LOGGER.info("GoodsTypeService.getBigType insert data is success|request={}", result);
			return RespMessage.successResp("查询数据成功", result);
		} else {
			LOGGER.info("GoodsTypeService.getBigType insert data is fail|request={}", id);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}
	
	
	/**
	 * 插入大类数据
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage insertBigType(TGoodsBigType bigType) {

		if (bigType == null) {
			LOGGER.info("GoodsTypeService.insertBigType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsBigTypeMapper.insert(bigType);
		if (result > 0) {
			LOGGER.info("GoodsTypeService.insertBigType insert data is success|request={}", bigType);
			return RespMessage.successResp("插入成功", "");
		} else {
			LOGGER.info("GoodsTypeService.insertBigType insert data is fail|request={}", bigType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 修改大类信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage editBigType(TGoodsBigType bigType) {
		if (bigType == null || bigType.getId() <= 0) {
			LOGGER.info("GoodsTypeService.editBigType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsBigTypeMapper.update(bigType);
		if (result > 0) {
			LOGGER.info("GoodsTypeService.editBigType edit data is success|request={}", bigType);
			return RespMessage.successResp("修改成功", "");
		} else {
			LOGGER.info("GoodsTypeService.editBigType edit data is fail|request={}", bigType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 删除大类类别信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage deleteBigType(TGoodsBigType bigType) {
		if (bigType == null || bigType.getId() <= 0) {
			LOGGER.info("GoodsTypeService.deletebigType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsBigTypeMapper.updateToDelete(bigType.getId());
		if (result > 0) {
			LOGGER.info("GoodsTypeService.deletebigType delete data is success|request={}", bigType);
			return RespMessage.successResp("删除成功", "");
		} else {
			LOGGER.info("GoodsTypeService.deletebigType delete data is fail|request={}", bigType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 分页查询大类数据
	 * 
	 * @param pageSize
	 * @param pageCount
	 * @param where
	 * @return
	 */
	@SuppressWarnings("unused")
	public RespMessage pageListBigType(int pageSize, int pageCount, String where) {
		Map<String, Object> map = new HashMap<String, Object>();
		int skipCount = (pageCount-1) * pageSize;
		map.put("skipCount", skipCount);
		map.put("pageSize", pageSize);
		if (where != null && !where.equals("")) {
			map.put("bigTypeName", where);
		}
		List<TGoodsBigType> pageListData = goodsBigTypeMapper.pageList(map);

		if (pageListData == null) {
			LOGGER.info("GoodsTypeService.pageListBigType query data is fail|pageSize={}|pageCount={}|where={}",
					pageSize, pageCount, where);
			return RespMessage.failResp("");
		} else {
			LOGGER.info("GoodsTypeService.pageListBigType query data is success|pageSize={}|pageCount={}|where={}",
					pageSize, pageCount, where);
			PageUtil<TGoodsBigType> pageModel = new PageUtil<TGoodsBigType>();
			pageModel.setRowCount(goodsBigTypeMapper.getLjCount(where));
			pageModel.setList(pageListData);
			pageModel.setPageSize(pageSize);
			pageModel.setPageCount(pageCount);
			pageModel.setPageIndex(pageCount+1);
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("modle", pageModel);
			return RespMessage.successResp(retMap);
		}

	}
	
	/**
	 * 获取中类数据
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage getMidType(Integer id) {

		if (id <= 0) {
			LOGGER.info("GoodsTypeService.getMidType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		TGoodsMidType result = goodsMidTypeMapper.getLjById(id);
		if (result !=null) {
			LOGGER.info("GoodsTypeService.getMidType insert data is success|request={}", result);
			return RespMessage.successResp("查询数据成功", result);
		} else {
			LOGGER.info("GoodsTypeService.getMidType insert data is fail|request={}", id);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}
	
	/**
	 * 分页查询大类数据
	 * 
	 * @param pageSize
	 * @param pageCount
	 * @param where
	 * @return
	 */
	@SuppressWarnings("unused")
	public RespMessage pageListMidType(int pageSize, int pageCount, String where,int bigId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int skipCount = (pageCount-1) * pageSize;
		map.put("skipCount", skipCount);
		map.put("pageSize", pageSize);
		if (where != null && !where.equals("")) {
			map.put("midTypeName", where);
		}
		if(bigId>0)
		{
			map.put("bigId", bigId);
		}
		List<TGoodsMidType> pageListData = goodsMidTypeMapper.pageList(map);

		if (pageListData == null) {
			LOGGER.info("GoodsTypeService.pageListMidType query data is fail|pageSize={}|pageCount={}|where={}",
					pageSize, pageCount, where);
			return RespMessage.failResp("");
		} else {
			LOGGER.info("GoodsTypeService.pageListMidType query data is success|pageSize={}|pageCount={}|where={}",
					pageSize, pageCount, where);
			PageUtil<TGoodsMidType> pageModel = new PageUtil<TGoodsMidType>();
			pageModel.setRowCount(goodsMidTypeMapper.getLjCount(where));
			pageModel.setList(pageListData);
			pageModel.setPageSize(pageSize);
			pageModel.setPageCount(pageCount);
			pageModel.setPageIndex(pageCount+1);
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("modle", pageModel);
			return RespMessage.successResp(retMap);
		}

	}

	/**
	 * 插入中类数据
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage insertMidType(TGoodsMidType midType) {

		if (midType == null) {
			LOGGER.info("GoodsTypeService.insertMidType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsMidTypeMapper.insert(midType);
		if (result > 0) {
			LOGGER.info("GoodsTypeService.insertMidType insert data is success|request={}", midType);
			return RespMessage.successResp("插入成功", "");
		} else {
			LOGGER.info("GoodsTypeService.insertBigType insert data is fail|request={}", midType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 修改中类信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage editMidType(TGoodsMidType midType) {
		if (midType == null) {
			LOGGER.info("GoodsTypeService.editMidType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsMidTypeMapper.update(midType);
		if (result > 0) {
			LOGGER.info("GoodsTypeService.editMidType edit data is success|request={}", midType);
			return RespMessage.successResp("修改成功", "");
		} else {
			LOGGER.info("GoodsTypeService.editBigType edit data is fail|request={}", midType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 删除大类类别信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage deleteMidType(TGoodsMidType midType) {
		if (midType == null || "".equals(midType.getMiddleTypeName())) {
			LOGGER.info("GoodsTypeService.deleteMidType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsMidTypeMapper.updateToDelete(midType.getId());
		if (result > 0) {
			LOGGER.info("GoodsTypeService.deleteMidType delete data is success|request={}", midType);
			return RespMessage.successResp("删除成功", "");
		} else {
			LOGGER.info("GoodsTypeService.deleteMidType delete data is fail|request={}", midType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 插入中类数据
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage insertSmallType(TGoodsSmallType midType) {

		if (midType == null) {
			LOGGER.info("GoodsTypeService.insertSmallType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsSmallTypeMapper.insert(midType);
		if (result > 0) {
			LOGGER.info("GoodsTypeService.insertSmallType insert data is success|request={}", midType);
			return RespMessage.successResp("插入成功", "");
		} else {
			LOGGER.info("GoodsTypeService.insertSmallType insert data is fail|request={}", midType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 修改中类信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage editSmallType(TGoodsSmallType midType) {
		if (midType == null) {
			LOGGER.info("GoodsTypeService.editSmallType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsSmallTypeMapper.update(midType);
		if (result > 0) {
			LOGGER.info("GoodsTypeService.editSmallType edit data is success|request={}", midType);
			return RespMessage.successResp("修改成功", "");
		} else {
			LOGGER.info("GoodsTypeService.editSmallType edit data is fail|request={}", midType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	/**
	 * 删除大类类别信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage deleteSmallType(TGoodsSmallType midType) {
		if (midType == null || "".equals(midType.getSmallName())) {
			LOGGER.info("GoodsTypeService.deleteSmallType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsSmallTypeMapper.updateToDelete(midType.getId());
		if (result > 0) {
			LOGGER.info("GoodsTypeService.deleteSmallType delete data is success|request={}", midType);
			return RespMessage.successResp("删除成功", "");
		} else {
			LOGGER.info("GoodsTypeService.deleteSmallType delete data is fail|request={}", midType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}
	
	/**
	 * 获取小类数据
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage getSmallType(Integer id) {

		if (id <= 0) {
			LOGGER.info("GoodsTypeService.getSmallType request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		TGoodsSmallType result = goodsSmallTypeMapper.getLjById(id);
		if (result !=null) {
			LOGGER.info("GoodsTypeService.getSmallType insert data is success|request={}", result);
			return RespMessage.successResp("查询数据成功", result);
		} else {
			LOGGER.info("GoodsTypeService.getSmallType insert data is fail|request={}", id);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}
	
	/**
	 * 分页查询大类数据
	 * 
	 * @param pageSize
	 * @param pageCount
	 * @param where
	 * @return
	 */
	@SuppressWarnings("unused")
	public RespMessage pageListSmallType(int pageSize, int pageCount, String where,int smallId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int skipCount = (pageCount-1) * pageSize;
		map.put("skipCount", skipCount);
		map.put("pageSize", pageSize);
		if (where != null && !where.equals("")) {
			map.put("midTypeName", where);
		}
		if(smallId>0)
		{
			map.put("midId", smallId);
		}
		List<TGoodsSmallType> pageListData = goodsSmallTypeMapper.pageList(map);

		if (pageListData == null) {
			LOGGER.info("GoodsTypeService.pageListSmallType query data is fail|pageSize={}|pageCount={}|where={}",
					pageSize, pageCount, where);
			return RespMessage.failResp("");
		} else {
			LOGGER.info("GoodsTypeService.pageListSmallType query data is success|pageSize={}|pageCount={}|where={}",
					pageSize, pageCount, where);
			PageUtil<TGoodsSmallType> pageModel = new PageUtil<TGoodsSmallType>();
			pageModel.setRowCount(goodsSmallTypeMapper.getLjCount(where));
			pageModel.setList(pageListData);
			pageModel.setPageSize(pageSize);
			pageModel.setPageCount(pageCount);
			pageModel.setPageIndex(pageCount+1);
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("modle", pageModel);
			return RespMessage.successResp(retMap);
		}

	}

	/**
	 * 根据大类id获取中类、小类
	 * @param bigId
	 * @return
	 */
	public List<CategoryDto> getCategoryByBigType(Integer bigId){
		List<CategoryDto> categoryList = Lists.newArrayList();
		// 页面第一次初始化
		if (bigId == null){
			List<TGoodsBigType> bigTypeList = getAllBigType();
			if (CollectionUtils.isEmpty(bigTypeList)){
				return categoryList;
			}
			bigId = bigTypeList.get(0).getId();
			categoryList = BeanUtil.copyBeans(bigTypeList,CategoryDto.class);
		}else{
			TGoodsBigType goodsBigtype = goodsBigTypeMapper.getLjById(bigId);
			categoryList.add(BeanUtil.copyProperties(goodsBigtype, CategoryDto.class));
		}
		// 中类
		List<TGoodsMidType> midTypeList = goodsMidTypeMapper.findGoodsTypeByBigId(bigId);
		if (CollectionUtils.isEmpty(midTypeList)){
			return categoryList;
		}
		// 小类
		List<Integer> midIds = midTypeList.stream().map(m->m.getId()).collect(Collectors.toList());
		List<TGoodsSmallType> smallTypeList = goodsSmallTypeMapper.findGoodsTypeByMidIds(midIds);
		if (CollectionUtils.isEmpty(smallTypeList)){
			categoryList.get(0).setMidTypeDtos(BeanUtil.copyBeans(midTypeList,GoodsMidTypeDto.class));
			return categoryList;
		}
		List<GoodsMidTypeDto> midTypeDtoList = Lists.newArrayListWithCapacity(midTypeList.size());
		smallTypeList.stream().collect(Collectors.groupingBy(s -> s.getMiddId())).forEach((k, v) -> {
			TGoodsMidType midTypeTmp = midTypeList.stream().filter(m -> m.getId().equals(k)).findAny().orElse(null);
			GoodsMidTypeDto midTypeDto = BeanUtil.copyProperties(midTypeTmp,GoodsMidTypeDto.class);
			midTypeDto.setSmallTypeDtos(BeanUtil.copyBeans(v,GoodsSmallTypeDto.class));
			midTypeDtoList.add(midTypeDto);
		});
		categoryList.get(0).setMidTypeDtos(midTypeDtoList);
		return categoryList;
	}

	public List<TGoodsBigType> getAllBigType(){
		return goodsBigTypeMapper.getAllGoodsBigType();
	}

}
