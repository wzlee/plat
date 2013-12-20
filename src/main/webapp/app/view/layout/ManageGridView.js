Ext.define('plat.view.layout.ManageGridView',{
	extend:'Ext.grid.Panel',
	alias:'widget.managegrid',
	
	margins:1,
	columnLines:true,
	emptyText:'无数据或者数据加载失败！',
	titleCollapse:true,
	initComponent:function(){
    	this.tbar = [
	    				{xtype:'textfield',width:100,name:'text',emptyText:'输入名称...'},'-',
    					{iconCls:'icon-search',action:'search'},'-',
	    				{iconCls:'icon-add',action:'insert'},'-',
	    				{iconCls:'icon-delete',action:'delete',disabled:true},'-',
	    				{iconCls:'icon-sync',action:'sync'},'-','->','-',
	    				{iconCls:'icon-export',xtype:'exporterbutton'},'-'
	    	
	    ];
	    
	    Ext.Ajax.request({
    			url:'system/loadcolumns',
    			params:{clazz:this.getId().split('_')[0]},
    			success:function(response){
    				var columns = Ext.JSON.decode(response.responseText);
    				var fields = [];
    				Ext.each(columns,function(column){
    					fields.push(column.dataIndex);
    				});
    				Ext.define('plat.model.ManageModel',{extend: 'Ext.data.Model',fields:fields});
    				this.store = Ext.create('Ext.data.Store',{storeId:record.data.id,autoSync:false,model:'plat.model.ManageModel',sorters: [{property: 'id',direction: 'desc'}]});
    				this.selModel = Ext.create('Ext.selection.CellModel', {
						listeners: {
				            selectionchange: function(sm, selections) {
	//							this.getManageGrid().down('#removeButton').setDisabled(selections.length == 0);
				            }
				            
				        }
				    });
				    this.plugins = [ Ext.create('Ext.grid.plugin.CellEditing', {pluginId:'rowEditing',clicksToEdit: 2})];
			    	contenttab.add(Ext.create('plat.view.layout.ModulePanelView',{
			    		id:record.data.id,
			    		title:record.data.text,
			    		layout:'fit',
			    		items:[{
		    					xtype:record.data.id.split('_')[1],
		    					id:moduleid+"/"+record.data.id.split('_')[0],
		    					selModel :cellSelectionModel,
		    					columns: Ext.Array.insert(columns,0,[Ext.create("Ext.grid.RowNumberer",{})]),
		    					store:store,
		    					plugins: [cellEditing],
							    dockedItems: [{
							        xtype: 'pagingtoolbar',
							        store: store,
							        dock: 'bottom',
							        displayInfo: true
							    }]
		    				}]
			    		})).show();
    			},
    			failure:function(response){
    				Ext.example.msg("提示:","加载columns失败!");
    			}
    		});
	    
	    this.callParent(arguments);
    }
});