Ext.define('plat.model.business.ResponseBiddingModel',{
 	extend: 'Ext.data.Model',
    fields: [
    			'id',
    			'bidPrice',
    			'responseTime',
    			'description',
    			'linkMan',
    			'tel',
    			'email',
    			'attachment',
    			'status',
    			'email',
    			'status',
    			//应标人
    			'user',
    			{name:'user.id',type:'int'},
    			{name:'user.userName',type:'String'},
    			{name:'user.enterprise.name',type:'String'},
    			//子账号
    			'staff',
    			{name:'staff.userName',type:'String'},
    			{name:'staff.parent.enterprise.name',type:'String'}
    ]
	
});