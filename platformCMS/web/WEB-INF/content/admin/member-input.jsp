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
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>

	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
			rules: { 
				loginName: { 
       				required: true, 
       				remote: "member!checkLoginName.action?orgLoginName="+encodeURIComponent('${loginName}')
   				},
    	 		name: {
  					  required : true,
  					  userName : true
   	    	 	},
    	  		password: {
    					required: true,
    					minlength:3
    			}, 
    			passwordConfirm: {
    					equalTo:"#password"
    			},
    			email:"email"
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
				}
			});
		});

		jQuery.validator.addMethod("userName", function(value, element) {    
			  return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);    
		}, "用户名只能包括中文字、英文字母、数字和下划线");    
	</script>
</head>
<body>


<div class="body-box">
<div class="rhead">
<div class="rpos">当前位置： 会员管理 - 新增/修改</div>
<div class="clear"></div>
</div>
<form method="post" action="member!save" id="inputForm">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
	border="0">

	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>登录名:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<s:if test="id>0">
		<input disabled="disabled" type="text" name="loginName" size="40" id="loginName" value="${loginName}" />
		</s:if><s:else>
		<input type="text" name="loginName" size="40" id="loginName" value="${loginName}" />
		</s:else>
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>用户名:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
			<input type="text" name="name" size="40" value="${name}" />
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>密码:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input type="password" id="password" name="password" size="40" value="${password}" />
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired">*</span>确认密码:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input type="password" name="passwordConfirm" size="40" value="${password}" />
		</td>
	</tr>
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>邮箱:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<input type="text" name="email" size="40" value="${email}" />
		</td>
	</tr>
	
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>大侠:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<div style="word-break:break-all;width:250px; overflow:auto; ">
				<s:select list="#{'3':'_色女郎_Se Girl','4':'_视觉_Visual','5':'_艺术_Art','6':'_传媒_Media','7':'_摄影_Photography','8':'_空间_space','9':'_策略文案_Plan'}" name="typeTmp.id" theme="simple" headerKey="0" headerValue="非大侠" value="memberType.id"></s:select>
			</div>
		</td>
	</tr>
	
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span> 专栏:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<s:checkbox name="colu" value="colPce" fieldValue="true" theme="simple"/>
		</td>
	</tr>
	
	<tr>
		<td width="12%" class="pn-flabel pn-flabel-h"><span
			class="pn-frequired"></span>角色:</td>
		<td colspan="3" width="88%" class="pn-fcontent">
		<div style="word-break:break-all;width:250px; overflow:auto; ">
				<s:checkboxlist name="checkedRoleIds"  list="allRoles"  listKey="id" listValue="name" theme="simple"/>
			</div>
		</td>
	</tr>

	<tr>
		<td colspan="4" class="pn-fbutton"><input type="hidden" name="id"
			value="${id }" /> 
			<input type="submit" value="保存" /> &nbsp;
		<input type="reset" value="重置" /></td>

	</tr>
</table>
</form>

</div>
<div
	style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;"><iframe
	style="width: 186px; height: 199px;"
	src="Com_left_data/My97DatePicker.htm" border="0" frameborder="0"
	scrolling="no"></iframe></div>
</body>
</html>