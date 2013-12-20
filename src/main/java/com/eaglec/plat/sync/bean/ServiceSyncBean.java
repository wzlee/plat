package com.eaglec.plat.sync.bean;

import java.util.HashMap;

import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
/**
 * 服务同步类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class ServiceSyncBean extends AbstractSyncBean<Service> {

	public ServiceSyncBean(Service syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(Service syncObj) throws Exception {
		if (syncObj.getEnterprise().getIndustryType() == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		wid = syncObj.getEnterprise().getIndustryType();
		if (ignoreFields == null) {
			ignoreFields = "id,eid,sendemail_id,detailsId,mallId";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		foreignKeyMap.put("enterprise_id", new String[]{"enterprise", "eid", "id"});
		
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "sid";
		uniqueKeyValue = syncObj.getSid();

		selectSql = "SELECT s.*, e.eid FROM service AS s, enterprise AS e WHERE " +
				"s.enterprise_id = e.id AND s.sid = '" + uniqueKeyValue + "'";

		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
