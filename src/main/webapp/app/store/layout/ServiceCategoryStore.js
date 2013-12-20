Ext.define('plat.store.layout.ServiceCategoryStore', {
    extend: 'Ext.data.TreeStore',
    xtype:'servicecategorystore',
    
    fields:['id','text','pid','leaf','clazz'],
    proxy: {
        type: 'ajax',
        url: 'category/load'
    },
    root: {
        text: '所有类别',
        expanded: true
    },
    reader:{  
		type: 'json',
		root: '',
		messageProperty:"message"  
	}, 
    folderSort: true,
    nodeParam: 'pid',
    sorters: [{
        property: 'id',
        direction: 'ASC'
    }]
});
