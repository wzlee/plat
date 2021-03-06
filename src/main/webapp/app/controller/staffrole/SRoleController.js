Ext.define('plat.controller.staffrole.SRoleController', {
	extend: 'Ext.app.Controller',
	alias:'widget.srolecontroller',
	
    views: [
    		'staffrole.SAuthPanel',
			'staffrole.SUpdateRoleWindow',
			'staffrole.SRolesGrid',
			'staffrole.SMenuGrid'
    		],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
	        {
	        	ref:'sauthpanel',
	        	selector:'sauthpanel'
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
    showSMenus:function(grid, record) {
    	var menugrid=this.getSmenugrid();
    	menugrid.getStore().setProxy({
	        type: 'ajax',
	        api:{  
			    read:'smenu/allLoad?type=2&id='+record.data.id
	  		},  
			reader:{  
	      		type: 'json',
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
        		itemclick: this.showSMenus
            },
            'supdaterolewindow':{
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
							    url: 'smenu/updaterole?type=2',
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
							    url: 'smenu/updaterole?type=2',
							    params:{'id':me.getSmenugrid().down('hiddenfield[name=roleid]').getValue(),
							    		'menutree':outtreearray(),
							    		'rolename':me.getSmenugrid().down('hiddenfield[name=rolename]').getValue(),
							    		'roledesc':me.getSmenugrid().down('hiddenfield[name=roledesc]').getValue()
							    },
							    success: function(response) {
							    	var result = Ext.decode(response.responseText);
							    	if(result.success){
								    	Ext.example.msg('保存成功!',result.message);
								    	Ext.getCmp('smenugrid').getStore().reload();
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
    }
});