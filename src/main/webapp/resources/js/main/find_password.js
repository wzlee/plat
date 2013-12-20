$(function(){
	var f = [false, false];
	/*检查用户名  */
	var username = $('#username');
	//判断是否已经生成过元素，防止重复生成
	if(username.siblings().length==0){
		username.parent().append('<label class="info"></label>');
	}
	var info = username.siblings('label');
	var patt = /^\S{2,20}$/i;
	username.blur(function(){
		if(username.val()==''){
			info.removeClass('right').addClass('error').html('此项为必填项');
		}else if(!patt.test(username.val())){
			info.removeClass('right').addClass('error').html('长度必须为2-20个字符的英文或数字');
		}else if($.trim(username.val())!=''){
			$.post("user/checkname", 
				{ "username": username.val() },
		   		function(data){
					if(data.success){
						info.removeClass('right').addClass('error').html('会员名不存在');
					}else{
						info.removeClass('error').addClass('right').html('');;
					}
		 			f[0] = !data.success;
		   		},
		   		"json");
		}else{
//			$('#username').focus();
			f[0] =  false;
		}
	});
	/*检查用户名 和邮箱是否匹配 */
	var email = $('#email');
	if(email.siblings().length==0){
		email.parent().append('<label class="info"></label>');
	}
	var email_info = email.siblings('label');
	var email_patt = /^\w{1,15}(?:@(?!-))(?:(?:[a-z0-9-]*)(?:[a-z0-9](?!-))(?:\.(?!-)))+[a-z]{2,4}$/;
	email.blur(function(){
		if($.trim($('#username').val())!=''){
			if(!email_patt.test(email.val())){
				email_info.removeClass('right').addClass('error').html('请输入有效的电子邮箱地址');
			}else if($.trim(email.val())!=''){
	  			$.post("user/nameMateEmail", 
	 				{ 
		  				"username": $('#username').val() ,
		  		  		"email" :	email.val()
	 				},
	 	   			function(data){
		 				if(data.success){
		 					email_info.removeClass('error').addClass('right').html('');
		 				}else{
//		 					$('#email').focus();
		 					email_info.removeClass('right').addClass('error').html('邮箱与会员名不匹配!');
		 				}
		 	 			f[1] = data.success;
	 	   			},
	 	   			"json");
				}else{
//					$('#email').focus();
					f[1] =  false;
				}
			}else{
//				$('#username').focus();
				f[1] =  false;
			}
		});
  
		/* 表单提交 */
	$('.register-btn').click(function(){
		$('#register-form').ajaxSubmit({
			beforeSubmit: function(){
				if($.trim($('#username').val())==''){
					/*$('#errorMsg').text('用户名不能为空');
					$('#errorMsg').show();*/
					$('#username').focus();
					return false;
				}else if($.trim($('#email').val())==''){
					/*$('#errorMsg').text('邮箱不能为空');
					$('#errorMsg').show();*/
					$('#email').focus();
					return false;
				}else{
					if(f[0]&&f[1]){
						$('.register-btn').button('loading');
						return true;
					}else{
						/*$('#errorMsg').text('请确认会员名与邮箱是匹配的');
						$('#errorMsg').show();*/
						return false;
					}
				}
			},
			type: "post",
			url: "/user/handelfind_password",
			dataType: "json",
			success: function(data){
				$('.register-btn').button('reset');
				if(data.success){
					if(direction==''){
				  		alert(data.message);
				   	 	window.location.href='/';
					}else{
				  		alert(data.message);
				   		window.location.href=direction;
					}
				}else{
					alert(data.message);
				}
		     }
		 });
		 return false;
	});
	/*$("form").submit(function(e){
		e.preventDefault();
	});*/
})