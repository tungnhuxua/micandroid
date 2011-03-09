<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/blog/blog_message.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.bgiframe.min.js"></script>
<script language="javascript"> 
var message_delete = {action:"member-message!delMessage.action",msg:"删除操作不可恢复，您确认要继续吗？"};
function delMessage(op,id) {
	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('messageForm');
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
<div class="gerenshoucangbt">Message_留言_(共  <span style="color: #91BF29; font-size: 18px;">${page.totalCount}</span>  条留言其中  <span style="color: #91BF29; font-size: 18px;">${quietlyCount}</span>  条悄悄话)</div>
				<div class="grscxuangxiang"><span class="scshexhi">_留言设置</span>  
				<input name="messageType" type="radio" onchange="memberMessageSetupMessageType(this.value)" value="1" class="xx" <s:if test="messageType == 1"> checked="checked"</s:if>/>公开显示 
				<input name="messageType" type="radio" onchange="memberMessageSetupMessageType(this.value)" value="2" class="xx"  <s:if test="messageType == 2"> checked="checked"</s:if>/>隐藏此信息
				<input name="messageType" type="radio" onchange="memberMessageSetupMessageType(this.value)" value="3" class="xx"  <s:if test="messageType == 3"> checked="checked"</s:if>/>仅向好友公开</div>
				<form id="messageForm" method="post">
					<div class="gerenzhoucanglb">
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
							<dd><a target="_blank" href="${ctx}/blog/blog-home.action?tomember.id=${member.id}">${member.name }</a><s:if test="quietly == 1">发给您的<span style="color: red">悄悄话</span></s:if>,<s:date name="createDate" format="yyyy-MM-dd HH:mm"/>&nbsp;<a id="AanswerTrue${id}" href="javascript:memberMessageAnswerTure('${id}','${member.id}')">回复</a>&nbsp;|&nbsp;<a href="javascript:delMessage(message_delete,'${id}');">删除</a></dd>
							<dd>${comment }</dd>
							<span id="answerMessageAnswer${id}"></span>
							<s:iterator value="listAnswer" var="var2">
								<s:if test="#var1.id == #var2.connectionId"> 
									<dd>我的回复：<s:date name="createDate" format="yyyy-MM-dd HH:mm"/>&nbsp;|&nbsp;<a href="javascript:delMessage(message_delete,'${id}');">删除</a></dd>
		                       		<dd>${comment}</dd>
								</s:if>
	                        </s:iterator>
	                        <span id="answerMessage${id}"></span>
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
									<a href="${ctx }/blog/member-message.action?page.pageNo=${page.prePage}">上一页</a>
								</s:if>
								<ouun:pageNum pageNo="${page.pageNo}" url="${ctx}/blog/member-message.action?page.pageNo=" totalPages="${page.totalPages}"/>
								<s:if test="page.hasNext">
									<a href="${ctx }/blog/member-message.action?page.pageNo=${page.nextPage}">下一页</a>
								</s:if>
							</ul>
					</div>
					<div class="qyzhongjianyeshu">_共 ${page.totalCount} 个留言</div>
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