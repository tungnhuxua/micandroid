<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面TagModel</title>
<script type="text/javascript">
Ext.ux.tagmodel.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_tagmodel = new Ext.form.FieldSet({
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
					                        fieldLabel: '模型名称',
					                        name:'modelName',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '模型访问URL',
					                        name:'aurl',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '是否默认模型',
					                        name:'isDef',
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
	                        fieldLabel: '模型说明',
	                        name:'modelNotes',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '模型模板URL',
	                        name:'modelUrl',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '是否有效',
	                        name:'effective',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_tagmodel = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_tagmodel],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_tagmodel = new Ext.Panel({
  		renderTo:'detail_tagmodeldiv',
  		width:Ext.get("detail_tagmodeldiv").getWidth(),
  		height:Ext.get("detail_tagmodeldiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_tagmodel]
});
  
var record = grid_tagmodel.getSelectionModel().getSelected();
if (record) {
      frm_detail_tagmodel.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_tagmodeldiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_TagModel'){
             panel.setActiveTab('detail_TagModel');
             panel_detail_tagmodel.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_tagmodel.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_tagmodel.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_tagmodel.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.tagmodel.detail();
</script>
</head>
<body>
<div id="detail_tagmodeldiv" style="width:100%;height:100%;"></div>
</body>
</html>