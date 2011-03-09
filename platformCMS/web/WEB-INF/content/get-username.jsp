<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.javaside.cms.core.SpringSecurityUtils" %>
<%=SpringSecurityUtils.getCurrentUserName()%>