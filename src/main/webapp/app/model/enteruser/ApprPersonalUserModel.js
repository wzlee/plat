/**
 * 个人用户审核模型
 * 
 * @author xuwf
 * @since 2013-10-28
 */
Ext.define('plat.model.enteruser.ApprPersonalUserModel', {
			extend : 'Ext.data.Model',
			fields : [
				{name : 'id', type : 'int' }, 
				{name : 'serialId', type : 'String', defaultValue : '暂无' }, 
				{name : 'approveStatus',type : 'String'}, 
				{name : 'approveTime',type : 'String' }, 
				{name : 'manager.username',type : 'String'}, 
				{name : 'applyType',type : 'int'}, 
				{name : 'applyTime',type : 'String'}, 
				{name : 'approvedContext',type : 'String'},		
				// 申请时的信息
				{name : 'personalPhoto',type : 'String'},
				{name : 'idCardPhoto',type : 'String'},
				{name : 'name',type : 'String',defaultValue : '暂无'},
				// 个人用户信息
				{name : 'user.userName',type : 'String',defaultValue : '暂无'}, 
				{name : 'user.regTime',type : 'String',defaultValue : '暂无'}, 
				{name : 'user.email',type : 'String',defaultValue : '暂无'},
				{name : 'user.nickName',type : 'String',defaultValue : '暂无'},
				{name : 'user.headPortraint',type : 'String',defaultValue : '暂无'},
				{name : 'user.mobile',type : 'String',defaultValue : '暂无'},
				{name : 'user.address',type : 'String',defaultValue : '暂无'},
				{name : 'user.isDomainExpert',type : 'String',defaultValue : '暂无'},
				{name : 'user.certSign',type : 'int',defaultValue : '暂无'}
			]
});
