/**
 * @author 可乐加糖
 * @email  black.angel.liu@gmail.com
 */

function changetab(divArr,tardiv){
	//divArr这个是用来拆分的DIV，tardiv 是需要显示的。
	var arr = divArr.split(",");
	//需要显示的DIV.
	
	var tarobj = document.getElementById(tardiv);
	
	for(var i=0;i<arr.length;i++){
		var temp = document.getElementById(arr[i]);
		temp.style.display = "none";
	}
	tarobj.style.display = "block";
}