<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面BaseKeywords</title>
<script type="text/javascript">

Ext.ux.basekeywords.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_basekeywords = new Ext.form.FieldSet({
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
					                        fieldLabel: '上级关键字ID',
					                        name:'parentId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '关键字说明',
					                        name:'keywordsNotes'
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
	                        fieldLabel: '关键值',
	                        name:'keywordsValue'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '是否有下级节点',
	                        name:'lowerNode'
	                        }
                    ]
          }]
});

var frm_update_basekeywords = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_basekeywords],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_basekeywords.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_basekeywords.form.submit({
  	   		    url:'base/BaseKeywords/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_basekeywords.doLoad(0);
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

var panel_update_basekeywords = new Ext.Panel({
  		renderTo:'update_basekeywordsdiv',
  		width:Ext.get("update_basekeywordsdiv").getWidth(),
  		height:Ext.get("update_basekeywordsdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_basekeywords]
});

var record = grid_basekeywords.getSelectionModel().getSelected();
if (record) {
      frm_update_basekeywords.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_basekeywordsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_BaseKeywords'){
             panel.setActiveTab('update_BaseKeywords');
             panel_update_basekeywords.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_basekeywords.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_basekeywords.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_basekeywords.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.basekeywords.update();
</script>
</head>
<body>
<div id="update_basekeywordsdiv" style="width:100%;height:100%;"></div>
</body>
</html>