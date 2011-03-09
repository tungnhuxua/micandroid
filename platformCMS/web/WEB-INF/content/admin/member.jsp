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
<script  src="${ctx}/js/table.js" type="text/javascript"></script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 会员管理</div>
	<div class="clear"></div>
</div>
<script language="javascript">
var cms_edit = {action:"member!input"};
var cms_delete = {action:"member!delete",msg:"您确定删除吗？"};
function _gotoPage(pageNo) {
	try{
		var tableForm = document.getElementById('tableForm');
		tableForm.pageNo.value = pageNo;
		tableForm.action="cms_list.do";
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
		alert('gotoPage(pageNo)方法出错或不存在');
	}
}
function _operate(op,id) {
	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('tableForm');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.id.value = id;
	tableForm.submit();
}
function _validateBatch() {
	var batchChecks = document.getElementsByName('ids');
	var hasChecked = false;
	for(var i=0; i<batchChecks.length; i++) {
		if(batchChecks[i].checked) {
			hasChecked = true;
			break;
		}
	}
	if(!hasChecked) {alert('请选择要操作的数据！')};
	return hasChecked;
}

function cms_checkBox(name, checked) {
	$("input[name='" + name + "']").attr("checked", checked);
}
$(document).ready(function(){
	$("#serarchUserName").validate();
});
</script>
<form action="member!searchUserName" id="serarchUserName" method="post">
<div>
 <table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
 <tbody class="pn-ltbody">
    <tr>
       <td>用户搜索：<select name="serarchType"><option value="1">登录名</option><option value="2">姓名</option></select><input type="text" name="loginname" class="required"/>&nbsp;&nbsp;&nbsp;<input type="submit"  value="搜索" /></td>
    </tr>
 </tbody>
 </table>
</div>

</form>
<form id="tableForm" method="post" onsubmit="return _validateBatch();">

<table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">

<thead class="pn-lthead">

<tr>
	<th width="20"><input id="allCheck" value="checkbox" onclick="cms_checkBox('ids',this.checked);" type="checkbox"></th>
	<th>ID</th>
	<th><a href="javascript:sort('loginName','asc')"><b>登录名</b></a></th>
	<th><a href="javascript:sort('name','asc')""><b>姓名</b></a></th>
	<th><a href="javascript:sort('email','asc')"><b>电邮</b></a></th>
	<th><b>角色</b></th>
	<th>操作</th>
</tr>
</thead>

<tbody class="pn-ltbody">
<s:iterator value="page.result">
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="${id}" type="checkbox"></td>
	<td>${id}</td>
	<td>${loginName}&nbsp;</td>
	<td>${name}&nbsp;</td>
	<td>${email}&nbsp;</td>
	<td>${roleNames}&nbsp;</td>
	<td class="pn-lopt"><a href="javascript:_operate(cms_edit,'${id }');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(cms_delete,'${id }');" class="pn-loperator">删除</a></td>
</tr>
</s:iterator>
</tbody>
</table>
<input name="id" type="hidden">

<div class="pn-lbopt">
	<input value="删除" onclick="if(confirm('您确定删除吗？')){this.form.action='member!deleteBatch?';return true;}else{return false;}" type="submit">
&nbsp; <input value="添加会员" onclick="this.form.action='member!input.action';this.form.onsubmit=null;" type="submit">
</div>
</form>
</div>
<div id="footer">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="member!searchUserName?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}&serarchType=${serarchType}&loginname=${loginname}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="member!searchUserName?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}&serarchType=${serarchType}&loginname=${loginname}">下一页</a>
	</s:if>
</div>
</body>
</html>