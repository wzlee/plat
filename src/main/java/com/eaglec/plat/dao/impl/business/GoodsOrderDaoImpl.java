package com.eaglec.plat.dao.impl.business;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.business.GoodsOrderDao;
import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.domain.business.GoodsOrder;

@Repository
public class GoodsOrderDaoImpl extends BaseDaoImpl<GoodsOrder> implements GoodsOrderDao {
	@Override
	public int add(GoodsOrder o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public GoodsOrder save(GoodsOrder o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(GoodsOrder o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public GoodsOrder saveOrUpdate(GoodsOrder o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public GoodsOrder update(GoodsOrder o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}
