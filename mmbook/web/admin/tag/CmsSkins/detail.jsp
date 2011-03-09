<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看新增网站皮肤页面</title>
<script type="text/javascript">
Ext.ux.cmsskins.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_cmsskins = new Ext.form.FieldSet({
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
					                        fieldLabel: '模板版本ID',
					                        name:'versionId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '方案目录',
					                        name:'skinDir',
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
	                        fieldLabel: '方案名称',
	                        name:'skinName',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '设为默认',
	                        name:'isdefault',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_cmsskins = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_cmsskins],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_cmsskins = new Ext.Panel({
  		renderTo:'detail_cmsskinsdiv',
  		width:Ext.get("detail_cmsskinsdiv").getWidth(),
  		height:Ext.get("detail_cmsskinsdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_cmsskins]
});
  
var record = grid_cmsskins.getSelectionModel().getSelected();
if (record) {
      frm_detail_cmsskins.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_cmsskinsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_CmsSkins'){
             panel.setActiveTab('detail_CmsSkins');
             panel_detail_cmsskins.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_cmsskins.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_cmsskins.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_cmsskins.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmsskins.detail();
</script>
</head>
<body>
<div id="detail_cmsskinsdiv" style="width:100%;height:100%;"></div>
</body>
</html>