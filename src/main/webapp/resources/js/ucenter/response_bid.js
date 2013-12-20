$(function(){
	var jVal = {
		'bidPrice': function(){
			var ele = $('#bidPrice');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');			
			var patt = /^[0-9]\d*$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('只能输入数字');
			}else{
				if($('#maxPrice').val()){
					if(parseInt(ele.val()) >= parseInt($('#minPrice').val()) && parseInt(ele.val()) <= parseInt($('#maxPrice').val())){						
						info.removeClass('error').addClass('right').html('');
					}else {						
						info.removeClass('right').addClass('error').html('应在招标价格范围内');
					}
				}else {
					info.removeClass('error').addClass('right').html('');
				}
			}
		},
		'linkMan': function(){
			var ele = $('#linkMan');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');			
			if(ele.val()){
				info.removeClass('error').addClass('right').html('');							
			}else {
				info.removeClass('right').addClass('error').html('联系人不能为空');	
			};
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
			var patt = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if(ele.val()){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('电子邮箱不正确');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			}else {
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
		'description': function(){
			var ele = $('#myEditor');			
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');			
			info.removeClass('error').addClass('right').html('');
		},
		'uploadFiles' : function(){			
			var files = $('input[type="file"]');
			// 没有附件
			if(files.length < 1) {
				jVal.submitResponse();
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
					jVal.submitResponse();
				},
				error : function() {
					alert('附件上传失败');
				}
			});
		},
		'submitResponse' : function(){			
			$('#submitResponse').ajaxSubmit({
				url : 'bidding/submitResponse',
				type : 'post',
				dataType : 'json',							
				success : function(ret) {
					alert(ret.message);
					window.location.href='/ucenter/response_push?op=6';
				},
				error : function(ret) {
					alert('应标失败，谢谢');
				}
			});
		},
		'checkForm' : function(){
			var lable = $('.auth-form-content .info');			
			jVal.bidPrice();
			jVal.linkMan();
			jVal.tel();
			jVal.email();
			jVal.file(); // 验证文件
			jVal.description();
		}
	};
	jVal.checkForm();
	$("#submit").click(function(){
		jVal.checkForm();
		var lable = $('.auth-form-content .info');		
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}		
		jVal.uploadFiles();
	});
	$("#return").click(function(){
		window.location.href="/ucenter/response_push?op=6";
	});
	$('#bidPrice').blur(jVal.bidPrice);
	$('#linkMan').blur(jVal.linkMan);
	$('#tel').blur(jVal.tel);
	$('#email').blur(jVal.email);		
	UE.getEditor('myEditor').addListener("blur",jVal.description);
	biddingLog();
});
function biddingLog(){
	var grid = $('#bidding-log').datagrid({
		url : 'bidding/getBiddingLog',
		method : 'post',
		singleSelect : true,
		autoRowHeight : false,
		remoteSort : false,
		pagination : true,
		pageSize : 10,
		pageList : [10, 20, 30, 40, 50],
		queryParams : {
			bid : $('#bid').val()
		},
		columns : [[{
					field : 'userName',
					title : '操作人',
					align : 'center',
					width : 110,
					formatter : function(value, rowData, rowIndex) {
						if(rowData.user){
							return rowData.user.userName;
						}else{
							return rowData.manager.username;
						}
					}
				}, {
					field : 'action',
					title : '动作',
					align : 'center',
					width : 150,
					formatter : function(value, rowData, rowIndex) {						
						return PlatMap.BiddingServiceDetail.action[value];
					}
				}, {
					field : 'processTime',
					title : '时间',
					align : 'center',
					width : 150
				}, {
					field : 'remark',
					title : '备注',					
					align : 'center',
					width : 250
				}, {
					field : 'biddingStatus',
					title : '订单状态',
					width : 120,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						return PlatMap.BiddingService.status[value];
					}
				}]],
		onLoadSuccess : function() {
		}
	});
};

/*多附件上传*/
$(function(){
	var $uploads = "<div class='control-group plupload-file'><label class='control-label'>附件<em class='num'></em></label><div class='controls'><input class='file' type='file' name='file'/><b class='del'></b></div></div>";	//  创建第一个上传文件元素
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
					alert('附件上传上限5个！');
				}

			})
		
		})
});