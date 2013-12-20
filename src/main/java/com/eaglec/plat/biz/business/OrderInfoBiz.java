package com.eaglec.plat.biz.business;

import java.util.List;

import com.eaglec.plat.domain.business.OrderInfo;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;

/**
 * 订单交易详细信息Biz
 * @author xuwf
 * @since 2013-9-11
 *
 */
public interface OrderInfoBiz {
	
	/**
	 * 添加订单交易详细信息
	 * @author xuwf
	 * @since 2013-9-14
	 * @param orderInfo
	 */
	public void saveOrderInfo(OrderInfo orderInfo);
	
	/**
	 * 根据订单id查询该订单的订单详情
	 * @author xuwf
	 * @since 2013-9-17
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderInfo> findList(Integer orderId);
	
	/**
	 * 根据订单id查询该订单的订单详情(暂不使用)
	 * @author xuwf
	 * @since 2013-9-29
	 * 
	 * @param orderId
	 * @return
	 */
	public JSONData<OrderInfo> findOrderInfo(Integer orderId,int start,int limit);
	
	/**
	 * 根据订单id查询该订单的订单详情
	 * @author xuwf
	 * @since 2013-10-14
	 * @param orderId
	 * @param page
	 * @param rows
	 * @return
	 */
	public JSONRows<OrderInfo> findByOrderId(Integer orderId,int page,int rows);
}
