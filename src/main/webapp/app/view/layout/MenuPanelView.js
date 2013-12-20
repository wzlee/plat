/**
 * 左边菜单树
 */
Ext.define('plat.view.layout.MenuPanelView' ,{
    extend: 'Ext.panel.Panel',
    
    xtype: 'menupanel',
    
    title : '系统菜单',
    animate : Ext.isIE9?false:true,
    animCollapse : true,
    layout: 'accordion',
    width: 500,
    defaults: {
        bodyPadding: 10,
        titleAlign:'center',
        rootVisible: false,
        xtype:'treepanel'
    },
    initComponent:function(){
    	Ext.each(menu,function(menu){
        	switch(menu.pid){
				case 1000:
				  BASE_DATA_MODULE.push(menu);
				  break;
				case 1001:
				  OPERATION_DATA_MODULE.push(menu);
				  break;
			  	case 1002:
				  PLAT_CMS_MODULE.push(menu);
				  break;
				case 1003:
				  SYSTEM_DATA_MODULE.push(menu);
				  break;
				case 1004:
				  FLAT_DATA_MODULE.push(menu);
				  break;
			 	case 1005:
			  	  WX_DATA_MODULE.push(menu);
			  	  break;
				default:
					break;
			}
        });
    	Ext.apply(this, {
            items: [
                	{
		                title: '基础数据维护',
//		                store:Ext.createByAlias('menutree',{
//		                	defaultRootId:'1000'
//		                })
                		store: new Ext.data.TreeStore({
            				fields:['id','text','pid','leaf','idxtype'],
			                proxy: {
			                    type: 'ajax',
			                    url: 'menu/load'
			                },
			                root: {
			                    text: '所有类别',
			                    id: '1000',
			                    expanded: true,
			                    children:BASE_DATA_MODULE
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
			                }]
			            })
		            }, 
		            {
		                title: '运营数据管理',
                		store: new Ext.data.TreeStore({
                			fields:['id','text','pid','leaf','idxtype'],
//			                proxy: {
//			                    type: 'ajax',
//			                    url: 'menu/load'
//			                },
			                root: {
			                    text: '所有类别',
			                    id: '1001',
			                    expanded: true,
			                    children:OPERATION_DATA_MODULE
			                },
			                folderSort: true,
			                nodeParam: 'pid',
			                sorters: [{
			                    property: 'id',
			                    direction: 'ASC'
			                }]
			            }),
			            listeners:{
							expand:function( p, eOpts ){
								if(this.store.getRootNode().childNodes.length==0){
				            		this.store.setProxy({
					                    type: 'ajax',
					                    url: 'menu/load'
			                		});
			                		this.store.load();
								}
			            	}
			            }
		            }, 
	            	{
		                title: '平台CMS',
                		store: new Ext.data.TreeStore({
                			fields:['id','text','pid','leaf','idxtype'],
//			                proxy: {
//			                    type: 'ajax',
//			                    url: 'menu/load'
//			                },
			                root: {
			                    text: '所有类别',
			                    id: '1002',
			                    expanded: true,
			                    children:PLAT_CMS_MODULE
			                },
			                folderSort: true,
			                nodeParam: 'pid',
			                sorters: [{
			                    property: 'id',
			                    direction: 'ASC'
			                }]
			            }),
			            listeners:{
							expand:function( p, eOpts ){
			            		if(this.store.getRootNode().childNodes.length==0){
				            		this.store.setProxy({
					                    type: 'ajax',
					                    url: 'menu/load'
			                		});
			                		this.store.load();
								}
			            	}
			            }
		            }, 
	            	{
		                title: '系统数据管理',
                		store: new Ext.data.TreeStore({
                			fields:['id','text','pid','leaf','idxtype'],
//			                proxy: {
//			                    type: 'ajax',
//			                    url: 'menu/load'
//			                },
			                root: {
			                    text: '所有类别',
			                    id: '1003',
			                    expanded: true,
			                    children:SYSTEM_DATA_MODULE
			                },
			                folderSort: true,
			                nodeParam: 'pid',
			                sorters: [{
			                    property: 'id',
			                    direction: 'ASC'
			                }]
			            }),
			            listeners:{
							expand:function( p, eOpts ){
			            		if(this.store.getRootNode().childNodes.length==0){
				            		this.store.setProxy({
					                    type: 'ajax',
					                    url: 'menu/load'
			                		});
			                		this.store.load();
								}
			            	}
			            }
		            },
		            {
		                title: '窗口平台管理',
                		store: new Ext.data.TreeStore({
                			fields:['id','text','pid','leaf','idxtype'],
//			                proxy: {
//			                    type: 'ajax',
//			                    url: 'menu/load'
//			                },
			                root: {
			                    text: '所有类别',
			                    id: '1004',
			                    expanded: true,
			                    children:FLAT_DATA_MODULE
			                },
			                folderSort: true,
			                nodeParam: 'pid',
			                sorters: [{
			                    property: 'id',
			                    direction: 'ASC'
			                }]
			            }),
			            
			            listeners:{
							expand:function( p, eOpts ){
			            		if(this.store.getRootNode().childNodes.length==0){
				            		this.store.setProxy({
					                    type: 'ajax',
					                    url: 'menu/load'
			                		});
			                		this.store.load();
								}
			            	}
			            }
		            },
		            {
		                title: '微信平台',
                		store: new Ext.data.TreeStore({
                			fields:['id','text','pid','leaf','idxtype'],
//			                proxy: {
//			                    type: 'ajax',
//			                    url: 'menu/load'
//			                },
			                root: {
			                    text: '所有类别',
			                    id: '1005',
			                    expanded: true,
			                    children:WX_DATA_MODULE
			                },
			                folderSort: true,
			                nodeParam: 'pid',
			                sorters: [{
			                    property: 'id',
			                    direction: 'ASC'
			                }]
			            }),
			            
			            listeners:{
							expand:function( p, eOpts ){
			            		if(this.store.getRootNode().childNodes.length==0){
				            		this.store.setProxy({
					                    type: 'ajax',
					                    url: 'menu/load'
			                		});
			                		this.store.load();
								}
			            	}
			            }
		            }
            	],
            bbar:['->','-',
    				{	
		                xtype: 'systemmenu'
					},'-'
				]
    	});
	    this.callParent(arguments);
    }
});

