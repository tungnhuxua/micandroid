<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	<form action="${ctx }/searcharticle.action" name="contentSearchNews" method="post">
				<div id="seackbar">
					<input type="text" name="newsContent" class="foot_seach" />
					<ul>
						<li><a href="javascript:contentSearchNews();"><img src="images/seachbutton2.gif" alt="" /></a></li>
						<li><a href="javascript:addCollectionFront('${article.id}','${article.member.id}');"><img src="images/tools1.gif" alt="" title="收藏"/></a></li>
						<li><a href="#"><img src="images/tools2.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools3.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools4.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools5.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools6.gif" alt="" /></a></li>
					</ul>
				</div>
   </form>