/**
 * 个人用户实名认证标单
 */
$(function(){
	var jVal = {
		'name': function() {
			var ele = $('#name');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
//			var patt = /^\S+$/i;
			if(ele.val() == null || ele.val() == ''){
				info.removeClass('right').addClass('error').html('真实姓名不能为空');
			}else{
				info.removeClass('error').addClass('right').html('填写正确');
			}
		},
		'personalPhoto': function(){
			var ele = $('#personalPhoto');
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
		},
		'idCardPhoto': function(){
			var ele = $('#idCardPhoto');
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
		},
		// 提交实名申请
		'submitRealName' : function() {
			// STEP 1 异步上传文件
			var files = $('input[type="file"]');
			// 个人近照
			var form = $('#personalPhoto_form');
			var real = files.eq(0);
			var cloned = real.clone(true);
			real.hide();
			cloned.insertAfter(real);
			form.append(real);
			form.ajaxSubmit({
				url : 'public/uploadFile',
				type : 'post',
				dataType : 'json',
				clearForm : true,
				success : function(data) {
					cloned.next().val(data.message);
					// 身份证附件图片
					form = $('#idCardPhoto_form');
					real = files.eq(1);
					cloned = real.clone(true);
					real.hide();
					cloned.insertAfter(real);
					form.append(real);
					form.ajaxSubmit({
						url : 'public/uploadFile',
						type : 'post',
						dataType : 'json',
						clearForm : true,
						success : function(data) {
							cloned.next().val(data.message);
							// 提交表单
							$('.main-column form').ajaxSubmit({
								dataType : 'json',
								success : function(ret) {
									if(ret.success){
										$('#apply_ok').show();
									}else{
										$('#errMsg').html(ret.message);
										$('#apply_err').show();
									}
								},
								error : function(ret) {
									alert('认证申请提交失败！');
								}
							});
						},
						error : function() {
							alert('身份证附件图片上传失败');
						}
					});
				},
				error : function() {
					alert('个人近照图片上传失败');
				}
			});
		}
	};
	$('#commit').click(function() {
		jVal.name();
		jVal.personalPhoto();
		jVal.idCardPhoto();
		var lable = $('.auth-form-content .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.submitRealName();
	});
	$('#name').blur(jVal.name);
	$('#personalPhoto').change(jVal.personalPhoto);
	$('#idCardPhoto').change(jVal.idCardPhoto);
});

