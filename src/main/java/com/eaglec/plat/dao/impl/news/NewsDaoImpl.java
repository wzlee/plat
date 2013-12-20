package com.eaglec.plat.dao.impl.news;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.news.NewsDao;
import com.eaglec.plat.domain.cms.News;


@Repository
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {

	@Override
	public News get(int id) {
		return super.get(id);
	}

	@Override
	public News addNews(News news) {
		return super.save(news);
	}

	@Override
	public void deleteNews(News news) {
		super.delete(news);
	}

	@Override
	public void updateNews(News news) {
		super.update(news);
	}

	@Override
	public List<News> findNewsById(int newsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> getNewsList(int start, int limit) {
//		List<Object> params = new ArrayList<Object>();
//		FIXME
//		
//		params.add(start);
//		params.add(limit);
//		List<News> list =super.querynewsListbyprocedure("CALL query_news(?,?)",params.toArray());
//		return list;
		return null;
	}
	public List<News> getNewsList(String hql, int start, int limit) {
		List<News> list =super.findList(hql, start, limit);
		return list;
	}

	@Override
	public List<News> getNewsList(String hql) {
		// TODO Auto-generated method stub
		List<News> list = super.findList(hql);
		return list;
	}

	@Override
	public long getCount(String hql) {
		long total = super.count(hql);
		return total;
	}

}
