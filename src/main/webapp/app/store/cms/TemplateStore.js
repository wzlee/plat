Ext.define('plat.store.cms.TemplateStore', {
    extend: 'Ext.data.Store',
    alias: 'widget.templateStore',
    model:'plat.model.cms.TemplateModel',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        url : 'cms/findAllTempalte',
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});
