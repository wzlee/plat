/**
 * 企业子账号模型 xuwf 2013-8-23  
 */
Ext.define('plat.model.enteruser.EnterStaffModel', {
    extend: 'Ext.data.Model',
    fields: ['id','userName','password','satffName','sex','tel','mobile',
    'email','address','assignTime','remark','staffStatus',
    //所属主账号信息
    {name:'parent.id',type:'int'},
    {name:'parent.userName',type:'String'},
    {name:'parent.userStatus',type:'int'},
    {name:'parent.isPersonal',type:'boolean'},
    {name:'parent.password',type:'String'},
    {name:'parent.sex',type:'int'},
    {name:'parent.checkcode',type:'String'},
    {name:'parent.regTime',type:'String'},
    {name:'parent.email',type:'String'},
    {name:'parent.isApproved',type:'boolean'},
    {name:'parent.sourceId',type:'int'},    		
    {name:'parent.remark',type:'String'},    
    //如果分配人是服务机构的话，关联服务机构的用户信息
	    {name:'assigner.id',type:'int'},
	    {name:'assigner.userName',type:'String'},
//    {name:'assigner.userStatus',type:'int',defalutValue:null},
//    {name:'assigner.isPersonal',type:'boolean',defalutValue:null},
//    {name:'assigner.password',type:'String',defalutValue:null},
//    {name:'assigner.sex',type:'int',defalutValue:null},
//    {name:'assigner.checkcode',type:'String',defalutValue:null},
//    {name:'assigner.regTime',type:'String',defalutValue:null},
//    {name:'assigner.email',type:'String',defalutValue:null},
//    {name:'assigner.isApproved',type:'boolean',defalutValue:null},
//    {name:'assigner.sourceId',type:'int',defalutValue:null},    		
//    {name:'assigner.remark',type:'String',defalutValue:null},  
    //如果分配人是运营人员，关联manager类的信息
    {name:'manager.id',type:'int'},
    {name:'manager.username',type:'String'},
    {name:'manager.password',type:'String'},
    {name:'manager.userStatus',type:'int'},
    {name:'manager.userType',type:'int'},
    {name:'manager.remark',type:'String'},
    {name:'manager.flatId',type:'int'},
    {name:'manager.registerTime',type:'String'},
    //子账号角色
    {name:'staffRole.id',type:'int'},
    {name:'staffRole.rolename',type:'String'}
   ]
});
