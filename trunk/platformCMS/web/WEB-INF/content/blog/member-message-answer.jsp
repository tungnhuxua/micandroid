<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<dd>我的回复：<s:date name="entity.createDate" format="yyyy-MM-dd HH:mm"/>&nbsp;|&nbsp;<a href="javascript:delMessage(message_delete,'${entity.id}');">删除</a></dd>
<dd>${entity.comment}</dd>
