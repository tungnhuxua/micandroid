<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>用户档案</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/ui.all.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/fckeditor/fckeditor.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.core.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.datepicker-zh-CN.js"></script>

<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
  
    <script type="text/javascript">
			$(function() {
				$.datepicker.setDefaults($.extend({showMonthAfterYear: false}, $.datepicker.regional['zh-CN']));
				$("#datepicker").datepicker($.datepicker.regional['zh-CN']);
			});
	</script>
	<script>
		function submitForm(id){
			document.getElementById(id).submit();
		}
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#articletitle").focus();
			//为inputForm注册validate函数
			$("#memberInfoSave").validate();
		});


		//上传图片
		function uploadF() {	
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
		//获取日志或秀场的分类
		function systemcategory(){
			var url = "system-category.action";
			var cateId = $("#categoryid").val();
			var data = {'cateId':cateId};
			jQuery.post(url,data,function(redata){
					$("#systecategory").replaceWith(redata);
				});
		}

		//剪裁图片
		function imgCut() {
			if($('#uploadimgpath').val()=='') {
				alert("请先上传图片，再剪裁");
				return;
			}
			var url = "article-input-imgcut.action?imagePath="+ $("#uploadimgpath").val();
			window.open(url,"imgcut","height=550, width=1000, top=0, left=0, toolbar=no, menubar=no, scrollbars=auto, resizable=yes,location=no, status=no");
		}


		var fileNum = 1;

		function addImageFile(){
			if(fileNum>9){
					alert("最多上传10张图片");
					return ;
				}
				var file = "<tr><td><input type=\"file\"  size=\"20\" name=\"upload\"/>&nbsp;<input style=\"cursor:pointer;\" type=\"button\" value=\"删除\" onclick=\"fileDelete(this);\" /></td></tr>";
				$("#filesTable").append(file);
				fileNum++;
			}
		function fileDelete(id){
				$(id).parent().parent().remove();
				fileNum--;
			}


		//上传多图片
		function uploadFiles() {	
			var of = $('#filesTable');
			//检查是否选择了图片
			if(of.children().children().children().children().val()=='') {
				alert('请选择要上传的图片');
				return;
			}
			//将file移动至上传表单
			$('#filesContent').empty();
			$('#filesContent').append(of);

			var filesTable = "<table id=\"filesTable\"><tr><td><input type=\"file\"  size=\"20\" name=\"upload\"/>&nbsp;<input type=\"button\" value=\"删除\" onclick=\"fileDelete(this);\" style=\"cursor:pointer;\"/></td></tr></table>";
			$('#filesSpan').html(filesTable);
			fileNum = 1;
			
			$('#uploadFilesForm').submit();
		}

		function fckImg(files){
			$('#filesContent').empty();
			var imgs = files.split(',');
			var len = imgs.length-1;
			for(i = 0;i<len;i++){
				var oEditor = FCKeditorAPI.GetInstance('entity.content') ;
				var html = "<img  src='${ctx }"+imgs[i]+"'/>";
				oEditor.InsertHtml(html);
			}
		}
	</script>
</head>
<body>
<form id="uploadForm" action="${ctx }/admin/upload!upload.action" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContent"></span>
<input name="fileGroup" value="article" type="hidden"/>
</form>

<form id="uploadFilesForm" action="${ctx }/blog/files-upload!upload.action" method="post" enctype="multipart/form-data" target="hiddenIframe2" style="display:none;width:0px;height:0px;">
<span id="filesContent"></span>
<input name="fileGroup" value="article" type="hidden"/>
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>
<iframe name="hiddenIframe2" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>
<div class="container">	
	<div class="header">
			<%@include file="/common/blog-header.jsp" %>
	</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
			<div class="bqmainbh">
				<div class="jilu">
					<ul>
						<li><a href="">记录</a></li>
						<li><a href="">_分享</a></li>
					</ul>
				</div>
				<div class="jilu">精彩的作品还有可能出现在设计圈的首页，同时获得奖杯哦……</div>
				<form method="post" action="${ctx }/blog/article!save.action">
				<div style="padding-top: 30px;" class="jilu">
				  <table cellspacing="0" border="0" width="700">
                    <tbody><tr>
                      <td height="30" width="72" valign="top">_发布时间_</td>
                      <td valign="top" colspan="3">
                      <input type="text" id="datepicker" class="fbshijian dateISO" name="entity.amendDate"  value="<s:date name="entity.amendDate" format="yyyy-MM-dd"/>"/>&nbsp;
                      </td>
                      <td width="237"> </td>
                    </tr>
                    <tr>
                      <td height="30" valign="top">_标题_</td>
                      <td valign="top" colspan="3">
                      <input id="articletitle" type="text" maxlength="255" value="${entity.title }" name="entity.title" class="fbztimu required" size="80" maxlength="255" />
                      </td>
                      <td> </td>
                    </tr>
                    <tr>
                      <td height="25" valign="top">_分类_</td>
                      <td width="122" valign="top">
                      <s:select id="categoryid" name="cateId" list="#{'54':'日志','56':'秀场'}" value="cateId" theme="simple" onchange="systemcategory();"></s:select>
                      </td>
                      
                      <td width="130" valign="top">
                      <s:select id="systecategory" name="categoryTmp.id" list="categoryList" listKey="id" listValue="name" headerKey="-1" headerValue="请选择系统分类" value="entity.category.id" theme="simple"></s:select>
                         </td>
                         
                      <td width="129" valign="top">
                      
                      <s:if test="cateId==54">
                      <s:select id="logcategory" name="logCategoryT.id" list="logCate" listKey="id" listValue="name" headerKey="-1" headerValue="请选择分类" value="entity.logCategory.id" theme="simple"></s:select>
                                    </s:if><s:else>
                      <s:select id="showcategory" name="showCategoryT.id" list="showCate" listKey="id" listValue="name" headerKey="-1" headerValue="请选择分类" value="entity.showCategory.id" theme="simple"></s:select>              
                                 </s:else>    </td>
                      
                      <td> </td>
                    </tr>
                    <tr>
                      <td height="40" valign="top"> </td>
                      <td valign="top">_这里分日志和秀场</td>
                      <td valign="top">_这里出现相应的栏目</td>
                      <td valign="top" colspan="2">_这里用户自己设置的栏目</td>
                    </tr>
                    <tr>
                      <td height="25">_内容_</td>
                      <td bgcolor="#0c0c0e" class="ziyanse" colspan="4" width="628"></td>
                    </tr>
                    <tr>
                      <td height="25"></td>
                      <td  colspan="4" width="628">
                      <script type="text/javascript">
							<!--
							// Automatically calculates the editor base path based on the _samples directory.
							// This is usefull only for these samples. A real application should use something like this:
							// oFCKeditor.BasePath = '/fckeditor/' ;	// '/fckeditor/' is the default value.
							//var sBasePath = document.location.href.substring(0,document.location.href.lastIndexOf('_samples')) ;
							
							var oFCKeditor = new FCKeditor('entity.content') ;
							oFCKeditor.BasePath	= '${ctx}/fckeditor/';
							oFCKeditor.ToolbarSet = 'BlogToolbar';
							oFCKeditor.Height = 300;
							oFCKeditor.Value = '${entity.content}';
							oFCKeditor.Create();
							//-->
					  </script>
                      </td>
                    </tr>
                    <tr>
                      <td height="30" valign="top">_标签TAG_</td>
                      <td valign="top" colspan="3">
                      <input type="text" maxlength="255" value="${entity.tag }" name="tagStr"  size="80" maxlength="255" class="fbztimu"/>
                      (标签以逗号"，"分割)
                      </td>
                      <td> </td>
                    </tr>
                    <tr>
                      <td height="30" valign="top">_上传图片</td>
                      <td valign="top" colspan="4">
                      <span id="filesSpan">
                      	<table id="filesTable">
                      		<tr><td><input type="file"  size="20" name="upload"/>&nbsp;<input type="button" value="删除" onclick="fileDelete(this);" style="cursor:pointer;"/></td></tr>
                      	</table>
                      </span>
                      </td>
                      
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="2"><a onclick="addImageFile();" style="cursor:pointer;">_继续添加附件</a></td>
                      <td valign="top" colspan="2"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="2"><a class="STYLE2" style="cursor:pointer;" onclick="uploadFiles();">_上传图片</a></td>
                      <td valign="top" colspan="2"> </td>
                    </tr>
                    <tr>
						<td height="30" valign="top">_缩略图_</td>
						<td valign="top" colspan="3">
						<span id="ufc"><input type="file" id="uploadFile" size="20" /></span>
						<input type="button" value="上传" onclick="uploadF();" />&nbsp;<input type="hidden" value="${entity.imageUri}" id="uploadimgpath" size="20" /><input type="button" value="剪裁" onclick="imgCut();" /></td>
						<td colspan="2" class="pn-fbutton"><img id="preImg" alt="预览区"
							src="${ctx }${entity.imageUri}"
							style="background-color: #CCCCCC; border: 1px solid #333;height: 150px;width: 150px;" width="150" height="150"/></td>
					</tr>
                    <!--  
                    <tr>
                      <td height="40" valign="top">_发送到圈子</td>
                      <td valign="top" colspan="3"><input type="text" class="fbztimu" name="fbbiaoti"/></td>
                      <td> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top">_上传图片</td>
                      <td valign="top" colspan="2"><input type="text" class="fbztimu" name="fbbiaoti"/></td>
                      <td valign="top"><input type="button" class="liul" value="浏览" name="ll"/></td>
                      <td valign="top"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="2"><a href="">_继续添加附件</a></td>
                      <td valign="top" colspan="2"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="2"><a class="STYLE2" href="">_上传图片</a></td>
                      <td valign="top" colspan="2"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top">_插入视频</td>
                      <td valign="top" colspan="2"><input type="text" class="fbztimu" name="fbbiaoti2"/></td>
                      <td valign="top">    支持的视频网站</td>
                      <td valign="top"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="2"><a href="">_继续添加视频</a></td>
                      <td valign="top" colspan="2"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="2"><a class="STYLE2" href="">确定引用该视频</a></td>
                      <td valign="top" colspan="2"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top">_制作封面</td>
                      <td valign="top" colspan="2"><input type="text" class="fbztimu" name="fbbiaoti"/></td>
                      <td valign="top"><input type="button" class="liul" value="浏览" name="ll"/></td>
                      <td valign="top"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="2"><a class="STYLE2" href="">上传图片</a></td>
                      <td valign="top" colspan="2"> </td>
                    </tr>
					<tr>
                      <td height="30" valign="top"> </td>
                      <td valign="top" colspan="4"><img src="images/fbpic.gif"/></td>
                    </tr>
					<tr>
                      <td height="40"> </td>
                      <td><a href="">_点击预览_Preview</a></td>
                      <td><a href="">  _取消_Cancel</a></td>
                      <td colspan="2"><a href="">_完成_Achieve</a></td>
                    </tr>-->
                  </tbody></table>
				</div>
				<input type="hidden" name="id" 
					value="${entity.id }" /> 
					<input type="hidden"  id="imageUri" name="entity.imageUri" 
					value="${entity.imageUri}" />
					<input type="hidden" id="imageLink" name="entity.imageLink" 
					value="${entity.imageLink}" />
					<input type="hidden" name="artRandom" value="${entity.random}"/>
					<s:if test="entity.iscms == null"><input type="hidden" name="entity.iscms" value="0"/></s:if>
				<div class="queren"><input type="submit" value="_保存发布_Save Releases"/></div>
				</form>
			</div>
			<div class="footer">
				<%@include file="/common/blog-content-footer.jsp" %>
			</div>
		</div>
	</div>
</div>
</body>
</html>