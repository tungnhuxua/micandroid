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
<form action="<portlet:actionURL portletMode='VIEW' windowState='NORMAL'/>">
<p/>
<c:if test='${requestScope.org == null}'>
<table border='0' cellpadding='0' cellspacing='0' width="100%">
<tr>
<td class='portlet-table-td-right' width="15%">
*<fmt:message key="portlet.label.webId"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='webId' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width="15%">
*<fmt:message key="portlet.label.title"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='title' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.virtualHost"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='virtualHost' class='portlet-form-input-field' size='40'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.mx"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='mx' class='portlet-form-input-field' size='40'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.sendEmail"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='email' class='portlet-form-input-field' size='40'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.receiveEmail"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='receiveEmail' class='portlet-form-input-field' size='40'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.logo"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='logoUrl' class='portlet-form-input-field' size='40'/>
</td>
</tr>

<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.logoIcon"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='logoIcon' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width="15%">
*Meta:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='meta' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
*<fmt:message key="portlet.label.orgView"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='view' class='portlet-form-textarea-field' rows='5'  ></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
*<fmt:message key="portlet.label.orgMaxView"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='maxView' class='portlet-form-textarea-field' rows='5' ></textarea>
</td>
</tr>

<tr valign='top'>
<td class='portlet-table-td-right' colspan ='2'>
<input type='submit' name='action' onClick="document.pressed='add<c:out value="${requestScope.type}"/>'" value='<fmt:message key="portlet.button.add"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'  style="margin-right:10px;" />
</td>
</tr>
</table>
</c:if>

<c:if test='${requestScope.org != null}'>
<input type='hidden' name='orgId' value='<c:out value="${org.id}"/>' />
<table border='0' cellpadding='0' cellspacing='0' width="100%">
<tr valign="top">
<td class="portlet-table-td-right" width="15%"><fmt:message key="portlet.label.defaultPage"/>:</td>
<td class="portlet-table-td-left" width="85%">
<c:forEach var="tab" items="${requestScope.tabs}" >
<span class="portlet-rss" > 
<a href='page/<c:out value="${tab.id}" />?orgId=<c:out value="${org.id}"/>' target='_blank'><c:out value="${tab.label}"/></a>
</span>
</c:forEach>
</td>
</tr>

<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.webId"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='webId' class='portlet-form-input-field' size='40' value='<c:out value="${org.webId}"/>' />
</td>
</tr>

<tr>
<td class='portlet-table-td-right' width="15%">
*<fmt:message key="portlet.label.title"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='title' class='portlet-form-input-field' size='40' value='<c:out value="${org.title}"/>' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.virtualHost"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='virtualHost' class='portlet-form-input-field' size='40' value='<c:out value="${org.virtualHost}"/>'/>
</td>
</tr>

<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.mx"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='mx' class='portlet-form-input-field' size='40' value='<c:out value="${org.mx}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.sendEmail"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='email' class='portlet-form-input-field' size='40' value='<c:out value="${org.email}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
*<fmt:message key="portlet.label.receiveEmail"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='receiveEmail' class='portlet-form-input-field' size='40' value='<c:out value="${org.receiveEmail}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.logo"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='logoUrl' class='portlet-form-input-field' size='40' value='<c:out value="${org.logoUrl}"/>'/>
</td>
</tr>

<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.logoIcon"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='logoIcon' class='portlet-form-input-field' size='40' value='<c:out value="${org.logoIcon}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width="15%">
*Meta:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='meta' class='portlet-form-input-field' size='40' value='<c:if test='${requestScope.orgProfile != null}'><c:out value="${orgProfile.meta}"/></c:if>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
*<fmt:message key="portlet.label.orgView"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='view' class='portlet-form-textarea-field' rows='5' cols='60'><c:if test='${requestScope.orgProfile != null}'><c:out value="${orgProfile.view}"/></c:if></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
*<fmt:message key="portlet.label.orgMaxView"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='maxView' class='portlet-form-textarea-field' rows='5' cols='60'><c:if test='${requestScope.orgProfile != null}'><c:out value="${orgProfile.maxView}"/></c:if></textarea>
</td>
</tr>

<tr>
<td class='portlet-table-td-right' colspan ='2' >
<input type='submit' name='action' onClick="document.pressed='modify';document.state='normal';document.resetLastAction='1';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' style="margin-right:10px;"/>
</td>
</tr>
</table>
</c:if>
</form>
</fmt:bundle>
</body>
</html>