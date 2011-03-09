<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.javaside.cms.core.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<frameset cols="210,*" frameborder="0" border="0" framespacing="0">
<!--  
	<frame src="category!tree.action?action=article.action&actionParam=categoryTmp.id" name="leftFrame" noresize="noresize" id="leftFrame" />
-->	
<frame src="article-left.action" name="leftFrame" noresize="noresize" id="leftFrame" />
	<frame src="<security:authorize ifAnyGranted='A_VIEW_WZ'>article.action</security:authorize>" name="rightFrame" id="rightFrame" />
	
</frameset>
<noframes><body></body></noframes>
</html>