 
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>主页面TagModel</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.tagmodel");
Ext.ux.tagmodel.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

var frm_tagmodel = new Ext.FormPanel({
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
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '模型名称',
				name : 's_modelName'
			} ]
		}, {
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '模型说明',
				name : 's_modelNotes'
			} ]
		}, {
			columnWidth : .2,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询', 
				handler: function(){
                ds_tagmodel.baseParams['s_modelName'] = frm_tagmodel.form.findField('s_modelName').getValue();
                ds_tagmodel.baseParams['s_modelNotes'] = frm_tagmodel.form.findField('s_modelNotes').getValue(); 
                pag_tagmodel.doLoad(0);
                }
			}, {
				xtype : 'button', 
				text : '清 空',
				handler:function(){
				    frm_tagmodel.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a href='javascript:fun_opnetab(\""
			+"update_TagModel"+"\",\""+ "修改某业务"+"\",\""+ "admin/tag/TagModel/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/edit.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
			+"<a href='javascript:fun_opnetab(\""
			+"detail_TagModel"+"\",\""+ "查看某业务"+"\",\""+ "admin/tag/TagModel/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/table_edit.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
			+"<a href='javascript:fun_del_tagmodel()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
};

fun_del_tagmodel=function(){// 删除操作
        var record = grid_tagmodel.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'tag/TagModel/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_tagmodel.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_tagmodel =new Ext.data.Store({
	url:'tag/TagModel/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','modelName','modelNotes','aurl','modelUrl','isDef','effective','cz']),
	remoteSort:true
});

pag_tagmodel= new Ext.PagingToolbar({
	    store:ds_tagmodel,
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
 
var cm_tagmodel = new Ext.grid.ColumnModel([
    grid_row
    //,{header:'模型ID',width:60,sortable:true,dataIndex:'id'}
    ,{header:'模型名称',width:80,sortable:true,dataIndex:'modelName'}
    ,{header:'模型说明',width:100,sortable:true,dataIndex:'modelNotes'}
    ,{header:'模型访问URL',width:100,sortable:true,dataIndex:'aurl'}
    ,{header:'模型模板URL',width:100,sortable:true,dataIndex:'modelUrl'}
    ,{header:'是否默认模型',width:60,sortable:true,dataIndex:'isDef'}
    ,{header:'是否有效',width:60,sortable:true,dataIndex:'effective'}
	,{header :'操作',width:130,renderer:fun_rendercz}
]);

cm_tagmodel.defaultSortable = true;

grid_tagmodel= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_tagmodel,
	sm : new Ext.grid.CheckboxSelectionModel( ),
	cm : cm_tagmodel,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true, 
	 
	trackMouseOver : true,
		tbar : [{
	    text : '添加',
        iconCls : 'icon-add',
        handler : function() {fun_opnetab('add_TagModel','新增','admin/tag/TagModel/add.jsp','edit')}
        },'-',{
        text:'删除',
        iconCls:'icon-dela',
        handler : function() {deletetagmodel()}
        },'-',{
        text:'修改',
        iconCls:'icon-editp',
        handler : function() {uptagmodel()}
        },'-',{
        text : '刷新',
		iconCls : 'icon-ref',
		handler : function() {
			ds_tagmodel.load();
		}
	},'->','<span style="color:blue;">双击表格可查看  </span>'],
	bbar : pag_tagmodel
});

ds_tagmodel.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_tagmodeldiv = new Ext.Panel({
	renderTo : 'tagmodeldiv',
	width : Ext.get("tagmodeldiv").getWidth(),
	height : Ext.get("tagmodeldiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_tagmodel, grid_tagmodel]
});

panel.on('resize',function(){
    if(Ext.get("tagmodeldiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1151101'){
             panel.setActiveTab('1151101');
             panel_tagmodeldiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_tagmodeldiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_tagmodeldiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_tagmodeldiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.tagmodel.index();
</script>
</head>
<body><div id="tagmodeldiv" style="width:100%;height:100%;"></div></body>
</html>