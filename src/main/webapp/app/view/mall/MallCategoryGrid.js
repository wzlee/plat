Ext.define('plat.view.mall.MallCategoryGrid', {
			extend : 'Ext.tree.Panel',
			alias : 'widget.mallcategorygrid',
			title : '商城类别',
			id : 'mallcategorygrid',
			closeAction : 'hide',
			rootVisible : false,
    		useArrows: true,
			store : 'mall.MallCategoryStore',
			selModel : new Ext.selection.CheckboxModel(),
			tbar : [{
						text:'删除所选类别',
						action:'delMallCategory'
			
					},{
						xtype: 'combo',
	                    name:'isHide',
	                    allowBlank:false,
	                    width: 200,
	                    store:  [['','全部'],['0', '显示'], ['1', '隐藏']],
	                    rootVisible:false,
	                    editable:false,
	                    emptyText:'请选择显示状态...',
	                    fieldLabel: '显示状态',
	                    labelWidth:60
                    }],
			columns : [
					{
						header : '类别名称',
						xtype : 'treecolumn',
						dataIndex : 'text',
						flex : 1.2
					}, {
						header : '类别编码',
						align : 'center',
						dataIndex : 'code',
						width:70
					},{
						header : '显示顺序',
						align : 'center',
						dataIndex : 'sort',
						width:70
					},{
						header : '显示状态',
						align : 'center',
						dataIndex : 'hide',
						width:70,
						renderer:function(v){
							if(v){
								return '隐藏'
							}else{
							return '显示'
							}
						}
					},{
						text : '添加子类别',
						width : 85,
						menuDisabled : true,
						xtype : 'actioncolumn',
						tooltip : '添加商城类别',
						align : 'center',
						icon : 'resources/images/add.png',
						isDisabled:function(grid, rowIndex, colIndex){
						 	if(grid.getStore().getAt(rowIndex).data.code.length > 4){
						 		return true
						 	}
						 },
						handler : function(grid, rowIndex, colIndex,
								actionItem, event, record, row) {
							openMallCategoryWindow('添加商城类别', record);
						}
					}, {
						text : '添加服务',
						width : 70,
						menuDisabled : true,
						xtype : 'actioncolumn',
						tooltip : '添加服务',
						align : 'center',
						icon : 'resources/images/add.png',
						isDisabled:function(grid, rowIndex, colIndex){
						 	if(grid.getStore().getAt(rowIndex).data.code.length < 6){
						 		return true
						 	}
						 },
						handler : function(grid, rowIndex, colIndex,
								actionItem, event, record, row) {
							openAddServiceWindow('添加服务', record);
						}
					}, {
						text : '编辑',
						width : 40,
						menuDisabled : true,
						xtype : 'actioncolumn',
						tooltip : '编辑节点',
						align : 'center',
						icon : 'resources/images/edit.png',
						handler : function(grid, rowIndex, colIndex,
								actionItem, event, record, row) {
							openMallCategoryWindow('修改类别', record);
						},
						isDisabled:function(grid, rowIndex, colIndex){
						 	if(grid.getStore().getAt(rowIndex).data.pid==0){
						 		return true
						 	}
						 }
					}]
		});

/**
 * 打开商城类别添加或修改窗口
 * 
 * @param {}
 *            title
 * @param {}
 *            record
 */
function openMallCategoryWindow(title, record) {
	var categoryWindow = null;
	var windows = Ext.ComponentQuery.query('mallcategorywindow');
	if (windows.length > 0) {
		categoryWindow = windows[0];
		categoryWindow.setTitle(title);
		categoryWindow.show();
	} else {
		categoryWindow = Ext.widget('mallcategorywindow', {
					title : title
				}).show();
	}
	var form = categoryWindow.getComponent('mallcategoryform').form;
	form.reset();
	if (title.indexOf('添加') > -1) { // 添加
		form.findField('pid').setValue(record.data.id);
		form.findField('pname').setValue(record.data.text);
		// 设置 code编码
		makeCode(record, form);
	} else { // 修改
		form.findField('pname').setValue(record.parentNode.data.text);
		form.loadRecord(record);
	}
}
function openAddServiceWindow(title, record) {
	var addservicewindow = null;
	var windows = Ext.ComponentQuery.query('malladdservicewindow');
	if (windows.length > 0) {
		addservicewindow = windows[0];
		addservicewindow.setTitle(title);
		addservicewindow.show();
	} else {
		addservicewindow = Ext.widget('malladdservicewindow', {
					title : title
				}).show();
	}
	var grid = addservicewindow.getComponent('malladdservicegrid');
	grid.down('triggerfield[name=mallId]').setValue(record.data.id);

}
