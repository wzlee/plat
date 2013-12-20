package com.eaglec.plat.sync.bean;

import java.util.HashMap;

import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;

/**
 * 用户同步类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class UserSyncBean extends AbstractSyncBean<User> {

	public UserSyncBean(User syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}
	
	public UserSyncBean(User syncObj, int wid, SyncType syncType) {
		super(syncObj, syncType);
		super.wid = wid;
	}

	@Override
	protected void buildingBean(User syncObj) throws Exception {
		if (syncObj.getEnterprise().getIndustryType() == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		if (wid == null) {
			wid = syncObj.getEnterprise().getIndustryType();
		}
		if (ignoreFields == null) {
			ignoreFields = "id,code,status,eid,sendemail_id";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		foreignKeyMap.put("enterprise_id", new String[]{"enterprise", "eid", "id"});
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "uid";
		uniqueKeyValue = syncObj.getUid();

		selectSql = "SELECT u.*,e.eid FROM user as u,enterprise as e WHERE u.enterprise_id = e.id AND u.uid = '" + uniqueKeyValue + "'";

		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
