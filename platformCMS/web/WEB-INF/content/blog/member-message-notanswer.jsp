<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/blog/blog_message.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.bgiframe.min.js"></script>


</head>
<body>
<div class="container">	
		<div class="header">
			<%@include file="/common/blog-header.jsp" %>
		</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
			<div class="mainbh">
			    <div class="leftsidebar">
						<%@include file="/common/blog-content-left.jsp" %>
					</div>
				<div class="zhuti">
			<div class="gerenshoucangbt">Message_留言回复_(共  <span style="color: #91BF29; font-size: 18px;">${answerPage.totalCount}</span>  条留言回复)</div>
				<form id="messageForm" method="post">
					<div class="gerenzhoucanglb">
					
					<s:iterator value="answerPage.result" >
	                    <dl>
							<dt>  
							<a target="_blank" href="${ctx}/blog/blog-message.action?tomember.id=${memberTemp.id}">
								<s:if test="memberTemp.info.headPortraitUri != null && memberTemp.info.headPortraitUri != ''">
								   <img src="${ctx }/${memberTemp.info.headPortraitUri }" width="55" height="55"/>
								</s:if><s:else>
								<img src="${ctx }/images/baisexiaonian.jpg" />
								</s:else>
							</a>
							</dt>
							<dd>${memberTemp.name},<s:date name="createDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</dd>
							<dd>${comment }</dd>
						</dl>
					</s:iterator>
					</div>
					<input type="hidden" id="id" name="id"/>
				</form>
				<div class="qyyear">
					<div class="qyzhongjianyeshu">
						<ul>
								第${answerPage.pageNo}页, 共${answerPage.totalPages}页 
								<s:if test="answerPage.hasPre">
									<a href="${ctx }/blog/member-message-notanswer.action?page.pageNo=${page.prePage}">上一页</a>
								</s:if>
								<ouun:pageNum pageNo="${answerPage.pageNo}" url="${ctx}/blog/member-message-notanswer.action?answerPage.pageNo=" totalPages="${answerPage.totalPages}"/>
								<s:if test="answerPage.hasNext">
									<a href="${ctx }/blog/member-message-notanswer.action?answerPage.pageNo=${answerPage.nextPage}">下一页</a>
								</s:if>
							</ul>
					</div>
					<div class="qyzhongjianyeshu">_共 ${answerPage.totalCount} 个留言回复</div>
				  </div>
				</div>
				
		</div>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>