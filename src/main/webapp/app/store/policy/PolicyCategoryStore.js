Ext.define('plat.store.policy.PolicyCategoryStore', {//2013-11-20日下午15:38分 这个store正式变为专项资金的store
			extend : 'Ext.data.TreeStore',
			model : 'plat.model.policy.PolicyCategoryModel',
			storeId : 'policycategorystore',
			init : function() {
				console.log('PolicyCategoryStore was initialized!!!');
			},
			autoLoad : false,
			// pageSize : 1000,
			// autoSync : true,
			proxy : {
				type : 'ajax',
				url : 'policy/queryCategory',
				actionMethods : {
					read : 'POST'
				},
				extraParams: {
           		 	type : 1
      			},
				reader : {
					type : 'json',
					root : '',
					idProperty : 'id'
				},
				writer : {
					type : "json",
					encode : true,
					root : "data"
				},
				api : {
					read : 'policy/queryCategory',
					create : 'policy/addOrUpdateCategory',
					destroy : 'policy/deleteCategory',
					update : 'policy/addOrUpdateCategory'
				}
			}
		});
