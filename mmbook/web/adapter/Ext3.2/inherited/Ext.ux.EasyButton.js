/**
 * 封装简单按妞效果（刘文辉）
 * **/
Ext.ux.EasyButton = Ext.extend(Ext.Button, {
	cls : 'x-btn-over',
	listeners : {
		mouseout : function() {
			this.removeClass('x-btn-over');
			this.addClass('x-btn-over');
		}
	}
});
Ext.reg('easyButton', Ext.ux.EasyButton);