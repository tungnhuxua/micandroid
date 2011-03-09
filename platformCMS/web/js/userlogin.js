function userlogin(){
	var username = $("#username").val();
	var password = $("#userpassword").val();
	var remember = $("#security_remember_me").attr('checked');
	if(username.length<1){
		alert("请填写用户名");
		return;
	}
	if(password.length<1){
		alert("请填写密码");
		return;
	}
	
	var url = "j_spring_security_check";
	var data = {'j_username':username,'j_password':password,'_spring_security_remember_me':remember};
	jQuery.post(url,data,function(back){
		var reg = /登录失败/;
		if(reg.test(back)){
			alert("登录失败");
		}else{
			jQuery.post("get-username.action",function(ba){
				//$("#linkreg").hide();
				//$("#logout_login").hide();
				
				////$("#site_home").after("<li id='blogcenter'><a href='blog/member-home.action'>个人中心</a></li><li id='logout'>"+ba+"[<a href=\"#\" style=\"margin-left:0px;\" onclick=\"userlogout();\">退出</a>]</li>");
				//$("#site_home").hide();
				//$("#ooowoindex").hide();
				location.reload();
			})
			$('#login').dialog("close");
			$(".replylogin").hide();
		}
	});
}

function userlogout(){
	jQuery.post("j_spring_security_logout");
	$("#linkreg").show();
	$("#logout_login").show();
	$("#blogcenter").hide();
	$("#logout").hide();
	$("#logout_login").after("<li id=ooowoindex><a href='#'>设计圈首页</a></li>");
}

function showLogin(){
	$('#login').dialog({
		bgiframe: true,
		resizable: true,
		modal: true,
		width: 400,
		height:400
	});
	$('#login').dialog("open");
}

function toregister(){
	alert("设计圈暂时不对外开放注册！");
}

function bloglogin(){
	var username = $("#username").val();
	var password = $("#userpassword").val();

	var remember = $("#security_remember_me").attr('checked');
	if(username.length<1){
		alert("请填写用户名");
		return;
	}
	if(password.length<1){
		alert("请填写密码");
		return;
	}
	
	var url = "../j_spring_security_check";
	var data = {'j_username':username,'j_password':password,'_spring_security_remember_me':remember};
	jQuery.post(url,data,function(back){
		var reg = /登录失败/;
		if(reg.test(back)){
			alert("登录失败");
		}else{
			jQuery.post("../get-username.action",function(ba){
				//$("#ylogin").text(ba);
				//$("#site_home").after("<li><a href=\"../j_spring_security_logout\" style=\"margin-left:0px;\" >退出</a></li>");
				location.reload();
			})
			$('#bloglogin').dialog("close");
		}
	});
}
function showBlogLogin(){
	$('#bloglogin').dialog({
		bgiframe: true,
		resizable: true,
		modal: true,
		width: 400,
		height:400
	});
	$('#bloglogin').dialog("open");
}
//top新闻搜索
function searchNews(){
    var newstitle = document.searchnews.newsContent.value;
    if(newstitle.length<1)
        alert("请您输入要搜索的新闻内容");
    else
        document.searchnews.submit();
}
//底部新闻搜索
function searchNews1(){
	   var newstitle = document.searchnews1.newsContent.value;
	    if(document.all['seachto'][1].checked){
		 window.location.href='http://www.google.com';
		}else{
			if(newstitle.length<1){
			 alert("请您输入要搜索的新闻内容");
			}else{
				document.searchnews1.submit(); 
			}
		}
}

//TAG新闻搜索
function searchNews2(){
	   var newstitle = document.searchnews2.newsContent.value;
	    if(document.all['seachtoTag'][1].checked){
		 window.location.href='http://www.google.com';
		}else{
			if(newstitle.length<1){
			 alert("请您输入要搜索的新闻内容");
			}else{
				document.searchnews2.submit(); 
			}
		}
}