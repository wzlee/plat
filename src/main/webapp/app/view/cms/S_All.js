Ext.define('plat.view.cms.Theme1', {
	extend : 'Ext.panel.Panel',
	xtype : 'theme1',
	border : false,
	bodyBorder : false,
	autoScroll: true,
	items : {
		layout : 'absolute',
		items : [{
			title: '广告位',
			border: true,
			action : 'model',
			width : 700,
			height : 250,
			number: 1,
			indexs: 1,
			x : 0,
			y : 0,
			bbar:['->', {
				text: '1',
				handler: function(){
					var p = this.ownerCt.ownerCt;
					p.loadAdvertImg(1);
				}
			}, {
				text: '2',
				handler: function(){
					var p = this.ownerCt.ownerCt;
					p.loadAdvertImg(2);
				}
			}, {
				text: '3',
				handler: function(){
					var p = this.ownerCt.ownerCt;
					p.loadAdvertImg(3);
				}
			}, {
				text: '4',
				handler: function(){
					var p = this.ownerCt.ownerCt;
					p.loadAdvertImg(4);
				}
			}, {
				text: '5',
				handler: function(){
					var p = this.ownerCt.ownerCt;
					p.loadAdvertImg(5);
				}
			}, {
				text: '6',
				handler: function(){
					var p = this.ownerCt.ownerCt;
					p.loadAdvertImg(6);
				}
			}],
			loadAdvertImg: function(indexs){
				this.indexs = indexs;
				console.log(this.number);
			},
			html : '1'
		}, {
			title: '服务广告位',
			width : 1070,
			height : 'auto',
			x : 0,
			y : 255,
			style: 'border:0px #3892D3 solid;',
			layout : 'absolute',
			items: [{
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 0,
				html : '2'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 0,
				html : '3'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 0,
				html : '4'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 0,
				html : '5'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 860,
				y : 0,
				html : '6'
			}]
		}, {
			title: '推荐服务',
			width : 1070,
			height : 'auto',
			x : 0,
			y : 440,
			style: 'border: 0px #3892D3 solid',
			html : '<div class="topmain">' +
						'<table width="1070px" height="30px" border="0" cellspacing="0" cellpadding="0">' +
					      '<tr>' +
					        '<td class="s-home-td" style="color:#800080;border-bottom-color:#800080;">工业设计商城</td>' +
					        '<td class="s-home-td">物流商城</td>' +
					        '<td class="s-home-td">软件商城</td>' +
					        '<td class="s-home-td">物联网商城</td>' +
					      '</tr>' +
					    '</table>' +
					'</div>'
		}, {
			width : 1070,
			height : 145,
			x : 0,
			y : 510,
			hidden: false,
			style: 'border:0px #3892D3 solid;',
			layout : 'absolute',
			defaults : {
				style : {
					border : '1px #3892D3 solid'
				}
			},
			items : [{
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 0,
				html : '7'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 0,
				html : '8'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 0,
				html : '9'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 0,
				html : '10'
			}]
		}, {
			width : 1070,
			height : 145,
			x : 0,
			y : 440,
			hidden: true,
			style: 'border:0px #3892D3 solid;',
			layout : 'absolute',
			defaults : {
				style : {
					border : '1px #3892D3 solid'
				}
			},
			items : [{
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 0,
				html : '11'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 0,
				html : '12'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 0,
				html : '13'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 0,
				html : '14'
			}]
		}, {
			width : 1070,
			height : 145,
			x : 0,
			y : 440,
			hidden: true,
			style: 'border:0px #3892D3 solid;',
			layout : 'absolute',
			defaults : {
				style : {
					border : '1px #3892D3 solid'
				}
			},
			items : [{
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 0,
				html : '15'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 0,
				html : '16'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 0,
				html : '17'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 0,
				html : '18'
			}]
		}, {
			width : 1070,
			height : 145,
			x : 0,
			y : 440,
			hidden: true,
			style: 'border:0px #3892D3 solid;',
			layout : 'absolute',
			defaults : {
				style : {
					border : '1px #3892D3 solid'
				}
			},
			items : [{
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 0,
				html : '19'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 0,
				html : '20'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 21,
				x : 430,
				y : 0,
				html : '21'
			}, {
				action : 'model',
				width : 210,
				height : 145,
				number: 22,
				x : 645,
				y : 0,
				html : '22'
			}]
		}, {
			action : 'model',
			border: true,
			width : 1070,
			height : 80,
			number: 23,
			x : 0,
			y : 660,
			html : '<div class="s-home-div-algin">广告位</div>'
		}, {
			title: '政策法规',
			width : 1070,
			height : 'auto',
			x : 0,
			y : 745,
			hidden: false,
			style: 'border:0px #3892D3 solid;',
			layout : 'absolute',
			items : [{
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 0,
				html : '24'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 0,
				html : '25'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 0,
				html : '26'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 0,
				html : '27'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 295,
				number: 2,
				x : 860,
				y : 0,
				html : '<div class="s-home-div-algin">广告位</div>'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 150,
				html : '29'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 150,
				html : '30'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 150,
				html : '31'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 150,
				html : '32'
			}]
		}, {
			title: '创业指导',
			width : 1070,
			height : 'auto',
			x : 0,
			y : 1080,
			hidden: false,
			style: 'border:0px #3892D3 solid;',
			layout : 'absolute',
			items : [{
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 0,
				html : '33'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 0,
				html : '34'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 0,
				html : '35'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 0,
				html : '36'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 295,
				number: 2,
				x : 860,
				y : 0,
				html : '<div class="s-home-div-algin">广告位-37</div>'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 0,
				y : 150,
				html : '38'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 215,
				y : 150,
				html : '39'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 430,
				y : 150,
				html : '40'
			}, {
				action : 'model',
				border: true,
				width : 210,
				height : 145,
				number: 2,
				x : 645,
				y : 150,
				html : '41'
			}]
		}]
	}
});

