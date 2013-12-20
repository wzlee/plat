package com.eaglec.plat.biz.impl.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.news.NewsBiz;
import com.eaglec.plat.biz.publik.FileManagerBiz;
import com.eaglec.plat.dao.news.NewsDao;
import com.eaglec.plat.domain.base.FileManager;
import com.eaglec.plat.domain.cms.News;

@Service
public class NewsBizImpl implements NewsBiz {

	@Autowired
	private NewsDao newsDao;
	@Autowired
	private FileManagerBiz fileManagerBiz;
	
	public News get(int id){
		return newsDao.get(id);	
	}
	
	public News addNews(News news) {
		return newsDao.addNews(news);
	};

	/**
	 * 删除新闻的同时删除新闻图片
	 * @author liuliping
	 * @since 2013-07-28
	 * 
	 * @param id 新闻对象id
	 * @
	 * */
	public void deleteNews(int id, String realpath) {
		News news = newsDao.get(id);
		String picture = news.getPicture();
		List<FileManager> list = fileManagerBiz.findByName(picture);
		if (list.size() > 0) {
			fileManagerBiz.deleteFileManager(list.get(0).getId());
		}
		newsDao.deleteNews(news);
	};

	public void updateNews(News news) {
		newsDao.updateNews(news);

	};

	public List<News> findNewsById(int newsNo) {
		return null;
	};

	public List<News> getList() {
		List<News> list = newsDao.getNewsList("from News");
		return list;
	}

	public List<News> getNewsList(int start, int limit) {
		List<News> list = newsDao.getNewsList(start, limit);
		return list;
	};
	public List<News> getNewsList(String hql, int start, int limit) {
		List<News> list = newsDao.getNewsList(hql, start, limit);
		return list;
	};
	public List<News> getNewsList(String hql) {
		List<News> list = newsDao.getNewsList(hql);
		return list;
	};
	
	public long getCount(String hql) {
		long total = newsDao.getCount("SELECT count(*) " + hql);
		return total;
	}

	/**
	 * 根据新闻分类cid，来查找出此类别的置顶新闻，并返回
	 * @param cid 新闻分类的id
	 * @return 置顶的新闻对象或者null
	 * */
	@Override
	public News getTopNews(Integer cid) {
		String hql = "from News where isTop = true and cid = " + cid;
		List<News> list = newsDao.getNewsList(hql);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取枢纽平台首页行业新闻信息
	 */
	@Override
	public List<News> getIndexNews(){
		String hql = "from News order by pubdate desc";
		return newsDao.findList(hql);
	}
	
	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取枢纽平台首页行业新闻信息(含分页)
	 */
	@Override
	public List<News> getIndexNews(int start, int limit){
		String hql = "from News order by pubdate desc";
		return newsDao.findList(hql, start, limit);
	}

	@Override
	public List<News> getHotNews(int start, int limit) {
		String hql = "from News order by hits desc";
		return newsDao.findList(hql, start, limit);
	}
}
