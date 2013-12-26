Ext.define('plat.controller.enteruser.ApprUserController', {
    extend: 'Ext.app.Controller',    
    xtype:'apprusercontroller',
    
    stores: [
				'enteruser.ApprUserStore',
				'enteruser.ApprPersonalUserStore',
				'enteruser.OrgRegisterApprovalStore'
			],
    views: [
    			'enteruser.ApprUserWindow',
    			'enteruser.ApprUserDetailWindow',
    			'enteruser.ApprUserTab',
    			'enteruser.ApprPersonalUserWindow',
    			'enteruser.ApprPersonalUserDetailWindow',
    			'enteruser.ApprOrgRegisterDetailWindow',
    			'service.PictureWindow'
    		],    
     refs : [{
				ref : 'appruserwindow',
				selector : 'appruserwindow'
			}, {
				ref : 'apprusertab',
				selector : 'apprusertab'
			}, {
				ref : 'appruserdetailwindow',
				selector : 'appruserdetailwindow'
			},
			{
				ref : 'apprpersonaluserwindow',
				selector : 'apprpersonaluserwindow'
			},
			{
				ref : 'apprpersonaluserdetailwindow',
				selector : 'apprpersonaluserdetailwindow'
			},
			{
				ref : 'approrgregisterdetailwindow',
				selector : 'approrgregisterdetailwindow'
			},
			{
				ref : 'picturewindow',
				selector: 'picturewindow'
			}
		],
   
    init: function() {
        this.control({
        	'apprusertab actioncolumn':{
        		//企业实名认证
            	pictureclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		  var record = grid.getStore().getAt(rowIndex);
					  var src = record.data.licenceDuplicate;
					  if (src) {
						 if(src.indexOf('http') > -1){
						     src = record.data.licenceDuplicate;
						 }else {
						     src = 'upload/'+record.data.licenceDuplicate;
						 }					       			
					  } else {
						 src = 'resources/images/service/default_service_pic.gif';
					  }
					  var pictureWindows = this.getPicturewindow();
				      if(!pictureWindows){
				    	  pictureWindows = Ext.widget('picturewindow',{
				    		title:'查看图片['+record.data.name+']'
				          });    		
				      }
					  pictureWindows.update({src:src});
					  pictureWindows.show();
					  pictureWindows.setTitle('查看图片['+record.data.name+']');
            	},
            	pictureclick1:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		  var record = grid.getStore().getAt(rowIndex);
					  var src = record.data.businessLetter;
					  if (src) {
						 if(src.indexOf('http') > -1){
						     src = record.data.businessLetter;
						 }else {
						     src = 'upload/'+record.data.businessLetter;
						 }					       			
					  } else {
						 src = 'resources/images/service/default_service_pic.gif';
					  }
					  var pictureWindows = this.getPicturewindow();
				      if(!pictureWindows){
				    	  pictureWindows = Ext.widget('picturewindow',{
				    		title:'查看图片['+record.data.name+']'
				          });    		
				      }
					  pictureWindows.update({src:src});
					  pictureWindows.show();
					  pictureWindows.setTitle('查看图片['+record.data.name+']');
            	},
            	//服务机构认证
            	pictureclick2:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		  var record = grid.getStore().getAt(rowIndex);
					  var src = record.data.licenceDuplicate;
					  if (src) {
						 if(src.indexOf('http') > -1){
						     src = record.data.licenceDuplicate;
						 }else {
						     src = 'upload/'+record.data.licenceDuplicate;
						 }					       			
					  } else {
						 src = 'resources/images/service/default_service_pic.gif';
					  }
            		  if(record.raw.enterprise.isApproved) {
            		  	value = record.raw.enterprise.licenceDuplicate;
            		  	if(src.indexOf('http') > -1){
						     src = value;
						 }else {
						     src = 'upload/'+value;
						 }	
            		  }
					  var pictureWindows = this.getPicturewindow();
				      if(!pictureWindows){
				    	  pictureWindows = Ext.widget('picturewindow',{
				    		title:'查看图片['+record.data.name+']'
				          });    		
				      }
					  pictureWindows.update({src:src});
					  pictureWindows.show();
					  pictureWindows.setTitle('查看图片['+record.data.name+']');
            	},
            	pictureclick3:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		  var record = grid.getStore().getAt(rowIndex);
					  var src = record.data.businessLetter;
					  if (src) {
						 if(src.indexOf('http') > -1){
						     src = record.data.businessLetter;
						 }else {
						     src = 'upload/'+record.data.businessLetter;
						 }					       			
					  } else {
						 src = 'resources/images/service/default_service_pic.gif';
					  }
					  if(record.raw.enterprise.isApproved){
					  	value = record.raw.enterprise.businessLetter;
					  	if(src.indexOf('http') > -1){
						     src = value;
						 }else {
						     src = 'upload/'+value;
						 }	
					  }
					  var pictureWindows = this.getPicturewindow();
				      if(!pictureWindows){
				    	  pictureWindows = Ext.widget('picturewindow',{
				    		title:'查看图片['+record.data.name+']'
				          });    		
				      }
					  pictureWindows.update({src:src});
					  pictureWindows.show();
					  pictureWindows.setTitle('查看图片['+record.data.name+']');
            	},
            	//个人用户实名认证
            	pictureclick4:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		  var record = grid.getStore().getAt(rowIndex);
					  var src = record.data.personalPhoto;
					  if (src) {
						 if(src.indexOf('http') > -1){
						     src = record.data.personalPhoto;
						 }else {
						     src = 'upload/'+record.data.personalPhoto;
						 }					       			
					  } else {
						 src = 'resources/images/ucenter/default_logo.jpg';
					  }
					  var pictureWindows = this.getPicturewindow();
				      if(!pictureWindows){
				    	  pictureWindows = Ext.widget('picturewindow',{
				    		title:'查看图片['+record.data.name+']'
				          });    		
				      }
					  pictureWindows.update({src:src});
					  pictureWindows.show();
					  pictureWindows.setTitle('查看图片['+record.data.name+']');
            	},
            	pictureclick5:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		  var record = grid.getStore().getAt(rowIndex);
					  var src = record.data.idCardPhoto;
					  if (src) {
						 if(src.indexOf('http') > -1){
						     src = record.data.idCardPhoto;
						 }else {
						     src = 'upload/'+record.data.idCardPhoto;
						 }					       			
					  } else {
						 src = 'resources/images/ucenter/default_logo.jpg';
					  }
					  var pictureWindows = this.getPicturewindow();
				      if(!pictureWindows){
				    	  pictureWindows = Ext.widget('picturewindow',{
				    		title:'查看图片['+record.data.name+']'
				          });    		
				      }
					  pictureWindows.update({src:src});
					  pictureWindows.show();
					  pictureWindows.setTitle('查看图片['+record.data.name+']');
            	}
            },
            'appruserwindow' : {
            	afterrender:function(window){
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){
            			mask.show();
            			window.getComponent('appruserform').form.submit({
            				url : 'ucenter/approve',
            				success : function(form, action){            					
            					Ext.ComponentQuery.query('apprusertab')[0].down('grid')
            							.getStore().remove(form.getRecord());
            					mask.hide();
            					window.close();
            					var parentWin = Ext.ComponentQuery.query('appruserdetailwindow')[0];
            					if(parentWin) parentWin.close();
            					Ext.Msg.alert('提示', action.result.message);
            				},
            				failure : function(form, action){
            					mask.hide();
            					Ext.Msg.alert('提示', action.result.message)
            				}
            			});
            		},this);
            		window.down('button[action=esc]').on('click',function(){
            			window.getComponent('appruserform').form.reset();
            			window.close();
            		});
            	}
            },
            'apprpersonaluserwindow' : {//个人用户认证审核信息弹窗
            	afterrender:function(window){
            		var mask = new Ext.LoadMask(window.getEl(), {
						msg : '提交中,请稍等...',
						constrain : true
					});
            		window.down('button[action=submit]').on('click',function(){
            			mask.show();
            			window.getComponent('apprpersonaluserform').form.submit({
            				url : 'ucenter/userApprove',
            				success : function(form, action){            					
            					Ext.ComponentQuery.query('apprusertab')[0].down('grid')
            							.getStore().remove(form.getRecord());
            					mask.hide();
            					window.close();
            					var parentWin = Ext.ComponentQuery.query('apprpersonaluserdetailwindow')[0];
            					if(parentWin) parentWin.close();
            					Ext.Msg.alert('提示', action.result.message);
            				},
            				failure : function(form, action){
            					mask.hide();
            					Ext.Msg.alert('提示', action.result.message)
            				}
            			});
            		},this);
            		window.down('button[action=esc]').on('click',function(){
            			window.getComponent('apprpersonaluserform').form.reset();
            			window.close();
            		});
            	}
            },
            'apprusertab' : {
            	afterrender:function(panel){
            		panel.down('button[action=search]').on('click',function(){
            			var store = panel.down('grid').getStore();
            			var flag = panel.down('#showRealName').disabled;
            			if(panel.down('#showUserRealName').disabled){
            				flag = true;
            			}
            			if(panel.down('#showOrgRegister').disabled){
            				flag = true;
            			}
        				Ext.apply(store.proxy.extraParams,{
        					username : flag ? panel.down('#username').value : '',
        					enterpriseName : panel.down('#enterpriseName').value,
        					applyTimeBegin : panel.down('#applyTimeBegin').value,
        					applyTimeEnd : panel.down('#applyTimeEnd').value
        				});
        				store.loadPage(1);
            		});
            		panel.down('grid').on('itemdblclick', function(me, record){
            			if(panel.down('#showUserRealName').disabled){//个人用户详情弹窗不同
            				var apprpersonaluserdetail = null;
            				var windows = Ext.ComponentQuery.query('apprpersonaluserdetailwindow');
		                	if(windows.length > 0){
		                		apprpersonaluserdetail = windows[0];
		                		apprpersonaluserdetail.show();
		                	} else {
		                		apprpersonaluserdetail = Ext.widget('apprpersonaluserdetailwindow').show();
		                	}
		                	apprpersonaluserdetail.setTitle('个人用户实名认证申请详情');
		                	apprpersonaluserdetail.down('form').form.reset();
		                	apprpersonaluserdetail.down('form').loadRecord(record);
            			}else if(panel.down('#showOrgRegister').disabled){ // 服务机构注册申请详情
            				var approrgregisterdetailwindow = null;
            				var windows = Ext.ComponentQuery.query('approrgregisterdetailwindow');
		                	if(windows.length > 0){
		                		approrgregisterdetailwindow = windows[0];
		                		approrgregisterdetailwindow.show();
		                	} else {
		                		approrgregisterdetailwindow = Ext.widget('approrgregisterdetailwindow').show();
		                	}
		                	approrgregisterdetailwindow.setTitle('服务机构注册申请详情');
		                	approrgregisterdetailwindow.down('form').form.reset();
		                	approrgregisterdetailwindow.down('form').loadRecord(record);
            			}else{
		            		var appruserdetail = null;
					    	var windows = Ext.ComponentQuery.query('appruserdetailwindow');
		                	if(windows.length > 0){
		                		appruserdetail = windows[0];
		                		appruserdetail.show();
		                	} else {
		                		appruserdetail = Ext.widget('appruserdetailwindow').show();
		                	}
		                	if(panel.down('#showRealName').disabled){
	                			appruserdetail.setTitle('企业用户实名申请详情');
	                			appruserdetail.down('form').form.findField('icRegNumber').show();
		                	}else {
	                			appruserdetail.setTitle('用户服务机构申请详情');
	                			appruserdetail.down('form').form.findField('icRegNumber').hide();
		                	}
		                	appruserdetail.down('form').form.reset();
		                	appruserdetail.down('form').loadRecord(record);
            			}
            		});
            	}
            },
            'appruserdetailwindow' : {
            	afterrender:function(window){
            		window.down('button[action=submit]').on('click',function(){
            			var record = window.down('form').getRecord();
            			openApprUserWindow('通过', record);
            		});
            		window.down('button[action=esc]').on('click',function(){
            			var record = window.down('form').getRecord();
            			openApprUserWindow('驳回', record);
            		});
            	}
            },
            'apprpersonaluserdetailwindow' : {
            	afterrender:function(window){
            		window.down('button[action=submit]').on('click',function(){
            			var record = window.down('form').getRecord();
            			openApprPersonalUserWindow('通过', record);
            		});
            		window.down('button[action=esc]').on('click',function(){
            			var record = window.down('form').getRecord();
            			openApprPersonalUserWindow('驳回', record);
            		});
            	}
            }
        })    
    }
});

/**
 * 查询参数管理类<br/>
 * 应用以下js：<br/>
 * 
 * ApprUserTab.js
 * @type 
 */
var ParamManager = {
	realNameMap : {},
	serviceApplyMap : {},
	userRealNameMap : {},
	orgRegisterMap : {},
	inputParam : function(paramPanel, paramMap){
		paramMap.username = paramPanel.down('#username').value;
		paramMap.enterpriseName = paramPanel.down('#enterpriseName').value;
		paramMap.applyTimeBegin = paramPanel.down('#applyTimeBegin').value;
		paramMap.applyTimeEnd = paramPanel.down('#applyTimeEnd').value;
	},
	outParam : function(paramPanel, paramMap){
		paramPanel.down('#username').setValue(paramMap.username);
		paramPanel.down('#enterpriseName').setValue(paramMap.enterpriseName);
		paramPanel.down('#applyTimeBegin').setValue(paramMap.applyTimeBegin);
		paramPanel.down('#applyTimeEnd').setValue(paramMap.applyTimeEnd);
	},
	resetParam : function(paramPanel){
		paramPanel.down('#username').setValue('');
		paramPanel.down('#enterpriseName').setValue('');
		paramPanel.down('#applyTimeBegin').setValue('');
		paramPanel.down('#applyTimeEnd').setValue('');
	}
}

/**
 * 弹出认证用户窗口<br/>
 * 应用以下js：<br/>
 * 
 * ApprUserTab.js
 * @param {} title
 * @param {} record
 */
function openApprUserWindow(title, record){
	var appruserwindow = null;
	var windows = Ext.ComponentQuery.query('appruserwindow');
	if(windows.length > 0 ){
		appruserwindow = windows[0];
		appruserwindow.setTitle(title);
		appruserwindow.show();
	} else {
		appruserwindow = Ext.widget('appruserwindow',{title:title}).show();
	}
	var form = appruserwindow.getComponent('appruserform').form;
	form.reset();
	record.data.approveStatus = appruserwindow.title.indexOf('通过') > -1 ? '1' :'0';
	form.loadRecord(record);
}

/**
 * 弹出个人用户认证窗口<br/>	xuwf 2013-10-28
 * 应用以下js：<br/>
 * 
 * ApprUserTab.js
 * @param {} title
 * @param {} record
 */
function openApprPersonalUserWindow(title, record){
	var apprpersonaluserwindow = null;
	var windows = Ext.ComponentQuery.query('apprpersonaluserwindow');
	if(windows.length > 0 ){
		apprpersonaluserwindow = windows[0];
		apprpersonaluserwindow.setTitle(title);
		apprpersonaluserwindow.show();
	} else {
		apprpersonaluserwindow = Ext.widget('apprpersonaluserwindow',{title:title}).show();
	}
	var form = apprpersonaluserwindow.getComponent('apprpersonaluserform').form;
	form.reset();
	record.data.approveStatus = apprpersonaluserwindow.title.indexOf('通过') > -1 ? '1' :'0';
	form.loadRecord(record);
}
