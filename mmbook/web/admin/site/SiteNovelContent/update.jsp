<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitenovelcontent");
Ext.ux.sitenovelcontent.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_sitenovelcontent = new Ext.form.FieldSet({
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
					                        fieldLabel: '小说ID',
					                        name:'noveId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '上级章节ID',
					                        name:'chapterParentId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '章节号',
					                        name:'chapterNo'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '新增时间',
					                        name:'createTime'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '修改时间',
					                        name:'modifyTime'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '章节点击量',
					                        name:'chapterCount'
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
	                        name:'contentId'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '章节名称',
	                        name:'name'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '章节状态',
	                        name:'chapterModeNo'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '新增人',
	                        name:'creator'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '修改人',
	                        name:'modifior'
	                        }
                    ]
          }]
});

var frm_update_sitenovelcontent = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitenovelcontent],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitenovelcontent.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitenovelcontent.form.submit({
  	   		    url:'site/SiteNovelContent/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          if (!(typeof(pag_sitenovelcontent) == "undefined")) {
			            pag_sitenovelcontent.doLoad(0);
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

var panel_update_sitenovelcontent = new Ext.Panel({
  		renderTo:'update_sitenovelcontentdiv',
  		width:Ext.get("update_sitenovelcontentdiv").getWidth(),
  		height:Ext.get("update_sitenovelcontentdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitenovelcontent]
});

var record = grid_sitenovelcontent.getSelectionModel().getSelected();
if (record) {
      frm_update_sitenovelcontent.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_sitenovelcontent.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("update_sitenovelcontentdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteNovelContent'){
             panel.setActiveTab('update_SiteNovelContent');
             panel_update_sitenovelcontent.setWidth(panel.getInnerWidth()-2);
             panel_update_sitenovelcontent.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitenovelcontent.setWidth(panel.getInnerWidth()-2);
            panel_update_sitenovelcontent.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitenovelcontent.update();
</script>
</head>
<body>
<div id="update_sitenovelcontentdiv" style="width:100%;height:100%;"></div>
</body>
</html>