Ext.define('plat.view.manager.MenuGrid',{
	extend:'Ext.tree.Panel',
	alias:'widget.menugrid',
	title:'权限菜单显示',
	columnLines:true,
	rootVisible: false,
	id:'menugrid',
    height: 400,
    width: 300,
//    selModel:new Ext.selection.TreeModel({
//    	model:'MULTI',
//    	toggleOnClick:false,
//    	listeners:{
//    		selectionchange:function(record,selected){
////    			record.store.findRecord('id',record.getCurrentPosition().record.raw.pid).set('checked',true);
//    			Ext.each(record.store.query('pid',record.getCurrentPosition().record.raw.id).items,function(child){
//    				child.set('checked',true);
//    			});
//    		}
//    	}
//    }),
    closeAction:'hide',
    useArrows: true,
    tbar :['-',{iconCls:'icon-add',text:'保存角色菜单',action:'save',hidden:false},'-','->',
    	  	{xtype: 'hiddenfield',name : 'roleid' },
    	  	{xtype: 'hiddenfield',name : 'rolename' },
    	  	{xtype: 'hiddenfield',name : 'roledesc' }
	      ],
    store:new Ext.data.TreeStore({
        fields: ['id','pid','text','idxtype','leaf',{name:'checked',mapping:'select'}]
    })
});

