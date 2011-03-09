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
				<div class="grdanganzhu">注_目前设计圈暂时提供100个职业选择，你也可以自行填写！</div>
				<div class="zhiye">职业一</div>
				<div class="grzhiye"><input name="zhiye" type="text" class="zy" /><a href="">点击选择</a></div>
				<div class="zhiye">职业二 </div>
				<div class="grzhiye"><input name="zhiye" type="text" class="zy" /><a href="">点击选择</a></div>
				<div class="zhiye">职业三</div>
				<div class="grzhiye"><input name="zhiye" type="text" class="zy" /><a href="">点击选择</a></div>
				<div class="zhiye">职业四</div>
				<div class="grzhiye"><input name="zhiye" type="text" class="zy" /><a href="">点击选择</a></div>
				<div class="zhiyebaocun" ><a href="">确认保存 Confirmed Save</a></div>
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