package com.eaglec.plat.biz.business;

import java.util.List;

import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.business.OrderEvaluation;
import com.eaglec.plat.view.JSONData;

/**
 * 订单评价Biz
 * @author xuwf
 * @since 2013-9-11
 *
 */
public interface OrderEvaluationBiz {
	
	/**
	 * 保存订单评价
	 * @author xuwf
	 * @since 2013-9-14
	 * 
	 * @param orderEvaluation
	 */
	public void saveOrderEvaluation(OrderEvaluation orderEvaluation);
	
	/**
	 * 根据订单查询该订单的评价信息
	 * @author xuwf
	 * @since 2013-9-17
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderEvaluation> findList(Integer orderId);
	
	/**
	 * 根据订单查询该订单的评价信息
	 * @author xuwf
	 * @since 2013-9-29
	 * 
	 * @param orderId
	 * @return
	 */
	public JSONData<OrderEvaluation> findEval(Integer orderId,int start,int limit);
	
	/**
	 * 得到某张订单的买家评价（满意度）
	 * @author xuwf
	 * @since 2013-10-08
	 * 
	 * @param goodsOrder
	 * @return
	 */
	public Object getBuyerSatisfaction(GoodsOrder goodsOrder);
	
	/**
	 * 得到某张订单的卖家评价（满意度）
	 * @author xuwf
	 * @since 2013-10-14
	 * 
	 * @param goodsOrder
	 * @return
	 */
	public Object getSellerSatisfaction(GoodsOrder goodsOrder);
}
