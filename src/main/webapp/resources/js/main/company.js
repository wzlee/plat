$(function(){
	var $fa = $('.follow a');
	
	if(isLogin){
		if($('#isattention').val()=='true'){
			$fa.html('已关注');
			$fa.addClass("attentionservice");
		}else{
			$fa.html('关注该机构');
			$fa.removeClass("attentionservice");
		}
	}else{
		$fa.html('关注该机构');
		$fa.removeClass("attentionservice");
	}
	
	$('.attentionservice').hover(
		function(){
			$(this).html('取消关注');
  		},
  		function () {
			$(this).html('已关注');
  		}
	);
	
	$fa.click(function(){
		/*if(!isLogin){
			art.dialog({
			    title: '提示',
			    width:450,
			    lock:true,
			    content: '<span style="font-size:16px;">关注机构需要登录,请先登录！</span>',
			    button:[{
			    	value:'立即登录',
			    	callback:function(){
			    		window.location.href = "/login";
			    	}
			    }]
			});
			return;
		}*/
		if ($fa.text() == '取消关注'){
			art.dialog({
				id : "attentionservice",
			    title: '提示',
			    ok : function () {
			    	$.ajax({
						type : 'POST',
						url : 'service/cancelattention',
						async : false,
						data: {
							'attentionid':$('#attentionid').val(),
							'beattentionid':$('#beattentionid').val()
						},
						dataType : 'json',
						beforeSend:function(){					
						},
						success : function(data) {
							if (data.success) {
//								art.dialog({
//									id : "attentionservice",
//								    title: '提示',
//								    ok : function () {},
//								    okValue : '关闭',
//								    content: data.message,
//								    fixed : true
//								});
//								$fa.text('已关注');
//								$fa.addClass("attentionservice");
								location.reload()
							}
						},
						failure : function(response) {
							art.dialog({
								id : "attentionservice",
							    title: '提示',
							    ok : function () {},
							    okValue : '关闭',
							    content: '服务器正忙,请稍后再试',
							    fixed : true,
							    lock : true
							});
						}
					});
			    },
			    cancel : function () {},
			    okValue : '确定',
			    cancelValue : '取消',
			    content: "确定取消关注该服务机构吗",
			    fixed : true,
			    lock : true
			});
			return;
		}else{
			if($('#attentionid').val()==$('#beattentionid').val()){
				console.log($('#attentionid').val()+'=='+$('#beattentionid').val());
				art.dialog({
					id : "attentionservice",
				    title: '提示',
				    ok : function () {},
				    okValue : '关闭',
				    content: '不能关注自己的服务机构',
				    fixed : true,
				    lock : true
				});
			}else{
				art.dialog({
					id : "attentionservice",
				    title: '提示',
				    ok : function () {
				    	$.ajax({
							type : 'POST',
							url : 'service/addattention',
							async : false,
							data: {
								'attentionid':$('#attentionid').val(),
								'beattentionid':$('#beattentionid').val()
							},
							dataType : 'json',
							beforeSend:function(){					
							},
							success : function(data) {
								if (data.success) {
//									art.dialog({
//										id : "attentionservice",
//									    title: '提示',
//									    ok : function () {},
//									    okValue : '关闭',
//									    content: data.message,
//									    fixed : true
//									});
//									$fa.text('已关注');
//									$fa.addClass("attentionservice");
									location.reload();
								}
							},
							failure : function(response) {
								art.dialog({
									id : "attentionservice",
								    title: '提示',
								    ok : function () {},
								    okValue : '关闭',
								    content: '服务器正忙,请稍后再试',
								    fixed : true,
								    lock : true
								});
							}
						});
				    },
				    cancel : function () {},
				    okValue : '确定',
				    cancelValue : '取消',
				    content: "确定关注该服务机构吗",
				    fixed : true,
				    lock : true
				});
			}
		}
	
	});
	
	//公司信息收缩功能
	$('.control-action li.s1').click(function() {
		$(this).hide().siblings('li').show();
		$('#show-intro').removeClass('short-show');
	});
	$('.control-action li.s2').click(function() {
		$(this).hide().siblings('li').show();
		$('#show-intro').addClass('short-show');
	});
	 //服务列表显示方式切换
	$('.show-type li').click(function() {
//		$(this).addClass('active').siblings().removeClass('active');
//		var ons = $('.show-type li').index(this);
//		if(ons==0){
//			$('.s-list-show > ul').removeClass('list-show').addClass('list-show-block');
//		} else if(ons==1){
//			$('.s-list-show > ul').removeClass('list-show-block').addClass('list-show');
//		}
		var ons = $('.show-type li').index(this);
		var href = $('.plist .on').attr('url');
		if(ons==0){
			location.href = href.replace(/view=\S{4}/,'view=icon');
		} else if(ons==1){
			location.href = href.replace(/view=\S{4}/,'view=list');
		}
	});	
	//价格搜索
	$('.price-search').hover(function(){
		$(this).children('#rank-priceform').addClass('focus');
	},function(){
		$(this).children('#rank-priceform').removeClass('focus');
	})
//	//列表图片默认值
//	 $('.info img').error(function(){
// 	   		 $(this).attr('src',"resources/images/main/block_1.gif");
//	});
})

function filterService(paramstr){
	var href = $('.plist .on').attr('url');
	location.href = href.replace(/orderName=\S+/,paramstr);
}