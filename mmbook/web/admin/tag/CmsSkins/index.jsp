<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
 
<%@include file="../inc/param.jsp"%>
<html>
<head>
<title>网站皮肤主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.cmsskins");
Ext.ux.cmsskins.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();


/**cms 模板版本   **/
var cmsVersionStore = new Ext.data.SimpleStore({
			fields: ['value', 'text'],
			proxy: new Ext.data.HttpProxy({
			    url: 'tag/CmsVersion/getCmsVersionComboBox.do'
			})
});

var cmsversionCombo = new Ext.form.ComboBox({
      fieldLabel : '模板版本',  //UI标签名称
      hiddenName :'s_versionId',   //作为form提交时传送的参数名
      allowBlank : true,  //是否允许为空
      mode : "remote",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : cmsVersionStore,
      value:'',  //设置当前选中的值, 也可用作初始化时的默认值, 默认为空
      valueField : 'value',  //传送的值
      displayField : 'text'  //UI列表显示的文本
});

var frm_cmsskins = new Ext.FormPanel({
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
				fieldLabel : '方案名称',
				name : 's_skinName'
			}]
		}, {
			columnWidth : .3,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [cmsversionCombo ]
		}, {
			columnWidth : .3,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				handler: function(){
                ds_cmsskins.baseParams['s_skinName'] = frm_cmsskins.form.findField('s_skinName').getValue();
                ds_cmsskins.baseParams['s_versionId'] = frm_cmsskins.form.findField('s_versionId').getValue();
                pag_cmsskins.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px',
				text : '清 空',
				handler:function(){
				    frm_cmsskins.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {

	var return_moren= "<sc:authorize ifAnyGranted='P_CMSSKINS_INDEX_MOREN'><a class='grid_bn' href='javascript:fun_moren_cmsskins()'>"
			+"设成默认</a></sc:authorize>";

 
	var return_update = "<sc:authorize ifAnyGranted='P_CMSSKINS_INDEX_UPDATE'><a class='grid_bn' href='javascript:fun_opnetab(\""
			+"update_CmsSkins"+"\",\""+ "修改方案"+"\",\""+ "admin/tag/CmsSkins/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"修改</a></sc:authorize>";
			
	var return_detail =	 "<sc:authorize ifAnyGranted='P_CMSSKINS_INDEX_DETAIL'><a class='grid_bn' href='javascript:fun_opnetab(\""
			+"detail_CmsSkins"+"\",\""+ "查看方案"+"\",\""+ "admin/tag/CmsSkins/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"查看</a></sc:authorize>" ;
			
	var return_del= "<sc:authorize ifAnyGranted='P_CMSSKINS_INDEX_DELETE'><a class='grid_bn' href='javascript:fun_del_cmsskins()'>"
			+"删除</a></sc:authorize>";

  if(record.get("isdefault")=='1'){
    return return_update+return_detail ;
    }else{
     return return_moren+return_update+return_detail +return_del;
    }
};

fun_del_cmsskins=function(){// 删除操作
        var record = grid_cmsskins.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'tag/CmsSkins/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_cmsskins.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

fun_moren_cmsskins=function(){// 设成默认
        var record = grid_cmsskins.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认设成默认','确定要设成默认所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'tag/CmsSkins/moren.do?ids='+ record.get('id')+'&v='+record.get('versionId'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_cmsskins.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};


var ds_cmsskins =new Ext.data.Store({
	url:'tag/CmsSkins/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','versionId','skinName','skinDir','cmsVersion.versionName','cmsVersion.id','isdefault','isdfValue','cz']),
	remoteSort:true
});

pag_cmsskins= new Ext.PagingToolbar({
	    store:ds_cmsskins,
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
var checkboxsm = new Ext.grid.CheckboxSelectionModel();
var cm_cmsskins = new Ext.grid.ColumnModel([
    checkboxsm
    ,{header:'模板版本名称',width:100,sortable:true,dataIndex:'cmsVersion.versionName'}
    ,{header:'方案名称',width:100,sortable:true,dataIndex:'skinName'}
    ,{header:'方案目录',width:100,sortable:true,dataIndex:'skinDir'}
    ,{header:'是否默认',width:80,sortable:true,dataIndex:'isdfValue'}
	,{header : '操作',width:130,renderer:fun_rendercz}
]);

cm_cmsskins.defaultSortable = true;

function createToolbar() {
        var tb = []
		var add =new Ext.Button({
        	text : '添加',
        	iconCls : 'icon-adds',
            handler : function() {
              fun_opnetab("1013102","新增方案","admin/tag/CmsSkins/add.jsp","edit");
            }
		});
         
        var ref=new Ext.Button({	
        	text : '刷新',
		    iconCls : 'icon-ref',
		    handler : function() {
		         ds_cmsskins.load();
		    }
        });
        tb.push(add)
        tb.push('-')
        tb.push(ref)
		return tb
}

grid_cmsskins= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_cmsskins,
	sm : checkboxsm,
	cm : cm_cmsskins,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	tbar:createToolbar(),
	bbar : pag_cmsskins
});

ds_cmsskins.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_cmsskinsdiv = new Ext.Panel({
	renderTo : 'cmsskinsdiv',
	width : Ext.get("cmsskinsdiv").getWidth(),
	height : Ext.get("cmsskinsdiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_cmsskins, grid_cmsskins]
});

panel.on('resize',function(){
    if(Ext.get("cmsskinsdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1151203'){
             panel.setActiveTab('1151203');
             panel_cmsskinsdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_cmsskinsdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_cmsskinsdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_cmsskinsdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.cmsskins.index();
</script>
</head>
<body><div id="cmsskinsdiv" style="width:100%;height:100%;"></div></body>
</html>