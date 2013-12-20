Ext.define('plat.view.service.ServiceEditWindow', {
    extend: 'Ext.window.Window',
	xtype:'serviceeditwindow',
	
    width: 678,
    height: 500,
	layout:'fit',
	modal:true,
	buttonAlign:'center',
	closeAction : 'hide',
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
		            	id:'serviceeditform',
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
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'serviceNo',
				                    submitValue: true,
				                    fieldLabel: '服务编码'
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,				                    
				                    //disabled:true,				                    
				                    fieldLabel: '来源',	
				                    name:'serviceSource',
				                    renderer:function(v){
				                    	return PlatMap.Service.serviceSource[v];
				                    },
				                    submitValue:true
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
				                    xtype: 'textfield',
				                    name:'serviceName',
				                    allowBlank:false,
				                    afterLabelTextTpl: required,
				                    width: 259,
				                    minLength:5,
				                    minLengthText:'服务名称限制在5个字符以上',
				                    maxLength:50,
				                    maxLengthText:'服务名称限制在 50 个字符以内',
//				                    submitValue: true,
				                    fieldLabel: '服务名称'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'currentStatus',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '当前状态',
				                    renderer:function(v){
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
				        				return PlatMap.Service.currentStatus[v]==null ? '':PlatMap.Service.currentStatus[v];
				        			} 
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'registerTime',
				                    submitValue: true,
				                    fieldLabel: '注册时间'				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'serviceNum',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '服务次数'
				                    
				                },
				                {
				                    xtype: 'eusercategorycombo',
				                    name:'category.id',			                    
				                    afterLabelTextTpl: required,				                    
				                    allowBlank:false,
				                    width: 350,					
				                    editable:false,				                    
				                    emptyText:'请选择服务类别...',
				                    fieldLabel: '服务类别'
				                },				            
				                {
				                    xtype: 'smethodcombo',
				                    fieldLabel: '服务方式',
				                    editable:false,
				                    emptyText:'请选择服务方式...',
				                    width: 550,
				                    name:'serviceMethod',
				                    afterLabelTextTpl: required,
				                    allowBlank: false,
				                    multiSelect:true
				                },
				                {
				                    xtype: 'textfield',
				                    width: 180,
				                    name:'linkMan',
				                    afterLabelTextTpl: required,
				                    allowBlank:false,
				                    fieldLabel: '联系人'
				                },
				                {
				                    xtype: 'textfield',
				                    name:'tel',
				                    labelWidth:75,
				                    width: 180,
				                    afterLabelTextTpl: required,
				                    allowBlank:false,
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
				                    allowBlank:false,
				                    vtype:'email'
				                },
				                {				                		
					            	xtype: 'numberfield',
					            	width: 300,
					            	name:'costPrice',
					           		afterLabelTextTpl: required,
					            	allowBlank:false,
					            	allowDecimals:false,
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
				               /* {
				                    xtype: 'htmleditor',
				                    height: 150,
				                    width:550,
				                    name:'serviceProcedure',
				                    fieldLabel: '服务描述'
				                }*/
				                {
							    	xtype: 'textarea',
							        name: 'serviceProcedure',
							        id : 'serviceProcedure4',
							        height: 180,
							        anchor: '100%',
							        grow : true,
							        style: 'background-color: white;',
							        listeners :　{
							        	render : function (t, opts)　{
							        		setTimeout(function(){  
							                    var editor = KindEditor.create('#serviceProcedure4', {
							                    	uploadJson : 'public/uploadByKindedior',
							                    	themeType : 'simple',
							                    	resizeType : 0,
							                    	width : 650,
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
							                    t.findParentByType('serviceeditwindow').setKindeditor(editor);
							                },1000); 
							        	}
							        }
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
					action:'reset'
				}
			]
        });

        me.callParent(arguments);
    }

});