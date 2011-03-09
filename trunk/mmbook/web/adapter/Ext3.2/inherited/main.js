Ext.BLANK_IMAGE_URL = '/mmbook/adapter/Ext3.2/resources/images/default/s.gif';
Ext.QuickTips.init();
var start = {
	id : 'start-panel',
	title : '欢迎使用',
	layout : 'fit',
	bodyStyle : 'padding:25px',
	html : '<img src="/bmsh/images/bg.jpg"/>'
};

Ext.form.Field.prototype.msgTarget='under';
Ext.onReady(function() {

var contentPanel;
var node_id="1110000";
var treeLoader =new Ext.tree.TreeLoader({
		    dataUrl : 'security/Security/nemu.do?node_id='+node_id
});

var myroot =  new Ext.tree.AsyncTreeNode() ;

var Mtree = new Ext.tree.TreePanel({
		id : 'Mtreeid',
		border : false,
		rootVisible : false,
		autoScroll : true,
		loader : treeLoader ,
		root : myroot 
	

	});

function tree_refresh(){
   //alert("tree_refresh");
   var tree = Ext.getCmp('Mtreeid');
       var loader = new Ext.tree.TreeLoader({
		    dataUrl : 'security/Security/nemu.do?node_id=sfasdf'
		});
		//tree.myroot.reload()
       loader.load(myroot);
	//myroot.expand(); 
	//myroot.parentNode.reload(); 
		//   myroot.reload(); // 重刷整树
          myroot.expand();
}; 

	Mtree.on('click', function(node) {
		if (node.isLeaf()) {
			if (node.attributes.url == "") {
				Ext.Msg.alert('温馨提示', '试用期间 此功能暂不开放!');
			} else {
				// setTimeout(function() {
					var n = contentPanel.getComponent(node.id);
					if (n) {// 判断是否已经打开该面板
						// contentPanel.remove(n);
						contentPanel.setActiveTab(n);
					} else {
						if (contentPanel.items.length > 6) {
							Ext.Msg.alert('温馨提示','您打开的窗口数不能超过7个!');
						} else {
							var IFramePanel = new Ext.Panel({
								id : node.id,
								title : node.text,
								iconCls : node.attributes.iconCls,
								height : '100%',
								closable : true,
								autoScroll : true,
								margins : '5 5 5 5'
							});
							contentPanel.add(IFramePanel);
							contentPanel.setActiveTab(IFramePanel);

							IFramePanel.load({
								url : node.attributes.url,
								callback : function(r, options, success) {
									if (success.status == 404) {
										IFramePanel.load({
											url : "LoginAction!errorMenu.action"
										});
									}
								},
								scope : this, 
								discardUrl : true,
								nocache : true,
								text : "页面加载中,请稍候……",
								scripts : true
							});
						}
					}
				// }, 100);
			}
		}
	});

	var west = {
		region : 'west',
		id : 'west-panel',
		//title : 'SIMS菜单导航',
		width : 230,
		autoScroll : true,
		margins : '0 5 0 5',
		defaults : {
			autoScroll : true,
			border : false
		},
		layoutConfig : {
			animate : true
		},
		items : [Mtree]
		,

		
		bbar : [{
					text : '开始',
					iconCls : 'icon-plugin',
					menu : new Ext.menu.Menu({
						items : [{
							text : '关于系统',
							iconCls : 'icon-info',
							handler : function() {
								new Ext.Window({
									closeAction : 'close',
									resizable : false,
									bodyStyle : 'padding: 7',
									modal : true,
									title : '关于本系统',
									html : '本系统采用目前较为流行的技术实现,<br>前台使用了ExtJs技术,所以实现了跨浏览器<br>' +
											'本程序在IE6,IE7,FireFox3均测试通过!<br><br>主要技术: Struts2 + Spring2.5 + iBatis2.3 + ExtJs2.2<br><br>'
											+ '数&nbsp;&nbsp;据&nbsp;&nbsp;库: MySql 5.0',
									width : 300,
									height : 200
								}).show();
							}
						}, {
							text : '退出系统',
							iconCls : 'icon-delete',
							handler : function() {
								Ext.Msg.confirm('操作提示', '您确定要退出本系统?', function(btn) {
									if ('yes' == btn) {
										Ext.Ajax.request({
											url : 'logout.action',
											success : function() {
												location = '/bmsh/index.jsp';
											},
											failure : function() {
												Ext.Msg.show({
													title : '错误提示',
													msg : '退出系统失败!',
													icon : Ext.Msg.ERROR,
													buttons : Ext.Msg.OK
												});
											}
										});
									}
								});
							}
						}]
					})
				}]
	
	};

	var MenuTreeFilter = new Ext.tree.TreeFilter(Mtree, {
		clearBlank : true,
		autoClear : true
	});

	var MenuTreeHiddenPkgs = [];
	function filterMenuTree(e) {
		if (e.getKey() == 13) {
			var text = e.target.value;
			Ext.each(MenuTreeHiddenPkgs, function(n) {
				n.ui.show();
			});
			if (!text) {
				MenuTreeFilter.clear();
				return;
			}
			Mtree.expandAll();
			var re = new RegExp(Ext.escapeRe(text), 'i');
			MenuTreeFilter.filterBy(function(n) {
				return !n.isLeaf() || re.test(n.text);
			});
			// west empty packages that weren't filtered
			Mtree.root.cascade(function(n) {
				if (n.id != Mtree.root.id) {
					if (!n.isLeaf() && hasChild(n, re) == false) {
						n.ui.hide();
						MenuTreeHiddenPkgs.push(n);
					}
				}
			});
		}
	};
	// 查找该节点下是否有符合条件的子节点
	function hasChild(n, re) {
		var str = false;
		n.cascade(function(n1) {
			if (re.test(n1.text)) {
				str = true;
				return;
			}
		});
		return str;
	};

					tbar : [new Ext.form.TextField({
			width : 160,
			emptyText : '快速查找 输入后按回车',
			listeners : {
				render : function(f) {
					f.el.on('keydown', filterMenuTree, f, {
						buffer : 350
					});
				}
			}
		}), '-', {
			iconCls : 'icon-exp',
			tooltip : '展开',
			handler : function() {
				Mtree.root.expand(true);
			}
		}, '-', {
			iconCls : 'icon-col',
			tooltip : '收缩',
			handler : function() {
				Mtree.root.collapse(true);
			}
		}]

	contentPanel = new Ext.TabPanel({
		id : 'center-tab-panel',
		region : 'center',
		deferredRender : false,
		enableTabScroll : true,
		activeTab : 0,
		margins : '0 5 0 0',
		defaults : {
			autoScroll : true
		},
		plugins : new Ext.ux.TabCloseMenu(),
		items : [{
			contentEl : 'center1',
			title : '欢迎使用',
			iconCls : 'icon-home',
			html : '<p><iframe id="main"  frameborder="0" style="width:100%;height:100%;" src="MyJsp.jsp"></iframe></p>'
		}]
	});
	
var myTextField =new Ext.form.TextField({
			width : 160,
			emptyText : '快速查找 输入后按回车',
			listeners : {
				render : function(f) {
					f.el.on('keydown', filterMenuTree, f, {
						buffer : 350
					});
				}
			}
});
 
var north = new Ext.Panel({
		region : 'north',
		contentEl : 'north',
		height : 55,
		layout : 'anchor',
		//title : 'MM书城页面效果',
		closable : true,
		margins : '0 0 5 0',
		items : [ 
		{
			// xtype : 'panel',
			height : 55,
			// frame : true,
			html : '<div id="header"></div>',
			border : false
		} 
		],
		bbar : new Ext.Toolbar({
			border : false,
			items : [
				myTextField, '-', {
			iconCls : 'icon-exp',
			tooltip : '展开',
			handler : function() {
				Mtree.root.expand(true);
			}
		}, '-', {
			iconCls : 'icon-col',
			tooltip : '收缩',
			handler : function() {
				Mtree.root.collapse(true);
			}
		} ,'-',
			 '-', {
				text : '操作员',
				// iconCls : 'icon-info',
				iconCls : 'icon-gyxt',
				handler : function() {
					new Ext.Window({
						closeAction : 'close',
						resizable : false,
						iconCls : 'icon-gyxt',
						bodyStyle : 'padding: 7',
						modal : true,
						title : '操作员信息',
						html : 'xxj',
						width : 340,
						height : 220
					}).show();
				}
			}, '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
			
			 '-',{
				text : '刷新菜单',
				// iconCls : 'icon-info',
				iconCls : 'icon-gyxt',
				handler : function() {
					tree_refresh();
				}
			},
			 '-',  
			 '-'
			 
			 ]
		})
	});

 
		Ext.get('loading').remove();
		var vp = new Ext.Viewport({
			layout : 'border',
			defaults : {
				collapsible : true,
				split : true
			},
			items : [{
				xtype : 'box',
				region : 'north',
				applyTo : 'header',
				height : 30,
				split : false
			},
				west, contentPanel, north
			 ]
		});
});