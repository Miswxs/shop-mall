package com.lj.main.req;

/**
 * 已卖出的商品查询条件
 */
public class OrderSearchReq {
    // 商品ID
    private Long goodsNo;
    // 商品名称
    private String histGoodsName;
    // 买家昵称
    private String customerName;
    // 订单编号
    private String saleOrderNo;
    // 成交开始时间
    private String dealBeginTime;
    // 成交结束时间
    private String dealEndTime;
    // 订单状态
    private String status;

    Integer pageCount;

    Integer pageSize;

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getHistGoodsName() {
        return histGoodsName;
    }

    public void setHistGoodsName(String histGoodsName) {
        this.histGoodsName = histGoodsName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
    }

    public String getDealBeginTime() {
        return dealBeginTime;
    }

    public void setDealBeginTime(String dealBeginTime) {
        this.dealBeginTime = dealBeginTime;
    }

    public String getDealEndTime() {
        return dealEndTime;
    }

    public void setDealEndTime(String dealEndTime) {
        this.dealEndTime = dealEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "OrderSearchReq{" +
                "goodsNo=" + goodsNo +
                ", histGoodsName='" + histGoodsName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", saleOrderNo='" + saleOrderNo + '\'' +
                ", dealBeginTime='" + dealBeginTime + '\'' +
                ", dealEndTime='" + dealEndTime + '\'' +
                ", status='" + status + '\'' +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                '}';
    }
}
