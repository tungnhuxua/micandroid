<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script src="${ctx }/js/jquery.min.js"></script>
		<script src="${ctx }/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="${ctx }/css/jquery.Jcrop.css" type="text/css" />
		<script language="Javascript">


			//上传图片
			function upload() {	
				var of = $('#uploadFile');
				//检查是否选择了图片
				if(of.val()=='') {
					alert('请选择要上传的图片');
					return;
				}
				//将file移动至上传表单
				$('#fileContent').empty();
				$('#fileContent').append(of);
				//复制一个file放至原处
				$('#ufc').append(of.clone());
				//修改属性
				of.attr('id','');
				of.attr('name','upload');
				
				$('#uploadForm').submit();
			}
		</script>
</head>
<body>
<form id="uploadForm" action="upload!uploadCircle.action" method="post" enctype="multipart/form-data" target="hiddenIframe"  style="display:none;width:0px;height:0px;">
	<span id="fileContent"></span>
	<input name="fileGroup" value="circle" type="hidden" />
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>

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
				<div class="zhitibt">request_圈子申请</div>
				<div class="dangannav">
				<ul>
						<li>_<a href="member-addcircle.action">圈子信息</a></li>
						<li>_<a href="#" style="color: rgb(131, 195, 37);">圈子图片</a></li>
					</ul>
				</div>
				
				<form action="member-info!save.action" method="post" id="memberInfoSave">
				<div class="gexingshezhi">
					<div class="beijingyanseb">_背景图片设置_图片最大可上载2M
					</div>
						<div class="beijingyanse">
								<span id="ufc"><input type="file" id="uploadFile" size="20" /></span>
							   <input type="button" value="上传" onclick="upload();" />
						</div>
					<div id="capdiv" style="float: left;width: 150px;height: 150px;">
					
					<img id="capimage"  src="${ctx }/images/zhchepic.gif" width="150" height="150" />
					</div>
					
                </div>
				<input type="hidden" name="headPortraitUri" value="memberpicture"/>
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