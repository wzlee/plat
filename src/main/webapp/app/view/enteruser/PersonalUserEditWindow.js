Ext.define('plat.view.enteruser.PersonalUserEditWindow', {
    extend: 'Ext.window.Window',
    xtype: 'personalusereditwindow',
    height: 300,
    width: 360,    
    buttonAlign:'center',
    layout: 'fit',
    modal:true,
    closeAction : 'hide',
    title: '编辑用户信息',

    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    height: 214,                    
                    bodyPadding: 10,
                    layout: 'column',                    
    				defaults:{
					        labelWidth:61,
					        labelAlign:'right',
					        msgTarget: 'side',
					        margin:'2'
					    },
                    items: [                       
                    	{
				        	xtype: 'hiddenfield',
				        	name:'id'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'sex'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'checkcode'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'userStatus'
				        },				        
				        {
				        	xtype: 'hiddenfield',
				        	name:'regTime'
				        },				        
				        {
				        	xtype: 'hiddenfield',
				        	name:'isPersonal'
				        },	
				        {
				        	xtype:'hiddenfield',
				        	name:'certSign'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'isApproved'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'sourceId'
				        },		        
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '用户名',                            
                            width: 350,
                            submitValue: true,
                            name:'userName'
                            
                        },
                        {
                            xtype: 'textfield',                            
                            fieldLabel: '密码',
                            inputType:'password',
                            readOnly:true,
                            listeners: {
						        'focus' : function(password){
								　　　　password.blur();
								　　}
						    },
                            name:'password'
                        },
                        {
                            xtype: 'button',
                            text: '重置密码',
                            action:'resetPass'
                        },
                        {
				        	xtype: 'hiddenfield',
				        	value: 'false',
				        	name:'isreset'
				        },
                        {
				        	xtype: 'textfield',				        					        	
				        	fieldLabel: '联系电话',
				        	vtype:'mobile',
				        	name:'mobile'
				        }, 
				        {
				        	xtype: 'textfield',				        					        	
				        	fieldLabel: '联系地址',
				        	name:'address'
				        }, 
                        {
				        	xtype: 'textarea',				        	
				        	width: 320,
				        	fieldLabel: '备注',
				        	name:'remark'
				        }
                    
                
                    ]
                }
            ],
            buttons:[
				{
					text:'提交',
					action:'submit'
				},
				{
					text:'取消',
					action:'reset'
				}
			]
        });
		var mobileReg = /^[1][0-9]{10}$/;
		var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
       	Ext.apply(Ext.form.VTypes, {
	    	mobile:function(value,field){
	    		if(mobileReg.test(value) || telReg.test(value)){
	    			return true;
	    		}
	    		return false;
	    	},
	    	mobileText:'联系电话为固话或手机号码'
	    	
  		});
        me.callParent(arguments);
    }

});