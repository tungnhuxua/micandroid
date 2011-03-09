<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/blog/blog_link.js"></script>
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>

<script type="text/javascript" src="../js/ui.core.js"></script>
<script type="text/javascript" src="../js/ui.dialog.js"></script>
<script type="text/javascript" src="../js/jquery.bgiframe.min.js"></script>
<link type="text/css" href="../css/ui.all.css" rel="stylesheet" />

<script language="javascript">

	var link_move = {action:"member-link!linkMove.action"};
	var link_delete = {action:"member-link!deleteLink.action",msg:"确定要删除链接吗？"};
	
	function linkMove(op,id,moveType){
		var tableForm = document.getElementById('linkListFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.move.value = moveType;
		tableForm.submit();
	}
	
	function delLink(op,id) {
		if(op.msg && !confirm(op.msg)) {
			return;
		}
		var tableForm = document.getElementById('linkListFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.submit();
	}
   function addLinkFrom(){
	  var linkName = document.getElementById('linkName').value;
      var linkUri = document.getElementById('linkUri').value;
      var linkInfo = document.getElementById('linkInfo').value;
      bool = true;
      if(linkName==""){alert("请输入链接名称");bool = false;}
      var pattern = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/; 
      flag = pattern.test(linkUri); 
      
      if(!flag){ 
		   alert("非法的链接地址！"); 
		   bool = false;
	 	 }
      if(bool){blogManagerAddLink(linkName,linkUri,linkInfo);}
 	 
  }

</script>

</head>
<body>
<%@include file="/common/blog/link-modi.jsp" %>
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
			<div class="zhitibt">LINK_链接(共  <span style="color: #91BF29; font-size: 18px;">${page.totalCount} </span>   个链接)</div>
			<form action="" method="post" id="addLinkFrom">
				<div class="tianjialianjie">_添加链接_ADD LINK</div>
				<div class="gxliangjie">
				  <table width="400" border="0" cellspacing="0">
                    <tr>
                      <td width="70" height="30">_链接名称</td>
                      <td><input name="entity.linkName" id ="linkName" type="text" class="mc"/></td>
                    </tr>
                    <tr>
                      <td height="25">_链接URL</td>
                      <td><input name="entity.linkUri" id="linkUri" type="text" value="http://" class="mc"/></td>
                    </tr>
                    <tr>
                      <td>_链接介绍</td>
                      <td><textarea name="entity.linkInfo" id="linkInfo" cols="4" rows="1" class="ljjieshao"></textarea></td>
                    </tr>
                  </table>
                  <div class="zhiyebaocun" ><a href="javascript:addLinkFrom();">确认保存 Confirmed Save</a></div>
                </div>
            </form>
				<div class="tianjialianjie">_编辑链接_EDIT LINK</div>
				<form  method="post" id="linkListFrom">
				<div class="bzlianj">
					<table width="500" border="0" cellspacing="0">
					   	<s:iterator value="list" id="obj">
						  <tr>
							<td width="330" class="ljjshao">_<a href="${obj[2]}" target="_blank">${obj[1]}</a>_点击次数_${obj[5]}<br />_${obj[4]}</td>
							<td width="85" valign="top"><a href="javascript:showBlogLinkModi('${obj[0]}','${obj[1]}','${obj[2]}');">_编辑</a>&nbsp;&nbsp;<a href="javascript:delLink(link_delete,'${obj[0]}');">_删除</a></td>
							<td width="85" valign="top"><a href="javascript:linkMove(link_move,'${obj[0]}','up');">_上移</a>&nbsp;&nbsp;<a href="javascript:linkMove(link_move,'${obj[0]}','down');">_下移</a></td>
						  </tr>
						  </s:iterator>
						</table>

				</div>
				<input type="hidden" name="id"/>
				 <input type="hidden" name="move"/>
				</form>
				<div class="zhiyebaocun" ><a href="member-link.action">更新Update</a></div>
				
				
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