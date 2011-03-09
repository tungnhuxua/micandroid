Ext.BLANK_IMAGE_URL = '/mmbook/adapter/Ext3.2/resources/images/default/s.gif';
Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget='under';
Ext.onReady(function() {

	var contentPanel;
	var Mtree = new Ext.tree.TreePanel({
		id : 'Mtreeid',
		border : false,
		rootVisible : false,
		autoScroll : true,
		loader : new Ext.tree.TreeLoader({
		    dataUrl : '/mmbook/inc/admin.json'
		}),
		root : new Ext.tree.AsyncTreeNode()
	});

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
		items : [Mtree],
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

	var north = new Ext.Panel({
		region : 'north',
		contentEl : 'north',
		height : 55,
		layout : 'anchor',
		//title : 'MM书城页面效果',
		closable : true,
		margins : '0 0 5 0',
		items : [{
			// xtype : 'panel',
			height : 55,
			// frame : true,
			html : '<div id="header"></div>',
			border : false
		}],
		bbar : new Ext.Toolbar({
			border : false,
			items : ['&nbsp&nbsp 当前用户：xxj->', {
				text : '帮助',
				// iconCls : 'icon-info',
				iconCls : 'icon-gyxt',
				handler : function() {
					new Ext.Window({
						closeAction : 'close',
						resizable : false,
						iconCls : 'icon-gyxt',
						bodyStyle : 'padding: 7',
						modal : true,
						title : '关于本系统',
						html : 'MM书城',
						width : 340,
						height : 220
					}).show();
				}
			}, '-',{
				text : '退出',
				// iconCls : 'icon-delete',// iconCls : 'icon-key',
				iconCls : 'icon-sz',
				handler : function() {
					Ext.Msg.confirm('操作提示', '您确定要退出本系统?', function(btn) {
						if ('yes' == btn) {
					        location = logout;
				         }
					});
				}
			}, '-']
		})
	});

	Ext.get('loading').remove();
	new Ext.Viewport({
		layout : 'border',
		items : [west, contentPanel, north]
	});
});