<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>基本参数新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.basicparameters");
Ext.ux.basicparameters.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');

// 类型参数:1,系统参数;2,平台参数 下拉框
var ds_rating_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['1','系统参数'],['2','平台参数 ']]
});

var field_add_basicparameters = new Ext.form.FieldSet({
    id : 'field_add_basicparameters',
	title : '数据录入',
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
		                        fieldLabel: '基本参数名称',
		                        allowBlank : false,
		                        minLength: 6,
		                        maxLength: 15,
		                        name:'parametersName',
 								validationEvent : 'blur',
								validator : function(thisText) {
							     var isok =false;
	                       	     Ext.Ajax.request({
	                                    url : 'sitemanage/CmsTagCategory/repetitionText.do',   
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText   
	                                    },
	                                     success : function(response,options){
							                  var res = Ext.util.JSON.decode(response.responseText); 
							                  if(res.success == true){  
							                     isok=true;
							                  }
							             }
	                                });
	                        		return isok;
	                            }
		                    }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '基本参数标签',
		                        allowBlank : false,
		                        minLength: 6,
		                        maxLength: 50,
		                        name:'parametersTag'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '基本参数说明', 
		                        maxLength: 100,
		                        name:'parametersNote'
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
	                        fieldLabel: '基本参数值',
		                    allowBlank : false,
		                    minLength: 6,
		                    maxLength: 50,
	                        name:'parametersValue'
	                        }
	                        ,{
								xtype : 'combo',   // xtype 类型 combo 对应: Ext.form.ComboBox
								fieldLabel : '功能等级',  //标题名称
								emptyText : '请选择...',
								allowBlank : false,     //是否必选 false 心选 , true 可选
								triggerAction : 'all',
								forceSelection : true ,   //如果为true，将限制选择的值必须是下拉列表中的值。 如果为false，允许用户设置任意值到输入栏 (默认值为 false) 
								store : ds_rating_data,   //加载 store
								hiddenName :'parametersType',  // 查询条件名称
								valueField : 'id',        // 对应 ds_rating_data id
								displayField : 'val',     //对应 ds_rating_data val
								editable : false,
								mode : 'local'  // local 本地 ,  remote 远程
						     
                    }
                 ]
	 }]
});

var frm_add_basicparameters = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_basicparameters],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_basicparameters.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_basicparameters.form.submit({
	   		    url:'manage/BasicParameters/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_basicparameters) == "undefined")) {
			            pag_basicparameters.doLoad(0);
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

var panel_add_basicparameters = new Ext.Panel({
		renderTo:'add_basicparametersdiv',
		width:Ext.get("add_basicparametersdiv").getWidth(),
		height:Ext.get("add_basicparametersdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_basicparameters]
});

panel.on('resize',function(){
    if(Ext.get("add_BasicParametersdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1129101'){
             panel.setActiveTab('1129101');
             panel_add_basicparameters.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_basicparameters.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_basicparameters.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_basicparameters.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.basicparameters.add();
</script>
</head>
<body>
<div id="add_basicparametersdiv" style="width:100%;height:100%;"></div>
</body>
</html>