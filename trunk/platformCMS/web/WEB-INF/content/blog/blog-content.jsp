<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${tomember.name}'s blog</title>
<link type="text/css" href="${ctx }/css/ui.all.css" rel="stylesheet" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/js/blog/content.js"></script>
</head>
<body>
<div class="container">	
	<div class="header">
			<%@include file="/common/blog-header.jsp" %>
	</div>
	<div class="content">
		
		<div class="toubu">
			<%@include file="/common/blog-content-header-to.jsp" %>
		</div>
		<div class="main">
			<div class="bqmainbh">
				<div class="blogbiaoqian">
					<div class="fbtouxiang">
						<dl>
							<dt><s:if test="entity.imageUri!=null && entity.imageUri != ''"><img src="${ctx}${entity.imageUri}" alt="${entity.title}" width="110" height="115"/>
							</s:if></dt>
							<dd style="font-size: 11px;"><s:date name="entity.createDate" format="yyyy-MM-dd hh:mm:ss" /></dd>
							<dd>发布于  <s:if test="entity.category!=null">${entity.category.name}</s:if><s:elseif test="entity.logCategory!=null">${entity.logCategory.name}</s:elseif>${entity.showCategory.name}<s:else></s:else> </dd>
							<dd>TAG_<ouun:articletag href="#" value="${entity.tag}"/></dd>
						</dl>
					</div>
					<div class="soucanleft">
					<ul>
						<li><a href="">_浏览_${entity.readCount }</a></li>
						<li><a href="">_评论_<s:property value="entity.comments.size"/></a></li>
						<li><a href="">_投票_${entity.voteCount }</a></li>
					</ul>	
					</div>
				</div>
				<div class="zhuti">
				<div class="shoucanweizhi">
					<ul class="scweizhi">
						<li><a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">首页</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">设计圈博客</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">${tomember.name}</a></li>
						<!-- <li>_<a href="${ctx }/blog/blog-log.action?tomember.id=${member.id}">日志</a></li> -->
						<li>_<a href="#">${entity.title }</a></li>
					</ul>
					<ul class="scxuangxiang">
						<s:if test="preArticle!=null"><li>_<a href="${ctx}/blog/blog-content.action?id=${preArticle.id}&&tomember.id=${tomember.id}">上一篇</a></li></s:if>
						<s:if test="nextArticle!=null"><li>_<a href="${ctx}/blog/blog-content.action?id=${nextArticle.id}&&tomember.id=${tomember.id}">下一篇</a></li></s:if>
						<li>_<s:if test="entity.isblog == '1'"><a href="${ctx }/blog/blog-log.action?tomember.id=${tomember.id}">返回日志</a></s:if> <s:else><a href="${ctx }/blog/blog-show.action?tomember.id=${tomember.id}">返回秀场 </a></s:else> </li>
					</ul>
				</div>
				<div class="wezhanglb">
					<div class="wezhangbiaoti">${entity.title }</div>
					<div class="wezhangzw">${entity.content }</div>
				</div>
				<div class="ding"><img id="ding" src="${ctx }/images/ding.gif" style="cursor: pointer;" onclick="woding(${entity.id});" /> 
									<img src="${ctx }/images/tanks.gif" alt="" class="thanks" style="display: none;" />
									_${entity.voteCount }票_已经有${entity.voteCount }位会员顶过该文章</div>
				<div class="pianfu">
					<div class="shangxiapian">
						<ul>
						<s:if test="preArticle!=null">
							<li><a href="${ctx}/blog/blog-content.action?id=${preArticle.id}&&tomember.id=${tomember.id}">上一篇_
							${preArticle.title }</a></li>
						</s:if>
							<s:if test="nextArticle!=null">
							<li><a href="${ctx}/blog/blog-content.action?id=${nextArticle.id}&&tomember.id=${tomember.id}">下一篇_
							${nextArticle.title }</a></li>
						</s:if>
						</ul>
					</div>
					<div class="xiaoxuanxiang">
					<div><img height="20" width="98" src="${ctx }/images/tools.gif"/></div>
					<ul>
						<li><a href=""><img src="${ctx }/images/a.gif"/></a></li>
						<li><a href=""><img src="${ctx }/images/b.gif"/></a></li>
						<li><a href=""><img src="${ctx }/images/c.gif"/></a></li>
						<li><a href=""><img src="${ctx }/images/d.gif"/></a></li>
						<li><a href=""><img src="${ctx }/images/e.gif"/></a></li>
						<li><a href=""><img src="${ctx }/images/f.gif"/></a></li>
					</ul>
					</div>
				</div>
				<div class="wengzhangfk">
					<div class="fk">
						<ul>
							<li>_<a href="#">访客_(<s:property value="blogArticleAccessList.size"/>人)</a></li>
							<li>_<a href="#">Comments</a></li>
						</ul>
					</div>
				</div>
				<div class="lftouxiang">
					<ul>
					<s:iterator value="blogArticleAccessList">
							 <li>
								<a href="${ctx }/blog/blog-home.action?tomember.id=${tomember.id}" target="_blank">
									<s:if test="tomember.info.headPortraitUri != null && tomember.info.headPortraitUri != ''">
									   <img src="${ctx }${tomember.info.headPortraitUri }" width="50" height="50" title="${tomember.name}"/>
									</s:if><s:else>
									<img src="${ctx }/images/baisexiaonian.jpg" width="50" height="50"/>
									</s:else>
								</a>
							</li>
					 </s:iterator>
					</ul>
				</div>
				<div class="fkrs" id="articlelist">
					<ul>
						<li>_<a href="">文章评论(<span class="commentCount" id="commentCount"><s:property value="commentPage.totalCount" /></span>条)</a></li>
						<li>_<a href="">Comments</a></li>
					</ul>
				</div>
				<div class="plzhu">_以下网友留言只代表其个人观点，不代表本网站观点和立场</div>
				<div class="gerenzhoucanglb">
					<div style="display:none;" id="commenttop"></div>
					<s:iterator value="commentPage.result">
					<dl>
						<dt>
							<a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${member.id}">
								<s:if test="member.info.headPortraitUri != null && member.info.headPortraitUri != ''">
				   					<img src="${ctx }${member.info.headPortraitUri }" width="55px" height="55px"/>
								</s:if>
							</a>
						</dt>
						<dd><a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${member.id}">${member.name }</a> , <s:date name="createDate" format="yyyy年MM月dd日" />,<s:date name="createDate" format="HH.mm.ss" /> <security:authorize ifAnyGranted="A_VIEW_ZX"><!--  <span onclick="editComment('${id}');" style="cursor: pointer;">编辑</span>--> <span onclick="delComment('${id }',this);" style="cursor: pointer;">删除</span></security:authorize></dd>
						<dd>${content}</dd>
					</dl>
					</s:iterator>
				</div>
				<input type="hidden" id="totalPages" value="${commentPage.totalPages}"/>
					<input type="hidden" id="pageNo" value="${commentPage.prePage}"/>
					<input type="hidden" id="article-id" value="${entity.id}"/>
				<div style="padding-top: 20px;" class="qyyear">
					<div class="qyzhongjianyeshu">
						<ul>
							<li class="pagepri" style="margin-right: 5px;<s:if test="!commentPage.hasPre">display:none;</s:if>" ><a href="#articlelist" onclick="javascript:prePage();">_上一页</a></li>

							<li class="pagenext" style="margin-left: 5px;<s:if test="!commentPage.hasNext">display:none;</s:if>"><a href="#articlelist" onclick="javascript:nextPage();">_下一页</a></li>
						</ul>
					</div>
				  </div>
				<div style="padding-top: 20px; <s:if test='userName != "roleAnonymous" && userName != null'> display:none;</s:if>" class="dangannav replylogin"><form method="post" action="">    用户名 <input type="text" class="lyyhm _commentloginname" name="yhm"/>  密码 <input type="password" class="lyyhm _commentpassword" name="yhm"/>  <a style="cursor: pointer;" onclick="commentlogin();">登陆</a></form></div>
				<div class="fbly">
					<table cellspacing="0" border="0" width="500">
						  <tbody><tr>
							<td height="25" bgcolor="#0c0c0e"><span class="STYLE1">    <span class="STYLE1" style="color:#FFFFFF; ">评论内容_不能超过250字，请自觉遵守互联网相关政策法规！</span></span></td>
						  </tr>
						  <tr>
							<td><textarea id="reply" class="lynr" rows="5" cols="1" name="lynr"> </textarea></td>
						  </tr>
					</tbody></table>
					<div class="biaoqing"><a style="cursor: pointer;" onclick="javascript:comment(${entity.id});">发表留言</a>     <!--   <input type="checkbox" value="" name="qqh"/> 悄悄话 --> </div>
					<div class="biaoqingkuang">
					  <table cellspacing="0" border="0" width="492" class="bgkuang">
                        <tbody><tr>
                          <td height="40" width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                        </tr>
                        <tr>
                          <td height="40" width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                          <td width="40" class="hengxiang"> </td>
                        </tr>
                      </tbody></table>
					</div>
					<div>
                     	   <b>声明:以上网友发言只代表其个人观点，不代表设计圈网的观点或立场。</b><br />
						   <b>拒绝任何人以任何形式在本网站发表与中华人民共和国法律相抵触的言论!</b>
                    </div>
				</div>
				<div class="qytiaozhuang">
				  <input type="text" class="bitiankuang" name="yuming"/>
				  <input type="button" class="bianqianan" name="biaoqian"/>
				</div>
				<div class="qyxuanxiang"><input type="radio" name="wo"/>ooowo.com    <input type="radio" name="wo"/>Google</div>
				<div class="qyzuji">_你的足迹  <a href="">登入</a><a href="">_注册</a>你的免费帐号</div>
				<div class="faxian">
					<ul>
						<li>_发现 </li>
						<li><a href="">资讯</a>|</li>
						<li><a href="">分享</a>|</li>
						<li><a href="">下载</a>|</li>
						<li><a href="">blog</a>|</li>
						<li><a href="">本周</a>|</li>
						<li><a href="">本月</a>|</li>
						<li><a href="">热门标签</a>|</li>
						<li><a href="">会员</a>|</li>
						<li><a href="">搜索</a></li>
					</ul>
				</div>
				<div class="qyfaxian">
					<ul>
						<li>_设计圈ooowo小帮手 </li>
						<li><a href="">社群指南</a>|</li>
						<li><a href="">说明讨论区</a>|</li>
						<li><a href="">常见问题</a>|</li>
						<li><a href="">网站地图</a>|</li>
						<li><a href="">帮助</a>|</li>
					</ul>
				</div>
				</div>
				
		</div>
			<div class="footer">
				<%@include file="/common/blog-content-footer-to.jsp" %>
			</div>
		</div>
	</div>
</div>
</body>
</html>