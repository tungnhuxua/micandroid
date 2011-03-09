<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<li><a href="${ctx }/blog/message!receive.action">收信箱<s:if test="unRead>0">(${unRead }未读)</s:if></a></li>
<li>_<a href="${ctx }/blog/message!input.action">写信息</a></li>
<li>_<a href="${ctx }/blog/message!send.action">已发信息</a></li>