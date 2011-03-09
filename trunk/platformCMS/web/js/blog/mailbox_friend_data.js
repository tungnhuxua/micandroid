var oRegion = document.getElementById("txtRegion");   //需要弹出下拉列表的文本框      
var oDivList = document.getElementById("divList"); //要弹出的下拉列表      
var oClose = document.getElementById("tdClose");　　　//关闭div的单元格，也可使用按钮实现      
var colOptions = document.getElementsByTagName("li"); //所有列表元素      
var bNoAdjusted = true;      
oClose.onclick = function()      
{      
    oDivList.style.display = "none"; //隐藏div，实现关闭下拉框的效果      
};      
//设置下列选择项的一些事件      
for (var i=0; i<colOptions.length; i++)      
{      
    colOptions[i].style.cursor = "pointer";      
    colOptions[i].onclick = function() //为列表项添加单击事件      
    {      
    		oRegion.value = this.innerHTML;
    		oDivList.style.display = "none";
         
    };      
    colOptions[i].onmouseover = function()//为列表项添加鼠标移动事件      
    {      
    	//this.style.backgroundColor = "#ffff00";      
    };      
    colOptions[i].onmouseout = function()  //为列表项添加鼠标移走事件      
    {      
        //this.style.backgroundColor = "";      
    };      
}      
//文本获得焦点时的事件     
oRegion.onfocus = function()      
{      
    oDivList.style.display = "block";
    if (bNoAdjusted) //控制div是否已经显示的变量      
    {      
        bNoAdjusted = false;      
        //设置下拉列表的宽度和位置      
        oDivList.style.width = this.offsetWidth ;      
        oDivList.style.posTop = oDivList.offsetTop + this.offsetHeight;      
        oDivList.style.posLeft = oDivList.offsetLeft - this.offsetWidth -2;      
    }      
}; 
