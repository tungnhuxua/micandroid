<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
 
<%@include file="../inc/param.jsp"%>
<%

String index_u_id = ("1".equals(v_id)) ? "1011106" : "1012106" ;
%>
<html>
<head>
<title>复制类别标签页面</title>
<script type="text/javascript">

Ext.ux.cmstagsort.copy<%=u_id%>=Ext.extend(function(){
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');

/**选中类别ID*/
var categoryId='';
/**模板ID*/
var templetId_temp = "";
var cms_tag_category_name = "";
var record<%=u_id%> = grid_cmstagsort<%=u_id%>.getSelectionModel().getSelected();
if (record<%=u_id%>) {
      cms_tag_category_name = record<%=u_id%>.get("tagName");
      categoryId  = record<%=u_id%>.get("categoryId");
}

var fun_settest=function(textfield,value){
		frm_copy_cmstagsort<%=u_id%>.form.findField(textfield).setValue(value);
}
 
var getId_ = function(id){
     frm_copy_cmstagsort<%=u_id%>.form.findField("categoryId").setValue(id);
};
/**模板ID*/
var templetId_temp = 'test';
/**模板下拉框 qiongguo **/
var cmsTempletStore = new Ext.data.SimpleStore({
			fields: ['id', 'mc'],
			proxy: new Ext.data.HttpProxy({
			    url: 'tag/CmsTemplet/getCmsTempletCombo.do?versionId=1&webTempletType=20'
			})
});

var fun_copy_cms_templet=function(){
		frm_copy_cmstagsort<%=u_id%>.form.findField(textfield).setValue(value);
}

var field_copy_base<%=u_id%> = new Ext.form.FieldSet({
	title : '基础数据',
	autoHeight : true,
	id:'field_copy_base<%=u_id%>',
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
		                        name:'tagNameNew',
								invalidText : '标签名已经被注册!', 
		                        allowBlank : false,
		                        minLength: 2,
								maxLength: 20,
		                        validationEvent : 'blur',
								validator : function(thisText) {
							    var isok =false;
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
	                        		return true;
	                           }}
	                    ]
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
  frm_copy_cmstagsort<%=u_id%>.form.findField('categoryId').setValue(comboBoxCheckTree.getValue()); 
});

var field_copy_dataTransfer<%=u_id%> = new Ext.form.FieldSet({
	title : '类别配置',
	autoHeight : true,
	id:'field_copy_dataTransfer<%=u_id%>',
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
	                        allowBlank : false,
	                        readOnly: true
	                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [comboBoxCheckTree]
	           }]
});

var field_copy_datatemplet<%=u_id%> = new Ext.form.FieldSet({
	title : '模板配置',
	autoHeight : true,
	id:'field_copy_datatemplet<%=u_id%>',
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
		                    	xtype:'combo',
					            fieldLabel: '<font color="red">*</font>模板选择',
			                    blankText:'业务线名不能为空!', 
					            name:'templetName',
					            value : 'templetId',
								triggerAction : 'all',
								editable :false,
								valueField : 'id',
								displayField : 'mc',
								forceSelection : true,
								store : cmsTempletStore,
								loadingText: '正在加载...',
								emptyText : '请选择...',
								mode: 'remote',
								listeners:{"select":function(){//根据业务添加监听事件
								     templetId_temp = this.value;
								     frm_copy_cmstagsort<%=u_id%>.form.findField('templetId').setValue(frm_copy_cmstagsort<%=u_id%>.form.findField('templetName').getValue());
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
	  }]
});


var field_copy_dataDisplay<%=u_id%> = new Ext.form.FieldSet({
	title : '数据显示方式',
	autoHeight : true,
	id:'field_copy_dataDisplay<%=u_id%>',
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
	                    	xtype:'numberfield',
	                        fieldLabel: '行数',
	                        name:'rowNum',
	                        value : '10'
	                        } ,
	                        {
	                    	xtype:'hidden',
	                        fieldLabel: '模板版本ID',
	                        name:'versionId',
	                        value:'<%=v_id%>'
	                        },
				        {
			                xtype:'hidden',
				        	fieldLabel:'模板id',
				        	name:'templetId'
				        },{
				                xtype:'hidden',
					        	fieldLabel:'标签分类',
					        	name:'tagSort',
					        	value:'sort'
				        	}
	                    ]
            	} ]
});

var frm_copy_cmstagsort<%=u_id%> = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '复制',
        margins : '8',
        region:'center',
        labelWidth :120,
        autoScroll : true,
        items: [field_copy_base<%=u_id%>,field_copy_dataDisplay<%=u_id%>,field_copy_dataTransfer<%=u_id%>,field_copy_datatemplet<%=u_id%>],
          buttonAlign : 'center',
          buttons: [{
  	            text:'复制',
  	            handler:function(){
  				if (frm_copy_cmstagsort<%=u_id%>.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    Ext.MessageBox.confirm('确认操作','确定复制类别标签页面?',function(btn){
  	            if (btn == 'yes'){
  	  		    frm_copy_cmstagsort<%=u_id%>.form.submit({
  	   		    url:'tag/CmsTagCategory/save.do',
  	   		    success:function(form,action){
	   		         Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK,fn:function(){
			          if (!(typeof(pag_cmstagsort<%=u_id%>) == "undefined")) {
			            pag_cmstagsort<%=u_id%>.doLoad(0);
			          }
			          //fun_copnetab('<%//=u_id%>','类别标签管理','admin/tag/CmsTagCategory/index.jsp?u=<%=u_id%>&v=<%=v_id%>','icon-nav-p1');
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
	Ext.getCmp("field_copy_base<%=u_id%>").setWidth(Ext.get("field_copy_base<%=u_id%>").getWidth() - 22);
    Ext.getCmp("field_copy_dataTransfer<%=u_id%>").setWidth(Ext.get("field_copy_base<%=u_id%>").getWidth());
	Ext.getCmp("field_copy_datatemplet<%=u_id%>").setWidth(Ext.get("field_copy_base<%=u_id%>").getWidth());
	Ext.getCmp("field_copy_dataDisplay<%=u_id%>").setWidth(Ext.get("field_copy_base<%=u_id%>").getWidth());
};

var panel_copy_cmstagsort<%=u_id%> = new Ext.Panel({
  		renderTo:'copy_cmstagsortdiv<%=u_id%>',
  		width:Ext.get("copy_cmstagsortdiv<%=u_id%>").getWidth(),
  		height:Ext.get("copy_cmstagsortdiv<%=u_id%>").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_copy_cmstagsort<%=u_id%>]
});

fun_field_fit();
if (record<%=u_id%>) {
      frm_copy_cmstagsort<%=u_id%>.form.loadRecord(record<%=u_id%>);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需复制的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel<%=u_id%>.on('resize',function(){
    if(Ext.get("copy_cmstagsortdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='copy_CmsTagCategory<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('copy_CmsTagCategory<%=u_id%>');
             panel_copy_cmstagsort<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_copy_cmstagsort<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             fun_field_fit();
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_copy_cmstagsort<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_copy_cmstagsort<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
            fun_field_fit();
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmstagsort.copy<%=u_id%>();
</script>
</head>
<body>
<div id="copy_cmstagsortdiv<%=u_id%>" style="width:100%;height:100%;"></div>
</body>
</html>