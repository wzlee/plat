package com.eaglec.plat.biz.impl.wx;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.wx.WXNewsBiz;
import com.eaglec.plat.dao.wx.WXNewsDao;
import com.eaglec.plat.domain.wx.WXNews;
import com.eaglec.plat.view.JSONData;

@Service
public class WXNewsBizImpl implements WXNewsBiz {

	@Autowired
	WXNewsDao wXNewsDao;

	@Override
	public WXNews add(WXNews wXNews) {
		return wXNewsDao.save(wXNews);
	}

	@Override
	public WXNews get(Serializable id) {
		return wXNewsDao.get(id);
	}

	@Override
	public void delete(String idStr) {
		String hql = "DELETE FROM WXNews WHERE id IN(" + idStr + ")";
		wXNewsDao.executeHql(hql, null);
	}

	@Override
	public void delete(WXNews wXNews) {
		wXNewsDao.delete(wXNews);
	}

	@Override
	public List<WXNews> getAll() {
		String hql = "FROM WXNews";
		return wXNewsDao.findList(hql);
	}

	@Override
	public WXNews update(WXNews wXNews) {
		return wXNewsDao.update(wXNews);
	}

	@Override
	public List<WXNews> findList(String title, int start, int limit) {
		String hql = "FROM WXNews WHERE 1 = 1";
		if(!StringUtils.isEmpty(title)) {
			hql = hql + " AND title like '%" + title +"%'";
		}
		return wXNewsDao.findList(hql, start, limit);
	}

	@Override
	public List<Object> findArticleList(String title, int start, int limit) {
		String hql = "SELECT NEW com.eaglec.plat.domain.wx.Article(w.title, w.description, w.picture, w.id) FROM WXNews w ORDER BY w.pubdate DESC";
		return wXNewsDao.findObjects(hql);
	}

	@Override
	public JSONData<WXNews> query(String title, int start, int limit) {
		String hql = "FROM WXNews WHERE 1 = 1";
		if(!StringUtils.isEmpty(title)) {
			hql = hql + " AND title LIKE '%" + title + "%'";
		}
		return wXNewsDao.outJSONData(hql, start, limit);
	}
}
