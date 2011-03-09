<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>江湖_会员</title>
		<link type="text/css" rel="stylesheet" href="css/circle-web.css" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" />
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
		<script type="text/javascript" src="js/slide.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/ui.core.js"></script>
		<script type="text/javascript" src="js/ui.dialog.js"></script>
		<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="js/userlogin.js"></script>
		<script type="text/javascript" src="js/world.js"></script>
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<script type="text/javascript"></script>
	</head>
	<body>
		<%@ include file="/common/userlogin.jsp" %>
		<!-- top -->
	<div class="container">
		<%@ include file="/common/circle-top.jsp"%>
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
					<span>您的位置:首页&gt;江湖&gt;会员 </span>
				</p>
                <hr class="splitline"/>
			</div>
			
            <div id="maincontent">
                 <div id="listleft">
 						<ul>
							<%@include file="/common/circle-left.jsp" %>
							
						</ul>
                 </div>
                 
                 <div id="listcenter">
                         <div class="tabshow_title">
                            <h3>江湖_Hot_人气排行</h3>
                            <ul>
                                <li><span></span></li>
                                <li><span></span></li>
                            </ul>
                        </div>
                        <div id="case_show">
                        	<s:iterator value="page.result">
	                           <div id="case_show_content">
	                                 <div id="case_show_content_detalied">
	                                   <a class="coverBg100" href="${ctx}/blog/blog-home.action?tomember.id=${id}">${name}<p>READ MORE</p></a>
	                                     <a class="borderima" href="${ctx}/blog/blog-home.action?tomember.id=${id}">
	                                     <s:if test="info.headPortraitUri != null && info.headPortraitUri != ''">
	                                           <img src="${ctx }${info.headPortraitUri}" width="100" height="100"/>
	                                           </s:if>
	                                           <s:else>
	                                           <img src="images/xiaonian.jpg" width="100" height="100"/>
	                                           </s:else>
										</a>
	                                       <p>_${name }</p>
                                           
                                           <p>_点击_${info.accessing }</p>
	                                 </div>
	                          </div>
	                          </s:iterator>
	                         
                        </div>
                        
                    <div style="padding-left:10px;padding-top:40px;width:760px">
						第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="circle-member-hot.action?page.pageNo=${page.prePage}">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="circle-member-hot.action?&page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="circle-member-hot.action?page.pageNo=${page.nextPage}">下一页</a>
						</s:if>
					</div>
                     	<div id="contentFoot_nav_1">
                                <h3><span>导航&gt;</span><span>设计圈首页&gt;</span><span class="selectNav">江湖</span></h3>
                                <ul>
                                    <li><a href="#"><span>_大侠</span></a></li>
                                    <li><a href="#"><span>_会员</span></a></li>	
                                    <li><a href="#"><span>_博客blog</span></a></li>	
                                    <li><a href="#"><span>_群英会</span></a></li>	
                                </ul>
                        </div>
                         <div id="contentFoot_seach_1">
                                <ul>
                                  <li class="firstLi"><input type="text" value="" class="foot_seach" /></li>
                                    <li><img src="images/seachbutton2.gif" alt="" />	</li>
                                </ul>
                                <div>
                                    <input type="radio" name="seachto" checked="checked" /><span>ooowo.com</span>
                                    <input type="radio" name="seachto" /><span>google</span>
                                </div>
                                <div class="firstP_1">
                                    <span>你的足迹 登入注册你的免费账号</span>
                                </div>
                                <div id="contentFoot_tags_1">
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
                 
            			
			<div class="clean"></div>
			
		</div>
        <div class="clean"></div>
        <div id="bannerFour">
           <s:if test="adsArray.length>0">
					<s:if test="adsArray[2].type.name==1">
						<s:property value="adsArray[2].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[2].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[2].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[2].description'/>" />
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