package com.eaglec.plat.biz.impl.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.info.SenderGroupMessageRelationshipBiz;
import com.eaglec.plat.dao.info.SenderGroupMessageRelationshipDao;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.info.SenderGroupMessageRelationship;
import com.eaglec.plat.view.JSONData;


@Service
public class SenderGroupMessageRelationshipBizImpl implements SenderGroupMessageRelationshipBiz{
	
	@Autowired
	private SenderGroupMessageRelationshipDao senderMessageRelationshipDao;
	
	//添加
	public SenderGroupMessageRelationship save(SenderGroupMessageRelationship sendergroupmessagerelationship){
		return senderMessageRelationshipDao.save(sendergroupmessagerelationship);
	}
	//查询
	public List<SenderGroupMessageRelationship> find(){
		return senderMessageRelationshipDao.findList("from SenderGroupMessageRelationship s"+" order by s.message.sendTime desc");
	}
	//修改
	public SenderGroupMessageRelationship update(SenderGroupMessageRelationship sendergroupmessagerelationship){
		return senderMessageRelationshipDao.update(sendergroupmessagerelationship);
	}
	//删除
	public void delete(SenderGroupMessageRelationship sendergroupmessagerelationship){
		senderMessageRelationshipDao.delete(sendergroupmessagerelationship);
	}
	@Override
	public JSONData<Object> find(Manager manger,Integer type,int start,int limit) {
		return senderMessageRelationshipDao.outJSONDataO("select s.message,s.receiver from SenderGroupMessageRelationship s where s.sendManagerId="+manger.getId()+" and s.deleteSign="+type+" order by s.message.sendTime desc",start,limit);

	}
	@Override
	public int update(Manager manager, String ids) {
		return senderMessageRelationshipDao.executeHql("update SenderGroupMessageRelationship s set s.deleteSign = 1 where s.sendManagerId="+manager.getId()+" and s.message.id in("+ids+")",null);
	}
	
}
