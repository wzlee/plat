Ext.define('plat.view.layout.SystemWindowView',{
	extend:'Ext.window.Window',
	alias : 'widget.systemwindow',
	
	title:'系统信息',
	layout:'fit',
//	frame:true,
	width:600,
	height:500,
	closable:true,
	bodyPadding:'10px',
	
	initComponent:function(){
		this.loader = {
                    	loadMask: {
	                        showMask: true,
	                        msg: "信息加载中..."
	                    },
	                    scripts: true,
	                    autoLoad: true,                    
	                    url: "help.html"
	                }
		this.tools = [
						{
						    type:'toggle',
						    tooltip: '隐藏面板',
						    handler: function(event, toolEl, panel){
						        // refresh logic
						    }
						},
						{
						    type:'help',
						    tooltip: '帮助和关于',
						    handler: function(event, toolEl, panel){
						        // show help here
						    }
						}
					]
		this.callParent(arguments);
	}
});