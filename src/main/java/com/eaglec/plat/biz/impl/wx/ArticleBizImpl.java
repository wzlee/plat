package com.eaglec.plat.biz.impl.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.wx.ArticleBiz;
import com.eaglec.plat.dao.wx.ArticleDao;
import com.eaglec.plat.domain.wx.ArticleInfo;

@Service
public class ArticleBizImpl implements ArticleBiz {
	@Autowired
	ArticleDao articleDao;

	@Override
	public ArticleInfo save(ArticleInfo o) {
		return articleDao.save(o);
	}

	@Override
	public List<ArticleInfo> save(Iterable<ArticleInfo> its) {
		return articleDao.save(its);
	}

	@Override
	public void delete(ArticleInfo o) {
		articleDao.delete(o);
	}

	@Override
	public void delete(Serializable id) {
		articleDao.delete(id);
	}

	@Override
	public void delete(Iterable<ArticleInfo> its) {
		articleDao.delete(its);
	}

	@Override
	public ArticleInfo update(ArticleInfo o) {
		return articleDao.update(o);
	}

	@Override
	public ArticleInfo saveOrUpdate(ArticleInfo o) {
		return articleDao.saveOrUpdate(o);
	}

	@Override
	public ArticleInfo get(Serializable id) {
		return articleDao.get(id);
	}

	@Override
	public ArticleInfo get(String hql) {
		return articleDao.get(hql);
	}

	@Override
	public ArticleInfo get(String hql, Map<String, Object> params) {
		return articleDao.get(hql, params);
	}

	@Override
	public List<Object> findObjects(String hql) {
		return articleDao.findObjects(hql);
	}

	@Override
	public List<ArticleInfo> findList(String hql) {
		return articleDao.findList(hql);
	}

	@Override
	public List<ArticleInfo> findList(String hql, Map<String, Object> params) {
		return articleDao.findList(hql, params);
	}

}
