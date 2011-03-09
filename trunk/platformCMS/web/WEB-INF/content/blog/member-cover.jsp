<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>

<script>

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

		//剪裁图片
		function imgCut() {
			if($('#uploadimgpath').val()=='') {
				alert("请先上传图片，再剪裁");
				return; 
			}
			var url = "member-cover-imgcut.action?imagePath="+ $("#uploadimgpath").val();
			window.open(url,"imgcut","height=1000, width=1024, top=0, left=0, toolbar=no, menubar=no, scrollbars=1, resizable=yes,location=no, status=no");
		}
		
</script>
<script type="text/javascript">
function submitForm(id){
	document.getElementById(id).submit();
}
</script>
</head>
<body>
<form id="uploadForm" action="${ctx }/blog/upload!uploadCover.action" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContent"></span>
<input name="fileGroup" value="cap" type="hidden"/>
<input name="userCapDir" value="${member.id}" type="hidden" />
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
				<div class="grdanganbt">Cover_封面_玩出你自己的味道</div>
				<div class="grdangan">_设置你的设计圈bolg封面，封面尺寸多大都没问题，想没有也OK！</div>
				 <div class="zdyhaoyou">注_图片支持格式(jpg,gif,png),图片尺寸宽度建议为960像素，高度不限</div>
				<form action="member-cover!memberCoverSave.action" method="post" id="membercoverForm">
				<div class="gexingshezhi">
					<div class="beijingyanseb">_背景图片设置_图片最大可上载2M
					</div>
					<div class="beijingyanse">
						<ul >
							<li ><span id="ufc"><input type="file" id="uploadFile" size="20" /></span></li>
							<li ><input type="button" value="上传" onclick="upload();" /></li>
						</ul>
						<ul class="fmweizhi">
							<li class="jianju"><input <s:if test="member.info.coverSetup == 1 ">checked="checked"</s:if> name="entity.coverSetup" type="radio" value="1" />居左</li>
							<li class="jianju"><input <s:if test="member.info.coverSetup == 2 ">checked="checked"</s:if> name="entity.coverSetup" type="radio" value="2" />居中</li>
							<li class="jianju"><input <s:if test="member.info.coverSetup == 3 ">checked="checked"</s:if> name="entity.coverSetup" type="radio" value="3" />居右</li>
							<li class="jianju"><input <s:if test="member.info.coverSetup == 0 ">checked="checked"</s:if>  name="entity.coverSetup" type="radio" value="0" />无封面</li>
						</ul>
					</div>
					<div class="yulan">
					<ul>
						
						<li><a href="javascript:imgCut();">预览 preview</a></li>
						<li><a href="member-home.action">取消 Cancel</a></li>
						<li><img id="preImg" alt="预览区"
							src="${ctx }${entity.coverImg}"
							style="background-color: #CCCCCC; border: 1px solid #333;height: 150px;width: 150px;" width="150" height="150"/></li>
					</ul>
				</div>
                </div>
                
				<div class="zhiyebaocun" ><a href="javascript:submitForm('membercoverForm');">确认保存 Confirmed Save</a></div>
				<input type="hidden"  id="imageUri" name="entity.coverImg" value="${entity.coverImg}" />
				<input type="hidden" name="id" value="${entity.id }" /> 
				<input type="hidden"  name="entity.member.id" value="${member.id}" />
				<input type="hidden" value="${entity.coverImg}" id="uploadimgpath" size="20" />
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