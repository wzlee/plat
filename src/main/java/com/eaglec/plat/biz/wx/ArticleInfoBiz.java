package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;

import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.view.JSONData;

public interface ArticleInfoBiz {
	ArticleInfo add(ArticleInfo articleInfo);

	ArticleInfo get(Serializable id);

	void delete(ArticleInfo articleInfo);

	List<ArticleInfo> findList(String title, int start, int limit);
	
	ArticleInfo update(ArticleInfo articleInfo);
	
	ArticleInfo merge(ArticleInfo articleInfo);

	void delete(String idStr);

	JSONData<ArticleInfo> query(String title, int start, int limit);
}
