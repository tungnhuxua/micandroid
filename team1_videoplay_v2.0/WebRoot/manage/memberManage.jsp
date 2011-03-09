<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<%
	if(request.getAttribute("fenYe")==null){
		response.sendRedirect("../getAllMemberServlet.do?page=1");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台首页</title>
<link rel="stylesheet" href="/video/manage/css/style.css" type="text/css" media="all" />
<!--[if IE 6]>
			<link rel="stylesheet" href="css/ie6-style.css" type="text/css" media="all" />
		<![endif]-->
		<script type="text/javascript" src="./js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="./js/myjs.js"></script>
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
          	<h3 align="center">会员管理</h3>
          </div>
        </div>
     	<div class="navs">
          <div class="navs-bot">
          
          <table class="styleTable">
          	<tr class="styleTitleTr">
          	<td align="center">序号<br></td>
          	<td align="center">会员名</td>
          	<td align="center">等级</td>
          	<td align="center">邮箱</td>
          	<td align="center">注册时间</td>
          	<td align="center">最后登陆时间</td>
          	<td align="center">用户状态</td>
          	<td align="center">修改权限</td>
          	</tr>
    <c:forEach items="${fenYe.list}" var="member" varStatus="status">
    	 <tr>
          	<td align="center">${status.count}</td>
          	<td align="center">${member.user_name}</td>
          	<td align="center"><c:choose>
          		<c:when test="${member.user_point<=100}">
          			兵
          		</c:when>
          		<c:when test="${member.user_point>100&&member.user_point<=200}">
          			尉
          		</c:when>
          		<c:when test="${member.user_point>200&&member.user_point<=300}">
          			校
          		</c:when>
          		<c:otherwise>
          			将
          		</c:otherwise>
          	</c:choose></td>
          	<td align="center">${member.user_email}</td>
          	<td align="center">${member.user_registedate}</td>
          	<td align="center">
          		<c:if test="${empty member.user_lastlogindate}">尚未登录</c:if>
          		<c:if test="${!empty member.user_lastlogindate}">${member.user_lastlogindate}</c:if>
          	</td>
          	<td align="center">
          	<c:if test="${member.userStatus==0}">
          		正常
          	</c:if>
          	<c:if test="${member.userStatus==1}">
          		被冻结
          	</c:if>
          	</td>
      	<td align="center">

          	<c:if test="${member.userStatus==1}">
          		<a href="userStatusModifyServlet.do?id=${member.user_id}&tag=1"><font color="red">解冻</font></a>
          	</c:if>
          	  <c:if test="${member.userStatus==0}">
          		<a href="userStatusModifyServlet.do?id=${member.user_id}&tag=0">冻结</a>
          	</c:if>
          	&nbsp;&nbsp;&nbsp;&nbsp;<a href="userDeleteServlet.do?id=${member.user_id}">删除</a>
          </td>
          	</tr>
    </c:forEach>      	
          
          	
          </table>
          <br/> 
            <form action="getAllMemberServlet.do" id="dd" >
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
			
			    <td align="right">当前页数：[${fenYe.page}/${fenYe.pagecount}]&nbsp;
				<a href="getAllMemberServlet.do?page=1">第一页</a>　<a href="getAllMemberServlet.do?page=${fenYe.page>1 ? fenYe.page-1 : 1}">上一页</a>
				
				<a href="getAllMemberServlet.do?page=${fenYe.page<fenYe.pagecount?fenYe.page+1:fenYe.pagecount}">下一页</a>　<a href="getAllMemberServlet.do?page=${fenYe.pagecount}">最后一页&nbsp;</a>
				
				<span id="result"></span>
				<font color="blue">转到</font><input type="text" name="page" size="3" id="number"/><font color="blue">页</font><input type="submit" value="GO" onclick="checknum(${fenYe.pagecount})">
			</td>
			  </tr>
			</table>	
			</form>
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
        <p class="copy">&copy; Disign by Ecit Team One</p>
      </div>
      <!-- / Footer -->
    </div>
  </div>
  <!-- / Main -->
</div>
<!-- / Page -->
</body>
</html>
