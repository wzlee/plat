Ext.define('plat.view.mall.MallCategoryWindow', {
    extend: 'Ext.window.Window',
	xtype:'mallcategorywindow',
	
    width: 300,
    height: 350,
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
		            	id:'mallcategoryform',
		            	layout: {
		            		
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:60,
					        labelAlign:'right',
					        margin:'5'
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
				                    store:[['service', 'service']],
								    displayField: 'name',
								    valueField: 'value',
								    hidden:true,
								    submitValue:true
				                },{
				                    xtype: 'radiogroup',
				                    width: '200',
				                    fieldLabel: '是否显示',
				                    items: [
				                        {
				                        	boxLabel: '隐藏',
				                            inputValue: true,
				                            name : 'hide'
				                         },{   
				                         	boxLabel: '显示',
				                            inputValue: false,
				                            name : 'hide',
				                            checked : true
				                        }
				                    ]
				                }, {
									xtype : 'combo',
									name : 'sort',
									allowBlank : false,
									afterLabelTextTpl : required,
									width : 200,
									store : [[0, '0'], [1, '1'], [2, '2'], [3, '3'],
											[4, '4'], [5, '5'], [6, '6'], [7, '7'],
											[8, '8'], [9, '9'], [10, '10'], [11, '11'],
											[12, '12'], [13, '13'], [14, '14'],
											[15, '15'], [16, '16'], [17, '17'],
											[18, '18'], [19, '19'], [20, '20'],
											[21, '21'], [22, '22'], [23, '23'],
											[24, '24'], [25, '25']],
									rootVisible : false,
									editable : false,
									emptyText : '请选择顺序...',
									fieldLabel : '顺序'
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