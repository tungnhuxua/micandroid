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
     <LI><A class="nav_on" id="mynav1" onmouseover="javascript:qiehuan(1)" href="channle_1.jsp"><SPAN>首页</SPAN></A></LI>
	 <LI class="menu_line"></LI>
    <LI><A class="nav_on" id="mynav2" onmouseover="javascript:qiehuan(2)" href="channle_2.jsp"><SPAN>资讯</SPAN></A></LI>
	 <LI class="menu_line"></LI>
    <LI><A class="nav_on" id="mynav3" onmouseover="javascript:qiehuan(3)" href="channle_3.jsp"><SPAN>小说</SPAN></A></LI>
	 <LI class="menu_line"></LI>
    <LI><A class="nav_on" id="mynav4" onmouseover="javascript:qiehuan(4)" href="channle_4.jsp"><SPAN>音乐</SPAN></A></LI>
	 <LI class="menu_line"></LI>
    <LI><A class="nav_on" id="mynav5" onmouseover="javascript:qiehuan(5)" href="channle_5.jsp"><SPAN>软件</SPAN></A></LI>
	 <LI class="menu_line"></LI>

</UL>
<div id="menu_con">

        <div id="qh_con1" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_1.jsp"><span>网站导航</span></A></LI>
            <LI class="menu_line2"></LI>
            <LI><a href="part_2.jsp"><span>关于我们</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con2" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_3.jsp"><span>新闻</span></A></LI>
            <LI class="menu_line2"></LI>
            <LI><a href="part_4.jsp"><span>科技</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con3" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_5.jsp"><span>连载</span></A></LI>
            <LI class="menu_line2"></LI>
            <LI><a href="part_6.jsp"><span>排行</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con4" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_13.jsp"><span>经典音乐</span></A></LI>
            <LI class="menu_line2"></LI>
            <LI><a href="part_14.jsp"><span>流行音乐</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con5" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_12.jsp"><span>电脑软件</span></A></LI>
            <LI class="menu_line2"></LI>
            <LI><a href="part_11.jsp"><span>手机软件</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con6" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_8.jsp"><span>风景图片</span></A></LI>
            <LI class="menu_line2"></LI>
            <LI><a href="part_7.jsp"><span>美女图片</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con7" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_9.jsp"><span>留言版</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con8" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_10.jsp"><span>联系我们</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
        <div id="qh_con10" style="DISPLAY: none">
          <UL><LI style="width:50px;"></LI>
            <LI><a href="part_12.jsp"><span>电脑软件</span></A></LI>
            <LI class="menu_line2"></LI>
          </UL>
        </div>
       
 
      
      </div>
    </div>
  </div>
</div>
