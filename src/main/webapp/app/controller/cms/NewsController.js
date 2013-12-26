Ext.define('plat.controller.cms.NewsController', {
	extend : 'Ext.app.Controller',
	views : ['cms.NewsGrid', 'cms.NewsWin', 'cms.NewsForm',
		'public.UploadWin', 'public.UploadForm','service.PictureWindow'
	],
	stores : ['cms.NewsStore'],
	models : ['cms.NewsModel'],
	refs : [{
    	ref: 'newsgrid',
    	selector: 'newsgrid'
    }, {
    	ref: 'newswindow',
    	selector: 'newswindow'
    }, {
    	ref: 'newsform',
    	selector: 'newsform'
    },{
    	ref:'picturewindow',
    	selector:'picturewindow'
    }],
	init : function () {
		this.control({
			"newsgrid" : {
				afterrender:function(gridpanel){
					gridpanel.down('button[name=add]').on('click',function(){
                		this.addNews();
					}, this);
					/*gridpanel.down('button[name=modify]').on('click',function(){
                		this.modifyNews();
					}, this);
					gridpanel.down('button[name=delete]').on('click',function(){
                		this.deleteNews();
					}, this);*/
					gridpanel.down('button[action=search]').on('click',function(){
						var title = gridpanel.down('textfield[name=title]').getValue();
						gridpanel.getStore().getProxy().setExtraParam('title', title);
						gridpanel.getStore().loadPage(1);
					}, this);
					gridpanel.getStore().load();
            	},
            	itemdblclick:function(grid, record, item, index, e, eOpts){
            		this.modifyNews();
            	}
			},
			'newsgrid actioncolumn':{
            	pictureclick:function(column,grid, rowIndex, colIndex, node, e, record, rowEl){
            		  var record = grid.getStore().getAt(rowIndex);
					  var src = record.data.picture;
					  if (src) {
						 if(src.indexOf('http') > -1){
						     src = record.data.picture;
						 }else {
						     src = 'upload/'+record.data.picture;
						 }					       			
					  } else {
						 src = 'resources/images/service/default_service_pic.gif';
					  }
					  var pictureWindows = this.getPicturewindow();
				      if(!pictureWindows){
				    	  pictureWindows = Ext.widget('picturewindow',{
				    		title:'查看图片['+record.data.title+']'
				          });    		
				      }
					  pictureWindows.update({src:src});
					  pictureWindows.show();
					  pictureWindows.setTitle('查看图片['+record.data.title+']');
            	}
            },
			"newsform" : {
				afterrender : function (form) {
            		form.down('button[name=select]').on('click', function () {
            			this.selectPic();
            		}, this);
            	}
			},
			"newswindow" : {
				afterrender : function (window, opts) {
					var mask = new Ext.LoadMask(window.getEl(), {msg:"请求中,请稍等..."});
            		var options = {
        				clientValidation: true,
        				scope : this,
					    success: function(form, action) {
					    	if (action.result.success) {
						       Ext.example.msg('提示', action.result.message);
						       var record = form.getRecord();
						       window.hide();
						       form.reset();
						       Ext.ComponentQuery.query('newsgrid')[0].getStore().reload();
						       this.getNewsgrid().getSelectionModel().deselectAll();
					    	} else {
					    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
					    	}
					    	mask.hide();
					    },
					    failure: function(form, action) {
					        switch (action.failureType) {
					            case Ext.form.action.Action.CLIENT_INVALID:
						            Ext.Msg.alert('提示', '提交表单中包含非法字符(或必填项为空)！');
					                break;
					            case Ext.form.action.Action.CONNECT_FAILURE:
					                Ext.Msg.alert('提示', '<p align="center">Ajax请求失败</p>');
					                break;
					            case Ext.form.action.Action.SERVER_INVALID:
					               Ext.Msg.alert('提示', '<p align="center">' + 
					               	action.result.msg + '</p>');
					       }
					       mask.hide();
					    }
        			};
            		window.down('button[name=add]').on('click', function () {
            			var form = Ext.ComponentQuery.query('newsform')[0];
            			form.getKindeditor().sync();    //同步kindeditor的内容到textarea中
            			mask.show(form.getEl());
            			options.url = 'news/add';
            			form.getForm().submit(options);
            		});
            		window.down('button[name=modify]').on('click', function () {
            			var form = Ext.ComponentQuery.query('newsform')[0];
            			form.getKindeditor().sync();    //同步kindeditor的内容到textarea中
            			mask.show(form.getEl());
            			options.url = 'news/update';
            			form.getForm().submit(options);
            		});
            		window.down('button[name=cancel]').on('click', function () {
            			var Eform = window.query('newsform')[0];
            			Eform.getForm().reset();
            			Eform.getKindeditor().html('');
            			window.hide();
            			mask.hide();
            		});
            		
            	}
			},
			"uploadwindow[name=news]" : {
				afterrender : function (window, opts) {
					var mask = new Ext.LoadMask(window.getEl(), {
						msg : '上传中,请稍等...',
						constrain : true
					});
					window.down('button[name=submit]').on('click', function () {
            			this.uplodImage(mask);
            		}, this);
            		window.down('button[name=cancel]').on('click', function () {
            			window.hide();
            		}, this);
				}
			}
		})
	},
	
	
	addNews : function () {
    	var newsWindows = Ext.ComponentQuery.query('newswindow')[0];
    	if (!newsWindows) {
    		newsWindows = Ext.widget('newswindow',{
    			title:'添加新闻'
    		}).show();
    	} else {
    		newsWindows.setTitle('添加新闻');
    		newsWindows.show();
    	}
    	newsWindows.down('button[name=modify]').hide();
    	newsWindows.down('button[name=add]').show();
    	
    	this.getNewsform().getForm().reset();
    	var editor = this.getNewsform().getKindeditor();    //清空kindeditor中的内容
		if (editor) {
			editor.html('');
		}
    },
    modifyNews : function () {	//修改新闻表单
//    	var grid = Ext.ComponentQuery.query('newsgrid')[0];
    	var grid = this.getNewsgrid();
    	var records = grid.getSelectionModel().getSelection();
    	if (records.length < 1) {
    		Ext.Msg.show({
			    title: '提示',
			    msg: '请选择一条记录',
			    width: 180,
			    buttons: Ext.Msg.OK,
			    icon: Ext.MessageBox.INFO
			});
			return;
    	}
//    	var newsWindows = Ext.ComponentQuery.query('newswindow')[0];
    	var newsWindows = this.getNewswindow();
    	if (!newsWindows) {
    		newsWindows = Ext.widget('newswindow',{
    			title:'修改新闻'
    		}).show();
    	} else {
    		newsWindows.setTitle('修改新闻');
    		newsWindows.show();
    	}
//    	var Eform = Ext.ComponentQuery.query('newsform')[0];
    	var Eform = this.getNewsform();
    	Eform.getForm().loadRecord(records[0]);
    	var EoriginalPic = Eform.query('textfield[name=originalPic]')[0];
		if (records[0].data.picture) {	//当新闻图片存在时，隐藏输入框做标记
			EoriginalPic.setValue(records[0].data.picture);
		}
    	newsWindows.down('button[name=modify]').show();
    	newsWindows.down('button[name=add]').hide();
    	if (Eform.getKindeditor()) {
			Eform.getKindeditor().html(records[0].data.content);
		} else {
			setTimeout(function () {
				Eform.getKindeditor().html(records[0].data.content);
			}, 1000);
		}
    },
	selectPic : function () {
		var picWindow = Ext.ComponentQuery.query('uploadwindow[name=news]')[0];
    	if (!picWindow) {
    		Ext.widget('uploadwindow',{
    			title : '上传',
    			name : 'news'
    		}).show();
    	} else {
    		picWindow.show();
    	}
	},
	deleteNews : function () {
		var grid = Ext.ComponentQuery.query('newsgrid')[0];
    	var records = grid.getSelectionModel().getSelection();
    	if (records.length < 1) {
    		Ext.Msg.show({
			    title: '提示',
			    msg: '请选择一条记录',
			    width: 180,
			    buttons: Ext.Msg.OK,
			    icon: Ext.MessageBox.INFO
			});
			return;
    	}
    	Ext.MessageBox.confirm('警告','确定删除该新闻吗?',function(btn){
    		if(btn == 'yes'){
		    	grid.getStore().remove(records[0]);
    		}
    	});
	},
	uplodImage : function (mask) {
		var Ewindow = Ext.ComponentQuery.query('uploadwindow[name=news]')[0];
		var Eform = Ewindow.query('uploadform')[0];
		mask.show();
		Eform.getForm().submit({
			url : 'public/uploadFile',
			clientValidation: true,
		    success: function(form, action) {
		    	if (action.result.success) {
			       var EnewsForm = Ext.ComponentQuery.query('newsform')[0];
			       var Epicture = EnewsForm.down('textfield[name=picture]');
			       Epicture.setValue(action.result.message);
			       form.reset();
			       Ewindow.hide();
		    	} else {
		    		Ext.Msg.alert('提示','<p align="center">'+action.result.message+'</p>');
		    	}
		    	mask.hide();
		    	Ewindow.hide();
		    },
		    failure: function(form, action) {
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.Msg.alert('提示', '<p align="center">Ajax请求失败</p>');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
		               Ext.Msg.alert('提示', '<p align="center">' + 
		               	action.result.msg + '</p>');
		       }
		       mask.hide();
		       Ewindow.hide();
		    }
		});
	}
});