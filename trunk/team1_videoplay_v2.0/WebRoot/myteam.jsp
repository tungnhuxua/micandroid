<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱学乐园</title>
<link rel="stylesheet" href="css/myteam.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/impromptu.css" type="text/css" media="all" />
<!--[if IE 6]>
			<link rel="stylesheet" href="css/ie6-style.css" type="text/css" media="all" />
		<![endif]-->
<script src="js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="js/login_regist.js" type="text/javascript"></script>
<script src="js/jquery-impromptu.3.1.js" type="text/javascript"></script>
</head>
<body>
<!-- Page -->
<div id="page" class="shell">
  <!-- Header -->
  <div id="header">
    <!-- Top Navigation -->
    <div id="top-nav">
     <ul>
        <li class="home"><a href="index.jsp">home</a></li>
        <c:if test="${empty sessionScope.userInfo}"><li><a><font color="gray">游客你好，欢迎您注册为本网站的会员</font></a></li></c:if>
        <c:if test="${!empty sessionScope.userInfo}"><li><a><font color="gray">${sessionScope.userInfo.user_name},欢迎您的到来</font></a></li></c:if>
 
      </ul>
    </div>
    <!-- / Top Navigation -->
    <div class="cl">&nbsp;</div>
    <!-- Logo -->
    <div id="logo">
      <h1><a href="#">爱学<span>乐园</span></a></h1>
      <p class="description">your study zone</p>
    </div>
    <!-- / Logo -->
    <!-- Main Navigation -->
    <div id="main-nav">
      <div class="bg-right">
        <div class="bg-left">
   <c:if test="${empty sessionScope.userInfo}">
         <ul>
            <li><a href="index.jsp">主页</a></li>
            <!-- ，视频信息修改，上传视频，申请付款，充值 -->
            <li><a class="button button-left login">登陆</a></li>
            <li><a class="button button-right regist">注册</a></li>
            <li><a href="myteam.jsp">关于我们</a></li>
          </ul>
        </c:if>
        <c:if test="${!empty sessionScope.userInfo}">
          <ul>
            <li><a href="index.jsp">主页</a></li>
            <!-- ，视频信息修改，上传视频，申请付款，充值 -->
            <li><a href="userPay.jsp">充值</a></li>
            <li><a href="payUser.jsp">申请提现</a></li>
            <li><a href="videoUpload.jsp">上传视频</a></li>
            <li><a href="showUserDetailServlet.do">个人资料修改</a></li>
            <li><a class="button button-left login" href="exitSystem.do">退出</a></li>
            <li><a href="myteam.jsp">关于我们</a></li>
          </ul>
          </c:if>
        </div>
      </div>
    </div>
    <!-- / Main Navigation -->
    <div class="cl">&nbsp;</div>
    <!-- Sort Navigation -->
    <div id="sort-nav">
      <div class="bg-right">
        <div class="bg-left">
          <div class="cl">&nbsp;</div>
     <ul>
            <li class="first active first-active"><a href="#">热门视频分类</a><span class="sep">&nbsp;</span></li>
            <c:forEach items="${videoType.list}" var="videoType" begin="0" end="5">
             <li><a href="/team1_videoplay_v2.0/videoListServlet.do?videoTypeID=${videoType.type_id}&page=1">${videoType.type_name}</a><span class="sep">&nbsp;</span></li>
            </c:forEach>
           
          </ul>
          <div class="cl">&nbsp;</div>
        </div>
      </div>
    </div>
    <!-- / Sort Navigation -->
  </div>
  <!-- / Header -->
  <!-- Main -->
  <div id="main">
    <div id="main-bot">
      <div class="cl">&nbsp;</div>
      <!-- 主要内容 -->
      <div id="myContent">
      
      	<div class="navs">
          <div class="navs-bot" align="center"><br/><br/>
          			<h2><font color="gray" face="华文行楷">我们的团队</font></h2><br/>
       			<font color="gray" color="white" size="3">&nbsp;&nbsp;&nbsp;
       			该在线视频点播系统是在尹召华同学的带领下<br/>
       			由吴铎、袁高明、黄琼、吴作卓、李智慧、吴<br/>
       			颖、纪日华、李炜平、刘诗诗、陆雅婷、黄煜<br/>
       			和黄杰华协作开发完成的。我们组由8男5女组<br/>
       			成，虽分工不同，却是配合默契。在这20天奋<br/>
       			斗的日子里，我们重视结果但也享受过程，不<br/>
       			仅感受了炎炎夏日的热情，感受了同伴相持的<br/>
       			温暖，亦感受了前所未有的自信。相信这次的<br/>
       			开发体验会伴随着我们，成为我们人生画卷上<br/>
       			华丽的一笔。<br/>
        		</font>
            <br/><br/>
          </div>
        </div>
      </div>
      <!-- 主要内容结束 -->
      <br/>
      <!-- Footer -->
      <div id="footer">
        <div class="navs">
          <div class="navs-bot">
            <div class="cl">&nbsp;</div>
           <ul>
              <li>@copy right 本网站权利私有</li>
            </ul>
            <ul>
            	<li>东华理工大学授权，team one项目组完成，感谢东华理工大学，感谢厦门万策的吴老师的指导</li>
            </ul>
            <div class="cl">&nbsp;</div>
          </div>
        </div>
        <p class="copy">&copy; Designed By  <a href="myteam.jsp">Ecit team one</a></p>
      </div>
      <!-- / Footer -->
    </div>
  </div>
  </div>
  <!-- / Main -->
<!-- / Page -->
</body>
</html>
