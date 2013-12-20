$(function() {
	/************************end***************************/
	$('.service-type').click(function() {
		$('.interesting').show();
	});
	$('.service-head .close').click(function() {
		$('.interesting').hide();
		var categoryhtml ='';
		var spans = $('.selected-list li span');
			for(var i =0;i<spans.length;i++){
				categoryhtml+=spans[i].innerHTML+' ';
			}
		$('#myinterestservice1').html(categoryhtml);
		$('#myinterestservice2').html(categoryhtml);
	});
	/*********************感兴趣的服务领域**************************/
	//隔行换色
	$('.service-body .service-row:even').addClass('row-even');
	//点击服务条目
	$(document).on('click', '.service-list li span', function(event){
		var input = $(this).prev();
		if(input.is(':checked')){
			var selected = $('.selected-list').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}else{
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list');
		}
	});
	$(document).on('click', '.service-list li input', function(event){
		var input = $(this);
		if(input.is(':checked')){
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list');
		}else{
			var selected = $('.selected-list').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}
	});
	//点击已选
	$(document).on('click', '.selected-list li', function(event) {
		var input = $('.service-list').find("input[name='"
						+ $(this).children('input').attr('name') + "']");
		input.attr('checked', false);
		if(!input.is(':checked')) $(this).remove();
	});
	
	//用户管理
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
			$(this).children('a').html('取消');
			$(this).attr('action-data','cancel');
			$(this).parent().parent().next('.switch-content').children('.show-info').hide();
			$(this).parent().parent().next('.switch-content').children('.info-form').show();
			
		}else{
//			/*********************判断是否存在错误，存在的话弹窗***************************/
//			var lable = $('.auth-form-content .info');
//			for(var i=0;i<lable.length;i++){
//				if(!lable.eq(i).hasClass('right')){ 
//					alert('提交的数据有错误,请检查清楚再提交');
//					return;
//				}
//			}
//			/*********************判断是否存在错误，存在的话弹窗***************************/
			$(this).children('a').html('编辑');
			$(this).attr('action-data','edit');
			$(this).parent().parent().next('.switch-content').children('.show-info').show();
			$(this).parent().parent().next('.switch-content').children('.info-form').hide();
		}
	})
	
	/**/
	$('.service-type-my').click(function() {
		$('.myservice').show();
		
	});
	$('.service-head-my .close').click(function() {
		$('.myservice').hide();
		var categoryhtml ='';
		var spans = $('.selected-list-my li span');
			for(var i =0;i<spans.length;i++){
				categoryhtml+=spans[i].innerHTML+' ';
			}
		$('#myservice1').html(categoryhtml);
		$('#myservice2').html(categoryhtml);
	});
		/*********************我的服务领域**************************/
	//隔行换色
	$('.service-body-my .service-row-my:even').addClass('row-even-my');
	//点击服务条目
	$(document).on('click', '.service-list-my li span', function(event){
		var input = $(this).prev();
		if(input.is(':checked')){
			var selected = $('.selected-list-my').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}else{
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			if(checkBoxs.length >= 10){
				alert('最多选择10个服务领域'); return;
			}
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list-my');
		}
	});
	$(document).on('click', '.service-list-my li input', function(event){
		var input = $(this);
		if(input.is(':checked')){
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			if(checkBoxs.length >= 10){
				alert('最多选择10个服务领域'); 
				input[0].checked = false;
				return;
			}
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list-my');
		}else{
			var selected = $('.selected-list-my').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}
	});
	//点击已选
	$(document).on('click', '.selected-list-my li', function(event) {
		var input = $('.service-list-my').find("input[name='"
						+ $(this).children('input').attr('name') + "']");
		input.attr('checked', false);
		if(!input.is(':checked')) $(this).remove();
	});
	
});

$(function(){
	var jVal = {
		'headPortraint':function(){
			var ele = $('#headPortraint');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('right').addClass('error').html('请选择头像图片');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'mobile': function() {
			var ele = $('#mobile');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
			var mobileReg = /^1\d{10}$/;
			if(ele.val() != '' && ele.val() != null){
				if(!telReg.test(ele.val())&&!mobileReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('联系方式为固话或者手机号码');
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'userEmail':function(){
			var ele = $('#userEmail');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(ele.val() != '' && ele.val() != null){
				if(!emailReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('邮箱格式不正确');
				}else{
//					info.removeClass('error').addClass('right').html('填写正确');
					$.post("user/checkEmail", 
	           			{ "email" :	ele.val()},
	           	   		function(data){
	           				if(data.success){
	           					info.removeClass('error').addClass('right').html(data.message);
//	           					ele.attr('data-status',2);
	           				}else{
	           					info.removeClass('right').addClass('error').html(data.message);
//	           					ele.attr('data-status',1);
	           				}
	           	   		},
	           	   		"json");
				}
			}else{
				info.removeClass('right').addClass('error').html('邮箱不能为空');
			}
		},
		'submitInfo':function(){
			var certSign = $('#certSign').val();
			if(certSign == '认证用户'){
				$('#certSign').val(1);
				
			}else{
				$('#certSign').val(0);
			}
			$('#userinfoform').ajaxSubmit({
					dataType : 'json',
					url:'ucenter/updateUserInfo',
					type:'post',
					success : function(ret) {
						if(ret.success){
							art.dialog({
								width: 350,
								height: 100,
								fixed: true,
								lock: true,
								resize: false,
								title: '提示',
								content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
								
								ok: function () {
							        this.lock();
							        window.location.reload(true);
							    }
							
							}); 
							
						}
					},
					error : function(ret) {
						art.dialog({
							width: 350,
							height: 100,
							fixed: true,
							lock: true,
							resize: false,
							title: '提示',
							content:'<div class="ucenter-msg"><span class="icon-good"></span>信功</div>',
							
							ok: function () {
						        this.lock();
						        window.location.reload(true);
						    }
						
						}); 
					}
			});
		},
		
		'submitMyServiceInfo':function(){
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			var ids='';
			for(var i =0;i<checkBoxs.length;i++){
				ids+=checkBoxs[i].value+',';
			}
			$.post(
				'ucenter/saveUserServiceInfo',
				{'ids':ids,'uid':$('#userId').val()},
				 function(data) {
					if(data.success){
						art.dialog({
							width: 350,
							height: 100,
							fixed: true,
							lock: true,
							resize: false,
							title: '提示',
							content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
							
							ok: function () {
						        this.lock();
						        window.location.reload(true);
						    }
						
						}); 
						
					}else{
						jError(data.message,{VerticalPosition:'center'});
					}
					window.location.reload(); 
				},'json'
				
			);
		},
		'submitInterestServiceInfo':function(){
			var checkBoxs = $('.selected-list input[type="checkbox"]');
			var ids='';
			for(var i =0;i<checkBoxs.length;i++){
				ids+=checkBoxs[i].value+',';
			}
			$.post(
				'ucenter/saveInteresServiceInfo',
				{'ids':ids,'uid':$('#userId').val()},
				 function(data) {
					if(data.success){
						art.dialog({
							width: 350,
							height: 100,
							fixed: true,
							lock: true,
							resize: false,
							title: '提示',
							content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
							
							ok: function () {
						        this.lock();
						        window.location.reload(true);
						    }
						
						}); 
						//window.location.reload(false);
					}
					window.location.reload(); 
				},'json'
			);
		},
//		'submitLinkInfo':function(){
//			$('#linkinfofrom').ajaxSubmit({
//				dataType : 'json',
//				url:'ucenter/saveLinkInfo',
//				type:'post',
//				success : function(ret) {
//						if(ret.success){
//							jSuccess(ret.message,{VerticalPosition:'center'});
//							window.location.reload(); 
//						}
//					},
//					error : function(ret) {
//						jSuccess(ret.message,{VerticalPosition:'center'});
//					}
//			});
//		
//		},
		'submitPhoto' : function(){
//			if($('#headPortraint').val() != ''){
				$('#photoform').ajaxSubmit({
					dataType : 'json',
					url : 'public/uploadFile',
					type : 'post',
					success : function(ret) {
						if (ret.success) {
							var photoName = ret.message;
							$.post(
								'ucenter/updateHeadPortraint',
								{'photo':photoName, 'uid':$('#userId').val()},
								function(data) {
									if(data.success){
										art.dialog({
											width: 350,
											height: 100,
											fixed: true,
											lock: true,
											resize: false,
											title: '提示',
											content:'<div class="ucenter-msg"><span class="icon-good"></span>用户头像上传成功</div>',
											
											ok: function () {
										        this.lock();
										        window.location.reload(true); 
										    }
										
										}); 
										
									}else{
										jError(data.message,{VerticalPosition:'center'});
									}
								},'json'
							);
						}
					},
					error : function(ret) {
//						jSuccess(ret.message, {VerticalPosition : 'center'});
						jError(ret.message,{VerticalPosition:'center'});
					}
				});
//			}else{//不上传图片
//				$.post(
//					'ucenter/updateHeadPortraint',
//					{'uid':$('#userId').val()},
//					function(data) {
//						if(data.success){
//							jSuccess(data.message,{VerticalPosition:'center',  onClosed : function(){
//							window.location.reload(false); }});
//						}else{
//							jError(data.message,{VerticalPosition:'center'});
//						}
//					},'json'
//				);
//			}
		} 
	};
	/*我感兴趣的服务领域提交*/
	$('#interestservicescommit a').click(function() {
		if($('#interestservicescommit').attr('action-data')=='save'){
			jVal.submitInterestServiceInfo();
		}
		
	});
//	/*联系信息提交*/
//	$('#linkcommit').click(function() {
//		if($('#linkcommit').attr('action-data')=='edit'){
//			jVal.submitLinkInfo();
//		}
//	});
	
	/*我的服务领域信息提交*/
	$('#myservicecommit a').click(function() {
			if($('#myservicecommit').attr('action-data')=='save'){
			jVal.submitMyServiceInfo();
		}
		
	});
	
	//infocommit
	$('#infocommit a').click(function() {
		if($('#infocommit').attr('action-data')=='save'){
			var lable = $('.auth-form-content .info');
			for(var i=0;i<lable.length;i++){
				if(!lable.eq(i).hasClass('right')) return;
			}
			jVal.submitInfo();
		}
	});
	
	// 图像设置
	$('#photocommit a').click(function(){
		if($('#photocommit').attr('action-data')=='save'){
			var ele = $('#headPortraint');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('right').addClass('error').html('请选择头像图片');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
			
			var lable = $('.auth-form-content .info');
			for(var i=0;i<lable.length;i++){
				if(!lable.eq(i).hasClass('right')) return;
			}
			jVal.submitPhoto();
		}
	});
	$('#headPortraint').blur(jVal.headPortraint);
	$('#userEmail').blur(jVal.userEmail);
	$('#mobile').blur(jVal.mobile);
	$('#address').blur(jVal.address);
});

$(function(){
//	var jVal = {
//		'mobile': function() {
//			var ele = $('#mobile');
//			if(ele.siblings().length==0){
//				ele.parent().append('<label class="info"></label>');
//			}
//			var info = ele.siblings('label');
//			var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
//			var mobileReg = /^1\d{10}$/;
//			if(ele.val() != '' && ele.val() != null){
//				if(!telReg.test(ele.val())&&!mobileReg.test(ele.val())){
//					info.removeClass('right').addClass('error').html('联系方式为固话或者手机号码');
//				}else{
//					info.removeClass('error').addClass('right').html('填写正确');
//				}
//			}else{
//				info.removeClass('error').addClass('right').html('');
//			}
//		},
//		'userEmail':function(){
//			var ele = $('#userEmail');
//			if(ele.siblings().length==0){
//				ele.parent().append('<label class="info"></label>');
//			}
//			var info = ele.siblings('label');
//			var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
//			if(ele.val() != '' && ele.val() != null){
//				if(!emailReg.test(ele.val())){
//					info.removeClass('right').addClass('error').html('邮箱格式不正确');
//				}else{
////					info.removeClass('error').addClass('right').html('填写正确');
//					$.post("user/checkEmail", 
//	           			{ "email" :	ele.val()},
//	           	   		function(data){
//	           				if(data.success){
//	           					info.removeClass('error').addClass('right').html(data.message);
////	           					ele.attr('data-status',2);
//	           				}else{
//	           					info.removeClass('right').addClass('error').html(data.message);
////	           					ele.attr('data-status',1);
//	           				}
//	           	   		},
//	           	   		"json");
//				}
//			}else{
//				info.removeClass('right').addClass('error').html('邮箱不能为空');
//			}
//		}
//
//	};
//	$('#userEmail').blur(jVal.userEmail);
//	$('#mobile').blur(jVal.mobile);
});