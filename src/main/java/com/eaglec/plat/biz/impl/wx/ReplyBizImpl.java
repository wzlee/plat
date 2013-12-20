package com.eaglec.plat.biz.impl.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.wx.ReplyBiz;
import com.eaglec.plat.dao.wx.ReplyDao;
import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.domain.wx.ReplyInfo;

@Service
public class ReplyBizImpl implements ReplyBiz {
	@Autowired
	ReplyDao replyDao;

	@Override
	public ReplyInfo save(ReplyInfo o) {
		return replyDao.save(o);
	}

	@Override
	public List<ReplyInfo> save(Iterable<ReplyInfo> its) {
		return replyDao.save(its);
	}

	@Override
	public void delete(ReplyInfo o) {
		replyDao.delete(o);
	}

	@Override
	public void delete(Serializable id) {
		replyDao.delete(id);
	}

	@Override
	public void delete(Iterable<ReplyInfo> its) {
		replyDao.delete(its);
	}

	@Override
	public ReplyInfo update(ReplyInfo o) {
		return replyDao.update(o);
	}

	@Override
	public ReplyInfo get(Serializable id) {
		return replyDao.get(id);
	}

	@Override
	public ReplyInfo get(String hql) {
		return replyDao.get(hql);
	}

	@Override
	public ReplyInfo get(String hql, Map<String, Object> params) {
		return replyDao.get(hql, params);
	}

	@Override
	public List<Object> findObjects(String hql) {
		return replyDao.findObjects(hql);
	}

	@Override
	public List<ReplyInfo> findList(String hql) {
		return replyDao.findList(hql);
	}

	@Override
	public ReplyInfo saveOrUpdate(ArticleInfo o) {
		return null;
	}

}
