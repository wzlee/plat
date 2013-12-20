$(function(){
	$('.next1').click(function(){
		$('.step-1').hide();
		$('.step-2').show();
		
	})
	$('.prev1').click(function(event){
		$('.step-2').hide();
		$('.step-1').show();
		
	})
	$('.prev2').click(function(event){
		$('.step-3').hide();
		$('.step-2').show();
		
	})
	//多附件上传
	var uploads ="<div class='row'><div class='col wenjian'><input type='file' name='' class='input-file-name' /></div><div class='col end'><span class='del'>删除</span></div></div>";
	$('.up-step2 .more-files .btn-upload').click(function(){
		var filename = $(this).data('file');
		var $leng=$(this).parent().children('.row');
		if($leng.length < 5){
			$(this).parent().append(uploads);
			$(this).parent().find(".input-file-name").attr('name',filename);
			$('.more-files').find('.del').click(function(){
			
				 var $pa = $(this).parent();
				 $pa.parent().remove();
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

		return false;
	})
	var jVal = {
			'companyName': function(){
				var ele = $('#companyName');
				if(ele.siblings().length==0){
					ele.parent().append('<label class="info"></label>');
				}
				var info = ele.siblings('label');
				var patt = /^(?!^\d+$).{6,50}$/;
				if(ele.val()==''){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('此项为必填项');
				}else if(!patt.test(ele.val())){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('长度必须为6-50个字符中文或英文');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			},
			'companyAddress': function(){
				var ele = $('#companyAddress');
				if(ele.siblings().length==0){
					ele.parent().append('<label class="info"></label>');
				}
				var info = ele.siblings('label');
				var patt = /^(?!^\d+$).{6,50}$/;
				if(ele.val()==''){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('此项为必填项');
				}else if(!patt.test(ele.val())){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('长度必须为6-50个字符');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			},
			'responsiblePerson': function(){
				var ele = $('#responsiblePerson');
				if(ele.siblings().length==0){
					ele.parent().append('<label class="info"></label>');
				}
				var info = ele.siblings('label');
				var patt = /^(?!^\d+$).{2,4}$/;
				if(ele.val()==''){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('此项为必填项');
				}else if(!patt.test(ele.val())){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('长度为2-4个字符');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			},
			'contactPserson': function(){
				var ele = $('#contactPserson');
				if(ele.siblings().length==0){
					ele.parent().append('<label class="info"></label>');
				}
				var info = ele.siblings('label');
				var patt = /^(?!^\d+$).{2,4}$/;
				if(ele.val()==''){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('此项为必填项');
				}else if(!patt.test(ele.val())){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('长度为2-4个字符');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			},
			'mainBusinessProducts': function(){
				var ele = $('#mainBusinessProducts');
				if(ele.siblings().length==0){
					ele.parent().append('<label class="info"></label>');
				}
				var info = ele.siblings('label');
				var patt = /^(?!^\d+$).{6,100}$/;
				if(ele.val()==''){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('此项为必填项');
				}else if(!patt.test(ele.val())){
					jVal.errors = true;
					info.removeClass('right').addClass('error').html('长度为6-100个字符');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			},
			'funs':function(){
				$('.step-2').hide();
				$('.step-3').show();
				return false;
			}
	}
	
	$('#next2').click(function(){
		jVal.errors = false;
		jVal.usernameError = false;
		jVal.companyName();
		jVal.companyAddress();
		jVal.responsiblePerson();
		jVal.contactPserson();
		jVal.mainBusinessProducts();
		var lable = $('.step-2 .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.funs();
		return false;
	});
	$('#companyName').blur(jVal.companyName);
	$('#companyAddress').blur(jVal.companyAddress);
	$('#responsiblePerson').blur(jVal.responsiblePerson);
	$('#contactPserson').blur(jVal.contactPserson);
	$('#mainBusinessProducts').blur(jVal.mainBusinessProducts);
	$('#save').click(function() {
		var files = $('input[type="file"]');
		// 没有附件
		if(files.length < 1) {
			alert("无附件");
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
			url : 'sme/uploadSmeFiles',
			type : 'post',
			dataType : 'text',
			success : function(data){
				console.log(data);
				$('#fileMap').val(data);
				$.ajax({
					url : 'sme/save',
					type : 'post',
					dataType : 'html',
					data: $('#sme_info').serialize(),
					success : function(data1){
						art.dialog({
						width: 350,
						height: 100,
						fixed: true,
						lock: true,
						resize: false,
						title: '提示',
						content:'<div class="ucenter-msg"><span class="icon-good"></span>提交成功</div>',
						ok: function () {
					        this.lock();
					    }
					});
						document.write(data1);
					},
					error : function() {
					}
				})
			},
			error : function() {
				alert('附件上传失败');
			}
		});
	});
})