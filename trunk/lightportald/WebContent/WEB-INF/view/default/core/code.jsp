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
<%@ page import="java.util.*,org.light.portal.*,org.light.portal.model.*,org.light.portal.util.*,org.springframework.context.*,org.springframework.web.context.*" %>

<%
Organization org = OrganizationThreadLocal.getOrg();
java.util.Locale currentLocale = Context.getInstance().getLocale(request);
request.setAttribute("currentLocale",currentLocale);
request.setAttribute("fonts",ConfigurationUtil.getSupportedFonts());
request.setAttribute("colors",ConfigurationUtil.getSupportedFontColors());
request.setAttribute("fontSizes",ConfigurationUtil.getSupportedFontSizes());
request.setAttribute("headerHeights",ConfigurationUtil.getSupportedHeaderHeights());
request.setAttribute("maxShowTabsNumber",ConfigurationUtil.getMaxShowTabsNumber());

List<LabelBean> themes = ConfigurationUtil.getSupportedThemes();
for(LabelBean bean : themes){
	bean.setDesc(MessageUtil.getMessage(bean.getDesc(),currentLocale));
}
request.setAttribute("themes",themes);

List<LabelBean> windowSkins = ConfigurationUtil.getSupportedWindowSkins();
for(LabelBean bean : windowSkins){
	bean.setDesc(MessageUtil.getMessage(bean.getDesc(),currentLocale));
}
request.setAttribute("windowSkins",windowSkins);

request.setAttribute("languageCount",LocaleUtil.getSupportedLanguages().size());
request.setAttribute("languages",LocaleUtil.getSupportedLanguages());

request.setAttribute("regionCount",LocaleUtil.getSupportedRegions().size());
request.setAttribute("regions",LocaleUtil.getSupportedRegions());

request.setAttribute("timeZoneCount",LocaleUtil.getSupportedTimeZone(Context.getInstance().getLocale(request)).size());
request.setAttribute("timeZones",LocaleUtil.getSupportedTimeZone(Context.getInstance().getLocale(request)));

request.setAttribute("months", CalendarUtil.getMonths());
request.setAttribute("days", CalendarUtil.getDays());
request.setAttribute("years", CalendarUtil.getYears());
request.setAttribute("countries", LocaleUtil.getSupportedCountry(Context.getInstance().getLocale(request)));
request.setAttribute("provinces", LocaleUtil.getSupportedProvince());

ApplicationContext ctx = (ApplicationContext) request.getSession().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);	
if(request.getSession().getServletContext().getAttribute("channels") == null){
	request.getSession().getServletContext().setAttribute("channels", org.getChannels());
}
if(request.getSession().getServletContext().getAttribute("roles") == null){
	Collection<ObjectRole> roles = org.getRoleMap().values();
	List<LabelBean> owners = new ArrayList<LabelBean>();
	for(ObjectRole role : roles){
		owners.add(new LabelBean(role.getName(),role.getName()));
	}
	request.getSession().getServletContext().setAttribute("roles",owners);
}

List<LabelBean> orgs = new ArrayList<LabelBean>();
orgs.add(new LabelBean("0",MessageUtil.getMessage("portlet.label.all",currentLocale)));
orgs.add(new LabelBean(String.valueOf(org.getId()),org.getWebId()));
request.setAttribute("orgs", orgs);

request.setAttribute("jsHistoryDisableList",PropUtil.getString("js.history.disable.list"));

%>	