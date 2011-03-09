<%@ page contentType="text/html; charset=gb2312" %>
<br/>
list<br/>
<#if (listData?size>0)>
	<#list listData as info>
	${info.classifyName}. ${info.id} <br/>
	</#list>
</#if>
<br/>