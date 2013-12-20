var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.flat.FlatManagerForm', {
	extend : 'Ext.form.Panel',
//	title : '窗口信息',
	xtype: 'flatmanagerform',
	autoScroll : true,
    bodyPadding: '15 5 0',
    buttonAlign : 'center',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 80
    },
    defaults: {
    	xtype : 'textfield',
        anchor: '90%'
    },
    init : function () {
    	console.log('FlatManagerForm was initialized!!!');
    	this.callParent(arguments);
    },
    items: [{
			xtype : 'combo',
			fieldLabel: '窗口平台',
	        name: 'flatId',
	        afterLabelTextTpl: required,
	        allowBlank : false,
	        editable : false,
	        queryMode : 'local',
//	        store : 'flat.FlatStore',
	        store:  Ext.create('Ext.data.Store', {
				    fields: ['id', 'flatName'],
				    data : []
        	}),
		    displayField: 'flatName',
		    valueField: 'id'
		}, {
        	name : 'username',
        	fieldLabel: '用户名',
        	afterLabelTextTpl: required,
	        allowBlank : false
        }, {
        	name : 'remark',
        	fieldLabel: '备注'
        }]
});

