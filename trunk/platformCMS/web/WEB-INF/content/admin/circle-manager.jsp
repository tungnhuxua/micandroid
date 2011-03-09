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
	<div class="rpos">当前位置： 圈子管理  </div>
	<div class="clear"></div>
</div>
<script language="javascript">
var circle_commend= {action:"circle-manager!isCommend.action"};
var cms_del= {action:"circle-manager!delete.action",msg:"确定要删除该圈子吗？"};

function isCommend(op,commend,circleId) {

	var tableForm = document.getElementById('circleFrom');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.commend.value = commend;
	tableForm.circleId.value = circleId;
	tableForm.submit();
}

function del(op,id) {
	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('circleFrom');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.circleId.value = id;
	tableForm.submit();
}

</script>

<form action="circle-manager.action?state=2" id="searchResource" method="post">
<div>
 <table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
 <tbody class="pn-ltbody">
    <tr>
       <td>圈子搜索：
	       <input type="text"  name="circleName" class="required"/>&nbsp;&nbsp;&nbsp;<input type="submit"  value="搜索" />
       </td>
    </tr>
 </tbody>
 </table>
</div>
</form>

<form id="circleFrom" method="post" onsubmit="return _validateBatch();">
<table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
<thead class="pn-lthead">
<tr>
	<th>名称</th>
	<th>地区</th>
	<th>类型</th>
	<th>总人数</th>
	<th>创建时间</th>
	<th>是否推荐</th>
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
		<td align="center"><%=object[10]%></td>
		<td align="center"><%=object[2]%></td>
		<td align="center"><%if(object[11].toString().equals("1")){%><a href="javascript:isCommend(circle_commend,'<%=object[11]%>','<%=object[0]%>');">是</a><%}else{%><a href="javascript:isCommend(circle_commend,'<%=object[11]%>','<%=object[0]%>');">否</a><%} %></td>
		<!--  <td><input name="sortids" value="${id }" type="hidden"><input name="sorts" value="${sort}" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>-->
		<td class="pn-lopt"><a href="javascript:del(cms_del,'<%=object[0]%>');" class="pn-loperator">删除</a></td>
	</tr>
	<%} %>
</tbody>
</table>
<input name="commend" type="hidden">
<input name="circleId" type="hidden">
</form>
</div>
<div id="footer">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="circle-manager.action?page.pageNo=${page.prePage}&&circleTypeId=${circleTypeId}&&state=${state}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="circle-manager.action?page.pageNo=${page.nextPage}&&circleTypeId=${circleTypeId}&&state=${state}">下一页</a>
	</s:if>
</div>
</body>
</html>