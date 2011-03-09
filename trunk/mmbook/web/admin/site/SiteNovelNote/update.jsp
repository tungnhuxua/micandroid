<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitenovelnote");
Ext.ux.sitenovelnote.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_sitenovelnote = new Ext.form.FieldSet({
	title : '修改',
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
				        	xtype:'hidden',
				        	fieldLabel:'主键id',
				        	name:'id'
			             },{
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

var frm_update_sitenovelnote = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitenovelnote],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitenovelnote.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitenovelnote.form.submit({
  	   		    url:'site/SiteNovelNote/update.do',
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

var panel_update_sitenovelnote = new Ext.Panel({
  		renderTo:'update_sitenovelnotediv',
  		width:Ext.get("update_sitenovelnotediv").getWidth(),
  		height:Ext.get("update_sitenovelnotediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitenovelnote]
});

var record = grid_sitenovelnote.getSelectionModel().getSelected();
if (record) {
      frm_update_sitenovelnote.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_sitenovelnote.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("update_sitenovelnotediv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteNovelNote'){
             panel.setActiveTab('update_SiteNovelNote');
             panel_update_sitenovelnote.setWidth(panel.getInnerWidth()-2);
             panel_update_sitenovelnote.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitenovelnote.setWidth(panel.getInnerWidth()-2);
            panel_update_sitenovelnote.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitenovelnote.update();
</script>
</head>
<body>
<div id="update_sitenovelnotediv" style="width:100%;height:100%;"></div>
</body>
</html>