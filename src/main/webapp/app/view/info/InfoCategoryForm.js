var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.info.InfoCategoryForm', {
	extend : 'Ext.form.Panel',
//	title : '窗口信息',
	xtype: 'infocategoryform',
	autoScroll : true,
    bodyPadding: '5 5 0',
    buttonAlign : 'center',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 80
    },
    defaults: {
        anchor: '100%',
        xtype : 'textfield'
    },
    init : function () {
    	console.log('PolicyCategoryForm was initialized!!!');
    	this.callParent(arguments);
    },
    items: [{
    		xtype : 'hiddenfield',
    		name : 'id'
    	},
    	{
            fieldLabel: '类别名称',
            name: 'messageType',
            maxLength : 30,
            afterLabelTextTpl: required,
            validator:function(){
            	if(this.getValue()!=""&&null!=this.getValue()){
            		return true;
            	}else{
            		return "该项为必填项，请填写类别名称！";
            	}
        	}
        }, 
        {
        	xtype : 'textarea',
            fieldLabel: '类别描述',
            name: 'remark',
            maxLength : 100
        }
    ]/*,
    buttons: [{
        text: '保存'
    },{
        text: '还原'
    }]*/
});

