Ext.define('plat.controller.flat.FlatController', {
	extend : 'Ext.app.Controller',
	views : ['flat.FlatGrid', 'flat.FlatWin', 'flat.FlatForm', 'flat.FlatInfoDisplay'],
	stores : ['flat.FlatStore'],
	models : ['flat.FlatModel'],
	refs : [{
    	ref: 'flatgrid',
    	selector: 'flatgrid'
    }, {
    	ref: 'flatwindow',
    	selector: 'flatwindow'
    }, {
    	ref: 'flatform',
    	selector: 'flatform'
    }, {
    	ref: 'flatinfodisplay',
    	selector: 'flatinfodisplay'
    }],
	init : function () {
		this.control({
			'flatgrid' : {
				afterrender:function(gridpanel){
					/*gridpanel.down('button[name=add]').on('click',function(){
                		this.addPlatInfo();
					}, this);*/
					gridpanel.getStore().load();
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.showFlatInfo(record);
            	}
			}, 
			'flatform' : {
				afterrender : function (form) {
					/* 窗口平台信息表单渲染完成以后,进行数据加载*/
					var store = Ext.create('plat.store.flat.FlatStore');
					store.load({
						scope: this,
					    callback: function(records, operation, success) {
					        if (records.length > 0) {
						        form.getForm().loadRecord(records[0]);
						        form.getForm().findField('flatName2').setValue(records[0].data.flatName);
					        }
					    }
					});
					
					var mask = new Ext.LoadMask(form.getEl(), {
						msg : '表单提交中,请稍等...',
						constrain : true
					});
					
					/* 表单按钮点击事件*/
					form.down('button[text="保存"]').on('click', function () {
						/* 提交表单*/
						form.getForm().submit({
							url : 'flat/addOrUpdate',
							clientValidation: true,
						    success: function(form, action) {
						    	Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
//						    	mask.hide();
						    },
						    failure: function(form, action) {
						        switch (action.failureType) {
						            case Ext.form.action.Action.CLIENT_INVALID:
						                break;
						            case Ext.form.action.Action.CONNECT_FAILURE:
						                Ext.Msg.alert('提示', '<p align="center">Ajax请求失败</p>');
						                break;
						            case Ext.form.action.Action.SERVER_INVALID:
						               Ext.Msg.alert('提示', '<p align="center">' + 
						               	action.result.msg + '</p>');
						       }
						       mask.hide();
						    }
						});
					}, this);
					
					form.down('button[text="还原"]').on('click', function () {
						var store = Ext.data.StoreManager.lookup('flatstore');
						form.getForm().loadRecord(store.getAt(0));
					}, this);
				}
				
			},
			'flatinfodisplay' : {
				afterrender : function (_form) {
					var _flatgrid = this.getFlatgrid();
					_flatgrid.getS
				}
			}
		});
	},
	
	addPlatInfo : function () {    //新增平台信息(测试用,正式发布时候去掉)
		var flatWindow = this.getFlatwindow();
		if (!flatWindow) {
    		flatWindow = Ext.widget('flatwindow').show();
    	} else {
    		flatWindow.show();
    	}
	},
	
	showFlatInfo : function (record) {    //显示子窗口平台信息详细
		var _flatWindow = this.getFlatwindow();
		if (!_flatWindow) {
    		_flatWindow = Ext.widget('flatwindow').show();
    	} else {
    		_flatWindow.show();
    	}
    	var _flatinfodisplay = this.getFlatinfodisplay();
    	_flatinfodisplay.getForm().loadRecord(record);
    	_flatWindow.setTitle(record.data.flatName);
	}
});