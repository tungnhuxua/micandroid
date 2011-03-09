$(document).ready(show);
function show(){
	$.getJSON("typeQuery.do",null,callback);
}
function callback(data){
	var objectType = eval(data);
	var objType = $("#videoType");
	for(var i=0;i<objectType.length;i++) {
		var objOption = $("<option>");	
		objType.append(objOption);
		objOption.attr("value",objectType[i]["id"]);
		objOption.html(objectType[i]["typename"]);
	
	}
}