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
 * Customized/Extended Portlet Views
 */
%>
<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<textarea id="addDealFeed.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif' /></a>
</span>
<form name='dealFeedForm'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Add Deal Feed
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-left' >
        Enter a URL (RSS/ATOM or autodiscovery):
      </td>
    </tr>
    <tr>
    <td class='portlet-table-td-left' >
      <input type='text' name='pcFeed' value='' size='30' />
      <input name='AddFeed' type='button' value='Add' onclick="javascript:Light.addDealFeed('${id}');" />
    </td>
    </tr>
  </table>
</form>
<form name='myFeedFileForm' enctype='multipart/form-data' method='post'
  action ='<%= request.getContextPath() %>/uploadDealOpml.lp' onsubmit="javascript:return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback}, '${id}')" >
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Or import an OPML file:
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-left' >
        <input type='file' name='file' />
        <input type='submit' value='Upload' />
      </td>
    </tr>
    <tr>
  </table>
</form>
</textarea>

<textarea id="searchHistory.view" style="display:none;">
<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
<tbody>
<tr>
<td>
<img src="<%= request.getContextPath() %>/light/images/clear.gif" class="cl_h" onclick="javascript:clearSearchHistory();" title='<fmt:message key="portlet.button.clearSearchHistory"/>'>
<strong class="th"><fmt:message key="portlet.label.searchHistory"/>:</strong>
</td>
</tr>
<tr>
<td class="t_s_h">
<table border="0" cellpadding="0" cellspacing="0">
<tbody>
{for keyword in keywords}
<tr>
<td>
<span class='portlet-item'>
<a title="${keyword}" href="javascript:;" onclick="javascript:searchHistoryKeyword(event,'${id}','${keyword}','${type}');">${keyword}</a>
</span>
</td>
</tr>
{/for}
</tbody>
</table>
</td></tr></tbody></table>
</textarea>

<textarea id="searchSuggestion.view" style="display:none;">
<ul id="sug_list_${id}" class="sug_list">
	{for keyword in keywords}
    <li class="" onclick="toKeyword(this,'${id}')" onmouseover="overKeyword(this,'${id}')" onmouseout="outKeyword(this)"><p>${keyword}</p></li>
    {/for}    
   </ul>
<div class="close_sug"><a onclick="closeSearchSuggestion();return false;" href="javascript:;"><fmt:message key="portlet.button.close"/></a></div>
</textarea>
	
</fmt:bundle>