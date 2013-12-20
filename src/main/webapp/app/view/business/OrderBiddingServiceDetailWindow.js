Ext.define('plat.view.business.OrderBiddingServiceDetailWindow', {
    extend: 'Ext.window.Window',
    xtype: 'orderbiddingservicedetailwindow',
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
            		id:'orderbiddingservicedetailform',
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
            						name:'biddingService.id'
            					},
            					{
				                    xtype: 'displayfield',
				                    width: 250,
				                    name:'biddingService.bidNo',
				                    submitValue: true,
				                    fieldLabel: '招标单号'
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 250,				                    			                    
				                    fieldLabel: '服务类别',		
				                    name:'biddingService.category.text'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'biddingService.name',
				                    width: 250,
				                    fieldLabel: '服务名称'
				                },
				                {
				                    xtype: 'displayfield',
//				                    name:'biddingService.minPrice',
				                    name:'biddingServicePrice',
				                    width: 250,
				                    fieldLabel: '招标价格'
				                },
//				                {
//				                	xtype:'displayfield',
//				                	name:'biddingService.maxPrice',
//				                	width:158,
//				                	renderer:function(v){
//				                		return '-'+v;
//				                	}
//				                },
				                {
				                    xtype: 'displayfield',
				                    name:'biddingService.linkMan',
				                    width: 250,
				                    fieldLabel: '联系人' 
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 250,
				                    name:'biddingService.tel',
				                    fieldLabel: '联系电话'				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'biddingService.email',
				                    width: 250,
				                    fieldLabel: '邮箱'
				                    
				                },
				                {				                		
					            	xtype: 'displayfield',
					            	width: 250,
					            	name:'biddingService.user.userName',
					              	fieldLabel: '创建人'
					           	},
				                {
				                	xtype: 'displayfield',
				                    name:'biddingService.createTime',
				                    width: 250,
				                    fieldLabel: '创建时间'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'biddingService.attachment',
				                    width: 400,
				                    fieldLabel: '附件'
				                },
				                {
				                    xtype: 'htmleditor',
				                    height: 125,
//				                    disabled:true,
				                    width:750,
				                    name:'biddingService.description',
				                    fieldLabel: '描述'
				                }
            		]
            	},
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
            ]
        
        });
        me.callParent(arguments);
    }
});