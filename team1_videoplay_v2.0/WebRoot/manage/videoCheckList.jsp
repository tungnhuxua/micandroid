<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
<c:when test="${!empty requestScope.allCheckVideo}">
<c:set var="allCheckVideo" value="${requestScope.allCheckVideo}" scope="page"></c:set>
<c:set var="videoTypeList" value="${requestScope.videoTypeList}" scope="page"></c:set>
<c:set var="userList" value="${requestScope.userList}" scope="page"></c:set>
</c:when>
<c:otherwise><jsp:forward page="/getAllCheckVideoServlet.do?page=1"></jsp:forward></c:otherwise>
</c:choose>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台用户管理</title>
<link rel="stylesheet" href="/video/manage/css/style.css" type="text/css" media="all" />
<!--[if IE 6]>
			<link rel="stylesheet" href="css/ie6-style.css" type="text/css" media="all" />
		<![endif]-->
		<script type="text/javascript" src="/video/JS/jquery-1.4.2.js"></script>
<script type="text/javascript" src="/video/JS/myjs.js"></script>
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
            <h3 align="center">视频审核</h3>
          <form action="/video/searchCheckVideoServlet.do?page=1" method="post"> 
          	视频： 
          	<input type="text" name="keyword"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<input type="submit" value="搜索"/>
          	</form>
          </div>
        </div>
      	<div class="navs">
          <div class="navs-bot">
          
          <table class="styleTable">
          	<tr class="styleTitleTr">
          	<td>编号</td>
          	<td>视频标题</td>
          	<td>上传用户</td>
          	<td>是否收费</td>
          	<td>类别</td>
          	<td>关键字</td>
          	<td>视频大小</td>
          	<td>操作</td>
          	</tr>
          	 <c:forEach items="${allCheckVideo.list}" var="allCheckVideo" varStatus="status">
          	<tr>
          	<td>${status.index+1}</td>
          	<td>${allCheckVideo.video_title}</td>
          	<td>${userList[status.index].user_name}</td>
          	<td>
          	<c:if test="${allCheckVideo.video_point>0}">收费</c:if>
          	<c:if test="${allCheckVideo.video_point<=0}">免费</c:if>
          	</td>
          	<td>${videoTypeList[status.index].type_name}</td>
          	<td>${allCheckVideo.video_keywords}</td>
          	<td>${allCheckVideo.video_size}</td>
          	<td>
        	  <a href="/video/checkVideoAgoServlet.do?videoID=${allCheckVideo.video_id}">审核</a>
          	</td>
          	</tr>
          	</c:forEach>
          </table>
          <br/>
            <c:if test="${!empty requestScope.flag}">
          <c:set value="${requestScope.flag}" var="flag"></c:set>
          <c:set value="${requestScope.keyword}" var="keyword"></c:set>
          </c:if>
        
          <c:choose><c:when test="${flag==1}">
            <!-- 搜索的分页 -->
            
         	<form action="/video/searchCheckVideoServlet.do" id="dd" >
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
			
			    <td align="right">当前页数：[${allCheckVideo.page}/${allCheckVideo.pagecount}]&nbsp;
				<a href="/video/searchCheckVideoServlet.do?page=1&keyword=${keyword}">第一页</a>　<a href="/video/searchCheckVideoServlet.do?page=${allCheckVideo.page>1 ? allCheckVideo.page-1 : 1}&keyword=${keyword}">上一页</a>
				<input type="hidden" name="keyword" value="${keyword}"/>
				<a href="/video/searchCheckVideoServlet.do?page=${allCheckVideo.page<allCheckVideo.pagecount?allCheckVideo.page+1:allCheckVideo.pagecount}&keyword=${keyword}">下一页</a>　<a href="/video/searchCheckVideoServlet.do?page=${allCheckVideo.pagecount}&keyword=${keyword}">最后一页&nbsp;</a>
				<span id="result"></span>
				<font color="blue">转到</font><input type="text" name="page" size="3" id="number"/><font color="blue">页</font><input type="submit" value="GO" onclick="checknum(${allCheckVideo.pagecount})">
			</td>
			  </tr>
			</table>	
			</form></c:when>
          <c:otherwise>
               <!-- 普通的分页 -->
   			<form action="/video/getAllCheckVideoServlet.do" id="dd" >
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
			
			    <td align="right">当前页数：[${allCheckVideo.page}/${allCheckVideo.pagecount}]&nbsp;
				<a href="/video/getAllCheckVideoServlet.do?page=1">第一页</a>　<a href="/video/getAllCheckVideoServlet.do?page=${allCheckVideo.page>1 ? allCheckVideo.page-1 : 1}">上一页</a>
				
				<a href="/video/getAllCheckVideoServlet.do?page=${allCheckVideo.page<allCheckVideo.pagecount?allCheckVideo.page+1:allCheckVideo.pagecount}">下一页</a>　<a href="/video/getAllCheckVideoServlet.do?page=${allCheckVideo.pagecount}">最后一页&nbsp;</a>
				
				<span id="result"></span>
				<font color="blue">转到</font><input type="text" name="page" size="3" id="number"/><font color="blue">页</font><input type="submit" value="GO" onclick="checknum(${allCheckVideo.pagecount})">
			</td>
			  </tr>
			</table>	
			</form>
          </c:otherwise>
          </c:choose>
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
