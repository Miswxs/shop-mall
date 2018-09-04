package com.lj.common.util;

import java.util.List;

public class PageUtil<T> {

	private Integer pageCount;//当前页
	private Integer pageSize;//每页的数据量
	private Integer RowCount;//总数据量
	private List<T> Data;

	private Integer skipCount;//从第几条数据开始
	private Integer PageIndex;//兼容pageing 前端插件

	public PageUtil() {
		super();
	}
	public PageUtil(Integer pageCount, Integer pageSize) {
		if(pageCount == null || pageCount < 1){
			pageCount = 1;
		}
		if (pageSize == null || pageSize < 1){
			pageSize = 10;
		}
		this.pageCount = pageCount;
		this.pageSize = pageSize;
	}

	public Integer getSkipCount() {
		return (pageCount-1)*pageSize;
	}
	public void setSkipCount(Integer skipCount) {
		this.skipCount = skipCount;
	}
	public Integer getPageCount() {
		return pageCount<=0?0:pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}


	public List<T> getList() {
		return Data;
	}
	public void setList(List<T> Data) {
		this.Data = Data;
	}
	public Integer getPageIndex() {
		return PageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		PageIndex = pageIndex;
	}
	public Integer getRowCount() {
		return RowCount;
	}
	public void setRowCount(Integer rowCount) {
		RowCount = rowCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
