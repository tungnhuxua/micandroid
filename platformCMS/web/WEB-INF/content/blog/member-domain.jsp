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
		function submitForm(id){
			document.getElementById(id).submit();
		}
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#domain").focus();
			//为inputForm注册validate函数
			$("#memberInfoSave").validate({
				rules: {
					'entity.domain':{
						isNumberOrLetter:true,
						isNotNumber:true,
						minlength:3,
						maxlength:20,
						remote:"checkdomain.action?oldDomain=${entity.domain}"
					}
				},
				messages: {
					'entity.domain':{
						remote: "域名已存在"
					}
				}
				
			});
		});
		 // 字符验证   
		 jQuery.validator.addMethod("isNumberOrLetter", function(value, element) {   
		   return this.optional(element) || (/^[0-9a-zA-Z]+$/.test(value));   
		 }, "只能包括英文字母和数字");

		 jQuery.validator.addMethod("isNotNumber", function(value, element) {   
			   return this.optional(element) || (!/^[0-9]*$/.test(value));   
			 }, "不能全为数字");
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
				<div class="zhuyi">注_域名由3-20位的0-9 a-z_或-字符组成,且不能全为数字,请谨慎修改。</div>
				<div class="shezhi">
					<div class="xiangpian">
					<s:if test="member.info.headPortraitUri != null && member.info.headPortraitUri != ''">
					   <img src="${ctx }${member.info.headPortraitUri }" width="59" height="60"/>
					</s:if><s:else>
					<img src="${ctx }/images/baisexiaonian.jpg" width="59" height="60"/>
					</s:else></div>
					<div class="xiangxi">
						<ul>
							<li><a href="#">_${entity.member.name }</a></li>
							<li><a href="#">_${entity.member.loginName}</a></li>
							<li><a href="#">_ID:${entity.member.id }</a></li>
						</ul>
					</div>
				</div>
				<form id="memberInfoSave" action="${ctx }/blog/member-update-domain" method="post">
				<div class="yuming">www.ooowo.com/<input id="domain" name="entity.domain" type="text" class="ymkuang" value="${entity.domain}"/></div>
				
				<input type="hidden" name="id" value="${entity.id }" /> 
				<input type="hidden"  name="entity.member.id" value="${member.id}" />
				<input type="hidden" name="action" value="/blog/member-domain"/>
				<div class="baocun"><input type="submit" value="确认保存 Confirmed Save" style="color:#83C325;"/></div>
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