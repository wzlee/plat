$(function(){
	window.onload = function(){
		$.ajax({
			url: '/category/findAllCategoryAndService',
			type: 'post',
			dataType: 'json',
			async: false,
			success: function(data){
				$('.mod-cate').html(data.message);
				/* 左侧菜单 */
				$('.mod-cate li').hover(function(){
					$(this).children('.name-wrap').children('.name-column').addClass('on');
					$(this).children('.mod-sub-cate').show();
				},function(){
					$(this).children('.name-wrap').children('.name-column').removeClass('on');
					$(this).children('.mod-sub-cate').hide();
				});
			},
			failure: function(data){
				console.log(data.message);
			}
		});
	};
	
	/*服务列表*/
	$('.service-list li').hover(function(){
		$(this).children('.apply').show();
	},function(){
		$(this).children('.apply').hide();
	})
	/*Placeholder*/
	$('input').placeholder();
	/*幻灯片*/
	$("#slider li:not(:first)").css("display","none");
	setInterval(function(){
	 if($("#slider li:last").is(":visible")){
		$("#slider li:first").fadeIn("slow").addClass("in");
		$("#slider li:last").hide();
	 }else{
		$("#slider li:visible").addClass("in");
		$("#slider li.in").next().fadeIn("slow");
		$("#slider li.in").hide().removeClass("in")}
	 },5000)
	 /* 表单提交 */
	$('.login-btn').click(function(){
		$('#userlogin').ajaxSubmit({
			beforeSubmit: function(){
				if($.trim($.trim($('#username').val()))==''){
					$('.show-error').find('.text').text("");
					$('.show-error').show();
					$('.show-error').find('.text').text("请输入会员名");
					$('#username').focus();
					return false;
				}else if($.trim($('#password').val()) ==''){
					$('.show-error').find('.text').text("");
					$('.show-error').show();
					$('.show-error').find('.text').text("请输入密码");
					$('#password').focus();
					return false;
				}else if($.trim($('#checkcode').val())==''){
					$('.show-error').find('.text').text("");
					$('.show-error').show();
					$('.show-error').find('.text').text("请输入验证码");
					$('#checkcode').focus();
					return false;
				}else{
					$('.login-btn').button('loading');
		        	return true;
				}
			},
			type: "post",
			url: "/userlogin",
			dataType: "json",
			data:{'password':$.md5($.trim($('#password').val()))},
			success: function(data){
				$('.login-btn').button('reset');
				if(data.success){
					window.location.href='/';
				}else{
					$('.show-error').find('.text').text("");
					$('.show-error').show();
					$('.show-error').find('.text').text(data.message);
				}
			}
		});
		return false;
	});
	
	/*屏蔽表单自己提交*/
	$("#userlogin").submit(function(e){
		e.preventDefault();
     });
})