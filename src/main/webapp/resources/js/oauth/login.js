$(function(){
	$('.login-button').click(function(){
		$('#loginform').ajaxSubmit({
			beforeSubmit: function(){
				if($.trim($('#username').val())==''){
					$('#errorMsg').text('会员名不能为空!').show();
					$('#username').focus();
					return false;
				}else if($.trim($('#password').val())==''){
					$('#errorMsg').text('密码不能为空!').show();
					$('#password').focus();
					return false;
				}else if($.trim($('#authcode').val())==''){
					$('#errorMsg').text('验证码不能为空!').show();
					$('#authcode').focus();
					return false;
				}else{
					$('.login-button').button('loading');
		        	return true;
				}
			},
			dataType: "json",
			data:{
				username:$.trim($('#username').val()),
				password:$.md5($.trim($('#password').val())),
				authcode:$.trim($('#authcode').val())
			},
			success: function(data){
				$('.login-button').button('reset');
				if(data.success){
					window.location.href = data.message;
				}else{
					$('#errorMsg').text(data.message).show();
				}
			}
		})
		return false;
	});
})
