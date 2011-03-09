<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>设计圈_用户注册成功</title>
		<link type="text/css" rel="stylesheet" href="css/member_register.css" />
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" />
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
       	<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/ui.core.js"></script>
		<script type="text/javascript" src="js/ui.dialog.js"></script>
		<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="js/userlogin.js"></script>
	</head>
	<body>
	<%@ include file="/common/userlogin.jsp" %>
	<div class="container">
	<div id="top">
			<!-- 顶部的容器 -->
			<div class="headernb">
				<!-- 放logo的地方 -->
				<div class="jhlogo">
					<a href="${ctx}">
						<img src="images/logo.gif" alt="这里写公司的信息，方便搜索引擎搜索" />
					</a>
				</div>
			    <div class="dibunav">
			      <div class="riqi"><script type="text/javascript" src="js/time.js"></script></div>
			      <div class="topnav">
			        <ul>
			        	<li><a href="news"><span>_资讯</span></a></li>
						<li><a href="person"><span>_人物</span></a></li>
						<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=all"><span>_秀场</span></a></li>
						<li><a href="special"><span>_专题</span></a></li>
						<li><a href="circle"><span>_圈网</span></a></li>
						<li><a href="download"><span>_下载</span></a></li>
						<li><a href="${ctx}/circle-web.action"><span>_江湖</span></a></li>
						<li><a href="#"><span>_商店</span></a></li>
						<li><a href="#"><span>_书店</span></a></li>	
			        </ul>
			      </div>
			    </div>
				<!-- 搜索容器 -->
				<div class="zuonav">
				 	<div class="youdh">
					<ul>
						
						<li><a href="#"><span>全站导航</span></a></li>
						<li id="linkreg" <s:if test='userName != "roleAnonymous" && userName != null'> style="display:none"</s:if>><a href="javascript:toregister();"><span>注册</span></a></li>
						<li id="logout_login" <s:if test='userName != "roleAnonymous" && userName != null'> style="display:none"</s:if>><a href="#" onclick="showLogin();"><span class="u_login">登录</span></a></li>
						<li id="site_home"><s:if test='userName != "roleAnonymous" && userName != null'><span id='blogcenter'><a href="blog/member-home.action">个人中心</a></span></s:if><s:else><a href="${ctx}"><span id="ooowoindex">设计圈首页</span></a></s:else></li>
						<s:if test='userName != "roleAnonymous" && userName != null'>
						<li id="logout">${userName}[<a href="#" style="margin-left:0px;" onclick="userlogout();">退出</a>]</li>
						</s:if>
					</ul>
					</div>
					<div class="ss">
						<form action="${ctx }/searcharticle.action" name="searchnews" method="post">
                        	<input type="text" name="newsContent"  class="indexss"/>
                        	<input name="biaoqian" type="button" class="bianqianan" onclick="searchNews()"/>
                       </form>
                    </div>
				</div>
			</div>
			<!-- 导航条 -->
			<div class="hd">
			    <div class="navbh">
			      <ul>
						<li><a href="${ctx}/newslist.action?showCategory.id=59"><span>_抢先看</span></a></li>
						<li><a href="${ctx}/personlist.action?showCategory.id=70"><span>_色女郎</span></a></li>	
						<li><a href="#"><span>_LOGO</span></a></li>	
						<li><a href="#"><span>_图库</span></a></li>	
						<li><a href="#"><span>_招聘</span></a></li>	
						<li><a href="${ctx}/speciallist.action?showCategory.id=76"><span>_杂志</span></a></li>	
						<li><a href="#"><span>_博客</span></a></li>	
						<li><a href="#"><span>_论坛</span></a></li>	
						<li><a href="#"><span>_群英会</span></a></li>	
						<li><a href="${ctx}/tag"><span>_标签TAG</span></a></li>	
						<li><a href="#"><span>_平面</span></a></li>	
						<li><a href="#"><span>_创意</span></a></li>	
						<li><a href="#"><span>_卡通</span></a></li>	
						<li class="lilaster"><a href="${ctx}/circlelist.action?showCategory.id=80"><span>_艺术</span></a></li>
			      </ul>
			    </div>
			  </div>
			<div class="clean"></div>
		</div>

		<div id="content">
		
			<div id="topBanner">
						<a href="" target="_blank">
						<img src="/ooowo" height="100px" width="960px" alt="top_banner" />
						</a>
			</div>
			
			<div id="postion">
				<p>
					<span>您的位置:首页&gt;注册</span>
				</p>
				<hr class="splitline"/>
			</div>
			
			<div style="width:830px; margin-top:40px;float:right;" >
                <div class="bqmainbh" align="left">
                    <div>
                    <span class="bigsize">Regisster new member_注册新用户</span><br />
                    <span class="bigsize">欢迎来到中国新锐设计师创意集会网站</span></div>
                </div>
				<div class="gongxi" align="left">恭喜你！注册成功,<br /><br />请登陆你的注册邮箱(${member.loginName})查收邮件并激活帐号。
				</div>
            </div>
        <div class="clean"></div>
        <div id="bannerFour">
           
					
						<a href="" target="_blank">
						<img src="/ooowo" height="100px" width="960px" alt="footer" />
						</a>
					
			
        </div>
			<%@ include file="/common/foot.jsp"%>
		</div>
	</div>
	</body>
</html>