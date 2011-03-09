<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>专题</title>
		<link type="text/css" rel="stylesheet" href="css/special.css" />
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
		<script type="text/javascript" src="js/slide.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
        <script type="text/javascript" src="js/util.js"></script>
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
					<span>您的位置:首页&gt;专题&gt;</span>
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
									<a title="${title }" <s:if test="uri!=null && uri!=''">href="${uri}"</s:if><s:else>href="${ctx}/specialcontent.action?article.id=${id}"</s:else>  target="_blank">
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

                
                <div id="personNewsTags1"></div>
                
                <div id="personNewsTags2"></div>
            <!--新闻1，2开始-->
			<div>
                <div class="details">
					<div class="details_all">
						<h4><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[0].id"/>"><strong><s:property value="subCategory[0].nameEn"/>_<s:property value="subCategory[0].name"/></strong></a></h4>
						<a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[0].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(0)">
                            	<li><a href="${ctx}/specialcontent.action?article.id=${id}" title="${title }"><ouun:substring length="20" value="${title }"/></a></li>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[0].id"/>">_more</a></li>
						</ul>
					</div>
				</div>
				
				
				<div class="details">
					<div class="details_all">
						<h4><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[1].id"/>"><strong><s:property value="subCategory[1].nameEn"/>_<s:property value="subCategory[1].name"/></strong></a></h4>
						<a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[0].id"/>"><span>more</span></a>
						<ul class="listOne">
                            <s:iterator value="articles.get(1)">
                            	<li><a href="${ctx}/specialcontent.action?article.id=${id}" title="${title }"><ouun:substring length="20" value="${title }"/></a></li>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[1].id"/>">_more</a></li>
						</ul>
					</div>
				</div>
			</div>
            <!--新闻1，2结束-->
                 <div id="personNewsTags1"></div>
                 <div id="personNewsTags2"></div>
             <!--新闻3，4开始-->
            <div>
				<div class="details">
                        <div class="details_all">
                            <h4><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[2].id"/>"><strong><s:property value="subCategory[2].nameEn"/>_<s:property value="subCategory[2].name"/></strong></a></h4>
                            <a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[2].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(2)">
                            	<li><a href="${ctx}/specialcontent.action?article.id=${id}" title="${title }"><ouun:substring length="20" value="${title }"/></a></li>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[2].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="details">
                        <div class="details_all">
                            <h4><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[3].id"/>"><strong><s:property value="subCategory[3].nameEn"/>_<s:property value="subCategory[3].name"/></strong></a></h4>
                            <a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[3].id"/>"><span>more</span></a>
                            <ul class="listOne">
                            <s:iterator value="articles.get(3)">
                            	<li><a href="${ctx}/specialcontent.action?article.id=${id}" title="${title }"><ouun:substring length="20" value="${title }"/></a></li>
                            </s:iterator>
                            <li></li>
                            <li><a href="${ctx}/speciallist.action?showCategory.id=<s:property value="subCategory[3].id"/>">_more</a></li>
                            </ul>
                        </div>
                    </div>
              </div>  
              <!--新闻3，4结束-->
                <div id="bannerTwo">
                   <s:if test="adsArray.length>0">
						<s:if test="adsArray[7].type.name==1">
							<s:property value="adsArray[7].script" escapeJavaScript="false"/>
						</s:if><s:else>
							<a href="<s:property value='adsArray[7].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[7].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[7].description'/>" />
							</a>
						</s:else>
					</s:if>
                </div>
				<!--合作媒体开始-->
                  <div id="cooperation">
                       <div id="cooperation_media">
                           <ul>
                             <li>Cooperation media_合作媒体 <a href="#"><span>_合作推广</span></a></li>
                           </ul>
                       </div>
                       <div class="cooperation_list">
                          <div>
                            <ul>
                              <li>
                              <s:if test="adsArray.length>10">
										<s:if test="adsArray[10].type.name==1">
											<s:property value="adsArray[10].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[10].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[10].image'/>" height="100px" width="250px" alt="<s:property value='adsArray[10].description'/>" />
											</a>
										</s:else>
					          </s:if>
                              </li>
                              <li>
                              <s:if test="adsArray.length>11">
										<s:if test="adsArray[11].type.name==1">
											<s:property value="adsArray[11].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[11].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[11].image'/>" height="100px" width="250px" alt="<s:property value='adsArray[11].description'/>" />
											</a>
										</s:else>
					          </s:if>
							  </li>
                              <li>
                              <s:if test="adsArray.length>12">
										<s:if test="adsArray[12].type.name==1">
											<s:property value="adsArray[12].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[12].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[12].image'/>" height="100px" width="250px" alt="<s:property value='adsArray[12].description'/>" />
											</a>
										</s:else>
					          </s:if>
                              </li>
                              <li>
                              <s:if test="adsArray.length>13">
										<s:if test="adsArray[13].type.name==1">
											<s:property value="adsArray[13].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[13].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[13].image'/>" height="100px" width="250px" alt="<s:property value='adsArray[13].description'/>" />
											</a>
										</s:else>
					          </s:if>
                              </li>
                              <li>
                              <s:if test="adsArray.length>14">
										<s:if test="adsArray[14].type.name==1">
											<s:property value="adsArray[14].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[14].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[14].image'/>" height="100px" width="250px" alt="<s:property value='adsArray[14].description'/>" />
											</a>
										</s:else>
					          </s:if>
                              </li>
                              <li>
                              <s:if test="adsArray.length>15">
										<s:if test="adsArray[15].type.name==1">
											<s:property value="adsArray[15].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[15].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[15].image'/>" height="100px" width="250px" alt="<s:property value='adsArray[15].description'/>" />
											</a>
										</s:else>
					          </s:if>
                              </li>
                              <li>
                              <s:if test="adsArray.length>16">
										<s:if test="adsArray[16].type.name==1">
											<s:property value="adsArray[16].script" escapeJavaScript="false"/>
										</s:if><s:else>
										<a href="<s:property value='adsArray[16].url'/>" target="_blank">
											<img src="${ctx }<s:property value='adsArray[16].image'/>" height="100px" width="250px" alt="<s:property value='adsArray[16].description'/>" />
											</a>
										</s:else>
					          </s:if>
                              </li>
                            </ul>
                          </div>
                       </div>
                      
                  </div>
                <!--合作媒体结束-->
                <div class="clean"></div>
                
				<%@ include file="/common/searcharticle.jsp"%>
				
                <div id="bannerThree">
                   <s:if test="adsArray.length>0">
					<s:if test="adsArray[8].type.name==1">
						<s:property value="adsArray[8].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[8].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[8].image'/>" height="100px" width="600px" alt="<s:property value='adsArray[8].description'/>" />
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
				<div class="rightContent_ad_345-120">
                    <s:if test="adsArray.length>0">
					<s:if test="adsArray[1].type.name==1">
						<s:property value="adsArray[1].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[1].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[1].image'/>" height="120px" width="345px" alt="<s:property value='adsArray[1].description'/>" />
						</a>
					</s:else>
				</s:if>
                    
                </div>
                <div class="rightContent_ad_345-120">
                   <s:if test="adsArray.length>0">
					<s:if test="adsArray[2].type.name==1">
						<s:property value="adsArray[2].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[2].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[2].image'/>" height="120px" width="345px" alt="<s:property value='adsArray[2].description'/>" />
						</a>
					</s:else>
				</s:if>
                </div>
				<!-- 票数 -->
				<div class="votes">
					<h2>Vote Top 10 投票排行 <a href="javascript:;" class="vottab" rel="votesweek">本周</a> <a href="javascript:;" class="vottab" rel="votesmonth">本月</a></h2>
					
					<div id="votesweek" class="votweek">
						<dl>
						<s:iterator value="weekVotes" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/specialcontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/specialcontent.action?article.id=${id}"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
							
						</dl>
					</div>
				
					<div id="votesmonth" class="votmonth">
						<dl>
							<s:iterator value="monthVotes" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="${ctx}/specialcontent.action?article.id=${id}">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><a href="${ctx}/specialcontent.action?article.id=${id}"><ouun:substring length="14" value="${title}"/></a></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
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
                
                <div class="advtype2">
					<s:if test="adsArray.length>0">
						<s:if test="adsArray[3].type.name==1">
							<s:property value="adsArray[3].script" escapeJavaScript="false"/>
						</s:if><s:else>
							<a href="<s:property value='adsArray[3].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[3].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[3].description'/>" />
							</a>
						</s:else>
					</s:if>
					<s:if test="adsArray.length>0">
						<s:if test="adsArray[4].type.name==1">
							<s:property value="adsArray[4].script" escapeJavaScript="false"/>
						</s:if><s:else>
							<a href="<s:property value='adsArray[4].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[4].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[4].description'/>" />
							</a>
						</s:else>
					</s:if>
					<s:if test="adsArray.length>0">
						<s:if test="adsArray[6].type.name==1">
							<s:property value="adsArray[6].script" escapeJavaScript="false"/>
						</s:if><s:else>
							<a href="<s:property value='adsArray[6].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[6].image'/>" height="600px" width="160px" alt="<s:property value='adsArray[6].description'/>" />
							</a>
						</s:else>
					</s:if>
				</div>
				
				<div class="advtype3">
					<s:if test="adsArray.length>0">
						<s:if test="adsArray[5].type.name==1">
							<s:property value="adsArray[5].script" escapeJavaScript="false"/>
						</s:if><s:else>
							<a href="<s:property value='adsArray[5].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[5].image'/>" height="204px" width="204px" alt="<s:property value='adsArray[5].description'/>" />
							</a>
						</s:else>
					</s:if>
				</div>
				<div class="clean"></div>
			</div>
        <div class="clean"></div>

        <div id="bannerFour">
       		 <s:if test="adsArray.length>0">
					<s:if test="adsArray[9].type.name==1">
						<s:property value="adsArray[9].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[9].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[9].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[9].description'/>" />
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