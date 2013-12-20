package com.eaglec.plat.biz.service;

import com.eaglec.plat.domain.service.ServiceConsumer;

/**
 * 预约服务客户信息<br/>
 * 封装对客户信息的操作
 * 
 * @author liuliping
 * @since 2013-11-28
 * 
 */
public interface ServiceConsumerBiz {
	public abstract void save(ServiceConsumer serviceConsumer);
}