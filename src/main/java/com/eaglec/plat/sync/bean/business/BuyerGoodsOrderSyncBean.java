package com.eaglec.plat.sync.bean.business;

import java.util.HashMap;

import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;

/**
 * 买家订单同步类
 * 
 * @author xuwf
 * @since 2013-12-05
 */
public class BuyerGoodsOrderSyncBean extends AbstractSyncBean<GoodsOrder> {

	public BuyerGoodsOrderSyncBean(GoodsOrder syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(GoodsOrder syncObj) throws Exception {
		if (syncObj.getBuyer().getEnterprise().getIndustryType() == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		wid = syncObj.getBuyer().getEnterprise().getIndustryType();
		if (ignoreFields == null) {
			ignoreFields = "id,uid,sid,biddingService_id,staff_id,seller_id,purchaser_id";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		foreignKeyMap.put("buyer_id", new String[]{"user", "uid", "id"});
		foreignKeyMap.put("service_id", new String[]{"service", "sid", "id"});
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "oid";
		uniqueKeyValue = syncObj.getOid();
		
		selectSql = "SELECT o.*,u.uid,s.sid FROM goodsorder o,user u,service s WHERE o.buyer_id = u.id AND o.service_id = s.id"
					+" AND o.oid = '"+uniqueKeyValue+"'";
				
		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
