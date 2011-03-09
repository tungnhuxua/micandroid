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
<textarea id="addContent.view" style="display:none;">
<%@ include file="/WEB-INF/view/default/core/status.jsp"%>
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<span class='portlet-rss' style='padding-top:10px;'>
	<input type='text' name='keyword' class='portlet-form-input-field' size='24' value='${keyword}'/> 
	<input name='action' type='submit' class='portlet-form-button' value='<fmt:message key="portlet.button.search"/>'/>
</span>
<br/>
{if searchLists != ''}
{for portlet in searchLists}
	<span class='portlet-rss'>
	 <a href="javascript:;" onClick="javascript:addContent('${id}','${portlet.name}',event);">
	<img src="{if portlet.icon != ''}{if portlet.needPrefix == 1}<%= request.getContextPath() %>{/if}${portlet.icon}{/if}{if portlet.icon == ''}<%= request.getContextPath() %>/light/images/portlet.gif{/if}" style="border:0px; width:16; height:16;" />
	${portlet.title}</a>
    <light:authorize role="ROLE_ADMIN"> 
     	<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','edit',null,'','edit','','name=${portlet.name}');"><img title='<fmt:message key="portlet.button.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" height='13' width='11'/></a>       
		<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','deleteSearched',null,'${portlet.name}');"><img title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11'/></a>       
   	</light:authorize>
    </span>
{/for}
{/if}
{if searchLists == ''}
  <span class='portlet-rss'>
  	<light:authorize role="ROLE_ADMIN">
  	<a href="javascript:;" onClick="javascript:showAddAllFeed(event,'${id}');">
  	<img src="<%= request.getContextPath() %>/light/images/add.gif" style="border:0px;" align="absmiddle" />
	<fmt:message key="portlet.button.addAllFeed"/></a>
  	<a href="<%= request.getContextPath() %>/opml/allfeeds.opml" target='_blank' style="margin: 0 0 0 10px;"><img src="<%= request.getContextPath() %>/light/images/document.gif" style="border:0px; width:16; height:16;" align="absmiddle" /><fmt:message key="portlet.button.exportToOPML"/></a>
    </light:authorize>    
  </span>
  
  {if show != ''}
    <!-- show : default -->
    {if show == 'default'}
      <span class='portlet-rss'>
        <a href="javascript:;" onClick="javascript:showMyFeed('${id}');">
        <img src="<%= request.getContextPath() %>/light/images/showMod.gif" style="border:0px;width:15px;height:15px;"/>
		<fmt:message key="portlet.button.myFeeds"/></a>
		<a href="javascript:;" onClick="javascript:showAddFeed(event,'${id}');showMyFeed('${id}');" style="margin: 0 0 0 10px;">
    	<img src="<%= request.getContextPath() %>/light/images/add.gif" title='<fmt:message key="portlet.button.addMyFeed"/>' style="border:0px; width:16; height:16;" align="absmiddle" />
    	</a>
      </span>
      <span class='portlet-rss'>
      	<a href="javascript:;" onClick="javascript:showFeatured('${id}');">
        <img src="<%= request.getContextPath() %>/light/images/showMod.gif" style="border:0px;width:15px;height:15px;"/>
		<fmt:message key="portlet.button.featured"/></a>        
      </span>
      <span class='portlet-rss'>
        <a href="javascript:;" onClick="javascript:showCategory('${id}');">
        <img src="<%= request.getContextPath() %>/light/images/showMod.gif" style="border:0px;width:15px;height:15px;"/>
		<fmt:message key="portlet.label.classifiedContent"/></a>               
      </span>

      {for portlet in defaultLists}
        <span class='portlet-rss'>
          <a href='javascript:;' onClick="javascript:addContent('${id}','${portlet.name}',event);">
			<img src="{if portlet.icon != ''}{if portlet.needPrefix == 1}<%= request.getContextPath() %>{/if}${portlet.icon}{/if}{if portlet.icon == ''}<%= request.getContextPath() %>/light/images/portlet.gif{/if}" style="border:0px; width:16; height:16;" />
			${portlet.title}</a>              
			<light:authorize role="ROLE_ADMIN"> 
			    <a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','edit',null,'','edit','','name=${portlet.name}');"><img title='<fmt:message key="portlet.button.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" height='13' width='11'/></a>       
		     	<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','deleteDefault',null,'${portlet.name}');"><img title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11'/></a>       
		   	</light:authorize>
        </span>
	 {/for}
	 {/if}
	 
    <!-- show : myFeed -->
    {if show == 'myFeed'}
      <span class='portlet-rss'>
       	<a href="javascript:;" onClick="javascript:hideMyFeed('${id}');">
	  	<img src="<%= request.getContextPath() %>/light/images/hideMod.gif" style="border:0px;width:15px;height:15px;" align="absmiddle" />
		<fmt:message key="portlet.button.myFeeds"/></a>        
        <a href="<%= request.getContextPath() %>/opml/myfeeds.opml" target='_blank' style="margin: 0 0 0 10px;"><img src="<%= request.getContextPath() %>/light/images/document.gif" style="border:0px; width:16; height:16;" align="absmiddle" /><fmt:message key="portlet.button.exportToOPML"/></a>
      </span>
	  {if myFeedLists == ''}	      
        <fmt:message key="portlet.message.empty"/>
      {/if}
      {for portlet in myFeedLists}      
        <span class='portlet-rss'>
          <a href='javascript:;' onClick="javascript:addContent('${id}','${portlet.name}',event);" style="margin: 0 0 0 20px;">
			<img src="{if portlet.icon != ''}{if portlet.needPrefix == 1}<%= request.getContextPath() %>{/if}${portlet.icon}{/if}{if portlet.icon == ''}<%= request.getContextPath() %>/light/images/portlet.gif{/if}" style="border:0px; width:16; height:16;" />
			${portlet.title}</a>  
			<light:authorize role="ROLE_ADMIN"> 
	     	<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','edit',null,'','edit','','name=${portlet.name}');"><img title='<fmt:message key="portlet.button.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" height='13' width='11'/></a>       
     		</light:authorize> 
     		<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','deleteCategoryFeed',null,'${portlet.name}');"><img title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11'/></a>       			   	             
        </span>
      {/for}
	 {/if}

    <!-- show : featured -->
    {if show == 'featured'}
      <span class='portlet-rss'>
        <a href="javascript:;" onClick="javascript:hideFeatured('${id}');">
	  	<img src="<%= request.getContextPath() %>/light/images/hideMod.gif" style="border:0px;width:15px;height:15px;" align="absmiddle" />
		<fmt:message key="portlet.button.featured"/></a>         
        <light:authenticateUser>  
          <a href="javascript:;" onClick="javascript:showAddFeaturedFeed(event,'${id}');showMyFeed('${id}');" style="margin: 0 0 0 10px;" title='<fmt:message key="portlet.label.addFeaturedFeed"/>'>
		    <img src="<%= request.getContextPath() %>/light/images/add.gif" style="border:0px; width:16; height:16;" align="absmiddle" />
		  </a>          
        </light:authenticateUser>
      </span>
      {if featuredLists == ''}	
        <fmt:message key="portlet.message.empty"/>
      {/if}
	  {for portlet in featuredLists}    
 	     <span class='portlet-rss'>
          <a href='javascript:;' onClick="javascript:addContent('${id}','${portlet.name}',event);" style="margin: 0 0 0 20px;">
			<img src="{if portlet.icon != ''}{if portlet.needPrefix == 1}<%= request.getContextPath() %>{/if}${portlet.icon}{/if}{if portlet.icon == ''}<%= request.getContextPath() %>/light/images/portlet.gif{/if}" style="border:0px; width:16; height:16;" />
			${portlet.title}</a> 
			<light:authorize role="ROLE_ADMIN"> 
	     		<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','edit',null,'','edit','','name=${portlet.name}');"><img title='<fmt:message key="portlet.button.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" height='13' width='11'/></a>       
     			<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','deleteCategoryFeed',null,'${portlet.name}');"><img title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11'/></a>       
	   	  	</light:authorize>             
    	 </span>
       {/for}
	 {/if}

    <!-- show : category -->
    {if show == 'category'}
      <span class='portlet-rss'>
        <a href="javascript:;" onClick="javascript:hideCategory('${id}');">
	  	<img src="<%= request.getContextPath() %>/light/images/hideMod.gif" style="border:0px;width:15px;height:15px;" align="absmiddle" />
		<fmt:message key="portlet.label.classifiedContent"/></a>             
      </span>
	  {if categories == ''}	
        <fmt:message key="portlet.message.empty"/>
      {/if}
	  {for dic in categories} 
	    {if dic.showed == 1}	       	
          <span class='portlet-rss'>
             <a href="javascript:;" onClick="javascript:hideCategoryContent('${id}','${dic.name}');" style="margin: 0 0 0 10px;">
		  	<img src="<%= request.getContextPath() %>/light/images/hideMod.gif" style="border:0px;width:15px;height:15px;" align="absmiddle" />
			${dic.title}</a>             		
            <light:authenticateUser>  
         	 <a href="javascript:;" onClick="javascript:showAddCategoryFeed(event,'${id}','${dic.name}');" style="margin: 0 0 0 10px;" title='<fmt:message key="portlet.label.addCategoryFeed"/>'>
		    	<img src="<%= request.getContextPath() %>/light/images/add.gif" style="border:0px; width:16; height:16;" align="absmiddle" />
		  	 </a>
       		</light:authenticateUser>
          </span>
          {if dic.subCategories == ''}	 
          	  {for portlet in dic.feedLists}   
	            <span class='portlet-rss'>
		          <a href='javascript:;' onClick="javascript:addContent('${id}','${portlet.name}',event);" style="margin: 0 0 0 20px;">
					<img src="{if portlet.icon != ''}{if portlet.needPrefix == 1}<%= request.getContextPath() %>{/if}${portlet.icon}{/if}{if portlet.icon == ''}<%= request.getContextPath() %>/light/images/portlet.gif{/if}" style="border:0px; width:16; height:16;" />
					${portlet.title}</a>              
				  <light:authorize role="ROLE_ADMIN"> 
			     	<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','edit',null,'','edit','','name=${portlet.name}');"><img title='<fmt:message key="portlet.button.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" height='13' width='11'/></a>       
		     		<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','deleteCategoryFeed',null,'${portlet.name}');"><img title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11'/></a>       
			   	  </light:authorize>	  	 		 
	            </span>
	          {/for}
          {/if}

          {if dic.subCategories != ''}
          	{for sub in dic.subCategories}   
          		{if sub.showed == 1}	
          		 	<span class='portlet-rss'>
          		 		<a href="javascript:;" onClick="javascript:hideSubCategory('${id}','${sub.name}');" style="margin: 0 0 0 20px;">
				        <img src="<%= request.getContextPath() %>/light/images/hideMod.gif" style="border:0px;width:15px;height:15px;"/>
						${sub.title}</a>               			            
		                <light:authenticateUser>  
			         	 <a href="javascript:;" onClick="javascript:showAddSubCategoryFeed(event,'${id}','${dic.name}','${sub.name}');" style="margin: 0 0 0 10px;" title='<fmt:message key="portlet.label.addSubCategoryFeed"/>'>
					    	<img src="<%= request.getContextPath() %>/light/images/add.gif" style="border:0px; width:16; height:16;" align="absmiddle" />
					  	 </a>
			       		</light:authenticateUser>
		        	</span>
		        	{for portlet in sub.feedLists}
			            <span class='portlet-rss'>
				          <a href='javascript:;' onClick="javascript:addContent('${id}','${portlet.name}',event);" style="margin: 0 0 0 30px;">
							<img src="{if portlet.icon != ''}{if portlet.needPrefix == 1}<%= request.getContextPath() %>{/if}${portlet.icon}{/if}{if portlet.icon == ''}<%= request.getContextPath() %>/light/images/portlet.gif{/if}" style="border:0px; width:16; height:16;" />
							${portlet.title}</a>              
						  <light:authorize role="ROLE_ADMIN"> 
					     	<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','edit',null,'','edit','','name=${portlet.name}');"><img title='<fmt:message key="portlet.button.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" height='13' width='11'/></a>       
		     				<a href='javascript:;'  onClick="javascript:Light.executeAction('${id}','','deleteSubCategoryFeed',null,'${portlet.name}');"><img title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11'/></a>       
					   	  </light:authorize>	  	 		 
			            </span>			            
			          {/for}
          		{/if}
          		{if sub.showed == 0}	 
	            	<span class='portlet-rss'>
			            <a href="javascript:;" onClick="javascript:showSubCategory('${id}','${sub.name}');" style="margin: 0 0 0 20px;">
				        <img src="<%= request.getContextPath() %>/light/images/showMod.gif" style="border:0px;width:15px;height:15px;"/>
						${sub.title}</a>               			            			            
		        	</span>
		        {/if}
	          {/for}
          {/if}
        {/if}
        {if dic.showed == 0}	 
          <span class='portlet-rss'>
			 <a href="javascript:;" onClick="javascript:showCategoryContent('${id}','${dic.name}');" style="margin: 0 0 0 10px;">
	         <img src="<%= request.getContextPath() %>/light/images/showMod.gif" style="border:0px;width:15px;height:15px;"/>
			 ${dic.title}</a>   
          </span>
       {/if}
	  {/for}
    {/if}
  {/if}
  {/if}
</form>
</textarea>
<textarea id="contentPortlet.edit" style="display:none;">
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter,'view');">
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin:20px 0 10px 10px;'>
<tr>
<td class='portlet-table-td-right' width='30%'><fmt:message key="portlet.label.title"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='label' value='${label}' class='portlet-form-input-field' size='30' /> 
<input type='hidden' name='name' value='${name}'/> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.keywords"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='keywords' value='${keywords}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.owner"/>: </td>
<td class='portlet-table-td-left'>
<select size="1" name='owner' class="portlet-form-select" style="width:200px;">
<c:forEach var="role" items="${roles}">
<option value='<c:out value="${role.id}"/>' 
{if userId == '<c:out value="${role.id}"/>' } selected='selected' {/if}
>
<c:out value="${role.desc}"/></option>
</c:forEach>
<option value='<c:out value="${user.userId}"/>' 
{if userId == '<c:out value="${user.userId}"/>' } selected='selected' {/if}
>
<c:out value="${user.userId}"/></option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.org"/>: </td>
<td class='portlet-table-td-left'>
<select size="1" name='orgId' class="portlet-form-select" style="width:200px;">
<c:forEach var="org" items="${requestScope.orgs}">
<option value='<c:out value="${org.id}"/>' 
{if orgId == '<c:out value="${org.id}"/>' } selected='selected' {/if}
>
<c:out value="${org.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.path"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='path' value='${path}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.icon"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='icon' value='${icon}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.iconCssSprite"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='iconCssSprite' value='${iconCssSprite}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.url"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='url' value='${url}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.tag"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='tag' value='${tag}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.subtag"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='subtag' value='${subtag}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.language"/>: </td>
<td class='portlet-table-td-left'>
<select size="1" name='language' class="portlet-form-select" style="width:200px;">
<option value='all' 
{if language == 'all' } selected='selected' {/if}
>
<fmt:message key="portlet.label.all"/></option>
<c:forEach var="language" items="${requestScope.languages}">
<option value='<c:out value="${language.id}"/>' 
{if language == '<c:out value="${language.id}"/>' } selected='selected' {/if}
>
<c:out value="${language.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.contentSkin"/>: </td>
<td class='portlet-table-td-left'>
<select name="windowSkin" size="1" class="portlet-form-select" style="width:200px;">
<option value=''
{if windowSkin == '' } selected="selected" {/if}
>
</option>
<c:forEach var="windowSkin" items="${requestScope.windowSkins}" varStatus="status">
<option value='<c:out value="${windowSkin.id}"/>'
{if windowSkin == '<c:out value="${windowSkin.id}"/>' } selected="selected" {/if}
>
<c:out value="${windowSkin.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.windowStatus"/>: </td>
<td class='portlet-table-td-left'>
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
<td class='portlet-table-td-right'><fmt:message key="portlet.label.mode"/>: </td>
<td class='portlet-table-td-left'>
<select name="windowMode" size="1" class="portlet-form-select" style="width:200px;">
<option value='0' 
{if windowMode == '0' } selected="selected" {/if}
><fmt:message key="portlet.label.viewMode"/></option>
<option value='1' 
{if windowMode == '1' } selected="selected" {/if}
><fmt:message key="portlet.label.editMode"/></option>
<option value='2' 
{if windowMode == '2' } selected="selected" {/if}
><fmt:message key="portlet.label.editMode"/></option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.type"/>: </td>
<td class='portlet-table-td-left'>
<select name="type" size="1" class="portlet-form-select" style="width:200px;">
<option value='0' 
{if type == '0' } selected="selected" {/if}
><fmt:message key="portlet.label.content.type.0"/></option>
<option value='1' 
{if type == '1' } selected="selected" {/if}
><fmt:message key="portlet.label.content.type.1"/></option>
<option value='2' 
{if type == '2' } selected="selected" {/if}
><fmt:message key="portlet.label.content.type.2"/></option>
<option value='2' 
{if type == '3' } selected="selected" {/if}
><fmt:message key="portlet.label.content.type.3"/></option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.refreshMode"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='refreshMode' value='1' class='portlet-form-radio'
{if refreshMode == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='refreshMode' value='0' class='portlet-form-radio'
{if refreshMode == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.editMode"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='editMode' value='1' class='portlet-form-radio'
{if editMode == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='editMode' value='0' class='portlet-form-radio'
{if editMode == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.helpMode"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='helpMode' value='1' class='portlet-form-radio'
{if helpMode == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='helpMode' value='0' class='portlet-form-radio'
{if helpMode == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.configMode"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='configMode' value='1' class='portlet-form-radio'
{if configMode == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='configMode' value='0' class='portlet-form-radio'
{if configMode == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.allowMinimized"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='minimized' value='1' class='portlet-form-radio'
{if minimized == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='minimized' value='0' class='portlet-form-radio'
{if minimized == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.allowMaximized"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='maximized' value='1' class='portlet-form-radio'
{if maximized == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='maximized' value='0' class='portlet-form-radio'
{if maximized == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.allowJS"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='allowJS' value='1' class='portlet-form-radio'
{if allowJS == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='allowJS' value='0' class='portlet-form-radio'
{if allowJS == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.pageRefresh"/>: </td>
<td class='portlet-table-td-left'> 
<input type='radio' name='pageRefreshed' value='1' class='portlet-form-radio'
{if pageRefreshed == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='pageRefreshed' value='0' class='portlet-form-radio'
{if pageRefreshed == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.autoRefresh"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='autoRefreshed' value='1' class='portlet-form-radio'
{if autoRefreshed == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='autoRefreshed' value='0' class='portlet-form-radio'
{if autoRefreshed == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.periodTime"/>: </td>
<td class='portlet-table-td-left'>
<select name='periodTime' size='1'  class='portlet-form-select' style="width:200px;">
<option value='0'
{if showType == '0' } selected="selected" {/if}
></option>
<c:forEach var="i" begin="10000" end="600000" step="10000">
<option value='<c:out value="${i}" />'
{if showType == '<c:out value="${i}" />' } selected="selected" {/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.show.normal"/>: </td>
<td class='portlet-table-td-left'>
<select name='showNumber' size='1'  class='portlet-form-select' style="width:200px;">
<option value='0'
{if showType == '0' } selected="selected" {/if}
></option>
<c:forEach var="i" begin="1" end="50" step="1">
<option value='<c:out value="${i}" />'
{if showType == '<c:out value="${i}" />' } selected="selected" {/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.showType"/>: </td>
<td class='portlet-table-td-left'>
<select name="showType" size="1" class="portlet-form-select" style="width:200px;">
<option value='0' 
{if showType == '0' } selected="selected" {/if}
><fmt:message key="portlet.label.showType.subject"/></option>
<option value='1' 
{if showType == '1' } selected="selected" {/if}
><fmt:message key="portlet.label.showType.desc"/></option>
<option value='2' 
{if showType == '2' } selected="selected" {/if}
><fmt:message key="portlet.label.showType.both"/></option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.parameter"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='parameter' value='${parameter}' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='60%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='save'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('${id}','view','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</textarea>
</fmt:bundle>