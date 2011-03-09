<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改页面</title>
<script type="text/javascript">

Ext.ux.sitemessage.update=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_update_sitemessage = new Ext.form.FieldSet({
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
					                        fieldLabel: '留言标题',
					                        name:'messageTitle'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '回复留言ID',
					                        name:'replyId'
					                        }
				                        ,{
					                    	xtype:'textfield',
					                        fieldLabel: '留言类别',
					                        name:'messageType'
				                       } ,{
					                    	xtype:'textfield',
					                        fieldLabel: '是否已回复',
					                        name:'whetherReply'}
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                  
	                    		xtype:'textfield',
	                        fieldLabel: '留言内容',
	                        name:'messageContent'
	                        }
	                        ,{
	                    		xtype:'textfield',
	                        fieldLabel: '留言人ID',
	                        name:'messageUser'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '是否已查看',
	                        name:'whetherReader'
	                        },{
	                    	xtype:'textfield',
	                        fieldLabel: '留言时间',
	                        name:'insertTime'}
                    ]
          }]
});

var frm_update_sitemessage = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitemessage],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitemessage.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitemessage.form.submit({
  	   		    url:'manage/SiteMessage/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_sitemessage.doLoad(0);
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

var panel_update_sitemessage = new Ext.Panel({
  		renderTo:'update_sitemessagediv',
  		width:Ext.get("update_sitemessagediv").getWidth(),
  		height:Ext.get("update_sitemessagediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitemessage]
});

var record = grid_sitemessage.getSelectionModel().getSelected();
if (record) {
      frm_update_sitemessage.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_sitemessagediv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteMessage'){
             panel.setActiveTab('update_SiteMessage');
             panel_update_sitemessage.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_sitemessage.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitemessage.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_sitemessage.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitemessage.update();
</script>
</head>
<body>
<div id="update_sitemessagediv" style="width:100%;height:100%;"></div>
</body>
</html>