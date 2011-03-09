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
<%@ page import="java.util.*,org.light.portal.*,org.light.portal.util.*" %>
<fmt:bundle basename="resourceBundle">
<%
List<LabelBean> windowSkins = ConfigurationUtil.getSupportedWindowSkins();
Locale currentLocale = Context.getInstance().getLocale(request);
for(org.light.portal.util.LabelBean bean : windowSkins){
	bean.setDesc(MessageUtil.getMessage(bean.getDesc(),currentLocale));
}
request.setAttribute("windowSkins",windowSkins);
List<LabelBean> clients = ConfigurationUtil.getSupportedClients();
for(LabelBean bean : clients){
	bean.setDesc(MessageUtil.getMessage(bean.getDesc(),currentLocale));
}
request.setAttribute("clients",clients);
request.setAttribute("colors",org.light.portal.util.ConfigurationUtil.getSupportedFontColors());
%>

<textarea id="configMode.jst" style="display:none;">
<form name="form_${id}">
<table border='0' cellpadding='0' cellspacing='0' width="99%" style='margin-top:10px;'>
<tr>
<td class='portlet-table-td-right' width="30%"><fmt:message key="portlet.label.title"/>:</td>
<td class='portlet-table-td-left'>
<input type='text' name='pcTitle' value='${title}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.client"/>:</td>
<td class="portlet-table-td-left">
<select name="pcClient" size="1" class="portlet-form-select">
<c:forEach var="client" items="${requestScope.clients}" varStatus="status">
<option value='<c:out value="${client.id}"/>'
{if client == '<c:out value="${client.id}"/>' } selected="selected" {/if}
>
<c:out value="${client.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.titleBgColor"/>:</td>
<td class='portlet-table-td-left'>
<div class='color-block' style='background-color:red;' onclick="javascript:setColor('${id}',1,'red');"> </div>
<div class='color-block' style='background-color:orange;' onclick="javascript:setColor('${id}',1,'orange');"> </div>
<div class='color-block' style='background-color:yellow;' onclick="javascript:setColor('${id}',1,'yellow');"> </div>
<div class='color-block' style='background-color:green;' onclick="javascript:setColor('${id}',1,'green');"> </div>
<div class='color-block' style='background-color:blue;' onclick="javascript:setColor('${id}',1,'blue');"> </div>
<div class='color-block' style='background-color:black;' onclick="javascript:setColor('${id}',1,'black');"> </div>
<div class='color-block' style='background-color:white;' onclick="javascript:setColor('${id}',1,'white');"> </div>
<select size="1" class="portlet-form-select" style="width:68px;" onchange="javascript:setColor('${id}',1,this.value);this.style.backgroundColor=this.value;">
<c:forEach var="color" items="${requestScope.colors}">
<option value='<c:out value="${color.id}"/>' style='background-color:<c:out value="${color.id}"/>'>
<c:out value="${color.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.titleColor"/>:
</td>
<td class='portlet-table-td-left'>
<div class='color-block' style='background-color:red;' onclick="javascript:setColor('${id}',2,'red');"> </div>
<div class='color-block' style='background-color:orange;' onclick="javascript:setColor('${id}',2,'orange');"> </div>
<div class='color-block' style='background-color:yellow;' onclick="javascript:setColor('${id}',2,'yellow');"> </div>
<div class='color-block' style='background-color:green;' onclick="javascript:setColor('${id}',2,'green');"> </div>
<div class='color-block' style='background-color:blue;' onclick="javascript:setColor('${id}',2,'blue');"> </div>
<div class='color-block' style='background-color:black;' onclick="javascript:setColor('${id}',2,'black');"> </div>
<div class='color-block' style='background-color:white;' onclick="javascript:setColor('${id}',2,'white');"> </div>
<select size="1" class="portlet-form-select" style="width:68px;" onchange="javascript:setColor('${id}',2,this.value);this.style.backgroundColor=this.value;">
<c:forEach var="color" items="${requestScope.colors}">
<option value='<c:out value="${color.id}"/>' style='background-color:<c:out value="${color.id}"/>'>
<c:out value="${color.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.contentBgColor"/>:
</td>
<td class='portlet-table-td-left'>
<div class='color-block' style='background-color:red;' onclick="javascript:setColor('${id}',3,'red');"> </div>
<div class='color-block' style='background-color:orange;' onclick="javascript:setColor('${id}',3,'orange');"> </div>
<div class='color-block' style='background-color:yellow;' onclick="javascript:setColor('${id}',3,'yellow');"> </div>
<div class='color-block' style='background-color:green;' onclick="javascript:setColor('${id}',3,'green');"> </div>
<div class='color-block' style='background-color:blue;' onclick="javascript:setColor('${id}',3,'blue');"> </div>
<div class='color-block' style='background-color:black;' onclick="javascript:setColor('${id}',3,'black');"> </div>
<div class='color-block' style='background-color:white;' onclick="javascript:setColor('${id}',3,'white');"> </div>
<select size="1" class="portlet-form-select" style="width:68px;" onchange="javascript:setColor('${id}',3,this.value);this.style.backgroundColor=this.value;">
<c:forEach var="color" items="${requestScope.colors}">
<option value='<c:out value="${color.id}"/>' style='background-color:<c:out value="${color.id}"/>'>
<c:out value="${color.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.textColor"/>:
</td>
<td class='portlet-table-td-left'>
<div class='color-block' style='background-color:red;' onclick="javascript:setColor('${id}',4,'red');"> </div>
<div class='color-block' style='background-color:orange;' onclick="javascript:setColor('${id}',4,'orange');"> </div>
<div class='color-block' style='background-color:yellow;' onclick="javascript:setColor('${id}',4,'yellow');"> </div>
<div class='color-block' style='background-color:green;' onclick="javascript:setColor('${id}',4,'green');"> </div>
<div class='color-block' style='background-color:blue;' onclick="javascript:setColor('${id}',4,'blue');"> </div>
<div class='color-block' style='background-color:black;' onclick="javascript:setColor('${id}',4,'black');"> </div>
<div class='color-block' style='background-color:white;' onclick="javascript:setColor('${id}',4,'white');"> </div>
<select size="1" class="portlet-form-select" style="width:68px;" onchange="javascript:setColor('${id}',4,this.value);this.style.backgroundColor=this.value;">
<c:forEach var="color" items="${requestScope.colors}">
<option value='<c:out value="${color.id}"/>' style='background-color:<c:out value="${color.id}"/>'>
<c:out value="${color.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.contentSkin"/>: </td>
<td class="portlet-table-td-left">
<select name="windowSkin" size="1" class="portlet-form-select" style="width:200px;">
<c:forEach var="windowSkin" items="${requestScope.windowSkins}" varStatus="status">
<option value='<c:out value="${windowSkin.id}"/>'
{if skin == '<c:out value="${windowSkin.id}"/>' } selected='selected' {/if}
><c:out value="${windowSkin.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.windowStatus"/>: </td>
<td class="portlet-table-td-left">
<select name="windowStatus" size="1" class="portlet-form-select" style="width:200px;">
<option value='0' 
{if windowStatus == '0' } selected="selected" {/if}
><fmt:message key="portlet.label.normal"/></option>
<option value='1' 
{if windowStatus == '1' } selected="selected" {/if}
><fmt:message key="portlet.label.minimized"/></option>
<option value='2' 
{if windowStatus == '2' } selected="selected" {/if}
><fmt:message key="portlet.label.maximized"/></option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.autoRefresh"/>: </td>
<td class='portlet-table-td-left'>
<select name='auto' size='1'  class='portlet-form-select' style="width:200px;">
<option value='1'
{if autoRefreshed == 1 } selected='selected' {/if}
>true</option>
<option value='0'
{if autoRefreshed == 0 } selected='selected' {/if}
>false</option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.periodTime"/>: </td>
<td class='portlet-table-td-left'>
<select name='second' size='1'  class='portlet-form-select' style="width:200px;">
<option  value='0'
{if periodTime == 0 } selected='selected' {/if}
>0
</option>
<c:forEach var="i" begin="60" end="300" step="30">
<option  value='<c:out value="${i}" />'
{if periodTime == <c:out value="${i}" /> } selected='selected' {/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.show.normal"/>:
</td>
<td class='portlet-table-td-left'>
<select name='showNumber' size='1'  class='portlet-form-select' style="width:200px;">
<c:forEach var="i" begin="0" end="100" step="1">
<option  value='<c:out value="${i}" />'
{if showNumber == <c:out value="${i}" /> } selected='selected' {/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.margin.top"/>:
</td>
<td class='portlet-table-td-left'>
<select name='marginTop' size='1'  class='portlet-form-select' style="width:200px;">
<c:forEach var="i" begin="0" end="100" step="1">
<option  value='<c:out value="${i}" />'
{if marginTop == <c:out value="${i}" /> } selected='selected' {/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<tr valign='top'>
<td class="portlet-table-td-right">
<fmt:message key="portlet.label.columns.cross"/>:
</td>
<td class="portlet-table-td-left">
<select name="colspan" size="1" class="portlet-form-select" style="width:200px;">
<option value='0'
{if colspan == 0 } selected='selected' {/if}
> </option>
<c:forEach var="i" begin="2" end="10" step="1">
<option  value='<c:out value="${i}" />'
{if colspan == <c:out value="${i}" /> } selected='selected' {/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class="portlet-table-td-right"></td>
<td class='portlet-table-td-left'>
<input type='checkbox' name='transparent' value='${transparent}' class='portlet-form-checkbox' onchange="javascript:switchPortletTransparence('${id}',this);" 
{if transparent == true}
checked="checked"
{/if}
>
<fmt:message key="portlet.label.transparentPortlet"/></input> 
</td>
</tr>
<tr>
<td></td>
<td class='portlet-table-td-left'>
<input type='checkbox' name='showIcon' value='${showIcon}' class='portlet-form-checkbox' onchange="javascript:switchPortletIcon('${id}',this);" 
{if showIcon == true}
checked="checked"
{/if}
>
<fmt:message key="portlet.label.showIcon"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<input name='save' type='button' value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button'
 onclick="javascript:configPortlet('${id}');" />
<input name='default' type='button' value='<fmt:message key="portlet.button.default"/>' class='portlet-form-button'
 onclick="javascript:defaultConfigPortlet('${id}');" />
<input type='button' name='action' onClick="javascript:Light.executeRender('${id}','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>

<pre id="objectComment.jst" style="display:none;">
<form name='objectCommentForm_${id}'>
<input type='hidden' name='objectId' value='${objectId}'/>
<input type='hidden' name='objectType' value='${objectType}'/>
<input type='hidden' name='parentId' value='${parentId}'/>
<table border='0' cellpadding='0' cellspacing='0' width="100%">
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.comment"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='comment' class='portlet-form-textarea-field' rows='5' style="width:100%;"></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<input type='button' name='action' onClick="javascript:saveObjectComment('${id}');" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:hidePopupDiv('${popupName}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' /></td>
</tr>
</table>
</form>
</pre>

<pre id="chattingInput.jst" style="display:none;">
<form name='form_${id}'>
<table border='0' cellpadding='0' cellspacing='0' width="100%">
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='chat' class='portlet-form-textarea-field' rows='2' style="width:100%;" onkeypress="return keyDownChat(event,'${id}');"></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'  colspan='2'>
<input type='button' name='action' value='<fmt:message key="portlet.button.send"/>' class='portlet-form-button' onclick="javascript:sendChatMessage('${id}');"/>      
<input type='button' name='action' onClick="javascript:showInviteList('${id}');" value='<fmt:message key="portlet.button.invite"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</pre>

<textarea id="inviteList.jst" style="display:none;">
<form id='inviteList' name='inviteList'>
${content}
<table border='0' cellpadding='0' cellspacing='0' width='98%' style='margin-top:20px;'>
<tr>
<td class='portlet-table-td-right'>
<input type='button' name='action' onClick="javascript:inviteBuddysToChat(this.form);hidePopupDiv('inviteList');" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:hidePopupDiv('inviteList');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
<textarea id="addUserTag.jst" style="display:none;">
<form id='addUserTag' name='addUserTag'>
<table border='0' cellpadding='0' cellspacing='0' width='98%' style='margin-top:20px;'>
<tr>
<td class='portlet-table-td-left'>
<span class='portlet-note'><fmt:message key="portlet.label.addUserTag"/></span>
<input type='hidden' name='id' value='${id}'/>
<input type='hidden' name='objectType' value='${objectType}'/>
<input type='hidden' name='objectId' value='${objectId}'/>
<input type='text' name='tag' value='' class='portlet-form-input-field' size='60' onchange="Light.addUserTag(this.form);hidePopupDiv('addUserTag');" AUTOCOMPLETE='OFF'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<input type='button' name='action' onClick="javascript:Light.addUserTag(this.form);hidePopupDiv('addUserTag');" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:hidePopupDiv('addUserTag');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
<textarea id="picturePositionTag.jst" style="display:none;">
{if tag.type == 200 }
<img id='pin_${tag.id}' class='pptagging' src='<%= request.getContextPath() %>/light/images/ptag.png' style='cursor: pointer;'
 onmouseover="javascript:Light.showPicturePositionTagging(event,'${id}','${pictureId}','${tag.id}',${mode});"
/>
{elseif tag.type > 200 }
<img id='pin_${tag.id}' class='pptagging' src='<%= request.getContextPath() %>/light/images/pin.png' style='cursor: pointer;'
 onmouseover="javascript:Light.showPicturePositionTagging(event,'${id}','${pictureId}','${tag.id}',${mode});"
/>
{else}
<img id='pin_${tag.id}' class='pptagging' src='<%= request.getContextPath() %>/light/images/ptag.png' style='cursor: pointer;'
 onmouseover="javascript:Light.showPicturePositionTagging(event,'${id}','${pictureId}','${tag.id}',${mode});"
/>
{/if}
</textarea>
<textarea id="picturePositionTagEdit.jst" style="display:none;">
{if tag.type == 200 }
<img id='pin_${tag.id}' class='pptagging' src='<%= request.getContextPath() %>/light/images/ptag.png' style='cursor: pointer;'
 ondblclick="javascript:Light.showPicturePositionTagging(event,'${id}','${pictureId}','${tag.id}',${mode});"
 onmousedown="javascript:Light.moveBeginPPT(event,'${id}','${pictureId}','${tag.id}');" 
 onmousemove="javascript:Light.movePPT(event);"  
/>
{elseif tag.type > 200 }
<img id='pin_${tag.id}' class='pptagging' src='<%= request.getContextPath() %>/light/images/pin.png' style='cursor: pointer;'
 ondblclick="javascript:Light.showPicturePositionTagging(event,'${id}','${pictureId}','${tag.id}',${mode});"
 onmousedown="javascript:Light.moveBeginPPT(event,'${id}','${pictureId}','${tag.id}');" 
 onmousemove="javascript:Light.movePPT(event);"  
/>
{else}
<img id='pin_${tag.id}' class='pptagging' src='<%= request.getContextPath() %>/light/images/ptag.png' style='cursor: pointer;'
 ondblclick="javascript:Light.showPicturePositionTagging(event,'${id}','${pictureId}','${tag.id}',${mode});"
 onmousedown="javascript:Light.moveBeginPPT(event,'${id}','${pictureId}','${tag.id}');" 
 onmousemove="javascript:Light.movePPT(event);"  
/>
{/if}
</textarea>
<textarea id="imagePositionTagging.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');Light.hideAllPPT();">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
${content}
</textarea>
<%
java.util.List<org.light.portal.util.LabelBean> tagTypes = org.light.portal.util.ConfigurationUtil.getSupportedTagTypes();
for(org.light.portal.util.LabelBean bean : tagTypes){
	bean.setDesc(org.light.portal.core.PortalContextFactory.getPortalContext().getMessageByKey(bean.getDesc()));
}
request.setAttribute("tagTypes",tagTypes);
%>
<textarea id="imagePositionTaggingEdit.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');Light.hideAllPPT();">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form id='editPPTForm'>
<table border='0' cellpadding='0' cellspacing='0' >
<td class='portlet-table-td-left'>
<input type='hidden' id='tagId' value='${tagId}'/>
<input type='hidden' id='pictureId' value='${pictureId}'/>
<input type='hidden' id='positionX' value='${x}'/>
<input type='hidden' id='positionY' value='${y}'/>
<!--
<tr> 
<label><fmt:message key="portlet.label.x"/>:</label>
</td>
<td class='portlet-table-td-left'>
<input type='text' id='positionX' value = '${x}' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<label><fmt:message key="portlet.label.y"/>:</label>
</td>
<td class='portlet-table-td-left'>
<input type='text' id='positionY' value = '${y}' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
 -->
<tr>
<td class='portlet-table-td-left'>
<label><fmt:message key="portlet.label.type"/>:</label>
</td>
<td class='portlet-table-td-left'>
<select id='type' size='1' class='portlet-form-select' value='${type}' style='width:100px;' onchange="javascript:Light.changePPTType('editPPTForm',this.value);">
	<option value=''></option>
	<c:forEach var="type" items="${requestScope.tagTypes}" varStatus="status">
	<option value='<c:out value="${type.id}"/>'
	{if type == '<c:out value="${type.id}"/>'}selected='selected'{/if}
	><c:out value="${type.desc}"/></option>
	</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<label><fmt:message key="portlet.label.tag"/>:</label>
</td>
<td class='portlet-table-td-left'>
<input type='text' id='tag' value = '${tag}' class='portlet-form-input-field' size='60' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<label id='titleLabel'	
{if type != 200}
style='visibility: hidden;'
{/if}
><fmt:message key="portlet.label.title"/>:</label>
</td>
<td class='portlet-table-td-left'>
<input type='text' id='title' value = '${title}' class='portlet-form-input-field' size='60' 
{if type != 200}
style='visibility: hidden;'
{/if}
/> 
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-left'>
<label id='contentLabel' 
{if type != 200}
style='visibility: hidden;'
{/if}
><fmt:message key="portlet.label.content"/>:</label>
</td>
<td class='portlet-table-td-left'>
&lt;textarea id='content' class='portlet-form-textarea-field' 
{if type != 200}
style='visibility: hidden;'
{/if}
rows='5' cols='60'&gt;{if content != 'null'}${content}{/if}&lt;/textarea&gt;
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
</td>
<td class='portlet-table-td-left'>
<input type='button' onclick="javascript:if(Light.savePPT('editPPTForm','${id}')) hideTopPopupDiv('${popupName}');Light.hideAllPPT();" value = '<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' onclick="javascript:hideTopPopupDiv('${popupName}');Light.hideAllPPT();" value = '<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
{if tagId != ''}<input type='button' onclick="javascript:Light.deletePPT('editPPTForm','${id}');hideTopPopupDiv('${popupName}');Light.hideAllPPT();" value = '<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />{/if}
</td>
</tr>
</table>
</form>
</textarea>
<textarea id="addFeed.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='myFeedForm'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Add My Feed
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
      <input name='AddFeed' type='button' value='Add' onclick="javascript:addFeed('${id}');" />
    </td>
    </tr>
  </table>
</form>
<form name='myFeedFileForm' enctype='multipart/form-data' method='post'
  action ='<%= request.getContextPath() %>/uploadOpml.lp' onsubmit="javascript:return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback}, '${id}')" >
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
<textarea id="addAllFeed.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='allFeedFileForm' enctype='multipart/form-data' method='post'
  action ='<%= request.getContextPath() %>/uploadAllOpml.lp' onsubmit="javascript:return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback}, '${id}')" >
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Import an OPML file:
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
<textarea id="addFeaturedFeed.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='myFeaturedFeedForm'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Add Feed
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
      <input name='AddFeaturedFeed' type='button' value='Add' onclick="javascript:addFeaturedFeed('${id}');" />
    </td>
    </tr>
  </table>
</form>
</textarea>
<textarea id="addCategoryFeed.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='myCategoryFeedForm'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Add Feed
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
      <input name='AddCategoryFeed' type='button' value='Add' onclick="javascript:addCategoryFeed('${id}','${tag}');" />
    </td>
    </tr>
  </table>
</form>
</textarea>
<textarea id="addSubCategoryFeed.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='mySubCategoryFeedForm'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Add Feed
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
      <input name='AddSubCategoryFeed' type='button' value='Add' onclick="javascript:addSubCategoryFeed('${id}','${tag}','${subtag}');" />
    </td>
    </tr>
  </table>
</form>
</textarea>
<textarea id="editProfilePhoto.jst" style="display:none;">
<div title='' width='100%' style='clear:both;text-align:right;'>
	<a href='javascript:;' onclick='javascript:hideEditProfilePhoto();'>
	<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/>
	</a>
</div>

<div style='margin:10px 5px; '>
<p>
Share your photos to let friends and other members see who you are. 
</p>
<p style='margin:10px 0 20px 0;'>
Photos may not contain nudity, sexually explicit content, violent or offensive material, or copyrighted images. Do not load images of other people without their permission.
</p>
</div>
<form name='editProfilePhotoForm' enctype='multipart/form-data' method='post'
  action ='<%= request.getContextPath() %>/uploadProfilePhoto.lp' onsubmit="javascript:return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback}, '${id}')">
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Upload Photo :
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-left' >
        <input type='file' name='file' size='30' />
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-right' >
        <input type='submit' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
      </td>
    </tr>
  </table>
</form>
</textarea>
<textarea id="cropProfilePhoto.jst" style="display:none;">
<div title='' width='100%' style='clear:both;text-align:right;'>
	<a href='javascript:;' onclick='javascript:hideCropProfilePhoto();'>
	<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/>
	</a>
</div>
<div style='width:100%;margin:10px auto;text-align:center;'>
	<span class='portal-header-title' style='padding:0'><b>Select the area of the photo for your profile</b></span>
</div>
<table border='0' cellpadding='0' cellspacing='0' >
    <tr valign='middle'>
      <td class='portlet-table-td-left' >
		<div id='crop_${id}' style='position:relative;width:620px;text-align:center;'>
			<img src='<%= request.getContextPath() %>${ value }' id='cropbox_${id}'>
		</div>
	  </td>
	  <td class='portlet-table-td-left' >
		<div style='width:100px;height:100px;overflow:hidden;margin-left:5px;'>
			<img src='<%= request.getContextPath() %>${ value }' id='preview_${id}' />
		</div>
	  </td>
	  </tr>
</table>
<div style='width:100%;margin:10px auto;text-align:center;'>
	<input type="button" onclick="submitCropProfilePhoto('${id}');"value="Crop Photo">
</div>
</textarea>
<textarea id="editMyUrl.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick='javascript:hideEditMyUrl();'>
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='editMyUrlForm'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        URI :
      </td>    
      <td class='portlet-table-td-left' >
      <input type='text' name='uri' value='' size='18' onkeypress="return keyDownSaveMyUrl(event,'${id}');"/>     
      </td>
    </tr>
    <tr>
	<td class='portlet-table-td-right' colspan='2' >
	<input type='button' onClick="javascript:saveMyUrl('${id}');"  value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
	<input type='button' onClick='javascript:hideEditMyUrl();' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
	</td>
	</tr>
  </table>
</form>
</textarea>
<%
String host = request.getHeader("Host");
host += request.getContextPath();
%>
<textarea id="uploadPictures.jst" style="display:none;">	
	<table width="640" cellspacing="0" border="0">   
    <tr valign="top">
        <td colspan="2" align="center"><br/>
        <applet code="wjhk.jupload2.JUploadApplet"
            archive="wjhk.jupload.jar" width="640" height="400" alt=""
            mayscript>
            <param name="postURL" value="http://<%= host %>/uploadPictures.lp" />
            <param name="maxChunkSize" value="3000000" />
			<param name="maxFileSize" value="3000000" />
            <param name="uploadPolicy" value="PictureUploadPolicy" />
            <param name="nbFilesPerRequest" value="1" />    
            <param name="allowedFileExtensions" value="gif/jpg/jpeg/png"/>    
            <param name="afterUploadURL" value="javascript:Light.closeUploader('${id}','${popupName}');" /> 
            <param name="showLogWindow" value="false" />    
            <!-- Optionnal, see code comments -->
            <param name="debugLevel" value="1" />
            <!-- Optionnal, see code comments --> 
			Java 1.5 or higher plugin required. 
	  	</applet>        
        </td>
        <td class='portlet-table-td-right'>
        	<a href='javascript:;' onclick="javascript:Light.closeUploader('${id}','${popupName}');">
			<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>	
        </td>
    </tr>   
  	</table>
</textarea>
<textarea id="uploadMusics.jst" style="display:none;">	
	<table width="640" cellspacing="0" border="0">   
    <tr valign="top">
        <td colspan="2" align="center"><br/>
        <applet code="wjhk.jupload2.JUploadApplet"
            archive="wjhk.jupload.jar" width="640" height="400" alt=""
            mayscript>
            <param name="postURL" value="http://<%= host %>/uploadMusics.lp" />
            <param name="maxChunkSize" value="5000000" />
			<param name="maxFileSize" value="5000000" />
            <param name="uploadPolicy" value="DefaultUploadPolicy" />
            <param name="nbFilesPerRequest" value="1" />    
            <param name="allowedFileExtensions" value="mp3/mp4/wav/wma"/>    
            <param name="afterUploadURL" value="javascript:Light.closeUploader('${id}','${popupName}');" /> 
            <param name="showLogWindow" value="false" />    
            <!-- Optionnal, see code comments -->
            <param name="debugLevel" value="1" />
            <!-- Optionnal, see code comments --> 
			Java 1.5 or higher plugin required. 
	  	</applet>        
        </td>
        <td class='portlet-table-td-right'>
        	<a href='javascript:;' onclick="javascript:Light.closeUploader('${id}','${popupName}');">
			<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>	
        </td>
    </tr>   
  	</table>
</textarea> 
<%
java.util.List<org.light.portal.util.LabelBean> bgImages = org.light.portal.util.ConfigurationUtil.getSupportedBgImages();
request.setAttribute("bgImages",bgImages);
request.setAttribute("bgColumn",4);
request.setAttribute("bgImagesCount",bgImages.size());
%>
<textarea id="moreBgImage.jst" style="display:none;">
<form name='form_moreBgImage'>
<table border='0' cellpadding='0' cellspacing='0'>
<c:forEach var="image" items="${requestScope.bgImages}" varStatus="status">
<c:if test='${status.index % bgColumn == 0}'>
<tr valign='top'>
</c:if>
<td class='portlet-table-td-left'><image height='40' width='200' src='<%= request.getContextPath() %><c:out value="${image.id}"/>'/>
<input type='radio' name='ptBg' value='<c:out value="${image.id}"/>' {if bgImage == '<c:out value="${image.id}"/>'}checked='true'{/if}/>
</td>
<c:if test='${status.index % bgColumn == bgColumn - 1}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.bgImagesCountt % bgColumn != 0}'>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right' colspan='5'>
<input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:saveBgImage('${id}');" class='portlet-form-button'/>
<input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick='javascript:cancelBgImage();' class='portlet-form-button'/>
</td>
</tr>
</table>
</form>
</textarea>
<%
java.util.List<org.light.portal.util.LabelBean> headerImages = org.light.portal.util.ConfigurationUtil.getSupportedHeaderImages();
request.setAttribute("headerImages",headerImages);
request.setAttribute("headerColumn",2);
request.setAttribute("headerImagesCount",headerImages.size());
%>
<textarea id="moreHeaderImage.jst" style="display:none;">
<form name='form_moreHeaderImage'>
<table border='0' cellpadding='0' cellspacing='0'>
<c:forEach var="image" items="${requestScope.headerImages}" varStatus="status">
<c:if test='${status.index % headerColumn == 0}'>
<tr valign='top'>
</c:if>
<td class='portlet-table-td-left'><image height='40' width='200' src='<%= request.getContextPath() %><c:out value="${image.id}"/>'/>
<input type='radio' name='ptHeader' value='<c:out value="${image.id}"/>' {if headerImage == '<c:out value="${image.id}"/>'}checked='true'{/if}/>
</td>
<c:if test='${status.index % headerColumn == headerColumn - 1}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.headerImagesCount % headerColumn != 0}'>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:saveHeaderImage('${id}');" class='portlet-form-button'/>
<input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick='javascript:cancelHeaderImage();' class='portlet-form-button'/>
</td>
</tr>
</table>
</form>
</textarea>		
<textarea id="instantMessage.jst" style="display:none;">
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        <label>${from} want to IM you.<br/>Would you like to accept?</label>
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input name='yes' type='button' value='Yes' onclick="javascript:acceptChat('${eventId}','${from}');" />
      <input name='no' type='button' value='No' onclick="javascript:refuseChat('${eventId}');" />
    </td>
    </tr>
  </table>
  <embed src='<%= request.getContextPath() %>${ring}' autostart="true" loop="false" width="2" height="0">
  </embed>
</textarea>
<textarea id="addToFriend.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        <label>
        Do you reallly what to add <b>
       	<c:if test='${sessionScope.visitedUser != null}'>
        <c:out value="${sessionScope.visitedUser.displayName}"/>
        </c:if>
        <c:if test='${sessionScope.visitedUser == null}'>
        ${buddyName}
        </c:if>
        </b> as a friend?<br/>Click <fmt:message key="portlet.button.ok"/> only if you reallly what to add <b><c:out value="${sessionScope.visitedUser.displayName}"/></b> as a friend.
      	</label>
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:Light.saveAddToFriend('${id}','${buddyId}');hidePopupDiv('${popupName}');" class='portlet-form-button'/>
      <input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="responseAddToFriend.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        <label><fmt:message key="message.connection.add"/></label>
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<pre id="sendMessage.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
	<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
		<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/>
	</a>
</span>
<form  id='mForm_${id}' name='mForm_${id}'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
     	<td class='portlet-table-td-left' width='15%'>
			<fmt:message key="portlet.label.to"/>:
		</td>
		<td class='portlet-table-td-left'>
			${buddyName}
		</td>
    </tr> 
    <tr>
		<td class='portlet-table-td-left' width='15%'>
			<fmt:message key="portlet.label.subject"/>:
		</td>
		<td class='portlet-table-td-left'>
			<input type='text' id='subject' name='subject' value='' class='portlet-form-input-field' size='40' /> 
		</td>
	</tr>
	<tr>
		<td class='portlet-table-td-left' colspan='2'>
			<fmt:message key="portlet.label.content"/>:
			<input type='radio' id='format' name='format' value='0' checked='checked' class='portlet-form-radio'>
			<fmt:message key="portlet.label.format.html"/></input> 
			<input type='radio' id='format' name='format' value='1' class='portlet-form-radio'>
			<fmt:message key="portlet.label.format.text"/></input> 			
		</td>
	</tr>
	<tr>
		<td class='portlet-table-td-left' colspan='2'>
			<textarea id='content' name='content' class='portlet-form-textarea-field' rows='4' cols='42'></textarea>
		</td>
	</tr>   
    <tr>
    <td class='portlet-table-td-center' colspan='2'>
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:Light.sendMessageAction('${id}','${buddyId}','${popupName}');" class='portlet-form-button'/>
      <input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</form>
</pre>
<textarea id="responseSendMessageAction.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        {if value == 0}
        	Please login first.
        	<input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:Light.login();hidePopupDiv('${popupName}');" class='portlet-form-button'/>
        {elseif value == -1}
        	Error, please try to send message again.
        {else}
        	This Message has been sent successfully.
        {/if}
      </td>
    </tr>   
    {if value != 0} 
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
    {/if}
  </table>
</textarea>
<textarea id="forwardToFriends.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Do you reallly what to forward <b><c:out value="${sessionScope.visitedUser.displayName}"/></b> to your friends?<br/>Click <fmt:message key="portlet.button.ok"/> only if you reallly what to forward <b><c:out value="${sessionScope.visitedUser.displayName}"/></b> to your friends.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:Light.saveForwardToFriends('${id}');hidePopupDiv('${popupName}');" class='portlet-form-button'/>
      <input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="responseForwardToFriends.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        This user has been forwarded to your Friends.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="addToFavorites.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Do you reallly what to add <b><c:out value="${sessionScope.visitedUser.displayName}"/></b> to your favourites?<br/>Click <fmt:message key="portlet.button.ok"/> only if you reallly what to add <b><c:out value="${sessionScope.visitedUser.displayName}"/></b> to your favourites.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:Light.saveAddToFavorites('${id}');hidePopupDiv('${popupName}');" class='portlet-form-button'/>
      <input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="responseAddToFavorites.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        This user has been added to your Favorites.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="blockUser.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Do you reallly what to block <b><c:out value="${sessionScope.visitedUser.displayName}"/></b>?<br/>Click <fmt:message key="portlet.button.ok"/> only if you reallly what to block <b><c:out value="${sessionScope.visitedUser.displayName}"/></b>.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:Light.saveBlockUser('${id}');hidePopupDiv('${popupName}');" class='portlet-form-button'/>
      <input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="responseBlockUser.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        This user has been blocked.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="isBlockUser.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        ${userName} has blocked you, so you cannot Instant Message to ${userName}.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-right' >
      <br/><br/>
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="noIM.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        ${userName} don't accept anyone's Instant Message.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-right' >
      <br/><br/>
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="friendOnlyIM.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        ${userName} only accept friend's Instant Message.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-right' >
      <br/><br/>
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="joinToGroup1.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        You have joined to this group successfully.
      </td>
    </tr>        
  </table>
</textarea>
<textarea id="joinToGroup2.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        You are a member of this group already.<br/><br/>
      </td>
    </tr>        
  </table>
</textarea>
<textarea id="joinToGroup3.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        You need to be approved to become a member of this group.<br/>
        A message has been sent to this group's owner to approve your request.<br/>
      </td>
    </tr>        
  </table>
</textarea>
<textarea id="joinToGroup0.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        This group was deleted by Owner.<br/><br/>
      </td>
    </tr>        
  </table>
</textarea>
<textarea id="joinToGroup9.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Please login first, then click join button again.<br/><br/>
      </td>
    </tr>        
  </table>
</textarea>
<textarea id="inviteToGroup.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        You have invited the following friends to this group:<br/>${friendsName}
      </td>
    </tr> 
    <tr>
    <td class='portlet-table-td-right' >
      <br/><br/>
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>       
  </table>
</textarea>
<textarea id="resign.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Do you reallly what to resign from group <b><c:out value="${sessionScope.visitedGroup.displayName}"/></b>?<br/><br/>Click <fmt:message key="portlet.button.ok"/> only if you reallly what to resign from group <b><c:out value="${sessionScope.visitedUser.displayName}"/></b>.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-right' >
      <br/><br/>
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:resignGroup('${groupId}','${id}');hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="groupPrivacy.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='form_groupPrivacy'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        <input TYPE='checkbox' name='lBulletin' value='1' {if acceptLeaderBulletin == 1}checked='true'{/if}>Check here to display bulletins from this moderator in my bulletin space</input>        
      </td>
    </tr>  
    <tr>
      <td class='portlet-table-td-left' >
        <input TYPE='checkbox' name='mBulletin' value='1' {if acceptMembersBulletin == 1}checked='true'{/if}>Check here to display bulletins from members of this group in my bulletin space</input>
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-right' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:saveGroupPrivacy('groupPrivacy','${groupId}');hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</form>
</textarea>
<textarea id="savePrivacy.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        You have changed your privacy of group<b><c:out value="${sessionScope.visitedGroup.displayName}"/></b>.<br/><br/>
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-right' >
      <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
  </table>
</textarea>
<textarea id="deleteGroupProfile.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<form name='form_groupPrivacy'>
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Do you reallly what to delete the Group <b>${groupName}</b>?<br/>Click <fmt:message key="portlet.button.delete"/> only if you reallly what to delete the Group <b>${groupName}</b>.
      </td>
    </tr>    
    <tr>
    <td class='portlet-table-td-left' >
      <input type='button' value='<fmt:message key="portlet.button.delete"/>' onclick="javascript:confirmDeleteGroupProfile('${groupId}','${id}');hidePopupDiv('${popupName}');" class='portlet-form-button'/>
      <input type='button' value='<fmt:message key="portlet.button.cancel"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
    </td>
    </tr>
    
  </table>
</form>
</textarea>
<textarea id="viewMaxPicture.jst" style="display:none;">
<img id='picture_${pictureId}' src='${url}' style='cursor: url(<%= request.getContextPath() %>/light/images/zoomout.cur), pointer;' align="middle" height='${height}' width='${width}'/>
<br/>
<span style="align:center;margin:10px 0 10px 0;" width='100%'>${caption}</span>
{if tags != ''}
<div id='pptContainer'>
{for tag in tags}
<div style='position:absolute; left: ${tag.positionX}px; top: ${tag.positionY}px;'>
<img id='pin_${tag.id}' class='pptagging' src='<%= request.getContextPath() %>/light/images/ptag.png' style='cursor: pointer;'
 onmouseover="javascript:Light.showPicturePositionTagging(event,'${id}','${pictureId}','${tag.id}',0);"
/>
</div>
{/for}
</div>
{/if}
</textarea>
<textarea id="viewPicture.jst" style="display:none;">
<img id='currentMyPicture_${id}' src='${url}' class='portlet2'  align="middle" height='${height}' width='${width}'/>
<br/>${caption}
{if caption == ""}<br/>{/if}
</textarea>

<textarea id="slidePicture.jst" style="display:none;">
<img id='picture_${pictureId}' src='${url}' class='portlet2'  align="middle" height='${height}' width='${width}'
 onmouseover="javascript:Light.showPPT('${id}','${pictureId}','${tagging}');"
 onmouseout="javascript:Light.hidePPT(event,'${id}','${pictureId}','${tagging}');"
/>
<br/>${caption}
{if caption == ""}<br/>{/if}
</textarea>
<textarea id="refreshPicture.jst" style="display:none;">
<img id='currentMyPicture_${id}'  src='${url}' class='portlet'  align="middle" height='${sheight}' width='${swidth}' style='cursor: url(<%= request.getContextPath() %>/light/images/zoomin.cur), pointer;'   
onclick="javascript:viewMaxPictureAtClient(event,'${id}','${pictureId}','${url}','${caption}','${lwidth}','${lheight}');"
onmouseover="javascript:this.style.border='2px solid #83C2CD';"
onmouseout="javascript:this.style.border='';"
/>
<br/>${caption}
</textarea>
<textarea id="showTheme.jst" style="display:none;">
<span width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<span width='100%' style="clear: both;display: block; text-align:center;">
<img src='<%= request.getContextPath() %>${theme}' align="middle"/>
<br/>${caption}
{if caption == ""}<br/>{/if}
</span>
</textarea>
<textarea id="popItem.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block;
text-align:right;'>
<a href='javascript:;'
onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<table border='0' cellpadding='0' cellspacing='0' >
   <tr>
     <td class='portlet-table-td-left' >
       <br/>
       You have recommended this link to other members successfully.
       <br/>
     </td>
   </tr>
   <tr>
   <td class='portlet-table-td-right' >
     <input type='button' value='<fmt:message key="portlet.button.ok"/>'
onclick="javascript:hidePopupDiv('${popupName}');"
class='portlet-form-button'/>
   </td>
   </tr>
 </table>
</textarea>
<textarea id="forwardToFriend.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block;
text-align:right;'>
<a href='javascript:;'
onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<table border='0' cellpadding='0' cellspacing='0' >
   <tr>
     <td class='portlet-table-td-left' >
       <br/>
       You have fowarded this link to your friends successfully.
       <br/>
     </td>
   </tr>
   <tr>
   <td class='portlet-table-td-right' >
     <input type='button' value='<fmt:message key="portlet.button.ok"/>'
onclick="javascript:hidePopupDiv('${popupName}');"
class='portlet-form-button'/>
   </td>
   </tr>
 </table>
</textarea>
<textarea id="saveToBookmark.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block;
text-align:right;'>
<a href='javascript:;'
onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<table border='0' cellpadding='0' cellspacing='0' >
   <tr>
     <td class='portlet-table-td-left' >
       <br/>
       You have saved this link to your Bookmarks successfully.
       <br/>
     </td>
   </tr>
   <tr>
   <td class='portlet-table-td-right' >
     <input type='button' value='<fmt:message key="portlet.button.ok"/>'
onclick="javascript:hidePopupDiv('${popupName}');"
class='portlet-form-button'/>
   </td>
   </tr>
 </table>
</textarea>
<textarea id="saveToPicture.jst" style="display:none;">
<span title='' width='100%' style='clear: both;display: block;
text-align:right;'>
<a href='javascript:;'
onclick="javascript:hidePopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<table border='0' cellpadding='0' cellspacing='0' >
   <tr>
     <td class='portlet-table-td-left' >
       <br/>
       You have saved this picture to your picture list successfully.
       <br/>
     </td>
   </tr>
   <tr>
   <td class='portlet-table-td-right' >
     <input type='button' value='<fmt:message key="portlet.button.ok"/>' onclick="javascript:hidePopupDiv('${popupName}');" class='portlet-form-button'/>
   </td>
   </tr>
 </table>
</textarea>
<textarea id="addKeywords.jst" style="display:none;">
<form>
<table border='0' cellpadding='0' cellspacing='0' >
<tr>
<td class='portlet-table-td-left'>
<span class='portlet-note'><fmt:message key="portlet.label.addMyKeywords"/></span>
<input type='text' name='keywords' value='' class='portlet-form-input-field' size='80' onchange="Light.saveKeywords('${id}',this.form);" AUTOCOMPLETE='OFF'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<input type='button' name='action' onClick="javascript:Light.saveKeywords('${id}',this.form);" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:hidePopupDiv('${popupName}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
<textarea id="addNotKeywords.jst" style="display:none;">
<form>
<table border='0' cellpadding='0' cellspacing='0' >
<tr>
<td class='portlet-table-td-left'>
<span class='portlet-note'><fmt:message key="portlet.label.addNonKeywords"/></span>
<input type='text' name='words' value='' class='portlet-form-input-field' size='80' onchange="Light.saveNotKeywords('${id}',this.form);" AUTOCOMPLETE='OFF'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<input type='button' name='action' onClick="javascript:Light.saveNotKeywords('${id}',this.form);" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:hidePopupDiv('${popupName}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
<textarea id="addNotWords.jst" style="display:none;">
<form>
<table border='0' cellpadding='0' cellspacing='0' >
<tr>
<td class='portlet-table-td-left'>
<span class='portlet-note'><fmt:message key="portlet.label.addNonWords"/></span>
<input type='text' name='words' value='' class='portlet-form-input-field' size='80' onchange="Light.saveNotWords('${id}',this.form);" AUTOCOMPLETE='OFF'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<input type='button' name='action' onClick="javascript:Light.saveNotWords('${id}',this.form);" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:hidePopupDiv('${popupName}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
<textarea id="showLinkAction.jst" style="display:none;">
<span width='100%' style='clear: both;display: block; text-align:right;'>
<a href='javascript:;' onclick="javascript:hideTopPopupDiv('${popupName}');">
<img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a>
</span>
<table border='0' cellpadding='0' cellspacing='0' width="95%" >
<tr>
<td> 
    <input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','pop','${popupName}');"/>
</td>
</tr>
<tr>
<td>
	<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','forward','${popupName}');"/>
</td>
</tr>
<tr>
<td>
<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','bookmark','${popupName}');"/>
</td>
</tr>
<tr>
<td>
	<a href="http://www.facebook.com/sharer.php?u=${itemLink}&t=news" target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it!" alt="facebook it!" border="0" height='16' width='16'/></a>
</td>
</tr>
<tr>
<td>
<a href='http://digg.com/submit?phase=2&url=${itemLink}&topic=world_news' target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/digg.gif" title="digg it!" alt="digg it!" border="0" height='16' width='16'/></a>
</td>
</tr>
<light:authenticateOwner>   
<tr>
<td> 
    <input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11' onClick="javascript:Light.doLinkAction('${id}','${itemId}','delete','${popupName}');"/>
</td>
</tr>
</light:authenticateOwner>  
</table>
</textarea>
<textarea id="showNewsAction.jst" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width="95%" >   
<tr>
<td> 
    <input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','pop','${popupName}');"/>
</td>
</tr>
<tr>
<td>
	<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','forward','${popupName}');"/>
</td>
</tr>
<tr>
<td>
	<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','bookmark','${popupName}');"/>
</td>
</tr>
<tr>
<td>
	<a href="http://www.facebook.com/sharer.php?u=${itemLink}&t=news" target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it!" alt="facebook it!" border="0" height='16' width='16'/></a>
</td>
</tr>
<tr>
<td>
	<a href='http://digg.com/submit?phase=2&url=${itemLink}&topic=world_news' target='_blank'><img src="<%= request.getContextPath() %>/light/images/digg.gif" title="digg it!" alt="digg it!" border="0" height='16' width='16' onClick="javascript:hidePopupDiv('${popupName}');"/></a>
</td>
</tr>
<light:authorize role="ROLE_ADMIN">   
<tr>
<td> 
    <input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11' onClick="javascript:Light.doLinkAction('${id}','${itemId}','delete','${popupName}');"/>
</td>
</tr>
</light:authorize>  
</table>
</textarea>
<textarea id="showBlogAction.jst" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width="95%" >
<light:authenticateUser>   
<tr>
<td> 
    <input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','pop','${popupName}');"/>
</td>
</tr>
<tr>
<td>
	<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','forward','${popupName}');"/>
</td>
</tr>
</light:authenticateUser>
<tr>
<td>
	<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" height='16' width='16' onClick="javascript:Light.doLinkAction('${id}','${itemId}','bookmark','${popupName}');"/>
</td>
</tr>
<tr>
<td>
	<a href='http://blog.<c:out value="${sessionScope.org.webId}"/>/${uri}/entry/${anchor}' target="_blank"><img src='<%= request.getContextPath() %>/light/images/blog.gif' height='16' width='16' title='<fmt:message key="portlet.label.gotoBlog"/>'/></a>
</td>
</tr>
<tr>
<td>
	<a href="http://www.facebook.com/sharer.php?u=${itemLink}&t=news" target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it!" alt="facebook it!" border="0" height='16' width='16'/></a>
</td>
</tr>
<tr>
<td>
	<a href='http://digg.com/submit?phase=2&url=http://blog.<c:out value="${sessionScope.org.webId}"/>/${uri}/entry/${anchor}&title=${itemTitle}&bodytext=${itemTitle}&topic=world_news' target='_blank'><img src="<%= request.getContextPath() %>/light/images/digg.gif" title="digg it!" alt="digg it!" border="0" height='16' width='16' onClick="javascript:hidePopupDiv('${popupName}');"/></a>
</td>
</tr>
<light:authenticateUser>   
<tr>
<td> 
    <input type="image" title='<fmt:message key="portlet.button.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" height='11' width='11' onClick="javascript:Light.executeRender('${id}','edit','normal','blogId=${itemId}');hidePopupDiv('${popupName}');"/>
</td>
</tr>
<tr>
<td> 
    <input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11' onClick="javascript:Light.doLinkAction('${id}','${itemId}','delete','${popupName}');"/>
</td>
</tr>
</light:authenticateUser>  
</table>
</textarea>

</fmt:bundle>