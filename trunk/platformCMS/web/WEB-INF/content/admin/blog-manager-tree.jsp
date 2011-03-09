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
<h1>日志栏目</h1>
<hr>
<ul id="tree" class="filetree">

	<li><a href="#"><span
		class="folder">根栏目</span></a>
	<ul>
		<security:authorize ifAnyGranted="A_VIEW_ZX">
		<li><a target="rightFrame"
			href="article!bloglist.action?categoryTmp.id=54&iscms=0&isblog=1"> <span class="folder">资讯(未审核)</span>
		</a>
		<ul>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=59&iscms=0&isblog=1"><span class="file">抢先看</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=60&iscms=0&isblog=1"><span class="file">新闻动态</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=61&iscms=0&isblog=1"><span class="file">平面视觉</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=62&iscms=0&isblog=1"><span class="file">广告创意</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=63&iscms=0&isblog=1"><span class="file">清晰卡通</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=64&iscms=0&isblog=1"><span class="file">数码影像</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=65&iscms=0&isblog=1"><span class="file">产品造型</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=66&iscms=0&isblog=1"><span class="file">建筑空间</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=67&iscms=0&isblog=1"><span class="file">艺术生活</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=68&iscms=0&isblog=1"><span class="file">交互设计</span></a></li>
		</ul>
		</li>
		
		<li><a target="rightFrame"
			href="article!bloglist.action?categoryTmp.id=54&iscms=1&isblog=1"> <span class="folder">资讯(已审核)</span>
		</a>
		<ul>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=59&iscms=1&isblog=1"><span class="file">抢先看</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=60&iscms=1&isblog=1"><span class="file">新闻动态</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=61&iscms=1&isblog=1"><span class="file">平面视觉</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=62&iscms=1&isblog=1"><span class="file">广告创意</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=63&iscms=1&isblog=1"><span class="file">清晰卡通</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=64&iscms=1&isblog=1"><span class="file">数码影像</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=65&iscms=1&isblog=1"><span class="file">产品造型</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=66&iscms=1&isblog=1"><span class="file">建筑空间</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=67&iscms=1&isblog=1"><span class="file">艺术生活</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=68&iscms=1&isblog=1"><span class="file">交互设计</span></a></li>
		</ul>
		</li>
		
		</security:authorize>
	
		<security:authorize ifAnyGranted="A_VIEW_XC">
		<li><a target="rightFrame"
			href="article!bloglist.action?categoryTmp.id=56&iscms=0&isblog=2"><span class="folder">秀场(未审核)</span></a>
		<ul>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=95&iscms=0&isblog=2"><span class="file">品牌</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=96&iscms=0&isblog=2"><span class="file">平面视觉</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=97&iscms=0&isblog=2"><span class="file">包装设计</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=98&iscms=0&isblog=2"><span class="file">地产</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=99&iscms=0&isblog=2"><span class="file">插画卡通</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=100&iscms=0&isblog=2"><span class="file">空间</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=101&iscms=0&isblog=2"><span class="file">产品造型</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=102&iscms=0&isblog=2"><span class="file">摄影</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=103&iscms=0&isblog=2"><span class="file">策划文案</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=104&iscms=0&isblog=2"><span class="file">艺术</span></a></li>
		</ul>
		</li>
		<li><a target="rightFrame"
			href="article!bloglist.action?categoryTmp.id=56&iscms=1&isblog=2"><span class="folder">秀场(已审核)</span></a>
		<ul>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=95&iscms=1&isblog=2"><span class="file">品牌</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=96&iscms=1&isblog=2"><span class="file">平面视觉</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=97&iscms=1&isblog=2"><span class="file">包装设计</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=98&iscms=1&isblog=2"><span class="file">地产</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=99&iscms=1&isblog=2"><span class="file">插画卡通</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=100&iscms=1&isblog=2"><span class="file">空间</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=101&iscms=1&isblog=2"><span class="file">产品造型</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=102&iscms=1&isblog=2"><span class="file">摄影</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=103&iscms=1&isblog=2"><span class="file">策划文案</span></a></li>
			<li><a target="rightFrame"
				href="article!bloglist.action?categoryTmp.id=104&iscms=1&isblog=2"><span class="file">艺术</span></a></li>
		</ul>
		</li>
		</security:authorize>
	</ul>
	</li>
</ul>
</div>
</body>
</html>