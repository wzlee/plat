$(function(){
	//价格搜索
	$('.price-search').hover(function(){
		$(this).children('#rank-priceform').addClass('focus');
	},function(){
		$(this).children('#rank-priceform').removeClass('focus');
	})
	$('.price-search').find('.reset').click(function(){
		$('input.pri').val('');
	})
	var $swtich = $(".switch-control").find("a");
	
	/* 点击大图显示形式*/
	$($swtich[0]).on('click', function () { 
		var name = $(".current").text();
		$("input[name='name']").attr("value", name);
		$("input[name='type']").attr("value", 0);
		$(".search-box").find("form").submit();
	});
	
	/* 点击列表显示形式*/
	$($swtich[1]).on('click', function () { 
		var name = $(".current").text();
		$("input[name='name']").attr("value", name);
		$("input[name='type']").attr("value", 1);
		$(".search-box").find("form").submit();
	});
	
	/*服务结果按时间,申请数量,排序*/
	$(".nav-pills li > a").bind("click", function () {
		var order = null;
		var clazz = $(this).attr("class");
		switch (clazz) {
			case "serviceNum-order" : 
				order = "serviceNum";
				break;
			case "registerTime-order" : 
				order = "registerTime";
				break;
			case "costPrice-order" :
				order = "costPrice";
				break;
			default :
				order = null;
		}
		var currentCls = $("li[class=active]", $("ul[class=nav-pills]")).   
				children("a").attr('class');     //当前选中的排序方式
		var upOrDown = $("input[name='upOrDown']", $("form")).attr("value");
		if (clazz == currentCls) {    //当点击同一种排序方式，就切换降序和升序
			(upOrDown == 0) ? (upOrDown = 1) : (upOrDown = 0); 
		}
		var name = $(".current").text(); 
		$("input[name='name']", $("form")).attr("value", name);
		$("input[name='order']", $("form")).attr("value", order);
		$("input[name='upOrDown']", $("form")).attr("value", upOrDown);    //升序或者降序
		$(".search-box").find("form").submit();
		return false;
	});
	
	/*刷新页面时对部分样式的切换*/
	var order = $("input[name=order]", $("form")).attr("value");    //当前排序的方式
	var upOrDown = $("input[name='upOrDown']", $("form")).attr("value");
	var $a = $("." + order + "-order", $("ul > li"));
	$($a).parent().addClass("active");    //父节点切换样式
	upOrDown == 0 ? upOrDown = 'down' : upOrDown = 'up';
	$($a).children("i").removeClass();
	$($a).children("i").addClass(upOrDown);

	/*为价格区间确定按钮注册点击事件,提交隐藏了的表单*/
	$(":button[class=i]", $("#rank-priceform")).bind('click', function () {
		var $inputs = $(":input", $("#rank-priceform"));	
		var min = $inputs[0].value;
		var max = $inputs[1].value;
		var $inputs2 = $(":input[name=min], :input[name=max]", $("div[class=top-container]"));    //头部隐藏的表单中的input
//		console.debug();
		$inputs2[0].value = min;
		$inputs2[1].value = max;
		$($inputs2[0]).parent().submit();
	});
	
})