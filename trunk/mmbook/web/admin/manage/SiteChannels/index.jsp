 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>频道主页</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitechannels");
Ext.ux.sitechannels.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

var frm_sitechannels = new Ext.FormPanel({
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
				fieldLabel : '频道名称',
				name : 'channelsName'
			},{
				xtype : 'datefield',
				fieldLabel : '起始时间',
				name : 'begintime'
			}]
		}, {
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
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
				xtype : 'datefield',
				fieldLabel : '结束时间',
				name : 'endtime'
			}]
		}, {
			columnWidth : .2,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				style : 'margin-top: 28px',
				handler: function(){
                ds_sitechannels.baseParams['channelsName'] = frm_sitechannels.form.findField('channelsName').getValue();
                ds_sitechannels.baseParams['begintime'] = frm_sitechannels.form.findField('begintime').getValue();
                ds_sitechannels.baseParams['effective'] = frm_sitechannels.form.findField('effective').getValue();
                ds_sitechannels.baseParams['endtime'] = frm_sitechannels.form.findField('endtime').getValue();
                pag_sitechannels.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px;margin-top: 28px',
				text : '清 空',
				handler:function(){
				    frm_sitechannels.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_SiteChannels"+"\",\""+ "修改频道"+"\",\""+ "admin/manage/SiteChannels/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_SiteChannels"+"\",\""+ "查看频道"+"\",\""+ "admin/manage/SiteChannels/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_sitechannels()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_sitechannels=function(){// 删除操作
        var record = grid_sitechannels.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除频道','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'manage/SiteChannels/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_sitechannels.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_sitechannels =new Ext.data.Store({
	url:'manage/SiteChannels/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','channelsName','showType','sortValue','effective','channelsNotes','insertTime','siteId','cz']),
	remoteSort:true
});

pag_sitechannels= new Ext.PagingToolbar({
	    store:ds_sitechannels,
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
//列表要是中文
var cm_sitechannels = new Ext.grid.ColumnModel([
    grid_row
    ,{header:'频道名称',width:100,sortable:true,dataIndex:'channelsName'}
    ,{header:'显示方式',width:100,sortable:true,dataIndex:'showType'}
    ,{header:'排序',width:100,sortable:true,dataIndex:'sortValue'}
    ,{header:'是否有效',width:100,sortable:true,dataIndex:'effective'}
    ,{header:'备注',width:100,sortable:true,dataIndex:'channelsNotes'}
    ,{header:'新增时间',width:100,sortable:true,dataIndex:'insertTime'}
	,{header : '操作',width:190,renderer:fun_rendercz}
]);

cm_sitechannels.defaultSortable = true;

grid_sitechannels= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_sitechannels,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_sitechannels,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_sitechannels
});

ds_sitechannels.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_sitechannelsdiv = new Ext.Panel({
	renderTo : 'sitechannelsdiv',
	width : Ext.get("sitechannelsdiv").getWidth(),
	height : Ext.get("sitechannelsdiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_sitechannels, grid_sitechannels]
});

panel.on('resize',function(){
    if(Ext.get("sitechannelsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131102'){
             panel.setActiveTab('1131102');
             panel_sitechannelsdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_sitechannelsdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_sitechannelsdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_sitechannelsdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.sitechannels.index();
</script>
</head>
<body><div id="sitechannelsdiv" style="width:100%;height:100%;"></div></body>
</html>