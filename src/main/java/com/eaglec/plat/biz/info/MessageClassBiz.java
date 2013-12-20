package com.eaglec.plat.biz.info;

import java.util.List;

import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;


public interface MessageClassBiz {
	//添加
	public MessageClass save(MessageClass messageclass);
	//查询
	public JSONData<MessageClass> findAll(int start ,int limit);
	public List<MessageClass> find();
	//分页查询
	public JSONRows<MessageClass> find(int start,int limit);
	//根据id查询
	public MessageClass find(Integer id);
	//修改
	public MessageClass update(MessageClass messageclass);
	//删除
	public void delete(Integer id);
	//根据类别名称返回是否已经存在同意的消息类型    
	/**
	 * 真表示存在，假表示不存在
	 * @param messageType
	 * @return
	 */
	public boolean exist(String messageType);
	//根据messagetype查找
	public MessageClass find(String messageType);
}
