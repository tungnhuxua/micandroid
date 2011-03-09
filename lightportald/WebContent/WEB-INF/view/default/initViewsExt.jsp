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
<%
/**
 * Customized/Extended Init Views
 */
%>
<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<textarea id="loading.view" style="display:none;">
	<br/>
	<br/>
	<font size='3'><fmt:message key="message.loading.desc"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></font>
	<br/>
	<br/>
	<img src='<%= request.getContextPath() %>/light/images/loading.gif'/>
	<br/>
	<fmt:message key="message.loading"/>...
</textarea>
<textarea id="loginPortlet.view" style="display:none;" rows='0' cols='0'>
<c:if test='${sessionScope.user.id == sessionScope.org.userId}'>
	<form name="form_${id}">
	<table border='0' cellpadding='0' cellspacing='0' width='90%'>
	<tr>
	<td class='portlet-link' colspan='3'>
	<br/>
	</td>
	</tr>	
	<tr>
	<td class='portlet-table-td-right'><label FOR='email' ACCESSKEY='U'><fmt:message key="portlet.label.userId"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='text' id='email' name='email' value='${userId}' class='portlet-form-input-field' size='24' /> 
	</td>
	<td>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-right'><label FOR='password' ACCESSKEY='P'><fmt:message key="portlet.label.userPassword"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='password' id='password' name='password' value='' class='portlet-form-input-field' size='24' onkeypress="return Light.keyDownLogin(event,'${id}');"/> 
	</td>
	<td>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-table-td-left'>
	<input TYPE='checkbox' name='rememberMe' checked='checked' value='1'><label><fmt:message key="portlet.message.rememberMe"/></label></input>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-table-td-left'>
	<input name='login' type='button' value='<fmt:message key="portlet.button.login"/>' class='portlet-form-button'
	  onclick="javascript:Light.loginPortal('${id}');" />
	<span  class='portlet-link-left'> 
		<fmt:message key="portlet.label.or"/><a href='javascript:;' onclick="javascript:Light.showSignUp('${id}');" ><b><fmt:message key="portlet.label.signUp"></fmt:message></b></a>
	</span>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-link-left' colspan='3'>
	<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','edit','normal','type=forgot');" ><fmt:message key="portlet.label.forgotPassword"/></a>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-link-left' colspan='3'>
	<a href='javascript:;' onclick="javascript:Light.showOpenIdSignIn('${id}');" ><label><fmt:message key="portlet.label.signIn"/> <fmt:message key="portlet.label.with"/> <b><span id="openid_title_graphic">OpenID</span></label></b></a>
	</td>
	</tr>
	<%
	String apiKey = org.light.portal.util.PropUtil.getString("facebook.apiKey");
	if(!org.light.portal.util.StringUtil.isEmpty(apiKey)){
	%>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-link-left' colspan='3'>
	<fmt:message key="portlet.label.or"/> 
	<a href='http://www.facebook.com/login.php?api_key=<%= org.light.portal.util.PropUtil.getString("facebook.apiKey") %>&display=popup&extern=1&fbconnect=1&req_perms=publish_stream&return_session=1&v=1.0&next=http%3A%2F%2F<c:out value="${host}"/>%2FopenId%2Ffacebook.jsp&fb_connect=1&cancel_url=http%3A%2F%2F<c:out value="${host}"/>' >
	<span class='facebook'>facebook</span>
	<!-- <img src='<%= request.getContextPath() %>/light/images/f_connect.gif' align="middle" width='107' height='25' alt='' /> -->
	</a>	
	</td>
	</tr>	
	<%
	}
	%>		
	</table>
	</form>
</c:if>
<c:if test='${sessionScope.user.id != sessionScope.org.userId}'>
<table border="0" cellpadding="0" cellspacing="0" style='padding: 0 0 0 25px;'>
	<tr>
	<td class="portlet-table-td-left"><br/><br/><fmt:message key="info.header1"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></td>
	</tr>
	<tr>
	<td class="portlet-table-td-left">
	<p><br/><br/><font color='#ff6600'><b><fmt:message key="info.header2"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></b></font><br/></p>
	<ul>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/recommended.gif)"><fmt:message key="info.list1"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/friend.gif)"><fmt:message key="info.list2"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/privacy.gif)"><fmt:message key="info.list3"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/group.gif)"><fmt:message key="info.list4"/></li>
	</ul>
	</td>
	</tr>    
	</table>
</c:if>
</textarea>
<textarea id="offMode.view" style="display:none;"  rows='0' cols='0'>
	<a href='javascript:;' onclick="javascript:Light.onMode();" title="<fmt:message key="portlet.button.open"/>"><img src='<%= request.getContextPath() %>/light/images/on.gif' align="middle" alt='' /></a>
</textarea>
<textarea id="loginPortlet.view" style="display:none;" rows='0' cols='0'>
	<form name="form_${id}">
	<table border='0' cellpadding='0' cellspacing='0' width='90%'>
	<tr>
	<td class='portlet-link' colspan='3'>
	<br/>
	</td>
	</tr>	
	<tr>
	<td class='portlet-table-td-right'><label FOR='email' ACCESSKEY='U'><fmt:message key="portlet.label.userId"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='text' id='email' name='email' value='${userId}' class='portlet-form-input-field' size='24' /> 
	</td>
	<td>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-right'><label FOR='password' ACCESSKEY='P'><fmt:message key="portlet.label.userPassword"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='password' id='password' name='password' value='' class='portlet-form-input-field' size='24' onkeypress="return Light.keyDownLogin(event,'${id}');"/> 
	</td>
	<td>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-table-td-left'>
	<input TYPE='checkbox' name='rememberMe' checked='checked' value='1'><label><fmt:message key="portlet.message.rememberMe"/></label></input>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-table-td-left'>
	<input name='login' type='button' value='<fmt:message key="portlet.button.login"/>' class='portlet-form-button'
	  onclick="javascript:Light.loginPortal('${id}');" />
	<span  class='portlet-link-left'> 
		<fmt:message key="portlet.label.or"/><a href='javascript:;' onclick="javascript:Light.showSignUp('${id}');" ><b><fmt:message key="portlet.label.signUp"></fmt:message></b></a>
	</span>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-link-left' colspan='3'>
	<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','edit','normal','type=forgot');" ><fmt:message key="portlet.label.forgotPassword"/></a>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'></td>
	<td class='portlet-link-left' colspan='3'>
	<a href='javascript:;' onclick="javascript:Light.showOpenIdSignIn('${id}');" ><b><label><fmt:message key="portlet.label.signIn"/> <fmt:message key="portlet.label.with"/> <span id="openid_title_graphic">OpenID</span></label></b></a>
	</td>
	</tr>	
	</table>
	</form>
</textarea>
<c:if test='${requestScope.orgView == null}'>
<textarea id="infoPortlet.view" style="display:none;">
	<table border="0" cellpadding="0" cellspacing="0" style='padding: 0 0 0 25px;'>
	<tr>
	<td class="portlet-table-td-left"><br/><br/><font size='3'><fmt:message key="info.header1"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></font></td>
	</tr>
	<tr>
	<td class="portlet-table-td-left">
	<p><br/><br/><font color='#ff6600'><b><fmt:message key="info.header2"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></b></font><br/></p>
	<ul>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/recommended.gif)"><fmt:message key="info.list1"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/friend.gif)"><fmt:message key="info.list2"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/privacy.gif)"><fmt:message key="info.list3"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/group.gif)"><fmt:message key="info.list4"/></li>
	</ul>
	</td>
	</tr>    
	</table>
	<span class="portlet-rss" style="text-align:right;">
	<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','maximized','');" >more...</a>
	</span>
</textarea>
</c:if>
<c:if test='${requestScope.orgMaxView == null}'>
<textarea id="infoPortlet.max.view" style="display:none;">
	<table border="0" cellpadding="0" cellspacing="0" style='padding: 0 0 0 25px;'>
	<tr>
	<td class="portlet-table-td-left"><br/><br/><font size='3'><fmt:message key="info.header1"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></font></td>
	</tr>
	<tr>
	<td class="portlet-table-td-left">
	<br/>
	<p><fmt:message key="info.desc1"/></p> 
	</td>
	</tr>
	<tr>
	<td class="portlet-table-td-left">
	<p><br/><br/><font color='#ff6600'><b><fmt:message key="info.header2"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></b></font><br/></p>
	<ul>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/recommended.gif)"><fmt:message key="info.list1"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/friend.gif)"><fmt:message key="info.list2"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/privacy.gif)"><fmt:message key="info.list3"/></li>
	<li STYLE="padding:5px 15px 4px 6px; list-style-image:  url(light/images/group.gif)"><fmt:message key="info.list4"/></li>
	</ul>
	</td>
	</tr> 
	<tr>
	<td class="portlet-table-td-left"> 
	<br/>
	<p>We are a new site, developing new features as fast as we can. This service is still beta, if you have suggestions or comments, please <A href="javascript:;" onclick="javascript:Light.portal.showContact();">contact us</A></p><br/>
	<P/>
	</td>
	</tr>    
	<tr>
	<td class="portlet-table-td-left">
	<br/>
	<p><font color='#ff6600'><b><fmt:message key="info.header3"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></b></font></p>
	<br/>
	</td>
	</tr>    
	<tr>
	<td class="portlet-table-td-left">
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
	<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','normal','');" >back</a>
	</span>
</textarea>
</c:if>
</fmt:bundle>