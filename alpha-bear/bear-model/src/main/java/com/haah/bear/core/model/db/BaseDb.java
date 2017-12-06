package com.haah.bear.core.model.db;

import javax.validation.constraints.Min;

public class BaseDb {
	/**
	 * 起始页
	 */
	@Min(value = 1, message = "查询页码格式不合法")
	private int pageNo;
	/**
	 * 每页几条
	 */
	@Min(value = 1, message = "每页显示条数格式不合法")
	private int pageSize;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

}
