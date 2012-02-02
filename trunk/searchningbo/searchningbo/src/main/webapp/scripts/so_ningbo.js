
var ua = navigator.userAgent.toLowerCase();
var isAndroid = ua.indexOf("android") > -1;
if(isAndroid) {
window.location = '/android.jsp';
}

if((navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i))) {
   if (document.cookie.indexOf("iphone_redirect=false") == -1) window.location = "/ios.jsp";
}



// PAGE LOAD
$(document).ready(function() {
	
	
	$('#email1_btn').click(function(){


		$('#email1').animate({
		'height':'150px'
  		}, 500, function() {
    	
  		});
		$('#email1_btn').fadeOut(250);
		$('#table1').show();
		
		
		$('#email2').animate({
		'height':'70px'
  		}, 500, function() {
    	
  		});
		$('#email2_btn').fadeIn(250);
		$('#table2').hide();
		
		
});


$('#email2_btn').click(function(){


		$('#email2').animate({
		'height':'150px'
  		}, 500, function() {
    	
  		});
		$('#email2_btn').fadeOut(250);
		$('#table2').show();
		
		
		$('#email1').animate({
		'height':'70px'
  		}, 500, function() {
    	
  		});
		$('#email1_btn').fadeIn(250);
		$('#table1').hide();
});



	
	var ikeep_me = 0;
	
	$('#keeptrying').click(function(){
	


if (ikeep_me==0){
	
		$('.keep_me_cover').animate({
		'margin-left':'33px'
  		}, 300, function() {
    	ikeep_me = 1;
  		});
	

}


if (ikeep_me==1){

		$('.keep_me_cover').animate({
		'margin-left':'0px'
  		}, 300, function() {
    	ikeep_me = 0;
  		});
	
}

});
	



	
$('.check_email').delay(2000).animate({
		'height':'50px'
  		}, 1200, function() {
    	//Completed Anim
  		});

	
	$('#table1, #table2, #webcam_image').hide();

$('#sign_in_btn').click(function(){
  window.location = 'home.jsp';
});

$('#reg_signup_btn').click(function(){
  window.location = 'step1.jsp';
});

$('#step1_next_btn').click(function(){
	 window.location = 'step2.jsp';
	});	
	
$('#step2_back_btn').click(function(){
	 window.location = 'step1.jsp';
	});	
	
$('#step2_next_btn').click(function(){
	 window.location = 'step3.jsp';
	});	

$('#step3_back_btn').click(function(){
	 window.location = 'step2.jsp';
	});	
	
$('#step3_next_btn').click(function(){
	 window.location = 'home.jsp';
	});	

$('#forgot_password').click(function(){
	 window.location = 'forgot_password.jsp';
	});	

$('#index_btn').click(function(){
	 window.location = 'index.jsp';
	});	

$('#forgot_password_email_btn').click(function(){
	 window.location = 'forgot_password_email.jsp';
	});	





$('#image_upload').fileinput("<div style='float:right; margin-top:40px; width:100px' class='btn' id='upload_image_btn'>Upload File</div>");


$('#webcam_image_btn').click(function(){
	 $('#webcam_image').show();
	 $('#upload_image').hide();
	});	

$('#upload_image_btn').click(function(){
	 $('#webcam_image').hide();
	 $('#upload_image').show();
	});	



});



// PAGE RE-LOAD
$(window).resize(function() {	

		
});



	
	
	
