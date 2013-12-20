$(function(){
	var jVal = {
		'oldPwd': function(){
			var ele = $('#oldPwd');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label.info');
			var patt = /^\S{6,20}$/i;
			if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请输入6-20位数字、字母或符号');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'newPwd': function(){
			var ele = $('#newPwd');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label.info');
			var patt = /^\S{6,20}$/i;
			if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请输入6-20位数字、字母或符号');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'verifyNewPwd': function(){
			var ele = $('#verifyNewPwd');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label.info');
			var patt = /^\S{6,20}$/i;
			if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请输入6-20位数字、字母或符号');	
			}
			else if(ele.val()!=$('#newPwd').val()){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('两次密码输入不一致');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'sentIt': function(){
			if(!jVal.errors){
				$('#submit').button('loading');
				$.post(
						"ucenter/upwd", 
						{ 
							"oldpassword": $.md5($.trim($('#oldPwd').val())) ,
							"newpassword": $.md5($.trim($('#newPwd').val())) ,
							"id":$('#userid').val()
						},
			   			function(data){
			   				$('#submit').button('reset');
			     			if(data.success){
			     				$('#success').show();
			     			}else{
			     				$('#success').hide();
			     				alert(data.message);
			     			}
			   			}, 
			   			"json");
			}
		}
	};
	$('#submit').click(function(){
		jVal.errors = false;
		jVal.oldPwd();
		jVal.newPwd();
		jVal.verifyNewPwd();
		jVal.sentIt();
		return false;
	});
	$('#oldPwd').blur(jVal.oldPwd);
	$('#newPwd').blur(jVal.newPwd);
	$('#verifyNewPwd').blur(jVal.verifyNewPwd);
});