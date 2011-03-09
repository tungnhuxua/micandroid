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
		<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
			<tr valign='top'>
				<td class='portlet-table-td-left' width= '100%' colspan='3'>
					
				</td>
			</tr>
			<c:forEach var="buddy" items="${requestScope.members}" varStatus="status">
				<tr valign='top'>
				    
					<td class='portlet-table-td-left' width= '20%'>
						<c:if test='${buddy.photoUrl == null}'>
							<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${buddy.uri}"/>' target='_blank'>
							<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
							</a>
						</c:if>
						<c:if test='${buddy.photoUrl != null}'>
							<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${buddy.uri}"/>' target='_blank'>
							<img src='<%= request.getContextPath() %><c:out value="${buddy.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
							</a>
						</c:if>
					</td>
					<td class='portlet-table-td-left' width= '60%'>
						<span class='portlet-item'>
						    <c:if test='${buddy.userCurrentStatusId == 1}'>
							<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px' width='15' height='12' align="bottom" alt=''/>
							</c:if>
							<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${buddy.uri}"/>' target='_blank' ><c:out value="${buddy.displayName}"/></a>
						</span>
						<br/>
					</td>
					<td class='portlet-table-td-left' width= '20%'>
					</td>					
				</tr>
			</c:forEach>
			<tr valign='top'>			    
				<td class='portlet-table-td-right' width= '80%' colspan='2'>
					<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
				</td>
				<td class='portlet-table-td-left' width= '20%'>
				</td>
			</tr>						
		</table>
	</fmt:bundle>
</body>
</html>