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
<light:getHost/>
<c:forEach var="bean" items="${requestScope.portalInitList}">
<var id='<c:out value="${bean.id}"/>' title= '<c:out value="${bean.desc}"/>'></var>
</c:forEach>

<textarea id="empty.view" style="display:none;">
<div></div>
</textarea>

<textarea id="progressBar.view" style="display:none;" rows='0' cols='0'>
	<img src='<%= request.getContextPath() %>/light/images/progressBar.gif' align="middle" alt='' /><br/><fmt:message key="message.loading"/>
</textarea>
<textarea id="searchBar.view" style="display:none;"  rows='0' cols='0'>
	<input type='text' id='portalSearch' class='portlet-form-input-field' size='16' style="width:100px;"  onchange="javascript:Light.globalSearch(L$('portalSearch').value,'');"/>
	<input type="button" class="icons_search" style="" title='<fmt:message key="portlet.button.search"/>' onclick="javascript:Light.globalSearch(L$('portalSearch').value,'');" value="" />
</textarea>

</fmt:bundle>