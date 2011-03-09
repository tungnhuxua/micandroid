<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
<link type="text/css" href="css/login.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="css/style2.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ui.core.js"></script>
<script type="text/javascript" src="js/ui.dialog.js"></script>
<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="js/userlogin.js"></script>
<%@ include file="/common/userlogin.jsp" %>

<div id="top">
			<div class="headernb">
				<div class="jhlogo">
					<a href="${ctx}">
						<img src="images/logo.gif" alt="这里写公司的信息，方便搜索引擎搜索" />
					</a>
				</div>
				 <div class="dibunav">
			      <div class="riqi"><script type="text/javascript" src="js/time.js"></script></div>
			      <div class="topnav">
			       <ul>
						<li><a href="news"><span <s:if test='action == "/news" || action == "/newslist" || action == "/newscontent" || action == "/newspiclist" || action == "/newssearcharticle"'>class="selectNav"</s:if>>_资讯</span></a></li>
						<li><a href="person"><span <s:if test='action == "/person" || action == "/personlist" || action == "/personcontent" || action == "/personpiclist" || action == "/personsearcharticle"'>class="selectNav"</s:if>>_人物</span></a></li>
						<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=all"><span <s:if test='action == "/show" || action == "/showlist" || action == "/showcontent" || action == "/showsearcharticle"'>class="selectNav"</s:if>>_秀场</span></a></li>
						<li><a href="special"><span <s:if test='action == "/special" || action == "/speciallist" || action == "/specialcontent" || action == "/specialpiclist" || action == "/specialsearcharticle"'>class="selectNav"</s:if>>_专题</span></a></li>
						<li><a href="circle"><span <s:if test='action == "/circle" || action == "/circlelist" || action == "/circlecontent" || action == "/circlepiclist" || action == "/circlesearcharticle"'>class="selectNav"</s:if>>_圈网</span></a></li>
						<li><a href="download"><span <s:if test='action == "/download" || action == "/downloadcontent"'>class="selectNav"</s:if>>_下载</span></a></li>
						<li><a href="${ctx}/circle-web.action"><span>_江湖</span></a></li>
						<li><a href="#"><span>_商店</span></a></li>
						<li><a href="#"><span>_书店</span></a></li>					
					</ul>
			      </div>
			    </div>
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
			<div class="hd">
				<div class="navbh">
					<ul>
					    <s:if test='action == "/news" || action == "/newslist" || action == "/newscontent" || action == "/newspiclist" || action == "/newssearcharticle"'>
							<s:iterator value="category.child" status="stat">
								<s:if test="#stat.index<14">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/newslist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:if>
							</s:iterator>
						</s:if>
						<s:if test='action == "/person" || action == "/personlist" || action == "/personcontent" || action == "/personpiclist" || action == "/personsearcharticle.action"'>
							<s:iterator value="category.child" status="stat">
								<s:if test="#stat.index<14">
								<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/personlist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:if>
							</s:iterator>
						</s:if>
						<s:if test='action == "/show" || action == "/showlist" || action == "/showcontent" || action == "/showsearcharticle"'>
							<s:iterator value="category.child" status="stat">
								<s:if test="#stat.index<14">
								<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/show?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:if>
							</s:iterator>
						</s:if>
					    <s:if test='action == "/special" || action == "/speciallist" || action == "/specialcontent" || action == "/specialpiclist" || action == "/specialsearcharticle"'>
							<s:iterator value="category.child" status="stat">
							<s:if test="#stat.index<14">
								<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/speciallist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:if>
							</s:iterator>
						</s:if>
						<s:if test='action == "/circle" || action == "/circlelist" || action == "/circlecontent" || action == "/circlepiclist" || action == "/circlesearcharticle"'>
							<s:iterator value="category.child" status="stat">
								<s:if test="#stat.index<14">
								<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/circlelist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:if>
							</s:iterator>
						</s:if>
					</ul>
				</div>
			</div>
			<div class="clean"></div>
		</div>