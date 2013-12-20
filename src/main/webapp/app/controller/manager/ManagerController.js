Ext.define('plat.controller.manager.ManagerController', {
    extend: 'Ext.app.Controller',
    
    alias:'widget.managercontroller',
	
    views: [
		'manager.AuthPanel',
		'manager.UpdateManagerWindow',
		'manager.ViewManagerWindow',
		'manager.UpdateRoleWindow',
		'manager.ManagerGrid',
		'manager.RolesGrid',
		'manager.MenuGrid',
		'manager.ManagerWindow'
    ],
    refs: [
		{
			ref:'managergrid',
			selector:'managergrid'
		},
		{
			ref:'menugrid',
			selector:'menugrid'
		},
		{
			ref:'rolesgrid',
			selector:'rolesgrid'
		},
		{
			ref:'authpanel',
			selector:'authpanel'
		},
		{
			ref:'updatemanagerwindow',
			selector:'updatemanagerwindow'
		},
		{
			ref:'managerwindow',
			selector:'managerwindow'
		},
		{
			ref:'updaterolewindow',
			selector:'updaterolewindow'
		},{
			ref:'viewmanagerwindow',
			selector:'viewmanagerwindow'
		}
    ],
    showManagers:function(grid, record) {
    	var menugrid=this.getMenugrid();
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
	
    init: function() {
        this.control({
            'rolesgrid':{
            	afterrender:function(gridpanel){
            		gridpanel.getStore().load();
            		console.log(gridpanel.title + '渲染完毕...');
            		//添加角色
	        		gridpanel.down('button[action=add]').on('click',function(){
						var roleWindows = Ext.ComponentQuery.query('updaterolewindow');
							if(roleWindows.length == 0){
	        					Ext.widget('updaterolewindow',{title:'添加角色'}).show();
	        				}else{
	                			roleWindows[0].close();
	                			Ext.widget('updaterolewindow',{title:'添加角色'}).show(); 
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
        		//双击修改角名称和角色描述
//        		itemdblclick:function(grid, record){
//        			var iterator=function(newrec, oldrec) {
//						 Ext.Array.each(newrec, function(rec) {
//							Ext.Array.each(oldrec, function(old){
//								if(rec.childNodes!=null&&rec.childNodes.length!=0){
//									if(rec.data.id==old.id){
//										rec.set('checked',true);
//									}
//										iterator(rec.childNodes,oldrec)
//								}else{
//									if(rec.data.id==old.id){
//										rec.set('checked',true);
//									}
//								}
//							});
//					 	})
//				 	};
//						    
//        			var updateroleWindows = Ext.ComponentQuery.query('updaterolewindow'),view=null;
//	        			if(updateroleWindows.length==0){
//	            			view = Ext.widget('updaterolewindow');
//	        			}else{
//	        				updateroleWindows[0].close();
//	            			view = Ext.widget('updaterolewindow');
//	        			}
//							view.down('form').loadRecord(record);
//            	}
            },
            'updaterolewindow':{
            	afterrender:function(updaterolewindow){
//            		console.log(updaterolewindow.title + '渲染完毕...');
            		var mask = new Ext.LoadMask(updaterolewindow.getEl(), {
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
								       Ext.getCmp('rolesgrid').getStore().reload();
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
            'managergrid':{
            	afterrender:function(gridpanel){
            		console.log(gridpanel.title + '渲染完毕...');
            		Ext.data.StoreManager.lookup('managerGridStore').load();
            		gridpanel.down('button[action=add]').on('click',function(){
	                		this.addManager();
						},this);
            		gridpanel.down('button[action=search]').on('click',function(){
        				var store = Ext.data.StoreManager.lookup('managerGridStore');
        				Ext.apply(store.proxy.extraParams, {username : gridpanel.down('textfield[name=username]').getValue()});
        				store.loadPage(1);
					},this);
					gridpanel.down('textfield[name=username]').on('specialkey',function(field,e){
        				if (e.getKey() == e.ENTER) {
	        				var store = Ext.data.StoreManager.lookup('managerGridStore');
	        				Ext.apply(store.proxy.extraParams, {username : gridpanel.down('textfield[name=username]').getValue()});
	        				store.loadPage(1);
	                    }
					},this);
            	},
            	itemdblclick:function(grid, record){
            		if(record.data.userStatus!=3)
            		{
	            		var updatemanagerWindows = Ext.ComponentQuery.query('updatemanagerwindow'),view=null;
	        			if(updatemanagerWindows.length==0){
	            			view = Ext.widget('updatemanagerwindow');
	        			}else{
		        			updatemanagerWindows[0].close();
		            		view = Ext.widget('updatemanagerwindow');
	        			}
	        			
						view.down('form').loadRecord(record);
						var value = record.data.roleId;
						
						Ext.getCmp('updatemanagerwindow').down('form').down('combobox[id=pertainrole]').setValue(value);
            		}else{
            			var updatemanagerWindows = Ext.ComponentQuery.query('viewmanagerwindow'),view=null;
	        			if(updatemanagerWindows.length==0){
	            			view = Ext.widget('viewmanagerwindow');
	        			}else{
		        			updatemanagerWindows[0].close();
		            		view = Ext.widget('viewmanagerwindow');
	        			}
	        			
						Ext.getCmp('viewuser').loadRecord(record);
						var value = record.data.roleId;
            		}
	            }
            },
            'updatemanagerwindow':{
            	afterrender:function(updatemanagerwindow){
//            		console.log(updatemanagerwindow.title + '渲染完毕...');
            		var mask = new Ext.LoadMask(updatemanagerwindow.getEl(), {
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
						               Ext.Msg.alert('提示', action.result.message);
						       }
						    }
            			});
            		},this)
            	}
            },
            'menugrid':{
            	afterrender:function(gridpanel){
            		var me=this;
            		console.log(gridpanel.title + '渲染完毕...');
//            		me.getMenugrid().getStore().load();
            		gridpanel.down('button[action=save]').on('click',function(){
	            		if(me.getMenugrid().getStore().getUpdatedRecords().length>0){
		            		var outtreearray=function(){
		            			var outmenutree=[];
			            		Ext.each(me.getMenugrid().getChecked(),function(rec){
			            			if(rec.data.checked){
			            				outmenutree.push(rec.data.id);
			            			}
			            		});
			            		return outmenutree.join(',');
		            		};
	            			Ext.Ajax.request({
							    type:'POST',
							    url: 'menu/updaterole',
							    params:{'id':me.getMenugrid().down('hiddenfield[name=roleid]').getValue(),
							    		'menutree':outtreearray(),
							    		'rolename':me.getMenugrid().down('hiddenfield[name=rolename]').getValue(),
							    		'roledesc':me.getMenugrid().down('hiddenfield[name=roledesc]').getValue()
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
            		var record =this.getMenugrid().getView().getRecord(item);
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
            },
            'querymenupanel':{
            	afterrender:function(gridpanel){
            		console.log(gridpanel.title+'渲染完毕。。。。');
            	}
            },
            'managerwindow':{
            	afterrender:function(window){
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){
            			mask.show();
            			window.getComponent('managerform').form.submit({
				    		clientValidation: true,
						    url: 'manage/save',
						    params: {
						        'roleId':window.down('form').down('combobox[id=pertainrole]').getValue(),
						        'password':Ext.ux.MD5.hex_md5(window.down('form').down('textfield[name=password]').getValue()),
						        'passwordvalidate':Ext.ux.MD5.hex_md5(window.down('form').down('textfield[name=passwordvalidate]').getValue())
						    },
						    success: function(form, action) {
						    	mask.hide();
						    	if(action.result.success){
							       Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
							       window.close();
//							       Ext.getCmp('managergrid').getStore().reload();
							       Ext.data.StoreManager.lookup('managerGridStore').load();
						    	}else{
						    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
						    	}
						    },
						    failure: function(form, action) {
//						    	Ext.example.msg('','<p align="center">'+action.result.message+'</p>');
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
            	}
            }
        });
    },
    refreshUserGrid:function(){
    	this.getUsersgrid().getStore().reload();
    },
    //父节点选中
    checkParentNode:function(node, checked) {
         if (node.parentNode) {
             var flg = 0;
             node.parentNode.eachChild(function (child) {
                 if (child.get('checked')) {
                     node.parentNode.set('checked', true);
                 } else {
                     flg++;
                     if (flg == node.parentNode.childNodes.length) {
                         node.parentNode.set('checked', false);
                     }
                 }
             });
             setPnode(node.parentNode, checked);
         }
 	},
     //子节点被选中
	checkChildNode:function(node, checked) {
     	if (!node.isLeaf()) {
     		node.eachChild(function (child) {
         		child.set('checked', checked);
         		checkChildNode(child, checked);
     		});
     	}
	},
    addManager:function(){
    	var managerWindows = Ext.ComponentQuery.query('managerwindow');
	    	if(managerWindows.length == 0){
	    		Ext.widget('managerwindow',{
	    			title:'添加用户',
	    			id:'managerwindow'
	    		}).show();    		
	    	}else{
		   		managerWindows[0].close();
		   		Ext.widget('managerwindow',{
		    		title:'添加用户',
		    		id:'managerwindow'
		    	}).show();
   			}
    }
});