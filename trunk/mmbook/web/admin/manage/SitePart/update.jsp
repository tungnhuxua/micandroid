<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">

Ext.ux.sitepart.update=Ext.extend(function(){

var panel = Ext.getCmp('center-tab-panel');

var id = "";
var sortvalue="";
var record = grid_sitepart.getSelectionModel().getSelected();
if (record) {
      id = record.get("id");
}

// 是否有效 :0 无效．1 有效 下拉框
var ds_rating_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['0','无效'],['1','有效']]
});

var comboBoxCheckTree = new Ext.ux.ComboBoxCheckTree({
		fieldLabel: '站点分类',
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
			loader: new Ext.tree.TreeLoader({dataUrl:'manage/SiteContentSort/getTreeCombox.do?partId='+id,   
	        baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
			root : new Ext.tree.AsyncTreeNode({id:'1',text:'网站分类列表'})
			},
		selectValueModel:'all' //all 多选, folder选父节点  leaf选子节点
				
});
comboBoxCheckTree.on('change', function(box, newvar, oldvar){
  frm_update_sitepart.form.findField('relateId').setValue(comboBoxCheckTree.getValue()); 
});


var field_update_base = new Ext.form.FieldSet({
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
				        	xtype:'hidden',
				        	fieldLabel:'主键id',
				        	name:'id'
			             },{
					                    	xtype:'textfield',
					                        fieldLabel: '栏目名称',
					                        allowBlank : false,
					                        name:'partName'
					                        }
				                         ,{
					                    	xtype:'numberfield',
					                        fieldLabel: '排序值',
					                        regex:/^\d+$/,
					                        regexText:"只能输入整数",
					                        allowBlank : false,
					                        name:'sortValue'
				                        },{
					                    	xtype:'textfield',
					                        fieldLabel: '栏目说明',
					                        name:'partNotes'
					                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [
	                  
	                    	 {
	                    	xtype:'textfield',
	                        fieldLabel: '显示方式',
		                    regex:/^\d+$/,
		                    regexText:"只能输入整数",
		                    allowBlank : false,
	                        name:'showType'
	                       } ,{
	                    	xtype:'datefield',
	                        fieldLabel: '新增时间',
	                        name:'insertTime'
	                        }
                    ]
          }]
});

var field_update_sitepart = new Ext.form.FieldSet({
	title : '其它数据',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [ 
			                {   
						        xtype: 'treeField',   
						        name: 'parentPartName',   
						        fieldLabel: '父栏目选择',   
						        emptyText:'请选择...',
						        forceSelection : true ,
						        listWidth:200,   
						        listHeight:200,
						        valueField : 'id',       
								displayField : 'text', 
						        readOnly:false,
						        dataUrl:'manage/SitePart/getSynTree.do'  
				            },
							{ 
	                    		xtype:'textfield',
	                        	fieldLabel: '栏目类型',
	                        	name:'lowerNode'
	                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [
 						    {
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
						     
                           }
                    ]
          }]
});



var field_update_relate = new Ext.form.FieldSet({
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
			         items: [ 
			                 {
					            xtype:'textfield',
					            fieldLabel: '关联分类ID集合',
					            name:'relateId',
					            readOnly:true
				              } 
							 
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [ comboBoxCheckTree ]
          }]
});

var frm_update_sitepart = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_base,field_update_sitepart,field_update_relate],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitepart.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitepart.form.submit({
  	   		    url:'manage/SitePart/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_sitepart.doLoad(0);
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

var panel_update_sitepart = new Ext.Panel({
  		renderTo:'update_sitepartdiv',
  		width:Ext.get("update_sitepartdiv").getWidth(),
  		height:Ext.get("update_sitepartdiv").getHeight()-15,
  		border : false,
  		layout : 'border',
        items:[frm_update_sitepart]
});

var record = grid_sitepart.getSelectionModel().getSelected();
if (record) {
      frm_update_sitepart.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_sitepartdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SitePart'){
             panel.setActiveTab('update_SitePart');
             panel_update_sitepart.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_sitepart.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitepart.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_sitepart.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitepart.update();
</script>
</head>
<body>
<div id="update_sitepartdiv" style="width:100%;height:100%;"></div>
</body>
</html>