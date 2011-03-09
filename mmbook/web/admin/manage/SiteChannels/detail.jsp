<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.ux.sitechannels.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitechannels = new Ext.form.FieldSet({
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
					                        fieldLabel: 'channelsName',
					                        name:'channelsName',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: 'sortValue',
					                        name:'sortValue',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: 'channelsNotes',
					                        name:'channelsNotes',
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
	                        fieldLabel: 'showType',
	                        name:'showType',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: 'effective',
	                        name:'effective',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: 'insertTime',
	                        name:'insertTime',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitechannels = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitechannels],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_sitechannels = new Ext.Panel({
  		renderTo:'detail_sitechannelsdiv',
  		width:Ext.get("detail_sitechannelsdiv").getWidth(),
  		height:Ext.get("detail_sitechannelsdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitechannels]
});
  
var record = grid_sitechannels.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitechannels.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_sitechannelsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteChannels'){
             panel.setActiveTab('detail_SiteChannels');
             panel_detail_sitechannels.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_sitechannels.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitechannels.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_sitechannels.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitechannels.detail();
</script>
</head>
<body>
<div id="detail_sitechannelsdiv" style="width:100%;height:100%;"></div>
</body>
</html>