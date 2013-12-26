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
    kindeditor : null,
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
    	xtype : 'combo',
    	fieldLabel: '接受对象',
	    multiSelect: true,
	    name : 'receiver',
	    displayField: 'name',
	    valueField : 'value',
	    editable : false,
	    forceSelection : true,
//	    width: 500,
//	    labelWidth: 130,
	    queryMode: 'local',
	    store : Ext.create('Ext.data.Store', {
		    fields : ['name', 'value'],
		    data : [{"name" : "未绑定用户", "value" : 0}, 
		    	{"name" : "服务机构", "value" : 1},
		    	{"name" : "认证企业", "value" : 2},
		    	{"name" : "普通企业", "value" : 3},
		    	{"name" : "个人用户", "value" : 4}]
	    })
    }, {
    	xtype: 'container',
        layout: 'hbox',
        items : [{
//        	width : 335,
        	flex : 1,
        	margin: '0 5 0 0',
        	items : [{
	            xtype:'displayfield',
	            fieldLabel: '响应内容',
	            name: 'autoMessageContent',
	            anchor: '100%'
	        }]	          
        }, {
        	xtype:'button',
            text: '选择响应消息',
            name : 'selMsg',
            width : 100
        }]
    	
    }, /*{
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
    }, */{
    	xtype: 'textarea',
        name: 'content',
//        id : 'ta',
        height: 200,
        anchor: '100%',
        style: 'background-color: white;',
        listeners :　{
        	render : function (t, opts)　{
        		setTimeout(function(){  
                    var editor = KindEditor.create('textarea[name=content]', {
                    	uploadJson : 'public/uploadByKindedior',
                    	themeType : 'simple',
                    	resizeType : 0,
                    	items : ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', '/',
					        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
					        'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
					        'anchor', 'link', 'unlink', '|', 'fullscreen', 'about'
						]
                    }); 
                    t.findParentByType('articleinfoform').setKindeditor(editor);
                },1000); 
        	}
        }
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
    }],
    
    getKindeditor : function () {
    	return this.kindeditor;
    },
    
    setKindeditor : function (kindeditor) {
    	this.kindeditor = kindeditor;
    }
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

