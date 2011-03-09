<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/login.css">
<div id="login" style="width:450px; height:450px; border:5px solid #CCC; background-color:#f9f9f9;text-align:left;padding-left:40px;display:none">
  <h2>USERS LOGIN_用户登陆</h2>
  <h3>欢迎来到中国新锐设计师创意集会网站</h3>
  
  <ul>
     <li>_登陆邮箱_Username</li>
     <li><input  type="text" size="40" id="username"/></li>
     <li>&nbsp;</li>
     <li>_登陆密码_Passworde</li>
     <li><input  type="password" size="40" id="userpassword"/></li>
  </ul>
  
  <h2><span onclick="userlogin();" style="cursor: pointer;">确认登陆_Login</span></h2>
  <h4><span onclick="toregister();" style="cursor: pointer;">注册_Join</span></h4>
  
  <div id="login_forgetpassword">
  <input type="checkbox" name="remember"/> _记住用户名密码，保持登陆状态_Keep me logged in until I Log Out<br />
  <span>_忘记密码_Forgot Your Username or Password?</span> 
  </div>
</div>
