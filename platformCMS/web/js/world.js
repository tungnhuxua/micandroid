function enterCircle(id){

	var url = "enterCircle.action";
	var data = {'circleId':id};
	jQuery.post(url,data,function(data){
		if("nologin"==data){
			alert("您还未登录，请您登录后，在继续操作!");
			return ;
		}else{
			if("isenter"==data){
				alert("您已经加入该群英会或正在申请中，请不要在继续操作，谢谢！");
				return ;
			}else{
				alert("申请成功，请等待群主审核！");
			}
			
		}
	});
}