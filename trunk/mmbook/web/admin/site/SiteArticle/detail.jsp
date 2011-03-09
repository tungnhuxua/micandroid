<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%
String basePath = request.getScheme() + "://" 
+ request.getServerName() + ":" + request.getServerPort()  + "/"; 
%> 

<jsp:include page="../inc/detail_base.jsp" flush="true">
  <jsp:param name="pageTitle" value="newInstance.com"/>
</jsp:include>
<html>
<head>
<title>文章资讯查看页面SiteArticle</title>
<script type="text/javascript">
Ext.ux.sitearticle.detail=Ext.extend(function(){

var httpUrl = '<%=basePath%>';
var record = grid_sitearticle.getSelectionModel().getSelected();

var panel = Ext.getCmp('center-tab-panel');

//内容缩略图部分
//查看图片部分
var field_load_image = new Ext.form.FieldSet({
	title : '查看图片',
	id:'field_upload_addVersionImage',
	autoHeight : true,
	collapsible : true,
	layout: 'fit',
	
		//第一个
		items : [{
			layout : 'form',
			border : false,
			baseCls : 'x-plain',
			items : [{
	               // columnWidth:.3,
	                layout: 'column',
	                defaults : {
						
					},
                	items: [
                	 
                	{
	                    	xtype:'box',
	                        fieldLabel: '小图',
	                        autoEl: {
						        tag: 'img',
						        src: httpUrl+record.get('siteContent.previewsImgUrl'),
						        id:'imgxiao',
						        complete : 'off'
						     }
	                        }
                    ]
	           } 
	   
	]
	}]	
});
//上传结束

var field_detail_sitearticle = new Ext.form.FieldSet({
    id : 'field_detail_sitearticle',
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
		                        ]
	 }]
});



var frm_detail_sitearticle = new Ext.FormPanel({
		autoScroll : true, //滚动条 
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        margins : '8',
        region:'center',
        labelWidth : 80,
        buttonAlign : 'center',
        items: [field_base_sitecontent,field_detail_sitearticle,field_load_image],
        buttons: [{
  	        text:'关闭',
  	        //style : 'margin-left: 8px',
  	        handler:function(){
  				panel.remove(panel.getActiveTab());
  	   		}
  	   }]
});

panel_detail_sitearticle = new Ext.Panel({
  		renderTo:'detail_sitearticlediv',
  		width:Ext.get("detail_sitearticlediv").getWidth(),
  		height:Ext.get("detail_sitearticlediv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_detail_sitearticle]
});
  

if (record) {
      frm_detail_sitearticle.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需查看的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}


panel.on('resize',function(){
    if(Ext.get("detail_sitearticlediv")){
        var p =panel.getActiveTab().getId();
        if(p!='detail_SiteArticle'){
             panel.setActiveTab('detail_SiteArticle');
             panel_detail_sitearticle.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_detail_sitearticle.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_detail_sitearticle.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_detail_sitearticle.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitearticle.detail();
</script>
</head>
<body>
<div id="detail_sitearticlediv" style="width:100%;height:100%;"></div>
</body>
</html>