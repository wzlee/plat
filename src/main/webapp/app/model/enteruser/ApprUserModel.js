/**
 * 用户审核模型
 * 
 * @author Xiadi
 * @since 2013-8-24
 */
Ext.define('plat.model.enteruser.ApprUserModel', {
			extend : 'Ext.data.Model',
			fields : [
				{name : 'id', type : 'int' }, 
				{name : 'serialId', type : 'String', defaultValue : '暂无' }, 
				{name : 'approveStatus',type : 'String'}, 
				{name : 'approveTime',type : 'String' }, 
				{name : 'user.userName',type : 'String',defaultValue : '暂无'}, 
				{name : 'user.regTime',type : 'String',defaultValue : '暂无'}, 
				{name : 'user.attentionField',type : 'String',defaultValue : '暂无'}, 
				{name : 'manager.username',type : 'String'}, 
				{name : 'applyType',type : 'int'}, 
				{name : 'applyTime',type : 'String'}, 
				{name : 'approvedContext',type : 'String'},		
				// 申请时的信息
				{name : 'icRegNumber',type : 'String',defaultValue : '暂无'},
				{name : 'licenceDuplicate',type : 'String'},
				{name : 'businessLetter',type : 'String'},
				{name : 'attachment',type : 'String'},
				{name : 'name',type : 'String',defaultValue : '暂无'},
				// 企业信息
				{name : 'enterprise.id',type : 'int'},
				{name : 'enterprise.email',type : 'String',defaultValue : '暂无'},
				{name : 'enterprise.type',type : 'int' },
				{name : 'enterprise.industryType',type : 'int' },
				{name : 'enterprise.mainRemark',type : 'String',defaultValue : '暂无'},
				{name : 'enterprise.enterpriseProperty',type : 'String',defaultValue : '暂无'},
				{name : 'enterprise.legalPerson',type : 'String',defaultValue : '暂无'},
				{name : 'enterprise.linkman',type : 'String',defaultValue : '暂无'},
				{name : 'enterprise.tel',type : 'String',defaultValue : '暂无'},
				{name : 'enterprise.address',type : 'String',defaultValue : '暂无'},
				{name : 'enterprise.isApproved',type : 'boolean'}
					]
});
