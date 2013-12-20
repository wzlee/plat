package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.domain.wx.ReceiveInfo;

public interface ReceiveBiz {

	ReceiveInfo save(ReceiveInfo o);

	List<ReceiveInfo> save(Iterable<ReceiveInfo> its);

	void delete(ReceiveInfo o);

	void delete(Serializable id);

	ReceiveInfo update(ReceiveInfo o);

	void delete(Iterable<ReceiveInfo> its);

	ReceiveInfo saveOrUpdate(ArticleInfo o);

	ReceiveInfo get(Serializable id);

	ReceiveInfo get(String hql);

	ReceiveInfo get(String hql, Map<String, Object> params);

	List<Object> findObjects(String hql);

	List<ReceiveInfo> findList(String hql);
}
