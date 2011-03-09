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

<table border='0' cellpadding='0' cellspacing='0'  width='100%'>
<tr>	
	<td class='portlet-table-td-center'>
	<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','maximized','previous=1;index=<c:out value="${item.index}"/>');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' title='<fmt:message key="portlet.label.previous"/>' style='border: 0px' /></a>						
	<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','maximized','next=1;index=<c:out value="${item.index}"/>');" ><img src='<%= request.getContextPath() %>/light/images/next.gif' title='<fmt:message key="portlet.label.next"/>' style='border: 0px' /></a>	
	<light:authenticateUser>
	<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popRssItem(event,'<c:out value="${item.index}"/>','<c:out value="${requestScope.responseId}"/>');"/>
	<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardRssToFriend(event,'<c:out value="${item.index}"/>','<c:out value="${requestScope.responseId}"/>');"/>
	<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' onClick="javascript:saveToBookmark(event,'<c:out value="${item.index}"/>','<c:out value="${requestScope.responseId}"/>');"/>	
	<a href="http://www.facebook.com/sharer.php?u=<c:out value="${item.link}"/>&t=<c:out value="${item.title}"/>" target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it!" alt="facebook it!" border="0" height='16' width='16'/></a>
	<a href='http://digg.com/submit?phase=2&url=<c:out value="${item.link}"/>&title=<c:out value="${item.title}"/>&bodytext=<c:out value="${item.desc}"/>&topic=world_news' target='_blank'><img src="<%= request.getContextPath() %>/light/images/digg.gif" alt="digg it!" title="digg it!" border="0" /></a>
	</light:authenticateUser>
	<a href='<c:out value="${item.link}"/>' target='_blank'><img src="<%= request.getContextPath() %>/light/images/tab.png" title='<fmt:message key="portlet.label.open.browser.tab"/>' border="0" /></a>
	<input type="image" title='<fmt:message key="portlet.button.back"/>' src="<%= request.getContextPath() %>/light/images/exit.png" style='border: 0px;' height='16' width='16' name="<c:out value='${item.index}'/>" onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');"/>
    </td>
</tr>
</table>

<table width="100%">    
    <tr>
        <td>
        	<% String rssLink = ((org.light.portlets.rss.RssBean)request.getAttribute("item")).getLink();
        	   if(rssLink.indexOf("?")>0){
        		   rssLink+="&internal=true";
        	   }else{
        		   rssLink+="?internal=true";
        	   }
        	%>
        	<c:if test='${requestScope.state == "normal"}'>
            <iframe src="<%= rssLink %>" width="100%" height="<%= request.getAttribute("readerHeight") %>" frameborder="0">
	            Your browser does not support iframes
            </iframe>
            </c:if>
            <c:if test='${requestScope.state == "maximized"}'>
            <iframe src="<%= rssLink %>" width="100%" height="<%= request.getAttribute("readerMaxHeight") %>" frameborder="0">
	            Your browser does not support iframes
            </iframe>
            </c:if>
        </td>
    </tr>
</table>
</fmt:bundle>