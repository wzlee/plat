Ext.define('plat.controller.system.CategoryController', {
    extend: 'Ext.app.Controller',
    stores: ['system.CategoryStore'],
    views: [
    	'system.CategoryGrid',
    	'system.CategoryWindow'
    ],
    refs: [
	        {
	            ref: 'categorygrid',
	            selector: 'categorygrid'
	        },{
	        	ref: 'categoryWindow',
	            selector: 'categoryWindow'
	        }
    ],
    init: function() {
        this.control({
            'categorygrid' : {
            	afterrender:function(gridpanel){
            		gridpanel.down('button[action=add]').on('click',function(){
						var categoryWindow = null;
				    	var windows = Ext.ComponentQuery.query('categoryWindow');
	                	if(windows.length > 0){
	                		categoryWindow = windows[0];
	                		categoryWindow.setTitle('添加根类别');
	                		categoryWindow.show();
	                	} else {
	                		categoryWindow = Ext.widget('categoryWindow',{title:'添加根类别'}).show();
	                	}
	                	var form = categoryWindow.down('form').form;
	                	form.reset();
	                	makeCode(gridpanel.getStore().getRootNode(), form);
					},this);
            		gridpanel.down('button[action=refresh]').on('click',function(){
						gridpanel.getStore().reload();
					},this);
            	}
            },
            'categoryWindow' :{
            	afterrender:function(window){
            		window.down('button[action=submit]').on('click',function(){
            			var store = Ext.data.StoreManager.lookup('system.CategoryStore');
        				var mask = new Ext.LoadMask(window.getEl(), {
							msg : '提交中,请稍等...',
							constrain : true
						});
        				if(window.title.indexOf('修改') > -1){
					    	window.getComponent('categoryform').form.updateRecord();
        					window.close();
        				}else{
        					mask.show();
        					window.getComponent('categoryform').form.submit({
	        					url : 'category/add',
		        				clientValidation: true,
							    success: function(form, action) {
							    	mask.hide();
							    	if (action.result.success) {
										Ext.example.msg('提示', '已成功新增！');
										window.close();
										// STEP1. 获取父节点
										var parentRecord = form.findField('pid').value == "" ? store.getRootNode() 
																: store.getById(form.findField('pid').value);
										// STEP2. 创建新节点
										var record = Ext.create('plat.model.system.CategoryModel', action.result.data[0]);
										record.data.loaded = true;
										store.autoSync = false;
										parentRecord.appendChild(record);
										form.reset();
									} else {
										Ext.Msg.alert('提示', '<p align="center">操作失败</p>');
									}
									store.autoSync = true;
							    },
							    failure: function(form, action) {
							    	mask.hide();
							        switch (action.failureType) {
							            case Ext.form.action.Action.CLIENT_INVALID:
								            Ext.Msg.alert('提示', '提交表单中包含非法字符(或必填项为空)！');
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
        				}
            		},this);
            		window.down('button[action=esc]').on('click',function(){
            			window.getComponent('categoryform').form.reset();
            			window.close();
            		});
            	}
            }
        });
    }    
});

/**
 * 添加类别时，根据父类别生成code，并放入form中<br/>
 * 应用以下js文件:<br/>
 * CategoryController.js
 * CategoryGrid.js
 * @param {} parentRecord 父类别
 * @param {} form 目标form
 */
function makeCode(parentRecord, form){
	var childLength = parentRecord.childNodes.length;
	var code = '';
	var flag = parentRecord.isRoot();
	if(flag) code = ''; // 当没有任何根类时的初始code
	if(childLength > 0 && !flag){
		var childLastCode = parseInt(parentRecord.childNodes[childLength - 1].data.code);
		code = (childLastCode + 1);
	}else{
		parentCode = parentRecord.data.code;
		code = flag ? code : (parentCode == '' ? '10' : parentCode + '01');
	}
	form.findField('code').setValue(code);
}