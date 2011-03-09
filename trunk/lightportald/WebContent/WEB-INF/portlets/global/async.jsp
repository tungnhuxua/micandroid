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
<%@ page import="java.util.*,org.light.portal.*,org.light.portal.util.*" %>
<%
java.util.Locale currentLocale = Context.getInstance().getLocale(request);
List<LabelBean> types = new LinkedList<LabelBean>();
types.add(new LabelBean("all",MessageUtil.getMessage("portlet.label.all",currentLocale)));
String value=PropUtil.getString("portlet.search.list");
String[] lists = value.split(";");
int i = 0;
for(String list : lists){
	String[] unit = list.split(",");							
	if(unit.length >= 4){
		if(i == 0)
			types.add(new LabelBean(unit[0],MessageUtil.getMessage(unit[3],currentLocale),true));
		else
			types.add(new LabelBean(unit[0],MessageUtil.getMessage(unit[3],currentLocale)));
	}
	i++;
}
request.setAttribute("searchTypes",types);

List<LabelBean> sorts = new LinkedList<LabelBean>();
String value2=PropUtil.getString("search.criteria.sort");
String[] lists2 = value2.split(";");
for(String list : lists2){
	String[] unit = list.split(":");
	String sort = (unit.length > 1) ? unit[1] : "";
	String desc = unit[0];
	sorts.add(new LabelBean(sort,desc));
}
request.setAttribute("searchSorts",sorts);

List<LabelBean> shows = new LinkedList<LabelBean>();
String value3=PropUtil.getString("search.criteria.page.rows");
String[] units = value3.split(",");
for(String unit : units){
	shows.add(new LabelBean(unit,unit));
}
request.setAttribute("searchShows",shows);
%>
<textarea id="globalSearch.view" style="display:none;">
<form name='form_${id}' action="javascript:;">
	<table border='0' cellpadding='0' cellspacing='0' width='100%' style="margin:10px 20px;padding:5px;" >
		<tr valign='top'>
		<td class='portlet-table-td-left' width='100%'>
		<select name='type' size='1' onchange="javascript:Light.executeAction('${id}',this.form,'',null,null,'VIEW','normal');" class='portlet-form-select' style='width:80px;'>
		<c:forEach var="bean" items="${searchTypes}" >
		<option value='<c:out value="${bean.id}"/>'
		{if type == '<c:out value="${bean.id}"/>'}
		selected="selected"
		{/if}
		><c:out value="${bean.desc}"/></option>
		</c:forEach>
		</select>
		<input type='text' name='keyword' class='portlet-form-input-field' style='width:50%;' value='${keyword}'
			 onchange="javascript:Light.executeAction('${id}',this.form,'',null,null,'VIEW','normal');" /> 		
		<input type='button' onclick="javascript:Light.executeAction('${id}',this.form,'',null,null,'VIEW','normal');" class='portlet-form-button' value='<fmt:message key="portlet.button.go"/>'/>
		
		<label><fmt:message key="portlet.label.sort"/>:</label>
		<select name='sort' size='1' onchange="javascript:Light.executeAction('${id}',this.form,'',null,null,'VIEW','normal');" class='portlet-form-select' style='width:80px;'>
		<c:forEach var="bean" items="${searchSorts}" >
		<option value='<c:out value="${bean.id}"/>'
		{if sort == '<c:out value="${bean.id}"/>'}
		selected="selected"
		{/if}
		><c:out value="${bean.desc}"/></option>
		</c:forEach>
		</select>
		
		<label><fmt:message key="portlet.label.show"/>:</label>
		<select name='show' size='1' onchange="javascript:Light.executeAction('${id}',this.form,'',null,null,'VIEW','normal');" class='portlet-form-select' style='40'>
		<c:forEach var="bean" items="${searchShows}" >
		<option value='<c:out value="${bean.id}"/>'
		{if show == '<c:out value="${bean.id}"/>'}
		selected="selected"
		{/if}
		><c:out value="${bean.desc}"/></option>
		</c:forEach>
		</select>
		</td>
		<tr valign='top'>
		<td class='portlet-table-td-right' >		
			<div class="portlet-rss" style="clear:both;text-align:right;padding:0 25px 0 0;">
			{if pages > 1 }			 	
			 	    <label>(<fmt:message key="portlet.label.Results"/> ${start + 1 } -  ${end} <fmt:message key="portlet.label.of"/> ${total})</label>
					{if page > 1 }
						<a href="javascript:;" onclick="javascript:Light.executeRender('${id}','','','page=${page - 1};type=${type}');"><img src='<%= request.getContextPath() %>/light/images/previous.gif' width='16px' height='16px' title='<fmt:message key="portlet.label.previous"/>' alt='' /></a>						
					{/if}
					{for number in pageNumbers}
						{if number != page}
							<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','page=${number};pages=${pages};type=${type}');" >${number}</a>
						{/if}
						{if number == page}
							<label class='currentpage'>${number}</label>
						{/if}
					{/for}
					{if page < pages}
						<a href="javascript:;" onclick="javascript:Light.executeRender('${id}','','','page=${page + 1};type=${type}');"><img src='<%= request.getContextPath() %>/light/images/next.gif' width='16px' height='16px' title='<fmt:message key="portlet.label.next"/>' alt='' /></a>						
					{/if}				
			{else}
				<label><fmt:message key="portlet.label.total"/> ${total}
				{if total == 1 }
			  		<fmt:message key="portlet.label.result"/>		
				{else}
					<fmt:message key="portlet.label.results"/>	
				{/if}.			
			{/if}
			</div>
		</td>		
		</tr>
	</table>
	
	<table border='0' cellpadding='0' cellspacing='0' width= '98%' style="padding-top:10px;" >
	{for item in items}
		<tr valign='top'>
			<td class='portlet-table-td-left' colspan="4">
			<hr/>
			</td>
		</tr>	
		<tr valign='top'>
			<td class='portlet-table-td-left' width= '20%'>
			{if item.userData }
				<a href='${item.url}' target='_blank'>
				{if item.photoUrl }	
					{if item.httpPhotoUrl }
						<div style='position:relative;'>
						<ul style='background: transparent url(${item.photoUrl}) no-repeat scroll 0 0; list-style-type: none; width:<c:out value="${sessionScope.org.thumbWidth}"/>px; height:<c:out value="${sessionScope.org.thumbHeight}"/>px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
						  <li>	
						 </li>
						</ul>
						</div>
					{else}
						<div style='position:relative;'>
						<ul style='background: transparent url(<%= request.getContextPath() %>${item.photoUrl}) no-repeat scroll 0 0; list-style-type: none; width:<c:out value="${sessionScope.org.thumbWidth}"/>px; height:<c:out value="${sessionScope.org.thumbHeight}"/>px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
						  <li>	
						 </li>
						</ul>
						</div>
					{/if}								
				{else}
					{if item.type != "Group" }
					<div style='position:relative;'>
					<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>) no-repeat scroll 0 0; list-style-type: none; width:<c:out value="${sessionScope.org.thumbWidth}"/>px; height:<c:out value="${sessionScope.org.thumbHeight}"/>px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
					  <li>	
					 </li>
					</ul>
					</div>
					{else}
					<div style='position:relative;'>
					<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultGroupPortrait}"/>) no-repeat scroll 0 0; list-style-type: none; width:81px; height:81px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
					  <li>	
					 </li>
					</ul>
					</div>
					{/if}
				{/if}
				</a>				
			{/if}						
			</td>
			<td class='portlet-table-td-left' width= '60%'>
			<span class="portlet-rss" > 
			{if item.showDetail }				
				{if type == "all" }
					${item.type}: 
				{/if}
				<a href='${item.link}' target='_blank'>
					${item.detail}
				</a>
				<br/>
				<span class="portlet-note">
				${item.date} 					
				<fmt:message key="portlet.label.postedBy"/>: 
				<a href='${item.url}' target='_blank'>
					${item.name}
				</a>
				</span>
			{else} 
				{if item.userData }
					<a href='${item.url}' target='_blank'>
						${item.name}
					</a>
					<br/>
					<span class="portlet-note">
					<fmt:message key="portlet.label.recently.visit"/> ${item.date}
					</span>
				{else}
					<a href='${item.link}' taget='_blank'>${item.name}</a>
				{/if}
			{/if} 
			</span> 				
			</td>
							
			{if item.userData }		
			{if item.type != "Group" }
			<td class='portlet-table-td-left' width= '20%'>
			<span class="portlet-rss" > 
			<a href='javascript:;' onclick="javascript:Light.showSendMessage(event,'${id}','${item.id}','${item.name}');" ><img src='<%= request.getContextPath() %>/light/images/inbox.gif' height='16' width='16' align="top"/><fmt:message key="portlet.label.sendMessage"/></a>
			<br/>
			<a href='javascript:;' onclick="javascript:Light.showAddToFriend(event,'${id}','${item.id}','${item.name}');" ><img src='<%= request.getContextPath() %>/light/images/newFriend.gif' height='16' width='16' align="top"/><fmt:message key="portlet.label.addToFriend"/></a>			
			</span>
			</td>	
			{else}
			<td class='portlet-table-td-left' width= '40%'>
			<span class="portlet-rss" > 
			<a href='javascript:;' onclick="javascript:joinToGroup(event,'${item.id}','${id}');" ><img src='<%= request.getContextPath() %>/light/images/group.gif' height='16' width='16' align="top"/><fmt:message key="portlet.label.openJoin"/></a>
			<br/>
			</span>
			</td>	
			{/if}						
			{/if}	
		</tr>
		{/for}
		
	</table>

	<div class="portlet-rss" style="clear:both;text-align:right;padding:0 25px 0 0;">
		{if pages > 1 }			 	
		 	    <label>(<fmt:message key="portlet.label.Results"/> ${start + 1 } -  ${end} <fmt:message key="portlet.label.of"/> ${total})</label>
				{if page > 1 }
					<a href="javascript:;" onclick="javascript:Light.executeRender('${id}','','','page=${page - 1}');;type=${type}"><img src='<%= request.getContextPath() %>/light/images/previous.gif' width='16px' height='16px' title='<fmt:message key="portlet.label.previous"/>' alt='' /></a>						
				{/if}
				{for number in pageNumbers}
					{if number != page}
						<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','page=${number};pages=${pages};type=${type}');" >${number}</a>
					{/if}
					{if number == page}
						<label class='currentpage'>${number}</label>
					{/if}
				{/for}
				{if page < pages}
					<a href="javascript:;" onclick="javascript:Light.executeRender('${id}','','','page=${page + 1};type=${type}');"><img src='<%= request.getContextPath() %>/light/images/next.gif' width='16px' height='16px' title='<fmt:message key="portlet.label.next"/>' alt='' /></a>						
				{/if}				
		{else}
			<label><fmt:message key="portlet.label.total"/> ${total}
			{if total == 1 }
		  		<fmt:message key="portlet.label.result"/>		
			{else}
				<fmt:message key="portlet.label.results"/>	
			{/if}.			
		{/if}
	</div>
</form>
</div>
</textarea>

</fmt:bundle>