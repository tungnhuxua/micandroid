<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<div style='height:100px;'>
<span class="portal-header-menu-item">
	<a onclick="javascript:Light.showAbout();" href="javascript:;"><fmt:message key="portlet.lebel.about"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showFAQ();" href="javascript:;"><fmt:message key="portlet.lebel.faq"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showPrivacy();" href="javascript:;"><fmt:message key="portlet.lebel.privacy"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showContactUs();" href="javascript:;"><fmt:message key="portlet.lebel.contactUs"/></a>
	<span class="portal-header-menu-item-separater"></span>			
</span>
</div>
</fmt:bundle>