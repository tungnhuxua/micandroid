<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 <dl>
		<dt> 
			<a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${member.id}">
				<s:if test="member.info.headPortraitUri != null && member.info.headPortraitUri != ''">
					<img src="${ctx }${member.info.headPortraitUri }" width="55" height="55"/>
				</s:if><s:else>
				<img src="${ctx }/images/baisexiaonian.jpg" />
				</s:else>
			</a>
		</dt>
		<dd><a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${member.id}">${member.name }</a>,<s:date name="entity.createDate" format="yyyy-MM-dd HH:mm"/></dd>
		<dd>${entity.comment}</dd>
</dl>
