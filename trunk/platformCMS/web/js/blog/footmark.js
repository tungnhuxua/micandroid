//blog添加收藏
function addCollectionBlog(articleid){
	var url = "member-collection!addCollection.action";
	var data = {'entity.article.id':articleid};
	jQuery.post(url,data,function(data){
		if("nologin"==data){
			alert("您还未登录，登录后才可以收藏，谢谢您的关注。");
			return ;
		}if("iscollection"==data){
			alert("您已经收藏了该条资讯！");
			return ;
		}else{
			alert("收藏成功！");
		}
		
	});
}

function delFootMark(id){
	var footmark_delete = {msg:"删除操作不可恢复，您确认要继续吗？"};
	if(footmark_delete.msg && !confirm(footmark_delete.msg)) {
		return;
	}
	var url = "member-footmark!deleteFootmark.action";
	var data = {'id':id};
	jQuery.post(url,data,function(data){
		$("#footMarkDl"+id).fadeOut("slow");
		var count = $("#footMarkCount").text();
		count = parseInt(count)-1;
		$("#footMarkCount").text(count);
		$("#footMarkCount2").text(count);
	});
}