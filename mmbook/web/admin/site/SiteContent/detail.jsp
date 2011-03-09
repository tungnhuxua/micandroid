<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitecontent");
Ext.ux.sitecontent.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_sitecontent = new Ext.form.FieldSet({
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
					                        fieldLabel: '容内分类ID',
					                        name:'sortId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '标题',
					                        name:'title',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '内容简介',
					                        name:'synopsis',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '作者',
					                        name:'author',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '提交时间',
					                        name:'submitTime',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '最近修改时间',
					                        name:'updateTime',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '状态',
					                        name:'stateNo',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '排序值',
					                        name:'sortValue',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '查看级别',
					                        name:'viewClass',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '内容URL',
					                        name:'conentUrl',
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
	                        fieldLabel: '一级分类ID',
	                        name:'oneSortId',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '全标题',
	                        name:'titleFull',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '来源',
	                        name:'sources',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '内容提交人',
	                        name:'submitNam',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '修改人',
	                        name:'updateNam',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '缩略图URL',
	                        name:'previewsImgUrl',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '模型ID',
	                        name:'modelId',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '评论状态',
	                        name:'commentStat',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '显示时间段',
	                        name:'showTime',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_sitecontent = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_sitecontent],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

var panel_detail_sitecontent = new Ext.Panel({
  		renderTo:'detail_sitecontentdiv',
  		width:Ext.get("detail_sitecontentdiv").getWidth(),
  		height:Ext.get("detail_sitecontentdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitecontent]
});
  
var record = grid_sitecontent.getSelectionModel().getSelected();
if (record) {
      frm_detail_sitecontent.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_detail_sitecontent.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("detail_sitecontentdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteContent'){
             panel.setActiveTab('detail_SiteContent');
             panel_detail_sitecontent.setWidth(panel.getInnerWidth()-2);
             panel_detail_sitecontent.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitecontent.setWidth(panel.getInnerWidth()-2);
            panel_detail_sitecontent.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitecontent.detail();
</script>
</head>
<body>
<div id="detail_sitecontentdiv" style="width:100%;height:100%;"></div>
</body>
</html>