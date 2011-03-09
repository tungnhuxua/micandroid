<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
			<div class="qqiukuangc" id="requestAddFriend" style="display:none">
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
				<div class="request" style="font-weight:700; color:#83C325">ADD FRIEND_添加好友</div>
				<div class="request">_成功添加一位好友，可获得0喔元资历！</div>
				<div class="qqyonghuming" style="padding-top:40px">_发送>${tomember.name}</div>
				<div class="request">
					<form action="" method="post">
						<textarea name="fs" cols="1" rows="3" class="fasong"></textarea>
					</form>
				</div>
				<div class="accept">
					<ul>
						<li><a href="javascript:requestAddFriend('${member.id}','${tomember.id}');">_发送申请_Send application</a></li>
					</ul>
				</div>
			</div>
