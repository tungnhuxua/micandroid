<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.javaside.cms.core.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS后台管理 - powered by aooan</title>
<link href="css/admin.css" rel="stylesheet" type="text/css">
<style type="text/css">
	html{height:100%; overflow:hidden;}
	body{height:100%;}
</style>
</head>
<body>
	<!--页头Begin-->
<div id="top">
<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr>
    <td width="330"><img src="image/cms_logo.jpg" border="0"></td>
    <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tbody><tr>
        <td align="right" background="image/top_bg.jpg" height="32">
		<table style="margin-top: 5px;" border="0" cellpadding="0" cellspacing="0">
          <tbody><tr>
          	<td align="left">
				<span style="color: red;">ooowo设计圈</span>
&nbsp;
          	</td>
            <td align="center" width="17"><img src="image/msg2.jpg" border="0" height="8" width="10"></td>
            <td align="left" width="80"><span style="color: rgb(255, 255, 255);">您好, <%=SpringSecurityUtils.getCurrentUserName()%></span></td>
            <td align="center" width="17"><img src="image/ico5.jpg" border="0"></td>
            <td align="left" width="60"><a href="${ctx}/j_spring_security_logout" class="channel">退 出</a></td>
          </tr>
        </tbody></table></td>
      </tr>
      <tr>
        <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody><tr>
          <!--
          <security:authorize ifAnyGranted="A_MANAGE_ARTICLE_CATEGORY">  
            <td align="right" background="image/sy_bg.jpg" width="95">
            	<a href="main.action" target="mainFrame">文章栏目</a> &nbsp;
			</td>
			</security:authorize>
			
			<td width="8"><img src="image/menu_sep.jpg" border="0"></td>
            <td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="main.action" target="mainFrame">文章栏目</a>
            </td>-->
            <security:authorize ifAnyGranted="A_VIEW_ARTICLE_CATEGORY_TREE">
			<td width="8"><img src="image/sy_bg.jpg" border="0"></td>
            <td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="articlemain.action" target="mainFrame">文章内容</a>
            </td>
             </security:authorize>
             <security:authorize ifAnyGranted="A_MANAGE_ADS,A_MANAGE_ADS_LOCATION">
			<td width="8"><img src="image/menu_sep.jpg" border="0"></td>
            <td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="adsLocationmain.action" target="mainFrame">广告管理</a>
            </td>
            </security:authorize>
            <security:authorize ifAnyGranted="A_MANAGE_RESOURCE_TYPE">
            <td width="8"><img src="image/menu_sep.jpg" border="0"></td>
			<td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="downloadmain.action" target="mainFrame">下载分类管理</a>
			</td>
			 </security:authorize>
			 <security:authorize ifAnyGranted="A_MANAGE_RESOURCE">
            <td width="8"><img src="image/menu_sep.jpg" border="0"></td>
			<td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="resourcemain.action" target="mainFrame">下载资源管理</a>
			</td>
			</security:authorize>
			<security:authorize ifAnyGranted="A_MANAGE_MEMBER,A_MANAGE_ROLE">
            <td width="8"><img src="image/menu_sep.jpg" border="0"></td>
			<td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="authoritymain.action" target="mainFrame">会员管理</a>
			</td>
			</security:authorize>
			
			<td width="8"><img src="image/menu_sep.jpg" border="0"></td>
			<td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="circlemanagermain.action" target="mainFrame">圈子管理</a>
			</td>
			
            <td width="8"><img src="image/menu_sep.jpg" border="0"></td>
			<td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="blogmanagermain.action" target="mainFrame">用户日志管理</a>
			</td>
			
			<!--
            <td width="8"><img src="image/menu_sep.jpg" border="0"></td>
			<td align="center" background="image/menu_bg.jpg" width="76">
            	<a href="#" target="mainFrame">网站配置</a>
			</td>-->
            <td width="8"><img src="image/menu_sep.jpg" border="0"></td>
			<td align="center" background="image/menu_bg.jpg" width="76">
			</td>
            <td background="image/menu_bg.jpg">&nbsp; </td>
          </tr>
        </tbody></table></td>
      </tr>
    </tbody></table></td>
  </tr>
</tbody></table>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr>
    <td align="center" background="image/msg_bg.jpg" width="99">
    <img src="image/xs.gif" border="0" height="26" width="99"></td>
    <td background="image/msg_bg.jpg"><!--<a href="javascript:void(0);">关闭左栏</a>-->&nbsp;</td>
    <td align="center" background="image/msg_bg.jpg" width="73">
    【<a href="${ctx }" target="_blank">查看首页</a>】</td>
    <td background="image/msg_bg.jpg" width="28">
    <img src="image/tleft.jpg" border="0" height="26" width="28"></td>
    <td align="center" background="image/tbg.jpg" width="140">
    <script language="JavaScript" type="text/javascript">
		<!--
				var enabled = 0; today = new Date();
				var day; var date;
				if(today.getDay()==0) day = " 星期日"
				if(today.getDay()==1) day = " 星期一"
				if(today.getDay()==2) day = " 星期二"
				if(today.getDay()==3) day = " 星期三"
				if(today.getDay()==4) day = " 星期四"
				if(today.getDay()==5) day = " 星期五"
				if(today.getDay()==6) day = " 星期六"
				date = (today.getFullYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日" + day +"";
				document.write(date);
		// -->
	  </script></td>
  </tr>
</tbody></table>
<div style="border-top: 1px solid rgb(24, 121, 176);"></div>
</div>
<!--页头End-->
<!--主体框架Begin-->
<iframe id="mainFrame" name="mainFrame" src="welcome.action" style="width: 100%; height: 423px;" frameborder="0" scrolling="no"></iframe>
<!--主体框架End-->
<div style="border-top: 1px solid rgb(24, 121, 176);"></div>
<div style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;"><iframe style="width: 186px; height: 199px;" src="Com_left_data/My97DatePicker.htm" border="0" frameborder="0" scrolling="no"></iframe></div>
</body>
</html>