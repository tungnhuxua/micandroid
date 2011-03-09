<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<jsp:include page="../inc/add_base.jsp" flush="true">
  <jsp:param name="pageTitle" value="newInstance.com"/>
</jsp:include>
<html>
<head>
<title>文章资讯新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitearticle");
Ext.ux.sitearticle.add=Ext.extend(function(){

var panel = Ext.getCmp('center-tab-panel');

var storeLoadMask = new Ext.LoadMask(Ext.get('add_sitearticlediv'), {//遮掩层
	msg : "正在加载数据，请等待..."
});

 
//上传部分
var field_upload_addVersionImage = new Ext.form.FieldSet({
    id : 'field_upload_addVersionImage',
	title : '上传内容缩略图',
	autoHeight : true,
	layout: 'fit',
    layout:'column', 
		//第一个上传
		items : [{
				layout:'column',
				id:'column_small',
				items:[
				{
				columnWidth : .8,
				layout : 'form',
				baseCls : 'x-plain',
				border : false,
				//defaultType : 'textfield',
				defaults : {
					anchor : '88%'
				},
				items : [{
	                xtype: 'fileuploadfield',
	                name : 'upload',
	                id: 'VersionImage',
	                emptyText: '请上传小于5M的JPG/GIF文件', 
	                fieldLabel: '<span style="color:red;">*</span>缩略图',
	                buttonCfg: {
	                    text: '',
	                    iconCls: 'upload-icon'
	                }
	           }]
			}, {
				columnWidth : .1,
				layout : 'table',
				border : false,
				baseCls : 'x-plain',
				//defaultType : 'textfield',
				defaults : {
					anchor : '88%'
				},
				items : [{
				        	xtype:'hidden',
					         fieldLabel:'文件的业务类型',
					         name:'type',
					         value:'image'
			             },{
				        	xtype:'hidden',
					         fieldLabel:'类型格式',
					         name:'filetypename',
					         value:'jpg|gif'  //注意格式
			             },{
				        	xtype:'hidden',
					         fieldLabel:'产品ID',
					         name:'productId',
					         value:'xad'
			             },{
				        	xtype:'hidden',
					         fieldLabel:'文件名',
					         name:'uploadFileName'
			             },{
						xtype : "button",
						//width : 65,
						text : ' 上 传 ',
				 handler: function(){ 
					    imagefilepath = frm_add_sitearticle.form.findField('VersionImage').getValue();
					  //frm_add_sitearticle
						
					  	if (imagefilepath.length>0&&(imagefilepath.substring(imagefilepath.length-4,imagefilepath.length).toUpperCase()==".JPG"
					  		|| imagefilepath.substring(imagefilepath.length-4,imagefilepath.length).toUpperCase()==".GIF")) {
							frm_add_sitearticle.form.submit({
							    //waitTitle:'请稍后',
							    waitMsg:'正在上传,请稍后...',
								isvalid: true,
								url:'site/UploadFile/uploadFilesNew.do',
								success : function(form, action) {
								   frm_add_sitearticle.form.findField('previewsUpload').setValue('isUpload'); 
		                			var filepathname = action.result.filepathname;
		                			var imgurl =action.result.imgurl;
		                			frm_add_sitearticle.form.findField('siteContent.previewsImgUrl').setValue(imgurl);
								    
		                			Ext.Msg.show({title: '提示', msg: '上传成功',icon:Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK,fn:function(){
		                				 
		                			}});
		                			
								},
								//scope:this,
								failure : function(form, action) {
								    Ext.Msg.show({title: '错误', msg: action.result.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
								}
							})
						}else{
							Ext.Msg.show({title:'警告',msg:"请选择类型为(jpg/gif)的文件! ",icon: Ext.Msg.WARNING,minWidth: 200,buttons: Ext.Msg.OK});
						}
				}
			}]
		}
		
         
          ]}  
	]
});


//上传结束

var field_add_sitearticle = new Ext.form.FieldSet({
    id : 'field_add_sitearticle',
	title : '资讯内容',
	autoHeight : true,
	layout: 'fit',
    layout:'column',
    items:[{
                     columnWidth:.9,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '95%'
			         },
			         items: [{
		                    	xtype:'textarea',
		                        fieldLabel: '内容',
		                        width:'400',
		                        height:150,
		                        name:'fileContent'
		                        }
		                        ,comboBoxCheckTree_keyWords
		                        ]
	 }]
});

var frm_add_sitearticle = new Ext.FormPanel({
		autoScroll : true, //滚动条 
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        margins : '8',
        region:'center',
        labelWidth : 80,
        fileUpload: true,
        items:[field_base_sitecontent,field_upload_addVersionImage,field_add_sitearticle],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitearticle.form.isValid() == false){
	    		    return;
	  		    }else{
	  		       //Ext.Msg.show({title: '错误', msg: frm_add_sitearticle.form.findField('treeField').getValue(),icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
	  		       frm_add_sitearticle.form.findField('siteContent.sortId').setValue(frm_add_sitearticle.form.findField('treeField').getValue());
	  		    }
	  		    frm_add_sitearticle.form.submit({
	   		    url:'site/SiteArticle/saveInfo.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitearticle) == "undefined")) {
			            pag_sitearticle.doLoad(0);
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

var panel_add_sitearticle = new Ext.Panel({
		renderTo:'add_sitearticlediv',
		width:Ext.get("add_sitearticlediv").getWidth(),
		height:Ext.get("add_sitearticlediv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitearticle]
});

panel.on('resize',function(){
    if(Ext.get("add_SiteArticlediv")){
        var p =panel.getActiveTab().getId();
        if(p!='add_sitearticlediv'){
             panel.setActiveTab('add_sitearticlediv');
             panel_add_sitearticle.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_sitearticle.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitearticle.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_sitearticle.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitearticle.add();
</script>
</head>
<body>
<div id="add_sitearticlediv" style="width:100%;height:100%;"></div>
</body>
</html>