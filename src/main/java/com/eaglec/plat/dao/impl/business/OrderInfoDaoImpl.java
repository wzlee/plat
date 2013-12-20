package com.eaglec.plat.dao.impl.business;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.business.OrderInfoDao;
import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.domain.business.OrderInfo;

@Repository
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements
		OrderInfoDao {
	@Override
	public int add(OrderInfo o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public OrderInfo save(OrderInfo o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(OrderInfo o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public OrderInfo saveOrUpdate(OrderInfo o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public OrderInfo update(OrderInfo o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}
