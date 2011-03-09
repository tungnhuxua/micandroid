/*
 * 关闭TAB(刘文辉)
 */

Ext.ux.TabCloseMenu = function(){
    var tabs, menu, ctxItem;
    this.init = function(tp){
        tabs = tp;
        tabs.on('contextmenu', onContextMenu);
    }

    function onContextMenu(ts, item, e){
        if(!menu){ // create context menu on first right click
            menu = new Ext.menu.Menu([
            /**
            	{
                id: tabs.id + '-close',
                text: '关闭页面',
                handler : function(){
                    tabs.remove(ctxItem);
                }
            },**/
            	{
                id: tabs.id + '-close-others',
                iconCls : 'icon-info',
                text: '关闭其它',
                handler : function(){
                    tabs.items.each(function(item){
                        if(item.closable && item != ctxItem){
                            tabs.remove(item);
                        }
                    });
                }
            },{
                id: tabs.id + '-close-all',
                iconCls : 'icon-delete',
                text: '关闭全部 ',
                handler : function(){
                    tabs.items.each(function(item){
                        if(item.closable){
                            tabs.remove(item);
                        }
                    });
                }
            }]);
        }
        ctxItem = item;
        var items = menu.items;
         /**
        items.get(tabs.id + '-close').setDisabled(!item.closable);**/
        var disableOthers = true;
        tabs.items.each(function(){
            if(this != item && this.closable){
                disableOthers = false;
                return false;
            }
        });
        
        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
        menu.showAt(e.getPoint());
        items.get(tabs.id + '-close-all').setDisabled(disableOthers);
        menu.showAt(e.getPoint());
    }
};