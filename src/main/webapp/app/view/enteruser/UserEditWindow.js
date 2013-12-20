Ext.define('plat.view.enteruser.UserEditWindow', {
    extend: 'Ext.window.Window',
    xtype: 'usereditwindow',
    //height: 445,
    width: 578,  
    buttonAlign:'center',
    layout: 'fit',
    modal:true,
    closeAction : 'hide',
    title: '编辑会员信息',

    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    bodyPadding: 10,
                    layout:'column',                    
                    defaults:{
                    	xtype: 'textfield',
						labelWidth:61,
						labelAlign:'right',
						width:250,
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
				        	xtype: 'hiddenfield',
				        	name:'isApproved'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'sourceId'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'remark'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.id'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.enterpriseCode'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.type'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.type'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.licenceDuplicate'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.businessLetter'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.attachment'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.isApproved'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.icRegNumber'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'enterprise.profile'
				        },
                        {   
                        	xtype: 'displayfield', 
                            fieldLabel: '会员名',
                            afterLabelTextTpl: required,
                            submitValue: true,
							name:'userName'
                        },
                        {
                            xtype: 'textfield',                            
                            fieldLabel: '会员密码',
                            labelWidth:100,
                            width:242,
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
                            text: '重置',
                            width:42,
                            action:'resetPass'
                        },
                        {
				        	xtype: 'hiddenfield',
				        	value: 'false',
				        	name:'isreset'
				        },
                        {                                                        
                            fieldLabel: '企业简称',                            
                            allowBlank:false,                            
				            afterLabelTextTpl: required,
				            minLength : 2, //允许输入的最少字符数
							minLengthText : "企业简称最小长度不能少于2个字符！",//提示文本
				            maxLength : 20,//允许输入的最大字符数
							maxLengthText : "企业简称最大长度不能超过20个字符！",//提示文本
							regex:/^\S+$/,
							regexText:'企业简称不能有空格',
                            name:'enterprise.forShort',
                            emptyText:'请输入企业简称...'                          
                        },
                        {
                                                        
                            fieldLabel: '企业全称',
                            labelWidth:100,
                            width:289,
				            minLength : 2, //允许输入的最少字符数
							minLengthText : "企业全称最小长度不能少于2个字符！",//提示文本
				            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "企业全称最大长度不能超过100个字符！",//提示文本
							regex:/^\S+$/,
							regexText:'企业全称不能有空格',
                            name:'enterprise.name',
                            emptyText:'请输入企业全称...'
                        },
                        
                        {
				        	xtype: 'propertycombo',
				        	fieldLabel: '单位性质',
				        	editable:false,
				           	name:'enterprise.enterpriseProperty',
				           	emptyText:'请选择单位性质...'
				          	
				         }, 
				         {
				        	xtype: 'businesspatterncombo',
				        	fieldLabel: '经营模式',
				        	labelWidth:100,
				          	width:289,
				           	name:'enterprise.businessPattern',
				          	editable:false,
				           	emptyText:'请选择经营模式...'
				          	
				         },
				         {
				        	xtype:'displayfield',
				         	submitValue: true,
				        	fieldLabel: '所属窗口',
				           	name:'enterprise.industryType',
				           	renderer:function(value){
				           		return PlatMap.Enterprises.industryType[value];
				           	}			          	
				         },
				         {                                                        
                            fieldLabel: '法人姓名',
                            labelWidth:100,
                            width:290,
                            minLength : 2, //允许输入的最少字符数
							minLengthText : "法人姓名最小长度不能少于2个字符！",//提示文本
				            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "法人姓名最大长度不能超过100个字符！",//提示文本
                            name:'enterprise.legalPerson',
                            emptyText:'请输入法人姓名...'
                         },
                         {                                                        
                           	xtype: 'datefield',                           	
				         	fieldLabel: '成立日期',
				            editable:false,
				            format:'Y-m-d',
				            value: '2000-01-01',
                            name:'enterprise.openedTime'
                         },
                         {                         	
                            fieldLabel: '年营业额(万)',
                            labelWidth:100,
                           	width:289,
                            name:'enterprise.salesRevenue',
                            emptyText:'请输入年营业额...',
                            regex:/^\d*(.\d{1,4})?$/,
							regexText:'年营业额小数位最多4位',
							allowBlank:false,
							value:0
                         },
                         {
                         	xtype: 'numberfield',                         	
                         	hideTrigger: true,
                            fieldLabel: '员工人数',
                            minValue: 0,
                            name:'enterprise.staffNumber',
                            emptyText:'请输入员工人数...'
                         },
                         
                         {                                                        
                            fieldLabel: '联系人', 
                            labelWidth:100,
                           	width:289, 
                            name:'enterprise.linkman',
                            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "联系人最大长度不能超过100个字符！",//提示文本
                            emptyText:'请输入联系人...'
                         },
                         {                                                        
                            fieldLabel: '联系电话',
                            name:'enterprise.tel',                            
                            emptyText:'请输入联系电话...',
                            regex:/^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
							regexText:'联系电话输入不正确'	
                         },
                         {                                                        
                            fieldLabel: '电子邮箱',
                            labelWidth:100,
                            width:290,
                            name:'enterprise.email',
                            vtype:'email',
                            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "联系人最大长度不能超过100个字符！",//提示文本
                            emptyText:'请输入电子邮箱...'
                         },
                         {
				          	xtype: 'container',							
							layout: 'hbox',
							items : [{
								xtype:'textfield',
						        fieldLabel: '企业logo',
						        emptyText: '选择一张图片...',
						       	name: 'enterprise.photo',
						    	readOnly : true,
						    	labelWidth:61,
						       	labelAlign:'right',						      	
						      	flex : 1
							 }, {
							    xtype : 'button',
							    name : 'select_logo',
							    icon : 'jsLib/extjs/resources/themes/icons/add1.png'
							 }]
				         },                         
                         {                                                        
                            fieldLabel: '联系地址',  
                            width:544,
                            name:'enterprise.address',
                            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "企业地址最大长度不能超过100个字符！",//提示文本
                            emptyText:'请输入企业地址...'
                         },                                                 
				         {                                                        
                           	xtype: 'textareafield',
				         	fieldLabel: '主要产品',  
				         	width:544,
//				         	maxLength : 100,//允许输入的最大字符数
//							maxLengthText : "最大长度不能超过100个字符！",//提示文本
                            name:'enterprise.mainRemark'
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

        me.callParent(arguments);
    }

});