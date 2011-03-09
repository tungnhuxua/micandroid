<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>下载</title>
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/downloadcontent.css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/ui.core.js"></script>
		<script type="text/javascript" src="js/ui.dialog.js"></script>
		<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="js/userlogin.js"></script>
		<script type="text/javascript" src="js/content.js"></script>
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
						<li><a href="download"><span class="selectNav">_下载</span></a></li>
						<li><a href="circle-web.action"><span>_江湖</span></a></li>
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
			        	<li><a href="#"><span>_抢先看</span></a></li>
						<li><a href="#"><span>_色女朗</span></a></li>	
						<li><a href="#"><span>_LOGO</span></a></li>	
						<li><a href="#"><span>_图库</span></a></li>	
						<li><a href="#"><span>_招聘</span></a></li>	
						<li><a href="#"><span>_杂志</span></a></li>	
						<li><a href="#"><span>_博客</span></a></li>	
						<li><a href="#"><span>_论坛</span></a></li>	
						<li><a href="#"><span>_群英会</span></a></li>
                        <li><a href="#"><span>_标签TAG</span></a></li>	
                        <li><a href="#"><span>_平面</span></a></li>	
                        <li><a href="#"><span>_创意</span></a></li>	
                        <li><a href="#"><span>_卡通</span></a></li>	
                        <li><a href="#"><span>_影像</span></a></li>
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
					<span>您的位置:首页&gt;下载&gt;</span>
				</p>
                
			</div>
			
			<div id="contentLeft">
				<div id="totop">
					<a href="#top" rel="">
						<img src="images/toTop.gif" alt="返回顶部" />
					</a>
				</div>
				<div id="imageDetails">
                      <div id="topimage">
                          <ul>
                             <li><a href="#"><img src="images/seachbutton2.gif" alt="" /></a></li>
                             <li style="text-align:center;">${resource.name}</li>
                             <li style="text-align:right;"><a href="#"><img src="images/seachbutton2.gif" alt="" /></a></li>
                          </ul>
                      </div>
                      
                      <div id="imageUri">
                           <s:if test="resource.uri!=null && resource.uri!=''">
                               <img src="${ctx}${resource.uri}"  height="400px" width="600px"/>
                            </s:if><s:else>
                            <img src="images/download_image.jpg" />
                            </s:else>
                      </div>
                      
                      <div id="contentDatails">
                           <ul>
                              <li><h2>${resource.name }</h2></li>
                              <li>资源类型_${resource.type.name }</li>
                              <li>版权_${resource.copyright}</li>
                              <li>文件编号_${resource.id}</li>
                              <li>文件格式_${resource.format }</li>
                              <li>文件大小_${resource.size }</li>
                              <li>所需积分_${resource.points}喔元(限会员)</li>
                              <li>&nbsp;</li>
                              <li>标签TAG_<ouun:articletag href="#" value="${resource.tag}"/></li>
                              <li>描述_${resource.description}</li>
                              <li>&nbsp;</li>
                              <li><h2>下载_<a href="${ctx}/downfile.action?resource.id=${resource.id}" target="_blank">点击下载</a></h2></li>
                              
                           </ul>
                      </div>
                      
                      <div id="imageAuthor">
                         <div id="imageAuthor_image">
                             <img src="images/touxiang.jpg"/>
                         </div>
                         <div id="imageAuthor_content">
                             <ul>
                               <li><a href="#">${member.name }</a> 于<s:date
	name="resource.createDate" format="yyyy-MM-dd" />上传发布</li>
                               <li>查看此会员上传的更多图片</li>
                             </ul>
                         </div>
                      </div>
                </div>
                <form action="${ctx }/searcharticle.action" name="searchnews1" method="post">
				<div id="seackbar">
					<input type="text" name="newsContent" class="foot_seach" />
					<ul>
						<li><a href="javascript:searchNews1()"><img src="images/seachbutton2.gif" alt="" /></a></li>
                        <li>&nbsp;</li>
                        <li>&nbsp;</li>
						<li><a href="#"><img src="images/tools1.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools2.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools3.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools4.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools5.gif" alt="" /></a></li>
						<li><a href="#"><img src="images/tools6.gif" alt="" /></a></li>
					</ul>
				</div>
				</form>
				<div>
                    <input type="radio" value=""  name="seachto" checked="checked"/>ooowo.com
                    <input type="radio" value=""  name="seachto"/>Google
                </div>
				
				
				<!-- 第三个广告区 -->
				<div id="bannerThree">
				<s:if test="adsArray.length>4">
					<s:if test="adsArray[4].type.name==1">
						<s:property value="adsArray[4].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[4].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[4].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[4].description'/>" />
						</a>
					</s:else>
				</s:if><s:else>
				<img src="images/hotcommodity1.gif" alt="" />
				</s:else>
				</div>
				
				
				<div id="googleAdv">
					<ul>
						<li class="goolfirst">Google 提供的广告</li>
						<li>设计师</li>
						<li>创意</li>
						<li>北京</li>
						<li>平面设计</li>
						<li>店铺设计</li>
						<li>动画师</li>
					</ul>
				</div>
					
                 <div id="rapport_photo">
                          <div class="photo_title">
                              <h2>Other Photo_相关主题图片_all</h2>
                          </div>
                          
                          <div class="photo_list">
                              <div>
                                <ul>
                                  <s:iterator value="correlation">
                                    <li>
                                    <a class="borderima"  href="${ctx}/downloadcontent.action?resource.id=${id}&&type.id=${type.id}" >
                                     <s:if test="uri!=null && uri!=''">
                                       <img src="${ctx}${uri}" alt="${name }" width="100" height="100" title="${name }"/>
                                    </s:if><s:else>
                                       <img src="images/renwu.jpg" alt="用户名" />
                                    </s:else>
                                    </a>
                                    </li>
                                  </s:iterator>
                                </ul>    
                             </div>
                          </div>
                 </div>
                 
                 <div id="Projects">
                     <div class="photo_title">
                              <h2>Other Projects_精选图片_all</h2>
                          </div>
                          
                          <div class="photo_list">
                              <div>
                                <ul>
                                   <s:iterator value="recommendpic">
                                    <li>
                                    <a class="borderima"  href="${ctx}/downloadcontent.action?resource.id=${id}&&type.id=${type.id}" >
                                     <s:if test="uri!=null && uri!=''">
                                       <img src="${ctx}${uri}" alt="${name }" width="100" height="100" title="${name }"/>
                                    </s:if><s:else>
                                       <img src="images/renwu.jpg" alt="用户名" />
                                    </s:else>
                                    </a>
                                    </li>
                                   </s:iterator>
                                </ul>    
                             </div>
                          </div>
                 </div>
                 
                 <div id="other_photo">
                 		<div class="photo_title">
                              <h2>Other photo_作者  名称  其他主题_all</h2>
                          </div>
                          
                          <div class="photo_list">
                              <div>
                                <ul>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                    <li><a class="borderima"  href="#" alt=""><img src="images/renwu.jpg" alt="用户名" /></a></li>
                                </ul>    
                             </div>
                          </div>
                 </div>
                <div class="clean"></div>
               
						
			</div>
			<div id="rightContent">

				<div id="res">
                    <div id="res_title"><h2>_同类型资源</h2><a href="${ctx}/download?type.id=${type.id}">&gt;更多_more</a></div>
                     <div id="res_list">
                          <div>
                            <ul>
                              <s:iterator value="HomogeneousResource">
                                <li>
                                   <a href="${ctx}/downloadcontent.action?resource.id=${id}&&type.id=${type.id}" >
                                     <s:if test="uri!=null && uri!=''">
                                       <img src="${ctx}${uri}" alt="${name }" title="${name }"/>
                                    </s:if><s:else>
                                       <img src="images/renwu.jpg" alt="用户名" />
                                    </s:else>
                                    </a>
                                </li>
                              </s:iterator>
                               
                            </ul>    
                         </div>
                       </div>
                </div>
				
				<div class="advtype2">
				<s:if test="adsArray.length>1">
					<s:if test="adsArray[1].type.name==1">
						<s:property value="adsArray[1].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[1].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[1].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[1].description'/>" />
						</a>
					</s:else>
				</s:if><s:else>
				<img src="images/hotcommodity1.gif" alt="" />
				</s:else>
				
				<s:if test="adsArray.length>2">
					<s:if test="adsArray[2].type.name==1">
						<s:property value="adsArray[2].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[2].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[2].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[2].description'/>" />
						</a>
					</s:else>
				</s:if><s:else>
				<img src="images/hotcommodity1.gif" alt="" />
				</s:else>
				<s:if test="adsArray.length>3">
					<s:if test="adsArray[3].type.name==1">
						<s:property value="adsArray[3].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[3].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[3].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[3].description'/>" />
						</a>
					</s:else>
				</s:if><s:else>
	
				</s:else>
				
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
				</s:if><s:else>
				<img src="images/hotcommodity1.gif" alt="" />
				</s:else>
        </div>
		<!-- foot -->
			<%@ include file="/common/foot.jsp"%>
		</div>
	</div>
	</body>
</html>