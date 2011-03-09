<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="gerenzhoucanglb">
	<div style="display:none;" id="commenttop"></div>
	<s:iterator value="commentPage.result">
	<dl>
		<dt></dt>
		<dd>${member.name } , <s:date name="createDate" format="yyyy年MM月dd日" />,<s:date name="createDate" format="HH.mm.ss" /> <security:authorize ifAnyGranted="A_VIEW_ZX"><!--  <span onclick="editComment('${id}');" style="cursor: pointer;">编辑</span>--> <span onclick="delComment('${id }',this);" style="cursor: pointer;">删除</span></security:authorize></dd>
		<dd>${content}</dd>
	</dl>
	</s:iterator>
</div>