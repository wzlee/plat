$(function(){
	/*服务列表*/
	$('.service-list li').hover(function(){
		$(this).children('.apply').show();
	},function(){
		$(this).children('.apply').hide();
	});
	
	/* 左侧目录*/
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
})