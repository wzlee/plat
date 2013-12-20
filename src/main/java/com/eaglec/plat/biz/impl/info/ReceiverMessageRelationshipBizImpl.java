package com.eaglec.plat.biz.impl.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
import com.eaglec.plat.dao.info.ReceiverMessageRelationshipDao;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONRows;


@Service
public class ReceiverMessageRelationshipBizImpl implements ReceiverMessageRelationshipBiz{
	
	@Autowired
	private ReceiverMessageRelationshipDao receiverMessageRelationshipDao;
	
	//添加
	public ReceiverMessageRelationship save(ReceiverMessageRelationship receivermessagerelationship){
		return receiverMessageRelationshipDao.save(receivermessagerelationship);
	}
	//查询
	public List<ReceiverMessageRelationship> find(){
		return receiverMessageRelationshipDao.findList("from ReceiverMessageRelationship r"+" order by r.message.sendTime desc");
	}
	//修改
	public ReceiverMessageRelationship update(ReceiverMessageRelationship receivermessagerelationship){
		return receiverMessageRelationshipDao.update(receivermessagerelationship);
	}
	//删除
	public void delete(ReceiverMessageRelationship receivermessagerelationship){
		receiverMessageRelationshipDao.delete(receivermessagerelationship);
	}
	@Override
	public JSONRows<Object> find(User user, Integer type, int start, int limit) {
		return receiverMessageRelationshipDao.outJSONRowsO("select r.message from ReceiverMessageRelationship r where r.receiverUserId="+user.getId()+" and r.deleteSign="+type+" order by r.message.sendTime desc",start,limit);
	}
	@Override
	public JSONRows<Object> find(Staff staff, Integer type, int start, int limit) {
		return receiverMessageRelationshipDao.outJSONRowsO("select r.message from ReceiverMessageRelationship r where r.receiverStaffId="+staff.getId()+" and r.deleteSign="+type+" order by r.message.sendTime desc",start,limit);
	}
	@Override
	public JSONRows<Object> findtype(User user, Integer type, Integer messageType,int start, int limit) {
		if(!StringUtils.isEmpty(messageType)){
			return receiverMessageRelationshipDao.outJSONRowsO("select r.message,r.readSign ,r.sender from ReceiverMessageRelationship r where r.receiverUserId="+user.getId()+" and r.deleteSign="+type+" and r.message.messageClass.id = "+messageType+" order by r.message.sendTime desc",start,limit);
		}else{
			return receiverMessageRelationshipDao.outJSONRowsO("select r.message,r.readSign ,r.sender from ReceiverMessageRelationship r where r.receiverUserId="+user.getId()+" and r.deleteSign="+type+" order by r.message.sendTime desc",start,limit);
		}
	}
	@Override
	public JSONRows<Object> findtype(Staff staff, Integer type, Integer messageType, int start, int limit) {
		if(!StringUtils.isEmpty(messageType)){
			return receiverMessageRelationshipDao.outJSONRowsO("select r.message,r.readSign ,r.sender from ReceiverMessageRelationship r where r.receiverStaffId="+staff.getId()+" and r.deleteSign="+type+" and r.message.messageClass.id = "+messageType+" order by r.message.sendTime desc",start,limit);
		}else{
			return receiverMessageRelationshipDao.outJSONRowsO("select r.message,r.readSign ,r.sender from ReceiverMessageRelationship r where r.receiverStaffId="+staff.getId()+" and r.deleteSign="+type+" order by r.message.sendTime desc",start,limit);
		}
	}
	//设置已删除
	@Override
	public int update(User user, String ids) {
		// TODO Auto-generated method stub
		return receiverMessageRelationshipDao.executeHql("update ReceiverMessageRelationship s set s.deleteSign = 1 where s.receiverUserId="+user.getId()+" and s.message.id in("+ids+")",null);
	}
	@Override
	public int update(Staff staff, String ids) {
		// TODO Auto-generated method stub
		return receiverMessageRelationshipDao.executeHql("update ReceiverMessageRelationship s set s.deleteSign = 1 where s.receiverStaffId="+staff.getId()+" and s.message.id in("+ids+")",null);
	}
	
	//设置已读
	@Override
	public int updateyetRead(User user, Integer id) {
		return receiverMessageRelationshipDao.executeHql("update ReceiverMessageRelationship s set s.readSign = 1 where s.receiverUserId="+user.getId()+" and s.message.id ="+id+")",null);
	}
	@Override
	public int updateyetRead(Staff staff, Integer id) {
		return receiverMessageRelationshipDao.executeHql("update ReceiverMessageRelationship s set s.readSign = 1 where s.receiverStaffId="+staff.getId()+" and s.message.id ="+id+")",null);
	}
	@Override
	public int[] findMyMessage(Integer userId,Integer loginType) {
		String hql = "select sum(case when r.message.messageClass.id = "+Constant.MESSAGE_TYPE_SYSTEM
				+" then 1 else 0 end), sum(case when r.message.messageClass.id = "+Constant.MESSAGE_TYPE_NAME
				+ " then 1 else 0 end) from ReceiverMessageRelationship r where r.deleteSign = 0";
		//系统消息、交易消息 数据统计
		if(loginType == Constant.LOGIN_USER){
			hql += " and r.receiverUserId = "+userId;
		}else if(loginType == Constant.LOGIN_STAFF){
			hql += " and r.receiverStaffId = "+userId;
		}
		Object[] o = (Object[])receiverMessageRelationshipDao.findObjects(hql).get(0);
		int[] ret = new int[2];
		for (int i = 0; i < o.length; i++) {
			ret[i] = StrUtils.toInt(o[i]);
		}
		return ret;
	}
	@Override
	public int countUnreadMessage(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("select r.message from ReceiverMessageRelationship r where r.receiverUserId = ").append(user.getId()).
			append(" and r.deleteSign = ").append(0).append(" and r.readSign = ").append(0);
		
		return receiverMessageRelationshipDao.findObjects(sb.toString()).size();
	}
	@Override
	public int countUnreadMessage(Staff staff) {
		StringBuilder sb = new StringBuilder();
		sb.append("select r.message from ReceiverMessageRelationship r where r.receiverStaffId = ").append(staff.getId()).
		append(" and r.deleteSign = ").append(0).append(" and r.readSign = ").append(0);
	
		return receiverMessageRelationshipDao.findObjects(sb.toString()).size();
	}
	
}
