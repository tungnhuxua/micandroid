<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@include file="../inc/param.jsp"%>
<html>
<head>
<title>模板版本修改页面</title>
<script type="text/javascript">

Ext.ux.cmsversion.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_cmsversion = new Ext.form.FieldSet({
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
					                        fieldLabel: '模板版本名称',
					                        allowBlank : false,
					                        minLength: 2,
											maxLength: 20,
					                        name:'versionName'
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
	                        fieldLabel: '模板版本目录',
					        allowBlank : false,
					        minLength: 2,
							maxLength: 20,	                        
	                        name:'versionDir'
	                        }
                    ]
          }]
});

var frm_update_cmsversion = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_cmsversion],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_cmsversion.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_cmsversion.form.submit({
  	   		    url:'tag/CmsVersion/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_cmsversion.doLoad(0);
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
  	              Ext.MessageBox.confirm('确认操作','确定关闭模板版本页面?',function(btn){
  	                if (btn == 'yes'){
  				        panel.remove(panel.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }]
 });

var panel_update_cmsversion = new Ext.Panel({
  		renderTo:'update_cmsversiondiv',
  		width:Ext.get("update_cmsversiondiv").getWidth(),
  		height:Ext.get("update_cmsversiondiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_cmsversion]
});

var record = grid_cmsversion.getSelectionModel().getSelected();
if (record) {
      frm_update_cmsversion.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_cmsversiondiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_CmsVersion'){
             panel.setActiveTab('update_CmsVersion');
             panel_update_cmsversion.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_cmsversion.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_cmsversion.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_cmsversion.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmsversion.update();
</script>
</head>
<body>
<div id="update_cmsversiondiv" style="width:100%;height:100%;"></div>
</body>
</html>