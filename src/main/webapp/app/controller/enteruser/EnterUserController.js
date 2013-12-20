var parentId,parentName,userStatus,enterType;		//主账号ID,主账号用户名,主账号的用户状态
var staffgrid_g;//全局子账号表格
Ext.define('plat.controller.enteruser.EnterUserController', {
    extend: 'Ext.app.Controller',    
    xtype:'enterusercontroller',
    
    stores: [
				'enteruser.DisableUserStore',
				'enteruser.QEnterUserStore',
				'enteruser.EnterUserStore',
				'enteruser.EnterStaffStore',
				'enteruser.EnterpriseStore',
				'sme.SMEIdentifieStore'
			],
    views: [
    			'enteruser.EnterUserGrid',
    			'enteruser.EnterStaffGrid',
    			'enteruser.EnterUserPanel',
    			'enteruser.DisableUserGrid',
    			'enteruser.QEnterUserGrid',
    			'layout.combo.EnterUserTypeCombo',
    			'layout.combo.OrgEnterRoleCombo',
    			'layout.combo.PropertyCombo',
    			'layout.combo.IndustryTypeCombo',
    			'layout.combo.BusinessPatternCombo',
    			'enteruser.UserEditWindow',
    			'enteruser.StaffAddWindow',
    			'enteruser.StaffEditWindow',
    			'enteruser.StaffDetailWindow',
    			'enteruser.UserDetailWindow',
    			'enteruser.UserEditWindow',
    			'enteruser.UserEditWindow',
    			'enteruser.UserAddWindow',
    			'enteruser.PersonalUserDetailWindow',
    			'enteruser.PersonalUserAddWindow',
    			'enteruser.PersonalUserEditWindow',
    			'sme.SMEIdentifieGrid',
    			'sme.SMEDetailWindow',
    			'sme.SMEWindow'
    		],    
     refs: [
     		{
     			ref:'qenteruserstore',
     			selector:'qenteruserstore'
     		},
     		{
     			ref:'qenterusergrid',
     			selector:'qenterusergrid'
	     	},
	     	{
	     		ref:'enterstaffgrid',
	     		selector:'enterstaffgrid'
	     	},
	     	{
	     		ref:'staffaddwindow',
	     		selector:'staffaddwindow'
	     	},
	     	{
	     		ref:'staffdetailwindow',
	     		selector:'staffdetailwindow'

	     	},
	     	{
	     		ref:'staffeditwindow',
	     		selector:'staffeditwindow'
	     	},
	     	{
	     		ref:'userdetailwindow',
	     		selector:'userdetailwindow'
	     	},
	     	{
	     		ref:'useraddwindow',
	     		selector:'useraddwindow'
	     	},
	     	{
	     		ref:'usereditwindow',
	     		selector:'usereditwindow'
	     	},
	     	{
	     		ref:'enterusergrid',
	     		selector:'enterusergrid'
	     	},
	     	{
	     		ref:'disableusergrid',
	     		selector:'disableusergrid'
	     	},
	     	{
	     		ref:'personaluserdetailwindow',
	     		selector:'personaluserdetailwindow'
	     	},
	     	{
	     		ref:'personaluseraddwindow',
	     		selector:'personaluseraddwindow'
	     	},
	     	{
	     		ref:'personalusereditwindow',
	     		selector:'personalusereditwindow'
	     	},{
	     		ref:'smeidentifiegrid',
	     		selector:'smeidentifiegrid'
	     	},{
	     		ref:'smedetailwindow',
	     		selector:'smedetailwindow'
	     	},{
	     		ref:'smewindow',
	     		selector:'smewindow'
	     	}
    ],
  
    init: function() {
        this.control({
        	'enterusergrid':{	//会员列表管理
            	afterrender:function(gridpanel){
//            		console.log(gridpanel.title + '渲染完毕...');
            		var uName,enterpriseName,enterpriseType,industryType;
            		var store = gridpanel.getStore();
            		store.on('beforeload',function(store){
            			Ext.apply(store.proxy.extraParams, {uName:uName,enterpriseName:enterpriseName,enterpriseType:enterpriseType,industryType:industryType,userStatus:'1,2'});
            		});
            		store.loadPage(1,{
            			callback:function(r){
            				if(r.length>0){
			            		gridpanel.getSelectionModel().select(0,true);
			            		gridpanel.getView().focusRow(0);//获取焦点
            				}
            			}
            		});
            		
            		gridpanel.down('button[action=search]').on('click',function(){
            			uName = gridpanel.down('triggerfield[name=uName]').getValue();
            			enterpriseName = gridpanel.down('triggerfield[name=enterpriseName]').getValue();
            			enterpriseType = gridpanel.down('combo[name=enterpriseType]').getValue();
            			industryType = gridpanel.down('combo[name=industryType]').getValue();
            			store.loadPage(1,{params:{uName:uName,enterpriseName:enterpriseName,enterpriseType:enterpriseType,industryType:industryType},
            				callback:function(r){//默认选中第一行
	            				if(r.length>0){
				            		gridpanel.getSelectionModel().select(0,true);
				            		gridpanel.getView().focusRow(0);//获取焦点
	            				}
            				}
            			});            			
					},this);
					gridpanel.down('triggerfield[name=uName]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {        					
        					if(!field.getValue()){
        						store.loadPage(1,{params:{uName:''},
	        						callback:function(r){//默认选中第一行
			            				if(r.length>0){
						            		gridpanel.getSelectionModel().select(0,true);
						            		gridpanel.getView().focusRow(0);//获取焦点
			            				}
	            					}
        						});
        					}else {
        						uName = gridpanel.down('triggerfield[name=uName]').getValue();
        						store.loadPage(1,{params:{uName:uName},
        							callback:function(r){//默认选中第一行
			            				if(r.length>0){
						            		gridpanel.getSelectionModel().select(0,true);
						            		gridpanel.getView().focusRow(0);//获取焦点
			            				}
            						}
        						});
        					}
                       		
	                    }
					},this);
					gridpanel.down('triggerfield[name=enterpriseName]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {        					
        					if(!field.getValue()){
        						store.loadPage(1,{params:{enterpriseName:''},
	        						callback:function(r){//默认选中第一行
			            				if(r.length>0){
						            		gridpanel.getSelectionModel().select(0,true);
						            		gridpanel.getView().focusRow(0);//获取焦点
			            				}
	            					}
        						});
        					}else {
        						enterpriseName = gridpanel.down('triggerfield[name=enterpriseName]').getValue();
        						store.loadPage(1,{params:{enterpriseName:enterpriseName},
        							callback:function(r){//默认选中第一行
			            				if(r.length>0){
						            		gridpanel.getSelectionModel().select(0,true);
						            		gridpanel.getView().focusRow(0);//获取焦点
			            				}
            						}
        						});
        					}
                       		
	                    }
					},this);
					gridpanel.down('button[action=add]').down('[name=enterUser]').on('click',function(){
						var window = this.getUseraddwindow();
						if(!window){
	    					window = Ext.widget('useraddwindow',{
	    						 			
	    					});    		
    					}
						window.down('form').getForm().reset();
						window.show();
					},this);
					//个人用户添加
					gridpanel.down('button[action=add]').down('[name=personalUser]').on('click',function(){
						this.addPersonalUser();					
					},this);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件            		
            		if(record.data.isPersonal){//个人用户
            			if(cellIndex == 1){//查看个人用户详情
            				var window = this.getPersonaluserdetailwindow();
            				if(!window){
            					window = Ext.widget('personaluserdetailwindow',{
									title:'用户详情'+'['+record.data.userName+']'         					
            					})
            				}
            				window.setTitle('用户详情'+'['+record.data.userName+']');
            				window.down('form').getForm().reset();    					
	    					window.down('form').getForm().loadRecord(record);
	    					window.show();	
            			}
            		}else{//企业用户
	            		if(cellIndex==3){            			
	            			var window = this.getUserdetailwindow();
	            			if(!window){
		    					window = Ext.widget('userdetailwindow',{
		    						title: '企业信息'			
		    					});    		
	    					}
	    					window.down('form').getForm().reset();    					
	    					window.down('form').getForm().loadRecord(record);
	    					window.show();	
				        }
            		}
            	},	
            	select:this.showStaff		//行选中事件显示该主账号下的所有子账号信息
            },
            'enterusergrid actioncolumn':{
            	editclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		if(record.data.isPersonal){//个人用户
            			var window = this.getPersonalusereditwindow();
						var record = grid.getStore().getAt(rowIndex);
						if(!window){
	    					window = Ext.widget('personalusereditwindow',{
	    						title:'编辑用户信息'   			
	    					});    		
	    				}					
						window.down('form').form.reset();
						window.show();
						window.down('form').getForm().loadRecord(record);
            		}else{//企业用户
	            		var window = this.getUsereditwindow();
						var record = grid.getStore().getAt(rowIndex);
						if(!window){
	    					window = Ext.widget('usereditwindow',{
	    						title:'编辑用户信息'   			
	    					});    		
	    				}					
						window.down('form').form.reset();
						window.show();
						window.down('form').getForm().loadRecord(record);
            		}
            	},
            	disableclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){            		
            		var record = grid.getStore().getAt(rowIndex);
			                    Ext.MessageBox.confirm('警告','确定禁用【 '+record.data.userName+' 】吗?(将会禁用旗下子账号)',function(btn){
						    		if(btn == 'yes'){								    			
						    			record.set('userStatus',2);
						    			console.log(record.data);
						    			grid.getStore().update({
								    		params:record.data,
								    		callback:function(record,operation){
								    			var result =Ext.JSON.decode(operation.response.responseText);								    						
								    			if(result.success){
								    				Ext.example.msg('','<p align="center">会员【'+operation.params.userName+'】禁用成功</p>');
								    				var disablegrid = Ext.getCmp('jylbgl');		//获取禁用会员grid
								    				if(disablegrid){
								    					disablegrid.store.loadPage(1);
								    				}			
								    				//获取子账号grid,进行同步操作
								    				var staffgrid = Ext.getCmp('enterstaffgrid');
								    				if(staffgrid){
								    					staffgrid.store.load();
								    				}
								    			}else {
								    				Ext.Msg.alert('提示','会员【'+operation.params.userName+'】禁用失败');
								    			}	
								    		}
								    	});
//						    			grid.getStore().sync({
//								    			success: function(){
//								    				console.log('禁用用户【'+record.data.userName+'】成功');
//								    				Ext.example.msg('','<p align="center">用户【'+record.data.userName+'】禁用成功</p>');
//								    				//删除成功后立即设置子账号消失
//								    				staffgrid_g.setVisible(false);
//								    				},
//								    			failure:function(){
//								    				console.log('禁用用户【'+record.data.userName+'】失败');
//								    				Ext.example.msg('','<p align="center">用户【'+record.data.userName+'】禁用失败</p>');
//								    				}
//							    			});
									}
								});
            		},
            	deleteclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var record = grid.getStore().getAt(rowIndex);
			                    Ext.MessageBox.confirm('警告','确定删除【 '+record.data.userName+' 】吗?(将会删除旗下子账号)',function(btn){
						    		if(btn == 'yes'){
						    			record.set('userStatus',3);
						    			grid.getStore().update({
								    		params:record.data,
								    		callback:function(record,operation){
								    			var result =Ext.JSON.decode(operation.response.responseText);								    						
								    			if(result.success){
								    				Ext.example.msg('','<p align="center">会员【'+operation.params.userName+'】删除成功</p>');
								    				grid.getStore().remove(record);
								    				//获取子账号grid,进行同步操作
								    				var staffgrid = Ext.getCmp('enterstaffgrid');
								    				if(staffgrid){
								    					staffgrid.store.load();
								    				}
								    			}else {
								    				Ext.Msg.alert('提示','会员【'+operation.params.userName+'】删除失败');
								    			}	
								    		}
								    	});			
									}
								});
            	}
            },
            'enterusergrid triggerfield':{            	
            	afterrender:function(triggerfield){
//            		console.log(triggerfield.getName()+'渲染完毕...');
            		triggerfield.triggerCell.setDisplayed(false);
            	},
            	dirtychange:function(triggerfield){
            		//console.log('');
            		if(triggerfield.triggerCell!=null){
            			
            			if(triggerfield.getValue!=null&&!triggerfield.display){	//当值不为空，dispalyed为假时，显示清除按钮
            				triggerfield.display=true;
            				triggerfield.triggerCell.setDisplayed(triggerfield.display);
            				triggerfield.setWidth(200);            				
            			}else if((triggerfield.getValue!=null)&&(triggerfield.display)){	//当值不为空，displayed为真时，隐藏清除按钮
            				triggerfield.display=false;
            				triggerfield.triggerCell.setDisplayed(triggerfield.display);
            				triggerfield.setWidth(200);
            			}            			
            		}
            		
            	}        
            },
            'enterstaffgrid':{
            	afterrender:function(gridpanel){
					gridpanel.down('button[action=add]').on('click',function(){
                		this.addStaff();
					},this);		
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.showStaffDetail(record);
            	}
            },
            'enterstaffgrid actioncolumn':{
            	resetpwd:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var record = grid.getStore().getAt(rowIndex);
			        Ext.MessageBox.confirm('警告','确定子账号【'+record.data.userName+'】重置密码吗?',function(btn){
					if(btn == 'yes'){								    			
						    record.set('password','e10adc3949ba59abbe56e057f20f883e');
						    grid.getStore().update({
								params:record.data,
								callback:function(record,operation){
									var result =Ext.JSON.decode(operation.response.responseText);
									if(result.success){
								   	 Ext.example.msg('','<p align="center">子账号【'+operation.params.userName+'】重置密码成功</p>');
									}else {
								   	 Ext.Msg.alert('提示','子账号【'+operation.params.userName+'】重置密码失败');
									}	
								}
							});							    			
						}
					});
            	},
           		editclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var window = this.getStaffeditwindow();
					var record = grid.getStore().getAt(rowIndex);
					if(!window){
    					window = Ext.widget('staffeditwindow',{
    						title:'编辑子账号:【'+record.data.userName+"】"   			
    					});    		
    				};
    				
					window.getComponent('staffeditform').form.reset();
					var staffEditForm = window.getComponent('staffeditform');
					
					staffEditForm.down('combobox[name=staffRole.id]').getStore()
					.setProxy({	
						type:'ajax',
						actionMethods: {  
							read: 'POST'
						},		
						extraParams:{enterpriseType:enterType},
						url:'staff/loadStaffRole'	
					});
					staffEditForm.down('combobox[name=staffRole.id]').getStore().load();

					window.show();
					window.getComponent('staffeditform').getForm().loadRecord(record);
					var managerId = window.down('hiddenfield[name=manager.id]').getValue();
					if(managerId == 0){//分配人不是平台人员
						window.down('displayfield[name=fpname]').setValue(record.get('assigner.userName'));
					}else{
						window.down('displayfield[name=fpname]').setValue(record.get('manager.username'));
					}
            	},
            	disableclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var record = grid.getStore().getAt(rowIndex);
			        Ext.MessageBox.confirm('警告','确定禁用子账号【'+record.data.userName+'】吗?',function(btn){
					if(btn == 'yes'){								    			
						    record.set('staffStatus',2);
//								console.log(record);
						    grid.getStore().update({
								params:record.data,
								callback:function(record,operation){
//									console.log(operation);
									var result =Ext.JSON.decode(operation.response.responseText);
									if(result.success){
								   	 Ext.example.msg('','<p align="center">子账号【'+operation.params.userName+'】禁用成功</p>');
									}else {
								   	 Ext.Msg.alert('提示','子账号【'+operation.params.userName+'】禁用失败');
									}	
								}
							});							    			
						}
					});
            	},
            	recoverclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var record = grid.getStore().getAt(rowIndex);
			        Ext.MessageBox.confirm('警告','确定恢复子账号【'+record.data.userName+'】吗?',function(btn){
					if(btn == 'yes'){								    			
						    record.set('staffStatus',1);
						    grid.getStore().update({
								params:record.data,
								callback:function(record,operation){
									var result =Ext.JSON.decode(operation.response.responseText);
									if(result.success){
								   	 Ext.example.msg('','<p align="center">子账号【'+operation.params.userName+'】恢复成功</p>');
									}else {
								   	 Ext.Msg.alert('提示','子账号【'+operation.params.userName+'】恢复失败');
									}	
								}
							});							    			
						}
					});
            	},
            	deleteclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var record = grid.getStore().getAt(rowIndex);
			        Ext.MessageBox.confirm('警告','确定删除子账号【'+record.data.userName+'】吗?',function(btn){
					if(btn == 'yes'){								    			
						    record.set('staffStatus',3);
						    grid.getStore().update({
								params:record.data,
								callback:function(record,operation){
									var result =Ext.JSON.decode(operation.response.responseText);
									if(result.success){
								   	 Ext.example.msg('','<p align="center">子账号【'+operation.params.userName+'】删除成功</p>');
									}else {
								   	 Ext.Msg.alert('提示','子账号【'+operation.params.userName+'】删除失败');
									}	
								}
							});							    			
						}
					});
            	}
            },
            'disableusergrid':{		//禁用列表管理
            	afterrender:function(gridpanel){
//            		console.log(gridpanel.title + '渲染完毕...');
            		var uName,enterpriseName,enterpriseType,industryType;
            		var store = gridpanel.getStore();
            		store.on('beforeload',function(store){
            			Ext.apply(store.proxy.extraParams, {uName:uName,enterpriseName:enterpriseName,enterpriseType:enterpriseType,industryType:industryType,userStatus:'2'});
            		});
            		store.loadPage(1);
            		gridpanel.down('button[action=search]').on('click',function(){
            			uName = gridpanel.down('triggerfield[name=uName]').getValue();
            			enterpriseName = gridpanel.down('triggerfield[name=enterpriseName]').getValue();
            			enterpriseType = gridpanel.down('combo[name=enterpriseType]').getValue();
            			industryType = gridpanel.down('combo[name=industryType]').getValue();
            			store.loadPage(1,{params:{uName:uName,enterpriseName:enterpriseName,enterpriseType:enterpriseType,industryType:industryType}});      			
					},this);
					gridpanel.down('triggerfield[name=uName]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {        					
        					if(!field.getValue()){
        						store.loadPage(1,{params:{uName:''}});
        					}else {
        						uName = gridpanel.down('triggerfield[name=uName]').getValue();
        						store.loadPage(1,{params:{uName:uName}});
        					}                      		
	                    }
					},this);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件            		
            		if(record.data.isPersonal){//个人用户
            			if(cellIndex == 1){//查看个人用户详情
            				var window = this.getPersonaluserdetailwindow();
            				if(!window){
            					window = Ext.widget('personaluserdetailwindow',{
									title:'用户详情'+'['+record.data.userName+']'         					
            					})
            				}
            				window.setTitle('用户详情'+'['+record.data.userName+']');
            				window.down('form').getForm().reset();    					
	    					window.down('form').getForm().loadRecord(record);
	    					window.show();	
            			}
            		}else{//企业用户
	            		if(cellIndex==3){            			
	            			var window = this.getUserdetailwindow();
	            			if(!window){
		    					window = Ext.widget('userdetailwindow',{
		    						title: '企业信息'			
		    					});    		
	    					}
	    					window.down('form').getForm().reset();    					
	    					window.down('form').getForm().loadRecord(record);
	    					window.show();	
				        }
            		}
            	}
            },
            'disableusergrid actioncolumn':{	
            	editclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var window = this.getUsereditwindow();
					var record = grid.getStore().getAt(rowIndex);
					if(!window){
    					window = Ext.widget('usereditwindow',{
    						title:'编辑会员信息'   			
    					});    		
    				}					
					window.down('form').form.reset();
					window.show();
					window.down('form').getForm().loadRecord(record);
            	},
            	restoreclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var record = grid.getStore().getAt(rowIndex);
			                    Ext.MessageBox.confirm('警告','确定还原【 '+record.data.userName+' 】吗?',function(btn){
						    		if(btn == 'yes'){								    			
						    			record.set('userStatus',1);
						    			grid.getStore().update({
								    		params:record.data,
								    		callback:function(record,operation){
								    			var result =Ext.JSON.decode(operation.response.responseText);								    						
								    			if(result.success){
								    				Ext.example.msg('','<p align="center">会员【'+operation.params.userName+'】还原成功</p>');
								    				//var usergrid = Ext.getCmp('enterusergrid');		//获取会员grid
								    				if(grid){
								    					grid.store.loadPage(1);
								    				}
								    				//获取子账号grid,进行同步操作
								    				var staffgrid = Ext.getCmp('enterstaffgrid');
								    				if(staffgrid){
								    					staffgrid.store.load();
								    				}
								    			}else {
								    				Ext.Msg.alert('提示','会员【'+operation.params.userName+'】还原失败');
								    			}	
								    		}
								    	});
//						    			grid.getStore().sync({
//								    			success: function(){
//								    				console.log('还原用户【'+record.data.userName+'】成功');
//								    				Ext.example.msg('','<p align="center">用户【'+record.data.userName+'】还原功</p>');
//								    				},
//								    			failure:function(){
//								    				console.log('还原用户【'+record.data.userName+'】失败');
//								    				Ext.example.msg('','<p align="center">用户【'+record.data.userName+'】还原失败</p>');
//								    				}
//							    			});
											    			
									}
								});
            	},
            	deleteclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		var record = grid.getStore().getAt(rowIndex);
			                    Ext.MessageBox.confirm('警告','确定删除【 '+record.data.userName+' 】吗?',function(btn){
						    		if(btn == 'yes'){
						    			record.set('userStatus',3);
						    			grid.getStore().update({
								    		params:record.data,
								    		callback:function(record,operation){
								    			var result =Ext.JSON.decode(operation.response.responseText);								    						
								    			if(result.success){
								    				Ext.example.msg('','<p align="center">会员【'+operation.params.userName+'】删除成功</p>');
								    				grid.getStore().remove(record);
								    			}else {
								    				Ext.Msg.alert('提示','会员【'+operation.params.userName+'】删除失败');
								    			}	
								    		}
								    	});	
									}
								});
            	}
            },
            'disableusergrid triggerfield':{            	
            	afterrender:function(triggerfield){
//            		console.log(triggerfield.getName()+'渲染完毕...');
            		triggerfield.triggerCell.setDisplayed(false);
            	},
            	dirtychange:function(triggerfield){
            		//console.log('');
            		if(triggerfield.triggerCell!=null){
            			
            			if(triggerfield.getValue!=null&&!triggerfield.display){	//当值不为空，dispalyed为假时，显示清除按钮
            				triggerfield.display=true;
            				triggerfield.triggerCell.setDisplayed(triggerfield.display);
            				triggerfield.setWidth(200);            				
            			}else if((triggerfield.getValue!=null)&&(triggerfield.display)){	//当值不为空，displayed为真时，隐藏清除按钮
            				triggerfield.display=false;
            				triggerfield.triggerCell.setDisplayed(triggerfield.display);
            				triggerfield.setWidth(200);
            			}            			
            		}
            		
            	}        
            },
            'qenterusergrid':{
            	afterrender:function(gridpanel){
//            		console.log(gridpanel.title + '渲染完毕...');
            		var username = '';
            		var entershort = '';
            		var entername = '';
            		var enterpriseType;
            		var startTime = '';
            		var endTime = '';
            		var industryType;
            		gridpanel.getStore().on('beforeload',function(store){
            			Ext.apply(store.proxy.extraParams, {//设置全局参数
            				username:username,
            				entershort:entershort,
            				entername:entername,
            				enterpriseType:enterpriseType,
            				startTime:startTime,
            				endTime:endTime,
            				industryType : industryType,
            				userStatus:'1,2'
            			});
            		});
            		gridpanel.getStore().loadPage(1);	//首先加载全部数据
            		gridpanel.down('button[action=search]').on('click',function(){  
            			//查询事件时给所有条件赋值
            			username = gridpanel.down('triggerfield[name=username]').getValue();
            			entershort = gridpanel.down('triggerfield[name=entershort]').getValue();
	 					entername = gridpanel.down('triggerfield[name=entername]').getValue(); 		 
	 					enterpriseType = gridpanel.down('combo[name=enterpriseType]').getValue();
	 					startTime = gridpanel.down('datefield[name=startdt]').getValue();
	 					endTime = gridpanel.down('datefield[name=enddt]').getValue();
	 					industryType = gridpanel.down('combo[name=industryType]').getValue();
            			gridpanel.getStore().loadPage(1);	
					},this);
					//会员名的回车事件
        			gridpanel.down('triggerfield[name=username]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {
        					if(!field.getValue()){
        						gridpanel.getStore().loadPage(1);
        					}else {
        						gridpanel.getStore().loadPage(1,{params:{username:field.getValue()}});
        					}	
	                    }
					},this);
					//企业简称的回车事件
					gridpanel.down('triggerfield[name=entershort]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {
        					if(!field.getValue()){
        						gridpanel.getStore().load();
        					}else {
        						gridpanel.getStore().load({params:{entershort:field.getValue()}});
        					}
                       		
	                    }
					},this);
					//企业名称的回车事件
					gridpanel.down('triggerfield[name=entername]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {
        					if(!field.getValue()){
        						gridpanel.getStore().load();
        					}else {
        						gridpanel.getStore().load({params:{entername:field.getValue()}});
        					}
                       		
	                    }
					},this);
            	},
            	//单元格时间查询企业详情
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件            		
            		if(record.data.isPersonal){//个人用户
            			if(cellIndex == 1){//查看个人用户详情
            				var window = this.getPersonaluserdetailwindow();
            				if(!window){
            					window = Ext.widget('personaluserdetailwindow',{
									title:'用户详情'+'['+record.data.userName+']'         					
            					})
            				}
            				window.setTitle('用户详情'+'['+record.data.userName+']');
            				window.down('form').getForm().reset();    					
	    					window.down('form').getForm().loadRecord(record);
	    					window.show();	
            			}
            		}else{//企业用户
	            		if(cellIndex==3){            			
	            			var window = this.getUserdetailwindow();
	            			if(!window){
		    					window = Ext.widget('userdetailwindow',{
		    						title: '企业信息'			
		    					});    		
	    					}
	    					window.down('form').getForm().reset();    					
	    					window.down('form').getForm().loadRecord(record);
	    					window.show();	
				        }
            		}
            	}
            },
            'enteruserpanel':{	//会员列表管理
            	afterrender:function(panel){
//            		console.log(panel.title + '渲染完毕...');            		
            	}
            },
            'usereditwindow':{	//会员编辑窗口
            	afterrender:function(window){
            		var me = this;
            		var store = me.getEnterusergrid().getStore();
            		var act = 'edit';            		
            		window.down('button[action=resetPass]').on('click',function(){
            			window.down('textfield[name=password]').setValue(123456);
            			window.down('hiddenfield[name=isreset]').setValue('true');
            			Ext.Msg.alert('提示','密码重置成功,请提交');
            		});
            		window.down('button[action=submit]').on('click',function(){            			
						submitForm(window,store,act);							    	
            		});
            		window.down('button[action=reset]').on('click',function(){            			
            			window.hide();
            		});
            		/* 点击按钮，打开上传图片窗口*/
            		window.query('button[name=select_logo]')[0].on('click', function () {
            			this.selectPic('上传企业LOGO','editphoto');
            		}, this);
            	}
            },
            'userdetailwindow':{	//企业详细信息窗口
            	afterrender:function(window){
//            		console.log(window.title + '渲染完毕...');            		
            	}
            },
            'staffaddwindow':{	//添加子账号窗口
            	afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){
            			mask.show();
            			window.getComponent('staffaddform').form.submit({
				    		clientValidation: true,
						    url: 'staff/save',
						    params: {
						        action: 'save',
						        staffRoleId:window.down('combobox[name=roleName]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshStaff();
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
            			window.getComponent('staffaddform').form.reset();
            			window.hide();
            		
            		});            		
            	}
            },
            'staffeditwindow':{	//编辑子账号窗口
            	afterrender:function(window){
            		var me = this;
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){
            			window.getComponent('staffeditform').form.submit({
				    		clientValidation: true,
						    url: 'staff/edit',
						    params: {
						        action: 'edit'
//						        roleId:window.down('combobox[name=staffRole.id]').getValue()
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.refreshStaff();
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
            			window.getComponent('staffeditform').form.reset();
            			window.hide();
            		
            		});            		
            	}
            },
            'useraddwindow':{	//添加会员窗口
            	afterrender:function(window){
            		var me = this;
            		var store = me.getEnterusergrid().getStore();
            		var act = 'add';            		
            		window.down('button[action=submit]').on('click',function(){
            			var mask = new Ext.LoadMask(window.getEl(), {
							msg : '提交中,请稍等...',
							constrain : true
						});
						mask.show();
						console.log(PlatUrl);
            			Ext.data.JsonP.request({
						    url: PlatUrl + '/public/validateUserName',
						    timeout: 20000,
						    params: {userName : window.down('textfield[name=userName]').getValue()},
						    callbackKey: "callback",
						    success: function(result) {
						    	mask.hide();
						    	if(result.success){						    		
						    		submitForm(window,store,act);
						    	}else {
						    		Ext.Msg.alert('提示',result.message)
						    	}						    							    	
						    },
						    failure: function(result) {
						    	mask.hide();
						        Ext.Msg.alert('提示','网络出现故障，请稍后再试')
						    }
						});
            		});
            		
            		window.down('button[action=reset]').on('click',function(){
            			window.down('form').getForm().reset();
            		});
            		/* 点击按钮，打开上传图片窗口*/
            		window.query('button[name=select_logo]')[0].on('click', function () {
            			this.selectPic('上传企业LOGO','addphoto');
            		}, this);
            	}
            },
            "uploadwindow[name=addphoto]" : {
				afterrender : function (window, opts) {
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '上传中,请稍等...',
						constrain : true
					});
					window.down('button[name=submit]').on('click', function () {
            			this.uplodImage(mask, 'addphoto');
            		}, this);
            		window.down('button[name=cancel]').on('click', function () {
            			window.hide();
            		}, this);
				}
			},
			"uploadwindow[name=editphoto]" : {
				afterrender : function (window, opts) {
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '上传中,请稍等...',
						constrain : true
					});
					window.down('button[name=submit]').on('click', function () {
            			this.uplodImage(mask, 'editphoto');
            		}, this);
            		window.down('button[name=cancel]').on('click', function () {
            			window.hide();
            		}, this);
				}
			},
            'personalusereditwindow':{	//个人用户编辑窗口
            	afterrender:function(window){
            		var me = this;
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=resetPass]').on('click',function(){
            			window.down('textfield[name=password]').setValue(123456);
            			window.down('hiddenfield[name=isreset]').setValue('true');
            			Ext.Msg.alert('提示','密码重置成功,请提交');
            		});
            		window.down('button[action=submit]').on('click',function(){            			
            			var userStatus = window.down('hiddenfield[name=userStatus]').getValue();
            			var store = userStatus == 1?me.getEnterusergrid().getStore():me.getDisableusergrid().getStore();
            			mask.show();
            			window.down('form').getForm().submit({
				    		clientValidation: true,
						    url: 'user/edit',
						    params: {
						        action: 'edit'
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');							       
							       store.reload();
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
				    	})
            		});
            		window.down('button[action=reset]').on('click',function(){            			
            			window.hide();
            		});
            	}
            },
            'personaluseraddwindow':{	//添加个人用户窗口
            	afterrender:function(window){
            		var me = this;
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});         		
            		window.down('button[action=submit]').on('click',function(){
            			mask.show();
            			window.down('form').getForm().submit({
				    		clientValidation: true,
						    url: 'user/addPersonal',
						    params: {
						        action: 'add'					        
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       window.hide();							       
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       me.getEnterusergrid().getStore().reload();							       						       
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
				    	})
            		});
            		window.down('button[action=cancel]').on('click',function(){
            			window.down('form').getForm().reset();
            			window.hide();
            		});
            	}

            },
            //中小微企业认定
            'smeidentifiegrid':{
           	 	afterrender:function(grid){
           	 		var companyName = '';
            		grid.getStore().on('beforeload',function(store){
            			Ext.apply(store.proxy.extraParams, {//设置全局参数
            				companyName:companyName
            			});
            		});
            		grid.getStore().loadPage(1);	//首先加载全部数据
            		grid.down('button[action=search]').on('click',function(){
            			companyName = grid.down('triggerfield[name=companyName]').getValue()
            			grid.getStore().load();
            		});
           	 	},
           	 	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.showDetailwindow(record);
            	},
            	cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件             
	        		if(cellIndex==5){this.showDetailwindow(record);}   
				}
            	
            },
            'smewindow':{
            	afterrender:function(window){
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=enter]').on('click',function(){
            			var rowIndex = window.down('hiddenfield[name=rowIndex]').getValue();
            			var grid = this.getSmeidentifiegrid();
            			var record = grid.getStore().getAt(rowIndex);
            			console.log(window.title);
                  		record.set('approveStatus',window.title == '打回' ? 0 : 1);
                  		mask.show();
		    			grid.getStore().update({
		    				params:record.data,
		    				callback:function(result){
	    						mask.hide();
								window.hide();
		    				}
		    			});
            		},this);
   					window.down('button[action=cancle]').on('click',function(){            			
            			window.hide();
            			window.getComponent('smewindowform').form.reset();
   					});
            }
         }
        })    
    },
    selectPic : function (title, name) {
		var picWindow = Ext.ComponentQuery.query('uploadwindow[name='+ name +']')[0];
    	if (!picWindow) {
    		Ext.widget('uploadwindow',{
    			title : title,
    			name : name
    		}).show();
    	} else {
    		picWindow.show();
    	}
	},
	uplodImage : function (mask, name) {
		var Ewindow = Ext.ComponentQuery.query('uploadwindow[name='+ name +']')[0];
		var Eform = Ewindow.query('uploadform')[0];
		var window;
		if (name == 'licenceDuplicate' || name == 'businessLetter') {
			window = this.getApplywindow();
		}else if(name =='addphoto'){
			window = this.getUseraddwindow();
		}else if(name =='editphoto'){
			window = this.getUsereditwindow();
		}else if(name == 'personalPhoto' || name == 'idCardPhoto'){
			window = this.getApplypersonalwindow();
		}
		mask.show();
		Eform.getForm().submit({
			url : 'public/uploadFile',
			clientValidation: true,
		    success: function(form, action) {
		    	if (action.result.success) {
			       var addForm = window.query('form')[0];
			       var Epicture = addForm.down('textfield[name='+name+']');
			       if (name == 'addphoto' || name == 'editphoto') {
			       	 	Epicture = addForm.down('textfield[name=enterprise.photo]');
			       }else if(name == 'personalPhoto'){
			       		Epicture = addForm.down('textfield[name=personalPhoto]');
			       }else if(name == 'idCardPhoto'){
			       		Epicture = addForm.down('textfield[name=idCardPhoto]');
			       }
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
    addPersonalUser:function(){//添加个人用户window
    	var window = this.getPersonaluseraddwindow();
		if(!window){
	    	window = Ext.widget('personaluseraddwindow',{title:'添加个人用户'});    		
    	}
		window.down('form').getForm().reset();
		window.show();
    },
    refreshStaff:function(){
    	this.getEnterstaffgrid().getStore().reload();
    },
    addStaff:function(record){ 	//添加子账号window
	    var addStaffWindows = this.getStaffaddwindow();
		if(!addStaffWindows){
			addStaffWindows = Ext.widget('staffaddwindow',{
		    title:'添加子账号'    			
		   });    		
		}
		var staffAddForm = addStaffWindows.getComponent('staffaddform');
		staffAddForm.down('combobox[name=roleName]').getStore().setProxy(
		{	type:'ajax',
			actionMethods: {  
				read: 'POST'
			},		
			extraParams:{enterpriseType:enterType},
			url:'staff/loadStaffRole'	
		});
		staffAddForm.down('combobox[name=roleName]').getStore().load();
		staffAddForm.form.reset();
		staffAddForm.down('displayfield[name=parentName]').setValue(parentName);
		staffAddForm.down('hiddenfield[name=parentId]').setValue(parentId);
		staffAddForm.down('hiddenfield[name=staffStatus]').setValue(1);
	
		addStaffWindows.show();		
    },
    showStaff:function(grid,record,index,eOpts){//显示子账号grid
    	userStatus = record.data.userStatus;	//得到主账号用户状态
    	var isPersonal = record.data.isPersonal;	//得到主账号是否为个人户
	    var staffgrid = this.getEnterstaffgrid();
	    staffgrid_g = this.getEnterstaffgrid();	//将全局的子账号表格设置以便主账号状态改变调用
	    staffgrid.setTitle('【'+record.data.userName+'】子账号列表');
	    /*****用户状态是禁用和删除时把子账号的添加按钮禁用*****/
	    if(userStatus == 2){
	    	staffgrid.down('button[action=add]').setDisabled(true);

	    }else if(userStatus == 3){
	    	staffgrid.down('button[action=add]').setDisabled(true);	
	    	
	    }else{
	    	staffgrid.down('button[action=add]').setDisabled(false);
	    	
	    }
	    /************************************/
	    if(isPersonal){//如果是个人用户无法拥有子账号
//	    	Ext.example.msg('','<p align="center">账号【'+record.data.userName+'】属于个人账号,无法拥有子账号</p>');
			staffgrid.setVisible(false);
	    }
    	else{//友好企业用户账号
    		staffgrid.setVisible(true);
	    	staffgrid.getStore().setProxy({
	    		type:'ajax',
	    		actionMethods: {  
	        		read: 'POST'
	            },
	    		api:{
	    			read:'staff/query?parentId='+record.data.id,
	    			update:'staff/update'  
	    		},
	    		reader:{
	    			type:'json',
	    			root:'data',
	    			messageProperty:"message"  
	    		},
	    		writer:{  
				    type:"json",  
				    encode:true,  
				    root:"staff",  
				    allowSingle:true  
				}
	    	});
	    	staffgrid.getStore().load();
	    	parentId = record.data.id;
	    	parentName = record.data.userName;
	    	enterType = record.get("enterprise.type");//得到企业类型方面添加子窗体时调用	
    	}
    },
    showStaffDetail:function(record){//显示子账号详情form
    	var staffDetailWindows = this.getStaffdetailwindow();
    	if(!staffDetailWindows){
    		staffDetailWindows = Ext.widget('staffdetailwindow',{
    			title:'子账号详情【'+record.data.userName+'】'
    		});    		
    	}
		staffDetailWindows.show();
		staffDetailWindows.setTitle('子账号详情【'+record.data.userName+'】');
		staffDetailWindows.getComponent('staffdetailform').form.loadRecord(record);
		var managerId = staffDetailWindows.down('hiddenfield[name=manager.id]').getValue();
		if(managerId == 0){//分配人不是平台人员
			staffDetailWindows.down('displayfield[name=fpname]').setValue(record.get('assigner.userName'));
		}else{
			staffDetailWindows.down('displayfield[name=fpname]').setValue(record.get('manager.username'));
		}
    },
    showDetailwindow:function(record){
    	console.log(record);
    	var smedetailWindow = this.getSmedetailwindow();
    	if(!smedetailWindow){
    		smedetailWindow = Ext.widget('smedetailwindow',{
    			title:'详情'
    		});    		
    	}
    	smedetailWindow.down('form').loadRecord(record);
    	smedetailWindow.show();
    }
  
});
function openSMEWindow(title, rowIndex){
	var smewindow = null;
	var windows = Ext.ComponentQuery.query('smewindow');
	if(windows.length > 0 ){
		smewindow = windows[0];
		smewindow.setTitle(title);
		smewindow.show();
	} else {
		smewindow = Ext.widget('smewindow',{title:title}).show();
	}
	var form = smewindow.getComponent('smewindowform').form;
	form.reset();
	smewindow.down('hiddenfield[name=rowIndex]').setValue(rowIndex);
}
function submitForm(window,store,act){//添加会员或者编辑会员表单
	var mask = new Ext.LoadMask(window.getEl(), {
		msg : '提交中,请稍等...',
		constrain : true
	});
	mask.show();
	window.down('form').getForm().submit({	//提交表单
		clientValidation: true,
		url: 'user/'+act,
		submitEmptyText: false,
		params: {
			action: act				        
		},
		success: function(form, action) {
			mask.hide();
			if(action.result.success){
				if(action.result.message=="该用户名已存在!"){
					Ext.Msg.alert('','<p align="center">该用户名已存在!</p>');
				}else {
					window.hide();							       
					Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
					store.reload();	
				}
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
					Ext.Msg.alert('提示', '账号添加失败');
			}
		}
	});
};