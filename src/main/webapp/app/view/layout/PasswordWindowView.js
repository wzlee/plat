Ext.define('plat.view.layout.PasswordWindowView',{
	extend:'Ext.window.Window',
	alias : 'widget.passwordwindow',
	
	title:'修改密码',
	id:'passwordwindow',
	layout:'fit',
	frame:true,
	closable:false,
	initComponent:function(){
		var me = this;
		this.items = [
						{
							xtype:'form',
							id:'passwordform',
							width:300,
							height:130,
							buttonAlign:'center',
							bodyPadding:'10px',
							baseCls:"x-plain",
							waitMsgTarget: true,
							defaults:{
								labelWidth: 80,
								width:250,
								minLength:6,
			                	maxLength:15,
								labelAlign:'right',
								msgTarget: 'side'
							},
							items:[
									{
					                    xtype:'textfield',
					                    inputType:'password',
					                	fieldLabel: '原始密码',
					                	allowBlank:false,
					                    name: 'opassword',
					                    submitValue : false,
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
					                    submitValue : false,
					                    validator:function(value){
					                    	return me.comparePassword(value);
					                    },
					                    validateOnBlur:true,
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
					                    submitValue : false,
					                    validator:function(value){
					                    	return me.comfirmPassword(value);
					                    },
					                    validateOnBlur:true,
							            listeners  : {  
							                'focus':function(){  
							                    this.selectText();  
							                }
							            }  
					                }
								],
							buttons:[
										{
				    						text:'提&nbsp;&nbsp;交',
				    						iconCls:'icon-login',
				    						handler:function(){
												me.submitPassword();				    						
				    						}
				    						
				    					},
				    					{
				    						text:'取&nbsp;&nbsp;消',
				    						iconCls:'icon-clear',
				    						handler:function(){
				    							me.cancelChange();
				    						}
				    					}
			    					]
						}
					];
		this.callParent(arguments);
	},
	comparePassword:function(value){
		if(value == this.getComponent('passwordform').down('textfield[name=opassword]').getValue()){
			return '新与原始密码不能相同！';
		}else{
			return true;
		}
	},
	comfirmPassword:function(value){
		if(value != this.getComponent('passwordform').down('textfield[name=npassword]').getValue()){
			return '新密码两次输入不一致！';
		}else{
			return true;
		}
	},
	submitPassword:function(){
		var passwordForm = this.getComponent('passwordform');
		var handleMask = new Ext.LoadMask(passwordForm.getEl(),{msg:'系统处理中,请稍候...'});
		if(passwordForm.form.isValid()){
			handleMask.show();
			passwordForm.form.doAction('submit',{
				url:'manage/modifyCurrentPwd',
				params : {
					newpwd : $.md5(passwordForm.form.findField('npassword').getValue()),
					oldpwd : $.md5(passwordForm.form.findField('opassword').getValue())
				},
				type:'POST',
				success:function(form,action){
					handleMask.hide();
					passwordForm.form.reset();
					passwordForm.ownerCt.hide();
					Ext.example.msg('',action.result.message);
				},
				failure:function(form,action){
					handleMask.hide();
					Ext.example.msg('',action.result.message);
				}
			});
		}
	},
	cancelChange:function(){
		this.hide();
	}
});