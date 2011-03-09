<%@ include file="/common/taglibs.jsp"%>
<html>
<head>	    
	<light:portal/>
	<LINK href="<%= request.getContextPath() %>/light/theme<%= (session.getAttribute("browser") != null) ? (String)session.getAttribute("browser") : ""%>/common.css" rel="stylesheet" type="text/css">
	<LINK href="<%= request.getContextPath() %>/light/theme/<%= (session.getAttribute("theme") != null) ? (String)session.getAttribute("theme") : "theme6"%>/theme.css" rel="stylesheet" type="text/css">	    
	<%@ include file="/common/meta.jsp"%>	    		
	<title><c:out value="${sessionScope.org.title}"/></title>
</head>
<body>
	<% 
		int left = org.light.portal.util.PropUtil.getInt("portal.bar.width",180);
		int width = org.light.portal.util.PropUtil.getInt("portal.width",1016);
	%>
	<div id="portalContainer" class="portal-container" style="position:absolute;width:<%= width %>px;left:<%= left %>px;height:1684px;top:0pt;">
		<div id="portalHeader" class="portal-header">
			<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.headerInner.page") %>'></jsp:include>
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
					<jsp:include page="/WEB-INF/view/default/pannelsInner.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div id="portalFooter" style="width:<%= width %>px;top:1724px;" class="portal-footer">
			<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.footerInner.page") %>'></jsp:include>
		</div>
	</div>
  	
  	<script language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/cache/main/all<%= org.light.portal.util.PropUtil.getString("js.light.version") %>.js"></script>	
  	<!-- 
  	<script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/json2.js"></script>
	<script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/prototype.js"></script>
	<script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/rsh.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/template.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/light.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightExt.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightService.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightAjax.js"></script>	    
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightUtil.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortal.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortalCore.js"></script>		      	    	 	    		    					      	    	 	    		    				
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortalHeader.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortalMenu.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortalMenuMain.js"></script>
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortalTab.js"></script>		
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortalFooter.js"></script>		
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightPortlet.js"></script>	
    <script language="JavaScript" src="<%= request.getContextPath() %>/light/scripts/lightWindow.js"></script>	
     -->	
</body>
</html>