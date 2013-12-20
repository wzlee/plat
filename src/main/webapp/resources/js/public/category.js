window.onload = function(){
	$.ajax({
		url: '/category/findAllCategoryAndService',
		type: 'post',
		dataType: 'json',
		async: false,
		success: function(data){
			$('.all-cate-column').html(data.message);
			/* 左侧菜单 */
			$('.mod-cate li').hover(function(){
	var me = $(this);							 me.children('.name-wrap').children('h4').addClass('on');
					$(this).children('.mod-sub-cate').show();
				}, function(){
					$(this).children('.name-wrap').children('h4').removeClass('on');
					$(this).children('.mod-sub-cate').hide();
				}
　　　　　　);
		},
		failure: function(data){
			console.log(data.message);
		}
	});
}; 