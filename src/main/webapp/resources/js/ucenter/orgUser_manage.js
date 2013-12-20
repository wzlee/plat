$(function() {
	/******************************************************
	 * 左侧ul下卖家管理中心悬停事件显示所属菜单
	 * @author xuwf
	 * @since 2013-9-6
	*/
	//select下拉样式美化
	$(".se0").selecter({
        defaultLabel: $(".se0").find('option:selected').text()
    });	
	$(".se1").selecter({
        defaultLabel: $(".se1").find('option:selected').text()
    });	
	$(".se2").selecter({
        defaultLabel: $(".se2").find('option:selected').text()
    });	
	$(".se3").selecter({
        defaultLabel: $(".se3").find('option:selected').text()
    });	
	//地区选择	
	$("#province").change(function(){
		$("#province option").each(function(){
			var checkIndex=$("#province").get(0).selectedIndex;
		$(".city").hide();
		$(".city").eq(checkIndex).show();

		});
		});
		$("#province").change();
	
	/************************end***************************/
	
	$('.service-type').click(function() {
		$('.interesting').show();
	});
	$('.service-head .close').click(function() {
		$('.interesting').hide();
		var categoryhtml ='';
		var spans = $('.selected-list li span');
			for(var i =0;i<spans.length;i++){
				categoryhtml+=spans[i].innerHTML+' ';
			}
		$('#myinterestservice1').html(categoryhtml);
		$('#myinterestservice2').html(categoryhtml);
	});
	/*********************感兴趣的服务领域**************************/
	//隔行换色
	$('.service-body .service-row:even').addClass('row-even');
	//点击服务条目
	$(document).on('click', '.service-list li span', function(event){
		var input = $(this).prev();
		if(input.is(':checked')){
			var selected = $('.selected-list').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}else{
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list');
		}
	});
	$(document).on('click', '.service-list li input', function(event){
		var input = $(this);
		if(input.is(':checked')){
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list');
		}else{
			var selected = $('.selected-list').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}
	});
	//点击已选
	$(document).on('click', '.selected-list li', function(event) {
		var input = $('.service-list').find("input[name='"
						+ $(this).children('input').attr('name') + "']");
		input.attr('checked', false);
		if(!input.is(':checked')) $(this).remove();
	});
	
	//用户管理
	$('.action li.s2').click(function(){
		if($(this).attr('action-data')=='up'){
			$(this).attr('action-data','down');
			$(this).children('a').removeClass('up').addClass('down');
			$(this).parent().parent().next('.switch-content').hide();
		}else{
			$(this).attr('action-data','up');
			$(this).children('a').removeClass('down').addClass('up');
			$(this).parent().parent().next('.switch-content').show();
		}
	});
	
	$('.action li.s1').click(function(){
		if($(this).next().attr('action-data')=='down'){
			jError('请展开后再执行相应操作',{VerticalPosition:'center'});
			return false;
		}else if($(this).attr('action-data')=='edit'){
			$(this).children('a').html('取消');
			$(this).attr('action-data','cancel');
			$(this).parent().parent().next('.switch-content').children('.show-info').hide();
			$(this).parent().parent().next('.switch-content').children('.info-form').show();
		}else{
			$(this).children('a').html('编辑');
			$(this).attr('action-data','edit');
			$(this).parent().parent().next('.switch-content').children('.show-info').show();
			$(this).parent().parent().next('.switch-content').children('.info-form').hide();
		}
	})	
	/**/
	$('.service-type-my').click(function() {
		$('.myservice').show();
		
	});
	$('.service-head-my .close').click(function() {
		$('.myservice').hide();
		var categoryhtml ='';
		var spans = $('.selected-list-my li span');
			for(var i =0;i<spans.length;i++){
				categoryhtml+=spans[i].innerHTML+' ';
			}
		$('#myservice1').html(categoryhtml);
		$('#myservice2').html(categoryhtml);
	});
		/*********************我的服务领域**************************/
	//隔行换色
	$('.service-body-my .service-row-my:even').addClass('row-even-my');
	//点击服务条目
	$(document).on('click', '.service-list-my li span', function(event){
		var input = $(this).prev();
		if(input.is(':checked')){
			var selected = $('.selected-list-my').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}else{
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			if(checkBoxs.length >= 10){
				alert('最多选择10个服务领域'); return;
			}
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list-my');
		}
	});
	$(document).on('click', '.service-list-my li input', function(event){
		var input = $(this);
		if(input.is(':checked')){
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			if(checkBoxs.length >= 10){
				alert('最多选择10个服务领域'); 
				input[0].checked = false;
				return;
			}
			input[0].checked = true;
			$(this).parent().clone().appendTo('.selected-list-my');
		}else{
			var selected = $('.selected-list-my').find("input[name='"+ input.attr('name')+"']");
			selected.parent().remove();
			input.attr('checked', false);
		}
	});
	//点击已选
	$(document).on('click', '.selected-list-my li', function(event) {
		var input = $('.service-list-my').find("input[name='"
						+ $(this).children('input').attr('name') + "']");
		input.attr('checked', false);
		if(!input.is(':checked')) $(this).remove();
	});
	
});

$(function(){
	KindEditor.ready(function(K) {
        window.editor_profile = KindEditor.create('#editor_profile', {uploadJson : 'public/uploadByKindedior'});
        window.editor_mainRemark = KindEditor.create('#editor_mainRemark', {uploadJson : 'public/uploadByKindedior'});
//		window.editor_business = KindEditor.create('#editor_business', {uploadJson : 'public/uploadByKindedior'});
//		window.editor_advantageServiceAreas = KindEditor.create('#editor_advantageServiceAreas', {uploadJson : 'public/uploadByKindedior'});
//		window.editor_honorSociety = KindEditor.create('#editor_honorSociety', {uploadJson : 'public/uploadByKindedior'});
//		window.editor_professionalQualifications = KindEditor.create('#editor_professionalQualifications', {uploadJson : 'public/uploadByKindedior'})
	});
	var jVal = {
		'enterLogo':function(){
			var ele = $('#enterLogo');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('right').addClass('error').html('请选择头像图片');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'staffNumber': function(){
			var ele = $('#staffNumber');
			//判断是否已经生成过元素，防止重复生成
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\d*$/;
			if(ele.val() != ''){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('员工人数必须位数字');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').addClass('error').html('该项为必填项');
			}
		},
		'orgIndustry': function(){
			var ele = $('#orgIndustry');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==0){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请选择行业');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'orgProperty': function(){
			var ele = $('#orgProperty');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==0){
				jVal.errors = true;
				info.removeClass('right').addClass('error').html('请选择机构性质');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'business': function(){
			var ele = $('#org_business');
			if(ele.siblings().length==1){
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
		'org_advantageServiceAreas': function(){
			var ele = $('#org_advantageServiceAreas');
			if(ele.siblings().length==1){
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
		'orgWebsite': function(){
			var ele = $('#orgWebsite');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}else if(ele.val().length > 100){
				info.removeClass('right').addClass('error').html('长度不能超过100个字符');
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'org_honorSociety': function(){
			var ele = $('#org_honorSociety');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}else if(ele.val().length > 100){
				info.removeClass('right').addClass('error').html('长度不能超过100个字符');
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'org_professionalQualifications': function(){
			var ele = $('#org_professionalQualifications');
			if(ele.siblings().length==1){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val()==''){
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}else if(ele.val().length > 100){
				info.removeClass('right').addClass('error').html('长度不能超过100个字符');
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'salesRevenue': function() {
			var ele = $('#salesRevenue');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(\d+([.]\d{1,4})?)?$/;
			if(ele.val() != ''){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('年营业额只能为数字, 且最多4位小数');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').addClass('error').html('该项为必填项');
			}
		},
		'totalAssets': function() {
			var ele = $('#totalAssets');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(\d+([.]\d{1,4})?)?$/;
			if(ele.val() != ''){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('总资产只能为数字, 且最多4位小数');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').addClass('error').html('该项为必填项');
			}
		},
		'profit': function() {
			var ele = $('#profit');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(\d+([.]\d{1,4})?)?$/;
			if(ele.val() != ''){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('总资产只能为数字, 且最多4位小数');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').addClass('error').html('该项为必填项');
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
		'orgFax': function() {
			var ele = $('#orgFax');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var faxReg = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
			if(!faxReg.test(ele.val()) && ele.val() != ''){
				info.removeClass('right').addClass('error').html('传真格式不正确');
				return false;
			}else{
				info.removeClass('error').addClass('right').html('填写正确');
			}
		},
		'userEmail':function(){
			console.log('aa');
			var email = $('#userEmail');
			if(email.siblings().length==0){
				email.parent().append('<label class="info"></label>');
			}
			var info =  $('#userEmail').siblings('label');
			if(email.val().length<1){
				alert('请输入邮箱');
				return false;
			}else{
				info.removeClass('error');
			}
		},
		'orgTel': function() {
			var ele = $('#orgTel');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
			var mobileReg = /^1\d{10}$/;
			if(ele.val() != ''){
				if(!telReg.test(ele.val())&&!mobileReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('电话格式不正确(为固话或者手机号码)');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
//				info.removeClass('right').addClass('error').html('该项为必填项');
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'legalRepresentativeMobile': function() {
			var ele = $('#legalRepresentativeMobile');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var mobileReg = /^1\d{10}$/;
			if(ele.val() != ''){
				if(!mobileReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('手机格式不正确');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'email': function() {
			var ele = $('#email');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(ele.val() != ''){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('邮箱格式不正确');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'generalManagerMobile': function() {
			var ele = $('#generalManagerMobile');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var mobileReg = /^1\d{10}$/;
			if(ele.val() != ''){
				if(!mobileReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('手机格式不正确');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'generalManagerEmail': function() {
			var ele = $('#generalManagerEmail');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(ele.val() != ''){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('邮箱格式不正确');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'contactNameMobile': function() {
			var ele = $('#contactNameMobile');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var mobileReg = /^1\d{10}$/;
			if(ele.val() != ''){
				if(!mobileReg.test(ele.val())){
					info.removeClass('right').addClass('error').html('手机格式不正确');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'contactNameEmail': function() {
			var ele = $('#contactNameEmail');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(ele.val() != ''){
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('邮箱格式不正确');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('填写正确');
				}
			}else{
				info.removeClass('right').html('');
				info.removeClass('error').html('');
			}
		},
		'submitInfo':function(){
			$('#editor_profile').text(editor_profile.html());
			$('#userinfoform').ajaxSubmit({
					dataType : 'json',
					url:'ucenter/saveUserInfo',
					type:'post',
					success : function(ret) {
						if(ret.success){
							//jSuccess(ret.message,{VerticalPosition:'center',  onClosed : function(){
							
							art.dialog({
								width: 350,
								height: 100,
								fixed: true,
								lock: true,
								resize: false,
								title: '提示',
								content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
								
								ok: function () {
							        this.lock();
							        window.location.reload(false);
							    }
							}); 
							
						}
					},
					error : function(ret) {
						jError(ret.message,{VerticalPosition:'center'});
					}
			});
		},
		'submitEnterInfo' :function(){
			$('#editor_mainRemark').text(editor_mainRemark.html());
//			$('#editor_business').text(editor_business.html());
//			$('#editor_advantageServiceAreas').text(editor_advantageServiceAreas.html());
//			$('#editor_honorSociety').text(editor_honorSociety.html());
//			$('#editor_professionalQualifications').text(editor_professionalQualifications.html());
			$('#enterinfoform').ajaxSubmit({
					dataType : 'json',
					url:'ucenter/saveOrgEnterInfo',
					type:'post',
					success : function(ret) {
						if(ret.success){
							art.dialog({
								width: 350,
								height: 100,
								fixed: true,
								lock: true,
								resize: false,
								title: '提示',
								content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
								
								ok: function () {
							        this.lock();
							        window.location.reload(true);
							    }
							
							}); 
							
							
						}
					},
					error : function(ret) {
						jError(ret.message,{VerticalPosition:'center'});
					}
			});
		},
		'submitMyServiceInfo':function(){
			var checkBoxs = $('.selected-list-my input[type="checkbox"]');
			var ids='';
			for(var i =0;i<checkBoxs.length;i++){
				ids+=checkBoxs[i].value+',';
			}
			$.post(
				'ucenter/saveMyServiceInfo',
				{'ids':ids,'uid':$('#userId').val()},
				 function(data) {
					if(data.success){
						art.dialog({
							width: 350,
							height: 100,
							fixed: true,
							lock: true,
							resize: false,
							title: '提示',
							content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
							
							ok: function () {
						        this.lock();
						        window.location.reload(true);
						    }
						
						}); 
						
					}else{
						jError(data.message,{VerticalPosition:'center',  onClosed : function(){
								window.location.reload(false); }});
					}
				},'json'
				
			);
		},
		'submitInterestServiceInfo':function(){
			var checkBoxs = $('.selected-list input[type="checkbox"]');
			var ids='';
			for(var i =0;i<checkBoxs.length;i++){
				ids+=checkBoxs[i].value+',';
			}
			$.post(
				'ucenter/saveInteresServiceInfo',
				{'ids':ids,'uid':$('#userId').val()},
				 function(data) {
					if(data.success){
						art.dialog({
							width: 350,
							height: 100,
							fixed: true,
							lock: true,
							resize: false,
							title: '提示',
							content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
							
							ok: function () {
						        this.lock();
						        window.location.reload(true);
						    }
						
						}); 
						
					}else{
						jError(data.message,{VerticalPosition:'center',  onClosed : function(){
								window.location.reload(false); }});
					}
				},'json'
			);
		},
		'submitLinkInfo':function(){
			$('#linkinfofrom').ajaxSubmit({
				dataType : 'json',
				url:'ucenter/saveOrgLinkInfo',
				type:'post',
				success : function(ret) {
						if(ret.success){
							art.dialog({
								width: 350,
								height: 100,
								fixed: true,
								lock: true,
								resize: false,
								title: '提示',
								content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
								
								ok: function () {
							        this.lock();
							        window.location.reload(true);
							    }
							
							}); 
							
						}
					},
					error : function(ret) {
						jError(ret.message,{VerticalPosition:'center'});
					}
			});
		
		},
		'submitPhoto' : function(){
			if($('#enterLogo').val() != ''){
				$('#photoform').ajaxSubmit({
					dataType : 'json',
					url : 'public/uploadFile',
					type : 'post',
					success : function(ret) {
						if (ret.success) {
							var photoName = ret.message;
							$.post(
								'ucenter/updatePhoto',
								{'photo':photoName, 'id' : $('input[name="enterprise.id"]').val()},
								function(data) {
									if(data.success){
										art.dialog({
											width: 350,
											height: 100,
											fixed: true,
											lock: true,
											resize: false,
											title: '提示',
											content:'<div class="ucenter-msg"><span class="icon-good"></span>信息保存成功</div>',
											
											ok: function () {
										        this.lock();
										        window.location.reload(false); 
										    }
										}); 
										
											
									}else{
										jError(data.message,{VerticalPosition:'center'});
									}
								},'json'
							);
						}
					},
					error : function(ret) {
						jError(ret.message,{VerticalPosition:'center'});
					}
				});
			}else{//不上传图片
				$.post(
					'ucenter/updatePhoto',
					{'id' : $('input[name="enterprise.id"]').val()},
					function(data) {
						if(data.success){
							jSuccess(data.message,{VerticalPosition:'center',  onClosed : function(){
							window.location.reload(false); }});
						}else{
							jError(data.message,{VerticalPosition:'center'});
						}
					},'json'
				);
			}
		} 
	};
	/*我感兴趣的服务领域提交*/
	$('#interestservicescommit a').click(function() {
		if($('#interestservicescommit').attr('action-data')=='save'){
			jVal.submitInterestServiceInfo();
		}
		
	});
	/*联系信息提交*/
	$('#linkcommit a').click(function() {
		if($('#linkcommit').attr('action-data')=='save'){
			jVal.generalManagerEmail();
			jVal.generalManagerMobile();
			jVal.legalRepresentativeMobile();
			jVal.email();
			jVal.contactNameMobile();
			jVal.contactNameEmail();
			var lable = $('#linkinfofrom .info');
			for(var i=0;i<lable.length;i++){
				if(lable.eq(i).hasClass('error')) return;
			}
			jVal.submitLinkInfo();
		}
	});
	
	/*我的服务领域信息提交*/
	$('#myservicecommit a').click(function() {
			if($('#myservicecommit').attr('action-data')=='save'){
			jVal.submitMyServiceInfo();
		}
		
	});
	
	/*企业信息提交*/
	$('#entercommit a').click(function() {
			if($('#entercommit').attr('action-data')=='save'){
				jVal.orgIndustry();
				jVal.orgFax();
				jVal.orgTel();
				jVal.orgProperty();
				jVal.business();
				jVal.org_advantageServiceAreas();
				jVal.orgWebsite();
				jVal.org_honorSociety();
				jVal.org_professionalQualifications();
				jVal.profit();
				jVal.totalAssets();
				jVal.salesRevenue();
				jVal.staffNumber();
				jVal.org_icRegNumber();
				var lable = $('#enterinfoform .info');
				for(var i=0;i<lable.length;i++){
					if(lable.eq(i).hasClass('error')) return;
				}
				
				jVal.submitEnterInfo();
			}
	});
	
	//infocommit
	$('#infocommit a').click(function() {
		if($('#infocommit').attr('action-data')=='save'){
			jVal.submitInfo();
		}
	});
	
	// 图像设置
	$('#photocommit a').click(function(){
		if($('#photocommit').attr('action-data')=='save'){
			var ele = $('#enterLogo');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			if(ele.val() == ''){
				info.removeClass('right').addClass('error').html('请选择企业图像');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
			
			var lable = $('.auth-form-content .info');
			for(var i=0;i<lable.length;i++){
				if(!lable.eq(i).hasClass('right')) return;
			}
			jVal.submitPhoto();
		}
	});
	
	$('#enterLogo').blur(jVal.enterLogo);
	$('#staffNumber').blur(jVal.staffNumber);
	$('#orgIndustry').change(jVal.orgIndustry);
	$('#orgProperty').change(jVal.orgProperty);
	$('#org_business').blur(jVal.business);
	$('#org_advantageServiceAreas').blur(jVal.org_advantageServiceAreas);
	$('#orgWebsite').blur(jVal.orgWebsite);
	$('#org_honorSociety').blur(jVal.org_honorSociety);
	$('#org_professionalQualifications').blur(jVal.org_professionalQualifications);
	$('#totalAssets').blur(jVal.totalAssets);
	$('#salesRevenue').blur(jVal.salesRevenue);
	$('#profit').blur(jVal.profit);
	$('#orgTel').blur(jVal.orgTel);
	$('#orgFax').blur(jVal.orgFax);
	$('#userEmail').blur(jVal.userEmail);
	$('#icRegNumber').blur(jVal.org_icRegNumber);
	$('#generalManagerEmail').blur(jVal.generalManagerEmail);;
	$('#generalManagerMobile').blur(jVal.generalManagerMobile);
	$('#legalRepresentativeMobile').blur(jVal.legalRepresentativeMobile);
	$('#email').blur(jVal.email);
	$('#contactNameMobile').blur(jVal.contactNameMobile);
	$('#contactNameEmail').blur(jVal.contactNameEmail);
});

$(function(){
	$('.rule-sive').click(function(){
		var dialog = art.dialog({
			width: 350,
			height: 150,
			fixed: true,
			lock: true,
			resize: false,
			title: '消息',
			content:'<span></span>保存成功....',
			time:2000,
			ok: function () {
		        this
		       
		        .lock()
		        .time(2000);
		    
		    }
		}); 
		
	});
	// 查看 营业执照附件 企业公函 不存在时 
	$('.look-img a').click(function(){
		if($(this).attr('href').indexOf('.') < 0){
			art.dialog({
			    title: '提示',
			    ok : function () {window.location = 'ucenter/auth?op=2';},
			    okValue : '去认证',
			    cancel: function () { },
			    cancelValue: '取消',
			    content: '您还不是认证用户,未上传该图片.',
			    lock : true
			});
			return false;
		}
		return true;
	});
})