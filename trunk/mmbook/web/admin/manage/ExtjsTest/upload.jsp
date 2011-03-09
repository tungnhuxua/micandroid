<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" >
Ext.onReady(function() {
	var win = new Ext.Window({
		title : '多文件上传示例',
		width : 500,
		height : 500,
		resizable : false,
		layout : 'fit',
		items : [{
			xtype : 'uploadpanel',
		    //site/SiteInfomation/save.do
			//uploadUrl : 'uploadFiles.action',
			uploadUrl : '../../uploader/File/uploadFiles.do',
			filePostName : 'myUpload', // 这里很重要，默认值为'fileData',这里匹配action中的setMyUpload属性
			flashUrl : 'js/swfupload.swf',
			fileSize : '500 MB',
			height : 400,
			border : false,
			fileTypes : '*.*', // 在这里限制文件类型:'*.jpg,*.png,*.gif'
			fileTypesDescription : '所有文件',
			postParams : {
				path : '/pic/xx/' // 上传到服务器的files目录下面
			}
		}],
		bbar : ['作者:廖瀚卿 | QQ:3990995 | 博客:http://yourgame.javaeye.com']
	});

	win.show();
});
</script>