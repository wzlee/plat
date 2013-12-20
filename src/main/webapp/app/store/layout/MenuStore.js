Ext.define('plat.store.layout.MenuStore', {
    extend: 'Ext.data.TreeStore',
    xtype:'menustore',
    
    model:'plat.model.layout.Menu',
    fields:['id','text','pid','leaf','idxtype'],
    proxy: {
        type: 'ajax',
        url: 'rights/load'
    },
//    root: {
//        text: '所有类别',
//        id:0,
//        expanded: true
//    },
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
