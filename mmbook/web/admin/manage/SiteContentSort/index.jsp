 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>主页面sitecontentsort</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitecontentsort");
Ext.ux.sitecontentsort.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();



var frm_sitecontentsort = new Ext.FormPanel({
	labelAlign : 'right',
	labelWidth : 80,
	frame : true,
	title : '查询',
	region : 'north',
	height : 60,
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
				fieldLabel : '名称',
				name : 's_classifyName'
			}]
		}, {
			columnWidth : .3,
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
								hiddenName :'s_effective',
                                store : new Ext.data.SimpleStore({ 
                                   //填充的数据
									fields : ['val','tex'],
									data : [['1','有效'],['0','无效']]
                                 }),
								displayField : 'tex',
								valueField : 'val',
								editable : false,
								mode : 'local'
	                        }]
		}, {
			columnWidth : .3,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				handler: function(){
                ds_sitecontentsort.baseParams['s_classifyName'] = frm_sitecontentsort.form.findField('s_classifyName').getValue();
                ds_sitecontentsort.baseParams['s_effective'] = frm_sitecontentsort.form.findField('s_effective').getValue();
                pag_sitecontentsort.doLoad(0);
                }
			}, {
				xtype : 'button',
				text : '清 空',
				handler:function(){
				    frm_sitecontentsort.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_SiteContentSort"+"\",\""+ "修改网站内容分类"+"\",\""+ "admin/manage/SiteContentSort/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_SiteContentSort"+"\",\""+ "查看网站内容分类"+"\",\""+ "admin/manage/SiteContentSort/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_sitecontentsort()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_sitecontentsort=function(){// 删除操作
        var record = grid_sitecontentsort.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除网站内容分类','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'manage/SiteContentSort/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_sitecontentsort.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_sitecontentsort =new Ext.data.Store({
	url:'manage/SiteContentSort/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','classifyName','lowerNode','parentId','classifyGrade','sortValue','effective','classifyNature','classifyNotes','insertTime','cz']),
	remoteSort:true
});

pag_sitecontentsort= new Ext.PagingToolbar({
	    store:ds_sitecontentsort,
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

var cm_sitecontentsort = new Ext.grid.ColumnModel([
    grid_row
    ,{header:'名称',width:100,sortable:true,dataIndex:'classifyName'}
    ,{header:'是否有下级节点',width:100,sortable:true,dataIndex:'lowerNode'}
    ,{header:'上级节点ID',width:100,sortable:true,dataIndex:'parentId'}
    ,{header:'等级',width:100,sortable:true,dataIndex:'classifyGrade'}
    ,{header:'排序值',width:100,sortable:true,dataIndex:'sortValue'}
    ,{header:'有效性',width:100,sortable:true,dataIndex:'effective'}
    ,{header:'分类性质',width:100,sortable:true,dataIndex:'classifyNature'}
    ,{header:'备注',width:100,sortable:true,dataIndex:'classifyNotes'}
    ,{header:'新增时间',width:100,sortable:true,dataIndex:'insertTime'}
	,{header : '操作',width:190,renderer:fun_rendercz}
]);

cm_sitecontentsort.defaultSortable = true;

grid_sitecontentsort= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_sitecontentsort,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_sitecontentsort,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_sitecontentsort
});

ds_sitecontentsort.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_sitecontentsortdiv = new Ext.Panel({
	renderTo : 'sitecontentsortdiv',
	width : Ext.get("sitecontentsortdiv").getWidth(),
	height : Ext.get("sitecontentsortdiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_sitecontentsort, grid_sitecontentsort]
});

panel.on('resize',function(){
    if(Ext.get("sitecontentsortdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131107'){
             panel.setActiveTab('1131107');
             panel_sitecontentsortdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_sitecontentsortdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_sitecontentsortdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_sitecontentsortdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.sitecontentsort.index();
</script>
</head>
<body><div id="sitecontentsortdiv" style="width:100%;height:100%;"></div></body>
</html>