<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@include file="../inc/param.jsp"%>
<html>
<head>
<title>新增网站皮肤页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.cmsskins");
Ext.ux.cmsskins.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');


/**cms 模板版本   **/
var cmsVersionStore = new Ext.data.SimpleStore({
			fields: ['value', 'text'],
			proxy: new Ext.data.HttpProxy({
			    url: 'tag/CmsVersion/getCmsVersionComboBox.do'
			})
});

var cmsversionCombo = new Ext.form.ComboBox({
      fieldLabel : '模板版本',  //UI标签名称
      hiddenName :'versionId',   //作为form提交时传送的参数名
      allowBlank : false,  //是否允许为空
      mode : "remote",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : cmsVersionStore,
      valueField : 'value',  //传送的值
      displayField : 'text'  //UI列表显示的文本
});




//设成默认 本地下拉框
var ds_data_isdefault = new Ext.data.SimpleStore({
	fields : ['id','mc'],
	data : [['1','是'],['2','否']]
});

var field_add_cmsskins = new Ext.form.FieldSet({
	title : '基础数据',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [cmsversionCombo
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '<font color="red">*</font>方案目录',
		                        minLength: 2,
		                        allowBlank:false,
		                        maxLength: 30,
		                        vtype : 'alphanum',
		                        name:'skinDir'
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
	                        fieldLabel: '<font color="red">*</font>方案名称',
	                        minLength: 2,
		                    allowBlank:false,
		                    maxLength: 30,
	                        name:'skinName'
	                        }
	                        ,{
								xtype : 'combo',
								fieldLabel : '<font color="red">*</font>设成默认',
								emptyText : '请选择...',
								allowBlank : false,
								triggerAction : 'all',
								forceSelection : true ,
								store : ds_data_isdefault,
								displayField : 'mc',
								hiddenName :'isdefault',
								valueField : 'id',
								editable : false,
								minListWidth : 20,
								width : 20,
								mode : 'local'
		                        }
                    ]
	           }]
});

var frm_add_cmsskins = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_cmsskins],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_cmsskins.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_cmsskins.form.submit({
	   		    url:'tag/CmsSkins/save.do',
	   		    success:function(form,action){ 
			         
				 Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK,fn:function(){
				       if (!(typeof(pag_cmsskins) == "undefined")) {
			            pag_cmsskins.doLoad(0);
			          }
			          fun_copnetab('1151203','方案管理','admin/tag/CmsSkins/index.jsp','icon-nav-p1');
	   		     }});

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

var panel_add_cmsskins = new Ext.Panel({
		renderTo:'add_cmsskinsdiv',
		width:Ext.get("add_cmsskinsdiv").getWidth(),
		height:Ext.get("add_cmsskinsdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_cmsskins]
});

panel.on('resize',function(){
    if(Ext.get("add_cmsskinsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1013102'){
             panel.setActiveTab('1013102');
             panel_add_cmsskins.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_cmsskins.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_cmsskins.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_cmsskins.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmsskins.add();
</script>
</head>
<body>
<div id="add_cmsskinsdiv" style="width:100%;height:100%;"></div>
</body>
</html>