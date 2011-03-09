<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitepicnote");
Ext.ux.sitepicnote.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_sitepicnote = new Ext.form.FieldSet({
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
		                        name:'contentId'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '图片格式',
		                        name:'picFormat'
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
	                        fieldLabel: '图片小标题',
	                        name:'picTitle'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '图片类型',
	                        name:'picTypes'
	                        }
                    ]
	           }]
});

var frm_add_sitepicnote = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_sitepicnote],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitepicnote.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitepicnote.form.submit({
	   		    url:'site/SitePicNote/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitepicnote) == "undefined")) {
			            pag_sitepicnote.doLoad(0);
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

var panel_add_sitepicnote = new Ext.Panel({
		renderTo:'add_sitepicnotediv',
		width:Ext.get("add_sitepicnotediv").getWidth(),
		height:Ext.get("add_sitepicnotediv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitepicnote]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_add_sitepicnote.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("add_SitePicNotediv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_sitepicnote.setWidth(panel.getInnerWidth()-2);
             panel_add_sitepicnote.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitepicnote.setWidth(panel.getInnerWidth()-2);
            panel_add_sitepicnote.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitepicnote.add();
</script>
</head>
<body>
<div id="add_sitepicnotediv" style="width:100%;height:100%;"></div>
</body>
</html>