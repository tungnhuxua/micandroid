<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/blog/blog_music.js"></script>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript">
	function musicAddFrom(id){
		
        var musicName = document.getElementById('musicName').value;
        var musicUri = document.getElementById('musicUri').value;
        var bool = true;
        if(musicName == ""){
            alert("请输入歌曲名！");
            bool = false;
         }
         var pattern = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/; 
         flag = pattern.test(musicUri); 
         
         if(!flag){ 
  		   alert("非法的音乐地址！"); 
  		   bool = false;
  	 	 } 
         if(bool){
      	  blogManagerAddMusic(musicName,musicUri);
         }
	}
	
	$(document).ready(function(){
		$("#addMusicManagerForm").validate();
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
				<div class="zhitibt">Music_音乐</div>
				<div class="zdyhaoyou">注_支持格式</div>
				<div class="dangannav">
					<ul>
						<li><a href="#">添加音乐</a></li>
						<li>_<a href="#">Add Music</a></li>
					</ul>
				</div>
				<form  method="post" id="addMusicManagerForm">
					<div class="gexingshezhi">
					  <table width="300" border="0" cellspacing="0">
	                    <tr>
	                      <td  width="60" height="25">_歌曲名</td>
	                      <td><input name="entity.musicName" type="text" id="musicName" class="gequ"/></td>
	                    </tr>
	                    <tr>
	                      <td>_连接地址</td>
	                      <td rowspan="3"><input name="entity.musicUri" id="musicUri" type="text" class="ljdizhi"/></td>
	                    </tr>
	                    
	                  </table>
					</div>
					<div class="zhiyebaocun" ><a href="javascript:musicAddFrom('addMusicManagerForm');">确认保存 Confirmed Save</a></div>
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