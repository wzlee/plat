Ext.define('plat.view.mall.MallServiceGrid',{
	extend:'Ext.grid.Panel',
	alias:'widget.mallservicegrid',
	title:'服务显示',
	id:'mallservicegrid',
	width: 360,
    closeAction:'hide',
    store:'service.UServiceStore',
	columns: [
			     { text: '服务ID',align:'center', dataIndex: 'id',hidden:true},
		        { text: '服务名称',align:'center',width:120, dataIndex: 'serviceName'},				      
			    { text: '来源',align:'center',width:100, dataIndex: 'serviceSource',renderer:function(v){
			    	//服务来源 ：1服务机构本身上传  2运营管理人员代理录入 3 运营服务
		        	return PlatMap.Service.serviceSource[v];
			    }},
		        { text: '收费方式',align:'center',width:80, dataIndex: 'chargeMethod'},
			    {
					text : '删除',
					width : 100,
					name:'deRecomService',
					menuDisabled : true,
					xtype : 'actioncolumn',
					tooltip : '删除推荐服务',
					align : 'center',
					icon : 'resources/images/delete.png',
					handler : function(grid, rowIndex, colIndex) {
						var record = grid.getStore().getAt(rowIndex);
						Ext.MessageBox.confirm('警告', '确定要移除吗?',
							function(btn) {
								if (btn == 'yes') {
							    			Ext.Ajax.request({
											url: 'mall/mallRemoveService',
										    params: {
										        serviceIds: record.data.id+','
										    },
										    success: function(response){
										        var result = Ext.JSON.decode(response.responseText);
										        if(result.success){
										        	grid.getStore().on('beforeload',function(store) {
														Ext.apply(
													store.proxy.extraParams,
													{
														mallId : record.data.mallId,
														advNo : ''
													});});
													grid.getStore().load();
													
										        }else {
										        	Ext.Msg.alert('提示',result.message+",操作失败");
										        }												        
										    }
										});									    			
							   }
					});
			}
		}
   ]
});

