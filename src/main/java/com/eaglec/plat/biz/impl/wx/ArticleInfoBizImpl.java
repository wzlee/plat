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
	
	public ArticleInfo merge(ArticleInfo articleInfo) {
		return articleInfoDao.merge(articleInfo);
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
		StringBuilder sb = new StringBuilder().append("SELECT NEW ArticleInfo(a) FROM ArticleInfo a WHERE 1 = 1");
		if(!StringUtils.isEmpty(title)) {
			sb.append(" AND a.title LIKE '%").append(title).append("%'");
		}
		sb.append("ORDER BY a.pubdate DESC");
		return articleInfoDao.outJSONData(sb.toString(), start, limit);
	}
}
