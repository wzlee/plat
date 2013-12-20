Ext.define('plat.view.system.CategoryGrid',{
	extend:'Ext.tree.Panel',
	alias:'widget.categorygrid',
    
	requires: [
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.tree.*',
        'Ext.ux.CheckColumn'
    ],
	
    id:'sjlbgl',
    closable : true,
    useArrows: true,
    rootVisible: false,
  	store : 'system.CategoryStore',
    tbar :['-',
				{iconCls:'icon-add',text:'添加根类',action:'add'},'-',
				{iconCls:'icon-refresh-all',text:'刷新',action:'refresh'},'-','->'
	],
    initComponent: function() {
        this.width = 600;
        Ext.apply(this, {
            columns: [{
                xtype: 'treecolumn', //this is so we know which column will show the tree
                text: '类别名称',
                flex: 2,
                sortable: true,
                dataIndex: 'text'
            },{
                text: '所属实体类',
                flex: 1,
                dataIndex: 'clazz',
                sortable: true
            },{
                text: '编码',
                flex: 1,
                dataIndex: 'code',
                sortable: true
            }, {
            	text: '添加',
               	width: 55,
                menuDisabled: true,
                xtype: 'actioncolumn',
                tooltip: '添加子类别',
                align: 'center',
                icon: 'resources/images/add.png',
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                    return record.data.leaf;
                },
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                	openCategoryWindow('添加子类别', record);
                }
            }, {
                text: '编辑',
                width: 55,
                menuDisabled: true,
                xtype: 'actioncolumn',
                tooltip: '编辑节点',
                align: 'center',
                icon: 'resources/images/edit.png',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                    openCategoryWindow('修改类别', record);
                }
            },{
            	text: '删除',
               	width: 55,
                menuDisabled: true,
                xtype: 'actioncolumn',
                tooltip: '删除节点',
                align: 'center',
                icon: 'resources/images/delete.png',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                	record.collapse();
                    Ext.MessageBox.confirm('警告','确定删除该类别吗?同时将删除该类别下的子类！',function(btn){
			    		if(btn == 'yes'){
			    			record.remove();
			    		}
			    	});
                }
            }]
        });
        this.callParent();
    }
});

/**
 * 打开类别添加或修改窗口
 * @param {} title
 * @param {} record
 */
function openCategoryWindow(title, record){
	var categoryWindow = null;
	var windows = Ext.ComponentQuery.query('categoryWindow');
	if(windows.length > 0 ){
		categoryWindow = windows[0];
		categoryWindow.setTitle(title);
		categoryWindow.show();
	} else {
		categoryWindow = Ext.widget('categoryWindow',{title:title}).show();
	}
	var form = categoryWindow.getComponent('categoryform').form;
	form.reset();
	if(title.indexOf('添加') > -1){ // 添加
		form.findField('pid').setValue(record.data.id);
		form.findField('pname').setValue(record.data.text);
		//设置 code编码
		makeCode(record, form);
	}else{ // 修改
		form.findField('pname').setValue(record.parentNode.data.text);
		form.loadRecord(record);
	}
}