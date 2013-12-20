Ext.define('plat.model.business.EvaluationModel',{
 	extend: 'Ext.data.Model',
    fields: [
    			'id',
    			'dtime',
    			'remark',
    			'satisfaction',
				'user',
				'staff',
    			//评价人
    			{name:'user.enterprise.name',type:'String'},
    			{name:'user.userName',type:'String'},
    			{name:'staff.userName',type:'String'}
    			
    ]
	
});