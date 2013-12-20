package com.eaglec.plat.sync.bean.business;


import com.eaglec.plat.domain.business.OrderInfo;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
/**
 * 订单流水同步类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class OrderInfoSyncBean extends AbstractSyncBean<OrderInfo> {

	public OrderInfoSyncBean(OrderInfo syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(OrderInfo syncObj) throws Exception {
		Integer buyerWid = syncObj.getGoodsOrder().getBuyer().getEnterprise().getIndustryType();
		Integer sellerWid = syncObj.getGoodsOrder().getService().getEnterprise().getIndustryType();
		if (buyerWid == null) {
			throw new Exception("OrderInfo.getGoodsOrder().getBuyer().getEnterprise().getIndustryType() is null");
		}
		if (sellerWid == null) {
			throw new Exception("OrderInfo.getGoodsOrder().getService().getEnterprise().getIndustryType() is null");
		}
//		//同步订单流水到买家窗口（订单没必要同步到买家窗口）
//		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(
//				new BuyerOrderInfoSyncBean(syncObj, SyncFactory.getBuyerSyncType(syncObj.getGoodsOrder()))));
		//同步订单流水到卖家窗口
		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(
				new SellerOrderInfoSyncBean(syncObj, SyncFactory.getSellerSyncType(syncObj.getGoodsOrder()))));
		
		super.syncType = SyncType.NONE;
	}
}
