
$(function(){
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
		},2000);
	}
	/*动画显示图片*/
	function showPic(index){
		$('.dom-box li').eq(index).animate({"opacity": 1, "z-index": 1}, 1000).siblings('li').animate({"opacity": 0, "z-index": 0}, 1000);
		$('.dom-dots span').eq(index).addClass('current').siblings('span').removeClass('current');
	}
})