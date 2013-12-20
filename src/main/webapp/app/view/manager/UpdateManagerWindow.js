Ext.define('plat.view.manager.UpdateManagerWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.updatemanagerwindow',
    id:'updatemanagerwindow',
	
    width: 350,
    autoHeight:true,
    title: '修改用户',
    layout: 'fit',
    autoShow: true,
    modal:true,
    buttonAlign:'center',

    initComponent: function() {
        this.items = [
            {
	        	xtype:'form',
	        	layout: {
			        type: 'column'
			    },
			    defaults:{
			        labelWidth:80,
			        labelAlign:'right',
			        margin:'2'
			    },
			    bodyPadding:'10',
                items: [
                    {
                        xtype: 'textfield',
                        disabled:true,
                        width: 300,
                        name : 'username',
                        fieldLabel: '用户名'
                    },
                    {
                        xtype: 'hiddenfield',
                        width: 300,
                        name : 'id'
                    },
                    {
                        xtype: 'hiddenfield',
                        name : 'password',
                        width: 300,
                        fieldLabel: '密码'
                    },
                    {
                        xtype: 'hiddenfield',
                        name : 'userStatus',
                        width: 300,
                        fieldLabel: '账号状态'
                    },
					{
	                //1枢纽平台用户；2窗口平台用户；3其他
                        xtype: 'combobox',
                        name : 'userType',
                        width: 300,
                        fieldLabel: '用户类型',
                		width: 300,
					    queryMode: 'local',
					    displayField: 'userTypeDesc',
					    valueField: 'userType',
					    editable:false,
					    store:  new Ext.data.Store({
					        fields: ['userType','userTypeDesc'],
					        data:[
					        	{"userType":1,"userTypeDesc":"枢纽平台用户"},
					        	{"userType":2,"userTypeDesc":"窗口平台用户"},
					        	{"userType":3,"userTypeDesc":"其他"}
					        ]
					    })
                    },
                    {
                        xtype: 'textfield',
                        name : 'remark',
                        width: 300,
                        fieldLabel: '备注'
                    },
//                    {
//                        xtype: 'textfield',
//                        name : 'flatId',
//                        width: 300,
//                        fieldLabel: '平台ID'
//                    },
                    {
	            		xtype: 'combobox',
	            		id:'pertainrole',
	            		width: 300,
					    fieldLabel: '所属角色',
					    queryMode: 'local',
					    displayField: 'rolename',
	    				afterLabelTextTpl: required,
					    valueField: 'id',
					    editable:false,
					    store:  new Ext.data.Store({
					        fields: ['rolename','id','rights','createTime','roledesc'],
					        autoLoad: true,
							proxy: {
						        type: 'ajax',
						        actionMethods: {  
									read: 'POST'
								},
						        url: 'menu/queryrole',
						        reader: {
						            type: 'json',
						            root: 'data',
						            successProperty: 'success'
						        }
						    }
					    }),
					    validator:function(){
	                    	if(this.getValue()!=""&&null!=this.getValue()){
	                    		return true;
	                    	}else{
	                    		return "该项为必填项，请选择所属角色！";
	                    	}
	                    }
					}
                ]
            }
        ];
        this.buttons = [
            {
                text: '保存',
                action: 'save'
            },
            {
                text: '取消',
                scope: this,
                handler: this.close
            }
        ];
        this.callParent(arguments);
    }
});