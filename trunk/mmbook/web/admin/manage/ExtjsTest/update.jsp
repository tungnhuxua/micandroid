<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">

Ext.ux.extjstest.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_extjstest = new Ext.form.FieldSet({
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
					                        fieldLabel: '能功名称',
					                        name:'functionName'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '能功类型',
					                        name:'types'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '系联电话',
					                        name:'telephone'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '次数',
					                        name:'frequency'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '等级',
					                        name:'rating'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '能功说明',
					                        name:'functionExplain'
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
	                        fieldLabel: '上级能功ID',
	                        name:'parentId'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '新增时间',
	                        name:'insertTime'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '手机号码',
	                        name:'mobileTelephone'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '上传文件URL',
	                        name:'upfileUrl'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '是否为默认功能',
	                        name:'whetherDefault'
	                        }
                    ]
          }]
});

var frm_update_extjstest = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_extjstest],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_extjstest.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_extjstest.form.submit({
  	   		    url:'manage/ExtjsTest/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_extjstest.doLoad(0);
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

var panel_update_extjstest = new Ext.Panel({
  		renderTo:'update_extjstestdiv',
  		width:Ext.get("update_extjstestdiv").getWidth(),
  		height:Ext.get("update_extjstestdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_extjstest]
});

var record = grid_extjstest.getSelectionModel().getSelected();
if (record) {
      frm_update_extjstest.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_extjstestdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_ExtjsTest'){
             panel.setActiveTab('update_ExtjsTest');
             panel_update_extjstest.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_extjstest.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_extjstest.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_extjstest.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.extjstest.update();
</script>
</head>
<body>
<div id="update_extjstestdiv" style="width:100%;height:100%;"></div>
</body>
</html>