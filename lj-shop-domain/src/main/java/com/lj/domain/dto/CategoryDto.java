package com.lj.domain.dto;

import java.util.List;

/**
 * 商品分类
 */
public class CategoryDto {
    /**
     *大类id
     */
    private Integer id;
    /**
     * 大类名称
     */
    private String bigTypeName;

    private String imgUrl;

    private List<GoodsMidTypeDto> midTypeDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBigTypeName() {
        return bigTypeName;
    }

    public void setBigTypeName(String bigTypeName) {
        this.bigTypeName = bigTypeName;
    }

    public List<GoodsMidTypeDto> getMidTypeDtos() {
        return midTypeDtos;
    }

    public void setMidTypeDtos(List<GoodsMidTypeDto> midTypeDtos) {
        this.midTypeDtos = midTypeDtos;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
