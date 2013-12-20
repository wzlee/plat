$(function(){
	var check = document.getElementsByName("serviceMethod");
  	for(var i = 0; i < check.length; i ++){
  		check[i].onclick = function() {
  			var ele = $('#serviceMethod');
  			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			info.removeClass('right').addClass('error').html('');
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
			var ele = $('.separate-mini');
			var input = document.getElementsByName("serviceMethod");			
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			info.removeClass('right').addClass('error').html('');
			for(var i = 0; i < input.length; i ++){				
				if(input[i].checked){
					info.removeClass('error').addClass('right').html('');
					console.log(input[i].checked);
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
		// 编辑服务
		'editService' : function(){
			if(!$('input[name="file"]').val()){ 		
 				$('.main-column form').ajaxSubmit({
					url : 'service/changeService',
					type : 'post',
					dataType : 'json',
					data:{},
					success : function(ret) {
						art.dialog({
						    title: '提示',
						    content: ret.message,
						    ok: function () {
					    	},
					    	okValue:'确定'
						});						
						window.location.href='/ucenter/service_list?op=5';
					},
					error : function(ret) {
						art.dialog({
						    title: '提示',
						    content: ret.message,
						    ok: function () {
					    	},
					    	okValue:'确定'
						});
					}
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
							$('#imgupdate').val(data.message);
							$('.main-column form').ajaxSubmit({
							url : 'service/changeService',
							type : 'post',
							dataType : 'json',
							data:{},
							success : function(ret) {
								art.dialog({
								    title: '提示',
								    content: ret.message,
								    ok: function () {
							    	},
							    	okValue:'确定'
								});								
								window.location.href='/ucenter/service_list?op=5';
							},
							error : function(ret) {
								art.dialog({
								    title: '提示',
								    content: ret.message,
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
		}
	};
	
	$('#cancel').click(function() {
  		$('#detail').css('display','block');
		$('#edit').css('display','none');
		$('#btn').css('display','block');
		
	});
	
	$('#save').click(function() {
  		var lable = $('.auth-form-content .info');		
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.editService();		
	});
	
	$('#serviceName').blur(jVal.serviceName);
  	$('#costPrice').blur(jVal.costPrice);
  	$('#picturefile').blur(jVal.picturefile);
  	UE.getEditor('myEditor').addListener("blur",jVal.serviceProcedure); 	
  	$('#linkMan').blur(jVal.linkMan);
  	$('#tel').blur(jVal.tel);
  	$('#email').blur(jVal.email);
  	
   	$('#serviceclass').combobox({
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
   		method: 'get',
   		url: 'chat/getChatGroup?username='+ $('#chatgroup').attr('username') +'&eid='+ $('#user').val() +'&allGroup=true',
   		width: 300,
   		valueField:'id',
        textField:'groupName',
        onLoadSuccess: function(){
        	var falg = true;
        	var chatgroupid = $(this).attr('chatgroupid');
        	var data = $(this).combobox('getData');
        	if(data){
	        	for(var i = 0; i < data.length; i++){
	        		if(data[i].id == chatgroupid){
			        	$(this).combobox('setValue', chatgroupid);
			        	falg = false;
			        	break;
	        		}
	        	}
	        	//如果该服务还没关联过在线客服组，那就给他关联默认的组
        		falg ? $(this).combobox('setValue', 0) : null;
        	}
        }
   	});
});
function showform(){
	$('#detail').css('display','none');
	$('#edit').css('display','block');
	$('#btn').css('display','none');
};
