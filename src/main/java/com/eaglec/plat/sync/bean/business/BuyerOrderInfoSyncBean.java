package com.eaglec.plat.sync.bean.business;

import java.util.HashMap;

import org.springframework.util.StringUtils;

import com.eaglec.plat.domain.business.OrderInfo;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;

/**
 * 订单详情同步买方类
 * 
 * @author xuwf
 * @since 2013-12-06
 */
public class BuyerOrderInfoSyncBean extends AbstractSyncBean<OrderInfo> {

	public BuyerOrderInfoSyncBean(OrderInfo syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(OrderInfo syncObj) throws Exception {
		if (syncObj.getGoodsOrder().getBuyer().getEnterprise().getIndustryType() == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		wid = syncObj.getGoodsOrder().getBuyer().getEnterprise().getIndustryType();
		if (ignoreFields == null) {
			ignoreFields = "id,uid,oid,manager_id,staff_id";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		
		if(!StringUtils.isEmpty(syncObj.getProcessor())){//操作人为user
			foreignKeyMap.put("processor_id", new String[]{"user", "uid", "id"});
		}
		
		foreignKeyMap.put("order_id", new String[]{"goodsorder", "oid", "id"});
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "oiid";
		uniqueKeyValue = syncObj.getOiid();

		if(!StringUtils.isEmpty(syncObj.getProcessor())){//操作人为user
			selectSql = "SELECT oi.*,u.uid,o.oid FROM orderinfo oi,user u,goodsorder o WHERE oi.processor_id = u.id AND"
				+" oi.order_id = o.id AND oi.oiid = '"+uniqueKeyValue+"'";
		}else{
			selectSql = "SELECT oi.*,o.oid FROM orderinfo oi,goodsorder o WHERE "
				+" oi.order_id = o.id AND oi.oiid = '"+uniqueKeyValue+"'";
		}
		
		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
}
