/**
 * 服务管理模块
 * @author wzlee
 */
Ext.define('Plat.module.ServiceManager', {
    extend: 'Ext.ux.desktop.Module',
    
    id: 'service-manager',
    init : function(){
        this.launcher = {
        	text: '服务管理',
        	iconCls: 'tabs'
        };
    },
    createWindow : function(){
    	var me = this;
		var desktop = me.app.getDesktop();
//		var service = desktop.getApplication('service');
        var win = desktop.getWindow('service-window');
        if(!win){
			win = desktop.createWindow({
                id: 'service-window',
                title:'服务管理',
                width:100,
                height:600,
                iconCls: 'tabs',
                layout:'fit',
                items:[
	                	new Ext.application({
					    	name: 'service',
					    	appFolder:'app/module/service',
					    	controllers: ['ServiceController'],
					    	launch: function() {
					    		Ext.create('service.view.ServicePanel',{
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
	            	]
            });
        }
     	return win;
    }
});