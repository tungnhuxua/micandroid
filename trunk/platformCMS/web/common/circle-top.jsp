<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
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
						<%@include file="/common/circle-head-nav.jsp" %>	
			        </ul>
			      </div>
			    </div>
				<!-- 搜索容器 -->
				<div class="zuonav">
				 	<div class="youdh">
					<ul>
					  <s:if test='userName != "roleAnonymous" && userName != null'>
					 	 <li><a href="${ctx}/j_spring_security_logout">退出</a></li>
					  </s:if>
					  <s:else>
					  	<li id="linkreg" ><a href="javascript:toregister();"><span>注册</span></a></li>
					  </s:else>
					  
					  <s:if test='userName != "roleAnonymous" && userName != null'>
					  	 <li id="linkreg" ><a href="blog/member-home.action"><span style="font-weight: bold;color: #83C325;">${member.name}'s blog</span> </a>&nbsp;&nbsp;|</li>
					  </s:if>
					  <s:else>
					  <li id="logout_login"><a href="#" onclick="showLogin();">登录&nbsp;&nbsp;|</a></li>
					  </s:else>
					  <li id="site_home"><a href="${ctx}">首页&nbsp;&nbsp;|</a></li>
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
			      	 <%@include file="/common/circle-nav.jsp" %>
			      </ul>
			    </div>
			  </div>
			<div class="clean"></div>
		</div>