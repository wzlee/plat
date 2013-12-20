$(function(){
	var roleId;		//保存子账号角色id
	var check = document.getElementsByName("roleName");  	
  	for(var i = 0; i < check.length; i ++){
  		check[i].onclick = function() {
  			var ele = $('#roleName');
  			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			info.removeClass('right').addClass('error').html('请至少选择一种角色');
			var input = document.getElementsByName("roleName");
			for(var i = 0; i < input.length; i ++){				
				if(input[i].checked){
					info.removeClass('error').addClass('right').html('');	
					
				}
			}
		};
		
  	}
	var jVal = {
		'userName': function(){
			var ele = $('#userName');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');			
			if( ele.val().length<2 || ele.val().length>25 ){
				info.removeClass('right').addClass('error').html('用户名长度为2-25个字符');				
			}else{
				//info.removeClass('error').addClass('right').html('一旦注册成功不能修改');
			 	$.post(
		        	"staff/checkStaffName", 
		       		{ "username": $('#userName').val(),
		       		  "parentId": $('#parentId').val()
		       		},
		       	   	function(data){
	           			if(data.success){
	           				jVal.errors = true;
	           				info.removeClass('error').addClass('right').html(data.message);
	           				
	           			}else{
	           				info.removeClass('right').addClass('error').html(data.message);
	           				
	           			}
		       	   	},
		       	   	"json"
				);
			}
//			else {
//				info.removeClass('error').addClass('right').html('');
//			};
		},
		'password': function(){
			var ele = $('#password');
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
		'confirmpassword': function(){
			var ele = $('#confirmpassword');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}			
			var info = ele.siblings('label');	
			var pwd = $('#password');
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
		'radio':function(){
			var ele = $('.separate-mini');
			var input = document.getElementsByName("roleName");			
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			info.removeClass('right').addClass('error').html('请至少选择一种角色');
			for(var i = 0; i < input.length; i ++){				
				if(input[i].checked){
					info.removeClass('error').addClass('right').html('');					
				}
			};		
		},
		// 添加子账号
		'submitStaff' : function(){
			$('.main-column form').ajaxSubmit({
				url : 'staff/save',
				type : 'post',
				dataType : 'json',
				data:{
					parentId:$("#parentId").val(),
					staffRoleId:$("input[name='roleName']:checked").val()
				},
				success : function(ret) {
					if(ret.success){
						$('#apply_ok').show();
					}else{
						art.dialog({
							title: '提示',
							content: ret.message,
							ok: function () {
							},
							okValue:'确定'
						});						
					}
				},
				error : function(ret) {
					art.dialog({
						title: '提示',
						content: '添加子账号失败',
						ok: function () {
						},
						okValue:'确定'
					});					
				}
			});	
		}
  	};
  	
  	$('#save').click(function() {
  		var lable = $('.auth-form-content .info');
		if(lable.length < 6){
			jVal.userName();
			jVal.password();
			jVal.confirmpassword();
			jVal.mobile();
			jVal.staffName();
			jVal.radio();
			return;
		}
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.submitStaff();		
	});
	$('#userName').blur(jVal.userName);
  	$('#password').blur(jVal.password);
  	$('#confirmpassword').blur(jVal.confirmpassword);
  	$('#staffName').blur(jVal.staffName);
  	$('#mobile').blur(jVal.mobile);
  
});