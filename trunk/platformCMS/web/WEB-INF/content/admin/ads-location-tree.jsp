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
	<h1>广告位</h1>
	<hr>
	<ul id="tree" class="filetree">
		<li><a target="rightFrame" href="${action }"><span class="folder">所有广告位</span></a>
			<ul>
				<ouun:adslocation_tree value="${tree}" action="${action}" param="${actionParam}"/>
			</ul>
		</li>
		<li><span class="file">[<a href="ads-location!tree.action?action=${action }&actionParam=${actionParam }">刷新广告位</a>]</span></li>
	</ul>
</div>
</body>
</html>