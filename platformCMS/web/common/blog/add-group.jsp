<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="addGroup" style="display:none">
	<script type="text/javascript">
		function addGroup(id){
			var groupName = document.getElementById('groupName').value;
			if(groupName == ""){
				alert("请您填写好友分组名！");
			}else{
				document.getElementById(id).submit();
			}
			
		}
	</script>
  <form method="post" action="member-friend!addGroup.action" id="addGroupForm">
	  <ul style="">
	    <li style="float: left;">分类：<input type="text" class="qq" id="groupName" name="groupName"/></li>
	    <li style="float: right;"><a href="javascript:addGroup('addGroupForm');">添加</a></li>
	  </ul>
  </form>
</div>
