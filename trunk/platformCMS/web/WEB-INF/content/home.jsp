<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>首页</title>
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" />

		<link type="text/css" rel="stylesheet" href="css/shouye.css" />
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
		<script type="text/javascript" src="js/slide.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</head>
	<body>
	<%@ include file="/common/userlogin.jsp" %>
		<!-- top -->
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
		<!-- content -->
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
					<span>您的位置:首页&gt;</span>
				</p>
			</div>
			
			<div id="contentLeft">
				<div id="totop">
					<a href="#top" rel="">
						<img src="images/toTop.gif" alt="返回顶部" />
					</a>
				</div>
				<!-- 滚动广告 -->
				<div id="advertisement">
					

					<div id="topstory">
						<div id="highlight">
							<div id="featured">
								<!--标签开始 -->
								
								<s:iterator value="siteHomeArticle" status="sta">
								<div class="image" id="image_xixi-<s:property value='#sta.index+1'/>">
									<a title="${title }" <s:if test="uri!=null && uri!=''">href="${uri}"</s:if><s:else>href="${ctx}/searcharticlecontent.action?article.id=${id}"</s:else> target="_blank">
									<img alt="${title }" src="${ctx }${imageLink}" />
									</a>
									<div class="word">
									<h3>${title }</h3>
									<p><ouun:substring length="100" value="${content}"/></p>
									</div>
								</div>
								</s:iterator>
							</div>
							<div id="thumbs">
								<ul>
								 
								  <s:iterator value="siteHomeArticle" status="sta">
								  <li class="slideshowItem"><a id="thumb_xixi-<s:property value='#sta.index+1'/>" 
								  href="#image_xixi-<s:property value='#sta.index+1'/>"><img height="20" 
								  src="${ctx }${imageUri }" 
								  width="48" alt="" /></a></li>
								  </s:iterator>
								</ul>
							
							</div>
							<script type="text/javascript" src="js/adv.js"></script>
						</div>
					</div>
				</div>

                <!-- 当前热门文章 begin -->
                
                <div id="hot_essay">
                       <h2>Hot_当前热门文章</h2>
                    <div id="hot_essay_content">
                      <s:iterator value="hotArticle">
                        <div id="hot_essay_content_detalied">
                        <a class="coverBg" href="${ctx}/searcharticlecontent.action?article.id=${id}"><p>(查看全部)</p></a>
                            <s:if test="imageUri!=null && imageUri != ''">
                            	<img src="${ctx}${imageUri }" width="140" height="140"/>
                            </s:if><s:else>
                                <img src="images/hot_essay.jpg" width="140" height="140" />	
                                </s:else>
                           <p>&nbsp;</p>
                           <p><a href="${ctx}/searcharticlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="10" value="${title }"/></a></p>
                           <p>投票_${voteCount }</p>
                           <p>阅读_${readCount}</p>
                        </div>
                      </s:iterator>
                    </div>
                
                </div>
				<!-- 当前热门文章 end -->	
                
                <!--文章列表begin-->
                 <div id="recommend">
                    <div id="all_title">
                       <h2>Recommend_精彩推荐</h2><a href="#"></a>
                    </div>
                 </div>
                 
                    <div id="tablist">
                    <s:iterator value="recommendArticle">
                        <div class="news">
                            <div class="newsleft">
                            <a class="coverBg80" href="${ctx}/searcharticlecontent.action?article.id=${id}"><p>(查看全部)</p></a>
                            <s:if test="imageUri!=null && imageUri != ''">
                            	<img src="${ctx}${imageUri }" />
                            </s:if><s:else>
                                <img src="images/newsimages.gif" alt="" />	
                                </s:else>
                            </div>
                            
                            <div class="newsright">
                                <h3><a href="${ctx}/searcharticlecontent.action?article.id=${id}">${title }</a></h3>
                                <h4><s:date name="createDate" format="yyyy-MM-dd"/>  │  阅读_${readCount}  │  评论_<s:property value="comments.size"/>  │  投票_${voteCount }</h4>
                                <p><ouun:substring length="50" value="${content}"/></p>
                            </div>
                        </div>
                                               
                        </s:iterator>
                                                
                    </div>
                    
                
                <!--文章列表end-->	
				<!-- 人物访谈开始 -->
              
                
                 
                <div class="userlist">
                     <div id="all_title">
                           <h2>Talk_人物访谈</h2><a href="${ctx}/person">>更多_more</a>
                     </div>
                     <div>
                        <ul>
                           <s:iterator value="NewPersonArticle">
                           <li>
                            <a class="coverBg110" href="${ctx}/searcharticlecontent.action?article.id=${id}"><p>(查看全部)</p></a>
	                           <s:if test="imageUri!=null && imageUri != ''">
	                           
	                           		<img width="110" height="110" src="${ctx}${imageUri}" alt="" title="${title }"/>
	                           </s:if><s:else>
	                               <img src="images/renwu.gif" width="110" height="110" alt="人物访谈" />
	                           </s:else>
						  </li>
						   </s:iterator>
							
						</ul>    
                     </div>
                </div>
				<!-- 人物访谈结束 -->
                
                <div id="banner">
                   <s:if test="adsArray.length>2">
					<s:if test="adsArray[2].type.name==1">
						<s:property value="adsArray[2].script" escapeJavaScript="false"/>
					</s:if><s:else>
					  <a href="<s:property value='adsArray[2].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[2].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[2].description'/>" />
					  </a>
					</s:else>
				</s:if><s:else>
                   <img src="${ctx }/images/banner.jpg"/>
                   </s:else>
				</div>
                
                <!-- 精彩博文 begin -->
                
                <div id="blog">
                   <div id="all_title"><h2>NEW BLOG_精彩博文</h2><a href="#">>更多_more</a></div>
                   
					    <div id="tablist">
					    <s:iterator value="blogTopArticle">
                        <div class="news">
                            <div class="newsleft">
                            <a class="coverBg80" href="${ctx}/blog/blog-content.action?id=${id}&&tomember.id=${member.id}"><p>(查看全部)</p></a>
                            <s:if test="imageUri!=null && imageUri != ''">
                            	<img src="${ctx}${imageUri }" />
                            </s:if><s:else>
                                <img src="images/newsimages.gif" alt="" />	
                                </s:else>
                            </div>
                            
                            <div class="newsright">
                                <h3><a href="${ctx}/blog/blog-content.action?id=${id}&&tomember.id=${member.id}">${title }</a></h3>
                                <h4><s:date name="createDate" format="yyyy-MM-dd"/>  │  阅读_${readCount}  │  评论_<s:property value="comments.size"/>  │  投票_${voteCount }</h4>
                                <p><ouun:substring length="50" value="${content}"/></p>
                            </div>
                        </div>
                                               
                        </s:iterator>
            
                    </div>
                </div>
                
                <!-- 精彩博文 end-->
                
                <!-- 群英会 begin-->
                  <div id="group">
                       <div id="all_title"><h2>GROUP_群英会_热门圈子</h2><a href="${ctx}/circle-web.action?getState=4">>更多_more</a></div>
                       <div class="group_list">
                          <div>
                            <ul>
                              <s:iterator value="circleTopList" id="obj">
                                <li>
                                 <a class="coverBg90"  href="#" alt="${obj[1]}"><p>(查看全部)</p></a>
                                  <s:if test="#obj[8]!=null && #obj[8]!=''">
                                     <img src="${ctx}${obj[8]}" alt="${obj[1]}" />
                                  </s:if><s:else>
                                   <img src="images/renwu.gif" alt="${obj[1]}" />
                                  </s:else>
                                
                                </li>
                              </s:iterator>
                            </ul>    
                         </div>
                       </div>
                  </div>     
                 
                <!--  群英会 end-->
                
                <!--  专拦博客  begin-->
                 <div id="columns_bolg">
                       <div id="all_title"><h2>Columns BLOG_专拦博客</h2><a href="${ctx }/circle-member-columns.action">>更多_more</a></div>
                       <div class="group_list">
                          <div>
                            <ul>
                               
                             <s:iterator value="columnsPage.result">
                            	<li><a class="coverBg90"  href="${ctx}/blog/blog-home.action?tomember.id=${id}" >${name}<p></p></a>
                            	<s:if test="info.headPortraitUri != null && info.headPortraitUri != ''">
                                  <img src="${ctx }${ info.headPortraitUri}" width="90" height="90" alt="${name }"/>
                                  </s:if>
                                  <s:else>
                                  <img src="images/renwu.gif" alt="${name }" />
                                 </s:else></li>
                            </s:iterator>
                                
                            </ul>    
                         </div>
                       </div>
                 </div>
                
                <!--  专拦博客  end-->
                
                <!--  江湖大侠  begin-->
                 <div id="star_bolg">
                       <div id="all_title"><h2>BLOG STAR_江湖大侠</h2><a href="${ctx }/circle-heroes.action">>更多_more</a></div>
                       <div class="group_list">
                          <div>
                            <ul>
                                <s:iterator value="heroPage.result">
                            	<li><a class="coverBg90"  href="${ctx}/blog/blog-home.action?tomember.id=${id}" >${name}<p></p></a>
                            	<s:if test="info.headPortraitUri != null && info.headPortraitUri != ''">
                                  <img src="${ctx }${ info.headPortraitUri}" width="90" height="90" alt="${name }"/>
                                  </s:if>
                                  <s:else>
                                  <img src="images/renwu.gif" alt="${name }" />
                                 </s:else></li>
                            </s:iterator>
                            </ul>    
                         </div>
                       </div>
                 </div>
                
                <!--  江湖大侠  end-->
                
                <!--  最近加入  begin-->
                 <div id="new_join">
                       <div id="all_title"><h2>NEW JOIN_最近加入</h2><a href="${ctx }/circle-member-new.action">>更多_more</a></div>
                       <div class="group_list">
                          <div>
                            <ul>
                                <s:iterator value="newMemberPage.result">
                            	<li><a class="coverBg90"  href="${ctx}/blog/blog-home.action?tomember.id=${id}" >${name}<p></p></a>
                            	<s:if test="info.headPortraitUri != null && info.headPortraitUri != ''">
                                  <img src="${ctx }${ info.headPortraitUri}" width="90" height="90" alt="${name }"/>
                                  </s:if>
                                  <s:else>
                                  <img src="images/xiaonian.jpg" alt="${name }" width="90" height="90" />
                                 </s:else></li>
                            </s:iterator>
                            </ul>    
                         </div>
                       </div>
                 </div>
                
                <!--  最近加入   end-->
                
                <div id="banner">
                <s:if test="adsArray.length>3">
					<s:if test="adsArray[3].type.name==1">
						<s:property value="adsArray[3].script" escapeJavaScript="false"/>
					</s:if><s:else>
					  <a href="<s:property value='adsArray[3].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[3].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[3].description'/>" />
					  </a>
					</s:else>
				</s:if><s:else>
                   <img src="${ctx }/images/banner.jpg"/>
                   </s:else>
				</div>
                <!--  秀场 begin-->
                <div id="case_show">
                    <div id="all_title"><h2>CASE SHOW_秀场</h2><a href="${ctx}/show?showCategory.id=56&isNewsVote=all">>更多_more</a>
                    </div>
                        <div id="case_show_content">
                           <s:iterator value="showArticle">
                             <div id="case_show_content_detalied">
	                              <a class="coverBg" href="${ctx}/showcontent.action?article.id=${id}"><p>(查看全部)</p></a>
		                            <s:if test="imageUri!=null && imageUri != ''">
		                            	<img src="${ctx}${imageUri }" width="140" height="140"/>
		                            </s:if><s:else>
		                                <img src="images/hot_essay.jpg" width="140" height="140" />	
		                                </s:else>
                                   <p>&nbsp;</p>
                                   <h3><a href="${ctx}/searcharticlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="8" value="${title }"/></a></h3>
                                   <p>时间发布_<s:date name="createDate" format="yyyy-MM-dd"/></p>
                                   <p>阅读_${readCount}评论_</p>
                                   <p>投票_${voteCount }</p>
                             </div>
                           </s:iterator>
                        </div>
                </div>
                <!--  秀场 end-->
                
                <div class="clean"></div>
                
				<div id="contentFoot">
					<div id="contentFoot_nav">
						<h3><span>导航&gt;</span><span>设计圈首页&gt;</span><span class="selectNav">搜索</span></h3>

					</div>
           
                    <!-- 底部的搜索条 -->
					<div id="contentFoot_seach">
					<form action="${ctx }/searcharticle.action" name="searchnews1" method="post">
						<ul>
                       	  <li class="firstLi"><input type="text"  class="indexss" value="" class="foot_seach" name="newsContent" /></li>
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
                <div id="bannerThree">
                <s:if test="adsArray.length>4">
					<s:if test="adsArray[4].type.name==1">
						<s:property value="adsArray[4].script" escapeJavaScript="false"/>
					</s:if><s:else>
					  <a href="<s:property value='adsArray[4].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[4].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[4].description'/>" />
					  </a>
					</s:else>
				</s:if>
                </div>
                <div class="clean"></div>
                <div id="longdetails">
                	<div class="longdetails_left">
						<img src="images/DetailsImage.gif" alt=""/>
					</div>
					<div id="longdetails_right">
                    	<div>
                        	<ul>
                                <li class="titleLi">频道编辑:Snow<a href="#"><span>我要当编辑</span></a></li>
                                <li>责任内容:全站更新</li>
                                <li>个性签名:春暖花开</li>
                            </ul>
                        </div>
                        <div>
                        	 <p>
                                声明网站所转载的文章，书籍等内容纯属作者个人观点，并不代表本站观点。
                                欢迎转载，敬请书名转载设计圈网 ooowo.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </p>
                        </div>
					</div>	
                </div>
			</div>
			<div id="rightContent">
				<div id="rightContent_top">			
					<s:if test="adsArray.length>1">
					<s:if test="adsArray[0].type.name==1">
						<s:property value="adsArray[1].script" escapeJavaScript="false"/>
					</s:if><s:else>
					   <a href="<s:property value='adsArray[1].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[1].image'/>" height="200px" width="348px" alt="<s:property value='adsArray[1].description'/>" />
					   </a>
					</s:else>
				</s:if><s:else>
					<img src="images/person_ad2.jpg" />
				</s:else>
				</div>
                
				<div id="right_star_blog">
                    <h3>STAR_BLOG_明星博客</h3>
                    <div class="star_blog_list">
                          <div>
                            <ul>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>
                                <li><a href="#" alt=""><img src="images/renwu.gif" alt="用户名" /></a></li>

                            </ul>    
                         </div>
                       </div>
                </div>
			
				 <div id="seecial">
                    <h3>WEB Special_江湖动态</h3>
                    <ul>
                    <s:iterator value="memberDynamics" >
                      <li>
	                      <s:if test="messageType == 1">
	                         <script>
									var message = ${message};
									document.write("<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">");
									document.write(message.memberName);
									document.write("</a>_");
									document.write(message.date);
									document.write("和<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.tomemberId+">");
									document.write(message.tomemberName);
									document.write("</a>");
									document.write("加为了好友");
	                         </script>
	                      </s:if>
	                      <s:if test="messageType == 2">
	                         <script>
									var message = ${message};
									document.write("<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">");
									document.write(message.memberName);
									document.write("</a>_");
									document.write(message.date);
									document.write("发布了新日志");
									document.write("<a target=_blank href=${ctx}/blog/blog-content.action?id="+message.articleId+"&&tomember.id="+message.memberId+">");
									document.write("\""+message.articleTitle+"\"");
									document.write("</a>");
	                         </script>
	                      </s:if>
	                       <s:if test="messageType == 3">
	                         <script>
									var message = ${message};
									document.write("<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">");
									document.write(message.memberName);
									document.write("</a>_");
									document.write(message.date);
									document.write("发布了新作品");
									document.write("<a target=_blank href=${ctx}/blog/blog-content.action?id="+message.articleId+"&&tomember.id="+message.memberId+">");
									document.write("\""+message.articleTitle+"\"");
									document.write("</a>");
	                         </script>
	                      </s:if>
	                       <s:if test="messageType == 4">
	                         <script>
									var message = ${message};
									document.write("<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">");
									document.write(message.memberName);
									document.write("</a>_");
									document.write(message.date);
									document.write("收藏了文章");
									document.write("<a target=_blank href=${ctx}/blog/blog-content.action?id="+message.articleId+"&&tomember.id="+message.create+">");
									document.write("\""+message.articleTitle+"\"");
									document.write("</a>");
	                         </script>
	                      </s:if>
                      </li>
                    </s:iterator>
                    </ul>
                </div>
				
                <div id="banner_all_list">
                    
                    <div class="banner_list">
                       <div>
                           <ul>
							  <li>
								<s:if test="adsArray.length>6">
										<s:if test="adsArray[6].type.name==1">
											<s:property value="adsArray[6].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[6].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[6].image'/>" height="100px" width="200px" alt="<s:property value='adsArray[6].description'/>" />
											</a>
										</s:else>
								</s:if>
							  </li>
                              <li>
								<s:if test="adsArray.length>7">
										<s:if test="adsArray[7].type.name==1">
											<s:property value="adsArray[7].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[7].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[7].image'/>" height="100px" width="200px" alt="<s:property value='adsArray[7].description'/>" />
											</a>
										</s:else>
								</s:if>
							  </li>
                              <li>
								<s:if test="adsArray.length>8">
										<s:if test="adsArray[8].type.name==1">
											<s:property value="adsArray[8].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[8].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[8].image'/>" height="100px" width="200px" alt="<s:property value='adsArray[8].description'/>" />
											</a>
										</s:else>
								</s:if>
							  </li>
							  
                           </ul>
                       </div>
                    </div>
                </div>
                
				<div class="hotCommodity">
					<h3>FOUCS_焦点人物</h3>
					<p>
								<s:if test="adsArray.length>9">
										<s:if test="adsArray[9].type.name==1">
											<s:property value="adsArray[9].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[9].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[9].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[9].description'/>" />
											</a>
										</s:else>
								</s:if>
					</p>
				</div>
				
				
				<div class="hotCommodity">
					<h3>New Topic_热新专题</h3>
					<p>
						<s:if test="adsArray.length>10">
										<s:if test="adsArray[10].type.name==1">
											<s:property value="adsArray[10].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[10].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[10].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[10].description'/>" />
											</a>
										</s:else>
					    </s:if>
					</p>
				</div>
                
                <div class="hotCommodity">
					<h3>New Topic_热新下载</h3>
					<p>
					   <s:iterator value="downloadResourceTop">
						<a href="${ctx}/downloadcontent.action?resource.id=${id}&&type.id=${type.id}">
						<s:if test="uri!=null && uri!=''">
                             <img src="${ctx}${uri}"  height="204px" width="204px" alt="${name }" title="${name }"/>
                                 </s:if><s:else>
                             <img src="images/hotcommodity1.gif"  width="204" height="204" alt="${name }"/></s:else>
		                </a>
                       </s:iterator>
					</p>
				</div>
                
                <div class="hotCommodity">
					<h3>Recommend book_本周推荐书品</h3>
					<p>
						<s:if test="adsArray.length>11">
										<s:if test="adsArray[11].type.name==1">
											<s:property value="adsArray[11].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[11].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[11].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[11].description'/>" />
											</a>
										</s:else>
					    </s:if>
					</p>
				</div>
                
                <div class="hotCommodity">
					<h3>Recommend book_热销商品</h3>
					<p>
						<s:if test="adsArray.length>12">
										<s:if test="adsArray[12].type.name==1">
											<s:property value="adsArray[12].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[12].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[12].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[12].description'/>" />
											</a>
										</s:else>
					    </s:if>
					</p>
				</div>
                
                <div id="right_seecial">
                   <h3>WEB Special_圈网精选</h3>
                  <div id="right_seecial_list">
                        <div>
                               <ul>
                               <s:iterator value="circleRecommend">
                                  <li>
                                    <a href="${ctx}/searcharticlecontent.action?article.id=${id}">
										<s:if test="imageUri!=null">
										<img height="46px" width="46px" src="${ctx }${imageUri}" alt="${title }" />
										</s:if><s:else>
										<img src="images/renwu.gif" height="46px" width="46px"  alt="${title }" />
										</s:else>
									</a>
                                  </li>
                               </s:iterator>
                               </ul>
                           </div>
                   </div>
                </div>
				
				<div class="topTags">
					<a href="#"><span>创意</span></a>
					<a href="#"><span>coool</span></a>
					<a href="#"><span>家居</span></a>
					<a href="#"><span>有趣</span></a>
					<a href="#"><span>风格</span></a>
					<a href="#"><span>酷玩</span></a>
					<a href="#"><span>科技</span></a>
					<a href="#"><span>文化</span></a>
					<a href="#"><span>手机</span></a>
					<a href="#"><span>头枕</span></a>
					<a href="#"><span>性感</span></a>
					<a href="#"><span>视频</span></a>
					<a href="#"><span>BEDU</span></a>
					<a href="#"><span>遥控狙击枪</span></a>
					<a href="#"><span>科技数码</span></a>
					<a href="#"><span>电击枪</span></a>
					<a href="#"><span>有趣的家具</span></a>
					<a href="#"><span>鼻钉</span></a>
					<a href="#"><span>鼻环</span></a>
					<a href="#"><span>超酷饰品</span></a>
					<a href="#"><span>神奇茶几</span></a>
					<a href="#"><span>机器猫的竹蜻蜓</span></a>
					<a href="#"><span>数字抽屉</span></a>
					<a href="#"><span>温暖</span></a>
					<a href="#"><span>有趣橱柜</span></a>
				</div>
				<!-- 票数结束-->
				<div class="clean"></div>
			</div>
        <div class="clean"></div>
        <div id="bannerFour">
        	<s:if test="adsArray.length>5">
					<s:if test="adsArray[5].type.name==1">
						<s:property value="adsArray[5].script" escapeJavaScript="false"/>
					</s:if><s:else>
					<a href="<s:property value='adsArray[5].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[5].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[5].description'/>" />
						</a>
					</s:else>
			</s:if>
        </div>
		<!-- foot -->
			<%@ include file="/common/foot.jsp"%>
		</div>
	</div>
	</body>
</html>