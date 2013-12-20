Ext.define('plat.controller.system.UserController', {
	extend: 'Ext.app.Controller',
	alias:'widget.user',
	
	stores : ['system.UserStore'],
    views: [
    		'system.UserGridView',
    		'layout.combo.SexCombo',
    		'layout.combo.GroupCombo',
    		'layout.combo.UserStatus',
    		
    		
    		'system.QAuthPanel',
    		'system.FAuthPanel',
			'system.SUpdateRoleWindow',
			'system.SRolesGrid',
			'system.SMenuGrid'
    		],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
	        {
	            ref: 'usergrid',
	            selector: 'usergrid'
	        },
	        {
	        	ref:'qauthpanel',
	        	selector:'qauthpanel'
	        },
	        {
	        	ref:'fauthpanel',
	        	selector:'fauthpanel'
	        },
	        {
	        	ref:'supdaterolewindow',
	        	selector:'supdaterolewindow'
	        },
	        {
	        	ref:'srolesgrid',
	        	selector:'srolesgrid'
	        },
	        {
	        	ref:'smenugrid',
	        	selector:'smenugrid'
	        }
    ],
    showManagers:function(grid, record) {
    	var menugrid=this.getSmenugrid();
		//这里的方法是获取被改变了的记录的records数组
//    	Ext.each(menugrid.getStore().getUpdatedRecords(),
//    	function(rec){
//    		rec.set('checked',false);
//    	});
//    	Ext.each(record.data.menu,
//    	function(menu){
//    		menugrid.getStore().findRecord('id',menu.id).set('checked',true);
//    	});
//    	menugrid.getStore().removeAll();
    	menugrid.getStore().setProxy({
	        type: 'ajax',
	        api:{  
			    read:'menu/allLoad?id='+record.data.id
	  		},  
			reader:{  
	      		type: 'json',
//				root: '',
	        	messageProperty:"message"  
	  		}
   		});
   		menugrid.getStore().load();
   		menugrid.expandAll();
    	menugrid.down('hiddenfield[name=roleid]').setValue(record.data.id);
    	menugrid.down('hiddenfield[name=rolename]').setValue(record.data.rolename);
    	menugrid.down('hiddenfield[name=roledesc]').setValue(record.data.roledesc);
    },
    init:function(){
    	this.control({
    		'usergrid': {
                afterrender:function(gridpanel){
                	console.log(gridpanel.title + '渲染完毕...');
                	Ext.getBody().unmask();
        			gridpanel.getStore().on('beforeload',function(store,options){
//        				console.log(gridpanel.getDockedItems());
                		Ext.apply(store.proxy.extraParams, gridpanel.down('textfield[name=nickname]').getSubmitData());
                	},this);
//                	gridpanel.getStore().on('groupchange',function(store,groupers){
//        				var grouped = store.isGrouped(),
//	                    groupBy = groupers.items[0] ? groupers.items[0].property : '',
//	                    toggleMenuItems, len, i = 0;
//		                // Clear grouping button only valid if the store is grouped
//		                gridpanel.down('[action=ungroup]').setDisabled(!grouped);
//		                // Sync state of group toggle checkboxes
////		                if (grouped && groupBy === 'groupname') {
////		                    toggleMenuItems = gridpanel.down('button[text=Toggle groups...]').menu.items.items;
////		                    for (len = toggleMenuItems.length; i < len; i++) {
////		                        toggleMenuItems[i].setChecked(groupingFeature.isExpanded(toggleMenuItems[i].text));
////		                    }
////		                    gridpanel.down('[text=Toggle groups...]').enable();
////		                } else {
////		                    gridpanel.down('[text=Toggle groups...]').disable();
////		                }
//                	},this);
                	gridpanel.getSelectionModel().on('selectionchange', function(selModel, selections){
				        gridpanel.down('button[action=delete]').setDisabled(selections.length === 0);
				    });
        			gridpanel.down('textfield[name=nickname]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {
                       		gridpanel.getStore().load();
	                    }
					},this);
                	gridpanel.down('button[action=search]').on('click',function(){
                		gridpanel.getStore().load();
					},this);
					gridpanel.down('button[action=add]').on('click',function(){
//						gridpanel.getStore().suspendAutoSync();
//						gridpanel.getStore().resumeAutoSync();
						gridpanel.getStore().insert(0,{username:'user',password:'123456',realname:'用户',sex:'男',registerDate:Ext.Date.format(new Date(),'Y-m-d H:i:s'),groupname:'普通用户组',online:false,status:'未激活'});
						gridpanel.getPlugin('cellEditing').startEditByPosition({row:0,column:1});
					},this);
					gridpanel.down('button[action=delete]').on('click',function(){
						gridpanel.getStore().remove(gridpanel.getSelectionModel().getSelection());
//						gridpanel.getStore().sync();
					},this);
					gridpanel.down('button[action=sync]').on('click',function(){
						var newRecords = gridpanel.getStore().getNewRecords().length;
                		var updateRecords = gridpanel.getStore().getUpdatedRecords().length;
                		var removeRecords = gridpanel.getStore().getRemovedRecords().length;
                		if(newRecords > 0 || updateRecords > 0 || removeRecords > 0){
							gridpanel.getEl().mask("数据同步中,请稍候...");
							gridpanel.getStore().sync({
								success:function(batch,options){
									gridpanel.getEl().unmask();
									Ext.example.msg('','数据同步成功!');
								},
								failure:function(batch,options){
									gridpanel.getEl().unmask();
									Ext.example.msg("","同步失败,请稍后重新同步!");
								}
							});
                		}else{
                			Ext.example.msg("","没有数据被修改或是删除,同步取消!");
                		}
					},this);
//					gridpanel.down('button[action=import]').on('click',function(){
//                		Ext.example.msg("","功能建设中,敬请期待...");
//					},this);
//					gridpanel.down('button[action=export]').on('click',function(){
//                		Ext.example.msg("","功能建设中,敬请期待...");
//					},this);
                },
                beforeclose:function(panel){
					var newRecords = panel.getStore().getNewRecords().length;
                	var updateRecords = panel.getStore().getUpdatedRecords().length;
                	if(newRecords > 0 || updateRecords > 0){
                		Ext.example.msg('','<font color="red">有修改的数据未处理,请同步数据或取消编辑!</font>');
	                	return false;               
                	}
                },
                beforehide:function(gridpanel){
            		console.log(gridpanel.title + "将会隐藏!");
            	},
            	destroy:function(grid){
            		console.log(grid.title + "已被销毁...");
            	}
            },
            
            
            
            
            
            
            
            
            
            
            'srolesgrid':{
            	afterrender:function(gridpanel){
            		gridpanel.getStore().load();
            		console.log(gridpanel.title + '渲染完毕...');
            		//添加角色
	        		gridpanel.down('button[action=add]').on('click',function(){
						var roleWindows = Ext.ComponentQuery.query('supdaterolewindow');
							if(roleWindows.length == 0){
	        					Ext.widget('supdaterolewindow',{title:'添加角色'}).show();
	        				}else{
	                			roleWindows[0].close();
	                			Ext.widget('supdaterolewindow',{title:'添加角色'}).show(); 
	        				}
					},this);
					
            		//查找角色按角色名字模糊
	        		gridpanel.down('button[action=search]').on('click',function(){
//						gridpanel.down('pagingtoolbar[dock="bottom"]').moveFirst();            			
            			gridpanel.getStore().load({params:{rolename:gridpanel.down('textfield[name=rolename]').getValue()}});
					},this);
					//按下enter建也查找
					gridpanel.down('textfield[name=rolename]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {
//        					gridpanel.down('pagingtoolbar[dock="bottom"]').moveFirst();
        					if(!field.getValue()){
//        						gridpanel.getStore().load();
        					}else {
        						gridpanel.getStore().load({params:{rolename:field.getValue()}});
        					}
	                    }
					},this);
            	},
            	itemcontextmenu:function( menu, record, item, index, e, eOpts ){
            		console.log(menu);
            	},
            	//单击显示该角色的权限
        		itemclick: this.showManagers
            },
            'supdaterolewindow':{
            	afterrender:function(updaterolewindow){
//            		console.log(updaterolewindow.title + '渲染完毕...');
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
	            		updaterolewindow.down('button[action=save]').on('click',function(button){
	            			mask.show();
	            			updaterolewindow.down('form').submit({
					    		clientValidation: true,
							    url: 'menu/updaterole',
//								    params:{'menutree':idarray.join(',')},
							    success: function(form, action) {
							    	mask.hide();
							    	if(action.result.success){
								       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
								       updaterolewindow.hide();
								       Ext.getCmp('srolesgrid').getStore().reload();
							    	}else{
							    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
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
	            		},this)
            	}
            },
            'supdatemanagerwindow':{
            	afterrender:function(updatemanagerwindow){
//            		console.log(updatemanagerwindow.title + '渲染完毕...');
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		updatemanagerwindow.down('button[action=save]').on('click',function(button){
            			mask.show();
            			updatemanagerwindow.down('form').submit({
				    		clientValidation: true,
						    url: 'manage/update',
						    params:{'roleId':updatemanagerwindow.down('form').down('combobox[id=pertainrole]').getValue(),'username':updatemanagerwindow.down('form').down('textfield[name=username]').getValue()},
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       updatemanagerwindow.close();
//							       Ext.getCmp('managergrid').getStore().reload();
							       Ext.data.StoreManager.lookup('managerGridStore').load();
						    	}else{
						    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
						    	mask.hide();
						    	Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
						        switch (action.failureType) {
						            case Ext.form.action.Action.CLIENT_INVALID:
						                Ext.Msg.alert('提示', '提交表单中包含非法字符(或必填项为空)！');
						                break;
						            case Ext.form.action.Action.CONNECT_FAILURE:
						                Ext.Msg.alert('提示', 'Ajax请求失败');
						                break;
						            case Ext.form.action.Action.SERVER_INVALID:
						               Ext.Msg.alert('提示', action.result.msg);
						       }
						    }
            			});
            		},this)
            	}
            },
            'smenugrid':{
            	afterrender:function(gridpanel){
            		var me=this;
            		console.log(gridpanel.title + '渲染完毕...');
//            		me.getMenugrid().getStore().load();
            		gridpanel.down('button[action=save]').on('click',function(){
	            		if(me.getSmenugrid().getStore().getUpdatedRecords().length>0){
		            		var outtreearray=function(){
		            			var outmenutree=[];
			            		Ext.each(me.getSmenugrid().getChecked(),function(rec){
			            			if(rec.data.checked){
			            				outmenutree.push(rec.data.id);
			            			}
			            		});
			            		return outmenutree.join(',');
		            		};
	            			Ext.Ajax.request({
							    type:'POST',
							    url: 'menu/updaterole',
							    params:{'id':me.getSmenugrid().down('hiddenfield[name=roleid]').getValue(),
							    		'menutree':outtreearray(),
							    		'rolename':me.getSmenugrid().down('hiddenfield[name=rolename]').getValue(),
							    		'roledesc':me.getSmenugrid().down('hiddenfield[name=roledesc]').getValue()
							    },
							    success: function(response) {
							    	var result = Ext.decode(response.responseText);
							    	if(result.success){
								    	Ext.example.msg('保存成功!',result.message);
								    	Ext.getCmp('menugrid').getStore().reload();
							    	}else{
							    		Ext.example.msg('保存失败!',result.message);
							    	}
							    },
							    failure: function(form, action) {Ext.example.msg('保存失败!','出错啦');}
							});
	            		}else{
	            			Ext.example.msg('无法保存!','你没有做任何修改，此时保存没意义');
	            		}
            		});
            	},
            	checkchange:function(item,check,eOpts){
            		var record =this.getSmenugrid().getView().getRecord(item);
            		if (check) {
						record.bubble(function(parentNode) {
							if ('Root' != parentNode.get('text')) {
								parentNode.set('checked',true);
							}
						});
						record.cascadeBy(function(node) {
							node.set('checked',true);
							node.expand(true);
						});
					} else {
						record.cascadeBy(function(node) {
							node.set('checked',false);
						});
						record.bubble(function(parentNode) {
							if ('Root' != parentNode.get('text')) {
								var flag = true;
								for (var i = 0; i < parentNode.childNodes.length; i++) {
									var child = parentNode.childNodes[i];
									if(child.get('checked')){
										flag = false;
										continue;
									}
								}
								if(flag){
									parentNode.set('checked',false);
								}
							}
						});
					}
            	}
            }
    	});
    },
    loadCookies:function(loginform){
    	if(Ext.util.Cookies.get("user")){
    		loginform.down('textfield[name=user.username]').setValue(Ext.util.Cookies.get("user").split(',')[0]);
			loginform.down('textfield[name=user.password]').setValue(Ext.util.Cookies.get("user").split(',')[1]);
    	}else if(Ext.util.Cookies.get("plat_username")){
    		loginform.down('textfield[name=user.username]').setValue(Ext.util.Cookies.get("plat_username"));
    	}else{
    		return;
    	}
    }
});