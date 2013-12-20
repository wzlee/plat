Ext.define('plat.view.mall.MallModelConfigPanel',{
	extend:'Ext.panel.Panel',
	alias:'widget.mallModelConfigPanel',
	width: 600,
	height: 450,
	frame: false,
    border : false,
    layout : 'border',
    initComponent: function() {
        this.callParent();
    },
//    tbar: [{
//    	xtype : 'label',
//    	text : '请选择模版：',
//        forId: 'myFieldId',
//        margin: '0 10',
//    	scope : this
//    }, {
//    	xtype: 'combobox',
//        width: 135,
//    	hideLabel: true,
//    	valueField: 'id',
//        store: Ext.create('Ext.data.JsonStore', {
//            fields: ['id', 'text'],
//            data : [
//            	{id : 's_category', text : '服务类别'},
//				{id : 's_organ', text : '服务机构'}
//			]
//        }),
//        value: 's_category',
//        displayField: 'text',
//        typeAhead: true,
//        queryMode: 'local',
//        triggerAction: 'all',
//        emptyText:'请选择模版……',
//        selectOnFocus:true
//    }],
    items: [/*{
    	xtype: 'categoryServeTree'
    }*/{
    	xtype : 'panel',
		region: 'west',
		width: '55%',
    	layout:'card',
		activeItem: 0, // index or id
		items: [
//			{
//	    		xtype: 'categoryServeTree'
//	    	},
	    	{
				xtype: 'serviceagencygrid'	
			}]
    },{
    	xtype: 'modelForm'
    }]
});