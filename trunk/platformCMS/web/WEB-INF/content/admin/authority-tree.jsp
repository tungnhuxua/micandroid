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
	<h1>会员管理</h1>
	<hr>
	<ul id="tree" class="filetree">
		<security:authorize ifAnyGranted="A_MANAGE_MEMBER">
		<li><a target="rightFrame" href="member.action"><span class="file">会员</span></a>
			
		</li>
		</security:authorize>
		<security:authorize ifAnyGranted="A_MANAGE_ROLE">
		<li><span class="file"><a target="rightFrame" href="role.action">角色</a></span></li>
		</security:authorize>
	</ul>
</div>
</body>
</html>