<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="sybiaoti"><a href="member-home.action">个人中心首页</a></div>
					<ul>
						<li><a href="${ctx }/blog/member-info.action" <s:if test="action == '/blog/member-info' || action == '/blog/member-mark' || action == '/blog/member-notice' || action =='/blog/memberlogin-info' || action == '/blog/member-picture' || action == 'memberpicture'">style="color: #83C325;"</s:if>>个人档案</a></li>
						<li><a href="${ctx }/blog/member-cover.action">封面</a></li>
					</ul>
					<ul>
						<li><a href="${ctx }/blog/article-log.action?cateId=54" <s:if test="action == '/blog/article-log'">style="color: #83C325;"</s:if>>日志</a></li>
						<li><a href="${ctx }/blog/article-show.action?cateId=56">秀场</a></li>
					</ul>
					<ul>
						<li><a href="${ctx }/blog/member-friend.action" <s:if test="action == '/blog/member-friend'">style="color: #83C325;"</s:if>>好友</a></li>
						<li><a href="${ctx }/blog/member-fans.action" <s:if test="action == '/blog/member-fans'">style="color: #83C325;"</s:if>>粉丝团</a></li>
						<li><a href="${ctx }/blog/member-mycircle.action" <s:if test="action == '/blog/member-mycircle' || action == '/blog/member-circle' || action == '/blog/member-circle-unmanager' || action == '/blog/member-circle-manager'">style="color: #83C325;"</s:if>>圈子</a></li>
					</ul>
					<ul>
						<li><a href="${ctx }/blog/member-music.action" <s:if test="action == '/blog/member-music' || action == '/blog/member-music-add'">style="color: #83C325;"</s:if>>音乐</a></li>
						<li><a href="${ctx }/blog/member-link.action" <s:if test="action == '/blog/member-link'">style="color: #83C325;"</s:if>>链接</a></li>
					</ul>
					<ul>
						<li><a href="${ctx }/blog/member-collection.action" <s:if test="action == '/blog/member-collection'">style="color: #83C325;"</s:if>>收藏</a></li>
						<li><a href="${ctx }/blog/member-footmark.action" <s:if test="action == '/blog/member-footmark'">style="color: #83C325;"</s:if>>足迹</a></li>
					</ul>
					<ul>
						<li><a href="${ctx }/blog/member-message.action" <s:if test="action == '/blog/member-message'">style="color: #83C325;"</s:if>>留言</a></li>
						<li><a href="${ctx }/blog/message!receive.action" <s:if test="action == '/blog/member-message!receive'">style="color: #83C325;"</s:if>>信箱</a></li>
					</ul>
					<ul>
						<li><a href="#">帮助</a></li>
					</ul>