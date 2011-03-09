<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx }/js/blog/dynamics.js"></script>
				        
<s:iterator value="memberDynamicsList" status="sta">
<s:if test="#sta.index > 20">
	<li id = "dynamics${sta.index}">
		<s:if test="messageType == 1">
		  <script>
			var message = ${message};
			var str = "<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">";
			str = str+ message.memberName;
			str = str + "</a>_";
			str = str + message.date;
			str = str + "和<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.tomemberId+">";
			str = str + message.tomemberName;
			str = str + "</a>";
			document.getElementById("dynamics"+${sta.index}).innerHTML = str;
		  </script>                      
		</s:if>
		
		<s:if test="messageType == 2">
			<script type="text/javascript">
				var message = ${message};
				var str = "<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">";
				str = str+ message.memberName;
				str = str + "</a>_";
				str = str + message.date;
				str = str + "发布了新日志";
				str = str + "<a target=_blank href=${ctx}/blog/blog-content.action?id="+message.articleId+"&&tomember.id="+message.memberId+">";
				str = str + "\""+message.articleTitle+"\"";
				str = str + "</a>";
				document.getElementById("dynamics"+${sta.index}).innerHTML = str;
			 </script>
		</s:if>
		
		<s:if test="messageType == 3">
			<script type="text/javascript">
				var message = ${message};
				var str = "<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">";
				str = str+ message.memberName;
				str = str + "</a>_";
				str = str + message.date;
				str = str + "发布了新作品";
				str = str + "<a target=_blank href=${ctx}/blog/blog-content.action?id="+message.articleId+"&&tomember.id="+message.memberId+">";
				str = str + "\""+message.articleTitle+"\"";
				str = str + "</a>";
				document.getElementById("dynamics"+${sta.index}).innerHTML = str;
	
			 </script>
		</s:if>	
		
	    <s:if test="messageType == 4">
			<script type="text/javascript">
				var message = ${message};
				var str = "<a target=_blank href=${ctx}/blog/blog-home.action?tomember.id="+message.memberId+">";
				str = str+ message.memberName;
				str = str + "</a>_";
				str = str + message.date;
				str = str + "收藏了文章";
				str = str + "<a target=_blank href=${ctx}/searcharticlecontent.action?article.id="+message.articleId+">";
				str = str + "\""+message.articleTitle+"\"";
				str = str + "</a>";
				document.getElementById("dynamics"+${sta.index}).innerHTML = str;
			 </script>
		</s:if>		                      
	</li>
 </s:if>
 </s:iterator>
