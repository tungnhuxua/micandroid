<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>修改频道页面</title>
<script type="text/javascript">

Ext.ux.sitechannels.update=Ext.extend(function(){

var panel = Ext.getCmp('center-tab-panel');

var record = grid_sitechannels.getSelectionModel().getSelected();
var channelsId = record.get("id");
var siteId = record.get("siteId");
var field_update_sitechannels = new Ext.form.FieldSet({
	title : '修改基础数据',
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
				        	name:'id'
			             },{
					                    	xtype:'textfield',
					                        fieldLabel: '频道名称',
					                        name:'channelsName'
					                        }
				                        ,{
					                    		xtype:'numberfield',
					                        fieldLabel: '排序值',
					                        name:'sortValue'
					                        }
				                        ,{
					                    		xtype:'textfield',
					                        fieldLabel: '频道简介',
					                        name:'channelsNotes'
					                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [ {
								xtype : 'combo',
								fieldLabel : '显示方式',
								emptyText : '请选择',
								//allowBlank : false,
								triggerAction : 'all',
								hiddenName :'showType',
                                store : new Ext.data.SimpleStore({ 
                                   //填充的数据
									fields : ['val','tex'],
									data : [['0','默认'],['1','显示方式表']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        }
	                        ,{
								xtype : 'combo',
								fieldLabel : '是否有效',
								emptyText : '请选择',
								//allowBlank : false,
								triggerAction : 'all',
								hiddenName :'effective',
                                store : new Ext.data.SimpleStore({ 
                                   //填充的数据
									fields : ['val','tex'],
									data : [['1','有效'],['0','无效']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        },{
	                    		xtype:'hidden',
	                        fieldLabel: 'siteId',
	                        name:'siteId'
	                        }
                    ]
          }]
});

var sitePartVaule="";
var requestConfig = {
        url: 'manage/SiteChannelsPartReal/getSiteChannelsPartReal.do',
        params : {channelsId : channelsId},
        customer : '自定义属性',
        callback : function(options,success,response){//回调函数
           sitePartVaule = Ext.decode(response.responseText).data;
        }
}
Ext.Ajax.request(requestConfig);//发送请求  
 

//栏目下拉框
 var sitePartGroupStore = new Ext.data.Store({
     proxy: new Ext.data.HttpProxy({
         url: 'manage/SitePart/getLovcombo.do'
     }),
     reader: new Ext.data.JsonReader({
     root: 'list',
     id: 'id'
     }, [
         {name: 'id', mapping: 'id'},
         {name: 'name', mapping: 'partName'}
     ]),
     listeners: {     
        load: function() {     
             multiSelectComboBox.setValue(sitePartVaule);     
        }
    }
 });
sitePartGroupStore.load();
var multiSelectComboBox = new Ext.ux.form.LovCombo({
		 xtype:'lovcombo',
		 fieldLabel : '<span style="color:red;">*</span>栏目列表',
		 hiddenName : 'partIds',
		 allowBlank : false,
		 displayField : 'name',
		 valueField : 'id',
		 editable : false,
		 triggerAction : 'all',
		 allowBlank : true,
		 blankText : '栏目不能为空!',
		 emptyText : '请选择栏目...',
		 store : sitePartGroupStore
});



//远程取数下拉框据例子
var st_site_data = new Ext.data.SimpleStore({
			//数据格式 服务类与本地格式要一样
			fields: ['id', 'val'],
			proxy: new Ext.data.HttpProxy({
			    url: 'site/SiteInfomation/getComboBox.do'
			})
});

var field_update_sitepart = new Ext.form.FieldSet({
    id : 'field_update_sitepart',
	title : '附属信息',
	autoHeight : true,
	layout: 'fit',
    layout:'column',
    items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [ multiSelectComboBox
	                    ]
            	},{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [
			         {
						        xtype:'combo',
					            fieldLabel: '<font color="red">*</font>所属网站',
			                    blankText:'所属网站不能为空!', 
					            //hiddenName:'templetId',
					            name:'siteName',
					            value : siteId,
								triggerAction : 'all',
								editable :false,
								allowBlank : false,
								valueField : 'id',
								displayField : 'val',
								forceSelection : true,
								store : st_site_data,
								loadingText: '正在加载...',
								emptyText : '请选择...',
								mode: 'remote',
								listeners:{"select":function(){//根据业务添加监听事件
								     templetId_temp = this.value;
								     frm_update_sitechannels.form.findField('siteId').setValue(frm_update_sitechannels.form.findField('siteName').getValue());
								    }
							    }
						  }]
            	}]
});


var frm_update_sitechannels = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '修改',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items: [field_update_sitechannels,field_update_sitepart],
          buttonAlign : 'center',
          buttons: [{
  	            text:'修改',
  	            handler:function(){
  				if (frm_update_sitechannels.form.isValid() == false){
  	    		    return;
  	  		    }
  	  		    frm_update_sitechannels.form.submit({
  	   		    url:'manage/SiteChannels/update.do',
  	   		    success:function(form,action){
  	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
  			          pag_sitechannels.doLoad(0);
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


var panel_update_sitechannels = new Ext.Panel({
  		renderTo:'update_sitechannelsdiv',
  		width:Ext.get("update_sitechannelsdiv").getWidth(),
  		height:Ext.get("update_sitechannelsdiv").getHeight(),
  		border : false,
  		layout : 'border',
        items:[frm_update_sitechannels]
});


if (record) {
      frm_update_sitechannels.form.loadRecord(record);
}else{
      Ext.Msg.show({title: '提示', msg: '请选择需修改的记录!',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
}

panel.on('resize',function(){
    if(Ext.get("update_sitechannelsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='update_SiteChannels'){
             panel.setActiveTab('update_SiteChannels');
             panel_update_sitechannels.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_update_sitechannels.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_update_sitechannels.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_update_sitechannels.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitechannels.update();
</script>
</head>
<body>
<div id="update_sitechannelsdiv" style="width:100%;height:100%;"></div>
</body>
</html>