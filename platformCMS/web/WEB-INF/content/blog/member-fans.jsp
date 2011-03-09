<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link type="text/css" href="../css/ui.all.css" rel="stylesheet" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/blog/friend.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/ui.core.js"></script>
<script type="text/javascript" src="../js/ui.dialog.js"></script>
<script type="text/javascript" src="../js/jquery.bgiframe.min.js"></script>

</head>
<body>
<%@ include file="/common/blog/addfriend.jsp" %>
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
				<div class="gerenshoucangbt"><span class="hyguanglibt">FANS_粉丝团_(共 <span style="color: #91BF29; font-size: 18px;">${page.totalCount} </span>位粉丝)</span><span class="yhyjifeng"><a href="member-request.action">_邀请好友注册积分</a></span></div>
				
				   <div class="zdyhaoyou">注_当对方欣赏你时即成为你的粉丝，如果你同样欣赏他，那你们会自动成为好友！</div>
				   <form id="fansForm" method="post">
					  <div class="pengyou">
						   <s:iterator value="list"  id="obj">
							<dl>
								<dt>
									<a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${obj[6]}">
										 <s:if test="#obj[5]!=null && #obj[5]!=''">
											<img src="${ctx}${obj[5]}" width="75px" height="75px" alt="${obj[0]}"/>
										 </s:if>
										<s:else>
											<img src="../images/zhchepic.gif" width="75px" height="75px" alt="${obj[0]}"/>
										</s:else>
									</a>
								</dt>
								<dd>${obj[0]}</dd>
							</dl>
						   </s:iterator>
					</div>
						
					</form>
					<div class="qyyear">
						<div class="qyzhongjianyeshu">
							<ul>
								第${page.pageNo}页, 共${page.totalPages}页 
								<s:if test="page.hasPre">
									<a href="${ctx }/blog/member-fans.action?page.pageNo=${page.prePage}">上一页</a>
								</s:if>
								<ouun:pageNum pageNo="${page.pageNo}" url="${ctx}/blog/member-fans.action?page.pageNo=" totalPages="${page.totalPages}"/>
								<s:if test="page.hasNext">
									<a href="${ctx }/blog/member-fans.action?page.pageNo=${page.nextPage}">下一页</a>
								</s:if>
							</ul>
						</div>
						<div class="qyzhongjianyeshu">_共${page.totalCount}个好友</div>
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