<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="advimg">
<ul>
    <s:iterator value="randomFourArticle">
		<li><a class="coverBg120" href="${ctx}/searcharticlecontent.action?article.id=${id}">READ MORE<p>(查看全部)</p></a>
		     <s:if test="imageUri!=null && imageUri!=''">
				<img src="${ctx}${imageUri}" alt="${title}" />
			</s:if><s:else><img src="images/1.gif" alt="" /></s:else>
		</li>
	</s:iterator>

</ul>
</div>