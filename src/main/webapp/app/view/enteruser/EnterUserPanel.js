Ext.define('plat.view.enteruser.EnterUserPanel', {
    extend: 'Ext.panel.Panel',
    xtype:'enteruserpanel',
    id:'yhlbgl',
    height: 419,    
    width: 585,
    title:'用户列表管理',
    closable:true,
	closeAction:'hide',   
	bodyBorder: false,	
    layout: {
        type: 'border'
    },    
	defaults: {	    
	    split: true
	    //bodyPadding: 15
},
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'enterusergrid',
                    title: '主账号列表',
                    region: 'center'            
                },
                {
                    xtype: 'enterstaffgrid',
//                    title: '子账号列表',
                    region: 'south',
                    collapsible: true,
                    height: 300                   
                }
            ]
        });
        me.callParent(arguments);
    }

});