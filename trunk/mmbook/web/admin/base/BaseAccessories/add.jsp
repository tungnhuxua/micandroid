<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面BaseAccessories</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.baseaccessories");
Ext.ux.baseaccessories.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_baseaccessories = new Ext.form.FieldSet({
    id : 'field_add_baseaccessories',
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
		                        fieldLabel: '网站附件分类ID',
		                        name:'accessoriesTypeId'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '内容ID',
		                        name:'contentId'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '附件URL',
		                        name:'contentUrl'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '附件文件大小',
		                        name:'fileSize'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '文件后缀',
		                        name:'fileSurfix'
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
	                        fieldLabel: '附属ID',
	                        name:'subordinationId'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '分类ID',
	                        name:'sortId'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '附件格式',
	                        name:'format'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '附件说明',
	                        name:'notes'
	                        }
                    ]
	 }]
});

var frm_add_baseaccessories = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_baseaccessories],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_baseaccessories.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_baseaccessories.form.submit({
	   		    url:'base/BaseAccessories/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_baseaccessories) == "undefined")) {
			            pag_baseaccessories.doLoad(0);
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

var panel_add_baseaccessories = new Ext.Panel({
		renderTo:'add_baseaccessoriesdiv',
		width:Ext.get("add_baseaccessoriesdiv").getWidth(),
		height:Ext.get("add_baseaccessoriesdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_baseaccessories]
});

panel.on('resize',function(){
    if(Ext.get("add_BaseAccessoriesdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_baseaccessories.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_baseaccessories.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_baseaccessories.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_baseaccessories.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.baseaccessories.add();
</script>
</head>
<body>
<div id="add_baseaccessoriesdiv" style="width:100%;height:100%;"></div>
</body>
</html>