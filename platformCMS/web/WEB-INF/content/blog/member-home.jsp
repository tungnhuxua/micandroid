<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/blog/dynamics.js"></script>
</head>
<body>

<%@ include file="/common/blog/userlogin.jsp" %>
<div class="container">	
		<div class="header">
			<%@include file="/common/blog-header.jsp" %>
		</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
		<div class="mainbh" style="border:none">
			<div class="grzxzuobian">
				<div class="grzxbiaoti" style="color: #83C325;">个人中心首页</div>
				<ul class="grzxdaohang">
					<li><a href="member-info.action">个人档案</a></li>
					<li><a href="member-cover.action">封面</a></li>
				</ul>
				<ul class="grzxdaohang">
					<li><a href="article-log.action?cateId=54">日志</a></li>
					<li><a href="article-show.action?cateId=56">秀场</a></li>
				</ul>
				<ul class="grzxdaohang">
					<li><a href="member-friend.action">好友</a></li>
					<li><a href="member-fans.action">粉丝团</a></li>
					<li><a href="member-mycircle.action">圈子</a></li>
				</ul>
				<ul class="grzxdaohang">
					<li><a href="member-music.action">音乐</a></li>
					<li><a href="member-link.action">链接</a></li>
				</ul>
				<ul class="grzxdaohang">
					<li><a href="member-collection.action">收藏</a></li>
					<li><a href="member-footmark.action">足迹</a></li>
				</ul>
				<ul class="grzxdaohang">
					<li><a href="member-message.action">留言</a></li>
					<li><a href="${ctx }/blog/message!receive.action">信箱</a></li>
				</ul>
				<ul class="grzxdaohang">
					<li><a href="#">帮助</a></li>
				</ul>
			</div>
			<div class="grzxzhongjian">
				<div class="information">
					<div class="grxingxibiaoti">Informarion_信息</div>
					<div class="grxingxinav">
						<ul> 
							<li>_<a href="${ctx }/blog/message!receive.action"><span style="<s:if test="unRead != 0">color: #83C325</s:if>">短信(${unRead})</span></a></li>
							<li>_<a href="<s:if test="messageCount != 0">member-message.action</s:if><s:else>#</s:else>"><span style="<s:if test="messageCount != 0">color: #83C325</s:if>">留言(${messageCount})</span></a></li>
							<li style="width: 90px;">_<a href="<s:if test="messageAnswerCount != 0">member-message-notanswer.action</s:if><s:else>#</s:else>"><span style="<s:if test="messageAnswerCount != 0">color: #83C325</s:if>">留言回复(${messageAnswerCount})</span></a></li>
							<li>_<a href="<s:if test="requestFriendCount != 0">member-friend-requestlist.action</s:if><s:else>#</s:else>"><span style="<s:if test="requestFriendCount != 0">color: #83C325</s:if>">请求(${requestFriendCount})</span></a></li>
							<li>_<a href="#">评论(0)</a></li>
							<li>_<a href="#">系统(0)</a></li>
						</ul>
					</div>
				</div>
				<div class="tianjiao">
					<div class="grxingxibiaoti">Launched/Add_发起/添加</div>
					<div class="grxingxinav">
						<ul>
							<li>_<a href="member-music-add.action">音乐</a></li>
							<li>_<a href="article!input.action?cateId=54">日志</a></li>
							<li>_<a href="article!input.action?cateId=56">作品</a></li>
						</ul>
					</div>
				</div>
				<div class="dongtai">
					<div class="grxingxibiaoti">Dynamic_动态</div>
				</div>
				<div class="jinxiangshi">_仅显示最新50条_Show only the latest 50
				 <p>&nbsp;</p>
				   <ul>
				   
				    	<s:iterator value="memberDynamicsList">
				    	   <li>
				    	      <s:if test="messageType == 1">
				    	      <script>dynamicsMessageType1('${message}');</script>
				    	      </s:if>
				    	      <s:if test="messageType == 2">
				    	      <script>dynamicsMessageType2('${message}');</script>
				    	      </s:if>
				    	      <s:if test="messageType == 3">
				    	      <script>dynamicsMessageType3('${message}');</script>
				    	      </s:if>
				    	      <s:if test="messageType == 4">
				    	      <script>dynamicsMessageType4('${message}');</script>
				    	      </s:if>
				    	   </li>
				    	</s:iterator>
				     
				      <li >&nbsp;</li>
				      <s:if test="dynamicsFirendsPage.totalCount > 20"><li id="dynamicsMore"><a href="javascript:getDynamicsMore();">查看更多</a></li></s:if>
				  </ul>
				</div>
			</div>
			<div class="grzxyoubian">
				<div class="huoqidm">
					<div class="dmbiaoti"><a href="member-request.action">获取邀请代码</a>_<span>Access to the invitation code</span></div>
					<div class="jiangli"><a href="member-request.action"><b style="color: #83C325">_成功邀请好友加入可获得200喔元</b></a></div>
				</div>
				<div class="zuijingfk">
					<div class="dmbiaoti">_最近访客<span>_Recent visitors</span></div>
					<div class="fktouxiang">
					   
						<ul>
						<s:iterator value="memberLateGuestList">
							<li>
								<a href="${ctx }/blog/blog-home.action?tomember.id=${tomember.id}" target="_blank" title="${tomember.name }">
									<s:if test="tomember.info.headPortraitUri != null && member.info.headPortraitUri != ''">
									   <img src="${ctx }${tomember.info.headPortraitUri }" width="40" height="40"/>
									</s:if><s:else>
									<img src="${ctx }/images/baisexiaonian.jpg" width="40" height="40"/>
									</s:else>
								</a>
							</li>
					    </s:iterator>
						</ul>
					</div>
				</div>
				<div class="zuijingfk">
					<div class="dmbiaoti">_在线好友<span>_Online Friends</span></div>
					<div class="fktouxiang">
						<ul>
							<s:iterator value="onlineFirends" id="obj">
								<li>
									<a href="${ctx }/blog/blog-home.action?tomember.id=<s:property value="#obj[0]"/>" target="_blank" title="<s:property value="#obj[1]"/>">
									<s:if test="#obj[3] != null && #obj[3] != ''">
									   <img src="${ctx }<s:property value="#obj[3]"/>" width="40" height="40"/>
									</s:if><s:else>
									<img src="${ctx }/images/baisexiaonian.jpg" width="40" height="40"/>
									</s:else>
								</a>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="grsousuo">
					<form action="" method="post">
					<input name="grsousuo" type="text" class="grss"/>
					<input name="biaoqian" type="button" class="bianqianan"/>
					</form>
					<div class="dmbiaoti" style="padding-top:10px">_搜索设计圈会员<span>_Search Members</span></div>
					
				</div>
			</div>
			</div>
			<div class="ruhebianji">_如何编缉管理自己设计圈blog的<a href="#">小贴士</a>，或者给我们<a href="#">提建议！</a><br />
			<span>How do I edit a blog to manage their own small circle Designed with disabilities,or give us suggestions!</span></div>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>