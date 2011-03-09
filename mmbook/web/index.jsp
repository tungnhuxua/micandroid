<%@ page contentType="text/html; charset=UTF-8" %>


<html>
	<head>
		<title></title>
		<!-- 系统样式   -->
		<link rel="stylesheet" type="text/css"
			href="adapter/Ext3.2/resources/css/ext-all.css">
		<link rel="stylesheet" type="text/css" href="css/Ext.ux.form.LovCombo.css">	
		<link rel="stylesheet" type="text/css" href="css/ext-patch.css">
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/icons.css">
		<link rel="stylesheet" type="text/css" href="css/UploadPanel.css">
	</head>
	<body>
		<div id="loading">
			<div class="loading-indicator">
				<img src="./images/extanim32.gif" width="31" height="31"
					style="margin-right: 8px; float: left; vertical-align: top;" />
				欢迎MM书城管理平台
				<span id="loading-company">V1.0</span>
				<br />
				<span id="loading-msg">正在加载系统样式...</span>
			</div>
		</div>
		<div id="west"></div>
		<div id="north"></div>
		<div id="center1"></div>
		<div id="south"></div>
		<div id="db">
			<!-- EXTJS -->
<!--			<script type="text/javascript" src="adapter/Ext3.2/ext-base.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/ext-basex.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/ext-all.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/ext-lang-zh_CN.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/public.js"></script>
 -->			
			<script type="text/javascript" src="adapter/Ext2.2/ext-base.js"></script> 
			<script type="text/javascript" src="adapter/Ext2.2/ext-all.js"></script>
			<script type="text/javascript" src="adapter/Ext2.2/ext-lang-zh_CN.js"></script>
			<script type="text/javascript" src="adapter/Ext2.2/extend/public.js"></script>	
			
			<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '正在加载系统数据...';</script>
			<!-- 相关扩展 -->

			<script type="text/javascript" src="adapter/Ext3.2/inherited/main.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/TabCloseMenu.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.ThemeChange.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.EasyButton.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.grid.Search.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.grid.PageSizePlugin.js"></script>
		    <script type="text/javascript" src="adapter/Ext3.2/inherited/FileUploadField.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.ComboBoxCheckTree.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.TreeCheckNodeUI.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.form.LovCombo.js"></script>
			
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.form.TreeField.js"></script>
			
			<script type="text/javascript" src="adapter/Ext3.2/inherited/Ext.ux.ComboBoxTree.js"></script>
			
			<script type="text/javascript" src="adapter/Ext3.2/inherited/swfupload.js"></script>
			<script type="text/javascript" src="adapter/Ext3.2/inherited/UploadPanel.js"></script>
		</div>
		
		<div id="header">
			<h1>
				欢迎MM书城管理平台V2.0
			</h1>
		</div>
	</body>
</html>