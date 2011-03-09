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
<div style='margin:20px 5px 20px 5px;'>
<p class='text'>
<FONT color='#ff6600'><b>Read through our Frequently Asked Questions and move your mouse on them to find the answers you're looking for!</b></FONT>
</p>

<UL>

<LI>
<p class='text'>
<span onmouseover="javascript:showDesc(event,'Yes! Every feature and function you currently see on the site is FREE. <c:out value="${sessionScope.org.webId}"/> is supported solely by advertising. In the future, <c:out value="${sessionScope.org.webId}"/> may add paid Premium Services, but all the features and functions you have currently been enjoying on the <c:out value="${sessionScope.org.webId}"/> site will always remain FREE!','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">Is <c:out value="${sessionScope.org.webId}"/> free?</span>
</p>
</LI>

<LI>
<p class='text'>
<span onmouseover="javascript:showDesc(event,'After login, click on the page which you want to show it on your profile URL, click on the options menus, then click on manage page on the header, select page privacy setting as show on my space, then save. This page will be showed on your profile instead of default one, you also can add multiple pages to your profile by repeating these steps.','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">How to change my default profile page?</span>
</p>
</LI>

<LI>
<p class='text'>
<span onmouseover="javascript:showDesc(event,'you can visit to this user space, click on Add to friend link on the Contact me Widget, then system will send an internal messasge to this user, after this user approve your request, you two will become friends.','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">How to add a user to my friend list?</span>
</p>
</LI>

<LI>
<p class='text'>
<span onmouseover="javascript:showDesc(event,'First, you have to maximize my picture widget window, the configuraton funtions are only available on maximized window status. You can configure photos one by one by clicking configure current photo button, or click on configure all viewed photos button, you can configure all viewed photos, they are rendered from memory, so it is faster and more convenient. It allow users to add multiple tags using comma, then photos will be grouped by tag, so like categories.','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">How to configure pictures, such as add caption, categories, privacy setting?</span>
</p>
</LI>

</UL>

<div style='text-align:right'>
<input type='button' name='action' onClick="javascript:Light.closePortlet('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.close"/>' class='portlet-form-button' />
</div>

</div>
</fmt:bundle>
</body>
</html>