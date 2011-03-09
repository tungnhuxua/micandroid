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
<script type="text/javascript" src="${ctx }/js/blog/blog_message.js"></script>

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
<div class="blogweizhi">
					<ul>
						<li><a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">首页</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">设计圈博客</a></li>
						<li>_<a href="${ctx}/blog/blog-home.action?tomember.id=${tomember.id}">${tomember.name}</a></li>
						<li>_<a href="#">留言</a></li>
						<li>_(共${messagePage.totalCount}条留言)</li>
					</ul>
				</div>
				<div class="fbly">
					<table width="500" border="0" cellspacing="0">
						  <tr>
							<td height="25" bgcolor="#0C0C0E"><span class="STYLE1" style="color:#FFFFFF; ">&nbsp;&nbsp;&nbsp;&nbsp;评论内容_不能超过250字，请自觉遵守互联网相关政策法规！</span></td>
						  </tr>
						  <tr>
							<td><textarea id="reply" name="entity.comment" cols="1" rows="5" class="lynr"></textarea></td>
						  </tr>
					</table>
					<div class="biaoqing"><a href="javascript:addMessageBlogFront('${tomember.id}');">>发表留言</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="quietly" id="quietly" type="checkbox" />&nbsp;悄悄话</div>
					<div class="biaoqingkuang">
					  <table width="492" border="0" cellspacing="0" class="bgkuang">
                        <tr>
                          <td height="40" width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                        </tr>
                        <tr>
                          <td height="40" width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                          <td width="40" class="hengxiang">&nbsp;</td>
                        </tr>
                      </table>
                      <div>
                     	  <b> 声明:以上网友发言只代表其个人观点，不代表设计圈网的观点或立场。</b><br />
						  <b> 拒绝任何人以任何形式在本网站发表与中华人民共和国法律相抵触的言论!</b>
                      </div>
					</div>
					<div class="gerenzhoucanglb">
					  <s:if test="messageError == 2">该用户已经设置了隐藏留言功能，您无权查看！</s:if>
					  <s:if test="messageError == 3">该用户已经设置了对好友开放功能，您目前不是对方的好友，您无权查看！</s:if>
					  <span id="messageSpan"></span>
						<s:iterator value="list"  var="var1">
		                    <dl>
								<dt> 
								<a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${member.id}">
									<s:if test="member.info.headPortraitUri != null && member.info.headPortraitUri != ''">
									   <img src="${ctx }${member.info.headPortraitUri }" width="55" height="55"/>
									</s:if><s:else>
									<img src="${ctx }/images/baisexiaonian.jpg" />
									</s:else>
								</a>
								</dt>
								<dd><a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${member.id}">${member.name }</a>,<s:date name="createDate" format="yyyy-MM-dd HH:mm"/></dd>
								<dd>${comment }</dd>
								<s:iterator value="listAnswer" var="var2">
									<s:if test="#var1.id == #var2.connectionId"> 
										<dd>主人的回复：<s:date name="createDate" format="yyyy-MM-dd HH:mm"/></dd>
			                       		<dd>${comment}</dd>
									</s:if>
		                        </s:iterator>
							</dl>
						</s:iterator>
					</div>
				</div>
				<div class="qyyear"> 
				<s:if test="list != null">
					<div class="qyzhongjianyeshu">
						
							<ul>
								第${messagePage.pageNo}页, 共${messagePage.totalPages}页 
							<s:if test="messagePage.hasPre">
								<a href="${ctx }/blog/blog-message.action?messagePage.pageNo=${messagePage.prePage}&tomember.id=${tomember.id}">上一页</a>
							</s:if>
							
								<ouun:pageNum totalPages="${messagePage.totalPages}" pageNo="${messagePage.pageNo}" url="${ctx }/blog/blog-message.action?tomember.id=${tomember.id}&messagePage.pageNo=" />
							
							<s:if test="messagePage.hasNext">
								<a href="${ctx }/blog/blog-message.action?messagePage.pageNo=${messagePage.nextPage}&tomember.id=${tomember.id}">下一页</a>
							</s:if>
							</ul>
						
					</div>
					<div class="qyzhongjianyeshu">_共 <span class="commentCount" id="commentCount"> ${messagePage.totalCount}</span>  条留言</div>
				</s:if>
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