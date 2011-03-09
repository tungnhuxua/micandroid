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
function initAdsType(){
	var typeId = "${entity.type.id}";
	if(typeId=="1"){
		$(".googlecode").show();
		$(".adsimage").hide();
	}else{
		$(".googlecode").hide();
		$(".adsimage").show();
	}
}
initAdsType();

$(document).ready(function(){
	$("#submitForm").validate();
});
</script>
</head>
<body>

<form id="uploadForm" action="upload!upload.action" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContent"></span>
<input name="fileGroup" value="ads" type="hidden">
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>

<div class="body-box">
<div class="rhead">
<div class="rpos">当前位置： 广告管理 -<s:if test="location.locaton!=null">${location.location} - </s:if> 新增/修改</div>
<div class="clear"></div>
</div>
<form method="post" action="ads!save" id="submitForm">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
	border="0">

	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>广告名：</td>
		<td colspan="3" width="88%" class="pn-fcontent"><input
			type="text" maxlength="255" value="${entity.name }"
			name="entity.name" class="required" size="80" maxlength="255" /></td>
	</tr>
	
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>描述：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input
			type="text" maxlength="255" value="${entity.description }"
			name="entity.description" class="required" size="80" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>广告类型：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
			<s:radio list="adsTypes" listKey="id" listValue="name" name="entity.type.id" value="entity.type.id" theme="simple"></s:radio>
		</td>
	</tr>
	
	<tr class="googlecode">
		<td width="12%" class="pn-flabel">google广告代码：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
			<textarea rows="8" cols="80" name="entity.script">${entity.script}</textarea>
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>图片链接:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
			<input
			type="text" maxlength="255" value="${entity.url}"
			name="entity.url" class="required" size="80" maxlength="255" />
		</td>
	</tr>
	<tr class="adsimage">
		<td width="12%" class="pn-flabel">广告图片：</td>
		<td colspan="1" width="38%" class="pn-fcontent">
		<span id="ufc"><input type="file" id="uploadFile" size="20"/></span>
		<input type="button" value="上传" onclick="upload();" /></td>
		<td colspan="2" class="pn-fbutton"><img id="preImg" alt="预览区"
			src="${ctx }${entity.image}"
			style="background-color: #CCCCCC; border: 1px solid #333" /></td>
	</tr>

	<tr>
		<td colspan="4" class="pn-fbutton"><input type="hidden" name="id"
			value="${entity.id }" /> 
			<input type="hidden" id="imageUri" name="entity.image" 
			value="${entity.image}" />
			<input type="hidden" name="location.id" value="${location.id}">
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