Ext.define('plat.view.layout.LoginWindowView',{
	extend:'Ext.window.Window',
	alias : 'widget.loginwindow',
	id: 'loginwindow',
	title:'系统登录',
	layout:'fit',
	frame:true,
	closable:false,
	tools:[{
		type : 'toggle',
		tooltip : '隐藏面板',
		handler : function(event, toolEl, panel) {
		}
	}, {
		type : 'help',
		tooltip : '帮助和关于',
		handler : function(event, toolEl, panel) {
		}
	}],
	initComponent:function(){
		this.callParent(arguments);
	}
});