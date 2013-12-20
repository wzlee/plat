Ext.define('plat.view.info.SendInfoWindow', {
    extend: 'Ext.window.Window',
	xtype:'sendinfowindow',
	id : 'sendinfowindow',
	
    width: 350,
    autoHeight:true,
	layout:'fit',
	buttonAlign:'center',
	modal:true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
	            {	
	            	xtype:'form',
	            	id:'sendinfowindowform',
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
/*			                {
		                		xtype: 'combobox',
		                		id:'receviercombobox',
		                		width: 280,
							    fieldLabel: '接收人：',
							    queryMode: 'local',
							    displayField: 'userName',
                				afterLabelTextTpl: required,
							    valueField: 'userName',
							    store:  new Ext.data.Store({
							        fields: ['userName'],
							        autoLoad: true,
									proxy: {
								        type: 'ajax',
								        actionMethods: {  
        									read: 'POST'
        								},
								        url: 'info/allUser',
								        reader: {
								            type: 'json',
								            root: 'data',
								            successProperty: 'success'
								        }
								    }
							    }),
							     validator:function(){
			                    	if(this.getValue()!=""&&null!=this.getValue()){
			                    		if(this.getStore().find( 'userName', this.getValue()) != -1){
			                    			return true;
			                    		}else{
			                    			return "请填写存在的收信人！";
			                    		}
			                    	}else{
			                    		return "该项为必填项，请选择消息类别！";
			                    	}
			                    }
							},*/
				    		{
			                    xtype: 'textfield',
			                    name:'username',
			                    fieldLabel: '收信人：',
			                    width: 300,
                				afterLabelTextTpl: required,
								validator:function(){
			                    	if(this.getValue().length>=2&&this.getValue().length<=20){
			                    		return true;
			                    	}else{
			                    		return "用户名最小长度不能少于2个字符且不能大于20个字符！";
			                    	}
			                    },
			                    //下面是设置屏蔽空格
								initEvents : function() {
									var keyPress = function(e){
										var blockchars = ' ';
										var c = e.getCharCode();
										if(blockchars.indexOf(String.fromCharCode(c)) != -1){
											e.stopEvent();
										}
									};
								this.el.on("keypress", keyPress, this);
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
/*							    store:  new Ext.data.Store({
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