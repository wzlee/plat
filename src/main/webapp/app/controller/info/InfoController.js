Ext.define('plat.controller.info.InfoController', {
	extend : 'Ext.app.Controller',
	views : [
		'info.InfoCategoryGrid', 
		'info.InfoCategoryWin',
		'info.InfoCategoryForm',
		'info.InfoPanel',
		'info.DetailsWindow',
		'info.SendInfoWindow',
		'info.InfoGroupPanel',
		'info.SendInfoGroupWindow'
	],
	stores : ['info.InfoCategoryStore','info.InfoStore','info.InfoGroupStore'],
	models : ['info.InfoCategoryModel','info.InfoModel','info.InfoGroupModel'],
	refs : [{
    	ref : 'infocategorygrid',
    	selector : 'infocategorygrid'
    }, {
    	ref : 'infocategoryform',
    	selector : 'infocategoryform'
    }, {
    	ref : 'infocategorywin',
    	selector : 'infocategorywin'
    },{
    	ref : 'infopanel',
    	selector : 'infopanel'
    },
    {
    	ref : 'detailswindow',
    	selector : 'detailswindow'
    },
    {
    	ref : 'sendinfowindow',
    	selector : 'sendinfowindow'
    },
    {
    	ref : 'infogrouppanel',
    	selector : 'infogrouppanel'
    },
    {
    	ref : 'sendinfogroupwindow',
    	selector : 'sendinfogroupwindow'
    }
    ],
	init : function () {
	    var categorystore =new Ext.data.Store({
	        fields: ['messageType','id','remark'],
	        id:"categorystore",
	        autoLoad: false,
			proxy: {
		        type: 'ajax',
		        actionMethods: {  
					read: 'POST'
				},
		        url: 'info/queryCategory',
		        reader: {
		            type: 'json',
		            root: 'data',
		            successProperty: 'success'
		        }
		    }
	    });
		
		this.control({
			"infocategorygrid" : {
				afterrender:function(grid){
					grid.getStore().load();
					// 添加
					grid.down('button[name=add]').on('click', function () {
						this.saveCategory();
					}, this);
            	}
			}, 
			'infocategorywin' : {
				afterrender:function(me){
					var mask = new Ext.LoadMask(me.getEl(), {
						msg : '发送中,请稍等...',
						constrain : true
					});
					
					var _form = this.getInfocategoryform();
					 //遮罩
					var mask = new Ext.LoadMask(_form.getEl(), {
						msg : '表单提交中,请稍等...',
						constrain : true
					});
					var _store = this.getInfocategorygrid().getStore();
					
					 //保存
					me.down('button[name=save]').on('click', function () {
						mask.show();
						_form.getForm().submit({
							url : 'info/addOrUpdateCategory',
							clientValidation: true,
							scope : this,
						    success: function(form, action) {
						    	mask.hide();
						    	me.hide();
						    	Ext.example.msg('提示', action.result.message);
						    	_store.reload();
						    	if(categorystore.data.length!=0){
									categorystore.load();
								}
						    },
						    failure: function(form, action) {
						    	mask.hide();
						        switch (action.failureType) {
						            case Ext.form.action.Action.CLIENT_INVALID:
						                break;
						            case Ext.form.action.Action.CONNECT_FAILURE:
						                Ext.Msg.alert('提示', '<p align="center">Ajax请求失败</p>');
						                break;
						            case Ext.form.action.Action.SERVER_INVALID:
						               Ext.Msg.alert('提示', '<p align="center">' + action.result.message + '</p>');
						       }
						       mask.hide();
						    }
						});
					}, this);
					
//					 取消
					me.down('button[name=cancel]').on('click', function () {
						me.hide();
					}, this);
				}
			},
			'infopanel' : {
				afterrender:function(me){
					me.down('button[action=save]').on('click', function () {
						var window = Ext.getCmp('sendinfowindow');
	            			if(!window){
		    					window = Ext.widget('sendinfowindow',{
		    						title: '发送消息'		
		    					});    		
	    					}
	    				window.show();
					});
					
					me.down('button[action=delete]').on('click', function () {
//						Ext.Msg.alert('点击删除','删除了，ok')
//						console.log(me.down('grid').getSelectionModel().getSelection( ) [0].data.id)
						if(me.down('grid').getSelectionModel().getSelection( ).length>0){
		            		var infoids=function(){
		            			var outmenutree=[];
			            		Ext.each(me.down('grid').getSelectionModel().getSelection( ),function(rec){
			            			outmenutree.push(rec.data.id);
			            		});
			            		return outmenutree.join(',');
		            		};
//		            		console.log(infoids());
		            		
		            		Ext.Ajax.request({
							    type:'POST',
							    url: 'info/updatelist',
							    params:{'ids':infoids()},
							    success: function(response) {
							    	var result = Ext.decode(response.responseText);
							    	if(result.success){
								    	Ext.example.msg('删除消息成功!','成功将'+result.message+'条消息放入已删除！');
										Ext.data.StoreManager.lookup('sendStore').reload();
										Ext.data.StoreManager.lookup('deleteStore').reload();
							    	}else{
							    		Ext.example.msg('删除消息失败!','删除异常啦，请稍后再试');
							    	}
							    },
							    failure: function(form, action) {Ext.example.msg('删除消息失败!','删除异常啦，请稍后再试');}
							});
						}else{
							Ext.example.msg('无法删除','你还没选中任何消息记录！');
						}
					});
				}
			},
			'sendinfowindow' : {
				afterrender:function(me){
					var mask = new Ext.LoadMask(me.getEl(), {
						msg : '发送中,请稍等...',
						constrain : true
					});
					
					if(categorystore.data.length!=0){
						me.down('form').down('combobox[id=categorycombobox]').store=categorystore;
					}else{
						categorystore.load();
						me.down('form').down('combobox[id=categorycombobox]').store=categorystore;
					}
					
					me.down('button[action=submit]').on('click', function () {
						mask.show();
						me.down('form').submit({
				    		clientValidation: true,
						    url: 'info/save',
						    params:{/*'username':me.down('form').down('combobox[id=receviercombobox]').getValue(),*/
						    		'messageclassid':me.down('form').down('combobox[id=categorycombobox]').getValue()},
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
									Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
									me.close();
									Ext.data.StoreManager.lookup('sendStore').reload();
									Ext.data.StoreManager.lookup('deleteStore').reload();
						    	}else{
						    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
//						    	Ext.example.msg('','<p align="center">表格填写未完成！</p>');
						        switch (action.failureType) {
						            case Ext.form.action.Action.CLIENT_INVALID:
						                Ext.Msg.alert('Failure', '提交表单中包含非法字符(或必填项为空)！');
						                break;
						            case Ext.form.action.Action.CONNECT_FAILURE:
						                Ext.Msg.alert('Failure', 'Ajax请求失败');
						                break;
						            case Ext.form.action.Action.SERVER_INVALID:
						               Ext.Msg.alert('Failure', action.result.message);
						       }
						    }
            			});
					});
				}
			},
			'infogrouppanel' : {
				afterrender:function(me){
					me.down('button[action=save]').on('click', function () {
						var window = Ext.getCmp('sendinfogroupwindow');
	            			if(!window){
		    					window = Ext.widget('sendinfogroupwindow',{
		    						title: '发送消息'		
		    					});    		
	    					}
	    				window.show();
					});
					
					me.down('button[action=delete]').on('click', function () {
						if(me.down('grid').getSelectionModel().getSelection( ).length>0){
		            		var infoids=function(){
		            			var outmenutree=[];
			            		Ext.each(me.down('grid').getSelectionModel().getSelection( ),function(rec){
			            			outmenutree.push(rec.data.id);
			            		});
			            		return outmenutree.join(',');
		            		};
		            		
		            		Ext.Ajax.request({
							    type:'POST',
							    url: 'info/updategrouplist',
							    params:{'ids':infoids()},
							    success: function(response) {
							    	var result = Ext.decode(response.responseText);
							    	if(result.success){
								    	Ext.example.msg('删除消息成功!','成功将'+result.message+'条消息放入已删除！');
										Ext.data.StoreManager.lookup('sendGroupStore').reload();
										Ext.data.StoreManager.lookup('deleteGroupStore').reload();
							    	}else{
							    		Ext.example.msg('删除消息失败!','删除异常啦，请稍后再试');
							    	}
							    },
							    failure: function(form, action) {Ext.example.msg('删除消息失败!','删除异常啦，请稍后再试');}
							});
						}else{
							Ext.example.msg('无法删除','你还没选中任何消息记录！');
						}
					});
				}
			},
			'sendinfogroupwindow' : {
				afterrender:function(me){
					var mask = new Ext.LoadMask(me.getEl(), {
						msg : '发送中,请稍等...',
						constrain : true
					});
					
					if(categorystore.data.length!=0){
						me.down('form').down('combobox[id=categorycombobox]').store=categorystore;
					}else{
						categorystore.load();
						me.down('form').down('combobox[id=categorycombobox]').store=categorystore;
					}
					
					me.down('button[action=submit]').on('click', function () {
						mask.show();
						me.down('form').submit({
				    		clientValidation: true,
						    url: 'info/savegroup',
						    params:{/*'username':me.down('form').down('combobox[id=receviercombobox]').getValue(),*/
						    		'usertype':me.down('form').down('combobox[id=receviercombobox]').getValue(),
						    		'messageclassid':me.down('form').down('combobox[id=categorycombobox]').getValue()},
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
									Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
									me.close();
									Ext.data.StoreManager.lookup('sendGroupStore').reload();
									Ext.data.StoreManager.lookup('deleteGroupStore').reload();
						    	}else{
						    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
//						    	Ext.example.msg('','<p align="center">表格填写未完成！</p>');
						        switch (action.failureType) {
						            case Ext.form.action.Action.CLIENT_INVALID:
						                Ext.Msg.alert('提示', '提交表单中包含非法字符(或必填项为空)！');
						                break;
						            case Ext.form.action.Action.CONNECT_FAILURE:
						                Ext.Msg.alert('提示', 'Ajax请求失败');
						                break;
						            case Ext.form.action.Action.SERVER_INVALID:
						               Ext.Msg.alert('提示', action.result.message);
						       }
						    }
            			});
					});
				}
			}
		})
	},
	
	modifyCategory : function () {	//修改政策指引类别
		var _grid = this.getPolicycategorygrid();
		var _records = _grid.getSelectionModel().getSelection();
    	if (_records.length < 1) {
    		Ext.Msg.show({
			    title: '提示',
			    msg: '请选择一条记录',
			    width: 180,
			    buttons: Ext.Msg.OK,
			    icon: Ext.MessageBox.INFO
			});
			return;
    	}
		var _window = this.getPolicycategorywindow();
		if (!_window) {
			_window = Ext.create('plat.view.policy.PolicyCategoryWin');
		}
		_window.show();
		
		this.getPolicycategoryform().getForm().loadRecord(_records[0]);
	},
	
	saveCategory : function () {	//添加消息类别
		var _window = this.getInfocategorywin();
		if (!_window) {
			_window = Ext.create('plat.view.info.InfoCategoryWin');
		}
		_window.show();
		var _form = this.getInfocategoryform();
		_form.getForm().reset();
//		_form.getForm().findField('type').show();
//		_form.getForm().findField('display_type').hide();
	}
});