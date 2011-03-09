<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${requestScope.video}" var="video"></c:set>
<c:set value="${requestScope.videoType}" var="videoType"></c:set>
<c:set value="${requestScope.user}" var="user"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频审核</title>
<link rel="stylesheet" href="/video/manage/css/style.css" type="text/css" media="all" />
<!--[if IE 6]>
			<link rel="stylesheet" href="css/ie6-style.css" type="text/css" media="all" />
		<![endif]-->
</head>
<body>
<!-- Page -->
<div id="page" class="shell">
  <!-- Header -->
  <div id="header">
    <!-- Top Navigation -->
    <div id="top-nav">
       <ul>
        <li class="home"><a href="manageIndex.jsp">后台</a></li>
        <li>admin,欢迎您登录来到爱学乐园的后台</li>

      </ul>
    </div>
    <!-- / Top Navigation -->
    <div class="cl">&nbsp;</div>
    <!-- Logo -->
    <div id="logo">
      <h1><a href="#">爱学<span>乐园</span><span style="font-size: 15px;color: #E8D2E4;">后台管理</span></a></h1>
      <p class="description">your study zone</p>
    </div>
    <!-- / Logo -->
    <!-- Main Navigation -->
    <div id="main-nav">
      <div class="bg-right">
        <div class="bg-left">
          <ul>
            <li></li>
          </ul>
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
            <li class="first active first-active"><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;</a><span class="sep">&nbsp;</span></li>
            <li><a href="/video/manage/typeManage.jsp">视频类别管理</a><span class="sep">&nbsp;</span></li>
            <li><a href="/video/manage/videoCheckList.jsp">视频审核</a><span class="sep">&nbsp;</span></li>
            <li><a href="/video/manage/manageIndex.jsp">视频管理</a><span class="sep">&nbsp;</span></li>
            <li><a href="/video/manage/memberManage.jsp">会员管理</a><span class="sep">&nbsp;</span></li>
            <li><a href="/video/manage/payRequest.jsp">支付管理</a><span class="sep">&nbsp;</span></li>
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
          <div class="navs-bot">
          <div style="width: 20%;float: left;clear: left">&nbsp;</div>
          <div style="width: 60%;">
          <table class="styleTable">
          	<tr><td width="30%">类别名称：</td><td>${videoType.type_name}</td></tr>
          	<tr><td width="30%">上传用户： </td><td>${user.user_name}</td></tr>
          	<tr><td width="30%">视频积分： </td><td>${video.video_point}</td></tr>
          	<tr><td width="30%">搜索关键字： </td><td>${video.video_keywords}</td></tr>
          	<tr><td width="30%">是否收费：</td><td>	
          	<c:if test="${video.video_requirdMoney>0}">收费</c:if>
          	<c:if test="${video.video_requirdMoney<=0}">免费</c:if>
          	</td></tr>
          	<tr><td width="30%">播放次数： </td><td>${video.video_playcount}</td></tr>
          	<tr><td width="30%">视频大小：  </td><td>${video.video_size}</td></tr>
          	<tr><td width="30%">上传时间：  </td><td>
          	<fmt:formatDate value="${video.video_uploadtime}" var="uploadtime" pattern="yyyy-MM-dd HH:mm:ss"/>
          	${uploadtime}
          	</td></tr>
          	<tr><td width="30%">视频描述： </td><td>${video.video_desc}</td></tr>
          	<tr><td colspan="2"><a href="/video/manage/videoDetail.jsp?url=${video.video_url}">观看视频</a></td></tr>
          	<tr><td colspan="2"><input type="button" value="通过" onclick="javascript:window.location.href='/video/auditingVideoServlet.do?videoID=${video.video_id}&order=yes'"/>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<input type="button" value="拒绝" onclick="javascript:window.location.href='/video/auditingVideoServlet.do?videoID=${video.video_id}&order=no'"/></td></tr>
          </table>
          </div>
          <br/>
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
        <p class="copy">&copy; Design By Team One</p>
      </div>
      <!-- / Footer -->
    </div>
  </div>
  <!-- / Main -->
</div>
<!-- / Page -->
</body>
</html>
