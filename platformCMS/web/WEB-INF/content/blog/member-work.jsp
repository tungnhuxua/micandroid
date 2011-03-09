<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
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
			<div class="mainbh">
				<div class="leftsidebar">
					<%@include file="/common/blog-content-left.jsp" %>
				</div>
				<div class="zhuti">
				<div class="zhitibt">Information_个人档案</div>
				<div class="dangannav">
					<%@include file="/common/blog-content-nav.jsp" %>
				</div>
				<div class="grdanganzhu">注_请认真选择你的职业，最多可以选择4个，这是别人找到你合作的重要途径！</div>
				<form name="worksFrom" method="post" action="${ctx }/blog/member-works-update">
				<div class="works">
                   <dl>
                     <dt><a name="r1">管理_Management</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r1')" onclick="check('radio1r1')" id="radio1r1"  name="r1" value="董事长"  /><a href="#r1" onclick="uncheck('radio1r1')" title="点击取消职业">董事长</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r2')" id="radio1r2" name="r1" value="股东" /><a href="#r1" onclick="uncheck('radio1r2')" title="点击取消职业">股东</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r3')" id="radio1r3" name="r1" value="总裁/首席执行官" /><a href="#r1" onclick="uncheck('radio1r3')" title="点击取消职业">总裁/首席执行官</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r4')" id="radio1r4" name="r1" value="总经理" /><a href="#r1" onclick="uncheck('radio1r4')" title="点击取消职业">总经理</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r5')" id="radio1r5" name="r1" value="副总经理" /><a href="#r1" onclick="uncheck('radio1r5')" title="点击取消职业">副总经理</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r6')" id="radio1r6" name="r1" value="总监/主管" /><a href="#r1" onclick="uncheck('radio1r6')" title="点击取消职业">总监/主管</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r7')" id="radio1r7" name="r1" value="财务总监" /><a href="#r1" onclick="uncheck('radio1r7')" title="点击取消职业">财务总监</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio1r8')" id="radio1r8" name="r1" value="店长" /><a href="#r1" onclick="uncheck('radio1r8')" title="点击取消职业">店长</a></dd>
                   </dl>

                   <dl>
                     <dt><a name="r2">设计_Design</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r1')" onclick="check('radio2r1')" id="radio2r1" name="r2" value="平面设计"/><a href="#r2" onclick="uncheck('radio2r1')" title="点击取消职业">平面设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r2')" id="radio2r2" name="r2" value="互动设计"/><a href="#r2" onclick="uncheck('radio2r2')" title="点击取消职业">互动设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r3')" id="radio2r3" name="r2" value="三维设计"/><a href="#r2" onclick="uncheck('radio2r3')" title="点击取消职业">三维设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r4')" id="radio2r4" name="r2" value="视频设计"/><a href="#r2" onclick="uncheck('radio2r4')" title="点击取消职业">视频设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r5')" id="radio2r5" name="r2" value="视觉设计"/><a href="#r2" onclick="uncheck('radio2r5')" title="点击取消职业">视觉设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r6')" id="radio2r6" name="r2" value="服装设计"/><a href="#r2" onclick="uncheck('radio2r6')" title="点击取消职业">服装设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r7')" id="radio2r7" name="r2" value="建筑设计"/><a href="#r2" onclick="uncheck('radio2r7')" title="点击取消职业">建筑设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r8')" id="radio2r8" name="r2" value="空间设计"/><a href="#r2" onclick="uncheck('radio2r8')" title="点击取消职业">空间设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r9')" id="radio2r9" name="r2" value="工业设计"/><a href="#r2" onclick="uncheck('radio2r9')" title="点击取消职业">工业设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r10')" id="radio2r10" name="r2" value="界面设计"/><a href="#r2" onclick="uncheck('radio2r10')" title="点击取消职业">界面设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r11')" id="radio2r11" name="r2" value="游戏设计"/><a href="#r2" onclick="uncheck('radio2r11')" title="点击取消职业">游戏设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r12')" id="radio2r12" name="r2" value="产品设计"/><a href="#r2" onclick="uncheck('radio2r12')" title="点击取消职业">产品设计</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio2r13')" id="radio2r13" name="r2" value="设计相关"/><a href="#r2" onclick="uncheck('radio2r13')" title="点击取消职业">设计相关</a></dd>
                   </dl>
                   
                   <dl>
                     <dt><a name="r3">广告_Advertising</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio3r1')" id="radio3r1" name="r3" value="创意总监"/><a href="#r3" onclick="uncheck('radio3r1')" title="点击取消职业">创意总监</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio3r2')" id="radio3r2" name="r3" value="美术指导"/><a href="#r3" onclick="uncheck('radio3r2')" title="点击取消职业">美术指导</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio3r3')" id="radio3r3" name="r3" value="策略/文案"/><a href="#r3" onclick="uncheck('radio3r3')" title="点击取消职业">策略/文案</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio3r4')" id="radio3r4" name="r3" value="广告客户服务"/><a href="#r3" onclick="uncheck('radio3r4')" title="点击取消职业">广告客户服务</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio3r5')" id="radio3r5" name="r3" value="广告相关"/><a href="#r3" onclick="uncheck('radio3r5')" title="点击取消职业">广告相关</a></dd>
                   </dl>

                   <dl>
                     <dt><a name="r4">媒体/杂志_Media / Magazine</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio4r1')" id="radio4r1" name="r4" value="出版人"/><a href="#r4" onclick="uncheck('radio4r1')" title="点击取消职业">出版人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio4r2')" id="radio4r2" name="r4" value="主编"/><a href="#r4" onclick="uncheck('radio4r2')" title="点击取消职业">主编</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio4r3')" id="radio4r3" name="r4" value="编辑"/><a href="#r4" onclick="uncheck('radio4r3')" title="点击取消职业">编辑</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio4r4')" id="radio4r4" name="r4" value="美术编辑"/><a href="#r4" onclick="uncheck('radio4r4')" title="点击取消职业">美术编辑</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio4r5')" id="radio4r5" name="r4" value="媒体广告/市场"/><a href="#r4" onclick="uncheck('radio4r5')" title="点击取消职业">媒体广告/市场</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio4r6')" id="radio4r6" name="r4" value="记者"/><a href="#r4" onclick="uncheck('radio4r6')" title="点击取消职业">记者</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio4r7')" id="radio4r7" name="r4" value="媒体/杂志相关"/><a href="#r4" onclick="uncheck('radio4r7')" title="点击取消职业">媒体/杂志相关</a></dd>
                   </dl>

                   <dl>
                     <dt><a name="r5">艺术_Art</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r1')" id="radio5r1" name="r5" value="绘画"/><a href="#r5" onclick="uncheck('radio5r1')" title="点击取消职业">绘画</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r2')" id="radio5r2" name="r5" value="写作"/><a href="#r5" onclick="uncheck('radio5r2')" title="点击取消职业">写作</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r3')" id="radio5r3" name="r5" value="舞蹈"/><a href="#r5" onclick="uncheck('radio5r3')" title="点击取消职业">舞蹈</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r4')" id="radio5r4" name="r5" value="戏剧"/><a href="#r5" onclick="uncheck('radio5r4')" title="点击取消职业">戏剧</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r5')" id="radio5r5" name="r5" value="策展人"/><a href="#r5" onclick="uncheck('radio5r5')" title="点击取消职业">策展人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r6')" id="radio5r6" name="r5" value="书法"/><a href="#r5" onclick="uncheck('radio5r6')" title="点击取消职业">书法</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r7')" id="radio5r7" name="r5" value="视觉艺术"/><a href="#r5" onclick="uncheck('radio5r7')" title="点击取消职业">视觉艺术</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r8')" id="radio5r8" name="r5" value="行为艺术"/><a href="#r5" onclick="uncheck('radio5r8')" title="点击取消职业">行为艺术</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r9')" id="radio5r9" name="r5" value="收藏"/><a href="#r5" onclick="uncheck('radio5r9')" title="点击取消职业">收藏</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r10')" id="radio5r10" name="r5" value="雕塑"/><a href="#r5" onclick="uncheck('radio5r10')" title="点击取消职业">雕塑</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r11')" id="radio5r11" name="r5" value="舞台美术"/><a href="#r5" onclick="uncheck('radio5r11')" title="点击取消职业">舞台美术</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r12')" id="radio5r12" name="r5" value="曲艺/杂技"/><a href="#r5" onclick="uncheck('radio5r12')" title="点击取消职业">曲艺/杂技</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r13')" id="radio5r13" name="r5" value="戏剧导演"/><a href="#r5" onclick="uncheck('radio5r13')" title="点击取消职业">戏剧导演</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio5r14')" id="radio5r14" name="r5" value="艺术相关"/><a href="#r5" onclick="uncheck('radio5r14')" title="点击取消职业">艺术相关 </a></dd>
                   </dl>

                   <dl>
                     <dt><a name="r6">摄影_Photography</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio6r1')" id="radio6r1" name="r6" value="时装摄影"/><a href="#r6" onclick="uncheck('radio6r1')" title="点击取消职业">时装摄影</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio6r2')" id="radio6r2" name="r6" value="广告摄影"/><a href="#r6" onclick="uncheck('radio6r2')" title="点击取消职业">广告摄影</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio6r3')" id="radio6r3" name="r6" value="人像摄影"/><a href="#r6" onclick="uncheck('radio6r3')" title="点击取消职业">人像摄影</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio6r4')" id="radio6r4" name="r6" value="风光摄影"/><a href="#r6" onclick="uncheck('radio6r4')" title="点击取消职业">风光摄影</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio6r6')" id="radio6r6" name="r6" value="图片后期"/><a href="#r6" onclick="uncheck('radio6r6')" title="点击取消职业">图片后期</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio6r7')" id="radio6r7" name="r6" value="摄影相关"/><a href="#r6" onclick="uncheck('radio6r7')" title="点击取消职业">摄影相关</a></dd>
                   </dl>

                   <dl>
                     <dt><a name="r7">造型_Modeling</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio7r1')" id="radio7r1" name="r7" value="化妆师"/><a href="#r7" onclick="uncheck('radio7r1')" title="点击取消职业">化妆师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio7r2')" id="radio7r2" name="r7" value="发型师"/><a href="#r7" onclick="uncheck('radio7r2')" title="点击取消职业">发型师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio7r3')" id="radio7r3" name="r7" value="美甲师"/><a href="#r7" onclick="uncheck('radio7r3')" title="点击取消职业">美甲师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio7r4')" id="radio7r4" name="r7" value="服装造型师"/><a href="#r7" onclick="uncheck('radio7r4')" title="点击取消职业">服装造型师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio7r5')" id="radio7r5" name="r7" value="造型相关"/><a href="#r7" onclick="uncheck('radio7r5')" title="点击取消职业">造型相关</a></dd>
                   </dl>

                   <dl>
                     <dt><a name="r8">电影/电视_Film / TV</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r1')" id="radio8r1" name="r8" value="制片人"/><a href="#r8" onclick="uncheck('radio8r1')" title="点击取消职业">制片人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r2')" id="radio8r2" name="r8" value="电影/电视导演"/><a href="#r8" onclick="uncheck('radio8r2')" title="点击取消职业">电影/电视导演</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r3')" id="radio8r3" name="r8" value="电影/电视监制"/><a href="#r8" onclick="uncheck('radio8r3')" title="点击取消职业">电影/电视监制</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r4')" id="radio8r4" name="r8" value="演员"/><a href="#r8" onclick="uncheck('radio8r4')" title="点击取消职业">演员</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r5')" id="radio8r5" name="r8" value="编剧"/><a href="#r8" onclick="uncheck('radio8r5')" title="点击取消职业">编剧</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r6')" id="radio8r6" name="r8" value="电影摄影"/><a href="#r8" onclick="uncheck('radio8r6')" title="点击取消职业">电影摄影</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r7')" id="radio8r7" name="r8" value="电视摄像"/><a href="#r8" onclick="uncheck('radio8r7')" title="点击取消职业">电视摄像</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r8')" id="radio8r8" name="r8" value="节目主持人"/><a href="#r8" onclick="uncheck('radio8r8')" title="点击取消职业">节目主持人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r9')" id="radio8r9" name="r8" value="电影/电视后期"/><a href="#r8" onclick="uncheck('radio8r9')" title="点击取消职业">电影/电视后期</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r10')" id="radio8r10" name="r8" value="电影/电视美术"/><a href="#r8" onclick="uncheck('radio8r10')" title="点击取消职业">电影/电视美术</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio8r11')" id="radio8r11" name="r8" value="电影/电视相关"/><a href="#r8" onclick="uncheck('radio8r11')" title="点击取消职业">电影/电视相关</a></dd>
                   </dl>
                   
                   <dl>
                     <dt><a name="r9">音乐_Music</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r1')" id="radio9r1" name="r9" value="音乐制作人"/><a href="#r9" onclick="uncheck('radio9r1')" title="点击取消职业">音乐制作人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r2')" id="radio9r2" name="r9" value="歌手"/><a href="#r9" onclick="uncheck('radio9r2')" title="点击取消职业">歌手</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r3')" id="radio9r3" name="r9" value="乐手"/><a href="#r9" onclick="uncheck('radio9r3')" title="点击取消职业">乐手</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r4')" id="radio9r4" name="r9" value="乐队"/><a href="#r9" onclick="uncheck('radio9r4')" title="点击取消职业">乐队</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r5')" id="radio9r5" name="r9" value="DJ"/><a href="#r9" onclick="uncheck('radio9r5')" title="点击取消职业">DJ</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r6')" id="radio9r6" name="r9" value="词曲/编曲"/><a href="#r9" onclick="uncheck('radio9r6')" title="点击取消职业">词曲/编曲</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r7')" id="radio9r7" name="r9" value="录音/混音"/><a href="#r9" onclick="uncheck('radio9r7')" title="点击取消职业">录音/混音</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r8')" id="radio9r8" name="r9" value="音乐后期"/><a href="#r9" onclick="uncheck('radio9r8')" title="点击取消职业">音乐后期</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio9r9')" id="radio9r9" name="r9" value="音乐相关"/><a href="#r9" onclick="uncheck('radio9r9')" title="点击取消职业">音乐相关</a></dd>
                   </dl>
    
                   <dl>
                     <dt><a name="r10">模特儿_Models</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio10r1')" id="radio10r1" name="r10" value="时装模特儿"/><a href="#r10" onclick="uncheck('radio10r1')" title="点击取消职业">时装模特儿</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio10r2')" id="radio10r2" name="r10" value="平面模特儿"/><a href="#r10" onclick="uncheck('radio10r2')" title="点击取消职业">平面模特儿</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio10r3')" id="radio10r3" name="r10" value="车模"/><a href="#r10" onclick="uncheck('radio10r3')" title="点击取消职业">车模</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio10r4')" id="radio10r4" name="r10" value="人体模特儿"/><a href="#r10" onclick="uncheck('radio10r4')" title="点击取消职业">人体模特儿</a></dd>
                   </dl>
        
                   <dl>
                     <dt><a name="r11">CLUB</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio11r1')" id="radio11r1" name="r11" value="夜店潮人"/><a href="#r11" onclick="uncheck('radio11r1')" title="点击取消职业">夜店潮人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio11r2')" id="radio11r2" name="r11" value="调酒师"/><a href="#r11" onclick="uncheck('radio11r2')" title="点击取消职业">调酒师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio11r3')" id="radio11r3" name="r11" value="CLUB相关"/><a href="#r11" onclick="uncheck('radio11r3')" title="点击取消职业">CLUB相关</a></dd>
                   </dl>

                   <dl>
                     <dt><a name="r12">更多_More</a></dt>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r1')" id="radio12r1" name="r12" value="空姐"/><a href="#r12" onclick="uncheck('radio12r1')" title="点击取消职业">空姐</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r2')" id="radio12r2" name="r12" value="飞行员"/><a href="#r12" onclick="uncheck('radio12r2')" title="点击取消职业">飞行员</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r3')" id="radio12r3" name="r12" value="程序"/><a href="#r12" onclick="uncheck('radio12r3')" title="点击取消职业">程序</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r4')" id="radio12r4" name="r12" value="网络管理"/><a href="#r12" onclick="uncheck('radio12r4')" title="点击取消职业">网络管理</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r5')" id="radio12r5" name="r12" value="店主"/><a href="#r12" onclick="uncheck('radio12r5')" title="点击取消职业">店主</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r6')" id="radio12r6" name="r12" value="编导"/><a href="#r12" onclick="uncheck('radio12r6')" title="点击取消职业">编导</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r7')" id="radio12r7" name="r12" value="经纪人"/><a href="#r12" onclick="uncheck('radio12r7')" title="点击取消职业">经纪人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r8')" id="radio12r8" name="r12" value="教师"/><a href="#r12" onclick="uncheck('radio12r8')" title="点击取消职业">教师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r9')" id="radio12r9" name="r12" value="学生"/><a href="#r12" onclick="uncheck('radio12r9')" title="点击取消职业">学生</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r10')" id="radio12r10" name="r12" value="教练"/><a href="#r12" onclick="uncheck('radio12r10')" title="点击取消职业">教练</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r11')" id="radio12r11" name="r12" value="运动员"/><a href="#r12" onclick="uncheck('radio12r11')" title="点击取消职业">运动员</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r12')" id="radio12r12" name="r12" value="律师/法律顾问"/><a href="#r12" onclick="uncheck('radio12r12')" title="点击取消职业">律师/法律顾问</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r13')" id="radio12r13" name="r12" value="翻译"/><a href="#r12" onclick="uncheck('radio12r13')" title="点击取消职业">翻译</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r14')" id="radio12r14" name="r12" value="医生"/><a href="#r12" onclick="uncheck('radio12r14')" title="点击取消职业">医生</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r15')" id="radio12r15" name="r12" value="宠物医生"/><a href="#r12" onclick="uncheck('radio12r15')" title="点击取消职业">宠物医生</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r16')" id="radio12r16" name="r12" value="护士"/><a href="#r12" onclick="uncheck('radio12r16')" title="点击取消职业">护士</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r17')" id="radio12r17" name="r12" value="心理咨询"/><a href="#r12" onclick="uncheck('radio12r17')" title="点击取消职业">心理咨询</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r18')" id="radio12r18" name="r12" value="美容美体"/><a href="#r12" onclick="uncheck('radio12r18')" title="点击取消职业">美容美体</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r19')" id="radio12r19" name="r12" value="按摩"/><a href="#r12" onclick="uncheck('radio12r19')" title="点击取消职业">按摩</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r20')" id="radio12r20" name="r12" value="财务"/><a href="#r12" onclick="uncheck('radio12r20')" title="点击取消职业">财务</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r21')" id="radio12r21" name="r12" value="销售"/><a href="#r12" onclick="uncheck('radio12r21')" title="点击取消职业">销售</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r22')" id="radio12r22" name="r12" value="科研"/><a href="#r12" onclick="uncheck('radio12r22')" title="点击取消职业">科研</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r23')" id="radio12r23" name="r12" value="公务员"/><a href="#r12" onclick="uncheck('radio12r23')" title="点击取消职业">公务员</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r24')" id="radio12r24" name="r12" value="投资"/><a href="#r12" onclick="uncheck('radio12r24')" title="点击取消职业">投资</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r25')" id="radio12r25" name="r12" value="赛车"/><a href="#r12" onclick="uncheck('radio12r25')" title="点击取消职业">赛车</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r26')" id="radio12r26" name="r12" value="博士"/><a href="#r12" onclick="uncheck('radio12r26')" title="点击取消职业">博士</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r27')" id="radio12r27" name="r12" value="厨师"/><a href="#r12" onclick="uncheck('radio12r27')" title="点击取消职业">厨师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r28')" id="radio12r28" name="r12" value="魔术师"/><a href="#r12" onclick="uncheck('radio12r28')" title="点击取消职业">魔术师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r29')" id="radio12r29" name="r12" value="公关"/><a href="#r12" onclick="uncheck('radio12r29')" title="点击取消职业">公关</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r30')" id="radio12r30" name="r12" value="人力资源"/><a href="#r12" onclick="uncheck('radio12r30')" title="点击取消职业">人力资源</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r31')" id="radio12r31" name="r12" value="单身潮人"/><a href="#r12" onclick="uncheck('radio12r31')" title="点击取消职业">单身潮人</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r32')" id="radio12r32" name="r12" value="刺青师"/><a href="#r12" onclick="uncheck('radio12r32')" title="点击取消职业">刺青师</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r33')" id="radio12r33" name="r12" value="健身教练"/><a href="#r12" onclick="uncheck('radio12r33')" title="点击取消职业">健身教练</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r34')" id="radio12r34" name="r12" value="其他职业"/><a href="#r12" onclick="uncheck('radio12r34')" title="点击取消职业">其他职业</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r35')" id="radio12r35" name="r12" value="宅男"/><a href="#r12" onclick="uncheck('radio12r35')" title="点击取消职业">宅男</a></dd>
                     <dd><input type="radio" title="点击选中职业" onclick="check('radio12r36')" id="radio12r36" name="r12" value="宅女"/><a href="#r12" onclick="uncheck('radio12r36')" title="点击取消职业">宅女</a></dd>
                   </dl>
                </div>
				<div class="zhiyebaocun" >
				    <input type="hidden" name="workId" value="${entity.workId }"/>
				    <input type="hidden" name="workName" value="${entity.workName}"/>
					<input type="hidden" name="entity.id" value="${entity.id}"/>
					<input type="submit" value="保存提交信息"  onclick="return judge_submit();" /></div>
                </form>
<script language="javascript">
 var SetString = '${entity.workId }'; //定义改页面控件的已选定（输入）的值。
 var AllControlValue = SetString.split(";"); //各控件的设定值
 var ControlName = ""; //各控件的名称
 var ControlgCheck = ""; //多选控件是否选择
 var ControlValue = ""; //多选控件的选定值
 var ControlLen = ""; //多选空件的长度
 var ControlCount = 0; //定义单选或多选控件子的数量；
					
    if (SetString.length != 0)//没有设定传入值
    {
        for (i = 0; i < document.worksFrom.elements.length ; i++)//剔除SUBMIT控件和隐藏控件
        {
            if (ControlName != document.worksFrom.elements[i].name) {
			
                if (document.worksFrom.elements[i].type == "radio") {

                    ControlName = document.worksFrom.elements[i].name;

                    ControlLen = "document.worksFrom." + ControlName + ".length";

                    ControlCheck = "document.worksFrom." + ControlName + "[j].checked=true";
					
                    for (j = 0; j < eval(ControlLen); j++) {
                        if (AllControlValue[i - ControlCount] == j && AllControlValue[i - ControlCount].length != 0) {
							
                            eval(ControlCheck);
							

                        }

                    }

                    ControlCount = ControlCount + j - 1;


                }

            }

        }

    }
	
  function judge_submit() {

        var JudgeName = ""; //控件的名称

        var ReturnString = ""; //全部返回值

        var Year_Month_Reg = ""; //对文本框输入值的判断

        var ControlStringCheck = ""; //多选控件是否选择

        var ControlStringValue = ""; //多选控件的选定值

        var ControlStringLen = ""; //多选空件的长度
		
		var ReturnControlValue = ""; //返回单选选中所有值
		
		


        for (i = 0; i < document.worksFrom.elements.length - 2; i++)//剔除SUBMIT控件和隐藏控件
        {

            if (JudgeName != document.worksFrom.elements[i].name) {
                
                if (document.worksFrom.elements[i].type == "radio") {

                    JudgeName = document.worksFrom.elements[i].name;

                    ControlStringLen = "document.worksFrom." + JudgeName + ".length";

                    ControlStringCheck = "document.worksFrom." + JudgeName + "[j].checked";
					
					ControlValue ="document.worksFrom." + JudgeName + "[j].value";
					

                    for (j = 0; j < eval(ControlStringLen); j++) {
                        if (eval(ControlStringCheck)) {
                            ControlStringValue = j;
							var temp = eval(ControlValue);
							ReturnControlValue = ReturnControlValue + temp +";";
                        }
                    }
                    ReturnString = ReturnString + ";" + ControlStringValue;
                    ControlStringValue = "";
                }
           }
        }
        ReturnString = ReturnString.substring(1, ReturnString.length);

        document.worksFrom.workId.value = ReturnString;
        document.worksFrom.workName.value = ReturnControlValue;
 
        return true;
    }
	
function uncheck(id)
{
	document.getElementById(id).checked=false
}

function check(id){
	var count =0;
	//1
	for(j = 0; j<8;j++){
		if(document.worksFrom.r1[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//2
	for(j = 0; j<13;j++){
		if(document.worksFrom.r2[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//3
	for(j = 0; j<5;j++){
		if(document.worksFrom.r3[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//4
	for(j = 0; j<7;j++){
		if(document.worksFrom.r4[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//5
	for(j = 0; j<14;j++){
		if(document.worksFrom.r5[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//6
	for(j = 0; j<6;j++){
		if(document.worksFrom.r6[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//7
	for(j = 0; j<5;j++){
		if(document.worksFrom.r7[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//8
	for(j = 0; j<11;j++){
		if(document.worksFrom.r8[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//9
	for(j = 0; j<9;j++){
		if(document.worksFrom.r9[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//10
	for(j = 0; j<4;j++){
		if(document.worksFrom.r10[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//11
	for(j = 0; j<3;j++){
		if(document.worksFrom.r11[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
	//12
	for(j = 0; j<36;j++){
		if(document.worksFrom.r12[j].checked == true){
			 count = count + 1;
			 if(count > 4){
			   document.getElementById(id).checked=false;
			   message();
			 }
		 }
						
	}
  
}

	function message(){
	 alert("您最多只能选择四个职业!\n如果您需要更换其它职业,请您点击已选择的职业名称，取消职业，在继续选择您需要的职业！");
	}

</script>
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