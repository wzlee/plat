package com.eaglec.plat.biz.impl.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.wx.ReceiveBiz;
import com.eaglec.plat.dao.wx.ReceiveDao;
import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.domain.wx.ReceiveInfo;

@Service
public class ReceiveBizImpl implements ReceiveBiz {
	@Autowired
	ReceiveDao receiveDao;

	@Override
	public ReceiveInfo save(ReceiveInfo o) {
		return receiveDao.save(o);
	}

	@Override
	public List<ReceiveInfo> save(Iterable<ReceiveInfo> its) {
		return receiveDao.save(its);
	}

	@Override
	public void delete(ReceiveInfo o) {
		receiveDao.delete(o);
	}

	@Override
	public void delete(Serializable id) {
		receiveDao.delete(id);
	}

	@Override
	public void delete(Iterable<ReceiveInfo> its) {
		receiveDao.delete(its);
	}

	@Override
	public ReceiveInfo update(ReceiveInfo o) {
		return receiveDao.update(o);
	}

	@Override
	public ReceiveInfo get(Serializable id) {
		return receiveDao.get(id);
	}

	@Override
	public ReceiveInfo get(String hql) {
		return receiveDao.get(hql);
	}

	@Override
	public ReceiveInfo get(String hql, Map<String, Object> params) {
		return receiveDao.get(hql, params);
	}

	@Override
	public List<Object> findObjects(String hql) {
		return receiveDao.findObjects(hql);
	}

	@Override
	public List<ReceiveInfo> findList(String hql) {
		return receiveDao.findList(hql);
	}

	@Override
	public ReceiveInfo saveOrUpdate(ArticleInfo o) {
		return null;
	}

}
