<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/blog/footmark.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>

<script type="text/javascript">


</script>
</head>
<body>
<div class="container">	
		<div class="header">
			<%@include file="/common/blog-header.jsp" %>
		</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
			<div class="mainbh">
			    <div class="leftsidebar">
						<%@include file="/common/blog-content-left.jsp" %>
					</div>
				<div class="zhuti">
				<div class="gerenshoucangbt">Footprint_足迹_(共  <span id="footMarkCount" style="color: #91BF29; font-size: 18px;">${page.totalCount} </span> 个足迹)</div>
			    <form id="footmarkFrom" method="post">
					<div class="grzujilb">
						<s:iterator value="list">
							<dl id="footMarkDl${id}">
								<dt>
								<a target="_blank" href="${ctx}${uri}">
											<s:if test="article.imageUri!=null && article.imageUri!=''">
												<img src="${ctx}${article.imageUri}" width="55px" height="55px"/>
											</s:if>
											<s:else>
												 	<img src="../images/zhchepic.gif" width="55px" height="55px"/>
										    </s:else>
								</a>
								</dt>
								<dd>标题_${article.title}&nbsp;|&nbsp;${article.category.name}_${article.category.nameEn}</dd>
								<dd>发布时间_${article.createDate}浏览次数_${article.readCount}_评论_<s:property value="article.comments.size"/> <a href="javascript:delFootMark('${id}');">删除</a>&nbsp;|&nbsp;<a href="javascript:addCollectionBlog('${article.id}');">收藏</a></dd>
							</dl>
						</s:iterator>
					</div>
					<input type="hidden" id="id" name="id"/>
				</form>
				<div class="qyyear">
					<div class="qyzhongjianyeshu">
						<ul>
								第${page.pageNo}页, 共${page.totalPages}页 
								<s:if test="page.hasPre">
									<a href="${ctx }/blog/member-collection.action?page.pageNo=${page.prePage}">上一页</a>
								</s:if>
								<ouun:pageNum pageNo="${page.pageNo}" url="${ctx}/blog/member-collection.action?page.pageNo=" totalPages="${page.totalPages}"/>
								<s:if test="page.hasNext">
									<a href="${ctx }/blog/member-collection.action?page.pageNo=${page.nextPage}">下一页</a>
								</s:if>
							</ul>
					</div>
					<div class="qyzhongjianyeshu">_共  <span id="footMarkCount2"> ${page.totalCount}</span>   个足迹</div>
				  </div>
				</div>
		    </div>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>