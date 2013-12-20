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
	
	//全部机构分类
//	var actt = $('.organization-cate-list').find('li.active').index();
	var bread = $('span[class=current]');
	var lev1 = $('.organization-cate-list').find('li:contains("' + $(bread[0]).text() + '")').addClass("active");
	var actt = $('.organization-cate-list > li').index(lev1);
	$('.organization-cate-sub li').eq(actt).addClass('active').show();
	//第二层加样式
	var lev2 = $('.organization-cate-sub li').eq(actt);
	if (!bread[1]) {    //面包屑没有子服务类别时,给相应的标签加样式
		$(lev2).find('a:contains("全部")').addClass('active');
	} else {	 //面包屑有子服务类别时,给相应的标签加样式
		$(lev2).find('a:contains("' + $(bread[1]).text() + '")').addClass('active');
	}
	
	//过滤结果
	var $filter_form = $('.result-search form'); 
	var selects = $('.select-drop');
	var select_industryType_value = $(selects[0]).attr('value');
	var select_businessPattern_value = $(selects[1]).attr('value');
	$('option[value=' + select_industryType_value + ']', selects[0]).attr('selected', 'selected');
	$('option[value=' + select_businessPattern_value + ']', selects[1]).attr('selected', 'selected');
	$('.select-drop').bind('change', function () {
		var index = $('.select-drop').index(this);
		var param;
		if (index == 0) {
			$('input[name=industryType]', $($filter_form)).attr('value', this.value);
		} else {
			$('input[name=businessPattern]', $($filter_form)).attr('value', this.value);
		}
		$($filter_form).submit();
		return false;
	});
	$(':button', $($filter_form)).bind('click', function () {
		$('input[name=industryType]', $($filter_form)).attr('value', selects[0].value);
		$('input[name=businessPattern]', $($filter_form)).attr('value', selects[1].value);
		$($filter_form).submit();
		return false;
	});
	
	//图片懒加载
	$("img.lazy").lazyload({
		placeholder : "img/grey.gif",
		effect : "fadeIn"  
	});
})
