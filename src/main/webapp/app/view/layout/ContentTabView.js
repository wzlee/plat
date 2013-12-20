/*!
* 商品view层,这里只是一个负责显示的grid,没有逻辑代码
*/
Ext.define('plat.view.layout.ContentTabView' ,{
    extend: 'Ext.tab.Panel',
    alias : 'widget.contenttab',
    title : '',
    activeTab:0,
    initComponent:function(){
		this.items=[
					{
            			xtype:'panel',
            			id:'indexpage',
            			title:'平台网站首页',
            			bodyPadding:'10px',
            			layout:'fit',
//            			loader:{
//	                    	loadMask: {
//		                        showMask: true,
//		                        msg: "信息加载中..."
//		                    },
//		                    scripts: true,
//		                    autoLoad: true,                    
//		                    url: "html/home.html"
//		                },
            			items:[
            				{
            					xtype:'welcomepanel'
            				}
            			],
//            			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="home.html"> </iframe>',
//				        bbar:['->','-',"System UI Build on <a href='http://www.sencha.com' target='_blank' title='了解ExtJS'>ExtJs-4.2.1-GPL</a>,&nbsp;&nbsp;E_mail:<a href = 'http://mailto:admin@pservice.cn' target='_blank' title='发送电子邮件'>admin@pservice.cn</a>",'-'],
            			listeners:{
            				afterrender:function(){
            					Ext.getBody().unmask();
            				}
            			}
            		}
					];
		this.plugins = [Ext.create('Ext.ux.TabCloseMenu', {
		     closeTabText : '关闭当前',
		     closeOthersTabsText : '关闭其他',
		     closeAllTabsText : '关闭所有'
		    })
		];
	    this.callParent(arguments);
    }
});

