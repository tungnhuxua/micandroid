<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${tomember.name}'s blog</title>
<link type="text/css" href="${ctx }/css/ui.all.css" rel="stylesheet" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.bgiframe.min.js"></script>
<link type="text/css" href="../css/ui.all.css" rel="stylesheet" />
		<script type="text/javascript" src="../js/blog/friend.js"></script>
		<script type="text/javascript" src="../js/blog/front.js"></script>
		<script type="text/javascript" src="../js/ui.core.js"></script>
		<script type="text/javascript" src="../js/ui.dialog.js"></script>

</head>
<body>
<%@ include file="/common/blog/userlogin.jsp" %>
<%@ include file="/common/blog/requestAddFriend.jsp" %>
<div class="container">	
	<div class="header">
			<%@include file="/common/blog-header.jsp" %>
	</div>
	<div class="content">
		<!--  <div class="shouy"><img src="${ctx }/images/zhuyepic.jpg"/></div>-->
		<div class="toubu">
			<%@include file="/common/blog-content-header-to.jsp" %>
		</div>
		<div class="main">
			<div class="bqmainbh">
				<div class="soucanleftb">
					<ul class="soucanzbleft">
						<li style="font-weight: bold;"><a href="#">${tomember.info.realName }</a></li>
						<li style="font-weight: bold;">${tomember.info.liveProvince }_<s:if test="tomember.info.gender == '1'">男</s:if><s:else>女</s:else>
							<SCRIPT> 
							    //根据出生日期，计算年龄
								var birthday=new Date('${tomember.info.birthday}'.replace(/-/g, "\/")); 
								var d=new Date(); 
								var age = d.getFullYear()-birthday.getFullYear()-((d.getMonth()<birthday.getMonth()|| d.getMonth()==birthday.getMonth() && d.getDate()<birthday.getDate())?1:0);
								document.write("_"+age+"岁");
							</SCRIPT>
						</li>
					</ul>	
					<ul class="soucanzbleft">
						<li><a href="#"><s:if test="tomember.online == 1">_在线</s:if><s:else>_离线</s:else></a></li>
					  <s:if test='member.id!= "" && member.id!= null'>
						
						<li><a href="javascript:showFrontAddFriend();">_加为好友</a></li>
						<li><a href="${ctx }/blog/message!input.action?mid=${tomember.id}">_发信息</a></li>
						<li><a href="javascript:enjoyTo('${member.id}','${tomember.id}');">_欣赏</a></li>
					  </s:if>
					</ul>
					<ul class="soucanzbleft">
					<script language="javascript">
						var setString = '${tomember.info.workName}';
						var value = setString.split(";"); 
						for(i = 0;i<value.length;i++){
							document.write("<li>"+value[i]+"</li>");
						}
					</script>
					</ul>
					<ul class="soucanzbleft">
						<li><a href="#">资产_${tomember.info.mark }喔元</a></li>
						<li><a href="#">总访问量_${tomember.info.accessing}</a></li>
						<li><a href="#">日志_${logCount}</a></li>
						<li><a href="#">档案_1</a></li>
						<li><a href="#">秀场_${showCount}</a></li>
					</ul>
					<ul class="soucanzbleft">
						<li style="font-weight: bold;"><a href="#">链接</a></li>
						<s:iterator value="linkList" var="obj">
						<li><a target="_blank" href="${obj[2]}">${obj[1]}</a></li>
						</s:iterator>
					</ul>
					</div>
				<div class="zhutib">
				<div style="font-weight: bold;" class="shoucanweizhib">特别推荐</div>
				<div class="rizhinr">
				<s:iterator value="page.result">
					<dl>
						<dt>
							<a class="coverBg190" href="${ctx }/blog/blog-content.action?id=${id}&&tomember.id=${tomember.id}"></a>
							  <s:if test="imageUri!=null && imageUri != ''">
							  <img src="${ctx}${imageUri}" alt="${title}" width="190px" height="190px"/>
							</s:if>
						</dt>
						<dd style="font-weight: 700;"><a href="${ctx }/blog/blog-content.action?id=${id}&&tomember.id=${tomember.id}">${title }</a></dd>
						<dd><s:date name="createDate" format="yyyy-MM-dd" />_发表于_${showCategory.name }</dd>
						<dd>浏览_${readCount }_评论_<s:property value="comments.size"/>_投票_${voteCount }</dd>
					</dl>
				</s:iterator>
				</div>
				<div class="chakanqb">
					<div class="all"><a href="${ctx}/blog/blog-friend.action?tomember.id=${tomember.id}">项级好友_查看全部_ALL</a></div>
					  <s:iterator value="friendList" id="obj">
						<dl>
							<dt>
								<a class="coverBg75" href="${ctx}/blog/blog-home.action?tomember.id=${obj[8]}"></a>
									 <s:if test="#obj[5]!=null && #obj[5]!=''">
										<img src="${ctx}${obj[5]}" width="75px" height="75px" alt="${obj[0]}"/>
									 </s:if>
									<s:else>
										<img src="../images/zhchepic.gif" width="75px" height="75px" alt="${obj[0]}"/>
									</s:else>
							</dt>
							<dd>${obj[0]}</dd>
						</dl>
					  </s:iterator>
				</div>
				
				<%@include file="/common/blog/blog-search.jsp" %>
				</div>
					
		</div>
			<div class="footer">
				<%@include file="/common/blog-content-footer-to.jsp" %>
			</div>
		</div>
	</div>
</div>
</body>
</html>