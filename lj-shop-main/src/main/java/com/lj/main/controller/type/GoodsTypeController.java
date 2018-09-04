package com.lj.main.controller.type;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.common.resp.RespMessage;
import com.lj.db.model.TGoodsBigType;
import com.lj.db.model.TGoodsMidType;
import com.lj.db.model.TGoodsSmallType;
import com.lj.domain.dto.CategoryDto;
import com.lj.service.type.GoodsTypeService;

@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsTypeController.class);

	@Autowired
	private GoodsTypeService goodsTypeService;// 类别服务类

	/**
	 * 获取大类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getGoodsTypeBig")
	@ResponseBody
	public RespMessage getGoodsTypeBig(@RequestParam String id) {
		Integer intId = Integer.parseInt(id);
		LOGGER.info("GoodsTypeController.getGoodsTypeBig RespMessage req={}", intId);
		RespMessage respMessage = goodsTypeService.getBigType(intId);
		LOGGER.info("GoodsTypeController.getGoodsTypeBig RespMessage req={}|res={}", intId, respMessage);
		return respMessage;
	}

	/**
	 * 插入大类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveGoodsTypeBig")
	@ResponseBody
	public RespMessage saveGoodsTypeBig(@RequestParam String bigTypeName, String imgUrl) {
		TGoodsBigType model = new TGoodsBigType();
		model.setBigTypeName(bigTypeName);
		model.setImgUrl(imgUrl);
		LOGGER.info("GoodsTypeController.saveGoodsTypeBig RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.insertBigType(model);
		LOGGER.info("GoodsTypeController.saveGoodsTypeBig RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	/**
	 * 更新大类商品信息
	 * 
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/modifyGoodsBigType")
	@ResponseBody
	public RespMessage modifyGoodsBigType(@RequestParam String bigTypeName, String imgUrl, String id)
			throws UnsupportedEncodingException {
		TGoodsBigType model = new TGoodsBigType();
		model.setBigTypeName(java.net.URLDecoder.decode(bigTypeName, "UTF-8"));
		model.setImgUrl(imgUrl);
		model.setId(Integer.parseInt(id));
		LOGGER.info("GoodsTypeController.modifyGoodsBigType RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.editBigType(model);
		LOGGER.info("GoodsTypeController.modifyGoodsBigType RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	/**
	 * 删除大类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteGoodsBigType")
	@ResponseBody
	public RespMessage deleteGoodsBigType(@RequestParam String id) {
		TGoodsBigType model = new TGoodsBigType();
		model.setId(Integer.parseInt(id));
		LOGGER.info("GoodsTypeController.deleteGoodsBigType RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.deleteBigType(model);
		LOGGER.info("GoodsTypeController.deleteGoodsBigType RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	@RequestMapping("/pageListBigType")
	@ResponseBody
	public RespMessage pageListBigType(@RequestParam String typeName, int pageSize, int pageCount,String id) {
		LOGGER.info("GoodsTypeController.pageListBigType RespMessage bigTypeName={}|pageSize={}|pageCount={}",
				typeName, pageSize, pageCount);
		RespMessage respMessage = goodsTypeService.pageListBigType(pageSize, pageCount, typeName);
		LOGGER.info("GoodsTypeController.saveGoodsTypeMid RespMessage bigTypeName={}|pageSize={}|pageCount={}|res={}",
				typeName, pageSize, pageCount, respMessage);
		return respMessage;
	}

	/**
	 * 插入中类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveGoodsTypeMid")
	@ResponseBody
	public RespMessage saveGoodsTypeMid(@RequestParam String middleTypeName, String bigId) {
		LOGGER.info("GoodsTypeController.saveGoodsTypeMid RespMessage middleTypeName={}|bigId={}", middleTypeName,
				bigId);
		TGoodsMidType model = new TGoodsMidType();
		model.setMiddleTypeName(middleTypeName);
		model.setBigId(Integer.parseInt(bigId));
		RespMessage respMessage = goodsTypeService.insertMidType(model);
		LOGGER.info("GoodsTypeController.saveGoodsTypeMid RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	/**
	 * 获取中类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getGoodsTypeMid")
	@ResponseBody
	public RespMessage getGoodsTypeMid(@RequestParam String id) {
		Integer intId = Integer.parseInt(id);
		LOGGER.info("GoodsTypeController.getGoodsTypeMid RespMessage req={}", intId);
		RespMessage respMessage = goodsTypeService.getMidType(intId);
		LOGGER.info("GoodsTypeController.getGoodsTypeMid RespMessage req={}|res={}", intId, respMessage);
		return respMessage;
	}
	
	/**
	 * 更新中类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyGoodsMidType")
	public RespMessage modifyGoodsMidType(@RequestParam String middleTypeName, String bigId, String id) {
		TGoodsMidType model = new TGoodsMidType();
		model.setMiddleTypeName(middleTypeName);
		model.setBigId(Integer.parseInt(bigId));
		model.setId(Integer.parseInt(id));
		LOGGER.info("GoodsTypeController.modifyGoodsMidType RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.editMidType(model);
		LOGGER.info("GoodsTypeController.modifyGoodsMidType RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	/**
	 * 删除中类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteGoodsMidType")
	public RespMessage deleteGoodsMidType(@RequestParam String id) {
		TGoodsMidType model = new TGoodsMidType();
		model.setId(Integer.parseInt(id));
		LOGGER.info("GoodsTypeController.deleteGoodsMidType RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.deleteMidType(model);
		LOGGER.info("GoodsTypeController.deleteGoodsMidType RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	@RequestMapping("/pageListMidType")
	@ResponseBody
	public RespMessage pageListMidType(@RequestParam String typeName, int pageSize, int pageCount,String id) {
		LOGGER.info("GoodsTypeController.pageListMidType RespMessage bigTypeName={}|pageSize={}|pageCount={}",
				typeName, pageSize, pageCount);
		RespMessage respMessage = goodsTypeService.pageListMidType(pageSize, pageCount, typeName,"".equals(id)?0:Integer.parseInt(id));
		LOGGER.info("GoodsTypeController.pageListMidType RespMessage bigTypeName={}|pageSize={}|pageCount={}|res={}",
				typeName, pageSize, pageCount, respMessage);
		return respMessage;
	}
	
	/**
	 * 插入小类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveGoodsTypeSmall")
	public RespMessage saveGoodsTypeSmall(@RequestParam String smallTypeName, String midId,String imgUrl) {
		TGoodsSmallType model=new TGoodsSmallType();
		model.setImgUrl(imgUrl);
		model.setMiddId(Integer.parseInt(midId));
		model.setSmallName(smallTypeName);
		LOGGER.info("GoodsTypeController.saveGoodsTypeSmall RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.insertSmallType(model);
		LOGGER.info("GoodsTypeController.saveGoodsTypeSmall RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	/**
	 * 更新小类商品信息
	 * 
	 * @param model
	 * @return
	 */  
	@RequestMapping("/modifyGoodsSmallType")
	public RespMessage modifyGoodsSmallType(@RequestParam String smallTypeName, String midId, String id,String imgUrl) {
		TGoodsSmallType model=new TGoodsSmallType();
		model.setId(Integer.parseInt(id));
		model.setImgUrl(imgUrl);
		model.setMiddId(Integer.parseInt(midId));
		model.setSmallName(smallTypeName);
		LOGGER.info("GoodsTypeController.modifyGoodsSmallType RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.editSmallType(model);
		LOGGER.info("GoodsTypeController.modifyGoodsSmallType RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}

	/**
	 * 删除小类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteGoodsSmallType")
	public RespMessage deleteGoodsSmallType(@RequestParam String id) {
		TGoodsSmallType model=new TGoodsSmallType();
		model.setId(Integer.parseInt(id));
		LOGGER.info("GoodsTypeController.deleteGoodsSmallType RespMessage req={}", model);
		RespMessage respMessage = goodsTypeService.deleteSmallType(model);
		LOGGER.info("GoodsTypeController.deleteGoodsSmallType RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}
	
	/**
	 * 获取中类商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getGoodsTypeSmall")
	@ResponseBody
	public RespMessage getGoodsTypeSmall(@RequestParam String id) {
		Integer intId = Integer.parseInt(id);
		LOGGER.info("GoodsTypeController.getGoodsTypeSmall RespMessage req={}", intId);
		RespMessage respMessage = goodsTypeService.getSmallType(intId);
		LOGGER.info("GoodsTypeController.getGoodsTypeSmall RespMessage req={}|res={}", intId, respMessage);
		return respMessage;
	}
	
	@RequestMapping("/pageListSmallType")
	@ResponseBody
	public RespMessage pageListSmallType(@RequestParam String typeName, int pageSize, int pageCount,String id) {
		LOGGER.info("GoodsTypeController.pageListBigType RespMessage bigTypeName={}|pageSize={}|pageCount={}",
				typeName, pageSize, pageCount);
		RespMessage respMessage = goodsTypeService.pageListSmallType(pageSize, pageCount, typeName,"".equals(id)?0:Integer.parseInt(id));
		LOGGER.info("GoodsTypeController.saveGoodsTypeMid RespMessage bigTypeName={}|pageSize={}|pageCount={}|res={}",
				typeName, pageSize, pageCount, respMessage);
		return respMessage;
	}

	@RequestMapping("/getCategoryByBigType")
	public List<CategoryDto> getCategoryByBigType(@RequestParam(required = false) Integer bigId){
		return goodsTypeService.getCategoryByBigType(bigId);
	}

}
