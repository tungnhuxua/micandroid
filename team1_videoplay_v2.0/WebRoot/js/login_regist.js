var flag1=false;
var flag2=false;
var flag3=false;
var flag4=false;
var flag5=false;
var buyScoreFlag = false;
var checkPayUserResultFlag = false;
var checkBuyScoreFlag = false;
var userPayCheckFlag = false;


$(document).ready(function() {
	$(".login").click(login);
	$(".regist").click(regist);
});

/*
 * 登陆
 */
function login() {
	var loginTxt = "<font color='red'><h2>用户登陆</h2></font><br/>" +
			"<table>" +
			"<tr>" +
			"<td>用户名:</td><td><input type='text' name='username'/></td>" +
			"</tr>" +
			"<tr>" +
			"<td>密&nbsp;&nbsp;&nbsp;码:</td><td><input type='password' name='password'/></td>" +
			"</tr>" +
			"</table>";
	$.prompt(loginTxt,{
		callback: loginCallback,
		buttons: { 登陆: 'ok', 取消: 'cancel' },
		show: 'slideDown',
		prefix:'hua'
	});
	
	function loginCallback(v,m,f) {
		var username;
		if(v == 'ok') {
			 username = encodeURI(f.username);
			var password = f.password;
			$.get("loginServlet.do?username="+encodeURI(username)+"&password="+password,null,backLoginResult);
		}
		function backLoginResult(data) {
			var result = new Number(data);
			if(result == 1) {
				$.prompt("用户名不存在，请重新登陆");
			} else if(result == 2)  {
				$.prompt("密码错误，请重新登陆");
			} else if(result == 3) {
				$.prompt(username+",欢迎你",{
					callback: function () {window.location.reload();}
				});
			}else {
				$.prompt("用户已被冻结！");
			}
		}
	}
	

}

/*
 * 注册
 */
function regist() {
	var registTxt = "<font color='red'><h2>用户注册</h2></font><br/>" +
			"<table>" +
			"<tr>" +
			"<td>用&nbsp;户&nbsp;名:</td><td><input type='text' name='username' id='username' onblur='checkUserName()'/></td>" +
			"<td><span id='checkUserName'></span></td>"+
			"</tr>" +
			"<tr>" +
			"<td>密&nbsp;&nbsp;&nbsp;码:</td><td><input type='password' name='password1' id='password1' onblur='checkPassword1()'/></td>" +
			"<td><span id='checkPasswordResult'></span></td>"+
			"</tr>" +
			"<tr>" +
			"<td>确认密码:</td><td><input type='password' name='password2' id='password2' onblur='checkPassword2()'/></td>" +
			"<td><span id='checkPasswordResult2'></span></td>"+
			"</tr>" +
			"<tr>" +
			"<td>银行帐号:</td><td><input type='text' name='account' id='account'onblur='checkAccount()'/></td>" +
			"<td><span id='checkAccountResult'></span></td>"+
			"</tr>" +
			"<tr>" +
			"<td>邮&nbsp;&nbsp;&nbsp;箱:</td><td><input type='text' name='email' id='email' onblur='checkEmail()'/></td>" +
			"<td><span id='emailCheckResult'></span></td>"+
			"</tr>" +
			"<tr>" +
			"<td>手机号码:</td><td><input type='text' name='telephone' id='telephone' onblur='checkTelephone()'/></td>" +
			"<td><span id='telephoneCheckResult'></span></td>"+
			"</tr>" +
			"<tr>" +
			"<td>密码找回问题:</td><td>" +
			"<select name='question'>" +
			"<option value='您的母亲名字'>您的母亲名字</option>" +
			"<option value='您的父亲名字'>您的父亲名字</option>" +
			"<option value='您的中学名字'>您的中学名字</option>" +
			"</select>" +
			"</td>" +
			"</tr>" +
			"<tr>" +
			"<td>您的答案：</td><td><input type='text' name='answer'/></td>" +
			"</tr>" +
			"</table>";
	$.prompt(registTxt,{
		callback: registCallback,
		buttons: { 注册: 'ok', 取消: 'cancel' },
		show: 'slideDown',
		prefix:'hua'
	});
	
	function registCallback(v,m,f) {
		if(v == 'ok') {
			if(flag1&&flag2&&flag3&&flag4&&flag5){
				var username = encodeURI(f.username);
				var password = f.password1;
				var account = f.account;
				var email = f.email;
				var telephone = f.telephone;
				var question = encodeURI(f.question);
				var answer = encodeURI(f.answer);
				$.get("registServlet.do?username="+encodeURI(username)+"&password="+password+"&account="+account+"&email="+email+"&telephone="+telephone+"&question="+encodeURI(question)+"&answer="+encodeURI(answer),null,backRegistResult);
			}else {
				backRegistResult1();
			}
		}
	}
	
	function backRegistResult(data) {
		$.prompt("注册成功！");
	}
	function backRegistResult1() {
		$.prompt("注册失败！请重新按规则注册~");
	}
}
function checkUserName(){
	var username = $("#username").val();
	$.get("checkUserName.do?username="+username,null,checkUserNameResult)
}
function checkUserNameResult(data){
	$("#checkUserName").html(data);
}
function checkPassword1(){
	var password = $("#password1").val();
	flag1=false;
	if(password==""){
		$("#checkPasswordResult").html("<font color=red>*密码不能空!</font>");
		//return false;
	}else if(password.length<6||password.length>12){
		$("#checkPasswordResult").html("<font color=red>*密码长度为6-12位!</font>");
		//return false;
	}else {
		$("#checkPasswordResult").html("<font color=green>*密码OK!</font>");
		//return true;
		flag1=true;
	}
}
function checkPassword2(){
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	flag2=false;
	if(password1!=password2){
		$("#checkPasswordResult2").html("<font color=red>*密码要一致!</font>");
		//return false;
	}else {
		$("#checkPasswordResult2").html("<font color=green>*密码OK!</font>");
		//return true;
		flag2=true;
	}
}
function checkAccount(){
	var account = $("#account").val();
	flag3=false;
	if(account==""){
		$("#checkAccountResult").html("<font color=red>*银行卡号不可为空!</font>");
	//	return false;
	}else if(isNaN(account)==true){
		$("#checkAccountResult").html("<font color=red>*银行卡号必须为数字!</font>");
		//return false;
	}else if(account.length!=19){
		$("#checkAccountResult").html("<font color=red>*银行卡号长度为19位!</font>");
		//return false;
	}else {
		$("#checkAccountResult").html("<font color=green>*银行卡号OK!</font>");
		//return true;
		flag3=true;
	}
}

function checkEmail(){
	var email = $("#email").val();
	flag4=false;
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if(!reg.test(email)){
		$("#emailCheckResult").html("<font color=red>*邮箱格式有误!yinzhaohua1@gmail.com</font>");
		//return false;
	}else {
		$("#emailCheckResult").html("<font color=green>*邮箱OK!</font>");
		//return true;
		flag4=true;
	}
}
function checkTelephone(){
	var telephone = $("#telephone").val();
	flag5=false;
	 var reg =/^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8])[0-9]{8}$/;
	 if (telephone==""){
		 $("#telephoneCheckResult").html("<font color=red>*请填写手机号码！</font>");
		// return false;
     }else if(isNaN(telephone)||(telephone.length!=11)){
    	 $("#telephoneCheckResult").html("<font color=red>*手机号码为11位数字！请正确填写!</font>");
    	// return false;
     }else if(!reg.test(telephone)){
    	 $("#telephoneCheckResult").html("<font color=red>*您的手机号码不是移动号码，请重新输入!</font>");
    	// return false;
     }else {
    	 $("#telephoneCheckResult").html("<font color=green>*手机号码OK!</font>");
    	// return true;
    	 flag5=true;
     }
     
}
function checkWallet(money){
	var getMoney = $("#getMoney").val();
	checkPayUserResultFlag = false;
	if(getMoney==""){
		$("#checkWalletResult").html("<font color='red'>*请出入您提现的金额...~</font>");
		checkPayUserResultFlag = false;
	}else if(getMoney>money){
		$("#checkWalletResult").html("<font color='red'>*您的钱包没有那么多~</font>");
		checkPayUserResultFlag = false;
	}else if(isNaN(getMoney)){
		$("#checkWalletResult").html("<font color='red'>*请输入数字~</font>");
	}else {
		$("#checkWalletResult").html("<font color='green'>*OK~</font>");
		checkPayUserResultFlag = true;
	}
}
function checkBuyStore(buyScore1){
	var buyScore = $("#buyStore").val();
	buyScoreFlag = false;
	if(buyScore==""){
		$("#checkMoney").html("<font color='red'>*亲，请输入您要购买的积分数~</font>");
		buyScoreFlag = false;
	}else if(buyScore1<buyScore){
		$("#checkMoney").html("<font color='red'>*亲，你的钱包不够了~</font>&nbsp;&nbsp;<a href='userPay.jsp'>充值去..</a>");
		buyScoreFlag = false;
	}else if(isNaN(buyScore)){
		$("#checkMoney").html("<font color='red'>*亲，请输入数字~</font>");
		buyScoreFlag = false;
	}else {
		$("#checkMoney").html("<font color='red'>*亲，可以购买~</font>");
		buyScoreFlag = true;
	}
}
function userPayCheck(){
	var payCount = $("#payCount").val();
	userPayCheckFlag = false;
	if(payCount==""){
		$("#userPayCheckResult").html("<font color='red'>*亲，请输入充值金额~</font>");
		userPayCheckFlag = false;
	}else if(isNaN(payCount)){
		$("#userPayCheckResult").html("<font color='red'>*亲，请输入数字~</font>");
		userPayCheckFlag = false;
	}else {
		$("#userPayCheckResult").html("<font color='green'>*亲，可以充值了~</font>");
		userPayCheckFlag = true;
	}
}
function checkPayUserResult(){
	return checkPayUserResultFlag;
}
function checkBuyScore(){
	return buyScoreFlag;
}
function checkUserPay(){
	return userPayCheckFlag;
}