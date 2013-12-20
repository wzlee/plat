package com.eaglec.plat.biz.impl.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.info.MessageClassBiz;
import com.eaglec.plat.dao.info.MessageClassDao;
import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;


@Service
public class MessageClassBizImpl implements MessageClassBiz{
	
	@Autowired
	private MessageClassDao messageClassDao;
	
	//添加
	public MessageClass save(MessageClass messageclass){
		return messageClassDao.save(messageclass);
	}
	//查询
	public JSONData<MessageClass> findAll(int start ,int limit){
		return messageClassDao.outJSONData("from MessageClass order by id desc", start, limit);
	}
	//查询
	public List<MessageClass> find(){
		return messageClassDao.findList("from MessageClass order by id desc");
	}
	//修改
	public MessageClass update(MessageClass messageclass){
		return messageClassDao.update(messageclass);
	}
	//删除
	public void delete(Integer id){
		messageClassDao.delete(id);
	}
	@Override
	public boolean exist(String messageType) {
		// TODO Auto-generated method stub
		List<MessageClass> list = messageClassDao.findList("from MessageClass where messageType='"+messageType+"'");
		return list!=null && list.size()!=0;
	}
	@Override
	public MessageClass find(Integer id) {
		// TODO Auto-generated method stub
		return messageClassDao.get(id);
	}
	@Override
	public JSONRows<MessageClass> find(int start, int limit) {
		// TODO Auto-generated method stub
		return messageClassDao.outJSONRows("from MessageClass order by id desc", start, limit);
	}
	@Override
	public MessageClass find(String messageType) {
		List<MessageClass> list = messageClassDao.findList("from MessageClass where messageType='"+messageType+"'");
		return (list == null || list.size() == 0) ? null : list.get(0);
	}
	
}
