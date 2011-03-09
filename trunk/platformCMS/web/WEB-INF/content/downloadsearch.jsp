
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>下载</title>
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" />
		<link type="text/css" rel="stylesheet" href="css/download.css" />
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

<script type="text/javascript">
     function resourceSearch(){
    	 var resourceName = document.resourcesearch.searchResourceName.value;
    	 if(resourceName.length<1){
        	 alert("请您输入要搜索的下载内容");
         }else{
             document.resourcesearch.submit();
         }
     }
     
     function search_NewTitle(){
  	   var newstitle = document.search.newsContent.value;
	   if(document.all['seachto'][1].checked){
	  	 window.location.href='http://www.google.com';
	  	}else{
	  		if(newstitle.length<1){
	  		 alert("请您输入要搜索的新闻内容");
	  		}else{
	  			document.search.submit(); 
	  		}
	  	}
  }
</script>
	</head>
	<body>
		<%@ include file="/common/userlogin.jsp" %>
		<!-- top -->
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
					<span>您的位置:首页&gt;下载&gt;</span>
				</p>
                <hr class="splitline"/>
			</div>
            
            <div id="downloadseach">
                <div id="downloadseach_logo">
                     <ul>
                         <li>Logo</li>
                         <li><a href="${ctx}/download?recommend=4&letter=a">_a</a><a href="${ctx}/download?recommend=4&letter=b">_b</a><a href="${ctx}/download?recommend=4&letter=c">_c</a><a href="${ctx}/download?recommend=4&letter=d">_d</a><a href="${ctx}/download?recommend=4&letter=e">_e</a><a href="${ctx}/download?recommend=4&letter=f">_f</a><a href="${ctx}/download?recommend=4&letter=g">_g</a></li>
						 	<li><a href="${ctx}/download?recommend=4&letter=h">_h</a><a href="${ctx}/download?recommend=4&letter=i">_i</a><a href="${ctx}/download?recommend=4&letter=j">_j</a><a href="${ctx}/download?recommend=4&letter=k">_k</a><a href="${ctx}/download?recommend=4&letter=l">_l</a><a href="${ctx}/download?recommend=4&letter=m">_m</a><a href="${ctx}/download?recommend=4&letter=n">_n</a></li>
						 	<li><a href="${ctx}/download?recommend=4&letter=o">_o</a><a href="${ctx}/download?recommend=4&letter=p">_p</a><a href="${ctx}/download?recommend=4&letter=q">_q</a><a href="${ctx}/download?recommend=4&letter=r">_r</a><a href="${ctx}/download?recommend=4&letter=s">_s</a><a href="${ctx}/download?recommend=4&letter=t">_t</a></li>
						 	<li><a href="${ctx}/download?recommend=4&letter=u">_u</a><a href="${ctx}/download?recommend=4&letter=v">_v</a><a href="${ctx}/download?recommend=4&letter=w">_w</a><a href="${ctx}/download?recommend=4&letter=x">_x</a><a href="${ctx}/download?recommend=4&letter=y">_y</a><a href="${ctx}/download?recommend=4&letter=z">_z</a></li>
                     </ul>
                       
                </div>
                
                <div id="downloadseach_image">
                     <ul>
                     <li>图库</li>
                     <li>&nbsp;</li>
                     <s:iterator value="type.child" status="stat">
                         <li><a href="${ctx}/download?type.id=${id}">_<s:property value="name"/></a></li>
                     </s:iterator>
                     </ul>
                        
                </div>
                
 				<form action="downloadsearch.action" method="post" name="resourcesearch">
                <div id="downloadseach_seach">
                   <ul>
                         <li><input type="text" name="searchResourceName"  class="indexss required" size="50"/></li>
                         <li><a href="javascript:resourceSearch()"><img src="images/seachbutton2.gif" alt="" /></a></li>
                    </ul>
                </div>
                </form>
                <div id="downloadseach_hr">
                  <hr class="splitline"/>
                </div>
                
            </div>
			
            <div id="maincontent">
                 <div id="listleft">
                        <ul>
							<li><a href="${ctx}/download"><span>ALL_查看全部</span></a></li>
							<li><span>&nbsp;</span></li>
							<li><a href="#"><span>Logo</span></a></li>
                            <li><span>&nbsp;</span></li>
							<li><a href="${ctx}/download?recommend=4&letter=a">_a</a><a href="${ctx}/download?recommend=4&letter=b">_b</a><a href="${ctx}/download?recommend=4&letter=c">_c</a><a href="${ctx}/download?recommend=4&letter=d">_d</a><a href="${ctx}/download?recommend=4&letter=e">_e</a><a href="${ctx}/download?recommend=4&letter=f">_f</a><a href="${ctx}/download?recommend=4&letter=g">_g</a></li>
						 	<li><a href="${ctx}/download?recommend=4&letter=h">_h</a><a href="${ctx}/download?recommend=4&letter=i">_i</a><a href="${ctx}/download?recommend=4&letter=j">_j</a><a href="${ctx}/download?recommend=4&letter=k">_k</a><a href="${ctx}/download?recommend=4&letter=l">_l</a><a href="${ctx}/download?recommend=4&letter=m">_m</a><a href="${ctx}/download?recommend=4&letter=n">_n</a></li>
						 	<li><a href="${ctx}/download?recommend=4&letter=o">_o</a><a href="${ctx}/download?recommend=4&letter=p">_p</a><a href="${ctx}/download?recommend=4&letter=q">_q</a><a href="${ctx}/download?recommend=4&letter=r">_r</a><a href="${ctx}/download?recommend=4&letter=s">_s</a><a href="${ctx}/download?recommend=4&letter=t">_t</a></li>
						 	<li><a href="${ctx}/download?recommend=4&letter=u">_u</a><a href="${ctx}/download?recommend=4&letter=v">_v</a><a href="${ctx}/download?recommend=4&letter=w">_w</a><a href="${ctx}/download?recommend=4&letter=x">_x</a><a href="${ctx}/download?recommend=4&letter=y">_y</a><a href="${ctx}/download?recommend=4&letter=z">_z</a></li>
                            <li><span>&nbsp;</span></li>
							<li><a href="#"><span>图库</span></a></li>
                            <li><span>&nbsp;</span></li>
							<s:iterator value="type.child" status="stat">
		                         <li><a href="${ctx}/download?type.id=${id}">_<s:property value="name"/></a></li>
		                     </s:iterator>
							<li><span>&nbsp;</span></li>
							<li><a href="${ctx}/download?recommend=1"><span>UP_特别推荐</span></a></li>
							<li><span>&nbsp;</span></li>
							<li><a href="${ctx}/download?recommend=2"><span>NEWS_最新发布</span></a></li>
							<li><span>&nbsp;</span></li>
							<li><a href="${ctx}/download?recommend=3"><span>VOTE_人气排行</span></a></li>
							<li><span>&nbsp;</span></li>
							
						</ul>
                 </div>
                 
                 <div id="listcenter">
                         <div class="tabshow_title">
                            <h3>下载_<a href="${ctx}/download">搜索</a><span class="selectNav" style="padding-left: 30px;">约有${page.totalCount}项符合设计的查询,共${page.totalPages}页当前页为${page.pageNo}页</span></h3>
                            <ul>
                                <li><span></span></li>
                                <li><span></span></li>
                            </ul>
                        </div>
                        <div id="case_show">
                                <div id="case_show_content">
                                <s:iterator value="page.result">
                                     <div id="case_show_content_detalied">
                                           <a class="coverBg" href="${ctx}/downloadcontent.action?resource.id=${id}&&type.id=${type.id}">READ MORE<p>(查看全部)</p></a>
                                           <s:if test="uri!=null && uri!=''">
                                           	<img src="${ctx}${uri}"  height="140px" width="140px"/>
                                           </s:if><s:else>
                                           <img src="images/hot_essay.jpg" /></s:else>
                                           <p>&nbsp;</p>
                                           <h3>${name }</h3>
                                           <p>${type.name}</p>
                                           <p>TAG:<ouun:articletag href="#" value="${tag}" /></p>
                                     </div>
                                 </s:iterator>    
                                </div>
                        </div>
                       <div style="width: 600px;padding-top: 20px;padding-left: 20px;">
                                	  第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="${ctx}/downloadsearch.action?page.pageNo=${page.prePage}&searchResourceName=${searchResourceName}">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="downloadsearch.action?searchResourceName=${searchResourceName}&page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="${ctx}/downloadsearch.action?page.pageNo=${page.nextPage}&searchResourceName=${searchResourceName}">下一页</a>
						</s:if>
                       </div>
                       
                     <div style="padding-left: 20px;">
                		   <div id="contentFoot_nav_1">
                                <h3><span>导航&gt;</span><span>设计圈首页&gt;</span><span class="selectNav">搜索</span></h3>
                               
                            </div>
                            <!-- 底部的搜索条 -->
                            <div id="contentFoot_seach_1">
								<form action="${ctx }/searcharticle.action" name="search" method="post">
									<ul>
				                       	  <li class="firstLi"><input type="text"  id="newsContent" name="newsContent" value="" class="indexss"/></li>
				                          <li><a href="javascript:search_NewTitle()"><img src="images/seachbutton2.gif" alt="" /></a></li>
				                        </ul>
			                    </form>
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
                    
                 </div>
                 
                 <div id="listright">
                    <div>
                    	<s:if test="adsArray.length>1">
					<s:if test="adsArray[1].type.name==1">
						<s:property value="adsArray[1].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[1].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[1].image'/>" height="600px" width="160px" alt="<s:property value='adsArray[1].description'/>" />
						</a>
					</s:else>
				</s:if>
                    </div>
                 </div>
            </div>
            			
			<div class="clean"></div>
			
		</div>
        <div class="clean"></div>

        <div id="bannerFour">
        <s:if test="adsArray.length>3">
					<s:if test="adsArray[3].type.name==1">
						<s:property value="adsArray[3].script" escapeJavaScript="false"/>
					</s:if><s:else>
						<a href="<s:property value='adsArray[3].url'/>" target="_blank">
						<img src="${ctx }<s:property value='adsArray[3].image'/>" height="100px" width="960px" alt="<s:property value='adsArray[3].description'/>" />
						</a>
					</s:else>
				</s:if>
        </div>
		<!-- foot -->
		<%@ include file="/common/foot.jsp"%>
	</body>
</html>