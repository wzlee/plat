Ext.define('plat.model.business.BiddingServiceModel',{
 	extend: 'Ext.data.Model',
    fields: [
    			'id',
    			'bidNo',
    			'createTime',
    			'name',
    			'minPrice',
    			'maxPrice',
    			'attachment',
    			'description',
    			'linkMan',
    			'tel',
    			'email',
    			'status',
    			{name:'category.id',type:'int'},
    			{name:'category.text',type:'String'},
    			//招标申请人
    			{name:'user.id',type:'int'},
    			{name:'user.userName',type:'String'}
    			//子账号招标申请人
//    			{name:'staff.id',type:'int'},
//    			{name:'staff.userName',type:'String'}
   			
    ]
	
});