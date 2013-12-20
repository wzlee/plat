//全部机构分类
$(function(){
	
		var act = $(this).find('li.active').index();
		$('.organization-cate-sub li').eq(act).show().addClass('active');
	
	$('.organization-cate-list li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var actives = $('.organization-cate-list li').index(this);
		$('.organization-cate-sub li').eq(actives).show().addClass('active').siblings().hide().removeClass('active');
		return false;
	})
	//显示效果切换
	$('.show-type li').click(function() {
		$(this).addClass('active').siblings().removeClass('active');
		var ons = $('.show-type li').index(this);
		if(ons==0){
			$('.search-items').children('div').removeClass('list-view').addClass('list-view-block');
			$('.search-items .list-view-block:nth-child(3n+5)').each(function(){
				$(this).addClass('last');
			});
		} else if(ons==1){
			$('.search-items').children('div').removeClass('list-view-block').addClass('list-view');
		}
//		var ons = $('.show-type li').index(this);
//		var href = $('.plist .on').attr('url');
//		if(ons==0){
//			location.href = href.replace(/view=\S{4}/,'view=icon');
//		} else if(ons==1){
//			location.href = href.replace(/view=\S{4}/,'view=list');
//		}
	});		//价格搜索
	$('.price-search').hover(function(){
		$(this).children('#rank-priceform').addClass('focus');
	},function(){
		$(this).children('#rank-priceform').removeClass('focus');
	})
	//列表图片默认值
	 $('.info img').error(function(){
 	   		 $(this).attr('src',"resources/images/main/block_1.gif");
	});
})

function filterService(paramstr){
	var href = $('.plist .on').attr('url');
	location.href = href.replace(/orderName=\S+/,paramstr);
}