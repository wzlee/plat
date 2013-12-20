Ext.define('plat.view.enteruser.PersonalUserDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'personaluserdetailwindow',
	
    width: 550,
	layout:'fit',
	closeAction : 'hide',
	buttonAlign:'center',
	modal : true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [{	
            	xtype:'form',
            	id:'personaluserdetailform',
			    defaultType: 'displayfield',
			    bodyPadding:'10',
			    items:[
			    	{xtype: 'hiddenfield', name:'id' },
			    	{xtype: 'hiddenfield',name:'approveStatus' },
			    	{
			    		xtype:'fieldset',
				        title: '基本信息',
				        defaults:{
							labelWidth:90,
							labelAlign:'right'
						},
				        layout: {
					        type: 'column'
					    },
				        defaultType: 'displayfield',
				        items : [
					    	{fieldLabel: '会员名', columnWidth:0.5, name:'userName' },
					    	{fieldLabel: '昵称', columnWidth:0.5,name :'nickName',
					    		renderer:function(val){
					    			if(val == null || val == ''){return '暂无'};
					    			return val;
					    		}
					    	},
					    	{fieldLabel: '用户类型', columnWidth:0.5, name:'certSign',
					    		renderer:function(val){
					    			if(val == 0) return '未认证用户';
					    			if(val == 1) return '认证用户';
					    		}
					    	},
					    	{fieldLabel: '用户状态', columnWidth:0.5, name:'userStatus',
					    		renderer:function(val){
					    			return PlatMap.Enterprises.userStatus[val];
					    		}
					    	},
					    	{fieldLabel: '注册时间',columnWidth:0.5, name:'regTime' },
					    	{fieldLabel: '头像',columnWidth:0.5, name:'headPortraint',
					    		renderer:function(val){
					    			if(val == null || val == ''){
					    				return '暂无';
					    			}else if(val == 'default_logo.jpg'){
					    				return "<a href='resources/images/ucenter/default_logo.jpg' class='fancybox'>点击查看</a>";
					    			}else{
					    				if(val.indexOf('http') > -1){
					    					return "<a href='" + val + "' class='fancybox'>点击查看</a>";
					    				}else {
					    					return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
					    				}					    				
					    			}
					    		}
					    	}
				        ]
			    	},{
			    		xtype:'fieldset',
				        title: '个人信息',
				        defaultType: 'displayfield',
				        defaults:{
							labelWidth:90,
							labelAlign:'right'
						},
				        layout: {
					        type: 'column'
					    },
				        items : [
					    	{fieldLabel: '真实姓名',columnWidth:0.5,name:'realName',renderer : function(val){
					    			if(val == ''){return '暂无';}
					    			return val;
					    		}
					    	},
					    	{fieldLabel: '性别', columnWidth:0.5, name:'sex',
					    		renderer:function(val){
					    			if(val == 0) return '男';
					    			if(val == 1) return '女';
					    			return '暂无';
					    		}
					    	},
					    	{fieldLabel: '个人近照',columnWidth:0.5,name:'personalPhoto', renderer : function(val){
					    			if(val == null || val == ''){
					    				return '暂无';
					    			}
					    			if(val.indexOf('http') > -1){
					    				return "<a href='" + val + "' class='fancybox'>点击查看</a>";
					    			}else {
					    				return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
					    			}					    			
					    		}
					    	},
					    	{fieldLabel: '身份证附件照',columnWidth:0.5,name:'idCardPhoto', renderer : function(val){
					    			if(val == null || val == ''){ 
					    				return '暂无';
					    			}
					    			if(val.indexOf('http') > -1){
					    				return "<a href='" + val + "' class='fancybox'>点击查看</a>";
					    			}else {
					    				return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
					    			}					    			
					    		} 
					    	},
					    	{fieldLabel: '邮箱地址',columnWidth:0.5,name:'email',
					    		renderer:function(val){
					    			if(val == null || val == ''){return '暂无'};
					    			return val;
					    		}
					    	},
					    	{fieldLabel:'联系电话',columnWidth:0.5,name:'mobile',
					    		renderer:function(val){
					    			if(val == '') {return '暂无'};
					    			return val;
					    		}
					    	},
//					    	{fieldLabel: '是否为领域专家',columnWidth:0.5,name:'user.isDomainExpert',
//					    		renderer : function(val){
//					    			if(val)	return '是';
//					    			return '否';
//					    		}
//					    	},
					    	{fieldLabel: '联系地址',columnWidth:1,name:'address',
					    		renderer:function(val){
					    			if(val == '') {return '暂无'};
					    			return val;
					    		}
					    	}
				        ]
			    	}]
		    }]
			
        });
        me.callParent(arguments);
    }
});