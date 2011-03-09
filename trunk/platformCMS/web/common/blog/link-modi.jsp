<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="linkmodi" style="display:none">
	<script type="text/javascript">
	 var link_modi = {action:"member-link!linkModi.action"};
		function linkModi(op){
			$('#linkmodi').dialog("close");
			var tableForm = document.getElementById('memberLinkModiForm');
			tableForm.onsubmit=null;
			tableForm.action=op.action;
			tableForm.submit();
		}
	</script>
  <form method="post" id="memberLinkModiForm">
	<table width="350" border="1">
	  <tr>
	    <td>链接名称</td>
	    <td><input type="text" name="entity.linkName" id="linkNameModi" class="youbian"/></td>
	  </tr>
	  <tr>
	    <td>链接地址：</td>
	    <td><input type="text" name="entity.linkUri" id="linkUriModi" class="youbian"/></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="right"><span style="color: #FFFFFF"><a href="javascript:linkModi(link_modi)">修改_UPDATE</a></span></td>
	  </tr>
	</table>
	<input type="hidden" name="entity.id" id="id"/>
  </form>
</div>
