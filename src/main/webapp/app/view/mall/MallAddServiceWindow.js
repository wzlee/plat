Ext.define('plat.view.mall.MallAddServiceWindow', {
			extend : 'Ext.window.Window',
			xtype : 'malladdservicewindow',
			id : 'malladdservicewindow',
			width : 800,
			height : 520,
			layout : 'fit',
			closeAction : 'hide',
			buttonAlign : 'center',
			modal : true,
			title : '商城添加服务',
			initComponent : function() {
				var me = this;
				Ext.applyIf(me, {
							items : [{
										xtype : 'malladdservicegrid'

									}],

							buttons : [{
										text : '添加选中服务',
										action : 'addmallservice'
									}, {
										text : '取消',
										action : 'closeaddmallservice'
									}]
						});

				me.callParent(arguments);
			}

		});