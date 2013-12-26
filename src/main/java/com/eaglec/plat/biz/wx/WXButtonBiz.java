package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;

import com.eaglec.plat.domain.wx.WXButton;
import com.eaglec.plat.view.JSONData;

public interface WXButtonBiz {

	WXButton add(WXButton wXButton);

	WXButton get(Serializable id);

	void delete(WXButton wXButton);

	List<WXButton> getAll();

	List<WXButton> findList(String title, int start, int limit);
	
	WXButton update(WXButton wXButton);

	void delete(String idStr);

	JSONData<WXButton> query(String title, int start, int limit);
}
