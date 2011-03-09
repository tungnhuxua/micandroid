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
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	$("#tableForm").validate();
});
 
</script>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 广告系统 - 广告位管理 - 新增/修改</div>
	<div class="clear"></div>
</div>
<form id="tableForm" method="post" action="ads-location!save">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
	border="0">
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>所属广告位：</td>
		<td colspan="3" width="88%" class="pn-fcontent">${entity.parent.location }</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>广告位名：</td>
		<td colspan="3" width="88%" class="pn-fcontent">
			<input class="required" name="entity.location" value="${entity.location}" type="text">
		</td>
	</tr>
	<tr>
		<td colspan="4" class="pn-fbutton">
		<input name="id" value="${entity.id}" type="hidden">
		
		<input name="entity.parent.id" value="${entity.parent.id}" type="hidden">
		<input type="submit" value="保存" /></td>
	</tr>
</table>
</form>
</div>
<div style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;"><iframe style="width: 186px; height: 199px;" src="Com_left_data/My97DatePicker.htm" border="0" frameborder="0" scrolling="no"></iframe></div></body>
</html>