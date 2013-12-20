/**
 * 服务广告位 可以左右移动
 */
Ext.define('plat.view.cms.S_Advert', {
	extend : 'Ext.panel.Panel',
	xtype : 's_advert',
	border : false,
	bodyBorder : false,
	autoScroll: true,
	layout : 'absolute',
	items : [{
		width : 1070,
		height : 'auto',
		x : 0,
		y : 0,
		style: 'border:0px #3892D3 solid;',
		layout : 'absolute',
		items: [{
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
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 5,
			x : 860,
			y : 0
		}]
	}]
});