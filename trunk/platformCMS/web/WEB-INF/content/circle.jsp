<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>圈网</title>
		<link type="text/css" rel="stylesheet" href="css/index.css" />
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
		<script type="text/javascript" src="js/slide.js"></script>
		<script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</head>
	<body>
		<!-- top -->
	<div class="container">
		<%@ include file="/common/top.jsp"%>
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
					<span>您的位置:首页&gt;圈网&gt;</span>
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
								<s:iterator value="categoryHomeArticle" status="sta">
								<div class="image" id="image_xixi-<s:property value='#sta.index+1'/>">
									<a title="${title }" <s:if test="uri!=null && uri!=''">href="${uri}"</s:if><s:else>href="${ctx}/circlecontent.action?article.id=${id}"</s:else>  target="_blank">
									<img alt="${title }" src="${ctx }${imageLink}" />
									</a>
									<div class="word">
									<h3>${title }</h3>
									<p><ouun:substring length="100" value="${content}"/></p>
									</div>
								</div>
								</s:iterator>
								<!--标签结束 -->
							
							</div>
							<div id="thumbs">
								<ul>
								 <s:iterator value="categoryHomeArticle" status="sta">
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
                <div>
                	<div class="details">
                        <div class="details_left">
                           <!-- 取第一条新闻 -->
                        <s:if test="articles.get(0).get(0)!=null">
                        	<s:if test="articles.get(0).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(0).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(0).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(0).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[0].id"/>"><strong><s:property value="subCategory[0].nameEn"/>_<s:property value="subCategory[0].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[0].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(0)" status="sta">
                           		<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[0].id"/>">_more</a></li>
                            </ul>

                        </div>
                    </div>
                    
                    
                    <div class="details">
                        <div class="details_left">
                        <s:if test="articles.get(1).get(0)!=null">
                        	<s:if test="articles.get(1).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(1).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(1).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(1).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[1].id"/>"><strong><s:property value="subCategory[1].nameEn"/>_<s:property value="subCategory[1].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[1].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(1)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[1].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    
                    <div class="details">
                        <div class="details_left">
                        <s:if test="articles.get(2).get(0)!=null">
                        	<s:if test="articles.get(2).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(2).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(2).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(2).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[2].id"/>"><strong><s:property value="subCategory[2].nameEn"/>_<s:property value="subCategory[2].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[2].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(2)" status="sta">
                               <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                               </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[2].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    
                    <div class="details">
                        <div class="details_left">
	                        <s:if test="articles.get(3).get(0)!=null">
	                        	<s:if test="articles.get(3).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(3).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(3).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(3).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[3].id"/>"><strong><s:property value="subCategory[3].nameEn"/>_<s:property value="subCategory[3].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[3].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(3)" status="sta">
                             <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                             </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[3].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_left">
	                        <s:if test="articles.get(4).get(0)!=null">
	                        	<s:if test="articles.get(4).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(4).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(4).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(4).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[4].id"/>"><strong><s:property value="subCategory[4].nameEn"/>_<s:property value="subCategory[4].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[4].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(4)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                              </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[4].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_left">
	                        <s:if test="articles.get(5).get(0)!=null">
	                        	<s:if test="articles.get(5).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(5).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(5).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(5).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[5].id"/>"><strong><s:property value="subCategory[5].nameEn"/>_<s:property value="subCategory[5].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[5].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(5)" status="sta">
                               <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                               </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[5].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
				
				
				<!-- 6个内容模块结束 -->
				
				<div id="bannerTwo">
					<s:if test="adsArray.length>0">
					<s:if test="adsArray[3].type.name==1">
						<s:property value="adsArray[3].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[3].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[3].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[3].description'/>" />
						</a>
					</s:else>
				</s:if>
				</div>
				
				
				<div>
                	<div class="details">
					<div class="details_left">
	                        <s:if test="articles.get(6).get(0)!=null">
	                        	<s:if test="articles.get(6).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(6).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(6).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(6).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
					</div>
					<div class="details_right">
						<h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[6].id"/>"><strong><s:property value="subCategory[6].nameEn"/>_<s:property value="subCategory[6].name"/></strong></a></h4>
						<a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[6].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(6)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                              </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[6].id"/>">_more</a></li>
						</ul>
					</div>
				</div>
				
				
				<div class="details">
					<div class="details_left">
	                        <s:if test="articles.get(7).get(0)!=null">
	                        	<s:if test="articles.get(7).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(7).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(7).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(7).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
					</div>
					<div class="details_right">
						<h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[7].id"/>"><strong><s:property value="subCategory[7].nameEn"/>_<s:property value="subCategory[7].name"/></strong></a></h4>
						<a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[7].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(7)" status="sta">
                           	 <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                             </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[7].id"/>">_more</a></li>
						</ul>
					</div>
				</div>
				
				<div class="details">
                     <div class="details_left">
	                        <s:if test="articles.get(8).get(0)!=null">
	                        	<s:if test="articles.get(8).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(8).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(8).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(8).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[8].id"/>"><strong><s:property value="subCategory[8].nameEn"/>_<s:property value="subCategory[8].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[8].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(8)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                              </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[8].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_left">
	                        <s:if test="articles.get(9).get(0)!=null">
	                        	<s:if test="articles.get(9).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(9).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(9).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(9).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[9].id"/>"><strong><s:property value="subCategory[9].nameEn"/>_<s:property value="subCategory[9].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[9].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(9)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                              </s:if>
                            </s:iterator><li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[9].id"/>">_more</a></li>
                            
                            </ul>
                        </div>
                    </div>
                    
                   <div class="details">
                     <div class="details_left">
	                        <s:if test="articles.get(10).get(0)!=null">
	                        	<s:if test="articles.get(10).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(10).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(10).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(10).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[10].id"/>"><strong><s:property value="subCategory[10].nameEn"/>_<s:property value="subCategory[10].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[10].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(10)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                              </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[10].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                   <div class="details">
                     <div class="details_left">
	                        <s:if test="articles.get(11).get(0)!=null">
	                        	<s:if test="articles.get(11).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(11).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(11).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(11).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[11].id"/>"><strong><s:property value="subCategory[11].nameEn"/>_<s:property value="subCategory[11].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[11].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(11)" status="sta">
                               <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                               </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[11].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
				
								<!-- 6个内容模块结束 -->
				
				<div id="bannerTwo">
					<s:if test="adsArray.length>0">
					<s:if test="adsArray[4].type.name==1">
						<s:property value="adsArray[4].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[4].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[4].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[4].description'/>" />
						</a>
					</s:else>
				</s:if>
				</div>
				
				
				<div>
                	<div class="details">
					<div class="details_left">
	                        <s:if test="articles.get(12).get(0)!=null">
	                        	<s:if test="articles.get(12).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(12).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(12).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(12).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
					</div>
					<div class="details_right">
						<h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[12].id"/>"><strong><s:property value="subCategory[12].nameEn"/>_<s:property value="subCategory[12].name"/></strong></a></h4>
						<a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[12].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(12)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                              </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[12].id"/>">_more</a></li>
						</ul>
					</div>
				</div>
				
				
				<div class="details">
					<div class="details_left">
	                        <s:if test="articles.get(13).get(0)!=null">
	                        	<s:if test="articles.get(13).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(13).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(13).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(13).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
					</div>
					<div class="details_right">
						<h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[13].id"/>"><strong><s:property value="subCategory[13].nameEn"/>_<s:property value="subCategory[13].name"/></strong></a></h4>
						<a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[13].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(13)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[13].id"/>">_more</a></li>
						</ul>
					</div>
				</div>
				
				<div class="details">
                     <div class="details_left">
	                        <s:if test="articles.get(14).get(0)!=null">
	                        	<s:if test="articles.get(14).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(14).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(14).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(14).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[14].id"/>"><strong><s:property value="subCategory[14].nameEn"/>_<s:property value="subCategory[14].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[14].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(14)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[14].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_left">
	                        <s:if test="articles.get(15).get(0)!=null">
	                        	<s:if test="articles.get(15).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(15).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(15).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(15).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[15].id"/>"><strong><s:property value="subCategory[15].nameEn"/>_<s:property value="subCategory[15].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[15].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(15)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[15].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                   <div class="details">
                     <div class="details_left">
	                        <s:if test="articles.get(16).get(0)!=null">
	                        	<s:if test="articles.get(16).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(16).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(16).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(16).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[16].id"/>"><strong><s:property value="subCategory[16].nameEn"/>_<s:property value="subCategory[16].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[16].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(16)" status="sta">
                             	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[16].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                   <div class="details">
                     <div class="details_left">
	                        <s:if test="articles.get(17).get(0)!=null">
	                        	<s:if test="articles.get(17).get(0).getImageUri()!=null" >
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(17).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(17).get(0).getImageUri()'/>" alt=""/></a>
	                            </s:if><s:else>
	                            	<a href="${ctx}/circlecontent.action?article.id=<s:property value='articles.get(17).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
	                            </s:else>
	                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[17].id"/>"><strong><s:property value="subCategory[17].nameEn"/>_<s:property value="subCategory[17].name"/></strong></a></h4>
                            <a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[17].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(17)" status="sta">
                              <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/circlecontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                              </s:if>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/circlelist.action?showCategory.id=<s:property value="subCategory[17].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
				
				<%@ include file="/common/searcharticle.jsp"%>
				
                <div id="bannerThree">
                	<s:if test="adsArray.length>0">
					<s:if test="adsArray[5].type.name==1">
						<s:property value="adsArray[5].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[5].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[5].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[5].description'/>" />
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
                                欢迎转载，敬请书名转载设计圈网 ooowo.com
                            </p>
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
                
				<div class="webspecialimgs">
					<h3><span>WEB Special_圈网精选</span></h3>
					<s:iterator value="circleRecommend">
                                    <a href="${ctx}/circlecontent.action?article.id=${id}">
										<s:if test="imageUri!=null">
										<img height="46px" width="46px" src="${ctx }${imageUri}" alt="${title }" />
										</s:if><s:else>
										<img src="images/renwu.gif" height="46px" width="46px"  alt="${title }" />
										</s:else>
									</a>
                    </s:iterator>
				</div>
                
                <div class="hotCommodity">
					<h3>FOCUS_焦点人物</h3>
					<p>
						<s:if test="adsArray.length>7">
										<s:if test="adsArray[7].type.name==1">
											<s:property value="adsArray[7].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[7].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[7].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[7].description'/>" />
											</a>
										</s:else>
					   </s:if>
					</p>
				</div>
				
                <div class="hotCommodity">
					<h3>NEW Topic_最新专题</h3>
					<p>
						<s:if test="adsArray.length>8">
										<s:if test="adsArray[8].type.name==1">
											<s:property value="adsArray[8].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[8].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[8].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[8].description'/>" />
											</a>
										</s:else>
					   </s:if>
					</p>
				</div>
				
				
				<div class="hotCommodity">
					<h3>NEW Topic_最新下载</h3>
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
					<h3>Recommend book 本周推荐书品</h3>
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
					<h3>Recommend book 热销商品</h3>
					<p>
						<s:if test="adsArray.length>9">
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
					<s:if test="adsArray[6].type.name==1">
						<s:property value="adsArray[6].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[6].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[6].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[6].description'/>" />
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