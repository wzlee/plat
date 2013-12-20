Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath({
	'Ext.ux':'jsLib/extjs/ux'
});
Ext.require([
				'Ext.ux.TabCloseMenu',
				'Ext.ux.MD5',
//				'Ext.ux.CheckColumn',
				'Ext.ux.grid.FiltersFeature',
//				'Ext.ux.RowExpander',
				'Ext.ux.GridComboBox',
				'Ext.ux.GridComboBoxList',
				'Ext.ux.DataView.DragSelector',
				'Ext.ux.UploadPanel',
				'Ext.ux.TreePicker',
				'Ext.ux.ComboBoxTree'
			]);
Ext.application({
	appFolder : 'app',
	name : 'plat',
	launch : function () {
		Ext.create('Ext.window.Window', {
			title:'combotree test',
			layout:'fit',
			width:600,
			height:500,
			items : [
						{
							xtype:'form',
			            	id:'serviceform',
			            	layout: {
						        type: 'column'
						    },
						    defaults:{
						        labelWidth:60,
						        labelAlign:'right',
						        margin:'2'
						    },
						    bodyPadding:'10',
						    items:[
						{
			                xtype: 'comboboxtree',
			                name:'cid',
			                store: new Ext.data.TreeStore({
			    				fields:['id','text','leaf'],
				                root: {
			                			text:'所有类别',
			                			id:'ROOT',
			                			expanded: true,
			                			children:[
			                				{
										        text: "用户数据管理",
										        expanded: false,
										        children: [
										            { text: "用户账号管理",id:'yhzhgl|passportgrid', leaf: true},
										            { text: "用户权限管理",id:'yhqxgl|authoritypanel', leaf: true},
										            { text: "用户数据管理",id:'yhsjgl|customerdatagrid', leaf: true},
										            { text: "服务数据统计",id:'fwsjtj|servicedatagrid', leaf: true},
										            { text: "用户操作日志查询",id:'yhczrzcx|operateloggrid', leaf: true}
										        ]
										    },
										    {
										        text: "服务数据管理",
										        expanded: false,
										        children: [
										            { text: "新增服务管理",id:'xzfwgl|servicepanel', leaf: true },
										            { text: "已上架服务管理",id:'ysjfwgl|servicegrid', leaf: true},
										            { text: "已下架服务管理",id:'yxjfwgl|servicegrid', leaf: true},
										            { text: "已删除服务管理",id:'yscfwgl|servicegrid', leaf: true},
										            { text: "服务数据统计查询",id:'fwsjtjcx|statisticspanel', leaf: true}
										        ]
										    },
										    {
										        text: "系统数据管理",
										        expanded: false,
										        children: [
										            { text: "行业分类管理",id:'hyflgl|industrygrid', leaf: true},
										            { text: "行政区码管理",id:'xzqmgl|areacodegrid', leaf: true},
										            { text: "通用类别管理",id:'tylbgl|categorygrid', leaf: true},
										            { text: "网站模板管理",id:'wzmbgl|templategrid', leaf: true}
										        ]
										    }
			                			]
			                		}
				            }),
				            displayField:'text',
				            columns:[{text:'类别编码',dataIndex:'code'},{text:'类别名',dataIndex:'text'}],
			                width: 550,
			                fieldLabel: '服务类别'
	            	}]
            	}
            ]
		}).show();
	}
});