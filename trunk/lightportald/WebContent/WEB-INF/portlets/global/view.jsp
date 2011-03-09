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
<fmt:bundle basename="resourceBundle">
<light:getHost/>
<form action="<portlet:actionURL windowState='MAXIMIZED'><portlet:param name='action' value='find'/></portlet:actionURL>">
	<table border='0' cellpadding='0' cellspacing='0' width='98%' style="margin:10px 20px;padding:5px;" >
		<tr valign='top'>
		<td class='portlet-table-td-left' width='40%'>
		<select name='type' size='1' onchange='javascript:this.form.submit();' class='portlet-form-select' style='width:120px;'>
		<c:forEach var="bean" items="${sessionScope.searchTypes}" >
		<option value='<c:out value="${bean.id}"/>'
		<c:if test='${bean.defaulted == true}'>
		selected="selected"
		</c:if>
		><c:out value="${bean.desc}"/></option>
		</c:forEach>
		</select>
		<c:if test='${sessionScope.criteria != null}'>
			<input type='text' name='keyword' class='portlet-form-input-field' style='width:50%;' value='<c:out value="${sessionScope.criteria.keyword}"/>'
			 onchange="javascript:this.form.submit();" /> 
		</c:if>
		<c:if test='${sessionScope.criteria == null}'>
			<input type='text' name='keyword' class='portlet-form-input-field' style='width:50%;' value=''
			 onchange="javascript:this.form.submit();" /> 
		</c:if>
		<input type='submit' class='portlet-form-button' value='<fmt:message key="portlet.button.go"/>'/>
		</td>		
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.sort"/>:
		<select name='sort' size='1' onchange='javascript:this.form.submit();' class='portlet-form-select' style='width:80px;'>
		<c:forEach var="bean" items="${sessionScope.searchSorts}" >
		<option value='<c:out value="${bean.id}"/>'
		<c:if test='${bean.id == sessionScope.criteria.sort}'>
		selected="selected"
		</c:if>
		><c:out value="${bean.desc}"/></option>
		</c:forEach>
		</select>
		
		<fmt:message key="portlet.label.show"/>:
		<select name='show' size='1' onchange='javascript:this.form.submit();' class='portlet-form-select' style='40'>
		<c:forEach var="bean" items="${sessionScope.searchShows}" >
		<option value='<c:out value="${bean.id}"/>'
		<c:if test='${bean.id == sessionScope.criteria.rowPerPage}'>
		selected="selected"
		</c:if>
		><c:out value="${bean.desc}"/></option>
		</c:forEach>
		</select>
		</td>
		<td class='portlet-table-td-right' >
		<%@ include file="/common/paginator.jsp"%>
		<c:if test='${requestScope.pages <= 1}'>
			<c:if test='${requestScope.total == 1}'>
			<fmt:message key="portlet.label.total"/> <c:out value="${total}"/> <fmt:message key="portlet.label.result"/>.		
			</c:if>
			<c:if test='${requestScope.total > 1}'>
			<fmt:message key="portlet.label.total"/> <c:out value="${total}"/> <fmt:message key="portlet.label.results"/>.		
			</c:if>
		</c:if>	
		</td>		
		</tr>
	</table>
	<%
		int format = org.light.portal.util.PropUtil.getInt("portal.url.format");	
	%>
	<table border='0' cellpadding='0' cellspacing='0' width= '98%' style="padding-top:10px;" >
	<c:forEach var="item" items="${requestScope.items}" varStatus="status">
		<tr valign='top'>
			<td class='portlet-table-td-left' colspan="4">
			<hr/>
			</td>
		</tr>	
		<tr valign='top'>
			<td class='portlet-table-td-left' width= '20%'>
			<c:if test='${item.userData == true}'>
				<a href='
					<%
					if(format == 1){
					%>
						http://<c:out value="${item.uri}"/>.<c:out value="${host}"/>
					<%
					}
					%>
					<%
					if(format != 1){
					%>
						<c:if test='${item.type != "Group"}'>
							http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${item.uri}"/>
						</c:if>
						<c:if test='${item.type == "Group"}'>
							http://<c:out value="${sessionScope.org.groupSpacePrefix}"/><c:out value="${item.uri}"/>
						</c:if>
					<%
					}
					%>			
				' target='_blank'>
				<c:if test='${item.photoUrl == null}'>					
					<c:if test='${item.type != "Group"}'>
					<div style='position:relative;'>
					<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>) no-repeat scroll 0 0; list-style-type: none; width:<c:out value="${sessionScope.org.thumbWidth}"/>px; height:<c:out value="${sessionScope.org.thumbHeight}"/>px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
					  <li>	
					 </li>
					</ul>
					</div>
					</c:if>
					<c:if test='${item.type == "Group"}'>
					<div style='position:relative;'>
					<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultGroupPortrait}"/>) no-repeat scroll 0 0; list-style-type: none; width:81px; height:81px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
					  <li>	
					 </li>
					</ul>
					</div>
					</c:if>
				</c:if>
				<c:if test='${item.photoUrl != null}'>
				<c:if test='${item.httpPhotoUrl == true}'>
					<div style='position:relative;'>
					<ul style='background: transparent url(<c:out value="${item.photoUrl}"/>) no-repeat scroll 0 0; list-style-type: none; width:<c:out value="${sessionScope.org.thumbWidth}"/>px; height:<c:out value="${sessionScope.org.thumbHeight}"/>px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
					  <li>	
					 </li>
					</ul>
					</div>
				</c:if>
				<c:if test='${item.httpPhotoUrl == false}'>
					<div style='position:relative;'>
					<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${item.photoUrl}"/>) no-repeat scroll 0 0; list-style-type: none; width:<c:out value="${sessionScope.org.thumbWidth}"/>px; height:<c:out value="${sessionScope.org.thumbHeight}"/>px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
					  <li>	
					 </li>
					</ul>
					</div>
				</c:if>
				</c:if>	
				</a>				
			</c:if>
						
			</td>
			<td class='portlet-table-td-left' width= '20%'>
			<span class="portlet-rss" > 
			<c:if test='${item.userData == true}'>
				<fmt:message key="portlet.label.name"/>: 
				<a href='
					<%
					if(format == 1){
					%>
						http://<c:out value="${item.uri}"/>.<c:out value="${host}"/>
					<%
					}
					%>
					<%
					if(format != 1){
					%>
						<c:if test='${item.type != "Group"}'>
							http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${item.uri}"/>
						</c:if>
						<c:if test='${item.type == "Group"}'>
							http://<c:out value="${sessionScope.org.groupSpacePrefix}"/><c:out value="${item.uri}"/>
						</c:if>
					<%
					}
					%>
					' target='_blank'>
					<c:out value="${item.name}"/>
				</a>
				</span>
				<span class="portlet-note">
				<fmt:message key="portlet.label.recently.visit"/> <c:out value="${item.date}"/>
			</c:if>
			<c:if test='${item.userData == false}'>
				<a href='<c:out value="${item.link}"/>' taget='_blank'><c:out value="${item.name}"/></a>
			</c:if>
			</span> 				
			</td>
			<td class='portlet-table-td-left' width= '40%'>
				<c:if test='${item.showDetail == true}'>
				<span class="portlet-rss" > 
				<c:if test='${item.type == "All"}'>
				<c:out value="${item.type}"/>: 
				</c:if>
				<a href='<c:out value="${item.link}"/>' target='_blank'>
					<c:out value="${item.detail}" escapeXml="false"/>
				</a>
				</span>
				</c:if>
			</td>
			<c:if test='${item.userData == true}'>				
			<c:if test='${item.type != "Group"}'>
			<td class='portlet-table-td-left' width= '20%'>
			<span class="portlet-rss" > 
			<a href='javascript:void(0)' onclick="javascript:Light.showSendMessage(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${item.userId}"/>','<c:out value="${item.name}"/>');" ><img src='<%= request.getContextPath() %>/light/images/inbox.gif' style='border: 0px;' height='16' width='16' align="top"/><fmt:message key="portlet.label.sendMessage"/></a>
			<br/>
			<a href='javascript:void(0)' onclick="javascript:Light.showAddToFriend(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${item.userId}"/>','<c:out value="${item.name}"/>');" ><img src='<%= request.getContextPath() %>/light/images/newFriend.gif' style='border: 0px;' height='16' width='16' align="top"/><fmt:message key="portlet.label.addToFriend"/></a>			
			</span>
			</td>	
			</c:if>		
			<c:if test='${item.type == "Group"}'>
			<td class='portlet-table-td-left' width= '20%'>
			<span class="portlet-rss" > 
			<a href='javascript:void(0)' onclick="javascript:joinToGroup(event,'<c:out value="${item.userId}"/>','<c:out value="${requestScope.responseId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/group.gif' style='border: 0px;' height='16' width='16' align="top"/><fmt:message key="portlet.label.openJoin"/></a>
			<br/>
			</span>
			</td>	
			</c:if>							
			</c:if>
		</tr>
		</c:forEach>
		<tr valign='top'>
			<td class='portlet-table-td-left' colspan="4">
			</td>
		</tr>	
	</table>

	<table border='0' cellpadding='0' cellspacing='0' width= '98%' >		
		<%@ include file="/common/paginator.jsp"%>
		<c:if test='${requestScope.pages <= 1}'>
		<tr valign='top'>
			<td class='portlet-table-td-right'>
			<c:if test='${requestScope.total == 1}'>
			<fmt:message key="portlet.label.total"/> <c:out value="${total}"/> <fmt:message key="portlet.label.result"/>.		
			</c:if>
			<c:if test='${requestScope.total > 1}'>
			<fmt:message key="portlet.label.total"/> <c:out value="${total}"/> <fmt:message key="portlet.label.results"/>.		
			</c:if>
			</td>
		</tr>	
		</c:if>				
	</table>
</form>
</fmt:bundle>
</body>
</html>