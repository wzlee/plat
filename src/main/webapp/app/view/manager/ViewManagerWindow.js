Ext.define('plat.view.manager.ViewManagerWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.viewmanagerwindow',
    id:'viewmanagerwindow',
	
    width: 350,
    autoHeight:true,
    title: '查看用户',
    layout: 'fit',
    autoShow: true,
    modal:true,
    buttonAlign:'center',

    initComponent: function() {
        this.items = [
            {
	        	xtype:'form',
	        	id:'viewuser',
	        	layout: {
			        type: 'column'
			    },
			    defaults:{
			        labelWidth:80,
			        labelAlign:'right',
			        msgTarget: 'side',
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
                    	
                    	xtype: 'textfield',
                        name : 'roleName',
                        width: 300,
                        fieldLabel: '所属角色',
                         disabled:true
                    	
		                		
					},
                    {
                        xtype: 'textfield',
                        name : 'remark',
                        width: 300,
                        fieldLabel: '备注',
                        disabled:true
                     
                    }
                ]
            }
        ];
        this.buttons = [
            {
                text: '关闭',
                scope: this,
                handler: this.close
            }
        ];
        this.callParent(arguments);
    }
});