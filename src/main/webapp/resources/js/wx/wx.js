$(function(){
	$('#username, #password, #authcode').keyup(function(){
		var v = $(this);
		if(v.val() != null){
			if(v.attr('id') == "username"){
				$('#w-username').text('').hide();
			} else if (v.attr('id') == "password"){
				$('#w-pwd').text('').hide();
			} else if (v.attr('id') == "authcode"){
				$('#w-check').text('').hide();
			}
		}
	});
	
	//微信用户和平台用户之前的绑定
	$('#bindUserBut').click(function(){
		$('#bindUserForm').ajaxSubmit({
			dataType: "json",
			beforeSubmit: function(){
				if($.trim($('#username').val())==''){
					$('#w-username').text('用户名不能为空!').show();
					$('#username').focus();
					return false;
				}else if($.trim($('#password').val())==''){
					$('#w-pwd').text('密码不能为空!').show();
					$('#password').focus();
					return false;
				}else if($.trim($('#authcode').val())==''){
					$('#w-check').text('验证码不能为空!').show();
					$('#authcode').focus();
					return false;
				}else{
					$('#bindUserBut').val('loading');
		        	return true;
				}
			},
			data:{
				username:$.trim($('#username').val()),
				password:$.md5($.trim($('#password').val())),
				authcode:$.trim($('#authcode').val())
			},
			success: function(data){
				$('#bindUserBut').val('绑定账号');
				if(data.success){
					window.location.href = 'concernusers/pageSkip?page=wx/user_bind_succeed&openid='+$('#openid').val();
				}else{
					$('#authcode').click();
					var type = data.backupfield;
					type == 'w-check' ? $('.check-code').click() : null;
					$('#'+type).text(data.message).show();
				}
			}
		})
		return false;
	});
	
	//取消绑定登录
	$('#resetBind').click(function(){
		window.location.href = 'concernusers/pageSkip?page=wx/index&openid='+$('#accreditLoginBut').attr('openid');
	});
	
	//授权登录
	$('#accreditLoginBut, #bindSuccessLoginBut').click(function(){
		var openid = $(this).attr('openid');
		$.ajax({
		   	type: 'get',
		   	url: 'concernusers/wxUserLogin',
		   	dataType: 'json',
		   	data: {
		   		openid: openid
		   	},
		   	success: function(data){
		   		if(data.success){
		   			window.location.href = 'concernusers/pageSkip?page=wx/index&openid='+openid;
		   		} else {
		   			$('.hide').text(data.message).show();
		   		}
		   	}
		});
	});
	
	
	$('.load-more').click(function(){
		$.ajax({
		   	type: 'get',
		   	url: 'wxpage/reqData',
		   	dataType: 'json',
		   	data: {
		   		type: $(this).attr('type'),
		   		limit: 6,
		   		start: $(this).attr('start'),
		   		data:$('.dataParam').attr('dataid')
		   	},
		   	success: function(data){
		   		var start = $('.load-more').attr('start');
		   		$('.load-more').attr('start', parseInt(start) + 6);
		   		var services;
		   		if($(this).attr('type')=='1'){
		   			services= data.services;
		   			var html = [];
		   			for(var i = 0; i < services.length; i++){
			   		var service = '<li class="clearfix">' +
						       	'<a href="wxpage/serviceDetail?id='+ services[i].id +'" class="thumb">' +
									'<img src="upload/'+ services[i].picture +'" alt="">' +
								'</a>' +
								'<div class="description">' +
									'<h3><a hhref="wxpage/serviceDetail?id='+ services[i].id +'">'+ services[i].serviceName +'</a></h3>' +
									'<div class="info">¥:<span class="red">'+ services[i].costPrice +'</span></div>' +
									'<a href="wxpage/serviceDetail?id='+ services[i].id +'" class="website"></a>' +
								'</div>' +
								'</li>';
		   			html.push(service);
		   			}
		   			$('.list-service').append(html.join(''));
		   		}
		   		if($(this).attr('type')=='2'){
		   			services= data.enterprises;
		   			var html = [];
		   			for(var i = 0; i < services.length; i++){
		   				var enterprise = '<li class="clearfix">'+
										'<a href="" class="thumb"><img src="'+services[i].photo+'" alt=""></a>'+
											'<div class="description">'+
												'<h3><a href="wxpage/orgDetail?id='+ services[i].id +'">${enterpriseItem.name }</a></h3>'+
												'<div class="info">'+
													'<div class="mate-v"><img src="upload/'+services[i].photo+'" alt=""></div>'+
													'<div class="row">'+
														'<div class="col"><span class="gray">提供服务：</span></div>'+				
														'<c:forEach items="${enterpriseItem.myServices }" var="myServiceItem"> '
								 							+services[i].photo+'&nbsp;&nbsp;&nbsp; '+
														'</c:forEach> '+
													'</div>'+
												'</div>'+
												'<a href="wxpage/orgDetail?id='+ enterprises[i].id +'" class="website"></a>'+
											'</div>'+
										'</li>'
							html.push(service);
						}
						$('.list-service').append(html.join(''));
		   			}
		   			if($(this).attr('type')=='3'){
		   				services= data.policies;
		   					var html = [];
			   			for(var i = 0; i < services.length; i++){
			   			var policy = '<li class="clearfix">'+
										'<div class="description">'+
											'<h3><a href="wxpage/policyDetail?id='+services[i].id+'">'+services[i].title+'</a></h3>'+
										'<p>'+services[i].text+'</p>'+
										'<span class="pubdata">发布日期：'+services[i].time+'</span>'+
										'</div>'+
									'</li>'
						html.push(service);
			   			}
			   			$('.list-service').append(html.join(''));
		   		}
		   		
		     	
		   	}
		})
		
	});
})

