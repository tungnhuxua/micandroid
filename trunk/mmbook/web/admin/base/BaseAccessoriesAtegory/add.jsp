<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面BaseAccessoriesAtegory</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.baseaccessoriesategory");
Ext.ux.baseaccessoriesategory.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_baseaccessoriesategory = new Ext.form.FieldSet({
    id : 'field_add_baseaccessoriesategory',
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

var frm_add_baseaccessoriesategory = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_baseaccessoriesategory],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_baseaccessoriesategory.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_baseaccessoriesategory.form.submit({
	   		    url:'base/BaseAccessoriesAtegory/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_baseaccessoriesategory) == "undefined")) {
			            pag_baseaccessoriesategory.doLoad(0);
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

var panel_add_baseaccessoriesategory = new Ext.Panel({
		renderTo:'add_baseaccessoriesategorydiv',
		width:Ext.get("add_baseaccessoriesategorydiv").getWidth(),
		height:Ext.get("add_baseaccessoriesategorydiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_baseaccessoriesategory]
});

panel.on('resize',function(){
    if(Ext.get("add_BaseAccessoriesAtegorydiv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_baseaccessoriesategory.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_baseaccessoriesategory.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_baseaccessoriesategory.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_baseaccessoriesategory.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.baseaccessoriesategory.add();
</script>
</head>
<body>
<div id="add_baseaccessoriesategorydiv" style="width:100%;height:100%;"></div>
</body>
</html>