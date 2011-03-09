<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>图片剪裁</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	</head>
<body style="margin:3px auto;padding:0px;font-size:14px;width:1000px;">
<div style="">
	<div style="width:700px;height:300px;border:1px solid #333;margin-right:10px;float:left;">
		<img id="ferret" src="${ctx}<%=request.getParameter("imagePath") %>" title="image select" alt="loading IMG ......" />
	</div>
	<div style="float:left;">
		<!--  <div id="imgDiv" style="overflow:hidden;width:700px;height:310px;border:1px solid #333;">
			<img id="minImg" src="${ctx}/<%=request.getParameter("imagePath") %>"/>
		</div>-->
		<form id="submitForm" action="upload!UserCoverImgCut.action" target="hiddenIframe" method="post" id="cutImgForm" style="margin:7px 0px 0px 0px;padding:0px">
			<input type="hidden" id="imgSrcRoot"  name="imgSrcRoot" value="http://localhost:8080/jeecms/res_base/jeecms_com_www/upload"/>
			<input type="hidden" id="imgSrcPath"  name="imgSrcPath" value="/article/image/2009_4/11_24/u6e9g2eipldf.jpg"/>
			<input type="hidden" id="cutImageW"  name="txt_DropWidth"/>
			<input type="hidden" id="cutImageH"  name="txt_DropHeight"/>
			<input type="hidden" id="cutImageX"  name="txt_left"/>
			<input type="hidden" id="cutImageY"  name="txt_top"/>
			<input type="hidden" id="txt_width"  name="txt_width"/>
			<input type="hidden" id="txt_height"  name="txt_height"/>
			<input type="hidden" id="imgScale"  name="imgScale" value="1"/>
			<input type="hidden" name="uploadNum" value="2"/>
			<input type="hidden" name="entity.coverImg" value="<%=request.getParameter("imagePath")%>"/>
			<input name="fileGroup" value="cap" type="hidden" />
			<input type="hidden" name="entity.id" value="${id}" /> 
			<div>
			原图 &nbsp;&nbsp;&nbsp;宽：<span id="oimgW"></span> &nbsp; &nbsp; 高：<span id="oimgH"></span></div>
			<!--  缩略图 宽：<input type="text" name="reMinWidth" id="reMinWidth" value="310" size="5" onkeypress="if(keyCode=13){return;}" onfocus="this.select();"/>
			高：<input type="text" name="reMinHeight" id="reMinHeight" value="310" size="5" onkeypress="if(keyCode=13){return;}" onfocus="this.select();"/>
			<input type="button" value="重设" onclick="resetOption($('#reMinWidth').val(),$('#reMinHeight').val());"/>-->
			<div style="text-align:center;margin-top:7px;"><input type="button" value="确认剪裁" onclick="$('#submitForm').submit();window.close();"/> <input type="button" value="关闭窗口" onclick="window.close();"/></div>
		</form>
	</div>
	<div style="clear:both;"></div>
</div>
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery_imgareaselect.js" type="text/javascript"></script>
<script type="text/javascript">
	var container=960;
	var imageW = 100;
	var imageH = 100;
	var minWidth = 700;
	var minHeight = 300;
	var scale = 1;
	function preview(img, selection){
		showCut(selection.width,selection.height,selection.x1,selection.y1);
	}
	function showCut(w,h,x,y){   
		var scaleX = minWidth / w;
		var scaleY = minHeight / h;
		$('#minImg').css({ width: Math.round(scaleX * imageW * scale) + 'px', height: Math.round(scaleY * imageH * scale) + 'px', marginLeft: '-' + Math.round(scaleX * x ) + 'px', marginTop: '-' + Math.round(scaleY * y) + 'px' });
		$('input#cutImageW').val(w);
		$('input#cutImageH').val(h);
		$('input#cutImageX').val(x);
		$('input#cutImageY').val(y);
	}
	$(window).load(function () {
		imageW = $('#ferret').width();
		imageH = $('#ferret').height();
		$('#oimgW').html(imageW);
		$('#oimgH').html(imageH);
		var imgMax = imageW>imageH?imageW:imageH;
		if(imgMax>container) {
			scale = container/imgMax;
			$('#imgScale').val(scale);
			$('#ferret').css({width:Math.round(scale*imageW)+'px',height:Math.round(scale*imageH)+'px'});
			var txt_width = Math.round(scale*imageW);
			var txt_height =  Math.round(scale*imageH);
			$('input#txt_width').val(txt_width);
			$('input#txt_height').val(txt_height);
		}
		if(imageW<700||imageH<300) {
			alert("源图尺寸小于缩略图！如需剪裁请重设缩略图大小。");
			return;
		}
		if(imageW==minWidth&&imageH==minHeight) {
			alert("源图和缩略图尺寸一致！如需剪裁请重设缩略图大小。");
			return;
		}
		var minSelW = Math.round(700*scale);
		var minSelH = Math.round(300*scale);
		$('img#ferret').imgAreaSelect({selectionOpacity:0,outerOpacity:'0.5',selectionColor:'blue',onSelectChange:preview,minWidth:minSelW,minHeight:minSelH,aspectRatio:'700:300',x1:0,y1:0,x2:700,y2:300});
		showCut(700,300,0,0);
	});
	function resetOption(width,height) {
		minWidth = parseInt(width);
		minHeight = parseInt(height);
		if(imageW<minWidth||imageH<minHeight) {
			alert("源图尺寸小于缩略图！如需剪裁请重设缩略图大小。");
			return;
		}
		if(imageW==minWidth&&imageH==minHeight) {
			alert("源图和缩略图尺寸一致！如需剪裁请重设缩略图大小。");
			return;
		}
		$('#imgDiv').css({'width':minWidth+'px','height':minHeight+'px'});
		var minSelW = Math.round(minWidth*scale);
		var minSelH = Math.round(minHeight*scale);
		$('img#ferret').imgAreaSelect({selectionOpacity:0,outerOpacity:'0.5',selectionColor:'blue',onSelectChange:preview,minWidth:minSelW,minHeight:minSelH,aspectRatio:minWidth+':'+minHeight,x1:0,y1:0,x2:minWidth,y2:minHeight});
		//showCut(minWidth,minHeight,0,0);
	}
</script>
</body>
</html>