var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.mobileapp.AppMessageForm', {
	extend : 'Ext.form.Panel',
	xtype: 'appmessageform',
    bodyPadding: '5 5 0',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 80,
        anchor : '90%'
    },
    autoScroll : true, 
    layout : 'anchor',
    init : function () {
    	//console.log('PolicyForm was initialized!!!');
    	this.callParent(arguments);
    },
    items: [{
    	xtype : 'hiddenfield',
    	name : 'id'
    }, {
        xtype:'textfield',
        fieldLabel: '标题',
        afterLabelTextTpl: required,
        allowBlank: false,
        name: 'title',
        maxLength : 12
    }, {
    	xtype: 'container',
        layout: 'hbox',
        padding: '0 5 5 0',
        anchor : '90%',
        items : [{
        	xtype:'textfield',
            fieldLabel: '文件上传',
            name: 'picture',
            readOnly : true,
            flex : 1
        }, {
        	xtype : 'button',
        	name : 'select',
        	icon : 'jsLib/extjs/resources/themes/icons/add1.png'
        }]
    }, {
    	xtype: 'textarea',
        name: 'content', 
        fieldLabel : '内容',
        allowBlank: false,
        afterLabelTextTpl: required,
        grow : true,
        height : 100,
        maxLength : 200,
        style: 'background-color: white;'
    }]
});

