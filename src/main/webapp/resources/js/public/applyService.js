
//function nofind(){ 
//	var img=$('.detailimg');
//	$('.detailimg').attr('src','resources/images/service/default_service_pic.gif');
//
//	img.onerror=null;
//} 

/****************************
 *申请服务窗口js	xuwf 2013-9-2
 ****************************
 */
//邮箱正则表达式 
var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
var mobileReg = /^1\d{10}$/;
var patt_name = /^\S{2,10}$/i;
var nullReg = /(^\s*)|(\s*$)/;
function serviceApply(){
	
	$('#apply-btn').click(function(){
		var $btn = $(this);
		if($('#yselogin').length>0){
			$('.apply-form input[name=sid]').val($btn.data('sid'));
	 		$('.apply-form .sname').html($btn.data('sname'));
	 		$('.apply-form .sprice').html($btn.data('sprice'));
	 		$('.apply-form .link-tel').val($btn.data('tel'));
	 		$('.apply-form .link-mail').val($btn.data('email'));
	 		/********************************************
	 		 * 设置联系电话和邮箱返回最开始的状态(当取消后,再次点击申请服务)
	 		 ********************************************
	 	
	 		$('.apply-form .linkTel').html('<span class="tel"></span><input class="link-tel" name="linkTel" type="hidden"><span class="help-inline apply-tel-info" data-label="联系电话"></span>');
			$('.apply-form .linkMail').html('<span class="email"></span><input class="link-mail" name="linkMail" type="hidden"><span class="help-inline apply-email-info" data-label="联系邮箱"></span>');
	 		$('.updateTel').html('修改');
	 		$('.updateEmail').html('修改');	
	 		$('.apply-name-info').html('');	
	 		$('.apply-form .tel').html($btn.data('tel'));
	 		$('.apply-form .email').html($btn.data('email'));
	 		//如果数据为'暂无'点击修改之后把文本框的清空
	 		if('暂无' == $btn.data('tel')){
	 			$('.apply-form .link-tel').val();
	 		}else{
		 		$('.apply-form .link-tel').val($btn.data('tel'));
	 			
	 		}
	 		if('暂无' == $btn.data('email')){
	 			$('.apply-form .link-mail').val();
	 		}else{
	 			$('.apply-form .link-mail').val($btn.data('email'));
	 		}
			 */

		if(isApply == 0){
			art.dialog({
			    title: '提示',
			    width:450,
			    lock:true,
			    content: '<span style="font-size:16px;">没有申请服务的权限！</span>',
			    button:[{
			    	value:'确定',
			    	callback:function(){
			    		return true;
			    	}
			    }]
			});
			return;	
		}
		if(userId == $('#serviceEnterId').val()){//登录企业
			art.dialog({
			    title: '提示',
			    width:450,
			    lock:true,
			    content: '<span style="font-size:16px;">不能购买自己的服务！</span>',
			    button:[{
			    	value:'确定',
			    	callback:function(){
			    		return true;
			    	}
			    }]
			});
			return;
		}
		if(enterType == 1){//实名认证才能申请服务
			art.dialog({
			    title: '提示',
			    width:450,
			    lock:true,
			    content: '<span style="font-size:16px;">实名企业才能申请服务,请先认证！</span>',
			    button:[{
			    	value:'立即认证',
			    	callback:function(){
			    		window.location.href = "/ucenter/auth?op=2";
			    	}
			    }]
			});
			return;
		}
			var applyDialog = art.dialog.get('apply-dialog');//服务申请窗口
			if(applyDialog == null){
				applyDialog = art.dialog({
					id:'apply-dialog',
					width:450,
					lock:true,
					title:'【'+$btn.data('sname')+'】服务申请',
					content:$('.apply-form').html(),
				 	initialize: function () {
				 		$('.link-name,.link-tel,.link-mail').blur(function(e){
				 			if(!patt_name.test($('.link-name').val())){
				 				$('.apply-name-info').html('2-10个中文或者英文字符!');
				 			}else{
				 				$('.apply-name-info').html('');
				 			}
				 			
				 			if($('.link-tel').val()==''){
					 			$('.apply-tel-info').html('联系电话不能为空!');
					 			
				 			}else{
				 				if(!mobileReg.test($('.link-tel').val()) && !telReg.test($('.link-tel').val())){
					 				$('.apply-tel-info').html('联系电话格式不正确,格式为固话或手机号码!');
					 			}else{
					 				$('.apply-tel-info').html('');
					 			}
				 			}
				 			if($('.link-mail').val()==''){
								$('.apply-email-info').html('邮箱地址不能为空!');
				 			}else{
				 				if(!emailReg.test($('.link-mail').val())){
									$('.apply-email-info').html('请输入有效的电子邮箱地址!');
						 		}else{
						 			$('.apply-email-info').html('');
						 		}
				 			}
				 		});
				    },
					button:[
				        	{
								value :'确认提交',
								callback : function(){
									if(!patt_name.test($('.link-name').val())){
										$('.apply-name-info').html('2-10个中文或者英文字符!');
										return false;
									}else if(!mobileReg.test($('.link-tel').val()) && !telReg.test($('.link-tel').val())){
										$('.apply-tel-info').html('联系电话格式不正确,格式为固话或手机号码!');
										return false;
									}else if(!emailReg.test($('.link-mail').val())){
										$('.apply-email-info').html('请输入有效的电子邮箱地址!');
										return false;
									}else{
										$.ajax({
											type : 'POST',
											url : 'order/applyService',
											data:{id:$('.apply-sid').val(),linkMan:$('.link-name').val(),tel:$('.link-tel').val(),email:$('.link-mail').val(),remark:$('.buyer-remark').val()},
											dataType : 'json',
											beforeSend:function(){
											},
											success : function(data) {
												if (data.success) {
													applyDialog.hidden();
													
													$('.apply-btn').addClass('hide');
													$('.enter-btn').removeClass('hide');
													showSubmitDia($btn);
//													art.alert(data.message);
												}else{
													art.alert(data.message);
												}
												
											},
											failure : function(response) {
												art.alert(response);
//												console.log(response);
											}
										});
									}
								},
								focus : true
							},
							{
								value :'取消',
								callback:function(){
									$('.apply-name-info').html('');
									$('.apply-tel-info').html('');
									$('.apply-email-info').html('');
									applyDialog.visible();
								}
							}
			        	]
				});
			}else{
				applyDialog.visible();
			}
		}else{
			art.dialog({
			    title: '提示',
			    width:450,
			    lock:true,
			    content: '<span style="font-size:16px;">申请服务需要登录,请先登录！</span>',
			    button:[{
			    	value:'立即登录',
			    	callback:function(){
			    		window.location.href = "/login";
			    	}
			    }]
//			    ok: function () {
//			        this.title('警告').content('请注意此窗口两秒后将关闭！').lock().time(2000);
//			        return false;
//    			}
			});
		}
	});
};

/**
 * 显示确认提交完毕窗口 
 * @param 
 */
function showSubmitDia(btn){
	var submitDialog = art.dialog.get('submit_dialog');
	if(null == submitDialog){
		submitDialog = art.dialog({
			id:'submit_dialog',
			width:450,
			title:'【'+btn.data('sname')+'】申请',
			content:$('.submit-form').html(),
			lock:true
			
		});
	}
	submitDialog.time(3000);	//3秒后自动关闭
}
/**
 * 联系电话可以修改
 */
function updateTel(){
	var $btn = $(this);
	$('.tel').remove();
	$('.link-tel').attr('type','text');
	$('.updateTel').html('');
};
/**
 * Email联系电话可以修改	
 */
function updateEmail(){
	var $btn = $(this);
	$('.email').remove();
	$('.link-mail').attr('type','text');
	$('.updateEmail').html('');
};

/**
 * 关闭提交确认窗口
 */
function closeApply(){
	var submitDialog = art.dialog.get('submit_dialog');
	submitDialog.hidden();
}
