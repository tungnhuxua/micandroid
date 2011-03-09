<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/blog/aooan_player.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script language="javascript"> 
	var music_delete = {action:"member-music!delete.action",msg:"确定要删除音乐吗？"};
	var music_move = {action:"member-music!musicMove.action"};
	var music_top = {action:"member-music!musicOrderTop.action"};
	function delMusic(op,id) {
		if(op.msg && !confirm(op.msg)) {
			return;
		}
		var tableForm = document.getElementById('musicListFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.submit();
	}

	function musicMove(op,id,moveType){
		var tableForm = document.getElementById('musicListFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.move.value = moveType;
		tableForm.submit();
	}

	function musicTop(op,id){
		var tableForm = document.getElementById('musicListFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.submit();
	}
</script>
<script language="javascript"  for="player" event="PlayStateChange(lOldState, lNewState)" >
</script>
<script language="javascript"> 
<%java.util.List list = (java.util.List)request.getAttribute("list"); 
		for(int i =0;i<list.size();i++){
		Object[] object = (Object[])list.get(i);%>
	addlist("<%=object[2]%>","<%=object[1]%>");
<%} %>
</script>
</head>
<body onload="musicload()">
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
				<div class="dangannav">
					<ul>
						<li><a href="member-music-add.action">添加音乐</a></li>
						<li>_<a href="member-music-add.action">Add Music</a></li>
					</ul>
				</div>
				<table width="300" border="0" cellspacing="0">
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25"></td>
                    </tr>
                  </table>
                  <%@include file="/common/blog/aooan_player.jsp" %>
				<form method="post" id="musicListFrom">
					<div class="yinyueqingdang"><span style="font-size:14px; font-weight:700">_音乐清单</span>_(共用了 <span style="color: #91BF29; font-size: 18px;">${page.totalCount} </span>  首歌曲)_目前最多只支持引用十首歌曲</div>
					<div class="gequming">
						<table width="540" border="0" cellspacing="0">
						<s:iterator value="list"  id="obj" status="sta">
						  <tr>
							<td width="40" height="30"><input name="gq" type="checkbox" value="" class="qmkuang"/></td>
							<td width="390"><s:property value='#sta.index+1'/>.<a href="javascript:splay(<s:property value='#sta.index+1'/>);">${obj[1]}</a></td>
							<td width="110"><a href="javascript:delMusic(music_delete,'${obj[0]}');">删除</a><a href="javascript:musicTop(music_top,'${obj[0]}');">|置顶</a><a href="javascript:musicMove(music_move,'${obj[0]}','up');">|上升一位</a></td>
						  </tr>
						</s:iterator>
						</table>
						</div>
				 <input type="hidden" name="id"/>
				 <input type="hidden" name="move"/>
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