/**
 * 用户审核模型
 * 
 * @author Xiadi
 * @since 2013-8-24
 */
Ext.define('plat.model.enteruser.OrgRegisterApprovalModel', {
			extend : 'Ext.data.Model',
			fields : [
				{name : 'id', type : 'int' }, 
				{name : 'userName', type : 'String', defaultValue : '暂无' }, 
				{name : 'orgName',type : 'String'}, 
				{name : 'industryType',type : 'int' }, 
				{name : 'email',type : 'String',defaultValue : '暂无'}, 
				{name : 'icRegNumber',type : 'String',defaultValue : '暂无'}, 
				{name : 'licenceDuplicate',type : 'String',defaultValue : '暂无'}, 
				{name : 'businessLetter',type : 'String',defaultValue : '暂无'}, 
				{name : 'regTime',type : 'String'}, 
				{name : 'openedTime',type : 'String',defaultValue : '暂无'},
				{name : 'staffNumber',type : 'Integer'},
				{name : 'orgIndustry',type : 'Integer'},
				{name : 'orgProperty',type : 'Integer'},
				{name : 'business',type : 'String',defaultValue : '暂无'},
				{name : 'advantageServiceAreas',type : 'String',defaultValue : '暂无'},
				{name : 'totalAssets',type : 'double'},
				{name : 'turnover',type : 'double'},
				{name : 'profit',type : 'double'},
				{name : 'honorSociety',type : 'String',defaultValue : '暂无'},
				{name : 'professionalQualifications',type : 'String',defaultValue : '暂无'},
				{name : 'legalRepresentative',type : 'String',defaultValue : '暂无'},
				{name : 'legalRepresentativeMobile',type : 'String',defaultValue : '暂无'},
				{name : 'legalRepresentativeEmail',type : 'String',defaultValue : '暂无'},
				{name : 'generalName',type : 'String',defaultValue : '暂无'},
				{name : 'generalManagerMobile',type : 'String',defaultValue : '暂无'},
				{name : 'generalManagerEmail',type : 'String',defaultValue : '暂无'},
				{name : 'contactName',type : 'String',defaultValue : '暂无'},
				{name : 'contactNameMobile',type : 'String',defaultValue : '暂无'},
				{name : 'contactNameEmail',type : 'String',defaultValue : '暂无'},
				{name : 'orgAddress',type : 'String',defaultValue : '暂无'},
				{name : 'orgPhone',type : 'String',defaultValue : '暂无'},
				{name : 'orgFax',type : 'String',defaultValue : '暂无'},
				{name : 'orgWebsite',type : 'String',defaultValue : '暂无'},
				{name : 'profile',type : 'String',defaultValue : '暂无'},
				{name : 'approveStatus',type : 'int'},		
				{name : 'approveTime',type : 'String'},		
				{name : 'manager.username',type : 'String'}	
			]
});
