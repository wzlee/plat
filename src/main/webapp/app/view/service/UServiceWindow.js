Ext.define('plat.view.service.UServiceWindow', {
    extend: 'Ext.window.Window',
	xtype:'uservicewindow',
	
//    width: 600,
//    height: 510,
    width: 678,
    height: 500,
	layout:'fit',
	modal:true,
	closeAction : 'hide',
	buttonAlign:'center',
	kindeitor : null,
	getKindeditor : function () {
    	return this.kindeditor;
    },
    setKindeditor : function (kindeditor) {
    	this.kindeditor = kindeditor;
    },
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'userviceform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:61,
					        labelAlign:'right',
					        msgTarget: 'side',
					        margin:'2'
					    },
					    bodyPadding:'10',
					    items:[
					    		{
				                    xtype: 'hiddenfield',
				                    name:'id'
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'totalScore'
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'evaluateScore'
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'sid'
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'modifyTime'
				                },
					    	 	{
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'serviceNo',
				                    submitValue: true,
				                    fieldLabel: '服务编码'
				                    
				                },
				                {
				                	xtype: 'displayfield',
				                    width: 259,
				                    name:'serviceSource',
				                    submitValue: true,
				                    fieldLabel: '服务来源',renderer:function(v){
					    			//服务来源 ：1服务机构本身上传  2运营管理人员代理录入 3 运营服务
				        			return PlatMap.Service.serviceSource[v];
					   				}
				                },
				                {
				                	xtype:'hiddenfield',
				                	name:'enterprise.id',
				                	submitValue:true
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'enterprise.name',
				                    width: 259,
				                    fieldLabel: '所属企业',
				                    submitValue:true
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'serviceName',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '服务名称'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'currentStatus',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '当前状态',
				                    renderer:function(v){
					      	 			 // 服务状态:1新服务,2上架审核中，3已上架，4变更审核中，5已删除,6已下架,7下架审核中
				        				return PlatMap.Service.currentStatus[v];
				        			}
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'lastStatus',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '上一状态',
				                    renderer:function(v){
					      	  			// 服务状态:1新服务,2上架审核中，3已上架，4变更审核中，5已删除,6已下架,7下架审核中
				        				return PlatMap.Service.currentStatus[v];
				        			}
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'registerTime',
				                    submitValue: true,
				                    fieldLabel: '添加时间'				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'serviceNum',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '服务次数'
				                    
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'category.id'
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'category.code'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 350,
				                    name:'category.text',				                    
				                    fieldLabel: '服务类别',
				                    submitValue:true
				                },
				                {
				                    xtype: 'smethodcombo',
				                    fieldLabel: '服务方式',
				                    width: 550,
				                    name:'serviceMethod',
				                    editable:false,
				                    afterLabelTextTpl: required,
				                    allowBlank: false,
				                    emptyText:'请选择服务对象...',
				                    multiSelect:true
				                },
				                {
				                    xtype: 'textfield',
				                    width: 180,
				                    name:'linkMan',
				                    afterLabelTextTpl: required,
				                    allowBlank: false,
				                    fieldLabel: '联系人'
				                },
				                {
				                    xtype: 'textfield',
				                    name:'tel',
				                    labelWidth:75,
				                    width: 180,
				                    afterLabelTextTpl: required,
				                    allowBlank: false,
				                    regex:/^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
									regexText:'联系电话输入不正确',
				                    fieldLabel: '联系电话'
				                },
				                {
				                    xtype: 'textfield',
				                    width: 180,
				                    labelWidth:46,
				                    name:'email',
				                    fieldLabel: '邮箱',
				                    afterLabelTextTpl: required,
				                    allowBlank: false,
				                    vtype:'email'
				                },
				                {				                		
					            	xtype: 'numberfield',
					            	width: 300,
					            	name:'costPrice',
					           		afterLabelTextTpl: required,
					            	allowBlank:false,
					            	minValue: 0,
					           		value: 0,
					             	labelWidth:61,
					              	fieldLabel: '服务价格'
					           	},
//					            {
//					             	xtype: 'textfield',
//					              	width: 345,					              	
//					             	name:'chargeMethod',
//					              	afterLabelTextTpl: required,
//					             	allowBlank:false,
//					             	labelWidth:80,
//					           		fieldLabel: '收费模式'					                
//				                },
				                {
				                	xtype: 'container',
							        anchor: '100%',
							        layout: 'hbox',
							        items : [{
							        	xtype:'textfield',
						                fieldLabel: '图片',
						                name: 'picture',
						                readOnly : true,
						                labelWidth : 61,
						                labelAlign:'right',
						                afterLabelTextTpl: required,
				                    	allowBlank:false,
						                flex : 1
							        }, {
							        	xtype : 'button',
							        	name : 'select',
							        	icon : 'jsLib/extjs/resources/themes/icons/add1.png'
							        }]
				                },
				                /*{
				                    xtype: 'htmleditor',
				                    height: 150,
				                    width:550,
				                    name:'serviceProcedure',
				                    fieldLabel: '服务描述'
				                }*/
				                {
							    	xtype: 'textarea',
							        name: 'serviceProcedure',
							        id : 'serviceProcedure5',
							        height: 180,
							        anchor: '100%',
							        grow : true,
							        style: 'background-color: white;',
							        listeners :　{
							        	render : function (t, opts)　{
							        		setTimeout(function(){  
							                    var editor = KindEditor.create('#serviceProcedure5', {
							                    	uploadJson : 'public/uploadByKindedior',
							                    	themeType : 'simple',
							                    	width : 650,
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
							                    t.findParentByType('uservicewindow').setKindeditor(editor);
							                },1000); 
							        	}
							        }
							    }
					    	]
		            	}
            		],
			buttons:[
				{
					text:'提交变更申请',
//					iconCls:'icon-submit',
					action:'submit'
				},
				{
					text:'取消',
//					iconCls:'icon-reset',
					action:'close'
				}
			]
        });

        me.callParent(arguments);
    }

});