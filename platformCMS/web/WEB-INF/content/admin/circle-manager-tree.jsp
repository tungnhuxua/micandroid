<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="css/admin.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/jquery.treeview.css" />
<link rel="stylesheet" href="../css/screen.css" />

<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.cookie.js" type="text/javascript"></script>
<script src="../js/jquery.treeview.js" type="text/javascript"></script>
<script type="text/javascript">
		$(function() {
			$("#tree").treeview({
				collapsed: true,
				animated: "medium",
				control:"#sidetreecontrol",
				persist: "location",
				unique: true
			});
		})
		
	</script>
</head>
<body class="lbody">
<div id="main">
	<h1>圈子管理</h1>
	<hr>
<ul id="tree" class="filetree">
		<li><a target="rightFrame" href="circle-manager.action"><span class="folder">圈子类型</span></a>
			<ul>
			   <%java.util.List list = (java.util.List)request.getAttribute("circleType");
				  for(int i = 0;i<list.size();i++){
					  Object[] object = (Object[])list.get(i);%>
				<li><span class="file"><a target="rightFrame" href="circle-manager.action?circleTypeId=<%=object[0]%>&&state=1"><%=object[1]%></a></span></li>
				<%} %>
			</ul>
			<li><span class="folder">[<a target="rightFrame" href="circle-manager!notAuditing.action">圈子审核</a>]</span></li>
		</li>
	</ul>
</div>
</body>
</html>