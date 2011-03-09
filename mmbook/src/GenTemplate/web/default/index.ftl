<%@ page contentType="text/html; charset=gb2312" %>
<%@ include file="inc.jsp"%>
<%@ include file="head.jsp"%>
<body class="bg">
<%@ include file="channel_list.jsp"%>

<div style="height:5px;"></div>
 <div class="flash">
 </div>
<div class="con">
<div class="left">
<div class="lefttitle"><h1>小说</h1></div>
<div class="leftcon">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
<%@ include file="artilelist.jsp"%>
</table>

</div>
<div class="leftbottom"></div>
</div>
<div class="middle">
<div class="middletitle">
<ul>
<li id="one1" onmouseover="setTab('one',1,2)" class="hover">新闻</li>
<li id="one2" onmouseover="setTab('one',2,2)">科技</li>
</ul>
</div><div id="con_one_1">
<div class="middlecon"></div>
<div class="middlecon2">
<ul>
<li><span>[2010-08-20]</span>&middot; <a href="#">商标证书正式生效</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司取得了ISO9001质量体系认证</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司加入电力金具行业协会</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">公司年度营销会议正在筹备之中 </a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司是您理想的合作伙伴</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司荣获荣誉证书</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">商标证书正式生效</a></li>
</ul>
</div>
<div class="middlebottom"></div>
</div>
<div id="con_one_2" style="display:none">
<div class="middlecon"></div>
<div class="middlecon2">
<ul>
<li><span>[2010-08-20]</span>&middot; <a href="#">公司年度营销会议正在筹备之中</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司取得了ISO9001质量体系认证</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司加入电力金具行业协会</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">公司年度营销会议正在筹备之中 </a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司是您理想的合作伙伴</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">淄博金宇磨料有限公司荣获荣誉证书</a></li>
<li><span>[2010-08-20]</span>&middot; <a href="#">商标证书正式生效</a></li>
</ul>
</div>
<div class="middlebottom"></div>
</div>
</div>
<div class="right">
<div class="righttitle"><span><a href="#">更多>></a></span>音乐</div>
<div class="rightcon"><p>淄博金宇磨料有限公司是中外合资企业，始建于1990年，具有先进的生产技术和完善的质量保证体系。</p><p>所生产的棕刚玉、白刚玉、黑碳化硅、绿碳化硅等各种磨料及耐火材料，品质优良、质量稳定可靠，畅销20多个国家和地区，在广大用户中享较高的声誉，本公司还可根据客户的要求，提供各种专用产品。 
</p>
</div>
<div class="rightbottom"></div>
</div>
</div>
<div class="con2">
<div class="clear"></div>
<div class="pro">
<div class="protitle"><span><a href="#">更多>></a></span>精美图片</div>
<div class="procon">
<table width="629" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td><!--demo start-->
<DIV id=demo style="OVERFLOW: hidden; WIDTH: 629px">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tbody>
<tr>
    <td id=demo1>
    <table width="630" border="0" cellspacing="2" cellpadding="0">
<tr>
    <td align="center" style="padding-right:9px;"><img src="images/1.jpg"></td>
    <td align="center" style="padding-right:9px;"><img src="images/2.jpg"></td>
    <td align="center" style="padding-right:9px;"><img src="images/3.jpg"></td>
    <td align="center" style="padding-right:9px;"><img src="images/4.jpg"></td>
    <td align="center" style="padding-right:9px;"><img src="images/5.jpg"></td>
</tr>
</table>
    </td>
    <td id=demo2 width="0"></td>
</tr>
</tbody>
</table>
</DIV>
<!--demo end-->
<SCRIPT>
var dir=1//每步移动像素，大＝快
var speed=1//循环周期（毫秒）大＝慢
demo2.innerHTML=demo1.innerHTML
function Marquee(){//正常移动
//alert(demo2.offsetWidth+"\n"+demo.scrollLeft)
if (dir>0 && (demo2.offsetWidth-demo.scrollLeft)<=0) demo.scrollLeft=0
if (dir<0 &&(demo.scrollLeft<=0)) demo.scrollLeft=demo2.offsetWidth
demo.scrollLeft+=dir
demo.onmouseover=function() {clearInterval(MyMar)}//暂停移动
demo.onmouseout=function() {MyMar=setInterval(Marquee,speed)}//继续移动
}
function r_left(){if (dir=-1)dir=1}//换向左移
function r_right(){if (dir=1)dir=-1}//换向右移
var MyMar=setInterval(Marquee,speed)
</SCRIPT></td>
</tr>
</table>
</div>
<div class="prodi"></div>
</div>
<div class="tu"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="../../../res/default/images/tu1.gif" width="249" height="85" /></td>
  </tr>
  <tr>
    <td style="padding-top:10px;"><img src="images/tu2.gif" width="249" height="85" /></td>
  </tr>
</table>
</div>
</div>

<%@ include file="floor.jsp"%>