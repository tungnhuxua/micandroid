/**
 * 主题样式(刘文辉)
 */
Ext.ux.ThemeChange = Ext.extend(Ext.form.ComboBox, { 
	editable : false,
	displayField : 'theme',
	valueField : 'css',
	typeAhead : true,
	mode : 'local',
	triggerAction : 'all',
	selectOnFocus : true,
	initComponent : function() {
		var themes = [
				['默认风格', 'ext-all.css'],
				['深灰风格', 'xtheme-darkgray.css'],
				['银白风格', 'xtheme-gray.css'],
				['橄榄风格', 'xtheme-olive.css'],
				['紫兰风格', 'xtheme-purple.css'],
				['暗蓝风格', 'xtheme-slate.css'],
				['咖啡风格', 'xtheme-chocolate.css']
		];
		this.store = new Ext.data.SimpleStore( {
			fields : ['theme', 'css'],
			data : themes
		});
		this.value = '默认风格';
	},
	initEvents : function() {
		this.on('collapse', function() {
			Ext.util.CSS.swapStyleSheet('theme', 'adapter/Ext3.2/resources/css/'+ this.getValue());
		});
	}
});
Ext.reg('themeChange', Ext.ux.ThemeChange);