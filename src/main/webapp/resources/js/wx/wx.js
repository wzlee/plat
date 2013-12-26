$(function(){
	//绑定页面的用户名、密码、验证码验证
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
	
	//解除绑定
	$('#unbundling').click(function(){
		var openid = $(this).attr('openid');
		$.ajax({
		   	type: 'get',
		   	url: 'concernusers/unbundling',
		   	dataType: 'json',
		   	data: {
		   		id: $(this).attr('cuid'),
		   		role: 'user'
		   	},
		   	success: function(data){
		   		if(data.success){
		   			window.location.href = 'concernusers/pageSkip?page=wx/user_unbound_result&openid='+openid;
		   		} else {
		   			$('.hide').text(data.message).show();
		   		}
		   	}
		});
		
	});

	$('.load-more').click(function(){
		var type = $(this).attr('type');
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
		   		var datas = "";
		   		//服务列表加载更多
		   		if(type == '1'){
		   			datas = data.services;
		   			var html = [];
		   			for(var i = 0; i < datas.length; i++){
			   			var service = '<li class="clearfix">' +
					       	'<a href="wxpage/serviceDetail?id='+ datas[i].id +'" class="thumb">' +
								'<img src="upload/'+ datas[i].picture +'" alt="">' +
							'</a>' +
							'<div class="description">' +
								'<h3><a hhref="wxpage/serviceDetail?id='+ datas[i].id +'">'+ datas[i].serviceName +'</a></h3>' +
								'<div class="info">¥:<span class="red">'+ datas[i].costPrice +'</span></div>' +
								'<a href="wxpage/serviceDetail?id='+ datas[i].id +'" class="website"></a>' +
							'</div>' +
							'</li>';
		   				html.push(service);
		   			}
		   			
		   			$('.list-service').append(html.join(''));
		   		}
		   		//企业列表加载更多
		   		if(type == '2'){
		   			datas = data.enterprises;
		   			var html = [];
		   			for(var i = 0; i < datas.length; i++){
		   				var ms = datas[i].myServices;
		   				var enterprise = '<li class="clearfix">'+
						'<a href="" class="thumb"><img src="'+datas[i].photo+'" alt=""></a>'+
							'<div class="description">'+
								'<h3><a href="wxpage/orgDetail?id='+ datas[i].id +'">'+ datas[i].name +'</a></h3>'+
								'<div class="info">'+
									'<div class="mate-v"><img src="upload/'+datas[i].photo+'" alt=""></div>'+
									'<div class="row">'+
										'<div class="col"><span class="gray">提供服务：</span></div>';
										for(var j = 0; j < ms.length; j++){
											enterprise += ms[j].text + '&nbsp;&nbsp;&nbsp;';
										}
									enterprise += '</div>'+
								'</div>'+
								'<a href="wxpage/orgDetail?id='+ datas[i].id +'" class="website"></a>'+
							'</div>'+
						'</li>'
						html.push(enterprise);
					}
					$('.list-service').append(html.join(''));
				//政策列表加载跟多
	   			} else if(type == '3'){
	   				datas= data.policies;
   					var html = [];
		   			for(var i = 0; i < datas.length; i++){
			   			var policy = '<li class="clearfix">'+
							'<div class="description">'+
								'<h3><a href="wxpage/policyDetail?id='+datas[i].id+'">'+datas[i].title+'</a></h3>'+
							'<span class="pubdata">发布日期：'+datas[i].time+'</span>'+
							'</div>'+
						'</li>'
						html.push(policy);
		   			}
		   			$('.list-service').append(html.join(''));
		   		//平台动态
		   		}else if(type==4){
		   			//news
		   			datas= data.news;
   					var html = [];
		   			for(var i = 0; i < datas.length; i++){
			   			var newinfo = '<li class="clearfix">'+
							'<a href="wxpage/wXNewsDetail?id='+datas[i].id+'" class="thumb"><img src="upload/'+datas[i].picture+'" alt=""></a>'+
							'<div class="description">'+
								'<h3><a href="wxpage/wXNewsDetail?id='+datas[i].id+'">'+datas[i].title+'</a></h3>'+
								'<p>'+datas[i].description+'</p>'+
								'<span class="pubdata">发布日期：'+datas[i].pubdate+'</span>'+
							'</div>'+
							'</li>'
						html.push(newinfo);
		   			}
		   			$('.list-service').append(html.join(''));
		   			
		   		//用户中心我的招标
		   		}else if(type==5){
		   			//biddings
		   			datas = data.biddings;
		   			var html = [];
		   			for(var i =0;i<datas.length;i++){
		   				var bidinfo = '<div class="bid-result">'+
						'<ul>'+
							'<li><label for="">招标单号:</label>'+datas[i].bidNo+'</li>'+
							'<li><label for="">服务名称:</label>'+datas[i].name+'</li>'+
							'<li><label for="">服务类别:</label>'+datas[i].category.text+'</li>'+
							'<li><label for="">招标价格:</label>'+datas[i].minPrice+'-'+datas[i].maxPrice+'</li>'+
							'<li><label for="">状态:</label>'+datas[i].bidNo+'bidNo </li>'+
							'<li><label for="">创建时间:</label>'+datas[i].createTime+'createTime </li>'+
							'<li><label for="">联系人:</label>'+datas[i].linkMan+'linkMan </li>'+
							'<li><label for="">联系电话:</label>'+datas[i].tel+'tel </li>'+
							'<li><label for="">描述:</label>'+datas[i].description+' </li>'+
						'</ul>'+
					'</div>'
		   				html.push(bidinfo);
		   			}
		   			$('.bid-result').append(html.join(''));
		   		//用户中心我的收藏
		   		}else if(type==6){
		   			//favorties
		   			datas = data.favorties;
		   			var html = [];
		   			for(var i =0;i<datas.length;i++){
		   				var faverite = '<div class="pic fl">'+
						'<img src="upload/'+datas[i].picture+'" alt="">	'+				
						'</div>'+
						'<div class="info">'+
							'<div class="row">'+
								'<div class="col one">服务名称：</div>'+
								'<div class="col">'+datas[i].serviceName+'</div>'+
							'</div>'+
							'<div class="row">'+
								'<div class="col one">服务类别：</div>'+
								'<div class="col">'+datas[i].categoryName+'</div>'+
							'</div>'+
							'<div class="row ">'+
								'<div class="col one">收藏时间：</div>'+
								'<div class="col">'+datas[i].time+'</div>'+
							'</div>'+
							'<a href="" class="btn-button">取消收藏</a>'+
						'</div>'
		   				html.push(faverite);
		   			}
		   			$('.view-info').append(html.join(''));
		   			
		   		//用户中心我的服务
		   		}else if(type==7){
		   			//myservices
		   			datas = data.myservices;
		   			var html= [];
		   			for(var i =0;i<datas.length;i++){
		   				var myservice = '<div class="pic fl">'+
						'<img src="upload/'+datas[i].picture+'" alt="">'+				
						'</div>'+
						'<div class="info">'+
							'<div class="row">'+
								'<div class="col one">服务名称：</div>'+
								'<div class="col">'+datas[i].serviceName+'serviceName</div>'+
							'</div>'+
							'<div class="row">'+
								'<div class="col one">服务类别：</div>'+
								'<div class="col">'+datas[i].category.text+'</div>'+
							'</div>'+
							'<div class="row ">'+
								'<div class="col one">服务状态：</div>'+
								'<div class="col">'+
									'<c:if test="'+datas[i].currentStatus+'==3">'+
										'已上架'+
									'</c:if>'+
									'<c:if test="'+datas[i].currentStatus+'==4 ">'+
										'变更审核中'+
									'</c:if>'+
									'<c:if test="'+datas[i].currentStatus+'==7 ">'+
										'下架审核中'+
									'</c:if>'+
								'</div>'+
							'</div>'+
							'<div class="row ">'+
								'<div class="col one">价&nbsp;&nbsp;格：</div>'+
								'<div class="col">¥:<span class="red">'+datas[i].costPrice+'</span></div>'+
							'</div>'+
							'<div class="row ">'+
								'<div class="col one">添加时间：</div>'+
								'<div class="col">'+datas[i].registerTime+'}</div>'+
							'</div>'+
						'</div>	'
		   				html.push(myservice);
		   			}
		   			$('.view-info').append(html.join(''));
		   		}
		   		
		   		if(datas.length < 6){
		   			$('.load-more').hide();
		   		}
		   	}
		})
	});
	
	$('.search_load-more').click(function(){
		var type = $(this).attr('type');
		if(type == 'myService'){
			return myServiceLoad($(this));
		} else if(type == 'myBid'){
			return myBidLoad($(this));
		}
		$.ajax({
		   	type: 'get',
		   	url: 'wxpage/getMore',
		   	dataType: 'json',
		   	data: {
		   		type: type,
		   		limit: 6,
		   		start: $(this).attr('start'),
		   		title:$('#title').val()
		   	},
		   	success: function(data){
		   		var start = $('.search_load-more').attr('start');
		   		$('.search_load-more').attr('start', parseInt(start) + 6);
		   		var datas = "";
		   		//服务列表加载更多
		   		if(type == 'service'){
		   			datas = data.serviceList.data;
		   			var html = [];
		   			for(var i = 0; i < datas.length; i++){
			   			var service = '<li class="clearfix">' +
					       	'<a href="wxpage/serviceDetail?id='+ datas[i].id +'" class="thumb">' +
								'<img src="upload/'+ datas[i].picture +'" alt="">' +
							'</a>' +
							'<div class="description">' +
								'<h3><a hhref="wxpage/serviceDetail?id='+ datas[i].id +'">'+ datas[i].serviceName +'</a></h3>' +
								'<div class="info">¥:<span class="red">'+ datas[i].costPrice +'</span></div>' +
								'<a href="wxpage/serviceDetail?id='+ datas[i].id +'" class="website"></a>' +
							'</div>' +
							'</li>';
		   				html.push(service);
		   			}
		   			
		   			$('.list-service').append(html.join(''));
		   		//企业列表加载更多
		   		} else if(type == 'org'){
		   			datas = data.enterprises.data;
		   			var html = [];
		   			for(var i = 0; i < datas.length; i++){
		   				var ms = datas[i].myServices;
		   				var enterprise = '<li class="clearfix">'+
						'<a href="" class="thumb"><img src="'+datas[i].photo+'" alt=""></a>'+
							'<div class="description">'+
								'<h3><a href="wxpage/orgDetail?id='+ datas[i].id +'">'+ datas[i].name +'</a></h3>'+
								'<div class="info">'+
									'<div class="mate-v"><img src="upload/'+datas[i].photo+'" alt=""></div>'+
									'<div class="row">'+
										'<div class="col"><span class="gray">提供服务：</span></div>';			
										for(var j = 0; j < ms.length; j++){
											enterprise += ms[j].text + '&nbsp;&nbsp;&nbsp;';
										}
									enterprise += '</div>'+
								'</div>'+
								'<a href="wxpage/orgDetail?id='+ datas[i].id +'" class="website"></a>'+
							'</div>'+
						'</li>'
						html.push(enterprise);
					}
					$('.list-service').append(html.join(''));
				//政策列表加载跟多
	   			} else if(type == 'policy'){
	   				datas= data.policies.data;
   					var html = [];
		   			for(var i = 0; i < datas.length; i++){
			   			var policy = '<li class="clearfix">'+
							'<div class="description">'+
								'<h3><a href="wxpage/policyDetail?id='+datas[i].id+'">'+datas[i].title+'</a></h3>'+
							'<span class="pubdata">发布日期：'+datas[i].time+'</span>'+
							'</div>'+
						'</li>'
						html.push(policy);
		   			}
		   			$('.list-service').append(html.join(''));
		   		}
		   		
		   		if(datas.length < 6){
		   			$('.search_load-more').hide();
		   		}
		   	}
		})
	});
	
	//我的服务
	$('#myServiceBut').click(function(){
		myServiceLoad($(this));
	});
	
	//我的招标
	$('#myBidBut').click(function(){
		myBidLoad($(this));
	});
	
	//加载我的服务
	function myServiceLoad(obj){
		if(obj.attr('type') == 'myService'){
			var start = $('#start').val();
			if(start < 6){
				$('#start').val(6);
			}
		} else {
			$('#start').val(0);
		}
		$.ajax({
		   	type: 'get',
		   	url: 'wxpage/getMoreUserService',
		   	dataType: 'json',
		   	data: $('#myServiceForm').serialize(),
		   	success: function(data){
		   		if(obj.attr('type') == 'myService'){
			   		var start = $('#start').val();
			   		$('#start').val(parseInt(start) + 6);
		   		}
		   		var html = "";
		   		for(var i = 0; i < data.length; i++){
		   			html += '<div class="pic fl">' +
		   					'<img src="upload/'+ data[i].picture +'" alt="">' +
					'</div>' +
					'<div class="info">' +
						'<div class="row">' +
							'<div class="col one">服务名称：</div>' +
							'<div class="col">'+ data[i].serviceName +'</div>' +
						'</div>' +
						'<div class="row">' +
							'<div class="col one">服务类别：</div>' +
							'<div class="col">'+ data[i].category.text +'</div>' +
						'</div>' +
						'<div class="row ">' +
							'<div class="col one">服务状态：</div>' +
							'<div class="col">';
								data[i].currentStatus == 1 ? (html += '新服务') : null;
								data[i].currentStatus == 2 ? (html += '上架审核中') : null;
								data[i].currentStatus == 3 ? (html += '已上架') : null;
								data[i].currentStatus == 4 ? (html += '变更审核中') : null;
								data[i].currentStatus == 5 ? (html += '已删除') : null;
								data[i].currentStatus == 6 ? (html += '已下架') : null;
								data[i].currentStatus == 7 ? (html += '下架审核中') : null;
							html += '</div>' +
						'</div>' +
						'<div class="row ">' +
							'<div class="col one">价&nbsp;&nbsp;格：</div>' +
							'<div class="col">¥:<span class="red">'+ data[i].costPrice +'</span></div>' +
						'</div>' +
						'<div class="row ">' +
							'<div class="col one">添加时间：</div>' +
							'<div class="col">'+ data[i].registerTime +'</div>' +
						'</div>' +
					'</div>' +
					'<div class="hr-dotted"></div>';
	   			}
	   			if(obj.attr('type') == 'myService'){
			   		$('.view-info').append(html);
		   		} else {
	   				$('.view-info').html(html);
		   		}
	   			if(data.length < 6){
		   			$('.search_load-more').hide();
		   		}
		   	},
		   	error: function(){
		   		console.log("查询我的服务信息失败！");
		   	}
		});
	}

	function myBidLoad(obj){
		if(obj.attr('type') == 'myBid'){
			var start = $('#start').val();
			if(start < 6){
				$('#start').val(6);
			}
		} else {
			$('#start').val(0);
		}
		$.ajax({
		   	type: 'get',
		   	url: 'wxpage/getMoreUserBid',
		   	dataType: 'json',
		   	data: $('#myBidForm').serialize(),
		   	success: function(data){
		   		if(obj.attr('type') == 'myBid'){
			   		var start = $('#start').val();
			   		$('#start').val(parseInt(start) + 6);
		   		}
		   		var html = "";
		   		for(var i = 0; i < data.length; i++){
		   			html += '<div class="bid-result">' +
						'<ul>' +
							'<li><label for="">招标单号:</label>'+ data[i].bidNo +'</li>' +
							'<li><label for="">服务名称:</label>'+ data[i].name +'</li>' +
							'<li><label for="">服务类别:</label>'+ data[i].category.text +'</li>' +
							'<li><label for="">招标价格:</label>'+ data[i].minPrice +'-'+ data[i].maxPrice +'</li>' +
							'<li><label for="">状态:</label>';
								data[i].status == 1 ? (html += '待发布') : null;
								data[i].status == 2 ? (html += '待审核') : null;
								data[i].status == 3 ? (html += '平台退回') : null;
								data[i].status == 4 ? (html += '招标中') : null;
								data[i].status == 5 ? (html += '应标中') : null;
								data[i].status == 6 ? (html += '交易进行中') : null;
								data[i].status == 7 ? (html += '等待买家关闭') : null;
								data[i].status == 8 ? (html += '等待卖家关闭') : null;
								data[i].status == 9 ? (html += '申诉处理中') : null;
								data[i].status == 10 ? (html += '交易结束') : null;
								data[i].status == 11 ? (html += '招标取消') : null;
								data[i].status == 11 ? (html += '订单取消') : null;
							html += '</li>' +
							'<li><label for="">创建时间:</label>'+ data[i].createTime +'</li>' +
							'<li><label for="">联系人:</label>'+ data[i].linkMan +'</li>' +
							'<li><label for="">联系电话:</label>'+ data[i].tel +'</li>' +
							'<li><label for="">描述:</label>'+ data[i].description +'</li>' +
						'</ul>' +
					'</div>';
	   			}
	   			if(obj.attr('type') == 'myBid'){
			   		$('#myBidDiv').append(html);
		   		} else {
	   				$('#myBidDiv').html(html);
		   		}
	   			if(data.length < 6){
		   			$('.search_load-more').hide();
		   		}
		   	},
		   	error: function(){
		   		console.log("查询我的招标信息失败！");
		   	}
		});
	}

})

