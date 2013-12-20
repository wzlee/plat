var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.public.UploadForm', {
	extend : 'Ext.form.Panel',
	xtype: 'uploadform',
    bodyPadding: '5 5 0',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 50
    },
    items: [{
        xtype:'filefield',
        fieldLabel: '文件',
        afterLabelTextTpl: required,
        allowBlank: false,
        name: 'file',
        anchor: '100%',
//        buttonText : '选择',
        buttonText: '',
        readOnly : true,
        buttonConfig: {
            icon: 'jsLib/extjs/shared/icons/fam/image_add.png'
        }
    }]
});

