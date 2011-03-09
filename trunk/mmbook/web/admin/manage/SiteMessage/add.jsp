<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitemessage");
Ext.ux.sitemessage.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_sitemessage = new Ext.form.FieldSet({
    id : 'field_add_sitemessage',
	title : '新增',
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

var frm_add_sitemessage = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_sitemessage],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitemessage.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitemessage.form.submit({
	   		    url:'manage/SiteMessage/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitemessage) == "undefined")) {
			            pag_sitemessage.doLoad(0);
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

var panel_add_sitemessage = new Ext.Panel({
		renderTo:'add_sitemessagediv',
		width:Ext.get("add_sitemessagediv").getWidth(),
		height:Ext.get("add_sitemessagediv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitemessage]
});

panel.on('resize',function(){
    if(Ext.get("add_SiteMessagediv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_sitemessage.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_sitemessage.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitemessage.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_sitemessage.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitemessage.add();
</script>
</head>
<body>
<div id="add_sitemessagediv" style="width:100%;height:100%;"></div>
</body>
</html>