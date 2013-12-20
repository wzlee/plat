Ext.define('plat.view.enteruser.UserAddWindow', {
    extend: 'Ext.window.Window',
	xtype:'useraddwindow',
    height: 445,
    width: 578,
    buttonAlign:'center',
    layout: 'fit',        
    closeAction : 'hide',
    modal:true,
    title: '添加企业用户',
    
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
                            fieldLabel: '会员名',
                            allowBlank:false,
				            afterLabelTextTpl: required,
				            minLength : 2, //允许输入的最少字符数
							minLengthText : "最小长度不能少于2个字符！",//提示文本
				            maxLength : 20,//允许输入的最大字符数
							maxLengthText : "最大长度不能超过 20 个字符！",//提示文本
							regex:/^[a-zA-Z][a-zA-Z0-9_]{0,}$/,
							regexText:'会员名不匹配(字母开头)',
							name:'userName',
							emptyText:'请输入会员名...'
                        },
                        {                                                        
                            fieldLabel: '企业简称',                            
                            allowBlank:false,
                            labelWidth:100,
                            width:289,
				            afterLabelTextTpl: required,
				            minLength : 2, //允许输入的最少字符数
							minLengthText : "最小长度不能少于2个字符！",//提示文本
				            maxLength : 20,//允许输入的最大字符数
							maxLengthText : "最大长度不能超过 20 个字符！",//提示文本
							regex:/^\S+$/,
							regexText:'企业简称不能有空格',
                            name:'enterprise.forShort',
                            emptyText:'请输入企业简称...'                          
                        },
                        {
                                                        
                            fieldLabel: '企业全称',
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
				        	labelWidth:100,
				        	editable:false,
				          	width:289,
				           	name:'enterprise.enterpriseProperty',
				           	emptyText:'请选择单位性质...'
				          	
				         }, 
				         {
				        	xtype: 'businesspatterncombo',
				        	fieldLabel: '经营模式',				        	
				           	name:'enterprise.businessPattern',
				          	editable:false,
				           	emptyText:'请选择经营模式...'
				          	
				         },
				         {
				        	xtype: 'industrytypecombo',				        	
				        	fieldLabel: '所属窗口',				          	
				           	name:'enterprise.industryType',
				          	editable:false,
				          	labelWidth:100,
                           	width:289,
				           	emptyText:'请选择所属窗口...'	
				         },
				         {                                                        
                            fieldLabel: '法人姓名',
                            minLength : 2, //允许输入的最少字符数
							minLengthText : "法人姓名最小长度不能少于2个字符！",//提示文本
				            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "法人姓名最大长度不能超过100个字符！",//提示文本
                            name:'enterprise.legalPerson',
                            emptyText:'请输入法人姓名...'
                         },
                         {                                                        
                           	xtype: 'datefield',
                           	labelWidth:100,
                           	width:289,
				         	fieldLabel: '成立日期',
				            editable:false,
				            format:'Y-m-d',
				            value: '2000-01-01',
                            name:'enterprise.openedTime'
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
                            fieldLabel: '联系人', 
                            name:'enterprise.linkman',
                            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "联系人最大长度不能超过100个字符！",//提示文本
                            emptyText:'请输入联系人...'
                         },
                         {                                                        
                            fieldLabel: '联系电话', 
                            labelWidth:100,
                            width:290,
                            name:'enterprise.tel',                           
                            emptyText:'请输入联系电话...',
                            regex:/^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
							regexText:'联系电话输入不正确'				                
                         },
                         {                                                        
                            fieldLabel: '电子邮箱',
                            name:'enterprise.email',
                            vtype:'email',
                            maxLength : 100,//允许输入的最大字符数
							maxLengthText : "联系人最大长度不能超过100个字符！",//提示文本
                            emptyText:'请输入电子邮箱...'
                         },
                         {
				          	xtype: 'container',
							layout: 'hbox',
							width:290,
							items : [{
								xtype:'textfield',
						        fieldLabel: '企业logo',
						        emptyText: '选择一张图片...',
						       	name: 'enterprise.photo',
						    	readOnly : true,
						     	labelWidth : 100,
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
					text:'清空',
					action:'reset'
				}
			]
        });
        me.callParent(arguments);
    }

});