<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>栏目新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitepart");
Ext.ux.sitepart.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');

// 是否有效 :0 无效．1 有效 下拉框
var ds_rating_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['0','无效'],['1','有效']]
});

// 是否有下级节点
/*var ds_part_parent_id_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['0','无'],['1','有']]
});*/

//选择站点分类
var comboBoxCheckTree = new Ext.ux.ComboBoxCheckTree({
		fieldLabel: '站点分类(只选未节点)',
		width : 300,
		height : 150,
		hiddenName: 'softid',
		tree : {
			xtype:'treepanel',
			height:100,
			checkModel: 'multiple',    //多选: 'multiple'(默认)单选: 'single'级联多选: 'cascade'(同时选父和子);'parentCascade'(选父);'childCascade'(选子)
			onlyLeafCheckable: false, 
			animate: true,             //设置为true以启用展开 收缩时的动画效果
			rootVisible: true,
			autoScroll:true,  
			loader: new Ext.tree.TreeLoader({dataUrl:'manage/SiteContentSort/getTreeCombox.do?partId='+0,   
	        baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
			root : new Ext.tree.AsyncTreeNode({id:'1',text:'网站分类列表'})
			},
		selectValueModel:'leaf' //all 多选, folder选父节点  leaf选子节点
				
});

var field_add_sitepart = new Ext.form.FieldSet({
    id : 'field_add_sitepart',
	title : '新增',
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
		                        fieldLabel: '栏目名称',
		                        allowBlank : false,
		                        name:'partName'
		                        }
	                        ,{   
						        xtype: 'treeField',   
						        name: 'pigSource',   
						        fieldLabel: '父栏目选择',   
						        emptyText:'请选择...',
						        forceSelection : true ,
						        listWidth:200,   
						        listHeight:200,
						        valueField : 'id',       
								displayField : 'text', 
						        readOnly:false,
						        dataUrl:'manage/SitePart/getSynTree.do'  
				               },{
		                    	xtype:'numberfield',
		                        fieldLabel: '排序值',
		                        regex:/^\d+$/,
		                        regexText:"只能输入整数",
		                        name:'sortValue'
	                        },{
		                    	xtype:'textfield',
		                        fieldLabel: '栏目说明',
		                        name:'partNotes'
		                   }]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                    	xtype:'textfield',
	                        fieldLabel: '显示方式',
	                        regex:/^\d+$/,
	                        regexText:"只能输入整数",
	                        name:'showType'
	                       }  
	                      ,{
								xtype : 'combo',   // xtype 类型 combo 对应: Ext.form.ComboBox
								fieldLabel : '功能等级',  //标题名称
								emptyText : '请选择...',
								allowBlank : false,     //是否必选 false 心选 , true 可选
								triggerAction : 'all',
								forceSelection : true ,   //如果为true，将限制选择的值必须是下拉列表中的值。 如果为false，允许用户设置任意值到输入栏 (默认值为 false) 
								store : ds_rating_data,   //加载 store
								hiddenName :'effective',  // 查询条件名称
								valueField : 'id',        // 对应 ds_rating_data id
								displayField : 'val',     //对应 ds_rating_data val
								editable : false,
								mode : 'local'  // local 本地 ,  remote 远程
						     
                           },
                           	comboBoxCheckTree
                           ]
	 }]
});

var frm_add_sitepart = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 150,
        items:[field_add_sitepart],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitepart.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitepart.form.submit({
	   		    url:'manage/SitePart/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitepart) == "undefined")) {
			            pag_sitepart.doLoad(0);
			            //field_add_sitepart.doLoad(0);//刷新栏目选择下拉树
			          }
			          panel.remove(panel.getActiveTab());//关闭面板
			          fun_tab_rolepermission("","栏目列表","admin/manage/SitePart/index.jsp");
	 		       },
	 		     scope:this,
	 		     failure:function(form,action){
	 		          Ext.Msg.show({title: '错误', msg: action.result.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
	   		       }
	  		     }) 
	   		    }
	     },{
  	            text:'关闭',
  	            xtype : 'easyButton',
  	            style : 'margin-left: 8px',
  	            handler:function(){
  				    panel.remove(panel.getActiveTab());
  	   		    }
  	     },{
				xtype : 'easyButton',
				text : '清 空',
				style : 'margin-left: 8px',
				handler:function(){
				    frm_add_sitepart.getForm().reset();
				}
			}] 
});

var panel_add_sitepart = new Ext.Panel({
		renderTo:'add_sitepartdiv',
		width:Ext.get("add_sitepartdiv").getWidth(),
		height:Ext.get("add_sitepartdiv").getHeight()-15,
		border : false,
		layout : 'border',
        items:[frm_add_sitepart]
});

panel.on('resize',function(){
    if(Ext.get("add_SitePartdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131103'){
             panel.setActiveTab('1131103');
             panel_add_sitepart.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_sitepart.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitepart.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_sitepart.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

function fun_tab_rolepermission(id,code,url){// 打开TAB
        var tab = panel.findById('1131104');
        if (tab){
              panel.setActiveTab('1131104');
            }else{
                var IFramePanel = new Ext.Panel({
				id : '1131104',
				title : code,
				iconCls : 'icon-nav-p1',
				//layout:'fit',
				height : '100%',
				closable : true,
				//border:false,
				autoScroll : true,
				margins : '5 5 5 5'
			});
				panel.add(IFramePanel);
				panel.setActiveTab(IFramePanel);
				IFramePanel.load({
					url : url,
					callback : function(r, options, success) {
						if (success.status == 404) {
							IFramePanel.load({
								url : "LoginAction!errorMenu.action"
							});
						}
						//storeLoadMask.hide();
					},
					scope : this, // optional scope for the callback
					discardUrl : true,
					nocache : true,
					text : "页面加载中,请稍候……",
					scripts : true
				});
         }      
}

},Ext.util.Observable);
new Ext.ux.sitepart.add();
</script>
</head>
<body>
<div id="add_sitepartdiv" style="width:100%;height:100%;"></div>
</body>
</html>