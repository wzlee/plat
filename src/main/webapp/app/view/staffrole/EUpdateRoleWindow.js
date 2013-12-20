Ext.define('plat.view.staffrole.EUpdateRoleWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.eupdaterolewindow',
    id:'eupdaterolewindow',
	
    width: 350,
//    height: 190,
    autoHeight:true,
    title: '修改角色',
    layout: 'fit',
    autoShow: true,
    modal:true,
    buttonAlign:'center',

    initComponent: function() {
        this.items = [
            {
                xtype:'form',
	        	layout: {
			        type: 'column'
			    },
			    fieldDefaults: {
			        labelAlign: 'right',
			        msgTarget: 'qtip',
			        labelWidth: 80
  				},
			    defaults:{
			        labelWidth:120,
			        labelAlign:'right',
			        margin:'2'
			    },
			    bodyPadding:'10',
                items: [
                    {
                        xtype: 'textfield',
                        name : 'rolename',
                        width: 300,
                        fieldLabel: '角色名',
                        allowOnlyWhitespace:false,
                        allowBlank: false,
	                    blankText:'角色名不能为空!',
        				afterLabelTextTpl: required,
	                    maxLength : 20,//允许输入的最大字符数
						maxLengthText : "角色名最大长度不能超过20个字符！",//提示文本
						/*minLength : 3, //允许输入的最少字符数
						minLengthText : "角色名最小长度不能少于3个字符！",*/
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
                        xtype: 'hiddenfield',
                        name : 'id'
                    },
                    {
                        xtype: 'textfield',
                        name : 'roledesc',
                        width: 300,
                        fieldLabel: '角色描述'
                    },
                    {
	                    xtype: 'radiogroup',
	                    width: 200,
	                    fieldLabel: '是否允许申请服务',
	                    afterLabelTextTpl: required,
	                    allowBlank:false,
	                    items: [
	                        {
	                            xtype: 'radiofield',
	                            boxLabel: '是',
	                            inputValue: 1,
	                            columnWidth:50,
	                            name: 'checkeda'
	                        },
	                        {
	                            xtype: 'radiofield',
	                            boxLabel: '否',
	                            inputValue: 0,
	                            columnWidth:50,
	                            checked: true,
	                            name: 'checkeda'
	                        }
	                    ]
                    }
                ]
            }
        ];
        this.buttons = [
            {
	            text: '保存',
	            action: 'save'
            },
            {
	            text: '取消',
	            scope: this,
	            handler: this.close
            }
        ];
        this.callParent(arguments);
    }
});