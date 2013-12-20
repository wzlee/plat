package com.eaglec.plat.sync.bean.business;

import java.util.HashMap;

import com.eaglec.plat.domain.business.OrderEvaluation;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;

/**
 * 卖方评价同步类
 * 
 * @author xuwf
 * @since 2013-12-06
 */
public class SellerOrderEvaluationSyncBean extends AbstractSyncBean<OrderEvaluation> {

	public SellerOrderEvaluationSyncBean(OrderEvaluation syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(OrderEvaluation syncObj) throws Exception {
		if (syncObj.getGoodsOrder().getService().getEnterprise().getIndustryType() == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		wid = syncObj.getGoodsOrder().getService().getEnterprise().getIndustryType();
		if (ignoreFields == null) {
			ignoreFields = "id,uid,sid,oid,service_id,staff_id";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		foreignKeyMap.put("user_id", new String[]{"user", "uid", "id"});
		foreignKeyMap.put("order_id", new String[]{"goodsorder", "oid", "id"});
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "oeid";
		uniqueKeyValue = syncObj.getOeid();

		selectSql = "SELECT oe.*,u.uid,o.oid FROM orderevaluation oe,user u,goodsorder o WHERE oe.user_id = u.id"
				+" AND oe.order_id = o.id AND oe.oeid = '"+uniqueKeyValue+"'";
		
		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
