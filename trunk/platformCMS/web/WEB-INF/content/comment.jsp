<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="articlecontent">
<div class="articleImage"><img src="images/DetailsImage.gif" alt="" /></div>
<div>
<dl>
	<dt>${comment.member.name } , <s:date name="comment.createDate" format="yyyy年MM月dd日" /></dt>
	<dd>${comment.content}</dd>
</dl>
</div>
</div>