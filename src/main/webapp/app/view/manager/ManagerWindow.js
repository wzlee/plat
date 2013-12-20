Ext.define('plat.view.manager.ManagerWindow', {
    extend: 'Ext.window.Window',
	xtype:'managerwindow',
	
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
	            	id:'managerform',
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
			                    xtype: 'textfield',
			                    name:'username',
			                    fieldLabel: '用户名',
			                    width: 300,
                				afterLabelTextTpl: required,
								validator:function(){
			                    	if(this.getValue().length>=2&&this.getValue().length<=20){
			                    		return true;
			                    	}else{
			                    		return "用户名最小长度不能少于2个字符且不能大于20个字符！";
			                    	}
			                    }
			                },
			                {
			                    xtype: 'textfield',
			                    name:'password',
                				afterLabelTextTpl: required,
			                    inputType :'password',
			                    width: 300,
			                    fieldLabel: '密码',
			                    submitValue:false,
								validator:function(){
			                    	if(this.getValue().length>=6&&this.getValue().length<=20){
			                    		return true;
			                    	}else{
			                    		return "用户密码最小长度不能少于6个字符且不能大于20个字符！";
			                    	}
			                    }
			                },
			                {
			                    xtype: 'textfield',
			                    name:'passwordvalidate',
                				afterLabelTextTpl: required,
			                    inputType :'password',
			                    width: 300,
			                    fieldLabel: '核对密码',
			                    submitValue:false,
			                    validator:function(){
			                    	if(null!=this.getValue()&&""!=this.getValue()&&this.getValue()==this.up().down('textfield[name=password]').getValue()){
			                    		return true;
			                    	}
			                    	if(this.getValue()!=this.up().down('textfield[name=password]').getValue()){
			                    		return "确认密码与密码不一致！";
			                    	}
			                    	if(null==this.getValue()||""==this.getValue())
			                    	{
			                    		return "确认密码不能为空！";
			                    	}
				                    }
			                },
			                {
		                		xtype: 'combobox',
		                		id:'pertainrole',
		                		width: 300,
							    fieldLabel: '所属角色',
							    queryMode: 'local',
							    displayField: 'rolename',
                				afterLabelTextTpl: required,
							    valueField: 'id',
							    editable:false,
							    store:  new Ext.data.Store({
							        fields: ['rolename','id','rights','createTime','roledesc'],
							        autoLoad: true,
									proxy: {
								        type: 'ajax',
								        actionMethods: {  
        									read: 'POST'
        								},
								        url: 'menu/queryrole',
								        reader: {
								            type: 'json',
								            root: 'data',
								            successProperty: 'success'
								        }
								    }
							    }),
							     validator:function(){
			                    	if(this.getValue()!=""&&null!=this.getValue()){
			                    		return true;
			                    	}else{
			                    		return "该项为必填项，请选择所属角色！";
			                    	}
			                    }
							},
							{
		                        xtype: 'textfield',
		                        name : 'remark',
		                        width: 300,
		                        fieldLabel: '备注'
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
	                text: '取消',
	                scope: this,
	                handler: this.close
            	}
			]
        });
        me.callParent(arguments);
    }

});