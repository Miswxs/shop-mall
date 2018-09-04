package com.lj.main.controller.good;

import com.lj.common.annotation.Login;
import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.RespMessage;
import com.lj.db.model.TGoods;
import com.lj.db.model.TGoodsBigType;
import com.lj.domain.dto.BaseUser;
import com.lj.domain.dto.GoodsDto;
import com.lj.main.util.SessionUtil;
import com.lj.service.goods.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/goodsInfo")
public class GoodsInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsInfoController.class);

    @Autowired
	private GoodsService goodsService;// 商品服务
	/**
	 * 保存商品信息
	 * @param goodsInfo
	 * @return
	 */
    @RequestMapping(value="/saveGoodsInfo",method=RequestMethod.POST,consumes = "application/json")
	@Login
	public RespMessage saveGoodsInfo(@RequestBody TGoods goodsInfo) {
		LOGGER.info("GoodsInfoController.saveGoodsInfo req={}",goodsInfo);
		BaseUser baseUser = (BaseUser) SessionUtil.getHttpSession().getAttribute("baseUser");
		if (baseUser == null){
			new LJShopException(ErrorCode.UNLOGIN_ERROR,"登录已失效，请重新登录");
		}
		goodsInfo.setCreatedBy(baseUser.getId().toString());
		goodsInfo.setUpdatedBy(baseUser.getId().toString());
		goodsInfo.setSellerId(baseUser.getId());
		return goodsService.saveGoods(goodsInfo);
	}

    
    @RequestMapping("/pageListGoodType")
	@Login
	public RespMessage pageListGoodsType(@RequestParam String goodName, int pageSize, int pageCount) {
		LOGGER.info("GoodsInfoController.pageListGoodsType RespMessage goodName={}|pageSize={}|pageCount={}",
				goodName, pageSize, pageCount);
		BaseUser baseUser = (BaseUser) SessionUtil.getHttpSession().getAttribute("baseUser");
		if (baseUser == null){
			new LJShopException(ErrorCode.UNLOGIN_ERROR,"登录已失效，请重新登录");
		}
		Long userId = baseUser.getId();
		if ("admin".equals(baseUser.getUserName())){
			userId = null;
		}
		RespMessage respMessage = goodsService.pageListGoodsType(pageSize, pageCount, goodName,userId);
		LOGGER.info("GoodsInfoController.pageListGoodsType RespMessage bigTypeName={}|pageSize={}|pageCount={}|res={}",
				goodName, pageSize, pageCount, respMessage);
		return respMessage;
	}
    
    
    /**
	 * 删除商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteGoodsInfo")
	@ResponseBody
	public RespMessage deleteGoodsInfo(@RequestParam String id) {
		TGoods model = new TGoods();
		model.setId(Long.parseLong(id));
		LOGGER.info("GoodsInfoController.deleteGoodsInfo RespMessage req={}", model);
		RespMessage respMessage = goodsService.deleteGoodsInfo(model);
		LOGGER.info("GoodsInfoController.deleteGoodsInfo RespMessage req={}|res={}", model, respMessage);
		return respMessage;
	}
	
	/**
	 * 获取商品信息
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/getGoodsInfoById")
	@ResponseBody
	public RespMessage getGoodsInfoById(@RequestParam String id) {
		Integer intId = Integer.parseInt(id);
		LOGGER.info("GoodsTypeController.getGoodsTypeBig RespMessage req={}", intId);
		RespMessage respMessage = goodsService.getGoodsInfo(Long.parseLong(id));
		LOGGER.info("GoodsTypeController.getGoodsTypeBig RespMessage req={}|res={}", intId, respMessage);
		return respMessage;
	} 
	
	/**
	 * 更新大类商品信息
	 * 
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    @RequestMapping(value="/editGoodsinfo",method=RequestMethod.POST,consumes = "application/json")
	@Login
	public RespMessage editGoodsinfo(@RequestBody TGoods goodsInfo) throws UnsupportedEncodingException {
		LOGGER.info("GoodsTypeController.modifyGoodsBigType RespMessage req={}", goodsInfo);
		BaseUser baseUser = (BaseUser) SessionUtil.getHttpSession().getAttribute("baseUser");
		if (baseUser == null){
			new LJShopException(ErrorCode.UNLOGIN_ERROR,"登录已失效，请重新登录");
		}
		goodsInfo.setUpdatedBy(baseUser.getId().toString());
		RespMessage respMessage = goodsService.editGoodsinfo(goodsInfo);
		LOGGER.info("GoodsTypeController.modifyGoodsBigType RespMessage req={}|res={}", goodsInfo, respMessage);
		return respMessage;
	}
	/**
	 * 首页商品展示逻辑
	 * @return
	 */
	@RequestMapping("/selectGoodsForRecent")
	public List<GoodsDto> selectGoodsForRecent(){
		return goodsService.selectGoodsForRecent();
	}

	/**
	 * 首页商品展示逻辑：爆款
	 * @return
	 */
	@RequestMapping("/selectGoodsForHot")
	public List<GoodsDto> selectGoodsForHot(){
		return goodsService.selectGoodsForHot();
	}

	/**
	 * 首页商品展示逻辑：轮播图
	 * @return
	 */
	@RequestMapping("/selectGoodsForScroll")
	public List<TGoodsBigType> selectGoodsForScroll(){
		return goodsService.selectGoodsForScroll();
	}
}
