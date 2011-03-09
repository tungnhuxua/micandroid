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
<div class="rpos">当前位置： 文章管理 - 日志秀场审核</div>
<div class="clear"></div>
</div>
<form method="post" action="article!save" id="submitForm"><input
	type="hidden" name="pageNo" value="1" />
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
	border="0">

	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>标题：</td>
		<td colspan="3" width="88%" class="pn-fcontent">${entity.title}</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>所属栏目：</td>
		<td colspan="3" width="88%" class="pn-fcontent">${entity.category.name }</td>
	</tr>
	
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>标签：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		${entity.tag }
		</td>
	</tr>
		
	<tr>
		<td width="12%" class="pn-flabel">内容：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
				${entity.content}
			</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel">缩略图：</td>
		<td colspan="1" width="38%" class="pn-fcontent">
		</td>
		<td colspan="2" class="pn-fbutton"><img id="preImg" alt="预览区"
			src="${ctx }${entity.imageUri}"
			style="background-color: #CCCCCC; border: 1px solid #333;height: 150px;width: 150px;" /></td>
	</tr>
	
	<tr>
		<td colspan="4" class="pn-fbutton">
		<s:if test="entity.iscms == '1'">
		<a href="article!rerify.action?id=${entity.id}&artRandom=${entity.random}&iscms=${iscms}&isblog=${isblog}&cmsflag=0&categoryTmp.id=${categoryTmp.id}">取消审核</a></s:if>
		<s:else>
		<a href="article!rerify.action?id=${entity.id}&artRandom=${entity.random}&iscms=${iscms}&isblog=${isblog}&cmsflag=1&categoryTmp.id=${categoryTmp.id}">通过审核</a>
		</s:else>
		</td>

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