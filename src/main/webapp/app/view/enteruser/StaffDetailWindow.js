Ext.define('plat.view.enteruser.StaffDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'staffdetailwindow',
	modal:true,
    width: 560,
    height: 380,
	layout:'fit',
	buttonAlign:'center',
	closeAction : 'hide',
	modal:true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'staffdetailform',
		            
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					    	xtype:'displayfield',
					        labelWidth:60,
					        labelAlign:'left',
					        margin:'2'
//					        fixed:true,
//					        invalidText:'--暂无数据--'
					    },
					    bodyPadding:'10',
					    items:[
					    	 	{
				                    width: 259,
				                    name:'userName',
				                    submitValue: true,
				                    fieldLabel: '会员名'
				                    
				                },
				                {
				                    width: 259,				                    			                    
				                    fieldLabel: '性别',	
				                    name:'sex',
				                    renderer:function(v){
				                    	return v == 0 ? '男':'女'
				                    }
				                },
				                {
				                    width: 259,
				                    name:'staffStatus',				                    
				                    fieldLabel: '账号状态',
				                    renderer:function(v){
				                    	return PlatMap.Staff.status[v];
				                    }
				                },
				                {
				                	name:'parent.userName',
				                	width: 259,
				                    fieldLabel: '主账号'
				                },
				                {
				                    name:'assignTime',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '分配时间'
				                    
				                },
				                {
				                	xtype:'hiddenfield',
				                	name:'manager.id'
				                },
				                {
				                    fieldLabel: '分配人',
				                    width: 259,
				                    name:'fpname'
				                },
				                {
				                    name:'mobile',
				                    width: 259,
				                    fieldLabel: '手机号码'
				                },
				                {
				                    width: 550,
				                    name:'staffRole.rolename',
				                    fieldLabel: '角色权限'
				                },
//				                {
//				                    name:'staffName',
//				                    width: 259,
//				                    fieldLabel: '员工姓名'
//				                },
//				                {
//				                    xtype: 'displayfield',
//				                    name:'tel',
//				                    width: 259,
//				                    submitValue: true,
//				                    fieldLabel: '办公电话'
//				                },         
//				                {
//				                    xtype: 'displayfield',
//				                    width: 259,
//				                    name:'address',
//				                    submitValue: true,
//				                    fieldLabel: '地址'				                    
//				                }, 
				                {
				                    xtype: 'textarea',
				                    readOnly:true,
				                    height: 150,
				                    width:500,
				                    name:'remark',
				                    fieldLabel: '备注'
				                }
					    	]
		            	}
            		]
        });

        me.callParent(arguments);
    }

});