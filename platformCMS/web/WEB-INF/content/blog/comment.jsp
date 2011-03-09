<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<dl>
	<dt>
		<a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${comment.member.id}">
			<s:if test="comment.member.info.headPortraitUri != null && comment.member.info.headPortraitUri != ''">
					<img src="${ctx }${comment.member.info.headPortraitUri }" width="55" height="55"/>
				</s:if><s:else>
				<img src="${ctx }/images/baisexiaonian.jpg" />
			</s:else>
		</a>
	</dt>
	<dd><a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${comment.member.id}">${comment.member.name }</a> , <s:date name="comment.createDate" format="yyyy年MM月dd日" />,<s:date name="createDate" format="HH.mm.ss" /></dd>
	<dd>${comment.content}</dd>
</dl>
