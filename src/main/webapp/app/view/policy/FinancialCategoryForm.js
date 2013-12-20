var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.policy.FinancialCategoryForm', {
	extend : 'Ext.form.Panel',
//	title : '窗口信息',
	xtype: 'financialcategoryform',
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
    	console.log('FinancialCategoryForm was initialized!!!');
    	this.callParent(arguments);
    },
    items: [{
    		xtype : 'hiddenfield',
    		name : 'id'
    	}, /*{
    		xtype : 'hiddenfield',
    		name : 'leaf'
    	}, */{
    		xtype : 'hiddenfield',
    		name : 'pid'
    	}, {
            fieldLabel: '类别名称',
            name: 'text',
            maxLength : 30,
            afterLabelTextTpl: required,
            allowBlank:false
        }, 
//        	{
//			xtype : 'combo',
//			fieldLabel: '分类范畴',
//	        name: 'type',
//	        editable : false,
//	        emptyText : '请选择...',
//	        afterLabelTextTpl: required,
//	        store:  Ext.create('Ext.data.Store', {
//					    fields: ['id', 'text'],
//					    data : [{
//					    	id : 0,
//					    	text : '政策法规'
//					    }, {
//					    	id : 1,
//					    	text : '专项资金'
//					    }] 
//	        }),
//	        queryMode : 'local',
//		    displayField: 'text',
//		    valueField: 'id'
//		},
		{
//			xtype : 'displayfield',
//			name : 'display_type',
//			fieldLabel : '分类范畴',
//			hidden : true,
			xtype:'hiddenfield',
			name:'type',
			value:'1'
		}, 
		{
            xtype: 'radiogroup',
            anchor : '50%',
            fieldLabel: '子节点',
            items: [
                {
                    boxLabel: '是',
                    inputValue: true,
                    name : 'leaf'
                   
                },{
                    boxLabel: '否',
                    inputValue: false,
                    name : 'leaf',
                    checked : true
                   
                }
            ]
         }, {
        	xtype : 'textarea',
            fieldLabel: '类别描述',
            name: 'description',
            maxLength : 100
        }
    ]/*,
    buttons: [{
        text: '保存'
    },{
        text: '还原'
    }]*/
});

