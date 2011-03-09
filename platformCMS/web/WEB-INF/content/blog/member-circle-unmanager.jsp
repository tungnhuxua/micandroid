<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var circle_member_add = {action:"member-circle!addCircleMember.action",msg:"确定把会员加入该圈子吗？"};
	var circle_member_refuse = {action:"member-circle!refuseCircleMember.action",msg:"确定拒绝该会员的请求吗？"};
	function addCircleMember(op,id,mcuid) {
		if(op.msg && !confirm(op.msg)) {
			return;
		}
		var tableForm = document.getElementById('circleMemberFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.mcuid.value = mcuid;
		tableForm.submit();
	}

	function refuseCircleMember(op,id,mcuid) {
		if(op.msg && !confirm(op.msg)) {
			return;
		}
		var tableForm = document.getElementById('circleMemberFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.mcuid.value = mcuid;
		tableForm.submit();
	}
</script>
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
		  <form  name="circleMemberFrom">
			<div class="mainbh">
			    <div class="leftsidebar"><%@include file="/common/blog-content-left.jsp" %></div>
				<div class="zhuti">
					<div class="gerenshoucangbt"><span class="hyguanglibt">Friend_圈子   圈子共<span style="color: #91BF29; font-size: 18px;"> ${page.totalCount} </span>位会员正在申请加入)</span></div>
				
				   <div class="zdyhaoyou">注_当前是会员申请加入圈子，您可以允许他加入也可以拒绝！ </div>
					   <div class="grhaohougl">
							 <%
							    java.util.List list = (java.util.List)request.getAttribute("list");
							    for(int i = 0;i<list.size();i++){
							    	Object[] object = (Object[])list.get(i);%>
								<dl>
									<dt>
										<a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=<%=object[11]%>">
										<%if(object[12]!=null && object[12]!=""){ %>
											<img src="${ctx}<%=object[12] %>" width="80px" height="80px"/>
										<%}else{%>
										 	
										 	<img src="../images/zhchepic.gif" width="80px" height="80px"/>
										<%} %>
										</a>
									</dt>
									<dd style="font-size:14px"><%=object[1]%></dd>
									<dd><%=object[3] %>_<%=object[4] %>_个性签名</dd>
									<dd>共发表_0篇<a "target="_blank"  href="${ctx}/blog/blog-log.action?tomember.id=<%=object[11]%>">日志</a>|查看人物名称的 <a target="_blank" href="${ctx}/blog/archives.action?tomember.id=<%=object[11]%>">个人档案</a></dd>
									<dd><a href="javascript:addCircleMember(circle_member_add,'<%=object[7]%>','<%=object[9]%>')">_允许加入</a>|<a href="javascript:refuseCircleMember(circle_member_refuse,'<%=object[7]%>','<%=object[9]%>')">_拒绝</a></dd>
								</dl>
							<%} %>
						</div>
					<div class="qyyear">
						<div class="qyzhongjianyeshu">
							<ul>
								第${page.pageNo}页, 共${page.totalPages}页 
								<s:if test="page.hasPre">
									<a href="${ctx }/blog/member-circle-unmanager.action?id=${id}&&page.pageNo=${page.prePage}">上一页</a>
								</s:if>
								<ouun:pageNum pageNo="${page.pageNo}" url="${ctx}/blog/member-circle-unmanager.action?id=${id}&&page.pageNo=" totalPages="${page.totalPages}"/>
								<s:if test="page.hasNext">
									<a href="${ctx }/blog/member-circle-unmanager.action?id=${id}&&page.pageNo=${page.nextPage}">下一页</a>
								</s:if>
							</ul>
						</div>
						<div class="qyzhongjianyeshu">_共${page.totalCount}个好友申请加入</div>
					  </div>
				</div>
		    </div>
		    <input name="id" type="hidden">
		    <input name="mcuid" type="hidden">
		</form>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>