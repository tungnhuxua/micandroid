<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>fullscreen</title>
<style type="text/css">
<!--
body,td,th {
	font-family: Courier New, Courier, monospace;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1a1a1a;
	font: .8em/1.3em verdana,arial,helvetica,sans-serif;
	text-align: center;
}
#media
	{
	   margin-top: 60px;
    }
-->
</style>
</head>
<body>
<div id="media"></div>
<div id="flashcontent" style="width:640px;height:498px;"></div>
<script language="javascript" src="js/swfobject.js"></script>
<script language="JavaScript">
	var orginFlash = {init:false,isFullScreen:false,position:"",top:"",left:"",width:"",height:""};
	function writeFlash(fileName){
		var so = new SWFObject("playframe.swf?swfname="+fileName, "fplayer", "640", "498", 8, "#FFFFFF");
		so.addParam("quality", "high");
		so.addParam("swLiveConnect", "true");
		so.addParam("menu", "false");
		so.addParam("allowScriptAccess", "sameDomain");
		so.addParam("allowFullScreen", "true");
		so.write("flashcontent");		
	}
	function getScreenSize(){
		var w = 0;
		var h = 0;
		if( typeof( window.innerWidth ) == 'number' ) {
			w = window.innerWidth;
			h = window.innerHeight;
		} else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
			w = document.documentElement.clientWidth;
			h = document.documentElement.clientHeight;
		} else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
			w = document.body.clientWidth;
			h = document.body.clientHeight;
		}
		return {width:w,height:h};
	}
	function fullScreen(){
		if(!orginFlash.init){			
			orginFlash.position = document.getElementById("flashcontent").style.position;
			orginFlash.top = document.getElementById("flashcontent").style.top;
			orginFlash.left = document.getElementById("flashcontent").style.left;
			orginFlash.width = document.getElementById("flashcontent").style.width;
			orginFlash.height = document.getElementById("flashcontent").style.height;
		}
		orginFlash.init = true;
		orginFlash.isFullScreen = true;
		var screenSize = getScreenSize();
		try{
			document.getElementById("flashcontent").style.position = "absolute";
			document.getElementById("flashcontent").style.top = "0px";
			document.getElementById("flashcontent").style.left = "0px";
			document.getElementById("flashcontent").style.width = screenSize.width +"px";
			document.getElementById("flashcontent").style.height = screenSize.height +"px";
			document.body.style.overflow="hidden";
			window.scrollTo(0,0);
		}catch(e){
		}
	}
	function normal(){
		if(orginFlash.init){
			orginFlash.isFullScreen = false;
			try{
				document.getElementById("flashcontent").style.position = orginFlash.position;
				document.getElementById("flashcontent").style.top = orginFlash.top;
				document.getElementById("flashcontent").style.left = orginFlash.left;
				document.getElementById("flashcontent").style.width = orginFlash.width;
				document.getElementById("flashcontent").style.height = orginFlash.height;
				document.body.style.overflow="auto";
			}catch(e){				
			}
		}
	}
	function reSize(){
	
		if(orginFlash.isFullScreen){
			fullScreen();
		}
	}
	//window.onresize = reSize;
	//writeFlash("3.swf");
</script>
</body>
<%
String fileName=request.getParameter("fileName");
out.print("<script language='JavaScript'>");

out.print("window.onresize = reSize;");

out.print("writeFlash('"+fileName+"');");
out.print("</script>");
 
 %>
</html>
