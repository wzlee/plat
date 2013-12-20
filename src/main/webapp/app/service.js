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
	'Ext.ux.form.SearchField',
	'Ext.ux.TreeCombo'
			]);
var plat = new Ext.application({
    name: 'plat',
//    appFolder:'resource/app',
    controllers: ['policy.PolicyController'],
    launch: function() {
		Ext.create('plat.view.policy.PolicyGrid', {
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

