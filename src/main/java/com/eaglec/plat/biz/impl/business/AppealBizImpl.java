package com.eaglec.plat.biz.impl.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.business.AppealBiz;
import com.eaglec.plat.dao.business.AppealDao;
import com.eaglec.plat.dao.user.UserDao;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.Appeal;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.OrderAndBiddingAppeal;

@Service
public class AppealBizImpl implements AppealBiz {
	
	@Autowired
	private AppealDao appealDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveAppeal(Appeal appeal) {
		appealDao.save(appeal);	
	}
	
	public JSONRows<Appeal> query(Integer enterpriseId, Integer appealType,String orderNumber,
			Integer flag, String startTime,String endTime, int start, int limit){
		String hql = "from Appeal a where 1=1";
		
		if(!StringUtils.isEmpty(flag) && flag == 0){	//买家登录
			hql += " and a.goodsOrder.purchaser_id = "+enterpriseId;
		}else if(!StringUtils.isEmpty(flag) && flag == 1){//卖家登录
			hql += " and a.goodsOrder.seller_id = "+enterpriseId;
		}
		
		if(!StringUtils.isEmpty(appealType)){//我的申诉
			hql += " and a.appealType = "+appealType;		
		}else{//我被申诉
			hql += " and a.appealType = "+appealType;	
		}
		
		if(!StringUtils.isEmpty(orderNumber)){
			hql += " and a.goodsOrder.orderNumber like "+"'%"+orderNumber+"%'";
		}
		
		if(!StringUtils.isEmpty(startTime)){
			hql += " and a.goodsOrder.createTime >= '"+startTime+" 00:00:00'";
		}
		
		if(!StringUtils.isEmpty(endTime)){
			hql += " and a.goodsOrder.createTime <= '"+endTime+" 23:59:59'";
		}
		
		hql += " order by a.appealTime desc";
		
		return appealDao.outJSONRows(hql, start, limit);
	};

//暂时去除
//	public JSONRows<OrderAndBiddingAppeal> query(Integer userId,Integer appealType, String orderNo, 
//			Integer flag, String startTime,
//			String endTime, int start, int limit) {
//		List<Appeal> appealList = new ArrayList<Appeal>();//保存其中一种
//		List<OrderAndBiddingAppeal> obaList	= new ArrayList<OrderAndBiddingAppeal>();//保存招单和订单的申诉
//		
//		String hql = "from Appeal a where 1=1";
//	
//		if(!StringUtils.isEmpty(flag) && flag == 0){	//买家登录
//			User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
//			if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
//				hql += " and a.goodsOrder.purchaser_id = "+user.getEnterprise().getId();
//			}
//		}else if(!StringUtils.isEmpty(flag) && flag == 1){//卖家登录
//			User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
//			if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
//				hql += " and a.goodsOrder.seller_id = "+user.getEnterprise().getId();
//			}
//		}
//		
//		if(!StringUtils.isEmpty(appealType)){//我的申诉
//			hql += " and a.appealType = "+appealType;		
//		}else{//我被申诉
//			hql += " and a.appealType = "+appealType;	
//		}
//		
//		if(!StringUtils.isEmpty(orderNo)){
//			hql += " and a.goodsOrder.orderNumber like "+"'%"+orderNo+"%'";
//		}
//		
//		if(!StringUtils.isEmpty(startTime)){
//			hql += " and a.goodsOrder.createTime >= '"+startTime+" 00:00:00'";
//		}
//		
//		if(!StringUtils.isEmpty(endTime)){
//			hql += " and a.goodsOrder.createTime <= '"+endTime+" 23:59:59'";
//		}
//		
//		hql += " order by a.appealTime desc";
//		appealList = appealDao.findList(hql);
//		for (Appeal appeal : appealList) {
//			if(appeal.getGoodsOrder().getBiddingService() == null){
//				OrderAndBiddingAppeal oba = new OrderAndBiddingAppeal(appeal.getId(),appeal.getGoodsOrder().getOrderNumber(), appeal.getGoodsOrder().getService().getId(),
//						appeal.getGoodsOrder().getService().getServiceName(), appeal.getGoodsOrder().getService().getCategory().getText(), appeal.getGoodsOrder().getService().getCostPrice().toString(), appeal.getGoodsOrder().getTransactionPrice(), 
//						appeal.getGoodsOrder().getBuyer().getEnterprise().getName(), appeal.getGoodsOrder().getService().getEnterprise().getName(), appeal.getAppealType(), appeal.getHandlerRemark(), appeal.getProcessTime(),appeal.getGoodsOrder().getCreateTime(),appeal.getReason());
//				obaList.add(oba);
//			}
//		}
//		/************************招单申诉单*****************************/
//		hql = "from Appeal a where 1=1";
//		
//		if(!StringUtils.isEmpty(flag) && flag == 0){	//买家登录
//			User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
//			if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
//				hql += " and a.goodsOrder.purchaser_id = "+user.getEnterprise().getId();
//			}
//		}else if(!StringUtils.isEmpty(flag) && flag == 1){//卖家登录
//			User user = userDao.get(userId);//得到该用户方便以下得到用户企业id和登录用户企业id对比
//			if(!StringUtils.isEmpty(user.getEnterprise())){//数据暂时不准确，用户的企业不能为空
//				hql += " and a.goodsOrder.seller_id = "+user.getEnterprise().getId();
//			}
//		}
//		
//		if(!StringUtils.isEmpty(appealType) && appealType == 0){//我的申诉
//			if(!StringUtils.isEmpty(userId)){
//				hql += " and a.user.id = "+userId;
//			}			
//		}else if(!StringUtils.isEmpty(appealType) && appealType == 1){//我被申诉
//			if(!StringUtils.isEmpty(userId)){
//				hql += " and a.user.id !="+userId;
//			}	
//		}
//		
//		if(!StringUtils.isEmpty(orderNo)){
//			hql += " and a.goodsOrder.orderNumber like "+"'%"+orderNo+"%'";
//		}
//		
//		if(!StringUtils.isEmpty(startTime)){
//			hql += " and a.goodsOrder.createTime >= '"+startTime+" 00:00:00'";
//		}
//		
//		if(!StringUtils.isEmpty(endTime)){
//			hql += " and a.goodsOrder.createTime <= '"+endTime+" 23:59:59'";
//		}
//		
//		hql += " order by a.appealTime desc";
//		appealList = appealDao.findList(hql);
//		for (Appeal appeal : appealList) {
//			if(appeal.getGoodsOrder().getService() == null){
//			String price = null;
//			if(!StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMinPrice()) && !StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMaxPrice())){
//				price = appeal.getGoodsOrder().getBiddingService().getMinPrice().toString()+'-'+appeal.getGoodsOrder().getBiddingService().getMaxPrice();
//			}else if(!StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMinPrice())){
//				price = appeal.getGoodsOrder().getBiddingService().getMinPrice().toString();
//			}else if(!StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMaxPrice())){
//				price = appeal.getGoodsOrder().getBiddingService().getMaxPrice().toString();
//			}else{
//				price = "0";
//			}
//			OrderAndBiddingAppeal oba = new OrderAndBiddingAppeal(appeal.getId(),appeal.getGoodsOrder().getOrderNumber(), appeal.getGoodsOrder().getBiddingService().getId(),
//					appeal.getGoodsOrder().getBiddingService().getName(), appeal.getGoodsOrder().getBiddingService().getCategory().getText(), price, appeal.getGoodsOrder().getTransactionPrice(), 
//					appeal.getGoodsOrder().getBiddingService().getUser().getEnterprise().getName(), appeal.getGoodsOrder().getBiddingService().getRname(), appeal.getAppealType(), appeal.getHandlerRemark(), appeal.getProcessTime(),appeal.getGoodsOrder().getCreateTime(),appeal.getReason());
//			obaList.add(oba);
//			}
//		}
//		return new JSONRows<OrderAndBiddingAppeal>(true, getAppealList(obaList, start, limit), obaList.size());
//	}

	@Override
	public JSONData<Appeal> findByStatus(Integer orderStatus,String orderNumber,String appealType,int start,int limit) {
		String hql = "from Appeal a where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(orderStatus)){
			hql += " and a.goodsOrder.orderStatus = :orderStatus";
			params.put("orderStatus", orderStatus);
		}
		
		if(!StringUtils.isEmpty(orderNumber)){
			hql += " and a.goodsOrder.orderNumber like :orderNumber";
			params.put("orderNumber", "%"+orderNumber+"%");
		}
		
		if(!StringUtils.isEmpty(appealType)){
			hql += " and a.appealType in (" + appealType + ")";
		}
		hql += " order by a.appealTime desc";
		return appealDao.outJSONData(hql, params, start, limit);
	}

	@Override
	public JSONData<OrderAndBiddingAppeal> queryIntegrated(String orderNum,String appealType,String startTime,
			String endTime,String buyer,String seller,int start,int limit){
		List<Appeal> appealList = new ArrayList<Appeal>();//保存其中一种
		List<OrderAndBiddingAppeal> obaList	= new ArrayList<OrderAndBiddingAppeal>();//保存招单和订单的申诉
		String hql = "from Appeal a where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(orderNum)){
			hql += " and a.goodsOrder.orderNumber like :orderNumber";
			params.put("orderNumber", "%"+orderNum+"%");
		}
		if(!StringUtils.isEmpty(buyer)){
			hql += " and a.goodsOrder.buyer.enterprise.name like :buyer";
			params.put("buyer", "%"+buyer+"%");
		}
		if(!StringUtils.isEmpty(seller)){
			hql += " and a.goodsOrder.service.enterprise.name like :seller";
			params.put("seller", "%"+seller+"%");
		}
		if(!StringUtils.isEmpty(appealType)){
			hql += " and a.appealType in (" + appealType + ")";
		}
		
		if(!StringUtils.isEmpty(startTime)){
			startTime = startTime.substring(0, startTime.indexOf("T"));
			hql += " and a.processTime >= :startTime";
			params.put("startTime", startTime+" 00:00:00'");

		}
		if(!StringUtils.isEmpty(endTime)){
			endTime = endTime.substring(0, endTime.indexOf("T"));
			hql += " and a.processTime <= :endTime";
			params.put("endTime", endTime+" 23:59:59'");
		}
		
		hql += " order by a.processTime desc";
		appealList = appealDao.findList(hql, params);
		for (Appeal appeal : appealList) {
			if(appeal.getGoodsOrder().getOrderSource() == Constant.ORDER_SOURCE_S){
				OrderAndBiddingAppeal oba = new OrderAndBiddingAppeal(appeal.getId(),appeal.getGoodsOrder().getOrderNumber(), appeal.getGoodsOrder().getService().getId(),
						appeal.getGoodsOrder().getService().getServiceName(), appeal.getGoodsOrder().getService().getCategory().getText(), appeal.getGoodsOrder().getService().getCostPrice().toString(), appeal.getGoodsOrder().getTransactionPrice(), 
						appeal.getGoodsOrder().getBuyer().getEnterprise().getName(), appeal.getGoodsOrder().getService().getEnterprise().getName(), appeal.getAppealType(), appeal.getHandlerRemark(), appeal.getProcessTime(),appeal.getGoodsOrder().getCreateTime(),appeal.getReason(),appeal.getGoodsOrder());
				obaList.add(oba);
			}
		}
		/********************查找招单申诉集合**************************/
		hql = "from Appeal a where 1=1";
		Map<String, Object> params1= new HashMap<String, Object>();
		if(!StringUtils.isEmpty(orderNum)){
			hql += " and a.goodsOrder.orderNumber like :orderNumber";
			params1.put("orderNumber", "%"+orderNum+"%");
		}
		if(!StringUtils.isEmpty(buyer)){
			hql += " and a.goodsOrder.biddingService.user.enterprise.name like :buyer";
			params1.put("buyer", "%"+buyer+"%");
		}
		if(!StringUtils.isEmpty(seller)){
			hql += " and a.goodsOrder.biddingService.rname like :seller";
			params1.put("seller", "%"+seller+"%");
		}
		if(!StringUtils.isEmpty(appealType)){
			hql += " and a.appealType in (" + appealType + ")";
		}
		if(!StringUtils.isEmpty(startTime)){
			hql += " and a.processTime >= :startTime";
			params1.put("startTime", startTime+" 00:00:00'");
		}
		if(!StringUtils.isEmpty(endTime)){
			hql += " and a.processTime <= :endTime";
			params1.put("endTime", endTime+" 23:59:59'");
		}
		
		hql += " order by a.processTime desc";
		appealList = appealDao.findList(hql, params);
		for (Appeal appeal : appealList) {
			if(appeal.getGoodsOrder().getOrderSource() == Constant.ORDER_SOURCE_B){
				String price = null;
				if(!StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMinPrice()) && !StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMaxPrice())){
					price = appeal.getGoodsOrder().getBiddingService().getMinPrice().toString()+'-'+appeal.getGoodsOrder().getBiddingService().getMaxPrice();
				}else if(!StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMinPrice())){
					price = appeal.getGoodsOrder().getBiddingService().getMinPrice().toString();
				}else if(!StringUtils.isEmpty(appeal.getGoodsOrder().getBiddingService().getMaxPrice())){
					price = appeal.getGoodsOrder().getBiddingService().getMaxPrice().toString();
				}else{
					price = "0";
				}
				OrderAndBiddingAppeal oba = new OrderAndBiddingAppeal(appeal.getId(),appeal.getGoodsOrder().getOrderNumber(), appeal.getGoodsOrder().getBiddingService().getId(),
						appeal.getGoodsOrder().getBiddingService().getName(), appeal.getGoodsOrder().getBiddingService().getCategory().getText(), price, appeal.getGoodsOrder().getTransactionPrice(), 
						appeal.getGoodsOrder().getBiddingService().getUser().getEnterprise().getName(), appeal.getGoodsOrder().getBiddingService().getRname(), appeal.getAppealType(), appeal.getHandlerRemark(), appeal.getProcessTime(),appeal.getGoodsOrder().getCreateTime(),appeal.getReason(),appeal.getGoodsOrder());
				obaList.add(oba);
				}
		}
		return new JSONData<OrderAndBiddingAppeal>(true, getAppealList(obaList, start, limit), obaList.size());
		
	}
	/**
	 * 分页以上方法的集合（有待改善。性能太差）
	 * @param viewList
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<OrderAndBiddingAppeal> getAppealList(List<OrderAndBiddingAppeal> viewList,int start,int limit){
		List<OrderAndBiddingAppeal> list = new ArrayList<OrderAndBiddingAppeal>();
		for(int i = start;i<viewList.size() && i<(start+limit);i++){
			list.add(viewList.get(i));
		}
		
		return list;
	}
	@Override
	public Appeal findById(Integer id) {	
		return appealDao.get(id);
	}

	@Override
	public void update(Appeal appeal) {
		appealDao.update(appeal);	
	}

	@Override
	public JSONData<Appeal> queryByBidId(Integer bidId, int start, int limit) {
		String hql = "from Appeal a where a.biddingService.id = :bidId";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bidId", bidId);
		return appealDao.outJSONData(hql, params, start, limit);
	}

	@Override
	public Appeal queryByBidId(Integer bidId) {
		String hql = "from Appeal a where a.biddingService.id = :bidId";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bidId", bidId);
		return appealDao.get(hql, params);
	}

	@Override
	public Appeal queryByOrderId(Integer orderId) {
		String hql = "from Appeal a where a.goodsOrder.id = "+orderId;
		return appealDao.get(hql);
	}
	
}
