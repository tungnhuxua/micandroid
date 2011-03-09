<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script language="javascript">
   function search_NewTitle(){
	   var newstitle = document.search.newsContent.value;
    if(document.all['seachto'][1].checked){
	 window.location.href='http://www.google.com/search?hl=zh-CN&inlang=zh-CN&ie=GB2312&lr=lang_zh-CN&q=';
	}else{
		if(newstitle.length<1){
		 alert("请您输入要搜索的新闻内容");
		}else{
			document.search.submit(); 
		}
	}
   }
</script>
<div class="qytiaozhuang">
               <form action="${ctx }/searcharticle.action" id="search" method="post" name="search">
				  <input type="text" class="bitiankuang" name="newsContent"/>
				  <input type="button" onclick="javascript:search_NewTitle();" class="bianqianan" name="biaoqian"/>
				</form>
				</div>
				<div class="qyxuanxiang"><input type="radio" name="seachto" checked="checked" />ooowo.com    <input type="radio" name="seachto" />Google</div>
				<!--  <div class="qyzuji">_你的足迹  <a href="">登入</a><a href="">_注册</a>你的免费帐号</div>-->
				<div class="faxian">
					<ul>
						<li>_发现 </li>
						<li><a href="#">资讯</a>|</li>
						<li><a href="#">分享</a>|</li>
						<li><a href="#">下载</a>|</li>
						<li><a href="#">blog</a>|</li>
						<li><a href="#">本周</a>|</li>
						<li><a href="#">本月</a>|</li>
						<li><a href="#">热门标签</a>|</li>
						<li><a href="#">会员</a>|</li>
						<li><a href="#">搜索</a></li>
					</ul>
				</div>
				<div class="qyfaxian">
					<ul>
						<li>_设计圈ooowo小帮手 </li>
						<li><a href="#">社群指南</a>|</li>
						<li><a href="#">说明讨论区</a>|</li>
						<li><a href="#">常见问题</a>|</li>
						<li><a href="#">网站地图</a>|</li>
						<li><a href="#">帮助</a>|</li>
					</ul>
				</div>
