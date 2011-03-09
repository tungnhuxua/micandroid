/**
 * @author 可乐加糖
 * @email  black.angel.liu@gmail.com
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
 $(document).ready(function(){
 	//票数
	$(".vottab").click(function(){
		changetab("votesweek,votesmonth",this.rel);
	})
 	//热点评论
 	$(".comtab").click(function(){
		changetab("commentsweek,commentsmonth",this.rel);
	})
	//热点资讯
	$(".hottab").click(function(){
		changetab("hotweek,hotmonth",this.rel);
	})
	
 })