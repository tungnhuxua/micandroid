$(document).ready(function(){
	$("#requirdMoney1").click(requirdMoney1);
	$("#requirdMoney2").click(requirdMoney2);
});
function requirdMoney1(){
	$("#showAddMoney").slideDown("slow"); 
}

function requirdMoney2(){
	$("#showAddMoney").hide();
}