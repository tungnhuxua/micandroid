<%@ page contentType="text/html; charset=gb2312" %>

<div class="top">
<div class="topbg">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="46%" style="padding-left:20px;">&nbsp;</td>
      <td width="27%" height="84">&nbsp;</td>
      <td width="16%" align="right"><img src="../../../res/default/images/tubiao1.gif"  style="padding-bottom:1px; padding-right:6px;" /><a href="../../../res/default/yindao.html">返回首页</a></td>
      <td width="11%" align="center"><img src="images/tubiao1.gif"   style="padding-bottom:1px; padding-right:6px;" /><a href="#">联系我们</a></td>
      </tr>
  </table>
</div>
</div>
<script language="javascript">
	function qiehuan(num){
		for(var id = 1;id<=9;id++)
		{
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				document.getElementById("mynav"+id).className="nav_on";
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				document.getElementById("mynav"+id).className="";
			}
		}
	}
</script>
<div id="menu_out">
  <div id="menu_in">
    <div id="menu">
      <UL id="nav"><LI style="width:70px;"></LI>
 <#if (SiteChannelList?size>0)>
	<#list SiteChannelList as SiteChannelList>
    <LI><A class="nav_on" id="mynav${SiteChannelList.id}" onmouseover="javascript:qiehuan(${SiteChannelList.id})" href="channle_${SiteChannelList.id}.jsp"><SPAN>${SiteChannelList.channelsName}</SPAN></A></LI>
	 <LI class="menu_line"></LI>
	</#list>
<#else>
 	<UL id="nav"><LI style="width:70px;"></LI>
    <LI><A class="nav_on" id="mynav0" onmouseover="javascript:qiehuan(0)" href="#"><SPAN>无信息</SPAN></A></LI>
</#if>  

</UL>
<div id="menu_con">

	<#list channelAndpart as channelAndpart>
        <div id="qh_con${channelAndpart.id}" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
          <#list channelAndpart.sitePartList as siteParalist>
            <LI><a href="part_${siteParalist.id}.jsp"><span>${siteParalist.partName}</span></A></LI>
            <LI class="menu_line2"></LI>
          </#list>
          </UL>
        </div>
	</#list>
       
 
      
      </div>
    </div>
  </div>
</div>
