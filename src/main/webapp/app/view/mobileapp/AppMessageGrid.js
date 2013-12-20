Ext.define('plat.view.mobileapp.AppMessageGrid', {
			extend : 'Ext.grid.Panel',
			xtype : 'appmessagegrid',
			id : 'xxts',
			title : '移动端推送消息列表',
			columnLines : true,
			rootVisible : false,
			height : 400,
			width : 300,
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			closeAction : 'hide',
			useArrows : true,
			tbar : [{
						iconCls : 'icon-add',
						text : '推送新消息',
						name : 'add'
					}, '-', {
						iconCls : 'icon-remove',
						text : '删除历史消息',
						name : 'remove'
					}],
			store : 'mobileapp.AppMessageStore',
			initComponent : function() {
				Ext.apply(this, {
					columns : [new Ext.grid.column.RowNumberer({
						text : '#',
						align : 'center',
						width : 30
					}), {
						text: '标题',  dataIndex:'title'
					}, {
						text : '内容', dataIndex : 'content' , flex : 1			
					}, {
						text : '时间', width: 150, dataIndex : 'time', renderer : function(value) {
							return unix_to_datetime(value);
						}
					}, {
						text : '状态', width: 80, dataIndex : 'flag', renderer : function(value) {
							switch (value) {//0成功,1失败,2发送中
								case 0:
									value = '成功';
									break;
								case 1:
									value = '失败';
									break;
								default:
									value = '发送中';
									break;
							}
							return value;
						}
					}, {
						text : '提示信息', width : 150, dataIndex : 'message'				
					}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'mobileapp.AppMessageStore',
						dock : 'bottom',
						displayInfo : true
					}]
				});
				this.callParent(arguments);
			}
		});
