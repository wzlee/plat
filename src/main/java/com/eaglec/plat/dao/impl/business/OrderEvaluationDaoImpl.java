package com.eaglec.plat.dao.impl.business;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.business.OrderEvaluationDao;
import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.domain.business.OrderEvaluation;

@Repository
public class OrderEvaluationDaoImpl extends BaseDaoImpl<OrderEvaluation>
		implements OrderEvaluationDao {
	@Override
	public int add(OrderEvaluation o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public OrderEvaluation save(OrderEvaluation o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(OrderEvaluation o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public OrderEvaluation saveOrUpdate(OrderEvaluation o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public OrderEvaluation update(OrderEvaluation o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}
