
function dynamicsMessageType1(messages){
	eval("message = "+messages);
	document.write("<a target=_blank href=blog-home.action?tomember.id="+message.memberId+">");
	document.write(message.memberName);
	document.write("</a>_");
	document.write(message.date);
	document.write("和<a target=_blank href=blog-home.action?tomember.id="+message.tomemberId+">");
	document.write(message.tomemberName);
	document.write("</a>");
	document.write("加为了好友");
}

function dynamicsMessageType2(messages){
	eval("message = "+messages);
	document.write("<a target=_blank href='blog-home.action?tomember.id="+message.memberId+"'>");
	document.write(message.memberName);
	document.write("</a>_");
	document.write(message.date);
	document.write("发布了新日志");
	document.write("<a target=_blank href=blog-content.action?id="+message.articleId+"&&tomember.id="+message.memberId+">");
	document.write("\""+message.articleTitle+"\"");
	document.write("</a>");
}

function dynamicsMessageType3(messages){
	eval("message = "+messages);
	document.write("<a target=_blank href=blog-home.action?tomember.id="+message.memberId+">");
	document.write(message.memberName);
	document.write("</a>_");
	document.write(message.date);
	document.write("发布了新作品");
	document.write("<a target=_blank href=blog-content.action?id="+message.articleId+"&&tomember.id="+message.memberId+">");
	document.write("\""+message.articleTitle+"\"");
	document.write("</a>");
}

function dynamicsMessageType4(messages){
	eval("message = "+messages);
	document.write("<a target=_blank href=blog-home.action?tomember.id="+message.memberId+">");
	document.write(message.memberName);
	document.write("</a>_");
	document.write(message.date);
	document.write("收藏了文章");
	document.write("<a target=_blank href=../searcharticlecontent.action?article.id="+message.articleId+">");
	document.write("\""+message.articleTitle+"\"");
	document.write("</a>");
}

//查看用户动态更多
function getDynamicsMore(){
	var url = "member-dynamics-more.action";
	var data = {'ssuid':1};
	jQuery.post(url,data,function(data){
		
			$("#dynamicsMore").after(data);
			$("#dynamicsMore").hide();
	});
}
