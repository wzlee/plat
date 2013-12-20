package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;

import com.eaglec.plat.domain.wx.AutoMessage;
import com.eaglec.plat.view.JSONData;

public interface AutoMessageBiz {

	List<AutoMessage> getByType(String msgType);

	List<AutoMessage> getAll();

	AutoMessage get(Serializable id);

	AutoMessage add(AutoMessage autoMsg);

	void delete(AutoMessage autoMsg);
	
	void delete(String idStr) throws org.hibernate.exception.ConstraintViolationException;

	AutoMessage update(AutoMessage autoMsg);

	AutoMessage findByReqKey(String name, String value);
	
	JSONData<AutoMessage> query(String reqKey, String clickKey, int start, int limit);

}
