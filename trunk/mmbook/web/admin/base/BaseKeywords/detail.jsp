<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面BaseKeywords</title>
<script type="text/javascript">
Ext.ux.basekeywords.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_basekeywords = new Ext.form.FieldSet({
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
					                        fieldLabel: '上级关键字ID',
					                        name:'parentId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '关键字说明',
					                        name:'keywordsNotes',
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
	                        fieldLabel: '关键值',
	                        name:'keywordsValue',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '是否有下级节点',
	                        name:'lowerNode',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_basekeywords = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_basekeywords],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_basekeywords = new Ext.Panel({
  		renderTo:'detail_basekeywordsdiv',
  		width:Ext.get("detail_basekeywordsdiv").getWidth(),
  		height:Ext.get("detail_basekeywordsdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_basekeywords]
});
  
var record = grid_basekeywords.getSelectionModel().getSelected();
if (record) {
      frm_detail_basekeywords.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_basekeywordsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_BaseKeywords'){
             panel.setActiveTab('detail_BaseKeywords');
             panel_detail_basekeywords.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_basekeywords.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_basekeywords.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_basekeywords.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.basekeywords.detail();
</script>
</head>
<body>
<div id="detail_basekeywordsdiv" style="width:100%;height:100%;"></div>
</body>
</html>