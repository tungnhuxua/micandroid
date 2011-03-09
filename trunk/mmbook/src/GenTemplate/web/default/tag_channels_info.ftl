<br/>
list<br/>
<#if (listData?size>0)>
	<#list listData as info>
	${info.partName}. ${info.id} <br/>
	</#list>
</#if>
<br/>