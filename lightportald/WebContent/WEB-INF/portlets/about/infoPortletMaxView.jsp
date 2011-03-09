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
<html>
<head>
</head>
<body>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
</head>
<body>
<fmt:bundle basename="resourceBundle">
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td class="portlet-table-td-left"><br/><br/><font size='3'><fmt:message key="portlet.info.header1"/></font></td>
	</tr>
	<tr>
	<td class="portlet-table-td-left">
	<br/>
	<p><b><fmt:message key="portlet.info.desc1"/></p> 
	</td>
	</tr>
	<tr>
	<td class="portlet-table-td-left">
	<p><br/><br/><font color='#ff6600'><b><fmt:message key="portlet.info.header2"/></b></font><br/></p>
	<ul>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/recommended.gif)"><fmt:message key="portlet.info.list1"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/friend.gif)"><fmt:message key="portlet.info.list2"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/privacy.gif)"><fmt:message key="portlet.info.list3"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/group.gif)"><fmt:message key="portlet.info.list4"/></li>
	</ul>
	</td>
	</tr>  
	<p>We are a new site, developing new features as fast as we can. This service is still beta, if you have suggestions or comments, please <A href="javascript:void(0)" onclick="javascript:Light.portal.showContact();">contact us</A></p><br/>
	<P/>
	<p><font color='#ff6600'><b>How Do I Use <c:out value="${sessionScope.org.webId}"/>?</b></font></p>
	<OL>
	<li><b>Sign Up</b> and Create a Profile<br/><br/>(Your Profile is Your Space on the Web, where you can describe yourself, hobbies and interests. You can even upload pics and write journals.)<br/><br/></li>
	<li><b>Invite</b> your Friends to join Your Personal Network.<br/><br/>OR, <b>Search</b> the site for your Friends who are already Members of <c:out value="${sessionScope.org.webId}"/>.<br/><br/></li>
	<li>Browse, modify, and import your RSS feeds with our integrated RSS/ATOM feedreader, send your favourite news to public or friends or save to your personal bookmarks. You can easily import an OPML file as well.<br/><br/></li>
	<li>To check your POPS/IMAP email or gmail account, to stick webnotes, weather, calendar, blog and many more to come !</li>
	<li>Upload your photo and music !</li>
	<li>Create your own group or join other groups as many as you want !</li>
	</OL>
	</td>
	</tr>    
	<tr>
	<td class="portlet-table-td-left">
	<br/><br/>
	<p><c:out value="${sessionScope.org.webId}"/> is a start up company created in 2006 based in North America and specialized in Web 2.0 applications. We are open to all business opportunities, please contact us at : business@<c:out value="${sessionScope.org.webId}"/> </p>
	</td>
	</tr>
	</table>
	<span class="portlet-rss" style="text-align:right;padding : 15 15 15 15;">
	<a href='javascript:void(0)' onclick="<portlet:renderURL  windowState='NORMAL'/>" >back</a>
	</span>
</fmt:bundle>
</body>
</html>