(function( $ ){
	/* Declare a variable collection */
	var elems = {
		cover:    "#pops-cover",
		tip:      "#pops-tips-box",
		page:     "#pops-page-box",
		msg:      "#pops-msg-box",
		closeBar: "#pops-closeBar-box"
	};
	
	$.fn.extend({
		/* Create new elements */
		newElems: function(id,content){
			content == undefined? content='': content;
			return $(this).append("<div id='"+id.replace("#","")+"'>"+content+"</div>"); 
		},
		/* Get style of the elements */
		getCss: function(){
			return {
				left  : $(this).offset().left,
				top   : $(this).offset().top,
				width : $(this).width(),
				height: $(this).height()
			};
		},
		/* Set style of the elements */
		setCss: function(options){
			options = $.extend({ left: null, top: null, width: null, heihgt: null },options);
			options.left  != null? $(this).css("left", options.left+"px"): null;
			options.top   != null? $(this).css("top" , options.top +"px"): null;
			options.width != null? $(this).width(options.width+"px"): null;
			options.height!= null? $(this).height(options.height+"px"): null;
		},	
		/* Cover the entire page <for $.cover()> */
		entirePage: function(){
			var width = Math.max(document.documentElement.clientWidth,document.body.scrollWidth);
			var height= Math.max(document.documentElement.clientHeight,document.body.scrollHeight);
			
			$(this).css({"width": width+"px","height": height+"px", "left": "0px", "top": "0px"});
		},
		/* To the left of center or left <for $.elemPosition()> */
		posLeft: function(){
			var pos = Math.max(window.screen.availWidth-$(this).width())/2;
			pos < "0"? pos="0":pos;
			document.documentElement.scrollLeft != 0? pos+=document.documentElement.scrollLeft: null;
			$(this).css("left", pos+"px");
		},
		/* To the top of center or top <for $.elemPosition()> */
		posTop: function(pos){
			pos=="center"?
				pos=Math.max(window.screen.availHeight-$(this).height())/2-100:pos=="top"?
						pos="0":null;
			pos < "0"? pos="0": pos;
			document.documentElement.scrollTop != 0? pos+=document.documentElement.scrollTop: null;
			$(this).css("top", pos+"px");
		},
		/* Set the position of the elements <for $.tips() && $.popPage()>*/
		elemPosition: function(pos){
			if( pos=='center' ) {
				this.posTop('center');
			}
			else if( pos=='top' ) {
				this.posTop('0');
			}else{
				return;
			}
			this.posLeft();
		},
		
		
		/* Add a close bar for elements */
		closeBar: function(delElems){
			if($(elems.closeBar).length != 0) { $(elems.closeBar).remove(); }
			
			var cls = "<a href='javascript:;' onfocus='this.blur();'>关闭</a>";
			$(this).newElems(elems.closeBar, cls);
			get = $(this).getCss();
			$(elems.closeBar).setCss({left: (get.width-$(elems.closeBar).width() ), top: 0});
			
			$(elems.closeBar).click(function(){/* Remove the elements */
				$.removeElems(delElems);
			});
		},
		/* Pop-up error message when input error */
		tips: function(msg){
			$(elems.tip).length == 0?
				$("body").newElems(elems.tip, msg):$(elems.tip).html(msg);
			get = $(this).getCss();
			$(elems.tip).setCss({left: (get.left + get.width), top: get.top});
		}
	});
	
	$.extend({
		/* Pop-up mask layer <if all cover else just element cover> */
		cover: function(elem){
			if($(elems.cover).length != 0) { $(elems.cover).remove(); }

			$("body").newElems(elems.cover);
			if(typeof elem == 'string'){
				get = $(elem).getCss();
				$(elems.cover).setCss({left: get.left, top: get.top, width: get.width, height: get.height});
			}else{
				$(elems.cover).entirePage();
			}
		},
		/* Pop-up a new page */
		popPage: function(options){
			options = $.extend({ url: null, pos: 'center', isBar: null, isCover: null },options);
			if(options.url != null){
				$("body").newElems(elems.page);
				$(elems.page).load(options.url, '1=1', function(){
					$(elems.page).elemPosition(options.pos/* Default 'center' */);
					
					if(options.isBar != "none") $(elems.page).closeBar("cover,page");
				});
			}
			options.isCover == "none"? null: $.cover();
		},
		/* Pop-up messages */
		msgs: function(options){
			options = $.extend({ pos: 'top', msg: null },options);
			
			if(options.msg != null){
				if($(elems.msg).length != 0){ $(elems.msg).remove(); }
				$("body").newElems(elems.msg,options.msg);
				$(elems.msg).elemPosition(options.pos/* Default 'top' */);
			}
		},
		/* What elements to remove */
		removeElems: function(elements){
			elements == "all"? 
				$.removeAllElems():$.removeSomeElems(elements);
		},
		/* Remove some elements */
		removeSomeElems: function(elements){
			var el = elements.split(",");
			for( var i=0; i<el.length; i++)
				$( elems[ el[i] ] ).remove();
		},
		/* Remove all element of the lists */
		removeAllElems: function(){
			for ( var key in elems )
				$( elems[ key ] ).remove();
		}
	});
})( jQuery );