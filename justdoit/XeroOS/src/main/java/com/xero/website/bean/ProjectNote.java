package com.xero.website.bean;

import com.xero.core.bean.BaseEntity;

public class ProjectNote extends BaseEntity{

	
	private static final long serialVersionUID = -5767672521591170321L;

	private Integer projectId ;
	
	private boolean showCustomer ;
	
	private String content ;
	
	private Integer userId ;
	

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public boolean isShowCustomer() {
		return showCustomer;
	}

	public void setShowCustomer(boolean showCustomer) {
		this.showCustomer = showCustomer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
