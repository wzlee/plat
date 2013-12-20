Ext.define('plat.view.info.DetailsWindow', {
    extend: 'Ext.window.Window',
	xtype:'detailswindow',
	id : 'detailswindow',
	
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
	            	id:'detailswindowform',
	            	/*fieldDefaults: {
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
				    bodyPadding:'10',*/
				    items:[
			                {
			                	xtype     : 'textareafield',
			                	disabled  : true,
        						grow      : true,
						        name      : 'content',
						        anchor    : '100%'
							}
				    	]
	            	}
            ]
        });
        me.callParent(arguments);
    }

});