<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>搜索宁波 - So Ningbo</title>
<script src="scripts/jquery-1.7.min.js" type="text/javascript"></script>
<script src="scripts/so_ningbo.js" type="text/javascript"></script>
<link href="global.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="container">
  <div class="header" style="height:90px">
    
    <div class="logo"> 
    <a href="http://www.soningbo.com"> 
    <img src="images/home_logo.png" alt="搜索宁波 - So Ningbo" name="logo" width="180" height="46" id="logo" style="display:block;" /> 
    </a> 
    </div>
    
    <div class="sign_in">
    <table width="300" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>电子邮件 </th>
    <td>密码</th>
    <td>保持登录</th>
    <td width="78">&nbsp;</th>
  </tr>
  <tr>
    <td><input type="text" name="email" id="email" tabindex="1" /></td>
    <td><input type="text" name="password" id="password" tabindex="1" /></td>
    <td>
    
    <div class="keep_me">
    <div class="keep_me_cover" id="keeptrying"><img src="images/keep_me_cover.png" width="36" height="30" /></div>
    </div>
    
    </td>
    <td width="78">
    <div >
   
    
    <div class="btn" id="sign_in_btn" style="-moz-box-shadow: 0px 0px 0px 0 #CCC;
	-webkit-box-shadow: 0px 0px 0px 0 #CCC;
	box-shadow: 0px 0px 0px 0 #CCC;">登录 </div>
    </div>
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div id="forgot_password" style="cursor:hand; cursor:pointer">忘记密码?</div></td>
    <td>&nbsp;</td>
    <td width="78">&nbsp;</td>
  </tr>
</table>
    </div> 
    
    
    
  </div>
  
  <div class="content">
  
  <div class="home_video">
  <div style="padding-top:50px; font-size:24px; font-weight:bold; color:#333">您的城市宁波</div>
  <div style="color:#333333">宁波，您的城市－ 探索，并成为它的一部分！</div>
  <div style="padding-top:15px"><img src="images/video.jpg" width="430" height="262" /></div>
  </div>
  
  <div class="home_login">
    <div style="width:90%; margin: 0 auto; padding-top:50px; font-size:24px; color:#333333">
    登录<br />
    <div style="font-size:16px">登录      免费与你的城市连接</div>
        <div class="greybar" style="margin-top:10px"></div>
    <div class="whitebar"></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:15px">
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">名称:</th>
    <td><input type="text" name="reg_name" id="reg_name" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">电子邮件:</th>
    <td><input type="text" name="reg_email" id="reg_email" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">再次输入电子邮件:</th>
    <td><input type="text" name="reg_email2" id="reg_email2" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">密码:</th>
    <td><input type="text" name="reg_password" id="reg_password" tabindex="1" class="text_fld" /></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">我:</th>
    <td>
      <select name="reg_gender" id="reg_gender" class="drop_down">
        <option value="0" selected="selected">选择性别 </option>
        <option value="1">男</option>
        <option value="2">性别</option>
      </select></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">出生日期:</th>
    <td><select name="reg_year" id="reg_year">
      <option value="0" selected="selected">年 </option>
      <option value="1905">1905</option>
      <option value="1906">1906</option>
      <option value="1907">1907</option>
      <option value="1908">1908</option>
      <option value="1909">1909</option>
      <option value="1910">1910</option>
      <option value="1911">1911</option>
      <option value="1912">1912</option>
      <option value="1913">1913</option>
      <option value="1914">1914</option>
      <option value="1915">1915</option>
      <option value="1916">1916</option>
      <option value="1917">1917</option>
      <option value="1918">1918</option>
      <option value="1919">1919</option>
      <option value="1920">1920</option>
    </select>
      <select name="reg_month" id="reg_month">
        <option value="0" selected="selected">月</option>
        <option value="1">一月 </option>
        <option value="2">二月 </option>
      </select>
      <select name="reg_day" id="reg_day">
        <option value="0" selected="selected">日</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
        <option value="13">13</option>
        <option value="14">14</option>
        <option value="15">15</option>
        <option value="16">16</option>
        <option value="17">17</option>
        <option value="18">18</option>
        <option value="19">19</option>
        <option value="20">20</option>
        <option value="21">21</option>
        <option value="22">22</option>
        <option value="23">23</option>
        <option value="24">24</option>
        <option value="25">25</option>
        <option value="26">26</option>
        <option value="27">27</option>
        <option value="28">28</option>
        <option value="29">29</option>
        <option value="30">30</option>
        <option value="31">31</option>
      </select></td>
  </tr>
  <tr>
    <td style="text-align:right; font-size:14px; padding-right:15px">&nbsp;</th>
    <td>
    
    <div id="reg_signup_btn"><div style="margin-left:90px;">注册</div></div>
    
    </td>
  </tr>
</table>

 <div class="greybar" style="margin-top:10px"></div>
    <div class="whitebar"></div>
    
   <div style="text-align:center; font-size:12px; padding-top:10px"> 为您的企业创建页面，点击这里</div>
    </div>
    
  
  </div>
  
  
 <jsp:include page="/footer.jsp" />
</div>
</body>
</html>
