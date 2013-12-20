package com.eaglec.plat.biz.mobileApp;

import com.eaglec.plat.domain.mobileApp.AppMessage;
import com.eaglec.plat.view.JSONData;

public interface AppMessageBiz {
	
	/**
	 * 保存app推送的消息
	 * @author liuliping
	 * @since 2013-12-04
	 * @param appMessage 推送的消息对象
	 * @return
	 * */
	int save(AppMessage appMessage);
	
	/**
	 * 分页查询app历史推送消息
	 * @author liuliping
	 * @since 2013-12-04
	 * @param start 分页起始条数
	 * @param limit 分页大小
	 * @param title 消息标题
	 * @return 推送的历史消息集合
	 * */
	JSONData<AppMessage> find(int start, int limit, String title);
	
	/**
	 * 根据id返回消息实
	 * */
	AppMessage get(Integer id);
	
	void update(AppMessage appMessage);
	
	/**
	 * 删除历史消息
	 * * 
	 * @param idStr id的字符串形式"1,2,3,4...10,11" 
	 * @param jsonObject 
	 * @throws Exception */
	void removeMessages(String idStr) throws Exception;
}
