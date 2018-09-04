package com.lj.service.user;

import com.google.common.collect.Lists;
import com.lj.common.resp.PageResp;
import com.lj.common.util.BeanUtil;
import com.lj.db.mapper.TShopCartMapper;
import com.lj.db.model.TShopCart;
import com.lj.domain.dto.GoodsDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopCartService.class);

    @Autowired
    private TShopCartMapper shopCartMapper;

    /**
     * 购物车列表
     * @param userId
     * @param offset
     * @param pageSize
     * @return
     */
    public PageResp<GoodsDto> getShopCartList(Long userId,Integer offset,Integer pageSize){
        Integer total = shopCartMapper.countShopCartByUserId(userId);
        List<GoodsDto> list = Lists.newArrayListWithCapacity(total);
        if (offset >= total){
            return new PageResp<GoodsDto>(list,total);
        }
        if (total > 0){
            list = BeanUtil.copyBeans(shopCartMapper.findShopCartByUserId(userId,offset,pageSize),GoodsDto.class);
        }
        return new PageResp<GoodsDto>(list,total);
    }

    /**
     * 计算购物车商品总数
     * @param userId
     * @return
     */
    public Integer countGoodsNumByUserId(Long userId){
        return shopCartMapper.countGoodsNumByUserId(userId);
    }

    /**
     * 批量删除
     * @param userId
     * @param gIds
     * @param type 0：清空购物车 1：批量删除
     */
    public void deleteGoods(Long userId,List<Long> gIds,String type){
        shopCartMapper.deleteGoodsByIds(userId,gIds,type);
    }

    /**
     * 查询购物车id对应的商品信息
     * @param userId
     * @param cartIds
     * @return
     */
    public List<GoodsDto> getShopCartByIds(Long userId,List<Long> cartIds){
        return BeanUtil.copyBeans(shopCartMapper.getShopCartByIds(userId,cartIds),GoodsDto.class);
    }

    /**
     * 添加到购物车
     * @param shopCart
     */
    public void addToCart(TShopCart shopCart){
        int result = shopCartMapper.update(shopCart);
        if (result >0) {
            return;
        }
        shopCartMapper.insert(shopCart);
    }

    /**
     * 减少购物车数量
     * @param shopCart
     */
    public void decreaseCart(TShopCart shopCart){
        shopCartMapper.decreaseCart(shopCart);
    }

}
