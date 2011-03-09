<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/ui.all.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.core.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${ctx }/js/area.js"></script>

<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
        
	<script>
		function submitForm(id){
			document.getElementById(id).submit();
		}
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#enraalName").focus();
			//为inputForm注册validate函数
			$("#memberInfoSave").validate();
		});
  
	</script>
		<script language="javascript">
var cms_delete = {action:"message!sendDelete.action",msg:"您确定删除吗？"};
var cms_delete_batch = {action:"message!deleteSendBatch.action",msg:"您确定删除吗？"};

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
	var batchChecks = document.getElementsByName('mids');
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

function _deleteBatch(op){

	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('tableForm');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.submit();
}

function cms_checkBox(name, checked) {
	$("input[name='" + name + "']").attr("checked", checked);
}
</script>
</head>
<body>
<div class="container">	
		<div class="header">
			<%@include file="/common/blog-header.jsp" %>
	</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
			<div class="mainbh">
				<div class="leftsidebar">
					<%@include file="/common/blog-content-left.jsp" %>
				</div>
				<div class="zhuti">
				<div class="zhitibt">Mailbox_信箱_(共${page.totalCount}条信息)</div>
				<div class="dangannav">
					<ul>
						<%@include file="/common/blog-message-nav.jsp" %>
					</ul>
				</div>
				<form method="post" id="tableForm">
				<div class="quangxuan"><input type="checkbox" onclick="cms_checkBox('mids',this.checked);"/> 全选    <a href="javascript:_deleteBatch(cms_delete_batch);">删除</a></div>
				<div class="zdyhaoyou">_已发信息_(共${page.totalCount}条信息)</div>
				<div class="gexingshezhi">
				  <table width="600" cellspacing="5" border="0" class="miaobian">
                    <tbody>
                     <s:iterator value="page.result">
                    <tr>
                      <td width="18" valign="top" rowspan="2"><input type="checkbox" name="mids" value="${id}"/></td>
                      <td width="40" class="shouxingxikuang" rowspan="2"> </td>
                      <td width="7" valign="top"> </td>
                      <td width="56" valign="top">${toMember.name }</td>
                      <td width="171" valign="top"><a href="${ctx }/blog/message!senddetail.action?id=${id}">${title }</a></td>
                      <td width="166" valign="top"><s:date name="createDate" format="yyyy-MM-dd hh:mm:ss" /></td>
                      <td width="88" valign="top"><a href="javascript:_operate(cms_delete,'${id }');">删除</a></td>
                    </tr>
                    <tr>
                      <td colspan="5"> </td>
                    </tr>
                    </s:iterator>
                  </tbody></table>
				</div><input name="id" type="hidden">
				</form>
				<div style="padding-top: 30px;" class="qyzhongjianyeshu">
						<ul>
							第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="${ctx }/blog/message!send.action?page.pageNo=${page.prePage}">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="${ctx }/blog/message!send.action?page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="${ctx }/blog/message!send.action?page.pageNo=${page.nextPage}">下一页</a>
						</s:if>
						</ul>
					</div>
				<!--  	<div class="qyzhongjianyeshu">_共84个圈子</div>-->
				</div>
		</div>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>