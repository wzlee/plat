Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath({
	'Ext.ux':'jsLib/extjs/ux'
});
Ext.require([
	'Ext.ux.TabCloseMenu',
	'Ext.ux.MD5',
	'Ext.ux.grid.FiltersFeature',
	'Ext.ux.GridComboBox',
	'Ext.ux.GridComboBoxList',
	'Ext.ux.DataView.DragSelector',
	'Ext.ux.UploadPanel'
]);
Ext.getBody().unmask();
    	Ext.get('loading').remove();
    	 Ext.fly('loading-mask').animate({
            opacity:0,
            remove:true
        });
var plat = new Ext.application({
    name: 'plat',
    controllers: ['cms.CmsController'],
    launch: function() {
    	
		Ext.create('plat.view.cms.CmsPanel', {
//		Ext.create('plat.view.cms.Theme1', {
//    	Ext.create('plat.view.cms.S_Advert', {
//    	Ext.create('plat.view.cms.S_Commend', {
			width:'100%',
			height:'100%',
			renderTo:Ext.getBody()
    	});
        this.controllers.addListener('add',this.initController,this);
    },
    initController:function(index, controller, token){
    	controller.init();
    	console.log(token+'初始化完毕...');
  	}
});

