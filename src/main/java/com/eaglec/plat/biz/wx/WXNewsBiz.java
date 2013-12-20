package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;

import com.eaglec.plat.domain.wx.WXNews;
import com.eaglec.plat.view.JSONData;

public interface WXNewsBiz {

	WXNews add(WXNews wXNews);

	WXNews get(Serializable id);

	void delete(WXNews wXNews);

	List<WXNews> getAll();

	List<WXNews> findList(String title, int start, int limit);
	
	/**
	 * 分页获取平台动态的图文信息
	 * @author liuliping
	 * @param title 标题
	 * @param start 分页起始
	 * @param limit 单页结果数量
	 * @since 2013-12-16
	 * @return
	 * */
	List<Object> findArticleList(String title, int start, int limit);

	WXNews update(WXNews wXNews);

	void delete(String idStr);

	JSONData<WXNews> query(String title, int start, int limit);
}
