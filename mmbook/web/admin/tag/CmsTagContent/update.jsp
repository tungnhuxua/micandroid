<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@ page import= "cn.mmbook.platform.model.tag.CmsTagContent"%> 

<%
	CmsTagContent cmsTagContent =(CmsTagContent)request.getAttribute("cmsTagContent");
%>
<html>
<head>
<title>修改内容标签页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.cmstagcontent");
Ext.ux.cmstagcontent.update<%=cmsTagContent.getId()%>=Ext.extend(function(){
var storeLoadMask<%=cmsTagContent.getId()%> = new Ext.LoadMask(Ext.get("update_cmstagcontentdiv<%=cmsTagContent.getId()%>"), {//遮掩层
	msg : "正在加载数据，请等待..."
});

var panel<%=cmsTagContent.getId()%> = Ext.getCmp('center-tab-panel');
var cmsModelstoreData =<%=cmsTagContent.getCmsModelStore()%>;
//var posStoreDate =<%//=cmsTagContent.getPosStore()%>;
var fieldsCode= '<%=cmsTagContent.getFieldsCode()%>';
var tagName ='<%=cmsTagContent.getTagName()%>';
var modelId= '<%=cmsTagContent.getModelId()%>';
var templetId_temp = '<%=cmsTagContent.getTempletId()%>';
var keywordIds ='<%=cmsTagContent.getKeyId()%>';

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
         url: 'contentmanager/Content/selectKeywordByGroupId.do?keywordIds='+keywordIds+'&keyid=keyid'
     }),  
     reader: new Ext.data.JsonReader({  
     root: 'list',
     id: 'id'  
     }, [  
         {name: 'id', mapping: 'id'},  
         {name: 'mc', mapping: 'name'}  
     ]),
     listeners: {     
        load: function() {     
             multiSelectComboBox.setValue(keywordIds);     
        }     
    }  
});
keywordStore.load();
var multiSelectComboBox = new Ext.ux.form.LovCombo({
	xtype:'lovcombo',
	fieldLabel: '关键字<br/>常用变量表示<a href=\'javascript:fun_setKeyId(\"keyId\",\"$keyid\")\'><span style=\'vertical-align: bottom\'>$keyid</span></a>',  
	hiddenName : 'keyId',
	valueField : 'id',
	displayField : 'mc',
	editable : false,
	triggerAction : 'all',
	emptyText:'请选择关键字...', 
	store : keywordStore,
	blankText :'关键字不能为空!'
});

var orderTypeStore = new Ext.data.SimpleStore({
	fields : ['value','text'],
	data : [['','请选择'],['ORDER BY id_ ASC','ID升序'],['ORDER BY id_ DESC','ID降序'],
	['ORDER BY view_count_ ASC','点击量升序'],['ORDER BY view_count_ DESC','点击量降序'],
	['ORDER BY create_time_ ASC','发布时间升序'],['ORDER BY create_time_ DESC','发布时间降序'],
	['ORDER BY modify_time_ ASC','更新时间升序'],['ORDER BY modify_time_ DESC','更新时间降序']]
});

var orderTypeCombo =  new Ext.form.ComboBox({
      fieldLabel : '排序方式',  //UI标签名称
      hiddenName: 'orderType',
      allowBlank : false,  //是否允许为空
      mode : "local",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : orderTypeStore,
      value:'<%if(null!=cmsTagContent.getOrderType()){out.print(cmsTagContent.getOrderType());}%>',
      valueField : 'value',  //传送的值
      displayField : 'text' 
});

var continueModeNoStore = new Ext.data.SimpleStore({
	fields : ['value','text'],
	data : [['','请选择'],['11','连载中'],['16','已完结']]
});

var continueModeNoCombo =  new Ext.form.ComboBox({
      fieldLabel : '小说连载状态',  //UI标签名称
      hiddenName: 'continueModeNo',
      allowBlank : false,  //是否允许为空
      mode : "local",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : continueModeNoStore,
      value:'<%if(null!=cmsTagContent.getContinueModeNo()){out.print(cmsTagContent.getContinueModeNo());}%>',
      valueField : 'value',  //传送的值
      displayField : 'text' 
});

var cmsTempletStore = new Ext.data.SimpleStore({
	fields: ['id', 'mc'],
	proxy: new Ext.data.HttpProxy({
		url: 'tag/CmsTemplet/getCmsTempletCombo.do?versionId=<%=cmsTagContent.getVersionId()%>&webTempletType=21'
	}),
	listeners: {     
        load: function() {     
             cmsTempletCombo.setValue(templetId_temp);     
        }     
    }    
});
cmsTempletStore.load(); 
var cmsTempletCombo    =  new Ext.form.ComboBox({
      fieldLabel : '模板选择',  //UI标签名称
      hiddenName: 'templetId',
      allowBlank : false,  //是否允许为空
      mode : "remote",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : cmsTempletStore,
      value:'<%if(null!=cmsTagContent.getContinueModeNo()){out.print(cmsTagContent.getContinueModeNo());}%>',
      valueField : 'id',  //传送的值
      displayField : 'mc' 
});

var cmsModelStore = new Ext.data.SimpleStore({
	fields: ['id', 'mc'],
	data : cmsModelstoreData
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
        frm_update_cmstagcontent<%=cmsTagContent.getId()%>.form.findField("categoryId").setValue(id);
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

var modelCombo = new Ext.ux.ComboBoxTree({
	store : cmsModelStore,
	width : 140,
	mode : 'local',
	fieldLabel: '内容类型',
	hiddenName: 'modelId',
	value: '<%=cmsTagContent.getModelId()%>',
	listeners : {
        select : function(combo){
            modelId =combo.value;
            Ext.getCmp("checkbox<%=cmsTagContent.getId()%>").destroy();
            Ext.getCmp("secondColumn_update<%=cmsTagContent.getId()%>").destroy();
           Ext.getCmp("field_update_fields<%=cmsTagContent.getId()%>").destroy();


             var field_update_fields<%=cmsTagContent.getId()%> = new Ext.form.FieldSet({
 	            title : '数据读取字段',
 	            autoHeight : true,
 	            id:'field_update_fields<%=cmsTagContent.getId()%>',
	            layout: 'fit',
                     layout:'column',
                     items:[{
                     columnWidth:.5,
                     id:'secondColumn_update<%=cmsTagContent.getId()%>',
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
            
         Ext.getCmp("frm_update_cmstagcontent<%=cmsTagContent.getId()%>").add(field_update_fields<%=cmsTagContent.getId()%>);
         Ext.getCmp("frm_update_cmstagcontent<%=cmsTagContent.getId()%>").doLayout('field_update_fields<%=cmsTagContent.getId()%>');

         parentName.url='tag/CmsCategory/getCmsCategoryComboxByType.do?modelId='+modelId;
         if(parentName.getValue!=""&parentName.hiddenField.value != ""){
             parentName.tree.root.reload();
             parentName.tree.root.expand();
             parentName.tree.root.load();   
             parentName.setValue("");
             parentName.hiddenField.value = "";
             parentName.collapse();
         }
         fun_field_fit();
        }}
});

var field_update_cmstagcontent<%=cmsTagContent.getId()%> = new Ext.form.FieldSet({
    id:'field_update_cmstagcontent<%=cmsTagContent.getId()%>',
	title : '修改内容标签',
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
				         name:'id',
				         value:'<%=cmsTagContent.getId()%>'
			          },{
				         xtype:'hidden',
				         fieldLabel:'模板版本ID',
				         name:'versionId',
				         value:'<%=cmsTagContent.getVersionId()%>'
			          },{
		                    	xtype:'textfield',
		                        fieldLabel: '内容标签名称',
		                        name:'tagNameString',
								invalidText : '标签名称已经被使用!', 
		                        allowBlank : false,
		                        minLength: 2,
								maxLength: 16,
								value:tagName,
		                        validationEvent : 'blur',
		                        regex:/^[\w\u4E00-\u9FFF]+$/,
		                        regexText:'输入中文、数字和英文',
								validator : function(thisText) {
							        var isok =false;
							        if(tagName!=thisText){
	                       	         Ext.Ajax.request({
	                                    url : 'tag/CmsTagContent/tagNameText.do',  
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText,
	                                        versionId : <%=cmsTagContent.getVersionId()%>
	                                    },
	                                     success : function(response,options){
							                  var res = Ext.util.JSON.decode(response.responseText); 
							                  if(res.success == true){  
							                     isok=true;
							                  }
							             }
	                                });
	                                }else{
	                                   isok=true;
	                                }
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
					    name:'tagExp',
					    value:'<%if(null!=cmsTagContent.getTagExp()){out.print(cmsTagContent.getTagExp());}%>'
	                }]
          }]
});  

var field_update_contentmodel<%=cmsTagContent.getId()%> = new Ext.form.FieldSet({
    id:'field_update_contentmodel<%=cmsTagContent.getId()%>',
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

function getCheckboxgroupData<%=cmsTagContent.getId()%>(fieldsCode,modelId){
    var myCheckboxItems = [];
    Ext.Ajax.request({
        url: 'tag/CmsTagContent/getCheckbox.do?fieldsCode='+fieldsCode+'&modelId='+modelId,
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

var myCheckboxGroup<%=cmsTagContent.getId()%> = new Ext.form.CheckboxGroup({  
      columnWidth: 1,  
      xtype : 'checkboxgroup',
      id: 'checkbox<%=cmsTagContent.getId()%>',
      itemCls : 'x-check-group-alt',   
      columns: 3,
      name: 'fieldsCode',
	  fieldLabel: '读取字段',   
      items :  getCheckboxgroupData<%=cmsTagContent.getId()%>(fieldsCode,modelId)
});

var field_update_fields<%=cmsTagContent.getId()%> = new Ext.form.FieldSet({
	 title : '数据读取字段',
	 autoHeight : true,
	 id:'field_update_fields<%=cmsTagContent.getId()%>',
	 layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     id:'secondColumn_update<%=cmsTagContent.getId()%>',
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
	frm_update_cmstagcontent<%=cmsTagContent.getId()%>.form.findField(textfield).setValue(value);
}
fun_setKeyId =function(textfield,value){
    keywordStore.proxy= new Ext.data.HttpProxy({url: 'tag/Content/selectKeywordByGroupId.do?keyid=keyid'});  
	keywordStore.load();
	setTimeout(function() {
	    frm_update_cmstagcontent<%=cmsTagContent.getId()%>.form.findField(textfield).setValue(value);
	}, 300)
}
var field_update_date<%=cmsTagContent.getId()%> = new Ext.form.FieldSet({
    id:'field_update_date<%=cmsTagContent.getId()%>',
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
	                        fieldLabel: '栏目选择<br/>常用变量表示<a href=\'javascript:fun_setText(\"categoryId\",\"$catid\")\'><span style=\'vertical-align: bottom\'>$catid</span></a>',
	                        name:'categoryId',
	                        value:'<%=cmsTagContent.getCategoryId()%>'
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
						             select : function(combo){ 
						             	 keywordIds='';
						             	 frm_update_cmstagcontent<%=cmsTagContent.getId()%>.form.findField('keyId').setValue('') ;
						                 keywordStore.proxy= new Ext.data.HttpProxy({url: 'contentmanager/Content/selectKeywordByGroupId.do?groupId='+combo.value+'&keyid=keyid'});  
						                 keywordStore.load();   
						             }  
					         	}
							},{
				                xtype : 'datefield',
				                fieldLabel : '更新日期',
				                //allowBlank : false,
			            	    name : 'updateTime',
			            	    format: 'Y-m-d H:i:s',
			            	    value:'<%if(null!=cmsTagContent.getUpdateTimeString()){out.print(cmsTagContent.getUpdateTimeString());}%>'
			                },orderTypeCombo]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [parentName,multiSelectComboBox,{
				                xtype : 'datefield',
				                fieldLabel : '发布日期',
				                //allowBlank : false,
			            	    name : 'insertTime',
			            	    format: 'Y-m-d H:i:s',
			            	    value:'<%if(null!=cmsTagContent.getInsertTimeString()){out.print(cmsTagContent.getInsertTimeString());}%>'
			                },continueModeNoCombo]
          }]
});  

var field_update_datefs<%=cmsTagContent.getId()%> = new Ext.form.FieldSet({
    id:'field_update_datefs<%=cmsTagContent.getId()%>',
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
                            {boxLabel: '是', name: 'ifPage', inputValue: 0<%if("0".equals(cmsTagContent.getIfPage())){out.print(",checked: true");}%>},
                            {boxLabel: '否', name: 'ifPage', inputValue: 1<%if("1".equals(cmsTagContent.getIfPage())){out.print(",checked: true");}%>}
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
	                        value:'<%if(null!=cmsTagContent.getRowNums()&&!"null".equals(cmsTagContent.getRowNums())){out.print(cmsTagContent.getRowNums());}%>'
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

var frm_update_cmstagcontent<%=cmsTagContent.getId()%> = new Ext.FormPanel({
        id:'frm_update_cmstagcontent<%=cmsTagContent.getId()%>',
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 120,
        autoScroll : true,
        items: [field_update_cmstagcontent<%=cmsTagContent.getId()%>,field_update_contentmodel<%=cmsTagContent.getId()%>,
        field_update_fields<%=cmsTagContent.getId()%>,field_update_date<%=cmsTagContent.getId()%>,field_update_datefs<%=cmsTagContent.getId()%>],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_cmstagcontent<%=cmsTagContent.getId()%>.form.isValid() == false){
  	    		    return
  	  		    }
  	  		    var keyid =frm_update_cmstagcontent<%=cmsTagContent.getId()%>.form.findField('keyId').getValue();
  	  		    if(keyid.length>259){
  	  		        Ext.Msg.show({title: '提示', msg: '关键字选择数不能超过20个!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  	  		        return;
  	  		    }
  	  		     var ids = [];   
                 var cbitems = Ext.getCmp("checkbox<%=cmsTagContent.getId()%>").items;   
                 
                 for (var i = 0; i < cbitems.length; i++) {   
                     if (cbitems.itemAt(i).checked) {   
                          ids.push(cbitems.itemAt(i).inputValue);   
                     }
                 }
                 
                 Ext.MessageBox.confirm('确认操作','确定修改内容标签页面?',function(btn){
  	            if (btn == 'yes'){
  	  		    frm_update_cmstagcontent<%=cmsTagContent.getId()%>.form.submit({
  	   		    url:'tag/CmsTagContent/update.do',
  	   		    params:{
					fieldsCodeString: ids.toString()
				}
				,method:'POST',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  	   		          <% if("1".equals(cmsTagContent.getVersionId())){%>
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
  	              Ext.MessageBox.confirm('确认操作','确定关闭内容标签页面?',function(btn){
  	                if (btn == 'yes'){
  				       panel<%=cmsTagContent.getId()%>.remove(panel<%=cmsTagContent.getId()%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }]
});

function fun_field_fit() {
	Ext.getCmp("field_update_cmstagcontent<%=cmsTagContent.getId()%>").setWidth(Ext.get("field_update_cmstagcontent<%=cmsTagContent.getId()%>").getWidth() - 22);
    Ext.getCmp("field_update_contentmodel<%=cmsTagContent.getId()%>").setWidth(Ext.get("field_update_cmstagcontent<%=cmsTagContent.getId()%>").getWidth());
	Ext.getCmp("field_update_fields<%=cmsTagContent.getId()%>").setWidth(Ext.get("field_update_cmstagcontent<%=cmsTagContent.getId()%>").getWidth());
	Ext.getCmp("field_update_date<%=cmsTagContent.getId()%>").setWidth(Ext.get("field_update_cmstagcontent<%=cmsTagContent.getId()%>").getWidth());
	Ext.getCmp("field_update_datefs<%=cmsTagContent.getId()%>").setWidth(Ext.get("field_update_cmstagcontent<%=cmsTagContent.getId()%>").getWidth());
};

var panel_update_cmstagcontent<%=cmsTagContent.getId()%> = new Ext.Panel({
  		renderTo:'update_cmstagcontentdiv<%=cmsTagContent.getId()%>',
  		width:Ext.get("update_cmstagcontentdiv<%=cmsTagContent.getId()%>").getWidth(),
  		height:Ext.get("update_cmstagcontentdiv<%=cmsTagContent.getId()%>").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_cmstagcontent<%=cmsTagContent.getId()%>]
});
fun_field_fit();

panel<%=cmsTagContent.getId()%>.on('beforeremove', function(tab, item) {
		if(item.id=='update_CmsTagContent<%=cmsTagContent.getId()%>'){
		   if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_cmstagcontent<%=cmsTagContent.getId()%>.destroy();
		    }
		}
});

panel<%=cmsTagContent.getId()%>.on('resize',function(){
    if(Ext.get("update_cmstagcontentdiv<%=cmsTagContent.getId()%>")){
        var p =panel<%=cmsTagContent.getId()%>.getActiveTab().getId();
        if(p!='update_CmsTagContent<%=cmsTagContent.getId()%>'){
             panel<%=cmsTagContent.getId()%>.setActiveTab('update_CmsTagContent<%=cmsTagContent.getId()%>');
             panel_update_cmstagcontent<%=cmsTagContent.getId()%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_cmstagcontent<%=cmsTagContent.getId()%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             fun_field_fit();
             panel<%=cmsTagContent.getId()%>.setActiveTab(p);
        }else{
            panel_update_cmstagcontent<%=cmsTagContent.getId()%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_cmstagcontent<%=cmsTagContent.getId()%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
            fun_field_fit();
        }
    }
});
setTimeout(function() {
	storeLoadMask<%=cmsTagContent.getId()%>.hide();
}, 300)
},Ext.util.Observable);
new Ext.ux.cmstagcontent.update<%=cmsTagContent.getId()%>();
</script>
</head>
<body>
<div id="update_cmstagcontentdiv<%=cmsTagContent.getId()%>" style="width:100%;height:100%;"></div>
</body>
</html>
