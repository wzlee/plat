Ext.define('plat.view.cms.CategoryServeTree',{
	extend: 'Ext.tree.Panel',
	alias: 'widget.categoryServeTree',
//	region: 'west',
	layout: 'fit',
	width: '40%',
//	collapsible:true,
	frame: false,
//	split:true,
	autoScroll: true,
	multiSelect: false,
    singleExpand: false,
    useArrows: true,
    rootVisible: false,
    store: 'cms.ServerAsyncStore',
    initComponent: function() {
        this.callParent(arguments);
    }
});



