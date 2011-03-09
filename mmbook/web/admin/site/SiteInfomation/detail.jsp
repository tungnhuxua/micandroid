<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>查看页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.siteinfomation");
Ext.ux.siteinfomation.detail=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_detail_siteinfomation = new Ext.form.FieldSet({
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
					                        fieldLabel: '网站名称',
					                        name:'siteName',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '上传ftp',
					                        name:'ftpUploadId',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '路径',
					                        name:'path',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '当前系统',
					                        name:'currentSystem',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '后台本地化',
					                        name:'localeAdmin',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '是否使用相对路径',
					                        name:'isRelativePath',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '动态页后缀',
					                        name:'dynamicSuffix',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '协议',
					                        name:'protocol',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '域名别名',
					                        name:'domainAlias',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '终审级别',
					                        name:'finalStep',
					                        readOnly: true
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '网站LOGO',
					                        name:'logoUrl',
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
	                        fieldLabel: '配置ID',
	                        name:'configId',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '域名',
	                        name:'domain',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '网站简称',
	                        name:'shortName',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '静态页面存在URL',
	                        name:'staticDir',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '前台本地化',
	                        name:'localeFront',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '模板版本',
	                        name:'tplSolution',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '静态页后缀',
	                        name:'staticSuffix',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '是否开启静态化',
	                        name:'isStaticOn',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '域名重定向',
	                        name:'domainRedirect',
	                        readOnly: true
	                        }
	                        ,{
	                        xtype:'textfield',
	                        fieldLabel: '审核后',
	                        name:'afterCheck',
	                        readOnly: true
	                        }
                    ]
	           }]
});

var frm_detail_siteinfomation = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '查看',
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_detail_siteinfomation],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

var panel_detail_siteinfomation = new Ext.Panel({
  		renderTo:'detail_siteinfomationdiv',
  		width:Ext.get("detail_siteinfomationdiv").getWidth(),
  		height:Ext.get("detail_siteinfomationdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_siteinfomation]
});
  
var record = grid_siteinfomation.getSelectionModel().getSelected();
if (record) {
      frm_detail_siteinfomation.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_detail_siteinfomation.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("detail_siteinfomationdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteInfomation'){
             panel.setActiveTab('detail_SiteInfomation');
             panel_detail_siteinfomation.setWidth(panel.getInnerWidth()-2);
             panel_detail_siteinfomation.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_siteinfomation.setWidth(panel.getInnerWidth()-2);
            panel_detail_siteinfomation.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.siteinfomation.detail();
</script>
</head>
<body>
<div id="detail_siteinfomationdiv" style="width:100%;height:100%;"></div>
</body>
</html>