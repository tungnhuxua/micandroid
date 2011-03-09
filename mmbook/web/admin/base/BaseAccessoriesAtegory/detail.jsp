<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面BaseAccessoriesAtegory</title>
<script type="text/javascript">
Ext.ux.baseaccessoriesategory.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_baseaccessoriesategory = new Ext.form.FieldSet({
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
					                        fieldLabel: '分类名称',
					                        name:'sortName',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '父级分类id',
					                        name:'parentId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '分类格式',
					                        name:'sortFormat',
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
	                        fieldLabel: '分类说明',
	                        name:'sortNotes',
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

var frm_detail_baseaccessoriesategory = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_baseaccessoriesategory],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_baseaccessoriesategory = new Ext.Panel({
  		renderTo:'detail_baseaccessoriesategorydiv',
  		width:Ext.get("detail_baseaccessoriesategorydiv").getWidth(),
  		height:Ext.get("detail_baseaccessoriesategorydiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_baseaccessoriesategory]
});
  
var record = grid_baseaccessoriesategory.getSelectionModel().getSelected();
if (record) {
      frm_detail_baseaccessoriesategory.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_baseaccessoriesategorydiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_BaseAccessoriesAtegory'){
             panel.setActiveTab('detail_BaseAccessoriesAtegory');
             panel_detail_baseaccessoriesategory.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_baseaccessoriesategory.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_baseaccessoriesategory.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_baseaccessoriesategory.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.baseaccessoriesategory.detail();
</script>
</head>
<body>
<div id="detail_baseaccessoriesategorydiv" style="width:100%;height:100%;"></div>
</body>
</html>