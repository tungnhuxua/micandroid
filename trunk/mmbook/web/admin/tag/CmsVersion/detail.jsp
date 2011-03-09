<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@include file="../inc/param.jsp"%>
<html>
<head>
<title>版本模板查看页面</title>
<script type="text/javascript">
Ext.ux.cmsversion.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_cmsversion = new Ext.form.FieldSet({
	title : '查看',
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
					             fieldLabel: '模板版本名称',
					             name:'versionName',
					             readOnly: true
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
	                        name:'versionDir',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_cmsversion = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_cmsversion],
        buttons: [{
  	        text:'关闭',
  	        handler:function(){
  	              Ext.MessageBox.confirm('确认操作','确定关闭模板版本页面?',function(btn){
  	                if (btn == 'yes'){
  				        panel.remove(panel.getActiveTab());
  	   		        }
  	  		       },this);
  	  		 }
  	   }]
});

panel_detail_cmsversion = new Ext.Panel({
  		renderTo:'detail_cmsversiondiv',
  		width:Ext.get("detail_cmsversiondiv").getWidth(),
  		height:Ext.get("detail_cmsversiondiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_cmsversion]
});
  
var record = grid_cmsversion.getSelectionModel().getSelected();
if (record) {
      frm_detail_cmsversion.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_cmsversiondiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_CmsVersion'){
             panel.setActiveTab('detail_CmsVersion');
             panel_detail_cmsversion.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_cmsversion.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_cmsversion.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_cmsversion.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmsversion.detail();
</script>
</head>
<body>
<div id="detail_cmsversiondiv" style="width:100%;height:100%;"></div>
</body>
</html>