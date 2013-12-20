$(function() {
	var trs = $('table tbody tr');
	for (var i = 0; i < trs.length; i++) {
		var tds = trs.eq(i).children();
		// STEP1. 申请类型
		var applyType = tds.eq(1).text();
		tds.eq(1).text(PlatMap.ApprovedDetail.applyType[applyType]);
		// STEP2. 处理状态
		var approveStatus = tds.eq(3).text();
		tds.eq(3).children().eq(0).text(PlatMap.ApprovedDetail.approveStatus[approveStatus]);
		// STEP3. 操作(只针对最后一次)
		if (i < trs.length - 1 || approveStatus == 2)
			continue;
		var as = tds.eq(6).find('a');
		if (applyType == '1') { // 申请实名
			switch (approveStatus) {
				case '0' :
					as.eq(0).text('重新申请');
					as.eq(1).text('申请服务机构认证');
					break;
				case '1' :
					as.eq(0).text();
					as.eq(1).text('申请服务机构认证');
					break;
			}
		} else { // 申请服务机构
			switch (approveStatus) {
				case '0' :
					as.eq(1).text('重新申请');
					break;
			}
		}
	}
});