<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
 
<%@include file="../inc/param.jsp"%>
<%
//首页 KEY qiongguo 
String index_u_id = ("1".equals(v_id)) ? "1011106" : "1012106" ;
%>
<html>
<head>
<title>修改类别标签页面</title>
<script type="text/javascript">

Ext.ux.cmstagsort.update<%=u_id%>=Ext.extend(function(){
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');

/**选中类别ID*/
var categoryId='';
/**模板ID*/
var templetId_temp = '';
var cms_tag_category_name = "";
var templet_id_="";
var record<%=u_id%> = grid_cmstagsort<%=u_id%>.getSelectionModel().getSelected();
if (record<%=u_id%>) {
      cms_tag_category_name = record<%=u_id%>.get("tagName");
      templet_id_ = record<%=u_id%>.get("id");
      templetId_temp = record<%=u_id%>.get("templetId");
      categoryId  = record<%=u_id%>.get("categoryId");
}

var fun_settest=function(textfield,value){
		frm_update_cmstagsort<%=u_id%>.form.findField(textfield).setValue(value);
}
 
var getId_ = function(id){
     frm_update_cmstagsort<%=u_id%>.form.findField("categoryId").setValue(id);
};

/**模板下拉框   **/
var cmsTempletStore = new Ext.data.SimpleStore({
			fields: ['id', 'mc'],
			proxy: new Ext.data.HttpProxy({
			    url: 'tag/CmsTemplet/getCmsTempletCombo.do?versionId=<%=v_id%>&webTempletType=20'
			})
});


var fun_update_cms_templet=function(){
		frm_update_cmstagsort<%=u_id%>.form.findField(textfield).setValue(value);
}


var field_update_base<%=u_id%> = new Ext.form.FieldSet({
	title : '基础数据',
	autoHeight : true,
	id:'field_update_base<%=u_id%>',
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
		                        fieldLabel: '标签名称',
					            value : cms_tag_category_name,
					            name:'tagNameNew',
					            value : cms_tag_category_name,
								invalidText : '标签名已经被注册!', 
		                        allowBlank : false,
		                        minLength: 2,
								regex:/^[\w\u4E00-\u9FFF]+$/,
		                        regexText:'输入中文、数字和英文',
								maxLength: 20,
		                        validationEvent : 'blur',
								validator : function(thisText) {
							     var isok =false;
							     if(cms_tag_category_name!=thisText){
	                       	     Ext.Ajax.request({
	                                    url : 'tag/CmsTagCategory/repetitionText.do',   
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
	                                }else{
	                                   isok=true;
	                                }
	                        		return true;
	                           }
		                        }
	                         
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [ {
	                    	xtype:'textfield',
	                        fieldLabel: '标签说明',
	                        maxLength:66,
							maxLengthText:'长度应该小于66个字符',
	                        name:'tagExp'
	                        } 
                    ]
	           }]
});

var comboBoxCheckTree = new Ext.ux.ComboBoxCheckTree({
		fieldLabel: '类别选择',
		width : 300,
		height : 150,
		hiddenName: 'cmstagsortid',
		tree : {
			xtype:'treepanel',
			height:100,
			checkModel: 'multiple',    //多选: 'multiple'(默认)单选: 'single'级联多选: 'cascade'(同时选父和子);'parentCascade'(选父);'childCascade'(选子)
			onlyLeafCheckable: false, 
			animate: true,             //设置为true以启用展开 收缩时的动画效果
			rootVisible: true,
			autoScroll:true,  
			loader: new Ext.tree.TreeLoader({dataUrl:'manage/SiteContentSort/getTreeCombox.do?value='+categoryId,
	        baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
			root : new Ext.tree.AsyncTreeNode({id:'1',text:'类别列表'})
			},
		selectValueModel:'all' //all 多选, folder选父节点  leaf选子节点
				
});

comboBoxCheckTree.on('change', function(box, newvar, oldvar){
  frm_update_cmstagsort<%=u_id%>.form.findField('categoryId').setValue(comboBoxCheckTree.getValue()); 
});

var field_update_dataTransfer<%=u_id%> = new Ext.form.FieldSet({
	title : '类别配置',
	autoHeight : true,
	id:'field_update_dataTransfer<%=u_id%>',
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
	                        fieldLabel: '类别选择<br/>常用变量表示<a href=\'javascript:fun_settest(\"categoryId\",\"$catid\")\'><span style=\'vertical-align: bottom\'>$catid</span></a>',
	                        name:'categoryId',
	                        readOnly: true
	                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [
	                     comboBoxCheckTree
                    ]
	           }]
});

var field_update_datatemplet<%=u_id%> = new Ext.form.FieldSet({
	title : '模板配置',
	autoHeight : true,
	id:'field_update_datatemplet<%=u_id%>',
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [{
						        xtype:'combo',
					            fieldLabel: '<font color="red">*</font>模板选择',
			                    blankText:'业务线名不能为空!', 
					            //hiddenName:'templetId',
					            name:'templetName',
					            value : 'templetId',
								triggerAction : 'all',
								editable :false,
								allowBlank : false,
								valueField : 'id',
								displayField : 'mc',
								forceSelection : true,
								store : cmsTempletStore,
								loadingText: '正在加载...',
								emptyText : '请选择...',
								mode: 'remote',
								listeners:{"select":function(){//根据业务添加监听事件
								     templetId_temp = this.value;
								     frm_update_cmstagsort<%=u_id%>.form.findField('templetId').setValue(frm_update_cmstagsort<%=u_id%>.form.findField('templetName').getValue());
								    }
							 }
						  }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
          buttonAlign : 'center',
          buttons: [{
                id : 'mybuttons',
  	            text: '修改选中的模板',
  	            handler:function(){
  	               fun_opnetab("update_CmsTemplet"+templetId_temp,"修改模板","/admin/tag/CmsTemplet/update.jsp?id="+templetId_temp,"edit");
  	             }
  	     }]
	           }
	 ]
});

var field_update_dataDisplay<%=u_id%> = new Ext.form.FieldSet({
	title : '数据显示方式',
	autoHeight : true,
	id:'field_update_dataDisplay<%=u_id%>',
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [{
	                    	xtype:'numberfield',
	                        fieldLabel: '行数',
	                        name:'rowNum',
	                        value : '10'
	                     },{
	                    	 xtype:'hidden',
	                         fieldLabel: '模板版本ID',
	                         name:'versionId',
	                         value:'<%=v_id%>'
	                        },{
			                xtype:'hidden',
				        	fieldLabel:'主键id',
				        	name:'id'
				        }, {
			                xtype:'hidden',
				        	fieldLabel:'模板id',
				        	name:'templetId'
				        },{
				                xtype:'hidden',
					        	fieldLabel:'标签分类',
					        	name:'tagSort',
					        	value:'sort'
				       }]
            	}]
});

var frm_update_cmstagsort<%=u_id%> = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 120,
        autoScroll : true,
        items: [field_update_base<%=u_id%>,field_update_dataDisplay<%=u_id%>,field_update_dataTransfer<%=u_id%>,field_update_datatemplet<%=u_id%>],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_cmstagsort<%=u_id%>.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    Ext.MessageBox.confirm('确认操作','确定修改类别标签页面?',function(btn){
  	            if (btn == 'yes'){
  	  		    frm_update_cmstagsort<%=u_id%>.form.submit({
  	   		    url:'tag/CmsTagCategory/update.do',
  	   		    success:function(form,action){
	   		         Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK,fn:function(){
			          if (!(typeof(pag_cmstagsort<%=u_id%>) == "undefined")) {
			            pag_cmstagsort<%=u_id%>.doLoad(0);
			          }
			         }});
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
  	              Ext.MessageBox.confirm('确认操作','确定关闭类别标签页面?',function(btn){
  	                if (btn == 'yes'){
  				       panel<%=u_id%>.remove(panel<%=u_id%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }]
 });
function fun_field_fit() {
	Ext.getCmp("field_update_base<%=u_id%>").setWidth(Ext.get("field_update_base<%=u_id%>").getWidth() - 22);
    Ext.getCmp("field_update_dataTransfer<%=u_id%>").setWidth(Ext.get("field_update_base<%=u_id%>").getWidth());
	Ext.getCmp("field_update_datatemplet<%=u_id%>").setWidth(Ext.get("field_update_base<%=u_id%>").getWidth());
	Ext.getCmp("field_update_dataDisplay<%=u_id%>").setWidth(Ext.get("field_update_base<%=u_id%>").getWidth());
};

var panel_update_cmstagsort<%=u_id%> = new Ext.Panel({
  		renderTo:'update_cmstagsortdiv<%=u_id%>',
  		width:Ext.get("update_cmstagsortdiv<%=u_id%>").getWidth(),
  		height:Ext.get("update_cmstagsortdiv<%=u_id%>").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_cmstagsort<%=u_id%>]
});

fun_field_fit();
if (record<%=u_id%>) {
      frm_update_cmstagsort<%=u_id%>.form.loadRecord(record<%=u_id%>);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel<%=u_id%>.on('resize',function(){
    if(Ext.get("update_cmstagsortdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='update_CmsTagSort<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('update_CmsTagSort<%=u_id%>');
             panel_update_cmstagsort<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_cmstagsort<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             fun_field_fit();
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_update_cmstagsort<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_cmstagsort<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
            fun_field_fit();
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmstagsort.update<%=u_id%>();
</script>
</head>
<body>
<div id="update_cmstagsortdiv<%=u_id%>" style="width:100%;height:100%;"></div>
</body>
</html>