Ext.define('plat.view.info.InfoCategoryGrid', {
    extend : 'Ext.grid.Panel',
    xtype : 'infocategorygrid',
    title : '消息类别',
    id : 'infocategorygrid',
    closeAction : 'hide',
    rootVisible : false,
    useArrows: true,
    closable : true,
    columnLines:true,
    store : 'info.InfoCategoryStore',
//            selModel : new Ext.selection.CheckboxModel(),
    tbar : [{
        text : '新增',
        iconCls : 'icon-add',
        name : 'add'
    }],
    columns : [
        {header : '类别名称', dataIndex : 'messageType', width : 250,align : 'center'},
        {header : '类别描述', dataIndex : 'remark', flex : 1, align : 'center'},
//                {header : '添加时间', dataIndex : 'time', width : 140, align : 'center'},
        {       text : '编辑',
                width : 40,
                menuDisabled : true,
                xtype : 'actioncolumn',
                tooltip : '编辑消息类别',
                align : 'center',
                icon : 'resources/images/edit.png',
                handler : function(grid, rowIndex, colIndex,actionItem, event, record, row) {
                    //编辑信息
                    var _window = Ext.ComponentQuery.query('infocategorywin')[0];
                    if (!_window) {
                        _window = Ext.create('plat.view.info.InfoCategoryWin');
                    }
                    _window.show();
                    _window.setTitle('修改类别');
//                            var map = {0 : '政策指引', 1 : '资金资助'};
                    var _form = _window.down('infocategoryform');
                    _form.loadRecord(record);
//                            _form.getForm().findField('display_type').setValue(map[record.data.type]).show();
                },
                isDisabled:function(view, rowIdx, colIdx, item, record){
                    return false;
                 }
            }, {
                text : '删除',
                width : 40,
                menuDisabled : true,
                xtype : 'actioncolumn',
                tooltip : '删除类别',
                align : 'center',
                icon : 'resources/images/delete.png',
                handler : function(grid, rowIndex, colIndex,actionItem, event, record, row) {
                    //删除信息
                    Ext.Ajax.request({
                    	type:'POST',
					    url: 'info/deleteCategory',
					    params: {
					        id: record.data.id
					    },
					    success: function(response){
					        var text = Ext.decode(response.responseText);
					        Ext.example.msg('提示', text.message);
					        if(text.success){
								grid.getStore().remove(record);
								if(Ext.data.StoreManager.lookup('categorystore').data.length!=0){
									Ext.data.StoreManager.lookup('categorystore').load();
								}
					        }
					    },
					    failure: function(response, opts) {
					        Ext.Msg.alert('出错', '服务端错误:状态' + response.status);
					    }
                	});
                }/*,
                isDisabled : function(view, rowIdx, colIdx, item, record){
                	if (record.data.children.length > 0) {
                		return true;
                	}
                }*/
            }
    ],
	dockedItems :[{
	    xtype: 'pagingtoolbar',
	    store:'info.InfoCategoryStore',
	    dock: 'bottom',
	    displayInfo: true
	}]
});
