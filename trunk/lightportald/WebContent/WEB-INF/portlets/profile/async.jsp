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

<textarea id="myCard.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr valign="top">
<td class='portlet-table-td-center' width='80' style="padding:10px 2px 2px 2px;">
<a href='${url}' ><img src='<%= request.getContextPath() %>${image}' align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/></a>
</td>
<td class='portlet-table-td-left' style="padding:10px 2px 2px 2px;">
<span class="portlet-rss" > 
<a href='${url}' >${name}</a>
</td>
</tr>
<tr valign="top">
<td class='portlet-table-td-left' colspan='2'>
<div style='width: 137px;'>
<img src="<%= request.getContextPath() %>/light/images/talk_top.png" style="border: medium none ;" width="137" height="11"/>
<div class='talk-body'>
<div>${caption}</div>
{if permission == 1 }
<div class='portlet-item' style='text-align:right;'><a href="javascript:;" onclick="javascript:Light.executeRender('${id}','edit','normal');" /><fmt:message key="portlet.button.update"/></a></div>
{/if}
</div>
<div><img src="<%= request.getContextPath() %>/light/images/talk_bottom.png" style="border: medium none ;" width="137" height="11"></div>
</div>
</td>
</tr>
</table>
</textarea>

<textarea id="myCard.edit" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr valign="top">
<td class='portlet-table-td-center' width='80' style="padding:10px 2px 2px 2px;">
<a href='${url}' ><img src='<%= request.getContextPath() %>${image}' align="middle" width='75' height='75'/></a>
</td>
<td class='portlet-table-td-left' style="padding:10px 2px 2px 2px;">
<span class="portlet-rss" > 
<a href='${url}' >${name}</a>
</td>
</tr>
<tr valign="top">
<td class='portlet-table-td-left' colspan='2'>
<form name='form_${id}' action="javascript:;">
<div style='width: 137px;'>
<img src="<%= request.getContextPath() %>/light/images/talk_top.png" style="border: medium none ;" width="137" height="11"/>
<div class='talk-body'>
<input type='text' name='caption' value='${caption}' class='portlet-form-input-field' size='15' onChange="javascript:Light.executeAction('${id}',this.form,'save',null,null,'VIEW','normal');"/> 
<div class='portlet-item' style='text-align:right;padding-top:10px;'>
{if permission == 1 }
<a href="javascript:;" onclick="javascript:document.resetLastAction=1;Light.executeAction('${id}',this.form,'save',null,null,'VIEW','normal');" /><fmt:message key="portlet.button.save"/></a>
{/if}
<a href="javascript:;" onclick="javascript:Light.executeRender('${id}','view','normal');" /><fmt:message key="portlet.button.cancel"/></a>
</div>
</div>
<div><img src="<%= request.getContextPath() %>/light/images/talk_bottom.png" style="border: medium none ;" width="137" height="11"></div>
</div>
</form>
</td>
</tr>
</table>
</textarea>

<textarea id="myMusicPlayerPortlet.view" style="display:none;">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody>
<tr>
<td style="font-size: 12px;" class="portlet-table-td-center">
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" height="120" width="300">
  <param name="movie" value="<%= request.getContextPath() %>/light/swf/myMusicPlayer.swf">
  <param name="quality" value="high">
  <embed src="<%= request.getContextPath() %>/light/swf/myMusicPlayer.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" height="120" width="300">   
</object>
</td>
</tr>
</tbody>
</table>
</textarea>


</fmt:bundle>