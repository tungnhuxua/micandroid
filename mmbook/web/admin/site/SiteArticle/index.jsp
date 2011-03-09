 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>文章资讯主页面SiteArticle</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitearticle");
Ext.ux.sitearticle.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

var frm_sitearticle = new Ext.FormPanel({
	labelAlign : 'right',
	labelWidth : 80,
	frame : true,
	title : '查询',
	region : 'north',
	height : 140,
	minSize : 100,
	maxSize : 160,
	split : true,
	collapsible : true,
	margins : '8,0,8,8',
	layout: 'fit',
		layout : 'column',
		items : [{
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '内容标题',
				name : 's_siteContent.title'
			},{
				xtype : 'textfield',
				fieldLabel : '模型ID',
				name : 's_siteContent.modelId'
			},{
				xtype : 'datefield',
				fieldLabel : '起始时间',
				name : 's_beginTime'
			}]
		}, {
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '状态',
				name : 's_siteContent.stateNo'
			},{
				xtype : 'textfield',
				fieldLabel : '分类ID',
				name : 's_siteContent.sortId'
			},{
				xtype : 'datefield',
				fieldLabel : '结束时间',
				name : 's_endTime'
			}]
		}, {
			columnWidth : .2,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				style : 'margin-top: 28px',
				handler: function(){
                ds_sitearticle.baseParams['s_siteContent.title'] = frm_sitearticle.form.findField('s_siteContent.title').getValue();
                ds_sitearticle.baseParams['s_siteContent.modelId'] = frm_sitearticle.form.findField('s_siteContent.modelId').getValue();
                ds_sitearticle.baseParams['s_beginTime'] = frm_sitearticle.form.findField('s_beginTime').getValue();
                ds_sitearticle.baseParams['s_siteContent.stateNo'] = frm_sitearticle.form.findField('s_siteContent.stateNo').getValue();
                ds_sitearticle.baseParams['s_siteContent.sortId'] = frm_sitearticle.form.findField('s_siteContent.sortId').getValue();
                ds_sitearticle.baseParams['s_endTime'] = frm_sitearticle.form.findField('s_endTime').getValue();
                pag_sitearticle.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px;margin-top: 28px',
				text : '清 空',
				handler:function(){
				    frm_sitearticle.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_SiteArticle"+"\",\""+ "修改资讯内容"+"\",\""+ "admin/site/SiteArticle/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_SiteArticle"+"\",\""+ "查看资讯内容"+"\",\""+ "admin/site/SiteArticle/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_sitearticle()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_sitearticle=function(){// 删除操作
        var record = grid_sitearticle.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选资讯内容?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'site/SiteArticle/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_sitearticle.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_sitearticle =new Ext.data.Store({
	url:'site/SiteArticle/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','contentId',
	  'siteContent.id','siteContent.sortId','siteContent.oneSortId','siteContent.title','siteContent.titleFull','siteContent.synopsis','siteContent.sources','siteContent.author','siteContent.submitNam','siteContent.submitTime','siteContent.updateNam','siteContent.updateTime','siteContent.previewsImgUrl','siteContent.stateNo','siteContent.modelId','siteContent.sortValue','siteContent.commentStat','siteContent.viewClass','siteContent.showTime','siteContent.conentUrl',
	  ,'baseAccessories.id','baseAccessories.accessoriesTypeId','baseAccessories.content','baseAccessories.subordinationId','baseAccessories.contentUrl','baseAccessories.format','baseAccessories.fileSize','baseAccessories.notes','baseAccessories.fileSurfix',
	  'cz']),
	remoteSort:true
});

pag_sitearticle= new Ext.PagingToolbar({
	    store:ds_sitearticle,
	    pageSize:10,
	    plugins: pagep,
	    displayInfo:true,
	    displayMsg : '第 {0} 条到 {1} 条,共{2} 条',
	    emptyMsg: "没有数据记录",
	    doLoad : function(start){
            record_start = start;
            var o = {}, pn = this.paramNames;
            o[pn.start] = start;
            o[pn.limit] = pagep.getValue();
            this.store.load({params:o});
        }
});

var grid_row =new Ext.grid.RowNumberer({//显示序号
	        header : "序号",
	        width: 35,
	        css : 'background: #eceff6;',
		    renderer:function(value,metadata,record,rowIndex){
		    	return record_start + 1 + rowIndex;
			}
});

var cm_sitearticle = new Ext.grid.ColumnModel([
    grid_row
    
    ,{header:'内容ID',width:100,sortable:true,dataIndex:'contentId'}
    ,{header:'分类ID',width:50,sortable:true,dataIndex:'siteContent.sortId'}
    ,{header:'标题',width:160,sortable:true,dataIndex:'siteContent.title'}
    //,{header:'提交时间',width:100,sortable:true,dataIndex:'siteContent.submitTime'}
    ,{header:'状态',width:100,sortable:true,dataIndex:'siteContent.stateNo'}
    ,{header:'模型ID',width:100,sortable:true,dataIndex:'siteContent.modelId'}
    ,{header:'排序值',width:100,sortable:true,dataIndex:'siteContent.sortValue'}
    ,{header:'评论状态',width:100,sortable:true,dataIndex:'siteContent.commentStat'}
    ,{header:'查看级别',width:100,sortable:true,dataIndex:'siteContent.viewClass'}
    ,{header:'显示时间段',width:100,sortable:true,dataIndex:'siteContent.showTime'}
    ,{header : '操作',width:190,renderer:fun_rendercz}
    
]);

cm_sitearticle.defaultSortable = true;

grid_sitearticle= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_sitearticle,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_sitearticle,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_sitearticle
});

ds_sitearticle.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_sitearticlediv = new Ext.Panel({
	renderTo : 'sitearticlediv',
	width : Ext.get("sitearticlediv").getWidth(),
	height : Ext.get("sitearticlediv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_sitearticle, grid_sitearticle]
});

panel.on('resize',function(){
    if(Ext.get("sitearticlediv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131208'){
             panel.setActiveTab('1131208');
             panel_sitearticlediv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_sitearticlediv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_sitearticlediv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_sitearticlediv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.sitearticle.index();
</script>
</head>
<body><div id="sitearticlediv" style="width:100%;height:100%;"></div></body>
</html>