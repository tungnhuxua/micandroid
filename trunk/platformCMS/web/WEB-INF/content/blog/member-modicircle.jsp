<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>${member.name}'s blog</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/area.js"></script>
<script type="text/javascript">
function submitForm(id){
	document.getElementById(id).submit();
}

//上传图片
function upload() {	
	var of = $('#uploadFile');
	//检查是否选择了图片
	if(of.val()=='') {
		alert('请选择要上传的图片');
		return;
	}
	//将file移动至上传表单
	$('#fileContent').empty();
	$('#fileContent').append(of);
	//复制一个file放至原处
	$('#ufc').append(of.clone());
	//修改属性
	of.attr('id','');
	of.attr('name','upload');
	
	$('#uploadForm').submit();
}
</script>
<script type="text/javascript">
		var liveProvince = "${entity.province}";
		var liveCity = "${entity.city}";
		var liveCounty = "${entity.county}";
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
        
</head>
<body>
<% org.javaside.cms.entity.MemberCircle memberCircle = (org.javaside.cms.entity.MemberCircle)request.getAttribute("entity");%>
<form id="uploadForm" action="upload!uploadCircle.action" method="post" enctype="multipart/form-data" target="hiddenIframe"  style="display:none;width:0px;height:0px;">
	<span id="fileContent"></span>
	<input name="fileGroup" value="circle" type="hidden" />
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>
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
				<div class="zhitibt">request_圈子申请</div>
				
				<form action="member-circle!modiCircle.action" method="post" id="circleForm">
				<div class="gexingshezhi">
					<table width="550" border="0" cellspacing="0">
					  <tr>
					    <td height="25" colspan="7" valign="bottom">圈子名称</td>
					    </tr>
					  <tr>
					    <td colspan="7" height="25"><input name="entity.circleName" value="${entity.circleName}" type="text" class="qq" /></td>
					    </tr>
					
					  <tr>
					    <td height="25" colspan="7" valign="bottom">地址</td>
					    </tr>
					  <tr>
					    <td  height="25" colspan="7">
					    <select id="province" onchange="getCities()" style="width:120px;" name="entity.province"></select>  
						<select id="city" onchange="getCounties()" style="width:120px;" name="entity.city"></select>  
						<select id="county" style="width:120px;" name="entity.county"></select> 
					    </td>   
					  </tr>
					    <tr>
					      <td height="25" colspan="7" valign="bottom">圈子类型</td>
					    </tr>
					    <tr>
					      <td colspan="7" height="25">
					        <select name="entity.circleType">
					           <% java.util.List list = (java.util.List)request.getAttribute("circleTypeList");
					              for(int i = 0 ;i<list.size();i++){
					            	  Object[] object = (Object[])list.get(i);%>
					              <option  <%if(object[0].toString().equals(memberCircle.getCircleType())){out.print("selected='selected'");}%> value="<%=object[0] %>"><%=object[1]%></option>
					              <%} %>
					        </select>
					      </td>
					    </tr>
					    
					    <tr>
					   	 <td height="25" colspan="7" valign="bottom">圈子图片</td>
					    </tr>
					    
					    <tr>
					   	 <td colspan="7" height="25"><span id="ufc"><input type="file" id="uploadFile" size="20" /></span>
							   <input type="button" value="上传" onclick="upload();" /></td>
					    </tr>
					      
					    <tr>
					   	 <td height="25" colspan="7" valign="bottom"></td>
					    </tr>
					    <tr>
					   	 <td height="25" colspan="7" valign="bottom">
					   	    <s:if test="entity.circleImage!=null && entity.circleImage!=''">
					   	    	<img id="capimage"  src="${ctx }${entity.circleImage}" width="150" height="150" />
					   	    </s:if><s:else>
					   	 	   <img src="../images/zhchepic.gif" width="80px" height="80px"/>
					   		</s:else>
					   	 </td>
					    </tr>
					    
					    <tr>
					      <td height="25" colspan="7" valign="bottom">圈子描述</td>
					    </tr>
					    
					    <tr>
					      <td colspan="7"><textarea name="entity.des" cols="1" rows="10" class="wodejianjie">${entity.des}</textarea></td>
					    </tr>
					
					</table>

                </div>
				<div class="zhiyebaocun" ><a href="javascript:submitForm('circleForm');">更新_Update</a></div>
				<input type="hidden" id="entity.circleImage" name="entity.circleImage" value="${entity.circleImage}"/>
				<input type="hidden"  name="entity.id" value="${entity.id}"/>
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