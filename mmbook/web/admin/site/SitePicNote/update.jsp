<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitepicnote");
Ext.ux.sitepicnote.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_sitepicnote = new Ext.form.FieldSet({
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
					                        fieldLabel: '内容ID',
					                        name:'contentId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '图片格式',
					                        name:'picFormat'
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
	                        fieldLabel: '图片小标题',
	                        name:'picTitle'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '图片类型',
	                        name:'picTypes'
	                        }
                    ]
          }]
});

var frm_update_sitepicnote = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitepicnote],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitepicnote.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitepicnote.form.submit({
  	   		    url:'site/SitePicNote/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          if (!(typeof(pag_sitepicnote) == "undefined")) {
			            pag_sitepicnote.doLoad(0);
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

var panel_update_sitepicnote = new Ext.Panel({
  		renderTo:'update_sitepicnotediv',
  		width:Ext.get("update_sitepicnotediv").getWidth(),
  		height:Ext.get("update_sitepicnotediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitepicnote]
});

var record = grid_sitepicnote.getSelectionModel().getSelected();
if (record) {
      frm_update_sitepicnote.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_sitepicnote.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("update_sitepicnotediv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SitePicNote'){
             panel.setActiveTab('update_SitePicNote');
             panel_update_sitepicnote.setWidth(panel.getInnerWidth()-2);
             panel_update_sitepicnote.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitepicnote.setWidth(panel.getInnerWidth()-2);
            panel_update_sitepicnote.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitepicnote.update();
</script>
</head>
<body>
<div id="update_sitepicnotediv" style="width:100%;height:100%;"></div>
</body>
</html>