Ext.define('plat.controller.cms.CmsController', {
    extend: 'Ext.app.Controller',
    alias:'widget.cmsController',
    stores: ['cms.TemplateStore', 'cms.ChannelStore', 'cms.ServerAsyncStore', 'cms.ServiceAgencyStore' ],
    views: ['cms.CmsPanel', 'cms.ChannelTree', 'cms.TemplatePanel', 'cms.BannerForm', 'cms.ChannelForm', 'public.UploadWin', 'public.UploadForm', 
    		'cms.ModelConfigPanel', 'cms.CategoryServeTree', 'cms.ModelForm', 'cms.S_Advert', 'cms.S_Banner', 'cms.S_Commend',
    		'cms.S_Category', 'cms.ServiceAgencyGrid'],
    refs: [{
		ref:'channelTree',
		selector:'channelTree'
	}, {
		ref:'templatePanel',
		selector:'templatePanel'
	}, {
		ref:'modelConfigPanel',
		selector:'modelConfigPanel'
	}, {
		ref:'modelForm',
		selector:'modelForm'
	}, {
		ref:'bannerForm',
		selector:'bannerForm'
	}, {
		ref:'banner',
		selector:'banner'
	}, {
		ref:'channelForm',
		selector:'channelForm'
	}, {
		ref:'uploadwindow',
		selector:'uploadwindow'
	}, {
		ref:'uploadform',
		selector:'uploadform'
	}, {
		ref:'serviceagencygrid',
		selector:'serviceagencygrid'
	}],
    init: function() {
        this.control({
        	'channelTree' : {
        		itemdblclick : this.getChannelToModel,
        		itemcontextmenu: this.nodeMenu
        	},
        	'channelTree button[action=addRootChannel]' : {
        		click: this.channelConfig
        	},
        	'channelTree button[action=deleteChannel]' : {
        		click: this.deleteChannel
        	},
        	'channelTree button[action=modfiyChannel]' : {
        		click: this.channelConfig
        	},
        	'channelTree actioncolumn[name=ascending]' : {
        		click: this.seqencing
        	},
        	'channelTree actioncolumn[name=descending]' : {
        		click: this.seqencing
        	},
        	'channelForm combobox[name=mtcode]' : {
        		render: this.comboxTemplateLoad,
        		select: this.templateSelect
        	},
        	'templatePanel [xtype=combobox]' : {
        		afterrender : this.comboxAfterRender,
        		select : this.selectTemplate
        	},
        	'templatePanel panel[action=model]' : {
        		afterrender : this.afterModuleRender
        	},
        	'modelForm button[name=selectImg]' : {
        		click: this.uploadImages
        	},
        	'modelForm [xtype=filefield]' : {
        		change : this.onChangeImages
        	},
        	'bannerForm button[name=selectImg]' : {
        		click: this.uploadImages
        	},
        	'categoryServeTree' : {
        		itemdblclick : this.setLinkFn
        	},
        	'uploadwindow button[name=submit]' : {
        		click: this.imagesSubmit
        	},
        	'uploadwindow button[name=cancel]' : {
        		click: function(but){
        			but.ownerCt.ownerCt.close();
        		}
        	},
        	'serviceagencygrid' : {    //服务机构表格
        		afterrender : function (grid) {
        			var store = grid.getStore();
        			var enterpName = '';
        			store.on('beforeload',function(store){
            			Ext.apply(store.proxy.extraParams, {enterpName:enterpName});
            		});
            		store.loadPage(1);
        			grid.down("button[action=search]").on("click",function(){
        				enterpName = grid.down('textfield[name=enterpName]').getValue();
            			store.loadPage(1,{params:{enterpName:enterpName}});        				
        			});
        		},
        		itemdblclick : function (me, record, item, index, e, eOpts) {
        			this.getModelForm().getForm().loadRecord(record);
        		}
        	}
        });
    },
    nodeMenu: function(view, record, item, index, e, eOpts){
    	var nodemenu = new Ext.menu.Menu({
    		items : [{
	    		text : '新增节点',
	    		action: 'addChannel',
	            handler: this.channelConfig,
	            scope: this
	        }, {
	            text : '修改节点', 
	            action: 'modfiyChannel',
	            handler: this.channelConfig,
	            scope: this
	        }, {
	            text : '删除节点',
	            action:'deleteChannel',
	            handler: this.deleteChannel,
	            scope: this
	        }] 
        });
        e.preventDefault(); 
    	//这两个很重要，否则点击鼠标右键还是会出现浏览器的选项  
    	e.stopEvent();
        //菜单打开的位置
    	nodemenu.showAt(e.getXY());
    },
    /**
     * 频道配置
     * @param {} but
     */
    channelConfig: function(but){
    	var channelModel = this.getChannelTree().getSelectionModel();
    	var record = channelModel.getSelection()[0];
    	if(record && (but.action == 'addChannel')){
    		var path = this.getChannelTree().getStore().getNodeById(record.data.id).getDepth();
    		if(path == 3){
    			return Ext.MessageBox.alert('提示', '系统目前只支持三级节点配置！');
    		}
    	}
    	var cWin = Ext.create('Ext.window.Window',{
    		title: '频道配置',
			layout: 'fit',
			frame: false,
			autoWidth: true,
			autoHeight: true,
			modal: true,
			record: record,
			addOrUpdate: but.action,
			items : Ext.widget('channelForm'),
		    buttonAlign: 'center',
		    buttons: [{
		    	name: 'channelSave',
		        text: '保存',
		        handler: this.channelSave,
		        scope: this
		    }, {
		    	name: 'channelCanel',
		        text: '取消',
		        handler: this.modelCanel
		    }]
    	});
    	if(but.action == 'modfiyChannel'){
    		var number = channelModel.getCount();
    		if(number > 0){
    			cWin.show();
    			this.getChannelForm().getForm().loadRecord(record);
    			if(record.data.mtcode == 'banner'){
    				this.getChannelForm().down('numberfield[name=mnumber]').show();
    			}
    		} else {
    			return Ext.MessageBox.alert('提示', '您还未选择要修改的信息！');
    		}
    	} else {
    		cWin.show();
    		//添加根节点
	    	if(but.action == 'addRootChannel'){
	    		record = null;
	    		cWin.record = null;
	    		this.getChannelForm().down('textfield[name=mtcode]').hide();
	    		this.getChannelForm().down('textfield[name=chttp]').hide();
	    		this.getChannelForm().down('fieldcontainer[name=linktypeRadio]').hide();
	    		this.getChannelForm().down('fieldcontainer[name=isconfigCheck]').hide();
	    	}
    		if(record){
    			this.getChannelForm().down('textfield[name=ccode]').setValue(record.data.ccode);
	    		this.getChannelForm().down('textfield[name=pid]').setValue(record.data.id);
    		} else {
    			this.getChannelForm().down('textfield[name=ccode]').show();
    			this.getChannelForm().down('textfield[name=pid]').setValue(0);
    		}
    		this.getChannelForm().down('textfield[name=leaf]').setValue('on');
    	}
    },
    deleteChannel: function(){
    	var me = this;
    	var nodeModel = this.getChannelTree().getSelectionModel();
		var record = nodeModel.getSelection()[0];
		var cNode = me.getChannelTree().getStore().getNodeById(record.data.id);
    	if(cNode.childNodes.length > 0){
    		return Ext.MessageBox.alert('警告', '该节点下还有子节点，请先删除子节点！');
    	}
    	var num = nodeModel.getCount();
		if(num > 0){
			Ext.MessageBox.confirm('提示', '您确定要删除已选中的数据吗？', function(but){
				var node = me.getChannelTree().getStore().getNodeById(record.data.pid);
				var leafs = false;
				if(node && node.childNodes.length == 1){
					leafs = true;
				}
				if(but == 'yes'){
					Ext.Ajax.request({
		    			url: 'cms/deleteChannel',
		    			method: 'get',
		    			params : {
		    				id: record.data.id,
		    				pid: record.data.pid,
		    				cname: record.data.cname,
		    				leaf: leafs
		    			},
		    			success: function(response, opts) {
		    				var retul = Ext.decode(response.responseText);
					        if(retul.success){
					        	Ext.example.msg('','<p align="center">'+ retul.message +'</p>');
								var nodec = me.getChannelTree().getStore().getNodeById(record.data.id);
								nodec.remove();
								if(node && !node.hasChildNodes()){
									node.set('leaf', true);
								}
					        } else {
					        	Ext.MessageBox.alert('错误', retul.message)
					        }
					    },
					    failure: function(response, opts) {
					        console.log(response.status);
					    }
		    		});
				}
			});
			
		} else {
			return Ext.MessageBox.alert('提示', '您还未选择要删除的信息！');
		}
    },
    channelSave: function(but){
    	var me = this, win = but.ownerCt.ownerCt;
    	var chaForm = this.getChannelForm();
    	if(chaForm.getForm().isValid()){
    		chaForm.getForm().submit({
    			clientValidation: true,
                url: win.addOrUpdate == 'modfiyChannel' ? 'cms/updateChannel' : 'cms/addChannel',
                method: 'post',
                waitTitle: '提示',
                waitMsg: '系统正在提交数据，请稍等……',
                waitMsgTarget: true,
                params: {
			        isChannel: chaForm.down('checkboxfield[name=isChannel]').getValue()
			    },
                success: function(form, action){
                	var result = action.result;
                	if(result.success){
                		var node = null, id = '';
                		if(win.record){
                			node = me.getChannelTree().getStore().getNodeById(result.backupfield.pid);
            			} else {
            				node = me.getChannelTree().getRootNode();
            			}
                		if(win.addOrUpdate == 'modfiyChannel'){
                			var nodec = me.getChannelTree().getStore().getNodeById(result.backupfield.id);
                			nodec.set('cname', result.backupfield.cname);
                			nodec.set('mtcode', result.backupfield.mtcode);
                			nodec.set('chttp', result.backupfield.chttp);
                			nodec.set('cdesc', result.backupfield.cdesc);
                			nodec.set('isChannel', result.backupfield.isChannel);
                			nodec.set('mnumber', result.backupfield.mnumber);
                			//重新加载一下模块的数据
                			me.getChannelToModel(null, win.record);
                		} else {
                			//判断节点是否为叶子节点
                			if(node.isLeaf()){
                				node.set('leaf', false);
                			}
                			node.appendChild(result.backupfield, false, true);
	                		if(!node.isExpanded()){
	                			node.expand();
	                		}
                		}
                		Ext.example.msg('','<p align="center">'+ result.message +'</p>');
	                	//关闭弹窗窗体
	                	me.modelCanel(but);
                	}
                },
                failure: function(form, action){
                    if(action.result.message == 'could not execute statement'){
            			return Ext.MessageBox.alert('提示', '该编号已被使用');
            		}
                },
                scope: true
            });
    	}
    },
    seqencing: function(treegrid, colel, rowIndex, colIndex, e, record, row) {
    	var me = this;
    	var imgClass = Ext.query('img', colel)[0].className;
    	if(imgClass.indexOf('x-hidden') != -1){
    		return;
    	}
    	var meNode = me.getChannelTree().getStore().getNodeById(record.data.id);
    	meNode.collapse();
    	
    	var store = treegrid.getStore();
        var rother = null, meIndex = 0, youIndex = 0;
        if(colIndex == 1){
        	var mepath = meNode.getPath();
        	//截取本节点上一段路径
        	mepath = mepath.substring(0, mepath.lastIndexOf('/') + 1);
        	
        	rother = store.getAt(rowIndex - 1);//升序
        	//获取目标节点的路径
        	var youpath = rother.getPath();
        	youpath = youpath.substring(mepath.length);
        	var otherid = youpath.substring(0, youpath.indexOf('/'));
        	var targetNode = me.getChannelTree().getStore().getNodeById(otherid);
        	if(targetNode){
        		targetNode.collapse();
        		rother = targetNode;
        	}
        	
        	meIndex = record.data.index - 1;
        	youIndex = rother.data.index + 1;
        } else {
        	rother = store.getAt(rowIndex + 1);//降序
        	meIndex = record.data.index;
        	youIndex = rother.data.index - 1;
        }
        Ext.Ajax.request({
        	url: 'cms/seqencing',
			method: 'get',
			params : {
				cid: record.data.id,
				cindex: record.data.cindex,
				oid: rother.data.id,
				oindex: rother.data.cindex
			},
			success: function(response, opts) {
				var retul = Ext.decode(response.responseText);
		        if(retul.success){
	        	 	var parentNode = me.getChannelTree().getStore().getNodeById(record.data.pid);
	        	 	if(!parentNode){
	        	 		parentNode = me.getChannelTree().getRootNode();
	        	 	}
	        	 	record.remove();
	        	 	rother.remove();
	        	 	/**********将id对换一下**********/
	        	 	var id = record.data.id;
	        	 	record.data.id = rother.data.id;
		        	rother.data.id = id;
		        	/**********将索引值对换以下**********/
		        	var cindex = record.data.cindex;
		        	record.data.cindex = rother.data.cindex;
		        	rother.data.cindex = cindex;
		        	/**********插入新的孩子节点**********/
	        	 	parentNode.insertChild(meIndex, record);
	        	 	parentNode.insertChild(youIndex, rother);
		        	Ext.example.msg('','<p align="center">'+ retul.message +'</p>');
		        } else {
		        	Ext.MessageBox.alert('错误', retul.message)
		        }
		    },
		    failure: function(response, opts) {
		        console.log(response.status);
		    }
        });
    },
    comboxAfterRender: function(combox, obj){
    	this.templateStore = combox.getStore();
    	//加载模版的数据
    	this.templateStore.load();
    },
    comboxTemplateLoad: function(combox, obj){
    	if(this.templateStore){
    		combox.getStore().data = this.templateStore.data;
    	}
    },
    templateSelect: function(combox, obj){
    	if(combox.getValue() == 'banner'){
    		this.getChannelForm().down('numberfield[name=mnumber]').show();
    	} else {
    		this.getChannelForm().down('numberfield[name=mnumber]').setValue(0);
    		this.getChannelForm().down('numberfield[name=mnumber]').hide();
    	}
    },
    /**
     * @param {} grid
     * @param {} record
     * @param {} item
     * @param {} index
     * @param {} e
     * @param {} eOpts
     * 双击频道的时候，给该频道对应的模块赋值
     */
    getChannelToModel: function(grid, record, item, index, e, eOpts){
    	var me = this;
    	var path = this.getChannelTree().getStore().getNodeById(record.data.id).getDepth();
    	var chttp = record.data.chttp;
		if(path == 3 && chttp.indexOf('mall/index?mallId') != -1){
			return;
		}
    	this.getTemplatePanel().removeAll();//移除当前的模块模版
    	//如果为父节点就不执行下面的代码了
    	if(!record.data.leaf){
    		return;
    	}
    	var mtcode = record.data.mtcode;
    	if(mtcode == 'banner'){
    		var bbar = ['->'];
			for(var i = 1; i <= record.data.mnumber; i++){
    			bbar.push({
    				text: i,
					indexs: i,
					records: null,
					handler: function(b){
						var p = this.ownerCt.ownerCt;
						p.loadAdvertImg(b.indexs, b.records);
					}
    			})
    		}
    		var bannerPanel = Ext.create('plat.view.cms.S_Banner', {
    			title: record.data.cname,
				otitle: record.data.cname,
    			bbar: bbar,
    			nodeData: record,
				loadAdvertImg: function(number, records){
					this.number = number;
					this.setTitle(this.otitle+"【"+ number +"】");
					if(records){
						if(records.micon.indexOf('http') > -1){
							var html = '<div width="100%" height="100%" style="text-align:right;">' +
				    					'<img number="'+ number +'" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
							   			'<img width="'+ this.width +'" height="'+ this.height +'" src="'+ records.micon +'" style="vertical-align:middle;" />' +
							   	   '</div>';
						}else {
							var html = '<div width="100%" height="100%" style="text-align:right;">' +
				    					'<img number="'+ number +'" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
							   			'<img width="'+ this.width +'" height="'+ this.height +'" src="upload/'+ records.micon +'" style="vertical-align:middle;" />' +
							   	   '</div>';
						}				    	
				    	this.body.update(html);
				    	this.data = records;
					} else {
						this.body.update("");
					}
				}
    		});
    		this.getTemplatePanel().add(bannerPanel);
    	} else {
    		this.getTemplatePanel().add({
				nodeData: record,
				title: record.data.cname,
				otitle: record.data.cname,
				xtype: record.data.mtcode
			});
    	}
		
    	Ext.Ajax.request({
			url: 'cms/findAllModuel',
			method: 'post',
			params : {
				mchannel: record.data.ccode
			},
			success: function(response, opts) {
		        var modelObj = Ext.decode(response.responseText).data;
		        for(var i = 0; i < modelObj.length; i++){
		        	//获取色块的索引值
	        		var number = modelObj[i].mposition;
		        	if(mtcode == 'banner'){
		        		var buts = Ext.ComponentQuery.query(mtcode +' button');
		        		for(var y = 0; y < buts.length; y++){
		        			if(number == buts[y].indexs){
		        				buts[y].records = modelObj[i];
		        				break;
		        			}
		        		}
		        	}
		        	//根据选择器查找当前的色块对象
		        	var modelPanel = Ext.ComponentQuery.query('templatePanel panel[number='+ number +']')[0];
		        	if(modelPanel){
		        		if(modelObj[i].micon.indexOf('http') > -1){
		        			var html = '<div width="100%" height="100%" style="text-align:right;">' +
			        					'<img number="'+ number +'" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
	            			   			'<img width="'+ modelPanel.width +'" height="'+ modelPanel.height +'" src="'+ modelObj[i].micon +'" style="vertical-align:middle;" />' +
	            			   	   '</div>';
		        		}else {
		        			var html = '<div width="100%" height="100%" style="text-align:right;">' +
			        					'<img number="'+ number +'" src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />' +
	            			   			'<img width="'+ modelPanel.width +'" height="'+ modelPanel.height +'" src="upload/'+ modelObj[i].micon +'" style="vertical-align:middle;" />' +
	            			   	   '</div>';
		        		}			        	
			        	modelPanel.body.update(html);
			        	//将该色块的所有属性都赋值到该色块的data(自己定义的)属性上
			        	modelPanel.data = modelObj[i];
		        	}
		        }
		    },
		    failure: function(response, opts) {
		        console.log(response.status);
		    }
		});
    },
    setLinkFn: function(tree, record, item, index, e, eOpts){
    	if(record.getDepth() > 2){
	    	this.getModelForm().down('textfield[name=mname]').setValue(record.get('text'));
	    	this.getModelForm().down('textfield[name=mindex]').setValue(record.get('clazz'));
	    	this.getModelForm().down('textfield[name=mdesc]').setValue(record.get('description'));
	    	this.getModelForm().down('textfield[name=micon]').setValue(record.get('picture'));
	    	if(record.get('picture').indexOf('http') > -1){
	    		Ext.get('imgpre').dom.src = record.get('picture');
	    	}else {
	    		Ext.get('imgpre').dom.src = 'upload/' + record.get('picture');
	    	}	    	
    	} else {
    		this.getModelForm().down('textfield[name=mname]').reset();
	    	this.getModelForm().down('textfield[name=mindex]').reset();
	    	this.getModelForm().down('textfield[name=mdesc]').reset();
	    	this.getModelForm().down('textfield[name=micon]').reset();
	    	Ext.get('imgpre').dom.src = '';
    	}
    },
    afterModuleRender: function(panel){
    	//给每个色块都添加双击事件
    	var me = this;
    	panel.el.on('dblclick', function(){me.modelSetup(panel)}, panel);
    	panel.el.on('mouseover', this.modelMouseoverFn, panel, me);
//    	panel.el.on('mouseout', this.modelMouseoutFn, panel);
    },
    selectTemplate: function(combox){
    	this.getTemplatePanel().removeAll();
    	this.getTemplatePanel().add({xtype:combox.getValue()});
    },
    /**
     * 色块配置，包括添加和修改
     * @param {} panel
     * @param {} obj
     */
    modelSetup: function(panel, obj){
    	var tempObj = this.getTemplatePanel().items.items[0], modelForm = null;
    	var xtypes = 'modelConfigPanel', nodeData = tempObj.nodeData, record = tempObj.data;
    	if(nodeData){
    		var mtcode = nodeData.data.mtcode;
        	if(mtcode == 'banner'){
	    		xtypes = 'bannerForm';
	    		var but = Ext.ComponentQuery.query(mtcode +' button[indexs='+ panel.number +']')[0];
	    		if(!but.records){
	    			record = null;
	    		}
        	} else {
	    		tempObj = Ext.ComponentQuery.query('templatePanel panel[number='+ panel.number +']')[0];
	    		record = tempObj.data;
	    	}
    	} else {
    		tempObj = Ext.ComponentQuery.query('templatePanel panel[number='+ panel.number +']')[0];
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
    	if(xtypes != 'bannerForm'){
    		modelForm = this.getModelForm();
    	} else {
    		modelForm = this.getBannerForm();
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
    },
    modelSave : function(but){
    	var me = this, win = but.ownerCt.ownerCt;
    	var modelForm = win.modelForm;
    	if(modelForm.form.isValid()){
    		modelForm.getForm().submit({
    			clientValidation: true,
                url: win.addOrUpdate == 'add' ? 'cms/addModule' : 'cms/updateModule',
                method: 'post',
                waitTitle: '提示',
                waitMsg: '系统正在提交数据，请稍等……',
                waitMsgTarget: true, 
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
	                	//给预览模块的图标更新以下
	                	win.modelPanel.body.update(html);
	                	//将新的表单内容赋值给该色块
	                	win.modelPanel.data = modelForm.getValues();
	                	if(but.types == 'bannerForm'){
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
    },
    /**
     * 鼠标移入到色块的时候处理逻辑
     * @param {} panel
     * @param {} obj
     */
    modelMouseoverFn: function(panel, obj, thisObj){
    	var me = this;
    	if(obj.localName == 'img'){
    		var divObj = Ext.fly(Ext.fly(obj).parent('div').dom);
    		/*divObj.insertHtml(
    			'afterBegin', //beforeBegin, afterBegin, beforeEnd, afterEnd
				'<img src="resources/images/delete.png" style="position:absolute;cursor:pointer;" />'
    		);*/
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
							        console.log(response.status);
							    }
	    					});
	    				}
	    			});
	    		}
    		});
    	}
    },
    /**
     * 鼠标移出的时候，移除右上角的删除图标
     * @param {} panel
     * @param {} obj
     */
    modelMouseoutFn: function(panel, obj){
    	if(obj.localName == 'img'){
    		var divObj = Ext.fly(Ext.fly(obj).parent('div').dom);
//    		divObj.first().remove();
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
	imagesSubmit: function(but){
		var me = this;
		this.getUploadform().getForm().submit({
			url : 'public/uploadFile',
			clientValidation: true,
		    success: function(form, action) {
		    	var result = action.result;
		    	if (result.success) {
		    		var formObj = but.ownerCt.ownerCt.formObj;
		    		if(formObj){
				       formObj.down('textfield[name=micon]').setValue(result.message);
				       if(result.message.indexOf('http') > -1){
				       		Ext.get('imgpre').dom.src = result.message;
				       }else {
				       		Ext.get('imgpre').dom.src = 'upload/' +result.message;
				       }				       
				       me.getUploadwindow().hide();
				       Ext.example.msg('','<p align="center">'+ result.message +'</p>');
		    		} else {
		    			Ext.Msg.alert('提示','页面组件加载出错，请重新操作或者刷新！');
		    		}
		    	} else {
		    		Ext.Msg.alert('提示','<p align="center">'+result.message+'</p>');
		    	}
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
		    }
		});
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
    modelCanel: function(but){
    	but.ownerCt.ownerCt.close();
    }
});



