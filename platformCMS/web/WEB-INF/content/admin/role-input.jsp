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
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>

<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js"
	type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<script>
	$(document).ready( function() {
		//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#submitForm").validate();
		});
</script>
</head>
<body>

<div class="body-box">
<div class="rhead">
<div class="rpos">当前位置： 角色管理 - 新增/修改</div>
<div class="clear"></div>
</div>
<form method="post" action="role!save" id="submitForm">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
	border="0">

	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>角色名:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input type="text" name="name" size="40" value="${name}" class="required"/>
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>授权:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
			<div style="word-break:break-all;width:300px; overflow:auto; ">
				<s:checkboxlist name="checkedAuthIds"  list="allAuths"  listKey="id" listValue="displayName" theme="simple"/>
			</div>
		</td>
	</tr>

	<tr>
		<td colspan="4" class="pn-fbutton"><input type="hidden" name="id"
			value="${id }" /> <input type="submit" value="保存" /> &nbsp; <input
			type="reset" value="重置" /></td>

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