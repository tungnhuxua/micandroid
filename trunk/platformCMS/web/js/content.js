/**
 * @author Administrator
 */
window.onscroll = function(){
	//得到他前面的数字，匹配掉
	var distop = document.getElementById("totop").style.marginTop;
	distop = distop.replace("px","");
	if(document.documentElement.scrollTop - 300 >100){
		document.getElementById("totop").style.marginTop = document.documentElement.scrollTop - 300 + "px";
	}else{
		return false;
	}
}
//投票处理
function woding(id){
	$("#ding").fadeOut("slow"); 
	$("#ding").attr("src","images/tanks.gif"); 
	$("#ding").removeAttr("onclick"); 
	$("#ding").fadeIn("slow"); 
	$("#vcount").fadeOut("slow");
	$(".countp").fadeOut("slow");
	var count = $("#vcount").text();
	count = parseInt(count)+1;
	$("#vcount").text(count);
	$(".countp").text(count);
	$(".countp").text(count);
	$(".countding").text(count);
	$("#vcount").fadeIn("slow");
	$(".countp").slideToggle("slow");
	var url = "ding.action";
	var data = {'article.id':id};
	jQuery.post(url,data);
}

//发表文章评论

function comment(id){
	var recon = $("#reply").val();
	if(recon.length<1){
		alert("请填写评论。");
		return ;
	}
	if(recon.length>250){
		alert("评论内容_不能超过250字。");
		return ;
	}
	var url = "comment.action";
	var data = {'article.id':id,'comment.content':recon};
	jQuery.post(url,data,function(data){
		if("nologin"==data){
			alert("您还未登录，登录后才可以发表评论，谢谢您的关注。");
			return ;
		}else{
			$(".articletop").after(data);
			var count = $("#commentCount").text();
			count = parseInt(count)+1;
			$(".commentCount").text(count);
			$("#reply").val("");
		}
	});
}
//评论上一页
function prePage(){
	var authority = $("#authority").val();
	var articleId = $("#article-id").val();
	var pageNo = $("#pageNo").val();
	var totalPages = $("#totalPages").val();
	totalPages = parseInt(totalPages);
	pageNo = parseInt(pageNo)-1;
	if(pageNo<1){
		return ;
	}
	var url = "comment-page.action";
	var data = {'article.id':articleId,'commentPage.pageNo':pageNo,'authority':authority};
	jQuery.post(url,data,function(data){
		$(".articlecontent").remove();
		$(".articletop").after(data);
		$("#pageNo").val(pageNo);
		//隐藏下一页
		if(pageNo<=1){
			$(".pagepri").hide();
		}
		//显示上一页
		if(pageNo<totalPages){
			$(".pagenext").show();
		}
	})
}
//评论下一页
function nextPage(){
	var authority = $("#authority").val();
	var articleId = $("#article-id").val();
	var pageNo = $("#pageNo").val();
	var totalPages = $("#totalPages").val();
	totalPages = parseInt(totalPages);
	pageNo = parseInt(pageNo)+1;
	if(pageNo>totalPages){
		return ;
	}
	
	var url = "comment-page.action";
	var data = {'article.id':articleId,'commentPage.pageNo':pageNo,'authority':authority};
	jQuery.post(url,data,function(data){
		$(".articlecontent").remove();
		$(".articletop").after(data);
		$("#pageNo").val(pageNo);
		//隐藏下一页
		if(pageNo>=totalPages){
			$(".pagenext").hide();
		}
		//显示上一页
		if(pageNo>1){
			$(".pagepri").show();
		}
	})
}

//评论处的登录
function commentlogin(){

	var username = $("._commentloginname").val();
	var password = $("._commentpassword").val();
	if(username.length<1){
		alert("请填写用户名");
		return;
	}
	if(password.length<1){
		alert("请填写密码");
		return;
	}
	
	var url = "j_spring_security_check";
	var data = {'j_username':username,'j_password':password,'_spring_security_remember_me':false};
	jQuery.post(url,data,function(back){
		var reg = /登录失败/;
		if(reg.test(back)){
			alert("登录失败");
		}else{
			jQuery.post("get-username.action",function(ba){
				$("#linkreg").hide();
				$("#logout_login").hide();
				$("#site_home").after("<li>"+ba+"[<a href=\"#\" style=\"margin-left:0px;\" onclick=\"userlogout();\">退出</a>]</li>");
			})
			$(".replylogin").hide();
		}
	});

}

//编辑评论
function editComment(id){
	alert("编辑评论研发中...");
}

//删除评论
function delComment(id,dom){
	var url = "del_comment.action";	
	var data = {'comment.id':id};
	jQuery.post(url,data,function(back){
		if(back=='success'){
			$(dom).parent().parent().parent().parent().remove();
		}else{
			alert("删除失败!");
		}
	});
}
//新闻搜索
function contentSearchNews(){
	var newsTitle = document.contentSearchNews.newsContent.value;
	if(newsTitle.length<1){
		alert('请您输入要搜索的新闻内容');
	}else{
		document.contentSearchNews.submit();
	}
}
//前台添加收藏
function addCollectionFront(articleid,memberid){
	var url = "blog/member-collection!addCollection.action";
	var data = {'entity.article.id':articleid,'entity.article.member.id':memberid};
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