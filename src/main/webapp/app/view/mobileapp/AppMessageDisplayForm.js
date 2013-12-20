Ext.define('plat.view.mobileapp.AppMessageDisplayForm', {
	extend : 'Ext.form.Panel',
	xtype: 'appmessagedisplayform',
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
    	xtype : 'displayfield',
        fieldLabel: '标题',
        name: 'title'
    }, {
    	xtype : 'displayfield',
        name: 'content', 
        fieldLabel : '内容'
    }]
});

