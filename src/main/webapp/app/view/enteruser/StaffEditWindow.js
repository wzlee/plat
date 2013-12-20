Ext.define('plat.view.enteruser.StaffEditWindow', {
    extend: 'Ext.window.Window',
	xtype:'staffeditwindow',
//	id:'staffaddwindow',
       width: 560,
    height: 400,
	layout:'fit',
	buttonAlign:'center',
	closeAction : 'hide',
	modal:true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'staffeditform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:70,
					        labelAlign:'right',
					        margin:'2',
						    editable:false,
					        invalidText:'--暂无数据--'
					    },
					    bodyPadding:'10',
					    items:[
					    		{
					    			xtype:'hiddenfield',
					    			name:'id'
					    		},
					    	 	{
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'userName',
				                    fieldLabel: '会员名',
				                    submitValue:true
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'password',
				                    width: 200,
//				                    allowBlank:false,
//				                    readOnly:true,
//				                    afterLabelTextTpl: required,
				                    fieldLabel: '密码'
				                },
				                {
				                    xtype: 'radiogroup',
				                    width: 259,				                    
				                    //disabled:true,				                    
				                    fieldLabel: '性别',				                    
				                    items: [
				                        {
				                            xtype: 'radiofield',
				                            boxLabel: '男',
				                            inputValue: '0',
//				                            columnWidth:10,				                            
				                            name: 'sex'
				                            
				                            
				                        },
				                        {
				                            xtype: 'radiofield',
				                            boxLabel: '女',
				                            inputValue: '1',
//				                            columnWidth:10,				                            
				                            name: 'sex'
				                            
				                        }
				                    ]
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'staffStatus',				                    
				                    fieldLabel: '账号状态',
				                    submitValue:true,
				                    renderer:function(v){
				                    	return PlatMap.Staff.status[v];
				                    }
				                },
				                {
					    			xtype:'hiddenfield',
					    			name:'parent.id'
					    		},
				                {
				                	xtype: 'displayfield',
				                	name:'parent.userName',
				                	width: 259,
				                	submitValue:true,
				                    fieldLabel: '主账号'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'assignTime',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '分配时间'
				                },
				                {
					    			xtype:'hiddenfield',
					    			name:'manager.id'
					    		},
					    		{
					    			xtype:'hiddenfield',
					    			name:'assigner.id'
					    		},
				                {
				                    xtype: 'displayfield',
				                    fieldLabel: '分配人',
				                    submitValue:true,
				                    width: 259,
				                    name:'fpname'
				                },
				                {
				                    xtype: 'textfield',
				                    name:'mobile',
				                    width: 259,
				                    vtype:'mobile',
				                    fieldLabel: '手机号码'
				                },
//				                {
//				                    xtype: 'textfield',
//				                    width: 259,
//				                    name:'email',
//				                    fieldLabel: '电子邮箱',
//				                    vtype:'email'
//				                },
				                {
				                	xtype: 'combobox',
				                	name:'staffRole.id',
				                	width: 300,
				                    fieldLabel: '用户角色',
				                    allowBlank:false,
				                    afterLabelTextTpl: required,
				                    editable:false,
				                    displayField: 'rolename',
    								valueField: 'id', 
				               		store: new Ext.data.Store({
    									autoLoad:false,
				               			fields:['id','rolename']
				               			
				               		})
				                },
//				                {
//				                    xtype: 'displayfield',
//				                    name:'staffName',
//				                    width: 259,
//				                    fieldLabel: '员工姓名'
//				                },
//				                {
//				                    xtype: 'displayfield',
//				                    name:'tel',
//				                    width: 259,
//				                    submitValue: true,
//				                    fieldLabel: '办公电话'
//				                },
//				                {
//				                    xtype: 'displayfield',
//				                    name:'mobile',
//				                    width: 259,
//				                    submitValue: true,
//				                    fieldLabel: '手机号码'
//				                },
//				                {
//				                    xtype: 'displayfield',
//				                    name:'email',
//				                    width: 259,
//				                    submitValue: true,
//				                    fieldLabel: '电子邮箱'
//				                    
//				                },
//				                {
//				                    xtype: 'displayfield',
//				                    width: 259,
//				                    name:'address',
//				                    submitValue: true,
//				                    fieldLabel: '地址'				                    
//				                }, 
				                {
				                    xtype: 'htmleditor',
				                    height: 150,
				                    width:523,
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