Ext.define('plat.model.mall.AdvertisementModel', {
    extend: 'Ext.data.Model',
   
    fields: [{
        name: 'id',
        type: 'int'
    }, {
        name: 'advertisementNo',
        type: 'String'
    }, {
        name: 'channel.id',
        type: 'int'
    }, {
        name: 'channel.cname',
        type: 'String'
    }, {
        name: 'position',
        type: 'int'
    }, {
        name: 'mallCategory.id',
        type: 'int'
    }, {
        name: 'mallCategory.text',
        type: 'String'
    }, {
        name: 'sort',
        type: 'int'
    }, {
        name: 'uploadImage',
        type: 'String'
    }, {
        name: 'service.id',
        type: 'int'
    },{
        name: 'serviceId',
        type: 'int',
        mapping:'service.id'
    }, {
        name: 'service.serviceNo',
        type: 'String'
    }, {
        name: 'service.serviceName',
        type: 'String'
    },{
        name: 'pageLink',
        type: 'String'
    }, {
        name: 'createTime',
        type: 'String'
    }, {
        name: 'uploadImage',
        type: 'String'
    }]
});