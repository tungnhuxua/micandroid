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
<c:if test='${requestScope.item != null}'>
<table border='0' cellpadding='0' cellspacing='0'  width='95%'>
    <tr>
	<td class='portlet-table-td-left'>   
	<span class="portlet-item">
	<light:authenticateUser>
    <a href='javascript:void(0)' onclick="javascript:addObjectComment(event,'<c:out value="${requestScope.responseId}"/>',<c:out value="${item.id}"/>,<%= org.light.portal.util.Constants._OBJECT_TYPE_POPULAR_ITEM %>);" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addComment"/></a>    
    </light:authenticateUser>
    </span>
	</td>
    <td class='portlet-table-td-right'> 
    <span class="portlet-item">
   	<a href='javascript:void(0)' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" ><fmt:message key="portlet.button.back"/></a>
    </span>
	</td>
	</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0'  width='95%'>
	<tr>
	<td class='portlet-table-td-center' width='10%'>   
	<span class="portlet-item">
	<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${item.uri}"/>' ><c:out value="${item.displayName}"/></a>
	<br/>
	<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${item.uri}"/>' >
	<c:if test='${item.photoUrl == null}'>
	<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='40' height='30'/>
	</c:if>
	<c:if test='${item.photoUrl != null}'>
	<img src='<%= request.getContextPath() %><c:out value="${item.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${item.photoSmallWidth / 4}"/>' height='<c:out value="${item.photoSmallHeight / 4}"/>'/>
	</c:if>
	</a>
	<br/>
	<c:if test='${item.currentStatusId == 1 }'>
	<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='16' width='16'  align="bottom" alt=''/>
	</c:if>
	</span>
	</td>
	<td class='portlet-table-td-left'>
	<b><FONT color='#ff6600' size='4'><c:out value="${item.popCount}"/></FONT></b> <fmt:message key="portlet.label.votes"/> 
	<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' name="<c:out value='${item.id}'/>"  onClick="document.pressed='pop';document.parameter=this.name;"/>
	<light:authenticateUser>
	<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' name="<c:out value='${item.id}'/>" onClick="document.pressed='forward';document.parameter=this.name;"/>
	</light:authenticateUser>
	<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' name="<c:out value='${item.id}'/>" onClick="document.pressed='bookmark';document.parameter=this.name;"/>
	<a href="http://www.facebook.com/sharer.php?u=<c:out value="${item.link}"/>&t=<c:out value="${item.title}"/>" target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it!" alt="facebook it!" border="0" height='16' width='16'/></a>
	<a href='http://digg.com/submit?phase=2&url=<c:out value="${item.link}"/>&title=<c:out value="${item.title}"/>&bodytext=<c:out value="${item.desc}"/>&topic=world_news' target='_blank'><img src="<%= request.getContextPath() %>/light/images/digg.gif" title="digg it!" alt="digg it!" border="0" height='16' width='16'/></a>
	<br/>
    <span class="portlet-rss">
   	<a href='<c:out value="${item.link}"/>' target='blank'><c:out value="${item.title}"/></a>   	    
    </span>
    <br/>
    <c:out value="${item.desc}" escapeXml="false" />
	</td>
	</tr>   
</table>
</c:if>
<span class="portlet-title">
<fmt:message key="portlet.label.comments"/>
</span>
<br/><br/>
<c:if test='${requestScope.comments != null}'>
<table border='0' cellpadding='0' cellspacing='0'  width='95%'>
   <c:forEach var="comment" items="${requestScope.comments}" varStatus="status" >
	<tr>
	<td class='portlet-table-td-center' width='10%'>   
	<span class="portlet-item">
	<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${comment.uri}"/>' ><c:out value="${comment.displayName}"/></a>
	<br/>
	<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${comment.uri}"/>' >
	<c:if test='${comment.photoUrl == null}'>
	<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
	</c:if>
	<c:if test='${comment.photoUrl != null}'>
	<img src='<%= request.getContextPath() %><c:out value="${comment.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
	</c:if>
	</a>
	<br/>
	<c:if test='${comment.currentStatusId == 1 }'>
	<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='16' width='16'  align="bottom" alt=''/>
	</c:if>
	</span>
	</td>
	<td class='portlet-table-td-left'>
	<fmt:message key="portlet.label.date"/>: <c:out value="${comment.date}"/>
	<br/><br/>
	<c:out value="${comment.comment}" />
	</td>
	</tr>
   </c:forEach> 
</table>
<table border='0' cellpadding='0' cellspacing='0'  width='95%'>
    <tr>
	<td class='portlet-table-td-left'>   
	<span class="portlet-item">
	<light:authenticateUser>
    <a href='javascript:void(0)' onclick="javascript:addObjectComment(event,'<c:out value="${requestScope.responseId}"/>',<c:out value="${item.id}"/>,<%= org.light.portal.util.Constants._OBJECT_TYPE_POPULAR_ITEM %>);" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addComment"/></a>    
    </light:authenticateUser>
    </span>
	</td>
    <td class='portlet-table-td-right'> 
    <span class="portlet-item">
   	<a href='javascript:void(0)' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" ><fmt:message key="portlet.button.back"/></a>
    </span>
	</td>
	</tr>
</table>
</c:if>
<br/>
</form>
</fmt:bundle>
</body>
</html>