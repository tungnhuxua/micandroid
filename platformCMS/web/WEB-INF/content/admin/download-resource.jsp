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
	<div class="rpos">当前位置： 下载资源管理 - <s:if test="resourceType.name!=null">${resourceType.name} - </s:if>列表</div>
	<div class="clear"></div>
</div>
<script language="javascript">
var cms_edit = {action:"download-resource!input"};
var cms_delete = {action:"download-resource!delete",msg:"您确定删除吗？"};
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
</script>

<form action="download-resource!searchResource" id="searchResource" method="post">
<div>
 <table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
 <tbody class="pn-ltbody">
    <tr>
       <td>下载搜索：
	       <input type="text"  name="resourceName" class="required"/>&nbsp;&nbsp;&nbsp;<input type="submit"  value="搜索" />
       </td>
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
	<th>标题</th>
	<th>类型</th>
	<th>大小</th>
	<th>是否推荐</th>
	<th>上传时间</th>
<!--  	<th>排列顺序</th>-->
	<th>操作</th>
</tr>
</thead>
<tbody class="pn-ltbody">
<s:iterator value="page.result">
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="${id}" type="checkbox"></td>
	<td>${id}</td>
	<td>${name}</td>
	<td>${type.name }</td>
	<td>${size}</td>
	<td><s:if test="recommend>0">是</s:if><s:else>否</s:else></td>
	<td><s:date name="createDate" format="yyyy-MM-dd" /></td>
	<!--  <td><input name="sortids" value="${id }" type="hidden"><input name="sorts" value="${sort}" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>-->
	<td class="pn-lopt"><a href="javascript:_operate(cms_edit,'${id }');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(cms_delete,'${id }');" class="pn-loperator">删除</a></td>
</tr>
</s:iterator>
</tbody>
</table>
<input name="id" type="hidden">
<input name="resourceType.id" value="${resourceType.id}" type="hidden">
<div class="pn-lbopt">
	<input value="删除" onclick="if(confirm('您确定删除吗？')){this.form.action='download-resource!deleteBatch';return true;}else{return false;}" type="submit">
&nbsp; <input value="添加资源" onclick="this.form.action='download-resource!input.action';this.form.onsubmit=null;" type="submit">

</div>
</form>
</div>
<div id="footer">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="article.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}&resourceType.id=${resourceType.id}&resourceName=${resourceName }">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="article.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}&resourceType.id=${resourceType.id}&resourceName=${resourceName }">下一页</a>
	</s:if>
</div>
</body>
</html>