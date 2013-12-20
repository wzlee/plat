//Loading效果
$(window).load(function(){
	$('#loading').fadeOut(3000, function() {
		$('.service-canvas').show();
	    $('.enterprise-test').show();
	});
});

$(function(){
	//验证浏览器
	var userAgent = navigator.userAgent.toLowerCase();
	var core,name,version;
	jQuery.browser = {
		version : (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
		safari : /webkit/.test(userAgent),
		chrome:/chrome/.test(userAgent) && !/(applewebkit|safari)/.test(userAgent),
		opera : /opera/.test(userAgent),
		msie : /msie/.test(userAgent) && !/opera/.test(userAgent),
		mozilla : /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
	};
	version = jQuery.browser.version;
	version = jQuery.browser.version;
	if(jQuery.browser.msie){
		core = 'mise';
		name = 'IE';
		if(jQuery.browser.version < 9.0){
			alert('本页面使用了大量的HTML5和CSS3技术，请升级浏览器至IE10.0以上版本');
			window.location.href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-10-worldwide-languages";
		}
	}

	//资金转盘
	$('.wheel-b').click(function() {
		$(this).parent('#service-wheel').addClass('item-clicked').removeClass('jump');
		setTimeout(function(){
			$('.huge-arrow').show();
			$('.wheel-b').hide();
			$('.enterprise-info-panel').show();
		},500)
	});

	//关闭
	$('.bookmark-close').click(function() {
		$('.huge-arrow').hide();
		$('#service-wheel').removeClass('item-clicked').addClass('jump');
		$('.enterprise-info-panel').hide();
		$('.enterprise-service-list').hide();
		$('.wheel-b').show();
	});

	//日期
	$('#datepicker').datepicker({
            showOn: 'button',
            buttonText: ''
	});

	//所属行业
	$("#selecterTrade").selecter({
		defaultLabel: '所属行业'
	});

	//资金用途
	$('#selecterFund').selecter({
		defaultLabel: '资金用途'
	});

	//提交
	$('.commit').click(function() {
		$(this).parent('.enterprise-info-panel').hide();
		$(this).parent('.enterprise-info-panel').siblings('.loading').show();
		setTimeout(function(){
			$('.loading').hide();
			$('.enterprise-service-list').show();
		},3000)
	});

	//点击放大镜
	$('#magnifier').click(function() {
		$(this).parent('#broad').addClass('broad-active');
		$(this).parent('#broad').addClass('x-broad-change');
		$(this).siblings('.test-sign').hide();
		$(this).addClass('move-action');
		$(this).siblings('.company-scroll').show();
		setTimeout(function(){
			$('.company-scroll').hide();
		},5000);
		setTimeout(function(){
			$('#magnifier').removeClass('move-action').hide();
			$('#broad').addClass('xx-broad-change');
			$('#broad .result-bookmark-close').show();
		},5500);
	});
	//公司名称切换
	var companies = new Array();
	companies[0] = '深圳天源达科技有限公司';
	companies[1] = '深圳市戈多宠物用品有限公司';
	companies[2] = '博肯地毯贸易有限公司';
	companies[3] = '深圳市宏普达科技有限公司';
	companies[4] = '深圳怡高实业有限公司';
	companies[5] = '宏远丝印移印加工厂';
	companies[6] = '深圳环讯国际物流公司';
	companies[7] = '深圳市博视锐科技有限公司';
	companies[8] = '深圳市优易讯科技有限公司';
	companies[9] = '深圳市诺希尔燃气设备有限公司';

	var index=0;
	var company_num = companies.length;
	var timer;
	autoShow(index);
	function showItem(index){
		$('.company-scroll').html(companies[index]);
	}
	function autoShow(index){
		timer = setInterval(function(){
			showItem(index);
			index++;
			index = (index == company_num) ? 0 : index;
		},100)
	}

	//搜索结果框关闭
	$('#result-bookmark-close').click(function() {
		$('#broad').removeClass('broad-active x-broad-change xx-broad-change');
		$(this).siblings('.test-sign').show();
		$(this).siblings('#magnifier').show();
		$(this).hide();
	});
})