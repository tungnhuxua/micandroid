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
<%@ include file="/common/blog/listAddFriend.jsp" %>
<div class="container">	
		<div class="header">
			<%@include file="/common/blog-header.jsp" %>
		</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
		  <form  name="requestFrom">
			<div class="mainbh">
			    <div class="leftsidebar">
						<%@include file="/common/blog-content-left.jsp" %>
					</div>
				<div class="zhuti">
				<div class="gerenshoucangbt"><span class="hyguanglibt">Request_朋友申请_(共${page.totalCount}位朋友正在申请您加为好友)</span></div>
				
				   <div class="zdyhaoyou">注_当对方申请加你为好友，那么你只需确认接受你们就会成为好友！</div>
				   <form id="fansForm" method="post">
					   <div class="grhaohougl">
							 <%
							    java.util.List list = (java.util.List)request.getAttribute("list");
							    for(int i = 0;i<list.size();i++){
							    	Object[] object = (Object[])list.get(i);%>
								<dl>
									<dt>
										<a href="">
										<%if(object[5]!=null && object[5]!=""){ %>
											<img src="${ctx}<%=object[5] %>" width="80px" height="80px"/>
										<%}else{%>
										 	
										 	<img src="../images/zhchepic.gif" width="80px" height="80px"/>
										<%} %>
										</a>
									</dt>
									<dd style="font-size:14px"><%=object[0]%></dd>
									<dd><%=object[3] %>_<%=object[4] %></dd>
									<dd>共发表_0篇<a target="_blank" href="${ctx}/blog/blog-log.action?tomember.id=<%=object[8]%>">日志</a>|查看人物名称的 <a target="_blank" href="${ctx}/blog/archives.action?tomember.id=<%=object[8]%>">个人档案</a></dd>
									<dd><a href="javascript:showlistAddFriend('<%=object[9]%>','<%=object[8]%>','<%=object[0] %>','<%if(object[5]!=null && object[5]!=""){%>${ctx}<%=object[5]%><% }else{%>../images/qqxiaonian.jpg<%}%>');">加为好友</a></dd>
								</dl>
							<%} %>
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