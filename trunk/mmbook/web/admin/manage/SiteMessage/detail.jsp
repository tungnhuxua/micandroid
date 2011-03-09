<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.ux.sitemessage.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitemessage = new Ext.form.FieldSet({
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
					                        fieldLabel: '留言标题',
					                        name:'messageTitle',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '回复留言ID',
					                        name:'replyId',
					                        readOnly: true
					                        }
				                        ,{
					                    	xtype:'textfield',
					                        fieldLabel: '留言类别',
					                        name:'messageType',
					                        readOnly: true
				                        },{
					                    	xtype:'textfield',
					                        fieldLabel: '是否已回复',
					                        name:'whetherReply',
					                        readOnly: true}
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                        xtype:'textfield',
	                        fieldLabel: '留言内容',
	                        name:'messageContent',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '留言人ID',
	                        name:'messageUser',
	                        readOnly: true
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '是否已查看',
	                        name:'whetherReader',
	                        readOnly: true
	                        },{
	                    	xtype:'textfield',
	                        fieldLabel: '留言时间',
	                        name:'insertTime',
	                        readOnly: true}
                    ]
	           }]
});

var frm_detail_sitemessage = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitemessage],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_sitemessage = new Ext.Panel({
  		renderTo:'detail_sitemessagediv',
  		width:Ext.get("detail_sitemessagediv").getWidth(),
  		height:Ext.get("detail_sitemessagediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitemessage]
});
  
var record = grid_sitemessage.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitemessage.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_sitemessagediv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteMessage'){
             panel.setActiveTab('detail_SiteMessage');
             panel_detail_sitemessage.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_sitemessage.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitemessage.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_sitemessage.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitemessage.detail();
</script>
</head>
<body>
<div id="detail_sitemessagediv" style="width:100%;height:100%;"></div>
</body>
</html>