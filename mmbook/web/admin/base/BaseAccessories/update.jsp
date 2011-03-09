<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面BaseAccessories</title>
<script type="text/javascript">

Ext.ux.baseaccessories.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_baseaccessories = new Ext.form.FieldSet({
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
					                        fieldLabel: '网站附件分类ID',
					                        name:'accessoriesTypeId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '内容ID',
					                        name:'contentId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '附件URL',
					                        name:'contentUrl'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '附件文件大小',
					                        name:'fileSize'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '文件后缀',
					                        name:'fileSurfix'
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
	                        fieldLabel: '附属ID',
	                        name:'subordinationId'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '分类ID',
	                        name:'sortId'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '附件格式',
	                        name:'format'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '附件说明',
	                        name:'notes'
	                        }
                    ]
          }]
});

var frm_update_baseaccessories = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_baseaccessories],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_baseaccessories.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_baseaccessories.form.submit({
  	   		    url:'base/BaseAccessories/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_baseaccessories.doLoad(0);
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

var panel_update_baseaccessories = new Ext.Panel({
  		renderTo:'update_baseaccessoriesdiv',
  		width:Ext.get("update_baseaccessoriesdiv").getWidth(),
  		height:Ext.get("update_baseaccessoriesdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_baseaccessories]
});

var record = grid_baseaccessories.getSelectionModel().getSelected();
if (record) {
      frm_update_baseaccessories.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_baseaccessoriesdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_BaseAccessories'){
             panel.setActiveTab('update_BaseAccessories');
             panel_update_baseaccessories.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_baseaccessories.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_baseaccessories.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_baseaccessories.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.baseaccessories.update();
</script>
</head>
<body>
<div id="update_baseaccessoriesdiv" style="width:100%;height:100%;"></div>
</body>
</html>