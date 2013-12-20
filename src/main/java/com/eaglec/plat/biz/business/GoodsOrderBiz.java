package com.eaglec.plat.biz.business;

import java.util.List;

import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.view.GoodsOrderView;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.OrderAndBiddingView;
/**
 * 订单Service
 * @author xuwf
 * @since 2013-9-6
 * 
 */
public interface GoodsOrderBiz {
	
	/**
	 * 添加服务订单
	 * @author xuwf
	 * @since 2013-9-6
	 * @param goodsOrder
	 */
	public void saveGoodsOrder(GoodsOrder goodsOrder);
	
	/**
	 * 更新服务订单
	 * @author xuwf
	 * @since 2013-9-14
	 * @param goodsOrder
	 */
	public void updateGoodsOrder(GoodsOrder goodsOrder);
	
	/**
	 * 复合条件订单查询
	 * @author xuwf
	 * @since 2013-9-12
	 * 
	 * @param userId		登录用户id
	 * @param orderNo		订单编号	支持模糊查询
	 * @param serviceName	服务商品
	 * @param buyer			买家
	 * @param orderStatus	订单状态
	 * @param startTime		下单时间
	 * @param endTime		结束时间
	 * @param flag 			记号(如果是买家查询flag=0，如果是卖家查询flag=1)
	 * @param start
	 * @param limit
	 * @return	easyUI格式读取
	 */
	public JSONRows<GoodsOrder> query(Integer enterpriseId,String orderNo,String serviceName,
			Integer flag,String orderStatus,String startTime,
			String endTime,int start, int limit);
	
	/**
	 * 获取用户所有状态的买单
	 * @author liuliping
	 * @since 2013-11-25
	 * 
	 * @param userId		登录用户id
	 * @return	手机端app数据
	 */
	List<Object> getPurchaseOrders(Integer enterpriseId);
	
	/**
	 * 复合条件查询招标单、订单视图
	 * @author xuwf
	 * @since 2013-10-10
	 * 
	 * @param userId		登录用户id
	 * @param orderNo		订单编号	支持模糊查询
	 * @param buyer			买家
	 * @param orderStatus	订单状态
	 * @param startTime		下单时间
	 * @param endTime		结束时间
	 * @param flag 			记号(如果是买家查询flag=0，如果是卖家查询flag=1)
	 * @param start
	 * @param limit
	 * @return	easyUI格式读取
	 */
	public JSONRows<OrderAndBiddingView> queryBO(Integer userId,String orderNo,String serviceName,
			Integer flag,String orderStatus,String startTime,
			String endTime,int start, int limit);
	
	/**	
	 * 根据不同状态查询招标单、订单视图(分为买家和卖家)
	 * @author xuwf
	 * @since 2013-9-12
	 * 
	 * @param userId		登录用户
	 * @param orderStatus	订单状态
	 * @param flag			记号(如果是买家查询flag=0，如果是卖家查询flag=1)
	 * @param start
	 * @param limit
	 * @return	easyUI格式读取
	 */
//	public JSONRows<OrderAndBiddingView> queryBOByStatus(Integer userId,String orderStatus,Integer flag,int start, int limit);
	
	/**	
	 * 根据不同状态查询(分为买家和卖家)
	 * @author xuwf
	 * @since 2013-9-12
	 * 
	 * @param userId		登录用户
	 * @param orderStatus	订单状态
	 * @param flag			记号(如果是买家查询flag=0，如果是卖家查询flag=1)
	 * @param start
	 * @param limit
	 * @return	easyUI格式读取
	 */
	public JSONRows<GoodsOrder> queryByStatus(Integer enterpriseId,String orderStatus,Integer flag,int start, int limit);

	/**
	 * 根据订单id查询订单
	 * @author xuwf
	 * @since 2013-9-14
	 * 
	 * @param orderId
	 * @return	订单
	 */
	public GoodsOrder findById(Integer orderId);
	
	/**
	 * 混合条件查询订单(支撑运营平台-订单管理)
	 * @author xuwf
	 * @since 2013-9-24
	 * @param orderNumber	订单编号
	 * @param orderStatus	订单状态
	 * @param startTime		下单时间
	 * @param endTime		结束时间
	 * @param start
	 * @param limit
	 * @return
	 */
	public JSONData<GoodsOrder> queryOrder(String orderNumber,String serviceName,String orderStatus,
			String startTime,String endTime,int start,int limit);

	/**
	 * 我的买单汇总(用户中心首页)
	 * @author Xiadi
	 * @since 2013-10-17 
	 *
	 * @param purchaserId 指定买家企业Id
	 */
	public int[] findMyTradingOfBuy(Integer purchaserId);
	
	/**
	 * 我的卖单汇总(用户中心首页)
	 * @author Xiadi
	 * @since 2013-10-17 
	 *
	 * @param sellerId 指定卖家企业Id
	 */
	public int[] findMyTradingOfSell(Integer sellerId);

	/**
	 * 根据用户id来获取订单(分)
	 * */
}
