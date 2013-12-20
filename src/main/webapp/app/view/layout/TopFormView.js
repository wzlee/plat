/*!
* 商品view层,这里只是一个负责显示的grid,没有逻辑代码
*/
Ext.define('plat.view.layout.TopFormView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.topform',
    title : '',
//    frame:true,
    layout: 'column',
    defaults:{
    	xtype:'displayfield',
		labelAlign:'right',
		labelWidth:60,
		labelStyle:'font-family:Century Gothic,Microsoft YaHei,Verdana;text-shadow: 0 1px 0 #FFFFFF, 1px 2px 2px #AAAAAA;',
		fieldStyle:'color:green'
    },
    initComponent:function(){
    	this.items = [
//    					{
//						    value:'<a href="http://www.plat.com" target="_blank" style="text-decoration:none;" title="去平台网站首页">运营支撑系统</a>',
//						    fieldStyle:'color:blue;padding-top:3px;margin-left:20px;text-shadow: 0 1px 0 #FFFFFF, 1px 2px 2px #AAAAAA;',
//						    width:120
//						},
    					{
						    fieldLabel:'当前时间',
						    name: 'currenttime',
						    width:280
						},
    					{
						    fieldLabel:'当前用户',
						    name: 'nickname',
						    width:150
						},
						{
						    fieldLabel:'角色名',
						    name: 'groupname',
						    width:150
						},
						{
						    fieldLabel:'登陆时间',
						    name: 'logindate',
						    width:200
						},
						{
						    fieldLabel:'登陆IP',
						    name: 'loginip',
						    width:150
						}
					];
	    this.callParent(arguments);
    }
});

