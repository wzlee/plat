Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath({
	'Ext.ux':'jsLib/extjs/ux'
});
Ext.require([
	'Ext.ux.TabCloseMenu',
	'Ext.ux.MD5',
//	'Ext.ux.CheckColumn',
	'Ext.ux.grid.FiltersFeature',
//	'Ext.ux.RowExpander',
	'Ext.ux.GridComboBox',
	'Ext.ux.GridComboBoxList',
	'Ext.ux.DataView.DragSelector',
	'Ext.ux.UploadPanel',
	'Ext.ux.form.SearchField',
	'Ext.ux.TreeCombo'
]);
var BASE_DATA_MODULE = [];
var	OPERATION_DATA_MODULE = [];
var	PLAT_CMS_MODULE = [];
var	SYSTEM_DATA_MODULE = [];
var	FLAT_DATA_MODULE = [];
var	WX_DATA_MODULE = [];
var plat = new Ext.application({
    name: 'plat',
    controllers: [
    	'MainController',
    	'system.MonitorController',
    	'service.ServiceController',
    	'staffrole.ERoleController',
    	'staffrole.SRoleController',
    	'cms.CmsController',
    	'manager.ManagerController',
    	'cms.NewsController',
    	'system.CategoryController',
    	'enteruser.EnterUserController',
    	'enteruser.ApprUserController',
    	'business.OrderController',
    	'flat.FlatController',
    	'flat.FlatManagerController',
    	'mall.MallController',
    	'policy.PolicyController',
    	'info.InfoController',
    	'mobileapp.MobileAppController',
    	'wx.WXNewsController'
    ],
    launch: function() {
    	Ext.get('loading').remove();
        Ext.fly('loading-mask').animate({
            opacity:0,
            remove:true
        });
		Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [{
    			xtype:'menupanel',
    			region:'west',
    			width:250,
    			collapsible:true,
    			split:true
    		}, {
                xtype: 'contenttab',
                region:'center'
            }]
    	});
        this.controllers.addListener('add',this.initController,this);
        // 是否锁屏
        var lockFlag = Ext.util.Cookies.get('lockFlag', true);
        if(lockFlag == 'true'){
        	this.lock();
        }
    },
    initController:function(index, controller, token){
    	controller.init();
    	console.log(token+'初始化完毕...');
  	},
  	lock:function(){
		Ext.create('Ext.window.Window', {
    		id:'lock-window',
			title:'屏幕已被锁定!',
			layout:'fit',
			modal:true,
			closable:false,
			buttonAlign:'center',
			items: [{
				xtype:'form',
				width:200,
				height:50,
				bodyPadding:'10',
				items:[
						{
							xtype:'textfield',
							inputType:'password',
							name:'pwd',
							submitValue : false,
							labelAlign:'right',
							labelWidth:60,
							allowBlank:false,
							fieldLabel:'用户密码'
						}
				]
			}],
			buttons:[
					{
						text:'解锁',
						handler:function(button){
							var form = this.up('window').down('form').form;
							if(form.isValid()){
								form.doAction('submit',{
									url : 'manage/unlock',
									params : {
										password : $.md5(form.findField('pwd').getValue())
									},
									type:'POST',
									success:function(form,action){
										if(action.result.success){
											button.ownerCt.ownerCt.close();
											Ext.util.Cookies.set('lockFlag', false);
										}
										Ext.example.msg('',action.result.message);
									},
									failure:function(form,action){
										Ext.example.msg('',action.result.message);
									}
								});
							}
						}
					}
			]
		}).show();
	}
});
// 全局控制Console对象是否可用
var console = window.console;
$(function(){
	if(console == undefined){
		console = {};
		var func = ['log', 'debug', 'info', 'warn', 'error', 'assert', 'dir', 'dirxml',
				'trace','group', 'groupEnd', 'time', 'timeEnd', 'profile', 'profileEnd',
				'count', 'exception', 'table'];
		for (var i = 0, j = func.length; i < j; i++) {
			console[func[i]] = function() {
			}
		} 
	}
});
// 全局控制Session过期
Ext.Ajax.on('requestcomplete', function(conn, response, options) {
	if(response.getResponseHeader){
		var sessionStatus = response.getResponseHeader('sessionstatus');
		if (typeof(sessionStatus) != "undefined") {
			Ext.Msg.alert('提示', '会话超时，请重新登录!', function(btn, text) {
				if (btn == 'ok') {
					window.location = 'admin';
				}
			});
		}
	}
}, this);