Ext.define('plat.view.service.ServiceDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'servicedetailwindow',
	
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
		            	id:'servicedetailform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:60,
					        labelAlign:'right',
					        margin:'2',
//						    disabled:true,
						    editable:false,
					        emptyText:'--暂无数据--'
					    },
					    bodyPadding:'10',
					    items:[
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
				                    fieldLabel: '服务来源',		
				                    name:'serviceSource',
				                    renderer:function(v){
				                    	return PlatMap.Service.serviceSource[v];
				                    },
				                    submitValue:true
//				                    items: [
//				                        {
//				                            xtype: 'radiofield',
//				                            boxLabel: '运营平台',
//				                            inputValue: '1',
//				                            readOnly:true,
//				                            columnWidth:50,				                            
//				                            name: 'serviceSource'
//				                            
//				                            
//				                        },
//				                        {
//				                            xtype: 'radiofield',
//				                            boxLabel: '服务机构',
//				                            inputValue: '2',
//				                            readOnly:true,
//				                            columnWidth:50,				                            
//				                            name: 'serviceSource'
//				                            
//				                        }
//				                    ]
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'enterprise.name',
				                    width: 259,
				                    fieldLabel: '所属企业'
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
					            	xtype: 'displayfield',
					            	width: 259,
					            	name:'costPrice',
					              	fieldLabel: '服务价格',
					              	renderer:function(v){
				        				return v==0?"面议":v;
				        			}
					           	},
//				                {
//				                    xtype: 'displayfield',
//				                    width: 350,
//				                    name:'chargeMethod',
//				                    labelWidth:61,
//				                    fieldLabel: '收费模式'
//				                },
				                {
				                	xtype: 'displayfield',
				                    name:'picture',
				                    width: 259,
				                    fieldLabel: '服务图片'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 350,
				                    name:'category.text',				                    
				                    fieldLabel: '服务类别'
				                },
				                {
				                    xtype: 'displayfield',
				                    fieldLabel: '服务方式',
				                    width: 550,
				                    name:'serviceMethod'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 180,
				                    name:'linkMan',
				                    fieldLabel: '联系人'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'tel',
				                    width: 180,
				                    fieldLabel: '联系电话'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 180,
				                    name:'email',
				                    fieldLabel: '邮箱',
				                    vtype:'email'
				                },
				              
				                /*{
				                    xtype: 'htmleditor',
				                    height: 150,
//				                    disabled:true,
				                    width:550,
				                    name:'serviceProcedure',
				                    fieldLabel: '服务描述'
				                }*/
				                {
							    	xtype: 'textarea',
							        name: 'serviceProcedure',
							        id : 'serviceProcedure3',
							        height: 180,
							        anchor: '100%',
							        grow : true,
							        style: 'background-color: white;',
							        listeners :　{
							        	render : function (t, opts)　{
							        		setTimeout(function(){  
							                    var editor = KindEditor.create('#serviceProcedure3', {
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
							                    t.findParentByType('servicedetailwindow').setKindeditor(editor);
							                },1000); 
							        	}
							        }
							    }
					    	]
		            	}
            		]
//            		buttons:[
//				{
//					text:'取消',
//					action:'cancel'
//				}
//			]
        });

        me.callParent(arguments);
    }

});