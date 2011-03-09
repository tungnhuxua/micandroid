<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitemusic");
Ext.ux.sitemusic.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_sitemusic = new Ext.form.FieldSet({
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

var frm_update_sitemusic = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitemusic],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitemusic.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitemusic.form.submit({
  	   		    url:'site/SiteMusic/update.do',
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

var panel_update_sitemusic = new Ext.Panel({
  		renderTo:'update_sitemusicdiv',
  		width:Ext.get("update_sitemusicdiv").getWidth(),
  		height:Ext.get("update_sitemusicdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitemusic]
});

var record = grid_sitemusic.getSelectionModel().getSelected();
if (record) {
      frm_update_sitemusic.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_sitemusic.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("update_sitemusicdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteMusic'){
             panel.setActiveTab('update_SiteMusic');
             panel_update_sitemusic.setWidth(panel.getInnerWidth()-2);
             panel_update_sitemusic.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitemusic.setWidth(panel.getInnerWidth()-2);
            panel_update_sitemusic.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitemusic.update();
</script>
</head>
<body>
<div id="update_sitemusicdiv" style="width:100%;height:100%;"></div>
</body>
</html>