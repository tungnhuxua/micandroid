<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@ page import= "cn.mmbook.platform.model.tag.CmsTemplet"%> 

<%
System.out.println("xxxxxxxxx");
	CmsTemplet cmsTemplet =(CmsTemplet)request.getAttribute("cmsTemplet");
%>
<html>
<head>
<title>修改页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.cmstemplet");
Ext.ux.cmstemplet.update<%=cmsTemplet.getId()%>=Ext.extend(function(){
var panel<%=cmsTemplet.getId()%> = Ext.getCmp('center-tab-panel');
var tag ='1';
var extern ='user';
var templetName ='<%=cmsTemplet.getTempletName()%>';

var tagStore = new Ext.data.SimpleStore({
	fields : ['id','mc'],
	data : [['1','内容标签'],['2','栏目标签'],['3','内容推荐标签'],['4','列表标签']]
});

var tagCombo = new Ext.form.ComboBox({
    id:'tagCombo',
	emptyText : '请选择...',
	//allowBlank : false,
	triggerAction : 'all',
	hiddenName :'tagCombo',
	store : tagStore,
	valueField : 'id',
	displayField : 'mc',
	editable : false,
	value : '1',
	mode: 'local',
	listeners : {
        select : function(combo){
            tag =combo.value;
            tree_rpwestroot<%=cmsTemplet.getId()%>.reload(); // 重刷整树
        }
   }
});

var externStore = new Ext.data.SimpleStore({
	fields : ['id','mc'],
	data : [['user','用户信息'],['config','配置文件'],['cmscategory','栏目数据']]
});

var externCombo = new Ext.form.ComboBox({
    id:'externCombo',
	emptyText : '请选择...',
	//allowBlank : false,
	triggerAction : 'all',
	hiddenName :'externCombo',
	store : externStore,
	valueField : 'id',
	displayField : 'mc',
	editable : false,
	width : 160,
	value : 'user',
	mode: 'local',
	listeners : {
        select : function(combo){
            extern =combo.value;
            tree_rpcenteroot<%=cmsTemplet.getId()%>.reload(); // 重刷整树
        }
   }
});

var field_update_cmstemplet<%=cmsTemplet.getId()%> = new Ext.form.FieldSet({
	//title : '模板信息',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '68%'
			         },
			         items: [{
				        	xtype:'hidden',
				        	fieldLabel:'主键id',
				        	name:'id',
				        	value:'<%=cmsTemplet.getId()%>'
			             },{
				         xtype:'hidden',
				         fieldLabel:'模板版本ID',
				         name:'versionId',
				         value:'<%=cmsTemplet.getVersionId()%>'
			            },{
	                    	xtype:'textfield',
	                        fieldLabel: '模板类型',
	                        name:'webTempletType',
	                        cls:'noborder3',
	                        readOnly:true,
	                        value:'<%if(("10").equals(cmsTemplet.getWebTempletType())){out.print("首页");
	                        }else if(("11").equals(cmsTemplet.getWebTempletType())){out.print("栏目首页");
	                        }else if(("12").equals(cmsTemplet.getWebTempletType())){out.print("列表页");
	                        }else if(("13").equals(cmsTemplet.getWebTempletType())){out.print("详细页");
	                        }else if(("14").equals(cmsTemplet.getWebTempletType())){out.print("下载页");
	                        }else if(("15").equals(cmsTemplet.getWebTempletType())){out.print("预览页");
	                        }else if(("16").equals(cmsTemplet.getWebTempletType())){out.print("章节列表页");
	                        }else if(("17").equals(cmsTemplet.getWebTempletType())){out.print("搜索页");
	                        }else if(("18").equals(cmsTemplet.getWebTempletType())){out.print("其它页");
	                        }else if(("20").equals(cmsTemplet.getWebTempletType())){out.print("栏目标签");
	                        }else if(("21").equals(cmsTemplet.getWebTempletType())){out.print("内容标签");
	                        }else if(("22").equals(cmsTemplet.getWebTempletType())){out.print("推荐位标签");
	                        }%>'
	                     },{
		                    	xtype:'textfield',
		                        fieldLabel: '模板名称',
		                        name:'templetName',
								invalidText : '模板名称已经被使用!', 
		                        allowBlank : false,
		                        minLength: 2,
								maxLength: 16,
								value:templetName,
		                        validationEvent : 'blur',
		                        //regex:/^[\w\u4E00-\u9FFF]+$/,
		                        //regexText:'输入中文、数字和英文',
								validator : function(thisText) {
							        var isok =false;
							        if(templetName!=thisText){
	                       	         Ext.Ajax.request({
	                                    url : 'tag/CmsTemplet/templetNameText.do', 
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText,
	                                        versionId : <%=cmsTemplet.getVersionId()%>
	                                    },
	                                     success : function(response,options){
							                  var res = Ext.util.JSON.decode(response.responseText); 
							                  if(res.success == true){  
							                     isok=true;
							                  }
							             }
	                                });
	                                }else{
	                                   isok=true;
	                                }
	                        		return true;
	                           }
		             }]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                    	xtype:'textfield',
	                        fieldLabel: '模板版本',
	                        name:'versionIdString',
	                        cls:'noborder3',
	                        readOnly:true,
	                        value:'<%if(("1").equals(cmsTemplet.getVersionId())){out.print("wap1.x");}else{out.print("wap2.0");}%>'
	                     },{
					        xtype:'textfield',
					        fieldLabel: '文件名',
					        name:'fileName',
					        cls:'noborder3',
					        readOnly:true,
					        value:'<%=cmsTemplet.getFileName()%>'
					},{
				        	xtype:'hidden',
				        	fieldLabel:'模板内容',
				        	name:'templetContentString'
			             }]
          }]
});

var frm_update_cmstemplet<%=cmsTemplet.getId()%> = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改 - '+'<%if(("1").equals(cmsTemplet.getVersionId())){out.print("wap1.x");}else{out.print("wap2.0");}%>',
        margins : '8 8 0 8',
        region : 'north',
        height : 115,
        labelWidth : 80,
        collapsible : true,
        items: [field_update_cmstemplet<%=cmsTemplet.getId()%>]
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

function show_ln<%=cmsTemplet.getId()%>(){
    var txt_ln  = document.getElementById('txt_ln<%=cmsTemplet.getId()%>');
    var txt_main  = document.getElementById('templetContentShow<%=cmsTemplet.getId()%>');
    txt_ln.scrollTop = txt_main.scrollTop;
    while(txt_ln.scrollTop != txt_main.scrollTop){
        txt_ln.value += '\n'+(i_line++);
        txt_ln.scrollTop = txt_main.scrollTop;
    }
    return;
};

function insertAtCursor<%=cmsTemplet.getId()%>(text){
    var _codeContainer = document.getElementById('templetContentShow<%=cmsTemplet.getId()%>');    
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

var frm_update_templetContent<%=cmsTemplet.getId()%> = new Ext.FormPanel({
        layout : 'border',
        labelAlign: 'left',
        frame:true,
        //title: '修改模板',
        margins : '8',
        region:'center',
        labelWidth : 80,
        tbar: [{	
                    text : 'loop',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '循环语名loop',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("{loop $dataList $data}\n\r\n{/loop}");               
                	}
           		},'-',{	
           		    text : 'if',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '判断语名if',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("{if ()}\n\r\n{/if}");                 
                	}
           		},'-',{	
           		    text : 'else',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '判断语名else',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("{else}");               
                	}
           		},'-',{	
           		    text : 'elseif',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '判断语名elseif',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("{else if ()}");                
                	}
           		},'-',{
           		    text : 'header',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '引用页头',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("{template \"header\"}");                   
                	}
           		},'-',{
           		    text : 'footer',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '引用页脚',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("{template \"footer\"}");                   
                	}
           		},'-',{
           		    text : 'include',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: '引用文件',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("<"+"%@ include file"+"=\"xxx.jsp\"%>");                   
                	}
           		},'-',{
           		    text : 'amp',
        			minWidth: 55,
        			iconCls : 'icon-editp',
        			tooltip: 'URL连接符',
                	handler : function() {
             		    insertAtCursor<%=cmsTemplet.getId()%>("[&]");                    
                	}
           		},'-',{
           		    text : 'reset',
        			minWidth: 55,
        			iconCls : 'icon-ref',
        			tooltip: '重置文本框',
                	handler : function() {
             		    frm_update_templetContent<%=cmsTemplet.getId()%>.form.reset();        
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
                    id:'txt_ln<%=cmsTemplet.getId()%>',
                    name: 'txt_ln<%=cmsTemplet.getId()%>',
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
                    id:'templetContentShow<%=cmsTemplet.getId()%>',
                    name: 'templetContentShow<%=cmsTemplet.getId()%>',
                    hideLabel:true,
                    autoHeight : false,
                    anchor:'100%',
                    value:'',
                    style:'font-family:Fixedsys,verdana,宋体;font-size:12px;',
                    listeners:{
                        render:function(ta){
                            ta.el.dom.onscroll = function(){
                            var t= this.scrollTop; 
                                 show_ln<%=cmsTemplet.getId()%>();
                            };
                        }
                    }
               }]
       }]
});

var tree_rpwestroot<%=cmsTemplet.getId()%> = new Ext.tree.AsyncTreeNode({
			//id : 'treerpwestroot',
			text : '插入标签',
			iconCls : 'icon-treeg'
});

var tree_rpwestpanel<%=cmsTemplet.getId()%> = new Ext.tree.TreePanel({
			//id : 'tree_rpwestid',
			iconCls : 'icon-undo',
			border : false,
			animate : false,//不以动画形式伸展,收缩子节
			rootVisible : false,//是否显示根节点
			autoHeight : false,
			layout: 'fit',
			loader : new Ext.tree.TreeLoader(),
			root : tree_rpwestroot<%=cmsTemplet.getId()%>
});

tree_rpwestpanel<%=cmsTemplet.getId()%>.on('beforeload', function(node) {
	tree_rpwestpanel<%=cmsTemplet.getId()%>.loader.dataUrl = 'tag/CmsTemplet/getTagTree.do?versionId='+<%=cmsTemplet.getVersionId()%>+'&tag='+tag;
});

tree_rpwestpanel<%=cmsTemplet.getId()%>.on('click', function(node) {
            insertAtCursor<%=cmsTemplet.getId()%>(node.attributes['arr']);
});

var rpwestpanelFilter = new Ext.tree.TreeFilter(tree_rpwestpanel<%=cmsTemplet.getId()%>, {
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
			tree_rpwestpanel<%=cmsTemplet.getId()%>.expandAll();
			var re = new RegExp(Ext.escapeRe(text), 'i');
			rpwestpanelFilter.filterBy(function(n) {
				return !n.isLeaf() || re.test(n.text);
			});
			// west empty packages that weren't filtered
			tree_rpwestpanel<%=cmsTemplet.getId()%>.root.cascade(function(n) {
				if (n.id != tree_rpwestpanel<%=cmsTemplet.getId()%>.root.id) {
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

var tree_rpcenteroot<%=cmsTemplet.getId()%> = new Ext.tree.AsyncTreeNode({
			//id : 'treerpcenteroot',
			text : '插入变量',
			iconCls : 'icon-treeg'
});

var tree_rpcenterpanel<%=cmsTemplet.getId()%> = new Ext.tree.TreePanel({
			//id : 'tree_rpcenterid',
			iconCls : 'icon-undo',
			border : false,
			animate : false,//不以动画形式伸展,收缩子节
			rootVisible : false,//是否显示根节点
			autoHeight : false,
			layout: 'fit',
		    loader : new Ext.tree.TreeLoader(),
			root : tree_rpcenteroot<%=cmsTemplet.getId()%>
});

tree_rpcenterpanel<%=cmsTemplet.getId()%>.on('beforeload', function(node) {
	tree_rpcenterpanel<%=cmsTemplet.getId()%>.loader.dataUrl = '/mmbook/inc/cmstemplet/'+extern+'.json';
});

tree_rpcenterpanel<%=cmsTemplet.getId()%>.on('click', function(node) {
            insertAtCursor<%=cmsTemplet.getId()%>(node.attributes['arr']);
});

var rpcenterpanelFilter = new Ext.tree.TreeFilter(tree_rpcenterpanel<%=cmsTemplet.getId()%>, {
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
			tree_rpcenterpanel<%=cmsTemplet.getId()%>.expandAll();
			var re = new RegExp(Ext.escapeRe(text), 'i');
			rpcenterpanelFilter.filterBy(function(n) {
				return !n.isLeaf() || re.test(n.text);
			});
			// west empty packages that weren't filtered
			tree_rpcenterpanel<%=cmsTemplet.getId()%>.root.cascade(function(n) {
				if (n.id != tree_rpcenterpanel<%=cmsTemplet.getId()%>.root.id) {
					if (!n.isLeaf() && hasChild(n, re) == false) {
						n.ui.hide();
						rpcenterpanelHiddenPkgs.push(n);
					}
				}
			});
		}
	};
	
var tabPanel<%=cmsTemplet.getId()%> = new Ext.TabPanel({
		id : 'tabPanel<%=cmsTemplet.getId()%>',
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
                items: [tree_rpwestpanel<%=cmsTemplet.getId()%>],
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
                items: [tree_rpcenterpanel<%=cmsTemplet.getId()%>],
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
	
var panel_update_templetContent<%=cmsTemplet.getId()%> = new Ext.Panel({
        region:'center',
  		border : false,
  		layout : 'border',
        items:[frm_update_templetContent<%=cmsTemplet.getId()%>,tabPanel<%=cmsTemplet.getId()%>],
        buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_cmstemplet<%=cmsTemplet.getId()%>.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    if(frm_update_templetContent<%=cmsTemplet.getId()%>.form.findField('templetContentShow<%=cmsTemplet.getId()%>').getValue()==""){
	  		        Ext.Msg.show({title: '提示', msg: '模板页面内容不能为空!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
	  		        return;
	  		    }
  	  		    frm_update_cmstemplet<%=cmsTemplet.getId()%>.form.findField('templetContentString').setValue(frm_update_templetContent<%=cmsTemplet.getId()%>.form.findField('templetContentShow<%=cmsTemplet.getId()%>').getValue());
  	  		    //Ext.Msg.show({title: '提示', msg: frm_update_cmstemplet.form.findField('templetContentString').getValue(),icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  	  		    //return;
  	  		    Ext.MessageBox.confirm('确认操作','确定修改模板页面?',function(btn){
  	            if (btn == 'yes'){
  	  		    frm_update_cmstemplet<%=cmsTemplet.getId()%>.form.submit({
  	   		    url:'tag/CmsTemplet/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  	   		          <% if("1".equals(cmsTemplet.getVersionId())){%>
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
  				        panel<%=cmsTemplet.getId()%>.remove(panel<%=cmsTemplet.getId()%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }]
});

var panel_update_cmstemplet<%=cmsTemplet.getId()%> = new Ext.Panel({
  		renderTo:'update_cmstempletdiv<%=cmsTemplet.getId()%>',
  		width:Ext.get("update_cmstempletdiv<%=cmsTemplet.getId()%>").getWidth(),
  		height:Ext.get("update_cmstempletdiv<%=cmsTemplet.getId()%>").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_cmstemplet<%=cmsTemplet.getId()%>,panel_update_templetContent<%=cmsTemplet.getId()%>]
});

var templetContentStringhidden =document.getElementById("updatecmstempletdival<%=cmsTemplet.getId()%>").value;
frm_update_templetContent<%=cmsTemplet.getId()%>.form.findField('templetContentShow<%=cmsTemplet.getId()%>').setRawValue(templetContentStringhidden);

panel<%=cmsTemplet.getId()%>.on('beforeremove', function(tab, item) {
		if(item.id=='update_CmsTemplet<%=cmsTemplet.getId()%>'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_cmstemplet<%=cmsTemplet.getId()%>.destroy();
		    }
		}
});

panel<%=cmsTemplet.getId()%>.on('resize',function(){
    if(Ext.get("update_cmstempletdiv<%=cmsTemplet.getId()%>")){
        var p =panel<%=cmsTemplet.getId()%>.getActiveTab().getId();
        if(p!='update_CmsTemplet<%=cmsTemplet.getId()%>'){
             panel<%=cmsTemplet.getId()%>.setActiveTab('update_CmsTemplet<%=cmsTemplet.getId()%>');
             panel_update_cmstemplet<%=cmsTemplet.getId()%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_cmstemplet<%=cmsTemplet.getId()%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel<%=cmsTemplet.getId()%>.setActiveTab(p);
        }else{
            panel_update_cmstemplet<%=cmsTemplet.getId()%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_cmstemplet<%=cmsTemplet.getId()%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmstemplet.update<%=cmsTemplet.getId()%>();
</script>
</head>
<body>
<div id="update_cmstempletdiv<%=cmsTemplet.getId()%>" style="width:100%;height:100%;"></div>
<div id="updatecmstempletdiv<%=cmsTemplet.getId()%>" style="display: none;"><textarea id ="updatecmstempletdival<%=cmsTemplet.getId()%>" style='font-family:Fixedsys,verdana,宋体;font-size:12px;'><% if(cmsTemplet.getTempletContentString()!=null){out.print(cmsTemplet.getTempletContentString());}%></textarea></div>
</body>
</html>