<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>

<script type="text/javascript">
	var collection_delete = {action:"member-collection!deleteCollection.action",msg:"确定要删除收藏吗？"};
	var collection_max = {action:"member-collection!maxCollectionOrder.action"};
	function delCollection(op,id) {
		if(op.msg && !confirm(op.msg)) {
			return;
		}
		var tableForm = document.getElementById('collectionFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.submit();
	}

	function maxCollection(op,id) {
		var tableForm = document.getElementById('collectionFrom');
		tableForm.onsubmit=null;
		tableForm.action=op.action;
		tableForm.id.value = id;
		tableForm.submit();
	}
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
				<div class="gerenshoucangbt">Collect_收藏_(共   <span style="color: #91BF29; font-size: 18px;">${page.totalCount} </span>个主题)</div>
				<form id="collectionFrom">
					<div class="gerenzhoucanglb">
					
						<s:iterator value="list">
							<dl>
								<dt>
									<a target="_blank" href="${ctx}/blog/blog-content.action?id=${article.id}&&tomember.id=${article.member.id}">
										<s:if test="article.imageUri!=null && article.imageUri!=''">
											<img src="${ctx}${article.imageUri}" width="55px" height="55px"/>
										</s:if>
										<s:else>
											 	<img src="../images/zhchepic.gif" width="55px" height="55px"/>
									    </s:else>
									</a>
								</dt>
								<dd>标题_${article.title}&nbsp;|&nbsp;${article.category.name}_${article.category.nameEn}</dd>
								<dd>发布时间_${article.createDate}_浏览次数_${article.readCount}_评论_<s:property value="article.comments.size"/>&nbsp;<a href="javascript:delCollection(collection_delete,'${id}');">删除</a>&nbsp;|&nbsp;<a href="javascript:maxCollection(collection_max,'${id}');">置顶</a></dd>
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
					<div class="qyzhongjianyeshu">_共  ${page.totalCount}   个主题</div>
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