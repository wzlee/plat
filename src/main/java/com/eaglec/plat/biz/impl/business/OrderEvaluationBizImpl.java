package com.eaglec.plat.biz.impl.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.business.OrderEvaluationBiz;
import com.eaglec.plat.dao.business.OrderEvaluationDao;
import com.eaglec.plat.dao.business.ResponseBiddingDao;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.business.OrderEvaluation;
import com.eaglec.plat.domain.business.ResponseBidding;
import com.eaglec.plat.view.JSONData;

@Service
public class OrderEvaluationBizImpl implements OrderEvaluationBiz {
	@Autowired
	private OrderEvaluationDao orderEvaluationDao;
	@Autowired
	private ResponseBiddingDao responseBiddingDao;
	@Override
	public void saveOrderEvaluation(OrderEvaluation orderEvaluation) {
		orderEvaluationDao.save(orderEvaluation);
	}
	
	@Override
	public List<OrderEvaluation> findList(Integer orderId) {
		String hql = "from OrderEvaluation e where e.goodsOrder.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderId);
		return orderEvaluationDao.findList(hql, params);
	}

	@Override
	public JSONData<OrderEvaluation> findEval(Integer orderId,int start,int limit) {
		String hql = "from OrderEvaluation e where e.goodsOrder.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderId);
		return orderEvaluationDao.outJSONData(hql, params, start, limit);
	}

	@Override
	public Object getBuyerSatisfaction(GoodsOrder goodsOrder) {
		String hql = null;
		if(!StringUtils.isEmpty(goodsOrder.getBuyer())){//主账号评价
			if(!StringUtils.isEmpty(goodsOrder.getService())){//普通服务
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+" and e.user.id = " + goodsOrder.getBuyer().getId();
			}else{//招标服务
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+" and e.user.id = " + goodsOrder.getBiddingService().getUser().getId();
			}
		}else{//子账号评价
			if(!StringUtils.isEmpty(goodsOrder.getService())){//普通服务
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+" and e.staff.id = " + goodsOrder.getStaff().getId();
			}else{//招标服务
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+" and e.staff.id = " + goodsOrder.getBiddingService().getStaff().getId();
			}
		}
		return orderEvaluationDao.uniqueResult(hql);
	}
	
	@Override
	public Object getSellerSatisfaction(GoodsOrder goodsOrder){
		String hql = null;
		if(!StringUtils.isEmpty(goodsOrder.getBuyer())){//主账号评价
			if(!StringUtils.isEmpty(goodsOrder.getService())){//普通服务
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+ " and e.user.enterprise.id =" + goodsOrder.getService().getEnterprise().getId();
			}else{//招标服务
				ResponseBidding rb = responseBiddingDao.get(goodsOrder.getBiddingService().getRid());
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+ " and e.user.enterprise.id =" + rb.getUser().getEnterprise().getId();
			}
		}else{//子账号评价
			if(!StringUtils.isEmpty(goodsOrder.getService())){//普通服务
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+ " and e.staff.parent.enterprise.id =" + goodsOrder.getService().getEnterprise().getId();
			}else{//招标服务
				ResponseBidding rb = responseBiddingDao.get(goodsOrder.getBiddingService().getRid());
				hql = "select e.satisfaction from OrderEvaluation e where e.goodsOrder.id = "+goodsOrder.getId()
					+ " and e.staff.parent.enterprise.id =" + rb.getStaff().getParent().getEnterprise().getId();
			}
		}
		return orderEvaluationDao.uniqueResult(hql);
	}
}
