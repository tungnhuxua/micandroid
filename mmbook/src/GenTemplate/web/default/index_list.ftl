<%@ page contentType="text/html; charset=gb2312" %>
<#if (listInfo?size>0)>
	<#list listInfo as info>
<tr>
    <td style="padding-top:9px;padding-left:2px;"><a href="${info.id}" title="ok">${info.title}</a></td>
</tr>
	</#list>
</#if>
