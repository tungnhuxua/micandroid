<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.javaside.cms.core.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<title></title>
<link href="css/admin.css" rel="stylesheet" type="text/css">
<link href="css/theme.css" rel="stylesheet" type="text/css">

<script src="../js/jquery.js" type="text/javascript"></script>
</head>
<body>
<div class="body-box">
<table   style="position:absolute;left:0;top:0;"   height="100%*"   width="100%*"   valign="middle">  
      <tr   valign="middle">  
          <td>  
              <table   width="*"   border="0"   align="center"   valign="middle">  
                  <tr>  
                      <td>  </br></br></br></br></br>
  							<h3>欢迎<%=SpringSecurityUtils.getCurrentUserName()%>登录OOOWO后台.</h3>
                      </td>  
                  </tr>  
              </table>  
          </td>  
      </tr>  
  </table>   
</div>

</body>
</html>