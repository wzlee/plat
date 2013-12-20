package com.eaglec.plat.sync.bean.business;

import java.util.HashMap;

import com.eaglec.plat.domain.business.Appeal;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;

/**
 * 订单申诉同步卖方类
 * 
 * @author xuwf
 * @since 2013-12-06
 */
public class SellerAppealSyncBean extends AbstractSyncBean<Appeal> {

	public SellerAppealSyncBean(Appeal syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(Appeal syncObj) throws Exception {
		if (syncObj.getGoodsOrder().getService().getEnterprise().getIndustryType() == null) {
			throw new Exception("appeal.getGoodsOrder().getEnterprise().getIndustryType() is null");
		}
		wid = syncObj.getGoodsOrder().getService().getEnterprise().getIndustryType();
		if (ignoreFields == null) {
			ignoreFields = "id,uid,oid,manager_id,staff_id";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		foreignKeyMap.put("user_id", new String[]{"user", "uid", "id"});
		foreignKeyMap.put("order_id", new String[]{"goodsorder", "oid", "id"});
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "aid";
		uniqueKeyValue = syncObj.getAid();

		selectSql = "SELECT a.*,u.uid,o.oid FROM appeal a,user u,goodsorder o WHERE a.user_id = u.id AND"
				+" a.order_id = o.id AND a.aid = '"+uniqueKeyValue+"'";
		
		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
