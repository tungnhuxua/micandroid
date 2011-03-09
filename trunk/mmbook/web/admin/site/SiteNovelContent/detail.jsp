<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitenovelcontent");
Ext.ux.sitenovelcontent.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitenovelcontent = new Ext.form.FieldSet({
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
					                        fieldLabel: '小说ID',
					                        name:'noveId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '上级章节ID',
					                        name:'chapterParentId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '章节号',
					                        name:'chapterNo',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '新增时间',
					                        name:'createTime',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '修改时间',
					                        name:'modifyTime',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '章节点击量',
					                        name:'chapterCount',
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
	                        fieldLabel: '内容ID',
	                        name:'contentId',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '章节名称',
	                        name:'name',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '章节状态',
	                        name:'chapterModeNo',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '新增人',
	                        name:'creator',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '修改人',
	                        name:'modifior',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitenovelcontent = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitenovelcontent],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

var panel_detail_sitenovelcontent = new Ext.Panel({
  		renderTo:'detail_sitenovelcontentdiv',
  		width:Ext.get("detail_sitenovelcontentdiv").getWidth(),
  		height:Ext.get("detail_sitenovelcontentdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitenovelcontent]
});
  
var record = grid_sitenovelcontent.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitenovelcontent.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_detail_sitenovelcontent.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("detail_sitenovelcontentdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteNovelContent'){
             panel.setActiveTab('detail_SiteNovelContent');
             panel_detail_sitenovelcontent.setWidth(panel.getInnerWidth()-2);
             panel_detail_sitenovelcontent.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitenovelcontent.setWidth(panel.getInnerWidth()-2);
            panel_detail_sitenovelcontent.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitenovelcontent.detail();
</script>
</head>
<body>
<div id="detail_sitenovelcontentdiv" style="width:100%;height:100%;"></div>
</body>
</html>