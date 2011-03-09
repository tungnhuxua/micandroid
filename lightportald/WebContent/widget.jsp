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
<%
String id = request.getParameter("id");
if(id != null){
	long portletId = Long.parseLong(id);	
	org.springframework.context.ApplicationContext ctx = (org.springframework.context.ApplicationContext) request.getSession().getServletContext().getAttribute(org.springframework.web.context.WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);	
	org.light.portlets.widget.Widget widget = ((org.light.portal.core.service.PortletService)ctx.getBean("portletService")).getWidgetByPortletId(portletId);
	if(widget != null){				 
		 request.setAttribute("widget",widget);					
	 }
}
%>
<table border='0' cellpadding='0' cellspacing='0' width='100%' > 
<tr> 
<td class='portlet-table-td-center'> 
<c:if test='${widget != null}'>
<c:out value="${widget.content}" escapeXml="false"/>
</c:if>
<c:if test='${widget == null}'>
widget error.
</c:if>
</td>
</tr>
</table>
</body>
</html>