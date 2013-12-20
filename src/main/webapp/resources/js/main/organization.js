//顶部自动切换
$(function(){
	var index = 0;
	var itemTimer;
	var itemNum = $('.special-list li').length;
	$('.special-list li').hover(function() {
		clearInterval(itemTimer);
		index = $(this).index();
		showItem(index);
	}, function() {
		autoSlider(index);
	});
	showItem(index);
	autoSlider(index);
	//自动播放
	function autoSlider(index){
		itemTimer = setInterval(function(){
			showItem(index);
			index++;
			index = (index == itemNum) ? 0 : index;
		},5000)
	}
	//显示元素
	function showItem(index){
		$('.special-item .item-box').eq(index).show().siblings('.item-box').hide();
	}
})
//全部机构分类
$(function(){
	var index = 0;
	var itemTimer;
	$('.organization-cate-list li').hover(function() {
		clearInterval(itemTimer);
		$(this).addClass('active').siblings('li').removeClass('active');
		index = $(this).index();
		showItem(index);
	}, function() {
		hideItem(index);
	});
	function showItem(index){
		$('.organization-cate-sub').show();
		$('.organization-cate-sub li').eq(index).show().siblings('li').hide();
	}
	function hideItem(index){
		$('.organization-cate-sub').hover(function() {
			$(this).addClass('active');
		}, function() {
			$(this).removeClass('active');
			$('.organization-cate-list li').eq(index).removeClass('active');
		});
		itemTimer = setInterval(function(){
			if($('.organization-cate-list li').eq(index).hasClass('active') && $('.organization-cate-sub').hasClass('active')){
				$('.organization-cate-list li').eq(index).show();
				$('.organization-cate-sub li').eq(index).show();
			}else{
				$('.organization-cate-list li').eq(index).removeClass('active');
				$('.organization-cate-sub li').eq(index).hide();
				$('.organization-cate-sub').hide();
			}
		},250)
	}
});
//var actt = $('.organization-cate-list').find('li.active').index();
//$('.organization-cate-sub li').eq(actt).addClass('active').show();
