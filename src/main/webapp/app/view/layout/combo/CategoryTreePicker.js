Ext.define('plat.view.layout.combo.CategoryTreePicker',{
	extend:'Ext.ux.TreePicker',
	xtype:'catetreepicker',

//	store:Ext.create('Ext.data.TreeStore',{
//		model:'plat.model.system.CategoryModel',
//		rootVisible : false,
//		proxy: {
//        	type: 'ajax',
//     		url:'category/findAll'
//    	},
//    	root:{
//     		text:'',
//     		id:'root'
//   		 },
//	    autoSync:true,
//	    folderSort: true,
//	    sorters: [{property: 'id', direction: 'ASC'}]
//	})
	store : 
		new Ext.data.TreeStore({
						fields : ['id', 'text', 'pid', 'leaf', 'idxtype'],
						proxy : {
							type : 'ajax',
							url : 'category/load'
						},
						root : {
							text : '所有类别',
							id : '1',
							expanded : true
						},
						reader : {
							type : 'json',
							root : '',
							messageProperty : "message"
						},
						folderSort : true,
						nodeParam : 'pid',
						sorters : [{
									property : 'id',
									direction : 'ASC'
								}],
						listeners : {
							exception : function(proxy, response, operation) {
								Ext.MessageBox.show({
											title : '服务器错误',
											msg : operation.getError(),
											icon : Ext.MessageBox.ERROR,
											buttons : Ext.Msg.OK
										});
							},
							beforeload : function(store, operation) {
								Ext.apply(store.proxy.extraParams, {
											clazz : 'service'
								});
							}
						}
					})
});