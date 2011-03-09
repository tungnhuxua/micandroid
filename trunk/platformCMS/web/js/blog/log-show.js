	function a_hiden(id){
		var url = "operate.action";
		var data = {'id':id,'ahiden':'1'};
		jQuery.post(url,data,function(dat){
			if(dat){
				$("#hiden_"+id).hide();
				$("#unhiden_"+id).show();	
			}else{
					alert("隐藏失败");
				}
		});
	}

	
	function a_unhiden(id){
		var url = "operate.action";
		var data = {'id':id,'ahiden':'0'};
		jQuery.post(url,data,function(dat){
			if(dat){
				$("#unhiden_"+id).hide();
				$("#hiden_"+id).show();	
			}else{
					alert("取消隐藏失败");
				}
		});
	}

	function a_up(id){
		var url = "operate.action";
		var data = {'id':id,'aup':'1'};
		jQuery.post(url,data,function(dat){
			if(dat){
				$("#up_"+id).hide();
				$("#unup_"+id).show();	
			}else{
					alert("置顶失败");
				}
		});
	}

	
	function a_unup(id){
		var url = "operate.action";
		var data = {'id':id,'aup':'0'};
		jQuery.post(url,data,function(dat){
			if(dat){
				$("#unup_"+id).hide();
				$("#up_"+id).show();	
			}else{
					alert("取消置顶失败");
				}
		});
	}

	function a_upblog(id){
		var url = "operate.action";
		var data = {'id':id,'upblog':'1'};
		jQuery.post(url,data,function(dat){
			if(dat){
				$("#upblog_"+id).hide();
				$("#unupblog_"+id).show();	
			}else{
					alert("首页特别推荐失败");
				}
		});
	}

	
	function a_unupblog(id){
		var url = "operate.action";
		var data = {'id':id,'upblog':'0'};
		jQuery.post(url,data,function(dat){
			if(dat){
				$("#unupblog_"+id).hide();
				$("#upblog_"+id).show();	
			}else{
					alert("取消首页特别推荐失败");
				}
		});
	}