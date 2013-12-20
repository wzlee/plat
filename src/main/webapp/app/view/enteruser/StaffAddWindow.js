Ext.define('plat.view.enteruser.StaffAddWindow', {
    extend: 'Ext.window.Window',
	xtype:'staffaddwindow',
//	id:'staffaddwindow',
    width: 350,
    height: 450,
	layout:'fit',
	buttonAlign:'center',
	closeAction : 'hide',
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'staffaddform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:61,
					        labelAlign:'right',
					        msgTarget: 'side',
					        margin:'2'
					    },
					    bodyPadding:'10',
					    items:[
					    		{
					    			xtype:'hiddenfield',
					    			name:'managerId',
					    			value:manager.id
					    		},
					    		{
					    			xtype:'displayfield',
					    			name:'managerName',
					    			width:200,
					    			fieldLabel:'分配人',
					    			value:manager.username
					    		},
					    		{
					    			xtype:'hiddenfield',
					    			name:'staffStatus'
					    		},
					    		{
					    			xtype:'hiddenfield',
					    			name:'parentId'
					    		},
					    		{
					    			xtype:'displayfield',
					    			name:'parentName',
					    			width:200,
					    			fieldLabel:'主账号'
					    		},
				                {
				                    xtype: 'textfield',
				                    name:'userName',
				                    allowBlank:false,
						            afterLabelTextTpl: required,
						            minLength : 2, //允许输入的最少字符数
									minLengthText : "最小长度不能少于2个字符",//提示文本
						            maxLength : 25,//允许输入的最大字符数
									maxLengthText : "最大长度不能超过 25 个字符",//提示文本
									regex:/^\S+$/,
									regexText:'会员名不能有空格',
									fieldLabel: '会员名'
				
				                },
				                {
				                    xtype: 'displayfield',
				                    inputType:'password',
				                    name:'password',
				                    width: 200,
				                    fieldLabel: '密码',
				                    value:'123456',
				                    submitValue:true,
				                    renderer:function(value){
				                    	return '******'
				                    }
				                },
				                {
				                    xtype: 'radiogroup',
				                    width: 200,
				                    fieldLabel: '性别',
				                    afterLabelTextTpl: required,
				                    allowBlank:false,
				                    items: [
				                        {
				                            xtype: 'radiofield',
				                            boxLabel: '男',
				                            inputValue: 0,
				                            columnWidth:50,
				                            checked: true,
				                            name: 'sex'
				                        },
				                        {
				                            xtype: 'radiofield',
				                            boxLabel: '女',
				                            inputValue: 1,
				                            columnWidth:50,
				                            name: 'sex'
				                            
				                        }
				                    ]
				                },
				                {
				                    xtype: 'textfield',
				                    name:'mobile',
				                    vtype:'mobile',
				                    width: 200,
				                    fieldLabel: '联系电话'
				                },
//				                {
//				                    xtype: 'textfield',
//				                    width: 300,
//				                    name:'email',
//				                    fieldLabel: '电子邮箱',
//				                    vtype:'email'
//				                },
				                 {
				                	xtype: 'combobox',
				                	name:'roleName',
				                	width: 300,
				                    fieldLabel: '用户角色',
				                    afterLabelTextTpl: required,
				                    allowBlank:false,
				                    editable:false,
				                    displayField: 'rolename',
    								valueField: 'id', 
				               		store: new Ext.data.Store({
    									autoLoad:false,
				               			fields:['id','rolename']
				               			
				               		})
				                },
				                {
				                    xtype: 'htmleditor',
				                    height: 130,
				                    width:300,
				                    name:'remark',
				                    fieldLabel: '备注'
				                }   
					    	]
		            	}
            		],
			buttons:[
				{
					text:'提交',
//					iconCls:'icon-submit',
					action:'submit'
				},
				{
					text:'取消',
//					iconCls:'icon-reset',
					action:'cancel'
				}
			]
        });
        var nameTest=/^[a-zA-Z]\w{1,19}$/;
		var shortTest=/^\S+$/;
		var mobileReg = /^[1][0-9]{10}$/;
		var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
       	Ext.apply(Ext.form.VTypes, {
	  		userName : function(value,field) {
	  			return nameTest.test(value);
	   		},	   		
	    	userNameText: '用户名必须以字母开头,不能为纯数字的2-20位字符',
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