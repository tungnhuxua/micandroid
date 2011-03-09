<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link type="text/css" href="${ctx }/css/ui.all.css" rel="stylesheet" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.core.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.dialog.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/js/blog/log-show.js"></script>
<script type="text/javascript">
	function showAddLog(){
		$('#addGroup').dialog({
			bgiframe: false,
			resizable: true,
			modal: true,
			width: 300,
			height:100
		});
		$('#addGroup').dialog("open");
	}


</script>

</head>
<body>
<form id="uploadForm" action="${ctx }/admin/upload!upload.action" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContent"></span>
<input name="fileGroup" value="article" type="hidden"/>
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>
<div id="addGroup" style="display:none">
	<script type="text/javascript">
		function addGroup(id){
			document.getElementById(id).submit();
		}
	</script>
  <form method="post" action="${ctx }/blog/addLogCategory.action" id="addGroupForm">
	  <ul style="">
	    <li style="float: left;">分类：<input type="text" class="qq" name="logCategory.name"/></li>
	    <input type="hidden" name="cateId" value="${cateId }"/>
	    <input type="hidden" name="logId" value="${logId }"/>
	    <li style="float: right;"><a href="javascript:addGroup('addGroupForm');">添加</a></li>
	  </ul>
  </form>
</div>
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
				<div class="gerenshoucangbt">Diary_日志_每发布一篇日志即可获得0喔元，如被资讯频道发布更可获得0喔元奖励！</div>
				<div class="faburizhixx"><a href="article!input.action?cateId=54">_发布新日志</a>     <!--   <a href="">_管理设置</a>    <input type="radio" value="" name="lb"/>列表显示  <input type="radio" value="" name="lb"/>图标显示--></div>
				 <div class="zdyhaoyou">
				 	<ul>
				 		<s:iterator value="logCate">
				 			<li><a href="${ctx }/blog/article-log.action?cateId=${cateId}&logId=<s:property value="id"/>">_<s:property value="name"/></a></li>	
				 		</s:iterator>
						<li><a href="${ctx }/blog/article-log.action?cateId=${cateId}">_显示全部</a></li>
						<li><a style="text-decoration: underline; color: rgb(131, 195, 37);" href="javascript:showAddLog();">_添加日志分类</a></li>
					</ul>
				 </div>
				<div class="grhaohougl">
					<s:iterator value="page.result">
						<dl>
							<dt><a target="_blank" href="${ctx}/blog/blog-content.action?id=${id}&&tomember.id=${member.id}"> 
							<s:if test="imageUri!=null && imageUri != ''"><img src="${ctx}${imageUri}" alt="${title}" width="80" height="80"/>
							</s:if><s:else>
								<img src="${ctx }/images/xiaonian.jpg" width="80" height="80" alt="${title}" />	
							</s:else>
							</a></dt>
							<dd style="font-size: 14px;"><a target="_blank" href="${ctx}/blog/blog-content.action?id=${id}&&tomember.id=${member.id}" title="点击预览"> ${title}</a> | ${category.name }</dd>
							<dd>发布时间_<s:date name="createDate" format="yyyy-MM-dd"/></dd>
							<dd>阅读_${readCount} | 评论_<s:property value="comments.size"/> | 投票_${voteCount }</dd>
							<dd><a href="${ctx}/blog/article!input.action?id=${id}">编辑</a> | <a id="unhiden_${id }" onclick="a_unhiden('${id}');" style="cursor: pointer;<s:if test="hidden != '1'">display:none;</s:if>" >取消隐藏</a><a id="hiden_${id }" onclick="a_hiden('${id}');" style="cursor: pointer;<s:if test="hidden == '1'">display:none;</s:if>" >隐藏</a> | <a href="${ctx }/blog/article!delete.action?id=${id}&artRandom=${random}&cateId=${cateId}&logId=${logId}">删除</a> | <a id="unup_${id }" onclick="a_unup('${id}');"  style="cursor: pointer;<s:if test="up != '1'">display:none;</s:if>" >取消置顶</a><a  id="up_${id }" onclick="a_up('${id}');"  style="cursor: pointer;<s:if test="up == '1'">display:none;</s:if>" >置顶</a> | <a  id="unupblog_${id }" onclick="a_unupblog('${id}');" style="cursor: pointer;<s:if test="upspecial != '1'">display:none;</s:if>">取消首页特别推荐</a><a  id="upblog_${id }" onclick="a_upblog('${id}');" style="cursor: pointer;<s:if test="upspecial == '1'">display:none;</s:if>">首页特别推荐</a></dd>
						</dl>
					</s:iterator>
				</div>
				<div class="qyyear">
					<div class="qyzhongjianyeshu">
						<ul>
							第${page.pageNo}页, 共${page.totalPages}页 
						<s:if test="page.hasPre">
							<a href="${ctx }/blog/article-log.action?page.pageNo=${page.prePage}&cateId=${cateId}">上一页</a>
						</s:if>
						<ouun:pageNum totalPages="${page.totalPages}" pageNo="${page.pageNo}" url="${ctx }/blog/article-log.action?cateId=${cateId}&page.pageNo=" />
						<s:if test="page.hasNext">
							<a href="${ctx }/blog/article-log.action?page.pageNo=${page.nextPage}&cateId=${cateId}">下一页</a>
						</s:if>
						</ul>
					</div>
					<div class="qyzhongjianyeshu">_共${page.totalCount}篇日志</div>
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