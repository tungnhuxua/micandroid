<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script>
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#oldpassword").focus();
			//为passwordform validate函数
			$("#passwordupdateform").validate({
			rules: { 
				oldpassword: { 
       				required: true, 
       				remote: "checkoldpassword.action?password="+encodeURIComponent('${oldpassword}')
   				},
    	  		password: {
    					required: true,
    					minlength:3
    			}, 
    			passwordConfirm: {
        			    required:true,
    					equalTo:"#password"
    			}
				},
				messages: {
					oldpassword: {
						remote: "密码错误！"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
				}
			});
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
				<div class="zhitibt">Information_个人档案</div>
				<div class="dangannav">
					<%@include file="/common/blog-content-nav.jsp" %>
				</div>
				<div class="mizhuyi">注_由6位以上18位以下的英文(区分大小写)、数字、特殊字符构成</div>
				<form action="${ctx }/blog/member-update.action" method="post" id="passwordupdateform">
				<div>请输入原始密码 </div>
				<input id="oldpassword" name="oldpassword" type="text" class="mimakuang" />
				<div class="jiumima" >新密码</div>
				<input id="password" name="password" type="password" class="mimakuang" />
				<div class="jiumima" >再次输入密码</div>
				<input name="passwordConfirm" type="password"  class="mimakuang"/>
				<div class="ckjifenguizhe" ><a href="javascript:document.getElementById('passwordupdateform').submit();">确认保存 Confirmed Save</a></div>
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