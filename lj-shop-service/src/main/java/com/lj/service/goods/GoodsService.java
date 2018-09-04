package com.lj.service.goods;

import com.lj.common.util.BeanUtil;
import com.lj.common.util.PageUtil;
import com.lj.db.mapper.TGoodsBigTypeMapper;
import com.lj.domain.dto.GoodsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.common.enums.ErrorCode;
import com.lj.common.resp.RespMessage;
import com.lj.db.mapper.TGoodsMapper;
import com.lj.db.model.TGoods;
import com.lj.db.model.TGoodsBigType;
import com.lj.db.model.TGoodsMidType;
import com.lj.service.order.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);
    
    @Autowired
    private TGoodsMapper goodsMapper;
    @Autowired
    private TGoodsBigTypeMapper goodsBigTypeMapper;

    /**
     * 保存商品信息
     * @param goods 实体
     * @return
     */
    public RespMessage saveGoods(TGoods goods)
    {
    	if (goods==null) {
			LOGGER.info("GoodsService.saveGoods request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsMapper.insert(goods);
		if (result>0) {
			LOGGER.info("GoodsService.saveGoods insert data is success|request={}", goods);
			return RespMessage.successResp("数据保存成功", result);
		} else {
			LOGGER.info("GoodsService.saveGoods insert data  is fail|request={}", goods);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
    }
    
    
    
    /**
	 * 分页查询商品数据
	 * 
	 * @param pageSize
	 * @param pageCount
	 * @param where
	 * @return
	 */
	@SuppressWarnings("unused")
	public RespMessage pageListGoodsType(int pageSize, int pageCount, String goodsName,Long sellerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int skipCount = (pageCount-1) * pageSize;
		map.put("skipCount", skipCount);
		map.put("pageSize", pageSize);
		map.put("goodsName", goodsName);
		map.put("sellerId", sellerId);
		List<TGoods> pageListData = goodsMapper.pageList(map);

		if (pageListData == null) {
			LOGGER.info("GoodsService.pageListGoodsType query data is fail|pageSize={}|pageCount={}|goodsName={}|sellerId={}",
					pageSize, pageCount, goodsName,sellerId);
			return RespMessage.failResp("");
		} else {
			LOGGER.info("GoodsService.pageListGoodsType query data is success|pageSize={}|pageCount={}|goodsName={}|sellerId={}",
					pageSize, pageCount,goodsName, sellerId);
			PageUtil<TGoods> pageModel = new PageUtil<TGoods>();
			pageModel.setRowCount(goodsMapper.getGoodsCount(map));
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
	 * 删除商品信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage deleteGoodsInfo(TGoods bigType) {
		if (bigType == null || bigType.getId() <= 0) {
			LOGGER.info("GoodsService.deleteGoodsInfo request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsMapper.updateToDelete(bigType.getId().intValue());
		if (result > 0) {
			LOGGER.info("GoodsService.deleteGoodsInfo delete data is success|request={}", bigType);
			return RespMessage.successResp("删除成功", "");
		} else {
			LOGGER.info("GoodsService.deletebigType delete data is fail|request={}", bigType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}
	
	
	/**
	 * 获取商品信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage getGoodsInfo(long id) {

		if (id <= 0) {
			LOGGER.info("GoodsService.getGoodsInfo request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		TGoods result = goodsMapper.findGoodsById(id);
		if (result !=null) {
			LOGGER.info("GoodsService.getGoodsInfo insert data is success|request={}", result);
			return RespMessage.successResp("查询数据成功", result);
		} else {
			LOGGER.info("GoodsService.getGoodsInfo insert data is fail|request={}", id);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}
	
	/**
	 * 修改大类信息
	 * 
	 * @param bigType
	 * @return
	 */
	public RespMessage editGoodsinfo(TGoods bigType) {
		if (bigType == null || bigType.getId() <= 0) {
			LOGGER.info("GoodsService.editGoodsinfo request parems is empty.");
			return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(), "参数为空", "");
		}
		int result = goodsMapper.update(bigType);
		if (result > 0) {
			LOGGER.info("GoodsService.editGoodsinfo edit data is success|request={}", bigType);
			return RespMessage.successResp("修改成功", "");
		} else {
			LOGGER.info("GoodsService.editGoodsinfo edit data is fail|request={}", bigType);
			return RespMessage.failResp(ErrorCode.SYSTEM_ERROR, "");
		}
	}
	/**
	 * 首页商品展示逻辑：最近上新
	 * @return
	 */
	public List<GoodsDto> selectGoodsForRecent(){
		return BeanUtil.copyBeans(goodsMapper.selectGoodsForRecent(),GoodsDto.class);
	}

	/**
	 * 首頁商品展示:最近爆款
	 * @return
	 */
	public List<GoodsDto> selectGoodsForHot(){
		return BeanUtil.copyBeans(goodsMapper.selectGoodsForHot(),GoodsDto.class);
	}

	/**
	 * 首頁商品展示:轮播图
	 * @return
	 */
	public List<TGoodsBigType> selectGoodsForScroll(){
		return goodsBigTypeMapper.selectGoodsForScroll();
	}
}
