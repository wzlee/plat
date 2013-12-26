$(function(){
	$('.check-code').click();
	/*新闻滚动*/
	if($(".scroll-panel > ul > li").length !=0 ){
		$(".scroll-panel").jCarouselLite({
			vertical: true,
			hoverPause:true,
			visible: 1,
			auto:5000,
			speed:1000
		});	
	}

	/*
	 * Banner
	 */
	var index = 0;
	var picTimer;
	var bannerSlide = $('.dom-banner');
	var bannerPicLen = $('.dom-box li').length;

	/* 添加圆点 */
	var dot = "<p id=\"dotList\" class=\"dom-dots\">";
		for(var i = 0; i < bannerPicLen; i++ ) {
			dot += "<span></span>";
		}
		dot += "</p>";
	bannerSlide.append(dot);

	/* 幻灯延迟动画 */
	showPic(index);
	autoSlide(index);

	/*鼠标滑动方块*/
	$('.dom-dots span').hover(function() {
		clearInterval(picTimer);
		index = $(this).index();
		showPic(index);
	}, function() {
		autoSlide(index);
	});
	/*自动播放*/
	function autoSlide(index) {
		picTimer = setInterval(function(){
			showPic(index);
			index++;
			index = (index == bannerPicLen) ? 0 : index;
		},3000);
	}
	/*动画显示图片*/
	function showPic(index){
		$('.dom-box li').eq(index).animate({"opacity": 1, "z-index": 1}, 1000).siblings('li').animate({"opacity": 0, "z-index": 0}, 1000);
		$('.dom-dots span').eq(index).addClass('current').siblings('span').removeClass('current');
	}
	
	$.ajax({
		url: '/category/findAllCategoryAndService',
		type: 'post',
		dataType: 'json',
		async: true,
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
			dataType: "json",
			data:{password:$.md5($.trim($('#password').val()))},
			success: function(data){
				$('.login-btn').button('reset');
				if(data.success){
					window.location.href=data.message;
				}else{
					$('.check-code').click();
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
 	
 	$("img.lazy").lazyload({
		placeholder : "resources/images/service/default_service_pic.gif",
		effect : "fadeIn"  ,
		skip_invisible : false
	});
})



