$(function(){
	$('#uemail').click(function(){
		var infodialog = art.dialog.get('info-dialog');
		if(infodialog == null){
			infodialog = art.dialog({
				id:'info-dialog',
				width:350,
				lock:true,
				follow:$('#follow')[0],
				title:'修改邮箱',
				content:$('#success').html(),
				button:[
			        	{
			        		id: 'button-disabled',
							value :'确认',
							callback : function(){
								var me = this;
								var ele = $('#newemail');
								if(ele.siblings().length==1){
									ele.parent().append('<label class="info"></label>');
								}
								var info = ele.siblings('label.info');
								var patt = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
								if(!patt.test(ele.val())){
									info.removeClass('right').addClass('error').html("请输入有效的电子邮箱地址");
									return false;
								}else{
									me.button({
					                    id: 'button-disabled',
					                    value: '处理中...',
					                    disabled: true
					                 });
									$.post(
										"ucenter/checkEmail", 
										{ "email": $.trim($('#newemail').val()) ,"id":$('#userid').val()},
							   			function(data){
							     			if (data.success) {
												info.removeClass('error').addClass('right').html(data.message);
												me.button({
													id : 'button-disabled',
													value : '确认',
													disabled : false
												});
												// 刷新当前页面
												location.replace(location);
												art.dialog.get('info-dialog').close();
											}else{
												info.removeClass('right').addClass('error').html(data.message);
												me.button({
								                    id: 'button-disabled',
								                    value: '确认',
								                    disabled: false
								                 });
							     			}
							   			}, 
							   			"json");
									return false;
								};
						},
						focus : true
						},
						{
							value :'取消',
							callback:function(){
							}
						}
		        	]
			});
		}else{
			infodialog.visible();
		}
	});
	
//		console.log(systime);
//		console.log(sentime);
//		console.log((systime-sentime));
//		console.log(Math.ceil((systime-sentime)/1000));
//		console.log(Math.ceil(0.1));
	
	if((systime-sentime)<=600000){
		//灰色
		$('#sendemail').css("background-color", "#cccccc");
		$('#sendemail').attr("disabled", true);

		var speed = 1000; //速度
		var wait = Math.ceil((600000-(systime-sentime))/1000); //停留时间
		function updateinfo(){
			if(wait == 0){
				//姿色
				$('#sendemail').css("background-color", "#606");
				$('#sendemail').attr("disabled", false);
				$('#sendemail').html("发送邮箱验证码");
			}
			else{
				$('#sendemail').html(formatSeconds(wait)+"后重新获取...");
				wait--;
//				console.log(wait);
				window.setTimeout(updateinfo,speed);
			}
		}
		updateinfo();
	}
	
	//发送邮件验证
	$('#sendemail').click(function(){
		$('#sendemail').button('loading');
		$.post(
			"ucenter/sendEmail", 
			{ "id":$('#userid').val()},
   			function(data){
   				$('#sendemail').button('reset');
//     			$('#sendmessage').html(data.message);
   				if(data.success){
   					// 刷新当前页面
					location.replace(location);
   				}
//   			art.dialog.alert(data.message);
   				art.dialog({
				    title: '提示',
				    content: data.message,
				    ok: function () {
//				        this
//				        .title('确定')
//				        .content('请注意artDialog两秒后将关闭！')
//				        .lock()
//				        .time(2000);
//				        return false;
				    },
				    okValue:'确定'
				})
   			}, 
   			"json");
	});
	var jVal = {
		'newmail': function(){
			var ele = $('#newemail');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label.info');
			var patt = /^\w{1,15}(?:@(?!-))(?:(?:[a-z0-9-]*)(?:[a-z0-9](?!-))(?:\.(?!-)))+[a-z]{2,4}$/;
			if(!patt.test(ele.val())){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请输入有效的电子邮箱地址');
			}else{
				$('#submit').button('loading');
				$.post(
					"ucenter/checkEmail", 
					{ "email": $.trim($('#newemail').val()) ,"id":$('#userid').val()},
		   			function(data){
		     			$('#submit').button('reset');
		     			if(data.success){
		     				$('#success').hide();
							info.removeClass('error').addClass('right').html('');
		     			}else{
		     				$('#success').show();
							info.removeClass('right').addClass('error').html(data.message);
		     			}
		   			}, 
		   			"json");
			}
		},
		'sentIt': function(){
			if(!jVal.errors){
				console.log('hi');
			}
		}
	};
	$('#submit').click(function(){
		jVal.errors = false;
		jVal.newmail();
		return false;
	});
//	$('#newemail').blur(jVal.newemail);
})
//格式化分钟为时分  
function formatMinutes(minutes){  
    var day = parseInt(Math.floor(minutes / 1440));  
    var hour = day >0  
                   ?Math.floor((minutes - day*1440)/60)  
                   :Math.floor(minutes/60);  
    var minute = hour > 0  
                      ? Math.floor(minutes -day*1440 - hour*60)  
                      :minutes;  
    var time="";  
    if (day > 0) time += day + "天";  
    if (hour > 0) time += hour + "小时";  
    if (minute > 0) time += minute + "分钟";  
    return time;  
} 
//格式化秒数为时分秒  
function formatSeconds(seconds) {  
    if(seconds >0){  
        var minutes = Math.floor(seconds/60);  
        seconds = seconds - minutes * 60;  
        return formatMinutes(minutes) + (seconds > 0 ? seconds + "秒" : "");  
    }  
    return seconds;  
} 