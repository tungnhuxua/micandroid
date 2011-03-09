<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面BaseAccessories</title>
<script type="text/javascript">
Ext.ux.baseaccessories.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_baseaccessories = new Ext.form.FieldSet({
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
					                        fieldLabel: '网站附件分类ID',
					                        name:'accessoriesTypeId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '内容ID',
					                        name:'contentId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '附件URL',
					                        name:'contentUrl',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '附件文件大小',
					                        name:'fileSize',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '文件后缀',
					                        name:'fileSurfix',
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
	                        fieldLabel: '附属ID',
	                        name:'subordinationId',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '分类ID',
	                        name:'sortId',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '附件格式',
	                        name:'format',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '附件说明',
	                        name:'notes',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_baseaccessories = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_baseaccessories],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_baseaccessories = new Ext.Panel({
  		renderTo:'detail_baseaccessoriesdiv',
  		width:Ext.get("detail_baseaccessoriesdiv").getWidth(),
  		height:Ext.get("detail_baseaccessoriesdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_baseaccessories]
});
  
var record = grid_baseaccessories.getSelectionModel().getSelected();
if (record) {
      frm_detail_baseaccessories.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_baseaccessoriesdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_BaseAccessories'){
             panel.setActiveTab('detail_BaseAccessories');
             panel_detail_baseaccessories.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_baseaccessories.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_baseaccessories.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_baseaccessories.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.baseaccessories.detail();
</script>
</head>
<body>
<div id="detail_baseaccessoriesdiv" style="width:100%;height:100%;"></div>
</body>
</html>