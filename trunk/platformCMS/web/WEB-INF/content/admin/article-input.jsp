<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<title></title>
<link href="css/admin.css" rel="stylesheet" type="text/css">
<link href="css/theme.css" rel="stylesheet" type="text/css">
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript">
//上传图片
function upload() {	
	var of = $('#uploadFile');
	//检查是否选择了图片
	if(of.val()=='') {
		alert('请选择要上传的图片');
		return;
	}
	//将file移动至上传表单
	$('#fileContent').empty();
	$('#fileContent').append(of);
	//复制一个file放至原处
	$('#ufc').append(of.clone());
	//修改属性
	of.attr('id','');
	of.attr('name','upload');
	
	$('#uploadForm').submit();
}

function uploadlink() {	
	var of = $('#uploadFilelink');
	//检查是否选择了图片
	if(of.val()=='') {
		alert('请选择要上传的图片');
		return;
	}
	//将file移动至上传表单
	$('#fileContentlink').empty();
	$('#fileContentlink').append(of);
	//复制一个file放至原处
	$('#ufclink').append(of.clone());
	//修改属性
	of.attr('id','');
	of.attr('name','upload');
	
	$('#uploadFormlink').submit();
}
$(document).ready(function(){
	$("#submitForm").validate({
	});
});
</script>

</head>
<body>

<form id="uploadForm" action="upload!upload.action" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContent"></span>
<input name="fileGroup" value="article" type="hidden">
</form>
<form id="uploadFormlink" action="upload!uploadlink.action" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContentlink"></span>
<input name="fileGrouplink" value="article" type="hidden">
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>

<div class="body-box">
<div class="rhead">
<div class="rpos">当前位置： 文章管理 - 新增/修改</div>
<div class="clear"></div>
</div>
<form method="post" action="article!save" id="submitForm"><input
	type="hidden" name="pageNo" value="1" />
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
	border="0">

	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>标题：</td>
		<td colspan="3" width="88%" class="pn-fcontent"><input
			type="text" maxlength="255" value="${entity.title }"
			name="entity.title" class="required" size="80" maxlength="255" /></td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>所属栏目：</td>
		<td colspan="3" width="88%" class="pn-fcontent"><ouun:category_select
			name="categoryTmp.id" list="${categoryList}"
			selected="${categoryTmp.id}" /></td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>文章来源：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input
			type="text" maxlength="255" value="${entity.source }"
			name="entity.source" class="required" size="80" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>网站首页滚动新闻：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<s:checkbox name="sitHome" value="entity.siteHome" fieldValue="true" theme="simple"/>
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>栏目首页滚动新闻：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<s:checkbox name="catHome" value="entity.categoryHome" fieldValue="true" theme="simple"/>
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>是否推荐：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<s:checkbox name="recom" value="entity.recommend" fieldValue="true" theme="simple"/>
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>标签(多个标签以","分开)：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input
			type="text" maxlength="255" value="${entity.tag }"
			name="tagStr"  size="80" maxlength="255" />
		</td>
	</tr>
		<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>发布方式：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input type="radio" name="radio1" checked="checked"/>新闻发布
		<input type="radio" name="radio1" />跳转地址
		<input
			type="text" maxlength="255" value="${entity.uri }"
			name="entity.uri" id=uri class="url"  size="80" maxlength="255" />此链接地址在图片新闻中应用
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel">内容：</td>
		<td colspan="3" width="88%" class="pn-fcontent"><script
			type="text/javascript">
				<!--
				// Automatically calculates the editor base path based on the _samples directory.
				// This is usefull only for these samples. A real application should use something like this:
				// oFCKeditor.BasePath = '/fckeditor/' ;	// '/fckeditor/' is the default value.
				//var sBasePath = document.location.href.substring(0,document.location.href.lastIndexOf('_samples')) ;
				
				var oFCKeditor = new FCKeditor('entity.content') ;
				oFCKeditor.BasePath	= '${ctx}/fckeditor/';
				oFCKeditor.Height = 300;
				oFCKeditor.Value = '${entity.content}';
				oFCKeditor.Create();
				//-->
		  </script></td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel">缩略图：</td>
		<td colspan="1" width="38%" class="pn-fcontent">
		<span id="ufc"><input type="file" id="uploadFile" size="20" /></span>
		<input type="button" value="上传" onclick="upload();" /></td>
		<td colspan="2" class="pn-fbutton"><img id="preImg" alt="预览区"
			src="${ctx }${entity.imageUri}"
			style="background-color: #CCCCCC; border: 1px solid #333" /></td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel">内容图片：</td>
		<td colspan="1" width="38%" class="pn-fcontent">
		<span id="ufclink"><input type="file" id="uploadFilelink" size="20" /></span>
		<input type="button" value="上传" onclick="uploadlink();" /></td>
		<td colspan="2" class="pn-fbutton">
			<img id="preImglink" alt="预览区"src="${ctx }${entity.imageLink}"style="background-color: #CCCCCC; border: 1px solid #333" />
		</td>
	</tr>
	<tr>
		<td colspan="4" class="pn-fbutton"><input type="hidden" name="id"
			value="${entity.id }" /> 
			<input type="hidden" id="imageUri" name="entity.imageUri" 
			value="${entity.imageUri}" />
			<input type="hidden" id="imageLink" name="entity.imageLink" 
			value="${entity.imageLink}" />
			<input type="hidden" name="artRandom" value="${artRandom}">
			<input type="hidden" name="entity.iscms" value="1">
			<input type="hidden" name="entity.isblog" value="0">
			<input type="submit" value="保存" /> &nbsp;
		<input type="reset" value="重置" /></td>

	</tr>
</table>
</form>

</div>
<div
	style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;"><iframe
	style="width: 186px; height: 199px;"
	src="Com_left_data/My97DatePicker.htm" border="0" frameborder="0"
	scrolling="no"></iframe></div>
</body>
</html>