/**
 * 登录控制层
 */
Ext.define('plat.controller.LoginController', {
    extend: 'Ext.app.Controller',
    alias:'widget.logincontroller',
    
    views: [
    		'layout.LoginFormView',
    		'layout.LoginWindowView'
    		],
    refs: [
    		{
	            ref: 'loginform',
	            selector: 'loginform'
	        },
	        {
	            ref: 'loginwindwo',
	            selector: 'loginwindwo'
	        }
    ],
    init: function() {
        this.control({
        	'loginform':{
    			afterrender:function(loginform){
    				this.loadCookies(loginform);
					loginform.down('textfield[name=password]').on('specialkey',function(field,e){
						 // e.HOME, e.END, e.PAGE_UP, e.PAGE_DOWN,
                    	// e.TAB, e.ESC, arrow keys: e.LEFT, e.RIGHT, e.UP, e.DOWN
	                    if (e.getKey() == e.ENTER) {
                       		this.formSubmit(loginform);
	                    }
					},this);
    				loginform.down('button[action=login]').on('click',function(){
                		if(loginform.form.isValid()){
//    						var md5_password = hex_md5(loginform.down('textfield[name=password]').getValue());
                			if(loginform.down('checkbox[name=rusername]').getValue()){Ext.util.Cookies.set("username",loginform.down('textfield[name=username]').getValue());};
                			if(loginform.down('checkbox[name=rpassword]').getValue()){Ext.util.Cookies.set("user",loginform.down('textfield[name=username]').getValue()+'|'+loginform.down('textfield[name=password]').getValue());};
//                			loginform.down('textfield[name=password]').setValue(md5_password);
                			this.formSubmit(loginform);
                		};
					},this);
					loginform.down('button[action=reset]').on('click',function(){loginform.form.reset();},this);
    			}
    		}
        });
    },
    loadCookies:function(loginform){
    	if(Ext.util.Cookies.get("user")){
    		loginform.down('textfield[name=username]').setValue(Ext.util.Cookies.get("user").split('|')[0]);
			loginform.down('textfield[name=password]').setValue(Ext.util.Cookies.get("user").split('|')[1]);
    	}else if(Ext.util.Cookies.get("username")){
    		loginform.down('textfield[name=username]').setValue(Ext.util.Cookies.get("username"));
    		loginform.down('textfield[name=password]').focus();
    	}else{
    		return;
    	}
    },
    formSubmit:function(formpanel){
    	formpanel.getEl().mask("登陆验证中,请稍候...");
    	Ext.Ajax.request({
//			clientValidation: true,
		    type:'POST',
		    url: 'public/login',
		    params:{'password':Ext.ux.MD5.hex_md5(formpanel.down('textfield[name=password]').getValue()),'username':formpanel.down('textfield[name=username]').getValue()},
		    success: function(response) {
		    	formpanel.getEl().unmask();
		    	var result = Ext.decode(response.responseText);
		    	if(result.success){
			    	formpanel.ownerCt.hide();
//			    	Ext.getBody().mask("登陆成功！主界面渲染中,请稍候...");
	    			window.location.reload();
//			        Ext.getBody().unmask();
		    	}else{
		    		Ext.example.msg('登陆失败!',result.message);
		    	}
		    },
		    failure: function(form, action) {
		    	formpanel.getEl().unmask();
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		                Ext.example.msg('登录失败!', '请核对是否输入不合法的字符!');
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.example.msg('登录失败!', 'Ajax请求失败!');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
	            		formpanel.down('textfield[name='+action.result.errorfield+']').markInvalid(action.result.message);
	              		formpanel.down('textfield[name='+action.result.errorfield+']').focus();
		       }
		    }
		});
    }
});