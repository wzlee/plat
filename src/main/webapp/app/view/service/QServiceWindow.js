Ext.define('plat.view.service.QServiceWindow', {
    extend: 'Ext.window.Window',
	xtype:'qservicewindow',
    width: 600,
    height: 510,
	layout:'fit',
	buttonAlign:'center',
	id:'qservicewindow',
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'serviceform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:80,
					        labelAlign:'right',
					        margin:'2'
					    },
					    bodyPadding:'10',
					    items:[
					   			{
				                    xtype: 'hiddenfield',
				                    name:'id'
				                },
					    	 	{
				                    xtype: 'displayfield',
				                    width: 259,
				                    fieldLabel: '服务编码',
				                    name:'serviceNo'
				                },
				                {
				                    xtype: 'radiogroup',
				                    width: 296,
				                    fieldLabel: '服务来源',     
				                    name:'serviceSource',
				                    items: [
				                        {
				                            xtype: 'radiofield',
				                            width: 45,
				                            boxLabel: '平台',
				                            inputValue: '1',
				                            name:'serviceSource'
				                                 
				                        },
				                        {
				                            xtype: 'radiofield',
				                            boxLabel: '服务机构',
				                            inputValue: '2',
				                          	name:'serviceSource',
				                            checked:true
				                        }
				                    ]
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'serviceOrg.id'
				                },
				                 {
				                    xtype: 'serviceorgcombo',
				                    name:'serviceOrg.orgName',
				                    width: 259,
				                    readOnly:true,
				                    fieldLabel: '服务机构',
				                    listeners:{
				                    	select:function(combo,records){
				                    		console.log(records);
				                    		this.ownerCt.down('hiddenfield[name=serviceOrg.id]').setValue(records[0].data.id);
								          	console.log(this.ownerCt.down('hiddenfield[name=serviceOrg.id]').getValue());          		
				                    	}
				                    }
				                },
				                {
				                    xtype: 'displayfield',
				                    fieldLabel: '服务名称',
				                    name:'serviceName'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'currentStatus',
				                    width: 259,
				                    fieldLabel: '当前状态'
				                   
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'lastStatus',
				                    width: 259,
				                    fieldLabel: '上一状态'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    fieldLabel: '注册时间',
				                    name:'registerTime'
				                },
				                {
				                    xtype: 'displayfield',
				                    fieldLabel: '服务次数',
				                    name:'serviceNum'
				                },
				                {
				                    xtype: 'hiddenfield',
				                    name:'category.id'
				                },
				                {
				                    xtype: 'comboboxtree',
				                    name:'category.text',
				                    store: new Ext.data.TreeStore({
			            				fields:['id','text','pid','leaf','idxtype'],
						                proxy: {
						                    type: 'ajax',
						                    url: 'category/load'
						                },
						                root: {
						                    text: '所有类别',
						                    id: '0',
						                    expanded: true
						                },
						                reader:{  
							      			type: 'json',
											root: '',
							        		messageProperty:"message"  
							      		}, 
						                folderSort: true,
						                nodeParam: 'pid',
						                sorters: [{
						                    property: 'id',
						                    direction: 'ASC'
						                }],
						                listeners : {  
							                exception : function(proxy, response, operation) {  
							                    Ext.MessageBox.show({  
							                        title : '服务器错误',  
							                        msg : operation.getError(),  
							                        icon : Ext.MessageBox.ERROR,  
							                        buttons : Ext.Msg.OK  
							                    });  
							                },  
							                beforeload : function(store, operation) {  
							                    Ext.apply(store.proxy.extraParams, {clazz:'service'});
							                }  
							            }  
						            }),
						            displayField:'text',
				                    width: 350,
				                    fieldLabel: '服务类别'
				                },
				                {
				                    //xtype: 'combobox',
				                	xtype: 'sobjectcombo',
				                    fieldLabel: '服务对象',
				                    readOnly:true,
				                    width:300,
				                    name:'serviceObject',
				                    multiSelect:true
				                },
				                {
				                    xtype: 'smethodcombo',
				                    readOnly:true,
				                    fieldLabel: '服务方式',
				                    width:300,
				                    multiSelect:true,
				                    name:'serviceMethod'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    fieldLabel: '联系人',
				                    name:'linkMan'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 180,
				                    fieldLabel: '电话',
				                    name:'tel'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    fieldLabel: '邮箱',
				                    vtype:'email',
				                    name:'email'
				                },
				                {
				                    xtype: 'displayfield',
				                    readOnly:true,
				                    width: 550,
				                    height:50,
				                    fieldLabel: '收费模式',
				                    name:'chargeMethod'
				                },
				                {
				                    xtype: 'htmleditor',
				                    readOnly:true,
				                    height: 150,
				                    width:550,
				                    fieldLabel: '服务描述',
				                    name:'serviceProcedure'
				                }
				                
					    	]
		            	}
            		]
        });

        me.callParent(arguments);
    }

});