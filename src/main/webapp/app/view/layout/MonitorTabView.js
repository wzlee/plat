/*!
* 商品view层,这里只是一个负责显示的grid,没有逻辑代码
*/
Ext.define('plat.view.layout.MonitorTabView' ,{
    extend: 'Ext.tab.Panel',
    alias : 'widget.monitortab',
    title : '',
    activeTab:0,
    initComponent:function(){
		this.items=[
					{
            			xtype:'panel',
            			title:'系统首页',
            			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="http://www.baidu.com"> </iframe>'
            		}
					]
	    this.callParent(arguments);
    }
});

