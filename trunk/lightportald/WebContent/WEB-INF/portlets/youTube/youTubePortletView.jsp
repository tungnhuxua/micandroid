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
<script language="JavaScript">

  keyDownYouTube  = function (e, id) {  
	  var KeyID;
	  if (window.event) {	
		keyID = window.event.keyCode;
	  } else {
	    keyID = e.which;
	  } 
	  if ( keyID == 13){   
	    submitYouTube(id);    
	  }
	  return !(keyID == 13);
  }
  
  submitYouTube = function (id){
 	var key = document.forms['form_'+id]['tag'].value; 
 	Light.executeAction(id,'','','','','','maximized','action=search;tag='+key); 	
  } 

</script>
<fmt:bundle basename="resourceBundle">
<form name="form_<c:out value="${requestScope.responseId}"/>" >
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left'>
<img src='<%= request.getContextPath() %>/light/images/youtube.png' style='border: 0px;'  align="middle" />
<input type='text' name='tag' class='portlet-form-input-field' size='10'
	 onkeypress="return keyDownYouTube(event,'<c:out value="${requestScope.responseId}"/>');"/> 
<input name='Submit' type='button' class='portlet-form-button' value='<fmt:message key="portlet.button.go"/>' 
	 onclick="javascript:submitYouTube('<c:out value="${requestScope.responseId}"/>');" />
<a href="javascript:void(0);" title='<fmt:message key="portlet.label.mostFeatured"/>' onClick="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','','','','','maximized','action=featured');"><img src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16'/></a> 	 
</td>
</td>
</tr>
</table>
</form>
<c:if test='${sessionScope.youTubes != null}'>
<table border='0' cellpadding='0' cellspacing='0' width= '95%' >
<c:forEach var="video" items="${sessionScope.youTubes}" varStatus="status">
<c:if test='${status.index == 0}'>
<tr class='portlet-table-td-left'>
<td class='portlet-item'>
<light:authenticateUser>  
<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popYouTubeItem(event,'<c:out value="${video.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardYouTubeToFriend(event,'<c:out value="${video.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
</light:authenticateUser>
<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' onClick="javascript:saveYouTubeToBookmark(event,'<c:out value="${video.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
<c:out value="${video.title}"/>
<br/>
<fmt:message key="portlet.label.author"/>: 
<a href='javascript:void(0)' onclick="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','','','','','','action=author;author=<c:out value="${video.author}"/>');">
<c:out value="${video.author}"/>
</a>
<br/>
<span >
<a href='<c:out value="${video.videoUrl}"/>' title='<c:out value="${video.desc}"/>'><img src='<c:out value="${video.picUrl}" />' style='border: 0px;'  align="middle" /></a>
</span>
</td>
</tr>
</c:if>
</c:forEach>
</table>
<br/>
<c:if test='${requestScope.state == "normal" && requestScope.videosCount > 1 }'>
	<span class="portlet-rss" style="text-align:right;">
	<a href='javascript:void(0)' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" >more......</a> 
	</span>
</c:if>
</c:if>
</fmt:bundle>
</body>
</html>