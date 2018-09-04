package com.lj.domain.dto;

public class GoodsSmallTypeDto {
    /**
     *主键id
     */
    private Integer id;
    /**
     *商品中型类别id
     */
    private Integer middId;
    /**
     *小类型名称
     */
    private String smallName;
    /**
     *
     */
    private String imgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMiddId() {
        return middId;
    }

    public void setMiddId(Integer middId) {
        this.middId = middId;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
