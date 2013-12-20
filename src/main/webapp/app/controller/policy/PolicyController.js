Ext.define('plat.controller.policy.PolicyController', {
	extend : 'Ext.app.Controller',
	views : [
		'policy.PolicyCategoryGrid', 
		'policy.PolicyCategoryWin',
		'policy.PolicyCategoryForm',
		'policy.PolicyGrid',
		'policy.PolicyWin',
		'policy.PolicyForm',
		'public.UploadWin',
		'public.UploadFinancialWin',
		'public.UploadForm',
		'public.UploadFinancialForm',
		'policy.FinancialReportingGrid',
		'policy.AddReportWindow',
		'policy.RuleGrid',
		'policy.RulePanel',
		'policy.RuleReportingGrid',
		'policy.FinancialWin',
		'policy.FinancialForm',
		'policy.FinancialCategoryGrid',
		'policy.FinancialCategoryWin',
		'policy.FinancialCategoryForm'
	],
	stores : ['policy.PolicyCategoryStore', 'policy.PolicyCategoryStore1','policy.PolicyStore' ,'policy.PolicyReportingStore',
			  'policy.ReportCategoryStore','policy.AddrReportPolicyStore','policy.FiltrateRuleStore'],
	models : ['policy.PolicyCategoryModel', 'policy.PolicyModel' ,'policy.FinacialReportingModel','policy.FiltrateRuleModel'],
	refs : [{
    	ref: 'policycategorygrid',
    	selector: 'policycategorygrid'
    }, {
    	ref: 'policycategoryform',
    	selector: 'policycategoryform'
    }, {
    	ref: 'policycategorywindow',
    	selector: 'policycategorywindow'
    }, {
    	ref: 'policygrid',
    	selector: 'policygrid'
    }, {
    	ref: 'policyform',
    	selector: 'policyform'
    }, {
    	ref: 'policywindow',
    	selector: 'policywindow'
    }, {
    	ref: 'uploadwindow',
    	selector: 'uploadwindow'
    }, {
    	ref: 'uploadfinancialwin',
    	selector: 'uploadfinancialwin'
    },{
    	ref: 'uploadform',
    	selector: 'uploadform'
    },{
    	ref: 'uploadfinancialform',
    	selector: 'uploadfinancialform'
    },{
    	ref: 'financialreportinggrid',
    	selector: 'financialreportinggrid'
    }, {
    	ref: 'rulepanel',
    	selector: 'rulepanel'
    }, {
    	ref: 'rulegrid',
    	selector: 'rulegrid'
    },{
    	ref: 'rulereportinggrid',
    	selector: 'rulereportinggrid'
    },{
    	ref: 'financialwin',
    	selector: 'financialwin'
    },{
    	ref: 'financialform',
    	selector: 'financialform'
    },{
    	ref:'financialcategorygrid',
    	selector:'financialcategorygrid'
    },{
    	ref:'financialcategorywindow',
    	selector:'financialcategorywindow'
    },{
    	ref:'financialcategoryform',
    	selector:'financialcategoryform'
    }],
	init : function () {
		this.control({
			"policycategorygrid" : {
				afterrender:function(grid){
					/* 添加*/
					grid.down('button[name=add]').on('click', function () {
						this.saveCategory(0);
					}, this);
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		
            	}
			}, 
			'policycategorywindow' : {
				afterrender:function(me){
					var _form = this.getPolicycategoryform();
					/* 遮罩*/
					var mask = new Ext.LoadMask(_form.getEl(), {
						msg : '表单提交中,请稍等...',
						constrain : true
					});
					var _store = this.getPolicycategorygrid().getStore();
					
					/* 保存*/
					me.down('button[name=save]').on('click', function () {
						_form.getForm().submit({
							url : 'policy/addOrUpdateCategory',
							clientValidation: true,
							scope : this,
						    success: function(form, action) {
						    	me.hide();
//						    	Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
						    	Ext.example.msg('提示', action.result.message);
						    	_store.reload();
						    	this.getPolicycategorygrid().getSelectionModel().deselectAll();
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
					
					/* 取消*/
					me.down('button[name=cancel]').on('click', function () {
						me.hide();
					}, this);
				}
			},
			/*******************************专项资金的类别管理***********************************/
			"financialcategorygrid" : {
				afterrender:function(grid){
					/* 添加*/
					grid.down('button[name=add]').on('click', function () {
						this.saveCategory(1);
					}, this);
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		
            	}
			}, 
			'financialcategorywindow' : {
				afterrender:function(me){
					var _form = this.getFinancialcategoryform();
					/* 遮罩*/
					var mask = new Ext.LoadMask(_form.getEl(), {
						msg : '表单提交中,请稍等...',
						constrain : true
					});
					var _store = this.getFinancialcategorygrid().getStore();
					
					/* 保存*/
					me.down('button[name=save]').on('click', function () {
						_form.getForm().submit({
							url : 'policy/addOrUpdateCategory',
							clientValidation: true,
							scope : this,
						    success: function(form, action) {
						    	me.hide();
//						    	Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
						    	Ext.example.msg('提示', action.result.message);
						    	_store.reload();
						    	this.getFinancialcategorygrid().getSelectionModel().deselectAll();
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
					
					/* 取消*/
					me.down('button[name=cancel]').on('click', function () {
						me.hide();
					}, this);
				}
			},
			/*******************************end***********************************/
			'policygrid' : {
				afterrender:function(grid){
					/* 数据加载*/
					var _store = grid.getStore();
					_store.on('beforeload', function(store) {
							Ext.apply(_store.proxy.extraParams, {
								type:0
							});
						});
					_store.load();
					
					/* 添加*/
					grid.down('button[name=add]').on('click', function () {
						this.addPolicy(0,'添加政策信息',null);
					}, this);
					
					/* 搜索*/
					grid.down('button[name=search]').on('click', function () {
						_store.getProxy().setExtraParam('title', grid.down('textfield[name=title]').getValue());
						_store.getProxy().setExtraParam('pcid', grid.down('treecombo[name=pcid]').getValue());
						_store.loadPage(1);
					}, this);
					
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.addPolicy(0,'修改政策信息',record);
            	}
			},
			'policywindow' : {
				afterrender:function(me){
					/* 保存*/
					me.down('button[name=save]').on('click', function () {
						this.savePolicy(0);
					}, this);
					
					me.down('button[name=cancel]').on('click', function () {
						me.hide();
					}, this);
				}
			},
			'policyform' : {
				afterrender:function(me){
					/* 上传文件*/
					me.down('button[name=select]').on('click', function () {
						this.selectPic();
					}, this);
				}
			},
			'financialwin' : {
				afterrender:function(me){
					var textareas = me.query('textarea');
						Ext.each(textareas, function (textarea, index, countriesItSelf) {
							var editor = textarea.findParentByType('panel').getKindeditor();
							if (editor) {
								editor.sync();
							}
						});
					/* 保存*/
					me.down('button[name=save]').on('click', function () {
						var textareas = me.query('textarea');
						Ext.each(textareas, function (textarea, index, countriesItSelf) {
							var editor = textarea.findParentByType('panel').getKindeditor();
							if (editor) {
								editor.sync();
							}
						});
						this.savePolicy(1);
					}, this);
					
					me.down('button[name=cancel]').on('click', function () {
						me.hide();
					}, this);
				}
			},
			'financialform' : {
				afterrender:function(me){
					/* 上传文件*/
					me.down('button[name=select]').on('click', function () {
						this.selectFinancialPic();
					}, this);
				}
			},
			'uploadwindow[name=policy]' : {
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
			'uploadfinancialwin[name=financial]' : {
				afterrender : function (window, opts) {
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '上传中,请稍等...',
						constrain : true
					});
					window.down('button[name=submit]').on('click', function () {
            			this.uploadFinancialImage(mask);
            		}, this);
            		window.down('button[name=cancel]').on('click', function () {
            			window.hide();
            		}, this);
				}
			},
			'financialreportinggrid':{
				afterrender:function(grid){
					/* 数据加载*/
					var _store = grid.getStore();
					_store.on('beforeload', function(store) {
							Ext.apply(_store.proxy.extraParams, {
								type:1
							});
						});
					_store.load();
					/* 搜索*/
					grid.down('button[name=search]').on('click', function () {
						_store.getProxy().setExtraParam('pcid', grid.down('treecombo[name=pcid]').getValue());
						_store.loadPage(1);
					}, this);
					
					//点击新增按钮事件
					grid.down('button[name=add]').on('click', function () {
						this.addPolicy(1,'添加资助信息',null);
					}, this);
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.addPolicy(1,'修改资助信息',record);
            	}
            	
			},
//			'addreportwindow' : {
//				afterrender:function(window){
//					var me = this;
//					/*添加*/
//					window.down('button[action=addReport]').on('click', function() {
//						this.savePolicy('reporting');
//					}, this);	
//	            }
//			},
			'rulegrid':{
				select:function(grid, record, item, index, e, eOpts){
					var rGrid = Ext.ComponentQuery.query('rulereportinggrid')[0];
            		rGrid.down('textfield[name=ruleId]').setValue(record.data.ruleId)
            		Ext.Ajax.request({
					    url: 'policy/findeSelectRule',
					    params: {
					        ruleId: record.data.ruleId
					    },
					    success: function(response){
					        var text = Ext.decode(response.responseText);
					        rGrid.getSelectionModel().deselectAll();
					        Ext.each(text,function(rule,index,text){
					        	rGrid.getSelectionModel().select(rGrid.getStore().getById(rule.policy.id), true);	 
					        });
					    }
					});
            	}
			},
			'rulereportinggrid':{
				afterrender:function(grid){
					/* 数据加载*/
					var _store = grid.getStore();
					_store.on('beforeload', function(store) {
							Ext.apply(_store.proxy.extraParams, {
								type:1
							});
						});
					_store.load({
						
							callback:function(r){//默认选中第一行
	            				var rGrid = Ext.ComponentQuery.query('rulegrid')[0];
								rGrid.getSelectionModel().select(0,true);
            			}
        			});
					/* 保存 */
					grid.down('button[action=save]').on('click',
							function() {
								var selectionModel = grid.getSelectionModel()
										.getSelection();
								var reportingIds = '';
								
								var ruleId =grid.down('textfield[name=ruleId]').getValue();
								
								for (var i = 0; i < selectionModel.length; i++) {
									reportingIds += selectionModel[i].data.id+ ',';
								}
								if (selectionModel.length > 0) {
									var myMask = new Ext.LoadMask(grid, {
											msg    : "正在操作,请稍后...",
											msgCls : 'z-index:10000;'
									});
									myMask.show();
								
									Ext.Ajax.request({
										type : 'post',
										url : 'policy/addReporingRule',
										params : {
											'ruleId' : ruleId,
											'reportingIds' : reportingIds
										},
										success : function(response) {
											var result = Ext
													.decode(response.responseText);
											if (result.success) {
												myMask.hide();
												Ext.example.msg('保存成功!',
														result.message);
											} else {
												myMask.hide();
												Ext.example.msg('保存失败!',
														result.message);
											}
										},
										failure : function(form, action) {
											Ext.example.msg('保存失败!', '出错啦');
										}
									});
								}
					}, this);
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
	
	saveCategory : function (type) {	//添加政策法规和专项资金类别
		if(type == 0){//政策法规
			console.log('type=0');
			var _window = this.getPolicycategorywindow();
			if (!_window) {
				_window = Ext.create('plat.view.policy.PolicyCategoryWin');
			}
			_window.show();
			var _form = this.getPolicycategoryform();
			_form.getForm().reset();
//			_form.getForm().findField('type').show();
//			_form.getForm().findField('display_type').hide();
		}else if(type == 1){//专项资金
			console.log('type=1');
			var _window = this.getFinancialcategorywindow();
			if (!_window) {
				_window = Ext.create('plat.view.policy.FinancialCategoryWin');
			}
			_window.show();
			var _form = this.getFinancialcategoryform();
			_form.getForm().reset();
//			_form.getForm().findField('type').show();
//			_form.getForm().findField('display_type').hide();
		}
	},
	
	deleteCategory : function ()　{　　　　//删除政策法规类别
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
		_grid.getStore().remove(_records);
	},
	
	addPolicy : function (type,title,record) {    //打开政策指引编辑窗口
		var so = null;
		if(type == 0){//政策指引
			var _window = this.getPolicywindow();
			if (!_window) {
				_window = Ext.create('plat.view.policy.PolicyWin');
			}
			_window.setTitle(title);
			_window.show();
			this.getPolicyform().getForm().reset();
//			so = this.getPolicyform().down('treecombo[name=policyCategory.id]').store;
			var editor = this.getPolicyform().getKindeditor();    //清空kindeditor中的内容
			if (editor) {
				editor.html('');
			}
			if(record){
				this.getPolicyform().getForm().loadRecord(record);
				editor.html(record.data.text);
			}
		}else if(type == 1){//资助导航
			var _window = this.getFinancialwin();
			if (!_window) {
				_window = Ext.create('plat.view.policy.FinancialWin');
			}
			_window.setTitle(title);
			_window.show();
			this.getFinancialform().getForm().reset();
//			so = this.getFinancialform().down('treecombo[name=policyCategory.id]').store;
			var editor = this.getFinancialform().getKindeditor();    //清空kindeditor中的内容
			if (editor) {
				editor.html('');
			}
			var textareas = this.getFinancialform().query('textarea');
			Ext.each(textareas, function (textarea, index, countriesItSelf) {
				var paneleditor = textarea.findParentByType('panel').getKindeditor();
				if (paneleditor) {
				paneleditor.html('');
			}
			});
			
			if(record){
				this.getFinancialform().getForm().loadRecord(record);
				editor.html(record.data.text);
				Ext.each(textareas, function (textarea, index, countriesItSelf) {
				var editor = textarea.findParentByType('panel').getKindeditor();
				if (editor) {
					editor.html(record.get(textarea.name));
				}
			});
			}
		 }
	},
	
	savePolicy : function (type) {  
		var _form;
		if(type == 0){
			_form = this.getPolicyform();
		}else if(type == 1){
			_form = this.getFinancialform();
		}
		_form.getKindeditor().sync();    //同步kindeditor的内容到textarea中
		/* 遮罩*/
		var mask = new Ext.LoadMask(_form.getEl(), {
			msg : '表单提交中,请稍等...',
			constrain : true
		});
		/* 提交表单*/
		_form.getForm().submit({
			url : 'policy/addOrUpdate',
			clientValidation: true,
			scope : this,
		    success: function(form, action) {
		    	if(type ==0){
		    		this.getPolicywindow().hide();
		    		this.getPolicygrid().getStore().reload();
		    		this.getPolicygrid().getSelectionModel().deselectAll();
		    	}else{
		    		this.getFinancialwin().hide();
		    		this.getFinancialreportinggrid().getStore().reload();
		    		this.getFinancialreportinggrid().getSelectionModel().deselectAll();
		    	}
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
	},
	
	/* 选择上传文件*/
	selectPic : function () {
		var picWindow = this.getUploadwindow();
    	if (!picWindow) {
    		Ext.widget('uploadwindow',{
	    		title : '上传',
	    		name : 'policy'
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
				       var Epicture = this.getPolicyform().down('textfield[name=uploadFile]');
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
	/* 选择上传文件-资助导航*/
	selectFinancialPic : function () {
		var picWindow = this.getUploadfinancialwin();
    	if (!picWindow) {
    		Ext.widget('uploadfinancialwin',{
	    		title : '上传',
	    		name : 'financial'
	    	}).show();
    	}else{
    		picWindow.show();
    	}
	},
	uploadFinancialImage: function (mask) {//上传资助导航图片
		var Ewindow = this.getUploadfinancialwin();
		var Eform = this.getUploadfinancialform();
		mask.show();
		Eform.getForm().submit({
			url : 'public/uploadFile',
			clientValidation: true,
			scope : this,
		    success: function(form, action) {
		    	if (action.result.success) {
				       var Epicture = this.getFinancialform().down('textfield[name=picture]');
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
	refreshReportingGrid:function(){
		var  adgrid = Ext.ComponentQuery.query('financialreportinggrid')[0];
		var store = adgrid.getStore();
		store.load();
	}
});