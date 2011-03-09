<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitepicnote");
Ext.ux.sitepicnote.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitepicnote = new Ext.form.FieldSet({
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
					                        fieldLabel: '内容ID',
					                        name:'contentId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '图片格式',
					                        name:'picFormat',
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
	                        fieldLabel: '图片小标题',
	                        name:'picTitle',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '图片类型',
	                        name:'picTypes',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitepicnote = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitepicnote],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

var panel_detail_sitepicnote = new Ext.Panel({
  		renderTo:'detail_sitepicnotediv',
  		width:Ext.get("detail_sitepicnotediv").getWidth(),
  		height:Ext.get("detail_sitepicnotediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitepicnote]
});
  
var record = grid_sitepicnote.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitepicnote.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_detail_sitepicnote.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("detail_sitepicnotediv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SitePicNote'){
             panel.setActiveTab('detail_SitePicNote');
             panel_detail_sitepicnote.setWidth(panel.getInnerWidth()-2);
             panel_detail_sitepicnote.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitepicnote.setWidth(panel.getInnerWidth()-2);
            panel_detail_sitepicnote.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitepicnote.detail();
</script>
</head>
<body>
<div id="detail_sitepicnotediv" style="width:100%;height:100%;"></div>
</body>
</html>