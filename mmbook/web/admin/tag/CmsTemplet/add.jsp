<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@ include file="../inc/param.jsp"%>

<html>
<head>
<title>新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.cmstemplet");
Ext.ux.cmstemplet.add<%=u_id%>=Ext.extend(function(){
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');
var tag ='1';
var extern ='user';

 
/**网页模板类型 **/

var webTempletTypeStore = new Ext.data.SimpleStore({
	fields : ['val','tex'],
	data : [['10','[页面]-首页'],['11','[页面]-栏目首页'],['12','[页面]-列表页'],['13','[页面]-详细页'],['14','[页面]-下载页'],['15','[页面]-预览页'], ['16','[页面]-章节列表页'], ['17','[页面]-搜索页'], ['18','[页面]-其它页'], ['20','[标签]-栏目标签'],['21','[标签]-内容标签'],['22','[标签]-推荐位标签'],['23','[标签]-列表标签']]
});

var webTempletTypeCombo = new Ext.form.ComboBox({
    id:'webTempletTypeCombo',
	fieldLabel : '模板类型',
	emptyText : '请选择...',
	//allowBlank : false,
	triggerAction : 'all',
	hiddenName :'webTempletType',
	store : webTempletTypeStore,
	displayField : 'tex',
	valueField : 'val',
	editable : false,
	mode : 'local'
});


var tagStore = new Ext.data.SimpleStore({
	fields : ['val','tex'],
	data : [['1','内容标签'],['2','栏目标签'],['3','内容推荐标签'],['4','列表标签']]
});

var tagCombo = new Ext.form.ComboBox({
    id:'tagCombo',
	xtype : 'combo',
	fieldLabel : '模板类型',
	emptyText : '请选择...',
	//allowBlank : false,
	triggerAction : 'all',
	hiddenName :'tagCombo',
	store : tagStore,
	displayField : 'tex',
	valueField : 'val',
	editable : false,
	mode : 'local',
	listeners : {
        select : function(combo){
            tag =combo.value;
            tree_rpwestroot<%=u_id%>.reload(); // 重刷整树
        }
   }
   
});

var externStore = new Ext.data.SimpleStore({
	fields : ['val','tex'],
	data : [['user','用户信息'],['config','配置文件'],['cmscategory','栏目数据']]
});

var externCombo = new Ext.form.ComboBox({
    id:'externCombo',
	xtype : 'combo',
	fieldLabel : '模板类型',
	emptyText : '请选择...',
	//allowBlank : false,
	triggerAction : 'all',
	hiddenName :'externCombo',
	store : externStore,
	displayField : 'tex',
	valueField : 'val',
	editable : false,
	mode : 'local',
	listeners : {
        select : function(combo){
            extern =combo.value;
            tree_rpcenteroot<%=u_id%>.reload(); // 重刷整树
        }
   }	
});


var field_add_cmstemplet<%=u_id%> = new Ext.form.FieldSet({
    title : '模板信息',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.33,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '93%'
			         },
			         items: [{
		                    	xtype:'textfield',
		                        fieldLabel: '模板名称',
		                        name:'templetName',
								invalidText : '模板名称已经被使用!', 
		                        allowBlank : false,
		                        minLength: 2,
								maxLength: 16,
		                        validationEvent : 'blur',
		                        //style : 'margin-top: 5px;',
		                        //labelStyle : 'margin-top: 5px;',
		                        //regex:/^[\w\u4E00-\u9FFF]+$/,
		                        //regexText:'输入中文、数字和英文',

								validator : function(thisText) {
							        var isok =false;
	                       	         Ext.Ajax.request({
	                                    url : 'tag/CmsTemplet/templetNameText.do',   
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText,
	                                        versionId : <%=v_id%>   
	                                    },
	                                     success : function(response,options){
							                  var res = Ext.util.JSON.decode(response.responseText); 
							                  if(res.success == true){  
							                     isok=true;
							                  }
							             }
	                                });
	                        		return true;
	                           }
		                   },{
				        	    xtype:'hidden',
				        	    fieldLabel:'方案ID',
				        	    name:'versionId',
				        	    value:'<%=v_id%>'
			                },{
				        	    xtype:'hidden',
				        	    fieldLabel:'模板内容',
				        	    name:'templetContentString'
			                }
	                    ]
            	},{
	                columnWidth:.33,
	                layout: 'form',
	                defaults : {
						anchor : '93%'
					},
                	items: [{
		                    	xtype:'textfield',
		                        fieldLabel: '文件名',
		                        allowBlank : false,
		                        name:'fileName',
								regex: /^\w+$/,
		                        regexText:'输入下划线、数字和英文',
		                        validationEvent : 'blur',
		                        invalidText : '文件名已经被使用!', 
		                        minLength: 2,
								maxLength: 60,
								validator : function(thisText) {
							        var isok =false;
	                       	        Ext.Ajax.request({
	                                    url : 'tag/CmsTemplet/fileNameText.do',   
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText,
	                                        versionId : '<%=v_id%>'
	                                    },
	                                    success : function(response,options){
							                 var res = Ext.util.JSON.decode(response.responseText); 
							                 if(res.success == true){
							                     isok=true;
							                 }
							             }
	                                });
	                        		return true;
	                           }
		                    }
                    ]
	           },{
                     columnWidth:.33,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '93%'
			         },
			         items: [webTempletTypeCombo]
            	}]
});

var frm_add_cmstemplet<%=u_id%> = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增 - '+'<%if(("1").equals(v_id)){out.print("wap1.x");}else{out.print("wap2.0");}%>',
        margins : '8 8 0 8',
        region : 'north',
        height : 115,
        labelWidth : 80,
        collapsible : true,
        //autoScroll:true,
        items:[field_add_cmstemplet<%=u_id%>]
});

var i_line =20;
var line =0;
for(i =1; i<i_line;i++){
   if(i==1){
      line=i;
   }else{
      line +='\n'+i;
   }
};

function show_ln<%=u_id%>(){
    var txt_ln  = document.getElementById('txt_ln<%=u_id%>');
    var txt_main  = document.getElementById('templetContentShow<%=u_id%>');
    txt_ln.scrollTop = txt_main.scrollTop;
    while(txt_ln.scrollTop != txt_main.scrollTop){
        txt_ln.value += '\n'+(i_line++);
        txt_ln.scrollTop = txt_main.scrollTop;
    }
    return;
};

function insertAtCursor<%=u_id%>(text){
    var _codeContainer = document.getElementById('templetContentShow<%=u_id%>');    
    _codeContainer.focus();
    if(document.selection){
        var sel=document.selection.createRange();
        sel.text=text;
    }else if(_codeContainer.selectionStart||_codeContainer.selectionStart=='0'){
        var startPos=_codeContainer.selectionStart;
        var endPos=_codeContainer.selectionEnd;
        var prevValue=_codeContainer.value;
        _codeContainer.value = prevValue.substring(0,startPos)+text+prevValue.substring(endPos,prevValue.length);
    }else{
        _codeContainer.value = _codeContainer.val()+text;
    }
};

var frm_add_templetContent<%=u_id%> = new Ext.FormPanel({
        layout : 'border',
        labelAlign: 'left',
        frame:true,
        margins : '8',
        region:'center',
        labelWidth : 80,
        tbar: [{	
                    text : 'loop',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '循环语名loop',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("{loop $dataList $data}\n\r\n{/loop}");               
                	}
           		},'-',{	
           		    text : 'if',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '判断语名if',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("{if ()}\n\r\n{/if}");                 
                	}
           		},'-',{	
           		    text : 'else',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '判断语名else',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("{else}");               
                	}
           		},'-',{	
           		    text : 'elseif',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '判断语名elseif',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("{else if ()}");                
                	}
           		},'-',{
           		    text : 'header',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '引用页头',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("{template \"header\"}");                   
                	}
           		},'-',{
           		    text : 'footer',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '引用页脚',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("{template \"footer\"}");                   
                	}
           		},'-',{
           		    text : 'include',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '引用文件',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("<"+"%@ include file"+"=\"xxx.jsp\"%>");                   
                	}
           		},'-',{
           		    text : 'amp',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: 'URL连接符',
                	handler : function() {
             		    insertAtCursor<%=u_id%>("[&]");                    
                	}
           		},'-',{
           		    text : 'reset',
        			minWidth: 55,
        			iconCls : 'icon-ref',
        			tooltip: '重置文本框',
                	handler : function() {
             		    frm_add_templetContent<%=u_id%>.form.reset();        
                	}
           		}
        ],
        items:[{
                layout: 'fit',
                border:false,
                region : 'west',
                width:40,
                defaults: {height: '100%'},
                items: [{
                    xtype:'textarea',
                    id:'txt_ln<%=u_id%>',
                    name: 'txt_ln<%=u_id%>',
                    hideLabel:true,
                    //autoHeight : false,
                    anchor:'100%',
                    style:'overflow:hidden;scrolling:yes;font-family:Fixedsys,verdana,宋体;font-size:12px;color:#0000FF;background-color:#eeeeee;',
                    disabled :true,
                    value:line
               }]
       },{
                border:false,
                region:'center',
                defaults: {height: '100%',width:'100%'},
                items: [{
                    xtype:'textarea',
                    //xtype:'htmleditor',
                    id:'templetContentShow<%=u_id%>',
                    name: 'templetContentShow<%=u_id%>',
                    hideLabel:true,
                    autoHeight : false,
                    anchor:'100%',
                    value:'',
                    style:'font-family:Fixedsys,verdana,宋体;font-size:12px;',
                    listeners:{
                        render:function(ta){
                            ta.el.dom.onscroll = function(){
                            var t= this.scrollTop; 
                                 show_ln<%=u_id%>();
                            };
                        }
                    }
               }]
       }]
});

var tree_rpwestroot<%=u_id%> = new Ext.tree.AsyncTreeNode({
			//id : 'treerpwestroot',
			text : '插入标签',
			iconCls : 'icon-treeg'
});

var tree_rpwestpanel<%=u_id%> = new Ext.tree.TreePanel({
			//id : 'tree_rpwestid',
			iconCls : 'icon-undo',
			border : false,
			animate : false,//不以动画形式伸展,收缩子节
			rootVisible : false,//是否显示根节点
			autoHeight : false,
			layout: 'fit',
			loader : new Ext.tree.TreeLoader(),
			root : tree_rpwestroot<%=u_id%>
			
});

tree_rpwestpanel<%=u_id%>.on('beforeload', function(node) {
	tree_rpwestpanel<%=u_id%>.loader.dataUrl = 'tag/CmsTemplet/getTagTree.do?versionId='+<%=v_id%>+'&tag='+tag;
});

tree_rpwestpanel<%=u_id%>.on('click', function(node) {
            insertAtCursor<%=u_id%>(node.attributes['arr']);
});

var rpwestpanelFilter = new Ext.tree.TreeFilter(tree_rpwestpanel<%=u_id%>, {
		clearBlank : true,
		autoClear : true
	});

	var rpwestpanelHiddenPkgs = [];
	function filterwestpanel(e) {
		if (e.getKey() == 13) {
			var text = e.target.value;
			Ext.each(rpwestpanelHiddenPkgs, function(n) {
				n.ui.show();
			});
			if (!text) {
				rpwestpanelFilter.clear();
				return;
			}
			tree_rpwestpanel<%=u_id%>.expandAll();
			var re = new RegExp(Ext.escapeRe(text), 'i');
			rpwestpanelFilter.filterBy(function(n) {
				return !n.isLeaf() || re.test(n.text);
			});
			// west empty packages that weren't filtered
			tree_rpwestpanel<%=u_id%>.root.cascade(function(n) {
				if (n.id != tree_rpwestpanel<%=u_id%>.root.id) {
					if (!n.isLeaf() && hasChild(n, re) == false) {
						n.ui.hide();
						rpwestpanelHiddenPkgs.push(n);
					}
				}
			});
		}
	};
	// 查找该节点下是否有符合条件的子节点
	function hasChild(n, re) {
		var str = false;
		n.cascade(function(n1) {
			if (re.test(n1.text)) {
				str = true;
				return;
			}
		});
		return str;
	};

var tree_rpcenteroot<%=u_id%> = new Ext.tree.AsyncTreeNode({
			//id : 'treerpcenteroot',
			text : '插入变量',
			iconCls : 'icon-treeg'
});

var tree_rpcenterpanel<%=u_id%> = new Ext.tree.TreePanel({
			//id : 'tree_rpcenterid',
			iconCls : 'icon-undo',
			border : false,
			animate : false,//不以动画形式伸展,收缩子节
			rootVisible : false,//是否显示根节点
			checkModel : "single",
			autoHeight : false,
			layout: 'fit',
			loader : new Ext.tree.TreeLoader(),
			root : tree_rpcenteroot<%=u_id%>
});

tree_rpcenterpanel<%=u_id%>.on('beforeload', function(node) {
	tree_rpcenterpanel<%=u_id%>.loader.dataUrl = '/mmbook/inc/cmstemplet/'+extern+'.json';
});

tree_rpcenterpanel<%=u_id%>.on('click', function(node) {
     insertAtCursor<%=u_id%>(node.attributes['arr']);
});

var rpcenterpanelFilter = new Ext.tree.TreeFilter(tree_rpcenterpanel<%=u_id%>, {
		clearBlank : true,
		autoClear : true
	});

	var rpcenterpanelHiddenPkgs = [];
	function filtercenterpanel(e) {
		if (e.getKey() == 13) {
			var text = e.target.value;
			Ext.each(rpcenterpanelHiddenPkgs, function(n) {
				n.ui.show();
			});
			if (!text) {
				rpcenterpanelFilter.clear();
				return;
			}
			tree_rpcenterpanel<%=u_id%>.expandAll();
			var re = new RegExp(Ext.escapeRe(text), 'i');
			rpcenterpanelFilter.filterBy(function(n) {
				return !n.isLeaf() || re.test(n.text);
			});
			// west empty packages that weren't filtered
			tree_rpcenterpanel<%=u_id%>.root.cascade(function(n) {
				if (n.id != tree_rpcenterpanel<%=u_id%>.root.id) {
					if (!n.isLeaf() && hasChild(n, re) == false) {
						n.ui.hide();
						rpcenterpanelHiddenPkgs.push(n);
					}
				}
			});
		}
	};

var tabPanel<%=u_id%> = new Ext.TabPanel({
		//id : 'tabPanel',
		region : 'east',
		width : 190,
		deferredRender : false,
		enableTabScroll : true,
		activeTab : 0,
		margins : '8 8 8 0',
		defaults : {
			autoScroll : true
		},
		items : [{
                title:'插入标签',
                layout:'form',
                defaults: {width: 171},
                defaultType: 'textfield',
                items: [tree_rpwestpanel<%=u_id%>],
                bbar : [new Ext.form.TextField({
			        width : 160,
	        		emptyText : '快速查找 输入后按回车',
	        		listeners : {
		        		render : function(f) {
		        			f.el.on('keydown', filterwestpanel, f, {
		        				buffer : 350
		        			});
		        		}
		        	}
		        })],
                tbar : [tagCombo]
            },{
                title:'插入变量',
                layout:'form',
                defaults: {width: 171},
                defaultType: 'textfield',
                items: [tree_rpcenterpanel<%=u_id%>],
                bbar : [new Ext.form.TextField({
			        width : 160,
	        		emptyText : '快速查找 输入后按回车',
	        		listeners : {
		        		render : function(f) {
		        			f.el.on('keydown', filtercenterpanel, f, {
		        				buffer : 350
		        			});
		        		}
		        	}
		        })],
                tbar : [externCombo]
            }]
});

var panel_add_templetContent<%=u_id%> = new Ext.Panel({
        region:'center',
  		border : false,
  		layout : 'border',
        items:[frm_add_templetContent<%=u_id%>,tabPanel<%=u_id%>],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_cmstemplet<%=u_id%>.form.isValid() == false){
	    		    return;
	  		    }
	  		    if(frm_add_templetContent<%=u_id%>.form.findField('templetContentShow<%=u_id%>').getValue()==""){
	  		        Ext.Msg.show({title: '提示', msg: '模板页面内容不能为空!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
	  		        return;
	  		    }
	  		    frm_add_cmstemplet<%=u_id%>.form.findField('templetContentString').setValue(frm_add_templetContent<%=u_id%>.form.findField('templetContentShow<%=u_id%>').getValue());
	  		    Ext.MessageBox.confirm('确认操作','确定保存模板页面?',function(btn){
  	            if (btn == 'yes'){
	  		    frm_add_cmstemplet<%=u_id%>.form.submit({
	   		    url:'tag/CmsTemplet/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          <% if("1".equals(v_id)){%>
  	   		              if (!(typeof(pag_cmstemplet1011110) == "undefined")) {
			                  pag_cmstemplet1011110.doLoad(0);
			              }
  	   		          <%}else{%>
  	   		              if (!(typeof(pag_cmstemplet1012110) == "undefined")) {
			                  pag_cmstemplet1012110.doLoad(0);
			              }
  	   		          <%}%>
	 		       },
	 		     scope:this,
	 		     failure:function(form,action){
	 		          Ext.Msg.show({title: '错误', msg: action.result.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
	   		       }
	  		     })}
  	  		     },this);
	   		    }
	     },{
  	            text:'关闭',
  	            style : 'margin-left: 8px',
  	            handler:function(){
  	              Ext.MessageBox.confirm('确认操作','确定关闭模板页面?',function(btn){
  	                if (btn == 'yes'){
  				        panel<%=u_id%>.remove(panel<%=u_id%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }]
});

var panel_add_cmstemplet<%=u_id%> = new Ext.Panel({
		renderTo:'add_cmstempletdiv<%=u_id%>',
		width:Ext.get("add_cmstempletdiv<%=u_id%>").getWidth(),
		height:Ext.get("add_cmstempletdiv<%=u_id%>").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_cmstemplet<%=u_id%>,panel_add_templetContent<%=u_id%>]
});

panel<%=u_id%>.on('beforeremove', function(tab, item) {
		if(item.id=='<%=u_id%>'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_add_cmstemplet<%=u_id%>.destroy();
		    }
		}
});

panel<%=u_id%>.on('resize',function(){
    if(Ext.get("add_cmstempletdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='add_CmsTemplet<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('add_CmsTemplet<%=u_id%>');
             panel_add_cmstemplet<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_cmstemplet<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_add_cmstemplet<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_cmstemplet<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmstemplet.add<%=u_id%>();
</script>
</head>
<body>
<div id="add_cmstempletdiv<%=u_id%>" style="width:100%;height:100%;"></div>
</body>
</html>