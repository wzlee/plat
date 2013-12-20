/**
 * 布局控制层
 */
Ext.define('plat.controller.MainController', {
    extend: 'Ext.app.Controller',
    alias:'widget.maincontroller',
    
    views: [
    		'layout.WelcomeView',
    		'layout.MenuPanelView',
    		'layout.ContentTabView',
    		'layout.SystemMenuView',
    		'layout.SystemWindowView',
    		'layout.PasswordWindowView'
    		],
    refs: [
	        {
	            ref: 'welcomepanel',
	            selector: 'welcomepanel'
	        },
	        {
	            ref: 'menupanel',
	            selector: 'menupanel'
	        },
	        {
	            ref: 'systemmenu',
	            selector: 'systemmenu'
	        },
	        {
	            ref: 'systemwindow',
	            selector: 'systemwindow'
	        },
	        {
	            ref: 'passwordwindow',
	            selector: 'passwordwindow'
	        },
        	{
	            ref: 'contenttab',
	            selector: 'contenttab'
	        }
    ],
    init: function() {
        this.control({
        	'welcomepanel':{
        		afterrender:function(formpanel){
        			this.showCurrentTime(formpanel.down('displayfield[name=currenttime]'));
//        			this.loadUserInfo(topform);
//        			console.log(BASE_DATA_MODULE);
//        			formpanel.loadData(manager);
        		}
        	},
            'menupanel > treepanel':{
            	afterrender:function(treepanel){
            		treepanel.on('itemclick',function(view,record,item,index){
//            			console.log(record.data.qtip);
						if(record.data.leaf){
//							this.loadController(record.raw.controller);
							this.addPanel(record.data.text,record.data.idxtype);
						}
					},this);
            	}
            },
            'contenttab':{
            	beforeadd:function(tab,panel,index){
//            		Ext.getBody().mask("界面渲染中...");
            		console.log(tab.title + '加入组件:'+ panel.title);
            	},
            	afterlayout:function(tab,layout){
//            		Ext.getBody().unmask();
            		console.log(tab.title + '布局完毕...');
            	},
            	remove:function(tab,panel){
            		console.log(tab.title + '移除组件:'+ panel.title);
            	}
            }
        });
    },
    showCurrentTime:function(ev,type){   
		var Y,M,D,W,H,I,S;   
		function fillZero(v){   
			if(v<10){v='0'+v;}   
			return v;   
		}
		(function(){   
		 	var d=new Date();   
			var Week=['星期天','星期一','星期二','星期三','星期四','星期五','星期六'];   
			Y=d.getFullYear();   
			M=fillZero(d.getMonth()+1);   
			D=fillZero(d.getDate());   
			W=Week[d.getDay()];   
			H=fillZero(d.getHours());   
			I=fillZero(d.getMinutes());   
			S=fillZero(d.getSeconds());   
			if(type && type==12){   
				if(H<=12){   
					H='上午 '+H;   
				}else if(H>12 && H<24){   
					H-=12;   
					H='下午 '+fillZero(H);   
				}else if(H==24){   
					H='下午 00';   
				}   
			}   
			ev.setValue(Y+'年'+M+'月'+D+'日 '+' '+W+' '+H+':'+I+':'+S);   
			setTimeout(arguments.callee,1000);   
		})();   
	},
    collapseAll:function(){
    	this.getMenutree().collapseAll();
    },
    expandAll:function(){
    	this.getMenutree().expandAll();
    },
    loadController:function(controller){
    	this.getController(controller);
    },
    addPanel:function(title,id_xtype){
    	var id = id_xtype.split('|')[0];
    	var xtype = id_xtype.split('|')[1];
    	var contenttab = this.getContenttab();
    	var component = contenttab.getComponent(id);
    	if(!component){
    		contenttab.getEl().mask("组件初始化中,请稍候...");
    		if(Ext.ClassManager.getNameByAlias('widget.'+xtype)){
	    		component = Ext.widget(xtype,{
	    			title:title,
	    			id:id
	    		});
	    		contenttab.add(component).show();
    		}else{
    			Ext.example.msg('','<p align="center">模块建设中,敬请期待!</p>');
    		}
    	}else{
    		contenttab.setActiveTab(component);
    	}
    	contenttab.getEl().unmask();
    }
});