 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitemusic");
Ext.ux.sitemusic.index=Ext.extend(function(){
var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

var frm_sitemusic = new Ext.FormPanel({
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
                ds_sitemusic.baseParams['s_1'] = frm_sitemusic.form.findField('s_1').getValue();
                ds_sitemusic.baseParams['s_2'] = frm_sitemusic.form.findField('s_2').getValue();
                ds_sitemusic.baseParams['s_3'] = frm_sitemusic.form.findField('s_3').getValue();
                ds_sitemusic.baseParams['s_4'] = frm_sitemusic.form.findField('s_4').getValue();
                pag_sitemusic.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px;margin-top: 28px',
				text : '清 空',
				handler:function(){
				    frm_sitemusic.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_SiteMusic"+"\",\""+ "修改某业务"+"\",\""+ "pages/site/SiteMusic/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_SiteMusic"+"\",\""+ "查看某业务"+"\",\""+ "pages/site/SiteMusic/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_sitemusic()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_sitemusic=function(){// 删除操作
        var record = grid_sitemusic.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'site/SiteMusic/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_sitemusic.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_sitemusic =new Ext.data.Store({
	url:'site/SiteMusic/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','conentId','singer','musicSection','musicFormat','musicStyle','cz']),
	remoteSort:true
});

pag_sitemusic= new Ext.PagingToolbar({
	    store:ds_sitemusic,
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

var cm_sitemusic = new Ext.grid.ColumnModel([
    grid_row
    ,{header:'内容ID',width:100,sortable:true,dataIndex:'conentId'}
    ,{header:'歌手名',width:100,sortable:true,dataIndex:'singer'}
    ,{header:'歌手地域',width:100,sortable:true,dataIndex:'musicSection'}
    ,{header:'音乐格式',width:100,sortable:true,dataIndex:'musicFormat'}
    ,{header:'音乐风格',width:100,sortable:true,dataIndex:'musicStyle'}
	,{header : '操作',width:90,sortable:false,renderer:fun_rendercz}
]);

cm_sitemusic.defaultSortable = true;

grid_sitemusic= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_sitemusic,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_sitemusic,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_sitemusic
});

ds_sitemusic.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_sitemusicdiv = new Ext.Panel({
	renderTo : 'sitemusicdiv',
	width : Ext.get("sitemusicdiv").getWidth(),
	height : Ext.get("sitemusicdiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_sitemusic, grid_sitemusic]
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='改自己页面的菜单ID'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_sitemusicdiv.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("sitemusicdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='改自己页面的菜单ID'){
             panel.setActiveTab('改自己页面的菜单ID');
             panel_sitemusicdiv.setWidth(panel.getInnerWidth()-2);
             panel_sitemusicdiv.setHeight(panel.getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_sitemusicdiv.setWidth(panel.getInnerWidth()-2);
            panel_sitemusicdiv.setHeight(panel.getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.sitemusic.index();
</script>
</head>
<body><div id="sitemusicdiv" style="width:100%;height:100%;"></div></body>
</html>