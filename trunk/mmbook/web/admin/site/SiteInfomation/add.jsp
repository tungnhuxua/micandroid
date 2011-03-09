<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.siteinfomation");
Ext.ux.siteinfomation.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_siteinfomation = new Ext.form.FieldSet({
	title : '新增',
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
		                        name:'siteName'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '上传ftp',
		                        name:'ftpUploadId'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '路径',
		                        name:'path'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '当前系统',
		                        name:'currentSystem'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '后台本地化',
		                        name:'localeAdmin'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '是否使用相对路径',
		                        name:'isRelativePath'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '动态页后缀',
		                        name:'dynamicSuffix'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '协议',
		                        name:'protocol'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '域名别名',
		                        name:'domainAlias'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '终审级别',
		                        name:'finalStep'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '网站LOGO',
		                        name:'logoUrl'
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
	                        name:'configId'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '域名',
	                        name:'domain'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '网站简称',
	                        name:'shortName'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '静态页面存在URL',
	                        name:'staticDir'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '前台本地化',
	                        name:'localeFront'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '模板版本',
	                        name:'tplSolution'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '静态页后缀',
	                        name:'staticSuffix'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '是否开启静态化',
	                        name:'isStaticOn'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '域名重定向',
	                        name:'domainRedirect'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '审核后',
	                        name:'afterCheck'
	                        }
                    ]
	           }]
});

var frm_add_siteinfomation = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_siteinfomation],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_siteinfomation.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_siteinfomation.form.submit({
	   		    url:'site/SiteInfomation/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_siteinfomation) == "undefined")) {
			            pag_siteinfomation.doLoad(0);
			          }
	 		       },
	 		     scope:this,
	 		     failure:function(form,action){
	 		          Ext.Msg.show({title: '错误', msg: action.result.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
	   		       }
	  		     }) 
	   		    }
	     },{
  	            text:'关闭',
  	            style : 'margin-left: 8px',
  	            handler:function(){
  				    panel.remove(panel.getActiveTab());
  	   		    }
  	     }] 
});

var panel_add_siteinfomation = new Ext.Panel({
		renderTo:'add_siteinfomationdiv',
		width:Ext.get("add_siteinfomationdiv").getWidth(),
		height:Ext.get("add_siteinfomationdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_siteinfomation]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_add_siteinfomation.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("add_SiteInfomationdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_siteinfomation.setWidth(panel.getInnerWidth()-2);
             panel_add_siteinfomation.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_siteinfomation.setWidth(panel.getInnerWidth()-2);
            panel_add_siteinfomation.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.siteinfomation.add();
</script>
</head>
<body>
<div id="add_siteinfomationdiv" style="width:100%;height:100%;"></div>
</body>
</html>