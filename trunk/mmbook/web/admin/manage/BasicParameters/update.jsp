<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>基本参数修改页面</title>
<script type="text/javascript">

Ext.ux.basicparameters.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');

// 类型参数:1,系统参数;2,平台参数 下拉框
var ds_rating_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['1','系统参数'],['2','平台参数 ']]
});


var field_update_basicparameters = new Ext.form.FieldSet({
	title : '修改',
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
					                        fieldLabel: '基本参数名称',
					                        name:'parametersName'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '基本参数标签',
					                        name:'parametersTag'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '基本参数说明',
					                        name:'parametersNote'
					                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                  
	                    		xtype:'textfield',
	                        fieldLabel: '基本参数值',
	                        name:'parametersValue'
	                        }
	                        ,{
								xtype : 'combo',   // xtype 类型 combo 对应: Ext.form.ComboBox
								fieldLabel : '功能等级',  //标题名称
								emptyText : '请选择...',
								allowBlank : false,     //是否必选 false 心选 , true 可选
								triggerAction : 'all',
								forceSelection : true ,   //如果为true，将限制选择的值必须是下拉列表中的值。 如果为false，允许用户设置任意值到输入栏 (默认值为 false) 
								store : ds_rating_data,   //加载 store
								hiddenName :'parametersType',  // 查询条件名称
								valueField : 'id',        // 对应 ds_rating_data id
								displayField : 'val',     //对应 ds_rating_data val
								editable : false,
								mode : 'local'  // local 本地 ,  remote 远程
					         }
                ]
          }]
});

var frm_update_basicparameters = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_basicparameters],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_basicparameters.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_basicparameters.form.submit({
  	   		    url:'manage/BasicParameters/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_basicparameters.doLoad(0);
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

var panel_update_basicparameters = new Ext.Panel({
  		renderTo:'update_basicparametersdiv',
  		width:Ext.get("update_basicparametersdiv").getWidth(),
  		height:Ext.get("update_basicparametersdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_basicparameters]
});

var record = grid_basicparameters.getSelectionModel().getSelected();
if (record) {
      frm_update_basicparameters.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_basicparametersdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_BasicParameters'){
             panel.setActiveTab('update_BasicParameters');
             panel_update_basicparameters.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_basicparameters.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_basicparameters.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_basicparameters.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.basicparameters.update();
</script>
</head>
<body>
<div id="update_basicparametersdiv" style="width:100%;height:100%;"></div>
</body>
</html>