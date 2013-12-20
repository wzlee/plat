Ext.define('plat.controller.flat.FlatManagerController', {
	extend : 'Ext.app.Controller',
	views : ['flat.FlatManagerGrid', 'flat.FlatManagerForm', 'flat.FlatManagerWin',
		'flat.FlatManagerTab', 'flat.FlatUserGrid'
	],
	stores : ['flat.FlatManagerStore', 'flat.FlatStore', 'flat.FlatUserStore'],
	models : ['flat.FlatManagerModel'],
	refs : [{
    	ref: 'flatmanagergrid',
    	selector: 'flatmanagergrid'
    }, {
    	ref: 'flatmanagerform',
    	selector: 'flatmanagerform'
    }, {
    	ref: 'flatmanagerwindow',
    	selector: 'flatmanagerwindow'
    }, {
    	ref: 'flatusergrid',
    	selector: 'flatusergrid'
    }],
	init : function () {
		this.control({
			'flatmanagergrid' : {
				afterrender : function (grid) {
					grid.down('button[name=search]').on('click', function () {
						var flatId = grid.down('combo[name=flatId]').getValue();
						grid.getStore().getProxy().setExtraParam('flatId', flatId);
						grid.getStore().loadPage(1);
					}, this);
					
					grid.down('button[name=add]').on('click', function () {
						var _window = this.getFlatmanagerwindow();
						if (!_window)　{
							_window = Ext.widget('flatmanagerwindow');
						}
						_window.show();
					}, this);
					
					var _flatstore = Ext.create('plat.store.flat.FlatStore');
					_flatstore.load({
					    scope: this,
					    callback: function(records, operation, success) {
					    	grid.down('combo').getStore().add(records);
					    }
					});
					
					grid.getStore().load();
				}
			},
			'flatmanagerwindow' : {
				afterrender : function (me) {
					var _form = this.getFlatmanagerform();
					
					/* 窗口平台combo加载数据*/
					var _flatstore = Ext.data.StoreManager.lookup('flatstore');
					_form.getForm().findField('flatId').getStore().add(_flatstore.getRange());
					
					var mask = new Ext.LoadMask(_form.getEl(), {
						msg : '表单提交中,请稍等...',
						constrain : true
					});
					/* 提交表单*/
					me.down('button[name=save]').on('click', function () {
						mask.show();
						_form.getForm().submit({
							url : 'manage/save',
							clientValidation: true,
							scope : this,
							params : { 
								password : Ext.ux.MD5.hex_md5("123456"),
								passwordvalidate : Ext.ux.MD5.hex_md5("123456"),
								roleId : 1
							},
						    success: function(form, action) {
						    	mask.hide();
						    	me.hide();
						    	Ext.Msg.alert('提示', action.result.message);
						    	this.getFlatmanagergrid().getStore().reload();
						    },
						    failure: function(form, action) {
						        switch (action.failureType) {
						            case Ext.form.action.Action.CLIENT_INVALID:
						                break;
						            case Ext.form.action.Action.CONNECT_FAILURE:
						                Ext.Msg.alert('提示', 'Ajax请求失败');
						                break;
						            case Ext.form.action.Action.SERVER_INVALID:
						               Ext.Msg.alert('提示', action.result.message);
						       }
						       mask.hide();
						    }
						});
					}, this);
					
					me.down('button[name=cancel]').on('click', function () {
						me.hide();
					}, this);
				},
				hide : function (me) {
					var _form = this.getFlatmanagerform();
					_form.getForm().reset();    //重置表单
				}
			},
			'flatusergrid' : {
				afterrender : function (me) {
					/* 窗口平台combo加载数据*/
					var _flatstore = Ext.data.StoreManager.lookup('flatstore');
					me.down('combo[name=flatId]').getStore().add(_flatstore.getRange());
					
					/* 点击搜索*/
					me.down('button[name=search]').on('click', function () {
						var flatId = me.down('combo[name=flatId]').getValue();
						var roleId = me.down('combo[name=userStatus]').getValue();
						me.getStore().getProxy().setExtraParam('flatId', flatId);
						me.getStore().getProxy().setExtraParam('userStatus', roleId);
						me.getStore().loadPage(1);
					}, this);
				}
			}
		});
	}
	
});