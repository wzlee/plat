Ext.define('plat.view.sme.SMEDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'smedetailwindow',
    width: 800,
    height:600,
	layout:'fit',
	modal:true,
	buttonAlign:'center',
	closeAction : 'hide',
	kindeitor : null,
	getKindeditor : function () {
    	return this.kindeditor;
    },
    setKindeditor : function (kindeditor) {
    	this.kindeditor = kindeditor;
    },
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items : [{
				xtype : 'form',
				id : 'serviceeditform',
				autoScroll : true,
				layout : {
					type : 'column'
				},
				defaults : {
					labelWidth : 150,
					labelAlign : 'right',
					msgTarget : 'side',
					margin : '2'
				},
				bodyPadding : '10',
				items : [{
					xtype : 'hiddenfield',
					name : 'id'
				}, {
					xtype : 'hiddenfield',
					name : 'totalScore'
				}, {
					xtype : 'hiddenfield',
					name : 'evaluateScore'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'companyName',
					fieldLabel : '公司名称'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,				                    
					fieldLabel : '成立时间',
					name : 'registerDate'
				}, {
					xtype : 'displayfield',
					name : 'companyAddress',
					width : 259,
					//labelWidth:86,
					fieldLabel : '公司地址'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'postcode',
					fieldLabel : '邮政编码'
				}, {
					xtype : 'displayfield',
					name : 'responsiblePerson',
					width : 259,
					//labelWidth:86,
					fieldLabel : '公司负责人'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'responsiblePosition',
					fieldLabel : '职务'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'responsiblePhone',
					fieldLabel : '电话'
				}, {
					xtype : 'displayfield',
					name : 'responsibleMobile',
					width : 259,
					//labelWidth:86,
					fieldLabel : '手机'
				}, {
					xtype : 'displayfield',
					name : 'contactPserson',
					width : 259,
					//labelWidth:86,
					fieldLabel : '公司联系人'
				}, {
					xtype : 'displayfield',
					name : 'contactFax',
					width : 259,
					//labelWidth:86,
					fieldLabel : '传真'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'contactMobile',
					fieldLabel : '手机'
				}, {
					xtype : 'displayfield',
					name : 'contactEmail',
					//labelWidth:86,
					width : 259,
					fieldLabel : '邮箱'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'otherContactPerson',
					fieldLabel : '其他联系人'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'mainBusinessProducts',
					fieldLabel : '主营业务产品'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'qualification',
					fieldLabel : '已取得资源'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'scaleIntroduction',
					fieldLabel : '企业规模简介'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'rushDate',
					fieldLabel : '办证日期'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'documenType',
					fieldLabel : '办理中小型企业信息类型'
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'applicationReport',
					fieldLabel : '申请报告',
					renderer : function(value) {
						//要显示的内容
						var showStr = '';
						if (value.contains('|')) {
							var temp = value.split('|');
							for (var i = 0; i < temp.length; i++)
								showStr += '<a target="_blank" href="upload/files/'+ temp[i].substring(0,temp[i].indexOf('f'))+temp[i].substring(temp[i].lastIndexOf('.'))+ '">'+ temp[i].substring(temp[i].indexOf('f')+ 1) + '</a><br/>'
							return showStr;
						}
						return '<a target="_blank" href="upload/files/'+ value.substring(0,value.indexOf('f'))+ value.substring(value.lastIndexOf('.')) + '">'+ value.substring(value.indexOf('f')+ 1) + '</a><br/>';
					}
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'copiesOfDocuments',
					fieldLabel : '营业执照和组织机构证',
					renderer : function(value) {
						//要显示的内容
						var showStr = '';
						if (value.contains('|')) {
							var temp = value.split('|');
							for (var i = 0; i < temp.length; i++)
								showStr += '<a target="_blank" href="upload/files/'+ temp[i].substring(0,temp[i].indexOf('f'))+temp[i].substring(temp[i].lastIndexOf('.'))+ '">'+ temp[i].substring(temp[i].indexOf('f')+ 1) + '</a><br/>'
							return showStr;
						}
						return '<a target="_blank" href="upload/files/'+ value.substring(0,value.indexOf('f'))+ value.substring(value.lastIndexOf('.')) + '">'+ value.substring(value.indexOf('f')+ 1) + '</a><br/>';
					}
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'socialSecurityDetails',
					fieldLabel : '社保单',
					renderer : function(value) {
						//要显示的内容
						var showStr = '';
						if (value.contains('|')) {
							var temp = value.split('|');
							for (var i = 0; i < temp.length; i++)
								showStr += '<a target="_blank" href="upload/files/'+ temp[i].substring(0,temp[i].indexOf('f'))+temp[i].substring(temp[i].lastIndexOf('.'))+ '">'+ temp[i].substring(temp[i].indexOf('f')+ 1) + '</a><br/>'
							return showStr;
						}
						return '<a target="_blank" href="upload/files/'+ value.substring(0,value.indexOf('f'))+ value.substring(value.lastIndexOf('.')) + '">'+ value.substring(value.indexOf('f')+ 1) + '</a><br/>';
					}
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'copyOfTheAuditReport',
					fieldLabel : '审计报告',
					renderer : function(value) {
						//要显示的内容
						var showStr = '';
						if (value.contains('|')) {
							var temp = value.split('|');
							for (var i = 0; i < temp.length; i++)
								showStr += '<a target="_blank" href="upload/files/'+ temp[i].substring(0,temp[i].indexOf('f'))+temp[i].substring(temp[i].lastIndexOf('.'))+ '">'+ temp[i].substring(temp[i].indexOf('f')+ 1) + '</a><br/>'
							return showStr;
						}
						return '<a target="_blank" href="upload/files/'+ value.substring(0,value.indexOf('f'))+ value.substring(value.lastIndexOf('.')) + '">'+ value.substring(value.indexOf('f')+ 1) + '</a><br/>';
					}
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'otherSupportingDocuments',
					fieldLabel : '资质证明',
					renderer : function(value) {
						//要显示的内容
						var showStr = '';
						if (value.contains('|')) {
							var temp = value.split('|');
							for (var i = 0; i < temp.length; i++)
								showStr += '<a target="_blank" href="upload/files/'+ temp[i].substring(0,temp[i].indexOf('f'))+temp[i].substring(temp[i].lastIndexOf('.'))+ '">'+ temp[i].substring(temp[i].indexOf('f')+ 1) + '</a><br/>'
							return showStr;
						}
						return '<a target="_blank" href="upload/files/'+ value.substring(0,value.indexOf('f'))+ value.substring(value.lastIndexOf('.')) + '">'+ value.substring(value.indexOf('f')+ 1) + '</a><br/>';
					}
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'applicationUndertaking',
					fieldLabel : '承诺书',
					renderer : function(value) {
						//要显示的内容
						var showStr = '';
						if (value.contains('|')) {
							var temp = value.split('|');
							for (var i = 0; i < temp.length; i++)
								showStr += '<a target="_blank" href="upload/files/'+ temp[i].substring(0,temp[i].indexOf('f'))+temp[i].substring(temp[i].lastIndexOf('.'))+ '">'+ temp[i].substring(temp[i].indexOf('f')+ 1) + '</a><br/>'
							return showStr;
						}
						return '<a target="_blank" href="upload/files/'+ value.substring(0,value.indexOf('f'))+ value.substring(value.lastIndexOf('.')) + '">'+ value.substring(value.indexOf('f')+ 1) + '</a><br/>';
					}
				}, {
					xtype : 'displayfield',
					width : 259,
					//labelWidth:86,
					name : 'tenderDocuments',
					fieldLabel : '投标文件',
					renderer : function(value) {
						//要显示的内容
						var showStr = '';
						if (value.contains('|')) {
							var temp = value.split('|');
							for (var i = 0; i < temp.length; i++)
								showStr += '<a target="_blank" href="upload/files/'+ temp[i].substring(0,temp[i].indexOf('f'))+temp[i].substring(temp[i].lastIndexOf('.'))+ '">'+ temp[i].substring(temp[i].indexOf('f')+ 1) + '</a><br/>'
							return showStr;
						}
						return '<a target="_blank" href="upload/files/'+ value.substring(0,value.indexOf('f'))+ value.substring(value.lastIndexOf('.')) + '">'+ value.substring(value.indexOf('f')+ 1) + '</a><br/>';
					}
				}]
			}],
			buttons:[
				{
					text:'取消',
					scope: this,
                	handler: this.close
				}
			]
        });

        me.callParent(arguments);
    }

});