<%
String queryString = request.getQueryString();
if (queryString != null && queryString.length() > 0){
	org.light.portal.core.auth.openid.OpenIdAuthentication.getInstance().verifyResponse(request,response);		
}
%>