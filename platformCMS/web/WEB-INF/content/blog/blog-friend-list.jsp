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

</head>
<body>
<%@ include file="/common/blog/userlogin.jsp" %>
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
					<div class="soucanleft">
					<ul class="soucanzb">
						<s:iterator value="logCate">
						<li><a href="${ctx }/blog/blog-logcategory.action?tomember.id=${tomember.id}&logCategoryT.id=${id}">_${name }</a></li>
						</s:iterator>
					</ul>	
					<ul>
					<s:iterator id="now" value="times">
						<li><a href="${ctx }/blog/blog-date-log.action?tomember.id=${tomember.id}&date=<s:date name="now" format="yyyy-MM"/>">_<s:date name="now" format="yyyy.MM"/></a></li>
					</s:iterator>
					<s:iterator id="now2" value="years">
						<li><a href="${ctx }/blog/blog-date-log.action?timeType=year&tomember.id=${tomember.id}&date=<s:date name="now2" format="yyyy-MM"/>">_<s:date name="now2" format="yyyy"/></a></li>
					</s:iterator>
					</ul>
					</div>
				</div>
				<div class="zhuti">
				<div class="shoucanweizhi">
					<ul class="scweizhi">
						<li><a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">首页</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">设计圈博客</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">${tomember.name}</a></li>
						<li>_<a href="#">朋友</a></li>
					</ul>
					<ul class="scxuangxiang">
						<li>_<a href="blog-friend.action?tomember.id=${tomember.id}">头像显示</a></li>
					</ul>
				</div>
				<div class="shoucanglb">
				
				<s:iterator value="list" id="obj">
					<dl>
						<dt>
							<a href="${ctx}/blog/blog-home.action?tomember.id=${obj[8]}" target="_blank">
							 <s:if test="#obj[5]!=null && #obj[5]!=''">
								<img src="${ctx}${obj[5]}" width="70px" height="70px"/>
							 </s:if>
							<s:else>
								<img src="../images/zhchepic.gif" width="70px" height="70px"/>
							</s:else>
							</a>
						</dt>
						<dd style="font-weight:700">${obj[0]}</dd>
						<dd>${obj[3]}_${obj[4]}_个性签名</dd>
						<dd>共发表_0篇<a href="${ctx}/blog/blog-log.action?tomember.id=${obj[8] }">日志</a>&nbsp;|&nbsp;查看 ${obj[0]} 的<a target="_blank" href="${ctx}/blog/archives.action?tomember.id=${obj[8]}">个人档案</a></dd>
					</dl>
			    </s:iterator>
				</div>
				
				<div class="qyyear">
					<div class="qyzhongjianyeshu">
						<ul>
							第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="${ctx }/blog/blog-friend-list.action?page.pageNo=${page.prePage}&tomember.id=${tomember.id}">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="${ctx }/blog/blog-friend-list.action?tomember.id=${tomember.id}&page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="${ctx }/blog/blog-friend-list.action?page.pageNo=${page.nextPage}&tomember.id=${tomember.id}">下一页</a>
						</s:if>
						</ul>
					</div>
					<div class="qyzhongjianyeshu">_共  ${page.totalCount}  位朋友</div>
				  </div>
					<%@include file="/common/blog/blog-search.jsp" %>
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