var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.wx.ArticleInfoForm', {
	extend : 'Ext.form.Panel',
	xtype: 'articleinfoform',
    bodyPadding: '5 5 0',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 80,
        anchor: '100%'
    },
    init : function () {
    	//console.log('NewsForm was initialized!!!');
    	this.callParent(arguments);
    },
     items: [{
        xtype:'textfield',
        fieldLabel: '标题',
        afterLabelTextTpl: required,
        allowBlank: false,
        name: 'title',
        maxLength : 100
    }, /*{
        xtype:'textfield',
        fieldLabel: '发布人',
        allowBlank: false,
        afterLabelTextTpl: required,
        name: 'author'
    }, */, {
        xtype:'hiddenfield',
        fieldLabel: '响应id',
        name: 'autoMessageId'
    }, {
    	xtype: 'container',
        layout: 'hbox',
        padding : '0 0 5 0',
        items : [{
        	xtype:'textfield',
            fieldLabel: '配图',
            name: 'picUrl',
            allowBlank : false,
            afterLabelTextTpl: required,
            readOnly : true,
            flex : 1
        }, {
        	xtype : 'button',
        	name : 'select',
        	icon : 'jsLib/extjs/resources/themes/icons/add1.png'
        }]
    	
    }, {
        xtype:'textfield',
        fieldLabel: '描述',
        allowBlank: false,
        afterLabelTextTpl: required,
        name: 'description',
        maxLength : 25
    },  {
        xtype:'textfield',
        fieldLabel: '跳转链接',
//                allowBlank: false,
//                afterLabelTextTpl: required,
        name: 'url'
    }, {
        xtype:'fieldset',
        columnWidth: 0.5,
        title: '响应消息',
        collapsible: false,
//		        defaultType: 'textfield',
        layout: 'anchor',
        items :[{
	    	xtype: 'container',
	        layout: 'hbox',
	        items : [{
	        	width : 335,
	        	margin: '0 5 0 0',
	        	items : [{
		            xtype:'displayfield',
		            fieldLabel: '响应内容',
		            name: 'content',
		            anchor: '100%'
		        }, {
		            xtype:'displayfield',
		            fieldLabel: '输入指令',
		            name: 'reqKey',
		            anchor: '100%'
		        },{
		            xtype:'displayfield',
		            fieldLabel: '点击指令',
		            name: 'clickKey',
		            anchor: '100%'
		        }]	          
	        }, {
	        	xtype:'button',
	            text: '选择响应消息',
	            name : 'selMsg',
	            width : 100
	        }]
	    	
	    }]
    }, {
    	xtype : 'textfield',
    	hidden : true,
    	name : 'id'
    }, {
    	xtype : 'textfield',
    	hidden : true,
    	name : 'pubdate'
    }, {
    	xtype : 'textfield',
    	hidden : true,
    	name : 'originalPic'
    }]
    /*,

    buttons: [{
        text: 'Save',
        handler: function() {
            this.up('form').getForm().isValid();
        }
    },{
        text: 'Cancel',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }]*/
});

