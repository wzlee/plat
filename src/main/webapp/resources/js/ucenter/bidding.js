/**
 * 用户应用中心——招标方
 */
$(function() {	 
	
	if($('#bidding-list').length > 0){
		bidding_list();
	}
	if($('#bidding-deallist').length > 0){
		bidding_deallist();
	}
	
	//开关
	$('.action li.s2').click(function(){
		if($(this).attr('action-data')=='up'){
			$(this).attr('action-data','down');
			$(this).children('a').removeClass('up').addClass('down');
			$(this).parent().parent().next('.switch-content').hide();
		}else{
			$(this).attr('action-data','up');
			$(this).children('a').removeClass('down').addClass('up');
			$(this).parent().parent().next('.switch-content').show();
		}
	});
	$('.action li.s1').click(function(){
		if($(this).next().attr('action-data')=='down'){
			jError('请展开后再执行相应操作',{VerticalPosition:'center'});
			return false;
		}else if($(this).attr('action-data')=='edit'){
			$(this).children('a').html('保存');
			$(this).attr('action-data','save');
			$(this).parent().parent().next('.switch-content').children('.show-info').hide();
			$(this).parent().parent().next('.switch-content').children('.info-form').show();
		}else{
			$(this).children('a').html('编辑');
			$(this).attr('action-data','edit');
			jSuccess('保存成功',{VerticalPosition:'center'});
			$(this).parent().parent().next('.switch-content').children('.show-info').show();
			$(this).parent().parent().next('.switch-content').children('.info-form').hide();
		}
	})
	
	/** *******************表单验证************************ */
	var jVal = {
		'categoryId': function(){
			var ele = $('#categoryId');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('.controls label');
			var t = $('#categoryId').combotree('tree');	// 获取树对象
			var node = t.tree('getSelected');
			if(!node){
				info.removeClass('right').addClass('error').html('服务类别不能为空');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'name': function(){
			var ele = $('#name');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}			
			var info = ele.siblings('label');
			if( ele.val().length<5 || ele.val().length>50 ){
				info.removeClass('right').addClass('error').html('服务名称应在5字符以上，50字符以内');				
			}else {
				info.removeClass('error').addClass('right').html('');
			};
		},
		'minPrice': function(){
			var ele = $('#minPrice');
			var input = document.getElementsByName("serviceMethod");			
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\d*$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('价格必须为数字');
			}else{
				info.removeClass('error').addClass('right').html('不填表示面议');
			}
		},
		'maxPrice': function(){
			var ele = $('#maxPrice');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\d*$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('价格必须为数字');
			}else{
				var minPrice = $('#minPrice').val();
				var maxPrice = ele.val();
				if(minPrice > maxPrice){
					info.removeClass('right').addClass('error').html('最大价格不得小于最小价格');
					return;
				}
				info.removeClass('error').addClass('right').html('不填表示面议');
			}
		},
		'linkMan': function(){
			var ele = $('#linkMan');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S+$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('联系人不能为空');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'tel': function(){
			var ele = $('#tel');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('联系电话不正确');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'email': function(){
			var ele = $('#email');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)?$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('电子邮箱不正确');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'file': function(){
			var files = $('input[name="file"]');
			for(var i = 0;i<files.length;i++){
				var ele = files.eq(i);
				if(ele.siblings('label').length==0){
					ele.parent().append('<label class="info"></label>');
				}
				var info = ele.siblings('label');
				var patt = /^\S+$/i;
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('请选择文件上传');
				}else{
					info.removeClass('error').addClass('right').html('已选择文件');
				}
			}
		},
		//招标申请——保存 or 编辑需求——保存
		'save' : function(status){ 
			if(status) {
				$('input[name=\'status\']').val(status);
			}
			$('.auth-form-content form').ajaxSubmit({
				dataType : 'json',
				success : function(ret) {
					if(ret.success){
						$('#apply_ok').show();
					}else{
						$('#apply_err').show();
					}
				},
				error : function(ret) {
					alert('提交失败！');
				}
			});
		},
		// 上传附件
		'uploadFiles' : function(status){
			var files = $('input[type="file"]');
			// 没有附件
			if(files.length < 1) {
				jVal.save(status);
				return;
			}
			// 上传
			var form = $('#attachment_form');
			for(var i = 0;i<files.length;i++){
				var real = files.eq(i);
				var cloned = real.clone(true);
				real.hide();
				cloned.insertAfter(real);
				form.append(real);
			}
			form.ajaxSubmit({
				url : 'public/uploadAttachments',
				type : 'post',
				dataType : 'json',
				success : function(data){
					$('#attachment').val(data.message);
					jVal.save(status);
				},
				error : function() {
					alert('附件上传失败');
				}
			});
		}
  	}; 
  	$('#save').click(function() {
  		jVal.categoryId();
		jVal.name();
		jVal.linkMan();
		jVal.tel();
		jVal.minPrice();
		jVal.maxPrice();
		jVal.email();
		jVal.file(); // 验证文件
		var lable = $('.auth-form-content .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.uploadFiles(1); // 待发布
	});
  	$('#saveAndIssue').click(function() {  		
		jVal.categoryId();
		jVal.name();
		jVal.linkMan();
		jVal.tel();
		jVal.minPrice();
		jVal.maxPrice();
		jVal.email();
		jVal.file(); // 验证文件
		var lable = $('.auth-form-content .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.uploadFiles(2); // 待审核
	});  		
  	$('#name').blur(jVal.name);
  	$('#minPrice').blur(jVal.minPrice);
  	$('#maxPrice').blur(jVal.maxPrice);
  	$('#linkMan').blur(jVal.linkMan);
  	$('#tel').blur(jVal.tel);
  	$('#email').blur(jVal.email);
  	
    $('#categoryId').combotree({
		url:'category/findServiceCategory',
		onSelect:function(){
	   		var t = $('#categoryId').combotree('tree');	// 获取树对象
			var n = t.tree('getSelected');
			var nodes = t.tree('getRoots');				
			for(var i=0;i<nodes.length;i++){					
				if(n.id == nodes[i].id){						
					$("#categoryId").combotree('clear');
				}
			}
		}
	});
	$('#categoryId').combobox({
		onChange:jVal.categoryId
	});
});

/** *******************我的招标************************ */
function bidding_list() {
	var grid = $('#bidding-list').datagrid({
		url : 'bidding/getBiddingList',
		method : 'post',
		singleSelect : true,
		autoRowHeight : false,
		remoteSort : false,
		pagination : true,
		pageSize : 10,
		pageList : [10, 20, 30, 40, 50],
		queryParams : {
        	categoryId : $("#categoryId").combobox('getValue'),
			name : $("#serviceName").val(),
			beginTime : $("#beginTime").datebox('getValue'),
			endTime : $("#endTime").datebox('getValue'),
			status : $("#status").combobox('getValue')
		},
		columns : [[{
					field : 'bidNo',
					title : '招标单号',
					align : 'center',
					width : 110,
					formatter : function(value, rowData, rowIndex) {
						return '<a target="_blank" href="bidding/toDetail?op=101&bid=' + rowData.id + '">'+ value +'</a>';
					}
				}, {
					field : 'category.text',
					title : '服务类别',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, rowIndex) {
						return rowData.category.text;
					}
				}, {
					field : 'name',
					title : '服务名称',
					align : 'center',
					width : 115
				}, {
					field : 'minPrice',
					title : '招标价格',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, rowIndex) {
						if(value != undefined)
							return rowData['minPrice'] + '--' + rowData['maxPrice'];
						else
							return '面议';
					}
				}, {
					field : 'attachment',
					title : '附件',
					width : 60,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						if(value){
							var fnames = value.split(',');
							value = [];
							for(var i=0;i<fnames.length;i++){
								 var aname = '附件' + (i+1);
								 value[i] = '<a target="_blank" href="upload/files/'+fnames[i]+'">' + aname + '</a>';
							}
							value = value.join(' ');
						}
						return value;
					}
				}, {
					field : 'description',
					title : '描述',
					width : 60,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						if(value){
							var temp = value.replace(/<[^>].*?>/g,'').substring(0,6);  
							value = '<div class="zb-description">'+temp+'<div class="zb-description-all">'+value+'</div></div>';
						}
						return value;
					},
					styler : function(value,row,index){
						return 'overflow: visible;';
					}
				}, {
					field : 'linkMan',
					title : '联系人',
					width : 80,
					align : 'center'
				}, {
					field : 'status',
					title : '状态',
					width : 80,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						return '<span>'+PlatMap.BiddingService.status[value]+'</span>';
					}
				}, {
					field : 'createTime',
					title : '创建时间',
					align : 'center',
					width : 80,
					formatter : function(value, rowData, rowIndex) {
						return value.substring(0,11);
					}
				}]],
		onLoadSuccess : function() {
			$('.zb-description').hover(function(){
				$(this).children().show();
			},function(){
				$(this).children().hide();
			});
		}
	});
};

/** *******************待我处理************************ */
function bidding_deallist() {
	var grid = $('#bidding-deallist').datagrid({
		url : 'bidding/getDeallist',
		method : 'post',
		singleSelect : true,
		autoRowHeight : false,
		remoteSort : false,
		pagination : true,
		pageSize : 10,
		pageList : [10, 20, 30, 40, 50],
		queryParams : {
        	categoryId : $("#categoryId").combobox('getValue'),
			name : $("#name").val(),
			status : $("#status").combobox('getValue')
		},
		columns : [[{
					field : 'bidNo',
					title : '招标单号',
					align : 'center',
					width : 110,
					formatter : function(value, rowData, rowIndex) {
						return '<a target="_blank" href="bidding/toDetail?op=101&bid=' + rowData.id + '">'+ value +'</a>';
					}
				}, {
					field : 'category.text',
					title : '服务类别',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, rowIndex) {
						return rowData.category.text;
					}
				}, {
					field : 'name',
					title : '服务名称',
					align : 'center',
					width : 115
				}, {
					field : 'minPrice',
					title : '招标价格',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, rowIndex) {
						if(value != undefined)
							return rowData['minPrice'] + '--' + rowData['maxPrice'];
						else
							return '面议';
					}
				}, {
					field : 'attachment',
					title : '附件',
					width : 60,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						if(value){
							var fnames = value.split(',');
							value = [];
							for(var i=0;i<fnames.length;i++){
								 var aname = '附件' + (i+1);
								 value[i] = '<a target="_blank" href="upload/files/'+fnames[i]+'">' + aname + '</a>';
							}
							value = value.join(' ');
						}
						return value;
					}
				}, {
					field : 'description',
					title : '描述',
					width : 60,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						if(value){
							var temp = value.replace(/<[^>].*?>/g,'').substring(0,6);  
							value = '<div class="zb-description">'+temp+'<div class="zb-description-all">'+value+'</div></div>';
						}
						return value;
					},
					styler : function(value,row,index){
						return 'overflow: visible;';
					}
				}, {
					field : 'linkMan',
					title : '联系人',
					width : 80,
					align : 'center'
				}, {
					field : 'status',
					title : '状态',
					width : 80,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						return '<span>'+PlatMap.BiddingService.status[value]+'</span>';
					}
				}, {
					field : 'id',
					title : '操作',
					align : 'center',
					width : 140,
					formatter : function(value, rowData, rowIndex) {
						switch(rowData.status){
	            			case 1 :
	            			case 3 : 
	            				return '<a href="bidding/toDetail?op=101&flag=1&bid=' + rowData.id + '">发布招标</a>&nbsp;&nbsp;'
	            					+ '<a href="bidding/toEdit?op=101&bid=' + rowData.id + '">编辑</a>&nbsp;&nbsp;'
	            					+ '<a href="bidding/toDetail?op=101&flag=2&bid=' + rowData.id + '">取消招标</a>';
	            			case 4 : 
	            				return '<a href="bidding/toSelect?op=101&bid=' + rowData.id + '">应标处理</a>';
	            		}
            			return value;
					}
				}]],
		onLoadSuccess : function() {
			$('.zb-description').hover(function(){
				$(this).children().show();
			},function(){
				$(this).children().hide();
			});
		}
	});
};


/*多附件上传*/
$(function(){
	var $uploads = "<div class='control-group plupload-file'><label class='control-label'>附件<em class='num'></em></label><div class='controls'><input class='' type='file' name='file'/><b class='del'></b></div></div>";	//  创建第一个上传文件元素
		$('#uploadon').each(function(){

		$(this).click(function(){
				$filesL = $('.upload-box').children('div');
				if($filesL.length < 5 ){ //附件上限5个
					$('.upload-box').append($uploads);
					$('.upload-box em').each(function(i){
						var i=i+1;
						 $(this).text(i);
					})	
					$('.plupload-file').find('.del').click(function(){ //删除附件
						$pa = $(this).parent(); 
						
						$pa.parent().remove();
						$('.upload-box em').each(function(i){
							var i=i+1;
							 $(this).text(i);
						})
		
		
					})
				}
				else{
					art.dialog({
						width: 350,
						height: 100,
						fixed: true,
						lock: true,
						resize: false,
						title: '提示',
						content:'<div class="ucenter-msg"><span class="icon-alert"></span>附件上限为5个！</div>',
						
						ok: function () {
					        this.lock();
					        
					    }
					}); 
				}

			})
		
		})
});