
//用户留言
function addMessageBlogFront(uid){
	var quietly = 0;
	var bool = document.getElementById("quietly");
	if(bool.checked){
		quietly = 1;
	}
	var recon = $("#reply").val();
	if(recon.length<1){
		alert("请填写留言!");
		return ;
	}
	if(recon.length>250){
		alert("留言内容_不能超过250字。");
		return ;
	}
	var url = "member-message-add.action";
	var data = {'entity.uid':uid,'entity.comment':recon,'entity.quietly':quietly};
	jQuery.post(url,data,function(data){
		if("nologin"==data){
			alert("您还未登录，登录后才可以发表留言，谢谢您的关注。");
			return ;
		}if("seconds"==data){
			alert("您发表留言太快了，休息下吧！");
			return ;
		}else{
			$("#messageSpan").after(data);
			var count = $("#commentCount").text();
			count = parseInt(count)+1;
			$(".commentCount").text(count);
			$("#reply").val("");
		}
	});
}

function memberMessageSetupMessageType(id){
	var url = "member-message!setupMessageType.action";
	var data = {'messageType':id};
	jQuery.post(url,data,function(data){
		if(id == 1){
			alert("您设置了留言对外开放，现在所有会员都可以查看到您的留言！");
		}if(id == 2){
			alert("您设置了留言隐藏，现在只有您自己可以查看留言！");
		}if(id == 3){
			alert("您设置了留言仅向好友公开，现在只有您的朋友才可以查看您的留言！");
		}
	});
}
//显示留言回复框
function memberMessageAnswerTure(id,tid){
	$("#answerMessage"+id).after("<div style='padding-top:20px' id = 'divAnswer"+id+"'><textarea id='reply"+id+"' cols='10' style='height:50px; width:500px;' rows='5'></textarea><br/><a href=javascript:addMessageAnswer('"+id+"','"+tid+"');>提交</a>&nbsp;&nbsp;<a href=javascript:memberMessageAnswerFalse('"+id+"','"+tid+"')> 取消 </a></div>");
	$("#AanswerTrue"+id).attr("href","javascript:memberMessageAnswerFalse('"+id+"','"+tid+"');"); 
	
}
//取消留言回复框
function memberMessageAnswerFalse(id,tid){
	$("#divAnswer"+id).hide();
	$("#AanswerTrue"+id).attr("href","javascript:memberMessageAnswerTure('"+id+"','"+tid+"');"); 
}


//用户留言回复
function addMessageAnswer(id,tid){

	var recon = $("#reply"+id).val();
	if(recon.length<1){
		alert("请填写留言!");
		return ;
	}
	if(recon.length>250){
		alert("留言内容_不能超过250字。");
		return ;
	}
	var url = "member-message-answer.action";
	var data = {'tomember.id':tid,'entity.comment':recon,'entity.connectionId':id};
	jQuery.post(url,data,function(data){
		$("#divAnswer"+id).hide();
		$("#answerMessageAnswer"+id).after(data);
	});
}