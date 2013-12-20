$(function(){
	var flag = 0;	//判断密码是否修改过	1、未修改	2、已修改
	var jVal = {
		'newpwd': function(){			
			var ele = $('#newpwd');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}			
			var info = ele.siblings('label');					
			if(ele.val().length<6 || ele.val().length>15){
				info.removeClass('right').addClass('error').html('密码长度为6-15个字符');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'confirmnewpwd': function(){
			var ele = $('#confirmnewpwd');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}			
			var info = ele.siblings('label');	
			var pwd = $('#newpwd');
			if(ele.val() != pwd.val()){
				info.removeClass('right').addClass('error').html('两次输入的密码不一致');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'staffName': function(){
			var ele = $('#staffName');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}			
			var info = ele.siblings('label');					
			if(ele.val().length<2 || ele.val().length>25){
				info.removeClass('right').addClass('error').html('姓名长度为2-25个字符');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'mobile': function(){
			var ele = $('#mobile');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}			
			var info = ele.siblings('label');	
			var mobileReg = /^1\d{10}$/;
			var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
			if(ele.val() != ''){
			if(!mobileReg.test(ele.val()) && !telReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('联系电话格式不正确,格式为固话或手机号码');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		// 修改子账号
		'modifyStaff' : function(){
			$('.main-column form').ajaxSubmit({
				url : 'staff/editStaff',
				type : 'post',
				dataType : 'json',
				data:{
					flag:flag					
				},
				success : function(ret) {
					if(ret.success){
						$('#apply_ok').show();
					}else{
						$('#apply_error').show();
					}
				},
				error : function(ret) {
					art.dialog({
						title: '提示',
						content: '修改子账号失败',
						ok: function () {
						},
						okValue:'确定'
					});					
				}
			});	
		}
	}
	$('#save').click(function() {
		jVal.mobile();
		jVal.staffName();
		var lable = $('#edit .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
  		jVal.modifyStaff();	
	});
	
	$('#cancel').click(function() {
  		$('#detail').css('display','block');
		$('#edit').css('display','none');
		$('#btn').css('display','block');
		
	});
	
	$('#modifyPwdText').click(function(){//点击修改密码弹出修改密码弹出
  		$('#modify_pwd').show();
  	});
  	
	$('#modifyPwd').click(function(){//修改密码链接点击修改密码后显示‘红色信息’		
		jVal.newpwd();
		jVal.confirmnewpwd();
		var lable = $('#modify_pwd .info');		
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		$('#password').val($('#confirmnewpwd').val());//修改密码变更后 改变密码的数据 
		flag = 1;	//已修改
  		$('#modifyPwdText').text('密码已修改,请保存');
  		$('#modifyPwdText').css({'color':'red','cursor':'default'});
  		$('#modifyPwdText').unbind('click'); 
  		$('#modify_pwd').hide();
  	});
  	$('#staffName').blur(jVal.staffName);
  	$('#mobile').blur(jVal.mobile);
  	$('#newpwd').blur(jVal.newpwd);
  	$('#confirmnewpwd').blur(jVal.confirmnewpwd);
});
function showform(){
	$('#detail').css('display','none');
	$('#edit').css('display','block');
	$('#btn').css('display','none');
};
