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
	<light:portal/>	    
	<LINK href="<%= request.getContextPath() %>/light/theme<%= (session.getAttribute("browser") != null) ? (String)session.getAttribute("browser") : ""%>/common.css" rel="stylesheet" type="text/css">
	<LINK href="<%= request.getContextPath() %>/light/theme/<%= (session.getAttribute("theme") != null) ? (String)session.getAttribute("theme") : "theme1"%>/theme.css" rel="stylesheet" type="text/css">	    
	<link rel="apple-touch-icon" href='<%= org.light.portal.util.PropUtil.getString("iphone.icon") %>' />
	<%@ include file="/common/meta.jsp"%>	    
	<meta name = "viewport" content = "width = device-width">
	<meta name = "viewport" content = "initial-scale = 1.0,user-scalable = no">	     
	<title><c:out value="${sessionScope.org.title}"/></title>
</head>
<body>
  <% 
		int left = org.light.portal.util.PropUtil.getInt("portal.bar.width",180);
  		int width = 768 - left;
	%>
	<div id="portalContainer" class="portal-container" style="position:absolute;width:<%= width %>px;left:<%= left %>px;height:1684px;top:0pt;">
		<div id="portalHeader" class="portal-header">
			<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.headerInner.mobile.page") %>'></jsp:include>
	    </div>
	    <div id="portalMenu" class="portal-header-menu" style="z-index:1088;">
	    	<jsp:include page="/WEB-INF/view/default/menuInner.jsp"></jsp:include>
	    </div>
		<div id="portalBody" class="portal-body">
			<div id="portalContent" style="position:absolute;width:<%= width %>px;" class="portal-content">
				<div id="tabs" class="portal-tabs">
					<jsp:include page="/WEB-INF/view/default/tabsInner.jsp"></jsp:include>
				</div>
				<div id="tabPanels" class="portal-tab-panels">
					<jsp:include page="/WEB-INF/view/default/ipadpannelsInner.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div id="portalFooter" style="width:<%= width %>px;top:1724px;" class="portal-footer">
			<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.footerInner.ipad.page") %>'></jsp:include>
		</div>
	</div>
	
	<script language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/cache/main/all<%= org.light.portal.util.PropUtil.getString("js.light.version") %>.js"></script>	
  
</body>

</html>