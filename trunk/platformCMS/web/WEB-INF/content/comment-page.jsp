<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:iterator value="commentPage.result">
<div class="articlecontent">
<div class="articleImage"><img src="images/DetailsImage.gif"
	alt="" /></div>
<div>
<dl>
	<dt>${member.name } , <s:date name="createDate" format="yyyy年MM月dd日" />，<s:date name="createDate" format="HH,mm,ss" /> <s:date name="comment.createDate" format="HH.mm.ss" /><s:if test="authority == 'A_VIEW'"><span onclick="editComment('${id}');" style="cursor: pointer;">编辑</span> <span onclick="delComment('${id }',this);" style="cursor: pointer;">删除</span></s:if></dt>
	<dd>${content}</dd>
</dl>
</div>
</div>
</s:iterator>