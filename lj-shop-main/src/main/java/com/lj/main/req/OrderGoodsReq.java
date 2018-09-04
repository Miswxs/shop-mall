package com.lj.main.req;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单商品关联表：用于创建订单
 */
public class OrderGoodsReq {
    /**
     *商品id
     */
    @NotNull(message = "请先选择商品")
    private Long goodsNo;
    /**
     *商品数量
     */
    @NotNull(message = "请选择商品数量")
    private Integer goodsNum;
    /**
     *历史商品价格
     */
    @NotNull(message = "商品价格不能为空")
    private BigDecimal histGoodsPrice;
    /**
     *历史商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String histGoodsName;
    /**
     *历史商品主图
     */
    @NotBlank(message = "商品主图不能为空")
    private String histGoodsImg;

    private String goodsColor;
    private String goodsSize;

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getHistGoodsPrice() {
        return histGoodsPrice;
    }

    public void setHistGoodsPrice(BigDecimal histGoodsPrice) {
        this.histGoodsPrice = histGoodsPrice;
    }

    public String getHistGoodsName() {
        return histGoodsName;
    }

    public void setHistGoodsName(String histGoodsName) {
        this.histGoodsName = histGoodsName;
    }

    public String getHistGoodsImg() {
        return histGoodsImg;
    }

    public void setHistGoodsImg(String histGoodsImg) {
        this.histGoodsImg = histGoodsImg;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }
}
