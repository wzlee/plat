package com.eaglec.plat.sync.bean;

import java.util.HashMap;

import com.eaglec.plat.domain.base.OrgRegisterApproval;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
/**
 * 服务机构注册审核同步
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class OrgRegisterSyncBean extends AbstractSyncBean<OrgRegisterApproval> {

	public OrgRegisterSyncBean(OrgRegisterApproval syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(OrgRegisterApproval syncObj) throws Exception {
		if (syncObj.getIndustryType() == null) {
			throw new Exception("OrgRegisterApproval.getIndustryType() is null");
		}
		wid = syncObj.getIndustryType();
		if (ignoreFields == null) {
			ignoreFields = "id,manager_id";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "oraid";
		uniqueKeyValue = syncObj.getOraid();

		selectSql = "SELECT * FROM orgregisterapproval WHERE oraid = '" + uniqueKeyValue + "'";

		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
