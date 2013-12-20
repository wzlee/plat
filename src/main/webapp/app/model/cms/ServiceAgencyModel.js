/**
 * 服务机构模版 liuliping
 */
Ext.define('plat.model.cms.ServiceAgencyModel', {
    extend: 'Ext.data.Model',
    fields: [{
    		name : 'mindex',
    		mapping : 'id'
    	},
    	'enterpriseNo',
    	'enterpriseProperty',
    	'enterpriseCode',{
    		name : 'mname',
    		mapping : 'name'
    	},	
    	'forShort',
    	'type',
    	'legalPerson',
    	'registerType',
    	'areaCounty',
    	'linkman',
		'tel',
		'fax',
		'email',
		'website',
		'address',
		'postcode',
		'industryType',
		'mainRemark',
		'shareType',
		'openedTime', {
			name : 'micon',
			mapping : 'photo'
		},
		'licenceDuplicate',
		'businessLetter',
		'isApproved',
		'icRegNumber',
		'serviceMiddleClass',
		'epShort'
    ]
});
