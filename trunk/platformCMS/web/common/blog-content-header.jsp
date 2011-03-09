<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="blacface">
			<s:if test="member.info.headPortraitUri != null && member.info.headPortraitUri != ''">
			   <img src="${ctx }${member.info.headPortraitUri }" width="110" height="110"/>
			</s:if><s:else>
			<img src="${ctx }/images/xiaonian.jpg" />
			</s:else>
</div>
			<div class="geirenzhongxin">
				<div class="danhang">
					<div class="geren">${member.name}'s blog</div>
					<div class="dz"><s:if test="member.info.domain != null && member.info.domain !=''">http://www.ooowo.com/${member.info.domain}</s:if><s:else>http://www.ooowo.com/blog/blog-home.action?tomember.id=${member.id}</s:else></div>
					<div class="blognav">
						<ul>
							<li><a href="${ctx }/blog/blog-home.action?tomember.id=${member.id}" <s:if test="action == '/blog/member-home'">style="color: #83C325;"</s:if>>首页</a>|</li>
							<li><a href="${ctx }/blog/blog-log.action?tomember.id=${member.id}" <s:if test="action == '/blog/blog-log'">style="color: #83C325;"</s:if>>日志</a>|</li>
							<li><a href="${ctx }/blog/blog-show.action?tomember.id=${member.id}" <s:if test="action == '/blog/blog-show'">style="color: #83C325;"</s:if>>秀场</a>|</li>
							<li><a href="${ctx }/blog/blog-friend.action?tomember.id=${member.id}" <s:if test="action == '/blog/blog-friend'">style="color: #83C325;"</s:if>>朋友</a>|</li>
							<li><a href="${ctx }/blog/blog-circle.action?tomember.id=${member.id}" <s:if test="action == '/blog/blog-circle'">style="color: #83C325;"</s:if>>群英会</a>|</li>
							<li><a href="${ctx }/blog/blog-collection.action?tomember.id=${member.id}" <s:if test="action == '/blog/blog-collection'">style="color: #83C325;"</s:if>>收藏</a>|</li>
							<li><a href="${ctx }/blog/article-tag.action?tomember.id=${member.id}" <s:if test="action == '/blog/article-tag'">style="color: #83C325;"</s:if>>标签</a>|</li>
							<li><a href="${ctx}/blog/archives.action?tomember.id=${member.id}" <s:if test="action == '/blog/archives'">style="color: #83C325;"</s:if>>档案</a>|</li>
							<li><a href="${ctx}/blog/blog-message.action?tomember.id=${member.id}" <s:if test="action == '/blog/blog-message'">style="color: #83C325;"</s:if>>留言</a>|</li>
							<li><a href="${ctx }/blog/member-home.action">个人中心</a></li>
						</ul>
					</div>
				</div>
				<div class="wangzhi" align="center">${member.info.notice}</div>
</div>