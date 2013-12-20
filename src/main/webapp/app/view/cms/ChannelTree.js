Ext.define('plat.view.cms.ChannelTree',{
	extend: 'Ext.tree.Panel',
	alias: 'widget.channelTree',
	title: '频道列表',
    width: 280,
    region: 'west',
	split:true,
	collapsible: true,
	columnLines:true,
	multiSelect: false,
    singleExpand: false,
    useArrows: true,
    rootVisible: false,
    store: 'cms.ChannelStore',
    tbar :[{
    	iconCls:'icon-add',
    	text:'添加根类',
    	action:'addRootChannel'
    }, {
    	iconCls:'icon-edit',
    	text:'修改',
    	action:'modfiyChannel'
    }, {
    	iconCls:'icon-remove',
    	text:'删除',
    	action:'deleteChannel'
    }],
    initComponent: function() {
        this.callParent(arguments);
    },
    columns: [
        { text: '频道ID', align:'center', dataIndex: 'id', hidden:true},
        { text: '频道编码', align:'center', dataIndex: 'ccode', hidden:true},
        { text: '是否为频道', align:'center', dataIndex: 'isChannel', hidden:true},
        { text: '模块个数', align:'center', dataIndex: 'mnumber', hidden:true},
        { text: '链接方式', align:'center', dataIndex: 'linktype', hidden:true},
        { text: '连接地址', align:'center', dataIndex: 'chttp', hidden:true},
        { text: '频道位置', align:'center', dataIndex: 'cindex', hidden:true},
        { text: '频道名称',flex: 1, xtype: 'treecolumn', menuDisabled: true, sortable: false, dataIndex: 'cname'},
        {
            xtype:'actioncolumn',
            name: 'ascending',
            align:'center',
            width: 24,
            cls: 'tasks-icon-column-header tasks-ascending-column-header',
            tooltip: '上移',
            icon: '../../jsLib/extjs/resources/themes/icons/ascending.png',
          	iconCls: '',
            emptyCellText: '',
            menuDisabled: true,
            sortable: false,
            getClass: function(v, metadata, record, colIndex, store){
            	if(!record.data.isChannel){
            		return 'x-hidden';
            	}
            	if(record.isFirst()){
            		return 'x-hidden';
            	}
            }
        },{
            xtype:'actioncolumn',
            name: 'descending',
            width: 24,
            cls: 'tasks-icon-column-header tasks-descending-column-header',
            tooltip: '下移',
            icon: '../../jsLib/extjs/resources/themes/icons/descending.png',
            iconCls: '',
            emptyCellText: '',
            menuDisabled: true,
            sortable: false,
            getClass: function(v, metadata, record, colIndex, store){
            	if(!record.data.isChannel){
            		return 'x-hidden';
            	}
            	if(record.isLast()){
            		return 'x-hidden';
            	}
            }
        }
	]
});



