<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:select id="systecategory" name="categoryTmp.id" list="categoryList" listKey="id" listValue="name" headerKey="-1" headerValue="请选择系统分类" value="entity.category.id" theme="simple"></s:select>