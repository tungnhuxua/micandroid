<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@ page import= "cn.mmbook.platform.model.tag.CmsTagList"%> 
<%@include file="../inc/param.jsp"%>
<%
	CmsTagList cmstaglist =(CmsTagList)request.getAttribute("cmsTagList");
System.out.println("updateBaset");
%>
<html>
<head>
<title>修改内容列表标签页面</title> 	
<script type="text/javascript">
Ext.namespace("Ext.ux.cmstaglist");
Ext.ux.cmstaglist.update<%=cmstaglist.getId()%>=Ext.extend(function(){
var storeLoadMask<%=cmstaglist.getId()%> = new Ext.LoadMask(Ext.get("update_cmstaglistdiv<%=cmstaglist.getId()%>"), {//遮掩层
	msg : "正在加载数据，请等待..."
});

var panel<%=cmstaglist.getId()%> = Ext.getCmp('center-tab-panel');
var cmsModelstoreData =<%=cmstaglist.getCmsModelStore()%>;
var radiogroupValue =<%=cmstaglist.getListDestroy()%>;

var tagName ='<%=cmstaglist.getTagName()%>';
var modelId= '<%=cmstaglist.getModelId()%>';
var templetId_temp = '<%=cmstaglist.getTempletId()%>';
 

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
      value:'<%if(null!=cmstaglist.getOrderType()) {out.print(cmstaglist.getOrderType());}%>',
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
      value:'<%if(null!=cmstaglist.getContinueModeNo()){out.print(cmstaglist.getContinueModeNo());}%>',
      valueField : 'value',  //传送的值
      displayField : 'text' 
});

var cmsTempletStore = new Ext.data.SimpleStore({
	fields: ['id', 'mc'],
	proxy: new Ext.data.HttpProxy({
		url: 'tag/CmsTemplet/getCmsTempletCombo.do?versionId=<%=cmstaglist.getVersionId()%>&webTempletType=23'
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
      allowBlank : false,  //是否允许为空
      mode : "remote",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : cmsTempletStore,
      hiddenName: 'templetId',
      valueField : 'id',  //传送的值
      displayField : 'mc',
      listeners:{
	    "select":function(){
		    templetId_temp = this.value;
		}
	}
});

var cmsModelStore = new Ext.data.SimpleStore({
	fields: ['id', 'mc'],
	data : cmsModelstoreData
});
var parentName =new Ext.ux.ComboBoxTree({
	xtype:'combotree',
	fieldLabel: '类别选择<br/>关联内容列表类型操作',
	hiddenName:'parentName',
	allowUnLeafClick:true,
	loadingText: '正在加载...',
	emptyText : '请选择...',
	treeHeight:250,
	triggerAction : 'all',
	loadingText : '加载中...',
	url:'manage/SiteContentSort/getComboBoxTree.do?modelId='+modelId,
	onSelect:function(id){
        frm_update_cmstaglist<%=cmstaglist.getId()%>.form.findField("sortId").setValue(id);
    }
});

parentName.on('focus', function(){
	    if(modelId!=""){
	         parentName.url='manage/SiteContentSort/getComboBoxTree.do?modelId='+modelId;
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
	fieldLabel: '内容列表类型',
	hiddenName: 'modelId',
	value: '<%=cmstaglist.getModelId()%>',
	listeners : {
        select : function(combo){
            modelId =combo.value;
            
        }}
});

var field_update_cmstaglist<%=cmstaglist.getId()%> = new Ext.form.FieldSet({
    id:'field_update_cmstaglist<%=cmstaglist.getId()%>',
	title : '修改内容列表标签',
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
				         value:'<%=cmstaglist.getId()%>'
			          },{
				         xtype:'hidden',
				         fieldLabel:'模板版本ID',
				         name:'versionId',
				         value:'<%=cmstaglist.getVersionId()%>'
			          },{
		                    	xtype:'textfield',
		                        fieldLabel: '内容列表标签名称',
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
	                                    url : 'tag/CmsTagList/tagNameText.do',  
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText,
	                                        versionId : <%=cmstaglist.getVersionId()%>
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
	                        		return true;
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
					    value:'<%if(null!=cmstaglist.getTagExp()){out.print(cmstaglist.getTagExp());}%>'
	                }]
          }]
});  

var field_update_contentmodel<%=cmstaglist.getId()%> = new Ext.form.FieldSet({
    id:'field_update_contentmodel<%=cmstaglist.getId()%>',
	title : '选择内容列表模型',
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
 
 
var field_update_fields<%=cmstaglist.getId()%> = new Ext.form.FieldSet({
	 title : '列表显示信息',
	 autoHeight : true,
	 id:'field_update_fields<%=cmstaglist.getId()%>',
	 layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     id:'secondColumn_update<%=cmstaglist.getId()%>',
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         }, 
			         items: [{
                                    xtype : 'radiogroup',
                                    fieldLabel : '标题样式',   
                                    columns: 3,//设置显示的列数 
                                    items :[{   
                                        inputValue : '1',   
                                        boxLabel : '标题',
                                        name : 'listDestroy'
                                    },{   
                                        inputValue : '2',   
                                        boxLabel : '序列+标题',   
                                      
                                        name : 'listDestroy'  
                                    },{   
                                        inputValue : '3',   
                                        boxLabel : '标题+缩略图',   
                                        name : 'listDestroy'  
                                    },{   
                                        inputValue : '4',   
                                        boxLabel : '序列+标题+缩略图',   
                                        name : 'listDestroy'  
                                    },{   
                                        inputValue : '5',   
                                        boxLabel : '全标题',   
                                        name : 'listDestroy'  
                                    },{   
                                        inputValue : '6',   
                                        boxLabel : '序列+全标题',   
                                        name : 'listDestroy'  
                                    }]   
                                }]  
					}] 
});
 
var field_update_date<%=cmstaglist.getId()%> = new Ext.form.FieldSet({
    id:'field_update_date<%=cmstaglist.getId()%>',
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
	                        fieldLabel: '类别选择<br/>常用变量表示<a href=\'javascript:fun_setText(\"categoryId\",\"$catid\")\'><span style=\'vertical-align: bottom\'>$catid</span></a>',
	                        name:'sortId',
	                        value:'<%=cmstaglist.getSortId()%>'
	                        },  {
				                xtype : 'datefield',
				                fieldLabel : '更新日期',
				                //allowBlank : false,
			            	    name : 'updateTime',
			            	    format: 'Y-m-d H:i:s',
			            	    value:'<%if(null!=cmstaglist.getUpdateTimeString()){out.print(cmstaglist.getUpdateTimeString());}%>'
			                },orderTypeCombo]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [parentName,{
				                xtype : 'datefield',
				                fieldLabel : '发布日期',
				                //allowBlank : false,
			            	    name : 'insertTime',
			            	    format: 'Y-m-d H:i:s',
			            	    value:'<%if(null!=cmstaglist.getInsertTimeString()){out.print(cmstaglist.getInsertTimeString());}%>'
			                },continueModeNoCombo]
          }]
});  

var field_update_datefs<%=cmstaglist.getId()%> = new Ext.form.FieldSet({
    id:'field_update_datefs<%=cmstaglist.getId()%>',
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
                            {boxLabel: '是', name: 'ifPage', inputValue: 0<%if("0".equals(cmstaglist.getIfPage())){out.print(",checked: true");}%>},
                            {boxLabel: '否', name: 'ifPage', inputValue: 1<%if("1".equals(cmstaglist.getIfPage())){out.print(",checked: true");}%>}
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
	                        value:'<%if(null!=cmstaglist.getRowNums()&&!"null".equals(cmstaglist.getRowNums())){out.print(cmstaglist.getRowNums());}%>'
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

var frm_update_cmstaglist<%=cmstaglist.getId()%> = new Ext.FormPanel({
        id:'frm_update_cmstaglist<%=cmstaglist.getId()%>',
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 120,
        autoScroll : true,
        items: [field_update_cmstaglist<%=cmstaglist.getId()%>,field_update_contentmodel<%=cmstaglist.getId()%>,
        field_update_fields<%=cmstaglist.getId()%>,field_update_date<%=cmstaglist.getId()%>,field_update_datefs<%=cmstaglist.getId()%>],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_cmstaglist<%=cmstaglist.getId()%>.form.isValid() == false){
  	    		    return
  	  		    }
  	  		    
                Ext.MessageBox.confirm('确认操作','确定修改内容列表标签页面?',function(btn){
  	            if (btn == 'yes'){
  	  		    frm_update_cmstaglist<%=cmstaglist.getId()%>.form.submit({
  	   		    url:'tag/CmsTagList/update.do',
  	   		    params:{
					fieldsCodeString:''
				}
				,method:'POST',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  	   		          if (!(typeof(pag_cmstaglist<%=u_id%>) == "undefined")) {
			                  pag_cmstaglist<%=u_id%>.doLoad(0);
			          }
  	   		         
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
  	              Ext.MessageBox.confirm('确认操作','确定关闭内容列表标签页面?',function(btn){
  	                if (btn == 'yes'){
  				       panel<%=cmstaglist.getId()%>.remove(panel<%=cmstaglist.getId()%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }]
});

function fun_field_fit() {
	Ext.getCmp("field_update_cmstaglist<%=cmstaglist.getId()%>").setWidth(Ext.get("field_update_cmstaglist<%=cmstaglist.getId()%>").getWidth() - 22);
    Ext.getCmp("field_update_contentmodel<%=cmstaglist.getId()%>").setWidth(Ext.get("field_update_cmstaglist<%=cmstaglist.getId()%>").getWidth());
	Ext.getCmp("field_update_fields<%=cmstaglist.getId()%>").setWidth(Ext.get("field_update_cmstaglist<%=cmstaglist.getId()%>").getWidth());
	Ext.getCmp("field_update_date<%=cmstaglist.getId()%>").setWidth(Ext.get("field_update_cmstaglist<%=cmstaglist.getId()%>").getWidth());
	Ext.getCmp("field_update_datefs<%=cmstaglist.getId()%>").setWidth(Ext.get("field_update_cmstaglist<%=cmstaglist.getId()%>").getWidth());
};

var panel_update_cmstaglist<%=cmstaglist.getId()%> = new Ext.Panel({
  		renderTo:'update_cmstaglistdiv<%=cmstaglist.getId()%>',
  		width:Ext.get("update_cmstaglistdiv<%=cmstaglist.getId()%>").getWidth(),
  		height:Ext.get("update_cmstaglistdiv<%=cmstaglist.getId()%>").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_cmstaglist<%=cmstaglist.getId()%>]
});
fun_field_fit();

panel<%=cmstaglist.getId()%>.on('beforeremove', function(tab, item) {
		if(item.id=='update_cmstaglist<%=cmstaglist.getId()%>'){
		   if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_update_cmstaglist<%=cmstaglist.getId()%>.destroy();
		    }
		}
});

panel<%=cmstaglist.getId()%>.on('resize',function(){
    if(Ext.get("update_cmstaglistdiv<%=cmstaglist.getId()%>")){
        var p =panel<%=cmstaglist.getId()%>.getActiveTab().getId();
        if(p!='update_cmstaglist<%=cmstaglist.getId()%>'){
             panel<%=cmstaglist.getId()%>.setActiveTab('update_cmstaglist<%=cmstaglist.getId()%>');
             panel_update_cmstaglist<%=cmstaglist.getId()%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_cmstaglist<%=cmstaglist.getId()%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             fun_field_fit();
             panel<%=cmstaglist.getId()%>.setActiveTab(p);
        }else{
            panel_update_cmstaglist<%=cmstaglist.getId()%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_cmstaglist<%=cmstaglist.getId()%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
            fun_field_fit();
        }
    }
});
setTimeout(function() {
	storeLoadMask<%=cmstaglist.getId()%>.hide();
}, 300)
},Ext.util.Observable);
new Ext.ux.cmstaglist.update<%=cmstaglist.getId()%>();
</script>
</head>
<body>
<div id="update_cmstaglistdiv<%=cmstaglist.getId()%>" style="width:100%;height:100%;"></div>
</body>
</html>
