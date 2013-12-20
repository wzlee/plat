Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath({
	'Ext.ux':'jsLib/extjs/ux'
});
Ext.require([
	'Ext.ux.TabCloseMenu',
	'Ext.ux.MD5',
//	'Ext.ux.CheckColumn',
	'Ext.ux.grid.FiltersFeature',
//	'Ext.ux.RowExpander',
	'Ext.ux.GridComboBox',
	'Ext.ux.GridComboBoxList',
	'Ext.ux.DataView.DragSelector',
	'Ext.ux.UploadPanel',
	'Ext.ux.ComboBoxTree',
	'Ext.ux.TreeCombo'
]);

Ext.application({
	appFolder : 'app',
	name : 'plat',
	launch : function () {
		Ext.create('Ext.container.Viewport', {
			layout : 'border',
			items : [{
				xtype : 'panel',
				region : 'west',
				width : 200,
				titile : 'asdfasdf',
				border : true,
				html : '我在左边'
			}, {
				xtype : 'newsgrid',
				region : 'center'
			} /*{
				xtype : 'aservicegrid',
				region : 'center'
			}*/]
		});
	},
	controllers : ['cms.NewsController','service.ServiceController']
});