/**
 * 服务商城【Banner】
 */
Ext.define('plat.view.cms.Global_Banner', {
	extend : 'Ext.panel.Panel',
	xtype : 'global_Banner',
	border : true,
	bodyBorder : false,
	layout : 'absolute',
	action : 'model',
	width : 715,
	height : 305,
	number: 1,
	x : 0,
	y : 0,
	bbar:['->', {
		text: '1',
		indexs: 1,
		records: null,
		handler: function(b){
			var p = this.ownerCt.ownerCt;
			p.loadAdvertImg(1, b.records);
		}
	}, {
		text: '2',
		indexs: 2,
		records: null,
		handler: function(b){
			var p = this.ownerCt.ownerCt;
			p.loadAdvertImg(2, b.records);
		}
	}, {
		text: '3',
		indexs: 3,
		records: null,
		handler: function(b){
			var p = this.ownerCt.ownerCt;
			p.loadAdvertImg(3, b.records);
		}
	}],
	loadAdvertImg: function(number, records){
		this.number = number;
		this.setTitle(this.otitle+"【"+ number +"】");
		if(records){
			if(records.micon.indexOf('http') > -1){
				var html = '<div width="100%" height="100%" style="text-align:right;">' +
	    					'<img number="'+ number +'" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
				   			'<img width="'+ this.width +'" height="'+ this.height +'" src="'+ records.micon +'" style="vertical-align:middle;" />' +
				   	   '</div>';
			}else {
				var html = '<div width="100%" height="100%" style="text-align:right;">' +
	    					'<img number="'+ number +'" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
				   			'<img width="'+ this.width +'" height="'+ this.height +'" src="upload/'+ records.micon +'" style="vertical-align:middle;" />' +
				   	   '</div>';
			}	    	
	    	this.body.update(html);
	    	this.data = records;
		} else {
			this.body.update("");
		}
	}
});