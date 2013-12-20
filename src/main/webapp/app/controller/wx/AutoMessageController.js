Ext.define('plat.controller.wx.AutoMessageController', {
	extend : 'Ext.app.Controller',
	views : ['wx.ArticleInfoForm', 'wx.AutoMessageGrid',
		 'wx.AutoMessageWin', 'wx.AutoMessageForm'
	],
	stores : ['wx.AutoMessageStore'],
	models : ['wx.AutoMessageModel'],
	refs : [{
    	ref: 'automessagegrid',
    	selector: 'automessagegrid'
    }, {
    	ref: 'automessagewindow',
    	selector: 'automessagewindow'
    }, {
    	ref: 'automessageform',
    	selector: 'automessageform'
    }],
	init : function () {
		this.control({
			"automessagegrid" : {
				afterrender:function(gridpanel){
					gridpanel.down('button[name=add]').on('click',function(){
                		this.addAutoMessage();
					}, this);
					/*gridpanel.down('button[name=modify]').on('click',function(){
                		this.modifyNews();
					}, this);
					gridpanel.down('button[name=delete]').on('click',function(){
                		this.deleteNews();
					}, this);*/
					gridpanel.down('button[action=search]').on('click',function(){
						var reqKey = gridpanel.down('textfield[name=reqKey]').getValue();
						var clickKey = gridpanel.down('textfield[name=clickKey]').getValue();
						gridpanel.getStore().getProxy().setExtraParam('reqKey', reqKey);
						gridpanel.getStore().getProxy().setExtraParam('clickKey', clickKey);
						gridpanel.getStore().loadPage(1);
					}, this);
					gridpanel.getStore().load();
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.modifyMessage();
            	}
			},
			
			"automessagewindow" : {
				afterrender : function (window, opts) {
            		var mask = new Ext.LoadMask(window.getEl(), {msg:"请求中,请稍等..."});
            		var options = {
        				clientValidation: true,
        				scope : this,
					    success: function(form, action) {
					    	if (action.result.success) {
						       Ext.example.msg('提示', "提交成功");
						       var record = form.getRecord();
						       window.hide();
						       form.reset();
						       Ext.ComponentQuery.query('automessagegrid')[0].getStore().reload();
						       this.getAutomessagegrid().getSelectionModel().deselectAll();
					    	} else {
					    		Ext.Msg.alert('提示','<p align="center">' + action.result.message + '</p>');
					    	}
					    	mask.hide();
					    },
					    failure: function(form, action) {
					        switch (action.failureType) {
					            case Ext.form.action.Action.CLIENT_INVALID:
						            Ext.Msg.alert('提示', '<p align="center">提交表单中包含非法字符(或必填项为空)！</p>');
					                break;
					            case Ext.form.action.Action.CONNECT_FAILURE:
					                Ext.Msg.alert('提示', '<p align="center">ajax请求失败</p>');
					                break;
					            case Ext.form.action.Action.SERVER_INVALID:
					               Ext.Msg.alert('提示', '<p align="center">' + 
					               	action.result.message + '</p>');
					       }
					       mask.hide();
					    }
        			};
            		window.down('button[name=submit]').on('click', function () {
            			var form = Ext.ComponentQuery.query('automessageform')[0];
            			mask.show(form.getEl());
            			options.url = 'automessage/addOrUpdate';
            			form.getForm().submit(options);
            		});
            		
            		window.down('button[name=cancel]').on('click', function () {
            			var Eform = window.query('automessageform')[0];
            			Eform.getForm().reset();
            			window.hide();
            			mask.hide();
            		});
            	}
			}
		})
	},
	
	/**
	 * 添加响应消息
	 * */
	addAutoMessage : function() {
		var automessageWindows = Ext.ComponentQuery.query('automessagewindow')[0];
    	if (!automessageWindows) {
    		automessageWindows = Ext.widget('automessagewindow',{
    			title:'添加响应消息'
    		}).show();
    	} else {
    		automessageWindows.setTitle('添加响应消息');
    		automessageWindows.show();
    	}
    	
    	this.getAutomessageform().getForm().reset();
	},
	
	/**
	 * 响应信息
	 * */
	showAutoMessageWin : function() {
		var _window = this.getAutomessagewindow();
		if(!_window) {
			_window = Ext.widget('automessagewindow',{
    			title:'响应消息'
    		});
		} 
		_window.show();
	},
	
	/**
	 * 修改
	 * */
	modifyMessage : function() {
		var _win = this.getAutomessagewindow();
		if(!_win) {
			_win = Ext.widget('automessagewindow');
		}
		_win.show();
		var records = this.getAutomessagegrid().getSelectionModel().getSelection();
		if(records.length > 0) {
			this.getAutomessageform().getForm().loadRecord(records[0]);
		}
	}
});