<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.ux.extjstest.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_extjstest = new Ext.form.FieldSet({
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
					                        fieldLabel: '能功名称',
					                        name:'functionName',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '能功类型',
					                        name:'types',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '系联电话',
					                        name:'telephone',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '次数',
					                        name:'frequency',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '等级',
					                        name:'rating',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '能功说明',
					                        name:'functionExplain',
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
	                        fieldLabel: '上级能功ID',
	                        name:'parentId',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '新增时间',
	                        name:'insertTime',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '手机号码',
	                        name:'mobileTelephone',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '上传文件URL',
	                        name:'upfileUrl',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '是否为默认功能',
	                        name:'whetherDefault',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_extjstest = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_extjstest],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_extjstest = new Ext.Panel({
  		renderTo:'detail_extjstestdiv',
  		width:Ext.get("detail_extjstestdiv").getWidth(),
  		height:Ext.get("detail_extjstestdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_extjstest]
});
  
var record = grid_extjstest.getSelectionModel().getSelected();
if (record) {
      frm_detail_extjstest.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("detail_extjstestdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_ExtjsTest'){
             panel.setActiveTab('detail_ExtjsTest');
             panel_detail_extjstest.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_extjstest.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_extjstest.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_extjstest.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.extjstest.detail();
</script>
</head>
<body>
<div id="detail_extjstestdiv" style="width:100%;height:100%;"></div>
</body>
</html>