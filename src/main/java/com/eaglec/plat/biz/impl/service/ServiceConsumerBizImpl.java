package com.eaglec.plat.biz.impl.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.eaglec.plat.biz.service.ServiceConsumerBiz;
import com.eaglec.plat.dao.service.ServiceConsumerDao;
import com.eaglec.plat.domain.service.ServiceConsumer;

@org.springframework.stereotype.Service
public class ServiceConsumerBizImpl implements ServiceConsumerBiz {

	@Autowired
	private ServiceConsumerDao serviceConsumerDao;
	

	@Override
	public void save(ServiceConsumer serviceConsumer) {
		// TODO Auto-generated method stub
		serviceConsumerDao.save(serviceConsumer);
	}

}
