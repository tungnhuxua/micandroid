<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>资讯_搜索</title>
		<link type="text/css" rel="stylesheet" href="css/newslist.css" />
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
		<script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript" src="js/newslist.js"></script>
	</head>
	<body>
	<div class="container">
		<%@ include file="/common/top.jsp"%>

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
					<span>您的位置:首页&gt;<a href="${ctx }/news">资讯</a>&gt;搜索</span>
				</p>
			</div>
			<div id="contentLeft">
				<div id="totop">
					<a href="#top" rel="">
						<img src="images/toTop.gif" alt="返回顶部" />
					</a>
				</div>
				
				<div id="bannertop">
					<s:if test="adsArray.length>1">
					<s:if test="adsArray[1].type.name==1">
						<s:property value="adsArray[1].script" escapeJavaScript="false"/>
					</s:if><s:else>
					   <a href="<s:property value='adsArray[1].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[1].image'/>" height="150px" width="100%" alt="<s:property value='adsArray[1].description'/>" />
					   </a>
					</s:else>
				</s:if>
				</div>
				<div id="tabshow">
					<h3>约有${page.totalCount}项符合设计的查询,共${page.totalPages}页当前页为${page.pageNo}页</h3>
					<ul>
					</ul>
				</div>
				
				
				
					<div id="tablist">
					<s:iterator value="page.result">
					<div class="news">
						<div class="newsleft">
							<a href="${ctx}/newscontent.action?article.id=${id}">
							<s:if test="imageUri!=null && imageUri != ''"><img src="${ctx}${imageUri}" alt="" />
							</s:if><s:else>
								<img src="images/newsimages.gif" alt="" />	
							</s:else>
							</a>					
						</div>
						
						<div class="newsright">
							<h3><a href="${ctx}/newscontent.action?article.id=${id}">${title }</a></h3>
							<h4><s:date name="createDate" format="yyyy-MM-dd"/>│  阅读_${readCount}  │  评论_<s:property value="comments.size"/>  │  投票_${voteCount }</h4>
							<p>
								<ouun:substring length="50" value="${content}"/>
							</p>
						</div>
					</div>
					</s:iterator>
					
					</div>
					<div style="padding-left:96px">
						第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="newssearcharticle.action?page.pageNo=${page.prePage}&newsContent=${newsContent}"">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="newssearcharticle.action?newsContent=${newsContent}&page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="newssearcharticle.action?page.pageNo=${page.nextPage}&newsContent=${newsContent}"">下一页</a>
						</s:if>
					</div>
								
				<%@ include file="/common/searcharticle.jsp"%>
				
				<div id="bannerThree">
					<s:if test="adsArray.length>2">
					<s:if test="adsArray[2].type.name==1">
						<s:property value="adsArray[2].script" escapeJavaScript="false"/>
					</s:if><s:else>
					<a href="<s:property value='adsArray[0].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[2].image'/>" height="100px" width="100%" alt="<s:property value='adsArray[2].description'/>" />
					</a>
					</s:else>
				</s:if>
				</div>
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
                                欢迎转载，敬请书名转载设计圈网 ooowo.coe
                            </p>
                        </div>
					</div>	
                </div>
				
			</div>
			<div id="rightContent">
				<div id="rightContent_top">
					<s:if test="adsArray.length>3">
					<s:if test="adsArray[3].type.name==1">
						<s:property value="adsArray[3].script" escapeJavaScript="false"/>
					</s:if><s:else>
					   <a href="<s:property value='adsArray[3].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[3].image'/>" height="200px" width="348" alt="<s:property value='adsArray[3].description'/>" />
					   </a>
					</s:else>
				</s:if>
				</div>
				<div id="rightContent_top2">
					<s:if test="adsArray.length>4">
					<s:if test="adsArray[4].type.name==1">
						<s:property value="adsArray[4].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[0].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[4].image'/>" height="120px" width="348" alt="<s:property value='adsArray[4].description'/>" />
					    </a>
					</s:else>
				</s:if>
				</div>
				<div class="votes">
					<h2>Vote Top 10 投票排行 <a href="javascript:;" class="vottab" rel="votesweek">本周</a> <a href="javascript:;" class="vottab" rel="votesmonth">本月</a></h2>
					
					<div id="votesweek" class="votweek">
						<dl>
							<s:iterator value="weekVotes" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="javascript:;">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><ouun:substring length="14" value="${title}"/></dd>
							</s:else>
						</s:iterator>
							
						</dl>
					</div>
				
					<div id="votesmonth" class="votmonth">
						<dl>
							<s:iterator value="monthVotes" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="javascript:;">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><ouun:substring length="14" value="${title}"/></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				</div>
				<div class="votes">
					<h2>HOT Comments 热点评论 <a href="javascript:;" class="comtab" rel="commentsweek">本周</a> <a href="javascript:;" class="comtab" rel="commentsmonth">本月</a></h2>
					
					<div id="commentsweek" class="votweek">
						<dl>
							<dt>
								<a href="javascript:;"><img src="images/vote1.gif" alt="" /></a>
								<a><span>票数</span><span>123</span></a>
							</dt>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
							<dd>达人大赛达人大赛达人大赛达人</dd>
						</dl>
					</div>
				
					<div id="commentsmonth" class="votmonth">
						<dl>
							<dt>
								<a href="javascript:;"><img src="images/vote1.gif" alt="" /></a>
								<a><span>票数</span><span>123</span></a>
							</dt>
							<dd>百变百变百变百变百变百变百变</dd>		
							<dd>百变百变百变百变百变百变百变</dd>	
							<dd>百变百变百变百变百变百变百变</dd>		
							<dd>百变百变百变百变百变百变百变</dd>	
							<dd>百变百变百变百变百变百变百变</dd>		
							<dd>百变百变百变百变百变百变百变</dd>	
							<dd>百变百变百变百变百变百变百变</dd>		
							<dd>百变百变百变百变百变百变百变</dd>	
						</dl>
					</div>
				</div>
				<div class="votes">
					<h2>HOT Top10 热点资讯 <a href="javascript:;" class="hottab" rel="hotweek">本周</a> <a href="javascript:;" class="hottab" rel="hotmonth">本月</a></h2>
					
					<div id="hotweek" class="votweek">
						<dl>
							<s:iterator value="weekHots" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="javascript:;">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><ouun:substring length="14" value="${title}"/></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				
					<div id="hotmonth" class="votmonth">
						<dl>
							<s:iterator value="monthHots" status="sta">
							<s:if test="#sta.first">
								<dt>
								<a href="javascript:;">
								<s:if test="imageUri!=null">
								<img height="100px" width="100px" src="${ctx }${imageUri}" alt="${title }" />
								</s:if><s:else>
								<img src="images/vote1.gif"  alt="${title }" />
								</s:else>
								
								</a>
								<a><span>票数</span><span>${voteCount}</span></a>
							</dt>
							</s:if><s:else>
								<dd><ouun:substring length="14" value="${title}"/></dd>
							</s:else>
						</s:iterator>
						</dl>
					</div>
				</div>
				
				
				<div class="hotCommodity">
					<h3>Recommend book 本周推荐书品</h3>
					<p>
						<img src="images/hotcommodity1.gif" alt="本周推荐书品" />
					</p>
				</div>
				
				
				<div class="hotCommodity">
					<h3>Recommend book 热销商品</h3>
					<p>
						<img src="images/hotcommodity1.gif" alt="本周推荐书品" />
						<img src="images/hotcommodity1.gif" alt="本周推荐书品" />
					</p>
				</div>
				
				<div class="webspecialimgs">
					<h3><span>WEB Special_圈网精选</span></h3>
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
					<img src="images/newsimages.gif" alt="" />
				</div>
				<div class="clean"></div>
			</div>
        <div class="clean"></div>
        <div id="bannerFour">
					<s:if test="adsArray.length>5">
					<s:if test="adsArray[5].type.name==1">
						<s:property value="adsArray[5].script" escapeJavaScript="false"/>
					</s:if><s:else>
					<a href="<s:property value='adsArray[0].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[5].image'/>" height="100px" width="960" alt="<s:property value='adsArray[5].description'/>" />
					</a>
					</s:else>
				</s:if>
        </div>
		<%@ include file="/common/foot.jsp"%>
			</div>
		</div>
	</body>
</html>