Ext.define('plat.view.layout.ManageTestView',{
	extend:'Ext.grid.Panel',
	alias:'widget.testgrid',
	
	margins:1,
	columnLines:true,
	emptyText:'无数据或者数据加载失败！',
	titleCollapse:true,
	initComponent:function(){
	    
	    Ext.Ajax.request({
    			url:'system/loadcolumns',
    			params:{clazz:'user'},
    			success:function(response){
    				var columns = Ext.JSON.decode(response.responseText);
    				this.columns = Ext.Array.insert(columns,0,[Ext.create("Ext.grid.RowNumberer",{})]);
//    				var fields = [];
//    				Ext.each(columns,function(column){
//    					fields.push(column.dataIndex);
//    				});
//    				Ext.define('plat.model.ManageModel',{extend: 'Ext.data.Model',fields:fields});
//    				this.store = Ext.create('Ext.data.Store',{storeId:'user_store',autoSync:false,model:'plat.model.ManageModel',sorters: [{property: 'id',direction: 'desc'}]});
    				this.selModel = Ext.create('Ext.selection.CellModel', {
										listeners: {
								            selectionchange: function(sm, selections) {
					//							this.getManageGrid().down('#removeButton').setDisabled(selections.length == 0);
								            }
								            
								        }
								    });
				    this.plugins = [Ext.create('Ext.grid.plugin.CellEditing', {pluginId:'rowEditing',clicksToEdit: 2})];
				    this.dockedItems = [{
									        xtype: 'pagingtoolbar',
									        store: store,
									        dock: 'bottom',
									        displayInfo: true
									    }]
    			},
    			failure:function(response){
    				Ext.example.msg("提示:","加载columns失败!");
    			}
    		});
	    
	    this.callParent(arguments);
    }
});