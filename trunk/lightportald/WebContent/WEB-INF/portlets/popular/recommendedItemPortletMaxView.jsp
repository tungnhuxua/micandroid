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
<c:if test='${requestScope.success != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</table>
</c:if>
<form action="<portlet:actionURL />">
<c:if test='${requestScope.lists != null}'>
<table border='0' cellpadding='0' cellspacing='0'  width='95%' style='margin:10px 0 0 0;'>
   <c:forEach var="item" items="${requestScope.lists}" varStatus="status" >
	<tr>	
	<td class='portlet-table-td-left'>	
    <span class="portlet-rss">
   		<a href='javascript:;' 
   			onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_RECOMMENDED_ITEM %>,'<c:out value="${item.id}"/>','<c:out value="${item.link}"/>');"
	        onmouseout="javascript:Light.hideSummary();"
			onclick="javascript:readRecommendedItem('<c:out value="${item.id}"/>','<c:out value="${requestScope.responseId}"/>');Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','maximized','index=<c:out value="${item.id}"/>');""><c:out value="${item.title}"/><i>( <c:out value="${item.time}"/>)</i></a>  
		<light:authenticateUser>
		<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' name="<c:out value='${item.id}'/>"  onClick="document.pressed='pop';document.parameter=this.name;"/>	
		<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' name="<c:out value='${item.id}'/>" onClick="document.pressed='forward';document.parameter=this.name;"/>
		</light:authenticateUser>
		<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' name="<c:out value='${item.id}'/>" onClick="document.pressed='bookmark';document.parameter=this.name;"/>
		<a href="http://www.facebook.com/sharer.php?u=<c:out value="${item.link}"/>&t=<c:out value="${item.title}"/>" target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it!" alt="facebook it!" border="0" height='16' width='16'/></a>
		<a href='http://digg.com/submit?phase=2&url=<c:out value="${item.link}"/>&title=<c:out value="${item.title}"/>&bodytext=<c:out value="${item.desc}"/>&topic=world_news' target='_blank'><img src="<%= request.getContextPath() %>/light/images/digg.gif" title="digg it!" alt="digg it!" border="0" /></a>
	    <light:authenticateOwner>    
	    <input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" name="<c:out value='${item.id}'/>" style='border: 0px;' height='11' width='11' onClick="document.pressed='delete';document.parameter=this.name;"/>
	    </light:authenticateOwner> 	
	    <br/>
		<c:out value="${item.desc}" escapeXml="false"/>	
   </span>  
</td>
</tr>
   </c:forEach>   
</table>
</c:if>
<c:if test='${requestScope.pages > 1}'>
<table border='0' cellpadding='0' cellspacing='0'  width='95%'>
<tr>
<td class='portlet-table-td-center'>
<%@ include file="/common/paginator.jsp"%>
</td>
</tr>
</table>
</c:if>
</form>
</fmt:bundle>
</body>
</html>