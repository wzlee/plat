var flag = true;
$(function(){
	$('#div1').click(function(){
		$('.register-content').hide();
		$('#register1').show();
		$('.type-tab li').removeClass('active');
		$(this).addClass('active');
		flag = true;
	});
	$('#div2').click(function(){
		$('.register-content').hide();
		$('#register2').show();
		$('.type-tab li').removeClass('active');
		$(this).addClass('active');
		flag = false;
	});
	$('#div3').click(function(){
		$('.register-content').hide();
		$('#register3').show();
		$('.type-tab li').removeClass('active');
		$(this).addClass('active');
	});
	
	
	$("form").submit(function(e){
		e.preventDefault();
    });
    $('#password','#password1').passwordStrength({
   	 	minLen:6,
     	maxLen:20
    });
    
   $("#enterprise_type").selecter({
        defaultLabel: $("#enterprise_type").find('option:first').text()
    });	    
    $("#enterprise_industry").selecter({
        defaultLabel: "请选择..."
    });

    function selectCallback(value, index) {
//        console.log("OPTION SELECTED: " + value + ", INDEX: " + index);
    }
	var jVal = {
		'username': function(){
			var ele = flag? $('#username') : $('#username1');
			//判断是否已经生成过元素，防止重复生成
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^[a-zA-Z][a-zA-Z0-9_]{1,}$/;
			if(ele.val()==''){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(ele.val().length <2 || ele.val().length >20){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('长度必须为2-20个字符');				
			}else if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('用户名不匹配(字母开头)');			
			}else{
				//info.removeClass('error').addClass('right').html('一旦注册成功不能修改');
			 	$.post(
		        		"user/checkname", 
		       			{ "username": ele.val() },
		       	   		function(data){
	           				if(data.success){
	           					jVal.errors = true;
	           					info.removeClass('error').addClass('right').html(data.message);
	           					ele.attr('data-status',2);
	           				}else{
	           					info.removeClass('right').addClass('error').html(data.message);
	           					ele.attr('data-status',1);
	           				}
		       	   		},
		       	   		"json"
					);
			}
		},
		'password': function(){
			var ele = flag ? $('#password') : $('#password1');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S{6,20}$/i;
			if(ele.val()==''){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请输入6-20位数字、字母或符号');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'confirm_password': function(){
			var pwd = flag ? $('#password') : $('#password1');
			var ele = flag ? $('#confirm_password') : $('#confirm_password1');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('此项为必填项');
			}
			else if(ele.val()!=pwd.val()){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('两次密码输入不一致');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'company_name': function(){
			var ele = $('#company_name');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(?!^\d+$).{2,50}$/;
			if(ele.val()==''){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('长度必须为2-50个字符的中文、英文或数字');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'email': function(){
			var ele = flag ? $('#email') : $('#email1');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
//			var patt = /^\w{1,15}(?:@(?!-))(?:(?:[a-z0-9-]*)(?:[a-z0-9](?!-))(?:\.(?!-)))+[a-z]{2,4}$/;
			if(ele.val()==''){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请输入有效的电子邮箱地址');
			}else{
				//info.removeClass('error').addClass('right').html('');
	        	$.post("user/checkEmail", 
	           			{ "email" :	ele.val()},
	           	   		function(data){
	           				if(data.success){
	           					jVal.errors = true;
	           					info.removeClass('error').addClass('right').html(data.message);
	           					ele.attr('data-status',2);
	           				}else{
	           					info.removeClass('right').addClass('error').html(data.message);
	           					ele.attr('data-status',1);
	           				}
	           	   		},
	           	   		"json");
			}
		},
		'enterprise_type': function(){
			var ele = $('#enterprise_type');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请选择所属窗口');
			}else{
//				console.log(ele.val());
				info.removeClass('error').addClass('right').html('');
			}
		},
		'enterpriseIndustry': function(){
			var ele = $('#enterprise_industry');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请选择所属行业');
			}else{
//				console.log(ele.val());
				info.removeClass('error').addClass('right').html('');
			}
		},
		'radio': function(){
			var ele = $('.user_radio');
			var flag = false;
			if(ele.siblings().length==0){
				ele.parent().parent().append('<label id="user_radio_msg" class="info"></label>');
			}
			var info = $('#user_radio_msg');
			ele.each(function(index){
				 if($(this).is(":checked")){
				 	flag = true;
				 }
			});
			if(flag){
				info.removeClass('error').addClass('right').html('');
			}else{
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请选择会员身份');
			}
		},
		'checkbox': function(){
			var ele = flag ? $('#checkbox') : $('#checkbox1');
			if($('#checkbox-info').length==0){
				ele.siblings().append('<label id="checkbox-info" class="info"></label>');
			}
			var info = $('#checkbox-info');
			if(ele.is(":checked")==false){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请接受服务条款');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'sendIt': function(loading){
			var r_egister = flag ? $('#register-form') : $('#register-form1');
			var user_name = flag ? $('#username') : $('#username1');
			var pass_word = flag ? $('#password') : $('#password1');
			var e_mail = flag ? $('#email') : $('#email1');
			var type = flag ? 1 : 2;
			if(!jVal.errors && user_name.attr('data-status')==2 && e_mail.attr('data-status')==2){
				$(loading).button('loading');
				
				r_egister.ajaxSubmit({
//					timeout:10000,
					type: "post",
					url: "user/register_step_2?type="+type,
					data:{'password':$.md5($.trim(pass_word.val()))},
					dataType: "json",
					success: function(data){
						$(loading).button('reset');
						if(data.success){
							window.location.href='user/register_success?enterpriseType='+data.message;
						}else{
							alert(data.message);
						}
					},
					error:function(a,b,c){
						art.dialog({
					    	title: '提示',
					    	content: '系统繁忙，请稍后再试!!!',
					    	ok: function () {},
					    	okValue:'确定'
						});
						$(loading).button('reset');
					}
				});
			}
		}
	};
	$('#commit').click(function(){
		jVal.errors = false;
		jVal.usernameError = false;
		jVal.username();
		jVal.password();
		jVal.confirm_password();
		jVal.company_name();
		jVal.enterprise_type();
		jVal.enterpriseIndustry();
		jVal.email();
		jVal.checkbox();
//		jVal.radio();
//		$(this).button('loading');
		jVal.sendIt(this);
		return false;
	});
	$('#commit1').click(function(){
		jVal.errors = false;
		jVal.usernameError = false;
		jVal.username();
		jVal.password();
		jVal.confirm_password();
		/*jVal.company_name();
		jVal.enterprise_type();*/
		jVal.email();
		jVal.checkbox();
		/*jVal.radio();*/
//		$(this).button('loading');
		jVal.sendIt(this);
		return false;
	});
	$('#username,#username1').blur(jVal.username);
	$('#password,#password1').blur(jVal.password);
	$('#confirm_password,#confirm_password1').blur(jVal.confirm_password);
	$('#company_name').blur(jVal.company_name);
	$('#enterprise_type').change(jVal.enterprise_type);
	$('#enterprise_industry').change(jVal.enterpriseIndustry);
	$('#email,#email1').blur(jVal.email);
	$('.user_radio').change(jVal.radio);
	$('#checkbox').blur(jVal.checkbox);
})
$(function(){
	$('.rule-link').click(function(){
		var dialog = art.dialog({
			width: 600,
			height: 400,
			fixed: true,
			lock: true,
			resize: false,
			title: '深圳市中小企业服务平台条款',
			button:[{
				value:'关闭',
				callback:function(){
					return true;
				}	
			}]
		}); 
		$.ajax({
		    url: '/test/rule',
		    success: function (data) {
		        dialog.content(data);
		    },
		    cache: false
		});
	})
})