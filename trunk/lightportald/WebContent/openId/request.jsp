<%
String url = request.getParameter("openid_identifier");	
org.light.portal.core.auth.openid.OpenIdAuthentication.getInstance().authRequest(url,request,response);		
%>