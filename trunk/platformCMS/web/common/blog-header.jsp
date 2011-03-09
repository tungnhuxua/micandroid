<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
    function isBlogLogin(){
         alert("您还没有登录，请您登录在继续操作！");
    }
</script>
	    <script type="text/javascript" src="${ctx}/js/ui.core.js"></script>
		<script type="text/javascript" src="${ctx}/js/ui.dialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/userlogin.js"></script>
		<link type="text/css" href="${ctx}/css/ui.all.css" rel="stylesheet" />
		
<div class="hdbh">
		<div class="facepic">
		<s:if test="member.info.headPortraitUri != null && member.info.headPortraitUri != ''">
		   <img src="${ctx }${member.info.headPortraitUri }" width="22" height="22"/>
		</s:if><s:else>
		<img src="${ctx }/images/baisexiaonian.jpg" />
		</s:else>
		</div>
		<div class="mingzi"><s:if test="member.name != null && member.name != ''">${member.name}</s:if><s:else><a href="javascript:showBlogLogin();"><span id="ylogin">登录</span></a></s:else></div>
		<div class="topnava">
		<s:if test='userName != "roleAnonymous" && userName != null'>
			<ul>
			
				<li><a href="<s:if test="member.name != null && member.name != ''">${ctx }/blog/member-home.action?member.id=${member.id}</s:if><s:else>javascript:isBlogLogin();</s:else>">_个人中心</a></li>
				<li><a href="<s:if test="member.name != null && member.name != ''">${ctx }/blog/blog-home.action?tomember.id=${member.id}</s:if><s:else>javascript:isBlogLogin();</s:else>">_我的首页</a></li>
				<li><a <s:if test="unRead>0">style="background:#83C325;"</s:if> href="<s:if test="member.name != null && member.name != ''" >${ctx }/blog/message!receive.action</s:if><s:else>javascript:isBlogLogin();</s:else>">_信箱(${unRead})</a></li>
				<li id="site_home"><a href="<s:if test="member.name != null && member.name != ''">${ctx }/blog/article!input.action?cateId=54</s:if><s:else>javascript:isBlogLogin();</s:else>">_发新日志</a></li>
				<li><a href="${ctx}/j_spring_security_logout" style="margin-left:0px;" >退出</a></li>
				
			</ul>
		</s:if><s:else>
			&nbsp;
		</s:else>
		</div>
		<div class="feixian"></div>
		<div class="xiangyou">
			<div class="topnavb">
				<ul>
					<li><a href="${ctx }/home">_首页</a></li>
					<li><a href="${ctx }/news">_资讯</a></li>
					<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=all">_秀场</a></li>
					<li><a href="#">_社区</a></li>
					<li><a href="#">_商店</a></li>
					<li><a href="#">_书店</a></li>
				</ul>
			</div>
			<div class="logo"><img src="${ctx }/images/logo.jpg" /></div>
	  </div>
</div>