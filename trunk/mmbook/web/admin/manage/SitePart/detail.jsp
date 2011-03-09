<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.ux.sitepart.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitepart = new Ext.form.FieldSet({
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
					                        fieldLabel: '栏目名称',
					                        name:'partName',
					                        readOnly: true
					                        }
				                        ,{
					                    	xtype:'textfield',
					                        fieldLabel: '父站栏目id',
					                        name:'partParentId',
					                        readOnly: true
				                        },{
					                    	xtype:'textfield',
					                        fieldLabel: '排序值',
					                        name:'sortValue',
					                        readOnly: true
				                        },{
					                    	xtype:'textfield',
					                        fieldLabel: '栏目说明',
					                        name:'partNotes',
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
	                        fieldLabel: '有无下级节点',
	                        name:'lowerNode',
	                        readOnly: true
	                       } ,{
	                    	xtype:'textfield',
	                        fieldLabel: '显示方式',
	                        name:'showType',
	                        readOnly: true
	                        },{
	                    	xtype:'textfield',
	                        fieldLabel: '是否有效',
	                        name:'effective',
	                        readOnly: true
	                       } ,{
	                    	xtype:'textfield',
	                        fieldLabel: '新增时间',
	                        name:'insertTime',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitepart = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitepart],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_sitepart = new Ext.Panel({
  		renderTo:'detail_sitepartdiv',
  		width:Ext.get("detail_sitepartdiv").getWidth(),
  		height:Ext.get("detail_sitepartdiv").getHeight()-15,
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitepart]
});
  
var record = grid_sitepart.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitepart.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_sitepartdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SitePart'){
             panel.setActiveTab('detail_SitePart');
             panel_detail_sitepart.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_sitepart.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitepart.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_sitepart.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitepart.detail();
</script>
</head>
<body>
<div id="detail_sitepartdiv" style="width:100%;height:100%;"></div>
</body>
</html>