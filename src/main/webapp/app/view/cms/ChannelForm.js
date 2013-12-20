Ext.define('plat.view.cms.ChannelForm',{
	extend:'Ext.form.Panel',
	alias : 'widget.channelForm',
	frame: false,
    width: 350,
    bodyPadding: 5,
    fieldDefaults: {
        layout: 'anchor',
        anchor: '98%',
        labelAlign: 'right',
        labelWidth: 70,
        msgTarget: 'qtip'
    },
	initComponent: function() {
        this.callParent();
    },
    items : [{
        name: 'id',
        xtype: 'textfield',
    	hidden: true,
        fieldLabel: '频道ID'
    }, {
        name: 'pid',
        xtype: 'textfield',
    	hidden: true,
        fieldLabel: '频道父ID'
    }, {
        name: 'cindex',
        xtype: 'textfield',
    	hidden: true,
        fieldLabel: '频道位置'
    }, {
        name: 'ccode',
        xtype: 'textfield',
    	hidden: true,
        fieldLabel: '频道编码',
        allowBlank: false
    }, {
        name: 'leaf',
        xtype: 'textfield',
    	hidden: true,
        fieldLabel: '是否为子节点',
        allowBlank: false
    }, {
        name: 'cname',
        xtype: 'textfield',
        fieldLabel: '频道名称',
        allowBlank: false
    }, {
        name: 'chttp',
        xtype: 'textfield',
        fieldLabel: '连接地址',
        value: 'http://localhost',
        allowBlank: false
    }, {
        xtype: 'combobox',
        name: 'mtcode',
        fieldLabel: '所属模版',
        store: 'cms.TemplateStore',
        valueField: 'mtcode',
        displayField: 'mtname',
        queryMode: 'local',
        triggerAction: 'all',
        emptyText:'请选择模版……',
        typeAhead: true,
        editable: false,
        allowBlank: true
    }, {
    	name: 'linktypeRadio',
    	xtype: 'fieldcontainer',
        layout: 'hbox',
        items: [{
            name: 'email',
            xtype: 'radiogroup',
            fieldLabel: '链接方式',
            flex: 1,
			items: [
                {boxLabel: '新窗口', name: 'linktype', inputValue: '_blank', checked: true},
                {boxLabel: '当前窗口', name: 'linktype', inputValue: '_self'}
            ]
        }]
    }, {
    	name: 'isconfigCheck',
        xtype: 'fieldcontainer',
        layout: 'hbox',
        anchor: '98%',
        items: [{
	    	name: 'isChannel',
            xtype: 'checkboxfield',
            checked: false,
    		fieldLabel: '是否为频道',
        	boxLabel: '',
            flex: 3
        }, {
	        name: 'mnumber',
	        xtype: 'numberfield',
	        fieldLabel: '模块个数',
	        hidden: true,
	        value: 3,
	        maxValue: 8,
	        minValue: 0,
	        flex: 4.4
        }]
    }, {
        name: 'cdesc',
        xtype: 'textareafield',
        fieldLabel: '频道描述'
    }]
});



