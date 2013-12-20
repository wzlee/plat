var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.wx.AutoMessageForm', {
	extend : 'Ext.form.Panel',
	xtype: 'automessageform',
    bodyPadding: '5 5 0',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 80,
        anchor: '100%'
    },
    init : function () {
    	console.log('AutoMessageForm was initialized!!!');
    	this.callParent(arguments);
    },
     items: [{
        xtype: 'combobox',
        name: 'type',
        fieldLabel: '类型',
        afterLabelTextTpl: required,
        store: Ext.create('Ext.data.JsonStore', {
            fields: ['value', 'text'],
            data : [
            	{value : 'news', text : '图文'},
				{value : 'text', text : '文本'}
			]
        }),
        valueField: 'value',
        displayField: 'text',
        queryMode: 'local',
        typeAhead: true,
        editable: false,
        allowBlank: false
    }, {
        xtype:'textfield',
        fieldLabel: '输入指令',
        afterLabelTextTpl: required,
        allowBlank: false,
        name: 'reqKey',
        maxLength : 100
    }, {
        xtype:'textfield',
        fieldLabel: '点击指令',
        allowBlank: false,
        afterLabelTextTpl: required,
        name: 'clickKey'
    }, {
        xtype:'textarea',
        name: 'content',
        afterLabelTextTpl: required,
        fieldLabel : '内容',
        allowBlank : false,
        style: 'background-color: white;'
    }, {
    	xtype : 'textfield',
    	hidden : true,
    	name : 'id'
    }]
});

