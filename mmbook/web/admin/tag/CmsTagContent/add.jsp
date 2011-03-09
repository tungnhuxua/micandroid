<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@include file="../inc/param.jsp"%>

<html>
<head>
<title>新增内容标签页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.cmstagcontent");
Ext.ux.cmstagcontent.add<%=u_id%>=Ext.extend(function(){
var storeLoadMask<%=u_id%> = new Ext.LoadMask(Ext.get("add_cmstagcontentdiv<%=u_id%>"), {//遮掩层
	msg : "正在加载数据，请等待..."
});
//storeLoadMask<%=u_id%>.show();
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');
var modelId= '0';
var templetId_temp = '0';

//关键字组下拉框
var keywordGroupStore = new Ext.data.Store({  
     proxy: new Ext.data.HttpProxy({  
         url: 'contentmanager/Content/selectKeywordGroup.do'
     }),  
     reader: new Ext.data.JsonReader({  
     root: 'list',  
     id: 'id'  
     }, [  
         {name: 'id', mapping: 'id'},  
         {name: 'mc', mapping: 'name'}  
     ])
}); 

//关键字下拉框
var keywordStore = new Ext.data.Store({  
     proxy: new Ext.data.HttpProxy({  
         url: 'contentmanager/Content/selectKeywordByGroupId.do?keyid=keyid'  
     }),  
     reader: new Ext.data.JsonReader({  
     root: 'list',
     id: 'id'  
     }, [  
         {name: 'id', mapping: 'id'},  
         {name: 'mc', mapping: 'name'}  
     ])  
});
 
var orderTypeStore = new Ext.data.SimpleStore({
	fields : ['val','tex'],
	data : [['','请选择'],['ORDER BY id_ ASC','ID升序'],['ORDER BY id_ DESC','ID降序'],
	['ORDER BY view_count_ ASC','点击量升序'],['ORDER BY view_count_ DESC','点击量降序'],
	['ORDER BY create_time_ ASC','发布时间升序'],['ORDER BY create_time_ DESC','发布时间降序'],
	['ORDER BY modify_time_ ASC','更新时间升序'],['ORDER BY modify_time_ DESC','更新时间降序']]
});

var orderTypeCombo = new Ext.form.ComboBox({
    id:'orderTypeCombo',
	xtype : 'combo',
	fieldLabel : '排序方式',
	emptyText : '请选择...',
	//allowBlank : false,
	triggerAction : 'all',
	hiddenName :'orderType',
	store : orderTypeStore,
	displayField : 'tex',
	valueField : 'val',
	editable : false,
	mode : 'local'
});

var continueModeNoStore = new Ext.data.SimpleStore({
	fields : ['val','tex'],
	data : [['','请选择'],['11','连载中'],['16','已完结']]
});

var continueModeNoCombo = new Ext.form.ComboBox({
    id:'continueModeNoCombo',
	xtype : 'combo',
	fieldLabel : '小说连载状态',
	emptyText : '请选择...',
	triggerAction : 'all',
	hiddenName :'continueModeNo',
	store : continueModeNoStore,
	displayField : 'tex',
	valueField : 'val',
	editable : false,
	mode : 'local'
});

var parentName =new Ext.ux.ComboBoxTree({
	xtype:'combotree',
	fieldLabel: '栏目选择<br/>关联内容类型操作',
	hiddenName:'parentName',
	allowUnLeafClick:true,
	loadingText: '正在加载...',
	emptyText : '请选择...',
	treeHeight:250,
	triggerAction : 'all',
	loadingText : '加载中...',
	url:'tag/CmsCategory/getCmsCategoryComboxByType.do?modelId='+modelId,
	onSelect:function(id){
	    modelIdtmp =1;
        frm_add_cmstagcontent<%=u_id%>.form.findField("categoryId").setValue(id);
    }
});
parentName.on('focus', function(){
	    if(modelId!=""){
	         parentName.url='tag/CmsCategory/getCmsCategoryComboxByType.do?modelId='+modelId;
             parentName.tree.root.reload();
             parentName.setValue("");
             parentName.hiddenField.value = "";
             parentName.collapse();
         }
});

var cmsTempletStore = new Ext.data.SimpleStore({
	fields: ['id', 'mc'],
	proxy: new Ext.data.HttpProxy({
		url: 'tag/CmsTemplet/getCmsTempletCombo.do?versionId=<%=v_id%>&webTempletType=21'
	})
});

var cmsTempletCombo = new Ext.form.ComboBox({
    id:'cmsTempletCombo',
	xtype : 'combo',
	fieldLabel : '模板选择',
	emptyText : '请选择...',
	triggerAction : 'all',
	hiddenName :'templetId',
	store : cmsTempletStore,
	displayField : 'mc',
	valueField : 'id',
	editable : false,
	mode : 'remote',
	listeners:{
	    "select":function(){
		    templetId_temp = this.value;
		}
	}
});

var cmsModelStore = new Ext.data.SimpleStore({
	fields: ['id', 'mc'],
	proxy: new Ext.data.HttpProxy({
		url: 'tag/CmsTagContent/getCmsModelCombo.do'
	})
});

var modelCombo = new Ext.form.ComboBox({
    id:'cmsTempletCombo',
	xtype : 'combo',
	fieldLabel : '内容类型',
	emptyText : '请选择...',
	triggerAction : 'all',
	hiddenName :'modelId',
	store : cmsModelStore,
	displayField : 'mc',
	valueField : 'id',
	editable : false,
	mode : 'remote',
	listeners : {
        select : function(combo){
            modelId =combo.value;
            Ext.getCmp("checkbox<%=u_id%>").destroy();
            Ext.getCmp("secondColumn_add<%=u_id%>").destroy();
            Ext.getCmp("field_add_fields<%=u_id%>").destroy();
  
            var field_add_fields<%=u_id%> = new Ext.form.FieldSet({
	            title : '数据读取字段',
	            autoHeight : true,
	            id:'field_add_fields<%=u_id%>',
	            layout: 'fit',
                     layout:'column',
                     items:[{
                     columnWidth:.5,
                     id:'secondColumn_add<%=u_id%>',
                     layout: 'form',
                     labelWidth : 120,
                     defaults : {
		   			 	anchor : '88%'
			         },
 					items: [{
			 			columnWidth : .4,
						layout : 'form',
						defaults : {
							anchor : '93%'
						},
						items : [{
							xtype : 'textfield',
							fieldLabel : '标签名称',
							name : 's_tagName'
						}]
					}]
            	}]
            });
         Ext.getCmp("frm_add_cmstagcontent<%=u_id%>").add(field_add_fields<%=u_id%>);
         Ext.getCmp("frm_add_cmstagcontent<%=u_id%>").doLayout('field_add_fields<%=u_id%>');
         
         fun_field_fit();

        }}
});
//parentName.tree.root.reload();

var field_add_cmstagcontent<%=u_id%> = new Ext.form.FieldSet({
    id:'field_add_cmstagcontent<%=u_id%>',
	title : '添加内容标签',
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
				         fieldLabel:'模板版本ID',
				         name:'versionId',
				         value:'<%=v_id%>'
			          },{
		                    	xtype:'textfield',
		                        fieldLabel: '内容标签名称',
		                        name:'tagNameString',
								invalidText : '标签名称已经被使用!', 
		                        allowBlank : false,
		                        minLength: 2,
								maxLength: 16,
		                        validationEvent : 'blur',
		                        regex:/^[\w\u4E00-\u9FFF]+$/,
		                        regexText:'输入中文、数字和英文',

								validator : function(thisText) {
							        var isok =false;
	                       	         Ext.Ajax.request({
	                                    url : 'tag/CmsTagContent/tagNameText.do',   
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText,
	                                        versionId : <%=v_id%>
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
		             }]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                    xtype:'textfield',
	                    fieldLabel: '标签说明',
					    name:'tagExp'
	                }]
          }]
});  

var field_add_contentmodel<%=u_id%> = new Ext.form.FieldSet({
    id:'field_add_contentmodel<%=u_id%>',
	title : '选择内容模型',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [modelCombo]
            	}]
});

function getCheckboxgroupData<%=u_id%>(modelId){
    var myCheckboxItems = [];
    Ext.Ajax.request({
        url: 'tag/CmsTagContent/getCheckbox.do?modelId='+modelId,
        sync : true,
        success : function(response, options){
        var result_data = Ext.util.JSON.decode(response.responseText).root; 

        for(var i = 0; i < result_data.length; i++) {
            var boxLabel = result_data[i]["boxLabel"];   
            var name = result_data[i]["name"];   
            var inputValue =result_data[i]["inputValue"];
            var checked = result_data[i]["checked"];
            var disabled = result_data[i]["disabled"];
            myCheckboxItems.push({
                 boxLabel : boxLabel,
                 name : 'fieldsCode',
                 inputValue: inputValue,
                 checked: checked,
                 disabled: disabled
            });   
           //alert(boxLabel);
        }}
    }); 
    return   myCheckboxItems; 
}; 

var myCheckboxGroup<%=u_id%> = new Ext.form.CheckboxGroup({  
      columnWidth: 1,  
      xtype : 'checkboxgroup',
      id: 'checkbox<%=u_id%>',
      itemCls : 'x-check-group-alt',   
      columns: 3,
      name: 'fieldsCode',
	  fieldLabel: '读取字段',   
	  items: [{
		xtype : 'textfield',
		fieldLabel : '标签名称',
		name : 's_tagName'
	  }]
});

var field_add_fields<%=u_id%> = new Ext.form.FieldSet({
	 title : '数据读取字段',
	 autoHeight : true,
	 id:'field_add_fields<%=u_id%>',
	 layout: 'fit',
     layout:'column',
     items:[{
             columnWidth:.5,
              id:'secondColumn_add<%=u_id%>',
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [{
			 			columnWidth : .4,
						layout : 'form',
						defaults : {
							anchor : '93%'
						},
				items : [{
					xtype : 'textfield',
					fieldLabel : '标签名称',
					name : 's_tagName'
				}]
			}]
      }]
});
fun_setText =function(textfield,value){
	frm_add_cmstagcontent<%=u_id%>.form.findField(textfield).setValue(value);
}
fun_setKeyId =function(textfield,value){
    keywordStore.proxy= new Ext.data.HttpProxy({url: 'contentmanager/Content/selectKeywordByGroupId.do?keyid=keyid'});  
	keywordStore.load();
	setTimeout(function() {
	    frm_add_cmstagcontent<%=u_id%>.form.findField(textfield).setValue(value);
	}, 300)
}
var field_add_date<%=u_id%> = new Ext.form.FieldSet({
    id:'field_add_date<%=u_id%>',
	title : '数据调用条件',
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
	                    	allowBlank : false,
	                        fieldLabel: '栏目选择<br/>常用变量表示<a href=\'javascript:fun_setText(\"categoryId\",\"$catid\")\'><span style=\'vertical-align: bottom\'>$catid</span></a>',
	                        name:'categoryId'
	                        },{
								xtype:'combo',  
								fieldLabel: '关键字组<br/>关联关键字选择',  
								store: keywordGroupStore,  
								valueField :"id",  
								displayField: "mc",  
								mode: 'remote', 
								forceSelection: true,
								emptyText:'请选择关键字组...', 
								editable: false,
								triggerAction: 'all',  
								listeners:{    
						             select : function(combo, record,index){ 
						             	 frm_add_cmstagcontent<%=u_id%>.form.findField('keyId').setValue('') ;
						                 keywordStore.proxy= new Ext.data.HttpProxy({url: 'contentmanager/Content/selectKeywordByGroupId.do?groupId='+combo.value+'&keyid=keyid'});  
						                 keywordStore.load();   
						             }  
					         	}
							},{
				                xtype : 'datefield',
				                fieldLabel : '更新日期',
				                //allowBlank : false,
				                format: 'Y-m-d H:i:s',
			            	    name : 'updateTime'
			                },orderTypeCombo]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [parentName,{
								xtype:'lovcombo',
								fieldLabel: '关键字<br/>常用变量表示<a href=\'javascript:fun_setKeyId(\"keyId\",\"$keyid\")\'><span style=\'vertical-align: bottom\'>$keyid</span></a>',  
								hiddenName : 'keyId',
								valueField : 'id',
								displayField : 'mc',
								editable : false,
								triggerAction : 'all',
								allowBlank : true,
								emptyText:'请选择关键字...', 
								store : keywordStore,
		                        blankText :'关键字不能为空!'
				  },{
				                xtype : 'datefield',
				                fieldLabel : '发布日期',
				                //allowBlank : false,
				                format: 'Y-m-d H:i:s',
			            	    name : 'insertTime'
			                },continueModeNoCombo]
          }]
});  


var field_add_datefs<%=u_id%> = new Ext.form.FieldSet({
    id:'field_add_datefs<%=u_id%>',
	title : '数据显示方式',
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
                         xtype: 'radiogroup',
                         fieldLabel: '分页显示',
                         items: [
                            {boxLabel: '是', name: 'ifPage', inputValue: 0},
                            {boxLabel: '否', name: 'ifPage', inputValue: 1,checked: true}
                         ]},cmsTempletCombo
                ]},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                    	xtype:'numberfield',
	                        fieldLabel: '调用条数',
	                        name:'rowNums',
	                        regex :  /^[0-9]*[1-9][0-9]*$/ , //正则表达式在
	                        regexText:'只能输入数字!',//正则表达式错误提示 
	                        maxValue: 100,
	                        minValue: 0,
	                        allowBlank : false,
	                        value: 20
	                }],
	                buttonAlign : 'left',
                    buttons: [{
                        text: '修改选中的模板',
  	                    handler:function(){
  	                        fun_opnetab("update_CmsTemplet"+templetId_temp,"修改模板","sitemanage/CmsTemplet/updateBaset.do?id="+templetId_temp,"edit");
  	                    }
  	               }]
          }]
});

var frm_add_cmstagcontent<%=u_id%> = new Ext.FormPanel({
        id:'frm_add_cmstagcontent<%=u_id%>',
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 120,
        autoScroll : true,
        items:[field_add_cmstagcontent<%=u_id%>,field_add_contentmodel<%=u_id%>,field_add_fields<%=u_id%>,
        field_add_date<%=u_id%>,field_add_datefs<%=u_id%>],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
	            if (frm_add_cmstagcontent<%=u_id%>.form.isValid() == false){
	    		   return;
	  		    }
	  		    var ids = [];   
                 var cbitems = Ext.getCmp("checkbox<%=u_id%>").items;   
                 for (var i = 0; i < cbitems.length; i++) {   
                     if (cbitems.itemAt(i).checked) {   
                          ids.push(cbitems.itemAt(i).inputValue);   
                     }
                 }
                 Ext.MessageBox.confirm('确认操作','确定保存内容标签页面?',function(btn){
  	            if (btn == 'yes'){
	  		    frm_add_cmstagcontent<%=u_id%>.form.submit({
	   		    url:'tag/CmsTagContent/save.do',
	   		    params:{
					fieldsCodeString: ids.toString()
				}
				,method:'POST',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          <% if("1".equals(v_id)){%>
  	   		              if (!(typeof(pag_cmstagcontent1011108) == "undefined")) {
			                  pag_cmstagcontent1011108.doLoad(0);
			              }
  	   		          <%}else{%>
  	   		              if (!(typeof(pag_cmstagcontent1012108) == "undefined")) {
			                  pag_cmstagcontent1012108.doLoad(0);
			              }
  	   		          <%}%>
	 		       },
	 		     scope:this,
	 		     failure:function(form,action){
	 		          Ext.Msg.show({title: '错误', msg: action.result.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
	   		       }
	  		     })}
  	  		     },this);  
	   		    }
	     },{
  	            text:'关闭',
  	            style : 'margin-left: 8px',
  	   		     handler:function(){
  	              Ext.MessageBox.confirm('确认操作','确定关闭内容推荐标签页面?',function(btn){
  	                if (btn == 'yes'){
  				       panel<%=u_id%>.remove(panel<%=u_id%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }] 
});

function fun_field_fit() {
	Ext.getCmp("field_add_cmstagcontent<%=u_id%>").setWidth(Ext.get("field_add_cmstagcontent<%=u_id%>").getWidth() - 22);
    Ext.getCmp("field_add_contentmodel<%=u_id%>").setWidth(Ext.get("field_add_cmstagcontent<%=u_id%>").getWidth());
	Ext.getCmp("field_add_fields<%=u_id%>").setWidth(Ext.get("field_add_cmstagcontent<%=u_id%>").getWidth());
	Ext.getCmp("field_add_date<%=u_id%>").setWidth(Ext.get("field_add_cmstagcontent<%=u_id%>").getWidth());
	Ext.getCmp("field_add_datefs<%=u_id%>").setWidth(Ext.get("field_add_cmstagcontent<%=u_id%>").getWidth());
};

var panel_add_cmstagcontent<%=u_id%> = new Ext.Panel({
		renderTo:'add_cmstagcontentdiv<%=u_id%>',
		width:Ext.get("add_cmstagcontentdiv<%=u_id%>").getWidth(),
		height:Ext.get("add_cmstagcontentdiv<%=u_id%>").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_cmstagcontent<%=u_id%>]
});
fun_field_fit();

panel<%=u_id%>.on('beforeremove', function(tab, item) {
		if(item.id=='<%=u_id%>'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_add_cmstagcontent<%=u_id%>.destroy();
		    }
		}
});

panel<%=u_id%>.on('resize',function(){
    if(Ext.get("add_cmstagcontentdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('<%=u_id%>');
             panel_add_cmstagcontent<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_cmstagcontent<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             fun_field_fit();
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_add_cmstagcontent<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_cmstagcontent<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
            fun_field_fit();
        }
    }
});
setTimeout(function() {
	storeLoadMask<%=u_id%>.hide();
}, 300)

},Ext.util.Observable);
new Ext.ux.cmstagcontent.add<%=u_id%>();
</script>
</head>
<body>
<div id="add_cmstagcontentdiv<%=u_id%>" style="width:100%;height:100%;"></div>
</body>
</html>