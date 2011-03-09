<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript"> 
	function copyUrl()
	{ 
	 var clipBoardContent=document.requestFrom.requesthttp.value;
	 window.clipboardData.setData("Text",clipBoardContent);
	 alert("复制邀请链接成功,发给您的每位朋友吧"); 
	} 
</script>

</head>
<body>
<div class="container">	
		<div class="header">
			<%@include file="/common/blog-header.jsp" %>
		</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
		  <form  name="requestFrom">
			<div class="mainbh">
			    <div class="leftsidebar">
						<%@include file="/common/blog-content-left.jsp" %>
					</div>
				<div class="zhuti"> 
				<div class="yaoqitibt">Request Firend_邀请好友</div>
				<div class="yaoqijifeng">_邀请好友注册送积分</div>
				<div class="yaoqingzhu">注_小贴士：邀请好友成功注册送100喔元奖励，好友越多，送的越多<br />
_点击“复制链接”按钮复制邀请链接地址，粘贴到MSN、QQ或其他方式发送给你的朋友，打开此网址即可加入。</div>
				<div class="fuzhilianjie">_复制链接地址&nbsp;&nbsp;<input name="requesthttp" onclick="copyUrl()" type="text" style="width: 450px;" value="http://124.232.148.3:8080/member-register.action?member.id=${member.id}&&member.usercode=${member.usercode}"class="qq"/></div>
				<div class="yaoqingfz"><a href="javascript:copyUrl();">复制链接 CopyLink</a></div>
				</div>
		    </div>
		</form>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>