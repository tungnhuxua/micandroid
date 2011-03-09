 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>主页面sitepart</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.sitepart");
Ext.ux.sitepart.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

//是否有效本地下拉框
var ds_effective_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['1','有效'],['0','无效']]
});

var frm_sitepart = new Ext.FormPanel({
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
				fieldLabel : '栏目名称',
				name : 's_partName'
			},{
				xtype : 'datefield',
				fieldLabel : '起始时间',
				name : 's_insertTime1',
				format: 'Y-m-d H:i:s',
				id : 's_insertTime1'
			}]
		}, {
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'combo',   // xtype 类型 combo 对应: Ext.form.ComboBox
				fieldLabel : '是否有效',  //标题名称
				emptyText : '请选择...',
				allowBlank : true,     //是否必选 false 心选 , true 可选
				triggerAction : 'all',
				forceSelection : true ,   //如果为true，将限制选择的值必须是下拉列表中的值。 如果为false，允许用户设置任意值到输入栏 (默认值为 false) 
				store : ds_effective_data,   //加载 store
				hiddenName :'s_effective',  // 查询条件名称
				valueField : 'id',        // 对应 ds_rating_data id
				displayField : 'val',     //对应 ds_rating_data val
				editable : false,
				mode : 'local'  // local 本地 ,  remote 远程
		     },{
				xtype : 'datefield',
				fieldLabel : '结束时间',
				name : 's_insertTime2',
				format: 'Y-m-d H:i:s',
				id : 's_insertTime2'
			}]
		}, {
			columnWidth : .2,
			layout : 'table',
			items : [{
				id : 'sitePartSearch',
				xtype : 'button',
				text : '查 询',
				style : 'margin-top: 28px',
				handler: function(){
	                ds_sitepart.baseParams['s_partName'] = frm_sitepart.form.findField('s_partName').getValue();
	                ds_sitepart.baseParams['s_effective'] = frm_sitepart.form.findField('s_effective').getValue();
	                ds_sitepart.baseParams['s_insertTime1'] = frm_sitepart.form.findField('s_insertTime1').getRawValue();
	                ds_sitepart.baseParams['s_insertTime2'] = frm_sitepart.form.findField('s_insertTime2').getRawValue();
	                pag_sitepart.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px;margin-top: 28px',
				text : '清 空',
				handler:function(){
				    frm_sitepart.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_SitePart"+"\",\""+ "修改信息"+"\",\""+ "admin/manage/SitePart/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_SitePart"+"\",\""+ "查看信息"+"\",\""+ "admin/manage/SitePart/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_sitepart()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_sitepart=function(){// 删除操作
        var record = grid_sitepart.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'manage/SitePart/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_sitepart.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_sitepart =new Ext.data.Store({
	url:'manage/SitePart/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','partName','lowerNode','parentPartName','showType','sortValue','effective','partNotes','insertTime','cz']),
	remoteSort:true
});

pag_sitepart= new Ext.PagingToolbar({
	    store:ds_sitepart,
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

var cm_sitepart = new Ext.grid.ColumnModel([
    grid_row
    ,{header:'栏目名称',width:100,sortable:true,dataIndex:'partName',align:"center"}
    ,{header:'是否有下级节点',width:100,sortable:true,dataIndex:'lowerNode',align:"center",
    	renderer:function(lowerNodeFlag){
    		if(lowerNodeFlag==1){
    			return "是";
    		}
    		return "否";
    	}
    }
    ,{header:'父站栏目',width:100,sortable:true,dataIndex:'parentPartName',align:"center",
    	renderer:function(parentPartNameFlag){
    		if(parentPartNameFlag=="" || null == parentPartNameFlag){
    			return "无";
    		}
    		return parentPartNameFlag;
    	}
    }
    ,{header:'显示方式',width:100,sortable:true,dataIndex:'showType',align:"center"}
    ,{header:'排序值',width:100,sortable:true,dataIndex:'sortValue',align:"center"}
    ,{header:'是否有效',width:100,sortable:true,dataIndex:'effective',align:"center",
    	renderer:function(effectiveFlag){
    		if(effectiveFlag==1){
    			return "有效";
    		}
    		return "无效";
    	}
    }
    ,{header:'栏目说明',width:100,sortable:true,dataIndex:'partNotes',align:"center"}
    ,{header:'新增时间',width:100,sortable:true,dataIndex:'insertTime',align:"center"}
	,{header : '操作',width:150,renderer:fun_rendercz,align:"center"}
]);

cm_sitepart.defaultSortable = true;

var search_sitepart =new Ext.ux.grid.Search({
		searchText : '搜索条件',
		searchTipText : '选择列、输入搜索条件，按回车键搜索',
		selectAllText : '全选',
		mode : 'local',
		position : 'top',
		align : 'right',
		iconCls : 'icon-zoom',
		minChars : 0,
		width : 240,
		dateFormat : 'Y-m-d'
});

grid_sitepart= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_sitepart,
	sm : new Ext.grid.RowSelectionModel( ),
	cm : cm_sitepart,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	plugins : search_sitepart,
	tbar : ['<span style="color:blue;">双击表格可查看详细信息  </span>'],	
	bbar : pag_sitepart
});

function fun_rowdblclickfn(grid, rowIndex, e){// 双击事件
    var row = grid_sitepart.store.getById(grid.store.data.items[rowIndex].id);   
    fun_tab_rolepermission('detail_SitePart','查看信息','admin/manage/SitePart/detail.jsp'); 
    //fun_opnetab('detail_SitePart','查看信息','admin/manage/SitePart/detail.jsp.','detail');
}  


function fun_tab_rolepermission(id,code,url){// 打开TAB
        var tab = panel.findById(id);
        if (tab){
              panel.setActiveTab(id);
            }else{
                 if (panel.items.length > 7) {
							Ext.Msg.alert('温馨提示', '您打开的窗口数己超过7个!');
						}else{
                          var IFramePanel = new Ext.Panel({
								id : id,
								title : code,
								iconCls : 'icon-nav-p1',
								//layout:'fit',
								height : '100%',
								closable : true,
								//border:false,
								autoScroll : true,
								margins : '5 5 5 5'
							});
							panel.add(IFramePanel);
							panel.setActiveTab(IFramePanel);
							IFramePanel.load({
								url : url,
								callback : function(r, options, success) {
									if (success.status == 404) {
										IFramePanel.load({
											url : "LoginAction!errorMenu.action"
										});
									}
									//storeLoadMask.hide();
								},
								scope : this, // optional scope for the callback
								discardUrl : true,
								nocache : true,
								text : "页面加载中,请稍候……",
								scripts : true
							});
							}
            }      
}

grid_sitepart.addListener('rowdblclick', fun_rowdblclickfn);   

ds_sitepart.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_sitepartdiv = new Ext.Panel({
	renderTo : 'sitepartdiv',
	width : Ext.get("sitepartdiv").getWidth(),
	height : Ext.get("sitepartdiv").getHeight()-10,
	border : false,
	layout : 'border',
	items : [frm_sitepart, grid_sitepart]
});

panel.on('resize',function(){
    if(Ext.get("sitepartdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131104'){
             panel.setActiveTab('1131104');
             panel_sitepartdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_sitepartdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_sitepartdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_sitepartdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.sitepart.index();
</script>
</head>
<body><div id="sitepartdiv" style="width:100%;height:100%;"></div></body>
</html>