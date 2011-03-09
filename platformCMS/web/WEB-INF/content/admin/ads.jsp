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

<script src="../js/jquery.js" type="text/javascript"></script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 广告管理 - <s:if test="location.location!=null">${location.location} - </s:if>列表</div>
	<div class="clear"></div>
</div>
<script language="javascript">
var cms_edit = {action:"ads!input"};
var cms_delete = {action:"ads!delete?entity.parent.id=${entity.id}",msg:"您确定删除吗？"};

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
</script>
<form id="tableForm" method="post" onsubmit="return _validateBatch();">
<table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
<thead class="pn-lthead">
<tr>
	<th width="20"><input id="allCheck" value="checkbox" onclick="cms_checkBox('ids',this.checked);" type="checkbox"></th>
	<th>ID</th>
	<th>名称</th>
	<th>描述</th>
	<th>操作</th>
</tr>
</thead>
<tbody class="pn-ltbody">
<s:iterator value="page.result">
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="${id}" type="checkbox"></td>
	<td>${id}</td>
	<td>${name}</td>
	<td>${description}</td>
	<td class="pn-lopt"><a href="javascript:_operate(cms_edit,'${id }');" class="pn-loperator">修改</a><!-- ┆<a href="javascript:_operate(cms_delete,'${id }');" class="pn-loperator">删除</a> --></td>
</tr>
</s:iterator>
</tbody>
</table>
<input name="id" type="hidden">
<input name="location.id" value="${location.id }" type="hidden">
<div class="pn-lbopt">
	<!--<input value="删除" onclick="if(confirm('您确定删除吗？')){this.form.action='ads!deleteBatch?entity.parent.id=${entity.id}';return true;}else{return false;}" type="submit">-->
&nbsp; <!--<input value="添加广告" onclick="this.form.action='ads!input.action';this.form.onsubmit=null;" type="submit">-->
</div>
</form>
</div>
<div id="footer">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="ads.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}&location.id=${location.id}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="ads.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}&location.id=${location.id}">下一页</a>
	</s:if>
</div>
</body>
</html>