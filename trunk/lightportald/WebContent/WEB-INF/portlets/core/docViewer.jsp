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

<table border='0' cellpadding='0' cellspacing='0'  width='98%'>
<tr>	
	<td class='portlet-table-td-right'>
	<input type="image" title='<fmt:message key="portlet.button.back"/>' src="<%= request.getContextPath() %>/light/images/exit.png" style='border: 0px;margin:10px;' height='16' width='16' name="<c:out value='${item.index}'/>" onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');"/>
    </td>
</tr>
</table>

<table width="100%">    
    <tr>
        <td>        	
            <iframe src="<c:out value="${url}"/>" width="100%" height="500" frameborder="0">
	            Your browser does not support iframes
            </iframe>
        </td>
    </tr>
</table>
</fmt:bundle>