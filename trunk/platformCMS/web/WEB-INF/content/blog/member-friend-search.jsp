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

<script type="text/javascript">
	var friend_delete = {action:"member-friend!delete.action",msg:"确定要删除您的好友吗？"};
	var friend_query  = {action:"member-friend.action"};
	var friend_search = {action:"member-friend-search.action"};
	function _operate(op,id,tid) {
		if(op.msg && !confirm(op.msg)) {
			return;
		}
		var tableForm = document.getElementById('friendFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.tid.value = tid;
		tableForm.submit();
	}
	function queryGroupFriend(op,grouptype,state){
		var tableForm = document.getElementById('friendFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.groupType.value = grouptype;
		tableForm.state.value = state;
		tableForm.submit();
	}
	
	function queryFriendAll(op,state){
		var tableForm = document.getElementById('friendFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.state.value = state;
		tableForm.submit();
	}
	
	function searchFriendAll(op){
		var tableForm = document.getElementById('searchFriendFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.name.value;
		tableForm.submit();
	}
</script>
</head>
<body>
<%@ include file="/common/blog/add-group.jsp" %>
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
				<div class="gerenshoucangbt"><span class="hyguanglibt">Firend_好友_(共搜索出  <span style="color: #91BF29; font-size: 18px;">${page.totalCount}</span>  位好友)</span><span class="yhyjifeng"><a href="member-request.action">_邀请好友注册积分</a></span></div>
				  <form id="searchFriendFrom" method="post">
					<div class="grscxuangxiang"><input name="name" id="name" type="text" class="bitiankuang" />
				 	 <input name="biaoqian" type="button" onclick="searchFriendAll(friend_search)" class="bianqianan"/>&nbsp;&nbsp;_搜索姓名_Search Name 
				    </div>
				    <input name="name" type="hidden">
				  </form>
				 <div class="zdyhaoyou">
				 	<ul>
				 	  <%java.util.List friendGroupList = (java.util.List)request.getAttribute("friendGroupList");
				 	   for(int i = 0;i<friendGroupList.size();i++){
				 		  Object[] object = (Object[])friendGroupList.get(i);%>
						<li><a href="javascript:queryGroupFriend(friend_query,'<%=object[1]%>','1');">_<%=object[0]%></a></li>
					  <%} %>
						<li><a href="javascript:queryFriendAll(friend_query,'0');">_显示全部</a></li>
						<!-- <li><a href="javascript:showAddGroup();">_添加好友分类</a></li> -->
					</ul>
				 </div>
				<form id="friendFrom" method="post">
				<div class="grhaohougl">
				 <%
				    java.util.List list = (java.util.List)request.getAttribute("list");
				    for(int i = 0;i<list.size();i++){
				    	Object[] object = (Object[])list.get(i);
				    
				 %>
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
						<dd><a href="javascript:queryGroupFriend(friend_query,'<%=object[6]%>','1');"><%=object[7]%></a>|<a href="javascript:_operate(friend_delete,'<%=object[9]%>','<%=object[8]%>');">删除好友</a>|<a  id="upblog_<%=object[9]%>" onclick="a_blogIndex('<%=object[9]%>');" style="cursor: pointer;<%if(object[10].toString().equals("0")){out.print("display:none;");}%> ">放置个人首页</a><a  id="unupblog_<%=object[9]%>" onclick="a_unblogIndex('<%=object[9]%>');" style="cursor: pointer;<%if(object[10].toString().equals("1")){out.print("display:none;");}%>">取消放置个人首页</a></dd>
					</dl>
				<%} %>
				</div>
					<input name="id" type="hidden">
					<input name="tid" type="hidden">
					<input name="groupType" type="hidden">
					<input name="state" type="hidden">
				</form>
				<div class="qyyear">
					<div class="qyzhongjianyeshu">
						<ul>
								第${page.pageNo}页, 共${page.totalPages}页 
								<s:if test="page.hasPre">
									<a href="${ctx }/blog/member-friend.action?page.pageNo=${page.prePage}">上一页</a>
								</s:if>
								<ouun:pageNum pageNo="${page.pageNo}" url="${ctx}/blog/member-friend.action?page.pageNo=" totalPages="${page.totalPages}"/>
								<s:if test="page.hasNext">
									<a href="${ctx }/blog/member-friend.action?page.pageNo=${page.nextPage}">下一页</a>
								</s:if>
							</ul>
					</div>
					<div class="qyzhongjianyeshu">_共搜索出  ${page.totalCount}  个好友</div>
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