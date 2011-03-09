<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %> 
<%
	if(request.getAttribute("fenYe")==null){
		response.sendRedirect("../getAllPayUser.do?page=1");
		return;
	}	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台用户管理</title>
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
             <div class="navs">
          <div class="navs-bot">
          	<h3 align="center">支付管理</h3>
          </div>
        </div>
          <table class="styleTable">
          	<tr class="styleTitleTr">
          	<td>序号</td>
          	<td>会员名</td>
          	<td> 申请金额</td>
          	<td>申请时间</td>
          	<td>支付帐号 </td>
          	<td>会员邮箱</td>
          	<td>支付状态</td>
          	<td>操作</td>
          	</tr>
          	
       <c:forEach items="${fenYe.list}" var="payUser" varStatus="status">
       <tr>
          	<td>${status.index+1}</td>
          	<td>${userList[status.index].user_name}</td>
          	<td>${payUser.payuserApplymoney}</td>
          	<td><fmt:formatDate value="${payUser.payuserApplytime}" var="payuserApplytime" pattern="yyyy-MM-dd HH:mm:ss"/>
    						${payuserApplytime}</td>
          	
          	<td>${userList[status.index].user_account}</td>
          	<td>${userList[status.index].user_email}</td>
          	<td>
          	<c:if test="${payUser.payuserState==0}"><font color="red">尚未支付</font></c:if>
          	<c:if test="${payUser.payuserState==1}">支付完毕</c:if>
          	</td>
          	<td>
          		<c:if test="${payUser.payuserState==0}">
					<a href="payUserDetail.do?id=${payUser.payuserId}">支付</a>
				</c:if>
          	<c:if test="${payUser.payuserState==1}">无操作</c:if>
          	</td>
          
          	</tr>
       </c:forEach>   	
          	
          	
          	
          </table>
          <br/>
           <form action="getAllPayUser.do" id="dd" >
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
			
			    <td align="right">当前页数：[${fenYe.page}/${fenYe.pagecount}]&nbsp;
				<a href="getAllPayUser.do?page=1">第一页</a>　<a href="getAllPayUser.do?page=${fenYe.page>1 ? fenYe.page-1 : 1}">上一页</a>
				
				<a href="getAllPayUser.do?page=${fenYe.page<fenYe.pagecount?fenYe.page+1:fenYe.pagecount}">下一页</a>　<a href="getAllPayUser.do?page=${fenYe.pagecount}">最后一页&nbsp;</a>
				
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
