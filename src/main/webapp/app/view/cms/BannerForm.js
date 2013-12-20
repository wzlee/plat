Ext.define('plat.view.cms.BannerForm',{
	extend:'Ext.form.Panel',
	alias : 'widget.bannerForm',
	frame: false,
	border: false,
    width: 450,
    bodyPadding: 5,
    fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 70,
        msgTarget: 'qtip'
    },
    defaultType: 'textfield',
    layout: 'anchor',
        defaults: {
            anchor: '100%'
        },
	initComponent: function() {
        this.callParent();
    },
    items : [{
    	hidden: true,
        name: 'mid',
        fieldLabel: '模块ID'
    }, {
    	hidden: true,
        name: 'mchannel',
        fieldLabel: '所属频道'
    }, {
    	hidden: true,
        name: 'mposition',
        fieldLabel: '色块索引'
    }, {
    	hidden: true,
        name: 'mcode',
        fieldLabel: '色块编码'
    }, {
    	xtype: 'fieldcontainer',
        flex: 1,
        layout: 'anchor',
        defaultType: 'textfield',
        defaults: {
            hideLabel: false,
            anchor: '98%'
        },
    	items:[{
            name: 'mname',
            fieldLabel: '色块名称',
            allowBlank: true,
            flex: 1
        }, {
            name: 'mindex',
            fieldLabel: '链接地址',
            value: 'http://www.houjie88.com',
            allowBlank: true,
            flex: 1
        }, {
            xtype: 'combobox',
            name: 'mclass',
            fieldLabel: '色块样式',
            store: Ext.create('Ext.data.JsonStore', {
                fields: ['id', 'text'],
                data : [
                	{id : 'cube', text : 'cube'},
					{id : 'cubeRandom', text : 'cubeRandom'},
					{id : 'block', text : 'block'},
					{id : 'cubeStop', text : 'cubeStop'},
					{id : 'cubeHide', text : 'cubeHide'},
					{id : 'cubeSize', text : 'cubeSize'},
					{id : 'horizontal', text : 'horizontal'},
					{id : 'showBars', text : 'showBars'},
					{id : 'showBarsRandom', text : 'showBarsRandom'},
					{id : 'cubeStopRandom', text : 'cubeStopRandom'}
				]
            }),
            value: 'cube',
            valueField: 'id',
            displayField: 'text',
            queryMode: 'local',
            typeAhead: true,
            editable: false,
            allowBlank: true
        }, {
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
            xtype: 'fieldcontainer',
            layout: 'hbox',
            items: [{
	            xtype: 'textfield',
	            readOnly: true,
	            name: 'micon',
                fieldLabel: '色块图标',
	            id: 'form-file',
	            emptyText: '请选择图标……',
	            buttonText: '',
                flex: 9
            },{
            	xtype : 'button',
	        	text : '选择',
	        	flex : 1,
	        	name : 'selectImg'
            }]
        }, {
			id : "imgpre",
        	xtype : "component",
        	fieldLabel: '图标预览',
        	flex: 1,
			autoEl : {
				height : 120,
			    tag : "img",
				style : "padding-left:75px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);"
			}
        }, {
            name: 'mdesc',
            style: 'margin-top:5px;',
            fieldLabel: '色块描述',
        	xtype: 'textareafield',
        	height: 120,
            width: 'auto'
        }] 
    }]
});



