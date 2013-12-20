Ext.define('plat.view.enteruser.PersonalUserAddWindow', {
    extend: 'Ext.window.Window',
	xtype:'personaluseraddwindow',
    height: 130,
    width: 300,
    buttonAlign:'center',
    layout: 'fit',        
    closeAction : 'hide',
    modal:true,
    title: '添加个人用户',
    
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    bodyPadding: 10,
                    layout: {
                        type: 'column'                        
                    },
                    defaults:{
                    	xtype: 'textfield',
						labelWidth:61,
						labelAlign:'right',
						msgTarget: 'side',
						margin:'2'
					},
                    items: [
                    	{
				        	xtype: 'hiddenfield',
				        	name:'password',				        	
				        	value:123456
				        },
				       
				        {
				        	xtype: 'hiddenfield',
				        	name:'userStatus',
				        	value:'1'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'sourceId',
				        	value:'1'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'sex',
				        	value:'1'
				        },
				        {
				        	xtype:'hiddenfield',
				        	name:'certSign',
				        	value:'0'
				        
				        },
                        {                                                        
                            fieldLabel: '会员名',
                            allowBlank:false,
				            afterLabelTextTpl: required,
				            minLength : 2, //允许输入的最少字符数
							minLengthText : "最小长度不能少于2个字符",//提示文本
				            maxLength : 20,//允许输入的最大字符数
							maxLengthText : "最大长度不能超过 20 个字符",//提示文本
							regex:/^[a-zA-Z][a-zA-Z0-9_]{0,}$/,
							regexText:'会员名不匹配(字母开头)',
							name:'userName'
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
					action:'cancel'
				}
			]
        });		
        me.callParent(arguments);
    }

});