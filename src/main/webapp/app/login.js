Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath({
	'Ext.ux':'jsLib/extjs/ux'
});
Ext.require([
	'Ext.ux.MD5'
]);
Ext.application({
	appFolder : 'app',
	name : 'plat',
	controllers: ['LoginController'],
	launch : function () {
		Ext.create('Ext.window.Window', {
			id:'login-window',
			title:'系统登录',
			layout:'fit',
			frame:true,
			closable:false,
			items: [{
				xtype:'loginform',
				width:300,
				height:200
			}]
		}).show();
	}
});