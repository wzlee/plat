$(function() {
	$('.service-type').click(function() {
		$('.overlay').show();
	});
	$('.service-head .close').click(function() {
		$('.overlay').hide();
	});
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
			$(this).children('a').html('保存');
			$(this).attr('action-data','save');
			$(this).parent().parent().next('.switch-content').children('.show-info').hide();
			$(this).parent().parent().next('.switch-content').children('.info-form').show();
		}else{
			$(this).children('a').html('编辑');
			$(this).attr('action-data','edit');
			jSuccess('保存成功',{VerticalPosition:'center'});
			$(this).parent().parent().next('.switch-content').children('.show-info').show();
			$(this).parent().parent().next('.switch-content').children('.info-form').hide();
		}
	})
});

$(function(){
	var jVal = {
		'icRegNumber': function(){
			var ele = $('#icRegNumber');
			//判断是否已经生成过元素，防止重复生成
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
//			var patt = /^\S{15}$/i;
			var patt = /^\S{1,20}$/i;
			if(!patt.test(ele.val())){
//				info.removeClass('right').addClass('error').html('组织机构号只能为长度为15个字符的数字');
				info.removeClass('right').addClass('error').html('组织机构号为1-20个字符');
			}else{
//				if(!isValidBusCode(ele.val())){
//					info.removeClass('right').addClass('error').html('营业执照不合法');
//				}else{
					$.getJSON('ucenter/validateIcRegNumber', 
						{icRegNumber : ele.val(), eid : $('input[name="enterprise.id"]').val()}, function(data){
						if(data.success){
							info.removeClass('error').addClass('right').html('填写正确');
						}else{
							info.removeClass('right').addClass('error').html(data.message);
						}
					});
//				}
			}
		},
		'name': function() {
			var ele = $('#name');
			if(ele.siblings().length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S{2,50}$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('公司实名为2-50个字符');
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
		'licencefile': function(){
			var ele = $('#licencefile');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S+$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('请选择文件上传');
			}else{
				info.removeClass('error').addClass('right').html('已选择文件');
			}
		},
		'businessLetter': function(){
			var ele = $('#businessLetter');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\S+$/i;
			if(!patt.test(ele.val())){
				info.removeClass('right').addClass('error').html('请选择文件上传');
			}else{
				info.removeClass('error').addClass('right').html('已选择文件');
			}
		},
		// 提交实名申请
		'submitRealName' : function() {
			// STEP 1 异步上传文件
			var files = $('input[type="file"]');
			// 工商营业执照
			var form = $('#licenceDuplicate_form');
			var real = files.eq(0);
			var cloned = real.clone(true);
			real.hide();
			cloned.insertAfter(real);
			form.append(real);
			form.ajaxSubmit({
				url : 'public/uploadFile',
				type : 'post',
				dataType : 'json',
				clearForm : true,
				success : function(data) {
					cloned.next().val(data.message);
					// 企业公函
					form = $('#businessLetter_form');
					real = files.eq(1);
					cloned = real.clone(true);
					real.hide();
					cloned.insertAfter(real);
					form.append(real);
					form.ajaxSubmit({
						url : 'public/uploadFile',
						type : 'post',
						dataType : 'json',
						clearForm : true,
						success : function(data) {
							cloned.next().val(data.message);
							// 提交表单
							$('.main-column form').ajaxSubmit({
								dataType : 'json',
								success : function(ret) {
									if(ret.success){
										$('#apply_ok').show();
									}else{
										$('#apply_err').show();
									}
								},
								error : function(ret) {
									alert('认证申请提交失败！');
								}
							});
						},
						error : function() {
							alert('企业公函上传失败');
						}
					});
				},
				error : function() {
					alert('工商营业执照上传失败');
				}
			});
		},
		// 提交服务机构申请
		'submitServiceOrg' : function(){
			$('.main-column form').ajaxSubmit({
				dataType : 'json',
				success : function(ret) {
					if(ret.success){
						$('#apply_ok').show();
					}else{
						$('#apply_err').show();
					}
				},
				error : function(ret) {
					alert('认证申请提交失败！');
				}
			});
		}
	};
	$('#commit').click(function() {
		jVal.icRegNumber();
		jVal.name();
		jVal.licencefile();
		jVal.businessLetter();
		var lable = $('.auth-form-content .info');
		for(var i=0;i<lable.length;i++){
			if(!lable.eq(i).hasClass('right')) return;
		}
		jVal.submitRealName();
	});
	$('#commit2').click(function() {
		var files = $('input[type="file"]');
		if(files.length > 0){
			jVal.icRegNumber();
			jVal.name();
			jVal.licencefile();
			jVal.businessLetter();
			var lable = $('.auth-form-content .info');
			for(var i=0;i<lable.length;i++){
				if(!lable.eq(i).hasClass('right')) return;
			}
			jVal.submitRealName();
		}else{
			jVal.submitServiceOrg();
		}
	});
	$('#icRegNumber').blur(jVal.icRegNumber);
	$('#name').blur(jVal.name);
	$('#licencefile').change(jVal.licencefile);
	$('#businessLetter').change(jVal.businessLetter);
});

/**
 * 验证营业执照是否合法：营业执照长度须为15位数字，前14位为顺序码， 最后一位为根据GB/T 17710 1999(ISO
 * 7064:1993)的混合系统校验位生成算法 计算得出。此方法即是根据此算法来验证最后一位校验位是否政正确。如果
 * 最后一位校验位不正确，则认为此营业执照号不正确(不符合编码规则)。 以下说明来自于网络:
 * 我国现行的营业执照上的注册号都是15位的，不存在13位的，从07年开始国 家进行了全面的注册号升级就全部都是15位的了，如果你看见的是13位的注
 * 册号那肯定是假的。 15位数字的含义，代码结构工商注册号由14位数字本体码和1位数字校验码
 * 组成，其中本体码从左至右依次为：6位首次登记机关码、8位顺序码。 一、前六位代表的是工商行政管理机关的代码，国家工商行政管理总局用
 * “100000”表示，省级、地市级、区县级登记机关代码分别使用6位行 政区划代码表示。设立在经济技术开发区、高新技术开发区和保税区
 * 的工商行政管理机关（县级或县级以上）或者各类专业分局应由批准 设立的上级机关统一赋予工商行政管理机关代码，并报国家工商行政 管理总局信息化管理部门备案。
 * 二、顺序码是7-14位，顺序码指工商行政管理机关在其管辖范围内按照先 后次序为申请登记注册的市场主体所分配的顺序号。为了便于管理和
 * 赋码，8位顺序码中的第1位（自左至右）采用以下分配规则： 1）内资各类企业使用“0”、“1”、“2”、“3”； 2）外资企业使用“4”、“5”；
 * 3）个体工商户使用“6”、“7”、“8”、“9”。 顺序码是系统根据企业性质情况自动生成的。 三、校验码是最后一位，校验码用于检验本体码的正确性
 */
function isValidBusCode(busCode) {
	var ret = false;
	var sum = 0;
	var s = [];
	var p = [];
	var a = [];
	var m = 10;
	p[0] = m;
	for (var i = 0; i < busCode.length; i++) {
		a[i] = parseInt(busCode.substring(i, i + 1), m);
		s[i] = (p[i] % (m + 1)) + a[i];
		if (0 == s[i] % m) {
			p[i + 1] = 10 * 2;
		} else {
			p[i + 1] = (s[i] % m) * 2;
		}
	}
	if (1 == (s[14] % m)) {
		//组织机构号正确!
		ret = true;
	} else {
		//组织机构号错误!
		ret = false;
	}
	return ret;
}