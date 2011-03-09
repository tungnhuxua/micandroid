<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>首页_搜索</title>
		<link type="text/css" rel="stylesheet" href="css/newslist.css" />
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/ui.core.js"></script>
		<script type="text/javascript" src="js/ui.dialog.js"></script>
		<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="js/userlogin.js"></script>
		<script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript" src="js/newslist.js"></script>
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->

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
				<s:if test="adsArray.length>0">
					<s:if test="adsArray[0].type.name==1">
						<s:property value="adsArray[0].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[0].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[0].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[0].description'/>" />
						</a>
					</s:else>
				</s:if>
			</div>
			
			<div id="postion">
				<p>
					<span>您的位置:首页&gt;搜索</span>
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
				           <li>设计(1006)</li>
				           <li>素材(525)</li>
				           <li>插画(453)</li>
				           <li>创意(438)</li>
				           <li>广告(416)</li>
				           <li>摄影(347)</li>
				           <li>艺术设计(329)</li>
				           <li>艺术(227)</li>
				           <li>Photoshop</li>
				           <li>PHOTOSHOP</li>
				           <li>photoshop</li>
				           <li>WEB(179)</li>
				           <li>FLASH(198)</li>
				           <li>设计(1006)</li>
				         </ul>   
				     </div>
				     
				     <div id="searchNews_search">
					     <form action="${ctx }/searcharticle.action" id="searchnews1" method="post">
							<ul>
	                       	  <li class="firstLi">跳至&nbsp;&nbsp;<input type="text" value="" class="foot_seach" name="newsContent" /></li>
	                          <li><a style="margin-left: 5px" href="javascript:searchnews1.submit()"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                     </form>
						<div style="padding-left: 40px;padding-top: 20px;">
							<input type="radio" name="search" checked="checked" /><span>ooowo.com</span>
							<input type="radio" name="search" /><span>google</span>
						</div>
					</div>
				     
				</div>
				<div id="bannertop">
				   <s:if test="adsArray.length>0">
					<s:if test="adsArray[7].type.name==1">
						<s:property value="adsArray[7].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[7].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[7].image'/>" height="150px" width="600px" alt="<s:property value='adsArray[7].description'/>" />
						</a>
					</s:else>
				</s:if>
				</div>
				<div id="tabshow">
					<h3>约有${page.totalCount}项符合设计的查询,共${page.totalPages}页当前页为${page.pageNo}页 </h3>
					<ul>
					</ul>
				</div>
				
					<div id="tablist">
					<s:iterator value="page.result">
					<div class="news">
						<div class="newsleft">
							<a href="${ctx}/searcharticlecontent.action?article.id=${id}">
							<s:if test="imageUri!=null && imageUri != ''"><img src="${ctx}${imageUri}" alt="" />
							</s:if><s:else>
								<img src="images/newsimages.gif" alt="" />	
							</s:else>
							</a>					
						</div>
						
						<div class="newsright">
							<h3><a href="${ctx}/searcharticlecontent.action?article.id=${id}">${title }</a></h3>
							<h4><s:date name="createDate" format="yyyy-MM-dd"/>│  阅读_${readCount}  │  评论_<s:property value="comments.size"/>  │  投票_${voteCount }</h4>
							<p>
								<ouun:substring length="50" value="${content}"/>
							</p>
						</div>
					</div>
					</s:iterator>
					
					</div>
					<div style="padding-left:96px;padding-top: 20px;">
						第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="searcharticle.action?page.pageNo=${page.prePage}&newsContent=${newsContent}"">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="searcharticle.action?newsContent=${newsContent}&page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="searcharticle.action?page.pageNo=${page.nextPage}&newsContent=${newsContent}"">下一页</a>
						</s:if>
					</div>
								
				<div id="contentFoot">
					<div id="contentFoot_nav">
						<h3><span>导航&gt;</span><span>设计圈首页&gt;</span><span class="selectNav">搜索</span></h3>
						<ul>
						    <s:if test='action == "/news" || action == "/newslist" || action == "/newscontent" || action == "/newspiclist"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/newslist?showCategory.id=${id }"><span><s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
							<s:if test='action == "/person" || action == "/personlist" || action == "/personcontent" || action == "/personpiclist"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/personlist?showCategory.id=${id }"><span><s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
							<s:if test='action == "/show" || action == "/showlist" || action == "/showcontent"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/show?showCategory.id=${id }"><span><s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
						    <s:if test='action == "/special" || action == "/speciallist" || action == "/specialcontent" || action == "/specialpiclist"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/speciallist?showCategory.id=${id }"><span><s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
							<s:if test='action == "/circle" || action == "/circlelist" || action == "/circlecontent" || action == "/circlepiclist"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/circlelist?showCategory.id=${id }"><span><s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
						</ul>
					</div>
					<div id="contentFoot_seach">
						<form action="${ctx }/searcharticle.action" id="searchnews2" method="post">
							<ul>
	                       	  <li class="firstLi">跳至&nbsp;&nbsp;<input type="text" value="" class="foot_seach" name="newsContent" /></li>
	                          <li><a style="margin-left: 5px" href="javascript:searchnews2.submit()"><img src="images/seachbutton2.gif" alt="" /></a></li>
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
					<s:if test="adsArray.length>0">
						<s:if test="adsArray[1].type.name==1">
							<s:property value="adsArray[1].script" escapeJavaScript="false"/>
						</s:if><s:else>
							<a href="<s:property value='adsArray[1].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[1].image'/>" height="240px" width="345px" alt="<s:property value='adsArray[1].description'/>" />
							</a>
						</s:else>
					</s:if>
                </div>
               
                
                <div class="hotCommodity">
					<h3>Recommend_本周推荐书品</h3>
					<p>
						<s:if test="adsArray.length>3">
							<s:if test="adsArray[3].type.name==1">
								<s:property value="adsArray[3].script" escapeJavaScript="false" />
							</s:if>
							<s:else>
							<a href="<s:property value='adsArray[3].url'/>" target="_blank">
								<img src="${ctx }<s:property value='adsArray[3].image'/>"
									height="204px" width="204px"
									alt="<s:property value='adsArray[3].description'/>" />
							</a>
							</s:else>
						</s:if>

						<s:if test="adsArray.length>3">
							<s:if test="adsArray[4].type.name==1">
								<s:property value="adsArray[4].script" escapeJavaScript="false" />
							</s:if>
							<s:else>
							<a href="<s:property value='adsArray[4].url'/>" target="_blank">
								<img src="${ctx }<s:property value='adsArray[4].image'/>"
									height="204px" width="204px"
									alt="<s:property value='adsArray[4].description'/>" />
							</a>
							</s:else>
						</s:if>
					</p>
				</div>
				
				<div class="hotCommodity">
					<h3>Recommend book 热销商品</h3>
					<p>
						<s:if test="adsArray.length>3">
							<s:if test="adsArray[5].type.name==1">
								<s:property value="adsArray[5].script" escapeJavaScript="false" />
							</s:if>
							<s:else>
							<a href="<s:property value='adsArray[5].url'/>" target="_blank">
								<img src="${ctx }<s:property value='adsArray[5].image'/>"
									height="204px" width="204px"
									alt="<s:property value='adsArray[5].description'/>" />
							</a>
							</s:else>
						</s:if>
						
						<s:if test="adsArray.length>3">
							<s:if test="adsArray[6].type.name==1">
								<s:property value="adsArray[6].script" escapeJavaScript="false" />
							</s:if>
							<s:else>
							<a href="<s:property value='adsArray[6].url'/>" target="_blank">
								<img src="${ctx }<s:property value='adsArray[6].image'/>"
									height="204px" width="204px"
									alt="<s:property value='adsArray[6].description'/>" />
							</a>
							</s:else>
						</s:if>
					</p>
				</div>
				
                <div class="advtype3">
					<s:if test="adsArray.length>0">
					<s:if test="adsArray[2].type.name==1">
						<s:property value="adsArray[2].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[2].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[2].image'/>" height="600px" width="160px" alt="<s:property value='adsArray[2].description'/>" />
						</a>
					</s:else>
				</s:if>
				</div>
				<div class="clean"></div>
			</div>
        <div class="clean"></div>
        <div id="bannerFour">
           <s:if test="adsArray.length>0">
					<s:if test="adsArray[8].type.name==1">
						<s:property value="adsArray[8].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[8].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[8].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[8].description'/>" />
						</a>
					</s:else>
			</s:if>
        </div>
			<%@ include file="/common/foot.jsp"%>
		</div>
	</div>
	</body>
</html>