 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitecontent");
Ext.ux.sitecontent.index=Ext.extend(function(){
var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

var frm_sitecontent = new Ext.FormPanel({
	labelAlign : 'right',
	labelWidth : 80,
	frame : true,
	title : '查询',
	region : 'north',
	height : 100,
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
				fieldLabel : '条件一',
				name : 's_1'
			},{
				xtype : 'datefield',
				fieldLabel : '条件三',
				name : 's_3'
			}]
		}, {
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '条件二',
				name : 's_2'
			},{
				xtype : 'datefield',
				fieldLabel : '条件四',
				name : 's_4'
			}]
		}, {
			columnWidth : .2,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				style : 'margin-top: 28px',
				handler: function(){
                ds_sitecontent.baseParams['s_1'] = frm_sitecontent.form.findField('s_1').getValue();
                ds_sitecontent.baseParams['s_2'] = frm_sitecontent.form.findField('s_2').getValue();
                ds_sitecontent.baseParams['s_3'] = frm_sitecontent.form.findField('s_3').getValue();
                ds_sitecontent.baseParams['s_4'] = frm_sitecontent.form.findField('s_4').getValue();
                pag_sitecontent.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px;margin-top: 28px',
				text : '清 空',
				handler:function(){
				    frm_sitecontent.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_SiteContent"+"\",\""+ "修改某业务"+"\",\""+ "pages/site/SiteContent/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_SiteContent"+"\",\""+ "查看某业务"+"\",\""+ "pages/site/SiteContent/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_sitecontent()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_sitecontent=function(){// 删除操作
        var record = grid_sitecontent.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'site/SiteContent/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_sitecontent.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_sitecontent =new Ext.data.Store({
	url:'site/SiteContent/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','sortId','oneSortId','title','titleFull','synopsis','sources','author','submitNam','submitTime','updateNam','updateTime','previewsImgUrl','stateNo','modelId','sortValue','commentStat','viewClass','showTime','conentUrl','cz']),
	remoteSort:true
});

pag_sitecontent= new Ext.PagingToolbar({
	    store:ds_sitecontent,
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

var cm_sitecontent = new Ext.grid.ColumnModel([
    grid_row
    ,{header:'容内分类ID',width:100,sortable:true,dataIndex:'sortId'}
    ,{header:'一级分类ID',width:100,sortable:true,dataIndex:'oneSortId'}
    ,{header:'标题',width:100,sortable:true,dataIndex:'title'}
    ,{header:'全标题',width:100,sortable:true,dataIndex:'titleFull'}
    ,{header:'内容简介',width:100,sortable:true,dataIndex:'synopsis'}
    ,{header:'来源',width:100,sortable:true,dataIndex:'sources'}
    ,{header:'作者',width:100,sortable:true,dataIndex:'author'}
    ,{header:'内容提交人',width:100,sortable:true,dataIndex:'submitNam'}
    ,{header:'提交时间',width:100,sortable:true,dataIndex:'submitTime'}
    ,{header:'修改人',width:100,sortable:true,dataIndex:'updateNam'}
    ,{header:'最近修改时间',width:100,sortable:true,dataIndex:'updateTime'}
    ,{header:'缩略图URL',width:100,sortable:true,dataIndex:'previewsImgUrl'}
    ,{header:'状态',width:100,sortable:true,dataIndex:'stateNo'}
    ,{header:'模型ID',width:100,sortable:true,dataIndex:'modelId'}
    ,{header:'排序值',width:100,sortable:true,dataIndex:'sortValue'}
    ,{header:'评论状态',width:100,sortable:true,dataIndex:'commentStat'}
    ,{header:'查看级别',width:100,sortable:true,dataIndex:'viewClass'}
    ,{header:'显示时间段',width:100,sortable:true,dataIndex:'showTime'}
    ,{header:'内容URL',width:100,sortable:true,dataIndex:'conentUrl'}
	,{header : '操作',width:90,sortable:false,renderer:fun_rendercz}
]);

cm_sitecontent.defaultSortable = true;

grid_sitecontent= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_sitecontent,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_sitecontent,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_sitecontent
});

ds_sitecontent.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_sitecontentdiv = new Ext.Panel({
	renderTo : 'sitecontentdiv',
	width : Ext.get("sitecontentdiv").getWidth(),
	height : Ext.get("sitecontentdiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_sitecontent, grid_sitecontent]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_sitecontentdiv.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("sitecontentdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_sitecontentdiv.setWidth(panel.getInnerWidth()-2);
             panel_sitecontentdiv.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_sitecontentdiv.setWidth(panel.getInnerWidth()-2);
            panel_sitecontentdiv.setHeight(panel.getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.sitecontent.index();
</script>
</head>
<body><div id="sitecontentdiv" style="width:100%;height:100%;"></div></body>
</html>