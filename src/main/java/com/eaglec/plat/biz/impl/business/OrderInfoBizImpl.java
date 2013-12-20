package com.eaglec.plat.biz.impl.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.business.OrderInfoBiz;
import com.eaglec.plat.dao.business.OrderInfoDao;
import com.eaglec.plat.domain.business.OrderInfo;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;

@Service
public class OrderInfoBizImpl implements OrderInfoBiz {
	@Autowired
	private OrderInfoDao orderInfoDao;
	
	@Override
	public void saveOrderInfo(OrderInfo orderInfo) {	
		orderInfoDao.save(orderInfo);
	}

	@Override
	public List<OrderInfo> findList(Integer orderId) {
		String hql = "from OrderInfo i where i.goodsOrder.id = :id order by i.processTime";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderId);
		return orderInfoDao.findList(hql, params);
	}
	
	@Override
	public JSONRows<OrderInfo> findByOrderId(Integer orderId,int page,int rows){
		String hql = "from OrderInfo i where i.goodsOrder.id = :id order by i.processTime";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderId);
		return orderInfoDao.outJSONRows(hql, params, page, rows);
	}
	@Override
	public JSONData<OrderInfo> findOrderInfo(Integer orderId,int start,int limit) {
		String hql = "from OrderInfo i where i.goodsOrder.id = :id order by i.processTime";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderId);
		return orderInfoDao.outJSONData(hql, params, start, limit);
	}

}
