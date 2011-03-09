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
	var circle_delete = {action:"member-circle!delCircle.action",msg:"确定要删除该群吗？"};
	function delCircle(op,id) {
		if(op.msg && !confirm(op.msg)) {
			return;
		}
		var tableForm = document.getElementById('circleFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
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
		  <form  id="circleFrom">
			<div class="mainbh">
			    <div class="leftsidebar"><%@include file="/common/blog-content-left.jsp" %></div>
				<div class="zhuti">
					<div class="gerenshoucangbt"><span class="hyguanglibt">Firend_圈子_(共${page.totalCount}个圈子)</span><span class="yhyjifeng"><a href="member-addcircle.action">_申请圈子</a></span></div>
					<div class="dangannav">
						<ul>
							<li>_<a href="member-mycircle.action" >我加入的圈子</a></li>
							<li>_<a href="#" style="color: rgb(131, 195, 37);">我管理的圈子</a></li>
						</ul>
					</div>

					<div class="grhaohougl">
						<%java.util.List list = (java.util.List)request.getAttribute("list"); 
						  for(int i =0;i<list.size();i++){
							  Object[] object = (Object[])list.get(i);%>
						<dl>
							<dt><a href="member-modicircle.action?id=<%=object[0]%>">
							  <%if(object[8]!= null && object[8]!=""){%>
								<img  src="${ctx }<%=object[8]%>" width="80" height="80"/>
							  <%}else{%>
							    <img src="../images/zhchepic.gif" width="80px" height="80px"/>
							  <% }%>
							</a>
							</dt>
							<dd style="font-size:14px"><%=object[1]%></dd>
							<dd><%=object[5]%>_<%=object[6] %>_<%=object[4]%></dd>
							<dd><a href="member-circle-manager.action?id=<%=object[0]%>">圈员管理</a>|<a href="member-circle-unmanager.action?id=<%=object[0]%>">等待审核</a></dd>
							<dd><a href="#"><%=object[9]%></a>|<a href="javascript:delCircle(circle_delete,'<%=object[0]%>');">解散群</a></dd>
						</dl>
						<%} %>
					</div>
					<div class="qyyear">
						<div class="qyzhongjianyeshu">
							<ul>
								第${page.pageNo}页, 共${page.totalPages}页 
								<s:if test="page.hasPre">
									<a href="${ctx }/blog/member-circle.action?page.pageNo=${page.prePage}">上一页</a>
								</s:if>
								<ouun:pageNum pageNo="${page.pageNo}" url="${ctx}/blog/member-circle.action?page.pageNo=" totalPages="${page.totalPages}"/>
								<s:if test="page.hasNext">
									<a href="${ctx }/blog/member-circle.action?page.pageNo=${page.nextPage}">下一页</a>
								</s:if>
							</ul>
						</div>
						<div class="qyzhongjianyeshu">_共${page.totalCount}个圈子</div>
					  </div>
				</div>
		    </div>
		    	<input name="id" id="id" type="hidden"/>
		</form>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>