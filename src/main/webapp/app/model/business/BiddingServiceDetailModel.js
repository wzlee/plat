Ext.define('plat.model.business.BiddingServiceDetailModel',{
 	extend: 'Ext.data.Model',
    fields: [
    			'id',
    			'biddingStatus',
    			'processTime',
    			'action',
    			'remark',
    			//操作人
    			'manager',
    			'user',
    			'staff',
    			{name:'manager.id',type:'int'},
    			{name:'manager.username',type:'String'},
    			{name:'user.id',type:'int'},
    			{name:'user.userName',type:'String'},
    			'staff.userName'
    ]
	
});