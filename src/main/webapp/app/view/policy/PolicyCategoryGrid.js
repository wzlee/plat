Ext.define('plat.view.policy.PolicyCategoryGrid', {
            extend : 'Ext.tree.Panel',
            xtype : 'policycategorygrid',
            title : '政策法规类别管理',
            id : 'zczylbgl',
            closeAction : 'hide',
            rootVisible : false,
            useArrows: true,
            closable : true,
            store : 'policy.PolicyCategoryStore1',
//            selModel : new Ext.selection.CheckboxModel(),
            tbar : [{
                text : '新增',
                iconCls : 'icon-add',
                name : 'add'
            }/*, '-', {
                text : '修改',
                iconCls : 'icon-edit',
                name : 'modify'
            }, '-', {
                text : '删除',
                iconCls : 'icon-remove',
                name : 'delete'
            }*/],
            columns : [
               /* new Ext.grid.column.RowNumberer({
                            text : '#',
                            align : 'center',
                            width : 30
                    }),*/
                {xtype : 'treecolumn', header : '类别名称', dataIndex : 'text', width : 250},
//                {header : '分类范畴', dataIndex : 'type', flex : 1, align : 'center',
//                	renderer:function(value){
//                		if(value == 0){
//                			value = '政策法规';
//                		}else if(value == 1){
//                			value = '专项资金';
//                		}
//                		return value;
//                	}
//                },
                {header : '类别描述', dataIndex : 'description', flex : 1, align : 'center'},
                {header : '添加时间', dataIndex : 'time', width : 140, align : 'center'},
                {
                        text : '添加子类别',
                        width : 85,
                        menuDisabled : true,
                        xtype : 'actioncolumn', 
                        tooltip : '添加商城类别',
                        align : 'center',
                        icon : 'resources/images/add.png',
                        isDisabled:function(view, rowIdx, colIdx, item, record){
                            if((record.data.type == 0) && (record.data.leaf == true)){
                                return true;
                            }
                         },
                        handler : function(grid, rowIndex, colIndex,
                                actionItem, event, record, row) {
                            //弹出窗口，加载pid,添加子类别
                            var _window = Ext.ComponentQuery.query('policycategorywindow')[0];
                            if (!_window) {
                                _window = Ext.create('plat.view.policy.PolicyCategoryWin');
                            }
                            _window.show();
                            _window.setTitle('添加类别');
                            var _form = _window.down('policycategoryform');
                            var map = {0 : '政策指引', 1 : '资金资助'};
                            _form.getForm().reset();
                            _form.getForm().findField('pid').setValue(record.data.id);
                            _form.getForm().findField('type').setValue(record.data.type).hide();
//                            _form.getForm().findField('display_type')
//                            	.setValue(map[record.data.type]).show();
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
                            //编辑信息
                            var _window = Ext.ComponentQuery.query('policycategorywindow')[0];
                            if (!_window) {
                                _window = Ext.create('plat.view.policy.PolicyCategoryWin');
                            }
                            _window.show();
                            _window.setTitle('修改类别');
                            var map = {0 : '政策指引', 1 : '资金资助'};
                            var _form = _window.down('policycategoryform');
                            _form.loadRecord(record).findField('type').hide();
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
                        handler : function(grid, rowIndex, colIndex,
                                actionItem, event, record, row) {
                            //删除信息
                            Ext.Ajax.request({
							    url: 'policy/deleteCategory',
							    params: {
							        id: record.data.id
							    },
							    success: function(response){
							        var text = Ext.decode(response.responseText);
							        Ext.example.msg('提示', text.message);
//									grid.getStore().remove(record);
							        record.remove();
							    },
							    failure: function(response, opts) {
							        Ext.Msg.alert('出错', '服务端错误:状态' + response.status);
							    }
                        	});
                        },
                        isDisabled : function(view, rowIdx, colIdx, item, record){
                        	if (record.data.children.length > 0) {
                        		return true;
                        	}
                        }
                    }
            ]
        });
