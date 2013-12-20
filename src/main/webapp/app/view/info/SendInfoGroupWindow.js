Ext.define('plat.view.info.SendInfoGroupWindow', {
    extend: 'Ext.window.Window',
	xtype:'sendinfogroupwindow',
	id : 'sendinfogroupwindow',
	
    width: 350,
    autoHeight:true,
    modal:true,
	layout:'fit',
	buttonAlign:'center',
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
	            {	
	            	xtype:'form',
	            	id:'sendinfogroupwindowform',
	            	fieldDefaults: {
				        labelAlign: 'right',
				        msgTarget: 'side'
  				    },
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
		                		xtype: 'combobox',
		                		id:'receviercombobox',
		                		width: 280,
							    fieldLabel: '收信类型：',
							    queryMode: 'local',
							    displayField: 'name',
                				afterLabelTextTpl: required,
							    valueField: 'usertype',
							    editable:false,
							    store:Ext.create('Ext.data.Store', {
    								fields: ['usertype', 'name'],
    								data : [
								        {"usertype":"1", "name":"全部用户"},
								        {"usertype":"2", "name":"企业用户"},
								        {"usertype":"3", "name":"机构用户"},
								        {"usertype":"4", "name":"个人用户"}
								    ]
								}),
								validator:function(){
									if(this.getValue()!=""&&null!=this.getValue()){
										return true;
									}else{
										return "该项为必填项，请选择消息类别！";
									}
								}
							},
			                {
		                		xtype: 'combobox',
		                		id:'categorycombobox',
		                		width: 280,
							    fieldLabel: '消息类别：',
							    queryMode: 'local',
							    displayField: 'messageType',
                				afterLabelTextTpl: required,
							    valueField: 'id',
//							    editable:false,
						/*	    store:  new Ext.data.Store({
							        fields: ['messageType','id','remark'],
							        autoLoad: true,
									proxy: {
								        type: 'ajax',
								        actionMethods: {  
        									read: 'POST'
        								},
								        url: 'info/queryCategory',
								        reader: {
								            type: 'json',
								            root: 'data',
								            successProperty: 'success'
								        }
								    }
							    }),*/
							     validator:function(){
			                    	if(this.getValue()!=""&&null!=this.getValue()){
			                    		if(this.getStore().find( 'id', this.getValue()) != -1){
			                    			return true;
			                    		}else{
			                    			return "请填写存在的消息类别！";
			                    		}
			                    	}else{
			                    		return "该项为必填项，请选择消息类别！";
			                    	}
			                    }
							},
							{
						        xtype     : 'textareafield',
						        grow      : true,
						        name      : 'content',
						        fieldLabel: '消息内容：',
						        anchor    : '100%',
						        afterLabelTextTpl: required,
						        validator:function(){
			                    	if(this.getValue()!=""&&null!=this.getValue()){
		                    			return true;
			                    	}else{
			                    		return "该项为必填项，请选择消息类别！";
			                    	}
			                    }
						    }
				    	]
	            	}
            ],
			buttons:[
				{
					text:'发送',
					action:'submit'
				},
				{
	                text: '返回',
	                scope: this,
	                handler: this.close
            	}
			]
        });
        me.callParent(arguments);
    }

});