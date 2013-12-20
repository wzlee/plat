Ext.define('plat.view.mall.MallAddServiceAdWindow',{
	extend:'Ext.window.Window',
	xtype:'malladdserviceadwindow',
	
	closable:true,
    closeAction:'hide',
	layout:'fit',
    width: 420,
    height: 450,
    buttonAlign:'center',
    modal:true,
    initComponent: function() {
        Ext.apply(this, {
        	items :[
		            {	
		            	xtype:'form',
		            	id:'addserviceadform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:61,
					        labelAlign:'right',
					        msgTarget: 'side',
					        margin:'10'
					    },
					    bodyPadding:'10',
					    items:[
					  			{
				                    xtype: 'textfield',
				                    width: 300,
				                    name:'id',
				                    hidden:true
				                },  {
				                    xtype: 'textfield',
				                    width: 300,
				                    name:'mallId',
				                    hidden:true
				                },{
				                    xtype: 'displayfield',
				                    width: 250,
				                    name:'advertisementNo',
				                    fieldLabel: '编号',
				                    readOnly:true
				                },{ 
				                    xtype: 'combo',
				                    name:'channel.id',
				                    allowBlank:false,
				                    afterLabelTextTpl: required,
				                    width: 300,
				                    store:  [
				                    [14, '工业设计超市'], 
				                    [16, '软件超市'], 
				                    [17, '物联网超市'],
									[15, '物流超市']],
				                    rootVisible:false,
				                    editable:false,
				                    emptyText:'请选择频道...',
				                    fieldLabel: '所属频道'
				                },
				                {
				                    xtype: 'combo',
				                    name:'position',
				                    allowBlank:false,
				                    afterLabelTextTpl: required,
				                    width: 300,
				                    //1、服务广告轮播位 2、商城Tab服务推荐位 3、分类服务推荐位 4、左侧服务广告位
				                    store:  [[1, '服务广告轮播位'], [4, '左侧服务广告位']],
				                    rootVisible:false,
				                    editable:false,
				                    emptyText:'请选择位置...',
				                    fieldLabel: '所属位置'
							    }, {
				                    xtype: 'combo',
				                    name:'mallCategory.id',
				                    allowBlank:false,
				                    afterLabelTextTpl: required,
				                    width: 250,
				                    store: Ext.data.StoreManager.lookup('MallChildrenCategoryStore') ? 
				                    		Ext.data.StoreManager.lookup('MallChildrenCategoryStore'):
				                    		Ext .create( 'plat.store.mall.MallChildrenCategoryStore',
													{
														storeId : 'mallchildrencategorystore'
													}),
						            displayField:'text',
									valueField:'id',
				                    rootVisible:false,
				                    editable:false,
				                    emptyText:'请选择类别...',
				                    fieldLabel: '服务类别'
						        }, {
									xtype : 'combo',
									name : 'sort',
									allowBlank : false,
									afterLabelTextTpl: required,
									width : 200,
									store : [['0', '0'],['1', '1'], ['2', '2'], ['3', '3'],
											['4', '4'], ['5', '5'], ['6', '6'],
											['7', '7'], ['8', '8'], ['9', '9'],
											['10', '10'], ['11', '11'], ['12', '12'],
											['13', '13'], ['14', '14'], ['15', '15'],
											['16', '16'], ['17', '17'], ['18', '18'],
											['19', '19'], ['20', '20'], ['21', '21'],
											['22', '22'], ['23', '23'], ['24', '24'],
											['25', '25'], ['26', '26'], ['27', '27'],
											['28', '28'], ['29', '29'], ['30', '30'],
											['31', '31'], ['32', '32'], ['33', '33'],
											['34', '34'], ['35', '35'], ['36', '36'],
											['37', '37'], ['38', '38'], ['39', '39'],
											['40', '40'], ['41', '41'], ['42', '42'],
											['43', '43'], ['44', '44'], ['45', '45'],
											['46', '46'], ['47', '47'], ['48', '48'],
											['49', '49'], ['50', '50'], ['51', '51'],
											['52', '52'], ['53', '53'], ['54', '54'],
											['55', '55'], ['56', '56'], ['57', '57'],
											['58', '58'], ['59', '59'], ['60', '60'],
											['61', '61'], ['62', '62'], ['63', '63'],
											['64', '64'], ['65', '65'], ['66', '66'],
											['67', '67'], ['68', '68'], ['69', '69'],
											['70', '70'], ['71', '71'], ['72', '72'],
											['73', '73'], ['74', '74'], ['75', '75'],
											['76', '76'], ['77', '77'], ['78', '78'],
											['79', '79'], ['80', '80'], ['81', '81'],
											['82', '82'], ['83', '83'], ['84', '84'],
											['85', '85'], ['86', '86'], ['87', '87'],
											['88', '88'], ['89', '89'], ['90', '90'],
											['91', '91'], ['92', '92'], ['93', '93'],
											['94', '94'], ['95', '95'], ['96', '96'],
											['97', '97'], ['98', '98'], ['99', '99'],
											['100', '100']],
									rootVisible : false,
									editable : false,
									emptyText : '请选择顺序...',
									fieldLabel : '显示顺序'
								}, {
				                	xtype: 'container',
							        anchor: '100%',
							        layout: 'hbox',
							       
							        items : [{
							        	xtype:'textfield',
						                fieldLabel: '图片',
						                name: 'uploadImage',
						                readOnly : true,
						                labelWidth : 61,
						                labelAlign:'right',
						                flex : 1,
						                width: 275,
						                 afterLabelTextTpl: required
							        }, {
							        	xtype : 'button',
							        	name : 'select',
							        	icon : 'jsLib/extjs/resources/themes/icons/add1.png'
							        }]
				                },{
				                    xtype: 'textfield',
				                    width: 300,
				                    name:'service.id',
				                    hidden:true,
				                    submitValue:true
				                },{
									xtype : 'combo',
									name : 'serviceId',
									fieldLabel : '对应服务',
									afterLabelTextTpl: required,
									queryMode: 'local',
									store : Ext.create('Ext.data.JsonStore', {
												fields : ['id','serviceName' ],
												autoLoad : true,
												proxy : {
													type : 'ajax',
													actionMethods : {
														read : 'POST'
													},
													extraParams : {
														serviceName : '',
														mallId : null
													},
													url : 'ad/findRecomService',
													reader : {
														type : 'json',
														root : 'data',
														successProperty : 'success'
													}
												}
											}),
									displayField:'serviceName',
									valueField:'id',
									typeAhead : false,
									emptyText:'请选择对应服务...',
									width: 250,
									anchor : '100%',
									listConfig : {
										loadingText : 'Searching...'
									}
		
								}
					    	]
				
		            	}
            		],
            		buttons:[
					{
						text:'提交',
						action:'addServiceAd'
					},{
						text:'清空',
						action:'reset'
					},{
		                text: '取消',
		                scope: this,
		                handler: this.close
					}
			]
        });
        this.callParent();
    }
});