Ext.define('plat.view.enteruser.ApprPersonalUserDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'apprpersonaluserdetailwindow',
	
    width: 600,
	layout:'fit',
	closeAction : 'hide',
	buttonAlign:'center',
	modal : true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [{	
            	xtype:'form',
            	id:'apprpersonaluserdetailform',
			    defaultType: 'displayfield',
			    bodyPadding:'10',
			    items:[
			    	{xtype: 'hiddenfield', name:'id' },
			    	{xtype: 'hiddenfield',name:'approveStatus' },
			    	{
			    		xtype:'fieldset',
				        title: '申请信息',
				        layout: {
					        type: 'column'
					    },
				        defaultType: 'displayfield',
				        items : [
					    	{fieldLabel: '申请编号', columnWidth:0.5, name:'serialId' },
					    	{fieldLabel: '会员名', columnWidth:0.5, name:'user.userName' },
					    	{fieldLabel: '申请实名', columnWidth:0.5, name:'name'},
					    	{fieldLabel: '申请时间',columnWidth:0.5, name:'applyTime' },
				        	{fieldLabel: '个人近照',columnWidth:0.5,name:'personalPhoto', renderer : function(val){
					    			if(val == '') return '暂无';
					    			if(val.indexOf('http') > -1){
					    				return "<a href='" + val + "' class='fancybox'>点击查看</a>";
					    			}else {
					    				return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
					    			}
					    			
					    		}
					    	},
					    	{fieldLabel: '身份证附件照',columnWidth:0.5,name:'idCardPhoto', renderer : function(val){
					    			if(val == '') return '暂无';
					    			if(val.indexOf('http') > -1){
					    				return "<a href='" + val + "' class='fancybox'>点击查看</a>";
					    			}else {
					    				return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
					    			}					    			
					    		} 
					    	}
				        ]
			    	},{
			    		xtype:'fieldset',
				        title: '用户信息',
				        defaultType: 'displayfield',
				        layout: {
					        type: 'column'
					    },
				        items : [
					    	{fieldLabel: '昵称',columnWidth:0.5,name:'user.nickName',renderer : function(val){
					    			if(val == '') return '暂无';
					    			return val;
					    		}
					    	},
					    	{fieldLabel: '注册时间',columnWidth:0.5,name:'user.regTime' },
					    	{fieldLabel: '邮箱地址',columnWidth:0.5,name:'user.email' },
					    	{fieldLabel: '联系电话',columnWidth:0.5,name:'user.mobile', renderer : function(val){
					    			if(val == '') return '暂无';
					    			return val;
					    		}
					    	},
//					    	{fieldLabel: '是否为领域专家',columnWidth:0.5,name:'user.isDomainExpert',
//					    		renderer : function(val){
//					    			if(val)	return '是';
//					    			return '否';
//					    		}
//					    	},
					    	{fieldLabel: '头像',columnWidth:0.5,name:'user.headPortraint',
					    		renderer : function(val){
					    			if(val == '') return '暂无';
					    			if(val.indexOf('http') > -1){
					    				return "<a href='" + val + "' class='fancybox'>点击查看</a>";
					    			}else {
					    				return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
					    			}					    			
					    		}
					    	},
					    	{fieldLabel: '联系地址',columnWidth:1,name:'user.address' }
				        ]
			    	}]
		    }],
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