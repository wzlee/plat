package com.eaglec.plat.biz.impl.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.info.MessageBiz;
import com.eaglec.plat.dao.info.MessageDao;
import com.eaglec.plat.domain.info.Message;


@Service
public class MessageBizImpl implements MessageBiz{
	
	@Autowired
	private MessageDao messageDao;
	
	//添加
	public Message save(Message message){
		return messageDao.save(message);
	}
	//查询
	public List<Message> find(){
		return messageDao.findList("from Message");
	}
	//修改
	public Message update(Message message){
		return messageDao.update(message);
	}
	//删除
	public void delete(Message message){
		messageDao.delete(message);
	}
	
}
