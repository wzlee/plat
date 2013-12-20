Ext.onReady(function(){
	new Ext.application({
		name: 'service',
		appFolder:'app/module/service',
		controllers: ['ServiceController'],
		launch: function() {
			Ext.create('Ext.container.Viewport',{
				layout:'border',
				items :[
		    		{
		        		xtype: 'categorygrid',
		        		region:'west',
		        		width:200,
		        		margins:2,
		        		collapsible:true
		        	},
		        	{
		        		xtype: 'servicegrid',
		        		margins:2,
		        		region:'center'
		        	}
		    	]
			});
		},
	    initController:function(index, controller, token){
	    	controller.init();
	    	console.log(token+'初始化完毕...');
	  	}
	})
})