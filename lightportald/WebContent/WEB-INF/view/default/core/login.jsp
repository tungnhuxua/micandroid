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
<textarea id="localLogin.view" style="display:none;">
	<form name="form_${id}">
		<table border='0' cellpadding='0' cellspacing='0' width='99%'>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left' colspan='3'>
		<label><fmt:message key="portlet.label.signIn"/> <fmt:message key="portlet.label.with"/> <span style="color:#FF6200;"><c:out value="${sessionScope.org.webId}"/></span> <fmt:message key="portlet.label.accountid"/></</label>
		<br/><br/><br/><br/>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-right'><label FOR='email' ACCESSKEY='U'><fmt:message key="portlet.label.userId"/>: </label></td>
		<td class='portlet-table-td-left'>
		<input type='text' name='email' value='${userId}' class='portlet-form-input-field' size='36' /> 
		</td>
		<td>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-right'><label FOR='password' ACCESSKEY='P'><fmt:message key="portlet.label.userPassword"/>: </label></td>
		<td class='portlet-table-td-left'>
		<input type='password' name='password' value='' class='portlet-form-input-field' size='36' onkeypress="return Light.keyDownLoginTo(event,'${id}');"/> 
		</td>
		<td>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-table-td-left'>
		<input TYPE='checkbox' name='rememberMe'  value='1'><label><fmt:message key="portlet.message.rememberMe"/></label></input>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-table-td-left'>
		<br/><br/>
		<input name='login' type='button' value='<fmt:message key="portlet.button.login"/>' class='portlet-form-button'
		  onclick="javascript:Light.loginToPortal('${id}');" />
		<span  class='portlet-link-left'> 
		<fmt:message key="portlet.label.or"/><a href='javascript:;' onclick="javascript:Light.showSignUp('${id}');" ><b><fmt:message key="portlet.label.signUpFor"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></b></a>
		</span>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left' colspan='3'>
		<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','edit','normal','type=forgot');" ><fmt:message key="portlet.label.forgotPassword"/></a>
		</td>
		</tr>
		</table>
	</form>
</textarea>
<textarea id="allLogin.view" style="display:none;">	
	<table border='0' cellpadding='0' cellspacing='0' width='100%'>
	<tr valign='top'>
	<td width="50%">
		<form name="form_${id}">
		<table border='0' cellpadding='0' cellspacing='0' width='99%'>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left' colspan='3'>
		<label><fmt:message key="portlet.label.signIn"/> <fmt:message key="portlet.label.with"/> <span style="color:#FF6200;"><c:out value="${sessionScope.org.webId}"/></span> <fmt:message key="portlet.label.accountid"/></</label>
		<br/><br/><br/><br/>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-right'><label FOR='email' ACCESSKEY='U'><fmt:message key="portlet.label.userId"/>: </label></td>
		<td class='portlet-table-td-left'>
		<input type='text' name='email' value='${userId}' class='portlet-form-input-field' size='36' /> 
		</td>
		<td>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-right'><label FOR='password' ACCESSKEY='P'><fmt:message key="portlet.label.userPassword"/>: </label></td>
		<td class='portlet-table-td-left'>
		<input type='password' name='password' value='' class='portlet-form-input-field' size='36' onkeypress="return Light.keyDownLoginTo(event,'${id}');"/> 
		</td>
		<td>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-table-td-left'>
		<input TYPE='checkbox' name='rememberMe'  value='1'><label><fmt:message key="portlet.message.rememberMe"/></label></input>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-table-td-left'>
		<br/><br/>
		<input name='login' type='button' value='<fmt:message key="portlet.button.login"/>' class='portlet-form-button'
		  onclick="javascript:Light.loginToPortal('${id}');" />
		<span  class='portlet-link-left'> 
		<fmt:message key="portlet.label.or"/><a href='javascript:;' onclick="javascript:Light.showSignUp('${id}');" ><b><fmt:message key="portlet.label.signUpFor"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></b></a>
		</span>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left' colspan='3'>
		<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','edit','normal','type=forgot');" ><fmt:message key="portlet.label.forgotPassword"/></a>
		</td>
		</tr>
		</table>
		</form>
	</td>
	<td width="50%">
		<form action='<%= request.getContextPath() %><%= org.light.portal.util.PropUtil.getString("portal.openid.url.request") %>' method="POST" onsubmit="javascript:return Light.validateOpenIdLogin(this);" >
		<table border='0' cellpadding='0' cellspacing='0' width='99%'>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<label><fmt:message key="portlet.label.signIn"/> <fmt:message key="portlet.label.with"/> <span id="openid_title_graphic">OpenID</span></label>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-table-td-left' colspan='3'><input id="openid_identifier" name="openid_identifier" type="text" class='portlet-form-input-field' size="24"/>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<table style="width: 100%;">
		<tbody>
		<tr>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.myopenid.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/myopenid.ico"> myOpenID</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='https://www.google.com/accounts/o8/id';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/google.ico"> Google</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://www.flickr.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/flickr.ico"> Flickr</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='https://me.yahoo.com/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/yahoo.ico"> Yahoo!</a></td>
		</tr>
		<tr>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://openid.aol.com/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/aol.ico"> AOL</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.blogspot.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/blogger.ico"> Blogger</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.livejournal.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/lj.ico"> Livejournal</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.pip.verisignlabs.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/verisign.ico"> Verisign</a></td>
		</tr>
		<tr>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.myvidoop.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/vidoop2.ico"> Vidoop</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://claimid.com/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/claimid.ico"> claimID</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://technorati.com/people/technorati/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/technorati.ico"> Technorati</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.vox.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/vox.ico"> Vox</a></td>
		</tr>		
		<tr style="">
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/images/openid.ico"> Other OpenID</a></td>
		<td style="padding: 4px; text-align: right; vertical-align: middle;"></td>
		<td style="padding: 4px; text-align: right; vertical-align: middle;"></td>
		<td style="padding: 4px; text-align: center vertical-align: middle;">
		<a href="http://openid.net/" style="color: rgb(110, 145, 175);" target="_blank"><fmt:message key="portlet.label.help"/></a></td>
		</tr>
		</tbody>
		</table>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<input class="portlet-form-button" type="submit" value="<fmt:message key="portlet.button.login"/>"/>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<label><fmt:message key="portlet.message.signin.openid"/></label>		
		</td>
		</tr>
		</table>
		</form>
	</td>
	</tr>
	</table>
</textarea>
<textarea id="openIdSignIn.view" style="display:none;">		
	<form action='<%= request.getContextPath() %><%= org.light.portal.util.PropUtil.getString("portal.openid.url.request") %>' method="POST" onsubmit="javascript:return Light.validateOpenIdLogin(this);" >
		<table border='0' cellpadding='0' cellspacing='0' width='99%'>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<label><fmt:message key="portlet.label.signIn"/> <fmt:message key="portlet.label.with"/> <span id="openid_title_graphic">OpenID</span></label>		
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-table-td-left' colspan='3'><input id="openid_identifier" name="openid_identifier" type="text" class='portlet-form-input-field' size="24"/>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<table style="width: 100%;">
		<tbody>
		<tr>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.myopenid.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/myopenid.ico"> myOpenID</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='https://www.google.com/accounts/o8/id';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/google.ico"> Google</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://www.flickr.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/flickr.ico"> Flickr</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='https://me.yahoo.com/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/yahoo.ico"> Yahoo!</a></td>
		</tr>
		<tr>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://openid.aol.com/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/aol.ico"> AOL</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.blogspot.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/blogger.ico"> Blogger</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.livejournal.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/lj.ico"> Livejournal</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.pip.verisignlabs.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/verisign.ico"> Verisign</a></td>
		</tr>
		<tr>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.myvidoop.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/vidoop2.ico"> Vidoop</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://claimid.com/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/claimid.ico"> claimID</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://technorati.com/people/technorati/username';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/technorati.ico"> Technorati</a></td>
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://username.vox.com/';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/static/opicons/vox.ico"> Vox</a></td>
		</tr>		
		<tr style="">
		<td style="padding: 4px; vertical-align: middle; cursor: pointer;">
		<a href="javascript:;" onclick="javascript:$('openid_identifier').value='http://';">
		<img style="width: 16px; height: 16px; vertical-align: middle;" src="https://www.idselector.com/images/openid.ico"> Other OpenID</a></td>
		<td style="padding: 4px; text-align: right; vertical-align: middle;"></td>
		<td style="padding: 4px; text-align: right; vertical-align: middle;"></td>
		<td style="padding: 4px; text-align: center vertical-align: middle;">
		<a href="http://openid.net/" style="color: rgb(110, 145, 175);" target="_blank"><fmt:message key="portlet.label.help"/></a></td>
		</tr>
		</tbody>
		</table>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<input class="portlet-form-button" type="submit" value="<fmt:message key="portlet.button.login"/>"/>
		<input type='button' name='action' onClick="javascript:Light.closePortlet('${id}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'></td>
		<td class='portlet-link-left'>
		<label><fmt:message key="portlet.message.signin.openid"/></label>		
		</td>
		</tr>
		</table>
	</form>
</textarea>

<textarea id="loginPortlet.edit" style="display:none;">		
<%@ include file="/WEB-INF/view/default/core/forgotPassword.jsp"%>
</textarea>

<textarea id="localLogin.edit" style="display:none;">		
<%@ include file="/WEB-INF/view/default/core/forgotPassword.jsp"%>
</textarea>

<textarea id="allLogin.edit" style="display:none;">		
<%@ include file="/WEB-INF/view/default/core/forgotPassword.jsp"%>
</textarea>

</fmt:bundle>