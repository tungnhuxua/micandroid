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

<jsp:include page="/WEB-INF/view/default/core/code.jsp" ></jsp:include>

<jsp:include page="/WEB-INF/view/default/core/login.jsp" ></jsp:include>

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.portal.registration.page") %>' />

<jsp:include page="/WEB-INF/view/default/core/options.jsp" ></jsp:include>

<jsp:include page="/WEB-INF/view/default/core/content.jsp" ></jsp:include>

<jsp:include page="/WEB-INF/view/default/core/myAccount.jsp" ></jsp:include>

<textarea id="sessionTimeoutWarning.jst" style="display:none;">
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-center' style="color:red;">
        Warning! Due to inactivity, your session will expire in 00:00:${timeLeft}. To extend your session another 10 minute(s), please press the "Extend" button
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-center' >
      <input name='reactive' type='button' value='<fmt:message key="portlet.button.extend"/>' onclick="javascript:Light.refreshSessionTiimeout();" />
    </td>
    </tr>
  </table>
</textarea>

<textarea id="changeLanguagePortlet.view" style="display:none;">
<form name="form_${id}">
<table border='0' cellpadding='0' cellspacing='0' width='95%' style='margin:10px;'>
<c:forEach var="language" items="${applicationScope.languages}" varStatus="status">
<c:if test='${status.index % 3 == 0}'>
<tr>
</c:if>
<td class='portlet-table-td-left' width='33%'>
<c:if test='${language.supported}'>
<input type='radio' name='language' value='<c:out value="${language.id}"/>'
<c:if test='${sessionScope.currentLocale == language.id}'>
checked="checked"
</c:if>
>
<label><c:out value="${language.desc}"/></label></input>
</c:if>
<c:if test='${!language.supported}'>

<label style="padding:3px 0 0 25px;"><c:out value="${language.desc}"/></label>
</c:if>
</td>
<c:if test='${status.index % 3 == 2}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${applicationScope.languageCount % 3 != 0}'>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right' colspan='3' style="padding:20px;">
<input name='Save' type='button' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button'
 onclick="javascript:Light.saveLanguage('${id}',true);" />
<input name='Cancel' type='button' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'
 onclick="javascript:Light.closePortlet('${id}');" />
</td>
</tr>
</table>
</form>
</textarea>

<textarea id="viewTab.jst" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%' >
{for tab in tabs}
<tr valign='middle' class="t_s_h">
<td class='portlet-table-td-left' width='100%' style='padding:5px;'>
<span class='portlet-item'><a href="javascript:;" onclick="javascript:Light.gotoTab('${tab.parentId}','${tab.serverId}');">${tab.label}</a></span>
</td>
</tr>
{/for}
</table>
</textarea>

<textarea id="socialShare.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<div class='socialMedia portlet-item'>
	<ul>
		<li><a href="http://www.facebook.com/share.php?u=${link}&t=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it" alt="" border="0" height='16' width='16' align='top'> Facebook</a></li>		
		<li><a href="http://del.icio.us/post?url=${link}&title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/delicious.gif" title="delicious it" alt="" border="0" height='16' width='16' align='top'> del.icio.us</a></li>
		<li><a href="http://digg.com/submit?phase=2&url=${link}&title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/digg.gif" title="digg it" alt="" border="0" height='16' width='16' align='top'> Digg</a></li>
		
		<li><a href="http://www.stumbleupon.com/submit?url=${link}&title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/stumbleupon.png" title="StumbleUpon" alt="" border="0" height='16' width='16' align='top'> StumbleUpon</a></li>
		<li><a href="http://www.google.com/bookmarks/mark?op=edit&bkmk=${link}&title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/google.gif" title="Google Bookmarks" alt="" border="0" height='16' width='16' align='top'> Google Bookmarks</a></li>

		<li><a href="http://www.technorati.com/faves?add=${link}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/technorati.png" title="Technorati" alt="" border="0" height='16' width='16' align='top'> Technorati</a></li>
		<li><a href="http://blinklist.com/index.php?Action=Blink/addblink.php&Url=${link}&Title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/blinklist.png" title="BlinkList" alt="" border="0" height='16' width='16' align='top'> BlinkList</a></li>

		<li><a href="http://www.newsvine.com/_wine/save?u=${link}&h=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/newsvine.png" title="Newsvine" alt="" border="0" height='16' width='16' align='top'> Newsvine</a></li>

		<li><a href="http://reddit.com/submit?url=${link}&title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/reddit.png" title="reddit" alt="" border="0" height='16' width='16' align='top'> reddit</a></li>

		<li><a href="https://favorites.live.com/quickadd.aspx?marklet=1&mkt=en-us&url=${link}&title=${title}&top=1" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/windowsLive.png" title="Windows Live" alt="" border="0" height='16' width='16' align='top'> Windows Live</a></li>
		<li><a href="http://tailrank.com/share/?link_href=${link}&title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/tailrank.png" title="Tailrank" alt="" border="0" height='16' width='16' align='top'> Tailrank</a></li>
		<li><a href="http://myweb2.search.yahoo.com/myresults/bookmarklet?u=${link}&t=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/yahoo.png" title="Yahoo! My Web" alt="" border="0" height='16' width='16' align='top'> Yahoo! My Web</a></li>
		
		<li><a href="aim:goim?message=${title} at ${link}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/aim.png" title="AOL IM" alt="" border="0" height='16' width='16' align='top'> AOL IM</a></li>
		<li><a href="http://ma.gnolia.com/bookmarklet/add?url=${link}&title=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/ma.gnolia.png" title="ma.gnolia" alt="" border="0" height='16' width='16' align='top'> ma.gnolia</a></li>
		<!-- 
		<li><a href="http://www.netscape.com/submit/?U=${link}&T=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/link.png" title="netscape" alt="" border="0" height='16' width='16' align='top'> Netscape</a></li>
		<li><a href="http://furl.net/storeIt.jsp?u=${link}&t=${title}" onclick="javascript:hidePopupDiv('${popupName}');" target='_blank'><img src="<%= request.getContextPath() %>/light/images/link.png" title="furl it!" alt="" border="0" height='16' width='16' align='top'> Furl</a></li>
		 -->
	</ul>
	<div class="clear"></div>
</div>
</textarea>

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.portal.views.ext") %>' />

</fmt:bundle>
