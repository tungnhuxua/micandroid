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

<textarea id="forumCategory.edit" style="display:none;">
<form name='form_${id}' action="javascript:;" style='margin:20px 0;'>
{if success }
<div class='portlet-msg-success' >${success}</div>
{/if}
{if error }
<div class='portlet-msg-error' >${error}</div>
{/if}
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-right' width='40%'>
<fmt:message key="portlet.label.category"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='category' value='${forum.category}' class='portlet-form-input-field' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.desc"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='categoryDesc' value='${forum.categoryDesc}' class='portlet-form-input-field' /> 
</td>
</tr>
<tr>
<td></td>
<td class='portlet-table-td-left' style='padding:10;'>
<input type='button' onClick="javascript:document.resetLastAction='1';Light.executeAction('${id}',this.form,'category',null,null,'VIEW','normal');" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' onClick="javascript:Light.executeRender('${id}','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>

<textarea id="forum.edit" style="display:none;">
<form name='form_${id}' action="javascript:;" style='margin:20px 0;'>
{if success }
<div class='portlet-msg-success' >${success}</div>
{/if}
{if error }
<div class='portlet-msg-error' >${error}</div>
{/if}
<input type='hidden' name='categoryId' value='${forum.categoryId}' /> 
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-right' width='40%'>
<fmt:message key="portlet.label.forum"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='forum' value='${forum.name}' class='portlet-form-input-field'/> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.desc"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='forumDesc' value='${forum.desc}' class='portlet-form-input-field'/> 
</td>
</tr>
<tr>
<td></td>
<td class='portlet-table-td-left' style='padding:10;'>
<input type='button' onClick="javascript:document.resetLastAction='1';Light.executeAction('${id}',this.form,'forum',null,null,'VIEW','normal');" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' onClick="javascript:Light.executeRender('${id}','view','normal','categoryId=${forum.categoryId}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>

<pre id="forumPost.edit" style="display:none;">
<form name='form_${id}' action="javascript:;" style='margin:20px 0;'>
{if success }
<div class='portlet-msg-success' >${success}</div>
{/if}
{if error }
<div class='portlet-msg-error' >${error}</div>
{/if}
<table border='0' cellpadding='0' cellspacing='10' width='98%'>
<input type='hidden' name='categoryId' value='${forum.categoryId}' /> 
<input type='hidden' name='forumId' value='${forum.forumId}' /> 
<input type='hidden' name='topicName' value='${forum.topicName}' /> 
<tr>
<td class='portlet-table-td-left'>
<b>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId}');" >${forum.categoryName}</a>
->
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${forum.forumId}');" >${forum.forumName}</a>
{if forum.topicId }
->
${forum.topicName}
{/if}
</b>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
{if forum.topicId }
<input type='hidden' name='topicId' value='${forum.topicId}' /> 
<input type='hidden' name='newTopic' value='0' />
{else}
<fmt:message key="portlet.label.topic"/>:
<input type='text' name='topic' value='${forum.topic}' class='portlet-form-input-field' style='width:80%' /> 
<input type='hidden' name='newTopic' value='1' /> 
{/if}
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.content"/>:
<input type='radio' name='format' value='0' class='portlet-form-radio'
{if fourm.format } checked="checked" {/if}
>
<fmt:message key="portlet.label.format.html"/></input> 
<input type='radio' name='format' value='1' class='portlet-form-radio'
{if !fourm.format } checked="checked" {/if}
>
<fmt:message key="portlet.label.format.text"/></input> 
<light:authorizeEditor>
<input type='checkbox' name='htmlEditor' value='1' onchange="javascript:Light.executeAction('${id}','','','','','edit','maximized','categoryId=${forum.categoryId};forumId=${forum.forumId}');" 
{if forum.showHtmlEditor }
checked="checked"
{/if}
><fmt:message key="portlet.label.htmlEditor"/></input>
</light:authorizeEditor>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='content' class='portlet-form-textarea-field' rows='10' style='width:98%'>{if forum.content }${forum.content}{/if}</textarea>
</td>
</tr> 
<tr>
<td class='portlet-table-td-right'>
{if forum.topicId }
<input type='button' onClick="document.resetLastAction='1';Light.executeAction('${id}',this.form,'reply',null,null,'VIEW','normal');" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
<input type='button' onClick="javascript:Light.executeRender('${id}','view','normal','categoryId=${forum.categoryId};forumId=${forum.forumId};topicId=${forum.topicId}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
{else}
<input type='button' onClick="document.resetLastAction='1';Light.executeAction('${id}',this.form,'save',null,null,'VIEW','normal');" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' onClick="javascript:Light.executeRender('${id}','view','normal','categoryId=${forum.categoryId};forumId=${forum.forumId}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
{/if}
</td>
</tr>
</table>
</form>
</pre>

<textarea id="forum.help" style="display:none;">
<table border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="portlet-table-td-left">
<h1>Forum Help Page</h1>
<p>
This page provides answers to user's frequently asked questions about the forums. 
If you are encountering problems, check to see if a solution is provided below. 
</p>
<HR/><h2>Frequently Asked Questions</h2>
<ul>
<li><a href="#html">Can I use HTML codes in my message?</a>
</ul>

<HR/><a name="html"><b>Can I use HTML codes in my message?</b></a>
<p>The forums are set up to allow html codes in the message body.
</p>
<p>
If you do use a code to create a effect, it's important to turn it off as well.
</P>
<p>
A mistake can screw up an entire thread (or page.)
</P>
</td>
</tr>
</table>
</textarea>
</fmt:bundle>