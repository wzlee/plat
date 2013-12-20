$(function(){
//	/*鼠标滑动显示下拉列表*/ 此代码在用户中心ucenter/header.jsp中引用的main.js已存在，故注释.xuwf 2013-11-08
//	$('.selected').hover(function() {
//		$(this).children('.select-list').show();
//	}, function() {
//		$(this).children('.select-list').hide();
//	});
//	/*点击效果*/
//	$('.select-list li').click(function(event) {
//		console.log($('.selected-name').text($(this).text()));
//	});
	
	/*右侧内容区高度保持与左侧菜单高度一致*/
	/*var menu_height = $('.sidebar').height();
	*if($('.main-column').height() < 540){
	*	$('.main-column').height(menu_height);
	*}
	*/
	/************************end***************************/
	/***推荐服务切换效果***/
	$('.expre').click(function(){
		var lis = $('.change li');
		/*$.getJSON('ucenter/recommendChange?'+new Date(), function(services){
			if(services.length > 0){
				for(var i=0;i<lis.length;i++){
					lis.eq(i).find('.service-a').attr('href', 'service/detail?id=' + services[i].id);
					lis.eq(i).find('.a-link').html(services[i].serviceName);
					lis.eq(i).find('.service-img').attr('src', 'upload/' + services[i].picture);
					var text = services[i].serviceProcedure.replace(/<[^>]+>/g,'');
					lis.eq(i).find('.service-text').html(text.substring(0,90) + (text.length > 90 ? '...' : ''));
					lis.eq(i).find('.service-price').html('￥'+services[i].costPrice);
				}
			}
		});*/
		$.post('ucenter/recommendChange', function(data){
			var json = $.parseJSON(data);
			if(data.length > 0){
				$.each(json, function(i, n) {
					lis.eq(i).find('.service-a').attr('href', 'service/detail?id=' + n.service.id);
					lis.eq(i).find('.a-link').html(n.service.serviceName);
					if(n.service.picture.indexOf('http') > -1){
						lis.eq(i).find('.service-img').attr('src', n.service.picture);
					}else {
						lis.eq(i).find('.service-img').attr('src', 'upload/' + n.service.picture);
					}					
					var text = n.service.serviceProcedure.replace(/<[^>]+>/g,'');
					lis.eq(i).find('.service-text').html(text.substring(0,90) + (text.length > 90 ? '...' : ''));
//					lis.eq(i).find('.service-price').html('￥'+n.service.costPrice);
					if(n.collected == true) {    //服务已收藏
						lis.eq(i).find('.service-price').html('￥'+n.service.costPrice + '<div class="collect"><a class="active" href="javascript:void(0);">已收藏</a></div>');
					} else {
						lis.eq(i).find('.service-price').html('￥'+n.service.costPrice + '<div class="collect"><a onclick="collect(this, ' + n.service.id + ');" href="javascript:void(0);">收藏</a></div>');
					}
				});
				
			}
		});
	});
	
	/************************我感兴趣的领域*****************************/
	$('.common-head .interesting_a').click(function(){
		$('.interesting').show();
	});
	$('.service-head .close').click(function() {
		$('.interesting').hide();
	});
	//隔行换色
	$('.service-body .service-row:even').addClass('row-even');
	//点击服务条目
	$(document).on('click', '.service-list li span', function(event){
		var input = $(this).prev();
		if(input.is(':checked')){
			var selected = $('.selected-list').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}else{
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list');
		}
	});
	$(document).on('click', '.service-list li input', function(event){
		var input = $(this);
		if(input.is(':checked')){
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list');
		}else{
			var selected = $('.selected-list').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}
	});
	//点击已选
	$(document).on('click', '.selected-list li', function(event) {
		var input = $('.service-list').find("input[name='"
						+ $(this).children('input').attr('name') + "']");
		input.attr('checked', false);
		if(!input.is(':checked')) $(this).remove();
	});
	$('.save').click(function(){
		var checkBoxs = $('.selected-list input[type="checkbox"]');
		var ids='';
		for(var i =0;i<checkBoxs.length;i++){
			ids+=checkBoxs[i].value+',';
		}
		$.post(
			'ucenter/saveInteresServiceInfo',
			{'ids':ids,'uid':$('#userId').val()},
			 function(data) {
			 	$('.interesting').hide();
				if(data.success){
					jSuccess(data.message,{VerticalPosition:'center',  onClosed : function(){
							window.location.reload(false); }});
				}else{
					jError(data.message,{VerticalPosition:'center',  onClosed : function(){
							window.location.reload(false); }});
				}
			},'json'
		);
	});
	/************************end 我感兴趣的领域*****************************/
	/*********************我提供的服务领域**************************/
	$('.common-head .myservice_a').click(function(){
		$('.myservice').show();
	});
	$('.service-head-my .close').click(function() {
		$('.myservice').hide();
	});
	//隔行换色
	$('.service-body-my .service-row-my:even').addClass('row-even-my');
	//点击服务条目
	$(document).on('click', '.service-list-my li span', function(event){
		var input = $(this).prev();
		if(input.is(':checked')){
			var selected = $('.selected-list-my').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}else{
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			if(checkBoxs.length >= 10){
				alert('最多选择10个服务领域'); return;
			}
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list-my');
		}
	});
	$(document).on('click', '.service-list-my li input', function(event){
		var input = $(this);
		if(input.is(':checked')){
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			if(checkBoxs.length >= 10){
				alert('最多选择10个服务领域'); 
				input[0].checked = false;
				return;
			}
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list-my');
		}else{
			var selected = $('.selected-list-my').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}
	});
	//点击已选
	$(document).on('click', '.selected-list-my li', function(event) {
		var input = $('.service-list-my').find("input[name='"
						+ $(this).children('input').attr('name') + "']");
		input.attr('checked', false);
		if(!input.is(':checked')) $(this).remove();
	});
	$('.save-my').click(function(){
		var checkBoxs = $('.selected-list-my input[type="checkbox"]');
		var ids='';
		for(var i =0;i<checkBoxs.length;i++){
			ids+=checkBoxs[i].value+',';
		}
		$.post(
			'ucenter/saveMyServiceInfo',
			{'ids':ids,'uid':$('#userId').val()},
			 function(data) {
			 	$('.myservice').hide();
				if(data.success){
					jSuccess(data.message,{VerticalPosition:'center',  onClosed : function(){
							window.location.reload(false); }});
				}else{
					jError(data.message,{VerticalPosition:'center',  onClosed : function(){
							window.location.reload(false); }});
				}
			},'json'
			
		);
	});
	/************************end 我提供的服务领域*****************************/
	
	$('.save-personalservice').click(function(){
		var checkBoxs = $('.selected-list-my input[type="checkbox"]');
		var ids='';
		for(var i =0;i<checkBoxs.length;i++){
			ids+=checkBoxs[i].value+',';
		}
		$.post(
			'ucenter/saveUserServiceInfo',
			{'ids':ids,'uid':$('#userId').val()},
			 function(data) {
				if(data.success){
					jSuccess(data.message,{VerticalPosition:'center',  onClosed : function(){
							window.location.reload(false); }});
				}else{
					jError(data.message,{VerticalPosition:'center',  onClosed : function(){
							window.location.reload(false); }});
				}
			},'json'
			
		);
	});
});