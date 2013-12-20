Ext.define('plat.view.policy.AddReportWindow', {
	extend : 'Ext.window.Window',
	xtype : 'addreportwindow',

	closable : true,
	closeAction : 'hide',
	modal : true,
	layout : {
		type : 'vbox',
		align : 'stretch',
		pack : 'start'
	},
	width : 680,
	height : 550,
	buttonAlign : 'center',
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				xtype : 'form',
				id : 'addreportform',
				layout : {
					type : 'column'
				},
				kindeditor : null,    //新增属性
				defaults : {
					labelWidth : 70,
					labelAlign : 'right',
					msgTarget : 'side',
					margin : '5'
				},
				bodyPadding : '10',
				items : [{
        xtype:'textfield',
        fieldLabel: '标题',
        afterLabelTextTpl: required,
        allowBlank: false,
        name: 'title',
        maxLength : 100
    }, /*{
        xtype:'combo',
        fieldLabel: '政策类别',
        allowBlank: false,
        afterLabelTextTpl: required,
        name: 'policyCategory.id',
        editable : false,
        labelWidth : 80,
        emptyText : '请选择...',
//        store : 'flat.FlatStore',
        store:  Ext.create('Ext.data.Store', {
		    fields: ['id', 'text'],
//		    proxy : {
//		    	type: 'ajax',
//		        url : 'policy/queryCategory',
//		        actionMethods: {  
//		    		read: 'POST'
//		        },
//		        reader : {
//		        	type : 'json',
//			        root : 'data'
//		        }
//		    }
		    data : []
		}),
        queryMode : 'local',
	    displayField: 'text',
	    valueField: 'id'
    }*/{
        xtype: 'treecombo',
        name : 'policyCategory.id',
        afterLabelTextTpl: required,
        allowBlank:false,
//        width: 350,
       store: Ext.data.StoreManager.lookup('reportcategorystore') ? 
		                    		Ext.data.StoreManager.lookup('reportcategorystore'):
		                    		Ext .create( 'plat.store.policy.ReportCategoryStore',
											{
												storeId : 'reportcategorystore'
		}),
        displayField:'text',
		valueField:'id',
		canSelectFolders:false,
        rootVisible:false,
        editable:false,
        emptyText:'请选择类别...',
        fieldLabel: '类别'
    }, {
        xtype:'textfield',
        fieldLabel: '描述',
//        allowBlank: false,
//        afterLabelTextTpl: required,
        name: 'description'
    }, {
    	xtype:'textfield',
        fieldLabel: '来源',
        allowBlank: false,
        afterLabelTextTpl: required,
        name: 'source'
    }, {
    	xtype: 'container',
        anchor: '70%',
        layout: 'hbox',
        items : [{
        	xtype:'textfield',
            fieldLabel: '文件上传',
            name: 'picture',
            readOnly : true,
            flex : 1
        }, {
        	xtype : 'button',
        	name : 'select',
        	icon : 'jsLib/extjs/resources/themes/icons/add1.png'
        }]
    }, {
    	xtype: 'radiogroup',
        fieldLabel: '文件类型',
        columns: 2,
        anchor : '40%',
//        allowBlank : false,
        defaults: {
            name: 'fileType' //Each radio has the same name so the browser will make sure only one is checked at once
        },
        items: [{
            inputValue: 'image',
            boxLabel: '图片',
            checked : true
            
        }, {
            inputValue: 'video',
            boxLabel: '视频'
        }]
    }, 
    	/*{
    	xtype: 'htmleditor',
        name: 'text',
        height: 400,
        anchor: '100%',
        grow : true,
        style: 'background-color: white;',
    	plugins: [new Ext.create('Ext.ux.form.HtmlEditor.imageUpload', {
    									enableContextMenu:false, 
    									submitUrl:'public/uploadFile'})
    			]
    },*/ {
    	xtype: 'textarea',
        name: 'text', 
//        id : 'ta',
        height: 240,
        anchor: '100%',
        grow : true,
        style: 'background-color: white;',
        listeners :　{
        	render : function (t, opts)　{
        		setTimeout(function(){  
                    var editor = KindEditor.create('textarea[name=text]', {
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
                    t.findParentByType('policyform').setKindeditor(editor);
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
    	name : 'time'
    }, {
    	xtype : 'textfield',
    	hidden : true,
    	name : 'pv'
    }],
						getKindeditor : function () {
					    	return this.kindeditor;
					    },
					    setKindeditor : function (kindeditor) {
					    	this.kindeditor = kindeditor;
					    }
			}],
			buttons : [{
						text : '提交',

						action : 'addReport'
					}, {
						text : '清空',
						action : 'reset'
					}]
		});
		this.callParent();
	}
});