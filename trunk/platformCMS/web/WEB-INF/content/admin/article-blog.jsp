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
	<div class="rpos">当前位置： 文章管理 - <s:if test="categoryTmp.name!=null">${categoryTmp.name} - </s:if>列表</div>
	<div style="float: right"><s:actionmessage theme="mytheme" cssStyle="color: red;"/></div>
	<div class="clear"></div>
</div>
<script language="javascript">
var cms_edit = {action:"article!view.action"};
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
function _operate(op,id,rand) {
	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('tableForm');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.id.value = id;
	tableForm.artRandom.value = rand;
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
 <security:authorize ifAnyGranted="A_MANAGE_MEMBER,A_MANAGE_ROLE">
<form action="article!searchNews" id="searchNews" method="post">
<div>
 <table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
 <!--  
 <tbody class="pn-ltbody">
    <tr>
       <td>新闻搜索：
	       <input type="text"  name="newsContent" class="required"/>&nbsp;&nbsp;&nbsp;<input type="submit"  value="搜索" />
       </td>
    </tr>
 </tbody>-->
 </table>
</div>
</form>
</security:authorize>
<form id="tableForm" method="post" onsubmit="return _validateBatch();">
<table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
<thead class="pn-lthead">
<tr>
	<th width="20"><input id="allCheck" value="checkbox" onclick="cms_checkBox('ids',this.checked);" type="checkbox"></th>
	<th>ID</th>
	<th>名称</th>
	<th>推荐文章</th>
	<th>网站首页图片文章</th>
	<th>栏目首页图片文章</th>
<!--  	<th>排列顺序</th>-->
	<th>操作</th>
</tr>
</thead>
<tbody class="pn-ltbody">
<s:iterator value="page.result">
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="${id}" type="checkbox"></td>
	<td>${id}</td>
	<td>${title}</td>
	<td><s:if test="recommend>0"><span style="color: red;">是</span></s:if><s:else>否</s:else></td>
	<td><s:if test="siteHome>0"><span style="color: red;">是</span></s:if><s:else>否</s:else></td>
	<td><s:if test="categoryHome>0"><span style="color: red;">是</span></s:if><s:else>否</s:else></td>
	<!--  <td><input name="sortids" value="${id }" type="hidden"><input name="sorts" value="${sort}" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>-->
	<td class="pn-lopt"><a href="javascript:_operate(cms_edit,'${id }','${random }');" class="pn-loperator">查看</a></td>
</tr>
</s:iterator>
</tbody>
</table>
<input name="id" type="hidden">
<input id="artRandom" name="artRandom" type="hidden">
<input name="categoryTmp.id" value="${categoryTmp.id}" type="hidden">
<input name="iscms" value="${iscms }" type="hidden">
<input name="isblog" value="${isblog }" type="hidden"></input>
<div class="pn-lbopt">
	<s:if test="iscms == 1">
	<input value="取消审核" onclick="if(confirm('您确定取消吗？')){this.form.action='article!rerifyBatch?cmsflag=0';return true;}else{return false;}" type="submit">
	</s:if><s:else>
		<input value="通过审核" onclick="if(confirm('您确定通过吗？')){this.form.action='article!rerifyBatch?cmsflag=1';return true;}else{return false;}" type="submit">
	</s:else>
&nbsp; 

</div>
</form>
</div>
<div id="footer">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="article!bloglist.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}&categoryTmp.id=${categoryTmp.id}&iscms=${iscms}&isblog=${isblog}&newsContent=${newsContent }">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="article!bloglist.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}&categoryTmp.id=${categoryTmp.id}&iscms=${iscms}&isblog=${isblog}&newsContent=${newsContent }">下一页</a>
	</s:if>
</div>
</body>
</html>