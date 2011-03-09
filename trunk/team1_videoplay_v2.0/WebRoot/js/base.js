$(document).ready(function() {
//	$(".videoImage").click(function(){window.location.href=$(this).parent(".image").children(".clickAfterURL").val();});
	init();
	$(".holder").click(goToURL);
});

function init() {
	var url = document.getElementsByName("clickAfterURL")[0].value;
	$(".holder").children(".url").val(url);
}

function goToURL() {
	window.location.href=$(this).children(".url").val();
}

$(function() {
	
	$('input.field').
    focus(function() {
        if(this.title==this.value) {
            this.value = '';
        }
    }).
    blur(function(){
        if(this.value=='') {
            this.value = this.title;
        }
    });
	
	var currentPage = 1;
	////向前向后按钮
    $('#slider .buttons span').live('click', function() {
    	var arrSrc = document.getElementsByName("clickAfterURL");
    	var src;
    	if(arrSrc.length == 1) {
    		src =  arrSrc[0].value;
    	} else {
	    	if(currentPage == arrSrc.length) {
	    		src = arrSrc[0].value;
	    	} else {
	    		src = arrSrc[currentPage].value;
	    	}
    	}
    	$(".holder").children(".url").val(src);
    	
        var timeout = setTimeout(function() {$("img").trigger("slidermove")}, 300);
        var fragments_count = $(this).parents('#slider:eq(0)').find('.fragment').length;
        var fragmet_width = $(this).parents('#slider:eq(0)').find('.fragment').width();
        var perPage = 1;
        var numPages = Math.ceil(fragments_count/perPage);
        var stepMove = fragmet_width*perPage;
        var container = $(this).parents('#slider:eq(0)').find('.content');
        var firstPosition = 0;
        var lastPosition = -((numPages-1)*stepMove);
        
        if ($(this).hasClass('next')) {
            currentPage ++;
            if (currentPage > numPages) {
                currentPage = 1;
                container.animate({'left': firstPosition});
                return;
            };
            container.animate({'left': -((currentPage - 1)*stepMove)});
        };
        if ($(this).hasClass('prev')) {
            currentPage --;
            if (currentPage < 1) {
                currentPage = numPages;
                container.animate({'left': lastPosition});
                return;
            };
            container.animate({'left': -((currentPage-1)*stepMove)});
        };
    });
});