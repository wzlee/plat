Ext.define('plat.view.layout.combo.CategoryCombo',{
	extend:'Ext.form.field.ComboBox',
	alias:'widget.categorycombo',
	
	queryMode: 'remote',
    displayField: 'text',
    valueField: 'id',
    
    initComponent:function(){
    	this.store = Ext.create('Ext.data.ArrayStore',{
    		fields:['id','text'],
    		proxy:{
    			type:'ajax',
    			url:'category/load'
    		}
    	});
    	this.callParent(arguments);
    }
});