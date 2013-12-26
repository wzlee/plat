Ext.define('plat.controller.wx.WeiXinUserController', {
	extend : 'Ext.app.Controller',
	views : ['wx.WXUserPanel', 'wx.WeiXinUserGrid', 'wx.UserChangeGrid'],
	stores : ['wx.WeiXinUserStore', 'wx.UserChangeStore'],
	models : ['wx.WeiXinUserModel', 'wx.UserChangeModel'],
	refs : [{
    	ref: 'wxuserpanel',
    	selector: 'wxuserpanel'
    }, {
    	ref: 'weixinusergrid',
    	selector: 'weixinusergrid'
    }],
	init : function () {
		this.control({
			'wxuserpanel textfield[id=username]' : {
				keydown: this.keypressFn
			},
			'wxuserpanel combobox[id=status]': {
				select: this.selectStatus
			},
			'wxuserpanel button[action=search]': {
				click: this.searchWeiXinUser
			},
			'weixinusergrid' : {
				afterrender: this.afterrenderGrid,
				itemdblclick: this.itemdblclickGrid
			},
			'weixinusergrid actioncolumn[name=unbundling]' : {
				click: this.unbundlingFn
			}
		});
	},
	afterrenderGrid: function(grid, eOpts){
		this.getStore('wx.WeiXinUserStore').load();
		grid.down('textfield').on('keydown', this.keypressFn);
	},
	itemdblclickGrid: function(grid, record, item, index, e, eOpts){
		var me = this;
		Ext.Ajax.request({
			url: 'concernusers/getUserChangeByOpenid',
			method: 'get',
			params : {
				openid: record.data.wxUserToken
			},
			success: function(response, opts) {
				var retul = Ext.decode(response.responseText);
		        if(retul.success){
		        	me.getStore('wx.UserChangeStore').loadData(retul.data);
		        } else {
		        	Ext.MessageBox.alert('错误', retul.message)
		        }
		    },
		    failure: function(response, opts) {
		        Ext.example.msg(response.status);
		    }
		});
	},
	unbundlingFn: function(treegrid, colel, rowIndex, colIndex, e, record, row){
		var me = this;
		if(record.data.concern_status != 1){
			return null;
		}
		Ext.MessageBox.confirm('提示', '您确定要对该用户解绑吗？', function(but){
			if(but == 'yes'){
				Ext.Ajax.request({
	    			url: 'concernusers/unbundling',
	    			method: 'get',
	    			params : {
	    				id: record.data.id,
	    				role: 'admin'
	    			},
	    			success: function(response, opts) {
	    				var retul = Ext.decode(response.responseText);
				        if(retul.success){
				        	Ext.example.msg('','<p align="center">'+ retul.message +'</p>');
				        	me.getStore('wx.WeiXinUserStore').load();
				        } else {
				        	Ext.MessageBox.alert('错误', retul.message)
				        }
				    },
				    failure: function(response, opts) {
				        Ext.example.msg(response.status);
				    }
	    		});
			}
		});
	},
	keypressFn: function(field, e, eOpts){
		if(e.getKey() == Ext.EventObject.ENTER){
			
		}
	},
	selectStatus: function(combo, records, eOpts){
		this.searchWeiXinUser(null);
	},
	searchWeiXinUser: function(but){
		var me = this;
		var starttime = Ext.getCmp('starttime').getRawValue();
		var endtime = Ext.getCmp('endtime').getRawValue();
		if(starttime > endtime){
			return Ext.example.msg('','<p align="center">结束日期不能小于开始日期</p>');
		}
		Ext.Ajax.request({
			url: 'concernusers/getBindUser',
			method: 'get',
			params : {
				username: Ext.getCmp('weixinUser').getValue(),
				status: Ext.getCmp('status').getValue(),
				starttime: starttime,
				endtime: endtime
			},
			success: function(response, opts) {
				var retul = Ext.decode(response.responseText);
		        if(retul.success){
		        	me.getStore('wx.WeiXinUserStore').loadData(retul.data);
		        	me.getStore('wx.UserChangeStore').removeAll();
		        } else {
		        	Ext.MessageBox.alert('错误', retul.message);
		        }
		    },
		    failure: function(response, opts) {
		        Ext.example.msg(response.status);
		    }
		});
	}
	
});


