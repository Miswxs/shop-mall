package com.lj.main.req;

import javax.validation.Valid;
import java.util.List;

public class OrderReq {

    /**
     *订单状态
     */
    private String status;
    /**
     *历史收款人手机号
     */
    private String histAddressCellphone;
    /**
     *历史收款人姓名
     */
    private String histAddressName;
    /**
     *历史收款人详细地址
     */
    private String histAddressDetail;
    /**
     *备注
     */
    private String remark;

    @Valid
    private List<OrderGoodsReq> orderGoodsReqs;

    /* 查看全部订单时的请求参数 */
    private String orderStatus;
    private String evaluateStatus;
    private String searchTxt;
    private Integer currentPage;
    private Integer pageSize;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(String evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHistAddressCellphone() {
        return histAddressCellphone;
    }

    public void setHistAddressCellphone(String histAddressCellphone) {
        this.histAddressCellphone = histAddressCellphone;
    }

    public String getHistAddressName() {
        return histAddressName;
    }

    public void setHistAddressName(String histAddressName) {
        this.histAddressName = histAddressName;
    }

    public String getHistAddressDetail() {
        return histAddressDetail;
    }

    public void setHistAddressDetail(String histAddressDetail) {
        this.histAddressDetail = histAddressDetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderGoodsReq> getOrderGoodsReqs() {
        return orderGoodsReqs;
    }

    public void setOrderGoodsReqs(List<OrderGoodsReq> orderGoodsReqs) {
        this.orderGoodsReqs = orderGoodsReqs;
    }

    @Override
    public String toString() {
        return "OrderReq{" +
                "orderStatus='" + orderStatus + '\'' +
                ", evaluateStatus='" + evaluateStatus + '\'' +
                ", searchTxt='" + searchTxt + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
