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
			
				<div class="gexingshezhi">
					
						<table width="650" height="auto" cellspacing="0" border="0">
							  <tbody>
							  
							  <tr>
								<td>发送成功</td>
							
							  </tr>
							  <tr>
							    <td> </td>
					      </tr>
							</tbody></table>

					
                </div>
				
				
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