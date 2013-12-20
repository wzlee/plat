package com.eaglec.plat.sync.bean.business;


import com.eaglec.plat.domain.business.OrderEvaluation;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
/**
 * 订单评价同步类
 * 
 * @author xuwf
 * @since 2013-12-06
 */
public class OrderEvaluationSyncBean extends AbstractSyncBean<OrderEvaluation> {

	public OrderEvaluationSyncBean(OrderEvaluation syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(OrderEvaluation syncObj) throws Exception {
		Integer buyerWid = syncObj.getGoodsOrder().getBuyer().getEnterprise().getIndustryType();
		Integer sellerWid = syncObj.getGoodsOrder().getService().getEnterprise().getIndustryType();
		if (buyerWid == null) {
			throw new Exception("OrderEvaluation.getGoodsOrder().getBuyer().getEnterprise().getIndustryType() is null");
		}
		if (sellerWid == null) {
			throw new Exception("OrderEvaluation.getGoodsOrder().getService().getEnterprise().getIndustryType() is null");
		}
//		同步订单评价到买家窗口（订单没必要同步到买家窗口）
//		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderEvaluation>(
//				new BuyerOrderEvaluationSyncBean(syncObj, SyncFactory.getBuyerSyncType(syncObj.getGoodsOrder()))));
		//同步订单评价到卖家窗口
		SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderEvaluation>(
				new SellerOrderEvaluationSyncBean(syncObj, SyncFactory.getSellerSyncType(syncObj.getGoodsOrder()))));
		
		super.syncType = SyncType.NONE;
	}
}
