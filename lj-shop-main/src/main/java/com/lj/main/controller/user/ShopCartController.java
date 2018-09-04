package com.lj.main.controller.user;

import com.lj.common.annotation.Login;
import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.PageResp;
import com.lj.common.util.BeanUtil;
import com.lj.common.util.PageUtil;
import com.lj.common.util.ValidateUtil;
import com.lj.db.model.TShopCart;
import com.lj.domain.dto.BaseUser;
import com.lj.domain.dto.GoodsDto;
import com.lj.main.req.GoodsReq;
import com.lj.main.util.ThreadLocalUtil;
import com.lj.service.cache.EhcacheService;
import com.lj.service.user.ShopCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/shopcart")
public class ShopCartController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopCartController.class);

    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private EhcacheService ehcacheService;

    @RequestMapping("/getShopCartList")
    @Login
    public PageResp<GoodsDto> getShopCartList(@RequestParam Integer currentPage, Integer pageSize){
        LOGGER.info("/shopcart/getShopCartList入参[currentPage={},pageSize={}]",currentPage,pageSize);
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        PageUtil pageUtil =new PageUtil(currentPage,pageSize);
        return shopCartService.getShopCartList(user.getId(),pageUtil.getSkipCount(),pageUtil.getPageSize());
    }

    /**
     * 计算购物车商品总数
     * @return
     */
    @RequestMapping("/countGoodsNumByUserId")
    public Integer countGoodsNumByUserId(HttpServletRequest request){
        LOGGER.info("shopcart/countGoodsNumByUserId请求");
        String token = request.getHeader("token");
        BaseUser baseUser = ehcacheService.getBaseUser(token,null);
        // 如果没有登录则默认0
        if (baseUser == null){
            return 0;
        }
        return shopCartService.countGoodsNumByUserId(baseUser.getId());
    }

    /**
     * 批量删除购物车
     * @param gIds
     */
    @RequestMapping("/deleteGoods")
    @Login
    public void deleteGoods(@RequestBody List<Long> gIds){
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        shopCartService.deleteGoods(user.getId(),gIds,"1");
    }

    /**
     * 清空购物车
     * @param gIds
     */
    @RequestMapping("/clearShopCart")
    @Login
    public void clearShopCart(@RequestBody List<Long> gIds){
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        shopCartService.deleteGoods(user.getId(),gIds,"0");
    }

    /**
     * 根据购物车id获取商品信息，订单确认页
     * @param ids
     * @return
     */
    @RequestMapping("/getShopCartByIds")
    @Login
    public List<GoodsDto> getShopCartByIds(@RequestBody List<Long> ids){
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        return shopCartService.getShopCartByIds(user.getId(),ids);
    }

    /**
     * 加入购物车
     * @param goodsReq
     */
    @RequestMapping("/addToCart")
    @Login
    public void addToCart(@RequestBody GoodsReq goodsReq){
        ValidateUtil.notNull(goodsReq.getId(),"商品编号为空",ErrorCode.ARGS_MISS_ERROR);
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        TShopCart shopCart = BeanUtil.copyProperties(goodsReq,TShopCart.class);
        shopCart.setGoodsNo(goodsReq.getId());
        shopCart.setCustomerId(user.getId());
        shopCartService.addToCart(shopCart);
    }

    /**
     * 减少购物车数量
     * @param goodsNo
     * @param goodsNum
     */
    @RequestMapping("/decreaseCart")
    @Login
    public void decreaseCart(@RequestParam Long goodsNo,Integer goodsNum){
        ValidateUtil.notNull(goodsNo,"商品编号为空",ErrorCode.ARGS_MISS_ERROR);
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        TShopCart shopCart = new TShopCart();
        shopCart.setGoodsNo(goodsNo);
        shopCart.setGoodsNum(goodsNum);
        shopCart.setCustomerId(user.getId());
        shopCartService.decreaseCart(shopCart);
    }
}
