package com.eaglec.plat.biz.impl.wx;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.wx.ArticleInfoBiz;
import com.eaglec.plat.dao.wx.ArticleInfoDao;
import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.view.JSONData;

@Service
public class ArticleInfoBizImpl implements ArticleInfoBiz {

	@Autowired
	private ArticleInfoDao articleInfoDao;

	public ArticleInfo add(ArticleInfo articleInfo) {
		return articleInfoDao.save(articleInfo);
	}

	@Override
	public ArticleInfo get(Serializable id) {
		return articleInfoDao.get(id);
	}

	@Override
	public void delete(String idStr) {
		String hql = "DELETE FROM ArticleInfo WHERE id IN(" + idStr + ")";
		articleInfoDao.executeHql(hql, null);
	}

	@Override
	public void delete(ArticleInfo articleInfo) {
		articleInfoDao.delete(articleInfo);
	}


	@Override
	public ArticleInfo update(ArticleInfo articleInfo) {
		return articleInfoDao.update(articleInfo);
	}
	
	public ArticleInfo merge  (ArticleInfo articleInfo) {
		return articleInfoDao.update(articleInfo);
	}

	@Override
	public List<ArticleInfo> findList(String title, int start, int limit) {
		String hql = "FROM ArticleInfo WHERE 1 = 1";
		if(!StringUtils.isEmpty(title)) {
			hql = hql + " AND title like '%" + title +"%'";
		}
		return articleInfoDao.findList(hql, start, limit);
	}

	@Override
	public JSONData<ArticleInfo> query(String title, int start, int limit) {
		String hql = "SELECT NEW ArticleInfo(a) FROM ArticleInfo a WHERE 1 = 1";
		if(!StringUtils.isEmpty(title)) {
			hql = hql + " AND title LIKE '%" + title + "%'";
		}
		return articleInfoDao.outJSONData(hql, start, limit);
	}
}
