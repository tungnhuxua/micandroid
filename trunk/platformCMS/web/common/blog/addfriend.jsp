<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="addfriend" style="display:none">
	<script type="text/javascript">
		function addFriendForm(id){
			document.getElementById(id).submit();
		}
	</script>
  <form method="post" action="member-friend!addfriend.action" id="friendGroup">
	  <ul style="">
	    <li style="float: left;">好友分类：
	      <select name="groupType">
	         <%java.util.List friendGroupList = (java.util.List)request.getAttribute("friendGroupList");
				for(int i = 0;i<friendGroupList.size();i++){
				  Object[] object = (Object[])friendGroupList.get(i);%>
	       			  <option value="<%=object[1]%>"><%=object[0]%></option>
	         	<%} %>
	      </select>
	    </li>
	    <li style="float: right;"><a href="javascript:addFriendForm('friendGroup');">添加到分组</a></li>
	  </ul>
	  <input name="id" type="hidden">
  </form>
</div>
