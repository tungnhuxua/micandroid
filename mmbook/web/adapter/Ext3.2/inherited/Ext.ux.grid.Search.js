Ext.ns('Ext.ux.grid');
/**
 * 扩展GIRD查询(刘文辉)
 */
Ext.ux.grid.Search = function (config) {
    Ext.apply(this, config);
    Ext.ux.grid.Search.superclass.constructor.call(this);
}; // eo constructor

Ext.extend(Ext.ux.grid.Search, Ext.util.Observable, {
    searchText: 'Search',
    searchTipText: 'Type a text to search and press Enter',
    selectAllText: 'Select All',
    position: 'bottom',
    iconCls: 'icon-magnifier',
    checkIndexes: 'all',
    disableIndexes: [],
    dateFormat: undefined,
    showSelectAll: true,
    menuStyle: 'checkbox',
    minCharsTipText: 'Type at least {0} characters',
    mode: 'remote',
    width: 100,
    xtype: 'gridsearch',
    paramNames: {
        fields: 'fields',
        query: 'query'
    },
    shortcutKey: 'r',
    shortcutModifier: 'alt',
    init: function (grid) {
        this.grid = grid;
        // setup toolbar container if id was given
        if ('string' === typeof this.toolbarContainer) {
            this.toolbarContainer = Ext.getCmp(this.toolbarContainer);
        }
        // do our processing after grid render and reconfigure
        grid.onRender = grid.onRender.createSequence(this.onRender, this);
        grid.reconfigure = grid.reconfigure.createSequence(this.reconfigure, this);
    },
    onRender: function () {
        var panel = this.toolbarContainer || this.grid;
        var tb = 'bottom' === this.position ? panel.bottomToolbar : panel.topToolbar;

        // add menu
        this.menu = new Ext.menu.Menu();

        // handle position
        if ('right' === this.align) {
            tb.addFill();
        }
        else {
            if (0 < tb.items.getCount()) {
                tb.addSeparator();
            }
        }
        // add menu button
        tb.add({
            text: this.searchText,
            menu: this.menu,
            iconCls: this.iconCls
        });
        // add input field (TwinTriggerField in fact)
        this.field = new Ext.form.TwinTriggerField({
            width: this.width,
            emptyText: this.searchTipText,
            selectOnFocus: undefined === this.selectOnFocus ? true : this.selectOnFocus,
            trigger1Class: 'x-form-clear-trigger',
            trigger2Class: this.minChars ? 'x-hidden' : 'x-form-search-trigger',
            onTrigger1Click: this.minChars ? Ext.emptyFn : this.onTriggerClear.createDelegate(this),
            onTrigger2Click: this.onTriggerSearch.createDelegate(this),
            minLength: this.minLength
        });

        // install event handlers on input field
        this.field.on('render', function () {
            this.field.el.dom.qtip = this.minChars ? String.format(this.minCharsTipText, this.minChars) : this.searchTipText;
            if (this.minChars) {
                this.field.el.on({
                    scope: this,
                    buffer: 300,
                    keyup: this.onKeyUp
                });
            }
            // install key map
            var map = new Ext.KeyMap(this.field.el, [{
                key: Ext.EventObject.ENTER,
                scope: this,
                fn: this.onTriggerSearch
            },
            {
                key: Ext.EventObject.ESC,
                scope: this,
                fn: this.onTriggerClear
            }]);
            map.stopEvent = true;
        }, this, {
            single: true
        });
        tb.add(this.field);
        // reconfigure
        this.reconfigure();
        // keyMap
        if (this.shortcutKey && this.shortcutModifier) {
            var shortcutEl = this.grid.getEl();
            var shortcutCfg = [{
                key: this.shortcutKey,
                scope: this,
                stopEvent: true,
                fn: function () {
                    this.field.focus();
                }
            }];
            shortcutCfg[0][this.shortcutModifier] = true;
            this.keymap = new Ext.KeyMap(shortcutEl, shortcutCfg);
        }

        if (true === this.autoFocus) {
            this.grid.store.on({
                scope: this,
                load: function () {
                    this.field.focus();
                }
            });
        }
    },
    onKeyUp: function () {
        var length = this.field.getValue().toString().length;
        if (0 === length || this.minChars <= length) {
            this.onTriggerSearch();
        }
    },
    onTriggerClear: function () {
        if (this.field.getValue()) {
            this.field.setValue('');
            this.field.focus();
            this.onTriggerSearch();
        }
    },
    onTriggerSearch: function () {
        if (!this.field.isValid()) {
            return;
        }
        var val = this.field.getValue();
        var store = this.grid.store;
        // grid's store filter
        if ('local' === this.mode) {
            store.clearFilter();
            if (val) {
                store.filterBy(function (r) {
                    var retval = false;
                    this.menu.items.each(function (item) {
                        if (!item.checked || item.dataIndex == undefined || retval) {
                            return;
                        }
                        var rv = r.get(item.dataIndex);
                        rv = rv instanceof Date ? rv.format(this.dateFormat || r.fields.get(item.dataIndex).dateFormat) : rv;
                        var re = new RegExp(val, 'gi');
                        retval = re.test(rv);
                    }, this);
                    if (retval) {
                        return true;
                    }
                    return retval;
                }, this);
            }
            else {}
        }
        // ask server to filter records
        else {
            // clear start (necessary if we have paging)
            if (store.lastOptions && store.lastOptions.params) {
                store.lastOptions.params[store.paramNames.start] = 0;
            }
            // get fields to search array
            var fields = [];
            this.menu.items.each(function (item) {
                if (item.checked) {
                    fields.push(item.dataIndex);
                }
            });
            // add fields and query to baseParams of store
            delete(store.baseParams[this.paramNames.fields]);
            delete(store.baseParams[this.paramNames.query]);
            if (store.lastOptions && store.lastOptions.params) {
                delete(store.lastOptions.params[this.paramNames.fields]);
                delete(store.lastOptions.params[this.paramNames.query]);
            }
            if (fields.length) {
                store.baseParams[this.paramNames.fields] = Ext.encode(fields);
                store.baseParams[this.paramNames.query] = val;
            }

            // reload store
            store.reload();
        }
    },
    setDisabled: function () {
        this.field.setDisabled.apply(this.field, arguments);
    },
    enable: function () {
        this.setDisabled(false);
    },
    disable: function () {
        this.setDisabled(true);
    },
    reconfigure: function () {
        var menu = this.menu;
        menu.removeAll();

        // add Select All item plus separator
        if (this.showSelectAll && 'radio' !== this.menuStyle) {
            menu.add(new Ext.menu.CheckItem({
                text: this.selectAllText,
                checked: !(this.checkIndexes instanceof Array),
                hideOnClick: false,
                handler: function (item) {
                    var checked = !item.checked;
                    item.parentMenu.items.each(function (i) {
                        if (item !== i && i.setChecked && !i.disabled) {
                            i.setChecked(checked);
                        }
                    });
                }
            }), '-');
        }
        var cm = this.grid.colModel;
        var group = undefined;
        if ('radio' === this.menuStyle) {
            group = 'g' + (new Date).getTime();
        }
        Ext.each(cm.config, function (config) {
            var disable = false;
            if (config.header && config.dataIndex) {
                Ext.each(this.disableIndexes, function (item) {
                    disable = disable ? disable : item === config.dataIndex;
                });
                if (!disable) {
                    menu.add(new Ext.menu.CheckItem({
                        text: config.header,
                        hideOnClick: false,
                        group: group,
                        checked: 'all' === this.checkIndexes,
                        dataIndex: config.dataIndex
                    }));
                }
            }
        }, this);
        if (this.checkIndexes instanceof Array) {
            Ext.each(this.checkIndexes, function (di) {
                var item = menu.items.find(function (itm) {
                    return itm.dataIndex === di;
                });
                if (item) {
                    item.setChecked(true, true);
                }
            }, this);
        }
        if (this.readonlyIndexes instanceof Array) {
            Ext.each(this.readonlyIndexes, function (di) {
                var item = menu.items.find(function (itm) {
                    return itm.dataIndex === di;
                });
                if (item) {
                    item.disable();
                }
            }, this);
        }
    } // eo function reconfigure
}); // eo extend