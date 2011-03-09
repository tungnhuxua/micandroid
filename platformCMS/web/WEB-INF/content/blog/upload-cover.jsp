<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="../js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
parent.document.getElementById('imageUri').value="${uploadFileName}";
var fgroup = "${fileGroup}";

var imgSrc = parent.document.getElementById('preImg');
//$(imgSrc).css("width","auto");
//$(imgSrc).css("height","auto");
$(imgSrc).attr("src","${ctx}${uploadFileName}");

var uploadimgpath = parent.document.getElementById('uploadimgpath');
$(uploadimgpath).attr("value","${uploadFileName}");

</script>
</head>
<body>

</body>
</html>