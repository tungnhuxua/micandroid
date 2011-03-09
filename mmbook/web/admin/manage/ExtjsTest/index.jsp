<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<html>
<head>
<title>功能测试主页面extjstest</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.extjstest");
Ext.ux.extjstest.index=Ext.extend(function(){

var record_start = 0;
var panel = Ext.getCmp('center-tab-panel');
var pagep = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask = new Ext.LoadMask(Ext.getBody(), {
	msg : "正在加载数据，请等待..."
});
storeLoadMask.show();

//功能等级本地下拉框
var ds_rating_data = new Ext.data.SimpleStore({
	fields : ['id','val'],
	data : [['1','一级'],['2','二级'],['3','三级'],['4','四级']]
});

//远程取数下拉框据例子
var st_types_data = new Ext.data.SimpleStore({
			//数据格式 服务类与本地格式要一样
			fields: ['value', 'text','note'],
			proxy: new Ext.data.HttpProxy({
			    //请求cn.mmbook.platform.action.manage.ExtjsTestAction.getTypesCombox 方法 
			    url: 'manage/ExtjsTest/getTypesCombox.do'
			})
});

var remoteComboBox= new Ext.form.ComboBox({
      fieldLabel : '功能类型',  //UI标签名称
      name : 's_types',   //作为form提交时传送的参数名
      allowBlank : false,  //是否允许为空
      mode : "remote",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : st_types_data,
      value:'0',  //设置当前选中的值, 也可用作初始化时的默认值, 默认为空
      valueField : 'value',  //传送的值
      displayField : 'text'  //UI列表显示的文本
}); 

var frm_extjstest = new Ext.FormPanel({
	labelAlign : 'right',
	labelWidth : 60,
	frame : true,
	title : '查询条件',
	region : 'north',
	height : 130,
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
				fieldLabel : '能功名称',
				name : 's_functionName'
			},{
				xtype : 'datefield',
				fieldLabel : '新增时间',
				name : 's_insertTime',
				format: 'Y-m-d',
				id : 's_insertTime'
			}]
		}, {
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [
			 remoteComboBox
			,{
				xtype : 'combo',   // xtype 类型 combo 对应: Ext.form.ComboBox
				fieldLabel : '功能等级',  //标题名称
				emptyText : '请选择...',
				allowBlank : false,     //是否必选 false 心选 , true 可选
				triggerAction : 'all',
				forceSelection : true ,   //如果为true，将限制选择的值必须是下拉列表中的值。 如果为false，允许用户设置任意值到输入栏 (默认值为 false) 
				store : ds_rating_data,   //加载 store
				hiddenName :'s_rating',  // 查询条件名称
				valueField : 'id',        // 对应 ds_rating_data id
				displayField : 'val',     //对应 ds_rating_data val
				editable : false,
				mode : 'local'  // local 本地 ,  remote 远程
		     }
			]
		}, {
			columnWidth : .4,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				handler: function(){
                ds_extjstest.baseParams['s_functionName'] = frm_extjstest.form.findField('s_functionName').getValue();
                ds_extjstest.baseParams['s_insertTime'] = frm_extjstest.form.findField('s_insertTime').getRawValue();
                ds_extjstest.baseParams['s_types'] = frm_extjstest.form.findField('s_types').getValue();
                ds_extjstest.baseParams['s_rating'] = frm_extjstest.form.findField('s_rating').getValue();
                pag_extjstest.doLoad(0);
                }
			}, {
				xtype : 'button',
				text : '清 空',
				handler:function(){
				    frm_extjstest.getForm().reset();
				}
		}, {
				xtype : 'button',
				text : '测试弹出窗口',
				handler:function(){
				    fun_opnewindow("detail_ExtjsTest","窗口标题","admin/manage/ExtjsTest/detail.jsp","弹出窗口说明");
				}
		}, {
				xtype : 'button',
				text : '测试弹出窗口',
				handler:function(){
				    fun_windows("detail_ExtjsTest","窗口标题","http://wenda.tianya.cn/wenda/thread?tid=7991068e96c93daf","弹出窗口说明");
				}
		}]
	}]
});

function fun_rendercz(value, cellmeta, record, rowIndex, columnIndex, store) {
	var update_str= "<a href='javascript:fun_opnetab(\""
			+"update_ExtjsTest"+"\",\""+ "修改功能测试"+"\",\""+ "admin/manage/ExtjsTest/update.jsp?id="+record.get("id")+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/wrench.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
	var detail_str= "<a href='javascript:fun_opnetab(\""
			+"detail_ExtjsTest"+"\",\""+ "查看功能测试"+"\",\""+ "admin/manage/ExtjsTest/detail.jsp"+"\",\""+ "detail"+"\")'>"
			+"<img src = 'images/testhelp.gif' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>查看</span></a>"
	var delete_str= "<a href='javascript:fun_del_extjstest()'>"
			+"<img src = 'images/cross.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
	var copy_str= "<a href='javascript:fun_opnetab(\""
			+"copy_ExtjsTest"+"\",\""+ "复制作数据测试"+"\",\""+ "admin/manage/ExtjsTest/update.jsp"+"\",\""+ "edit"+"\")'>"
			+"<img src = 'images/undo.png' align='center' style ='margin-left: 5px' /><span style='vertical-align: bottom'>修改</span></a>"
	var defult_str= "<a href='javascript:fun_del_extjstest()'>"
			+"<img src = 'images/del.gif'  align='center' style ='margin-left: 5px'/><span style='vertical-align: bottom'>删除</span></a>"
    
     if(record.get("whetherDefault")=='1'){
         return update_str+detail_str+delete_str+copy_str;
     }else{
         return update_str+detail_str+delete_str+copy_str+defult_str;
     }
	 

};
// 删除方法
// 方法名 (fun_del_extjstest)+ =  + function () { 这写法有点特别 
// 正常写法是 function fun_del_extjstest(){ 
fun_del_extjstest=function(){
        var record = grid_extjstest.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'manage/ExtjsTest/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_extjstest.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_extjstest =new Ext.data.Store({
	url:'manage/ExtjsTest/list.do',
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','functionName','parentId','types','insertTime','telephone','mobileTelephone','frequency','upfileUrl','rating','whetherDefault','functionExplain','cz']),
	remoteSort:true
});

pag_extjstest= new Ext.PagingToolbar({
	    store:ds_extjstest,
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
 
var cm_extjstest = new Ext.grid.ColumnModel([
	 
    grid_row
    ,{header:'能功名称',width:100,sortable:true,dataIndex:'functionName'}
    ,{header:'上级能功ID',width:100,sortable:true,dataIndex:'parentId'}
    ,{header:'能功类型',width:100,sortable:true,dataIndex:'types'}
    ,{header:'新增时间',width:100,sortable:true,dataIndex:'insertTime'}
    ,{header:'系联电话',width:100,sortable:true,dataIndex:'telephone'}
    ,{header:'手机号码',width:100,sortable:true,dataIndex:'mobileTelephone'}
    ,{header:'次数',width:100,sortable:true,dataIndex:'frequency'}
    ,{header:'上传文件URL',width:100,sortable:true,dataIndex:'upfileUrl'}
    ,{header:'等级',width:100,sortable:true,dataIndex:'rating'}
    ,{header:'是否为默认功能',width:100,sortable:true,dataIndex:'whetherDefault'}
    ,{header:'能功说明',width:100,sortable:true,dataIndex:'functionExplain'}
	,{header : '操作',width:200,renderer:fun_rendercz}
]);

cm_extjstest.defaultSortable = true;

var search_extjstest =new Ext.ux.grid.Search({
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

grid_extjstest= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_extjstest,
	sm : new Ext.grid.RowSelectionModel( ),
	cm : cm_extjstest,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	plugins : search_extjstest,
	tbar : ['<span style="color:blue;">双击表格可查看详细信息  </span>'],	
	bbar : pag_extjstest
});

function fun_rowdblclickfn(grid, rowIndex, e){// 双击事件
    var row = grid_extjstest.store.getById(grid.store.data.items[rowIndex].id);   
    fun_tab_rolepermission('detail_ExtjsTest_click','查看详细信息','admin/manage/ExtjsTest/detail.jsp'); 
}  


function fun_tab_rolepermission(id,code,url){// 打开TAB
        var tab = panel.findById('tab-'+id);
        if (tab){
              panel.setActiveTab('tab-'+id);
            }else{
                 if (panel.items.length > 7) {
							Ext.Msg.alert('温馨提示', '您打开的窗口数己超过7个!');
						}else{
                          var IFramePanel = new Ext.Panel({
								id : 'tab-'+id,
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

grid_extjstest.addListener('rowdblclick', fun_rowdblclickfn);   

ds_extjstest.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask.hide();
		}
	}
});

var panel_extjstestdiv = new Ext.Panel({
	renderTo : 'extjstestdiv',
	width : Ext.get("extjstestdiv").getWidth(),
	height : Ext.get("extjstestdiv").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_extjstest, grid_extjstest]
});

panel.on('resize',function(){
    if(Ext.get("extjstestdiv")){
        var p =panel.getActiveTab().getId();
        if(p!='1110102'){
             panel.setActiveTab('1110102');
             panel_extjstestdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_extjstestdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_extjstestdiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_extjstestdiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.extjstest.index();
</script>
</head>
<body><div id="extjstestdiv" style="width:100%;height:100%;"></div></body>
</html>