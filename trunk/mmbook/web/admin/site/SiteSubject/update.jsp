<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitesubject");
Ext.ux.sitesubject.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_sitesubject = new Ext.form.FieldSet({
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
					                        fieldLabel: '模型ID',
					                        name:'modelId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '主题完整名称',
					                        name:'fullName'
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
	                        fieldLabel: '主题名称',
	                        name:'subjectName'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '主题说明',
	                        name:'subjectNotes'
	                        }
                    ]
          }]
});

var frm_update_sitesubject = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitesubject],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitesubject.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitesubject.form.submit({
  	   		    url:'site/SiteSubject/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          if (!(typeof(pag_sitesubject) == "undefined")) {
			            pag_sitesubject.doLoad(0);
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

var panel_update_sitesubject = new Ext.Panel({
  		renderTo:'update_sitesubjectdiv',
  		width:Ext.get("update_sitesubjectdiv").getWidth(),
  		height:Ext.get("update_sitesubjectdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitesubject]
});

var record = grid_sitesubject.getSelectionModel().getSelected();
if (record) {
      frm_update_sitesubject.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_sitesubject.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("update_sitesubjectdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteSubject'){
             panel.setActiveTab('update_SiteSubject');
             panel_update_sitesubject.setWidth(panel.getInnerWidth()-2);
             panel_update_sitesubject.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitesubject.setWidth(panel.getInnerWidth()-2);
            panel_update_sitesubject.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitesubject.update();
</script>
</head>
<body>
<div id="update_sitesubjectdiv" style="width:100%;height:100%;"></div>
</body>
</html>