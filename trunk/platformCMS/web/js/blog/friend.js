//显示欣赏添加朋友
function showAddFriend(id){
	$('#addfriend').dialog({
		bgiframe: false,
		resizable: true,
		modal: true,
		width: 300,
		height:100
	});
	$('#addfriend').dialog("open");
	var tableForm = document.getElementById('friendGroup');
	tableForm.onsubmit=null;
	tableForm.id.value = id; //给id赋值 到hidden
}
//显示添加分组
function showAddGroup(){
	$('#addGroup').dialog({
		bgiframe: false,
		resizable: true,
		modal: true,
		width: 300,
		height:100
	});
	$('#addGroup').dialog("open");

}
//显示好友申请
function showFrontAddFriend(){
	$('#requestAddFriend').dialog({
		bgiframe: false,
		resizable: true,
		modal: true,
		width: 410,
		height:400
	});
	$('#requestAddFriend').dialog("open");

}
//显示添加好友
function showlistAddFriend(id,tid,name,cap){
	
	$('#listAddFriend').dialog({
		bgiframe: false,
		resizable: true,
		modal: true,
		width: 410,
		height:400
	});
	$('#listAddFriend').dialog("open");
	$('#cap').attr("src",cap);
	$("#name").text(name);
	$("#id").attr("value",id);
	$("#tid").attr("value",tid);
}


function a_unblogIndex(id){
	var url = "friendIndexOperate.action";
	var data = {'id':id,'blogIndex':'1'};
	jQuery.post(url,data,function(dat){
		if(dat){
			$("#unupblog_"+id).hide();
			$("#upblog_"+id).show();	
		}else{
				alert("取消放置个人首页失败");
			}
	});
}

function a_blogIndex(id){
	var url = "friendIndexOperate.action";
	var data = {'id':id,'blogIndex':'0'};
	jQuery.post(url,data,function(dat){
		if(dat){
			$("#unupblog_"+id).show();
			$("#upblog_"+id).hide();	
		}else{
				alert("放置个人首页失败");
			}
	});
}
