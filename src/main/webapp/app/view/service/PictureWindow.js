Ext.define('plat.view.service.PictureWindow', {
    extend: 'Ext.window.Window',
	xtype:'picturewindow',
    width: 300,
    height: 300,
	layout:'fit',
	modal:true,
	buttonAlign:'center',
	closeAction : 'hide',
	tpl:new Ext.XTemplate(
	    '<img src={src} width="300" height="300" onerror="this.src=\'resources/images/service/default_service_pic.gif\'">'
	)

});