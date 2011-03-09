<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="css/admin.css" rel="stylesheet" type="text/css">
<link href="css/theme.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 文章系统 - 栏目管理 - 列表</div>
	<div class="clear"></div>
</div>
<script language="javascript">
var Com_edit = {action:"Com_edit.do"};
var Com_delete = {action:"Com_delete.do",msg:"您确定删除吗？"};
function _gotoPage(pageNo) {
	try{
		var tableForm = document.getElementById('tableForm');
		tableForm.pageNo.value = pageNo;
		tableForm.action="Com_list.do";
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
</script>
<form id="tableForm" method="post" onsubmit="return _validateBatch();">
<table class="pn-ltable" border="0" cellpadding="0" cellspacing="1" width="100%">
<thead class="pn-lthead">
<tr>
	<th width="20"><input id="allCheck" value="checkbox" onclick="Pn.checkBox('ids',this.checked);" type="checkbox"></th>
	<th>ID</th>
	<th>名称</th>
	<th>路径</th>
	<th>排列顺序</th>
	<th>文档数量</th>
	<th>点击次数</th>
	<th>是否显示</th>
	<th>操作</th>
</tr>
</thead>
<tbody class="pn-ltbody">
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="2" type="checkbox"></td>
	<td>2</td>
	<td>新闻</td>
	<td>news</td>
	<td><input name="prioritys" value="1" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>0</td>
	<td>4</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="2" type="hidden"><a href="javascript:_operate(Com_edit,'2');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'2');" class="pn-loperator">删除</a></td>
</tr>
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="3" type="checkbox"></td>
	<td>3</td>
	<td>娱乐</td>
	<td>ent</td>
	<td><input name="prioritys" value="2" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>0</td>
	<td>2</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="3" type="hidden"><a href="javascript:_operate(Com_edit,'3');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'3');" class="pn-loperator">删除</a></td>
</tr>
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="4" type="checkbox"></td>
	<td>4</td>
	<td>财经</td>
	<td>money</td>
	<td><input name="prioritys" value="3" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>0</td>
	<td>2</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="4" type="hidden"><a href="javascript:_operate(Com_edit,'4');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'4');" class="pn-loperator">删除</a></td>
</tr>
<tr class="pn-lhover" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="5" type="checkbox"></td>
	<td>5</td>
	<td>房产</td>
	<td>house</td>
	<td><input name="prioritys" value="4" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>0</td>
	<td>1</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="5" type="hidden"><a href="javascript:_operate(Com_edit,'5');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'5');" class="pn-loperator">删除</a></td>
</tr>
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="6" type="checkbox"></td>
	<td>6</td>
	<td>体育</td>
	<td>sports</td>
	<td><input name="prioritys" value="5" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>0</td>
	<td>2</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="6" type="hidden"><a href="javascript:_operate(Com_edit,'6');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'6');" class="pn-loperator">删除</a></td>
</tr>
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="7" type="checkbox"></td>
	<td>7</td>
	<td>科技</td>
	<td>tech</td>
	<td><input name="prioritys" value="6" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>0</td>
	<td>2</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="7" type="hidden"><a href="javascript:_operate(Com_edit,'7');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'7');" class="pn-loperator">删除</a></td>
</tr>
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="8" type="checkbox"></td>
	<td>8</td>
	<td>手机</td>
	<td>mobile</td>
	<td><input name="prioritys" value="7" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>2</td>
	<td>2</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="8" type="hidden"><a href="javascript:_operate(Com_edit,'8');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'8');" class="pn-loperator">删除</a></td>
</tr>
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="9" type="checkbox"></td>
	<td>9</td>
	<td>汽车</td>
	<td>car</td>
	<td><input name="prioritys" value="8" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>2</td>
	<td>2</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="9" type="hidden"><a href="javascript:_operate(Com_edit,'9');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'9');" class="pn-loperator">删除</a></td>
</tr>
<tr class="" onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input name="ids" value="10" type="checkbox"></td>
	<td>10</td>
	<td>关于我们</td>
	<td>about</td>
	<td><input name="prioritys" value="9" size="7" onfocus="this.select();" onkeypress="if(event.keyCode==13){this.blur();return false;}" type="text"></td>
	<td>0</td>
	<td>0</td>
	<td>是</td>
	<td class="pn-lopt"><input name="wids" value="10" type="hidden"><a href="javascript:_operate(Com_edit,'10');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'10');" class="pn-loperator">删除</a></td>
</tr>
</tbody>
</table>
<input name="id" type="hidden">
<input name="pageNo" value="1" type="hidden">
<input name="pid" value="1" type="hidden">
<div class="pn-lbopt">
	<input value="删除" onclick="if(confirm('您确定删除吗？')){this.form.action='Com_delete.do';return true;}else{return false;}" type="submit">
&nbsp; <input value="保存排列顺序" onclick="this.form.action='Com_priorityUpdate.do';this.form.onsubmit=null;" type="submit">
</div>
</form>
<script language="javascript">
</script></div>
<div style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;"><iframe style="width: 186px; height: 199px;" src="Com_left_data/My97DatePicker.htm" border="0" frameborder="0" scrolling="no"></iframe></div></body>
</html>