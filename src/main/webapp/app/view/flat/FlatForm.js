var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
//自定义IP地址验证
Ext.apply(Ext.form.field.VTypes, {
    IPAddress:  function(v) {
        return /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(v);
    },
    IPAddressText: '请输入合法的ip地址,如<br />"8.8.8.8, 202.168.0.9"',
    IPAddressMask: /[\d\.]/i
});
//自定义手机号码验证
Ext.apply(Ext.form.field.VTypes, {
    moblile:  function(v) {
        return /^1[3|4|5|8][0-9]\d{4,8}$/.test(v);
    },
    moblileText: '不是完整的11位手机号或者正确的手机号前七位',
    moblileMask: /[\d]/
});
//自定义电话号码验证
Ext.apply(Ext.form.field.VTypes, {
    tel:  function(v) {
        return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/.test(v);
    },
    telText: '不是正确的固定电话号码或者手机号码,请按照:0755-12345678,13288881234',
    telMask: /[\d\-]/
});

Ext.define('plat.view.flat.FlatForm', {
	extend : 'Ext.form.Panel',
//	title : '窗口信息',
	id : 'ckxx',
	xtype: 'flatform',
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
        defaultType: 'textfield',
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
        	name : 'flatName',
        	xtype : 'hiddenfield'
        }, {
            xtype : 'displayfield',
            fieldLabel: '平台名称',
            name : 'flatName2'
        },{
        	xtype : 'combo',
            fieldLabel: '平台类型',
            afterLabelTextTpl: required,
            name: 'flatType',
            editable : false,
            store: Ext.create('Ext.data.Store', {
			    fields: ['value', 'text'],
			    data : [
			        {"value" : 1, "text": "综合服务平台"},
			        {"value": 2, "text": "产业服务平台"}
			    ]
            }),
		    queryMode: 'local',
		    displayField: 'text',
		    valueField: 'value'
        },{
        	xtype : 'combo',
            fieldLabel: '平台性质',
            afterLabelTextTpl: required,
            name: 'orgProperty',
            editable : false,
            store: Ext.create('Ext.data.Store', {
			    fields: ['value', 'text'],
			    data : [
			        {"value" : "1", "text": "企业"},
			        {"value": "2", "text": "事业单位"},
			        {"value": "3", "text": "社会团体"},
			        {"value": "4", "text": "民办非企业"}
			    ]
            }),
		    queryMode: 'local',
		    displayField: 'text',
		    valueField: 'value'
        }, {
            xtype: 'datefield',
            fieldLabel: '成立时间',
            afterLabelTextTpl: required,
            name: 'establishTime',
	        maxValue: new Date(),
	        format: 'Y-m-d',
	        editable : false
        }, {
            fieldLabel: '法定代表人',
            afterLabelTextTpl: required,
            name: 'legalPerson',
            minLength : 2,
            maxLength : 30
        }, {
            fieldLabel: '网址',
            afterLabelTextTpl: required,
            name: 'website',
            minLength : 2,
            maxLength : 200
        }, {
            fieldLabel: 'IP地址',
            afterLabelTextTpl: required,
            name: 'ip',
            vtype : 'IPAddress'
        }, {
        	xtype : 'textarea',
            fieldLabel : '平台介绍',
            afterLabelTextTpl : required,
            name : 'mainRemark',
            grow : true
        }]
    },{
        xtype:'fieldset',
        title: '联系信息',
        collapsible: true,
        defaultType: 'textfield',
        layout: 'anchor',
        defaults: {
            anchor: '100%'
        },
        items :[{
            fieldLabel: '联系人',
            name: 'linkMan',
            maxLength : 20,
            afterLabelTextTpl: required,
            allowBlank:false
        }, {
            fieldLabel: '联系电话',
            name: 'tel',
            afterLabelTextTpl: required,
            allowBlank:false, 
            vtype : 'tel'
        }, {
            fieldLabel: '传真',
            name: 'fax',
            maxLength : 20
        }, {
            fieldLabel: '电子邮箱',
            name: 'email',
            vtype:'email',
            maxLength : 20
        }, {
            fieldLabel: '邮政编码',
            name: 'postcode',
            maxLength : 6,
            vtype : 'alphanum'
        }, {
            fieldLabel: '联系地址',
            name: 'address',
            maxLength : 200,
            minLength : 2
        }]
    }],
    buttons: [{
        text: '保存'
    },{
        text: '还原'
    }]
});

