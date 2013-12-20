Ext.define('plat.view.mall.MallServiceAdGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.mallserviceadgrid',
	xtype:  'mallserviceadgrid',
	
	id:'fwggpzgl',
	title : '服务广告',
	closable:true,
    closeAction:'hide',
	store : 'mall.MallAdStore',
	selModel : new Ext.selection.CheckboxModel(),
	tbar : [{
				text : '+添加服务广告',
				action : 'addServiceAd'
			},'-',{
				text : '删除所选服务广告',
				action : 'deleteServiceAd'
			},'-', {
				xtype : 'triggerfield',
				width : 250,
				name : 'advNo',
				fieldLabel : '服务广告编码',
				emptyText : '输入编码关键字...',
				labelWidth : 100,
				labelStyle : 'font-size:13',
				triggerCls : Ext.baseCSSPrefix + 'form-clear-trigger',
				onTriggerClick : function() {
					this.reset();
				}
			}, '-', {
				iconCls : 'icon-search',
				text : '查找',
				action : 'search'
			}

	],
	columns : [
			{xtype:'rownumberer',text:'序号',align:'center',width:80},
			{
				text : '',
				align : 'center',
				dataIndex : 'id',
				hidden : true
			}, {
				text : '服务广告编码',
				align : 'center',
				width : 180,
				dataIndex : 'advertisementNo'
			}, {
				text : '所属频道',
				align : 'center',
				width : 180,
				dataIndex : 'channel.cname'
			},{
				text : '服务名称',
				align : 'center',
				width : 180,
				dataIndex : 'service.serviceName'
			}, {
				text : '所属类别',
				align : 'center',
				width : 180,
				dataIndex : 'mallCategory.text'
			},{
	       		header : '图片',
	       		dataIndex : 'uploadImage',
	       		width : 50,
	       		toolTip : '55',
	       		align : 'center',
	       		renderer : function (value) {				       			
	       			if (value) {
	       				if(value.indexOf('http') > -1){
	       					return "<a href='" + value + "' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
	       				}else {
	       					return "<a href='upload/" + value + "' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
	       				}		       			
	       			} else {
	       				return "<a href='resources/images/nopic.gif' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
	       			}
	       		}
		    }, {
				text : '所属位置',
				align : 'center',
				width : 200,
				dataIndex : 'position',
				renderer : function(v) {
					return PlatMap.Mall.adposition[v];
				}
			}, {
				text : '顺序',
				align : 'center',
				width : 100,
				dataIndex : 'sort'
			}, {
				text : '添加时间',
				align : 'center',
				width : 200,
				dataIndex : 'createTime'

			},
				/*{
				text : '查看',
				width : 80,
				name:'viewRecomServce',
				menuDisabled : true,
				xtype : 'actioncolumn',
				tooltip : '查看推荐服务详细信息',
				align : 'center',
				iconCls : 'icon-view',
				handler : function(grid, rowIndex, colIndex) {
					var record = grid.getStore().getAt(rowIndex);
					openServiceAdInfoWindow('推荐服务详细信息',record);
				}
			},*/
			{
				text : '删除',
				width : 80,
				name:'deleteServiceAd',
				menuDisabled : true,
				xtype : 'actioncolumn',
				tooltip : '删除推荐服务',
				align : 'center',
				icon : 'resources/images/delete.png',
				handler : function(grid, rowIndex, colIndex) {
					var record = grid.getStore().getAt(rowIndex);
					Ext.MessageBox.confirm('警告', '删除后数据无法恢复,确定删除【 '+ record.data.advertisementNo + ' 】吗?',
						function(btn) {
							if (btn == 'yes') {
								grid.getStore().destroy({
									params : record.data,
										callback:function(record,operation){
		    						var result =Ext.JSON.decode(operation.response.responseText);								    						
		    						if(result.success){
			    						grid.getStore().on('beforeload',function(store) {
											Ext.apply(
												store.proxy.extraParams,
												{
													position : '1,4',
													advNo : ''
												});
											});
										grid.getStore().load();
		    						}else {
		    							Ext.Msg.alert('提示','服务【'+operation.params.serviceName+'】申请下架失败');
		    						}
				    			}
							});
						}
					});
				}
			}],
			dockedItems :[{
			        xtype: 'pagingtoolbar',
			        store: 'mall.MallAdStore',
			        dock: 'bottom',
			        displayInfo: true
			    }]
});
function openServiceAdInfoWindow(title, record) {
	var serviceadinfowindow = null;
	var windows = Ext.ComponentQuery.query('mallserviceadinfowindow');
	if (windows.length > 0) {
		serviceadinfowindow = windows[0];
		serviceadinfowindow.setTitle(title);
		serviceadinfowindow.show();
	} else {
		serviceadinfowindow = Ext.widget('serviceadinfoform', {
					title : title
				}).show();
	}
	var infoform = serviceadinfowindow.getComponent('serviceadinfoform').form;
	console.log(infoform);
	infoform.loadRecord(record);

}