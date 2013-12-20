Ext.define('plat.view.enteruser.ApprOrgRegisterDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'approrgregisterdetailwindow',
	
    width: 700,
	layout:'fit',
	closeAction : 'hide',
	buttonAlign:'center',
	modal : true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [{	
            	xtype:'form',
            	id:'approrgregisterdetailform',
			    defaultType: 'displayfield',
			    bodyPadding:'10',
			    items:[
			    	{xtype: 'hiddenfield', name:'id' },
			    	{xtype: 'hiddenfield',name:'approveStatus' },
			    	{
			    		xtype:'fieldset',
				        title: '服务机构申请信息',
				        layout: {
					        type: 'column'
					    },
					     defaults:{
					    	xtype:'displayfield',
							labelAlign:'left',
							labelWidth:120
					    },
				        defaultType: 'displayfield',
				        items : [
					    	{fieldLabel: '会员名', columnWidth:0.5, name:'userName' },
//					    	{fieldLabel: '申请时间',columnWidth:0.3,name:'regTime' },
					    	{fieldLabel: '申请机构', columnWidth:0.5, name:'orgName'},
					    	{fieldLabel: '组织机构代码', columnWidth:0.5, name:'icRegNumber'},
					    	{fieldLabel: '成立时间', columnWidth:0.5, name:'openedTime'},
					    	{fieldLabel: '职工人数', columnWidth:0.5, name:'staffNumber'},
					    	{fieldLabel: '所属窗口', columnWidth:0.5, name:'industryType',
					    		renderer:function(v){
					    			return PlatMap.Enterprises.industryType[v];
					    		}
					    	},
					    	{fieldLabel: '行业', columnWidth:0.5, name:'orgIndustry',
					    		renderer:function(v){
					    			return PlatMap.Enterprises.orgIndustry[v];
					    		}
					    	},
					    	{fieldLabel: '机构性质', columnWidth:0.5, name:'orgProperty',
					    		renderer:function(v){
					    			return PlatMap.Enterprises.orgProperty[v];
					    		}
					    	},
					    	{fieldLabel: '经营范围', columnWidth:0.5, name:'business'},
					    	{fieldLabel: '优势服务领域', columnWidth:0.5, name:'advantageServiceAreas'},
					    	{fieldLabel: '总资产(上年、万元)', columnWidth:0.5, name:'totalAssets'},
					    	{fieldLabel: '营业额(上年、万元)', columnWidth:0.5, name:'turnover'},
					    	{fieldLabel: '利润或净收入(上年、万元)', columnWidth:0.5, name:'profit'},
					    	{fieldLabel: '社会荣誉', columnWidth:0.5, name:'honorSociety'},
					    	{fieldLabel: '专业资质', columnWidth:0.5, name:'professionalQualifications'},
					    	{fieldLabel: '法定代表人', columnWidth:0.5, name:'legalRepresentative'},
					    	{fieldLabel: '手机', columnWidth:0.5, name:'legalRepresentativeMobile'},
					    	{fieldLabel: 'Email', columnWidth:0.5, name:'legalRepresentativeEmail'},
					    	{fieldLabel: '总经理姓名', columnWidth:0.5, name:'generalName'},
					    	{fieldLabel: '总经理手机', columnWidth:0.5, name:'generalManagerMobile'},
					    	{fieldLabel: 'Email', columnWidth:0.5, name:'generalManagerEmail'},
					    	{fieldLabel: '联系人姓名', columnWidth:0.5, name:'contactName'},
					    	{fieldLabel: '联系人手机', columnWidth:0.5, name:'contactNameMobile'},
					    	{fieldLabel: 'Email', columnWidth:0.5, name:'contactNameEmail'},
					    	{fieldLabel: '机构地址', columnWidth:0.5, name:'orgAddress'},
					    	{fieldLabel: '机构电话', columnWidth:0.5, name:'orgPhone'},
					    	{fieldLabel: '机构传真', columnWidth:0.5, name:'orgFax'},
					    	{fieldLabel: '公司网址', columnWidth:0.5, name:'orgWebsite'}
//					    	{fieldLabel: '邮箱地址',columnWidth:0.5,name:'email' },
//					    	{fieldLabel: '所属行业',columnWidth:0.5,name:'industryType', renderer : function(val){
//					    			if(val == 0) return '暂无';
//					    			return PlatMap.Enterprises.industryType[val];
//					    		}
//					    	},
//				        	{fieldLabel: '营业执照附件',columnWidth:0.5,name:'licenceDuplicate', renderer : function(val){
//					    			return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
//					    		}
//					    	},
//					    	{fieldLabel: '企业公函',columnWidth:0.5,name:'businessLetter', renderer : function(val){
//					    			return "<a href='upload/" + val + "' class='fancybox'>点击查看</a>";
//					    		} 
//					    	}
				        ]
			    	}]
		    }],
			buttons:[
				{
					text:'通过',
					action:'submit',
					handler : function(eventObj){
						var record = this.up('window').down('form').getRecord();
						Ext.MessageBox.confirm('提示', '您正在通过公司实名为 ['+ record.raw.orgName +'] 的服务机构注册申请，是否继续？', function(btn){
	    					if(btn != 'yes') return;
							Ext.Ajax.request({
								url: 'user/approveOrgRegister',
							    params: {
							        rid : record.raw.id,
							        status : 1
							    },
							    success: function(response){
							    	var result = eval('('+response.responseText+')');
							        Ext.Msg.alert('提示', result.message);
							        if(result.success){
							        	record.store.remove(record);
								        eventObj.up('window').close();
							        }
							    },
							    failure : function(response){
									Ext.Msg.alert('提示', response.responseText)
								}
					    	});
						});
					}
				},
				{
					text:'驳回',
					action:'esc',
					handler : function(eventObj){
						var record = this.up('window').down('form').getRecord();
						Ext.MessageBox.confirm('提示', '您正在驳回公司实名为 ['+ record.raw.orgName +'] 的服务机构注册申请，是否继续？', function(btn){
	    					if(btn != 'yes') return;
							Ext.Ajax.request({
								url: 'user/approveOrgRegister',
							    params: {
							        rid : record.raw.id,
							        status : 0
							    },
							    success: function(response){
							        var result = eval('('+response.responseText+')');
							        Ext.Msg.alert('提示', result.message);
							        if(result.success){
							        	record.store.remove(record);
								        eventObj.up('window').close();
							        }
							    },
							    failure : function(response){
									Ext.Msg.alert('提示', response.result.message)
								}
					    	});
						});
					}
				}
			]
        });
        me.callParent(arguments);
    }
});