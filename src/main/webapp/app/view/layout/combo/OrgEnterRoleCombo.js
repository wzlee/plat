Ext.define('plat.view.layout.combo.OrgEnterRoleCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'orgenterrole',
	editable:false,
	width:80,
	queryMode: 'local',
    displayField: 'roleName',
    valueField: 'id',
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['id','roleName'],
    		data:[['信息接收角色','信息接收角色'],['信息发布角色','信息发布角色'],['服务申请角色','服务申请角色'],
    		['交易管理角色','交易管理角色'],['认证管理角色','认证管理角色'],['客户服务角色','客户服务角色']
    		]
    	});
    	this.callParent(arguments);
    }
})