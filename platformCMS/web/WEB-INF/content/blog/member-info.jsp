<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/ui.all.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.core.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${ctx }/js/ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${ctx }/js/area.js"></script>

<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
function submitForm(id){
	document.getElementById(id).submit();
}
</script>
<script type="text/javascript">
		var liveProvince = "${entity.liveProvince}";
		var liveCity = "${entity.liveCity}";
		var liveCounty = "${entity.liveCounty}";
        $(document).ready(function(){   
            getProvinces();   
        });   
           
        function getProvinces(){   
            var pro = "";
            var selectedItem = "";   
            for(var i = 0 ; i < provinces.length; i++){
                if(liveProvince == provinces[i]){
                	selectedItem = "selected='selected'";
                 }   
                pro += "<option "+selectedItem+">" + provinces[i] + "</option>";
                selectedItem = "";   
            }   
            $('#province').empty().append(pro);   
            getCities();   
        }   
        function getCities(){   
            var proIndex = $('#province').attr('selectedIndex');   
            showCities(proIndex);   
            getCounties();   
        }   
        function showCities(proIndex){   
            var cit = "";   
            if(cities[proIndex] == null){   
                $('#city').empty();   
                return;   
            }  
            var selectedItem = "";  
            for(var i = 0 ;i < cities[proIndex].length ; i++){
            	if(liveCity == cities[proIndex][i]){
                	selectedItem = "selected='selected'";
                 }     
                cit += "<option "+selectedItem+">" + cities[proIndex][i] + "</option>";
                selectedItem = "";    
            }   
            $('#city').empty().append(cit);   
        }   
        function getCounties(){   
            var proIndex = $('#province').attr('selectedIndex');   
            var citIndex = $('#city').attr('selectedIndex');   
            showCounties(proIndex,citIndex);   
        }   
        function showCounties(proIndex,citIndex){   
            var cou = "";   
            if(counties[proIndex][citIndex] == null){   
                $('#county').empty();   
                return;   
            }   
            var selectedItem = "";
            
            for(var i = 0 ;i < counties[proIndex][citIndex].length;i++){ 
            	if(liveCounty == counties[proIndex][citIndex][i]){
                	selectedItem = "selected='selected'";
                 }   
                cou += "<option "+selectedItem+">" + counties[proIndex][citIndex][i] + "</option>";  
                selectedItem = ""; 
            }   
            $('#county').empty().append(cou);   
        }   
        </script>  
        
        
        
 <script type="text/javascript">
		var hometown = "${entity.hometown}";
		var homeCity = "${entity.homeCity}";
		var homeCounty = "${entity.homeCounty}";   
        $(document).ready(function(){   
            getProvinces2();   
        });   
           
        function getProvinces2(){   
            var pro = "";
            var selectedItem = "";   
            for(var i = 0 ; i < provinces.length; i++){
                if(hometown == provinces[i]){
                	selectedItem = "selected='selected'";
                  }   
                pro += "<option "+selectedItem+">" + provinces[i] + "</option>";
                selectedItem = "";   
            }   
            $('#province2').empty().append(pro);   
            getCities2();   
        }   
        function getCities2(){   
            var proIndex = $('#province2').attr('selectedIndex');   
            showCities2(proIndex);   
            getCounties2();   
        }   
        function showCities2(proIndex){   
            var cit = "";   
            if(cities[proIndex] == null){   
                $('#city2').empty();   
                return;   
            }   
            var selectedItem = "";
            for(var i = 0 ;i < cities[proIndex].length ; i++){
            	if(homeCity == cities[proIndex][i]){
                	selectedItem = "selected='selected'";
                  }   
                cit += "<option "+selectedItem+">" + cities[proIndex][i] + "</option>";
                selectedItem = "";   
            }   
            $('#city2').empty().append(cit);   
        }   
        function getCounties2(){   
            var proIndex = $('#province2').attr('selectedIndex');   
            var citIndex = $('#city2').attr('selectedIndex');   
            showCounties2(proIndex,citIndex);   
        }   
        function showCounties2(proIndex,citIndex){   
            var cou = "";   
            if(counties[proIndex][citIndex] == null){   
                $('#county2').empty();   
                return;   
            }  
            var selectedItem = ""; 
            for(var i = 0 ;i < counties[proIndex][citIndex].length;i++){
            	if(homeCounty == counties[proIndex][citIndex][i]){
                	selectedItem = "selected='selected'";
                  }   
                cou += "<option "+selectedItem+">" + counties[proIndex][citIndex][i] + "</option>";  
                selectedItem = "";  
            }   
            $('#county2').empty().append(cou);   
        }   
        </script>  
        <script type="text/javascript">
			$(function() {
				$.datepicker.setDefaults($.extend({showMonthAfterYear: false}, $.datepicker.regional['zh-CN']));
				$("#datepicker").datepicker(
						{ changeMonth: true,
						  changeYear: true,
						  yearRange: '1960:2008'
						});
			});
	</script>
			<script>
		function submitForm(id){
			document.getElementById(id).submit();
		}
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#enraalName").focus();
			//为inputForm注册validate函数
			$("#memberInfoSave").validate();
		});
  
	</script>
</head>
<body>
<div class="container">	
		<div class="header">
			<%@include file="/common/blog-header.jsp" %>
	</div>
	<div class="content">
		<div class="toubu">
			<%@include file="/common/blog-content-header.jsp" %>
		</div>
		<div class="main">
			<div class="mainbh">
				<div class="leftsidebar">
					<%@include file="/common/blog-content-left.jsp" %>
				</div>
				<div class="zhuti">
				<div class="zhitibt">Information_个人档案</div>
				<div class="dangannav">
					<%@include file="/common/blog-content-nav.jsp" %>
				</div>
				<div class="zdyhaoyou">注_完善你的个人档案同时请设置浏览权限！以便保护你的个人隐私
<br />
				完整填写可获10喔元！</div>
				<form action="member-info!save.action" method="post" id="memberInfoSave">
				<div class="gexingshezhi">
					<table width="550" border="0" cellspacing="0">
  <tr>
    <td height="25" colspan="7" valign="bottom">姓名</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.member.name" id="enraalName" type="text" class="qq" value="${entity.member.name }"/></td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">性别</td>
    </tr>
  <tr>
    <td colspan="7" height="25" colspan="7">
    	<s:radio list="#{'1':'男','2':'女'}" name="entity.gender"  theme="simple"></s:radio>
    </td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">出生日期</td>
    </tr>
  <tr>
    <td colspan="5" colspan="7">
     <input type="text" id="datepicker" class="lxkuang dateISO" name="entity.birthday"  value="<s:date name="entity.birthday" format="yyyy-MM-dd"/>"/>&nbsp;
      <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.birthdayHiden"  theme="simple"></s:radio>
      </td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">现居城市</td>
    </tr>
  <tr>
    <td  height="25" colspan="7">
    <select id="province" onchange="getCities()" style="width:120px;" name="entity.liveProvince"></select>  
	<select id="city" onchange="getCounties()" style="width:120px;" name="entity.liveCity"></select>  
	<select id="county" style="width:120px;" name="entity.liveCounty"></select> 
    </td>   
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">家乡</td>
    </tr>
  <tr>
    <td height="25" colspan="7">
    <select id="province2" onchange="getCities2()" style="width:120px;" name="entity.hometown"></select>  
	<select id="city2" onchange="getCounties2()" style="width:120px;" name="entity.homeCity"></select>  
	<select id="county2" style="width:120px;" name="entity.homeCounty"></select>
    </td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">婚姻状况</td>
    </tr>
  <tr>
    <td colspan="7"><s:radio list="#{'1':'已婚','2':'未婚'}" name="entity.marital"  theme="simple"></s:radio>
    <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.maritalHiden"  theme="simple"></s:radio>
    </td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">联系方式</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.contacts" type="text"  class="lxkuang" value="${entity.contacts }"/>
     <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.contactsHiden"  theme="simple"></s:radio>
     </td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">QQ</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.qq" type="text"  class="lxkuang" value="${entity.qq }"/>
      <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.qqHiden"  theme="simple"></s:radio>
      </td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">MSN</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.msn" type="text"  class="lxkuang" value="${entity.msn }"/>
      <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.msnHiden"  theme="simple"></s:radio>
      </td>
    </tr>
	
  <tr>
    <td height="25" colspan="7" valign="bottom">通讯地址</td>
    </tr>
	 <tr>
    <td colspan="7" height="25"><input name="entity.address" type="text"  class="xxtongxun" value="${entity.address }"/>
      <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.addressHiden"  theme="simple"></s:radio>
      </td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">邮编</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.postalcode" type="text"  class="lxkuang" value="${entity.postalcode }"/></td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">个人网站</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.website" type="text"  class="youbian" value="${entity.website }"/></td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">教育背景</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.education" type="text"  class="youbian" value="${entity.education }"/>
      <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.educationHiden"  theme="simple"></s:radio></td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">工作信息</td>
    </tr>
  <tr>
    <td colspan="7" height="25"><input name="entity.workInfo" type="text"  class="youbian" value="${entity.workInfo}"/>
      <s:radio list="#{'1':'_隐藏此信息','2':'_仅向好友公开'}" name="entity.workInfoHiden"  theme="simple"></s:radio></td>
    </tr>
  <tr>
    <td height="25" colspan="7" valign="bottom">我的简介</td>
    </tr>
  <tr>
    <td colspan="7"><textarea name="entity.introduction" cols="1" rows="10" class="wodejianjie">${entity.introduction }</textarea></td>
    </tr>
    <input type="hidden" name="id" value="${entity.id }" /> 
	<input type="hidden"  name="entity.member.id" value="${member.id}" />
	<input type="hidden" name="action" value="memberinfo"/>
</table>


                </div>
				<div class="zhiyebaocun" ><a href="javascript:submitForm('memberInfoSave');">确认保存 Confirmed Save</a></div>
				</form>
				</div>
		</div>
		<div class="footer">
			<%@include file="/common/blog-content-footer.jsp" %>
		</div>
	</div>
</div>
</div>
</body>
</html>