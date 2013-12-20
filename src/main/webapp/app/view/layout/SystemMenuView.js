/**
 * 系统菜单视图
 */
Ext.define('plat.view.layout.SystemMenuView' ,{
    extend: 'Ext.button.Button',
    alias : 'widget.systemmenu',
    
    text:'系统菜单',
    iconCls:'icon-windows',
	initComponent:function(){
		var me = this;
    	me.menu = [{
            			text:'系统信息',
            			iconCls:'icon-about',
            			handler:function(){
//            				me.aboutSystem();
            				Ext.Msg.alert('提示','该功能暂未开放');
            			}
            		},'-',
            		{
            			text:'修改密码',
            			iconCls:'icon-password',
            			handler:function(){
            				me.changePassword();
            			}
            		},'-',
        			{
            			text:'锁定屏幕',
            			iconCls:'icon-lock',
            			handler:function(){
            				plat.app.lock();
            				Ext.util.Cookies.set('lockFlag', true);
            			}
            		},'-',
        			{
            			text:'退出登陆',
            			iconCls:'icon-logout',
            			handler:function(){
            				me.logout();
            			}
            		}];
	    me.callParent(arguments);
    },
    changetheme:function(theme){
//        location.href = location.href.replace(
//            'ext-theme-' + theme, 'ext-theme-' + combo.getValue()
//        );
    	if(Ext.isIE && theme != 'ext-ie'){
    		Ext.example.msg('温馨提示:','系统检测到你使用的是IE浏览器,系统自动设置样式为[<font color="green">IE兼容</font>],您选择其他的主题样式可能会产生样式不兼容的问题!');
    	}else{
    		Ext.example.msg('','系统主题修改成功!');
    	}
		Ext.util.CSS.swapStyleSheet('theme', 'jsLib/extjs/resources/css/' + theme+'.css');
		Ext.util.Cookies.set('theme', theme);
    },
    changePassword:function(){
    	var passwordwin = Ext.getCmp('passwordwindow');
    	if(!passwordwin){
    		passwordwin = Ext.createWidget('passwordwindow');
    	}
    	var passwordform = passwordwin.getComponent('passwordform');
    	passwordform.form.reset();
    	passwordwin.show();
    },
    aboutSystem:function(){
    	Ext.createWidget('systemwindow').show();
    },
    logout:function(){
		Ext.getBody().mask("退出系统中,请稍候...");
		Ext.Ajax.request({
			url:'public/userlogout',
			timeout:10000,
			success:function(response){
				Ext.getBody().unmask();
				var result = Ext.decode(response.responseText);
		    	if(result.success){
					Ext.util.Cookies.clear();
					window.location.reload();
		    	}else{
					Ext.example.msg("提示:",'退出系统失败,请直接关闭页面!');
		    	}
			},
			failure:function(response){
				Ext.getBody().unmask();
				Ext.example.msg("提示:",'退出系统失败,请直接关闭页面!');
			}
		});
	}
});
