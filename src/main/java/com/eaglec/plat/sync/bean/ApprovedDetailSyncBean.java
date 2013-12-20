package com.eaglec.plat.sync.bean;

import java.util.HashMap;

import com.eaglec.plat.domain.base.ApprovedDetail;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
/**
 * 用户审核同步类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class ApprovedDetailSyncBean extends AbstractSyncBean<ApprovedDetail> {

	public ApprovedDetailSyncBean(ApprovedDetail syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(ApprovedDetail syncObj) throws Exception {
		if (syncObj.getEnterprise() == null || syncObj.getEnterprise().getIndustryType() == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		wid = syncObj.getEnterprise().getIndustryType();
		
		if (ignoreFields == null) {
			ignoreFields = "id,eid,uid,manager_id";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		foreignKeyMap.put("enterprise_id", new String[]{"enterprise", "eid", "id"});
		foreignKeyMap.put("user_id", new String[]{"user", "uid", "id"});
		
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "adid";
		uniqueKeyValue = syncObj.getAdid();

		selectSql = "SELECT a.*,e.eid,u.uid FROM approveddetail AS a, enterprise AS e, user AS u " +
				"WHERE a.enterprise_id = e.id AND a.user_id = u.id AND a.adid = '" + uniqueKeyValue + "'";

		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
