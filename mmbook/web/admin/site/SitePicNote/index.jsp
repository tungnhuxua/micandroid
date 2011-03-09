 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitepicnote");
Ext.ux.sitepicnote.index=Ext.extend(function(){
var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

var frm_sitepicnote = new Ext.FormPanel({
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
                ds_sitepicnote.baseParams['s_1'] = frm_sitepicnote.form.findField('s_1').getValue();
                ds_sitepicnote.baseParams['s_2'] = frm_sitepicnote.form.findField('s_2').getValue();
                ds_sitepicnote.baseParams['s_3'] = frm_sitepicnote.form.findField('s_3').getValue();
                ds_sitepicnote.baseParams['s_4'] = frm_sitepicnote.form.findField('s_4').getValue();
                pag_sitepicnote.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px;margin-top: 28px',
				text : '清 空',
				handler:function(){
				    frm_sitepicnote.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_SitePicNote"+"\",\""+ "修改某业务"+"\",\""+ "pages/site/SitePicNote/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_SitePicNote"+"\",\""+ "查看某业务"+"\",\""+ "pages/site/SitePicNote/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_sitepicnote()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_sitepicnote=function(){// 删除操作
        var record = grid_sitepicnote.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'site/SitePicNote/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_sitepicnote.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_sitepicnote =new Ext.data.Store({
	url:'site/SitePicNote/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','contentId','picTitle','picFormat','picTypes','cz']),
	remoteSort:true
});

pag_sitepicnote= new Ext.PagingToolbar({
	    store:ds_sitepicnote,
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

var cm_sitepicnote = new Ext.grid.ColumnModel([
    grid_row
    ,{header:'内容ID',width:100,sortable:true,dataIndex:'contentId'}
    ,{header:'图片小标题',width:100,sortable:true,dataIndex:'picTitle'}
    ,{header:'图片格式',width:100,sortable:true,dataIndex:'picFormat'}
    ,{header:'图片类型',width:100,sortable:true,dataIndex:'picTypes'}
	,{header : '操作',width:90,sortable:false,renderer:fun_rendercz}
]);

cm_sitepicnote.defaultSortable = true;

grid_sitepicnote= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_sitepicnote,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_sitepicnote,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_sitepicnote
});

ds_sitepicnote.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_sitepicnotediv = new Ext.Panel({
	renderTo : 'sitepicnotediv',
	width : Ext.get("sitepicnotediv").getWidth(),
	height : Ext.get("sitepicnotediv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_sitepicnote, grid_sitepicnote]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_sitepicnotediv.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("sitepicnotediv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_sitepicnotediv.setWidth(panel.getInnerWidth()-2);
             panel_sitepicnotediv.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_sitepicnotediv.setWidth(panel.getInnerWidth()-2);
            panel_sitepicnotediv.setHeight(panel.getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.sitepicnote.index();
</script>
</head>
<body><div id="sitepicnotediv" style="width:100%;height:100%;"></div></body>
</html>