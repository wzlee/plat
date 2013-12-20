/**
 * 链接记录控制层
 */
Ext.define('plat.controller.system.MonitorController', {
    extend: 'Ext.app.Controller',
    
    stores: [],
    views: ['system.DruidPanel'],
    refs: [
			{
	            ref: 'druidpanel',
	            selector: 'druidpanel'
	        }
    ],
    init: function() {
        this.control({
        	
        });
    }
});