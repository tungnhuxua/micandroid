<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.ux.sitecontentsort.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitecontentsort = new Ext.form.FieldSet({
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
					                        fieldLabel: '网站内容分类名称',
					                        name:'classifyName',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '上级节点ID',
					                        name:'parentId',
					                        readOnly: true
					                        }
				                        ,{
					                    	xtype:'textfield',
					                        fieldLabel: '排序值',
					                        name:'sortValue',
					                        readOnly: true
				                        },{
					                    	xtype:'textfield',
					                        fieldLabel: '分类性质',
					                        name:'classifyNature',
					                        readOnly: true
				                        },{
					                    		xtype:'textfield',
					                        fieldLabel: '新增时间',
					                        name:'insertTime',
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
	                        fieldLabel: '是否有下级节点',
	                        name:'lowerNode',
	                        readOnly: true
	                        },{
	                    	xtype:'textfield',
	                        fieldLabel: '等级',
	                        name:'classifyGrade',
	                        readOnly: true
	                        },{
	                    	xtype:'textfield',
	                        fieldLabel: '是否有效',
	                        name:'effective',
	                        readOnly: true
	                        },{
	                        xtype:'textfield',
	                        fieldLabel: '备注',
	                        name:'classifyNotes',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitecontentsort = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitecontentsort],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_sitecontentsort = new Ext.Panel({
  		renderTo:'detail_sitecontentsortdiv',
  		width:Ext.get("detail_sitecontentsortdiv").getWidth(),
  		height:Ext.get("detail_sitecontentsortdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitecontentsort]
});
  
var record = grid_sitecontentsort.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitecontentsort.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_sitecontentsortdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteContentSort'){
             panel.setActiveTab('detail_SiteContentSort');
             panel_detail_sitecontentsort.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_sitecontentsort.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitecontentsort.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_sitecontentsort.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitecontentsort.detail();
</script>
</head>
<body>
<div id="detail_sitecontentsortdiv" style="width:100%;height:100%;"></div>
</body>
</html>