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
<form action="<portlet:actionURL windowState='MAXIMIZED'><portlet:param name='action' value='find'/></portlet:actionURL>">
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<b><fmt:message key="portlet.title.findMember"/></b>
</td>
</tr>
<c:if test='${sessionScope.usResult == null}'>
<tr>
<td class='portlet-table-td-left'>
<br/>
<input type='text' name='keyword' class='portlet-form-input-field-hint' size='32' value='' 
	 onchange="javascript:this.form['input'].value='1';this.form.submit();" /> 
<input type='hidden' name='input'  value ='0'/>
<input type='submit' class='portlet-form-button' value='<fmt:message key="portlet.button.go"/>'/>
</td>
</tr>
</c:if>
<c:if test='${sessionScope.usResult != null}'>
<tr>
<td class='portlet-table-td-left'>
<br/>
<c:if test='${sessionScope.criteria != null}'>
<input type='text' name='keyword' class='portlet-form-input-field-hint' size='64' value='<c:out value="${sessionScope.criteria.keyword}"/>'
	 onchange="javascript:this.form.submit();" /> 
</c:if>
<c:if test='${sessionScope.criteria == null}'>
<input type='text' name='keyword' class='portlet-form-input-field-hint' size='64' value=''
	 onchange="javascript:this.form.submit();" /> 
</c:if>
<td class='portlet-table-td-left'>
<br/>
<input type='submit' class='portlet-form-button' value='<fmt:message key="portlet.button.go"/>'/>
<input type='button' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
<td class='portlet-table-td-left'>
<br/>
<input type='radio' name='type'  value='1' class='portlet-form-radio' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','maximized','viewType=0');"><fmt:message key="portlet.label.normalView"/></input> 
<input type='radio' name='type'  value='1' class='portlet-form-radio' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','maximized','viewType=1');"><fmt:message key="portlet.label.slideView"/></input> 
<input type='radio' name='type'  value='1' class='portlet-form-radio' checked='checked' ><fmt:message key="portlet.label.flowView"/></input> 
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width= '95%' >
	<tr valign='top'>
		<td class='portlet-table-td-center' colspan="3">
			<div style="width:100%;height:100px;margin:auto;margin-top:20px;">
				<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
					<tr valign='top'>
					<td class='portlet-table-td-left' width="40%">
						<c:forEach var="lmember" items="${requestScope.leftPage}" varStatus="status">
							<div style="margin-left:<c:out value="${ 10 + status.index * 50}"/>px;position:absolute;width:100px;background:#ffffff;cursor:pointer;cursor:hand;"
							onclick="javascript:changeNumber('<c:out value="${requestScope.responseId}"/>',this,<c:out value="${lmember.number}"/>,<c:out value="${current.number}"/>,2);"
							>
							<c:if test='${lmember.photoUrl == null}'>
								<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
							</c:if>
							<c:if test='${lmember.photoUrl != null}'>
								<img src='<%= request.getContextPath() %><c:out value="${lmember.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
							</c:if>	
							<br/>
							<span class="portlet-rss" > 
								<a href='javascript:void(0)'>
									<c:out value="${lmember.name}"/>
								</a>
							</span>
							<br/>							
							</div>
						</c:forEach>
					</td>
					<td class='portlet-table-td-center' width="20%">
						<c:if test='${current != null}'>
							<c:if test='${current.photoUrl == null}'>
								<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${current.uri}"/>' target='_blank'>
								<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
								</a>
							</c:if>
							<c:if test='${current.photoUrl != null}'>
								<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${current.uri}"/>' target='_blank'>
								<img src='<%= request.getContextPath() %><c:out value="${current.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
								</a>
							</c:if>	
							<br/>
							<span class="portlet-rss" style="text-align:center;" > 
								<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${current.uri}"/>' target='_blank'>
									<c:out value="${current.name}"/>
								</a>
							</span>						
						</c:if>
					</td>
					<td style="text-align:right;" width="40%">
						<c:forEach var="rmember" items="${requestScope.rightPage}" varStatus="status">
							<div style="margin-left:<c:out value="${10 + (rmember.number - current.number) * 50}"/>px;position:absolute;width:100px;background:#ffffff;cursor:pointer;cursor:hand;"
							onclick="javascript:changeNumber('<c:out value="${requestScope.responseId}"/>',this,<c:out value="${rmember.number}"/>,<c:out value="${current.number}"/>,2);"
							>
								<c:if test='${rmember.photoUrl == null}'>
										<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
								</c:if>
								<c:if test='${rmember.photoUrl != null}'>
										<img src='<%= request.getContextPath() %><c:out value="${rmember.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
								</c:if>	
								<br/>
								<span class="portlet-rss" style="text-align:right;"> 
									<a href='javascript:void(0)' >
										<c:out value="${rmember.name}"/>
									</a>
								</span>
								<br/>								
							</div>
						</c:forEach>
					</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
	
	<tr valign='top'>
		<td class='portlet-table-td-left' colspan="3">
		<br/><br/><br/><br/><br/><br/>
		</td>
	</tr>	
	<c:if test='${requestScope.pages > 1}'>
	<tr valign='top'>
		<td class='portlet-table-td-center' colspan="3">
			<div style="width:800px;height:13px;margin:auto;text-align:left;">				
					<div id="sliderBar" style="position:absolute;width:38px;height:13px;background: transparent url(<%= request.getContextPath() %>/light/images/leftArrow.gif) no-repeat scroll left top;margin-top:-1px;margin-left:1px;cursor:pointer;cursor:hand;text-align:left;"
						 onmousedown="javascript:moveSliderBarBegin(event,$('slider'),$('sliderBar'),'<c:out value="${requestScope.responseId}"/>','<c:out value="${requestScope.page}"/>','<c:out value="${requestScope.pages}"/>');"
						 onmouseup="javascript:moveSliderBarEnd($('slider'),$('sliderBar'),'<c:out value="${requestScope.responseId}"/>','<c:out value="${requestScope.page}"/>','<c:out value="${requestScope.pages}"/>',2);"
						 onmouseout="javascript:moveSliderBarEnd($('slider'),$('sliderBar'),'<c:out value="${requestScope.responseId}"/>','<c:out value="${requestScope.page}"/>','<c:out value="${requestScope.pages}"/>',2);"
					/>	
					<%  int sliderLeft= 30;
						int sliderRight = 650;
						int cpage = (Integer)request.getAttribute("page");
						int tpages = (Integer)request.getAttribute("pages");
						int left = 0;
						if(cpage == 0)
							left = sliderLeft;
						else if(cpage == (tpages - 1)) 
							left = sliderRight;
						else{
							left = cpage * Math.round((sliderRight - sliderLeft) / tpages);
						}
						if(left < sliderLeft) left = sliderLeft;	
						if(left > sliderRight) left = sliderRight;	
					%>				
					<div style="position:absolute;left:8px;width:700px;height:11px;border-bottom:1px solid #B5B5B5;border-top:1px solid #B5B5B5;">					
						<div id="slider" style="position:absolute;left:<%= left %>px;width:42px;height:13px;background: transparent url(<%= request.getContextPath() %>/light/images/slider.gif) no-repeat scroll left top;margin-top:-1px;cursor:pointer;cursor:hand;"
							  onmousedown="javascript:moveSliderBegin(event,this);"
							  onmousemove="javascript:moveSlider(event,this);"
							  onmouseup="javascript:moveSliderEnd(this,'<c:out value="${requestScope.responseId}"/>','<c:out value="${requestScope.page}"/>','<c:out value="${requestScope.pages}"/>',2);"
							  onmouseout="javascript:moveSliderEnd(this,'<c:out value="${requestScope.responseId}"/>','<c:out value="${requestScope.page}"/>','<c:out value="${requestScope.pages}"/>',2);">				
						</div>
					</div>
					<div style="position:absolute;left:700px;width:38px;height:13px;background: transparent url(<%= request.getContextPath() %>/light/images/rightArrow.gif) no-repeat scroll left top;margin-top:-1px;cursor:pointer;cursor:hand;margin-right:-12px;" 
					/>	
			</div>
		</td>
	</tr>	
	</c:if>		
	<tr valign='top'>
		<td class='portlet-table-td-center' colspan="3">
		<c:if test='${sessionScope.usResult.total > 0}'>
		<label class='currentpage'><c:out value="${requestScope.page + 1}"/></label> - 
		</c:if>
		<c:out value="${sessionScope.usResult.total}"/> <fmt:message key="portlet.label.results"/>.
		</td>
	</tr>			
</table>

</c:if>
</form>
</fmt:bundle>
</body>
</html>