<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
<TITLE>设计圈后台管理登录</TITLE>
	<%@ include file="/common/meta.jsp"%>
	<LINK href="${ctx}/css/admin.css" type="text/css" rel="stylesheet">
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
  	<script>
  		$(document).ready(function(){
    		$("#loginForm").validate();
    	});
    	
  		function CheckUserPass()
		{
		  var username =document.getElementById('username').value;
		  var password =document.getElementById('password').value;
		
		  if(document.getElementById('username').value==""){
             alert("请输入用户名");
             return false;
	       }else if (document.getElementById('password').value==""){
	          alert("请输入密码");
	          return false;
	       }
		}
  	</script>


<STYLE type=text/css>BODY {
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 12px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; BACKGROUND-COLOR: #eeeeee
}
.input {
	BORDER-RIGHT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; WIDTH: 150px; BORDER-BOTTOM: #cccccc 1px solid; HEIGHT: 16px
}
.version {
	FONT-WEIGHT: bold; FONT-SIZE: 25px; COLOR: #000000
}
</STYLE>


<META content="MSHTML 6.00.2900.5726" name=GENERATOR></HEAD>
<BODY>
<TABLE style="MARGIN-TOP: 10%" cellSpacing=0 cellPadding=0 align=center 
border=0>
  <TBODY>
  <TR>
    <TD><IMG src="${ctx}/images/login_r1_c1.jpg"></TD>
    <TD width=343 background=${ctx}/images/login_r1_c2.jpg>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD height=35>&nbsp;</TD></TR></TBODY></TABLE></TD>
    <TD><IMG src="${ctx}/images/login_r1_c3.jpg"></TD></TR>
  <TR>
    <TD><IMG src="${ctx}/images/login_r2_c1.jpg"></TD>
    <TD vAlign=top background=${ctx}/images/login_r2_c2.jpg>
      <FORM onSubmit="return CheckUserPass()" id="loginForm" action="${ctx}/j_spring_security_check" method="post">
      <TABLE style="MARGIN-TOP: 5px" cellSpacing=0 cellPadding=0 width="100%" 
      border=0>

        <TBODY>
        <TR>
          <TD align=middle colSpan=2 height=30>致力科技进步，成就企业价值!</TD>
        </TR>
        <TR>
          <TD class=f12-white align=right height=15></TD>
          <TD> <%if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {%>
		 <span style="color:red"> 登录失败，请重试.</span>
	  <%}%></TD>
        </TR>
        <TR>
          <TD class=f12-white align=right width="30%" height=30>用户名：</TD>
          <TD ><input type='text' name='j_username' class="required"
					<s:if test="not empty param.error"> value='<%=session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if>/></TD>
        </TR>
        <TR>
          <TD class=f12-white align=right height=30>密　码：</TD>
          <TD><input type='password' name='j_password' class="required" /></TD></TR>
        <TR>
          <TD class=f12-white align=right height=30></TD>
          <TD><input type="checkbox" name="_spring_security_remember_me" />两周内记住我</TD>
        </TR>
        <TR>
          <TD height=35>&nbsp;</TD>
          <TD>&nbsp;</TD></TR>
        <TR>
          <TD align=middle colSpan=2 height=30><INPUT type=image 
            src="${ctx}/images/login.gif"> &nbsp; <A 
            href="javascript:loginForm.reset();"><IMG 
            src="${ctx}/images/reset.gif" 
      border=0></A></TD></TR></TBODY></TABLE></FORM></TD>
    <TD><IMG src="${ctx}/images/login_r2_c3.jpg"></TD></TR></TBODY></TABLE>
</BODY>
</HTML>
