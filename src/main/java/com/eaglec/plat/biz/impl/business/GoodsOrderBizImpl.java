package com.eaglec.plat.biz.impl.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.business.GoodsOrderBiz;
import com.eaglec.plat.dao.business.BiddingServiceDao;
import com.eaglec.plat.dao.business.GoodsOrderDao;
import com.eaglec.plat.dao.business.ResponseBiddingDao;
import com.eaglec.plat.dao.user.UserDao;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.GoodsOrderView;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.OrderAndBiddingView;

@Service
public class GoodsOrderBizImpl implements GoodsOrderBiz{
	@Autowired
	private GoodsOrderDao goodsOrderDao;
	@Autowired 
	private UserDao userDao;
	@Autowired
	private BiddingServiceDao biddingServiceDao;
	@Autowired
	private ResponseBiddingDao responseBiddingDao;
	
	@Override
	public void saveGoodsOrder(GoodsOrder goodsOrder) {
		goodsOrderDao.save(goodsOrder);
	}

	@Override
	public JSONRows<GoodsOrder> query(Integer enterpriseId, String orderNo,String serviceName, 
			Integer flag, String orderStatus, String startTime,
			String endTime, int start, int limit) {
		String hql = "from GoodsOrder o where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_BUYER){//买家登录
			hql += " and o.purchaser_id = :enterpriseId";
			params.put("enterpriseId", enterpriseId);
		}else if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_SELLER){//卖家登录
			hql += " and o.seller_id = :enterpriseId";
			params.put("enterpriseId", enterpriseId);
		}
		if(!StringUtils.isEmpty(orderNo)){
			hql += " and o.orderNumber like :orderNo";
			params.put("orderNo", "%"+orderNo+"%");		
		}
		if(!StringUtils.isEmpty(serviceName)){
			hql += " and o.serviceName like :serviceName";
			params.put("serviceName", "%"+serviceName+"%");		
		}
		if(!StringUtils.isEmpty(orderStatus)){
			hql += " and o.orderStatus in ("+orderStatus+")";
		}
		if(!StringUtils.isEmpty(startTime)){
			hql += " and o.createTime >= :startTime";
			params.put("startTime", startTime+ " 00:00:00");
		}
		if(!StringUtils.isEmpty(endTime)){
			hql += " and o.createTime <= :endTime )";
			params.put("endTime", endTime+ " 23:59:59");
		}
		hql += " order by o.createTime desc";
		return goodsOrderDao.outJSONRows(hql, params, start, limit);
	}

	@Override
	public JSONRows<OrderAndBiddingView> queryBO(Integer userId,
			String orderNo,String serviceName, Integer flag, String orderStatus, String startTime,
			String endTime, int start, int limit) {
		List<OrderAndBiddingView> viewList = new ArrayList<OrderAndBiddingView>();
		/********************查找订单集合***************************/
		String hql = "from GoodsOrder o where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_BUYER){//买家登录
			User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
			if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
				hql += " and o.buyer.enterprise.id = :userId";
				params.put("userId", user.getEnterprise().getId());
			}			
		}else if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_SELLER){//卖家登录
			if(!StringUtils.isEmpty(userId)){
				User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
				if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
					hql += " and  o.seller_id = :enterpriseId";
					params.put("enterpriseId", user.getEnterprise().getId());
				}
			}	
		}
		
		if(!StringUtils.isEmpty(orderNo)){
			hql += " and o.orderNumber like :orderNo";
			params.put("orderNo", "%"+orderNo+"%");		
		}
		if(!StringUtils.isEmpty(serviceName)){
			hql += " and o.service.serviceName like :serviceName";
			params.put("serviceName", "%"+serviceName+"%");		
		}
		if(!StringUtils.isEmpty(orderStatus)){
			hql += " and o.orderStatus in ("+orderStatus+")";
		}
		
		if(!StringUtils.isEmpty(startTime)){
			hql += " and o.createTime >= :startTime";
			params.put("startTime", startTime+ " 00:00:00");
		}
		
		if(!StringUtils.isEmpty(endTime)){
			hql += " and o.createTime <= :endTime";
			params.put("endTime", endTime+ " 23:59:59");
		}
		
		hql += " order by o.createTime desc";
		List<GoodsOrder> goodsOrderList = goodsOrderDao.findList(hql, params);
		for(GoodsOrder goodsOrder:goodsOrderList){
			if(goodsOrder.getBiddingService() == null){//普通服务
				OrderAndBiddingView obv = new OrderAndBiddingView(goodsOrder.getId(), goodsOrder.getOrderNumber(), goodsOrder.getService().getId(), goodsOrder.getService().getServiceName(), goodsOrder.getService().getCategory().getText(), goodsOrder.getTransactionPrice(), goodsOrder.getBuyer().getEnterprise().getName(), goodsOrder.getService().getEnterprise().getName(), goodsOrder.getOrderStatus(), goodsOrder.getCreateTime(), goodsOrder.getOrderSource());
				viewList.add(obv);
			}
		}
		/********************查找招标服务订单集合***************************/
		hql = "from GoodsOrder b where 1=1";
		Map<String, Object> bidparams = new HashMap<String, Object>();
		List<GoodsOrder> orderList = new ArrayList<GoodsOrder>();
		if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_BUYER){//买家登录
			User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
			if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
				hql += " and b.biddingService.user.enterprise.id = :userId";
				bidparams.put("userId", user.getEnterprise().getId());
			}			
		}else if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_SELLER){//卖家登录
			User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
			if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
				hql += " and  b.seller_id = :enterpriseId";
				params.put("enterpriseId", user.getEnterprise().getId());
			}
		}
		
		if(!StringUtils.isEmpty(orderNo)){
			hql += " and b.orderNumber like :orderNo";
			bidparams.put("orderNo", "%"+orderNo+"%");		
		}
		if(!StringUtils.isEmpty(serviceName)){
			hql += " and b.biddingService.name like :serviceName";
			bidparams.put("serviceName", "%"+serviceName+"%");		
		}
		if(!StringUtils.isEmpty(orderStatus)){
			hql += " and b.orderStatus in ("+orderStatus+")";
		}
		
		if(!StringUtils.isEmpty(startTime)){
			hql += " and b.createTime >= :startTime";
			bidparams.put("startTime", startTime+ " 00:00:00");
		}
		
		if(!StringUtils.isEmpty(endTime)){
			hql += " and b.createTime <= :endTime";
			bidparams.put("endTime", endTime+ " 23:59:59");
		}
		
		hql += " order by b.createTime desc";
		orderList = goodsOrderDao.findList(hql, params);
		for (GoodsOrder goodsOrder : orderList) {
			if(goodsOrder.getService() == null){//招标服务
				OrderAndBiddingView obv = new OrderAndBiddingView(goodsOrder.getId(), goodsOrder.getOrderNumber(), goodsOrder.getBiddingService().getId(), goodsOrder.getBiddingService().getName(), goodsOrder.getBiddingService().getCategory().getText(), goodsOrder.getTransactionPrice(), goodsOrder.getBiddingService().getUser().getEnterprise().getName(), goodsOrder.getBiddingService().getRname(), goodsOrder.getOrderStatus(), goodsOrder.getCreateTime(), goodsOrder.getOrderSource());
				viewList.add(obv);
			}
		}
		
		return new JSONRows<OrderAndBiddingView>(true, getViewList(viewList, start, limit),viewList.size());
	}
	/**
	 * 分页以上方法的集合（有待改善。性能太差）
	 * @param viewList
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<OrderAndBiddingView> getViewList(List<OrderAndBiddingView> viewList,int start,int limit){
		List<OrderAndBiddingView> list = new ArrayList<OrderAndBiddingView>();
		for(int i = start;i<viewList.size() && i<(start+limit);i++){
			list.add(viewList.get(i));
		}
		
		return list;
	}
//	@Override
//	public JSONRows<OrderAndBiddingView> queryBOByStatus(Integer userId,String orderStatus,Integer flag,int start, int limit){
//		List<OrderAndBiddingView> viewList = new ArrayList<OrderAndBiddingView>();
//		/********************查找订单集合***************************/
//		String hql = "from GoodsOrder o where 1=1";
//		Map<String, Object> params = new HashMap<String, Object>();
//		if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_BUYER){//买家登录
//			if(!StringUtils.isEmpty(userId)){
//				hql += " and o.buyer.id = :userId";
//				params.put("userId", userId);
//			}
//		}else if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_SELLER){//卖家登录
//			if(!StringUtils.isEmpty(userId)){
//				User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
//				if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
//					hql += " and o.service.enterprise.id = :enterpriseId";
//					params.put("enterpriseId", user.getEnterprise().getId());
//				}
//			}
//		}
//		if(!StringUtils.isEmpty(orderStatus)){
//			hql += " and o.orderStatus in ("+orderStatus+")";
//		}
//		hql += " order by o.createTime desc";
//		List<GoodsOrder> goodsOrderList = goodsOrderDao.findList(hql, params);
//		for(GoodsOrder goodsOrder:goodsOrderList){
//			OrderAndBiddingView obv = new OrderAndBiddingView(goodsOrder.getId(),goodsOrder.getOrderNumber(),goodsOrder.getService().getId(),goodsOrder.getService().getServiceName(),goodsOrder.getService().getCategory().getText(),goodsOrder.getTransactionPrice(),goodsOrder.getBuyer().getEnterprise().getName(),goodsOrder.getService().getEnterprise().getName(),goodsOrder.getOrderStatus(),goodsOrder.getCreateTime(),goodsOrder.getOrderSource());
//			viewList.add(obv);
//		}
//		/********************查找招单集合***************************/
//		hql = "from BiddingService b where status > 5";
//		Map<String, Object> bidparams = new HashMap<String, Object>();
//		
//		if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_BUYER){//买家登录
//			if(!StringUtils.isEmpty(userId)){
//				hql += " and b.user.id = :userId";
//				bidparams.put("userId", userId);
//			}			
//		}else if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_SELLER){//卖家登录
//			if(!StringUtils.isEmpty(userId)){
//				hql += " and (select r.user.id from ResponseBidding r where r.id = b.rid) = :userId";
//				bidparams.put("userId", userId);
//			}	
//		}
//		
//		if(!StringUtils.isEmpty(orderStatus)){
//			hql += " and b.status in ("+orderStatus+")";
//		}
//		
//		hql += " order by b.createTime desc";
//		List<BiddingService> biddingList = biddingServiceDao.findList(hql, bidparams);
//		for (BiddingService biddingService : biddingList) {
//			if(!StringUtils.isEmpty(biddingService.getRid())){
//				ResponseBidding rb = responseBiddingDao.get(biddingService.getRid());
//				OrderAndBiddingView obv = new OrderAndBiddingView(biddingService.getId(),biddingService.getBidNo(),biddingService.getId(), biddingService.getName(), biddingService.getCategory().getText(), rb.getBidPrice(), biddingService.getUser().getEnterprise().getName(), rb.getUser().getEnterprise().getName(), biddingService.getStatus(), biddingService.getCreateTime(),);
//				viewList.add(obv);
//			}
//		}
//		
//		return new JSONRows<OrderAndBiddingView>(true, getViewList(viewList, start, limit),viewList.size());
//	}
//	
	@Override
	public JSONRows<GoodsOrder> queryByStatus(Integer enterpriseId, String orderStatus,
			Integer flag, int start, int limit) {
		String hql = "from GoodsOrder o where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_BUYER){//买家登录
			hql += " and o.purchaser_id = :enterpriseId";
			params.put("enterpriseId", enterpriseId);	
		}else if(!StringUtils.isEmpty(flag) && flag == Constant.FLAG_SELLER){//卖家登录
			hql += " and o.seller_id = :enterpriseId";
			params.put("enterpriseId", enterpriseId);
		}
		
		if(!StringUtils.isEmpty(orderStatus)){
			hql += " and o.orderStatus in ("+orderStatus+")";
		}
		hql += " order by o.createTime desc";
		return goodsOrderDao.outJSONRows(hql, params, start, limit);
	}

	@Override
	public GoodsOrder findById(Integer orderId) {		
		return goodsOrderDao.get(orderId);
	}

	@Override
	public void updateGoodsOrder(GoodsOrder goodsOrder) {
		goodsOrderDao.update(goodsOrder);
	}

	@Override
	public JSONData<GoodsOrder> queryOrder(String orderNumber,String serviceName,
			String orderStatus, String startTime, String endTime, int start,
			int limit) {
		String hql = "from GoodsOrder o where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(orderNumber)){
			hql += " and o.orderNumber like :orderNumber";
			params.put("orderNumber", "%"+orderNumber+"%");
		}
		if(!StringUtils.isEmpty(serviceName)){
			hql += " and o.serviceName like :serviceName";
			params.put("serviceName", "%"+serviceName+"%");
		}
		if(!StringUtils.isEmpty(orderStatus)){
			hql += " and o.orderStatus in (" + orderStatus + ")";
		}
		if(!StringUtils.isEmpty(startTime)){
			startTime = startTime.substring(0, startTime.indexOf("T"));
			hql += " and o.createTime >= :startTime";
			params.put("startTime", startTime+ " 00:00:00");
		}
		if(!StringUtils.isEmpty(endTime)){
			endTime = endTime.substring(0, endTime.indexOf("T"));
			hql += " and o.createTime <= :endTime";
			params.put("endTime", endTime+ " 23:59:59");
		}
		hql += " order by o.createTime desc";
		return goodsOrderDao.outJSONData(hql, params, start, limit);
	}

	@Override
	public int[] findMyTradingOfBuy(Integer purchaserId) {
		// 申请服务卖家已确认、申请服务卖家已拒绝、等待买家关闭的订单 数量汇总
		String hql = "select sum(case when orderStatus=" + Constant.TRANSACTIONS_IN + " then 1 else 0 end), " +
				"sum(case when orderStatus=" + Constant.ORDER_CANCEL + " then 1 else 0 end), " +
				"sum(case when orderStatus=" + Constant.TRANSACTIONS_IN + " or orderStatus=" + Constant.WAIT_BUYER_CLOSE + " then 1 else 0 end) " +
				"from GoodsOrder where purchaser_id = " + purchaserId;
		Object[] o = (Object[])goodsOrderDao.findObjects(hql).get(0);
		int[] ret = new int[3];
		for (int i = 0; i < o.length; i++) {
			ret[i] = StrUtils.toInt(o[i]);
		}
		return ret;
	}

	@Override
	public int[] findMyTradingOfSell(Integer sellerId) {
		// 等待确认订单、等待关闭订单  数量汇总
		String hql = "select sum(case when orderStatus=" + Constant.WAIT_SELLER_CONFIRM + " then 1 else 0 end), " +
				"sum(case when orderStatus=" + Constant.TRANSACTIONS_IN + " or orderStatus=" + Constant.WAIT_SELLER_CLOSE + " then 1 else 0 end) " +
				"from GoodsOrder where seller_id = " + sellerId;
		Object[] o = (Object[])goodsOrderDao.findObjects(hql).get(0);
		int[] ret = new int[2];
		for (int i = 0; i < o.length; i++) {
			ret[i] = StrUtils.toInt(o[i]);
		}
		return ret;
	}

	@Override
	public List<Object> getPurchaseOrders(Integer enterpriseId) {    //获取买单
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.eaglec.plat.view.GoodsOrderView(o.serviceName, o.id, o.service.id, o.biddingService.id, o.orderStatus, o.createTime) from GoodsOrder o where o.purchaser_id = ").
			append(enterpriseId).append(" order by o.createTime desc");
		return goodsOrderDao.findObjects(hql.toString());
	}

}
