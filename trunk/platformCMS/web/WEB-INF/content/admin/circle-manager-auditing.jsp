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
	<div class="rpos">当前位置： 圈子管理 - 审核</div>
	<div class="clear"></div>
</div>
<script language="javascript">
var cms_circle_pass_auditing = {action:"circle-manager!passAuditing.action",msg:"确定审核通过吗？"};

function passAuditing(op,id,uid) {
	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('notAuditingCircleForm');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.circleId.value = id;
	tableForm.uid.value = uid;
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

<form id="notAuditingCircleForm" method="post" onsubmit="return _validateBatch();">
<table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
<thead class="pn-lthead">
<tr>
	<th>名称</th>
	<th>地区</th>
	<th>类型</th>
	<th>创建时间</th>
	<th>创建人</th>
	<th>操作</th>
	
</tr>
</thead>
<tbody class="pn-ltbody">
	<%java.util.List list = (java.util.List)request.getAttribute("list");
	    for(int i = 0;i<list.size();i++){
	      Object[] object = (Object[])list.get(i);%>
	<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
		<td align="center"><%=object[1]%></td>
		<td align="center"><%=object[5]%>-<%=object[6]%>-<%=object[7]%></td>
		<td align="center"><%=object[9]%></td>
		<td align="center"><%=object[2]%></td>
		<td align="center"><a href="${ctx}/blog/archives.action?tomember.id=<%=object[11]%>" target="_blank"><%=object[10]%></a></td>
		<!--  <td><input name="sortids" value="${id }" type="hidden"><input name="sorts" value="${sort}" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>-->
		<td class="pn-lopt"><a href="javascript:passAuditing(cms_circle_pass_auditing,'<%=object[0]%>','<%=object[11]%>');" class="pn-loperator">审核</a></td>
	</tr>
	<%} %>
</tbody>
</table>
<input name="circleId" type="hidden">
<input name="uid" type="hidden">

</form>
</div>
<div id="footer">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="circle-manager!notAuditing.action?page.pageNo=${page.prePage}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="circle-manager!notAuditing.action?page.pageNo=${page.nextPage}">下一页</a>
	</s:if>
</div>
</body>
</html>