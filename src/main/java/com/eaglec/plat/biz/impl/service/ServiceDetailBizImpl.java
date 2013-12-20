package com.eaglec.plat.biz.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.service.ServiceDetailBiz;
import com.eaglec.plat.dao.service.ServiceDetailDao;
import com.eaglec.plat.domain.service.ServiceDetail;

@Service
public class ServiceDetailBizImpl implements ServiceDetailBiz {

	@Autowired
	private ServiceDetailDao serviceDetailDao;
	
	@Override
	public ServiceDetail create(ServiceDetail sd) {
		return serviceDetailDao.save(sd);
	}

}
