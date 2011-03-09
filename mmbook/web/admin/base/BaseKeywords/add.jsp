<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面BaseKeywords</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.basekeywords");
Ext.ux.basekeywords.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_basekeywords = new Ext.form.FieldSet({
    id : 'field_add_basekeywords',
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

var frm_add_basekeywords = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_basekeywords],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_basekeywords.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_basekeywords.form.submit({
	   		    url:'base/BaseKeywords/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_basekeywords) == "undefined")) {
			            pag_basekeywords.doLoad(0);
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

var panel_add_basekeywords = new Ext.Panel({
		renderTo:'add_basekeywordsdiv',
		width:Ext.get("add_basekeywordsdiv").getWidth(),
		height:Ext.get("add_basekeywordsdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_basekeywords]
});

panel.on('resize',function(){
    if(Ext.get("add_BaseKeywordsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1129103'){
             panel.setActiveTab('1129103');
             panel_add_basekeywords.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_basekeywords.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_basekeywords.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_basekeywords.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.basekeywords.add();
</script>
</head>
<body>
<div id="add_basekeywordsdiv" style="width:100%;height:100%;"></div>
</body>
</html>