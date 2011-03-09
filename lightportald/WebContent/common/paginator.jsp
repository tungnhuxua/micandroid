<c:if test='${pages > 1}'>
 	<span class="portlet-rss" style="text-align:right;padding:5px 10px;">
 	    <label>(<fmt:message key="portlet.label.Results"/> <c:out value="${requestScope.start + 1 }"/> -  <c:out value="${requestScope.end}"/> <fmt:message key="portlet.label.of"/> <c:out value="${total}"/>)</label>
		<c:if test='${page > 1}'>
			<a href="javascript:;" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','page=<c:out value="${requestScope.page - 1}"/>');"><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' width='16px' height='16px' title='<fmt:message key="portlet.label.previous"/>' alt='' /></a>						
		</c:if>
		<%
		int begin = (Integer)request.getAttribute("page");
		if(begin - 5 > 0) 
			begin -= 5;
		else
			begin = 1;
		int finish = (Integer)request.getAttribute("pages");
		if(finish >= begin+10) finish = begin+9;
		request.setAttribute("begin",begin);
		request.setAttribute("finish",finish);
		%>
		<c:forEach var="i" begin="${requestScope.begin}" end="${requestScope.finish}" step="1">
			<c:if test='${i != page}'>
				<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','page=<c:out value="${i}"/>;pages=<c:out value="${pages}"/>');" ><c:out value="${i}"/></a>
			</c:if>
			<c:if test='${i == page}'>
				<label class='currentpage'><c:out value="${i}"/></label>
			</c:if>
		</c:forEach>
		<c:if test='${page < pages}'>
			<a href="javascript:;" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','page=<c:out value="${requestScope.page + 1}"/>');"><img src='<%= request.getContextPath() %>/light/images/next.gif' style='border: 0px' width='16px' height='16px' title='<fmt:message key="portlet.label.next"/>' alt='' /></a>						
		</c:if>
	</span>
</c:if>