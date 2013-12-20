$(function(){
	
	$("#industry_type").selecter({
        defaultLabel: $("#enterprise_type").find('option:first').text()
    });	    
    $("#org_industry").selecter({
        defaultLabel: "请选择..."
    });
    $("#org_property").selecter({
        defaultLabel: "请选择..."
    });
    
	var mobileReg = /^1\d{10}$/;
	var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var jVal = {
		'org_username': function(){
			var ele = $('#org-userName');
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
		'org_password': function(){
			var ele = $('#org-password');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S{6,20}$/i;
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('请输入6-20位数字、字母或符号');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'org_confirm_password': function(){
			var pwd = $('#org-password');
			var ele = $('#org-confirm_password');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}
			else if(ele.val()!=pwd.val()){
				info.removeClass('right').addClass('error').html('两次密码输入不一致');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'org_orgName': function(){
			var ele = $('#orgName');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S{5,50}$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('公司实名为5-50个字符');
			}else{
				$.getJSON('ucenter/validateEname', {name : ele.val(), eid : $('input[name="enterprise.id"]').val()}, function(data){
					if(data.success){
						info.removeClass('error').addClass('right').html('填写正确');
					}else{
						info.removeClass('right').addClass('error').html(data.message);
					}
				});
			}
		},
		'org_email': function(){
			var ele = $('#org_email');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
//			var patt = /^\w{1,15}(?:@(?!-))(?:(?:[a-z0-9-]*)(?:[a-z0-9](?!-))(?:\.(?!-)))+[a-z]{2,4}$/;
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('请输入有效的电子邮箱地址');
			}else{
	        	$.post("user/checkEmail", 
	           			{ "email" :	ele.val()},
	           	   		function(data){
	           				if(data.success){
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
		'org_icRegNumber': function(){
			var ele = $('#icRegNumber');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S{6,20}$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('组织机构号为6-20个字符');
			}else{
				$.getJSON('ucenter/validateIcRegNumber', 
					{icRegNumber : ele.val(), eid : $('input[name="enterprise.id"]').val()}, function(data){
					if(data.success){
						info.removeClass('error').addClass('right').html('填写正确');
					}else{
						info.removeClass('right').addClass('error').html(data.message);
					}
				});
			}
		},
		'org_industryType': function(){
//			var ele = flag? $('#org_industry') : $('#org_property');
			var ele = $('#org_industry');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('请选择行业');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'industry_type': function(){
//			var ele = flag? $('#org_industry') : $('#org_property');
			var ele = $('#industry_type');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('请选择窗口');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'org_propertyType': function(){
//			var ele = flag? $('#org_industry') : $('#org_property');
			var ele = $('#org_property');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('请选择机构性质');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'org_business': function(){
			var ele = $('#org_business');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(ele.val().length > 100){
				info.removeClass('right').addClass('error').html('长度不能超过100个字符');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'advantageServiceAreas': function(){
			var ele = $('#advantageServiceAreas');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(ele.val().length > 100){
				info.removeClass('right').addClass('error').html('长度不能超过100个字符');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'openedTime': function(){
			var ele = $('#date-input');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'totalAssets': function(){
			var ele = $('#totalAssets');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'turnover': function(){
			var ele = $('#turnover');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'profit': function(){
			var ele = $('#profit');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'legalRepresentative': function(){
			var ele = $('#legalRepresentative');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'legalRepresentativeMobile': function(){
			var ele = $('#legalRepresentativeMobile');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(!mobileReg.test(ele.val())){
				info.removeClass('right').addClass('error').html('手机格式不正确');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'legalRepresentativeEmail': function(){
			var ele = $('#legalRepresentativeEmail');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').addClass('error').html('此项为必填项');
			}else if(!emailReg.test(ele.val())){
				info.removeClass('right').addClass('error').html('邮箱格式不正确');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'generalManagerMobile': function(){
			var ele = $('#generalManagerMobile');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('error').html('');
				info.removeClass('right').html('');
			}else{
				if(!mobileReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('手机格式不正确');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			}
		},
		'generalManagerEmail': function(){
			var ele = $('#generalManagerEmail');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('error').html('');
				info.removeClass('right').html('');
			}else{
				if(!emailReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('邮箱格式不正确');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			}
		},
		'contactNameMobile': function(){
			var ele = $('#contactNameMobile');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('error').html('');
				info.removeClass('right').html('');
			}else{
				if(!mobileReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('手机格式不正确');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			}
		},
		'contactNameEmail': function(){
			var ele = $('#contactNameEmail');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('error').html('');
				info.removeClass('right').html('');
			}else{
				if(!emailReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('邮箱格式不正确');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			}
		},
		'orgPhone': function(){
			var ele = $('#orgPhone');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('error').html('');
				info.removeClass('right').html('');
			}else{
				if(!telReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('区号-固定电话');
				}else{
					info.removeClass('error').addClass('right').html('');
				}
			}
		},
		'sendIt':function(){
			var totalAssets =$('#totalAssets').val();
			var turnover = $('#turnover').val();
			var profit = $('#profit').val();
			if(totalAssets == null){
				$('#totalAssets').val(0);
			}
			if(turnover == null){
				$('#turnover').val(0);
			}
			if(profit == null){
				$('#profit').val(0);
			}
			$('#org-register-form').ajaxSubmit({
				dataType : 'json',
				success : function(ret) {
					if(ret.success){
						location.href = 'user/orgregister_success';
					}
				},
				error : function(ret) {
					alert(ret.message);
				}
			});
		}
	};
	$('#commit3').click(function(){
		jVal.org_username();
		jVal.org_password();
		jVal.org_confirm_password();
		jVal.org_orgName();
		jVal.org_email();
		jVal.org_industryType();
		jVal.org_propertyType();
		jVal.industry_type();
		jVal.org_icRegNumber();
		jVal.org_business();
		jVal.advantageServiceAreas();
		jVal.openedTime();
		jVal.legalRepresentative();
		jVal.legalRepresentativeMobile();
		jVal.legalRepresentativeEmail();
		jVal.generalManagerEmail();
		jVal.contactNameMobile();
		jVal.contactNameEmail();
		jVal.orgPhone();
		jVal.profit();
		jVal.totalAssets();
		jVal.turnover();
//		jVal.org_licenceDuplicate();
//		jVal.org_businessLetter();
		var lable = $('#org-register-form .info');
		for(var i=0;i<lable.length;i++){
			if(lable.eq(i).hasClass('error')) return;
		}
		jVal.sendIt();
	});
	$('#org-userName').blur(jVal.org_username);
	$('#org-password').blur(jVal.org_password);
	$('#org-confirm_password').blur(jVal.org_confirm_password);
	$('#orgName').blur(jVal.org_orgName);
	$('#org_email').blur(jVal.org_email);
	$('#org_industry').change(jVal.org_industryType);
	$('#org_property').change(jVal.org_propertyType);
	$('#industry_type').change(jVal.industry_type);
	$('#icRegNumber').blur(jVal.org_icRegNumber);
	$('#org_business').blur(jVal.org_business);
	$('#advantageServiceAreas').blur(jVal.advantageServiceAreas);
	$('#date-input').blur(jVal.openedTime);
	$('#legalRepresentative').blur(jVal.legalRepresentative);
	$('#legalRepresentativeMobile').blur(jVal.legalRepresentativeMobile);
	$('#legalRepresentativeEmail').blur(jVal.legalRepresentativeEmail);
	$('#generalManagerMobile').blur(jVal.generalManagerMobile);
	$('#generalManagerEmail').blur(jVal.generalManagerEmail);
	$('#contactNameMobile').blur(jVal.contactNameMobile);
	$('#contactNameEmail').blur(jVal.contactNameEmail);
	$('#orgPhone').blur(jVal.orgPhone);
	$('#totalAssets').blur(jVal.totalAssets);
	$('#turnover').blur(jVal.turnover);
	$('#profit').blur(jVal.profit);
});