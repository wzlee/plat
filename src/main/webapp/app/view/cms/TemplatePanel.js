Ext.define('plat.view.cms.TemplatePanel',{
	extend:'Ext.panel.Panel',
	alias:'widget.templatePanel',
	id: 'tempPanelID',
    border : false,
    region : 'center',
    title: 'Banner信息',
    layout: {
	    type: 'hbox',
	    align: 'middle ',
	    pack: 'center'
	},
    autoScroll:true,
    bodyPadding: '50 0 0 0',
    defaults: {
        layout: 'anchor',
        defaults: {
            anchor: '100%',
            style: {
            	border: '0px #C7C1BB solid'
            }
        }
    },
    initComponent: function() {
        this.callParent();
    },
    tbar : [{
    	xtype : 'label',
    	text : '请选择模版：',
        forId: 'myFieldId',
        margin: '0 10',
        hidden: true,
    	scope : this
    }, {
    	xtype: 'combobox',
        width: 135,
        hidden: true,
    	hideLabel: true,
    	valueField: 'mtcode',
        store: 'cms.TemplateStore',
        displayField: 'mtname',
        typeAhead: true,
        queryMode: 'local',
        triggerAction: 'all',
        emptyText:'请选择模版……',
        selectOnFocus:true
    }]
});

