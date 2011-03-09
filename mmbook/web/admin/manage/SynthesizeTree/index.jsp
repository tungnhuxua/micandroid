<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
 
<%
response.setHeader("Cache-Control", "no-cache, post-check=0, pre-check=0");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "Thu, 01 Dec 1970 16:00:00 GMT");
%>
  
<html>
<head>
<title>网站分类综合管理</title>

<script type="text/javascript">
Ext.namespace("Ext.ux.rolepermission");

/**网站下拉框**/
var roleStore = new Ext.data.SimpleStore({
	fields: ['value', 'title'],
	proxy: new Ext.data.HttpProxy({
		url: 'site/SiteInfomation/getComboBox.do'
	})
});


Ext.ux.rolepermission.tree = Ext.extend(function(){
var sort =0
var roleId =-1;
var panel = Ext.getCmp('center-tab-panel');
var combo_part =  new Ext.form.ComboBox({ 
      fieldLabel : '网站列表',  //UI标签名称
      hiddenName :'roleId',   //作为form提交时传送的参数名
      allowBlank : false,  //是否允许为空
      mode : "remote",      //数据模式为远程模式, 也可不设置,即默认值也为remote
      readOnly : true,     //是否只读
      triggerAction : 'all',  //显示所有下列数.必须指定为'all'
      anchor : '90%',
      emptyText:'请选择...',   //没有默认值时,显示的字符串
      store : roleStore,
      valueField : 'value',  //传送的值
      displayField : 'title',  //UI列表显示的文本
	  listeners:{"select":function(){
			     roleId =Ext.get("roleId").getValue();
		         channel_tree_root.reload();
		         channel_tree_root.expand();
          }
      }

});
		
var tree_rpwestroot = new Ext.tree.AsyncTreeNode({
			id : 'treerpwestroot',
			text : '系统权限',
			iconCls : 'icon-treeg',
			expanded: true,
			children:[{id:'0',text:'菜单',iconCls:'icon-treep','leaf':true},{id:'1',text:'行为',iconCls:'icon-treep','leaf':true},{id:'2',text:'元素',iconCls:'icon-treep','leaf':true},{id:'3',text:'其它',iconCls:'icon-treep','leaf':true}]
		});

var tree_rpwestpanel = new Ext.tree.TreePanel({
			id : 'tree_rpwestid',
			title : '系统权限',
			region : 'center',
			iconCls : 'icon-undo',
			margins : '5 0 5 5',
			//collapseMode : 'mini',
			animate : false,//不以动画形式伸展,收缩子节
			rootVisible : true,//是否显示根节点
			checkModel : "single",
			split : true,
			width : '30%',
			minSize : 140,
			maxSize : 280,
			layout: 'fit',
			loader : new Ext.tree.TreeLoader(),
			root : tree_rpwestroot
});

var channel_tree_root = new Ext.tree.AsyncTreeNode({
			id : 'channel_tree_root',
			text : '频道列表',
			iconCls : 'icon-treeg',
			expanded: true,
			children:[{id:'0',text:'菜单',iconCls:'icon-treep','leaf':true},{id:'1',text:'行为',iconCls:'icon-treep','leaf':true},{id:'2',text:'元素',iconCls:'icon-treep','leaf':true},{id:'3',text:'其它',iconCls:'icon-treep','leaf':true}]
		});

var channel_tree_panel = new Ext.tree.TreePanel({
			id : 'channel_tree_panel',
			title : '频道',
			
			iconCls : 'icon-undo',
			margins : '5 5 5 0',
			region : 'west',
			//collapseMode : 'mini',
			animate : false,//不以动画形式伸展,收缩子节
			rootVisible : true,//是否显示根节点
			checkModel : "single",
			split : true,
			width : '30%',
			minSize : 140,
			maxSize : 280,
			layout: 'fit',
			loader : new Ext.tree.TreeLoader(),
			root : channel_tree_root
});


channel_tree_panel.on('click', function(node) {
         if(node.id!='treerpwestroot'){
            sort =node.id;
            //tree_rpcenterpanel.enable();
            tree_rpcenterpanel.setTitle("角色"+node.text);
			tree_rpcenteroot.setText("角色"+node.text);
			if(roleId==-1){
                Ext.Msg.show({
                title: '提示', msg: '请先选择系统角色！',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK
                });
            }else{
		        tree_rpcenteroot.reload(); // 重刷整树
                tree_rpcenteroot.expand();
            }
         }
});

var tree_rpcenteroot =new Ext.tree.AsyncTreeNode({
		    id : 'tree_rpcenterid',
			text : '角色菜单',
			arr : '',
			iconCls : 'icon-treeg',
			draggable : true
});
					
var tree_rpcenterpanel = new Ext.tree.TreePanel({
			id : 'CenterPanel',
			title : '角色权限',
			
			iconCls : 'icon-undo',
			loadMask : {
				msg : '数据加载中...'
			},
			region : 'east',
			margins : ' 5 1 0 0',  //5 5 5 0   5 0 5 5

			autoHeight : false,

			width : '30%',
			rootVisible : true,
			autoScroll : true,
			animate : true,
			root : tree_rpcenteroot,
			loader: new Ext.tree.TreeLoader({
                baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } //添加 uiProvider 属性
            })
		});

tree_rpcenterpanel.on('beforeload', function(node) {
	tree_rpcenterpanel.loader.dataUrl = 'system/RolePermission/getRolePermissionTree.do?sort='+sort+'&roleId='+roleId+node.attributes['arr'];
});

//tree_rpcenterpanel.disable();

var panel_rolepermissiondiv = new Ext.Panel({
			renderTo : 'rolepermissiontreediv',
			width : Ext.get("rolepermissiontreediv").getWidth(),
			height : Ext.get("rolepermissiontreediv").getHeight(),
			border : false,
			layout : 'border',
			items : [channel_tree_panel,tree_rpcenterpanel,tree_rpwestpanel],
			tbar : new Ext.Toolbar({
			   items : [{
		           text : '保存',
		           iconCls : 'icon-save',
		           tooltip : '保存',
		           handler: function(){
		              if(roleId==-1){
                          Ext.Msg.show({
                              title: '提示', msg: '请先选择系统角色！',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK
                          });
                          return;
                      }
                      var permissionIdarr = '', selNodes = tree_rpcenterpanel.getChecked();
                      Ext.each(selNodes, function(node){
                          if(permissionIdarr.length > 0){
                              permissionIdarr += ', ';
                          }
                          permissionIdarr += node.id;
                      });
                      if(permissionIdarr.length ==0){
                            Ext.Msg.show({title: '提示', msg: '请先选择角色权限后再保存！',icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
                            return;
                       }
                      Ext.Ajax.request({
					     url:'system/RolePermission/deleterolePermission.do',
					     params:{
								permissionIdarr:permissionIdarr,
								roleId:roleId,
								sort:sort
						 },
		                 method:'POST',
		                 success:function(response){
		                     var data = Ext.util.JSON.decode(response.responseText);
		                     if (data.success == true){
		                         Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
		                         tree_rpcenteroot.reload();
		                     }else{
		                         Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                     }
		                 },scope:this
		             });
                  }
	           }, '->','网站列表： ',combo_part]
		    })
});

panel.on('beforeremove', function(tab, item) {
		if(item.id=='1041107'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_rolepermissiondiv.destroy();
		    }
		}
});

panel.on('resize',function(){
    if(Ext.get("rolepermissiontreediv")){
        var p =panel.getActiveTab().getId();
        if(p!='1131110'){
             panel.setActiveTab('1131110');
             panel_rolepermissiondiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_rolepermissiondiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             panel.setActiveTab(p);
        }else{
            panel_rolepermissiondiv.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_rolepermissiondiv.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
        }
    }
});

},Ext.util.Observable);
new Ext.ux.rolepermission.tree();
</script>
</head>
<body><div id="rolepermissiontreediv" style="width:100%;height:100%;"></div></body>
</html>