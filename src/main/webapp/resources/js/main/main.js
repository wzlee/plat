$(function(){
	/*鼠标滑动显示下拉列表*/
	$('.selected').hover(function() {
		$(this).children('.select-list').show();
	}, function() {
		$(this).children('.select-list').hide();
	});
	/*导航滑动*/
	$('.top-nav > ul > li').hover(function() {
		$(this).addClass('active');
		$(this).children('.sub-nav').show();
	}, function() {
		$(this).removeClass('active');
		$(this).children('.sub-nav').hide();
	});
	//会员中心导航
	$(".sidebar li").bind("click",function(){
		$(this).children('.mod-sub-cate').slideToggle('200');
	});
		
	/*选择搜索*/
	$('.select-list li').click(function(event) {
		$('.selected-choose').children('.txt').text($(this).text());
		var display= $(this).data('display');
		var index = $(this).data('index');
		$('#search-input').attr('placeholder', '搜索'+display);
		if ($(this).text() == '机构') {
			$('.search-box > form').attr('action', 'enter/search?search_index=2');
		} else if($(this).text() == '政策'){
			$('.search-box > form').attr('action', 'policy/index?search_index=3');
		} 
		else { 
			$('.search-box > form').attr('action', 'service/result?search_index=1');
		}
		$(this).parent().hide();
	});
	/*回到顶部*/
	$('body').append('<a id="toTop"></a>');
	$(window).scroll(function() {
		if($(this).scrollTop() != 0) {
			$('#toTop').fadeIn();	
		} else {
			$('#toTop').fadeOut();
		}
	});
	$('#toTop').click(function() {
		$('body,html').animate({scrollTop:0},800);
	});
	/* 切换搜索服务和搜索机构*/
	
	/* 全局拦截ajax下Session过期的问题 */
	$(document).ajaxSuccess(function(event, response, ajaxOptions){
		if(response && response.getResponseHeader){
			var sessionStatus = response.getResponseHeader('sessionstatus');
			if (sessionStatus) {
				art.dialog({
				    title: '提示',
				    ok : function () {window.location = '/login';},
				    okValue : '登录',
				    content: '您没有登录或是会话超时，请重新登录!',
				    lock : true
				});
			}
		}
	});
	
})
	/**
	 * 没有图片及图片找不到时采用默认图片
	 * xuwf 2013-11-13
	 */
	function nofind(event){ 
		var img=$('.detailimg');
//		var img = event.srcElement;
//		img.src = "resources/images/service/default_service_pic.gif";
		$('.detailimg').attr('src','resources/images/service/default_service_pic.gif');
		img.onerror = null;
	} 