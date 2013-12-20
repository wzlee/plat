var orderId;		//定义一个全局变量接收订单id
var bidServiceId;	//定义一个全局变量接收招单id
Ext.define('plat.controller.business.OrderController', {
    extend: 'Ext.app.Controller',    
    xtype:'ordercontroller',
    
    stores: [
				'business.OrderStore',
				'business.AppealStore',
				'business.QAppealStore',
				'business.OrderInfoStore',
				'business.EvaluationStore',
				'business.BiddingServiceStore',
				'business.BiddingServiceDetailStore',
				'business.ResponseBiddingStore',
				'business.BiddingAppealStore',
				'business.OrderAndBiddingAppealStore',
				'business.ApprBiddingServiceStore'
			],
    views: [
    			'business.OrderGrid',
    			'layout.combo.OrderStatus',
    			'layout.combo.BiddingServiceStatus',
    			'layout.combo.HandlerMode',
    			'business.GoodsDetailWindow',
    			'business.OrderHandlerWindow',
				'business.OrderDetailWindow',
				'business.HandlerAppealGrid',
				'business.HGoodsDetailWindow',
				'business.HOrderDetailWindow',
				'business.AppealHandlerWindow',
				'business.QAppealGrid',
				'layout.combo.AppealTypeCombo',
				'business.OrderInfoGrid',
				'business.EvaluationGrid',
				'business.BiddingServiceGrid',
				'business.BiddingServiceAuditWindow',
				'business.ApprBiddingServiceWindow',
				'business.BiddingServiceDetailWindow',
				'business.BiddingServiceDetailGrid',
				'business.ResponseBiddingGrid',
				'business.BiddingOtherHandlerWindow',
				'business.HandlerBiddingServiceWindow',
				'business.BiddingAppealGrid',
				'business.BiddingAppealHandlerWindow',
				'business.AppealHandlerBiddingServiceWindow',
				'business.AppealBiddingServiceDetailWindow',
				'business.ApprBiddingGrid',
				'business.DAppealHandlerWindow',
				'business.OrderBiddingServiceDetailWindow',
				'business.OrderBiddingHandlerWindow'
    		],    
     refs: [
     		{
     			ref:'orderstore',
     			selector:'orderstore'
     		},
     		{
     			ref:'appealstore',
     			selector:'appealstore'
     		},
     		{
     			ref:'orderinfostore',
     			selector:'orderinfostore'
     		},
     		{
     			ref:'evaluationstore',
     			selector:'evaluationstore'
     		},
     		{
     			ref:'biddingservicestore',
     			selector:'biddingservicestore'
     		},
     		{
     			ref:'ordergrid',
     			selector:'ordergrid'
     		},
     		{
     			ref:'goodsdetailwindow',
     			selector:'goodsdetailwindow'
     		},
     		{
     			ref:'orderhandlerwindow',
     			selector:'orderhandlerwindow'
     		},
     		{
     			ref:'orderdetailwindow',
     			selector:'orderdetailwindow'
     		},
     		{
     			ref:'handlerappealgrid',
     			selector:'handlerappealgrid'
     		},
     		{
     			ref:'hgoodsdetailwindow',
     			selector:'hgoodsdetailwindow'
     		},
     		{
     			ref:'horderdetailwindow',
     			selector:'horderdetailwindow'
     		},
     		{
     			ref:'appealhandlerwindow',
     			selector:'appealhandlerwindow'
     		},
     		{
     			ref:'qappealstore',
     			selector:'qappealstore'
     		},
     		{
     			ref:'qappealgrid',
     			selector:'qappealgrid'
     		},
     		{
     			ref:'orderinfogrid',
     			selector:'orderinfogrid'
     		},
     		{
     			ref:'evaluationgrid',
     			selection:'evaluationgrid'
     		},
     		{
     			ref:'biddingservicegrid',
     			selector:'biddingservicegrid'
     		},
     		{
     			ref:'biddingserviceauditwindow',
     			selector:'biddingserviceauditwindow'
     		},
     		{
     			ref:'apprbiddingservicewindow',
     			selector:'apprbiddingservicewindow'
     		},
     		{
     			ref:'biddingservicedetailstore',
     			selector:'biddingservicedetailstore'
     		},
     		{
     			ref:'responsebiddingstore',
     			selector:'responsebiddingstore'
     		},
     		{
     			ref:'responsebiddinggrid',
     			selector:'responsebiddinggrid'
     		},
     		{
     			ref:'biddingservicedetailgrid',
     			selector:'biddingservicedetailgrid'
     		},
     		{
     			ref:'biddingservicedetailwindow',
     			selector:'biddingservicedetailwindow'
     		},
     		{
     			ref:'biddingotherhandlerwindow',
     			selector:'biddingotherhandlerwindow'
     		},
     		{
     			ref:'handlerbiddingservicewindow',
     			selector:'handlerbiddingservicewindow'
     		},
     		{
     			ref:'biddingappealstore',
     			selector:'biddingappealstore'
     		},
     		{
     			ref:'biddingappealgrid',
     			selector:'biddingappealgrid'
     		},
     		{
     			ref:'biddingappealhandlerwindow',
     			selector:'biddingappealhandlerwindow'
     		},
     		{
     			ref:'appealhandlerbiddingservicewindow',
     			selector:'appealhandlerbiddingservicewindow'
     		},
     		{
     			ref:'appealbiddingservicedetailwindow',
     			selector:'appealbiddingservicedetailwindow'
     		},
     		{
     			ref:'orderandbiddingappealstore',
     			selector:'orderandbiddingappealstore'
     		},
     		{
     			ref:'apprbiddinggrid',
     			selector:'apprbiddinggrid'
     		},
     		{
     			ref:'apprbiddingservicestore',
     			selector:'apprbiddingservicestore'
     		},
     		{
     			ref:'dappealhandlerwindow',
     			selector:'dappealhandlerwindow'
     		},
     		{
     			ref:'orderbiddingservicedetailwindow',
     			selector:'orderbiddingservicedetailwindow'
     		},
     		{
     			ref:'orderbiddinghandlerwindow',
     			selector:'orderbiddinghandlerwindow'
     		}
    ],
  
    init: function() {
        this.control({
        	'orderstore':function(store){
//        		console.log(store.title+'渲染完毕...');
        	},
        	'ordergrid':{//订单管理表格	
        		afterrender:function(gridpanel){
        			var orderStatus;
            		var orderNumber = '';
            		var serviceName = '';
            		var startTime = '';
            		var endTime = '';
            		gridpanel.getStore().on('beforeload',function(store){        
            			Ext.apply(store.proxy.extraParams, {//设置全局参数
            				orderNumber:orderNumber,
            				serviceName:serviceName,
            				orderStatus:orderStatus,
            				startTime:startTime,
            				endTime:endTime
            			});
            		});
            		gridpanel.getStore().loadPage(1);	//首先加载全部数据
            		gridpanel.down('button[action=search]').on('click',function(){ 
            			//多选下拉框默认传过来是数组
						orderStatus = gridpanel.down('combo[name=orderStatus]').getValue();
            			orderNumber = gridpanel.down('triggerfield[name=orderNumber]').getValue();
            			serviceName = gridpanel.down('triggerfield[name=serviceName]').getValue();
            			startTime = gridpanel.down('datefield[name=startdt]').getValue();
	 					endTime = gridpanel.down('datefield[name=enddt]').getValue();
//            			gridpanel.view.getFeature('group').expandAll();//展开全部
            			gridpanel.getStore().loadPage(1);
					},this);
					gridpanel.down('triggerfield[name=orderNumber]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {
        					if(!field.getValue()){
        						gridpanel.getStore().loadPage(1);
        					}else {
        						gridpanel.getStore().loadPage(1,{params:{orderNumber:field.getValue()}});
        					}	
	                    }
					},this);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件            		
            		if(cellIndex==2){//点击查看服务商品详情
            			if(record.data.orderSource == 1){//普通服务
	            			var window = this.getGoodsdetailwindow();
	            			if(!window){
		    					window = Ext.widget('goodsdetailwindow',{
		    						title: '服务商品详情['+record.get("service.serviceName")+']'		
		    					});    		
	    					}
	    					window.show();
							window.setTitle('服务商品详情['+record.get("service.serviceName")+']');
							window.getComponent('goodsdetailform').form.loadRecord(record);
            			}else if(record.data.orderSource == 2){//招标服务
            					var window = this.getOrderbiddingservicedetailwindow();
		            			if(!window){
			    					window = Ext.widget('orderbiddingservicedetailwindow',{
			    						title: '招标详情['+record.get('biddingService.bidNo')+']'		
			    					});    		
		    					}
		    					window.show();
								window.setTitle('招标详情['+record.get('biddingService.bidNo')+']');
								window.getComponent('orderbiddingservicedetailform').form.loadRecord(record);
								//招标价格为最小价格和最大价格之间显示
								window.down('displayfield[name=biddingServicePrice]').setValue(record.get('biddingService.minPrice')
									+'-'+record.get('biddingService.maxPrice'));
								//把值赋给bidServiceId
								bidServiceId = record.get('biddingService.id');
//					        	console.log(bidServiceId);
								
					        	var biddingservicedetailgrid = Ext.getCmp('biddingservicedetailgrid');
					        	biddingservicedetailgrid.store.load({params: {bidId:bidServiceId}});
					        	var responsebiddinggrid = Ext.getCmp('responsebiddinggrid');
					        	responsebiddinggrid.store.load({params: {status:7,bidId:bidServiceId }});
            			}
			        }   
			        if(cellIndex==1){//点击查看订单详情
            			var window = this.getOrderdetailwindow();
            			if(!window){
	    					window = Ext.widget('orderdetailwindow',{
	    						title: '订单详情['+record.data.orderNumber+']'		
	    					}); 
    					}
    					window.show();
						window.setTitle('订单详情['+record.data.orderNumber+']');
						window.getComponent('orderdetailform').form.loadRecord(record);
						var serviceId = window.down('hiddenfield[name=service.id]').getValue();
						if(serviceId == 0){//招标服务
							window.down('displayfield[name=service.enterprise.name]').setValue(record.get('biddingService.rname'));
						}
						//把值赋给orderId
						orderId = record.data.id;
//			        	console.log(orderId);
			        	var orderInfoGrid = Ext.getCmp('orderinfogrid');
			        	orderInfoGrid.store.load({params: {orderId:orderId}});
			        	var evaluationGrid = Ext.getCmp('evaluationgrid');
			        	evaluationGrid.store.load({params: {orderId:orderId}});
			        } 
            	}
        	},
        	'ordergrid actioncolumn':{//订单处理操作
        		handleclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
        			if(record.data.orderSource == 1){//普通服务
	        			var window = this.getOrderhandlerwindow();
						var record = grid.getStore().getAt(rowIndex);
						if(!window){
	    					window = Ext.widget('orderhandlerwindow',{
	    						title:'订单处理'   			
	    					});    		
	    				}					
						window.down('form').form.reset();
						window.show();
						window.down('form').getForm().loadRecord(record);
        			}else if(record.data.orderSource == 2){
        				var window = this.getOrderbiddinghandlerwindow();
						var record = grid.getStore().getAt(rowIndex);
						if(!window){
	    					window = Ext.widget('orderbiddinghandlerwindow',{
	    						title:'订单处理'   			
	    					});    		
	    				}					
						window.down('form').form.reset();
						window.show();
						window.down('form').getForm().loadRecord(record);
        			}
        		}
        	},
        	'orderhandlerwindow':{//处理普通服务订单窗口
        		afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){	
//            			console.log(window.down('hiddenfield[name=id]').getValue());
//            			console.log(window.down('htmleditor[name=handlerRemark]').getValue());
//            			console.log(window.down('combo[name=handlerMode]').getValue());
            			mask.show();
            			window.getComponent('orderhandlerform').form.submit({
				    		clientValidation: true,
						    url: 'order/edit',
						    params: {
						        orderId:window.down('hiddenfield[name=id]').getValue(),
						        handlerRemark:window.down('htmleditor[name=handlerRemark]').getValue(),
						        handlerMode:window.down('combo[name=handlerMode]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshOrder();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
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
            		window.down('button[action=cancel]').on('click',function(){
            			window.getComponent('orderhandlerform').form.reset();
            			window.hide();
            		
            		});            		
            	}
        	},
        	'orderbiddinghandlerwindow':{//处理招标服务订单窗口
        		afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){	
            			mask.show();
            			window.getComponent('orderbiddinghandlerform').form.submit({
				    		clientValidation: true,
						    url: 'order/edit',
						    params: {
						        orderId:window.down('hiddenfield[name=id]').getValue(),
						        handlerRemark:window.down('htmleditor[name=handlerRemark]').getValue(),
						        handlerMode:window.down('combo[name=handlerMode]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshOrder();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
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
            		window.down('button[action=cancel]').on('click',function(){
            			window.getComponent('orderbiddinghandlerform').form.reset();
            			window.hide();
            		
            		});            		
            	}
        	},
			'handlerappealgrid':{//申诉处理表格
            	afterrender:function(gridpanel){
            		var appealType;
            		var orderNumber = '';
            		gridpanel.getStore().on('beforeload',function(store){  
            			Ext.apply(store.proxy.extraParams, {//设置全局参数
            				orderStatus:9,
            				appealType:appealType,
            				orderNumber:orderNumber
            			});
            		});
            		gridpanel.getStore().loadPage(1);	//首先加载全部数据
            		gridpanel.down('button[action=search]').on('click',function(){  
            			appealType = gridpanel.down('combo[name=appealType]').getValue();
            			orderNumber = gridpanel.down('triggerfield[name=orderNumber]').getValue();
            			gridpanel.getStore().loadPage(1);
					},this);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件            		
            		if(cellIndex==2){//点击查看服务商品详情
            			if(record.get('goodsOrder.orderSource') == 1){
	            			var window = this.getHgoodsdetailwindow();
	            			if(!window){
		    					window = Ext.widget('hgoodsdetailwindow',{
		    						title: '服务商品详情['+record.get("goodsOrder.service.serviceName")+']'		
		    					});    		
	    					}
	    					window.show();
							window.setTitle('服务商品详情['+record.get("goodsOrder.service.serviceName")+']');
							window.getComponent('hgoodsdetailform').form.loadRecord(record);
            			}else if(record.get('goodsOrder.orderSource') == 2){
            				var window = this.getAppealbiddingservicedetailwindow();
		            			if(!window){
			    					window = Ext.widget('appealbiddingservicedetailwindow',{
			    						title: '招标详情['+record.get('goodsOrder.biddingService.bidNo')+']'		
			    					});    		
		    					}
		    					window.show();
								window.setTitle('招标详情['+record.get('goodsOrder.biddingService.bidNo')+']');
								window.getComponent('appealbiddingservicedetailform').form.loadRecord(record);
								//招标价格为最小价格和最大价格之间显示
								window.down('displayfield[name=biddingServicePrice]').setValue(record.get('goodsOrder.biddingService.minPrice')
									+'-'+record.get('goodsOrder.biddingService.maxPrice'));
								//把值赋给bidServiceId
								bidServiceId = record.get('goodsOrder.biddingService.id');
//					        	console.log(bidServiceId);
					        	var biddingservicedetailgrid = Ext.getCmp('biddingservicedetailgrid');
					        	biddingservicedetailgrid.store.load({params: {bidId:bidServiceId}});
					        	var responsebiddinggrid = Ext.getCmp('responsebiddinggrid');
					        	responsebiddinggrid.store.load({params: {status:7,bidId:bidServiceId }});
            			}
			        }   
			        if(cellIndex==1){//点击查看订单详情
            			var window = this.getHorderdetailwindow();
            			if(!window){
	    					window = Ext.widget('horderdetailwindow',{
	    						title: '订单详情['+record.get('goodsOrder.orderNumber')+']'		
	    					});    		
    					}
    					window.show();
						window.setTitle('订单详情['+record.get('goodsOrder.orderNumber')+']');
						window.getComponent('horderdetailform').form.loadRecord(record);
						var serviceId = window.down('hiddenfield[name=goodsOrder.service.id]').getValue();
							if(serviceId == 0){//招标服务
								window.down('displayfield[name=goodsOrder.service.enterprise.name]').setValue(record.get('goodsOrder.biddingService.rname'));
							}
						//把值赋给orderId
						orderId = record.get('goodsOrder.id');
//			        	console.log(orderId);
			        	var orderInfoGrid = Ext.getCmp('orderinfogrid');
			        	orderInfoGrid.store.load({params: {orderId:orderId}});
			        	var evaluationGrid = Ext.getCmp('evaluationgrid');
			        	evaluationGrid.store.load({params: {orderId:orderId}});
			        } 
            	}
            },
            'handlerappealgrid actioncolumn':{//申诉处理操作
        		appealclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
        			if(record.get('goodsOrder.orderSource') == 1){//普通服务的申诉处理弹窗
	        			var window = this.getAppealhandlerwindow();
						var record = grid.getStore().getAt(rowIndex);
						if(!window){
	    					window = Ext.widget('appealhandlerwindow',{
	    						title:'申诉处理'   			
	    					});    		
	    				}					
						window.down('form').form.reset();
						window.show();
						window.down('form').getForm().loadRecord(record);
        			}else if(record.get('goodsOrder.orderSource') == 2){//2.招标服务的申诉处理弹窗
						var window = this.getDappealhandlerwindow();
						var record = grid.getStore().getAt(rowIndex);
						if(!window){
	    					window = Ext.widget('dappealhandlerwindow',{
	    						title:'申诉处理'   			
	    					});    		
	    				}					
						window.down('form').form.reset();
						window.show();
						window.down('form').getForm().loadRecord(record);
//        				var window = this.getBiddingappealhandlerwindow();
//	            		if(!window){
//		    				window = Ext.widget('biddingappealhandlerwindow',{
//		    					title: '招标详情['+record.data.bidNo+']'		
//		    				});    		
//	    				}
//	    				window.show();
//						window.setTitle('招标详情['+record.data.bidNo+']');
//						window.getComponent('biddingappealhandlerform').form.loadRecord(record);
//						//把值赋给bidServiceId
//						bidServiceId = record.data.id;
//				        var biddingservicedetailgrid = Ext.getCmp('biddingservicedetailgrid');
//				        biddingservicedetailgrid.store.load({params: {bidId:bidServiceId}});
//				        var responsebiddinggrid = Ext.getCmp('responsebiddinggrid');
//				        responsebiddinggrid.store.load({params: {status:7,bidId:bidServiceId }});
//				        var biddingappealgrid = Ext.getCmp('biddingappealgrid');
//				        biddingappealgrid.store.load({params: {bidId:bidServiceId}});
	        		}
        		}
        	},
        	'appealhandlerwindow':{//申诉处理窗口
        		afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){	
            			mask.show();
            			window.getComponent('appealhandlerform').form.submit({
				    		clientValidation: true,
						    url: 'order/edit',
						    params: {
						    	appealId:window.down('hiddenfield[name=id]').getValue(),
						        orderId:window.down('hiddenfield[name=goodsOrder.id]').getValue(),
						        handlerRemark:window.down('htmleditor[name=handlerRemark]').getValue(),
						        handlerMode:window.down('combo[name=handlerMode]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshHandlerAppeal();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
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
            		window.down('button[action=cancel]').on('click',function(){
            			window.getComponent('appealhandlerform').form.reset();
            			window.hide();
            		
            		});            		
            	}
        	},
        	'dappealhandlerwindow':{//招标服务申诉处理窗口
        		afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){	
            			mask.show();
            			window.getComponent('dappealhandlerform').form.submit({
				    		clientValidation: true,
						    url: 'order/edit',
						    params: {
						    	appealId:window.down('hiddenfield[name=id]').getValue(),
						        orderId:window.down('hiddenfield[name=goodsOrder.id]').getValue(),
						        handlerRemark:window.down('htmleditor[name=handlerRemark]').getValue(),
						        handlerMode:window.down('combo[name=handlerMode]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshHandlerAppeal();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
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
            		window.down('button[action=cancel]').on('click',function(){
            			window.getComponent('dappealhandlerform').form.reset();
            			window.hide();
            		
            		});            		
            	}
        	},
        	'qappealgrid':{//申诉查询表格
            	afterrender:function(gridpanel){
            		var orderNumber = '';
            		var startTime = '';
            		var endTime = '';
            		var appealType;
            		var buyer = '';
            		var seller = '';
            		gridpanel.getStore().on('beforeload',function(store){  
            			Ext.apply(store.proxy.extraParams, {//设置全局参数
            				appealType:appealType,
            				orderNumber:orderNumber,
            				startTime:startTime,
            				endTime:endTime,
            				buyer:buyer,
            				seller:seller
            			});
            		});
            		gridpanel.getStore().loadPage(1);	//首先加载全部数据
            		gridpanel.down('button[action=search]').on('click',function(){  
            			appealType = gridpanel.down('appealtype[name=appealType]').getValue(),
            			orderNumber = gridpanel.down('triggerfield[name=orderNumber]').getValue();
            			startTime = gridpanel.down('datefield[name=startdt]').getValue();
	 					endTime = gridpanel.down('datefield[name=enddt]').getValue();
	 					buyer = gridpanel.down('triggerfield[name=buyer]').getValue();
	 					seller = gridpanel.down('triggerfield[name=seller]').getValue();

//            			gridpanel.view.getFeature('group').expandAll();//展开全部
            			gridpanel.getStore().loadPage(1);
					},this);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件            		
	            		if(cellIndex==2){//点击查看服务商品详情
	            			if(record.data.oaNum.indexOf('S')!=-1){
		            			var window = this.getHgoodsdetailwindow();
		            			if(!window){
			    					window = Ext.widget('hgoodsdetailwindow',{
			    						title: '服务商品详情['+record.get("goodsOrder.service.serviceName")+']'		
			    					});    		
		    					}
		    					window.show();
								window.setTitle('服务商品详情['+record.get("goodsOrder.service.serviceName")+']');
								window.getComponent('hgoodsdetailform').form.loadRecord(record);
				        	}else{
					        	var window = this.getAppealbiddingservicedetailwindow();
		            			if(!window){
			    					window = Ext.widget('appealbiddingservicedetailwindow',{
			    						title: '招标详情['+record.get('goodsOrder.biddingService.bidNo')+']'		
			    					});    		
		    					}
		    					window.show();
								window.setTitle('招标详情['+record.get('goodsOrder.biddingService.bidNo')+']');
								window.getComponent('appealbiddingservicedetailform').form.loadRecord(record);
								//招标价格为最小价格和最大价格之间显示
								window.down('displayfield[name=biddingServicePrice]').setValue(record.get('goodsOrder.biddingService.minPrice')
									+'-'+record.get('goodsOrder.biddingService.maxPrice'));
								//把值赋给bidServiceId
								bidServiceId = record.get('goodsOrder.biddingService.id');
//					        	console.log(bidServiceId);
					        	var biddingservicedetailgrid = Ext.getCmp('biddingservicedetailgrid');
					        	biddingservicedetailgrid.store.load({params: {bidId:bidServiceId}});
					        	var responsebiddinggrid = Ext.getCmp('responsebiddinggrid');
					        	responsebiddinggrid.store.load({params: {status:7,bidId:bidServiceId }});
				        	}  
	            		}
            		
			        if(cellIndex==1){//点击查看订单详情
	            		
	            			var window = this.getHorderdetailwindow();
	            			if(!window){
		    					window = Ext.widget('horderdetailwindow',{
		    						title: '订单详情['+record.get('goodsOrder.orderNumber')+']'		
		    					});    		
	    					}
	    					window.show();
							window.setTitle('订单详情['+record.get('goodsOrder.orderNumber')+']');
							window.getComponent('horderdetailform').form.loadRecord(record);
							var serviceId = window.down('hiddenfield[name=goodsOrder.service.id]').getValue();
//							console.log(serviceId);
							if(serviceId == 0){//招标服务
								window.down('displayfield[name=goodsOrder.service.enterprise.name]').setValue(record.get('goodsOrder.biddingService.rname'));
							}
							
							//把值赋给orderId
							orderId = record.get('goodsOrder.id');
	//			        	console.log(orderId);
				        	var orderInfoGrid = Ext.getCmp('orderinfogrid');
				        	orderInfoGrid.store.load({params: {orderId:orderId}});
				        	var evaluationGrid = Ext.getCmp('evaluationgrid');
				        	evaluationGrid.store.load({params: {orderId:orderId}});
				        
            		}
            	}
            },
            'apprbiddinggrid':{//招标审核grid
            	afterrender:function(gridpanel){
            		var bidNo = '';
            		var status = 2;
            		var name = '';
            		var startTime = '';
            		var endTime = '';
            		gridpanel.getStore().on('beforeload',function(store){  
            			Ext.apply(store.proxy.extraParams, {//设置全局参数
            				bidNo:bidNo,
            				name:name,
            				status:status,
            				startTime:startTime,
            				endTime:endTime
            			});
            		});
            		gridpanel.getStore().loadPage(1);	//首先加载全部数据
            		gridpanel.down('button[action=search]').on('click',function(){  
            			bidNo = gridpanel.down('triggerfield[name=bidNo]').getValue();
            			name = gridpanel.down('triggerfield[name=name]').getValue();
            			startTime = gridpanel.down('datefield[name=startdt]').getValue();
	 					endTime = gridpanel.down('datefield[name=enddt]').getValue();
//            			gridpanel.view.getFeature('group').expandAll();//展开全部
            			gridpanel.getStore().loadPage(1);
					},this);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){
            		 if(cellIndex==1){//点击查看招单详情
            			var window = this.getBiddingservicedetailwindow();
            			if(!window){
	    					window = Ext.widget('biddingservicedetailwindow',{
	    						title: '招标详情['+record.data.bidNo+']'		
	    					});    		
    					}
    					window.show();
						window.setTitle('招标详情['+record.data.bidNo+']');
						window.getComponent('biddingservicedetailform').form.loadRecord(record);
						//招标价格为最小价格和最大价格之间显示
						window.down('displayfield[name=biddingServicePrice]').setValue(record.get('minPrice')
							+'-'+record.get('maxPrice'));
						//把值赋给bidServiceId
						bidServiceId = record.data.id;
//			        	console.log(bidServiceId);
			        	var biddingservicedetailgrid = Ext.getCmp('biddingservicedetailgrid');
			        	biddingservicedetailgrid.store.load({params: {bidId:bidServiceId}});
			        	var responsebiddinggrid = Ext.getCmp('responsebiddinggrid');
			        	responsebiddinggrid.store.load({params: {status:7,bidId:bidServiceId }});
			        } 
            	}
            },
            'apprbiddinggrid actioncolumn':{//招标审核
            	handlerclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
        			var window = this.getBiddingserviceauditwindow();
					var record = grid.getStore().getAt(rowIndex);
					if(!window){
    					window = Ext.widget('biddingserviceauditwindow',{
    						title:'招标详情'   			
    					});    		
    				}					
					window.down('form').form.reset();
					window.show();
					window.down('form').getForm().loadRecord(record);
        		}
            },
 			'biddingservicegrid':{//标单管理表格
 				afterrender:function(gridpanel){
            		var bidNo = '';
            		var status;
            		var name = '';
            		var startTime = '';
            		var endTime = '';
            		gridpanel.getStore().on('beforeload',function(store){  
            			Ext.apply(store.proxy.extraParams, {//设置全局参数
            				bidNo:bidNo,
            				name:name,
            				status:status,
            				startTime:startTime,
            				endTime:endTime
            			});
            		});
            		gridpanel.getStore().loadPage(1);	//首先加载全部数据
            		gridpanel.down('button[action=search]').on('click',function(){  
            			status = gridpanel.down('combo[name=status]').getValue(),
            			bidNo = gridpanel.down('triggerfield[name=bidNo]').getValue();
            			name = gridpanel.down('triggerfield[name=name]').getValue();
            			startTime = gridpanel.down('datefield[name=startdt]').getValue();
	 					endTime = gridpanel.down('datefield[name=enddt]').getValue();
//            			gridpanel.view.getFeature('group').expandAll();//展开全部
            			gridpanel.getStore().loadPage(1);
					},this);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件            		  
			        if(cellIndex==1){//点击查看招单详情
            			var window = this.getBiddingservicedetailwindow();
            			if(!window){
	    					window = Ext.widget('biddingservicedetailwindow',{
	    						title: '招标详情['+record.data.bidNo+']'		
	    					});    		
    					}
    					window.show();
						window.setTitle('招标详情['+record.data.bidNo+']');
						window.getComponent('biddingservicedetailform').form.loadRecord(record);
						//招标价格为最小价格和最大价格之间显示
								window.down('displayfield[name=biddingServicePrice]').setValue(record.get('minPrice')
									+'-'+record.get('maxPrice'));
						//把值赋给bidServiceId
						bidServiceId = record.data.id;
//			        	console.log(bidServiceId);
			        	var biddingservicedetailgrid = Ext.getCmp('biddingservicedetailgrid');
			        	biddingservicedetailgrid.store.load({params: {bidId:bidServiceId}});
			        	var responsebiddinggrid = Ext.getCmp('responsebiddinggrid');
			        	responsebiddinggrid.store.load({params: {status:7,bidId:bidServiceId }});
			        } 
            	}
 			},
 			'biddingservicegrid actioncolumn':{	//标单管理处理事件
        		//其他处理
        		otherclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
        			var window = this.getBiddingotherhandlerwindow();
            		if(!window){
	    				window = Ext.widget('biddingotherhandlerwindow',{
	    					title: '招标详情['+record.data.bidNo+']'		
	    				});    		
    				}
    				window.show();
					window.setTitle('招标详情['+record.data.bidNo+']');
					window.getComponent('biddingotherhandlerform').form.loadRecord(record);
					//把值赋给bidServiceId
					bidServiceId = record.data.id;
			        var biddingservicedetailgrid = Ext.getCmp('biddingservicedetailgrid');
			        biddingservicedetailgrid.store.load({params: {bidId:bidServiceId}});
			        var responsebiddinggrid = Ext.getCmp('responsebiddinggrid');
			        responsebiddinggrid.store.load({params: {status:7,bidId:bidServiceId }});
        		}
 			},
 			'biddingserviceauditwindow':{//招标审核窗口
 				afterrender:function(window){
 					window.down('button[action=submit]').on('click',function(){
 						var record = window.down('form').getRecord();
 						openApprBiddingServiceWindow('通过',record);
 					});
        			window.down('button[action=esc]').on('click',function(){
            			var record = window.down('form').getRecord();
 						openApprBiddingServiceWindow('驳回',record);
            		});  
        		}	
 			},
 			'apprbiddingservicewindow':{//审核信息窗口
 				afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){	
//            			console.log(window.down('hiddenfield[name=id]').getValue());
//            			console.log(window.down('hiddenfield[name=status]').getValue());
//            			console.log(window.down('textareafield[name=remark]').getValue());
            			mask.show();
            			window.getComponent('apprbiddingserviceform').form.submit({
				    		clientValidation: true,
						    url: 'bidding/biddingAudit',
						    params: {
						        id:window.down('hiddenfield[name=id]').getValue(),
						        status:window.down('hiddenfield[name=status]').getValue(),
						        remark:window.down('textareafield[name=remark]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();	
							       var parentWin = Ext.ComponentQuery.query('biddingserviceauditwindow')[0];
            					   if(parentWin) parentWin.close();
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshApprBiddingGrid();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
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
            		window.down('button[action=cancel]').on('click',function(){
            			window.getComponent('apprbiddingserviceform').form.reset();
            			window.hide();
            		
            		});            		
            	}
 			},
        	'biddingotherhandlerwindow':{//招标管理其他处理窗口
        		afterrender:function(window){
        			var me = this;
        			window.down('button[action=cancel]').on('click',function(){
            			var record = window.down('form').getRecord();
 						openHandlerBiddingServiceWindow('取消招标',record);
            		});  
            		window.down('button[action=return]').on('click',function(){
            			window.getComponent('biddingotherhandlerform').form.reset();
            			window.hide();
            		});
        		}
        	},
        	'biddingappealhandlerwindow':{//招标管理申诉处理窗口
        		afterrender:function(window){
        			var me = this;
            		window.down('button[action=close]').on('click',function(){	
            			var record = window.down('form').getRecord();
 						openAppealHandlerBiddingServiceWindow('关闭交易',record);
            		});  
        			window.down('button[action=cancel]').on('click',function(){
            			var record = window.down('form').getRecord();
 						openAppealHandlerBiddingServiceWindow('取消招标',record);
            		}); 
            		window.down('button[action=closeWin]').on('click',function(){
            			window.getComponent('biddingappealhandlerform').form.reset();
            			window.hide();
            		});
        		}
        	},
        	'handlerbiddingservicewindow':{//其他处理提交备注窗口
        		afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
//            		console.log(window.down('hiddenfield[name=status]').getValue());
            		window.down('button[action=submit]').on('click',function(){	
            			mask.show();
//            			console.log(window.down('hiddenfield[name=id]').getValue());
//            			console.log(window.down('hiddenfield[name=status]').getValue());
//            			console.log(window.down('textareafield[name=remark]').getValue());
            			window.getComponent('handlerbiddingserviceform').form.submit({
				    		clientValidation: true,
						    url: 'bidding/handlerBidding',
						    params: {
						        id:window.down('hiddenfield[name=id]').getValue(),
						        status:window.down('hiddenfield[name=status]').getValue(),
						        remark:window.down('textareafield[name=remark]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();	
							       var parentWin = Ext.ComponentQuery.query('biddingotherhandlerwindow')[0];
            					   if(parentWin) parentWin.close();
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshBiddingService();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
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
            		window.down('button[action=cancel]').on('click',function(){
            			window.getComponent('handlerbiddingserviceform').form.reset();
            			window.hide();
            		
            		});            		
            	}
        	},
        	'appealhandlerbiddingservicewindow':{
        		afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){	
//            			console.log(window.down('hiddenfield[name=id]').getValue());
//            			console.log(window.down('hiddenfield[name=status]').getValue());
//            			console.log(window.down('textareafield[name=remark]').getValue());
            			mask.show();
            			window.getComponent('appealhandlerbiddingserviceform').form.submit({
				    		clientValidation: true,
						    url: 'bidding/appealHandlerBidding',
						    params: {
						        id:window.down('hiddenfield[name=id]').getValue(),
						        status:window.down('hiddenfield[name=status]').getValue(),
						        remark:window.down('textareafield[name=remark]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();	
							       var parentWin = Ext.ComponentQuery.query('biddingappealhandlerwindow')[0];
            					   if(parentWin) parentWin.close();
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshBiddingService();
						    	}else{
						    		Ext.Msg.alert('','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
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
            		window.down('button[action=cancel]').on('click',function(){
            			window.getComponent('appealhandlerbiddingserviceform').form.reset();
            			window.hide();
            		
            		});            		
            	}
        	}
        	
 		})    
    },
    refreshOrder:function(){
    	this.getOrdergrid().getStore().reload();
    },
    refreshApprBiddingGrid:function(){
    	this.getApprbiddinggrid().getStore().reload();
    },
    refreshBiddingService:function(){
    	this.getBiddingservicegrid().getStore().reload();
    },
    refreshHandlerAppeal:function(){
    	this.getHandlerappealgrid().getStore().reload();
    }
});

function openApprBiddingServiceWindow(title,record){
	var apprwindow = null;
 		var windows = Ext.ComponentQuery.query('apprbiddingservicewindow');
		if(windows.length > 0 ){
			apprwindow = windows[0];
			apprwindow.setTitle(title);
			apprwindow.show();
		} else {
			apprwindow = Ext.widget('apprbiddingservicewindow',{title:title}).show();
		}	
	    var form = apprwindow.getComponent('apprbiddingserviceform').form;
		form.reset();
		record.data.status = apprwindow.title.indexOf('通过') > -1 ? '4' :'3';
		form.loadRecord(record);
}

function openHandlerBiddingServiceWindow(title,record){//打开其他处理信息窗口
	var apprwindow = null;
 		var windows = Ext.ComponentQuery.query('handlerbiddingservicewindow');
		if(windows.length > 0 ){
			apprwindow = windows[0];
			apprwindow.setTitle(title);
			apprwindow.show();
		} else {
			apprwindow = Ext.widget('handlerbiddingservicewindow',{title:title}).show();
		}	
	    var form = apprwindow.getComponent('handlerbiddingserviceform').form;
		form.reset();
		record.data.status = apprwindow.title.indexOf('关闭交易') > -1 ? '10' :'11';
		form.loadRecord(record);
}
function openAppealHandlerBiddingServiceWindow(title,record){//打开标单申诉处理信息窗口
	var apprwindow = null;
 		var windows = Ext.ComponentQuery.query('appealhandlerbiddingservicewindow');
		if(windows.length > 0 ){
			apprwindow = windows[0];
			apprwindow.setTitle(title);
			apprwindow.show();
		} else {
			apprwindow = Ext.widget('appealhandlerbiddingservicewindow',{title:title}).show();
		}	
	    var form = apprwindow.getComponent('appealhandlerbiddingserviceform').form;
		form.reset();
		record.data.status = apprwindow.title.indexOf('关闭交易') > -1 ? '10' :'11';
		form.loadRecord(record);
}
