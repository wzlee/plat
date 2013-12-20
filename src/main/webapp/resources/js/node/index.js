
$(function(){
	/* 全局ajax默认设置*/
	$.ajaxSetup({
		timeout : 15000
	});

	/* 行业机构TOP10 */
	$('.column-org .list li:even').addClass('on');
	
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
	
	//幻灯片
	$('.box_skitter').skitter({
		theme: 'default',
		numbers_align: 'right',
		progressbar: false, 
		dots: true, 
		preview: false,
		hideTools: true,
		label: false
	});
	//服务列表导航
	$('.service-nav .icons .list li').hover(function(){
		$(this).addClass('on');
	},function(){
		$(this).removeClass('on');
	});
	
	/* 服务列表初始加载*/
	var start = 0;
	var limit = 18;
	loadService("'已上架'", "id", start, limit);
	start = start + limit;
	/* 加载更多服务*/
	$(".load-more a").click( function () {
		loadService("'已上架'", "id", start, limit); 
		start = start + limit;
	});
	
	/**
	 * @description ajax加载服务数据
	 * @param queryStatus 服务状态,如:'已上架'
	 * @param column 按某一列降序,如:'id'
	 * @param start 起始数字,如:0
	 * @param limit 分页限制,如:15
	 * */
	function loadService (queryStatus, column, start, limit) {
		var $loading = $('.load-more a');
		$loading.empty();
//		$loading.append('加载中...');
		$loading.append('<img src="resources/images/node/loading2.gif" style="position:relative;top:9px" />');
		$.post("service/getServiceData", { 
				"start" : start,
				"limit" : limit
			},
			function(data){
				var $loadMore = $(".load-more a");
				if (data.length == 0) {
					$loadMore.empty();
					$loadMore.append("已加载全部服务");
					return;
				}
				var html = '';
				$.each(data, function(i, n){
					if(n.picture.indexOf('http') > -1){
						html = html + '<li>' +
									'<a href="service/detail?&id='+n.id+'" target="_blank">'+
									'<img class="lazy" alt="'+n.serviceName+'" title="'+n.serviceName+'" src="resources/images/service-loading.gif" data-original="' + n.picture +'" width="232" height="200" />' +
									'</a>'+
										'<h3 class="name">' +n.serviceName + '</h3>' +
										'<h3 class="apply">' +
											'<a class="apply-btn" id="applyBtn" onclick="document.getElementById("applyBtn").onclick=null" href="service/detail?&id='+n.id+'" target="_blank">查看详情</a>' +
										'</h3>' +
									'</li>';
					}else {
						html = html + '<li>' +
									'<a href="service/detail?&id='+n.id+'" target="_blank">'+
									'<img class="lazy" alt="'+n.serviceName+'" title="'+n.serviceName+'" src="resources/images/service-loading.gif" data-original="upload/' + n.picture +'" width="232" height="200" />' +
									'</a>'+
										'<h3 class="name">' +n.serviceName + '</h3>' +
										'<h3 class="apply">' +
											'<a class="apply-btn" id="applyBtn" onclick="document.getElementById("applyBtn").onclick=null" href="service/detail?&id='+n.id+'" target="_blank">查看详情</a>' +
										'</h3>' +
									'</li>';
					}
					
				});
				$(".service-list ul").append(html);
				serviceApply();
				//服务列表申请
				$('.service-list ul li').hover(function(){
					$(this).children('h3.name').hide();
					$(this).children('h3.apply').show();
				}, function(){
					$(this).children('h3.name').show();
					$(this).children('h3.apply').hide();
				});
				if (data.length < limit) {
					$loadMore.empty();
					$loadMore.append("已加载全部服务");
				} else {
					$loading.empty();
					$loading.append('加载更多服务');
				}
		}, "json");
	}
	$("img.lazy").lazyload();
});