<%
/**
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
</head>
<body> 
<fmt:bundle basename="resourceBundle">
<table border='0' cellpadding='0' cellspacing='0' width='98%' style='margin: 10px 0 0 0;'>
<c:if test='${requestScope.title != null}'>
<tr valign='top'>
<td class='portlet-table-td-left'>
<span>
<c:out value="${requestScope.title}" escapeXml="false"/>
</span>
</td>
</tr>
</c:if>
<tr valign='top'>
<c:if test='${requestScope.type == 1}'> 
<light:authorize role="ROLE_BLOG">
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL portletMode='EDIT' windowState='MAXIMIZED' ><portlet:param name='add' value='add'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.add"/></a>
</td>
</light:authorize>
</c:if>
<c:if test='${requestScope.type != 1}'> 
<light:authenticateUser> 
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL portletMode='EDIT' windowState='MAXIMIZED' ><portlet:param name='add' value='add'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.add"/></a>
</td>
</light:authenticateUser>
</c:if>
</tr>
</table>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<c:if test='${requestScope.blogLists != null}'>
<table border='0' cellpadding='0' cellspacing='0'  width='98%'>
<c:forEach var="item" items="${requestScope.blogLists}" >
<tr valign='top' onmouseover="javascript:this.className='theme-bgcolor';this.getElementsByTagName('div')[0].style.opacity=100;" onmouseout="javascript:this.className='';this.getElementsByTagName('div')[0].style.opacity=0;">
<td class='portlet-table-td-left' width='100%'>
<span class="portlet-item"
<c:if test='${requestScope.showLevel == 0}'> 
	onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_BLOG %>,'<c:out value="${item.id}"/>');"
    onmouseout="javascript:Light.hideSummary();"
</c:if>
>
<a href='javascript:;' onclick="javascript:Light.hideSummary();Light.executeRender('<c:out value="${requestScope.responseId}"/>','','maximized','blogId=<c:out value="${item.id}"/>');">
<span style="font-size: 120%;">
<c:if test='${item.status != 0}'>
<c:out value="${item.title}"/>
</c:if>
<c:if test='${item.status == 0}'>
<i><c:out value="${item.title}"/>(Draft)</i>
</c:if>
<c:if test='${item.commentCount != 0}'>
(<c:out value="${item.commentCount}"/> <fmt:message key="portlet.label.comments"/>)
</c:if>
</span>
</a>
<br/>
<span class="portlet-note" style="padding-left:3px;"><c:out value="${item.date}"/> </span>
<c:if test='${item.postedById != sessionScope.user.id}'> 
<span class="portlet-note" style="padding-left:3px;"><fmt:message key="portlet.label.postedBy"/></span>
<span class="portlet-note" style="padding-left:0px;"><a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${item.uri}"/>' target='_blank'><span class="portlet-note-link"><c:out value="${item.displayName}"/></span></a></span>
</c:if>
</td>
<td class='portlet-table-td-right'>
<c:if test='${item.postedById == sessionScope.user.id || sessionScope.user.admin}'> 
<div class="icons hand" style="margin:5px;opacity:0;" >
<span class="icons edit" onclick="Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','action=edit','<c:out value="${item.id}"/>');" title='<fmt:message key="portlet.button.edit"/>'></span>
<span class="icons deleteLink" onclick="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','delete',null,'<c:out value="${item.id}"/>','view',null,null);" title='<fmt:message key="portlet.button.delete"/>'></span>

</div>
</c:if>
</td>
</tr> 
<c:if test='${requestScope.showLevel != 2}'> 
<tr> 
<td class='portlet-table-td-left'> 
<span>
<c:out value="${item.summary}" escapeXml="false"/>
</span>
<br/><br/> 
</td> 
</tr> 
</c:if>
<c:if test='${requestScope.showLevel == 2}'> 
<tr> 
<td class='portlet-table-td-left'> 
<span class='portlet-table-td-left'>
<c:out value="${item.content}" escapeXml="false"/>
</span> 
<br/><br/>
</td> 
</tr> 
</c:if>
</c:forEach>
<tr>
<td class='portlet-table-td-left'>
<%
String host = request.getHeader("Host");
host += request.getContextPath();
%>
<c:if test='${requestScope.type != "2"}'>
<a href='http://<%= host %>/rss/blog<c:out value="${requestScope.userId}"/>.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' style='border: 0px;padding-top:10px;' title='<fmt:message key="portlet.label.rssBlog"/>'/></a>
</c:if>
<c:if test='${requestScope.type == "2"}'>
<a href='http://<%= host %>/rss/communityBlog<c:out value="${requestScope.orgId}"/>.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' style='border: 0px;padding-top:10px;' title='<fmt:message key="portlet.label.rssBlog"/>'/></a>
</c:if>
</td>
<td class='portlet-table-td-right'>
<span class='portlet-link'>
<a href='javascript:;' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','categoryId=<c:out value="${categoryId}"/>');"><fmt:message key="portlet.button.back"/></a>
</span>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.showMore != null }'>
	<%@ include file="/common/paginator.jsp"%>
</c:if>
<c:if test='${requestScope.error != null}'>
<span class="portlet-item">
<c:out value="${requestScope.error}"/>
</span>
</c:if>
</form>
</fmt:bundle>
</body>
</html>