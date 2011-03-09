/**
简易下拉框（刘文辉）
*/
Ext.ux.EasyCombo = Ext.extend(Ext.form.ComboBox, {
    initComponent: function() {
        this.readOnly = true;
        this.displayField = 'mc';
        this.valueField = 'id';
        this.triggerAction = 'all';
        this.mode=this.mode;;
        this.store=this.store;
	    this.forceSelection= true;
        this.editable=false;
        Ext.ux.EasyCombo.superclass.initComponent.call(this);
    }
});
