//欣赏好友
function enjoyTo(id,toid){

	var url = "archives!enjoy.action";
	var data = {'member.id':id,'tomember.id':toid};
	jQuery.post(url,data,function(data){
		if("nologin"==data){
			alert("您还未登录，请您登录后，在继续操作!");
			return ;
		}if("youself"==data){
			alert("自己欣赏自己,自恋！");
			return ;
		}
		if("friend"==data){
			alert("对方已经添加您为好友！");
			return ;
		}if("twofriend"==data){
			alert("对方已经是您的好友！");
			return ;
		}if("twoenjoy"==data){
			alert("您和他互相欣赏已经加对方为好友！");
			return ;
		}else{
			if("isenter"==data){
				alert("您已经欣赏过这位好友了，请不要在继续操作，谢谢！");
				return ;
			}else{
				alert("欣赏成功");
			}
			
		}
	});
}
//申请加为好友
function requestAddFriend(id,toid){

	var url = "archives!requestAddFriend.action";
	var data = {'member.id':id,'tomember.id':toid};
	jQuery.post(url,data,function(data){
		if("nologin"==data){
			alert("您还未登录，请您登录后，在继续操作!");
			$('#requestAddFriend').dialog("close");
			return ;
		}if("youself"==data){
			alert("不能加自己为好友！");
			$('#requestAddFriend').dialog("close");
			return ;
		}else{
			if("isfriend"==data){
				alert("您已经添加过该好友或正在申请，请不要在继续操作，谢谢！");
				$('#requestAddFriend').dialog("close");
				return ;
			}else{
				alert("申请成功");
				$('#requestAddFriend').dialog("close");
			}
			
		}
		
	});
}