Ext.define('plat.view.mall.MallModelForm',{
	extend:'Ext.form.Panel',
	alias : 'widget.mallModelForm',
	id:'mallModelForm',
	frame: false,
    bodyPadding: 5,
    region: 'center',
	initComponent: function() {
        this.callParent();
    },
    items : [{
    	xtype: 'fieldcontainer',
        layout: 'anchor',
        defaultType: 'textfield',
        defaults: {
            anchor: '99%'
        },
        fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 70,
	        msgTarget: 'qtip'
	    },
    	items: [{
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
        	hidden: true,
            name: 'industryType',
            fieldLabel: '服务机构所属行业代码'
        }, {
            name: 'mname',
            fieldLabel: '色块名称',
            flex: 1,
            allowBlank: false
        }, {
            name: 'mindex',
            readOnly: true,
            fieldLabel: '链接地址',
            allowBlank: false
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
	    }, 
	     {
            name: 'micon',
            readOnly: true,
            fieldLabel: '色块图标'            
        },
//	    	{
//            xtype: 'fieldcontainer',
//            layout: 'hbox',
//            items: [{
//	            xtype: 'textfield',
//	            readOnly: true,
//	            name: 'micon',
//                fieldLabel: '色块图标',
//	            id: 'form-file',
//	            emptyText: '请选择图标……',
//	            buttonText: '',
//                flex: 8,
//                allowBlank: false
//            },{
//            	xtype : 'button',
//	        	text : '选择',
//	        	flex : 1.5,
//	        	name : 'selectImg'
//            }]
//        }, 
        	{
			id : "imgpre",
        	xtype : "component",
			autoEl : {
				height : 138,
			    tag : "img",
				style : "padding-left:75px;margin-bottom:5px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);"
			}
        }, {
        	fieldLabel: '备注',
        	grow: true,
            name: 'mdesc',
        	xtype: 'textareafield',
        	height: 80,
            width: 'auto'
        }]
    }]
});



