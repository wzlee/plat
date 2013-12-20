package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.domain.wx.ReplyInfo;

public interface ReplyBiz {

	ReplyInfo save(ReplyInfo o);

	List<ReplyInfo> save(Iterable<ReplyInfo> its);

	void delete(ReplyInfo o);

	void delete(Serializable id);

	ReplyInfo update(ReplyInfo o);

	void delete(Iterable<ReplyInfo> its);

	ReplyInfo saveOrUpdate(ArticleInfo o);

	ReplyInfo get(Serializable id);

	ReplyInfo get(String hql);

	ReplyInfo get(String hql, Map<String, Object> params);

	List<Object> findObjects(String hql);

	List<ReplyInfo> findList(String hql);
}
