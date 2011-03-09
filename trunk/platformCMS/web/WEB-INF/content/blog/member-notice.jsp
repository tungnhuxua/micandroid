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
				<div class="mizhuyi">注_请根据内容的字数手动分行</div>
				<form action="member-info!save.action" method="post" id="membernoticeform">
				<div class="danganggkuang"><textarea name="entity.notice" cols="1" rows="10" class="grdanganshezhi">${entity.notice}</textarea></div>
				
				<input type="hidden" name="id" value="${entity.id }" /> 
				<input type="hidden"  name="entity.member.id" value="${member.id}" />
				<input type="hidden" name="action" value="membernoticeinfo"/>
				<div class="ckjifenguizhe" ><a href="javascript:document.getElementById('membernoticeform').submit();">确认保存 Confirmed Save</a></div>
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