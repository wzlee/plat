/**
 * 左边菜单树
 */
Ext.define('plat.view.layout.MenuTreeView' ,{
    extend: 'Ext.tree.TreePanel',
    alias : 'widget.menutree',
    
    animate : Ext.isIE9?false:true,
    animCollapse : true,
    titleAlign:'center',
    rootVisible:false,
    store:'layout.MenuStore',
    initComponent:function(){
    	Ext.apply(this,{
    		
    	})
	    this.callParent(arguments);
    }
});

