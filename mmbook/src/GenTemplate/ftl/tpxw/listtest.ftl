<br/>
list<br/>
<#if (listData?size>0)>
	<#list listData as info>
	dfdsfgasdf<br/>
	${info.partName}. ${info.id} <br/>
	</#list>
<#else>
      无信息!
</#if>
<br/>