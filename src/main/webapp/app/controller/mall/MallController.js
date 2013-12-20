Ext.define('plat.controller.mall.MallController', {
	extend : 'Ext.app.Controller',
	alias : 'widget.mallcontroller',
	views : [
			//类别及服务管理
			'mall.MallCategoryPanel',
			'mall.MallCategoryWindow',
			'mall.MallCategoryGrid', 
			'mall.MallServiceGrid',
			'mall.MallAddServiceWindow', 
			'mall.MallAddServiceGrid',

			//Banner配置
			'mall.MallBannerPanel', 
			'mall.MallBannerGrid',
			'mall.BannerPanel',

			//推荐服务配置
			'mall.MallRecomServiceGrid',
			'mall.MallAddRecomServiceWindow',

			//服务广告配置
			'mall.MallServiceAdGrid', 
			'mall.MallAddServiceAdWindow',
			
			'mall.MallBannerForm' ,
			'mall.MallModelForm' ,
			'public.UploadWin',
			'public.UploadForm',
    		'mall.MallS_Advert',
    		'mall.MallS_Banner', 
    		'mall.MallS_Commend',
    		'mall.MallS_Category'
			],
	stores : [
			'mall.MallCategoryStore', 
			'mall.MallAddServiceStore',
			'mall.MallBannerCategoryStore', 
			'mall.MallAdvertisementStore',
			'mall.MallRecomServiceStore',
			'mall.MallAdStore',
			'mall.MallChildrenCategoryStore',
			'cms.TemplateStore', 
			'cms.ChannelStore'
			],
	refs : [{
				ref : 'mallcategorypanel',
				selector : 'mallcategorypanel'
			}, {
				ref : 'mallcategorywindow',
				selector : 'mallcategorywindow'
			}, {
				ref : 'mallcategorygrid',
				selector : 'mallcategorygrid'
			}, {
				ref : 'mallservicegrid',
				selector : 'mallservicegrid'
			}, {
				ref : 'malladdservicewindow',
				selector : 'malladdservicewindow'
			}, {
				ref : 'malladdservicegrid',
				selector : 'malladdservicegrid'
			}, {
				ref : 'mallbannerpanel',
				selector : 'mallbannerpanel'
			}, {
				ref : 'mallbannerfrid',
				selector : 'mallbannergrid'
			}, {
				ref : 'mallrecomservicegrid',
				selector : 'mallrecomservicegrid'
			}, {
				ref : 'malladdrecomservicewindow',
				selector : 'malladdrecomservicewindow'
			}, {
				ref : 'mallserviceadgrid',
				selector : 'mallserviceadgrid'
			}, {
				ref : 'malladdserviceadwindow',
				selector : 'malladdserviceadwindow'
			}, {
				ref : 'mallbannerform',
				selector : 'mallbannerform'
			}, {
				ref : 'mallmodelform',
				selector : 'mallmodelform'
			}, {
				ref : 'malls_advert',
				selector : 'malls_advert'
			}, {
				ref : 'malls_banner',
				selector : 'malls_banner'
			}, {
				ref : 'malls_commend',
				selector : 'malls_commend'
			}, {
				ref : 'malls_category',
				selector : 'malls_category'
			},{
				ref : 'bannerpanel',
				selector : 'bannerpanel'
			}, {
				ref:'uploadwindow',
				selector:'uploadwindow'
			}, {
				ref:'uploadform',
				selector:'uploadform'
			}
			
	],
	init : function() {
		this.control({
			
			'bannerPanel [xtype=combobox]' : {
        		afterrender : this.comboxAfterRender,
        		select : this.selectTemplate
        	},
        	'bannerPanel panel[action=model]' : {
        		afterrender : this.afterModuleRender
        	},
        	'mallModelForm button[name=selectImg]' : {
        		click: this.uploadImages
        	},
        	'mallModelForm [xtype=filefield]' : {
        		change : this.onChangeImages
        	},
        	'mallBannerForm button[name=selectImg]' : {
        		click: this.uploadImages
        	},
			
			/*类别及服务管理列表*/
			'mallcategorygrid' : {
				afterrender : function(grid) {
					grid.down('combo[name=isHide]').on('change',function(){
						var store = grid.getStore();
						var hide = grid.down('combo[name=isHide]').getValue();
						store.on('beforeload', function(store) {
							Ext.apply(store.proxy.extraParams, {
								hide:hide
							});
						});
						store.load();
					});
					grid.down('button[action=delMallCategory]').on('click',function(){
						var selectionModel = grid.getSelectionModel()
								.getSelection();
						var mallIds = '';
						for (var i = 0; i < selectionModel.length; i++) {
							mallIds += selectionModel[i].data.id
									+ ',';
						}
						if (selectionModel.length > 0) {
							Ext.Ajax.request({
								type : 'post',
								url : 'mall/delecteMalls',
								params : {
									'mallIds' : mallIds
								},
								success : function(response) {
									var result = Ext
											.decode(response.responseText);
									if (result.success) {
										Ext.example.msg('删除成功!',
												result.message);
												var store = grid.getStore();
												var hide = grid.down('combo[name=isHide]').getValue();
												store.on('beforeload', function(store) {
													Ext.apply(store.proxy.extraParams, {
														hide:hide
													});
												});
												store.load();
												
									} else {
										Ext.example.msg('删除失败!',
												result.message);
									}
								},
								failure : function(form, action) {
									Ext.example.msg('删除失败', '删除失败');
								}
							});
						}else{
							return;
						}
					
					},this);
					
				},
				// 双击显示该类别下的服务
				itemdblclick : function(grid, record) {
					var servicegrid = this.getMallservicegrid();
					servicegrid.getStore().setProxy({
								type : 'ajax',
								api : {
									read : 'mall/loadService?id='
											+ record.data.id
								},
								reader : {
									type : 'json',
									messageProperty : "message"
								}
							});
					servicegrid.getStore().load();
				}
			},
			/*添加或修改类别window*/
			'mallcategorywindow' : {
				afterrender : function(window) {
				
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
					window.down('button[action=submit]').on('click',function() {
								window.down('combobox[name=clazz]').setValue('service'); 
								var store = Ext.data.StoreManager.lookup('mall.MallCategoryStore');
								mask.show();
								if (window.title.indexOf('修改') > -1) {
									window.getComponent('mallcategoryform').form
											.submit({
												url : 'mall/update',
												clientValidation : true,
												success : function(form, action) {
													mask.hide();
													if (action.result.success) {
														Ext.example.msg('提示',
																'已修改成功！');
														window.close();
														form.reset();
														var  gridwindow = Ext.ComponentQuery.query('mallcategorypanel')[0];
														var grid = gridwindow.getComponent('mallcategorygrid');
														var store = grid.getStore();
														var hide = grid.down('combo[name=isHide]').getValue();
														store.on('beforeload', function(store) {
															Ext.apply(store.proxy.extraParams, {
																hide:hide
															});
														});
														store.load();
				
													} else {
														Ext.Msg.alert('提示',
																		'<p align="center">操作失败</p>');
													}
												},
												failure : function(form, action) {
													mask.hide();
													switch (action.failureType) {
														case Ext.form.action.Action.CLIENT_INVALID :
															Ext.Msg
																	.alert(
																			'提示',
																			'提交表单中包含非法字符(或必填项为空)！');
															break;
														case Ext.form.action.Action.CONNECT_FAILURE :
															Ext.Msg
																	.alert(
																			'提示',
																			'<p align="center">Ajax请求失败</p>');
															break;
														case Ext.form.action.Action.SERVER_INVALID :
															Ext.Msg
																	.alert(
																			'提示',
																			'<p align="center">'
																					+ action.result.msg
																					+ '</p>');
													}
												}
											});
								} else {
									window.getComponent('mallcategoryform').form
											.submit({
												url : 'mall/add',
												clientValidation : true,
												success : function(form, action) {
													mask.hide();
													if (action.result.success) {
														Ext.example.msg('提示',
																'已成功新增！');
														window.close();
														// STEP1. 获取父节点
														var parentRecord = form
																.findField('pid').value == ""
																? store
																		.getRootNode()
																: store
																		.getById(form
																				.findField('pid').value);
														// STEP2. 创建新节点
														var record = Ext
																.create(
																		'plat.model.mall.MallCategoryModel',
																		action.result.data[0]);
														record.data.loaded = true;
														store.autoSync = false;
														parentRecord
																.appendChild(record);
														form.reset();
													} else {
														Ext.Msg
																.alert('提示',
																		'<p align="center">操作失败</p>');
													}
													store.autoSync = true;
												},
												failure : function(form, action) {
													mask.hide();
													switch (action.failureType) {
														case Ext.form.action.Action.CLIENT_INVALID :
															Ext.Msg
																	.alert(
																			'提示',
																			'提交表单中包含非法字符(或必填项为空)！');
															break;
														case Ext.form.action.Action.CONNECT_FAILURE :
															Ext.Msg
																	.alert(
																			'提示',
																			'<p align="center">Ajax请求失败</p>');
															break;
														case Ext.form.action.Action.SERVER_INVALID :
															Ext.Msg
																	.alert(
																			'提示',
																			'<p align="center">'
																					+ action.result.msg
																					+ '</p>');
													}
												}
											});
								}
							}, this);
					window.down('button[action=esc]').on('click', function() {
								window.getComponent('mallcategoryform').form
										.reset();
								window.close();
							});
				}
			},
			/*类别添加服务window*/
			'malladdservicewindow' : {
				afterrender : function(window) {
					var store = window.getComponent('malladdservicegrid')
							.getStore();
					var serviceName = '';
					var cid,industryType;
					store.on('beforeload', function(store) {
								Ext.apply(store.proxy.extraParams, {
											serviceName : serviceName,
											cid : cid,
											industryType:industryType
										});
							});
					store.load();
					var gridpanel = window.getComponent('malladdservicegrid');
					/* 搜索事件 */
					gridpanel.down('button[action=search]').on('click',
							function() {
								var mallId = gridpanel.down('treecombo[name=id]')
										.getValue();
								serviceName = gridpanel
										.down('textfield[name=serviceName]')
										.getValue();
								industryType = gridpanel.down('combo[name=industryType]').getValue();
								var store = window.getComponent('malladdservicegrid')
										.getStore();
								store.load(store.proxy.extraParams, {
											cid : mallId,
											serviceName : serviceName,
											industryType:industryType
										});
							}, this);

					/* 添加服务到服务商城事件 */
					window.down('button[action=addmallservice]').on('click',
							function() {
								var grid = window
										.getComponent('malladdservicegrid');
								var mallId = grid
										.down('textfield[name=mallId]')
										.getValue();
								var selectionModel = grid.getSelectionModel()
										.getSelection();
								var serviceIds = '';
								for (var i = 0; i < selectionModel.length; i++) {
									serviceIds += selectionModel[i].data.id
											+ ',';
								}
								if (selectionModel.length > 0) {
									var myMask = new Ext.LoadMask(grid, {//也可以是Ext.getCmp('').getEl()窗口名称
											msg    : "正在操作,请稍后...",//你要写成Loading...也可以
											msgCls : 'z-index:10000;'
									});
									myMask.show();
									
									Ext.Ajax.request({
										type : 'post',
										url : 'mall/mallAddService',
										params : {
											'mallId' : mallId,
											'serviceIds' : serviceIds
										},
										success : function(response) {
											var result = Ext
													.decode(response.responseText);
											if (result.success) {
												myMask.hide();
												Ext.MessageBox.confirm("提示信息","添加成功，是否继续",
													function(e) {
														if (e == 'yes') {
															var gridpanel = window.getComponent('malladdservicegrid');
															var mallId = gridpanel.down('treecombo[name=id]').getValue();
															var serviceName = gridpanel.down('textfield[name=serviceName]').getValue();
															var store = window.getComponent('malladdservicegrid').getStore();
															store.load(
																			store.proxy.extraParams,
																			{
																				cid : mallId,
																				serviceName : serviceName
																			});
														} else {
															var gridpanel = window.getComponent('malladdservicegrid');
															var mallId = gridpanel.down('treecombo[name=id]').getValue();
															var serviceName = gridpanel.down('textfield[name=serviceName]').getValue();
															var store = window.getComponent('malladdservicegrid').getStore();
															store.load(
																			store.proxy.extraParams,
																			{
																				cid : mallId,
																				serviceName : serviceName
																			});
															window.close();
														}
													});
											} else {
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

					/* 取消事件 */
					window.down('button[action=closeaddmallservice]').on(
							'click', function() {
								window.close();
							});
				}
			},
			/*banner配置grid*/
			'mallbannergrid' : {
				afterrender : function(grid) {
					grid.getStore().load();
				},
				itemdblclick : this.getChannelToModel,
				scope : this
			},
			/*推荐服务列表*/
			'mallrecomservicegrid' : {
				afterrender : function(grid) {
					var me = this;
					var store = grid.getStore();
					var advNo = grid.down("triggerfield[name=advNo]")
							.getValue();
					store.on('beforeload', function(store) {
								Ext.apply(store.proxy.extraParams, {
											position : '2,3',
											advNo : advNo
										});
							});
					store.load();
					/* 推荐服务搜索事件 */
					grid.down('button[action=search]').on('click', function() {
						var store = grid.getStore();
						var advNo = grid.down("triggerfield[name=advNo]")
							.getValue();
						store.on('beforeload', function(store) {
								Ext.apply(store.proxy.extraParams, {
											position : '2,3',
											advNo : advNo
										});
							});
						store.load();
					}, this);
					/* 添加推荐服务事件 */
					grid.down('button[action=addRecomService]').on('click',
							function() {
								var addRecomServiceWindow = null;
								var windows = Ext.ComponentQuery
										.query('malladdrecomservicewindow');
								if (windows.length > 0) {
									addRecomServiceWindow = windows[0];
									
									addRecomServiceWindow.setTitle('添加推荐服务');
									addRecomServiceWindow.show();
								} else {
									addRecomServiceWindow = Ext.widget(
											'malladdrecomservicewindow', {
												title : '添加推荐服务'
											}).show();
								}
								var form = addRecomServiceWindow
										.getComponent('addrecomserviceform').form;
								addRecomServiceWindow.down('displayfield[name="advertisementNo"]').setVisible(false);
								addRecomServiceWindow.down('button[text="取消"]').setVisible(false);
								addRecomServiceWindow.down('button[action=reset]').setVisible(true);
								addRecomServiceWindow.down('combo[name="mallCategory.id"]').setValue('');
								form.reset();
							}, this);
							
				/*批量删除推荐服务事件*/
				grid.down('button[action=deleteRecomService]').on('click',
					function() {
						var selectionModel = grid.getSelectionModel()
								.getSelection();
						var advIds = '';
						for (var i = 0; i < selectionModel.length; i++) {
							advIds += selectionModel[i].data.id
									+ ',';
						}
						if (selectionModel.length > 0) {
								Ext.MessageBox.confirm("提示",
										"删除之后服务恢复，确定删除吗？", function(e) {
											if (e == 'yes') {
												Ext.Ajax.request({
													type : 'post',
													url : 'ad/delecteAdvs',
													params : {
														'advIds' : advIds
													},
													success : function(
															response) {
														var result = Ext
																.decode(response.responseText);
														if (result.success) {
															Ext.example
																	.msg(
																			'删除成功!',
																			result.message);
															me.refreshRecomServiceGrid();

														} else {
															Ext.example
																	.msg(
																			'删除失败!',
																			result.message);
														}
													},
													failure : function(
															form, action) {
														Ext.example.msg(
																'删除失败',
																'删除失败');
													}
												});
											}
										});
								}else{
								Ext.example.msg('提示','请选择推荐服务');
							}
					}, this);
				},
				/*双击打开编辑事件*/
				itemdblclick : function(grid, record, item, index, e, eOpts ) {
					var addRecomServiceWindow = null;
					var windows = Ext.ComponentQuery
							.query('malladdrecomservicewindow');
					if (windows.length > 0) {
						addRecomServiceWindow = windows[0];
						addRecomServiceWindow.setTitle('编辑推荐服务');
						addRecomServiceWindow.show();
					} else {
						addRecomServiceWindow = Ext.widget(
								'malladdrecomservicewindow', {
									title : '编辑推荐服务'
								}).show();
					}
					var form = addRecomServiceWindow
							.getComponent('addrecomserviceform').form;
					addRecomServiceWindow.down('combo[name="mallCategory.id"]').setValue('');
					addRecomServiceWindow.down('displayfield[name="advertisementNo"]').setVisible(true);
					addRecomServiceWindow.down('button[text="取消"]').setVisible(true);
					addRecomServiceWindow.down('button[action=reset]').setVisible(false);
					form.reset();
					form.loadRecord(record);
				}

			},
			/*添加推荐服务window*/
			'malladdrecomservicewindow':{
				afterrender : function(window) {
					
					 var mallId = window.down('combo[name=mallCategory.id]').getValue();
            		 window.down('textfield[name=mallId]').setValue(mallId);
            		
					 window.down('combo[name=serviceId]').on('expand', function() {
					 var store =  window.down('combo[name=serviceId]').getStore();
					 var mallId =  window.down('textfield[name=mallId]').getValue();
						store.removeAll();
					 	store.on('beforeload', function(store) {
								Ext.apply(store.proxy.extraParams, {
											mallId : mallId,
											serviceName:''
								});
							});
						store.load();
					 }, this);	
					var me = this;
					/*添加推荐服务，提交按钮事件*/
					window.down('button[action=addRecomService]').on('click', function() {
						var a = window.down('combo[name=serviceId]').getValue();
						window.down('textfield[name=service.id]').setValue(a);
						var n = Number(a);
            			if(!isNaN(n)){
            			window.getComponent('addrecomserviceform').form.submit({
				    		clientValidation: true,
						    url: 'ad/addAd',
						    params: {
						        action: 'save'						        
						    },
						    success: function(form, action) {
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshRecomServiceGrid();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
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
				    	}else{
            				Ext.Msg.alert('提示', '请选择正确的对应服务');
            			}
            		}, this);	
            		/*添加推荐服务窗口，重置按钮事件*/
            		window.down('button[action=reset]').on('click', function() {
            			window.down('combo[name="mallCategory.id"]').setValue('');
            			window.getComponent('addrecomserviceform').form.reset();
            		},this);
            		/*选择频道 动态加载一级分类*/
            		window.down('combo[name=channel.id]').on('change', function() {
            			var pid = window.down('combo[name=channel.id]').getValue();
            			var categoryCombo = window.down('combo[name="mallCategory.id"]');
            			var cateStore = categoryCombo.getStore();
            			cateStore.on('beforeload', function(store) {
								Ext.apply(cateStore.proxy.extraParams, {
											pid : pid
									});
							});
						cateStore.load();
            			
            		},this);
            		//商城类别选择是给mallid赋值
				     window.down('combo[name=mallCategory.id]').on('change', function() {
            			var mallId = window.down('combo[name=mallCategory.id]').getValue();
            			 window.down('textfield[name=mallId]').setValue(mallId);
            		},this);
				}
			},
			'mallserviceadgrid':{
				afterrender : function(grid) {
					var	me = this;
					var store = grid.getStore();
					var advNo = grid.down("triggerfield[name=advNo]").getValue();
					store.on('beforeload', function(store) {
								Ext.apply(store.proxy.extraParams, {
											position : '1,4',
											advNo:advNo
										});
							});
					store.load();
					/* 服务广告搜索事件 */
					grid.down('button[action=search]').on('click', function() {
						var store = grid.getStore();
						var advNo = grid.down("triggerfield[name=advNo]").getValue();
						store.on('beforeload', function(store) {
									Ext.apply(store.proxy.extraParams, {
												position : '1,4',
												advNo:advNo
											});
								});
						store.load();
					}, this);
					/* 批量删除服务广告事件 */
					grid.down('button[action=deleteServiceAd]').on('click', function() {
								var selectionModel = grid.getSelectionModel()
										.getSelection();
								var advIds = '';
								for (var i = 0; i < selectionModel.length; i++) {
									advIds += selectionModel[i].data.id
											+ ',';
								}
								if (selectionModel.length > 0) {
									Ext.MessageBox.confirm("提示", "删除之后无法恢复，确定删除吗？",
											function(e) {
												if (e == 'yes') {
													Ext.Ajax.request({
														type : 'post',
														url : 'ad/delecteAdvs',
														params : {
															'advIds' : advIds
														},
														success : function(
																response) {
															var result = Ext
																	.decode(response.responseText);
															if (result.success) {
																Ext.example
																		.msg(
																				'删除成功!',
																				result.message);
																me.refreshServiceAdGrid();
															} else {
																Ext.example
																		.msg(
																				'删除失败!',
																				result.message);
															}
														},
														failure : function(
																form, action) {
															Ext.example.msg(
																	'删除失败',
																	'删除失败');
														}
													});
												}
									});
								}else{
									Ext.example.msg('提示','请选择服务广告服务');
								}
					}, this);
					/* 添加服务广告事件 */
					grid.down('button[action=addServiceAd]').on('click',
							function() {
								var addServiceAdWindow = null;
								var windows = Ext.ComponentQuery
										.query('malladdserviceadwindow');
								if (windows.length > 0) {
									addServiceAdWindow = windows[0];
									addServiceAdWindow.setTitle('添加服务广告');
									addServiceAdWindow.show();
								} else {
									addServiceAdWindow = Ext.widget(
											'malladdserviceadwindow', {
												title : '添加服务广告'
											}).show();
								}
								var form = addServiceAdWindow
										.getComponent('addserviceadform').form;
								addServiceAdWindow.down('displayfield[name="advertisementNo"]').setVisible(false);
								addServiceAdWindow.down('button[text="取消"]').setVisible(false);
								addServiceAdWindow.down('button[action=reset]').setVisible(true);
								addServiceAdWindow.down('combo[name="mallCategory.id"]').setValue('');
								form.reset();
							}, this);
							
				},
				/*双击打开服务广告编辑窗口*/
				itemdblclick : function(grid, record, item, index, e, eOpts ) {
					var editServiceAdWindow = null;
					var windows = Ext.ComponentQuery
							.query('malladdserviceadwindow');
					if (windows.length > 0) {
						editServiceAdWindow = windows[0];
						editServiceAdWindow.setTitle('编辑服务广告');
						editServiceAdWindow.show();
					} else {
						editServiceAdWindow = Ext.widget(
								'malladdserviceadwindow', {
									title : '编辑服务广告'
								}).show();
					}
					var form = editServiceAdWindow
							.getComponent('addserviceadform').form;
					editServiceAdWindow.down('combo[name="mallCategory.id"]').setValue('');
					editServiceAdWindow.down('displayfield[name="advertisementNo"]').setVisible(true);
					editServiceAdWindow.down('button[text="取消"]').setVisible(true);
					editServiceAdWindow.down('button[action=reset]').setVisible(false);
					form.reset();
					form.loadRecord(record);

				}
			},
			'malladdserviceadwindow':{
				afterrender : function(window) {
					
					 var mallId = window.down('combo[name=mallCategory.id]').getValue();
            		 window.down('textfield[name=mallId]').setValue(mallId);
            		 
					 window.down('combo[name=serviceId]').on('click', function() {
					 var store =  window.down('combo[name=serviceId]').getStore();
					 var mallId =  window.down('textfield[name=mallId]').getValue();
					 	store.on('beforeload', function(store) {
								Ext.apply(store.proxy.extraParams, {
											mallId : mallId,
											serviceName:''
								});
							});
						store.load();
					 }, this);	
					var me = this;
					/*添加服务广告事件*/
					window.down('button[action=addServiceAd]').on('click', function() {
						var a = window.down('combo[name=serviceId]').getValue();
						window.down('textfield[name=service.id]').setValue(a);
						var n = Number(a);
            			if(!isNaN(n)){
						window.getComponent('addserviceadform').form.submit({
				    		clientValidation: true,
						    url: 'ad/addAd',
						    params: {
						        action: 'save'						        
						    },
						    success: function(form, action) {
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshServiceAdGrid();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
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
            			}else{
            				Ext.Msg.alert('提示', '请选择正确的对应服务');
            			}
            		}, this);	
            		/*上传图片事件*/
            		window.down('button[name=select]').on('click', function () {
            			this.selectPic('addServiceAdver');
            		}, this);
            		/*重置事件*/
            		window.down('button[action=reset]').on('click', function() {
            			window.down('combo[name="mallCategory.id"]').setValue('');
            			window.getComponent('addserviceadform').form.reset();
            		},this);
            		/*选择频道 动态加载一级分类*/
            		window.down('combo[name=channel.id]').on('change', function() {
            			var pid = window.down('combo[name=channel.id]').getValue();
            			var categoryCombo = window.down('combo[name="mallCategory.id"]');
            			var cateStore = categoryCombo.getStore();
            			cateStore.on('beforeload', function(store) {
								Ext.apply(store.proxy.extraParams, {
											pid : pid
										});
							});
						cateStore.load();
            		},this);
            		//商城类别选择是给mallid赋值
				     window.down('combo[name=mallCategory.id]').on('change', function() {
            			var mallId = window.down('combo[name=mallCategory.id]').getValue();
            			 window.down('textfield[name=mallId]').setValue(mallId);
            		},this);
				}
				
			},
			"uploadwindow[name=addServiceAdver]" : {
				afterrender : function (window, opts) {
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '上传中,请稍等...',
						constrain : true
					});
					window.down('button[name=submit]').on('click', function () {
            			this.uplodImage(mask, 'addServiceAdver');
            		}, this);
            		window.down('button[name=cancel]').on('click', function () {
            			window.hide();
            		}, this);
				}
			}
		});
	},
	selectPic : function (name) {
		var picWindow = Ext.ComponentQuery.query('uploadwindow[name='+ name +']')[0];
    	if (!picWindow) {
    		Ext.widget('uploadwindow',{
    			title : '上传',
    			name : name
    		}).show();
    	} else {
    		picWindow.show();
    	}
	},
	//刷新推荐服务列表
	refreshRecomServiceGrid : function() {
		var  grid = Ext.ComponentQuery
										.query('mallrecomservicegrid')[0];
		var store = grid.getStore();
		var advNo = grid.down("triggerfield[name=advNo]").getValue();
		store.on('beforeload', function(store) {
					Ext.apply(store.proxy.extraParams, {
								position : '2,3',
								advNo:advNo
							});
				});
		store.load();
	},
	//刷新服务广告列表
	refreshServiceAdGrid:function(){
		var  adgrid = Ext.ComponentQuery.query('mallserviceadgrid')[0];
		var store = adgrid.getStore();
		var advNo = adgrid.down("triggerfield[name=advNo]").getValue();
		store.on('beforeload', function(store) {
					Ext.apply(store.proxy.extraParams, {
								position : '1,4',
								advNo:advNo
							});
				});
		store.load();
	
	},
	getChannelToModel : function(grid, record, item, index, e, eOpts) {
		var me = this, tempPanel = Ext.getCmp('bannerPanel');
		tempPanel.removeAll();// 移除当前的模块模版
		var mtcode = record.data.mtcode;
		if (record.data.mnumber == 0)
			return;
		var bbar = ['->'];
		for (var i = 1; i <= record.data.mnumber; i++) {
			bbar.push({
						text : i,
						indexs : i,
						records : null,
						handler : function(b) {
							var p = this.ownerCt.ownerCt;
							p.loadAdvertImg(b.indexs, b.records);
						}
					})
		}
		var bannerPanel = Ext.create('plat.view.mall.MallS_Banner', {
			title : record.data.cname,
			otitle : record.data.cname,
			bbar : bbar,
			nodeData : record,
			loadAdvertImg : function(number, records) {
				this.number = number;
				this.setTitle(this.otitle + "【" + number + "】");
				if (records) {
					if(records.micon.indexOf('http') > -1){
						var html = '<div width="100%" height="100%" style="text-align:right;">'
							+ '<img number="'
							+ number
							+ '" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />'
							+ '<img width="'
							+ this.width
							+ '" height="'
							+ this.height
							+ '" src="'
							+ records.micon
							+ '" style="vertical-align:middle;" />' + '</div>';
					}else {
						var html = '<div width="100%" height="100%" style="text-align:right;">'
							+ '<img number="'
							+ number
							+ '" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />'
							+ '<img width="'
							+ this.width
							+ '" height="'
							+ this.height
							+ '" src="upload/'
							+ records.micon
							+ '" style="vertical-align:middle;" />' + '</div>';
					}					
					this.body.update(html);
					this.data = records;
				} else {
					this.body.update("");
				}
			}
		});
		tempPanel.add(bannerPanel);
		Ext.Ajax.request({
			url : 'cms/findAllModuel',
			method : 'post',
			params : {
				mchannel : record.data.ccode
			},
			success : function(response, opts) {
				var modelObj = Ext.decode(response.responseText).data;
				for (var i = 0; i < modelObj.length; i++) {
					// 获取色块的索引值
					var number = modelObj[i].mposition;
					var buts = Ext.ComponentQuery.query('malls_banner button');
					for (var y = 0; y < buts.length; y++) {
						if (number == buts[y].indexs) {
							buts[y].records = modelObj[i];
							break;
						}
					}
					// 根据选择器查找当前的色块对象
					var modelPanel = Ext.ComponentQuery
							.query('bannerPanel panel[number=' + number + ']')[0];
					if (modelPanel) {
						if(modelObj[i].micon.indexOf('http') > -1){
							var html = '<div width="100%" height="100%" style="text-align:right;">'
								+ '<img number="'
								+ number
								+ '" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />'
								+ '<img width="'
								+ modelPanel.width
								+ '" height="'
								+ modelPanel.height
								+ '" src="'
								+ modelObj[i].micon
								+ '" style="vertical-align:middle;" />'
								+ '</div>';
						}else {
							var html = '<div width="100%" height="100%" style="text-align:right;">'
								+ '<img number="'
								+ number
								+ '" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />'
								+ '<img width="'
								+ modelPanel.width
								+ '" height="'
								+ modelPanel.height
								+ '" src="upload/'
								+ modelObj[i].micon
								+ '" style="vertical-align:middle;" />'
								+ '</div>';
						}						
						modelPanel.body.update(html);
						// 将该色块的所有属性都赋值到该色块的data(自己定义的)属性上
						modelPanel.data = modelObj[i];
					}
				}
			},
			failure : function(response, opts) {
			}
		});
	},
	uplodImage : function (mask, name) {
		var Ewindow = Ext.ComponentQuery.query('uploadwindow[name='+ name +']')[0];
		var Eform = Ewindow.query('uploadform')[0];
		var window = this.getMalladdserviceadwindow();
		mask.show();
		Eform.getForm().submit({
			url : 'public/uploadFile',
			clientValidation: true,
		    success: function(form, action) {
		    	if (action.result.success) {
			       var addForm = window.query('form')[0];
			       var Epicture = addForm.down('textfield[name=uploadImage]');
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
    //banner
    comboxAfterRender: function(combox, obj){
    	this.templateStore = combox.getStore();
    	//加载模版的数据
    	this.templateStore.load();
    },
    selectTemplate: function(combox){
    	this.getBannerPanel().removeAll();
    	this.getBannerPanel().add({xtype:combox.getValue()});
    } ,
    afterModuleRender: function(panel){
    	//给每个色块都添加双击事件
    	var me = this;
    	panel.el.on('dblclick', function(){me.modelSetup(panel)}, panel);
    	panel.el.on('mouseover', this.modelMouseoverFn, panel, me);
    },
    modelSetup: function(panel, obj){
    	var tempObj = Ext.getCmp('bannerPanel').items.items[0], modelForm = null;
    	var xtypes = 'mallModelConfigPanel', nodeData = tempObj.nodeData, record = tempObj.data;
    	if(nodeData){
    		var mtcode = nodeData.data.mtcode;
        	if(mtcode == 'banner'){
	    		xtypes = 'mallBannerForm';
	    		var but = Ext.ComponentQuery.query('malls_banner button[indexs='+ panel.number +']')[0];
	    		if(!but.records){
	    			record = null;
	    		}
        	} else {
	    		tempObj = Ext.ComponentQuery.query('bannerPanel panel[number='+ panel.number +']')[0];
	    		record = tempObj.data;
	    	}
    	} else {
    		tempObj = Ext.ComponentQuery.query('bannerPanel panel[number='+ panel.number +']')[0];
    		record = tempObj.data;
    	}
    	var winConfig = Ext.create('Ext.window.Window',{
    		title: '色块配置',
			frame: false,
			modelPanel: tempObj,
			autoWidth: true,
			autoHeight: true,
			modal: true,
			addOrUpdate: record ? 'update' : 'add',
			items : Ext.widget(xtypes),
		    buttonAlign: 'center',
		    buttons: [{
		    	name: 'modelSave',
		    	types: xtypes,
		        text: '保存',
		        handler: this.modelSave,
		        scope: this
		    }, {
		    	name: 'modelCanel',
		        text: '取消',
		        handler: this.modelCanel
		    }]
    	}).show();
    	if(xtypes != 'mallBannerForm'){
    		modelForm = Ext.getCmp('mallModelForm');
    	} else {
    		modelForm =  Ext.getCmp('mallBannerForm');
    	}
    	winConfig.modelForm = modelForm;
    	//给色块的位置赋值
    	modelForm.down('textfield[name=mposition]').setValue(panel.number);
    	//给色块所属的频道赋值
    	modelForm.down('textfield[name=mchannel]').setValue(nodeData.data.ccode);
    	//给色块的编码赋值
    	modelForm.down('textfield[name=mcode]').setValue(nodeData.data.ccode+ '-' +panel.number);
    	if(record){
    		modelForm.items.each(function(item, index, length){
    			function child(item){
	    			if(item){
	    				if(item.name && record[item.name]){
							item.setValue(record[item.name]);
    					}
	    			}
	    			if(item.items){
	    				item.items.each(function(item1){
	    					//如果item1配置过，并且也有相应的数据就执行下去
	    					if(item1.name && record[item1.name]){
	    						//给该空间赋值
    							item1.setValue(record[item1.name]);
	    					}
	    					if(item1.id == 'imgpre'){
	    						//给色块图标预览的组件赋值
	    						if(record.micon.indexOf('http') > -1){
	    							Ext.get('imgpre').dom.src = record.micon;
	    						}else {
	    							Ext.get('imgpre').dom.src = 'upload/' +record.micon;
	    						}	    						
	    					}
	    					//继续遍历组件下面的组件
    						child(item1);
	    				});
    				}
    			}
    			child(item);
    		});
    	}
    } ,
    modelMouseoverFn: function(panel, obj, thisObj){
    	console.log('modelMouseoverFn');
    	var me = this;
    	if(obj.localName == 'img'){
    		var divObj = Ext.fly(Ext.fly(obj).parent('div').dom);
    		divObj.first().removeAllListeners();
    		divObj.first().addListener({
    			'click': function(but, dom){
	    			Ext.MessageBox.confirm('提示', '您确定要删除该模块吗？', function(b){
	    				if(b == 'yes'){
	    					Ext.Ajax.request({
	    						url: 'cms/deleteModule',
				    			method: 'post',
				    			params : {
				    				mid: me.data.mid,
				    				mchannel: me.data.mchannel,
				    				mposition: me.data.mposition
				    			},
				    			success: function(response, opts) {
							        var result = Ext.decode(response.responseText);
							        if(result.success){
							        	me.data = null;
							        	me.body.update("");
							        	var mtcode = me.nodeData.data.mtcode;
							        	if(mtcode == 'banner'){
							        		var mnumber = me.nodeData.data.mnumber;
							        		if(mnumber > 0){
							        			me.nodeData.data.mnumber = mnumber - 1;
							        		}
							        		thisObj.getChannelToModel(null, me.nodeData);
							        		var button = me.down('button[indexs='+ dom.getAttribute('number') +']');
							        		button.records = null;
							        	}
							        	Ext.example.msg('','<p align="center">'+ result.message +'</p>');
							        } else {
							        	Ext.MessageBox.alert('错误', result.message)
							        }
							    },
							    failure: function(response, opts) {
							    }
	    					});
	    				}
	    			});
	    		}
    		});
    	}
    },
    uploadImages: function(but){
		//获取上传图片的window弹窗
		var mWin = Ext.ComponentQuery.query('uploadwindow[name=module]')[0];
		var formObj = but.ownerCt.ownerCt.ownerCt.ownerCt;
    	if(!mWin){
    		Ext.widget('uploadwindow',{
    			title : '上传模块图片',
    			name : 'module',
    			formObj: formObj
    		}).show();
    	}else{
    		mWin.formObj = formObj;
    		mWin.show();
    	}
	},
	onChangeImages : function(field, newValue, oldValue){
		var imgObj = Ext.get("form-file-button-fileInputEl").dom;
   	 	var imgpreObj = Ext.get("imgpre").dom;
        if (Ext.isIE) {
        	imgObj.select();   
       	 	var imgSrc = document.selection.createRange().text;
       	 	imgpreObj.src = Ext.BLANK_IMAGE_URL;
        	imgpreObj.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
        } else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
        	var oFReader = new FileReader();
			var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
			oFReader.onload = function (oFREvent) {
			  	imgpreObj.src = oFREvent.target.result;
			};
		  	if (imgObj.files.length === 0) {
		  		return;
		  	}
		  	var oFile = imgObj.files[0];
		  	if (!rFilter.test(oFile.type)) {
		  		Ext.MessageBox.alert("错误", "您选择的文件无效!");
		  		return; 
		  	}
		  	oFReader.readAsDataURL(oFile);
        }
	},
	//保存banner
	modelSave : function(but){
    	var me = this;
    	//获取当前window
    	var win = but.ownerCt.ownerCt;
    	//获取banner表单
    	var modelForm = win.modelForm;
    	
    	if(modelForm.form.isValid()){
    		//banner表单提交
    		modelForm.getForm().submit({
    			clientValidation: true,
                url: win.addOrUpdate == 'add' ? 'cms/addModule' : 'cms/updateModule',
                method: 'post',
                waitTitle: '提示',
                waitMsg: '系统正在提交数据，请稍等……',
                waitMsgTarget: true, 
                //操作成功回调
                success: function(form, action){
                	var result = action.result;
                	if(result.success){
	                	//获取刚刚配置的色块组件
	                	var modelPanel = win.modelPanel;
	                	if(result.message.indexOf('http') > -1){
	                		var html = '<div width="100%" height="100%" style="text-align:right;">' +
	                					'<img src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
	                			   		'<img width="'+ modelPanel.width +'" height="'+ modelPanel.height +'" src="'+ result.message +'" style="vertical-align:middle;" />' +
	                			   '</div>';
	                	}else {
	                		var html = '<div width="100%" height="100%" style="text-align:right;">' +
	                					'<img src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
	                			   		'<img width="'+ modelPanel.width +'" height="'+ modelPanel.height +'" src="upload/'+ result.message +'" style="vertical-align:middle;" />' +
	                			   '</div>';
	                	}	                	
	                	//更新预览模块的图标
	                	win.modelPanel.body.update(html);
	                	//将新的表单内容赋值给该色块
	                	win.modelPanel.data = modelForm.getValues();
	                	if(but.types == 'mallBannerForm'){
	                		var buts = modelPanel.down('button[indexs='+ modelPanel.number +']');
	                		buts.records = modelForm.getValues();
	                	}
	                	win.addOrUpdate == 'add' ? (win.modelPanel.data.mid = result.backupfield) : null;
	                	//关闭弹窗窗体
	                	me.modelCanel(but);
	                	Ext.example.msg('','<p align="center">恭喜您操作成功！</p>');
                	}
                },
                failure: function(form, action){
                    alert(action.result.message);
                },
                scope: true
            });
    	}
    }, modelCanel: function(but){
    	but.ownerCt.ownerCt.close();
    }
});
