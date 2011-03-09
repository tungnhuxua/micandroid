<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面TagModel</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.tagmodel");
Ext.ux.tagmodel.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_tagmodel = new Ext.form.FieldSet({
    id : 'field_add_tagmodel',
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
			         items: [
			         		{
			        		   xtype:'textfield',
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

var frm_add_tagmodel = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_tagmodel],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_tagmodel.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_tagmodel.form.submit({
	   		    url:'tag/TagModel/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_tagmodel) == "undefined")) {
			            pag_tagmodel.doLoad(0);
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

var panel_add_tagmodel = new Ext.Panel({
		renderTo:'add_tagmodeldiv',
		width:Ext.get("add_tagmodeldiv").getWidth(),
		height:Ext.get("add_tagmodeldiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_tagmodel]
});

panel.on('resize',function(){
    if(Ext.get("add_TagModeldiv")){
        var p =panel.getActiveTab().getId();
        if(p!='add_TagModel'){
             panel.setActiveTab('add_TagModel');
             panel_add_tagmodel.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_tagmodel.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_tagmodel.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_tagmodel.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.tagmodel.add();
</script>
</head>
<body>
<div id="add_tagmodeldiv" style="width:100%;height:100%;"></div>
</body>
</html>