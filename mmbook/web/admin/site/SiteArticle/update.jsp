<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%
/**获取当前页面URL*/
String basePath = request.getScheme() + "://" 
+ request.getServerName() + ":" + request.getServerPort()  + "/"; 
%>

<jsp:include page="../inc/update_base.jsp" flush="true">
  <jsp:param name="pageTitle" value="newInstance.com"/>
</jsp:include>
<html>
<head>
<title>文章资讯修改页面SiteArticle</title>
<script type="text/javascript">

Ext.ux.sitearticle.update=Ext.extend(function(){

var panel = Ext.getCmp('center-tab-panel');
var httpUrl = '<%=basePath%>';
var record = grid_sitearticle.getSelectionModel().getSelected();

var storeLoadMask = new Ext.LoadMask(Ext.get('update_sitearticlediv'), {//遮掩层
	msg : "正在加载数据，请等待..."
});
 
//上传部分
var field_upload_updateVersionImage = new Ext.form.FieldSet({
    id : 'field_upload_updateVersionImage',
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
				columnWidth : .7,
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
				columnWidth : .4,
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
					    imagefilepath = frm_update_sitearticle.form.findField('VersionImage').getValue();
					  //frm_update_sitearticle
						
					  	if (imagefilepath.length>0&&(imagefilepath.substring(imagefilepath.length-4,imagefilepath.length).toUpperCase()==".JPG"
					  		|| imagefilepath.substring(imagefilepath.length-4,imagefilepath.length).toUpperCase()==".GIF")) {
							frm_update_sitearticle.form.submit({
							    //waitTitle:'请稍后',
							    waitMsg:'正在上传,请稍后...',
								isvalid: true,
								url:'site/UploadFile/uploadFilesNew.do',
								success : function(form, action) {
								   frm_update_sitearticle.form.findField('previewsUpload').setValue('isUpload'); 
		                			var filepathname = action.result.filepathname;
		                			var imgurl =action.result.imgurl;
		                			frm_update_sitearticle.form.findField('siteContent.previewsImgUrl').setValue(imgurl);
								    
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
          ,
          {
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '100%'
			         },
			         items: [{
	                    	xtype:'box',
	                    	//width: 100, //图片宽度      
    						//height: 100, //图片高度
	                        fieldLabel: '小图',
	                        autoEl: {
						        tag: 'img',
						        src: httpUrl+record.get('siteContent.previewsImgUrl'),
						        id:'imgxiao',
						        complete : 'off'
						     }
	                        }]
	 			}
	]
});


//上传结束
 
var field_update_sitearticle = new Ext.form.FieldSet({
    id : 'field_update_sitearticle',
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
		                        name:'baseAccessories.content'
		                        }
		                        ,comboBoxCheckTree_keyWords
		                      
		                      
		                        ,{
					        	 xtype:'hidden',
						         fieldLabel:'ID',
						         name:'id'
				             	},{
					        	 xtype:'hidden',
						         fieldLabel:'附件ID',
						         name:'baseAccessories.id'
				                }
		                        
		                        ]
	 }]
});

var frm_update_sitearticle = new Ext.FormPanel({
		autoScroll : true, //滚动条 
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        fileUpload: true,  //支持上传组件
        items: [field_base_sitecontent,field_update_sitearticle ,field_upload_updateVersionImage],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitearticle.form.isValid() == false){
  	    		    return;
  	  		    }else{
	  		       frm_update_sitearticle.form.findField('siteContent.sortId').setValue(frm_update_sitearticle.form.findField('treeField').getValue());
	  		    }
  	  		    frm_update_sitearticle.form.submit({
  	   		    url:'site/SiteArticle/updateInfo.do',
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

var panel_update_sitearticle = new Ext.Panel({
  		renderTo:'update_sitearticlediv',
  		width:Ext.get("update_sitearticlediv").getWidth(),
  		height:Ext.get("update_sitearticlediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitearticle]
});


if (record) {
      frm_update_sitearticle.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_sitearticlediv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteArticle'){
             panel.setActiveTab('update_SiteArticle');
             panel_update_sitearticle.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_sitearticle.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitearticle.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_sitearticle.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitearticle.update();
</script>
</head>
<body>
<div id="update_sitearticlediv" style="width:100%;height:100%;"></div>
</body>
</html>