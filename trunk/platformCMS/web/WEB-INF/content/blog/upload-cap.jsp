<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>用户档案</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/blog/cap/jquery1.2.6.pack.js"></script>
	    <script  type="text/javascript" src="${ctx}/js/blog/cap/ui.core.packed.js" ></script>
	    <script type="text/javascript" src="${ctx}/js/blog/cap/ui.draggable.packed.js" ></script>
	    <script type="text/javascript" src="${ctx}/js/blog/cap/CutPic.js"></script>
		<link rel="stylesheet" href="${ctx}/css/userCap.css" type="text/css" />
		    
		<script language="Javascript">

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

			function submitForm(id){
				document.getElementById(id).submit();
			}
		</script>
</head>
<body>
<form id="uploadForm" action="upload!uploadcap.action" method="post" enctype="multipart/form-data"  style="display:none;width:0px;height:0px;">
	<span id="fileContent"></span>
	<input name="fileGroup" value="cap" type="hidden" />
	<input name="userCapDir" value="${memberid}" type="hidden" />
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
				<div class="zhitibt">Information_个人档案</div>
				<div class="dangannav">
					<%@include file="/common/blog-content-nav.jsp" %>
				</div>
				
				<form action="upload!uploadCapCut.action" method="post" id="memberInfoSave">
				<div class="gexingshezhi">
					<div class="beijingyanseb">_背景图片设置_图片最大可上载2M
					</div>
						<div class="beijingyanse">
								<span id="ufc"><input type="file" id="uploadFile" size="20" /></span>
							    <input type="button" value="上传" onclick="upload();" />
						</div>

					
					<div id="Step2Container">
			                   <div id="Canvas" class="uploaddiv">
			                   
			                            <div id="ImageDragContainer">                               
			                               <img id="ImageDrag" class="imagePhoto" src="${ctx }${uploadFileName}" style="border-width:0px;" />                                                        
			                            </div>
			                            <div id="IconContainer">                               
			                               <img id="ImageIcon" class="imagePhoto" src="${ctx }${uploadFileName}" style="border-width:0px;" />                                                        
			                            </div>                          
			                    </div>
			                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			                    <div class="uploaddiv">
			                       <table>
			                            <tr> 
			                                <td id="Min">
			                                        <img alt="缩小" src="${ctx}/images/userCap/_c.gif" onmouseover="this.src='${ctx}/images/userCap/_c.gif';" onmouseout="this.src='${ctx}/images/userCap/_h.gif';" id="moresmall" class="smallbig" />
			                                </td>
			                                <td >
			                                    <div id="bar">
			                                        <div class="child">
			                                        </div>
			                                    </div>
			                                </td>
			                                <td id="Max">
			                                        <img alt="放大" src="${ctx}/images/userCap/c.gif" onmouseover="this.src='${ctx}/images/userCap/c.gif';" onmouseout="this.src='${ctx}/images/userCap/h.gif';" id="morebig" class="smallbig" />
			                                </td>
			                            </tr>
			                        </table>
			                    </div>
			                    	<input type="hidden" name="picture" value="${ctx }${uploadFileName}"/>
				                    <div class="zhiyebaocun" ><a href="javascript:submitForm('memberInfoSave');">确认保存 Confirmed Save</a></div>          
				                    <div>
				                      
				                   </div>  
			         </div>
     
					
                </div>
                <input name="txt_width" type="hidden" value="1" id="txt_width" /><br />
                <input name="txt_height" type="hidden" value="1" id="txt_height" /><br />
                <input name="txt_top" type="hidden" value="75" id="txt_top" /><br />
                <input name="txt_left" type="hidden" value="175" id="txt_left" /><br />
                <input name="txt_DropWidth" type="hidden" value="150" id="txt_DropWidth" /><br />
                <input name="txt_DropHeight" type="hidden" value="150" id="txt_DropHeight" /><br />
				<input name="txt_Zoom" type="hidden" id="txt_Zoom" />
                <input type="hidden" name="entity.id" value="${id}" /> 
                <input name="fileGroup" value="cap" type="hidden" />
				<input type="hidden"  name="entity.member.id" value="${memberid}" />
				<input type="hidden" id="headPortraitUri" name="entity.headPortraitUri" value="${uploadFileName}"/>
				<input type="hidden" name="action" value="memberpicture"/>
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