package com.eaglec.plat.biz.impl.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.business.OrderOperateLogBiz;
import com.eaglec.plat.dao.business.OrderOperateLogDao;
import com.eaglec.plat.domain.business.OrderOperateLog;

@Service
public class OrderOperateLogBizImpl implements OrderOperateLogBiz {
	@Autowired
	private OrderOperateLogDao orderOperateLogDao;
	
	@Override
	public void saveOrderOperLog(OrderOperateLog orderOperateLog) {
		orderOperateLogDao.save(orderOperateLog);
	}

}
