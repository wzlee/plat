package com.eaglec.plat.sync.bean.business;


import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.bean.EnterpriseSyncBean;
import com.eaglec.plat.sync.bean.UserSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
/**
 * 订单同步类
 * 
 * @author Xiadi
 * @since 2013-12-6
 */
public class GoodsOrderSyncBean extends AbstractSyncBean<GoodsOrder> {

	public GoodsOrderSyncBean(GoodsOrder syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(GoodsOrder syncObj) throws Exception {
		Integer buyerWid = syncObj.getBuyer().getEnterprise().getIndustryType();
		Integer sellerWid = syncObj.getService().getEnterprise().getIndustryType();
		if (buyerWid == null) {
			throw new Exception("GoodsOrder.getBuyer().getEnterprise().getIndustryType() is null");
		}
		if (sellerWid == null) {
			throw new Exception("GoodsOrder.getService().getEnterprise().getIndustryType() is null");
		}
		// 同步买家企业至卖家窗口
		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
				EnterpriseSyncBean(syncObj.getBuyer().getEnterprise(), sellerWid, SyncType.ONE)), true);
		// 同步买家用户至卖家窗口
		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
				UserSyncBean(syncObj.getBuyer(), sellerWid, SyncType.ONE)), true);
//		// 同步订单至买家窗口（订单没必要同步到买家窗口）
//		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(
//				new BuyerGoodsOrderSyncBean(syncObj, SyncFactory.getBuyerSyncType(syncObj))));
		// 同步订单至卖家窗口
		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(
				new SellerGoodsOrderSyncBean(syncObj, SyncFactory.getSellerSyncType(syncObj))));
		
		super.syncType = SyncType.NONE;
	}
}