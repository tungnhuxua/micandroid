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
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.rssLists != null}'>	
    <c:if test='${requestScope.portlet.showType == 0}'>
		<c:forEach var="item" items="${requestScope.rssLists}" >		
		<span class="portlet-rss" >
		<light:authenticateUser> 
		<image src="<%= request.getContextPath() %>/light/images/showMod.gif" style='border: 0px;cursor:pointer;' height='16' width='16' onClick='javascript:Light.showLinkAction(event,"<c:out value='${requestScope.responseId}'/>","<c:out value='${item.index}'/>","<c:out value='${item.link}'/>",this,"showNewsAction");'/>	
	    </light:authenticateUser> 
	    <a href='javascript:' onclick="javascript:trackRssItem('<c:out value="${item.index}"/>','<c:out value="${requestScope.responseId}"/>');Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','maximized','index=<c:out value="${item.index}"/>');"
			onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_RSS_ITEM %>,'<c:out value="${item.index}"/>','<c:out value="${item.link}"/>');"
		    onmouseout="javascript:Light.hideSummary();"><c:out value="${item.title}"/></a>	
		</span>
		
		</c:forEach>
	</c:if>
	
	<c:if test='${requestScope.portlet.showType == 1}'>
	<c:forEach var="item" items="${requestScope.rssLists}" >
	<span class="portlet-rss">
	<image src="<%= request.getContextPath() %>/light/images/showMod.gif" style='border: 0px;cursor:pointer;' height='16' width='16' onClick='javascript:Light.showLinkAction(event,"<c:out value='${requestScope.responseId}'/>","<c:out value='${item.index}'/>","<c:out value='${item.link}'/>",this,"showNewsAction");'/>	
    <c:if test='${item.addLink}'>
	<a href='<c:out value="${item.link}"/>' target='_blank' onclick="javascript:trackRssItem('<c:out value="${item.index}"/>','<c:out value="${requestScope.responseId}"/>');"><c:out value="${item.desc}" escapeXml="false"/></a>
	<br/>
	</c:if>
	<c:if test='${!item.addLink}'>
	<c:out value="${item.desc}" escapeXml="false"/>
	</c:if>
	<br/>
	</span>
	</c:forEach>
	</c:if>
	
	<c:if test='${requestScope.portlet.showType == 2}'>
	<c:forEach var="item" items="${requestScope.rssLists}" >
	<span class="portlet-rss">
	<image src="<%= request.getContextPath() %>/light/images/showMod.gif" style='border: 0px;cursor:pointer;' height='16' width='16' onClick='javascript:Light.showLinkAction(event,"<c:out value='${requestScope.responseId}'/>","<c:out value='${item.index}'/>","<c:out value='${item.link}'/>",this,"showNewsAction");'/>	
    <a href='<c:out value="${item.link}"/>' target='_blank' onclick="javascript:trackRssItem('<c:out value="${item.index}"/>','<c:out value="${requestScope.responseId}"/>');"><c:out value="${item.title}"/></a>
	<br/>
	<c:out value="${item.desc}" escapeXml="false"/>
	<br/>
	</span>	
	</c:forEach>
	</c:if>
	
	<c:if test='${requestScope.state == "normal" && requestScope.showMore != null }'>
		<%@ include file="/common/paginator.jsp"%>
	</c:if>
</c:if>
<c:if test='${requestScope.error != null}'>
<br/>
<span class="portlet-item">
<c:out value="${requestScope.error}"/>
</span>
<br/>
</c:if>
<c:if test='${requestScope.showMore == null }'>
<br/>	
</c:if>
</fmt:bundle>
</body>
</html>