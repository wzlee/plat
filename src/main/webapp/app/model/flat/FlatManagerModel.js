Ext.define('plat.model.flat.FlatManagerModel', {
	extend : 'Ext.data.Model',
	fields: [
		{name : 'id', mapping : 'manager.id'},
		{name : 'username', mapping : 'manager.username'},
		{name : 'password', mapping : 'manager.password'},
		{name : 'userStatus', mapping : 'manager.userStatus'},
		{name : 'userType', mapping : 'manager.userType'},
		{name : 'remark', mapping : 'manager.remark'},
		{name : 'flatId', mapping : 'manager.flatId'},
		{name : 'registerTime', mapping : 'manager.registerTime'},
		'flatName',
		{name : 'rolename', mapping : 'manager.role.rolename'}
		/*'role.id', 
		'role.roledesc', 
		'role.createTime', 
		'role.menuIds'*/
	]
});

