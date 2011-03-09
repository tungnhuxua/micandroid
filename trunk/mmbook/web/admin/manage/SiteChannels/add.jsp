<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增频道页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitechannels");
Ext.ux.sitechannels.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_sitechannels = new Ext.form.FieldSet({
    id : 'field_add_sitechannels',
	title : '频道基础数据',
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
		                        fieldLabel: '频道名称',
		                        name:'channelsName'
		                        }
	                        ,{
		                    	xtype:'numberfield',
		                        fieldLabel: '频道排序值',
		                        name:'sortValue'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '频道备注',
		                        name:'channelsNotes'
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
								xtype : 'combo',
								fieldLabel : '显示方式',
								emptyText : '请选择',
								//allowBlank : false,
								triggerAction : 'all',
								hiddenName :'showType',
                                store : new Ext.data.SimpleStore({ 
                                   //填充的数据
									fields : ['val','tex'],
									data : [['0','默认'],['1','显示方式表']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        }
	                        ,{
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
	                        }
	                        ,{
	                    	xtype:'datefield',
	                        fieldLabel: '新增时间',
	                        name:'insertTime'
	                        }
                    ]
	 }]
});

//栏目下拉框
 var sitePartGroupStore = new Ext.data.Store({  
     proxy: new Ext.data.HttpProxy({  
         url: 'manage/SitePart/getLovcombo.do'
     }),  
     reader: new Ext.data.JsonReader({  
     root: 'list',  
     id: 'id'  
     }, [  
         {name: 'id', mapping: 'id'},  
         {name: 'name', mapping: 'partName'}  
     ])
 }); 

//远程取数下拉框据例子
var st_site_data = new Ext.data.SimpleStore({
			//数据格式 服务类与本地格式要一样
			fields: ['id', 'val'],
			proxy: new Ext.data.HttpProxy({
			    url: 'site/SiteInfomation/getComboBox.do'
			})
});

var field_add_sitepart = new Ext.form.FieldSet({
    id : 'field_add_sitepart',
	title : '附属信息',
	autoHeight : true,
	layout: 'fit',
    layout:'column',
    items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [  {
					           xtype:'lovcombo',
					           fieldLabel : '<span style="color:red;">*</span>栏目列表',
					           hiddenName : 'partIds',
					           allowBlank : false,
					           displayField : 'name',
					           valueField : 'id',
					           editable : false,
					           triggerAction : 'all',
					           allowBlank : true,
					           blankText : '栏目不能为空!',
					           emptyText : '请选择栏目...',
					           store : sitePartGroupStore
							  }
	                         
	                    ]
            	},{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [{
						xtype : 'combo',   // xtype 类型 combo 对应: Ext.form.ComboBox
						fieldLabel : '所属网站',  //标题名称
						emptyText : '请选择...',
						allowBlank : false,     //是否必选 false 心选 , true 可选
						triggerAction : 'all',
						forceSelection : true ,   //如果为true，将限制选择的值必须是下拉列表中的值。 如果为false，允许用户设置任意值到输入栏 (默认值为 false) 
						store : st_site_data,   //加载 store
						hiddenName :'siteId',  // 查询条件名称
						valueField : 'id',        // 对应 ds_rating_data id
						displayField : 'val',     //对应 ds_rating_data val
						editable : false,
						mode : 'remote'  // local 本地 ,  remote 远程
						}]
            	}]
});

var frm_add_sitechannels = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_sitechannels,field_add_sitepart],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitechannels.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitechannels.form.submit({
	   		    url:'manage/SiteChannels/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitechannels) == "undefined")) {
			            pag_sitechannels.doLoad(0);
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

var panel_add_sitechannels = new Ext.Panel({
		renderTo:'add_sitechannelsdiv',
		width:Ext.get("add_sitechannelsdiv").getWidth(),
		height:Ext.get("add_sitechannelsdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitechannels]
});

panel.on('resize',function(){
    if(Ext.get("add_SiteChannelsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131101'){
             panel.setActiveTab('1131101');
             panel_add_sitechannels.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_sitechannels.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitechannels.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_sitechannels.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitechannels.add();
</script>
</head>
<body>
<div id="add_sitechannelsdiv" style="width:100%;height:100%;"></div>
</body>
</html>