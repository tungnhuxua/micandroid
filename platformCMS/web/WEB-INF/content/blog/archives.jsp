<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>${tomember.name}'s blog</title>
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<link type="text/css" href="../css/ui.all.css" rel="stylesheet" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/blog/friend.js"></script>
		<script type="text/javascript" src="../js/blog/front.js"></script>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/ui.core.js"></script>
		<script type="text/javascript" src="../js/ui.dialog.js"></script>
		<script type="text/javascript" src="../js/jquery.bgiframe.min.js"></script>

</head>
<body>
<%@ include file="/common/blog/userlogin.jsp" %>
<%@ include file="/common/blog/requestAddFriend.jsp" %>
<div class="container">
	<div class="header">
		<%@include file="/common/blog-header.jsp" %>
	</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header-to.jsp" %>
		</div>
		<div class="main">
		
			<div class="bqmainbh">
				<div class="danganzhuti">
				<form name="archivesForm">
				<div class="danganweizhi">
					<span class"bgwz">
					<ul>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">首页</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">设计圈博客</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">${tomember.name}</a></li>
						<li>_<a href="#">个人档案</a></li>
					</ul>
					</span>
					<span class="haoyou" ><s:if test='member.id!= "" && member.id!= null'><a href="javascript:showFrontAddFriend();">_加为好友</a>&nbsp;<a href="${ctx }/blog/message!input.action?mid=${tomember.id}">_发信息</a>&nbsp;<a href="javascript:enjoyTo('${member.id}','${tomember.id}');">_欣赏</a></s:if></span>
				</div>
				
				<div class="dangannr">
					<p>
						<span style="color:#221D1C">${tomember.name}</span><br />
						<s:if test="tomember.info.gender == '1'">男</s:if><s:else>女</s:else><br />
						${tomember.info.liveProvince}_${tomember.info.liveCity}<br />
						<s:if test="messageError == 1">
							<s:if test="tomember.info.birthdayHiden =='2'">出生_${tomember.info.birthday}</s:if>
						</s:if>
					</p>
					
					<p>
						家乡_${tomember.info.hometown}_${tomember.info.homeCity}<br />
						 <s:if test="messageError == 1">
							<s:if test="tomember.info.maritalHiden =='2'">婚姻状况_<s:if test="tomember.info.marital =='1'">已婚</s:if><s:else>未婚</s:else><br /></s:if>
							<s:if test="tomember.info.contactsHiden =='2'">联络方式_${tomember.info.contacts}<br /></s:if>
							<s:if test="tomember.info.qqHiden =='2'">qq:${tomember.info.qq}<br /></s:if>
							<s:if test="tomember.info.msnHiden =='2'">MSN：${tomember.info.msn}<br /></s:if>
							<s:if test="tomember.info.addressHiden =='2'">通讯地址_${tomember.info.address}<br /></s:if>
							<s:if test="tomember.info.workInfoHiden =='2'">工作信息_${tomember.info.workInfo}<br /></s:if>
						 </s:if>
						邮编_${tomember.info.postalcode}
					</p>
					<p>个人网站_<a href="${tomember.info.website}" target="_blank">${tomember.info.website}</a></p>
					<s:if test="messageError == 1">
						<p><s:if test="tomember.info.educationHiden =='2'">教育背景_${tomember.info.education}<br /></s:if></p>
					</s:if>
					职业_<br>
					<script language="javascript">
						var setString = '${tomember.info.workName}';
						var value = setString.split(";"); 
						for(i = 0;i<value.length;i++){
							document.write(value[i]+"<br>");
						}
					</script>
					
					<p></p>
					<div style="float: left;width: 60px;height: auto;">我的介绍_</div>
					<div style="float: right;width: 750px;height: auto;"><pre style="font-family:微软雅黑,宋体,Verdana,Arial,Tahoma;">${tomember.info.introduction}</pre></div>
				</div>
				 <input name="member.id" type="hidden" value="${member.id}">
		   		 <input name="tomember.id" type="hidden" value="${tomember.id}">
				</form>
				<%@include file="/common/blog/blog-search.jsp" %>
				</div>
			
		</div>
		<div class="footer">
			<%@include file="/common/blog-content-footer-to.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>
