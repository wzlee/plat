Ext.define('plat.model.business.OrderInfoModel',{
 	extend: 'Ext.data.Model',
    fields: [
    			'id',
    			'orderStatus',
    			'remark',
    			'processTime',
    			'action',
    			'processor',
    			'manager',
    			'staff',
    			//处理人
    			'staff.userName',
    			{name:'processor.userName',type:'String'},
    			{name:'manager.username',type:'String'}
    			
    ]
	
});