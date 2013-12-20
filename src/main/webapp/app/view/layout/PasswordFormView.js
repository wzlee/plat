Ext.define('plat.view.layout.PasswordFormView',{
	extend:'Ext.form.Panel',
	alias : 'widget.passwordform',
	
	border:false,
	buttonAlign:'center',
	bodyPadding:'5 10 0',
	baseCls:"x-plain",
	waitMsgTarget: true,
	defaults:{
		labelWidth: 40,
		width:180,
		labelAlign:'right',
		msgTarget: 'side'
	},
	initComponent:function(){
		this.items = [    
						{
		                    xtype:'textfield',
		                    inputType:'password',
		                	fieldLabel: '原始密码',
		                	allowBlank:false,
		                    name: 'opassword',  
				            listeners  : {  
				                'focus':function(){  
				                    this.selectText();  
				                }  
				            }  
		                },
		                {
		                    xtype:'textfield',
		                    inputType:'password',
		                	fieldLabel: '新密码',
		                	allowBlank:false,
		                    name: 'npassword',  
				            listeners  : {  
				                'focus':function(){  
				                    this.selectText();  
				                }  
				            }  
		                },
	                	{
		                    xtype:'textfield',
		                    inputType:'password',
		                	fieldLabel: '确认新密码',
		                	allowBlank:false,
		                    name: 'cnpassword',  
				            listeners  : {  
				                'focus':function(){  
				                    this.selectText();  
				                }  
				            }  
		                }
					];
		this.buttons = [{
    						text:'提&nbsp;&nbsp;叫',
    						iconCls:'icon-login',
    						action:'login'
    					},
    					{
    						text:'重&nbsp;&nbsp;置',
    						iconCls:'icon-clear',
    						action:'reset'
    					}];
		this.callParent(arguments);
	}
});