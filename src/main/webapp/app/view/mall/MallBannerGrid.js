Ext.define('plat.view.mall.MallBannerGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.mallbannergrid',
	
	title:'Banner',
	initComponent : function() {
		this.cellEditing = new Ext.grid.plugin.CellEditing({
					clicksToEdit : 1
				});
		Ext.apply(this, {
			plugins : [this.cellEditing],
			store : 'mall.MallBannerCategoryStore',
			columns : [{
						header : '商城名称',
						dataIndex : 'cname',
						flex : 1
					}, {
						header : '商城名称',
						dataIndex : 'mnumber',
						hidden : true
					}, {
						header : 'banner数量',
						dataIndex : 'mnumber',
						width : 130,
						editor : new Ext.form.field.ComboBox({
							typeAhead : true,
							editable : false,
							gridObj : this,
							store : [['0','0'],['1', '1'], ['2', '2'], ['3', '3'],
									['4', '4'], ['5', '5'], ['6', '6']],
							listeners : {
								select : function(combo, records) {
									Ext.MessageBox.confirm("提示信息",
											"banner数量已修改，是否保存", function(e) {
												if (e == 'yes') {
													var v = combo.getValue();
													combo.setValue(100);
													var record = combo.gridObj
															.getSelectionModel()
															.getSelection()[0];
													record.set('mnumber', v);
													combo.gridObj.getStore()
															.update({
																		params : record.data
																	});
												} else {

												}
											});
								}
							}
						})
					}],
			selModel : {
				selType : 'cellmodel'
			}
		});
		this.callParent();
	}
})