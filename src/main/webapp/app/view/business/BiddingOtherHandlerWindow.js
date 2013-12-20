Ext.define('plat.view.business.BiddingOtherHandlerWindow', {
    extend: 'Ext.window.Window',
    xtype: 'biddingotherhandlerwindow',
    id:'biddingotherhandlerwindow',
    width: 800,    
    height: 530,
    buttonAlign:'center',
    layout: {
    	type:'border'
    },
    modal:true,
    closeAction : 'hide',
    defaults:{
    	split:true
    },
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
            	{
            		xtype:'form',
            		region:'west',
            		id:'biddingotherhandlerform',
//            		title:'订单信息',
            		layout: 'column',   
            		width:800,
            		height:420,
    				defaults:{
						labelWidth:60,
					 	labelAlign:'right',
					 	msgTarget: 'side',
					    margin:'2'
					},
            		items:[
            					{
            						xtype:'hiddenfield',
            						name:'id'
            					},
            					{
				                    xtype: 'displayfield',
				                    width: 250,
				                    name:'bidNo',
				                    submitValue: true,
				                    fieldLabel: '招标单号'
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 250,				                    			                    
				                    fieldLabel: '服务类别',		
				                    name:'category.text'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'name',
				                    width: 250,
				                    fieldLabel: '服务名称'
				                },
				              
				                {
				                    xtype: 'displayfield',
				                    name:'attachment',
				                    width: 250,
				                    fieldLabel: '附件'
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
				                	width:158,
				                	renderer:function(v){
				                		return '-'+v;
				                	}
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'linkMan',
				                    width: 250,
				                    fieldLabel: '联系人' 
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 250,
				                    name:'tel',
				                    fieldLabel: '联系电话'				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'email',
				                    width: 250,
				                    fieldLabel: '邮箱'
				                    
				                },
				                {				                		
					            	xtype: 'displayfield',
					            	width: 250,
					            	name:'user.userName',
					              	fieldLabel: '创建人'
					           	},
				                {
				                	xtype: 'displayfield',
				                    name:'createTime',
				                    width: 250,
				                    fieldLabel: '创建时间'
				                },
				                {
				                    xtype: 'htmleditor',
				                    height: 125,
//				                    disabled:true,
				                    width:750,
				                    name:'description',				                    
				                    fieldLabel: '描述'
				                }
            		]
            	},
//            	{
//            		xtype:'panel',
//            		region:'center',
//            		items:[
//	            		{
//		            		xtype:'responsebiddinggrid',
//		            		region:'north',
//		            		height:150,
//		            		width:400
//		            	},
//		            	{
//		            		xtype:'biddingservicedetailgrid',
//		            		region:'south',
//		            		width:500
//		            	}
//            		]
//            	}
            	{
		            		xtype:'responsebiddinggrid',
		            		region:'south',
		            		height:70
		            	
		            	},
		            	{
		            		xtype:'biddingservicedetailgrid',
		            		region:'south',
		      				height:100
		            	}
            ],
            buttons:[
            	{
            		text:'取消招标',
            		action:'cancel'
            	},
				{
					text:'关闭窗口',
					action:'return'
				}
			]
        });
        me.callParent(arguments);
    }
});