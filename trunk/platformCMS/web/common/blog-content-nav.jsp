<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul>
						<li><a href="${ctx }/blog/memberlogin-info.action" <s:if test="action == '/blog/memberlogin-info'">style="color: #83C325;"</s:if>>登陆信息</a></li>
						<li>_<a href="${ctx }/blog/member-info.action" <s:if test="action == '/blog/member-info'">style="color: #83C325;"</s:if>>个人档案</a></li>
						<li>_<a href="${ctx }/blog/member-picture.action" <s:if test="action == '/blog/member-picture' || action == 'memberpicture'">style="color: #83C325;"</s:if>>编辑头像</a></li>
						<li>_<a href="${ctx }/blog/member-notice.action" <s:if test="action == '/blog/member-notice'">style="color: #83C325;"</s:if>>个人公告</a></li>
						<li>_<a href="${ctx }/blog/member-password.action" <s:if test="action == null">style="color: #83C325;"</s:if>>修改密码</a></li>
						<li>_<a href="${ctx }/blog/member-work.action" <s:if test="action == '/blog/member-work'">style="color: #83C325;"</s:if>>职业管理</a></li>
						<li>_<a href="${ctx }/blog/member-mark.action" <s:if test="action == '/blog/member-mark'">style="color: #83C325;"</s:if>>积分</a></li>
						<li>_<a href="${ctx }/blog/member-domain.action" <s:if test="action == '/blog/member-domain'">style="color: #83C325;"</s:if>>个性域名</a></li>
					</ul>