
/**
 * 收藏服务
 * @author liuliping
 * @param id 服务id
 * */
function collect(obj, id) {
	if ($(obj).text() == '已收藏') {
		art.dialog({
			id : "d_service_collect",
		    title: '提示',
		    ok : function () {},
		    okValue : '关闭',
		    content: "已经收藏过此服务,去看看<a href='service/result' style='color:#0000FF;' target='_blank'>其他服务</a>",
		    fixed : true
		});
		return;
	}
	
	art.dialog({
			id : "d_service_collect",
		    title: '提示',
		    ok : function () {
		    	$.ajax({
					type : 'POST',
					url : 'myFavorites/add',
					async : false,
					data: {
						serviceId : id
					},
					dataType : 'json',
					beforeSend:function(){					
					},
					success : function(data) {
						if (data.success) {
							art.dialog({
								id : "d_service_collect2",
							    title: '提示',
							    ok : function () {},
							    okValue : '关闭',
							    content: data.message,
							    fixed : true
							});
							$(obj).text('已收藏');
							$(obj).addClass("active");
						}
					},
					failure : function(response) {
						art.dialog({
							id : "d_service_collect2",
						    title: '提示',
						    ok : function () {},
						    okValue : '关闭',
						    content: '服务器正忙,请稍后再试',
						    fixed : true,
						    lock : true
						});
			//			$.messager.alert('提示','服务器正忙,请稍后再试');
					}
				});
		    },
		    cancel : function () {},
		    okValue : '确定',
		    cancelValue : '取消',
		    content: "确定收藏该服务吗",
		    fixed : true,
		    lock : true
		});
	

}

/**
 * 分享 
 */
function share() {
	art.dialog({
		id : "d_service_collect",
	    title: '提示',
	    ok : function () {},
	    okValue : '关闭',
	    content: "暂未开放",
	    fixed : true
	});
}

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
})




