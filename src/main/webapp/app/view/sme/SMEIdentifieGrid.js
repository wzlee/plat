Ext.define('plat.view.sme.SMEIdentifieGrid',{
	extend:'Ext.grid.Panel',
	xtype:'smeidentifiegrid',
    
	title:'中小微企业认定',
	id:'zxwqyrd',
	closable:true,
    closeAction:'hide',
	columnLines:true,
    store:'sme.SMEIdentifieStore',
    tbar :['-',				
				{xtype: 'triggerfield',name:'companyName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
					display:false,	//自定义变量判断triggercls是否display
					fieldLabel : '公司名称',
    				emptyText: '请输入公司名称关键字...',  
    				labelWidth : 60,
    				onTriggerClick:function(){
    					this.reset();
    					this.display = false;
	    				this.triggerCell.setDisplayed(false);
	    				this.setWidth(200);
    				}
    			},'-',			
				{iconCls:'icon-search',text:'查找',action:'search'},'-'
    			
	],
    initComponent: function() {
    	var me = this;
        Ext.apply(this, {
			columns: [
						new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
				        { text: 'id',align:'center', dataIndex: 'id',hidden:true},
				        /*{ text: '申请报告',align:'center',width:84, dataIndex: 'applicationReport',
					        renderer:function(value){
					        	//要显示的内容
					        	var showStr='';
					        	if(value.contains('|')){
					        			//按标识分割
					        		var temp = value.split('|');
					        	
					        		for(var i=0;i<temp.length;i++){
					        			showStr +='<a target="_blank" href="upload/files/'+temp[i]+'">' + temp[i].substring(temp[i].indexOf('f')+1) + '</a><br/>'
					        		}
					        		return showStr;
					        	}
					        
					        	return  '<a target="_blank" href="upload/files/'+value+'">' + value.substring(value.indexOf('f')+1) + '</a><br/>';
					        }
				        },
			        	{ text: '营业执照和组织机构证',align:'center',width:120,dataIndex:'copiesOfDocuments',
					        renderer:function(value){
					        	//要显示的内容
					        	var showStr='';
					        	if(value.contains('|')){
					        			//按标识分割
					        		var temp = value.split('|');
					        	
					        		for(var i=0;i<temp.length;i++){
					        			showStr += temp[i]+'<br/>';
					        		}
					        		return showStr;
					        	}
					        
					        	return  value;
					        }
				        },
				        { text: '社保单',align:'center',width:70, dataIndex: 'socialSecurityDetails',
					        renderer:function(value){
					        	//要显示的内容
					        	var showStr='';
					        	if(value.contains('|')){
					        			//按标识分割
					        		var temp = value.split('|');
					        	
					        		for(var i=0;i<temp.length;i++){
					        			showStr += temp[i]+'<br/>';
					        		}
					        		return showStr;
					        	}
					        
					        	return  value;
					        }
				        },
				        { text: '审计报告',align:'center',width:124, dataIndex: 'copyOfTheAuditReport',
					        renderer:function(value){
					        	//要显示的内容
					        	var showStr='';
					        	if(value.contains('|')){
					        			//按标识分割
					        		var temp = value.split('|');
					        	
					        		for(var i=0;i<temp.length;i++){
					        			showStr += temp[i]+'<br/>';
					        		}
					        		return showStr;
					        	}
					        
					        	return  value;
					        }
				        },
				        { text: '资质证明',align:'center',width:156, dataIndex: 'otherSupportingDocuments',
					        renderer:function(value){
					        	//要显示的内容
					        	var showStr='';
					        	if(value.contains('|')){
					        			//按标识分割
					        		var temp = value.split('|');
					        	
					        		for(var i=0;i<temp.length;i++){
					        			showStr += temp[i]+'<br/>';
					        		}
					        		return showStr;
					        	}
					        
					        	return  value;
					        }
				        },
				        { text: '承诺书',align:'center', dataIndex: 'applicationUndertaking',
					        renderer:function(value){
					        	//要显示的内容
					        	var showStr='';
					        	if(value.contains('|')){
					        			//按标识分割
					        		var temp = value.split('|');
					        	
					        		for(var i=0;i<temp.length;i++){
					        			showStr += temp[i]+'<br/>';
					        		}
					        		return showStr;
					        	}
					        
					        	return  value;
					        }
				        },
				        { text: '投标文件',align:'center',width:100, dataIndex: 'tenderDocuments',
					        renderer:function(value){
					        	//要显示的内容
					        	var showStr='';
					        	if(value.contains('|')){
					        			//按标识分割
					        		var temp = value.split('|');
					        	
					        		for(var i=0;i<temp.length;i++){
					        			showStr += temp[i]+'<br/>';
					        		}
					        		return showStr;
					        	}
					        
					        	return  value;
					        }
				        },*/
				        { text: '会员名',align:'center',flex:1, dataIndex: 'user.userName'},
				        { text: '公司名称',align:'center',flex:1, dataIndex: 'companyName'},
				        { text: '公司负责人',align:'center',flex:1, dataIndex: 'responsiblePerson'},
				        { text: '公司地址',align:'center',flex:1, dataIndex: 'companyAddress'},
				        { text: '详情',align:'center',flex:1, renderer:function(value){return "<a href='javascript:void(0)'>详情</a>"}},
				        {
							xtype : 'actioncolumn',
							text : '通过',
							align : 'center',
							sortable : false,
							menuDisabled : true,
							flex:1,
							items : [
					            {
//					               	icon:'resources/images/reject.png',
					               	iconCls : 'icon-ok',
					                tooltip: '通过',
//					                handler: function(grid, rowIndex, colIndex) {
//					                    var record = grid.getStore().getAt(rowIndex);
//					                  		record.set('approveStatus',1);
//								    			grid.getStore().update({
//								    				params:record.data,
//								    				callback:function(result){
//								    						console.log(result);
//								    				}
//								    			});
//					                },
					                handler: function(grid, rowIndex, colIndex) {
					                	openSMEWindow('通过',rowIndex);
					                },
					                isDisabled:function(grid, rowIndex, colIndex){
					                	return grid.getStore().getAt(rowIndex).data.approveStatus != "2";
					               }
					            }	
								]
						},{
							xtype : 'actioncolumn',
							text : '打回',
							menuDisabled : true,
							align : 'center',
							sortable : false,
							flex:1,
							items : [
					            {
					               	icon:'resources/images/reject.png',
					                tooltip: '打回',
//					                handler: function(grid, rowIndex, colIndex) {
//					                    var record = grid.getStore().getAt(rowIndex);
//					                  		record.set('approveStatus',0);
//								    			grid.getStore().update({
//								    				params:record.data,
//								    				callback:function(result){
//								    						console.log(result);
//								    				}
//								    			});
//					                },
					                handler: function(grid, rowIndex, colIndex) {
					                	openSMEWindow('打回',rowIndex);
					                },
					                isDisabled:function(grid, rowIndex, colIndex){
					                	return grid.getStore().getAt(rowIndex).data.approveStatus != "2";
					               }
					            }	
								]
						}
		    		],
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'sme.SMEIdentifieStore',
					        dock: 'bottom',
					        displayInfo: true
					}]
        });
        this.callParent();
    }
});