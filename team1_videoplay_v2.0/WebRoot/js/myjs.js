function checknum(pagecount){
	var pagecount1 = parseInt(pagecount);
	var pagenumber = parseInt($("#number").val());
	if(pagecount1>=pagenumber){
		$("#dd").submit(function(){return true});
	}else {
		$("#dd").submit(function(){return false});
		$("#result").html("<font color='red'>*value is to large!</font>");
	}
};

$(document).ready(function(){
	$("#clickButton").click(confirm1);
});

function confirm1() {

		$.prompt("您的账户余额已不足，点确定去充值！",
		{
			callback: callback,
			buttons: { 确定: 'ok', 取消: 'cancel' },
			show: 'slideDown',
			prefix:'hua'
		}
		);
	
	function callback(v,m,f) {
		if(v == "ok") {
			window.location.href="userPay.jsp";
		}
	}
}
