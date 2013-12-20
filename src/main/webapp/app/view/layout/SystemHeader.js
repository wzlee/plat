Ext.define('plat.view.layout.SystemHeader', {
    extend: 'Ext.Container',
    xtype: 'systemheader',
    id: 'system-header',
    height: 40,
    layout: {
        type: 'hbox',
        align: 'middle'
    },
    initComponent: function() {
        this.items = [{
            xtype: 'component',
            id: 'app-header-title',
            html: 'Ext JS Kitchen Sink',
            flex: 1
        },{
                xtype: 'themeSwitcher'
            }];

//        if (!Ext.getCmp('options-toolbar')) {
//            this.items.push({
//                xtype: 'themeSwitcher'
//            });
//        }

        this.callParent();
    }
});
