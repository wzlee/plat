Ext.define('plat.view.business.BiddingServiceAuditWindow', {
    extend: 'Ext.window.Window',
	xtype:'biddingserviceauditwindow',
    width: 600,
    height: 400,
	layout:'fit',
	modal:true,
	buttonAlign:'center',
	closeAction : 'hide',
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'biddingserviceauditform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:60,
					        labelAlign:'right',
					        margin:'2',
//						    disabled:true,
						    editable:false,
					        emptyText:'--暂无数据--'
					    },
					    bodyPadding:'10',
					    items:[
					    	 	{
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'bidNo',
				                    submitValue: true,
				                    fieldLabel: '招标单号'
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,				                    			                    
				                    fieldLabel: '服务类别',		
				                    name:'category.text'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'name',
				                    width: 259,
				                    fieldLabel: '服务名称'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'minPrice',
				                    width: 92,
				                    fieldLabel: '招标价格'
				                },
				                {
				                	xtype:'displayfield',
				                	name:'maxPrice',
				                	width:167,
				                	renderer:function(v){
				                		return '-'+v;
				                	}
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'attachment',
				                    width: 259,
				                    fieldLabel: '附件'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'linkMan',
				                    width: 259,
				                    fieldLabel: '联系人' 
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'tel',
				                    fieldLabel: '联系电话'				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'email',
				                    width: 259,
				                    fieldLabel: '邮箱'
				                    
				                },
				                {				                		
					            	xtype: 'displayfield',
					            	width: 259,
					            	name:'user.userName',
					              	fieldLabel: '创建人'
					           	},
				                {
				                	xtype: 'displayfield',
				                    name:'createTime',
				                    width: 259,
				                    fieldLabel: '创建时间'
				                },
				                {
				                    xtype: 'htmleditor',
				                   	height: 150,
//				                    disabled:true,
				                    width:550,
				                    name:'description',				                    
				                    fieldLabel: '描述'
				                }
					    	]
		            	}
            		],
           buttons:[
           			{
           				text:'通过',
           				action:'submit'
           			},
					{
						text:'驳回',
						action:'esc'
					}
			]
            		
        });

        me.callParent(arguments);
    }

});