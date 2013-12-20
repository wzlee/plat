package com.eaglec.plat.biz.news;

import java.util.List;

import com.eaglec.plat.domain.cms.News;

public interface NewsBiz {
	public News get(int id);

	public News addNews(News oh);

	public void deleteNews(int id, String realpath);

	public void updateNews(News news);

	public List<News> findNewsById(int newsNo);

	public List<News> getList();
	
	public List<News> getNewsList(int start, int limit);

	public List<News> getNewsList(String hql, int start, int limit);

	public long getCount(String hql);

	public List<News> getNewsList(String hql);

	public News getTopNews(Integer cid);
	
	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取枢纽平台首页行业新闻信息
	 */
	public List<News> getIndexNews();
	
	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取枢纽平台首页行业新闻信息(含分页功能)
	 */
	public List<News> getIndexNews(int start, int limit);

	/**
	 * 新闻热点
	 * @author Xiadi
	 * @since 2013-10-29 
	 *
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<News> getHotNews(int start, int limit);

}
