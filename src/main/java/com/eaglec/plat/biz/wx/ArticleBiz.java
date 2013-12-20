package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.eaglec.plat.domain.wx.ArticleInfo;

public interface ArticleBiz {

	ArticleInfo save(ArticleInfo o);

	List<ArticleInfo> save(Iterable<ArticleInfo> its);

	void delete(ArticleInfo o);

	void delete(Serializable id);

	ArticleInfo update(ArticleInfo o);

	void delete(Iterable<ArticleInfo> its);

	ArticleInfo saveOrUpdate(ArticleInfo o);

	ArticleInfo get(Serializable id);

	ArticleInfo get(String hql);

	ArticleInfo get(String hql, Map<String, Object> params);

	List<Object> findObjects(String hql);

	List<ArticleInfo> findList(String hql);

	List<ArticleInfo> findList(String hql, Map<String, Object> params);
}
