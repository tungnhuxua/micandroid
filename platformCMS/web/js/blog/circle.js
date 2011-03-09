function a_uncircleBlogIndex(id){
	var url = "circleIndexOperate.action";
	var data = {'id':id,'circleIndex':'1'};
	jQuery.post(url,data,function(dat){
		if(dat){
			$("#unupblog_"+id).hide();
			$("#upblog_"+id).show();	
		}else{
				alert("取消放置个人首页失败");
			}
	});
}

function a_circleBlogIndex(id){
	var url = "circleIndexOperate.action";
	var data = {'id':id,'circleIndex':'0'};
	jQuery.post(url,data,function(dat){
		if(dat){
			$("#unupblog_"+id).show();
			$("#upblog_"+id).hide();	
		}else{
				alert("放置个人首页失败");
			}
	});
}