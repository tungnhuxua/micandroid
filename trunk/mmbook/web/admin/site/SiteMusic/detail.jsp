<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitemusic");
Ext.ux.sitemusic.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitemusic = new Ext.form.FieldSet({
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
					                        name:'conentId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '歌手地域',
					                        name:'musicSection',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '音乐风格',
					                        name:'musicStyle',
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
	                        fieldLabel: '歌手名',
	                        name:'singer',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '音乐格式',
	                        name:'musicFormat',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitemusic = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitemusic],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

var panel_detail_sitemusic = new Ext.Panel({
  		renderTo:'detail_sitemusicdiv',
  		width:Ext.get("detail_sitemusicdiv").getWidth(),
  		height:Ext.get("detail_sitemusicdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitemusic]
});
  
var record = grid_sitemusic.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitemusic.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_detail_sitemusic.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("detail_sitemusicdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteMusic'){
             panel.setActiveTab('detail_SiteMusic');
             panel_detail_sitemusic.setWidth(panel.getInnerWidth()-2);
             panel_detail_sitemusic.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitemusic.setWidth(panel.getInnerWidth()-2);
            panel_detail_sitemusic.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitemusic.detail();
</script>
</head>
<body>
<div id="detail_sitemusicdiv" style="width:100%;height:100%;"></div>
</body>
</html>