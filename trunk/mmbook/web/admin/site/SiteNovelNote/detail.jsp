<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitenovelnote");
Ext.ux.sitenovelnote.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitenovelnote = new Ext.form.FieldSet({
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
					                        fieldLabel: '连载状态',
					                        name:'serialstoryStat',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '总字数',
					                        name:'wordCount',
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
	                        fieldLabel: '章节数',
	                        name:'chapterCount',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '小说作者信息',
	                        name:'authorNote',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitenovelnote = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitenovelnote],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

var panel_detail_sitenovelnote = new Ext.Panel({
  		renderTo:'detail_sitenovelnotediv',
  		width:Ext.get("detail_sitenovelnotediv").getWidth(),
  		height:Ext.get("detail_sitenovelnotediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitenovelnote]
});
  
var record = grid_sitenovelnote.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitenovelnote.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_detail_sitenovelnote.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("detail_sitenovelnotediv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteNovelNote'){
             panel.setActiveTab('detail_SiteNovelNote');
             panel_detail_sitenovelnote.setWidth(panel.getInnerWidth()-2);
             panel_detail_sitenovelnote.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitenovelnote.setWidth(panel.getInnerWidth()-2);
            panel_detail_sitenovelnote.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitenovelnote.detail();
</script>
</head>
<body>
<div id="detail_sitenovelnotediv" style="width:100%;height:100%;"></div>
</body>
</html>