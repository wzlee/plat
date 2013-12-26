Ext.define('plat.view.enteruser.ApprUserDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'appruserdetailwindow',
	
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
            	id:'appruserdetailform',
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
					    	{fieldLabel: '申请实名', columnWidth:0.5, name:'name', renderer : function(val){
				        			var record = this.up('form').getRecord();
				        			if(record && record.raw.enterprise.isApproved) val = record.raw.enterprise.name;
					    			return val;
					    		} 
					    	},
					    	{fieldLabel: '组织机构号', columnWidth:0.5, name:'icRegNumber', renderer : function(val){
				        			var record = this.up('form').getRecord();
				        			if(record && record.raw.enterprise.isApproved) val = record.raw.enterprise.licenceDuplicate;
					    			return val;
					    		}
					    	},
				        	{fieldLabel: '营业执照附件',columnWidth:0.5,name:'licenceDuplicate', renderer : function(val){
				        			var record = this.up('form').getRecord();
				        			if(record && record.raw.enterprise.isApproved) val = record.raw.enterprise.licenceDuplicate;
					    			if(val == '') return '暂无';
					    			if(val.indexOf('http') > -1){
					    				return "<a target='_blank' href='" + val + "'>点击查看</a>";
					    			}else {
					    				return "<a target='_blank' href='upload/" + val + "'>点击查看</a>";
					    			}					    			
					    		}
					    	},
					    	{fieldLabel: '企业公函',columnWidth:0.5,name:'businessLetter', renderer : function(val){
					    			var record = this.up('form').getRecord();
				        			if(record && record.raw.enterprise.isApproved) val = record.raw.enterprise.businessLetter;
					    			if(val == '') return '暂无';
					    			if(val.indexOf('http') > -1){
					    				return "<a target='_blank' href='" + val + "'>点击查看</a>";
					    			}else {
					    				return "<a target='_blank' href='upload/" + val + "'>点击查看</a>";
					    			}					    			
					    		} 
					    	},
					    	{fieldLabel: '申请时间',columnWidth:0.5,name:'applyTime' }
				        ]
			    	},{
			    		xtype:'fieldset',
				        title: '企业信息',
				        defaultType: 'displayfield',
				        layout: {
					        type: 'column'
					    },
				        items : [
					    	{fieldLabel: '用户类型',columnWidth:0.5,name:'enterprise.type',renderer : function(val){
					    			if(val == 0) return '暂无';
					    			return PlatMap.Enterprises.type[val];
					    		}
					    	},
					    	{fieldLabel: '注册时间',columnWidth:0.5,name:'user.regTime' },
					    	{fieldLabel: '邮箱地址',columnWidth:0.5,name:'enterprise.email' },
					    	{fieldLabel: '所属行业',columnWidth:0.5,name:'enterprise.industryType', renderer : function(val){
					    			if(val == 0) return '暂无';
					    			return PlatMap.Enterprises.industryType[val];
					    		}
					    	},
					    	{fieldLabel: '关注领域',columnWidth:0.5,name:'user.attentionField' },
					    	{fieldLabel: '主要产品或业务',columnWidth:0.5,name:'enterprise.mainRemark' },
					    	{fieldLabel: '单位性质',columnWidth:0.5,name:'enterprise.enterpriseProperty' },
					    	{fieldLabel: '法人姓名',columnWidth:0.5,name:'enterprise.legalPerson' },
					    	{fieldLabel: '联系人',columnWidth:0.5,name:'enterprise.linkman' },
					    	{fieldLabel: '联系电话',columnWidth:0.5,name:'enterprise.tel' },
					    	{fieldLabel: '企业地址',columnWidth:0.5,name:'enterprise.address' }
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