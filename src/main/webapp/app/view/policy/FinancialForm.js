var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('plat.view.policy.FinancialForm', {
	extend : 'Ext.form.Panel',
	xtype: 'financialform',
    bodyPadding: '5 5 0',
    fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 60,
        anchor : '70%'
    },
    autoScroll : true, 
    layout : 'anchor',
    kindeditor : null,    //新增属性
    init : function () {
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
				    Ext.create( 'plat.store.policy.ReportCategoryStore',
					{
						storeId : 'reportcategorystore'
					}),
        displayField:'text',
		valueField:'id',
		loadParams:{type:1},
//		nameTarget:'hiddenfield[name="category.text"]',
//		codeTarget:'hiddenfield[name="category.code"]',
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
		xtype:'datefield',
		name:'overTime',
		width:300,
		labelWidth:120,
		allowBlank:false,
        afterLabelTextTpl: required,
		emptyText:'请选择截止日期',
		fieldLabel:'截止日期',
		labelStyle:'font-size:13',
        format:'Y-m-d'
    },{
    	xtype: 'container',
        anchor: '70%',
        layout: 'hbox',
        items : [{
        	xtype:'textfield',
            fieldLabel: '文件上传',
            name: 'uploadFile',
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
        hidden:true,
        defaults: {
            name: 'fileType' //Each radio has the same name so the browser will make sure only one is checked at once
        },
        items: [{
            inputValue: 'image',
            boxLabel: '图片'
        }, {
            inputValue: 'video',
            boxLabel: '视频',
            checked : true
        }],
        hidden:true
    }, {
    	xtype: 'textarea',
        name: 'text', 
//        id : 'ta',
        fieldLabel: '申报通知全文',
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
                    t.findParentByType('financialform').setKindeditor(editor);
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
    },{
		xtype : 'tabpanel',
		height : 240,
        width: 710,
		id:'reportingtabpannel',
		activeItem:0,
		items : [{
			 		kindeditor : null,
					getKindeditor : function () {
				    	return this.kindeditor;
				    },
				    setKindeditor : function (kindeditor) {
				    	this.kindeditor = kindeditor;
				    },
					title : '申报资格',
					itemId : 'qualifications',
					name:'qualificationpanel',
					height : 400,
					items : [{
						//'qualifications','material','timelimit','process','validity'
				    	xtype: 'textarea',
				        name: 'qualifications',
				        height: 240,
				        width : 709,
				        grow : true,
				        style: 'background-color: white;',
				        listeners :　{
				        	render : function (t, opts)　{
				        		setTimeout(function(){  
				                    var editor = KindEditor.create('textarea[name=qualifications]', {
				                    	uploadJson : 'public/uploadByKindedior',
				                    	themeType : 'simple',
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
				                    t.findParentByType('panel[name=qualificationpanel]').setKindeditor(editor);
				                },1000); 
				        	}
				        }
				    }]
				},{
					kindeditor : null,
					getKindeditor : function () {
				    	return this.kindeditor;
				    },
				    
				    setKindeditor : function (kindeditor) {
				    	this.kindeditor = kindeditor;
				    },
					title : '备案要求',
					itemId : 'validity',
					name:'validTimepanel',
					items : [{
				    	xtype: 'textarea',
				        name: 'validity',
				        height: 240,
				        width : 709,
				        grow : true,
				        style: 'background-color: white;',
				        listeners :　{
				        	render : function (t, opts)　{
				        		setTimeout(function(){  
				                    var editor = KindEditor.create('textarea[name=validity]', {
				                    	uploadJson : 'public/uploadByKindedior',
				                    	themeType : 'simple',
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
				                    t.findParentByType('panel[name=validTimepanel]').setKindeditor(editor);
				                },1000); 
				        	}
				        }
				    }]
				
				}, {
					kindeditor : null,
					getKindeditor : function () {
				    	return this.kindeditor;
				    },
				    
				    setKindeditor : function (kindeditor) {
				    	this.kindeditor = kindeditor;
				    },
					title : '所需材料',
					itemId : 'material',
					name:'materialspanel',
					items : [{
						
				    	xtype: 'textarea',
				        name: 'material',
				        height: 240,
				        width : 709,
				        grow : true,
				        style: 'background-color: white;',
				        listeners :　{
				        	render : function (t, opts)　{
				        		setTimeout(function(){  
				                    var editor = KindEditor.create('textarea[name=material]', {
				                    	uploadJson : 'public/uploadByKindedior',
				                    	themeType : 'simple',
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
				                    t.findParentByType('panel[name=materialspanel]').setKindeditor(editor);
				                },1000); 
				        	}
				        }
				    }]
				}, {
					kindeditor : null,
					getKindeditor : function () {
				    	return this.kindeditor;
				    },
				    
				    setKindeditor : function (kindeditor) {
				    	this.kindeditor = kindeditor;
				    },
					title : '申报流程',
					itemId : 'process',
					name:'processpanel',
					items : [{
				    	xtype: 'textarea',
				        name: 'process',
				       	height: 240,
				        width : 709,
				        grow : true,
				        style: 'background-color: white;',
				        listeners :　{
				        	render : function (t, opts)　{
				        		setTimeout(function(){  
				                    var editor = KindEditor.create('textarea[name=process]', {
				                    	uploadJson : 'public/uploadByKindedior',
				                    	themeType : 'simple',
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
				                    t.findParentByType('panel[name=processpanel]').setKindeditor(editor);
				                },1000); 
				        	}
				        }
				    }]
				}, {
					kindeditor : null,
					getKindeditor : function () {
				    	return this.kindeditor;
				    },
				    
				    setKindeditor : function (kindeditor) {
				    	this.kindeditor = kindeditor;
				    },
					title : '受理时限',
					itemId : 'timeLimit',
					name:'timeLimitpanel',
					items : [{
				    	xtype: 'textarea',
				        name: 'timeLimit',
				        height: 240,
				        width : 709,
				        grow : true,
				        style: 'background-color: white;',
				        listeners :　{
				        	render : function (t, opts)　{
				        		setTimeout(function(){  
				                    var editor = KindEditor.create('textarea[name=timeLimit]', {
				                    	uploadJson : 'public/uploadByKindedior',
				                    	themeType : 'simple',
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
				                    t.findParentByType('panel[name=timeLimitpanel]').setKindeditor(editor);
				                },1000); 
				        	}
				        }
				    }]
				}]
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

