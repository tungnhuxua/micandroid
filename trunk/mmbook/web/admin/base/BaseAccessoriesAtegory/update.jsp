<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面BaseAccessoriesAtegory</title>
<script type="text/javascript">

Ext.ux.baseaccessoriesategory.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_baseaccessoriesategory = new Ext.form.FieldSet({
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
					                        fieldLabel: '分类名称',
					                        name:'sortName'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '父级分类id',
					                        name:'parentId'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '分类格式',
					                        name:'sortFormat'
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
	                        fieldLabel: '分类说明',
	                        name:'sortNotes'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '是否有下级节点',
	                        name:'lowerNode'
	                        }
                    ]
          }]
});

var frm_update_baseaccessoriesategory = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_baseaccessoriesategory],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_baseaccessoriesategory.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_baseaccessoriesategory.form.submit({
  	   		    url:'base/BaseAccessoriesAtegory/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_baseaccessoriesategory.doLoad(0);
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

var panel_update_baseaccessoriesategory = new Ext.Panel({
  		renderTo:'update_baseaccessoriesategorydiv',
  		width:Ext.get("update_baseaccessoriesategorydiv").getWidth(),
  		height:Ext.get("update_baseaccessoriesategorydiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_baseaccessoriesategory]
});

var record = grid_baseaccessoriesategory.getSelectionModel().getSelected();
if (record) {
      frm_update_baseaccessoriesategory.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_baseaccessoriesategorydiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_BaseAccessoriesAtegory'){
             panel.setActiveTab('update_BaseAccessoriesAtegory');
             panel_update_baseaccessoriesategory.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_baseaccessoriesategory.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_baseaccessoriesategory.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_baseaccessoriesategory.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.baseaccessoriesategory.update();
</script>
</head>
<body>
<div id="update_baseaccessoriesategorydiv" style="width:100%;height:100%;"></div>
</body>
</html>