Ext.define('plat.controller.mobileapp.MobileAppController', {
	extend : 'Ext.app.Controller',
	views : [
		'mobileapp.AppMessageGrid', 'mobileapp.AppMessageForm', 'mobileapp.AppMessageWin',
		'mobileapp.AppMessageDisplayWin', 'mobileapp.AppMessageDisplayForm',
		'public.UploadWin', 'public.UploadForm'
	],
	stores : ['mobileapp.AppMessageStore'],
	models : ['mobileapp.AppMessageModel'],
	refs : [{
    	ref: 'appmessagegrid',
    	selector: 'appmessagegrid'
    }, {
    	ref: 'appmessagewindow',
    	selector: 'appmessagewindow'
    }, {
    	ref: 'appmessageform',
    	selector: 'appmessageform'
    }, {
    	ref: 'appmessagedisplaywindow',
    	selector: 'appmessagedisplaywindow'
    }, {
    	ref: 'appmessagedisplayform',
    	selector: 'appmessagedisplayform'
    }, {
    	ref: 'uploadwindow',
    	selector: 'uploadwindow'
    }, {
    	ref: 'uploadform',
    	selector: 'uploadform'
    }],
	init : function () {
		this.control({
			"appmessagegrid" : {
				afterrender:function(grid){
					console.log("AppMessageGrid was initialized!!!");
					
					grid.down('button[name=add]').on('click', function () {
						this.openMessageWindow();
					}, this);
					
					grid.down('button[name=remove]').on('click', function () {
						var selects = grid.getSelectionModel().getSelection();
						if(selects.length > 0) {
							Ext.MessageBox.confirm("提示信息","是否删除选中的历史消息",
								function(e) {
									if (e == 'yes') {
										var idStr = '';
										Ext.each(selects, function(item, index, items) {
											idStr = idStr + item.data.id + ",";
										});
										Ext.Ajax.request({
											type : 'post',
											url : 'mobileApp/removeMessages',
											params : {
												'idStr' : idStr.substring(0, idStr.length - 1)
											},
											success : function(
													response) {
												var result = Ext.decode(response.responseText);
												if (result.success) {
													Ext.example.msg('删除成功!',result.message);
													grid.getStore().reload();
												} else {
													Ext.example.msg('删除失败!',result.message);
												}
											},
											failure : function(form, action) {
												Ext.example.msg('删除失败','删除失败');
											}
										});
									}
							});
						} else {
							Ext.example.msg('提示', '<p align="center">请先选择要删除的历史消息</p>');
						}
					}, this);
					
					grid.getStore().load();
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		var _win = this.getAppmessagedisplaywindow();
            		if(!_win) {
            			_win = Ext.create('plat.view.mobileapp.AppMessageDisplayWin');
            		}
        			_win.show();
        			this.getAppmessagedisplayform().getForm().loadRecord(record);
            	}
			}, 
			
			"appmessageform" : {
				afterrender : function(me) {
					/* 上传文件*/
					me.down('button[name=select]').on('click', function () {
						this.selectPic();
					}, this);
				}
			},
			
			'appmessagewindow' : {
				afterrender:function(me){
					/* 保存*/
					me.down('button[name=save]').on('click', function () {
						this.submitMessage();
					}, this);
					
					me.down('button[name=cancel]').on('click', function () {
						me.hide();
					}, this);
				}
			},
			
			'appmessagedisplaywindow' : {
				afterrender:function(me){
					/* 保存*/
					me.down('button[name=close]').on('click', function () {
						me.hide();
					}, this);
				}
			},
			
			'appmessagedisplayform' : {
				afterrender:function(me){
					console.log(123);
				}
			},
			
			'uploadwindow[name=appMessage]' : {
				afterrender : function (window, opts) {
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '上传中,请稍等...',
						constrain : true
					});
					window.down('button[name=submit]').on('click', function () {
            			this.uplodImage(mask);
            		}, this);
            		window.down('button[name=cancel]').on('click', function () {
            			window.hide();
            		}, this);
				}
			}
		})
	},
	
	/* 打开填写消息窗口*/
	openMessageWindow : function() {
		var _window = this.getAppmessagewindow();
		if (!_window) {
			_window = Ext.create('plat.view.mobileapp.AppMessageWin');
		}
		_window.show();
		var _form = this.getAppmessageform();
		_form.getForm().reset();
	},
	
	/* 选择上传文件*/
	selectPic : function () {
		var picWindow = this.getUploadwindow();
    	if (!picWindow) {
    		Ext.widget('uploadwindow',{
	    		title : '上传',
	    		name : 'appMessage'
	    	}).show();
    	}else{
    		picWindow.show();
    	}
	},
	
	/* 提交文件上传表单*/
	uplodImage : function (mask) {
		var Ewindow = this.getUploadwindow();
		var Eform = this.getUploadform();
		mask.show();
		Eform.getForm().submit({
			url : 'public/uploadFile',
			clientValidation: true,
			scope : this,
		    success: function(form, action) {
		    	if (action.result.success) {
				       var Epicture = this.getAppmessageform().down('textfield[name=picture]');
				       Epicture.setValue(action.result.message);
			       form.reset();
			       Ewindow.hide();
		    	} else {
		    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
		    	}
		    	mask.hide();
		    	Ewindow.hide();
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
		       Ewindow.hide();
		    }
		});
	},
	
	/* 提交消息表单*/
	submitMessage : function() {  
		var _form = this.getAppmessageform();
		/* 遮罩*/
		var mask = new Ext.LoadMask(_form.getEl(), {
			msg : '表单提交中,请稍等...',
			constrain : true
		});
		/* 提交表单*/
		_form.getForm().submit({
			url : 'mobileApp/message',
			clientValidation: true,
			scope : this,
		    success: function(form, action) {
	    		this.getAppmessagewindow().hide();
	    		this.getAppmessagegrid().getStore().reload();
//	    		this.getAppmessagegrid().getSelectionModel().deselectAll();
		    	Ext.example.msg('提示', action.result.message);
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
	}
});

/* 转换时间格式*/
function unix_to_datetime(unix) {
    var now = new Date(parseInt(unix) * 1000);
    return now.toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}