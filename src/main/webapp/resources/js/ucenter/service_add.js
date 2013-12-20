  $(function(){
	//select下拉美化
	//  $(".sel1").selecter({
	 //       defaultLabel: $(".sel1").find('option:selected').text()
	 //   });	
	 
  	var check = document.getElementsByName("serviceMethod");  	
  	for(var i = 0; i < check.length; i ++){
  		check[i].onclick = function() {
  			var ele = $('#serviceMethod');
  			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			info.removeClass('right').addClass('error').html('请至少选择一项服务方式');
			var input = document.getElementsByName("serviceMethod");
			for(var i = 0; i < input.length; i ++){				
				if(input[i].checked){
					info.removeClass('error').addClass('right').html('');					
				}
			}
		};
  	}		
  	var jVal = {
  		
		'serviceName': function(){
			var ele = $('#serviceName');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');			
			if( ele.val().length<5 || ele.val().length>50 ){
				info.removeClass('right').addClass('error').html('服务名称应在5字符以上，50字符以内');				
			}else {
				info.removeClass('error').addClass('right').html('');
			};
		},
		'serviceclass': function(){
			var ele = $('#serviceclass');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}			
			var info = ele.siblings('label');			
			var value = ele.combobox('getValue');
			info.removeClass('right').addClass('error').html('服务类别不能为空');			
			if(!value){
				info.removeClass('right').addClass('error').html('服务类别不能为空');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'checkbox': function(){
			var ele = $('#method-checkbox');
			var input = document.getElementsByName("serviceMethod");			
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			info.removeClass('right').addClass('error').html('请至少选择一项服务方式');
			for(var i = 0; i < input.length; i ++){				
				if(input[i].checked){
					info.removeClass('error').addClass('right').html('');					
				}
			}			
			
		},
		'costPrice': function(){
			var ele = $('#costPrice');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^[0-9]{1,}$/;			
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('服务价格只能是数字且不能为空');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'picturefile': function(){
			var ele = $('#picturefile');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S+$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('请选择图片上传');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'serviceProcedure': function(){
			var ele = $('#myEditor');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');			
			if(UE.getEditor('myEditor').getContent()){
				info.removeClass('error').addClass('right').html('');								
			}else {
				info.removeClass('right').addClass('error').html('不能为空');
			};			
		},
		'linkMan': function(){
			var ele = $('#linkMan');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S+$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('联系人不能为空');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'tel': function(){
			var ele = $('#tel');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('联系电话不正确');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		'email': function(){
			var ele = $('#email');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('电子邮箱不正确');
			}else{
				info.removeClass('error').addClass('right').html('');
			}
		},
		// 提交新服务
		'submitNewService' : function(act){
			if(!$('input[name="file"]').val()){
				art.dialog({
					title: '提示',
					content: '图片必须上传',
					ok: function () {
					},
					okValue:'确定'
				}); 				
 			}else {
				var files = $('input[type="file"]');				
				var form = $('#picture_form');
				var real = files.eq(0);
				var cloned = real.clone(true);
				real.hide();
				cloned.insertAfter(real);
				form.append(real);
				form.ajaxSubmit({
					url : 'public/uploadFile',
					type : 'post',
					dataType : 'json',
					clearForm : false,
					success : function(data) {
						if(data.success){
							cloned.next().val(data.message);
							$('.main-column form').ajaxSubmit({
							url : 'service/addservice',
							type : 'post',
							dataType : 'json',
							data:{action:act},
							success : function(ret) {
								art.dialog({
									title: '提示',
									content: ret.message,
									ok: function () {
									},
									okValue:'确定'
								});								
								window.location.href="/ucenter/service_list?op=5";
							},
							error : function(ret) {
								art.dialog({
									title: '提示',
									content: '添加服务失败',
									ok: function () {
									},
									okValue:'确定'
								});
							}
							});	
						}			
					},
					error : function() {
						art.dialog({
							title: '提示',
							content: '图片上传失败',
							ok: function () {
							},
							okValue:'确定'
						});						
					}
				});
 			}
		},
		// 提交新服务并申请上架
		'submitNewServiceAndUp' : function(act){
			if(!$('input[name="file"]').val()){
				art.dialog({
					title: '提示',
					content: '图片必须上传',
					ok: function () {
					},
					okValue:'确定'
				}); 				
 			}else {
				var files = $('input[type="file"]');				
				var form = $('#picture_form');
				var real = files.eq(0);
				var cloned = real.clone(true);
				real.hide();
				cloned.insertAfter(real);
				form.append(real);
				form.ajaxSubmit({
					url : 'public/uploadFile',
					type : 'post',
					dataType : 'json',
					clearForm : false,
					success : function(data) {
						if(data.success){
							cloned.next().val(data.message);
							$('.main-column form').ajaxSubmit({
								url : 'service/addservice',
								type : 'post',
								dataType : 'json',
								data:{action:act},
								success : function(ret) {
									art.dialog({
										title: '提示',
										content: ret.message,
										ok: function () {
										},
										okValue:'确定'
									});									
									window.location.href="/ucenter/service_list?op=5";
								},
								error : function(ret) {
									art.dialog({
										title: '提示',
										content: '添加服务失败',
										ok: function () {
										},
										okValue:'确定'
									});									
								}
							});	
						}			
					},
					error : function() {
						art.dialog({
							title: '提示',
							content: '图片上传失败',
							ok: function () {
							},
							okValue:'确定'
						});						
					}
				});
 			}
		},
		'checkForm' : function(){
			var lable = $('.auth-form-content .info');		
			jVal.serviceName();
			jVal.serviceclass();
			jVal.checkbox();
			jVal.costPrice();
			jVal.picturefile();
			jVal.serviceProcedure();
			jVal.linkMan();
			jVal.tel();
			jVal.email();
		}
  	}; 
	
  	$('#save').click(function() {
  		jVal.checkForm();
  		var lable = $('.auth-form-content .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.submitNewService('save');		
	});
	
  	$('#saveandup').click(function() {  		
		jVal.checkForm();
		var lable = $('.auth-form-content .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.submitNewServiceAndUp('saveandup');
	});
  	$('#serviceName').blur(jVal.serviceName);
  	$('#costPrice').blur(jVal.costPrice);
  	$('#picturefile').blur(jVal.picturefile);  	
  	UE.getEditor('myEditor').addListener("blur",jVal.serviceProcedure);
  	$('#linkMan').blur(jVal.linkMan);
  	$('#tel').blur(jVal.tel);
  	$('#email').blur(jVal.email);
  	
   	$('#serviceclass').combobox({
   		width: 300,
   		data : eval($('#myServices').html()),
   		valueField:'id',  
        textField:'text',
        editable:false,
        onChange:jVal.serviceclass
   	});
   	
   	/**
   	 * 咨询客服组
   	 */
   	$('#chatgroup').combobox({
   		method: 'post',
   		url: 'chat/getChatGroup?username='+ $('#chatgroup').attr('username') +'&eid='+ $('#user').val() +'&allGroup=true',
   		width: 300,
   		valueField:'id',
        textField:'groupName',
        onLoadSuccess: function(){
        	$(this).combobox('setValue', 0);
        }
   	});
}); 
  
