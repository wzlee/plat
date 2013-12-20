
Ext.define('plat.view.flat.FlatInfoDisplay', {
	extend : 'Ext.form.Panel',
	xtype: 'flatinfodisplay',
	autoScroll : true,
    bodyPadding: '5 5 0',
    buttonAlign : 'center',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 80
    },
    defaults: {
        anchor: '100%'
    },
    init : function () {
    	console.log('FlatForm was initialized!!!');
    	this.callParent(arguments);
    },
    items: [{
        xtype:'fieldset',
        title: '基本信息',
        defaultType: 'displayfield',
        collapsible: true,
        layout: 'anchor',
        defaults: {
            anchor: '100%',
            allowBlank:false
        },
        items :[{
        	name : 'id',
        	xtype : 'hiddenfield'
        }, {
            fieldLabel: '平台名称',
            name: 'flatName'
        }, {
        	fieldLabel: '平台类型',
            name: 'flatType',
            renderer : function (value) {
            	var data = {
            		1 : "综合服务平台",
			        2 : "产业服务平台"
            	};
            	return data[value];
            }
        }, {
        	fieldLabel: '平台性质',
            name: 'orgProperty',
            renderer : function (value) {
            	var data = {
            		"1" : "企业",
			        "2" : "事业单位",
			        "3" : "社会团体",
			        "4" : "民办非企业"
            	};
            	return data[value];
            }
        }, {
            fieldLabel: '成立时间',
            name: 'establishTime'
        }, {
            fieldLabel: '法定代表人',
            name: 'legalPerson'
        }, {
            fieldLabel: '网址',
            name: 'website'
        }, {
            fieldLabel: 'IP地址',
            name: 'ip'
        }, {
//        	xtype : 'textarea',
            fieldLabel : '平台介绍',
            name : 'mainRemark'
//            grow : true,
//            editable : false
//            disable : true
        }]
    },{
        xtype:'fieldset',
        title: '联系信息',
        collapsible: true,
        defaultType: 'displayfield',
        layout: 'anchor',
        defaults: {
            anchor: '100%'
        },
        items :[{
            fieldLabel: '联系人',
            name: 'linkMan'
        }, {
            fieldLabel: '联系电话',
            name: 'tel'
        }, {
            fieldLabel: '传真',
            name: 'fax'
        }, {
            fieldLabel: '电子邮箱',
            name: 'email'
        }, {
            fieldLabel: '邮政编码',
            name: 'postcode'
        }, {
            fieldLabel: '联系地址',
            name: 'address'
        }]
    }]/*,
    buttons: [{
        text: '保存'
    },{
        text: '还原'
    }]*/
});

