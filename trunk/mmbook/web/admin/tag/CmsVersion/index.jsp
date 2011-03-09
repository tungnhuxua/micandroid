<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@include file="../inc/param.jsp"%>

<html>
<head>
<title>版本模板-主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.cmsversion");
Ext.ux.cmsversion.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.get("cmsversiondiv<%=u_id%>"), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

var frm_cmsversion = new Ext.FormPanel({
	labelAlign : 'right',
	labelWidth : 80,
	frame : true,
	title : '查询',
	region : 'north',
	height : 70,
	minSize : 100,
	maxSize : 160,
	split : true,
	collapsible : true,
	margins : '8,0,8,8',
	layout: 'fit',
		layout : 'column',
		items : [{
			columnWidth : .3,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '模板版本名称',
				name : 's_versionName'
			}]
		}, {
			columnWidth : .2,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				
				handler: function(){
                ds_cmsversion.baseParams['s_versionName'] = frm_cmsversion.form.findField('s_versionName').getValue();
                pag_cmsversion.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px',
				text : '清 空',
				handler:function(){
				    frm_cmsversion.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<sc:authorize ifAnyGranted='P_CMSVERSION_INDEX_UPDATE'><a class='grid_bn' href='javascript:fun_opnetab(\""
			+"update_CmsVersion"+"\",\""+ "修改模板版本"+"\",\""+ "admin/tag/CmsVersion/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"修改</a></sc:authorize>"
			+"<sc:authorize ifAnyGranted='P_CMSVERSION_INDEX_DETAIL'><a class='grid_bn' href='javascript:fun_opnetab(\""
			+"detail_CmsVersion"+"\",\""+ "查看模板版本"+"\",\""+ "admin/tag/CmsVersion/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"查看</a>"
};


var ds_cmsversion =new Ext.data.Store({
	url:'tag/CmsVersion/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','versionName','versionDir','cz']),
	remoteSort:true
});

pag_cmsversion= new Ext.PagingToolbar({
	    store:ds_cmsversion,
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

var cm_cmsversion = new Ext.grid.ColumnModel([
    grid_row
    
    ,{header:'模板版本名称',width:100,sortable:true,dataIndex:'versionName'}
    ,{header:'模板版本目录',width:100,sortable:true,dataIndex:'versionDir'}
	,{header : '操作',width:90,renderer:fun_rendercz}
]);

cm_cmsversion.defaultSortable = true;

grid_cmsversion= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_cmsversion,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_cmsversion,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_cmsversion
});

ds_cmsversion.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_cmsversiondiv = new Ext.Panel({
	renderTo : 'cmsversiondiv<%=u_id%>',
	width : Ext.get("cmsversiondiv<%=u_id%>").getWidth(),
	height : Ext.get("cmsversiondiv<%=u_id%>").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_cmsversion, grid_cmsversion]
});

panel.on('resize',function(){
    if(Ext.get("cmsversiondiv<%=u_id%>")){
        var p =panel.getActiveTab().getId();
        if(p!='<%=u_id%>'){
             panel.setActiveTab('<%=u_id%>');
             panel_cmsversiondiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_cmsversiondiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_cmsversiondiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_cmsversiondiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.cmsversion.index();
</script>
</head>
<body><div id="cmsversiondiv<%=u_id%>" style="width:100%;height:100%;"></div><br><br></body>
</html>