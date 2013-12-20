/*!
* 商品view层,这里只是一个负责显示的grid,没有逻辑代码
*/
Ext.define('plat.view.layout.WelcomeView' ,{
    extend: 'Ext.form.Panel',
    xtype : 'welcomepanel',
    title : '',
    frame:true,
//    layout: 'column',
    bodyPadding:'50',
    defaults:{
    	xtype:'displayfield',
		labelAlign:'right',
		labelWidth:100,
		labelStyle:'font-family:Century Gothic,Microsoft YaHei,Verdana;text-shadow: 0 1px 0 #FFFFFF, 1px 2px 2px #AAAAAA;',
		fieldStyle:'color:green'
    },
//    title:'欢迎登陆<a href="http://www.plat.com" target="_blank" style="text-decoration:none;" title="去平台网站首页">运营支撑系统</a>',
    initComponent:function(){
    	this.items = [
    					{
						    fieldLabel:'',
						    name: 'currenttime',
						    width:450
						},
    					{
						    fieldLabel:'当前用户',
						    name: 'nickname',
						    value:manager.username,
						    width:450
						},
						{
						    fieldLabel:'角色',
						    name: 'groupname',
						    value:manager.role.rolename,
						    width:450
						}
					];
	    this.callParent(arguments);
    }
});

