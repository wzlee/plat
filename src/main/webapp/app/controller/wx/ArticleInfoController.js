Ext.define('plat.controller.wx.ArticleInfoController', {
	extend : 'Ext.app.Controller',
	views : ['wx.ArticleInfoGrid', 'wx.ArticleInfoWin', 'wx.ArticleInfoForm', 
		 'wx.AutoMessageWin', 'wx.AutoMessage4ReadGrid', 
		'public.UploadWin', 'public.UploadForm'
	],
	stores : ['wx.ArticleInfoStore', 'wx.AutoMessageStore'],
	models : ['wx.ArticleInfoModel', 'wx.AutoMessageModel'],
	refs : [{
    	ref: 'articleinfogrid',
    	selector: 'articleinfogrid'
    }, {
    	ref: 'articleinfowindow',
    	selector: 'articleinfowindow'
    }, {
    	ref: 'articleinfoform',
    	selector: 'articleinfoform'
    }, {
    	ref: 'automessage4readgrid',
    	selector: 'automessage4readgrid'
    }, {
    	ref: 'automessagewindow',
    	selector: 'automessagewindow'
    }, {
    	ref: 'automessageform',
    	selector: 'automessageform'
    }],
	init : function () {
		this.control({
			"articleinfogrid" : {
				afterrender:function(gridpanel){
					gridpanel.down('button[name=add]').on('click',function(){
                		this.addNews();
					}, this);
					/*gridpanel.down('button[name=modify]').on('click',function(){
                		this.modifyNews();
					}, this);
					gridpanel.down('button[name=delete]').on('click',function(){
                		this.deleteNews();
					}, this);*/
					gridpanel.down('button[action=search]').on('click',function(){
						var title = gridpanel.down('textfield[name=title]').getValue();
						gridpanel.getStore().getProxy().setExtraParam('title', title);
						gridpanel.getStore().loadPage(1);
					}, this);
					gridpanel.getStore().load();
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.modifyNews();
            	}
			},
			
			"automessage4readgrid" : {
				afterrender:function(gridpanel){
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
            		
            	}
			},
			
			"articleinfoform" : {
				afterrender : function (form) {
            		form.down('button[name=select]').on('click', function () {
            			this.selectPic();
            		}, this);
            		
            		form.down('button[name=selMsg]').on('click', function () {
            			this.showAutoMessageWin();
            		}, this);
            	}
			},
			"articleinfowindow" : {
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
						       Ext.ComponentQuery.query('articleinfogrid')[0].getStore().reload();
						       this.getArticleinfogrid().getSelectionModel().deselectAll();
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
					                Ext.Msg.alert('提示', '<p align="center">Ajax请求失败</p>');
					                break;
					            case Ext.form.action.Action.SERVER_INVALID:
					               Ext.Msg.alert('提示', '<p align="center">' + 
					               	action.result.message + '</p>');
					       }
					       mask.hide();
					    }
        			};
            		window.down('button[name=submit]').on('click', function () {
            			var form = Ext.ComponentQuery.query('articleinfoform')[0];
            			mask.show(form.getEl());
            			options.url = 'articleinfo/addOrUpdate';
            			form.getForm().submit(options);
            		});
            		
            		window.down('button[name=cancel]').on('click', function () {
            			var Eform = window.query('articleinfoform')[0];
            			Eform.getForm().reset();
            			window.hide();
            			mask.hide();
            		});
            		
            	}
			},
			"uploadwindow[name=articleinfo]" : {
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
			},
			
			"automessagewindow" : {
				afterrender : function (window, opts) {
            		
            		window.down('button[name=ok]').on('click', function () {
            			this.selectAutoMessage();
            		}, this);
            		
            		window.down('button[name=cancel]').on('click', function () {
            			window.hide();
            		}, this);
            		
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
	
	addNews : function () {
    	var articleinfoWindows = Ext.ComponentQuery.query('articleinfowindow')[0];
    	if (!articleinfoWindows) {
    		articleinfoWindows = Ext.widget('articleinfowindow',{
    			title:'添加消息'
    		}).show();
    	} else {
    		articleinfoWindows.setTitle('添加消息');
    		articleinfoWindows.show();
    	}
    	
    	this.getArticleinfoform().getForm().reset();
    },
    modifyNews : function () {	//修改新闻表单
//    	var grid = Ext.ComponentQuery.query('newsgrid')[0];
    	var grid = this.getArticleinfogrid();
    	var records = grid.getSelectionModel().getSelection();
    	if (records.length < 1) {
    		Ext.Msg.show({
			    title: '提示',
			    msg: '请选择一条记录',
			    width: 180,
			    buttons: Ext.Msg.OK,
			    icon: Ext.MessageBox.INFO
			});
			return;
    	}
//    	var newsWindows = Ext.ComponentQuery.query('newswindow')[0];
    	var articleinfoWindows = this.getArticleinfowindow();
    	if (!articleinfoWindows) {
    		articleinfoWindows = Ext.widget('articleinfowindow',{
    			title:'修改消息'
    		}).show();
    	} else {
    		articleinfoWindows.setTitle('修改消息');
    		articleinfoWindows.show();
    	}
//    	var Eform = Ext.ComponentQuery.query('newsform')[0];
    	var Eform = this.getArticleinfoform();
    	Eform.getForm().loadRecord(records[0]);
    	var EoriginalPic = Eform.query('textfield[name=originalPic]')[0];
		if (records[0].data.picUrl) {	//当新闻图片存在时，隐藏输入框做标记
			EoriginalPic.setValue(records[0].data.picUrl);
		}
    },
	selectPic : function () {
		var picWindow = Ext.ComponentQuery.query('uploadwindow[name=articleinfo]')[0];
    	if (!picWindow) {
    		Ext.widget('uploadwindow',{
    			title : '上传',
    			name : 'articleinfo'
    		}).show();
    	} else {
    		picWindow.show();
    	}
	},
	deleteNews : function () {
		var grid = Ext.ComponentQuery.query('articleinfogrid')[0];
    	var records = grid.getSelectionModel().getSelection();
    	if (records.length < 1) {
    		Ext.Msg.show({
			    title: '提示',
			    msg: '请选择一条记录',
			    width: 180,
			    buttons: Ext.Msg.OK,
			    icon: Ext.MessageBox.INFO
			});
			return;
    	}
    	Ext.MessageBox.confirm('警告','确定删除该新闻吗?',function(btn){
    		if(btn == 'yes'){
		    	grid.getStore().remove(records[0]);
    		}
    	});
	},
	uplodImage : function (mask) {
		var Ewindow = Ext.ComponentQuery.query('uploadwindow[name=articleinfo]')[0];
		var Eform = Ewindow.query('uploadform')[0];
		mask.show();
		Eform.getForm().submit({
			url : 'public/uploadFile',
			clientValidation: true,
		    success: function(form, action) {
		    	if (action.result.success) {
			       var EnewsForm = Ext.ComponentQuery.query('articleinfoform')[0];
			       var Epicture = EnewsForm.down('textfield[name=picUrl]');
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
	
	/**
	 * 选择响应信息
	 * */
	showAutoMessageWin : function() {
		var _window = this.getAutomessagewindow();
		if(!_window) {
    		_window = Ext.widget({
    			xtype : 'automessagewindow',
    			width : 600,
    			height : 320,
    			title : '选择响应信息',
    			buttons : [{
			    	text : '确定',
			    	name : 'ok'
			    }, {
			    	text : '取消',
			    	name : 'cancel'
			    }],
    			items : {
    				xtype : 'automessage4readgrid'
    			}
    		});
		} 
		_window.show();
	},
	
	/**
	 * 选取响应信息
	 * */
	selectAutoMessage : function() {
		var _form = this.getArticleinfoform();
		var records = this.getAutomessage4readgrid().getSelectionModel().getSelection();
		if(records.length > 0) {
			_form.getForm().findField('autoMessageId').setValue(records[0].data.id);
			_form.getForm().findField('reqKey').setValue(records[0].data.reqKey);
			_form.getForm().findField('clickKey').setValue(records[0].data.clickKey);
			_form.getForm().findField('content').setValue(records[0].data.content);
		}
		this.getAutomessagewindow().hide();
	}	
});