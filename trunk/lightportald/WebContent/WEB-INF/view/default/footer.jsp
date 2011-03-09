<%
/**
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
%>
<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<textarea id="footer.view" style="display:none;">
<div style='height:100px;'>
<span class="portal-header-menu-item">
	<label class="portal-footer-notes"><c:out value="${sessionScope.org.webId}"/> © <c:out value="${sessionScope.org.currentYear}"/></label>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showAbout();" href="javascript:;"><fmt:message key="portlet.lebel.about"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showCommunityBlog();" href="javascript:;"><fmt:message key="portlet.title.blog"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showFAQ();" href="javascript:;"><fmt:message key="portlet.lebel.faq"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showTerms();" href="javascript:;"><fmt:message key="portlet.lebel.terms"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showPrivacy();" href="javascript:;"><fmt:message key="portlet.lebel.privacy"/></a>
	<span class="portal-header-menu-item-separater"></span>
	<a onclick="javascript:Light.showContactUs();" href="javascript:;"><fmt:message key="portlet.lebel.contactUs"/></a>
	<span class="portal-header-menu-item-separater"></span>			
</span>
</div>
</textarea>
</fmt:bundle>