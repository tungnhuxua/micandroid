<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面TagModel</title>
<script type="text/javascript">

Ext.ux.tagmodel.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_tagmodel = new Ext.form.FieldSet({
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
					                        fieldLabel: '模型名称',
					                        name:'modelName'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '模型访问URL',
					                        name:'aurl'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '是否默认模型',
					                        name:'isDef'
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
	                        fieldLabel: '模型说明',
	                        name:'modelNotes'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '模型模板URL',
	                        name:'modelUrl'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '是否有效',
	                        name:'effective'
	                        }
                    ]
          }]
});

var frm_update_tagmodel = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_tagmodel],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_tagmodel.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_tagmodel.form.submit({
  	   		    url:'tag/TagModel/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_tagmodel.doLoad(0);
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

var panel_update_tagmodel = new Ext.Panel({
  		renderTo:'update_tagmodeldiv',
  		width:Ext.get("update_tagmodeldiv").getWidth(),
  		height:Ext.get("update_tagmodeldiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_tagmodel]
});

var record = grid_tagmodel.getSelectionModel().getSelected();
if (record) {
      frm_update_tagmodel.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_tagmodeldiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_TagModel'){
             panel.setActiveTab('update_TagModel');
             panel_update_tagmodel.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_tagmodel.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_tagmodel.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_tagmodel.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.tagmodel.update();
</script>
</head>
<body>
<div id="update_tagmodeldiv" style="width:100%;height:100%;"></div>
</body>
</html>