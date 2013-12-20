Ext.Loader.setConfig({
	enabled : true,
	paths   : {
		'Plat': 'app',
		'Ext.ux.desktop': 'app/ux'
	} 
});
Ext.require('Plat.App');

var PlatApp;
Ext.onReady(function () {
    PlatApp = new Plat.App({
    	version:'1.0.0'
    });
});