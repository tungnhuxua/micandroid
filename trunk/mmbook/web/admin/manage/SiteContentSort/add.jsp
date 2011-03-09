<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>网站内容分类新增页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitecontentsort");

Ext.ux.sitecontentsort.add=Ext.extend(function(){

var panel = Ext.getCmp('center-tab-panel');

var field_add_base = new Ext.form.FieldSet({
    id : 'field_add_base',
	title : '基础数据',
	autoHeight : true,
	layout: 'fit',
    layout:'column',
    items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [{
		                    	xtype:'textfield',
		                        fieldLabel: '名称',
		                        name:'classifyName',
		                        regex:/^[\w\u4E00-\u9FFF]+$/,
		                        regexText:'输入中文、数字和英文',
		                        allowBlank : false,
		                        minLength: 2,
		                        maxLength: 10
		                        },{
		                    	 xtype:'textfield',
		                         fieldLabel: '别名',
		                         name:'sortByname',
		                         regex:/^[\w\u4E00-\u9FFF]+$/,
		                         regexText:'输入中文、数字和英文',
		                         maxLength: 30
		                        }
	                        	,{
		                    	xtype:'numberfield',
		                        fieldLabel: '排序值',
		                        name:'sortValue'
	                        },{
	                    	 xtype:'textfield',
	                         fieldLabel: '备注',
	                         name:'classifyNotes'
	                        },{
	                    	 xtype:'textfield',
	                         fieldLabel: 'logo_url_',
	                         name:'logoUrl'
	                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
								xtype : 'combo',
								fieldLabel : '是否有效',
								emptyText : '请选择',
								//allowBlank : false,
								triggerAction : 'all',
								hiddenName :'effective',
                                store : new Ext.data.SimpleStore({ 
                                   //填充的数据
									fields : ['val','tex'],
									data : [['1','有效'],['0','无效']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        },{
								xtype : 'combo',
								fieldLabel : '分类性质',
								emptyText : '请选择',
								triggerAction : 'all',
								hiddenName :'classifyNature',
                                store : new Ext.data.SimpleStore({
									fields : ['val','tex'],
									data : [['1','性质1'],['2','性质2']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        },{
	                    	 xtype:'textfield',
	                         fieldLabel: '展示模型',
	                         name:'modelId'
	                        }
	                         ,{
	                    	 xtype:'hidden',
	                         fieldLabel: '上节点',
	                         name:'parentId'
	                        },{
	                    	 xtype:'hidden',
	                         fieldLabel: '网站栏目',
	                         name:'sitePartIds',
	                         value:'1,2'
	                        }
                     ]
	 }]
});

//选择栏目
var comboBoxCheckTree = new Ext.ux.ComboBoxCheckTree({
		fieldLabel: '网站栏目(只选未节点)',
		width : 300,
		height : 150,
		hiddenName: 'comboBoxvalue',
		tree : {
			xtype:'treepanel',
			height:100,
			checkModel: 'multiple',    //多选: 'multiple'(默认)单选: 'single'级联多选: 'cascade'(同时选父和子);'parentCascade'(选父);'childCascade'(选子)
			onlyLeafCheckable: false, 
			animate: true,             //设置为true以启用展开 收缩时的动画效果
			rootVisible: true,
			autoScroll:true,  
			loader: new Ext.tree.TreeLoader({dataUrl:'manage/SitePart/getSynTree.do?partId='+0,   
	        baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
			root : new Ext.tree.AsyncTreeNode({id:'1',text:'栏目列表'})
			},
		selectValueModel:'leaf' //all 多选, folder选父节点  leaf选子节点
				
});
comboBoxCheckTree.on('change', function(box, newvar, oldvar){
  frm_add_sitecontentsort.form.findField('sitePartIds').setValue(comboBoxCheckTree.getValue()); 
});

var field_add_other = new Ext.form.FieldSet({
    id : 'field_add_other',
	title : '关联数据',
	autoHeight : true,
	layout: 'fit',
    layout:'column',
    items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [ {   
						        xtype: 'treeField',   
						        name: 'parentIds',   
						        fieldLabel: '上节点选择',   
						        emptyText:'请选择...',
						        forceSelection : true ,
						        listWidth:200,   
						        listHeight:200,
						        valueField : 'id',       
								displayField : 'text', 
						        readOnly:false,
						        dataUrl:'manage/SiteContentSort/getTreeCombox.do'
				                ,listeners: {
									'select': function(node) {
										frm_add_sitecontentsort.form.findField('parentId').setValue(frm_add_sitecontentsort.form.findField('parentIds').getValue());
									}
								}       
				               }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [comboBoxCheckTree ]
	 }]
});


var frm_add_sitecontentsort = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_base,field_add_other],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitecontentsort.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitecontentsort.form.submit({
	   		    url:'manage/SiteContentSort/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitecontentsort) == "undefined")) {
			            pag_sitecontentsort.doLoad(0);
			          }
	 		       },
	 		     scope:this,
	 		     failure:function(form,action){
	 		          Ext.Msg.show({title: '错误', msg: action.result.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
	   		       }
	  		     }) 
	   		    }
	     },{
  	            text:'关闭',
  	            style : 'margin-left: 8px',
  	            handler:function(){
  				    panel.remove(panel.getActiveTab());
  	   		    }
  	     }] 
});

var panel_add_sitecontentsort = new Ext.Panel({
		renderTo:'add_sitecontentsortdiv',
		width:Ext.get("add_sitecontentsortdiv").getWidth(),
		height:Ext.get("add_sitecontentsortdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitecontentsort]
});

panel.on('resize',function(){
    if(Ext.get("add_SiteContentSortdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131106'){
             panel.setActiveTab('1131106');
             panel_add_sitecontentsort.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_sitecontentsort.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitecontentsort.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_sitecontentsort.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitecontentsort.add();
</script>
</head>
<body>
<div id="add_sitecontentsortdiv" style="width:100%;height:100%;"></div>
</body>
</html>