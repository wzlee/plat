Ext.define('plat.view.cms.S_Commend', {
	extend : 'Ext.panel.Panel',
	xtype : 's_commend',
	border : false,
	bodyBorder : false,
	autoScroll: false,
	layout : 'absolute',
	items : [{
		width : 'auto',
		height : 'auto',
		x : 0,
		y : 0,
		hidden: false,
		style: 'border:0px #3892D3 solid;',
		layout : 'absolute',
		items : [{
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 1,
			x : 0,
			y : 0
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 2,
			x : 215,
			y : 0
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 3,
			x : 430,
			y : 0
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 4,
			x : 645,
			y : 0
		}]
	}]
});