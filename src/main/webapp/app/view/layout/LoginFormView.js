Ext.define('plat.view.layout.LoginFormView',{
	extend:'Ext.form.Panel',
	alias : 'widget.loginform',
	border:false,
	buttonAlign:'center',
	bodyPadding:'10 50',
	initComponent:function(){
		this.items = [    
						{
	    					xtype:'image',
	    					alt:'logo',
	    					src:'resources/images/logo_smemall.png',
	    					margin:'5',
	    					fixed:true,
	    					width:180,
	    					height:51
			        	},
						{	
							layout:"anchor",
							waitMsgTarget: true,
							defaults:{
								labelWidth: 40,
								width:180,
								labelAlign:'right',
								msgTarget: 'side'
							},
							items:[
									{
					                    xtype:'textfield',
					                    fieldLabel: '账号',
					                    allowBlank:false,
					                    name: 'username',  
							            listeners  : {  
							                'focus':function(){  
							                    this.selectText();  
							                }  
							            }  
					                },
				                	{
					                    xtype:'textfield',
					                    inputType:'password',
					                	fieldLabel: '密码',
					                	allowBlank:false,
//					                	submitValue:false,
					                    name: 'password',  
							            listeners  : {  
							                'focus':function(){  
							                    this.selectText();  
							                }  
							            }  
					                },
				    				{
				    					xtype: 'checkboxgroup',
								        fieldLabel: '记住',
								        columns: 2,
								        layout: {
										    type: 'hbox',
										    align: 'left'
										},
								        vertical: true,
								        items: [
								            { boxLabel: '账号', name: 'rusername', inputValue: '1',checked: true},
								            { boxLabel: '密码 ', name: 'rpassword', inputValue: '2',listeners:{mouseover:function(){console.log("dsaasdf")}}}
								        ]
				    				}
								]
							}
						];
		this.buttons = [{
    						text:'登&nbsp;&nbsp;录',
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