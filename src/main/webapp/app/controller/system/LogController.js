Ext.define('plat.controller.system.LogController', {
	extend: 'Ext.app.Controller',
	alias:'widget.log',
	
	stores:['system.LogStore'],
    views: [
    		'system.LogGridView',
    		'layout.combo.LevelCombo'
    		],
    refs: [
	        {
	            ref: 'logstore',
	            selector: 'logstore'
	        },
	        {
	            ref: 'loggrid',
	            selector: 'loggrid'
	        }
    ],
    init:function(){
    	this.control({
    		'loggrid': {
                afterrender:function(gridpanel){
                	console.log(gridpanel.title + '渲染完毕...');
                	Ext.getBody().unmask();
        			gridpanel.getStore().on('beforeload',function(store,options){
//                		Ext.apply(store.proxy.extraParams, gridpanel.down('textfield,datefield,combo').getSubmitData());
        				Ext.apply(store.proxy.extraParams, {
    														keywords:gridpanel.down('textfield[name=keywords]').getValue(),
    														begindate:Ext.Date.format(gridpanel.down('datefield[name=begindate]').getValue(),'Y-m-d H:i:s,u'),
    														enddate:Ext.Date.format(gridpanel.down('datefield[name=enddate]').getValue(),'Y-m-d H:i:s,u'),
    														level:gridpanel.down('combo[name=level]').getValue()
    														});
                	},this);
                	gridpanel.down('button[action=search]').on('click',function(){
                		gridpanel.getStore().load();
					},this);
                },
                beforehide:function(gridpanel){
            		console.log(gridpanel.title + "将会隐藏!");
            	},
            	destroy:function(grid){
            		console.log(grid.title + "已被销毁...");
            	}
            }
    	});
    }
});