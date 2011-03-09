//添加链接
function blogManagerAddLink(linkName,linkUri,linkInfo){

	var url = "member-link!addLink.action";
	var data = {'entity.linkName':linkName,'entity.linkUri':linkUri,'entity.linkInfo':linkInfo};
	jQuery.post(url,data,function(data){
		if("notTwentyRows"==data){
			alert("目前最多只支持20个链接！");
			return ;
		}else{
			$("#linkName").attr("value","");
			$("#linkUri").attr("value","");
			$("#linkInfo").attr("value","");
			//alert("添加成功！");
			location.reload();
		}
		
	});
}
//显示链接编辑修改窗口
function showBlogLinkModi(id,linkName,linkUri){
	
	$('#linkmodi').dialog({
		bgiframe: false,
		resizable: true,
		modal: true,
		width: 400,
		height:150
	});
	$('#linkNameModi').attr("value",linkName);
	$("#id").attr("value",id);
	$("#linkUriModi").attr("value",linkUri);
}