package com.eaglec.plat.biz.impl.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.info.SenderMessageRelationshipBiz;
import com.eaglec.plat.dao.info.SenderMessageRelationshipDao;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.info.SenderMessageRelationship;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;


@Service
public class SenderMessageRelationshipBizImpl implements SenderMessageRelationshipBiz{
	
	@Autowired
	private SenderMessageRelationshipDao senderMessageRelationshipDao;
	
	//添加
	public SenderMessageRelationship save(SenderMessageRelationship sendermessagerelationship){
		return senderMessageRelationshipDao.save(sendermessagerelationship);
	}
	//查询
	public List<SenderMessageRelationship> find(){
		return senderMessageRelationshipDao.findList("from SenderMessageRelationship s"+" order by s.message.sendTime desc");
	}
	//修改
	public SenderMessageRelationship update(SenderMessageRelationship sendermessagerelationship){
		return senderMessageRelationshipDao.update(sendermessagerelationship);
	}
	//删除
	public void delete(SenderMessageRelationship sendermessagerelationship){
		senderMessageRelationshipDao.delete(sendermessagerelationship);
	}
	@Override
	public JSONData<Object> find(Manager manger,Integer type,int start,int limit) {
		return senderMessageRelationshipDao.outJSONDataO("select s.message,s.receiver from SenderMessageRelationship s where s.sendManagerId="+manger.getId()+" and s.deleteSign="+type+" order by s.message.sendTime desc",start,limit);

	}
	@Override
	public JSONRows<Object> find(User user,Integer type,int start,int limit) {
		/*List<Message> list = new ArrayList<Message>();
		List<Object> obj = senderMessageRelationshipDao.findObjects("select message from SenderMessageRelationship where sendUserId="+user.getId()+" and deleteSign="+type);
		for(Object o :obj){
			list.add((Message)o);
		}
		return null;*/
		return senderMessageRelationshipDao.outJSONRowsO("select s.message ,s.receiver from SenderMessageRelationship s where s.sendUserId="+user.getId()+" and s.deleteSign="+type+" order by s.message.sendTime desc",start,limit);
	}
	@Override
	public JSONRows<Object> find(Staff staff,Integer type,int start,int limit) {
		/*List<Message> list = new ArrayList<Message>();
		List<Object> obj = senderMessageRelationshipDao.findObjects("select message from SenderMessageRelationship where sendStaffId="+staff.getId()+" and deleteSign="+type);
		for(Object o :obj){
			list.add((Message)o);
		}
		return null;*/
		return senderMessageRelationshipDao.outJSONRowsO("select s.message ,s.receiver from SenderMessageRelationship s where s.sendStaffId="+staff.getId()+" and s.deleteSign="+type+" order by s.message.sendTime desc",start,limit);
	}
	@Override
	public int update(Manager manager, String ids) {
		return senderMessageRelationshipDao.executeHql("update SenderMessageRelationship s set s.deleteSign = 1 where s.sendManagerId="+manager.getId()+" and s.message.id in("+ids+")",null);
	}
	@Override
	public int update(User user, String ids) {
		return senderMessageRelationshipDao.executeHql("update SenderMessageRelationship s set s.deleteSign = 1 where s.sendUserId="+user.getId()+" and s.message.id in("+ids+")",null);
	}
	@Override
	public int update(Staff staff, String ids) {
		return senderMessageRelationshipDao.executeHql("update SenderMessageRelationship s set s.deleteSign = 1 where s.sendStaffId="+staff.getId()+" and s.message.id in("+ids+")",null);
	}
	
}
