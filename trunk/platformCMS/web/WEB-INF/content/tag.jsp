<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>标签</title>
		<link type="text/css" rel="stylesheet" href="css/tag.css" />
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" />
		<script type="text/javascript" src="js/slide.js"></script>
		<script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
       	<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/ui.core.js"></script>
		<script type="text/javascript" src="js/ui.dialog.js"></script>
		<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="js/userlogin.js"></script>
	</head>
	<body>
	<%@ include file="/common/userlogin.jsp" %>
	<div id="container">
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
						<li id="linkreg" <s:if test='userName != "roleAnonymous" && userName != null'> style="display:none"</s:if>><a href="#"><span>注册</span></a></li>
						<li id="logout_login" <s:if test='userName != "roleAnonymous" && userName != null'> style="display:none"</s:if>><a href="#" onclick="showLogin();"><span class="u_login">登录</span></a></li>
						<li id="site_home"><a href="${ctx}"><span>设计圈首页</span></a></li>
						<s:if test='userName != "roleAnonymous" && userName != null'>
						<li>${userName}[<a href="#" style="margin-left:0px;" onclick="userlogout();">退出</a>]</li>
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
			        <li><a href="#"><span>_抢先看</span></a>|</li>
			        <li><a href="#"><span>_新闻动态</span></a>|</li>
			        <li><a href="#"><span>_平面视觉</span></a>|</li>
			        <li><a href="#"><span>_广告创意</span></a>|</li>
			        <li><a href="#"><span>_清晰卡通</span></a>|</li>
			        <li><a href="#"><span>_数码影像</span></a>|</li>
			        <li><a href="#"><span>_产品造型</span></a>|</li>
			        <li><a href="#"><span>_建筑空间</span></a>|</li>
			        <li><a href="#"><span>_艺术生活</span></a>|</li>
			        <li><a href="#"><span>_交互设计</span></a>|</li>
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
					<span>您的位置:首页&gt;标签</span>
				</p>
			</div>
			<div id="contentLeft">
				<div id="totop">
					<a href="#top" rel="">
						<img src="images/toTop.gif" alt="返回顶部" />
					</a>
				</div>
				<div id="searchNews">
				     <div id="searchNews_titile"><h2>HOT TAG _热门标签</h2></div>
				     <div id="searchNews_lie">
				     <ul>
				         <s:iterator value="hotTags">
				         	<li><a href="${ctx}/taglist.action?tag.name=${tag.name}">${tag.name}(${count})</a></li>
				         </s:iterator>
				         </ul>   
				     </div>
				     
				     <div id="searchNews_search">
					     <form action="${ctx }/searcharticle.action" name="searchnews2" method="post">
							<ul>
	                       	  <li class="firstLi">跳至&nbsp;&nbsp;<input class="indexss" type="text" value="" class="foot_seach" name="newsContent" /></li>
	                          <li><a style="margin-left: 5px" href="javascript:searchNews2();"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                     </form>
						<div style="padding-left: 40px;padding-top: 20px;">
							<input type="radio" name="seachtoTag" checked="checked" /><span>ooowo.com</span>
							<input type="radio" name="seachtoTag" /><span>google</span>
						</div>
					</div>
				     
				</div>
				<div id="bannertop">
				   
					
						<a href="" target="_blank">
						<img src="/ooowo" height="150px" width="600px" alt="左边广告一" />
						</a>
					
				
				</div>
				<div id="tabshow">
					<h3>ALL_所有标签_(共${page.totalCount }个标签) </h3>
					<ul>
					</ul>
				</div>
				
				<div id="tagUlList">
                   <ul>
                      <s:iterator value="page.result">
                      <li><a href="${ctx}/taglist.action?tag.name=${name}">${name}</a></li>
                      </s:iterator>
                   </ul>
                
                </div>	
			    
			    <div style="padding-left:50px;padding-top:40px;width:500px">
						第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="tag.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
						</s:if>
						<s:if test="page.hasNext">
							<a href="tag.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
						</s:if>
					</div>
			    			
				<div id="contentFoot">
					<div id="contentFoot_nav">
						<h3><span>导航&gt;</span><span>设计圈首页&gt;</span><span class="selectNav">标签</span></h3>
						<ul>

						</ul>
					</div>
					<div id="contentFoot_seach">
								<form action="${ctx }/searcharticle.action" name="searchnews1" method="post">
									<ul>
				                       	  <li class="firstLi"><input class="indexss" type="text" id="newsContent" name="newsContent" value="" class="foot_seach" /></li>
				                            <li><a href="javascript:searchNews1()"><img src="images/seachbutton2.gif" alt="" /></a></li>
				                        </ul>
			                    </form>
						<div>
							<input type="radio" name="seachto" checked="checked" /><span>ooowo.com</span>
                            <input type="radio" name="seachto" /><span>google</span>
						</div>
                        <div class="firstP">
                        	<span>你的足迹 登入注册你的免费账号</span>
                        </div>
                        <div id="contentFoot_tags">
                                <a href="#"><span>发现</span></a>
                                <a href="#"><span>资讯</span></a>
                                <a href="#"><span>分享</span></a>
                                <a href="#"><span>下载</span></a>
                                <a href="#"><span>blog</span></a>
                                <a href="#"><span>本周</span></a>
                                <a href="#"><span>本月</span></a>
                                <a href="#"><span>热门标签</span></a>
                                <a href="#"><span>会员</span></a>
                                <a href="#"><span>搜索</span></a>
                                <a href="#"><span>发现</span></a>
                                <a href="#"><span>设计圈</span></a>
                                <a href="#"><span>ooowo小帮手</span></a>
                                <a href="#"><span>社区指南</span></a>
                                <a href="#"><span>说明讨论区</span></a>
                                <a href="#"><span>常见问题</span></a>
                                <a href="#"><span>帮助</span></a>
                          </div>
					</div>
				</div>
				
			</div>
			<div id="rightContent">
				<div class="rightContent_top_ad_345-240">
					
						
							<a href="" target="_blank">
							<img src="/ooowo" height="240px" width="345px" alt="右边广告一" />
							</a>
						
					
                </div>
               
                
                <div class="hotCommodity">
					<h3>Recommend_本周推荐书品</h3>
					<p>
						
							
							
							<a href="" target="_blank">
								<img src="/ooowo"
									height="204px" width="204px"
									alt="推荐书品1" />
							</a>
							
						

						
							
							
							<a href="" target="_blank">
								<img src="/ooowo"
									height="204px" width="204px"
									alt="推荐书品2" />
							</a>
							
						
					</p>
				</div>
				
				<div class="hotCommodity">
					<h3>Recommend book 热销商品</h3>
					<p>
						
							
							
							<a href="" target="_blank">
								<img src="/ooowo"
									height="204px" width="204px"
									alt="热销商品1" />
							</a>
							
						
						
						
							
							
							<a href="" target="_blank">
								<img src="/ooowo"
									height="204px" width="204px"
									alt="热销商品2" />
							</a>
							
						
					</p>
				</div>
				
                <div class="advtype3">
					
					
						<a href="" target="_blank">
						<img src="/ooowo" height="600px" width="160px" alt="右边长条广告" />
						</a>
					
				
				</div>
				<div class="clean"></div>
			</div>
        <div class="clean"></div>
        <div id="bannerFour">
           
					
						<a href="" target="_blank">
						<img src="/ooowo" height="100px" width="960px" alt="footer" />
						</a>
					
			
        </div>
				<div class="didaohang">
			      <ul>
			        <li><a href="">首页</a>|</li>
			        <li><a href="">关于我们|</a>|</li>
			        <li><a href="">版权声明</a>|</li>
			        <li><a href="">广告投放</a>|</li>
			        <li><a href="">联系我们</a>|</li>
			        <li><a href="">友情链接</a>|</li>
			        <li><a href="">招聘信息</a>|</li>
			        <li><a href="">理事单位</a>|</li>
			        <li><a href="">帮助</a>|</li>
			        <li><a href="">在线客服</a>|</li>
			        <li><a href="">blog</a></li>
			      </ul>
			    </div>
				<div class="footer">
			      <div class="dilogo"><img src="images/jhpic.jpg" /></div>
			      <div class="xinxi">Powered by 2007-ooowo.com.Visual design by snhoo.All Rights Reserved ooowo.com<br />
			        ICP备案号、粤ICP备07020707号</div>
			    </div>
		</div>
	</div>
	</body>
</html>