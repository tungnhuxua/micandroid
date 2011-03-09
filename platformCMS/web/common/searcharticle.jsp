<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script language="javascript">
   function search_NewTitle(){
	   var newstitle = document.search.newsContent.value;
    if(document.all['seachto'][1].checked){
	 window.location.href='http://www.google.com/search?hl=zh-CN&inlang=zh-CN&ie=GB2312&lr=lang_zh-CN&q=刘';
	}else{
		if(newstitle.length<1){
		 alert("请您输入要搜索的新闻内容");
		}else{
			document.search.submit(); 
		}
	}
   }
</script>
			<div id="contentFoot">
					<div id="contentFoot_nav">
						<h3><span>导航&gt;</span><span>设计圈首页&gt;</span>
						
						<span class="selectNav">
							<s:if test='action == "/news" || action == "/newslist" || action == "/newscontent" || action == "/newspiclist" || action == "/newssearcharticle"'>
								资讯
							</s:if>
							<s:if test='action == "/person" || action == "/personlist" || action == "/personcontent" || action == "/personpiclist" || action == "/personsearcharticle"'>
								人物 
							</s:if>
							<s:if test='action == "/show" || action == "/showlist" || action == "/showcontent" || action == "/showsearcharticle"'>
								秀场
							</s:if>
						    <s:if test='action == "/special" || action == "/speciallist" || action == "/specialcontent" || action == "/specialpiclist" || action == "/specialsearcharticle"'>
							专题
							</s:if>
							<s:if test='action == "/circle" || action == "/circlelist" || action == "/circlecontent" || action == "/circlepiclist" || action == "/circlesearcharticle"'>
								圈网
							</s:if>
							<s:if test='action == "/searcharticlecontent"'>
								
							</s:if>
						</span></h3>
						<ul>
						    <s:if test='action == "/news" || action == "/newslist" || action == "/newscontent" || action == "/newspiclist" || action == "/newssearcharticle"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/newslist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
							<s:if test='action == "/person" || action == "/personlist" || action == "/personcontent" || action == "/personpiclist" || action == "/personsearcharticle"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/personlist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
							<s:if test='action == "/show" || action == "/showlist" || action == "/showcontent" || action == "/showsearcharticle"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/show?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
						    <s:if test='action == "/special" || action == "/speciallist" || action == "/specialcontent" || action == "/specialpiclist" || action == "/specialsearcharticle"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/speciallist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
							<s:if test='action == "/circle" || action == "/circlelist" || action == "/circlecontent" || action == "/circlepiclist" || action == "/circlesearcharticle"'>
								<s:iterator value="category.child" status="stat">
									<li <s:if test="#stat.last">class="lilaster"</s:if>><a href="${ctx}/circlelist.action?showCategory.id=${id }"><span>_<s:property value="name"/></span></a></li>
								</s:iterator>
							</s:if>
							<s:if test='action == "/searcharticlecontent"'>
								
							</s:if>
						</ul>
					</div>
					<div id="contentFoot_seach">
					<s:if test='action == "/news" || action == "/newslist" || action == "/newscontent" || action == "/newspiclist" || action == "/newssearcharticle"'>
						<form action="${ctx }/newssearcharticle.action" id="search" method="post" name="search">
							<ul>
	                       	  <li class="firstLi"><input class="indexss" type="text" name="newsContent" value="" class="foot_seach" /></li>
	                            <li><a href="javascript:search_NewTitle()"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                    </form>
					</s:if>
					<s:if test='action == "/person" || action == "/personlist" || action == "/personcontent" || action == "/personpiclist" || action == "/personsearcharticle"'>
						<form action="${ctx }/personsearcharticle.action" id="search" method="post" name="search">
							<ul>
	                       	  <li class="firstLi"><input class="indexss" type="text" name="newsContent" value="" class="foot_seach" /></li>
	                            <li><a href="javascript:search_NewTitle()"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                    </form>	
					</s:if>
					<s:if test='action == "/show" || action == "/showlist" || action == "/showcontent" || action == "/showsearcharticle"'>
						<form action="${ctx }/showsearcharticle.action" id="search" method="post" name="search">
							<ul>
	                       	  <li class="firstLi"><input class="indexss" type="text" name="newsContent" value="" class="foot_seach" /></li>
	                            <li><a href="javascript:search_NewTitle()"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                    </form>	
				   </s:if>
				   <s:if test='action == "/special" || action == "/speciallist" || action == "/specialcontent" || action == "/specialpiclist" || action == "/specialsearcharticle"'>
						<form action="${ctx }/specialsearcharticle.action" id="search" method="post" name="search">
							<ul>
	                       	  <li class="firstLi"><input class="indexss" type="text" name="newsContent" value="" class="foot_seach" /></li>
	                            <li><a href="javascript:search_NewTitle()"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                    </form>	
					</s:if>
					<s:if test='action == "/circle" || action == "/circlelist" || action == "/circlecontent" || action == "/circlepiclist" || action == "/circlesearcharticle"'>
							<form action="${ctx }/circlesearcharticle.action" id="search" method="post" name="search">
							<ul>
	                       	  <li class="firstLi"><input class="indexss" type="text" name="newsContent" value="" class="foot_seach" /></li>
	                            <li><a href="javascript:search_NewTitle()"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                    </form>
					</s:if>
					<s:if test='action == "/searcharticlecontent"'>
						<form action="${ctx }/searcharticle.action" id="search" method="post" name="search">
							<ul>
	                       	  <li class="firstLi"><input class="indexss" type="text" name="newsContent" value="" class="foot_seach" /></li>
	                            <li><a href="javascript:search_NewTitle()"><img src="images/seachbutton2.gif" alt="" /></a></li>
	                        </ul>
	                    </form>	
					</s:if>
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