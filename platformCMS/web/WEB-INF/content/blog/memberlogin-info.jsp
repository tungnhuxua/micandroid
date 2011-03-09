<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
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
				<div class="zdyhaoyou">注_请完善你的个人真实信息，以便在网站回访，赠送纪念品及网站活动时能够联系到你，<span style="color:#83C325;">(以下信息我们将不会公开)</span><br />
				完整填写可获10喔元！</div>
				<form action="${ctx }/blog/member-info!save.action" method="post" id="memberlogingform">
				<div class="gexingshezhi">
					<table width="400" border="0" cellspacing="0">
  <tr>
    <td height="25" colspan="2" valign="bottom">登陆邮箱</td>
    </tr>
  <tr>
    <td width="253" height="25"><input name="member.loginName" type="text"  value="${ member.loginName}" class="yx" disabled="disabled"/></td>
    <td width="143">不可修改</td>
  </tr>
  <tr>
    <td height="25" valign="bottom">真实姓名</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25"><input name="entity.realName" type="text" value="${ entity.realName}"  class="yx"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25" valign="bottom">身份证号码</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25"><input name="entity.cardcode" type="text"  value="${ entity.cardcode}" class="yx"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25" valign="bottom">QQ</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25"><input name="entity.qq" type="text"  value="${ entity.qq}" class="qq"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25" valign="bottom">MSN</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25"><input name="entity.msn" type="text" value="${ entity.msn}" class="yx"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25" valign="bottom">通讯地址</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25" colspan="2"><input name="entity.address" type="text" value="${ entity.address}" class="txdz"/></td>
    </tr>
  <tr>
    <td height="25" valign="bottom">邮编</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25"><input name="entity.postalcode" type="text"  value="${ entity.postalcode}" class="qq"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25" valign="bottom">电话</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="25"><input name="entity.tel" type="text"  value="${ entity.tel}" class="qq"/></td>
    <td>&nbsp;</td>
    <input type="hidden" name="id" value="${entity.id }" /> 
	<input type="hidden"  name="entity.member.id" value="${member.id}" />
	<input type="hidden" name="action" value="memberlogininfo"/>
  </tr>
</table>

                </div>
				<div class="zhiyebaocun" ><a href="javascript:document.getElementById('memberlogingform').submit();">确认保存 Confirmed Save</a></div>
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