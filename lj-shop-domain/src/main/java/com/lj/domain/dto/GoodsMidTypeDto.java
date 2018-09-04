package com.lj.domain.dto;

import java.util.List;

public class GoodsMidTypeDto {
    /**
     *主键
     */
    private Integer id;
    /**
     *中类名称
     */
    private String middleTypeName;
    /**
     *大类id
     */
    private Integer bigId;

    private List<GoodsSmallTypeDto> smallTypeDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMiddleTypeName() {
        return middleTypeName;
    }

    public void setMiddleTypeName(String middleTypeName) {
        this.middleTypeName = middleTypeName;
    }

    public Integer getBigId() {
        return bigId;
    }

    public void setBigId(Integer bigId) {
        this.bigId = bigId;
    }

    public List<GoodsSmallTypeDto> getSmallTypeDtos() {
        return smallTypeDtos;
    }

    public void setSmallTypeDtos(List<GoodsSmallTypeDto> smallTypeDtos) {
        this.smallTypeDtos = smallTypeDtos;
    }
}
