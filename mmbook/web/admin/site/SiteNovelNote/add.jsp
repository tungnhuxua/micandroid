<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitenovelnote");
Ext.ux.sitenovelnote.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_sitenovelnote = new Ext.form.FieldSet({
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
		                        fieldLabel: '连载状态',
		                        name:'serialstoryStat'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '总字数',
		                        name:'wordCount'
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
	                        fieldLabel: '章节数',
	                        name:'chapterCount'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '小说作者信息',
	                        name:'authorNote'
	                        }
                    ]
	           }]
});

var frm_add_sitenovelnote = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_sitenovelnote],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitenovelnote.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitenovelnote.form.submit({
	   		    url:'site/SiteNovelNote/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitenovelnote) == "undefined")) {
			            pag_sitenovelnote.doLoad(0);
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

var panel_add_sitenovelnote = new Ext.Panel({
		renderTo:'add_sitenovelnotediv',
		width:Ext.get("add_sitenovelnotediv").getWidth(),
		height:Ext.get("add_sitenovelnotediv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitenovelnote]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_add_sitenovelnote.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("add_SiteNovelNotediv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_sitenovelnote.setWidth(panel.getInnerWidth()-2);
             panel_add_sitenovelnote.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitenovelnote.setWidth(panel.getInnerWidth()-2);
            panel_add_sitenovelnote.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitenovelnote.add();
</script>
</head>
<body>
<div id="add_sitenovelnotediv" style="width:100%;height:100%;"></div>
</body>
</html>