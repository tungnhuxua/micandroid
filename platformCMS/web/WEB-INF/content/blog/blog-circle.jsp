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
<script type="text/javascript" src="${ctx }/js/world.js"></script>

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
						<li>_<a href="#">群英会</a></li>
					</ul>
					<ul class="scxuangxiang">
						<li>_<a href="blog-circle-list.action?tomember.id=${tomember.id}">列表显示</a></li>
					</ul>
				</div>
				<div class="qymingdang">
				<s:iterator value="list" id="obj">
					<dl>
						<dt>
							<a href="#" target="_blank">
									<s:if test="#obj[8] != null && #obj[8] != '' ">
										<img src="${ctx}${obj[8]}" width="105px" height="100px"/>
									</s:if>
									<s:else>
										<img src="../images/zhchepic.gif" width="105px" height="100px"/>
									</s:else>
							</a>
						</dt>
						<dd class="cuti">${obj[1]}</dd>
						<dd>${obj[5]}_${obj[6]}</dd>
						<dd class="lvse"><a href="#">${obj[9]}</a></dd>
						<dd>${obj[10]}位<a href="#">会员</a></dd>
						<dd><a href="#">圈子档案</a>|<a href="javascript:enterCircle('${obj[0]}');">申请加入</a></dd>
					</dl>
				</s:iterator>
				</div>
				
				<div class="qyyear">
					<div class="qyzhongjianyeshu">
						<ul>
							第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="${ctx }/blog/blog-circle.action?page.pageNo=${page.prePage}&tomember.id=${tomember.id}">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="${ctx }/blog/blog-circle.action?tomember.id=${tomember.id}&page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="${ctx }/blog/blog-circle.action?page.pageNo=${page.nextPage}&tomember.id=${tomember.id}">下一页</a>
						</s:if>
						</ul>
					</div>
					<div class="qyzhongjianyeshu">_共  ${page.totalCount}  个群英会</div>
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