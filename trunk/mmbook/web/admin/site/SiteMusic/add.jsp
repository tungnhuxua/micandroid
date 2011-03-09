<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitemusic");
Ext.ux.sitemusic.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_sitemusic = new Ext.form.FieldSet({
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
		                        fieldLabel: '内容ID',
		                        name:'conentId'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '歌手地域',
		                        name:'musicSection'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '音乐风格',
		                        name:'musicStyle'
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
	                        fieldLabel: '歌手名',
	                        name:'singer'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '音乐格式',
	                        name:'musicFormat'
	                        }
                    ]
	           }]
});

var frm_add_sitemusic = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_sitemusic],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitemusic.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitemusic.form.submit({
	   		    url:'site/SiteMusic/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitemusic) == "undefined")) {
			            pag_sitemusic.doLoad(0);
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

var panel_add_sitemusic = new Ext.Panel({
		renderTo:'add_sitemusicdiv',
		width:Ext.get("add_sitemusicdiv").getWidth(),
		height:Ext.get("add_sitemusicdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitemusic]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_add_sitemusic.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("add_SiteMusicdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_sitemusic.setWidth(panel.getInnerWidth()-2);
             panel_add_sitemusic.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitemusic.setWidth(panel.getInnerWidth()-2);
            panel_add_sitemusic.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitemusic.add();
</script>
</head>
<body>
<div id="add_sitemusicdiv" style="width:100%;height:100%;"></div>
</body>
</html>