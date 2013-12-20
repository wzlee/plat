package com.eaglec.plat.sync.bean;

import java.util.HashMap;
import java.util.Map;

import com.eaglec.plat.domain.service.ServiceDetail;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.StrUtils;
/**
 * 服务流水同步类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class ServiceDetailSyncBean extends AbstractSyncBean<ServiceDetail> {

	public ServiceDetailSyncBean(ServiceDetail syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(ServiceDetail syncObj) throws Exception {
		Map<String, Object> serviceRecord = SyncFactory.getDao().findFirst("SELECT * FROM service WHERE id = " + syncObj.getServiceId());
		Map<String, Object> enterpriseRecord = SyncFactory.getDao().findFirst("SELECT * FROM enterprise WHERE id = " + serviceRecord.get("enterprise_id"));
		if (enterpriseRecord == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		wid = StrUtils.toInt(enterpriseRecord.get("industryType"));
		
		// 如果wid == plat.WINDOW_ID, 则不同步流水
		if (wid == Common.WINDOW_ID)  {
			super.syncType = SyncType.NONE;
			return;
		}
		
		if (ignoreFields == null) {
			ignoreFields = "id,sid";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		foreignKeyMap.put("serviceId", new String[]{"service", "sid", "id"});
		
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "sdid";
		uniqueKeyValue = syncObj.getSdid();

		selectSql = "SELECT sd.*,s.sid FROM servicedetail AS sd, service as s WHERE sd.serviceId = s.id " +
				"and sd.sdid = '" + uniqueKeyValue + "'";

		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
