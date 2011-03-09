<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
	<title>设计圈后台登录页</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet">
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
  	<script>
  		$(document).ready(function(){
    		$("#loginForm").validate();
    	});
  	</script>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="image/css.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2800.1106" name=GENERATOR/>
<SCRIPT language=JavaScript>
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

</SCRIPT>

<META content="MSHTML 6.00.5730.13" name=GENERATOR>

</HEAD>
<BODY bgColor=#efefef leftMargin=0 topMargin=130><BR>
<FORM onSubmit="return CheckUserPass()" id="loginForm" action="${ctx}/j_spring_security_check" method="post">
<TABLE height=360 cellSpacing=0 cellPadding=0 width="100%" border=0>

  <TBODY>
  <TR>
    <TD vAlign=center align=middle width="100%" background=admin/image/login_bg.gif height=360>
      <TABLE height=360 cellSpacing=0 cellPadding=0 width=509 border=0>
        <TBODY>
        <TR>
          <TD width=201 height=360><IMG height=360 
            src="admin/image/login_left.gif" width=201></TD>
          <TD vAlign=bottom align=middle width=289 
          background=admin/image/login_rbg.gif>
            <TABLE height=307 cellSpacing=0 cellPadding=0 width=275 border=0>
              <TBODY>
              <TR>
                <TD width=275 height=18>&nbsp;</TD></TR>
              <TR>
                <TD vAlign=bottom align=middle height=229>
                  <TABLE height=144 cellSpacing=0 cellPadding=0 width=240 
                  border=0>
                  	<%
		if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
	%>
			<span style="color:red"> 登录失败，请重试.</span>
	<%
		}
	%>
                    <TBODY>
                    <TR>
                      <TD class=fontbluex align=right width=92 
                      height=26>用户名：</TD>
                      <TD colSpan=2>
                     <input type='text' name='j_username' class="required"
					<s:if test="not empty param.error"> value='<%=session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if>/>
                      </TD></TR>
                    <TR>
                      <TD class=fontbluex align=right height=26>密&nbsp;&nbsp;&nbsp;码：</TD>
                      <TD colSpan=2><input type='password' name='j_password' class="required" /></TD></TR>
                    <TR>
                    <TR>
                      <TD class=fontbluex align=right height=26><input type="checkbox" name="_spring_security_remember_me" /></TD>
                      <TD colSpan=2 abbr="left">两周内记住我</TD></TR>
                    <TR>
                      <TD class=fontbluex align=right height=40>&nbsp;</TD>
                      <TD align=middle colSpan=2 height=40><INPUT type=submit value=登录></TD>
                     </TR>
                     </TBODY>
                     </TABLE>
                     </TD>
                    </TR>
              <TR>
                <TD height=60>&nbsp;</TD></TR></TBODY></TABLE></TD>
          <TD width=19>
          <IMG height=360 src="admin/image/login_right.gif" width=19></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
  </FORM>
 </BODY>
</HTML>
