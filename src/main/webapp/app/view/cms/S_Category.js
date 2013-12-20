Ext.define('plat.view.cms.S_Category', {
	extend : 'Ext.panel.Panel',
	xtype : 's_category',
	border : false,
	bodyBorder : false,
	autoScroll: true,
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
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 295,
			number: 5,
			x : 860,
			y : 0,
			html : '<div class="s-home-div-algin">广告位</div>'
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 6,
			x : 0,
			y : 150
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 7,
			x : 215,
			y : 150
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 8,
			x : 430,
			y : 150
		}, {
			action : 'model',
			border: true,
			width : 210,
			height : 145,
			number: 9,
			x : 645,
			y : 150
		}]
	}]
});

