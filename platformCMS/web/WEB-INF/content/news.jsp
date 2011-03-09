<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>资讯</title>
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
					<span>您的位置:首页&gt;资讯&gt;</span>
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
									<a title="${title }" <s:if test="uri!=null && uri!=''">href="${uri}"</s:if><s:else>href="${ctx}/newscontent.action?article.id=${id}"</s:else>  target="_blank">
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
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(0).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(0).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(0).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        <s:if test="blogArticles.get(0).get(0)!=null">
                        	<s:if test="blogArticles.get(0).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(0).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(0).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(0).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[0].id"/>"><strong><s:property value="subCategory[0].nameEn"/>_<s:property value="subCategory[0].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[0].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(0)" status="stat">
                            	<s:if test="#stat.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                                <s:iterator value="blogArticles.get(0)" status="stat">
                            	<s:if test="#stat.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                    
                    
                    <div class="details">
                        <div class="details_left">
                            <!-- 取第一条新闻 -->
                        <s:if test="articles.get(1).get(0)!=null">
                        	<s:if test="articles.get(1).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(1).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(1).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(1).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        <s:if test="blogArticles.get(1).get(0)!=null">
                        	<s:if test="blogArticles.get(1).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(1).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(1).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(1).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[1].id"/>"><strong><s:property value="subCategory[1].nameEn"/>_<s:property value="subCategory[1].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[1].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(1)" status="sta">
                           		<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                                 <s:iterator value="blogArticles.get(1)" status="sta">
                           		<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                    
                    
                    <div class="details">
                        <div class="details_left">
                            <!-- 取第一条新闻 -->
                        <s:if test="articles.get(2).get(0)!=null">
                        	<s:if test="articles.get(2).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(2).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(2).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(2).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        <s:if test="blogArticles.get(2).get(0)!=null">
                        	<s:if test="blogArticles.get(2).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(2).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(2).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(2).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[2].id"/>"><strong><s:property value="subCategory[2].nameEn"/>_<s:property value="subCategory[2].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[2].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(2)" status="sta">
                           		<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                                <s:iterator value="blogArticles.get(2)" status="sta">
                           		<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                    
                    
                    <div class="details">
                        <div class="details_left">
                            <!-- 取第一条新闻 -->
                        <s:if test="articles.get(3).get(0)!=null">
                        	<s:if test="articles.get(3).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(3).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(3).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(3).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                         <s:if test="blogArticles.get(3).get(0)!=null">
                        	<s:if test="blogArticles.get(3).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(3).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(3).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(3).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[3].id"/>"><strong><s:property value="subCategory[3].nameEn"/>_<s:property value="subCategory[3].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[3].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(3)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                            <s:iterator value="blogArticles.get(3)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_left">
                           <!-- 取第一条新闻 -->
                        <s:if test="articles.get(4).get(0)!=null">
                        	<s:if test="articles.get(4).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(4).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(4).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(4).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                         <s:if test="blogArticles.get(4).get(0)!=null">
                        	<s:if test="blogArticles.get(4).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(4).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(4).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(4).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[4].id"/>"><strong><s:property value="subCategory[4].nameEn"/>_<s:property value="subCategory[4].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[4].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(4)" status="sta">
                            <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                             <s:iterator value="blogArticles.get(4)" status="sta">
                            <s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_left">
                            <!-- 取第一条新闻 -->
                        <s:if test="articles.get(5).get(0)!=null">
                        	<s:if test="articles.get(5).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(5).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(5).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(5).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        <s:if test="blogArticles.get(5).get(0)!=null">
                        	<s:if test="blogArticles.get(5).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(5).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(5).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(5).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[5].id"/>"><strong><s:property value="subCategory[5].nameEn"/>_<s:property value="subCategory[5].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[5].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(5)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                            <s:iterator value="blogArticles.get(5)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                </div>
				
				
				<!-- 6个内容模块结束 -->
				
				<div id="bannerTwo">
					<s:if test="adsArray.length>2">
					<s:if test="adsArray[2].type.name==1">
						<s:property value="adsArray[2].script" escapeJavaScript="false"/>
					</s:if><s:else>
					  <a href="<s:property value='adsArray[2].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[2].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[2].description'/>" />
					  </a>
					</s:else>
				</s:if>
				</div>
				
				
				<div>
                	<div class="details">
					<div class="details_left">
						<!-- 取第一条新闻 -->
                        <s:if test="articles.get(6).get(0)!=null">
                        	<s:if test="articles.get(6).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(6).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(6).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(6).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
					
					<s:if test="blogArticles.get(6).get(0)!=null">
                        	<s:if test="blogArticles.get(6).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(6).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(6).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(6).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                     </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
					</div>
					<div class="details_right">
						<h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[6].id"/>"><strong><s:property value="subCategory[6].nameEn"/>_<s:property value="subCategory[6].name"/></strong></a></h4>
						<a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[6].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(6)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
						</ul>
						<ul class="listTwo">
							<s:iterator value="blogArticles.get(6)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
						</ul>
					</div>
				</div>
				
				
				<div class="details">
					<div class="details_left">
						<!-- 取第一条新闻 -->
                        <s:if test="articles.get(7).get(0)!=null">
                        	<s:if test="articles.get(7).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(7).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(7).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(7).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
						

						<s:if test="blogArticles.get(7).get(0)!=null">
                        	<s:if test="blogArticles.get(7).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(7).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(7).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(7).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
					</div>
					<div class="details_right">
						<h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[7].id"/>"><strong><s:property value="subCategory[7].nameEn"/>_<s:property value="subCategory[7].name"/></strong></a></h4>
						<a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[7].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(7)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
						</ul>
						<ul class="listTwo">
							<s:iterator value="blogArticles.get(7)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
						</ul>
					</div>
				</div>
				
				<div class="details">
                     <div class="details_left">
                            <!-- 取第一条新闻 -->
                        <s:if test="articles.get(8).get(0)!=null">
                        	<s:if test="articles.get(8).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(8).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(8).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(8).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        <s:if test="blogArticles.get(8).get(0)!=null">
                        	<s:if test="blogArticles.get(8).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(8).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(8).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(8).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[8].id"/>"><strong><s:property value="subCategory[8].nameEn"/>_<s:property value="subCategory[8].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[8].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(8)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                            <s:iterator value="blogArticles.get(8)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_left">
                            <!-- 取第一条新闻 -->
                        <s:if test="articles.get(9).get(0)!=null">
                        	<s:if test="articles.get(9).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(9).get(0).getId()'/>"><img src="${ctx }<s:property value='articles.get(9).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='articles.get(9).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        
						<s:if test="blogArticles.get(9).get(0)!=null">
                        	<s:if test="blogArticles.get(9).get(0).getImageUri()!=null" >
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(9).get(0).getId()'/>"><img src="${ctx }<s:property value='blogArticles.get(9).get(0).getImageUri()'/>" alt=""/></a>
                            </s:if><s:else>
                            	<a href="${ctx}/newscontent.action?article.id=<s:property value='blogArticles.get(9).get(0).getId()'/>"><img src="images/DetailsImage.gif" alt=""/></a>
                            </s:else>
                        </s:if><s:else><img src="images/DetailsImage.gif" alt=""/></s:else>
                        </div>
                        <div class="details_right">
                            <h4><a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[9].id"/>"><strong><s:property value="subCategory[9].nameEn"/>_<s:property value="subCategory[9].name"/></strong></a></h4>
                            <a href="${ctx}/newslist.action?showCategory.id=<s:property value="subCategory[9].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(9)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                            <ul class="listTwo">
                             <s:iterator value="blogArticles.get(9)" status="sta">
                            	<s:if test="#sta.index>0">
                            	<li><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title }"/></a></li>
                            	</s:if>
                            </s:iterator>
                            </ul>
                        </div>
                    </div>
                </div>
				
                	<%@ include file="/common/searcharticle.jsp"%>

                <div id="bannerThree">
                	<s:if test="adsArray.length>3">
					<s:if test="adsArray[3].type.name==1">
						<s:property value="adsArray[3].script" escapeJavaScript="false"/>
					</s:if><s:else>
					  <a href="<s:property value='adsArray[3].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[3].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[3].description'/>" />
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
				
				<!-- 票数 -->
				<div class="votes">
					<h2>Vote Top 10 投票排行 <a href="javascript:;" class="vottab" rel="votesweek">本周</a> <a href="javascript:;" class="vottab" rel="votesmonth">本月</a></h2>
					
					<div id="votesweek" class="votweek">
						<dl>
						<s:iterator value="weekVotes" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/newscontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
							
						</dl>
					</div>
				
					<div id="votesmonth" class="votmonth">
						<dl>
							<s:iterator value="monthVotes" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/newscontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				</div>
				<!-- 热点评论 -->
				<div class="votes">
					<h2>HOT Comments 热点评论 <a href="javascript:;" class="comtab" rel="commentsweek">本周</a> <a href="javascript:;" class="comtab" rel="commentsmonth">本月</a></h2>
					
					<div id="commentsweek" class="votweek">
						<dl>
							<s:iterator value="weekComments" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/newscontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				
					<div id="commentsmonth" class="votmonth">
						<dl>
							<s:iterator value="monthComments" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/newscontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				</div>
				<!-- 热点资讯 -->
				<div class="votes">
					<h2>HOT Top10 热点资讯 <a href="javascript:;" class="hottab" rel="hotweek">本周</a> <a href="javascript:;" class="hottab" rel="hotmonth">本月</a></h2>
					
					<div id="hotweek" class="votweek">
						<dl>
							<s:iterator value="weekHots" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/newscontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/newscontent.action?article.id=${id}"  title="${title }"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				
					<div id="hotmonth" class="votmonth">
						<dl>
							<s:iterator value="monthHots" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/newscontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/newscontent.action?article.id=${id}" title="${title }"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				</div>
				
				
				
				
				<div class="hotCommodity">
					<h3>Recommend book 本周推荐书品</h3>
					<p>
						<s:if test="adsArray.length>5">
										<s:if test="adsArray[5].type.name==1">
											<s:property value="adsArray[5].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[5].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[5].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[5].description'/>" />
											</a>
										</s:else>
					   </s:if>
					</p>
				</div>
				
				
				<div class="hotCommodity">
					<h3>Recommend book 热销商品</h3>
					<p>
						<s:if test="adsArray.length>6">
										<s:if test="adsArray[6].type.name==1">
											<s:property value="adsArray[6].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[6].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[6].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[6].description'/>" />
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
				<!-- 票数结束-->
				<div class="clean"></div>
			
		</div>
        <div class="clean"></div>
        <div id="bannerFour">
        	<s:if test="adsArray.length>4">
					<s:if test="adsArray[4].type.name==1">
						<s:property value="adsArray[4].script" escapeJavaScript="false"/>
					</s:if><s:else>
					<a href="<s:property value='adsArray[4].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[4].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[4].description'/>" />
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