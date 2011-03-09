<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>秀场</title>
		<link type="text/css" rel="stylesheet" href="css/show.css" />
		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
		<script type="text/javascript" src="js/slide.js"></script>
		<script type="text/javascript"></script>
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
					<span>您的位置:首页&gt;秀场&gt;</span>
				</p>
                <hr class="splitline"/>
			</div>
			
            <div id="maincontent">
                 <div id="listleft">
                        <ul>
							<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=all"><span>ALL_查看全部</span></a></li>
							<li><span>&nbsp;</span></li>
						    <s:iterator value="category.child" status="stat">
								<li><a href="${ctx}/show?showCategory.id=${id }"><span><s:property value="name"/>_<s:property value="nameEn"/></span></a></li>
							</s:iterator>
							<li><span>&nbsp;</span></li>
							<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=recommend"><span>UP_特别推荐</span></a></li>
							<li><span>&nbsp;</span></li>
							<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=news"><span>NEWS_最新发布</span></a></li>
							<li><span>&nbsp;</span></li>
							<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=vote"><span>VOTE_投票排行</span></a></li>
							<li><span>&nbsp;</span></li>
							
						</ul>
                 </div>
                 
                 <div id="listcenter">
                         <div class="tabshow_title">
                            <h3>CASE SHOW _${showCategory.name}</h3>
                            <ul>
                                <li><span><a href="${ctx}/showlist.action?showCategory.id=${showCategory.id}&isNewsVote=all" class="tablink" >列表显示</a></span></li>
                                <li><span><a href="${ctx}/show?showCategory.id=${showCategory.id}&isNewsVote=all" class="tablink" >图标显示</a></span></li>
                            </ul>
                        </div>
                        <div id="case_show">
                        
                                <div id="case_show_content">
                                  <s:iterator value="page.result">
                                     <div id="case_show_content_detalied">
                                           <a class="coverBg" href="${ctx}/showcontent.action?article.id=${id}"><p>(查看全部)</p></a>
                                           <s:if test="imageUri!=null && imageUri != ''"><img width="140" height="140" src="${ctx}${imageUri}" alt="" />
											</s:if><s:else>
											 <a class="coverBg" href="${ctx}/showcontent.action?article.id=${id}"><p>(查看全部)</p></a>
                                              <img width="140" height="140" src="images/hot_essay.jpg" />
                                            </s:else>
                                           <p>&nbsp;</p>
                                           <h3><a href="${ctx}/showcontent.action?article.id=${id}" title="${title }"><ouun:substring length="8" value="${title }"/></a></h3>
                                           <p>发布时间_<s:date name="createDate" format="yyyy-MM-dd"/></p>
                                           <p>${showCategory.name}</p>
                                           <p>阅读_${readCount} 评价_<s:property value="comments.size"/></p>
                                           <p>投票_${voteCount }</p>
                                     </div>
                                  </s:iterator>
                                    
                               </div>
                        </div>
                        
                    <div style="padding-left:10px;padding-top:40px;width:600px">
						第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="show?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}&showCategory.id=${showCategory.id}&isNewsVote=${isNewsVote}">上一页</a>
						</s:if>
						<s:if test="page.hasNext">
							<a href="show?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}&showCategory.id=${showCategory.id}&isNewsVote=${isNewsVote}">下一页</a>
						</s:if>
					</div>
                       
                      <%@ include file="/common/searcharticle_pic.jsp"%>
                 </div>
                 <div id="listright">
                    <div>
                       <s:if test="adsArray.length>0">
								<s:if test="adsArray[1].type.name==1">
									<s:property value="adsArray[1].script" escapeJavaScript="false"/>
								</s:if><s:else>
									<a href="<s:property value='adsArray[1].url'/>" target="_blank">
									<img src="${ctx }<s:property value='adsArray[1].image'/>" height="600px" width="150px" alt="<s:property value='adsArray[1].description'/>" />
									</a>
								</s:else>
					  </s:if>
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