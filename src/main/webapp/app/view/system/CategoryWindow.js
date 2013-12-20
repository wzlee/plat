Ext.define('plat.view.system.CategoryWindow', {
    extend: 'Ext.window.Window',
	xtype:'categoryWindow',
	
    width: 300,
    height: 250,
	layout:'fit',
	closeAction : 'hide',
	buttonAlign:'center',
	modal : true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'categoryform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:60,
					        labelAlign:'right',
					        margin:'1'
					    },
					    bodyPadding:'10',
					    items:[
					    		{
				                    xtype: 'hiddenfield',
				                    name:'id'
				                },{
				                    xtype: 'hiddenfield',
				                    name:'code'
				                },{
				                    xtype: 'hiddenfield',
				                    name:'pid'
				                },{
				                    xtype: 'displayfield',
				                    name:'pname',
				                    width: '200',
				                    fieldLabel: '父类别',
				                    enabled : false
				                },{
				                    xtype: 'textfield',
				                    name:'text',
				                    width: '200',
				                    fieldLabel: '名称',
            						allowBlank: false
				                },{
				                    xtype: 'combobox',
				                    name:'clazz',
				                    width: '200',
				                    fieldLabel: '实体类',
				                    editable : false,
				                    store: {
				                    	fields: ['name', 'value'],
				                    	data : PlatMap.Category.clazz
				                    },
								    displayField: 'name',
								    valueField: 'value'
				                },{
				                    xtype: 'radiogroup',
				                    width: '200',
				                    fieldLabel: '叶节点',
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
				                },{
				                    xtype: 'textfield',
				                    name:'description',
				                    width: '200',
				                    fieldLabel: '描述'
				                }
					    	]
		            	}
            		],
			buttons:[
				{
					text:'提交',
					action:'submit'
				},
				{
					text:'取消',
					action:'esc'
				}
			]
        });
        me.callParent(arguments);
    }

});