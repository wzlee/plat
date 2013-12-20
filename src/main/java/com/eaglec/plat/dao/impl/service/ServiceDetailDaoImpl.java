package com.eaglec.plat.dao.impl.service;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.service.ServiceDetailDao;
import com.eaglec.plat.domain.service.ServiceDetail;

@Repository
public class ServiceDetailDaoImpl extends BaseDaoImpl<ServiceDetail> implements ServiceDetailDao {
	@Override
	public int add(ServiceDetail o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public ServiceDetail save(ServiceDetail o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(ServiceDetail o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public ServiceDetail saveOrUpdate(ServiceDetail o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public ServiceDetail update(ServiceDetail o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}
