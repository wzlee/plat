$(function(){
    var f = false;
    /*检查邮箱 */
	var ele = $('#email');
	if(ele.siblings().length==0){
		ele.parent().append('<label class="info"></label>');
	}
	var info = ele.siblings('label');
	var patt = /^\w{1,15}(?:@(?!-))(?:(?:[a-z0-9-]*)(?:[a-z0-9](?!-))(?:\.(?!-)))+[a-z]{2,4}$/;
	ele.blur(function(){
		if(!patt.test(ele.val())){
			info.removeClass('right').addClass('error').html('请输入有效的电子邮箱地址');
		}else if($.trim(ele.val())!=''){
			$.post("user/checkEmail", 
				{ "email" :	ele.val()},
		   		function(data){
					if(!data.success){
						info.removeClass('error').addClass('right').html('');
					}else{
						info.removeClass('right').addClass('error').html('邮箱不存在!');
					}
		 			f = !data.success;
		   		},
		   		"json");
		}else{
			$("#Validform_checktip").show();
			f =  false;
		}
    });
    
	/* 表单提交 */
    $('.register-btn').click(function(){
		$('#register-form').ajaxSubmit({
			beforeSubmit: function(){
				if(!f){
					$('#email').focus();
					return false;
				}else{
					$('.register-btn').button('loading');
					return true;
				}
			},
			type: "post",
		 	url: "/user/handelfind_user",
		 	dataType: "json",
		 	success: function(data){
				if(data.success){
			 		$('.register-btn').button('reset');
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
	});
})