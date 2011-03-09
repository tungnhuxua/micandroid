<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@include file="../inc/param.jsp"%>
<html>
<head>
<title>主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.cmstemplet");
Ext.ux.cmstemplet.index<%=u_id%>=Ext.extend(function(){

var record_start<%=u_id%> = 0;
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');
var pagep<%=u_id%> = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask<%=u_id%> = new Ext.LoadMask(Ext.get("cmstempletdiv<%=u_id%>"), {//遮掩层
	msg : "正在加载数据，请等待..."
});


var webTempletTypeStore = new Ext.data.SimpleStore({
	fields : ['val','tex'],
	data : [['10','[页面]-首页'],['11','[页面]-栏目首页'],['12','[页面]-列表页'],['13','[页面]-详细页'],['14','[页面]-下载页'],['15','[页面]-预览页'], ['16','[页面]-章节列表页'], ['17','[页面]-搜索页'], ['18','[页面]-其它页'], ['20','[标签]-栏目标签'],['21','[标签]-内容标签'],['22','[标签]-推荐位标签'],['23','[标签]-列表标签']]
});

var webTempletTypeCombo = new Ext.form.ComboBox({
    id:'webtemplettypecombo',
	xtype : 'combo',
	fieldLabel : '模板类型',
	emptyText : '请选择...',
	//allowBlank : false,
	triggerAction : 'all',
	hiddenName :'s_webTempletType',
	store : webTempletTypeStore,
	displayField : 'tex',
	valueField : 'val',
	editable : false,
	mode : 'local'
});

var frm_cmstemplet<%=u_id%> = new Ext.FormPanel({
	labelAlign : 'right',
	labelWidth : 80,
	frame : true,
	title : '查询',
	region : 'north',
	height : 70,
	minSize : 70,
	maxSize : 160,
	split : true,
	collapsible : true,
	margins : '8,0,8,8',
	layout: 'fit',
		layout : 'column',
		items : [{
			columnWidth : .27,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '模板名称',
				name : 's_templetName'
			}]
		}, {
			columnWidth : .27,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '模板文件名',
				name : 's_fileName'
			}]
		},{
			columnWidth : .27,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [webTempletTypeCombo]
		}, {
			columnWidth : .19,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				handler: function(){
                ds_cmstemplet<%=u_id%>.baseParams['s_templetName'] = frm_cmstemplet<%=u_id%>.form.findField('s_templetName').getValue();
                ds_cmstemplet<%=u_id%>.baseParams['s_fileName'] = frm_cmstemplet<%=u_id%>.form.findField('s_fileName').getValue();
                ds_cmstemplet<%=u_id%>.baseParams['s_webTempletType'] = frm_cmstemplet<%=u_id%>.form.findField('s_webTempletType').getValue();
                pag_cmstemplet<%=u_id%>.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px',
				text : '清 空',
				handler:function(){
				    frm_cmstemplet<%=u_id%>.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz<%=u_id%>(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a class='grid_bn' href='javascript:fun_opnetab(\""
			+"update_CmsTemplet"+record.get("id")+"\",\""+ "修改模板"+"\",\""+ "tag/CmsTemplet/updateBaset.do?id="+record.get("id")+"\",\""+ "edit"+"\")'>"
			+"修改</a>"
			+"<a class='grid_bn' href='javascript:fun_del_cmstemplet<%=u_id%>()'>"
			+"删除</a>"
};

function fun_webTempletType<%=u_id%>(value) {
	if(value=="10"){
	    return "首页";
	}else if(value=="11"){
	    return "栏目首页";
	}else if(value=="12"){
	    return "列表页";
	}else if(value=="13"){
	    return "详细页";
	}else if(value=="14"){
	    return "下载页";
	}else if(value=="15"){
	    return "预览页";
	}else if(value=="16"){
	    return "章节列表页";
	}else if(value=="17"){
	    return "搜索页";
	}else if(value=="18"){
	    return "其它页";
	}else if(value=="20"){
	    return "标签模板";
	}else if(value=="21"){
	    return "内容标签";
	}else if(value=="22"){
	    return "推荐位标签";
	}
};

function fun_versionId<%=u_id%>(value) {
	if(value=="1"){
	    return "wap1.x";
	}else{
	    return "wap2.0";
	}
};

function fun_insertTime<%=u_id%>(value, cellmeta, record, rowIndex, columnIndex, store) {
    return record.get("insertTimeString");
};

function fun_updateTime<%=u_id%>(value, cellmeta, record, rowIndex, columnIndex, store) {
	return record.get("updateTimeString");
};

fun_del_cmstemplet<%=u_id%>=function(){// 删除操作
        var record = grid_cmstemplet<%=u_id%>.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'sitemanage/CmsTemplet/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_cmstemplet<%=u_id%>.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_cmstemplet<%=u_id%> =new Ext.data.Store({
	url:'tag/CmsTemplet/list.do?versionId='+<%=v_id%>,
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','templetName','templetType','insertTime','updateTime','insertTimeString','updateTimeString','templetContentString','webTempletType','fileName','versionId','cz']),
	remoteSort:true
});

pag_cmstemplet<%=u_id%>= new Ext.PagingToolbar({
	    store:ds_cmstemplet<%=u_id%>,
	    pageSize:10,
	    plugins: pagep<%=u_id%>,
	    displayInfo:true,
	    displayMsg : '第 {0} 条到 {1} 条,共{2} 条',
	    emptyMsg: "没有数据记录",
	    doLoad : function(start){
            record_start<%=u_id%> = start;
            var o = {}, pn = this.paramNames;
            o[pn.start] = start;
            o[pn.limit] = pagep<%=u_id%>.getValue();
            this.store.load({params:o});
        }
});

var grid_row<%=u_id%> =new Ext.grid.RowNumberer({//显示序号
	        header : "序号",
	        width: 35,
	        css : 'background: #eceff6;',
		    renderer:function(value,metadata,record,rowIndex){
		    	return record_start<%=u_id%> + 1 + rowIndex;
			}
});

var cm_cmstemplet<%=u_id%> = new Ext.grid.ColumnModel([
    grid_row<%=u_id%>
    ,{header:'模板名称',width:120,sortable:true,dataIndex:'templetName'}
    ,{header:'模板类型',width:75,sortable:true,dataIndex:'webTempletType',renderer:fun_webTempletType<%=u_id%>}
    ,{header:'入库时间',width:100,sortable:true,dataIndex:'insertTime',renderer:fun_insertTime<%=u_id%>}
    ,{header:'更新时间',width:100,sortable:true,dataIndex:'updateTime',renderer:fun_updateTime<%=u_id%>}
    //,{header:'模板内容',width:100,sortable:true,dataIndex:'templetContentString'}
    //,{header:'网页模板类型',width:100,sortable:true,dataIndex:'webTempletType'}
    ,{header:'文件名',width:130,sortable:true,dataIndex:'fileName'}
    ,{header:'模板版本',width:75,sortable:true,dataIndex:'versionId',renderer:fun_versionId<%=u_id%>}
	,{header : '操作',width:90,renderer:fun_rendercz<%=u_id%>}
]);

cm_cmstemplet<%=u_id%>.defaultSortable = true;


function createToolbar() {
        var tb = []
		var add =new Ext.Button({
        	text : '添加',
        	iconCls : 'icon-adds',
            handler : function() {
              fun_opnetab("add_CmsTemplet<%=u_id%>","新增模板","admin/tag/CmsTemplet/add.jsp?u=<%=u_id%>&v=<%=v_id%>","edit");
            }
		});
         
        var ref=new Ext.Button({	
        	text : '刷新',
		    iconCls : 'icon-ref',
		    handler : function() {
		         ds_cmstemplet<%=u_id%>.load();
		    }
        });
        tb.push(add)
        tb.push('-') 
        tb.push(ref)
		return tb
}


grid_cmstemplet<%=u_id%>= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_cmstemplet<%=u_id%>,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_cmstemplet<%=u_id%>,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	tbar:createToolbar(),
	bbar : pag_cmstemplet<%=u_id%>
});

ds_cmstemplet<%=u_id%>.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask<%=u_id%>.hide();
		}
	}
});

var panel_cmstempletdiv<%=u_id%> = new Ext.Panel({
	renderTo : 'cmstempletdiv<%=u_id%>',
	width : Ext.get("cmstempletdiv<%=u_id%>").getWidth(),
	height : Ext.get("cmstempletdiv<%=u_id%>").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_cmstemplet<%=u_id%>, grid_cmstemplet<%=u_id%>]
});

panel<%=u_id%>.on('beforeremove', function(tab, item) {
		if(item.id=='<%=u_id%>'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_cmstempletdiv<%=u_id%>.destroy();
		    }
		}
});

panel<%=u_id%>.on('resize',function(){
    if(Ext.get("cmstempletdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('<%=u_id%>');
             panel_cmstempletdiv<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_cmstempletdiv<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_cmstempletdiv<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_cmstempletdiv<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.cmstemplet.index<%=u_id%>();
</script>
</head>
<body><div id="cmstempletdiv<%=u_id%>" style="width:100%;height:100%;"></div></body>
</html>