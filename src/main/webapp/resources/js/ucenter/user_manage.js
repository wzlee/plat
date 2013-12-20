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
	});
	var jVal = {
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
			$('#enterinfoform').ajaxSubmit({
					dataType : 'json',
					url:'ucenter/saveEnterInfo',
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
				url:'ucenter/saveLinkInfo',
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
});

$(function(){
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
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('员工人数必须位数字');
				return false;
			}else{
				info.removeClass('error').addClass('right').html('填写正确');
			}
		},
		'salesRevenue': function() {
			var ele = $('#salesRevenue');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(\d+([.]\d{1,4})?)?$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('年营业额只能为数字, 且最多4位小数');
				return false;
			}else{
				info.removeClass('error').addClass('right').html('填写正确');
			}
		}
		,
		'enterpriseTel': function() {
			var ele = $('#enterpriseTel');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var telReg = /^[0-9]{3,4}-[0-9]{7,8}$/;
			var mobileReg = /^1\d{10}/;
			if(!telReg.test(ele.val())&&!mobileReg.test(ele.val())){
				info.removeClass('right').addClass('error').html('联系方式不正确');
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
		}
	};

	$('#enterLogo').blur(jVal.enterLogo);
	$('#staffNumber').blur(jVal.staffNumber);
	$('#salesRevenue').blur(jVal.salesRevenue);
	$('#enterpriseTel').blur(jVal.enterpriseTel);
	$('#userEmail').blur(jVal.userEmail);
	
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