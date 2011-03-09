<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">

Ext.ux.sitecontentsort.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_sitecontentsort = new Ext.form.FieldSet({
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
					                        fieldLabel: '网站内容分类名称',
					                        name:'classifyName'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '上级节点ID',
					                        name:'parentId'
					                        }
				                        ,{
					                    	xtype:'textfield',
					                        fieldLabel: '排序值',
					                        name:'sortValue'
				                       } ,{
					                    	xtype:'textfield',
					                        fieldLabel: '分类性质',
					                        name:'classifyNature'
				                        },{
					                    		xtype:'textfield',
					                        fieldLabel: '新增时间',
					                        name:'insertTime'
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
	                        fieldLabel: '是否有下级节点',
	                        name:'lowerNode'
	                        },{
	                    	xtype:'textfield',
	                        fieldLabel: '等级',
	                        name:'classifyGrade'
	                       } ,{
	                    	xtype:'textfield',
	                        fieldLabel: '是否有效',
	                        name:'effective'
	                       } ,{
	                    		xtype:'textfield',
	                        fieldLabel: '备注',
	                        name:'classifyNotes'
	                        }
                    ]
          }]
});

var frm_update_sitecontentsort = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitecontentsort],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitecontentsort.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitecontentsort.form.submit({
  	   		    url:'manage/SiteContentSort/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_sitecontentsort.doLoad(0);
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

var panel_update_sitecontentsort = new Ext.Panel({
  		renderTo:'update_sitecontentsortdiv',
  		width:Ext.get("update_sitecontentsortdiv").getWidth(),
  		height:Ext.get("update_sitecontentsortdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitecontentsort]
});

var record = grid_sitecontentsort.getSelectionModel().getSelected();
if (record) {
      frm_update_sitecontentsort.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_sitecontentsortdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteContentSort'){
             panel.setActiveTab('update_SiteContentSort');
             panel_update_sitecontentsort.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_sitecontentsort.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitecontentsort.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_sitecontentsort.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitecontentsort.update();
</script>
</head>
<body>
<div id="update_sitecontentsortdiv" style="width:100%;height:100%;"></div>
</body>
</html>