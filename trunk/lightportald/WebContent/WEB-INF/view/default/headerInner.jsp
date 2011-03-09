<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<span class="portal-header-logo">
	<a href='javascript:;' onclick='javascript:Light.toMyAccount();'>
		<img src='<c:out value="${requestScope.orgLogo}"/>' alt='' />
	</a>
</span>	
</fmt:bundle>