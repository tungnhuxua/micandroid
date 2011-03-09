<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitesubject");
Ext.ux.sitesubject.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitesubject = new Ext.form.FieldSet({
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
					                        fieldLabel: '模型ID',
					                        name:'modelId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '主题完整名称',
					                        name:'fullName',
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
	                        fieldLabel: '主题名称',
	                        name:'subjectName',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '主题说明',
	                        name:'subjectNotes',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitesubject = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitesubject],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

var panel_detail_sitesubject = new Ext.Panel({
  		renderTo:'detail_sitesubjectdiv',
  		width:Ext.get("detail_sitesubjectdiv").getWidth(),
  		height:Ext.get("detail_sitesubjectdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitesubject]
});
  
var record = grid_sitesubject.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitesubject.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_detail_sitesubject.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("detail_sitesubjectdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteSubject'){
             panel.setActiveTab('detail_SiteSubject');
             panel_detail_sitesubject.setWidth(panel.getInnerWidth()-2);
             panel_detail_sitesubject.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitesubject.setWidth(panel.getInnerWidth()-2);
            panel_detail_sitesubject.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitesubject.detail();
</script>
</head>
<body>
<div id="detail_sitesubjectdiv" style="width:100%;height:100%;"></div>
</body>
</html>