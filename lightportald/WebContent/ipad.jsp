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
<light:subdomain> 
<head>			       
	<light:portal/>	    
	<LINK href="<%= request.getContextPath() %>/light/theme<%= (session.getAttribute("browser") != null) ? (String)session.getAttribute("browser") : ""%>/common.css" rel="stylesheet" type="text/css">
	<LINK href="<%= request.getContextPath() %>/light/theme/<%= (session.getAttribute("theme") != null) ? (String)session.getAttribute("theme") : "theme1"%>/theme.css" rel="stylesheet" type="text/css">	    
	<link rel="apple-touch-icon" href='<%= org.light.portal.util.PropUtil.getString("iphone.icon") %>' />
	<%@ include file="/common/meta.jsp"%>	    
	<meta name = "viewport" content = "width = device-width">
	<meta name = "viewport" content = "initial-scale = 1.0,user-scalable = no">	     
	<script language="JavaScript" type="text/javascript" src="cache/main/all<%= org.light.portal.util.PropUtil.getString("js.light.version") %>.js"></script>	
	<title><c:out value="${sessionScope.org.title}"/></title>
</head>
<body>
  <div id="initView">
  	<light:portlets/>	    		 
  	<jsp:include page="/WEB-INF/view/initIPadViews.jsp" flush="true"></jsp:include>  
  </div>     
</body>
</light:subdomain>
</html>