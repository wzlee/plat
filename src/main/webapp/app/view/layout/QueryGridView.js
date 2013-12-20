Ext.define('plat.view.layout.QueryGridView',{
	extend:'Ext.grid.Panel',
	alias:'widget.querygrid',
	
	margins:1,
	columnLines:true,
	emptyText:'无数据或者数据加载失败！',
	titleCollapse:true,
	initComponent:function(){
    	this.tbar = [
	    				{xtype:'textfield',width:100,name:'text',emptyText:'输入查询关键字...'},'-',
	    				{xtype:'datefield',width:100,name:'begin',emptyText:'选择开始时间...'},'-',
	    				{xtype:'datefield',width:100,name:'end',emptyText:'选择结束时间...'},'-',
	    				{xtype:'combo',width:100,name:'category',emptyText:'选择类别'},'-',
    					{iconCls:'icon-search',action:'search'},'-',
	    				{iconCls:'icon-export',xtype:'exporterbutton'},'-'
	    	
	    ];
	    this.callParent(arguments);
    }
//    addColumn: function(field, column, colIndex){
//        if(!column){
//                if(field.dataIndex){
//                        column = field;
//                        field = field.dataIndex;
//                } else{
//                        column = field.name || field;
//                }
//        }
//        this.store.addField(field);
//        this.colModel.addColumn(column, colIndex);
//    },
//    removeColumn: function(name, colIndex){
//        this.store.removeField(name);
//        if(typeof colIndex != 'number'){
//                colIndex = this.colModel.findColumnIndex(name);
//        }
//        if(colIndex >= 0){
//                this.colModel.removeColumn(colIndex);
//        }
//    }
});