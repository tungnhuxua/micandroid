<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>圈网-${article.category.name }-${article.title }</title>
		<link type="text/css" rel="stylesheet" href="css/content.css" />
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
		<script type="text/javascript" src="js/content.js"></script>
	</head>
	<body>
	<div class="container">
		<%@ include file="/common/top.jsp"%>
		<div id="content">
			<div id="topBanner">
				<s:if test="adsArray.length>0">
					<s:if test="adsArray[0].type.name==1">
						<s:property value="adsArray[0].script" escapeJavaScript="false" />
						</s:if>
						<s:else>
						<a href="<s:property value='adsArray[0].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[0].image'/>"
								height="100px" width="960px"
								alt="<s:property value='adsArray[0].description'/>" />
						</a>
						</s:else>
					</s:if>
			</div>
			
			<div id="postion">
				<p>
					<span>您的位置:首页&gt;圈网&gt;${article.category.name }</span>
				</p>
			</div>
			
			<div id="contentLeft">
				<div id="totop">
					<a href="#top" rel="">
						<img src="images/toTop.gif" alt="返回顶部" />
					</a>
				</div>
				<div id="news">
					<s:if test="imageUri!=null && imageUri != ''"><img src="${ctx}${imageUri}" alt="" />
					</s:if>
					<s:else>
						<div id="newspiaoshu">
							<ul>
								<li><span>票数</span></li>
								<li><span class="countp">${article.voteCount}</span></li>
							</ul>
					   </div>	
				    </s:else>	
					
					<p class="titlep">${article.title }</p>
					<p>来源_${article.source }  |  作者_${article.member.name }  |  发布时间_<s:date name="article.createDate" format="yyyy-MM-dd"/>  </p>
					<p>阅读_${article.readCount }  |  评论_<span class="commentCount"><s:property value="commentPage.totalCount" /></span>   |  投票_<span class="countding">${article.voteCount}</span></p>
					<p>
						TAG:<ouun:articletag href="#" value="${article.tag}"/>
					</p>
				</div>
				<div id="newsmaincontent" rel="newscontent">
					${article.content }
				</div>
				<div id="twoimage">
					<img id="ding" src="images/ding.gif" style="cursor: pointer;" onclick="woding(${article.id});"/>
					<img src="images/tanks.gif" alt="" class="thanks" style="display:none;"/>
				</div>
				<div id="voteinfo">
					<dl>
						<dt><span id="vcount">${article.voteCount}</span>已投票数</dt>
						<s:if test="preArticle!=null">
						<dd><a href="${ctx}/circlecontent.action?article.id=${preArticle.id}">上一篇_ ${preArticle.title }</a></dd>
						</s:if>
						<s:if test="nextArticle!=null">
						<dd><a href="${ctx}/circlecontent.action?article.id=${nextArticle.id}">下一篇_ ${nextArticle.title}</a></dd>
						</s:if>
					</dl>
				</div>
				
				<%@ include file="/common/front/functionMenuContent.jsp"%>
				
				<div id="listsugges">
					<ul>
						<li class="suggesfist"><span>栏目推荐 Sectionsre commend</span></li>
						<s:iterator value="recommendArticle">
						<li>
						   <a href="${ctx}/circlecontent.action?article.id=${id}">
									<s:if test="imageUri!=null && imageUri != ''"><img src="${ctx}${imageUri}" alt="" width="80" height="80" />
									</s:if><s:else>
										<img src="images/tuijian1.gif" alt=""  width="80" height="80"/>	
									</s:else>
						   </a>
						</li>
						</s:iterator>
					</ul>	
				</div>
				
				
				<div id="bannerThree">
				    <s:if test="adsArray.length>1">
						<s:if test="adsArray[1].type.name==1">
							<s:property value="adsArray[1].script" escapeJavaScript="false" />
						</s:if>
						<s:else>
						<a href="<s:property value='adsArray[1].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[1].image'/>"
								height="100px" width="600px"
								alt="<s:property value='adsArray[1].description'/>" />
						</a>
						</s:else>
					</s:if>
				</div>
				<div id="projectscontent">
					<dl id="proleft">
						<dt>Other Projects_相关文章_all</dt>
						<s:iterator value="TagCorrelationArticle">
							<dd><span><a href="${ctx}/circlecontent.action?article.id=${id}">${title}</a></span></dd>
						</s:iterator>
					</dl>
					
					<dl id="proright">
						<dt>S/M Down_素材下载_more</dt>
							<s:iterator value="downloadResourceTop">
											<dd>	
											   <a href="${ctx}/downloadcontent.action?resource.id=${id}&&type.id=${type.id}">
												<s:if test="uri!=null && uri!=''">
						                             <img src="${ctx}${uri}"  height="100" width="100" alt="${name }"/>
						                                 </s:if><s:else>
						                             <img src="images/vote1.gif"  width="100" height="100" alt="${name }"/></s:else>
								                </a>
								            </dd>
						    </s:iterator>
					</dl>					
				</div>
				
				<div id="articlelist">
					<div class="articletop">
					<h2>文章评论(<span class="commentCount" id="commentCount"><s:property value="commentPage.totalCount" /></span>条) Comments</h2>
					<h3>以下网友留言只代表其个人观点，不代表本网站的观点和立场</h3>
					</div>
					
					<s:iterator value="commentPage.result">
					<div class="articlecontent">
					<div class="articleImage"><img src="images/DetailsImage.gif"
						alt="" /></div>
					<div>
					<dl>
						<dt>${member.name } , <s:date name="createDate" format="yyyy年MM月dd日" />,<s:date name="createDate" format="HH.mm.ss" /> <security:authorize ifAnyGranted="A_VIEW_QW"><!--  <span onclick="editComment('${id}');" style="cursor: pointer;">编辑</span>--> <span onclick="delComment('${id }',this);" style="cursor: pointer;">删除</span></security:authorize></dt>
						<dd>${content}</dd>
					</dl>
					</div>
					</div>
					</s:iterator>
					
					
					</div>
					
					<!-- 分页验证权限 -->
					<security:authorize ifAnyGranted="A_VIEW_QW"><input type="hidden" id="authority" value="A_VIEW" /></security:authorize>
					<div id="pagecount">
					<ul>
						<li class="pagepri" <s:if test="!commentPage.hasPre">style="display:none" </s:if>><a href="#articlelist" onclick="javascript:prePage();">上一页</a></li>
						<li class="pagenext" <s:if test="!commentPage.hasNext">style="display:none" </s:if>><a href="#articlelist" onclick="javascript:nextPage();">下一页</a></li>
					</ul>
					<input type="hidden" id="totalPages" value="${commentPage.totalPages}"/>
					<input type="hidden" id="pageNo" value="${commentPage.prePage}"/>
					<input type="hidden" id="article-id" value="${article.id}"/>
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
				
				<div id="replydiv">
					<div class="replylogin" <s:if test='userName != "roleAnonymous" && userName != null'> style="display:none"</s:if>>
						<ul>
							<li>用户名</li>
							<li><input class="_commentloginname" type="text" value="" /></li>
							<li>密码</li>
							<li><input class="_commentpassword" type="password" value="" /></li>
							<li><a href="#replyconent" onclick="commentlogin();">登录</a></li>
						</ul>
					</div>
					<div class="replyconent">
						<h3>评论内容_不能超过250字，需审核后才会公布，请自觉遵守互联网相关政策法规</h3>
						<textarea id="reply"></textarea>
						<p><a href="#articlelist"><span onclick="javascript:comment(${article.id});">&gt;发表评论&lt;</span></a></p>
						<ul class="replyface">
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				<%@ include file="/common/content/randomFourArticle.jsp"%>
				</div>	
				
				
				<%@ include file="/common/searcharticle.jsp"%>
				
                <div id="bannerThree">
                	<s:if test="adsArray.length>2">
						<s:if test="adsArray[2].type.name==1">
							<s:property value="adsArray[2].script" escapeJavaScript="false" />
						</s:if>
						<s:else>
						<a href="<s:property value='adsArray[2].url'/>" target="_blank">
							<img src="${ctx }<s:property value='adsArray[2].image'/>"
								height="100px" width="600px"
								alt="<s:property value='adsArray[2].description'/>" />
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
				<div id="rightContent_top">
				
				<s:if test="adsArray.length>3">
					<s:if test="adsArray[3].type.name==1">
						<s:property value="adsArray[3].script" escapeJavaScript="false" />
					</s:if>
					<s:else>
					<a href="<s:property value='adsArray[3].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[3].image'/>"
							height="200px" width="348px"
							alt="<s:property value='adsArray[3].description'/>" />
					</a>
					</s:else>
				</s:if>
				</div>
				 
				<div class="votes">
					<h3>Featured Channels_频道精选</h3>
					<h4>
						 <s:iterator value="recommendArticleAll">
							 <a href="${ctx}/circlecontent.action?article.id=${id}">
									<s:if test="imageUri!=null && imageUri != ''"><img src="${ctx}${imageUri}" alt="" width="100" height="100" />
									</s:if><s:else>
										<img src="images/vote1.gif" alt=""  width="100" height="100"/>	
								</s:else>
							</a>	
						</s:iterator>
					</h4>
					
				</div>

				
				<div class="hotCommodity">
				<h3>FOCUS_焦点人物</h3>
				<p>
				<s:if test="adsArray.length>3">
					<s:if test="adsArray[9].type.name==1">
						<s:property value="adsArray[9].script" escapeJavaScript="false" />
					</s:if>
					<s:else>
					<a href="<s:property value='adsArray[9].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[9].image'/>"
							height="204px" width="204px"
							alt="<s:property value='adsArray[9].description'/>" />
					</a>
					</s:else>
				</s:if>
				
				</p>
				</div>


				<div class="hotCommodity">
				<h3>Recommend book 本周推荐书品</h3>
				<p>
				<s:if test="adsArray.length>3">
					<s:if test="adsArray[10].type.name==1">
						<s:property value="adsArray[10].script" escapeJavaScript="false" />
					</s:if>
					<s:else>
					<a href="<s:property value='adsArray[10].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[10].image'/>"
							height="204px" width="204px"
							alt="<s:property value='adsArray[10].description'/>" />
					</a>
					</s:else>
				</s:if>
				</p>
				</div>
				
				<div class="hotCommodity">
				<h3>Recommend book 热销商品</h3>
				<p>
				<s:if test="adsArray.length>3">
					<s:if test="adsArray[11].type.name==1">
						<s:property value="adsArray[11].script" escapeJavaScript="false" />
					</s:if>
					<s:else>
					<a href="<s:property value='adsArray[11].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[11].image'/>"
							height="204px" width="204px"
							alt="<s:property value='adsArray[11].description'/>" />
					</a>
					</s:else>
				</s:if>
				</p>
				</div>
				
				
				<div class="topTags">
					<h3>HOT TAG_热门标签_all</h3>
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
				
				<div class="advtype2">
						<s:if test="adsArray.length>4">
							<s:if test="adsArray[4].type.name==1">
								<s:property value="adsArray[4].script" escapeJavaScript="false" />
							</s:if>
							<s:else>
							<a href="<s:property value='adsArray[4].url'/>" target="_blank">
								<img src="${ctx }<s:property value='adsArray[4].image'/>"
									height="200px" width="348px"
									alt="<s:property value='adsArray[4].description'/>" />
							</a>
							</s:else>
						</s:if><s:else>
							<img src="images/hotcommodity1.gif" alt="" />
						</s:else>
						
						<s:if test="adsArray.length>5">
							<s:if test="adsArray[5].type.name==1">
								<s:property value="adsArray[5].script" escapeJavaScript="false" />
							</s:if>
							<s:else>
							<a href="<s:property value='adsArray[5].url'/>" target="_blank">
								<img src="${ctx }<s:property value='adsArray[5].image'/>"
									height="200px" width="348px"
									alt="<s:property value='adsArray[5].description'/>" />
							</a>
							</s:else>
						</s:if><s:else>
							<img src="images/hotcommodity1.gif" alt="" />
						</s:else> 
						
						<s:if test="adsArray.length>6">
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
						</s:if><s:else>
							<img src="images/hotcommodity1.gif" alt="" />
						</s:else>
				</div>
				
				<div class="advtype3">
					<s:if test="adsArray.length>7">
						<s:if test="adsArray[7].type.name==1">
							<s:property value="adsArray[7].script" escapeJavaScript="false" />
						</s:if>
						<s:else>
							<img src="${ctx }<s:property value='adsArray[7].image'/>"
								height="204px" width="204px"
								alt="<s:property value='adsArray[7].description'/>" />
						</s:else>
					</s:if><s:else>
						<img src="images/hotcommodity1.gif" alt="" />
					</s:else>
				</div>
				
				<!-- 票数结束-->
				<div class="clean"></div>
			</div>
        <div class="clean"></div>
        <div id="bannerFour">
		     <s:if test="adsArray.length>8">
				<s:if test="adsArray[8].type.name==1">
					<s:property value="adsArray[8].script" escapeJavaScript="false" />
				</s:if>
				<s:else>
				<a href="<s:property value='adsArray[0].url'/>" target="_blank">
					<img src="${ctx }<s:property value='adsArray[8].image'/>"
						height="100px" width="960px"
						alt="<s:property value='adsArray[8].description'/>" />
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