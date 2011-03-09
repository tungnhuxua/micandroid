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
<script language="JavaScript">

  keyDownWiki  = function (e, id) {  
	  var KeyID;
	  if (window.event) {	
		keyID = window.event.keyCode;
	  } else {
	    keyID = e.which;
	  } 
	  if ( keyID == 13){ 	   
	      submitWiki(id);    
	  }
	  return !(keyID == 13);
  }
  
  submitWiki = function (id){
    var key = document.forms['form_'+id]['psWiki'].value;
    if(key != null && key.length > 0)
 	    window.open("http://en.wikipedia.org/wiki/"+key); 
 	else
 	    window.open("http://en.wikipedia.org/wiki/Special:Search?search="+key); 
  } 
   submitWikiSearch = function (id){
    var key = document.forms['form_'+id]['psWiki'].value;
    window.open("http://en.wikipedia.org/wiki/Special:Search?search="+key); 
  } 

</script>
<fmt:bundle basename="resourceBundle">
<form name="form_<c:out value="${requestScope.responseId}"/>">
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.from"/>
</td>
<td class='portlet-table-td-left'>
<input type='text' name='psWiki' class='portlet-form-input-field' size='30'
	 onkeypress="return keyDownWiki(event,'<c:out value="${requestScope.responseId}"/>');" /> 
</td>
</tr>
<tr>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<input name='Submit' type='button' class='portlet-form-button' value='<fmt:message key="portlet.button.go"/>' 
	 onclick="javascript:submitWiki('<c:out value="${requestScope.responseId}"/>');" />

<input name='Submit' type='button' class='portlet-form-button' value='<fmt:message key="portlet.button.search"/>' 
	 onclick="javascript:submitWikiSearch('<c:out value="${requestScope.responseId}"/>');" />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>