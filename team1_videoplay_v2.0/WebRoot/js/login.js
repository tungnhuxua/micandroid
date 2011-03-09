var focusFlag = false;
$(document).ready(function () {
	$("#userName").focus();
	////用来判断焦点是不是在userName或password上面，
	$("#userName").focus(function () {
		focusFlag = true;
	}).blur(function () {
		focusFlag = false;
	});
	$("#password").focus(function () {
		focusFlag = true;
	}).blur(function () {
		focusFlag = false;
	});
	$("#submitButton").click(submitForm);
	$("#resetButton").click(resetForm);
});

$(document).keydown(function(event) {
	if(focusFlag && event.keyCode == 13) {
		submitForm();
	}
});

function submitForm() {
	var userName = $("#userName").val();
	var password = $("#password").val();
	$.get("/video/login.do?userName="+userName+"&password="+password,null,loginResult);
}

function loginResult(data) {
	/*0 is userName is not avaliable
	 *1  password is error
	 *2 login success.
	 */
	var flag = new Number(data);
	if(flag == 0) {
		myWindow("用户名不存在或没有启用，请重新输入！");
		return;
	}
	if(flag == 1) {
		myWindow("密码错误，请重新输入！");
		return;
	}
	if(flag == 2) {
		$("#loginForm").submit();
		return;
	}
}

function resetForm() {
	document.loginForm.reset();
}

function myWindow(message) {
	$.fn.extend({
		dropIn: function(speed, callback){
			var $t = $(this);
			if($t.css("display") == "none"){
				eltop = $t.css('top');
				elouterHeight = $t.outerHeight(true);

				$t.css({ top: -elouterHeight, display: 'block' })
					.animate({ top: eltop },speed,'swing', callback);
			}
		}
	});
		
	$.prompt(message,{ show:'dropIn' });
}