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
<h1>文章栏目</h1>
<hr>
<ul id="tree" class="filetree">

	<li><a href="#"><span
		class="folder">根栏目</span></a>
	<ul>
		<security:authorize ifAnyGranted="A_VIEW_ZX">
		<li><a target="rightFrame"
			href="article.action?categoryTmp.id=54"> <span class="folder">资讯</span>
		</a>
		<ul>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=59"><span class="file">抢先看</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=60"><span class="file">新闻动态</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=61"><span class="file">平面视觉</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=62"><span class="file">广告创意</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=63"><span class="file">清晰卡通</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=64"><span class="file">数码影像</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=65"><span class="file">产品造型</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=66"><span class="file">建筑空间</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=67"><span class="file">艺术生活</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=68"><span class="file">交互设计</span></a></li>
		</ul>
		</li>
		</security:authorize>
		<security:authorize ifAnyGranted="A_VIEW_RW">
		<li><a target="rightFrame"
			href="article.action?categoryTmp.id=55"><span class="folder">人物</span></a>
		<ul>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=69"><span class="file">访谈</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=70"><span class="file">色女郎</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=71"><span class="file">大师</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=72"><span class="file">名流</span></a></li>
		</ul>
		</li>
		</security:authorize>
		<security:authorize ifAnyGranted="A_VIEW_XC">
		<li><a target="rightFrame"
			href="article.action?categoryTmp.id=56"><span class="folder">秀场</span></a>
		<ul>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=95"><span class="file">品牌</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=96"><span class="file">平面视觉</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=97"><span class="file">包装设计</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=98"><span class="file">地产</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=99"><span class="file">插画卡通</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=100"><span class="file">空间</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=101"><span class="file">产品造型</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=102"><span class="file">摄影</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=103"><span class="file">策划文案</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=104"><span class="file">艺术</span></a></li>
		</ul>
		</li>
		</security:authorize>
		<security:authorize ifAnyGranted="A_VIEW_ZT">
		<li><a target="rightFrame"
			href="article.action?categoryTmp.id=57"><span class="folder">专题</span></a>
		<ul>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=73"><span class="file">焦点关注</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=74"><span class="file">课题研究</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=75"><span class="file">机构巡展</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=76"><span class="file">电子杂志</span></a></li>
		</ul>
		</li>
		</security:authorize>
		<security:authorize ifAnyGranted="A_VIEW_QW">
		<li><a target="rightFrame"
			href="article.action?categoryTmp.id=58"><span class="folder">圈网</span></a>
		<ul>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=77"><span class="file">设计师</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=78"><span class="file">广告</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=79"><span class="file">视觉</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=80"><span class="file">艺术</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=81"><span class="file">插画</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=82"><span class="file">影像</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=83"><span class="file">产品</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=84"><span class="file">空间</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=85"><span class="file">交互</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=86"><span class="file">文案</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=87"><span class="file">素材</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=88"><span class="file">教育</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=89"><span class="file">综合</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=90"><span class="file">团体</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=91"><span class="file">会展</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=92"><span class="file">媒体</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=93"><span class="file">时尚</span></a></li>
			<li><a target="rightFrame"
				href="article.action?categoryTmp.id=94"><span class="file">潮流</span></a></li>
		</ul>
		</li>
</security:authorize>
	</ul>
	</li>
</ul>
</div>
</body>
</html>