package com.eaglec.plat.sync.bean.business;


import com.eaglec.plat.domain.business.Appeal;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
/**
 * 订单申诉同步类
 * 
 * @author xuwf
 * @since 2013-12-06
 */
public class AppealSyncBean extends AbstractSyncBean<Appeal> {

	public AppealSyncBean(Appeal syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(Appeal syncObj) throws Exception {
		Integer buyerWid = syncObj.getGoodsOrder().getBuyer().getEnterprise().getIndustryType();
		Integer sellerWid = syncObj.getGoodsOrder().getService().getEnterprise().getIndustryType();
		if (buyerWid == null) {
			throw new Exception("appeal.getGoodsOrder().getBuyer().getEnterprise().getIndustryType() is null");
		}
		if (sellerWid == null) {
			throw new Exception("appeal.getGoodsOrder().getService().getEnterprise().getIndustryType() is null");
		}
//		//同步订单申诉到买家窗口（订单没必要同步到买家窗口）
//		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Appeal>(
//				new BuyerAppealSyncBean(syncObj, SyncFactory.getBuyerSyncType(syncObj.getGoodsOrder()))));
		//同步订单申诉到卖家窗口
		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Appeal>(
				new SellerAppealSyncBean(syncObj, SyncFactory.getSellerSyncType(syncObj.getGoodsOrder()))));
		
		super.syncType = SyncType.NONE;
	}
}
