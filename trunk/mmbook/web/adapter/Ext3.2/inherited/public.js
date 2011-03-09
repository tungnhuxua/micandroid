/**
 * 
 * 公用的JS写入此文件
 * 注意命名不能重 应以fun_ 加方法名 另注意变量名的命名
 * **/

function fun_opnetab(id, code, url, fs) {// 打开TAB
	var panel = Ext.getCmp('center-tab-panel');
	var tab = panel.findById(id);
	if (tab) {
		panel.remove(id);
	} //else {
		if (panel.items.length > 6) {
			Ext.Msg.alert('温馨提示', '您打开的窗口数不能超过7个!');
		} else {
			var IFramePanel = new Ext.Panel({
				id : id,
				title : code,
				iconCls : 'icon-tab-'+fs,
				height : '100%',
				closable : true,
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
				},
				scope : this, 
				discardUrl : true,
				nocache : true,
				text : "页面加载中,请稍候……",
				scripts : true
			});
		}
	//}
}

function fun_copnetab(id, code, url,fs) {// 关闭打开TAB
	var panel = Ext.getCmp('center-tab-panel');
	var tab = panel.findById(id);
	panel.remove(panel.getActiveTab());
	if (tab) {
		 panel.setActiveTab(tab);
	} else {
		if (panel.items.length > 6) {
			Ext.Msg.alert('温馨提示', '您打开的窗口数不能超过7个!');
		} else {
			var IFramePanel = new Ext.Panel({
				id : id,
				title : code,
				iconCls : fs,
				height : '100%',
				closable : true,
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
				},
				scope : this, 
				discardUrl : true,
				nocache : true,
				text : "页面加载中,请稍候……",
				scripts : true
			});
		}
	}
}

function getrecordarry(records,field) {//取GIRD数据
		var result = [];
		for(var i = 0; i < records.length; i++) {
			result.push(records[i].get(field));
		}
		return result;
}

//双击复制到剪贴板
function copyToClipboard(txt) { 
    if(window.clipboardData) {      
        window.clipboardData.clearData();      
        window.clipboardData.setData("Text", txt);  
    } else if(navigator.userAgent.indexOf("Opera") != -1) {      
        window.location = txt;      
    } else if (window.netscape) {      
        try {      
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");      
        } catch (e) {      
            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 'signed.applets.codebase_principal_support'设置为'true'");      
        }      
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);      
        if (!clip)return;      
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);      
        if (!trans)return;      
        trans.addDataFlavor('text/unicode');      
        var str = new Object();      
        var len = new Object();      
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);      
        var copytext = txt;      
        str.data = copytext;      
        trans.setTransferData("text/unicode",str,copytext.length*2);      
        var clipid = Components.interfaces.nsIClipboard;      
        if (!clip)return false;      
        clip.setData(trans,null,clipid.kGlobalClipboard);
      }
	  alert("复制" + txt + "成功!");
}

function fun_opnewindow(id, code, url, fs) {// 弹出窗口
	var panel = Ext.getCmp('center-tab-panel');
	var tab = panel.findById(id);
	if (tab) {
		panel.remove(id);
	}
var IFramePanel = new Ext.Panel({
				id : id,
				title : code,
				iconCls : 'icon-tab-'+fs,
				height : '100%',
				closable : true,
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
				},
				scope : this, 
				discardUrl : true,
				nocache : true,
				text : "页面加载中,请稍候……",
				scripts : true
			});
			
	var win = new Ext.Window({
		//id: id,
		title : code,
		width : 500,
		height : 500,
		resizable : false,
		layout : 'fit',
		items : [IFramePanel],
		bbar : [code]
	});
	win.show();
}

function fun_windows(id, code, url, notes) {// 弹出窗口
var win = new Ext.Window({
  id:id,
  title : code,
  width : 780,
  height : 470,
  isTopContainer : true,
  modal : true,
  resizable : false,
  bbar : [notes],
  contentEl : Ext.DomHelper.append(document.body, {
   tag : 'iframe',
   style : "border 0px none;scrollbar:true",
   src : url,
   height : "100%",
   width : "100%"
  })
 })
 win.show();
}