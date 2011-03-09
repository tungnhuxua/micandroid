<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>member.name}'s blog</title>
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
			$("#ids").focus();
			//为inputForm注册validate函数
			$("#messagebox").validate();
		});
  
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
				<div class="grdanganbt">Mailbox_信箱_(共${count }条信息)</div>
				<div class="grdangan">
					<ul>
						<%@include file="/common/blog-message-nav.jsp" %>
					</ul>
				</div>
				<form method="post" action="${ctx }/blog/message!save.action" id="messagebox">
				<div class="gexingshezhi">
					
						<table width="650" height="auto" cellspacing="0" border="0">
							  <tbody><tr>
								<td width="58" height="25">_收件人</td>
								<td width="210"><input type="text" class="xingxiangbt required" name="ids" id="ids" value="${receiveMessage.fromMember.loginName }"/></td>
								<td width="376">请输入用户ID，多用户请用逗号","间隔从好友里添加</td>
							  </tr>
							  <tr>
								<td height="25">_标题</td>
								<td width="210"><input type="text" class="xingxiangbt required" name="title" value="${receiveMessage.title }"/></td>
								<td>标题限制在20字</td>
							  </tr>
							  <tr>
								<td>_消息内容</td>
								<td rowspan="27" colspan="2"><textarea class="xxnr required" rows="26" cols="1" name="content"></textarea></td>
							  </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							  <tr>
							    <td> </td>
					      </tr>
							</tbody></table>

					
                </div>
				<div class="zhiyebaocun">
				<input type="submit" style="color: rgb(131, 195, 37);" value="_保存发布_Save Releases"/>
				</div>
				</form>
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