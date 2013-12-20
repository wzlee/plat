Ext.define('plat.view.layout.combo.LevelCombo',{
	extend:'Ext.form.field.ComboBox',
	alias:'widget.levelcombo',
	
	width:80,
	queryMode: 'local',
    displayField: 'name',
    valueField: 'value',
    
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['value','name'],
    		data:[['INFO','输出信息'],['DEBUG','调试信息'],['WARN','警告信息'],['ERROR','错误信息'],['OTHER','其他信息']]
    	});
    	this.callParent(arguments);
    }
});