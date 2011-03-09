<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<html>
<head>
<title>新增页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.sitecontent");
Ext.ux.sitecontent.add=Ext.extend(function(){
var panel = Ext.getCmp('center-tab-panel');
var field_add_sitecontent = new Ext.form.FieldSet({
	title : '新增',
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
		                        fieldLabel: '容内分类ID',
		                        name:'sortId'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '标题',
		                        name:'title'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '内容简介',
		                        name:'synopsis'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '作者',
		                        name:'author'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '提交时间',
		                        name:'submitTime'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '最近修改时间',
		                        name:'updateTime'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '状态',
		                        name:'stateNo'
		                        }
	                        ,{
		                    	xtype:'numberfield',
		                        fieldLabel: '排序值',
		                        name:'sortValue'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '查看级别',
		                        name:'viewClass'
		                        }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '内容URL',
		                        name:'conentUrl'
		                        }
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [{
	                  
	                    	xtype:'textfield',
	                        fieldLabel: '一级分类ID',
	                        name:'oneSortId'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '全标题',
	                        name:'titleFull'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '来源',
	                        name:'sources'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '内容提交人',
	                        name:'submitNam'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '修改人',
	                        name:'updateNam'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '缩略图URL',
	                        name:'previewsImgUrl'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '模型ID',
	                        name:'modelId'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '评论状态',
	                        name:'commentStat'
	                        }
	                        ,{
	                    	xtype:'textfield',
	                        fieldLabel: '显示时间段',
	                        name:'showTime'
	                        }
                    ]
	           }]
});

var frm_add_sitecontent = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        margins : '8',
        region:'center',
        labelWidth : 80,
        items:[field_add_sitecontent],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_sitecontent.form.isValid() == false){
	    		    return;
	  		    }
	  		    frm_add_sitecontent.form.submit({
	   		    url:'site/SiteContent/save.do',
	   		    success:function(form,action){
	   		          Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
			          if (!(typeof(pag_sitecontent) == "undefined")) {
			            pag_sitecontent.doLoad(0);
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

var panel_add_sitecontent = new Ext.Panel({
		renderTo:'add_sitecontentdiv',
		width:Ext.get("add_sitecontentdiv").getWidth(),
		height:Ext.get("add_sitecontentdiv").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_sitecontent]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_add_sitecontent.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("add_SiteContentdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_add_sitecontent.setWidth(panel.getInnerWidth()-2);
             panel_add_sitecontent.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_add_sitecontent.setWidth(panel.getInnerWidth()-2);
            panel_add_sitecontent.setHeight(panel.getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.sitecontent.add();
</script>
</head>
<body>
<div id="add_sitecontentdiv" style="width:100%;height:100%;"></div>
</body>
</html>