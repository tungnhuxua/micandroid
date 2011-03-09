<%@ page   contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增功能测试页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.extjstest");
Ext.ux.extjstest.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');

//远程取数下拉框据例子
var st_types_data = new Ext.data.SimpleStore({
			//数据格式 服务类与本地格式要一样
			fields: ['value', 'text','note'],
			proxy: new Ext.data.HttpProxy({
			    //请求cn.mmbook.platform.action.manage.ExtjsTestAction.getTypesCombox 方法 
			    url: 'manage/ExtjsTest/getTypesCombox.do'
			})
});

var remoteComboBox= new Ext.form.ComboBox({
      fieldLabel : '功能类型',  //UI标签名称
      name : 'types',   //作为form提交时传送的参数名
      allowBlank : false,  //是否允许为空
      mode : "remote",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : st_types_data,
      value:'0',  //设置当前选中的值, 也可用作初始化时的默认值, 默认为空
      valueField : 'value',  //传送的值
      displayField : 'text'  //UI列表显示的文本
});










var field_add_extjstest_base = new Ext.form.FieldSet({
	id : 'field_add_extjstest_base',
	title : '信息输入区',
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
		                        fieldLabel: '能功名称',
		                        allowBlank : false,
		                        name:'functionName'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '系联电话',
		                        name:'telephone'
		                        },
		                        {
		                    	xtype:'textfield',
		                        fieldLabel: '次数',
		                        name:'frequency'
		                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [
                			{
	                    	xtype:'datefield',
	                        fieldLabel: '新增时间',
	                        name:'insertTime'
	                        } 
	                        ,
	                        {
	                    	xtype:'textfield',
	                        fieldLabel: '手机号码',
	                        name:'mobileTelephone'
	                        }
	                        ,
	                        {
		                    	xtype:'textfield',
		                        fieldLabel: '能功说明',
		                        name:'functionExplain'
		                    }
                    ]
	           }]
});

var field_add_extjstest_important = new Ext.form.FieldSet({
	id : 'field_add_extjstest_important',
	title : '重要数据',
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
			        xtype: 'treeField',   
			        name: 'pigSource',   
			        fieldLabel: '来源地',   
			        emptyText:'选择来源地',   
			        listWidth:200,   
			        listHeight:200,   
			        readOnly:false,   
			        dataUrl:'manage/ExtjsTest/getSynTree.do?parentId=0'  
	               }
	                         ,
	                         {
								xtype : 'combo',
								fieldLabel : '等级',
								emptyText : '请选择',
								//allowBlank : false,
								triggerAction : 'all',
								hiddenName :'rating',
                                store : new Ext.data.SimpleStore({ 
                                   //填充的数据
									fields : ['val','tex'],
									data : [['1','一级'],['2','二级'],['3','三级'],['4','四级']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [
                	
                			//comboxWithTree
                	      
	                        //,
	                        {
	                            //是否为默认 选择 下拉框
								xtype : 'combo',
								fieldLabel : '是否为默认功能',
								emptyText : '请选择',
								//allowBlank : false,
								triggerAction : 'all',
								hiddenName :'whetherDefault',
                                store : new Ext.data.SimpleStore({ 
                                   //填充的数据
									fields : ['val','tex'],
									data : [['1','否'],['2','是']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        }
                    ]
	           }
	           ]
	 
});
 
var field_add_extjstest_upfile = new Ext.form.FieldSet({
	id : 'field_add_extjstest_upfile',
	title : '上传文件',
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
	                        fieldLabel: '上传文件URL',
	                        name:'upfileUrl'
	                        } 
	                         
	                    ]
            	} 
	           ,{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '60%'
					},
          buttonAlign : 'center',
          buttons: [{
                id : 'mybuttons',
  	            text: '上传文件',
  	            handler:function(){
  	               fun_windows("detail_ExtjsTest","窗口标题","/mmbook/admin/uploader/index.jsp","弹出窗口说明");
  	             }
  	     }]
	   }
	           
	           ]
});

//关键字组下拉框
 var keywordGroupStore = new Ext.data.Store({  
     proxy: new Ext.data.HttpProxy({  
         url: 'manage/ExtjsTest/getLovcombo.do'
     }),  
     reader: new Ext.data.JsonReader({  
     root: 'list',  
     id: 'id'  
     }, [  
         {name: 'id', mapping: 'id'},  
         {name: 'name', mapping: 'functionName'}  
     ])  
 }); 

var field_add_extjstest_aggregate = new Ext.form.FieldSet({
	id : 'field_add_extjstest_aggregate',
	title : '能功选择',
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
	                        fieldLabel: '能功集合体',
	                        name:'aggregate'
	                        }]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
					           xtype:'lovcombo',
					           fieldLabel : '<span style="color:red;">* </span>能功集合',
					           hiddenName : 'ids',
					           allowBlank : false,
					           displayField : 'name',
					           valueField : 'id',
					           editable : false,
					           triggerAction : 'all',
					           allowBlank : true,
					           blankText : '关键字组不能为空!',
					           emptyText : '请选择关键字组...',
					           store : keywordGroupStore
							  }]
	           } ]
});

var frm_add_extjstest = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        margins : '8',
        region:'center',
        labelWidth : 120,
        items:[field_add_extjstest_base,field_add_extjstest_important,field_add_extjstest_upfile,field_add_extjstest_aggregate],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_extjstest.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_extjstest.form.submit({
	   		    url:'manage/ExtjsTest/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_extjstest) == "undefined")) {
			            pag_extjstest.doLoad(0);
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

var panel_add_extjstest = new Ext.Panel({
		renderTo:'add_extjstestdiv',
		width:Ext.get("add_extjstestdiv").getWidth(),
		height:Ext.get("add_extjstestdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_extjstest]
});

panel.on('resize',function(){
    if(Ext.get("add_ExtjsTestdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1110101'){
             panel.setActiveTab('1110101');
             panel_add_extjstest.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_extjstest.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_extjstest.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_extjstest.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.extjstest.add();
</script>
</head>
<body>
<div id="tree"></div>
<div id="add_extjstestdiv" style="width:100%;height:100%;"></div>
</body>
</html>