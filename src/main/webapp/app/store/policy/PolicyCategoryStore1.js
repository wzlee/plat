Ext.define('plat.store.policy.PolicyCategoryStore1', {
			extend : 'Ext.data.TreeStore',
			model : 'plat.model.policy.PolicyCategoryModel',
			storeId : 'policycategorystore1',
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
           		 type: 0
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
