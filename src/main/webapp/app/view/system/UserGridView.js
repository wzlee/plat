Ext.define('plat.view.system.UserGridView',{
	extend:'Ext.grid.Panel',
	alias:'widget.usergrid',
	
	title:'用户管理',
	id:'usergrid',
	titleCollapse:true,
	closable:true,
	closeAction:'hide',
	autoDestroy :false,
	margins:1,
	columnLines:true,
	emptyText:'无数据或者数据加载失败！',
	store:'system.UserStore',
	initComponent:function(){
    	this.tbar = ['-',
	    				{xtype:'textfield',width:100,name:'nickname',emptyText:'输入名称...'},'-',
    					{text:'查询',iconCls:'icon-search',action:'search'},'-',
	    				{text:'添加',iconCls:'icon-add',action:'add'},'-',
	    				{text:'删除',iconCls:'icon-delete',action:'delete',disabled:true},'-',
	    				{text:'同步',iconCls:'icon-sync',action:'sync'},'-','->','-',
	    				{text:'全部下线',action:'alloffline'},'-'
//	    				{iconCls:'icon-export',xtype:'exporterbutton'},'-'
	    ];
	    this.columns = [
	    				{text:'ID',dataIndex:'id',hidden:true,width:10,align:'center'},
	    				{text:'用户名',dataIndex:'username',width:100,align:'center',editor:{xtype:'textfield',allowBlank:false}},
	    				{text:'密码',dataIndex:'password',width:150,align:'center',editor:{xtype:'textfield',allowBlank:false}},
	    				{text:'qq',dataIndex:'qq',width:80,align:'center',editor:'textfield'},
	    				{text:'昵称',dataIndex:'realname',width:80,align:'center',editor:'textfield'},
	    				{text:'邮箱',dataIndex:'email',width:110,align:'center',editor:'textfield'},
	    				{text:'分组',dataIndex:'groupname',width:80,align:'center',editor:'groupcombo'},
	    				{text:'账号状态',dataIndex:'status',width:80,align:'center',editor:'userstatus'},
	    				{text:'注册时间',dataIndex:'registerdate',width:100,align:'center',flex:1},
	    				{text:'在线',dataIndex:'online',width:50,align:'center',renderer:function(v){return v?'<font color="green">在线</font>':'<font color="gray">离线</font>'}},
	    				{text:'登陆时间',dataIndex:'logindate',width:100,align:'center',flex:1}
						];
	    this.selModel = Ext.create('Ext.selection.CheckboxModel',{allowDeselect :true});
	    this.features = [{ftype:'grouping',groupByText:'按此列分组',showGroupsText :'显示分组',startCollapsed:true,groupHeaderTpl: '{name} : 共[ <font color="green">{rows.length}</font> ]个用户'}],
	    this.plugins = [Ext.create('Ext.grid.plugin.CellEditing', {pluginId:'cellEditing',clicksToEdit: 2,listeners:{
//	    	validateedit:function(editor,e){
//                	console.log(e.field+':'+e.originalValue+':'+e.value);
//                	if (e.field == 'username' && e.value == "") {
//					    e.cancel = true;
//					    e.record.data[e.field] = e.originalValue;
//				  	}
//                }
	    }})];
//	    this.plugins = [Ext.create('Ext.grid.plugin.RowEditing', {pluginId:'rowEditing',clicksToEdit: 2})];
    	this.dockedItems = [{
						        xtype: 'pagingtoolbar',
						        store: 'system.UserStore',
						        dock: 'bottom',
						        displayInfo: true
						    }]
	    
//	    var _columns = [Ext.create("Ext.grid.RowNumberer",{})];
//	    function handleColumns(options,success,response){
//    				var headers = Ext.JSON.decode(response.responseText);
//    				var fields = [];
//    				Ext.each(headers,function(header){
//    					_columns.push(header)
//    					fields.push(header.dataIndex);
//    				});
//    				Ext.define('plat.model.ManageModel',{extend: 'Ext.data.Model',fields:fields});
//    				this.store = Ext.create('Ext.data.Store',{storeId:'user_store',autoSync:false,model:'plat.model.ManageModel',sorters: [{property: 'id',direction: 'desc'}]});
////    				this.columns = Ext.Array.insert(headers,0,[Ext.create("Ext.grid.RowNumberer",{})]);
//	    }
//	    Ext.Ajax.request({
//    			url:'system/loadcolumns',
//    			params:{clazz:'user'},
//    			success:function(response){
//    				console.log(response.responseText)
//    				var headers = Ext.JSON.decode(response.responseText);
//    				var fields = [];
//    				Ext.each(headers,function(header){
//    					_columns.push(header)
//    					fields.push(header.dataIndex);
//    				});
//    				console.log('pushed:'+_columns.length);
//    				Ext.define('plat.model.ManageModel',{extend: 'Ext.data.Model',fields:fields});
//    				this.store = Ext.create('Ext.data.Store',{storeId:'user_store',autoSync:false,model:'plat.model.ManageModel',sorters: [{property: 'id',direction: 'desc'}]});
//    				this.columns = Ext.Array.insert(headers,0,[Ext.create("Ext.grid.RowNumberer",{})]);
//    			},
//    			failure:function(response){
//    				Ext.example.msg("提示:","加载columns失败!");
//    			},
//    			callback:handleColumns
//    		});
//	    this.columns = _columns;
	    this.callParent(arguments);
    }
});