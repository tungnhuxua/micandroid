 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>基本参数 主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.basicparameters");
Ext.ux.basicparameters.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

// 类型参数:1,系统参数;2,平台参数 下拉框
var ds_rating_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['1','系统参数'],['2','平台参数 ']]
});

var frm_basicparameters = new Ext.FormPanel({
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
				fieldLabel : '参数名称',
				name : 's_parametersName'
			}]
		},{
			columnWidth : .3,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'combo',   // xtype 类型 combo 对应: Ext.form.ComboBox
				fieldLabel : '功能等级',  //标题名称
				emptyText : '请选择...',
				allowBlank : false,     //是否必选 false 心选 , true 可选
				triggerAction : 'all',
				forceSelection : true ,   //如果为true，将限制选择的值必须是下拉列表中的值。 如果为false，允许用户设置任意值到输入栏 (默认值为 false) 
				store : ds_rating_data,   //加载 store
				hiddenName :'s_parametersType',  // 查询条件名称
				valueField : 'id',        // 对应 ds_rating_data id
				displayField : 'val',     //对应 ds_rating_data val
				editable : false,
				mode : 'local'  // local 本地 ,  remote 远程
		     }]
		} ,{
			columnWidth : .3,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				handler: function(){
                ds_basicparameters.baseParams['s_parametersName'] = frm_basicparameters.form.findField('s_parametersName').getValue();
                ds_basicparameters.baseParams['s_parametersType'] = frm_basicparameters.form.findField('s_parametersType').getValue();
                pag_basicparameters.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px',
				text : '清 空',
				handler:function(){
				    frm_basicparameters.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_BasicParameters"+"\",\""+ "修改基本参数"+"\",\""+ "admin/manage/BasicParameters/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_BasicParameters"+"\",\""+ "查看基本参数"+"\",\""+ "admin/manage/BasicParameters/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_basicparameters()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_basicparameters=function(){// 删除操作
        var record = grid_basicparameters.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除基本参数?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'manage/BasicParameters/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_basicparameters.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_basicparameters =new Ext.data.Store({
	url:'manage/BasicParameters/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','parametersName','parametersValue','parametersTag','parametersType','parametersNote','typeValue','cz']),
	remoteSort:true
});

pag_basicparameters= new Ext.PagingToolbar({
	    store:ds_basicparameters,
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

var cm_basicparameters = new Ext.grid.ColumnModel([
    grid_row
    ,{header:'基本参数名称',width:100,sortable:true,dataIndex:'parametersName'}
    ,{header:'基本参数值',width:100,sortable:true,dataIndex:'parametersValue'}
    ,{header:'基本参数标签',width:100,sortable:true,dataIndex:'parametersTag'}
    ,{header:'参数类型',width:60,sortable:true,dataIndex:'typeValue'}
    ,{header:'基本参数说明',width:100,sortable:true,dataIndex:'parametersNote'}
	,{header : '操作',width:190,renderer:fun_rendercz}
]);

cm_basicparameters.defaultSortable = true;

grid_basicparameters= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_basicparameters,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_basicparameters,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	bbar : pag_basicparameters
});

ds_basicparameters.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_basicparametersdiv = new Ext.Panel({
	renderTo : 'basicparametersdiv',
	width : Ext.get("basicparametersdiv").getWidth(),
	height : Ext.get("basicparametersdiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_basicparameters, grid_basicparameters]
});

panel.on('resize',function(){
    if(Ext.get("basicparametersdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1129102'){
             panel.setActiveTab('1129102');
             panel_basicparametersdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_basicparametersdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_basicparametersdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_basicparametersdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.basicparameters.index();
</script>
</head>
<body><div id="basicparametersdiv" style="width:100%;height:100%;"></div></body>
</html>