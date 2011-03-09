<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>

<%@include file="../inc/param.jsp"%>
<html>
<head>
<title>内容标签主页面</title>
<script type="text/javascript">

Ext.namespace("Ext.ux.cmstagcontent");
Ext.ux.cmstagcontent.index<%=u_id%>=Ext.extend(function(){

var record_start<%=u_id%> = 0;
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');
var pagep<%=u_id%> = new Ext.ux.grid.PageSizePlugin();
var storeLoadMask<%=u_id%> = new Ext.LoadMask(Ext.get("cmstagcontentdiv<%=u_id%>"), {//遮掩层
	msg : "正在加载数据，请等待..."
});
storeLoadMask<%=u_id%>.show();

var frm_cmstagcontent<%=u_id%> = new Ext.FormPanel({
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
			columnWidth : .4,
			layout : 'form',
			defaults : {
				anchor : '93%'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '标签名称',
				name : 's_tagName'
			}]
		}, {
			columnWidth : .2,
			layout : 'table',
			items : [{
				xtype : 'button',
				text : '查 询',
				handler: function(){
                ds_cmstagcontent<%=u_id%>.baseParams['s_tagName'] = frm_cmstagcontent<%=u_id%>.form.findField('s_tagName').getValue();
                pag_cmstagcontent<%=u_id%>.doLoad(0);
                }
			}, {
				xtype : 'button',
				style : 'margin-left: 15px;',
				text : '清 空',
				handler:function(){
				    frm_cmstagcontent<%=u_id%>.getForm().reset();
				}
			}]
	}]
});

function fun_rendercz<%=u_id%>(value, cellmeta, record, rowIndex, columnIndex, store) {
	return "<a class='grid_bn' href='javascript:fun_opnetab(\""
			+"update_CmsTagContent"+record.get("id")+"\",\""+ "修改内容标签"+"\",\""+ "tag/CmsTagContent/updateBaset.do?id="+record.get("id")+"\",\""+ "edit"+"\")'>"
			+"修改</a>"
			+"<a class='grid_bn' href='javascript:fun_del_cmstagcontent<%=u_id%>()'>删除</a>"
};

function fun_tagNamezw<%=u_id%>(value, cellmeta, record, rowIndex, columnIndex, store) {
	return record.get("tagNameValue")
};

fun_del_cmstagcontent<%=u_id%>=function(){// 删除操作
        var record = grid_cmstagcontent<%=u_id%>.getSelectionModel().getSelected();
        if (record) {
        Ext.MessageBox.confirm('确认删除','确定要删除所选记录?',function(btn){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:'tag/CmsTagContent/delete.do?ids='+ record.get('id'),
		            method:'POST',
		            success:function(response){
		                var data = Ext.util.JSON.decode(response.responseText);
		                if (data.success == true){
		                    Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
							pag_cmstagcontent<%=u_id%>.doLoad(0);
		                }else{
		                    Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                }
		            },scope:this
		        });
			  }
		   },this);
      }
};

var ds_cmstagcontent<%=u_id%> =new Ext.data.Store({
	url:'tag/CmsTagContent/list.do?versionId='+<%=v_id%>,
	reader:new Ext.data.JsonReader({
	root:'list',
	totalProperty:'totalSize',
	id:'id'
	},
	  ['id','templetId','categoryId','tagName','tagNameValue','ifPage','rowNums','insertTime','posId','keyId','modelId','updateTime','tagExp','orderType','versionId','companyId','fieldsCode','makePeople','makeTime','ifsale','cz']),
	remoteSort:true
});

pag_cmstagcontent<%=u_id%>= new Ext.PagingToolbar({
	    store:ds_cmstagcontent<%=u_id%>,
	    pageSize:10,
	    plugins: pagep<%=u_id%>,
	    displayInfo:true,
	    displayMsg : '第 {0} 条到 {1} 条,共{2} 条',
	    emptyMsg: "没有数据记录",
	    doLoad : function(start){
            record_start = start;
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

var cm_cmstagcontent<%=u_id%> = new Ext.grid.ColumnModel([
    grid_row<%=u_id%>
    ,{header:'标签名称',width:100,sortable:true,dataIndex:'tagName'}
    ,{header:'中文标签',width:100,editor:new Ext.form.TextField({readOnly: true}),renderer:fun_tagNamezw<%=u_id%>}
    ,{header:'标签说明',width:120,sortable:true,dataIndex:'tagExp'}
	,{header : '操作',width:90,renderer:fun_rendercz<%=u_id%>}
]);

cm_cmstagcontent<%=u_id%>.defaultSortable = true;

function createToolbar() {
        var tb = []
		var add =new Ext.Button({
        	text : '添加',
        	iconCls : 'icon-adds',
            handler : function() {
              fun_opnetab("1013102","新增内容标签","admin/tag/CmsTagContent/add.jsp","edit");
            }
		});
         
        var ref=new Ext.Button({	
        	text : '刷新',
		    iconCls : 'icon-ref',
		    handler : function() {
		         ds_cmstagcontent<%=u_id%>.load();
		    }
        });
        tb.push(add)
        tb.push('-') 
        tb.push(ref)
		return tb
}

grid_cmstagcontent<%=u_id%>= new Ext.grid.EditorGridPanel({
	margins : '0 8 8 8',
	store : ds_cmstagcontent<%=u_id%>,
	sm : new Ext.grid.RowSelectionModel({singleSelect : true}),
	cm : cm_cmstagcontent<%=u_id%>,
	stripeRows : true,
	viewConfig : {forceFit : true},
	loadMask : {msg : '正在加载数据，请等待...'},
	region : 'center',
	clicksToEdit : 1,
	trackMouseOver : true,
	tbar:createToolbar(),
	bbar : pag_cmstagcontent<%=u_id%>
});

ds_cmstagcontent<%=u_id%>.load({
    params:{start:0},
    callback:function(r, options, success){
		if (success) {
			storeLoadMask<%=u_id%>.hide();
		}
	}
});

var panel_cmstagcontentdiv<%=u_id%> = new Ext.Panel({
    id : 'panel_cmstagcontentdiv<%=u_id%>',
	renderTo : 'cmstagcontentdiv<%=u_id%>',
	width : Ext.get("cmstagcontentdiv<%=u_id%>").getWidth(),
	height : Ext.get("cmstagcontentdiv<%=u_id%>").getHeight(),
	border : false,
	layout : 'border',
	items : [frm_cmstagcontent<%=u_id%>, grid_cmstagcontent<%=u_id%>]
});

panel<%=u_id%>.on('beforeremove', function(tab, item) {
		if(item.id=='<%=u_id%>'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        if(Ext.getCmp('panel_cmstagcontentdiv<%=u_id%>')){
		            Ext.getCmp('panel_cmstagcontentdiv<%=u_id%>').destroy();
		        }
		    }
		}
});

panel<%=u_id%>.on('resize',function(){
    if(Ext.get("cmstagcontentdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('<%=u_id%>');
             panel_cmstagcontentdiv<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_cmstagcontentdiv<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_cmstagcontentdiv<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_cmstagcontentdiv<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});
},Ext.util.Observable);
new Ext.ux.cmstagcontent.index<%=u_id%>();
</script>
</head>
<body><div id="cmstagcontentdiv<%=u_id%>" style="width:100%;height:100%;"></div></body>
</html>