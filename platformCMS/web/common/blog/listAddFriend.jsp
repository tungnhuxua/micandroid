<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script type="text/javascript">
		var request_friend_delete = {action:"member-friend!deleteRequestFriend.action",msg:"确定拒绝好友请求吗？"};
		function addlistFriendForm(id){
			document.getElementById(id).submit();
		}
		function requestFriendDel(op) {
			if(op.msg && !confirm(op.msg)) {
				return;
			}
			var tableForm = document.getElementById('listAddFriendForm');
			tableForm.onsubmit=null;
			tableForm.action=op.action;
			tableForm.submit();
		}
	</script>

	<div class="qqiukuangc" id="listAddFriend" style="display:none">
					<div class="qqiutools">
					<div><img src="../images/tools.gif" /></div>
					<UL>
					<li><a href=""><img src="../images/a.gif" /></a></li>
					<li><a href=""><img src="../images/b.gif" /></a></li>
					<li><a href=""><img src="../images/c.gif" /></a></li>
					<li><a href=""><img src="../images/d.gif" /></a></li>
					<li><a href=""><img src="../images/e.gif" /></a></li>
					<li><a href=""><img src="../images/f.gif" /></a></li>
					</UL>
					</div>
					<div class="request" style="font-weight:700">REQUEST_好友请求</div>
					<div class="request">_成功添加一位好友，可获得0喔元资历！</div>
					<div class="qqyonghuming">
						<dl>
							<dt><img  id="cap" width="80" height="80"/></dt>
							<dd style="margin-top:5px"><a href=""><span id="name">用户名</span></a></dd>
							<dd>请求成为你的好友</dd>
						</dl>
					</div>
					<div class="accept">
						<ul>
							<li><a href="javascript:addlistFriendForm('listAddFriendForm');">_确认接受_Accept</a></li>
							<li><a href="javascript:requestFriendDel(request_friend_delete);">_忽略_Neglected</a></li>
						</ul>
					</div>
					<form action="member-friend!requestAddFriend.action" id="listAddFriendForm" method="post">
					<div class="request" style="padding-top:30px">
					<select name="groupType">
					<%java.util.List friendGroupList = (java.util.List)request.getAttribute("friendGroupList");
						for(int i = 0;i<friendGroupList.size();i++){
						  Object[] object = (Object[])friendGroupList.get(i);%>
			       			  <option value="<%=object[1]%>"><%=object[0]%></option>
			         	<%} %>
					</select>
					_好友分类_Categories_
					<input type="hidden" id="id" name="id"/>
					<input type="hidden" id="tid" name="tid"/>
					</div>
					</form>
					
	</div>

